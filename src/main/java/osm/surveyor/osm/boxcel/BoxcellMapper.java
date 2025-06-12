package osm.surveyor.osm.boxcel;

import java.util.HashMap;
import java.util.Map;

import osm.surveyor.osm.BoundsBean;
import osm.surveyor.osm.way.Wayable;

public class BoxcellMapper implements BoxcellMappable {
	
	private BoundsBean bounds;
	private IndexMap indexMap = new IndexMap();
    private Map<Long, Wayable> wayMap = new HashMap<>();

	@Override
	public BoundsBean getBounds() {
		return bounds;
	}

	@Override
	public void setBounds(BoundsBean bounds) {
    	this.bounds = bounds;
    	this.indexMap.setBounds(bounds);
	}

	@Override
	public IndexMap getIndexMap() {
		return this.indexMap;
	}

	@Override
	public void setInxexMap(IndexMap indexMap) {
		this.indexMap = indexMap;
	}

	@Override
	public Map<Long, Wayable> getWayMap() {
		return this.wayMap;
	}

	@Override
	public void setWayMap(Map<Long, Wayable> map) {
		this.wayMap = map;
	}


	@Override
	public void reindex() {
    	for (Map.Entry<Integer,BoundsCellBean> entry : getIndexMap().entrySet()) {
    		BoundsCellBean cell = entry.getValue();
    		cell.clearWaylist();
    	}
    	
    	// indexMapに wayList のWayBeanを充填する
    	for (Map.Entry<Long,Wayable> entry : this.wayMap.entrySet()) {
    		Wayable way = entry.getValue();
    		way.getBoxels().clear();
    		getIndexMap().putWayType(way);
    	}
	}


	@Override
	public void build() {
		// TODO Auto-generated method stub
		
	}

}
