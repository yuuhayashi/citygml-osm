package osm.surveyor.osm;

import java.io.Serializable;
import java.util.ArrayList;
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
	
	/**
	 * 指定されたリレーションを取得する
	 * @param id	リレーションID
	 * @return		null = 該当なし
	 */
    public RelationBean getRelation(long id) {
    	for (RelationBean relation : relationList) {
    		if (relation.getId() == id) {
    	    	return relation;
    		}
    	}
    	return null;
    }
	
    /**
     * 指定されたWAYを取得する
     * @param id	WAY ID
     * @return		null = 該当なし
     */
    public WayBean getWay(long id) {
    	for (WayBean way : wayList) {
    		if (way.getId() == id) {
    	    	return way;
    		}
    	}
    	return null;
    }

	/**
	 * 指定されたNODEを取得する
	 * @param id	NODE ID
	 * @return		null = 該当なし
	 */
    public NodeBean getNode(long id) {
    	for (NodeBean node : nodeList) {
    		if (node.getId() == id) {
    	    	return node;
    		}
    	}
    	return null;
    }

	//-------------------------------------
	
	@XmlAttribute(name="version")
    public String getVersion() {
        return ElementOsm.VERSION;
    }
	
	@XmlAttribute(name="generator")
    public String getGenerator() {
        return ElementOsm.GENERATOR;
    }

	private ElementBounds bounds;
	
    @XmlElement(name="bounds")
    public ElementBounds getBounds() {
		return this.bounds;
	}

    public void setBounds(ElementBounds bounds) {
    	this.bounds = bounds;
    }
	
    private List<NodeBean> nodeList = new ArrayList<>();
    
    @XmlElement(name="node")
    public List<NodeBean> getNodeList() {
    	return nodeList;
    }

    public void setNodeList(List<NodeBean> nodeList) {
    	this.nodeList = nodeList;
    }
    
    private List<WayBean> wayList = new ArrayList<>();
    
    @XmlElement(name="way")
    public List<WayBean> getWayList() {
    	return wayList;
    }

    public void setWayList(List<WayBean> wayList) {
    	this.wayList = wayList;
    }
    
    private List<RelationBean> relationList = new ArrayList<>();
    
    @XmlElement(name="relation")
    public List<RelationBean> getRelationList() {
    	return relationList;
    }

    public void setRelationList(List<RelationBean> relationList) {
    	this.relationList = relationList;
    }
}
