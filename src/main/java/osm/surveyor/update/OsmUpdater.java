package osm.surveyor.update;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import osm.surveyor.osm.ElementBounds;
import osm.surveyor.osm.ElementMember;
import osm.surveyor.osm.ElementNode;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementTag;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.WayMap;
import osm.surveyor.osm.api.GetResponse;
import osm.surveyor.osm.api.HttpGet;
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
        try {
			File dir = new File(".");
			Files.list(dir.toPath()).forEach(new Consumer<Path>() {
				@Override
				public void accept(Path a) {
					if (Files.isRegularFile(a)) {
						File file = a.toFile();
						String filename = file.getName();
						System.out.println(filename);
						if (filename.endsWith(suffix1) && !filename.endsWith(suffix2)) {
							try {
								OsmUpdater updater = new OsmUpdater(file);
								updater.load();
								filename = filename.substring(0, filename.length() - suffix1.length());
								updater.ddom.export(Paths.get(filename + suffix2).toFile());
								updater.sdom.export(Paths.get(filename + ".org.osm").toFile());
							} catch (ParserConfigurationException e) {
								e.printStackTrace();
							} catch (SAXException e) {
								e.printStackTrace();
							} catch (IOException e) {
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
	
	OsmDom dom;		// source "*.osm" file.	
	OsmDom sdom;
	OsmDom ddom;
	
	public OsmUpdater(String filepath) throws ParserConfigurationException, SAXException, IOException {
		this(Paths.get(filepath).toFile());
	}

	public OsmUpdater(File file) throws ParserConfigurationException, SAXException, IOException {
		dom = new OsmDom();
		dom.load(file);
	}
	
	/**
	 * dom
	 * @throws MalformedURLException
	 * @throws ProtocolException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	public void load() throws MalformedURLException, ProtocolException, IOException, ParserConfigurationException, SAXException {
		// 指定されたOSMファイルの<bound/>を取得する
		ElementBounds bounds = dom.getBounds();

		sdom = new OsmDom();
		sdom.setBounds(bounds);
		
		// エクスポート用のOsmDomを生成する
		ddom = new OsmDom();
		ddom.setBounds(bounds);

		// OSMから<bound>範囲内の現在のデータを取得する
		Document sdoc = loadMap(bounds);
		sdom.load(sdoc);
		sdom.export(Paths.get("org.xml").toFile());
		
		sdom = filterBuilding(sdom);
		
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
	 * 取得したデータからRELATION:buildingオブジェクトのみ抽出する
	 * @param sdom	抽出元
	 * @return	抽出された新しいインスタンス
	 */
	private OsmDom filterBuilding(OsmDom sdom) {
		OsmDom ddom = new OsmDom();
		ddom.setBounds(sdom.getBounds());

		// 取得したデータからRELATION:buildingオブジェクトのみ抽出する
		for (String rKey : sdom.relations.keySet()) {
			ElementRelation relation = sdom.relations.get(rKey);
			if (relation.isBuilding()) {
				for (ElementMember member : relation.members) {
					if (member.type.equals("relation")) {
						ElementRelation polygon = sdom.relations.get(member.ref);
						if (polygon != null) {
							ddom.relations.put(polygon.clone());
						}
					}
				}
				ddom.relations.put(rKey, relation.clone());
			}
		}
		for (String rKey : ddom.relations.keySet()) {
			ElementRelation relation = ddom.relations.get(rKey);
			for (ElementMember menber : relation.members) {
				ElementWay sWay = sdom.ways.get(menber.ref);
				if (sWay != null) {
					ddom.ways.put(sWay.clone());
				}
			}
		}
		return ddom;
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

	/**
	 *  OSMから<bound>範囲内の現在のデータを取得する
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws ProtocolException 
	 * @throws MalformedURLException 
	 */
	public static Document loadMap(ElementBounds bounds) throws MalformedURLException, ProtocolException, IOException, ParserConfigurationException, SAXException {
        double minlon = Double.parseDouble(bounds.minlon);
		double maxlon = Double.parseDouble(bounds.maxlon);
		double minlat = Double.parseDouble(bounds.minlat);
		double maxlat = Double.parseDouble(bounds.maxlat);
		HttpGet obj = new HttpGet();
		GetResponse res = obj.getMap(minlon, minlat, maxlon, maxlat);
		res.printout();
		return res.getBody();
	}
	
	public void export(File out) throws ParserConfigurationException {
		ddom.export(out);
	}
	
}
