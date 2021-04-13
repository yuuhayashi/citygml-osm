package osm.surveyor.osm.update;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import osm.surveyor.osm.ElementBounds;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementTag;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;
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
			updater.sdom.export(Paths.get("current.osm").toFile());
		}
	}
	
	OsmDom dom;
	OsmDom sdom;
	
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
		
		// 取得したデータから不要なオブジェクトを取り除く
		while (removeNotBuilding(sdom.relations) != null);
		while (removeNotBuildingWay(sdom.ways) != null);
		/*
		HashMap<String, ElementWay> ways = new HashMap<>();
		for (String wKey : sdom.ways.keySet()) {
			ElementWay way = sdom.ways.get(wKey);
			for (ElementNode node : way.nodes) {
				
			}
		}
		*/
	}
	
	HashMap<String,ElementRelation> removeNotBuilding(HashMap<String,ElementRelation> relations) {
		for (String rKey : relations.keySet()) {
			ElementRelation relation = relations.get(rKey);
			if (!isBuildingTag(relation.tags)) {
				relations.remove(rKey);
				return relations;
			}
		}
		return null;
	}

	HashMap<String,ElementWay> removeNotBuildingWay(HashMap<String,ElementWay> ways) {
		for (String rKey : ways.keySet()) {
			ElementWay way = ways.get(rKey);
			if (!isBuildingTag(way.tags)) {
				ways.remove(rKey);
				return ways;
			}
		}
		return null;
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
