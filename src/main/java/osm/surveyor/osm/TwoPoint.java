package osm.surveyor.osm;

public class TwoPoint implements Cloneable {
	ElementNode a;
	ElementNode b;
	
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
				copy.a = this.a.clone();
			}
			if (this.b == null) {
				copy.b = null;
			}
			else {
				copy.b = this.b.clone();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			copy = null;
		}
		return copy;
	}
	
	public TwoPoint set(ElementNode a, ElementNode b) {
		this.a = a;
		this.b = b;
		return this;
	}
	
	public TwoPoint reverse() {
		ElementNode t = this.a.clone();
		this.a = this.b.clone();
		this.b = t;
		return this;
	}
	
	/**
	 * 指定のPointに接続可能かどうか。A,Bどちらかが同一ならTrue
	 * 
	 * @param node
	 * @return
	 */
	public boolean isConnectable(ElementNode node) {
		if (node == null) {
			return false;
		}
		if (!node.point.equals(this.a) && !node.point.equals(this.b)) {
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
