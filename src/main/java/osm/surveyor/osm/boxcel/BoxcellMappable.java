package osm.surveyor.osm.boxcel;

import java.util.List;
import java.util.Map;

import osm.surveyor.osm.BoundsBean;
import osm.surveyor.osm.NdBean;
import osm.surveyor.osm.NodeBean;
import osm.surveyor.osm.OsmPoint;
import osm.surveyor.osm.WayBean;
import osm.surveyor.osm.way.WayModel;

public interface BoxcellMappable {
	
    public BoundsBean getBounds();
    public void setBounds(BoundsBean bounds);
    
    public IndexMap getIndexMap();
    public void setInxevMap(IndexMap indexMap);
    
    public List<NodeBean> getNodeList();
    public void setNodeList(List<NodeBean> nodeList);
    
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
    	for (WayModel way : getWays()) {
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
    	for (WayModel way : getWays()) {
    		for (NdBean nd : way.getNdList()) {
    			NodeBean node = getNode(nd.getRef());
    			if (node != null) {
        			nd.setPoint(node.getPoint());
    			}
    		}
    	}
    	// indexMapに wayList のWayBeanを充填する
    	for (WayModel way : getWays()) {
    		getIndexMap().putWayType(way);
    	}
    }
    
    /**
     * 指定のwayBeanと重複するwayListを取得する
     * @param wayBean
     * @return	wayBeanと重複するwayListを返す
     */
    public List<WayModel> getWayList(WayModel wayBean);
    
    /**
     * 指定されたWAYを取得する
     * @param id	WAY ID
     * @return		null = 該当なし
     */
	public WayModel getWay(long id);

	public void putWay(WayModel way);
    
	public List<WayBean> getWays();
}
