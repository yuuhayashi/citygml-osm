package osm.surveyor.tools.geojson;

import org.json.JSONObject;

/**
 * {
 * 		"type": "Feature",
 * 		"properties": {
 * 			"id": "53394526",
 * 			"version": "1.4.9-SNAPSHOT",
 * 			"surveyYear": "2021",
 * 			"path": "10207_tatebayashi-shi_2020/bldg/54392356_bldg_6697_op.zip"
 * 		},
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
	JSONObject prop = new JSONObject();
	
	public Feature() {
		this.put("type", "Feature");
		this.put("properties", prop);
	}
	
	public void setId(String id) {
		if (id != null) {
			prop.put("id", id);
		}
	}
	
	public void setVersion(String version) {
		if (version != null) {
			prop.put("version", version);
		}
	}
	
	public void setSurveyYear(String year) {
		if (year != null) {
			prop.put("surveyYear", year);
		}
	}
	
	public void setPath(String path) {
		if (path != null) {
			prop.put("path", path);
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
