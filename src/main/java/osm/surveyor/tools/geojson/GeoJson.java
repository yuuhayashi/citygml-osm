package osm.surveyor.tools.geojson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import org.json.JSONArray;
import org.json.JSONObject;

import osm.surveyor.citygml.ConversionTable;

/**
 * 	{
 * 		"type": "FeatureCollection",
 * 		"crs": {
 * 			"type": "name",
 * 			"properties": {"name":"urn:ogc:def:crs:OGC:1.3:CRS84"}
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
	String type = "FeatureCollection";
	JSONObject crs = new JSONObject();
	String version = null;
	String path = null;	// Feature name
	ConversionTable conversion = null;
	
	public GeoJson() {
		this.properties.put("name", "urn:ogc:def:crs:OGC:1.3:CRS84");
		this.crs.put("type", "name");
		this.crs.put("properties", this.properties);
		this.put("type", this.type);
		this.put("crs", crs);
		this.put("features", this.features);
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public void put(String code) {
		Feature fpoint = new Feature();
		fpoint.setId(code);
		fpoint.setVersion(this.version);
		fpoint.setPath(this.path);
		fpoint.setPoint(code);
		this.features.put(fpoint);
		
		Feature fline = new Feature();
		fline.setId(code);
		fline.setLine(code);
		this.features.put(fline);
	}
	
    public void export(File file) {
    	try (FileOutputStream ps = new FileOutputStream(file);
    			OutputStreamWriter w = new OutputStreamWriter(ps, "utf-8");
    			PrintWriter pw = new PrintWriter(w)) 
    	{
    		pw.println("{");
    		output(pw, "\"type\": \""+ this.type +"\",", 1);
    		output(pw, "\"crs\": "+ this.crs.toString() +",", 1);
    		output(pw, "\"features\": [", 1);
    		int i = this.features.length();
    		for (Object obj : this.features) {
    			i--;
    			if (obj instanceof JSONObject) {
    				output(pw, obj.toString()+(i > 0 ? "," : ""), 2);
    			}
    			else if (obj instanceof String) {
    				output(pw, obj.toString()+(i > 0 ? "," : ""), 2);
    			}
    		}
    		output(pw, "]", 1);
    		pw.println("}");
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    void output(PrintWriter pw, String str, int level) {
    	for (int i=0; i < level; i++) {
    		pw.print("\t");
    	}
    	pw.println(str);
    }
}
