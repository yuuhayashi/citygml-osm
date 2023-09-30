package osm.surveyor.task.util;

import java.util.ArrayList;
import java.util.List;

import org.geotools.geometry.DirectPosition2D;

import haya4.tools.jpmesh.Jpmesh;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonLineString {
	
	private List<Point> line = new ArrayList<>();
	
	public void addPoint(Point point) {
		this.line.add(point);
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
		addPoint(p);
	}
	
	/*
	 *	[
	 *		[141.35,42.9],
	 *		[141.36249999999998,42.9],
	 *		[141.36249999999998,42.90833333333333],
	 *		[141.35,42.90833333333333],
	 *		[141.35,42.9]
	 *	]
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		boolean first = true;
		for (Point point : this.line) {
			if (first) {
				first = false;
			}
			else {
				sb.append(",");
			}
			sb.append(point.toString());
		}
		sb.append("]");
		return sb.toString();
	}
	
	/**
	 *	{
	 *		"geometry":{
	 *			"coordinates":[
	 *				[141.35,42.9],
	 *				[141.36249999999998,42.9],
	 *				[141.36249999999998,42.90833333333333],
	 *				[141.35,42.90833333333333],
	 *				[141.35,42.9]
	 *			],
	 *			"type":"LineString"
	 *		},
	 *		"type":"Feature",
	 *		"properties":{"id":"64412288"}
	 *	}
	 */
	public String getGeometry() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"geometry\":{");
			sb.append("\"type\":\"LineString\"");
			sb.append(",\"coordinates\":"+ toString());
			sb.append("}");
		sb.append("}");
		return sb.toString();
	}
}
