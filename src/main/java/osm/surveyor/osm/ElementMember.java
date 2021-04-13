package osm.surveyor.osm;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @code{
 *   <member type='way' ref='-102077' role='part' />
 * }
 */
public class ElementMember implements Cloneable {
	public long ref = 0;
	public String type = "way";
	public String role = "";
	
	public ElementMember() {
	}
	
	public void setWay(ElementWay way) {
		this.ref = way.id;
		this.type = "way";
	}
	
	public void setRole(String role) {
		this.role = role;
	}
    
	/*
	 * 
		<member ref='-1655' type='way' role='part' />
	 */
    public Node toNode(Document doc) {
		Element node = doc.createElement("member");
        node.setAttribute("ref", Long.toString(ref));
        node.setAttribute("type", type);
        node.setAttribute("role", role);
        return (Node)node;
    }
    
	@Override
	public ElementMember clone() {
		ElementMember copy = null;
		try {
			copy = (ElementMember)super.clone();
		}
		catch (Exception e) {
			e.printStackTrace();
			copy = null;
		}
		return copy;
	}

	//--------------------------------------------------------
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (ref ^ (ref >>> 32));
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		ElementMember other = (ElementMember) obj;
		if (ref != other.ref)
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

}
