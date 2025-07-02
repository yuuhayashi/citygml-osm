package osm.surveyor.osm.way;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

public interface Areable {
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
	 * あるエリアと重複している面積を一時的に記録する領域
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
	 * 指定のAREAと重複する部分の面積を取得する
	 * @param way
	 * @return
	 */
	public default double getIntersectArea(Areable way) {
		List<Integer> list = getIntersectBoxels(way.getBoxels());
		if (list.size() > 0) {
	        Polygon polygon = this.getPolygon();
	        if (polygon == null) {
	        	return 0.0d;
	        }
	        return getIntersectArea(way.getPolygon());
		}
		return 0.0d;
	}
	
	/**
	 * 指定の領域と重複する部分の面積を取得する
	 * @param way
	 * @return
	 */
	public default double getIntersectArea(Polygon polygon2) {
        if (polygon2 == null) {
        	return 0.0d;
        }
        Polygon polygon = this.getPolygon();
        if (polygon == null) {
        	return 0.0d;
        }
        Geometry intersect = polygon.intersection(polygon2);
		if (intersect == null) {
			return 0.0d;
		}
		if (intersect.isValid()) {
			return intersect.getArea();
		}
		return 0.0d;
	}
	
	public default List<Integer> getIntersectBoxels(List<Integer> boxcels) {
		List<Integer> list = new ArrayList<>();
		for (Integer key1 : getBoxels()) {
			for (Integer key2 : boxcels) {
				if (key1.intValue() == key2.intValue()) {
					list.add(key1);
				}
			}
		}
		return list;
	}
		
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
				Map<Long, Polygon> boxcelWeys =cell.getWayMap();
				for (Long id : boxcelWeys.keySet()) {
					if (id.longValue() != this.getId()) {
						Polygon poly = boxcelWeys.get(id);
						if (poly != null) {
							if (getIntersectArea(poly) > 0.0d) {
								return true;
							}
						}
					}
				}
			}
		}
        return false;
	}
	
	/**
	 * AREAの面積を求める。ただし、面積の単位は直行座標（メートルではない）
	 * @return	ラインが閉じたエリアでない場合は 0.0
	 */
	public double getArea();
	
	public boolean isBuilding();
	
	//---------------- List<NodeBean> nodeList ------------------
	public List<NdBean> getNdList();
	public void setNdList(List<NdBean> ndList);
	
	//---------------- Cloneable ------------------
	public Areable clone();
	
	//---------------- PoiBean ------------------
	public PoiBean getPoiBean();
}
