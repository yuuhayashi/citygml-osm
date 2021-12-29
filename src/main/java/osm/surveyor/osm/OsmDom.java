package osm.surveyor.osm;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import osm.surveyor.citygml.CityModelParser;

/**
 * Osmファイルをドムる
 * 
 */
@XmlRootElement(name="osm")
public class OsmDom {
	static final String outputEncoding = "UTF-8";
	
    public OsmDom() {
        super();
        this.idno = 0;
        bounds = new BoundsBean();
        nodes = new NodeBeans();	// k= node.id
        ways = new WayMap();		// k= way.id
        relations = new RelationMap();	// k= relation.id
    }

    @XmlTransient
	public long idno;
	
    @XmlTransient
    BoundsBean bounds = null;
    
    public void setBounds(BoundsBean bounds) {
    	this.bounds = bounds;
    }
    
    @XmlElement(name="bounds")
    public BoundsBean getBounds() {
		return bounds;
	}

    @XmlTransient
    public String srsName = null;

	@XmlAttribute(name="generator")
	public String generator = "JOSM";
	
	@XmlAttribute(name="version")
	public String version = "0.6";
	
    @XmlElement(name="node")
    public NodeBeans nodes;	// k= node.id
    
    @XmlTransient
    public WayMap ways;		// k= way.id

	@XmlElement(name="way")
	public List<ElementWay> getWays() {
		return new ArrayList<ElementWay>(ways.values());
	}

    @XmlTransient
    public RelationMap relations;	// k= relation.id
    
	@XmlElement(name="relation")
	public List<ElementRelation> getRelations() {
		return new ArrayList<ElementRelation>(relations.values());
	}

	/**
      * シリアル番号を生成する
     * @return
     */
    public long getNewId() {
    	return --this.idno;
    }
    
    /**
     * XML SAXパースを実行する
     * 
    public void parse(File file) throws ParserConfigurationException, SAXException, IOException, ParseException {
    	parse(new FileInputStream(file));
    }

    public void parse(InputStream is) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(false);
        
        SAXParser parser = factory.newSAXParser();
        try {
			parser.parse(is, new OsmParser(this));
		} catch (SAXParseException e) {}
    }
     */
    
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
		return CityModelParser.rounding(2, maxheight);
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
	
	/**
	 * 指定された"ref:MLIT_PLATEAU"のWAYを取得する
	 * 
	 * @param buildingid
	 * @return
	 */
    public List<ElementWay> getWay(String buildingid) {
    	List<ElementWay> ret = new ArrayList<>();
    	for (String wayid : this.ways.keySet()) {
			ElementWay way = this.ways.get(wayid);
			List<TagBean> tags = way.getTagList();
			for (TagBean tag : tags) {
				if (tag.k.equals("ref:MLIT_PLATEAU") && tag.v.equals(buildingid)) {
					ret.add(way);
				}
			}
		}
    	return ret;
    }

    /**
	 * 指定された"ref:MLIT_PLATEAU"のWAYの親リレーションを取得する
	 * 
	 * @param buildingid
	 * @return
	 */
    public List<ElementRelation> getRelation(String buildingid) {
    	List<ElementRelation> ret = new ArrayList<>();
    	List<ElementWay> ways = getWay(buildingid);
    	for (ElementWay way : ways) {
    		long wayid = way.getId();
			for (ElementRelation relation : getRelations()) {
				for (MemberBean member : relation.members) {
					if (member.isWay()) {
						if (wayid == member.getRef()) {
							ret.add(relation);
						}
					}
				}
			}
		}
    	return ret;
    }
}
