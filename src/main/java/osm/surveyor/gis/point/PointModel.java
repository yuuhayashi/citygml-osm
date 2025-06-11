package osm.surveyor.gis.point;

import org.locationtech.jts.geom.Coordinate;

public abstract class PointModel implements Cloneable {
	public String lat = null;
	public String lon = null;
	
	public void set(String lat, String lon) {
		this.lat = lat;
		this.lon = lon;
	}
	
	public void setLat(String lat) {
		this.lat = lat;
	}
    
	public void setLon(String lon) {
		this.lon = lon;
	}
	
	public String getGeomText() {
		String str = getGeom();
		if (str != null) {
			return new String("POINT("+ str +")");
		}
		return null;
	}

	public String getGeom() {
		if ((lon != null) && (lat != null)) {
			return new String(lon +" "+ lat);
		}
		return null;
	}
	
	/**
	 * GeoTools
	 * @return
	 */
	public Coordinate getCoordinate() {
		return new Coordinate(Double.parseDouble(lat), Double.parseDouble(lon));
	}
	
	//--------------------------------------

	@Override
	public PointModel clone() {
		try {
			PointModel obj = (PointModel)super.clone();
			obj.lat = (this.lat == null ? null : new String(this.lat));
			obj.lon = (this.lon == null ? null : new String(this.lon));
			return obj;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lat == null) ? 0 : lat.hashCode());
		result = prime * result + ((lon == null) ? 0 : lon.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PointModel other = (PointModel) obj;
		if (lat == null) {
			if (other.lat != null)
				return false;
		} else if (!lat.equals(other.lat))
			return false;
		if (lon == null) {
			if (other.lon != null)
				return false;
		} else if (!lon.equals(other.lon))
			return false;
		return true;
	}
}
