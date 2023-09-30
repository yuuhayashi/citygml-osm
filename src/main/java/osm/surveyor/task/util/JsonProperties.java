package osm.surveyor.task.util;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonProperties extends JsonTemple {
	/*
	 *	"properties":{
	 *		"id":"64412288"
	 *	}
	 */
	private String name;
	public void setName(String str ) {
		this.name = str;
	}
	
	public void setCitycode(Long citycode) {
		this.citycode = citycode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String path;
	private String id;
	private String version;
	
	private Long prefcode;
	public Long getPrefcode() {
		return this.prefcode;
	}
	public void setPrefcode(long code) {
		this.prefcode = Long.valueOf(code);
	}
	
	private String prefname;
	public String getPrefname() {
		return this.prefname;
	}
	public void setPrefname(String v) {
		this.prefname = v;
	}
	
	public void setMeshcode(Long meshcode) {
		this.meshcode = meshcode;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	private Long citycode;
	public Long getCitycode() {
		return this.citycode;
	}
	
	private String cityname;
	public String getCityname() {
		return this.cityname;
	}
	
	private Long meshcode;
	public Long getMeshcode() {
		return this.meshcode;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		boolean c1 = false;
		sb.append("{");
		c1 = outStr(c1, sb, "name", this.name);
		c1 = outStr(c1, sb, "path", this.path);
		c1 = outStr(c1, sb, "id", this.id);
		c1 = outStr(c1, sb, "version", this.version);
		c1 = out(c1, sb, "prefcode", getPrefcode());
		c1 = outStr(c1, sb, "prefname", getPrefname());
		c1 = out(c1, sb, "citycode", getCitycode());
		c1 = outStr(c1, sb, "cityname", getCityname());
		c1 = out(c1, sb, "meshcode", getMeshcode());
		sb.append("}");
		return sb.toString();
	}
	
	public void parse(JsonNode node) {
		JsonNode node1 = node.get("name");
		if (node1 != null) {
			this.name = node1.textValue();
		}
		
		node1 = node.get("path");
		if (node1 != null) {
			this.path = node1.textValue();
		}
		
		node1 = node.get("id");
		if (node1 != null) {
			this.id = node1.textValue();
		}

		node1 = node.get("version");
		if (node1 != null) {
			this.version = node1.textValue();
		}

		node1 = node.get("prefcode");
		if (node1 != null) {
			setPrefcode(node1.longValue());
		}

		node1 = node.get("prefname");
		if (node1 != null) {
			setPrefname(node1.textValue());
		}
	}
}
