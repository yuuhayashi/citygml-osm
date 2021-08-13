package osm.surveyor.osm;

import java.util.HashMap;

public class ElementNodeList extends HashMap<String, NodeBean> {

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
	public NodeBean append(NodeBean node) {
		String key = new String(node.point.lat +":"+ node.point.lon);
		if (this.containsKey(key)) {
			return this.get(key);
		}
		else {
			this.put(key, node);
			return node;
		}
	}
}
