package osm.surveyor.osm.boxcel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.locationtech.jts.geom.Polygon;

import osm.surveyor.osm.BoundsBean;
import osm.surveyor.osm.WayBean;
import osm.surveyor.osm.way.Wayable;

public interface BoxcellMappable {
	
    public BoundsBean getBounds();
    public void setBounds(BoundsBean bounds);
    
    //---------------------------------
    
    public void setInxexMap(IndexMap indexMap);

    public IndexMap getIndexMap();
    
    //---------------------------------------
    
    public Map<Long,Wayable> getWayMap();
    public void setWayMap(Map<Long,Wayable> map);
    
    /**
     * 指定されたWAYを取得する
     * @param id	WAY ID
     * @return		null = 該当なし
     */
	default Wayable getWay(long id) {
		return this.getWayMap().get(Long.valueOf(id));
	}

	default void putWay(Wayable way) {
		this.getWayMap().put(Long.valueOf(way.getId()), way);
	}
    
    /**
     * 指定のwayBeanと重複するwayListを取得する
     * @param sWay
     * @return	sWayと重複するwayListを返す
     */
	default List<Wayable> getWayList(Wayable sWay) {
    	Map<Long,Wayable> map = new HashMap<>();
    	
    	// 指定のwayBeanと同じボクセルに存在するwayListを取得する
    	for (Integer boxcelId : sWay.getBoxels()) {
    		BoundsCellBean cell = getIndexMap().get(boxcelId);
    		HashMap<Long, Polygon> wayMap = cell.getWayMap();
        	for (Map.Entry<Long, Polygon> entry : wayMap.entrySet()) {
        		Long wayid = entry.getKey();
        		Wayable way = getWay(wayid);
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
    			double area = way.getIntersectArea(sWay);
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
	
	default List<WayBean> getWayList() {
		List<Wayable> list = new ArrayList<>();
		for (Map.Entry<Long, Wayable> entry : getWayMap().entrySet()) {
			Wayable way = entry.getValue();
			list.add(way);
		}
		return list;
	}
	
	default void setWayList(List<Wayable> wayList) {
		Map<Long,Wayable> map = new HashMap<>();
		for (Wayable way : wayList) {
			map.put(way.getId(), way);
		}
		setWayMap(map);
	}

	//-------------------------------------------------------------
	
    /**
     * indexMapに wayList のWayBeanを充填する
     */
    public void reindex();
    
    /**
     * Fileに記載されていないインスタンスを充填する
     * ・boundsからindexMapを構築する
     * ・indexMapに wayList のWayBeanを充填する
     */
    public void build();
    
}
