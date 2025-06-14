package osm.surveyor.gis.node;

public abstract class NodeModel implements Cloneable {


    //--------------------------------------

	@Override
	public NodeModel clone() {
		NodeModel copy = null;
		try {
			copy = (NodeModel)super.clone();
		}
		catch (Exception e) {
			e.printStackTrace();
			copy = null;
		}
		return copy;
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
		if (getClass() != obj.getClass())
			return false;
		NodeModel other = (NodeModel) obj;
		if (!this.equals(other))
			return false;
		return true;
	}
}
