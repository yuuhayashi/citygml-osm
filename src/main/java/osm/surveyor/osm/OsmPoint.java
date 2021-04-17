package osm.surveyor.osm;

public class OsmPoint implements Cloneable {
	public String lat = null;
	public String lon = null;
	
	@Override
	public OsmPoint clone() {
		try {
			return (OsmPoint)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
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
	
    //--------------------------------------

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
		OsmPoint other = (OsmPoint) obj;
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
