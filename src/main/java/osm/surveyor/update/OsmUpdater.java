package osm.surveyor.update;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import osm.surveyor.osm.ElementBounds;
import osm.surveyor.osm.ElementMember;
import osm.surveyor.osm.ElementNode;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementTag;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.WayMap;
import osm.surveyor.sql.Postgis;

public class OsmUpdater {

	/**
	 * 指定されたOSMファイルの既存のOSMデータを取得する
	 * 
	 * @param args	アップデートしたいOSMデータファイル
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		String suffix1 = ".osm";
		String suffix2 = ".mrg.osm";
		String suffix3 = ".org.osm";
        try {
			File dir = new File(".");
			Files.list(dir.toPath()).forEach(new Consumer<Path>() {
				@Override
				public void accept(Path a) {
					if (Files.isRegularFile(a)) {
						File file = a.toFile();
						String filename = file.getName();
						System.out.println(filename);
						if (filename.endsWith(suffix1) && !filename.endsWith(suffix2) && !filename.endsWith(suffix3)) {
							try {
								OsmUpdater updater = new OsmUpdater(file);
								updater.download();
								updater.load();
								filename = filename.substring(0, filename.length() - suffix1.length());
								updater.ddom.export(Paths.get(filename + suffix2).toFile());
								updater.sdom.export(Paths.get(filename + suffix3).toFile());
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			});
            
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public OsmDom dom;		// source "*.osm" file.	
	public OsmDom sdom;
	public OsmDom ddom;
	
	public OsmUpdater(String filepath) throws ParserConfigurationException, SAXException, IOException, ParseException {
		this(Paths.get(filepath).toFile());
	}

	public OsmUpdater(File file) throws ParserConfigurationException, SAXException, IOException, ParseException {
		dom = new OsmDom();
		dom.parse(file);
	}
	
	/**
	 * OpenStreetMapから 既存データを収録する
	 * 
	 * @throws MalformedURLException
	 * @throws ProtocolException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	public void download() throws MalformedURLException, ProtocolException, IOException, ParserConfigurationException, SAXException {
		// 指定されたOSMファイルの<bound/>を取得する
		ElementBounds bounds = dom.getBounds();

		OsmDom org = new OsmDom();
		org.setBounds(bounds);
		
		// エクスポート用のOsmDomを生成する
		this.ddom = new OsmDom();
		this.ddom.setBounds(bounds);

		// OSMから<bound>範囲内の現在のデータを取得する
		org.downloadMap();
		
		// "building"関係のPOIのみに絞る
		this.sdom = new OsmDom();
		org.filterBuilding(this.sdom);
	}
	
	/**
	 * 既存POIとマージする
	 * 
	 * @throws MalformedURLException
	 * @throws ProtocolException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	public void load() throws MalformedURLException, ProtocolException, IOException, ParserConfigurationException, SAXException {
        try (Postgis db = new Postgis("osmdb")) {
            db.initTable();		// データベースの初期化
            
            // 既存データをPOSTGISへセットする
    		for (String rKey : sdom.ways.keySet()) {
    			ElementWay way = sdom.ways.get(rKey);
    			way.orignal = true;
    			way.insertTable(db);
    		}
            
			// インポートデータをPOSTGISへセットする
    		for (String rKey : dom.ways.keySet()) {
    			ElementWay way = dom.ways.get(rKey);
    			way.orignal = false;
    			way.insertTable(db);
    		}
            
    		// 既存データの内で、インポートデータと重複しないものを削除
    		ArrayList<ElementWay> list = new ArrayList<>();
    		for (String rKey : sdom.ways.keySet()) {
    			ElementWay way = sdom.ways.get(rKey);
    			if (!way.isIntersect(db, "WHERE (tWay.orignal=false)")) {
    				list.add(way);
    			}
    		}
    		for (ElementWay way : list) {
    			way.delete(db);
    			sdom.ways.remove(way.id);
    		}
    		
			// インポートデータのすべてのノードを'modify'に確定する
    		for (String rKey : dom.nodes.keySet()) {
    			ElementNode node = dom.nodes.get(rKey);
    			node.action = "modify";
    			node.orignal = false;
    			ddom.nodes.put(node.clone());
    		}

	    		// インポートデータの内で、既存データと重複しないものを'modify'に確定する
    		ArrayList<ElementWay> modifyList = new ArrayList<>();
    		for (String rKey : dom.ways.keySet()) {
    			ElementWay way = dom.ways.get(rKey);
    			if (!way.isIntersect(db, "WHERE (tWay.orignal=true)")) {
    				modifyList.add(way);
    			}
    		}
    		for (ElementWay way : modifyList) {
    			way.action = "modify";
    			way.orignal = false;
    			way.delete(db);
    			ddom.ways.put(way);
    		}
    		
    		// OverlappingMap:WayMap {key=dom.way.id, v=ddom.way}
    		WayMap overlappingMap = new WayMap();
    		
	    		// インポートデータの内で、既存データと重複するWAYを'modify'に確定する
    		for (String rKey : dom.ways.keySet()) {
    			ElementWay way = dom.ways.get(rKey).clone();
    			long wayid = way.getIntersect(db, "WHERE (tWay.orignal=true)");
    			if (wayid > 0) {
    				ElementWay sWay = sdom.ways.get(wayid);
    				sWay.action = "modify";
    				sWay.orignal = false;
    				sWay.nds = way.nds;
            		ElementTag tag = sWay.tags.get("fixme");
            		if (tag != null) {
            			String fixme = tag.v;
            			tag.v = fixme +"; PLATEAUデータで更新されています";
            		}
            		else {
            			tag = new ElementTag("fixme", "PLATEAUデータで更新されています");
            		}
            		sWay.addTag(tag);
    				way.copyTag(sWay);
            		sWay.copyTag(way);
            		sWay.delete(db);
            		ddom.ways.put(sWay);
            		overlappingMap.put(Long.toString(way.id), sWay);
    			}
    		}
    		
	    		// インポートデータの内で、既存データと重複するMultipolygonを'modify'に確定する
			// インポートデータの内で、既存データと重複するbuilding RELATIONを'modify'に確定する
    		for (String rKey : dom.relations.keySet()) {
    			ElementRelation relation = dom.relations.get(rKey);
    			if (relation.isMultipolygon()) {
        			for (ElementMember member : relation.members) {
						if (member.type.equals("way")) {
	        				ElementWay sWay = overlappingMap.get(member.ref);
	            			if (sWay != null) {
	                    		sWay.tags.toMultipolygonMember();
	            				member.ref = sWay.id;
	            			}
						}
        			}
    			}
    			else if (relation.isBuilding()) {
					for (ElementMember member : relation.members) {
						if (member.type.equals("way")) {
	        				ElementWay sWay = overlappingMap.get(member.ref);
	        				if (sWay != null) {
	            				member.ref = sWay.id;
	        				}
						}
					}
				}
				relation.action = "modify";
				ddom.relations.put(relation);
    		}
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * tag.key=`building*` を有するPOIを'building'POIとみなす
	 * 
	 */
	static boolean isBuildingTag(HashMap<String,ElementTag> tags) {
		for (String k : tags.keySet()) {
			ElementTag tag = tags.get(k);
			if (tag.k.startsWith("building")) {
				return true;
			}
		}
		return false;
	}

	public void export(File out) throws ParserConfigurationException {
		ddom.export(out);
	}
	
}
