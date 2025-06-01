package osm.surveyor.osm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.geotools.geometry.jts.JTSFactoryFinder;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;

public class BoundsCellBean {
	private List<Coordinate> ndList = new ArrayList<>();
	private HashMap<Long, Polygon> wayList = new HashMap<>();
	private Integer id;

	public BoundsCellBean(Integer id, double maxlat, double maxlon, double minlat, double minlon) {
		super();
		this.id = id;
		Coordinate nw = new Coordinate(maxlat, minlon);
		Coordinate ne = new Coordinate(maxlat, maxlon);
		Coordinate se = new Coordinate(minlat, maxlon);
		Coordinate sw = new Coordinate(minlat, minlon);
		ndList.add(nw);
		ndList.add(ne);
		ndList.add(se);
		ndList.add(sw);
		ndList.add(nw);
	}
	
	public Polygon getPolygon() {
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
	
	public Coordinate[] getCoordinates() {
		return ndList.toArray(new Coordinate[ndList.size()]);
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public void putWay(WayBean way) {
		this.wayList.put(way.getId(), way.getPolygon());
	}
	
	public void putWay(ElementWay way) {
		this.wayList.put(Long.valueOf(way.getId()), way.getPolygon());
	}
	
	public void removeWay(WayBean way) {
		this.wayList.remove(way.getId());
	}

	public void removeWay(ElementWay way) {
		this.wayList.remove(way.getId());
	}
	
	/**
	 * 指定のAREAと重複する領域の面積を取得する
	 * @param way
	 * @return
	 */
	public double getIntersectArea(Polygon way) {
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
}
