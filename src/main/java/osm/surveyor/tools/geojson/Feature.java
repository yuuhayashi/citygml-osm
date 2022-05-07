package osm.surveyor.tools.geojson;

import org.json.JSONObject;

/**
 * {
 * 		"type": "Feature",
 * 		"properties": {"id": "53394526"},
 * 		"geometry": Point.class
 * },
 * {
 * 		"type": "Feature",
 * 		"properties": {},
 * 		"geometry": LineString.class
 * }
 * 
 * @author hayashi
 *
 */
public class Feature extends JSONObject {
	public Feature() {
		this.put("type", "Feature");
		this.put("properties", new JSONObject());
	}
	
	public void setId(String id) {
		if (id != null) {
			JSONObject prop = new JSONObject();
			prop.put("id", id);
			this.put("properties", prop);
		}
	}
	
	public void setPoint(String code) {
		Point point = new Point();
		point.setMeshcode(code);
		this.put("geometry", point);
	}
	
	public void setLine(String code) {
		LineString line = new LineString();
		line.setMeshcode(code);
		this.put("geometry", line);
	}
}
