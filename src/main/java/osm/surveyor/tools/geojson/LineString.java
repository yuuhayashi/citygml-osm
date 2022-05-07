package osm.surveyor.tools.geojson;

import org.geotools.geometry.DirectPosition2D;
import org.json.JSONArray;
import org.json.JSONObject;

import haya4.tools.jpmesh.Jpmesh;

/**
 * {
 * 		"type": "LineString",
 * 		"coordinates": [
 * 			[139.7, 35.7],
 * 			[139.7125, 35.7],
 * 			[139.7125, 35.7083333],
 * 			[139.7, 35.7083333],
 * 			[139.7, 35.7]
 * 		]
 * }
 * 
 * @author hayashi
 *
 */
public class LineString extends JSONObject {
	String id;
	JSONArray coordinates = new JSONArray();
	
	public LineString() {
		this.id = null;
		this.put("type", "LineString");
		this.put("coordinates", coordinates);
	}
	
	public void setMeshcode(String code) {
		DirectPosition2D ret = Jpmesh.getPosition(code);
		putPoint(ret.getX(), ret.getY());
		putPoint(ret.getX() + (45d / 3600d), ret.getY());
		putPoint(ret.getX() + (45d / 3600d), ret.getY() + (30d / 3600d));
		putPoint(ret.getX(), ret.getY() + (30d / 3600d));
		putPoint(ret.getX(), ret.getY());
	}
	
	public void putPoint(double lon, double lat) {
		JSONArray coordinates1 = new JSONArray();
		coordinates1.put(lon);
		coordinates1.put(lat);
		coordinates.put(coordinates1);
	}
}
