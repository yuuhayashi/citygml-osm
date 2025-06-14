package osm.surveyor.osm;

import java.io.File;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.locationtech.jts.geom.Polygon;

import osm.surveyor.osm.boxcel.BoundsCellBean;
import osm.surveyor.osm.boxcel.BoxcellMappable;
import osm.surveyor.osm.boxcel.IndexMap;
import osm.surveyor.osm.way.Wayable;

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
public class OsmBean implements Serializable,BoxcellMappable {
	private static final long serialVersionUID = 1L;
	public static String VERSION = "0.6";
	public static String GENERATOR = "JOSM";
	
	public OsmBean() {
		super();
	}
	
	@XmlAttribute(name="version")
    public String getVersion() {
        return OsmBean.VERSION;
    }
	
	@XmlAttribute(name="generator")
    public String getGenerator() {
        return OsmBean.GENERATOR;
    }

	private BoundsBean bounds;
	private IndexMap indexMap = new IndexMap();
	
    @XmlElement(name="bounds")
    public BoundsBean getBounds() {
		return this.bounds;
	}
    public void setBounds(BoundsBean bounds) {
    	this.bounds = bounds;
    	this.indexMap.setBounds(bounds);
    }
    
	@Override
	public IndexMap getIndexMap() {
		return this.indexMap;
	}

	@Override
	public void setInxevMap(IndexMap indexMap) {
		this.indexMap = indexMap;
	}	
	
    public void reindex() {
    	for (Map.Entry<Integer,BoundsCellBean> entry : this.indexMap.entrySet()) {
    		BoundsCellBean cell = entry.getValue();
    		cell.clearWaylist();
    	}
    	
    	// indexMapに wayList のWayBeanを充填する
    	for (Wayable way : this.wayList) {
    		way.getBoxels().clear();
    		this.indexMap.putWayType(way);
    	}
    }
    
    private List<NodeBean> nodeList = new ArrayList<>();
    
    @XmlElement(name="node")
    public List<NodeBean> getNodeList() {
    	return this.nodeList;
    }
    public void setNodeList(List<NodeBean> nodeList) {
    	this.nodeList = nodeList;
    }
    
	
    private List<WayBean> wayList = new ArrayList<>();
    
    @XmlElement(name="way")
    public List<WayBean> getWayList() {
    	return this.wayList;
    }
    
    /**
     * 指定のwayBeanと重複するwayListを取得する
     * @param wayBean
     * @return	wayBeanと重複するwayListを返す
     */
	public List<Wayable> getWayList(Wayable wayBean) {
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
    }
	
    public void setWayList(List<WayBean> wayList) {
    	this.wayList = wayList;
    }
    
    /**
     * 指定されたWAYを取得する
     * @param id	WAY ID
     * @return		null = 該当なし
     */
    public WayBean getWay(long id) {
    	for (WayBean obj : this.wayList) {
    		if (obj.getId() == id) {
    			return obj;
    		}
    	}
    	return null;
    }

    private Wayable getWayBean(long wayid) {
    	for (Wayable way : this.wayList) {
    		if (way.getId() == wayid) {
    			return way;
    		}
    	}
    	return null;
    }

    private List<RelationBean> relationList = new ArrayList<>();
    
    @XmlElement(name="relation")
    public List<RelationBean> getRelationList() {
    	return this.relationList;
    }
    public void setRelationList(List<RelationBean> relationList) {
    	this.relationList = relationList;
    }

	//-------------------------------------
    
    /**
     * Fileに記載されていないインスタンスを充填する
     * ※ Nodeインスタンスに POINT を充填する
     * ・boundsからindexMapを構築する
     * ※ indexMapに wayList のWayBeanを充填する
     * 
     */
    public void build() {
    	// boundsからindexMapを構築する
    	this.indexMap.setBounds(this.bounds);
    	
    	for (NodeBean node : this.nodeList) {
    		OsmPoint point = new OsmPoint();
    		point.setLat(node.getLat());
    		point.setLon(node.getLon());
    		node.setPoint(point);
    	}
    	for (Wayable way : this.wayList) {
    		for (NdBean nd : way.getNdList()) {
    			NodeBean node = getNode(nd.getRef());
    			if (node != null) {
        			nd.setPoint(node.getPoint());
    			}
    		}
    	}
    	// indexMapに wayList のWayBeanを充填する
    	for (Wayable way : this.wayList) {
    		this.indexMap.putWayType(way);
    	}
    }

    /**
	 * 指定されたリレーションを取得する
	 * @param id	リレーションID
	 * @return		null = 該当なし
	 */
    public RelationBean getRelation(long id) {
    	for (RelationBean obj : this.relationList) {
    		if (obj.getId() == id) {
    			return obj;
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
    	for (NodeBean obj : this.nodeList) {
    		if (obj.getId() == id) {
    			return obj;
    		}
    	}
    	return null;
    }

	//-------------------------------------
	
	public void putNode(NodeBean poi) {
		List<NodeBean> removeList = new ArrayList<>();
    	for (NodeBean obj : this.nodeList) {
    		if (obj.getId() == poi.getId()) {
    			removeList.add(obj);
    		}
    	}
    	for (NodeBean obj : removeList) {
    		this.nodeList.remove(this.nodeList.indexOf(obj));
    	}
		this.nodeList.add(poi);
	}

	public void putWay(Wayable way) {
		List<WayBean> removeList = new ArrayList<>();
    	for (WayBean obj : this.wayList) {
    		if (obj.getId() == way.getId()) {
    			removeList.add(obj);
    		}
    	}
    	for (WayBean obj : removeList) {
    		this.wayList.remove(this.wayList.indexOf(obj));
    	}
		this.wayList.add((WayBean)way);
		this.indexMap.putWayType(way);
	}
	
	public void putRelation(RelationBean poi) {
		List<RelationBean> removeList = new ArrayList<>();
		for (RelationBean obj : this.relationList) {
			if (obj.getId() == poi.getId()) {
    			removeList.add(obj);
			}
		}
    	for (RelationBean obj : removeList) {
    		int index = this.relationList.indexOf(obj);
    		if (index >= 0) {
        		this.relationList.remove(index);
    		}
    	}
		getRelationList().add(poi);
	}
    
	/**
	 * NODEを削除する
	 * @param poi
	 */
	public void removeNode(NodeBean poi) {
		long id = poi.getId();
		for (NodeBean node : this.nodeList) {
			if (node.getId() == id) {
		    	int index = this.nodeList.indexOf(node);
		    	if (index >= 0) {
		    		this.nodeList.remove(index);
		    	}
		    	return;
			}
		}
	}
	
	/**
	 * WAYを削除する
	 * WAYに紐づくNODEも削除する
	 * IndexMapから削除する
	 * @param poi
	 */
	public void removeWay(Wayable poi) {
    	int index = this.wayList.indexOf(poi);
    	if (index >= 0) {
    		this.wayList.remove(index);
    	}
    	this.indexMap.removeWayBean(poi);
	}
	
	/**
	 * RELATIONを削除する
	 * RELATIONのメンバーも削除する
	 * @param poi
	 */
	public void removeRelation(RelationBean poi) {
		if (poi != null) {
			List<MemberBean> members = poi.getMemberList();
			for (MemberBean member : members) {
				if (member.isRelation()) {
					removeRelation(this.getRelation(member.getRef()));
				}
				else if (member.isWay()) {
					removeWay(this.getWay(member.getRef()));
				}
			}
			int index = this.relationList.indexOf(poi);
			if (index >= 0) {
				this.relationList.remove(index);
			}
		}
	}

	/**
	 * WAYに所属していないNODEを削除する
	 */
	public void gerbageNode() {
		List<NodeBean> list = new ArrayList<>();
		for (Wayable way : this.wayList) {
			for (NdBean nd : way.getNdList()) {
				list.add(this.getNode(nd.getRef()));
			}
		}
		this.nodeList.clear();
		for (NodeBean bean : list) {
			this.putNode(bean);
		}
	}
	
	public ArrayList<RelationBean> getParents(PoiBean obj) {
    	ArrayList<RelationBean> list = new ArrayList<>();
    	for (RelationBean relation : this.relationList) {
    		if (relation.hasMember(obj.getId())) {
    			list.add(relation);
    		}
    	}
    	return list;
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
}
