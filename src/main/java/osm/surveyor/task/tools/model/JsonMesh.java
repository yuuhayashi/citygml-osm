package osm.surveyor.task.tools.model;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Getter;
import lombok.Setter;
import osm.surveyor.task.util.JsonTemple;

/**
 *	{"citycode":20201, "cityname":"長野市", "meshcode":54375756},
 */
@Getter
@Setter
public class JsonMesh extends JsonTemple {
	
	private Long citycode;
	
	private String cityname;
	
	private Long meshcode;
	
	public Long getCitycode() {
		return citycode;
	}

	public void setCitycode(Long citycode) {
		this.citycode = citycode;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public Long getMeshcode() {
		return meshcode;
	}

	public void setMeshcode(Long meshcode) {
		this.meshcode = meshcode;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		boolean c = false;
		sb.append("{");
		c = out(c, sb, "citycode", getCitycode().toString());
		c = outStr(c, sb, "cityname", getCityname());
		c = out(c, sb, "meshcode", getMeshcode().toString());
		sb.append("}");
		return sb.toString();
	}
	
	public void parse(JsonNode node) {
		JsonNode node1 = node.get("citycode");
		if (node1 != null) {
			setCitycode(Long.valueOf(node1.longValue()));
		}
		
		node1 = node.get("cityname");
		if (node1 != null) {
			setCityname(node1.textValue());
		}

		node1 = node.get("meshcode");
		if (node1 != null) {
			setMeshcode(Long.valueOf(node1.longValue()));
		}
	}
}
