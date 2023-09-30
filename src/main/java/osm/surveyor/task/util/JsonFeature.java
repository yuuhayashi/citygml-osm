package osm.surveyor.task.util;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Getter;
import lombok.Setter;

/**
 *	{
 * 		"geometry":{
 * 			"coordinates":[141.35625,42.90416666666667],
 * 			"type":"Point"
 * 		},
 *		"type":"Feature",
 *		"properties":{"id":"64412288"}
 *	},
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
 *	},
 */
@Getter
@Setter
public class JsonFeature extends JsonTemple {
	public JsonGeometryPoint getGeometryPoint() {
		return geometryPoint;
	}

	public void setGeometryPoint(JsonGeometryPoint geometryPoint) {
		this.geometryPoint = geometryPoint;
	}

	public JsonGeometryLine getGeometryLine() {
		return geometryLine;
	}

	public void setGeometryLine(JsonGeometryLine geometryLine) {
		this.geometryLine = geometryLine;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public JsonProperties getProperties() {
		return properties;
	}

	public void setProperties(JsonProperties properties) {
		this.properties = properties;
	}

	private JsonGeometryPoint geometryPoint;
	private JsonGeometryLine geometryLine;
	
	private String type;
	
	private JsonProperties properties;
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		boolean c = false;
		sb.append("{");
		c = outStr(c, sb, "type", this.type);
		c = out(c, sb, "properties", this.properties.toString());
		c = out(c, sb, "geometry", this.geometryPoint);
		c = out(c, sb, "geometry", this.geometryLine);
		sb.append("}");
		return sb.toString();
	}
	
	public void parse(JsonNode node) {
		JsonNode node1 = node.get("type");
		if (node1 != null) {
			this.type = node1.textValue();
		}
		
		node1 = node.get("properties");
		if (node1 != null) {
			this.properties = new JsonProperties();
			this.properties.parse(node1);
		}
		
		node1 = node.get("geometry");
		if (node1 != null) {
			JsonNode node2 = node1.get("type");
			if (node2 != null) {
				String type2 = node2.textValue();
				if (type2.equals("Point")) {
					this.geometryLine = null;
					this.geometryPoint = new JsonGeometryPoint();
					this.geometryPoint.parse(node1);
				}
				else if (type2.equals("LineString")) {
					this.geometryPoint = null;
					this.geometryLine = new JsonGeometryLine();
					this.geometryLine.parse(node1);
				}
			}
		}
	}
}
