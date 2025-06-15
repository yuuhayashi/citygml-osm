package osm.surveyor.osm;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import osm.surveyor.gis.point.NdModel;

@XmlRootElement(name="nd")
public class OsmNd extends NdModel implements Cloneable {
	
	@XmlAttribute(name="ref")
	public long getRef() {
		return super.getRef();
	}
	public void setRef(long ref) {
		super.setRef(ref);
	}
	
	public OsmNd set(long id, OsmPoint point) {
		setRef(id);
		setPoint(point);
		return this;
	}

	public void setIdstr(String id) {
		setRef(Long.parseLong(id));
	}
	
    /**
     * <nd ref='-4'/>
     */
    public Node toNodeNd(Document doc) {
		Element node = (Element) doc.createElement("nd");
        node.setAttribute("ref", Long.toString(getRef()));
        return (Node)node;
    }
    
    public NodeBean toElementNode() {
    	NodeBean node = new NodeBean(0);
		node.setId(getRef());
		node.setPoint((getPoint() == null) ? null : getPoint().clone());
        return node;
    }

    public OsmNd loadElement(Element eElement) {
    	String attrKey = "ref";
		String str = eElement.getAttribute(attrKey);
		if ((str == null) || str.isEmpty()) {
			return null;
		}
		setRef(Long.parseLong(str));
		this.point = null;
		return this;
    }
    
    //--------------------------------------
	@Override
	public OsmNd clone() {
		OsmNd b = (OsmNd) super.clone();
		return b;
	}
}
