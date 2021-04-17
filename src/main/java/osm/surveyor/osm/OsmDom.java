package osm.surveyor.osm;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

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
	
    public OsmDom() {
        super();
    }

    Document doc;
    ElementBounds bounds = new ElementBounds();
    public HashMap<String, ElementNode> nodes = new HashMap<>();
    public HashMap<String, ElementWay> ways = new HashMap<>();
    public HashMap<String, ElementRelation> relations = new HashMap<>();
    
    public void setBounds(ElementBounds bounds) {
    	this.bounds = bounds;
    }
    
    public ElementBounds getBounds() {
		return bounds;
	}


	public void addNode(ElementNode node) {
    	this.nodes.put(Long.toString(node.id), node);
    }

    public void addWay(ElementWay way) {
    	this.ways.put(Long.toString(way.id), way);
    }
    
	public ElementWay getWay(long id) {
		return getWay(Long.toString(id));
	}
	
	public ElementWay getWay(String idStr) {
		return this.ways.get(idStr);
	}
    
    public void addRelations(ElementRelation relation) {
    	this.relations.put(Long.toString(relation.id), relation);
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
	    loadNodes(doc, nodes);
	    loadWays(doc, ways);
	    loadRelations(doc, relations);

	    // Relation->member->WAY は、WAY.member=true にする
		for (String rKey : relations.keySet()) {
			ElementRelation relation = relations.get(rKey);
			for (ElementMember menber : relation.members) {
				ElementWay sWay = ways.get(Long.toString(menber.ref));
				if (sWay != null) {
					sWay.member = true;
					ways.put(Long.toString(menber.ref), sWay);
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
    		ElementRelation relation = relations.get(key);
    		for (ElementMember member : relation.members) {
    			if (ways.containsKey(Long.toString(member.ref))) {
        			ElementWay way = ways.get(Long.toString(member.ref));
    				osm.appendChild(way.toNode(document));
    			}
    		}
        	osm.appendChild(relation.toNode(document));
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
    
    void loadNodes(Document doc, HashMap<String, ElementNode> nodes) {
	    NodeList nList = doc.getElementsByTagName("node");
	    for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			ElementNode node = (new ElementNode(0)).loadNode(nNode);
			if (node != null) {
				nodes.put(String.valueOf(node.id), node);
			}
	    }
    }

    /**
     * <way changeset="61979354" id="96350144" timestamp="2018-08-25T08:34:33Z" uid="7548722" user="Unnown" version="17" visible="true">
     * 	<way id='-2' action='modify' visible='true'>
	 * 	  <nd ref='-3'/>
	 * 	  <nd ref='-4'/>
	 * 	  <nd ref='-5'/>
	 * 	  <tag k='height' v='14.072000000000001' />
	 * 	  <tag k='building:part' v='yes' />
	 * 	</way>
     * @param doc
     * @param ways
     */
    void loadWays(Document doc, HashMap<String,ElementWay> ways) {
	    NodeList nList = doc.getElementsByTagName("way");
	    for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			ElementWay way = (new ElementWay(0)).loadWay(nNode);
			if (way != null) {
			    ways.put(Long.toString(way.id), way);
			}
	    }
    }

    /**
     * 	<relation id='-1654' action='modify' visible='true'>
     *	  <member ref='-1655' type='way' role='part' />
     *	  <tag k='type' v='building' />
     *	  <tag k='source:ref:id' v='BLD_57cd4ea7-0fb7-4b0e-a600-9982cf3b60ca' />
     *	  <tag k='addr:full' v='東京都大田区南六郷三丁目' />
     *	</relation>
     * @param doc
     * @param relations
     */
    void loadRelations(Document doc, HashMap<String,ElementRelation> relations) {
	    NodeList nList = doc.getElementsByTagName("relation");
	    for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			ElementRelation relation = (new ElementRelation(0)).loadRelation(nNode);
			if (relation != null) {
			    relations.put(Long.toString(relation.id), relation);
			}
	    }
    }
}
