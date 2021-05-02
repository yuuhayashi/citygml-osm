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
public class NodeMap extends HashMap<String, ElementNode> {
	public NodeMap() {
		super();
	}
	
	public void put(ElementNode node) {
		put(Long.toString(node.id), node);
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
			ElementNode node = (new ElementNode()).loadNode(nNode);
			if (node != null) {
				this.put(node);
			}
	    }
    }
}
