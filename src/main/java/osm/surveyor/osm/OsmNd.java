package osm.surveyor.osm;

public class OsmNd implements Cloneable {
	public long id = 0;
	public OsmPoint point = null;
	
	public OsmNd set(long id, OsmPoint point) {
		this.id = id;
		this.point = point;
		return this;
	}
	
	@Override
	public OsmNd clone() {
		OsmNd copy = null;
		try {
			copy = (OsmNd)super.clone();
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

    //--------------------------------------

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		if (id != other.id)
			return false;
		if (point == null) {
			if (other.point != null)
				return false;
		} else if (!point.equals(other.point))
			return false;
		return true;
	}
}
