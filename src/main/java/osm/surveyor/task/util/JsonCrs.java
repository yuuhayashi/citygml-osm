package osm.surveyor.task.util;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Getter;
import lombok.Setter;

/**
	"crs": {
		"type":"name",
		"properties":{
			"name":"urn:ogc:def:crs:OGC:1.3:CRS84"
		}
	}
*/
@Getter
@Setter
public class JsonCrs extends JsonTemple {
	
	private String type = "name";
	
	private JsonProperties properties;
	public void setProperties(JsonProperties prop) {
		this.properties = prop;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		boolean c1 = false;
		sb.append("{");
		c1 = outStr(c1, sb, "type", this.type);
		c1 = out(c1, sb, "properties", this.properties);
		sb.append("}");
		return sb.toString();
	}
	
	public void parse(JsonNode node) {
		setType(node.get("type").textValue());
		
		JsonNode node1 = node.get("properties");
		if (node1 != null) {
			this.properties = new JsonProperties();
			this.properties.parse(node1);
		}
	}
	
	public void setType(String type) {
		this.type = type;
	}
}
