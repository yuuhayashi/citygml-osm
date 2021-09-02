package osm.surveyor.osm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.ArrayList;

import javax.xml.bind.JAXB;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Osmファイルをドムる
 * 
 */
public class OsmDom {
	static final String outputEncoding = "UTF-8";
	public long idno;
	
    ElementBounds bounds = new ElementBounds();
	public String source = null;
    public String srsName = null;
    public NodeBeans nodes;	// k= node.id
    public WayMap ways;		// k= way.id
    public RelationMap relations = new RelationMap();	// k= relation.id
    
    public OsmDom() {
        super();
        this.idno = 0;
        nodes = new NodeBeans();	// k= node.id
        ways = new WayMap();		// k= way.id
        
    }

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
    
	void addRelation(OsmDom ddom, ElementRelation relation) {
		for (MemberBean member : relation.members) {
			if (member.getType().equals("way")) {
				ElementWay way = this.ways.get(member.getRef());
				if (way != null) {
					addWay(ddom, way);
				}
			}
		}
		ddom.relations.put(relation.clone());
	}
	
	void addWay(OsmDom ddom, ElementWay way) {
		for (OsmNd nd : way.nds) {
			NodeBean node = this.nodes.get(nd.id);
			if (node != null) {
				ddom.nodes.put(node.clone());
			}
		}
		ddom.ways.put(way.clone());
	}
	
    public void export(PrintStream out) {
    	JAXB.marshal(this, out);
    }
    public void export(String filepath) {
    	try (PrintStream ps = new PrintStream(filepath, "utf-8")) {
        	export(ps);
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    public void export(File file) {
    	export(file.getAbsolutePath());
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
    

	public ArrayList<ElementRelation> getParents(PoiBean obj) {
    	ArrayList<ElementRelation> list = new ArrayList<>();
    	for (String id : relations.keySet()) {
    		ElementRelation relation = relations.get(id);
    		for (MemberBean mem : relation.members) {
    			if (mem.getRef() == obj.getId()) {
    				list.add(relation);
    			}
    		}
    	}
    	return list;
    }
	
	public String getMaxHeight(ElementRelation relation) {
		String maxheight = "0";
		for (MemberBean member : relation.members) {
			if (member.getRole().equals("part")) {
				ElementWay way = ways.get(member.getRef());
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
			for (MemberBean member : relation.members) {
				ElementWay way = this.ways.get(member.getRef());
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
		NodeBeans list = new NodeBeans();
		for (String wayid : this.ways.keySet()) {
			ElementWay way = this.ways.get(wayid);
			for (OsmNd nd : way.nds) {
				NodeBean node = this.nodes.get(nd.id);
				list.put(node);
			}
		}
		this.nodes.clear();
		for (NodeBean bean : list) {
			this.nodes.put(bean);
		}
	}
}
