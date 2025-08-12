package osm.surveyor.osm.boxcel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.locationtech.jts.geom.Polygon;

import osm.surveyor.gis.point.NdModel;
import osm.surveyor.osm.BoundsBean;
import osm.surveyor.osm.NodeBean;
import osm.surveyor.osm.OsmPoint;
import osm.surveyor.osm.WayMap;
import osm.surveyor.osm.way.Areable;
import osm.surveyor.osm.way.WayModel;

public interface BoxcellMappable {
	
    public BoundsBean getBounds();
    public void setBounds(BoundsBean bounds);
    
    public IndexMap getIndexMap();
	public void setIndexMap(IndexMap indexMap);
    
    public List<NodeBean> getNodeList();
    public void setNodeList(List<NodeBean> nodeList);
    
	public WayMap getWayMap();
    
	/**
	 * 指定されたNODEを取得する
	 * @param id	NODE ID
	 * @return		null = 該当なし
	 */
    default NodeBean getNode(long id) {
    	for (NodeBean obj : getNodeList()) {
    		if (obj.getId() == id) {
    			return obj;
    		}
    	}
    	return null;
    }    
    
    /**
     * indexMapに wayList のWayBeanを充填する
     */
	default void reindex() {
    	for (Map.Entry<Integer,BoundsCellBean> entry : getIndexMap().entrySet()) {
    		BoundsCellBean cell = entry.getValue();
    		cell.clearWaylist();
    	}
    	
    	// indexMapに wayList のWayBeanを充填する
    	for (WayModel way : getWayMap().values()) {
    		way.getBoxels().clear();
    		getIndexMap().putWayType(way);
    	}
    }
    
    /**
     * Fileに記載されていないインスタンスを充填する
     * ・boundsからindexMapを構築する
     * ・indexMapに wayList のWayBeanを充填する
     */
	default void build() {
    	// boundsからindexMapを構築する
		getIndexMap().setBounds(getBounds());
    	
    	for (NodeBean node : getNodeList()) {
    		OsmPoint point = new OsmPoint();
    		point.setLat(node.getLat());
    		point.setLon(node.getLon());
    		node.setPoint(point);
    	}
    	for (WayModel way : getWayMap().values()) {
    		for (NdModel nd : way.getNdList()) {
    			NodeBean node = getNode(nd.getRef());
    			if (node != null) {
        			nd.setPoint(node.getPoint());
    			}
    		}
    	}
    	// indexMapに wayList のWayBeanを充填する
    	for (WayModel way : getWayMap().values()) {
    		getIndexMap().putWayType(way);
    	}
    }
    
    /**
     * 指定のwayBeanと同じBOXCELの wayidListを取得する
     * @param wayBean
     * @return	指定のwayBeanと同じBOXCELの wayidListを
     */
	default public Set<Long> getDepulicateWayids(WayModel wayBean) {
    	Set<Long> set = new HashSet<Long>();
    	
    	// 指定のwayBeanと同じボクセルに存在するwayListを取得する
    	for (Integer boxcelId : wayBean.getBoxels()) {
    		BoundsCellBean cell = getIndexMap().get(boxcelId);
    		HashMap<Long, Polygon> wayMap = cell.getWayMap();
        	for (Map.Entry<Long, Polygon> entry : wayMap.entrySet()) {
        		Long wayid = entry.getKey();
        		if (wayid != wayBean.getId()) {
        			set.add(wayid);
        		}
        	}
    	}
    	return set;
    }

	/**
     * 指定のwayBeanと重複するwayListを取得する
     * @param wayBean
     * @return	wayBeanと重複するwayListを返す
     */
	default public List<WayModel> getDuplicateWayList(WayModel wayBean) {
    	
    	// 指定のwayBeanと同じボクセルに存在するwayListを取得する
    	Set<Long> set = getDepulicateWayids(wayBean);
    	
    	// 指定のwayBeanと重複するwayListを取得する
    	List<WayModel> list = new ArrayList<>();
    	for (Long weyid : set) {
    		WayModel way = getWay(weyid);
    		if (way != null) {
    			double area = way.getIntersectArea(wayBean);
    			if (area > 0.0) {
    				way.setDuplicateArea(area);
    	    		list.add(way);
    			}
    		}
    	}
    	return list;
    }
	
	/**
	 * WAYを削除する
	 * WAYに紐づくNODEも削除する
	 * IndexMapから削除する
	 * @param poi
	 */
	@SuppressWarnings("unlikely-arg-type")
	default public void removeWay(Areable way) {
		if (way != null) {
			getIndexMap().removeWayBean(way);
			getWayMap().remove(way);
		}
	}

    /**
     * 指定されたWAYを取得する
     * @param id	WAY ID
     * @return		null = 該当なし
     */
    default public WayModel getWay(long id) {
    	return getWayMap().get(id);
    }

	default public void putWay(WayModel way) {
		this.getWayMap().put(way.getIdstr(), way);
		this.getIndexMap().putWayType(way);
	}
}
