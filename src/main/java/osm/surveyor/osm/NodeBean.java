package osm.surveyor.osm;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import osm.surveyor.citygml.CityModelParser;
import osm.surveyor.gis.node.NodeModel;

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
public class NodeBean extends NodeModel implements Cloneable,Serializable {
	private static final long serialVersionUID = -6012637985828366692L;

	//private OsmPoint point;
	
	public NodeBean(long id) {
		super(id);
		this.setPoint(new OsmPoint());
	}
	
	public NodeBean() {
		this(0l);
	}

	@Override
	public void setLat(String lat) {
		super.setLat(lat);
	}
	@Override
	public String getLat() {
		return super.getLat();
	}

	@Override
	public void setLon(String lon) {
		super.setLon(lon);
	}
	@Override
	public String getLon() {
		return super.getLon();
	}

	private String height = null;
	@XmlAttribute(name="height")
	public void setHeight(String height) {
		this.height = CityModelParser.checkNumberString(height);
	}
	public String getHeight() {
		return height;
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
    	element.setAttribute("lat", getLat());
    	element.setAttribute("lon", getLon());
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
			setLat(eElement.getAttribute("lat"));
			setLon(eElement.getAttribute("lon"));
			this.setHeight(eElement.getAttribute("height"));
			return this;
		}
		return null;
    }

    //--------------------------------------

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((height == null) ? 0 : height.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		NodeBean other = (NodeBean) obj;
		if (height == null) {
			if (other.getHeight() != null) {
				return false;
			}
		}
		else if (!height.equals(other.getHeight())) {
			return false;
		}
		return true;
	}

	@Override
	public NodeBean clone() {
		NodeBean b = (NodeBean) super.clone();
		b.setHeight(this.getHeight());
		return b;
	}
}
