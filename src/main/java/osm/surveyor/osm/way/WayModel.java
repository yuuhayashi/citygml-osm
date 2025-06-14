package osm.surveyor.osm.way;

import java.util.List;

import org.locationtech.jts.geom.Polygon;

import osm.surveyor.osm.NdBean;
import osm.surveyor.osm.PoiBean;
import osm.surveyor.osm.TagBean;
import osm.surveyor.osm.boxcel.BoxcellMappable;

public interface WayModel {
	public List<Integer> getBoxels();
	public void addBoxel(Integer boxelId);
	
	public long getId();
	public void setId(long id);

	/**
	 * fix=true 更新しないもの、fix=false 更新対象を示す。
	 */
	public boolean getFix();
	public void setFix(boolean b);
	
	/**
	 * あるエリアと承服している面積を一時的に記録する領域
	 */
	public double getDuplicateArea();
	public void setDuplicateArea(double area);
	
	/**
	 * AREAの面積を求める。ただし、面積の単位は直行座標（メートルではない）
	 * @return	ラインが閉じたエリア出ない場合は0.0d
	 */
	public Polygon getPolygon();

	/**
	 * このWAYと重複する面積が最大の WAY.id を返す
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public long getIntersectMaxArea(BoxcellMappable bean) throws Exception;
	
	/**
	 * 指定のAREAと重複する領域の面積を取得する
	 * @param way
	 * @return
	 */
	public double getIntersectArea(WayModel way);
	
	/**
	 * このWAYと重複するWAYが存在するかどうか
	 * @param db
	 * @param where
	 * @return
	 * @throws Exception
	 */
	public boolean isIntersect(List<WayModel> ways) throws Exception;
	
	public double getArea();
	
	public boolean isBuilding();
	
	//---------------- List<NodeBean> nodeList ------------------
	public List<NdBean> getNdList();
	public void setNdList(List<NdBean> ndList);
	
	//---------------- List<WayType> wayList ------------------
	

	//---------------- Cloneable ------------------
	public WayModel clone();
	
	//---------------- PoiBean ------------------
	public PoiBean getPoiBean();
	
	public TagBean getTag(String key);
	
	public void addTag(TagBean tag);
	
	public String getTagValue(String key);
	
	public List<TagBean> getTagList();
	
	public String getAction();
}
