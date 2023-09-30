package osm.surveyor.task.tools;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.geotools.geometry.DirectPosition2D;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import haya4.tools.jpmesh.Jpmesh;
import osm.surveyor.task.tools.model.JsonMesh;
import osm.surveyor.task.tools.model.MeshlistJson;
import osm.surveyor.task.util.Geojson;
import osm.surveyor.task.util.JsonCrs;
import osm.surveyor.task.util.JsonFeature;
import osm.surveyor.task.util.JsonGeometryLine;
import osm.surveyor.task.util.JsonGeometryPoint;
import osm.surveyor.task.util.JsonProperties;
import osm.surveyor.task.util.Point;

public class MeshlistConverter {

	/**
	 * 
	 * ARGS[0]		入力JSONファイル名
	 * ARGS[1]		出力GEOJSONファイル名
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		if (args.length >= 1) {
			(new MeshlistConverter()).proc(args[0]);
		}
		else {
			System.out.println("> java -jar meshcode-jp.jar osm.surveyor.task.tools.MeshlistConverter <source> <destination>");
			System.out.println("[exp] java -jar meshcode-jp.jar osm.surveyor.task.tools.MeshlistConverter meshlist_20.json meshlist_20.geojson");
		}
	}
	
	/**
	 * 「src/main/resources/static/data/meshlist_20.json」を読み込む
	 * @param args
	 * @throws IOException 
	 */
	@SuppressWarnings({ "deprecation", "removal" })
	private void proc(String sourceName) throws IOException {
    	ObjectMapper mapper = new ObjectMapper();
    	JsonNode node = mapper.readTree(Paths.get(sourceName).toFile());
    	MeshlistJson meshlist = new MeshlistJson();
    	meshlist.parse(node);
    	
    	
    	JsonCrs crs = new JsonCrs();
    	JsonProperties prop = new JsonProperties();
    	prop.setName("urn:ogc:def:crs:OGC:1.3:CRS84");
    	crs.setProperties(prop);
    	
    	// citylistを作成する
    	Hashtable<Long, List<JsonMesh>> citytable = new Hashtable<>();
    	for (JsonMesh mesh : meshlist.getMeshlist()) {
    		// citycode毎に分けて'citytable'に格納する
    		Long citycode = mesh.getCitycode();
    		List<JsonMesh> list = citytable.get(citycode);
    		if (list == null) {
    			list = new ArrayList<JsonMesh>();
    		}
			list.add(mesh);
    		citytable.put(citycode, list);
    	}
    	
    	Geojson index = new Geojson();
    	index.setCrs(crs);

    	for (Long citycode : citytable.keySet()) {
    		List<Point> total = new ArrayList<>();		// 市町村の全てのメッシュ中心線
    		String cityname = "-";
    		
    		List<JsonMesh> list = citytable.get(citycode);
        	Geojson geo = new Geojson();
        	geo.setCrs(crs);

        	List<JsonFeature> features = geo.getFeatures();
        	for (JsonMesh mesh : list) {
        		Long meshcode = mesh.getMeshcode();
        		cityname = mesh.getCityname();

        		JsonProperties p = new JsonProperties();
            	p.setPrefcode(meshlist.getPrefcode());
            	p.setPrefname(meshlist.getPrefname());
            	p.setCitycode(mesh.getCitycode());
            	p.setCityname(mesh.getCityname());
            	p.setMeshcode(meshcode);

            	// メッシュ領域の中心点
            	DirectPosition2D center = Jpmesh.getCenterPosition(meshcode.toString());
        		JsonGeometryPoint geop = new JsonGeometryPoint();
        		Point point = new Point();
        		point.setLng(new BigDecimal(center.getX()).setScale(5, BigDecimal.ROUND_HALF_UP));
        		point.setLat(new BigDecimal(center.getY()).setScale(6, BigDecimal.ROUND_HALF_UP));
        		geop.setCoordinates(point);
        		total.add(point);		// 中心点
        		
        		JsonFeature f = new JsonFeature();
        		f.setType("Feature");
        		f.setProperties(p);
        		f.setGeometryPoint(geop);
        		features.add(f);

        		// メッシュ領域の外周線
        		JsonGeometryLine geol = new JsonGeometryLine();
        		geol.setMeshcode(meshcode.toString());
        		
        		JsonFeature fl = new JsonFeature();
        		fl.setType("Feature");
        		fl.setProperties(p);
        		fl.setGeometryLine(geol);
        		features.add(fl);
        	}
        	
        	String destName = "meshcode_"+ meshlist.getPrefcode() +"_"+ citycode +".geojson";
        	toGeojson(geo, destName);

        	// TODO: 市町村の中心を算出する
        	int count = 0;
        	double lng = 0.0;
        	double lat = 0.0;
        	for (Point p : total) {
        		lng += new Double(p.getLng());
        		lat += new Double(p.getLat());
        		count++;
        	}
        	Point center = new Point();
        	center.setLng(lng / count);
        	center.setLat(lat / count);

    		JsonGeometryPoint geop = new JsonGeometryPoint();
    		geop.setCoordinates(center);

    		JsonProperties indexp = new JsonProperties();
    		indexp.setPrefcode(meshlist.getPrefcode());
    		indexp.setPrefname(meshlist.getPrefname());
    		indexp.setCitycode(citycode);
    		indexp.setCityname(cityname);
    		
        	JsonFeature fl = new JsonFeature();
    		fl.setType("Feature");
    		fl.setProperties(indexp);
    		fl.setGeometryPoint(geop);
    		
    		index.getFeatures().add(fl);
	    }

    	String destName = "meshcode_"+ meshlist.getPrefcode() +".geojson";
    	toGeojson(index, destName);
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
