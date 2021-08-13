package osm.surveyor.osm;

import org.w3c.dom.Element;

import java.io.Serializable;

import org.locationtech.jts.geom.Coordinate;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * CityGMLファイルをパースする
 * @param gmlFile
 * @code{
 * <osm>
 *   <node id='-107065' action='modify' visible='true' lat='35.43464696576' lon='139.4102145808' />
 *         :
 * </osm>
 * }
 * 
 */
public class ElementNode extends PoiBean implements Cloneable,Serializable {
	private static final long serialVersionUID = 1L;
	public OsmPoint point = null;
	public String height = null;

	public ElementNode(long id) {
		super(id);
		this.point = new OsmPoint();
	}
	
	@Override
	public ElementNode clone() {
		ElementNode copy = null;
		try {
			copy = (ElementNode)super.clone();
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
    public ElementNode loadNode(Node nNode) {
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
		int result = 1;
		result = prime * result + ((getAction() == null) ? 0 : getAction().hashCode());
		result = prime * result + ((height == null) ? 0 : height.hashCode());
		result = prime * result + (int) (getId() ^ (getId() >>> 32));
		result = prime * result + ((point == null) ? 0 : point.hashCode());
		result = prime * result + (getVisible() ? 1231 : 1237);
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
		ElementNode other = (ElementNode) obj;
		if (getAction() == null) {
			if (other.getAction() != null)
				return false;
		} else if (!getAction().equals(other.getAction()))
			return false;
		if (height == null) {
			if (other.height != null)
				return false;
		} else if (!height.equals(other.height))
			return false;
		if (getId() != other.getId())
			return false;
		if (point == null) {
			if (other.point != null)
				return false;
		} else if (!point.equals(other.point))
			return false;
		if (getVisible() != other.getVisible())
			return false;
		return true;
	}
}
