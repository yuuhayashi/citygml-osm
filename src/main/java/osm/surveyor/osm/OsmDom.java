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
	
	public static void main(String[] args) {
		
	}

    public OsmDom() {
        super();
    }

    Document doc;
    ElementBounds bounds = new ElementBounds();
    HashMap<String, ElementNode> nodes = new HashMap<>();
    HashMap<String, ElementWay> ways = new HashMap<>();
    HashMap<String, ElementRelation> relations = new HashMap<>();
    
    public void load(File sourcefile) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    doc = dBuilder.parse(sourcefile);
	    doc.getDocumentElement().normalize();
	    load(doc);
	}
    
    public void load(Document doc) throws ParserConfigurationException, SAXException, IOException {
	    loadBounds(doc, bounds);
	    loadNodes(doc, nodes);
	    loadWays(doc, ways);
	    loadRelations(doc, relations);
	}

    public void export(File out) throws ParserConfigurationException {
		DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = documentBuilder.newDocument();
    	Node osm = (new ElementOsm()).toNode(document);
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
            transformer.transform(new DOMSource(doc), new StreamResult(out));
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
	    NodeList nList = doc.getElementsByTagName("bounds");
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
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				String id = eElement.getAttribute("id");
				ElementNode node = new ElementNode(Long.parseLong(id));
				node.action = eElement.getAttribute("action");
				node.visible = Boolean.valueOf(eElement.getAttribute("visible"));
				node.lat = eElement.getAttribute("lat");
				node.lon = eElement.getAttribute("lon");
				node.height = eElement.getAttribute("height");
				nodes.put(String.valueOf(node.id), node);
			}
	    }
    }

    /**
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
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				String id = eElement.getAttribute("id");
				ElementWay way = new ElementWay(Long.parseLong(id));
				way.action = eElement.getAttribute("action");
				way.visible = Boolean.valueOf(eElement.getAttribute("visible"));
				
				NodeList list2 = nNode.getChildNodes();
			    for (int temp2 = 0; temp2 < list2.getLength(); temp2++) {
					Node node2 = list2.item(temp2);
					if (node2.getNodeType() == Node.ELEMENT_NODE) {
						Element e2 = (Element) node2;
						if (node2.getNodeName().equals("nd")) {
							String ref = e2.getAttribute("ref");
							way.addNode(nodes.get(ref));
						}
						if (node2.getNodeName().equals("tag")) {
							String k = e2.getAttribute("k");
							String v = e2.getAttribute("v");
							way.addTag(k, v);
						}
					}
			    }
				
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
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				String id = eElement.getAttribute("id");
				ElementRelation relation = new ElementRelation(Long.parseLong(id));
				relation.action = eElement.getAttribute("action");
				relation.visible = Boolean.valueOf(eElement.getAttribute("visible"));
				
				NodeList list2 = nNode.getChildNodes();
			    for (int temp2 = 0; temp2 < list2.getLength(); temp2++) {
					Node node2 = list2.item(temp2);
					if (node2.getNodeType() == Node.ELEMENT_NODE) {
						Element e2 = (Element) node2;
						if (node2.getNodeName().equals("member")) {
							String ref = e2.getAttribute("ref");
							String role = e2.getAttribute("role");
							relation.addMember(ways.get(ref), role);
						}
						if (node2.getNodeName().equals("tag")) {
							String k = e2.getAttribute("k");
							String v = e2.getAttribute("v");
							relation.addTag(k, v);
						}
					}
			    }
				
			    relations.put(Long.toString(relation.id), relation);
			}
	    }
    }
}
