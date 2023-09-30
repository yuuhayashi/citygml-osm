package osm.surveyor.task.util;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonNumberArray extends JsonTemple {
	
	private List<String> list = new ArrayList<>();
	
	public void setList(List<String> list) {
		this.list = new ArrayList<>();
		for (String str : list) {
			add(str);
		}
	}
	
	public void add(String str) {
		this.list.add(str);
	}
	
	/*
	 * [141.35625,42.90416666666667]
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		boolean first = false;
		for (String str : this.list) {
			first = out(first, sb, null, str);
		}
		sb.append("]");
		return sb.toString();
	}
}
