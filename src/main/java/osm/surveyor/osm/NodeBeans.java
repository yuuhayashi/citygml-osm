package osm.surveyor.osm;

import java.util.ArrayList;

/**
 * Hash key = node.id
 * 
 */
@SuppressWarnings("serial")
public class NodeBeans extends ArrayList<NodeBean> {
	public NodeBeans() {
		super();
	}
	
	public void put(NodeBean node) {
		if (node != null) {
			long id = node.getId();
			for (NodeBean bean : this) {
				if (bean.getId() == id) {
					this.remove(bean);
					break;
				}
			}
			add(node);
		}
	}
	
	public NodeBean get(long id) {
		for (NodeBean bean : this) {
			if (bean.getId() == id) {
				return bean;
			}
		}
		return null;
	}
	
	/**
	 * XMLをDOMする
	 * 
	 * @param doc
	public void load(Document doc) {
		NodeList nList = doc.getElementsByTagName("node");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			NodeBean node = (new NodeBean(0)).loadNode(nNode);
			if (node != null) {
				this.put(node);
			}
		}
	}
	 */
}
