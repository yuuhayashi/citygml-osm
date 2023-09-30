package osm.surveyor.task.util;

import java.util.ArrayList;
import java.util.List;

import org.geotools.geometry.DirectPosition2D;

import com.fasterxml.jackson.databind.JsonNode;

import haya4.tools.jpmesh.Jpmesh;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonGeometryLine extends JsonTemple {
	
	/**
	 *	{
	 *		"coordinates":[
	 *			[141.35,42.9],
	 *			[141.36249999999998,42.9],
	 *			[141.36249999999998,42.90833333333333],
	 *			[141.35,42.90833333333333],
	 *			[141.35,42.9]
	 *		],
	 *		"type":"LineString"
	 *	}
	 */

	private List<Point> coordinates = new ArrayList<>();
	
	private String type = "LineString";
	
	public String getType() {
		return type;
	}

	public void setMeshcode(String code) {
		DirectPosition2D ret = Jpmesh.getPosition(code);
		putPoint(ret.getX(), ret.getY());
		putPoint(ret.getX() + (45d / 3600d), ret.getY());
		putPoint(ret.getX() + (45d / 3600d), ret.getY() + (30d / 3600d));
		putPoint(ret.getX(), ret.getY() + (30d / 3600d));
		putPoint(ret.getX(), ret.getY());
	}
	
	private void putPoint(double lon, double lat) {
		Point p = new Point();
		p.setLng(lon);
		p.setLat(lat);
		this.coordinates.add(p);
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		boolean c1 = false;
		sb.append("{");
		if (coordinates != null) {
			sb.append("\"coordinates\":[");
			boolean c2 = false;
			for (Point p : coordinates) {
				c2 = out(c2, sb, null, p.toString());
			}
			sb.append("]");
			c1 = true;
		}
		c1 = outStr(c1, sb, "type", getType());
		sb.append("}");
		return sb.toString();
	}
	
	public void parse(JsonNode node) {
		JsonNode node1 = node.get("type");
		if (node1 != null) {
			this.type = node1.textValue();
		}
		
		node1 = node.get("coordinates");
		if (node1 != null) {
			this.coordinates = new ArrayList<Point>();
			for (JsonNode node2 : node1) {
				Point point = new Point();
				point.parse(node2);
				this.coordinates.add(point);
			}
		}
	}
}
