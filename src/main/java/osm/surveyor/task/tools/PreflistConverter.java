package osm.surveyor.task.tools;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import osm.surveyor.task.tools.model.JsonPref;
import osm.surveyor.task.tools.model.PreflistJson;
import osm.surveyor.task.util.Geojson;
import osm.surveyor.task.util.JsonCrs;
import osm.surveyor.task.util.JsonFeature;
import osm.surveyor.task.util.JsonGeometryPoint;
import osm.surveyor.task.util.JsonProperties;
import osm.surveyor.task.util.Point;

public class PreflistConverter {

	/**
	 * 
	 * ARGS[0]		入力JSONファイル名
	 * ARGS[1]		出力GEOJSONファイル名
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		if (args.length >= 2) {
			(new PreflistConverter()).proc(args[0], args[1]);
		}
		else {
			System.out.println("> java -jar meshcode-jp.jar osm.surveyor.task.tools.MeshlistConverter <source> <destination>");
			System.out.println("[exp] java -jar meshcode-jp.jar osm.surveyor.task.tools.MeshlistConverter meshlist_20.json meshlist_20.geojson");
		}
	}
	
	/**
	 * 「src/main/resources/static/data/preflist.json」を読み込む
	 * @param args
	 * @throws IOException 
	 */
	private void proc(String sourceName, String destName) throws IOException {
    	ObjectMapper mapper = new ObjectMapper();
    	JsonNode node = mapper.readTree(Paths.get(sourceName).toFile());
    	PreflistJson json = new PreflistJson();
    	json.parse(node);
    	
    	Geojson geo = new Geojson();
    	
    	JsonCrs crs = new JsonCrs();
    	JsonProperties prop = new JsonProperties();
    	prop.setName("urn:ogc:def:crs:OGC:1.3:CRS84");
    	crs.setProperties(prop);
    	geo.setCrs(crs);

    	List<JsonFeature> features = geo.getFeatures();
    	for (JsonPref pref : json.getPreflist()) {
    		JsonFeature f = new JsonFeature();
    		f.setType("Feature");

    		JsonProperties p = new JsonProperties();
        	p.setId(String.valueOf(pref.getPrefcode()));
        	p.setName(pref.getPrefname());
    		f.setProperties(p);
    		
    		JsonGeometryPoint geop = new JsonGeometryPoint();
    		Point point = pref.getPoint();
    		geop.setCoordinates(point);
    		f.setGeometryPoint(geop);

    		features.add(f);
    	}

    	toGeojson(geo, destName);
	}

	/**
	 * 
	 * @param pref
	 * @throws IOException
	 */
	private void toGeojson(Geojson geo, String destName) throws IOException {
		try {
            FileWriter fw = new FileWriter(destName);
            fw.write(geo.toString());
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}
}
