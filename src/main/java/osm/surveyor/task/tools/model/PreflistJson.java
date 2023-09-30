package osm.surveyor.task.tools.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Getter;
import lombok.Setter;
import osm.surveyor.task.util.JsonTemple;

@Getter
@Setter
public class PreflistJson extends JsonTemple {
	
	private List<JsonPref> preflist = new ArrayList<>();
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"preflist\":[");
		boolean c2 = false;
		for (JsonPref f : preflist) {
			c2 = out(c2, sb, null, f);
		}
		sb.append("]");
		sb.append("}");
		return sb.toString();
	}
	
	public List<JsonPref> getPreflist() {
		return preflist;
	}

	public void setPreflist(List<JsonPref> preflist) {
		this.preflist = preflist;
	}

	public void parse(JsonNode node) {
		JsonNode f = node.get("preflist");
		if (f != null) {
			this.preflist = new ArrayList<JsonPref>();
		    for (JsonNode f1 : f) {
		    	JsonPref pref = new JsonPref();
		    	pref.parse(f1);
		    	this.preflist.add(pref);
			}
		}
	}
}
