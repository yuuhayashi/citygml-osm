package osm.surveyor.tools.geojson;

import org.geotools.geometry.DirectPosition2D;
import org.json.JSONArray;
import org.json.JSONObject;

import haya4.tools.jpmesh.Jpmesh;

/**
 * {
 * 		"type": "Point",
 * 		"coordinates": [139.70625, 35.7041666]
 * }
 * 
 * @author hayashi
 *
 */
public class Point extends JSONObject {
	String id;
	JSONArray coordinates = new JSONArray();
	
	public Point() {
		this.id = null;
		this.put("type", "Point");
	}
	
	public void setMeshcode(String code) {
		DirectPosition2D ret = Jpmesh.getCenterPosition(code);
		setPoint(ret.getX(), ret.getY());
	}
	
	public void setPoint(double lon, double lat) {
		coordinates.put(lon);
		coordinates.put(lat);
		this.put("coordinates", coordinates);
	}
}
