package osm.surveyor.gis.point;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

import org.locationtech.jts.geom.Coordinate;

public abstract class NdModel implements Cloneable {

	private long ref = 0;
	public long getRef() {
		return ref;
	}
	@XmlAttribute(name="ref")
	public void setRef(long ref) {
		this.ref = ref;
	}

    @XmlTransient
	public PointModel point = null;
	public void setPoint(PointModel point) {
		this.point = point;
	}
	public PointModel getPoint() {
		return this.point;
	}

	public Coordinate getCoordinate() {
		return this.point.getCoordinate();
	}

	//--------------------------------------

	@Override
	public NdModel clone() {
		NdModel copy = null;
		try {
			copy = (NdModel)super.clone();
			copy.ref = this.ref;
			if (this.point == null) {
				copy.point = null;
			}
			else {
				copy.point = this.point.clone();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			copy = null;
		}
		return copy;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (ref ^ (ref >>> 32));
		result = prime * result + ((point == null) ? 0 : point.hashCode());
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
		NdModel other = (NdModel) obj;
		if (ref != other.getRef())
			return false;
		if (point == null) {
			if (other.point != null)
				return false;
		} else if (!point.equals(other.point))
			return false;
		return true;
	}
}
