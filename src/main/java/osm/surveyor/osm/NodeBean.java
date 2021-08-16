package osm.surveyor.osm;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.locationtech.jts.geom.Coordinate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @code{
 * <osm>
 *  <node id='-107065' action='modify' visible='true' lat='35.43464696576' lon='139.4102145808' />
 *  <node id='1803576119' timestamp='2012-06-27T05:23:26Z' uid='618878' user='nakanao' visible='true' version='1' changeset='12032994' lat='35.5420516' lon='139.7149118' />
 *         :
 * </osm>
 * }
 * 
 */
@XmlRootElement(name="node")
public class NodeBean extends PoiBean implements Cloneable,Serializable {
	private static final long serialVersionUID = -6012637985828366692L;

	public OsmPoint point;
	private String height = null;
	
	public NodeBean(long id) {
		super(id);
		this.point = new OsmPoint();
	}
	
	public NodeBean() {
		this(0l);
	}

	@XmlAttribute(name="lat")
	public String getLat() {
		return this.point.lat;
	}
	public void setLat(String lat) {
		this.point.setLat(lat);
	}

	@XmlAttribute(name="lon")
	public String getLon() {
		return this.point.lon;
	}
	public void setLon(String lon) {
		this.point.setLon(lon);
	}

	@XmlTransient
	public OsmPoint getPoint() {
		return point;
	}
	public void setPoint(OsmPoint point) {
		this.point = point;
	}
	
	@XmlAttribute(name="height")
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}

	/**
     * <nd ref='-4'/>
     */
    public Node toNodeNd(Document doc) {
		Element node = (Element) doc.createElement("nd");
        node.setAttribute("ref", getIdstr());
        return (Node)node;
    }
    
    /**
     * <node id='-107065' action='modify' visible='true' lat='35.43464696576' lon='139.4102145808' />
     * @param doc
     * @return
     */
    public Node toNode(Document doc) {
    	Element element = toElement(doc, "node");
    	element.setAttribute("lat", point.lat);
    	element.setAttribute("lon", point.lon);
        return (Node)element;
    }

    /**
     * <node id='-107065' action='modify' visible='true' lat='35.43464696576' lon='139.4102145808' />
     * <node id='1803576119' timestamp='2012-06-27T05:23:26Z' uid='618878' user='nakanao' visible='true' version='1' changeset='12032994' lat='35.5420516' lon='139.7149118' />
     * 
     * @param nNode		doc.getElementsByTagName("node")
     */
    public NodeBean loadNode(Node nNode) {
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			loadElement(eElement);
			this.point.lat = eElement.getAttribute("lat");
			this.point.lon = eElement.getAttribute("lon");
			this.height = eElement.getAttribute("height");
			return this;
		}
		return null;
    }

	public Coordinate getCoordinate() {
		return this.point.getCoordinate();
	}
    
    //--------------------------------------

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((height == null) ? 0 : height.hashCode());
		result = prime * result + ((point == null) ? 0 : point.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		NodeBean other = (NodeBean) obj;
		if (height == null) {
			if (other.height != null)
				return false;
		} else if (!height.equals(other.height))
			return false;
		if (point == null) {
			if (other.point != null)
				return false;
		} else if (!point.equals(other.point))
			return false;
		return true;
	}

	@Override
	public NodeBean clone() {
		NodeBean b = (NodeBean) super.clone();
		b.setLat(this.getLat());
		b.setLon(this.getLon());
		b.setHeight(this.getHeight());
		return b;
	}
}
