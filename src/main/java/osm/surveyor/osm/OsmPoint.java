package osm.surveyor.osm;

import osm.surveyor.gis.point.PointModel;

public class OsmPoint extends PointModel implements Cloneable {
	
	@Override
	public OsmPoint clone() {
		OsmPoint obj = (OsmPoint)super.clone();
		return obj;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		return true;
	}
}
