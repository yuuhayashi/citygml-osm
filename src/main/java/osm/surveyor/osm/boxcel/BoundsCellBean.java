package osm.surveyor.osm.boxcel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.geotools.geometry.jts.JTSFactoryFinder;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;

import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.NdBean;
import osm.surveyor.osm.way.WayModel;
import osm.surveyor.osm.way.Wayable;

public class BoundsCellBean {
	private List<Coordinate> ndList = new ArrayList<>();
	private HashMap<Long, Polygon> wayMap = new HashMap<>();
	private Integer id;
	private double maxlat;
	private double maxlon;
	private double minlat;
	private double minlon;

	public BoundsCellBean(Integer id, double maxlat, double maxlon, double minlat, double minlon) {
		super();
		this.id = id;
		this.maxlat = maxlat;
		this.maxlon = maxlon;
		this.minlat = minlat;
		this.minlon = minlon;
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
	
	public void clearWaylist() {
		this.wayMap.clear();
	}
	
	public HashMap<Long, Polygon> getWayMap() {
		return this.wayMap;
	}
	
	public void putWay(Wayable way) {
		this.wayMap.put(way.getId(), way.getPolygon());
	}
	
	public void putWay(ElementWay way) {
		this.wayMap.put(Long.valueOf(way.getId()), way.getPolygon());
	}
	
	public void removeWay(Wayable way) {
		this.wayMap.remove(way.getId());
	}

	public void removeWay(ElementWay way) {
		this.wayMap.remove(way.getId());
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
	
	public boolean isDuplicateBoxcel(Wayable way) {
		double wayMaxlat = -90.0d;
		double wayMaxlon = -180.0d;
		double wayMinlat = 90.0d;
		double wayMinlon = 180.0d;
		
		for (NdBean nd : way.getNdList()) {
			double lon = Double.valueOf(nd.getPoint().getLon());
			if (lon > wayMaxlon) {
				wayMaxlon = lon;
			}
			if (lon < wayMinlon) {
				wayMinlon = lon;
			}
			double lat = Double.valueOf(nd.getPoint().getLat());
			if (lat > wayMaxlat) {
				wayMaxlat = lat;
			}
			if (lat < wayMinlat) {
				wayMinlat = lat;
			}
		}
		if ((wayMaxlon > minlon) && (wayMinlon < maxlon) && (wayMaxlat > minlat) && (wayMinlat < maxlat)) {
			return true;
		}
		return false;
	}
}
