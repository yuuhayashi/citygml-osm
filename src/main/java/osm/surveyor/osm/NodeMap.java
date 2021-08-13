package osm.surveyor.osm;

import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Hash key = node.id
 * 
 */
@SuppressWarnings("serial")
public class NodeMap extends HashMap<String, NodeBean> {
	public NodeMap() {
		super();
	}
	
	public void put(NodeBean node) {
		put(Long.toString(node.getId()), node);
	}
	
	public NodeBean get(long id) {
		return get(Long.toString(id));
	}
	
	/**
	 * XMLをDOMする
	 * 
	 * @param doc
	 */
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
}
