package osm.surveyor.osm;

import org.jdom.Element;
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

    public void printout(OsmFile file) {
    	if ((k != null) && (v != null)) {
        	String str = "<tag";
        	str += out("k", k);
        	str += out("v", v);
        	str += " />";
        	System.out.println(str);
        	file.println(str);
    	}
    }
    
    private String out(String key, String value) {
    	String str = "";
    	if (value != null) {
    		str = " " + key +"='" + value +"'";
    	}
    	return str;
    }
}
