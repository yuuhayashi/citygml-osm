package osm.surveyor.osm;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * CityGMLファイルをパースする
 * @param gmlFile
 * @code{
 * <osm version='0.6' generator='JOSM'>
 *   <bounds minlat='35.4345061' minlon='139.410131' maxlat='35.4347116' maxlon='139.4104367' origin='CGImap 0.8.3 (2061540 spike-08.openstreetmap.org)' />
 *         :
 * </osm>
 * }
 */
@XmlRootElement(name="osm")
public class ElementOsm implements Serializable {
	private static final long serialVersionUID = 1L;
	public static String VERSION = "0.6";
	public static String GENERATOR = "JOSM";
	
	public ElementOsm() {
		super();
	}
	
	public Node toNode(Document document) throws ParserConfigurationException {
		Element osm = document.createElement("osm");
        osm.setAttribute("version", VERSION);
        osm.setAttribute("generator", GENERATOR);
        return (Node)osm;
    }
	
	@XmlAttribute(name="version")
    public String getVersion() {
        return ElementOsm.VERSION;
    }
	
	@XmlAttribute(name="generator")
    public String getGenerator() {
        return ElementOsm.GENERATOR;
    }

	private ElementBounds bounds;
	
    public ElementBounds getBounds() {
		return this.bounds;
	}

    @XmlElement(name="bounds")
    public void setBounds(ElementBounds bounds) {
    	this.bounds = bounds;
    }
	
    private List<NodeBean> nodeList;
    
    @XmlElement(name="node")
    public List<NodeBean> getNodeList() {
    	return nodeList;
    }

    public void setNodeList(List<NodeBean> nodeList) {
    	this.nodeList = nodeList;
    }
}
