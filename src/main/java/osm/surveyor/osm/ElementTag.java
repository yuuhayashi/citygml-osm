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
public class ElementTag implements Cloneable {
	public String k = null;
	public String v = null;
	
	public ElementTag(String key, String value) {
		this.k = key;
		this.v = value;
	}

	@Override
	public ElementTag clone() {
		try {
			return (ElementTag)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
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
