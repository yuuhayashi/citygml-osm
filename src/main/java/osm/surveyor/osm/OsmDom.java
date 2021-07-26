package osm.surveyor.osm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
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
import org.xml.sax.SAXParseException;

import osm.surveyor.osm.api.*;

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

    public void parse(File file) throws ParserConfigurationException, SAXException, IOException, ParseException {
    	parse(new FileInputStream(file));
    }

    /**
     * XML SAXパースを実行する
     * 
     */
    public void parse(InputStream is) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(false);
        
        SAXParser parser = factory.newSAXParser();
        try {
			parser.parse(is, new OsmParser(this));
		} catch (SAXParseException e) {}
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
    
	/**
	 *  OSMから<bound>範囲内の現在のデータを取得する
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws ProtocolException 
	 * @throws MalformedURLException 
	 */
	public void downloadMap() throws MalformedURLException, ProtocolException, IOException, ParserConfigurationException, SAXException {
        double minlon = Double.parseDouble(bounds.minlon);
		double maxlon = Double.parseDouble(bounds.maxlon);
		double minlat = Double.parseDouble(bounds.minlat);
		double maxlat = Double.parseDouble(bounds.maxlat);
		
		HttpGet http = new HttpGet();
		String urlstr = http.getHost() + "/api/0.6/map" + "?bbox="+ Double.toString(minlon) +","+ Double.toString(minlat) +","+ Double.toString(maxlon) +","+ Double.toString(maxlat);
        System.out.println("URL: " + urlstr);
        URL url = new URL(urlstr);
        
        HttpURLConnection urlconn = (HttpURLConnection)url.openConnection();
        try {
            urlconn.setRequestMethod("GET");
            urlconn.setInstanceFollowRedirects(false);
            urlconn.setRequestProperty("Accept-Language", "ja;q=0.7,en;q=0.3");
            urlconn.connect();

    		GetResponse res = new GetResponse();
            res.setCode(urlconn.getResponseCode());
    		res.setMessage(urlconn.getResponseMessage());
    		parse(urlconn.getInputStream());
        }
        finally {
            urlconn.disconnect();
        }
	}
	
	/**
	 * 取得したデータからRELATION:buildingオブジェクトのみ抽出する
	 * @param ddom	抽出 destination
	 * @return	抽出された新しいインスタンス
	 */
	public OsmDom filterBuilding(OsmDom ddom) {
		ddom.setBounds(this.getBounds());

		// 取得したデータからRELATION:buildingオブジェクトのみ抽出する
		for (String rKey : this.relations.keySet()) {
			ElementRelation relation = this.relations.get(rKey);
			if (relation.isBuilding()) {
				addRelation(ddom, relation);
			}
			else if (relation.isMultipolygon()) {
				if (relation.getTagValue("building") != null) {
					addRelation(ddom, relation);
				}
				else if (relation.getTagValue("building:part") != null) {
					addRelation(ddom, relation);
				}
			}
		}
		
		for (String rKey : this.ways.keySet()) {
			ElementWay way = this.ways.get(rKey);
			if (way.isBuilding()) {
				addWay(ddom, way);
			}
		}
		return ddom;
	}
	
	void addRelation(OsmDom ddom, ElementRelation relation) {
		for (ElementMember member : relation.members) {
			if (member.type.equals("way")) {
				ElementWay way = this.ways.get(member.ref);
				if (way != null) {
					addWay(ddom, way);
				}
			}
		}
		ddom.relations.put(relation.clone());
	}
	
	void addWay(OsmDom ddom, ElementWay way) {
		for (OsmNd nd : way.nds) {
			ElementNode node = this.nodes.get(nd.id);
			if (node != null) {
				ddom.nodes.put(node.clone());
			}
		}
		ddom.ways.put(way.clone());
	}
	

    public void export(File out) throws ParserConfigurationException {
		DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = documentBuilder.newDocument();
		ElementOsm ooo = new ElementOsm();
    	Node osm = ooo.toNode(document);
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
				String height = way.getTagValue("height");
				if (height != null) {
					if (Double.parseDouble(maxheight) < Double.parseDouble(height)) {
						maxheight = height;
					}
				}
			}
		}
		return maxheight;
	}
	
	/**
	 * RELATIONに所属していないWAYを削除する
	 */
	public void gerbageWay() {
		WayMap map = new WayMap();
		for (String id : this.ways.keySet()) {
			ElementWay way = this.ways.get(id);
			if (!way.member) {
				map.put(way);	// 単独WAYは、RELATIONに所属していなくても削除しない
			}
		}
		for (String id : this.relations.keySet()) {
			ElementRelation relation = this.relations.get(id);
			for (ElementMember member : relation.members) {
				ElementWay way = this.ways.get(member.ref);
				if (way != null) {
					map.put(way);
				}
			}
		}
		this.ways.clear();
		for (String key : map.keySet()) {
			this.ways.put(map.get(key));
		}
	}
	
	/**
	 * WAYに所属していないNODEを削除する
	 */
	public void gerbageNode() {
		NodeMap map = new NodeMap();
		for (String wayid : this.ways.keySet()) {
			ElementWay way = this.ways.get(wayid);
			for (OsmNd nd : way.nds) {
				map.put(this.nodes.get(nd.id));
			}
		}
		this.nodes.clear();
		for (String key : map.keySet()) {
			this.nodes.put(map.get(key));
		}
	}
}
