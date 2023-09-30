package osm.surveyor.task.util;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Geojson extends JsonTemple {
	
	private String type = "FeatureCollection";
	public void setType(String v) {
		this.type = v;
	}
	
	public JsonCrs getCrs() {
		return crs;
	}

	public void setCrs(JsonCrs crs) {
		this.crs = crs;
	}

	private JsonCrs crs;
	
	public List<JsonFeature> getFeatures() {
		return features;
	}

	private List<JsonFeature> features = new ArrayList<>();
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		boolean c = false;
		sb.append("{");
		c = outStr(c, sb, "type", this.type);
		c = out(c, sb, "crs", this.crs);
		if (c) {
			sb.append(",");
		}
		sb.append("\"features\":[");
		boolean c2 = false;
		for (JsonFeature f : features) {
			c2 = out(c2, sb, null, f);
			sb.append(System.lineSeparator());
		}
		sb.append("]");
		
		sb.append("}");
		return sb.toString();
	}
	
	public void parse(JsonNode node) {
		setType(node.get("type").textValue());
		
		JsonNode nodeCrs = node.get("crs");
		if (nodeCrs != null) {
			this.crs = new JsonCrs();
			this.crs.parse(nodeCrs);
		}
		
		JsonNode f = node.get("features");
		if (f != null) {
			this.features = new ArrayList<JsonFeature>();
		    for (JsonNode f1 : f) {
		    	JsonFeature feature = new JsonFeature();
		    	feature.parse(f1);
		    	this.features.add(feature);
			}
		}
	}
}
