package osm.surveyor.osm.way;

import java.util.List;

import org.geotools.geometry.jts.JTSFactoryFinder;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;

import osm.surveyor.osm.NdBean;
import osm.surveyor.osm.PoiBean;
import osm.surveyor.osm.boxcel.BoundsCellBean;
import osm.surveyor.osm.boxcel.BoxcellMappable;
import osm.surveyor.osm.boxcel.IndexMap;

public interface Wayable {
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
	default Polygon getPolygon() {
		try {
	        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
	        LinearRing ring = geometryFactory.createLinearRing(getCoordinates());
	        Polygon polygon = geometryFactory.createPolygon(ring, null);
	        if (polygon.isValid()) {
	            return polygon;
	        }
	        else {
	        	return null;
	        }
		}
		catch (Exception e) {
			return null;
		}
	}
	public Coordinate[] getCoordinates();

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
	public double getIntersectArea(Wayable way);
	
	/**
	 * このWAYと重複するWAYが存在するかどうか
	 * @param db
	 * @param where
	 * @return
	 * @throws Exception
	 */
	default boolean isIntersect(BoxcellMappable map) throws Exception {
		IndexMap mapindex = map.getIndexMap();
		for (Integer boxcelid : this.getBoxels()) {
			BoundsCellBean cell = mapindex.get(boxcelid);
			if (cell != null) {
				Polygon poly = cell.getWayMap().get(Long.valueOf(this.getId()));
				if (poly != null) {
					if (getIntersectArea(poly) > 0.0d) {
						return true;
					}
				}
			}
		}
        return false;
	}
	
	/**
	 * このWAYと重複するWAYが存在するかどうか
	 * @param db
	 * @param where
	 * @return
	 * @throws Exception
	default boolean isIntersect(WayMap ways) throws Exception {
        for (String k : ways.keySet()) {
        	WayModel way = ways.get(k);
        	double area = getIntersectArea(way);
			if (area > 0.0d) {
				return true;
			}
        }
        return false;
	}
	 */
	
	/**
	 * 指定のAREAと重複する領域の面積を取得する
	 * @param way
	 * @return
	 */
	public default double getIntersectArea(Polygon way) {
        Polygon polygon = this.getPolygon();
        if (polygon == null) {
        	return 0.0d;
        }
        if (way == null) {
        	return 0.0d;
        }
        Geometry intersect = polygon.intersection(way);
		if (intersect == null) {
			return 0.0d;
		}
		if (intersect.isValid()) {
			return intersect.getArea();
		}
		return 0.0d;
	}
	
	public double getArea();
	
	public boolean isBuilding();
	
	//---------------- List<NodeBean> nodeList ------------------
	public List<NdBean> getNdList();
	public void setNdList(List<NdBean> ndList);
	
	//---------------- Cloneable ------------------
	public Wayable clone();
	
	//---------------- PoiBean ------------------
	public PoiBean getPoiBean();
}
