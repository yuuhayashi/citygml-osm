package osm.surveyor.task.tools.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Getter;
import lombok.Setter;
import osm.surveyor.task.util.JsonTemple;

/**
 * {
 *   "prefcode":20,
 *   "prefname":"長野県",
 *   "meshlist":[
 *     {"citycode":"20201", "cityname":"長野市", "meshcode":"54375756"},
 *     {"citycode":"20201", "cityname":"長野市", "meshcode":"54375757"}
 *   ]
 * }
 * @author hayashi
 *
 */
@Getter
@Setter
public class MeshlistJson extends JsonTemple {
	
	private Long prefcode;
	public void setPrefcode(Long prefcode) {
		this.prefcode = prefcode;
	}

	public void setPrefname(String prefname) {
		this.prefname = prefname;
	}

	public void setMeshlist(List<JsonMesh> meshlist) {
		this.meshlist = meshlist;
	}

	public Long getPrefcode() {
		return this.prefcode;
	}
	
	private String prefname;
	public String getPrefname() {
		return this.prefname;
	}
	
	private List<JsonMesh> meshlist = new ArrayList<>();
	public List<JsonMesh> getMeshlist() {
		return this.meshlist;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		boolean c1 = false;
		c1 = out(c1, sb, "prefcode", getPrefcode());
		c1 = outStr(c1, sb, "prefname", getPrefname());
		
		sb.append("\"meshlist\":[");
		boolean c2 = false;
		for (JsonMesh mesh : getMeshlist()) {
			c2 = out(c2, sb, null, mesh);
		}
		sb.append("]");

		sb.append("}");
		return sb.toString();
	}
	
	public void parse(JsonNode node) {
		JsonNode node1 = node.get("prefcode");
		if (node1 != null) {
			setPrefcode(node1.longValue());
		}
		
		node1 = node.get("prefname");
		if (node1 != null) {
			setPrefname(node1.textValue());
		}
		
		node1 = node.get("meshlist");
		if (node1 != null) {
			this.meshlist = new ArrayList<JsonMesh>();
		    for (JsonNode f1 : node1) {
		    	JsonMesh mesh = new JsonMesh();
		    	mesh.parse(f1);
		    	this.meshlist.add(mesh);
			}
		}
	}
}
