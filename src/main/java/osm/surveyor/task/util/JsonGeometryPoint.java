package osm.surveyor.task.util;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Getter;
import lombok.Setter;

/**
 * 	{
 * 		"coordinates":[141.35625,42.90416666666667],
 * 		"type":"Point"
 * 	}
 */
@Getter
@Setter
public class JsonGeometryPoint extends JsonTemple {
	private Point coordinates;
	
	private String type = "Point";
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		boolean c = false;
		sb.append("{");
		c = out(c, sb, "coordinates", this.coordinates.toString());
		c = outStr(c, sb, "type", this.type);
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
			this.coordinates = new Point();
			this.coordinates.parse(node1);
		}
	}

	public Point getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Point coordinates) {
		this.coordinates = coordinates;
	}
}
