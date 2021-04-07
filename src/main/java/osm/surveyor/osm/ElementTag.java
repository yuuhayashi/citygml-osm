package osm.surveyor.osm;

import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * CityGMLファイルをパースする
 * @param gmlFile
 * @code{
 * <osm>
 *   <tag k='building' v='yes' />
 *         :
 * </osm>
 * }
 * 
 */
public class ElementTag {
	public String k = null;
	public String v = null;
	
	public ElementTag(String key, String value) {
		this.k = key;
		this.v = value;
	}
	
    /*
		<tag k='height' v='14.072000000000001' />
     */
    public Node toNodeNd(Document doc) {
		Element node = (Element) doc.createElement("tag");
        node.setAttribute("k", k);
        node.setAttribute("v", v);
        return (Node)node;
    }
}
