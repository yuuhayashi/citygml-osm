package osm.surveyor.osm;

import org.w3c.dom.Element;
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
public class ElementNode extends ElementOsmapi implements Cloneable {
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
        node.setAttribute("ref", Long.toString(id));
        return (Node)node;
    }
    
    /**
     * <node id='-107065' action='modify' visible='true' lat='35.43464696576' lon='139.4102145808' />
     * @param doc
     * @return
     */
    public Node toNode(Document doc) {
		Element node = (Element) doc.createElement("node");
        node.setAttribute("id", Long.toString(id));
        node.setAttribute("action", action);
        node.setAttribute("visible", Boolean.toString(visible));
        node.setAttribute("lat", point.lat);
        node.setAttribute("lon", point.lon);
        doc.appendChild((Node) node);
        return (Node)node;
    }

    
    //--------------------------------------

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((height == null) ? 0 : height.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((point == null) ? 0 : point.hashCode());
		result = prime * result + (visible ? 1231 : 1237);
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
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (height == null) {
			if (other.height != null)
				return false;
		} else if (!height.equals(other.height))
			return false;
		if (id != other.id)
			return false;
		if (point == null) {
			if (other.point != null)
				return false;
		} else if (!point.equals(other.point))
			return false;
		if (visible != other.visible)
			return false;
		return true;
	}
}
