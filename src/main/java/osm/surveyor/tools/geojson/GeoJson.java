package osm.surveyor.tools.geojson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 	{
 * 		"type": "FeatureCollection",
 * 		"crs": {
 * 			"type": "name",
 * 			"properties": {
 * 				"name": "urn:ogc:def:crs:OGC:1.3:CRS84"
 * 			}
 * 		},
 * 		"features": [ Feature.class ]
 * 	}
 * 
 * @author hayashi
 *
 */
public class GeoJson extends JSONObject {
	JSONArray features = new JSONArray();
	JSONObject properties = new JSONObject();
	
	public GeoJson() {
    	JSONObject crs = new JSONObject();
		crs.put("type", "name");
		crs.put("properties", properties);
		this.put("type", "FeatureCollection");
		this.put("crs", crs);
		this.put("features", this.features);
	}

	public void setName(String name) {
		this.properties.put("name", name);
	}
	
	public void put(String code) {
		Feature fpoint = new Feature();
		fpoint.setId(code);
		fpoint.setPoint(code);
		this.features.put(fpoint);
		
		Feature fline = new Feature();
		fline.setLine(code);
		this.features.put(fline);
	}
	
    public void export(File file) {
    	try (FileOutputStream ps = new FileOutputStream(file);
    			OutputStreamWriter w = new OutputStreamWriter(ps, "utf-8")) {
    		String str = this.toString();
    		
    		w.write(str);
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
