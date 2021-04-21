package osm.surveyor.osm;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class OsmNd implements Cloneable {
	public long id = 0;
	public OsmPoint point = null;
	
	public OsmNd set(long id, OsmPoint point) {
		this.id = id;
		this.point = point;
		return this;
	}
	
	@Override
	public OsmNd clone() {
		OsmNd copy = null;
		try {
			copy = (OsmNd)super.clone();
			if (this.point == null) {
				copy.point = null;
			}
			else {
				copy.point = this.point.clone();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			copy = null;
		}
		return copy;
	}
	
    /**
     * <nd ref='-4'/>
     */
    public Node toNodeNd(Document doc) {
		Element node = (Element) doc.createElement("nd");
        node.setAttribute("ref", Long.toString(id));
        return (Node)node;
    }
    
    public ElementNode toElementNode() {
		ElementNode node = new ElementNode();
		node.id = id;
		node.point = ((this.point == null) ? null : this.point.clone());
        return node;
    }


    public OsmNd loadElement(Element eElement) {
    	String attrKey = "ref";
		String str = eElement.getAttribute(attrKey);
		if ((str == null) || str.isEmpty()) {
			return null;
		}
		this.id = Long.parseLong(str);
		this.point = null;
		return this;
    }
    
    //--------------------------------------

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((point == null) ? 0 : point.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OsmNd other = (OsmNd) obj;
		if (id != other.id)
			return false;
		if (point == null) {
			if (other.point != null)
				return false;
		} else if (!point.equals(other.point))
			return false;
		return true;
	}
}
