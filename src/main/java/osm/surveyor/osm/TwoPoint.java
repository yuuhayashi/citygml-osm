package osm.surveyor.osm;

import osm.surveyor.gis.point.NdModel;

public class TwoPoint implements Cloneable {
	public NdBean a;
	public NdBean b;
	
	public TwoPoint() {
		this.a = null;
		this.b = null;
	}
	
	@Override
	public TwoPoint clone() {
		TwoPoint copy = null;
		try {
			copy = (TwoPoint)super.clone();
			if (this.a == null) {
				copy.a = null;
			}
			else {
				copy.a = (NdBean) this.a.clone();
			}
			if (this.b == null) {
				copy.b = null;
			}
			else {
				copy.b = (NdBean) this.b.clone();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			copy = null;
		}
		return copy;
	}
	
	public TwoPoint set(NdBean a, NdBean b) {
		this.a = a;
		this.b = b;
		return this;
	}
	
	public TwoPoint reverse() {
		NdBean t = (NdBean) this.a.clone();
		this.a = (NdBean) this.b.clone();
		this.b = t;
		return this;
	}
	
	/**
	 * 指定のPointに接続可能かどうか。A,Bどちらかが同一ならTrue
	 * 
	 * @param node
	 * @return
	 */
	public boolean isConnectable(NdModel node) {
		if (node == null) {
			return false;
		}
		if (!node.point.equals(this.a.point) && !node.point.equals(this.b.point)) {
			return false;
		}
		return true;
	}

	public boolean equal(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		TwoPoint other = (TwoPoint) obj;
		if (a == null) {
			if (other.a != null)
				return false;
		}
		if (b == null) {
			if (other.b != null)
				return false;
		}
		
		if ((!a.equals(other.a)) && (!b.equals(other.a)))
			return false;
		
		if ((!a.equals(other.b)) && (!b.equals(other.b)))
			return false;

		return true;
	}

	//---------------------------------
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((b == null) ? 0 : b.hashCode());
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
		TwoPoint other = (TwoPoint) obj;
		if (a == null) {
			if (other.a != null)
				return false;
		} else if (!a.equals(other.a))
			return false;
		if (b == null) {
			if (other.b != null)
				return false;
		} else if (!b.equals(other.b))
			return false;
		return true;
	}
	
}
