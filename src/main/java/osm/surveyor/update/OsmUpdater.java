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
import osm.surveyor.osm.OsmNd;
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
								filename = filename.substring(0, filename.length() - suffix1.length());

								OsmUpdater updater = new OsmUpdater(file);
								updater.load();
								updater.ddom.export(Paths.get(filename + suffix2).toFile());
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

		// OSMから<bound>範囲内の現在のデータを取得する
		Document sdoc = this.loadMap(bounds);
		sdom.load(sdoc);
		
		ddom = getBuilding(sdom);
		
        try (Postgis db = new Postgis("osmdb")) {
            db.initTable();		// データベースの初期化
            
            // 既存データをPOSTGISへセットする
    		for (String rKey : ddom.ways.keySet()) {
    			ElementWay way = ddom.ways.get(rKey);
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
    		for (String rKey : ddom.ways.keySet()) {
    			ElementWay way = ddom.ways.get(rKey);
    			if (!way.isIntersect(db, "WHERE (tWay.orignal=false)")) {
    				list.add(way);
    			}
    		}
    		for (ElementWay way : list) {
    			way.delete(db);
    			ddom.ways.remove(way.id);
    		}

			// インポートデータの内で、既存データと重複しないものを'modify'に確定する
    		ArrayList<ElementWay> modifyList = new ArrayList<>();
    		for (String rKey : dom.ways.keySet()) {
    			ElementWay way = dom.ways.get(rKey);
    			if (!way.isIntersect(db, "WHERE (tWay.orignal=true) AND (tWay.member=false)")) {
    				modifyList.add(way);
    			}
    		}
    		for (ElementWay way : modifyList) {
        		for (OsmNd nd : way.nds) {
        			ElementNode node = dom.nodes.get(nd.id);
        			node.action = "modify";
        			node.orignal = false;
        			ddom.nodes.put(node);
        		}
    			way.action = "modify";
    			way.orignal = false;
    			ddom.ways.put(way);
    			dom.ways.remove(way.id);
    			way.delete(db);
    		}
    		
	    		// インポートデータの内で、既存データと重複するWAYを'modify'に確定する
    		for (String rKey : dom.ways.keySet()) {
    			ElementWay way = dom.ways.get(rKey);
    			long wayid = way.getIntersect(db, "WHERE (tWay.orignal=true)");
    			if (wayid > 0) {
    				ElementWay dWay = ddom.ways.get(wayid);
    				dWay.action = "modify";
    				dWay.orignal = false;
    				dWay.nds = way.nds;
            		for (OsmNd nd : way.nds) {
            			ElementNode node = dom.nodes.get(nd.id);
            			node.action = "modify";
            			node.orignal = false;
            			ddom.nodes.put(node);
            		}
            		for (String k : way.tags.keySet()) {
            			ElementTag tag = way.tags.get(k);
            			dWay.tags.put(k, tag);
            		}
            		ElementTag tag = dWay.tags.get("fixme");
            		if (tag != null) {
            			String fixme = tag.v;
            			tag.v = fixme +"; PLATEAUデータで更新されています";
            		}
            		else {
            			tag = new ElementTag("fixme", "PLATEAUデータで更新されています");
            		}
            		dWay.tags.put("fixme", tag);
            		dWay.delete(db);
    			}
    		}
    		
	    		// インポートデータの内で、既存データと重複するRELATIONを'modify'に確定する
    		for (String rKey : dom.relations.keySet()) {
    			ElementRelation relation = dom.relations.get(rKey);
    			for (ElementMember member : relation.members) {
    				if (member.role.equals("outline")) {
    	    			break;
    				}
    			}
    			if (dWay != null) {
    				for (ElementMember member : relation.members) {
    					ElementWay way = dom.ways.get(member.ref);
    					if (way != null) {
        					ddom.ways.put(way);
    					}
    				}
    				ddom.relations.put(relation.clone());
    			}
    		}
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 既存データの内で、WAYに所属しないNODEを削除
		HashMap<String,ElementNode> killNodes = new HashMap<>();
		for (String rKey : ddom.nodes.keySet()) {
			ElementNode node = ddom.nodes.get(rKey);
			killNodes.put(rKey, node);
		}
		for (String rKey : ddom.ways.keySet()) {
			ElementWay way = ddom.ways.get(rKey);
    		for (OsmNd nd : way.nds) {
    			killNodes.remove(Long.toString(nd.id));
    		}
		}
		for (String rKey : killNodes.keySet()) {
			ElementNode node = killNodes.get(rKey);
			ddom.nodes.remove(Long.toString(node.id));
		}
	}
	
	/**
	 * 取得したデータからRELATION:buildingオブジェクトのみ抽出する
	 * @param sdom	抽出元
	 * @return	抽出された新しいインスタンス
	 */
	private OsmDom getBuilding(OsmDom sdom) {
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

		// 取得したデータからWAY:buildingオブジェクトのみ抽出する
		for (String rKey : sdom.ways.keySet()) {
			ElementWay way = sdom.ways.get(rKey);
			if (isBuildingTag(way.tags)) {
				ddom.ways.put(rKey, way.clone());
			}
		}
		
		for (String rKey : ddom.ways.keySet()) {
			ElementWay way = ddom.ways.get(rKey);
			for (OsmNd nd : way.nds) {
				ElementNode sNode = sdom.nodes.get(Long.toString(nd.id));
				ElementNode node = sNode.clone();
				nd.point = node.point;
				ddom.nodes.put(node);
			}
		}
		for (String rKey : dom.ways.keySet()) {
			ElementWay way = dom.ways.get(rKey);
			for (OsmNd nd : way.nds) {
				ElementNode sNode = dom.nodes.get(Long.toString(nd.id));
				nd.point = sNode.point;
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
	public Document loadMap(ElementBounds bounds) throws MalformedURLException, ProtocolException, IOException, ParserConfigurationException, SAXException {
        double minlon = Double.parseDouble(bounds.minlon);
		double maxlon = Double.parseDouble(bounds.maxlon);
		double minlat = Double.parseDouble(bounds.minlat);
		double maxlat = Double.parseDouble(bounds.maxlat);
		HttpGet obj = new HttpGet();
		GetResponse res = obj.getMap(minlon, minlat, maxlon, maxlat);
		res.printout();
		return res.getBody();
	}
	
}
