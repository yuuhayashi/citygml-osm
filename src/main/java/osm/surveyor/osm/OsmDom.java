package osm.surveyor.osm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Osmファイルをドムる
 * 
 */
public class OsmDom {
	static final String outputEncoding = "UTF-8";
	public long idno;
	
    public OsmDom() {
        super();
        this.idno = 0;
        nodes = new NodeMap();	// k= node.id
        ways = new WayMap();		// k= way.id
        relations = new RelationMap();	// k= relation.id
    }

    Document doc;
    ElementBounds bounds = new ElementBounds();
	public String source = null;
    public String srsName = null;
    public NodeMap nodes;	// k= node.id
    public WayMap ways;		// k= way.id
    public RelationMap relations;	// k= relation.id
    
    /**
      * シリアル番号を生成する
     * @return
     */
    public long getNewId() {
    	return --this.idno;
    }
    
    public void setBounds(ElementBounds bounds) {
    	this.bounds = bounds;
    }
    
    public ElementBounds getBounds() {
		return bounds;
	}

    public Document load(File sourcefile) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    doc = dBuilder.parse(sourcefile);
	    doc.getDocumentElement().normalize();
	    load(doc);
	    return doc;
	}
    
    public void load(Document doc) throws ParserConfigurationException, SAXException, IOException {
	    loadBounds(doc, bounds);
	    nodes.load(doc);
	    ways.load(doc);
	    relations.load(doc);

	    // Relation->member->WAY は、WAY.member=true にする
		for (String rKey : relations.keySet()) {
			ElementRelation relation = relations.get(rKey);
			for (ElementMember menber : relation.members) {
				ElementWay sWay = ways.get(menber.ref);
				if (sWay != null) {
					sWay.member = true;
				}
			}
		}
	}

    public void export(File out) throws ParserConfigurationException {
		DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = documentBuilder.newDocument();
    	Node osm = (new ElementOsm()).toNode(document);
    	osm.appendChild(bounds.toNode(document));
    	for (String key : nodes.keySet()) {
        	osm.appendChild(nodes.get(key).toNode(document));
    	}
    	for (String key : ways.keySet()) {
        	osm.appendChild(ways.get(key).toNode(document));
    	}
    	for (String key : relations.keySet()) {
        	osm.appendChild(relations.get(key).toNode(document));
    	}
    	document.appendChild(osm);
    	
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
	        transformer.setOutputProperty("indent", "yes"); //改行指定
	        transformer.setOutputProperty("encoding", outputEncoding); // エンコーディング
            transformer.transform(new DOMSource(document), new StreamResult(out));
		}
		catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		catch (TransformerException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 
     * <bounds minlat='35.4345061' minlon='139.410131' maxlat='35.4347116' maxlon='139.4104367' origin='CGImap 0.8.3 (2061540 spike-08.openstreetmap.org)' />
     * @return
     */
    void loadBounds(Document doc, ElementBounds bounds) {
    	Element osmElement = doc.getDocumentElement();
    	NodeList nList = osmElement.getElementsByTagName("bounds");
	    for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				bounds.minlat = eElement.getAttribute("minlat");
				bounds.minlon = eElement.getAttribute("minlon");
				bounds.maxlat = eElement.getAttribute("maxlat");
				bounds.maxlon = eElement.getAttribute("maxlon");
				bounds.origin = eElement.getAttribute("origin");
			}
	    }
    }
    
    /**
     * 
     * @param poi
     * @return
     */
    public String getSource() {
    	String src = "";
    	if (this.source != null) {
    		src += this.source;
    	}
    	if (this.srsName != null) {
    		src += "; "+ this.srsName;
    	}
    	return src;
    }
    

	public ArrayList<ElementRelation> getParents(ElementOsmapi obj) {
    	ArrayList<ElementRelation> list = new ArrayList<>();
    	for (String id : relations.keySet()) {
    		ElementRelation relation = relations.get(id);
    		for (ElementMember mem : relation.members) {
    			if (mem.ref == obj.id) {
    				list.add(relation);
    			}
    		}
    	}
    	return list;
    }
	
	public String getMaxHeight(ElementRelation relation) {
		String maxheight = "0";
		for (ElementMember member : relation.members) {
			if (member.role.equals("part")) {
				ElementWay way = ways.get(member.ref);
				ElementTag ele = way.tags.get("height");
				if (ele != null) {
					if (Double.parseDouble(maxheight) < Double.parseDouble(ele.v)) {
						maxheight = ele.v;
					}
				}
			}
		}
		return maxheight;
	}
	
}
