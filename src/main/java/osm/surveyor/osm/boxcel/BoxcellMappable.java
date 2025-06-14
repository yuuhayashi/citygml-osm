package osm.surveyor.osm.boxcel;

import java.util.List;

import osm.surveyor.osm.BoundsBean;
import osm.surveyor.osm.way.Wayable;

public interface BoxcellMappable {
	
    public BoundsBean getBounds();
    public void setBounds(BoundsBean bounds);
    
    public IndexMap getIndexMap();
    public void setInxevMap(IndexMap indexMap);
    
    public void putWay(Wayable way);
    
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
    
    /**
     * 指定のwayBeanと重複するwayListを取得する
     * @param wayBean
     * @return	wayBeanと重複するwayListを返す
     */
    public List<Wayable> getWayList(Wayable wayBean);
    
    /**
     * 指定されたWAYを取得する
     * @param id	WAY ID
     * @return		null = 該当なし
     */
	public Wayable getWay(long id);
}
