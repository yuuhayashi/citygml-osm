package osm.surveyor.osm;

import java.util.HashMap;

public class ElementNodeList extends HashMap<String, ElementNode> {

	private static final long serialVersionUID = 3570123486213986940L;

	public ElementNodeList() {
		super();
	}

	/**
	 * 二重登録を防ぐ
	 * 
	 * @param node
	 * @return
	 */
	public ElementNode append(ElementNode node) {
		String key = new String(node.lat +":"+ node.lon);
		if (this.containsKey(key)) {
			return this.get(key);
		}
		else {
			this.put(key, node);
			return node;
		}
	}
}