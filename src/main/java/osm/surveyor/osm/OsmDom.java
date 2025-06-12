package osm.surveyor.osm;

import java.io.File;
import java.io.PrintStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.locationtech.jts.geom.Polygon;

import osm.surveyor.citygml.CityModelParser;
import osm.surveyor.osm.boxcel.BoundsCellBean;
import osm.surveyor.osm.boxcel.BoxcellMappable;
import osm.surveyor.osm.boxcel.IndexMap;
import osm.surveyor.osm.way.Wayable;

/**
 * Osmファイルをドムる
 * 
 */
@XmlRootElement(name="osm")
public class OsmDom implements BoxcellMappable {
	static final String outputEncoding = "UTF-8";
	
    public OsmDom() {
        super();
        this.idno = 0;
        bounds = new BoundsBean();
        indexMap = new IndexMap();
        nodes = new NodeBeans();	// k= node.id
        ways = new WayMap();		// k= way.id
        relations = new RelationMap();	// k= relation.id
    }

    /**
     * Fileに記載されていないインスタンスを充填する
     * ※ Nodeインスタンスに POINT を充填する
     * ・boundsからindexMapを構築する
     * ※ indexMapに wayList のWayBeanを充填する
     * 
     */
	@Override
    public void build() {
    	// boundsからindexMapを構築する
    	this.indexMap.setBounds(this.bounds);
    	
    	for (NodeBean node : this.nodes) {
    		OsmPoint point = new OsmPoint();
    		point.setLat(node.getLat());
    		point.setLon(node.getLon());
    		node.setPoint(point);
    	}
    	
    	for (Map.Entry<String,ElementWay> entry : this.ways.entrySet()) {
    		ElementWay way = entry.getValue();
    		for (NdBean nd : way.getNdList()) {
    			NodeBean node = getNode(nd.getRef());
    			if (node != null) {
        			nd.setPoint(node.getPoint());
    			}
    		}
    	}
    	
    	// indexMapに wayList のWayBeanを充填する
    	for (Map.Entry<String,ElementWay> entry : this.ways.entrySet()) {
    		ElementWay way = entry.getValue();
    		this.indexMap.putWayType(way);
    	}
    }


    @XmlTransient
	public long idno;

	/**
      * シリアル番号を生成する
     * @return
     */
    public long getNewId() {
    	return --this.idno;
    }
	
    //--------------- IndexMap indexMap --------------------
	public IndexMap indexMap = null;
	
	@Override
	public IndexMap getIndexMap() {
		return this.indexMap;
	}

	@Override
	public void setInxexMap(IndexMap indexMap) {
		this.indexMap = indexMap;
	}
	
	@Override
	public void reindex() {
    	for (Map.Entry<Integer,BoundsCellBean> entry : this.indexMap.entrySet()) {
    		BoundsCellBean cell = entry.getValue();
    		cell.clearWaylist();
    	}
    	
    	// indexMapに wayList のWayBeanを充填する
    	for (Map.Entry<String,ElementWay> entry : this.ways.entrySet()) {
    		ElementWay way = entry.getValue();
    		way.getBoxels().clear();
    		this.indexMap.putWayType(way);
    	}
	}

    //--------------- BoundsBean bounds --------------------
    @XmlTransient
    BoundsBean bounds = null;
        
    public void setBounds(BoundsBean bounds) {
    	this.bounds = bounds;
    	this.indexMap.setBounds(bounds);
    }
    
    @XmlElement(name="bounds")
    public BoundsBean getBounds() {
		return bounds;
	}

    //---------------  --------------------
    @XmlTransient
    public String srsName = null;

	@XmlAttribute(name="generator")
	public String generator = "JOSM";
	
	@XmlAttribute(name="version")
	public String version = "0.6";
	
    //--------------- NODE --------------------
    @XmlElement(name="node")
    public NodeBeans nodes;	// k= node.id
    
	/**
	 * 指定されたNODEを取得する
	 * @param id	NODE ID
	 * @return		null = 該当なし
	 */
    public NodeBean getNode(long id) {
    	for (NodeBean obj : this.nodes) {
    		if (obj.getId() == id) {
    			return obj;
    		}
    	}
    	return null;
    }
    
    //--------------- WAY --------------------
    @XmlTransient
    public WayMap ways;		// k= way.id

	@XmlElement(name="way")
	public List<ElementWay> getWays() {
		return new ArrayList<ElementWay>(ways.values());
	}


    /**
     * 指定のwayBeanと重複するwayListを取得する
     * @param wayBean
     * @return	wayBeanと重複するwayListを返す
     */
	@Override
	public List<ElementWay> getWayList(Wayable wayBean) {
    	Map<Long,Wayable> map = new HashMap<>();
    	
    	// 指定のwayBeanと同じボクセルに存在するwayListを取得する
    	for (Integer boxcelId : wayBean.getBoxels()) {
    		BoundsCellBean cell = this.indexMap.get(boxcelId);
    		HashMap<Long, Polygon> wayMap = cell.getWayMap();
        	for (Map.Entry<Long, Polygon> entry : wayMap.entrySet()) {
        		Long wayid = entry.getKey();
        		Wayable way = getWayBean(wayid);
        		if (way != null) {
        			map.put(wayid, way);
        		}
        	}
    	}
    	
    	// 指定のwayBeanと重複するwayListを取得する
    	List<Long> removeList = new ArrayList<>();
    	for (Map.Entry<Long, Wayable> entry : map.entrySet()) {
    		Long wayid = entry.getKey();
    		Wayable way = entry.getValue();
    		if (way != null) {
    			double area = way.getIntersectArea(wayBean);
    			if (area == 0.0) {
    				removeList.add(wayid);
    			}
    			else {
    				way.setDuplicateArea(area);
    			}
    		}
    	}
    	for (Long id : removeList) {
    		map.remove(id);
    	}
    	
    	List<Wayable> list = new ArrayList<>();
    	for (Map.Entry<Long, Wayable> entry : map.entrySet()) {
    		list.add(entry.getValue());
    	}
    	return list;
		// TODO Auto-generated method stub
		return this.wayList;
	}

	@Override
	public Wayable getWay(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putWay(Wayable way) {
		this.indexMap.putWayType(way);
	}

	void addWay(OsmDom ddom, ElementWay way) {
		for (OsmNd nd : way.nds) {
			NodeBean node = this.nodes.get(nd.id);
			if (node != null) {
				ddom.nodes.put(node.clone());
			}
		}
		ddom.ways.put(way.clone());
		ddom.indexMap.putElementWay(way);
	}
	
    //--------------- RELATION --------------------
    @XmlTransient
    public RelationMap relations;	// k= relation.id
    
	@XmlElement(name="relation")
	public List<ElementRelation> getRelations() {
		return new ArrayList<ElementRelation>(relations.values());
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
	
    //--------------- RELATION --------------------
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
    
    //--------------- TAGs --------------------
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
	
	public String getMinEle(ElementRelation relation) {
		String minele = "10000";
		for (MemberBean member : relation.members) {
			if (member.getRole().equals("part")) {
				ElementWay way = ways.get(member.getRef());
				String ele = way.getTagValue("ele");
				if (ele != null) {
					if (Double.parseDouble(minele) > Double.parseDouble(ele)) {
						minele = ele;
					}
				}
			}
		}
		return CityModelParser.rounding(1, minele);
	}
	
	public String getMaxHeight(ElementRelation relation) {
		String minele = getMinEle(relation);
		String maxheight = "0";
		for (MemberBean member : relation.members) {
			if (member.getRole().equals("part")) {
				ElementWay way = ways.get(member.getRef());
				String ele = way.getTagValue("ele");
				String height = way.getTagValue("height");
				if (height != null) {
					if (ele != null) {
						double height1 = (Double.parseDouble(ele) - Double.parseDouble(minele) + Double.parseDouble(height));
						if (Double.parseDouble(maxheight) < height1) {
							maxheight = CityModelParser.rounding(2, String.valueOf(height1));
						}
					}
					else {
						if (Double.parseDouble(maxheight) < Double.parseDouble(height)) {
							maxheight = height;
						}
					}
				}
			}
		}
		return CityModelParser.rounding(2, maxheight);
	}

	public void toOutline() {
		System.out.println(LocalTime.now() +"\tOsmDom.toOutline()");
		for (String id : this.relations.keySet()) {
			ElementRelation relation = this.relations.get(id);
			if (relation.isBuilding()) {
				for (MemberBean member : relation.members) {
					if (member.getRole().equals("outline") && member.getType().equals("relation")) {
						ElementRelation multi = this.relations.get(member.getRef());
						if (multi !=null && multi.isMultipolygon()) {
							TagBean buil = multi.getTag("building");
							if (buil != null) {
								multi.replaceTag("building", new TagBean("building:part", buil.v));
							}
						}
					}
				}
			}
		}
	}

	/**
	 * オブジェクトが存在しないメンバーをRELATIONから削除する
	 */
	public void gerbageMember() {
		for (String id : this.relations.keySet()) {
			ElementRelation relation = this.relations.get(id);
			ArrayList<MemberBean> mems = new ArrayList<>();
			for (MemberBean member : relation.members) {
				if (member.isWay()) {
					ElementWay way = this.ways.get(member.getRef());
					if (way == null) {
						mems.add(member);
					}
				}
				else if (member.isRelation()) {
					ElementRelation r = this.relations.get(member.getRef());
					if (r == null) {
						mems.add(member);
					}
				}
			}
			for (MemberBean member : mems) {
				relation.removeMember(member.getRef());
			}
		}
	}
	
	/**
	 * RELATIONに所属していないWAYを削除する
	 */
	public void gerbageWay() {
		System.out.println(LocalTime.now() +"\tOsmDom.gerbageWay()");
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
					break;
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
