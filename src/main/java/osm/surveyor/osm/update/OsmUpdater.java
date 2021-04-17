package osm.surveyor.osm.update;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;

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
		if (args.length > 0) {
			OsmUpdater updater = new OsmUpdater(args[0]);
			updater.load();
			updater.ddom.export(Paths.get("current.osm").toFile());
		}
	}
	
	OsmDom dom;
	OsmDom sdom;
	OsmDom ddom;
	
	public OsmUpdater(String filepath) throws ParserConfigurationException, SAXException, IOException {
		dom = new OsmDom();
		dom.load(Paths.get(filepath).toFile());
	}
	
	public void load() throws MalformedURLException, ProtocolException, IOException, ParserConfigurationException, SAXException {
		// 指定されたOSMファイルの<bound/>を取得する
		ElementBounds bounds = dom.getBounds();

		sdom = new OsmDom();
		sdom.setBounds(bounds);

		// OSMから<bound>範囲内の現在のデータを取得する
		Document sdoc = this.loadMap(bounds);
		sdom.load(sdoc);
		
		ddom = new OsmDom();
		ddom.setBounds(bounds);

		// 取得したデータからRELATION:buildingオブジェクトのみ抽出する
		for (String rKey : sdom.relations.keySet()) {
			ElementRelation relation = sdom.relations.get(rKey);
			if (relation.isBuilding()) {
				ddom.relations.put(rKey, relation.clone());
			}
		}
		for (String rKey : ddom.relations.keySet()) {
			ElementRelation relation = ddom.relations.get(rKey);
			for (ElementMember menber : relation.members) {
				ElementWay sWay = sdom.ways.get(Long.toString(menber.ref));
				ddom.addWay(sWay.clone());
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
				ddom.addNode(node);
			}
		}
		for (String rKey : dom.ways.keySet()) {
			ElementWay way = dom.ways.get(rKey);
			for (OsmNd nd : way.nds) {
				ElementNode sNode = dom.nodes.get(Long.toString(nd.id));
				nd.point = sNode.point;
			}
		}
		
        try (Postgis db = new Postgis("osmdb")) {
            db.initTable();		// データベースの初期化
            
            // インポートデータをPOSTGISへセットする
    		for (String rKey : ddom.ways.keySet()) {
    			ElementWay way = ddom.ways.get(rKey);
    			way.orignal = true;
    			way.insertTable(db);
    		}
            
            // 既存データをPOSTGISへセットする
    		for (String rKey : dom.ways.keySet()) {
    			ElementWay way = dom.ways.get(rKey);
    			way.orignal = false;
    			way.insertTable(db);
    		}
            
            
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static boolean isBuildingTag(ArrayList<ElementTag> tags) {
		for (ElementTag tag : tags) {
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
