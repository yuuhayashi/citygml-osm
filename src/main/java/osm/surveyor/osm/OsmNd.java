package osm.surveyor.osm;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

import org.locationtech.jts.geom.Coordinate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class OsmNd implements Cloneable {
	
	@XmlAttribute(name="ref")
	public long id = 0;
	
	@XmlTransient
	public OsmPoint point = null;
	
	public OsmNd set(long id, OsmPoint point) {
		this.id = id;
		this.point = point;
		return this;
	}

	public void setIdstr(String id) {
		this.id = Long.parseLong(id);
	}
	
    /**
     * <nd ref='-4'/>
     */
    public Node toNodeNd(Document doc) {
		Element node = (Element) doc.createElement("nd");
        node.setAttribute("ref", Long.toString(this.id));
        return (Node)node;
    }
    
    public NodeBean toElementNode() {
    	NodeBean node = new NodeBean(0);
		node.setId(id);
		node.setPoint((this.point == null) ? null : this.point.clone());
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
    
	public Coordinate getCoordinate() {
		return this.point.getCoordinate();
	}
    
    //--------------------------------------

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
