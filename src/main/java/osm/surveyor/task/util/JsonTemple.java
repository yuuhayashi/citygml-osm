package osm.surveyor.task.util;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonTemple implements JsonRepository {
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		boolean c = false;
		sb.append("{");
		if (c) {
			sb.append(",");
		}
		sb.append("}");
		return sb.toString();
	}
	
	public void parse(JsonNode node) {
	}
	
	public boolean out(boolean c, StringBuffer sb, String name, JsonTemple obj) {
		boolean c1 = c;
		if (obj != null) {
			if (c1) {
				sb.append(",");
			}
			else {
				c1 = true;
			}
			if (name != null) {
				sb.append("\""+ name +"\":");
			}
			sb.append(obj.toString());
		}
		return c1;
	}
	
	public boolean out(boolean c, StringBuffer sb, String name, Long obj) {
		boolean c1 = c;
		if (obj != null) {
			if (c1) {
				sb.append(",");
			}
			else {
				c1 = true;
			}
			if (name != null) {
				sb.append("\""+ name +"\":");
			}
			sb.append(obj.toString());
		}
		return c1;
	}
	
	public boolean out(boolean c, StringBuffer sb, String name, String str) {
		boolean c1 = c;
		if (str != null) {
			if (c1) {
				sb.append(",");
			}
			else {
				c1 = true;
			}
			if (name != null) {
				sb.append("\""+ name +"\":");
			}
			sb.append(str);
		}
		return c1;
	}

	public boolean outStr(boolean c, StringBuffer sb, String name, JsonTemple obj) {
		boolean c1 = c;
		if (obj != null) {
			if (c1) {
				sb.append(",");
			}
			else {
				c1 = true;
			}
			if (name != null) {
				sb.append("\""+ name +"\":");
			}
			sb.append("\""+ obj.toString() +"\"");
		}
		return c1;
	}

	public boolean outStr(boolean c, StringBuffer sb, String name, String str) {
		boolean c1 = c;
		if (str != null) {
			if (c1) {
				sb.append(",");
			}
			else {
				c1 = true;
			}
			if (name != null) {
				sb.append("\""+ name +"\":");
			}
			sb.append("\""+ str +"\"");
		}
		return c1;
	}
}
