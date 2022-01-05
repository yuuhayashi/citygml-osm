package osm.surveyor.osm;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.locationtech.jts.geom.Coordinate;

/**
 * @code{
 *    <nd ref='-3'/>
 *         :
 * }
 */
@XmlRootElement(name="nd")
public class NdBean implements Serializable, Cloneable {
	private static final long serialVersionUID = 4936895079170613027L;

	private long ref = 0;

	@XmlAttribute(name="ref")
	public long getRef() {
		return ref;
	}
	public void setRef(long ref) {
		this.ref = ref;
	}

	@XmlTransient
	public OsmPoint point = null;
	public void setPoint(OsmPoint point) {
		this.point = point;
	}

	public Coordinate getCoordinate() {
		return this.point.getCoordinate();
	}

    //--------------------------------------

	@Override
	public NdBean clone() {
		NdBean copy = null;
		try {
			copy = (NdBean)super.clone();
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
		OsmNd other = (OsmNd) obj;
		if (ref != other.id)
			return false;
		if (point == null) {
			if (other.point != null)
				return false;
		} else if (!point.equals(other.point))
			return false;
		return true;
	}
}
