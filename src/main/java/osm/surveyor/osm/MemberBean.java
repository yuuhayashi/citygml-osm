package osm.surveyor.osm;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @code{
 *   <member type='way' ref='-102077' role='part' />
 * }
 */
@XmlRootElement(name="member")
public class MemberBean implements Cloneable,Serializable {

	private static final long serialVersionUID = -4795899529405389379L;
	private long ref = 0;
	private String type;
	private String role = "";
	
	public MemberBean() {
		this.type = "way";
		this.role = "";
	}
	
	public void setWay(ElementWay way) {
		this.ref = way.id;
		this.type = "way";
	}
	
	public void setRelation(ElementRelation relation) {
		this.ref = relation.id;
		this.type = "relation";
	}

	@XmlAttribute(name="id")
	public long getRef() {
		return this.ref;
	}

	public void setRef(long ref) {
		this.ref = ref;
	}
	
	public void setRef(String str) {
		setRef(Long.parseLong(str));
	}

	@XmlAttribute(name="type")
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@XmlAttribute(name="role")
	public String getRole() {
		return this.role;
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
    
    public MemberBean loadElement(Element eElement) {
		String ref = eElement.getAttribute("ref");
		String role = eElement.getAttribute("role");
		String type = eElement.getAttribute("type");
		if ((ref != null) && !ref.isEmpty()) {
			this.ref = Long.parseLong(ref);
		}
		else {
			this.ref = 0;
		}
		if ((role != null) && !role.isEmpty()) {
			this.role = role;
		}
		else {
			this.role = null;
		}
		if ((type != null) && !type.isEmpty()) {
			this.type = type;
		}
		else {
			this.type = null;
		}
		return this;
    }
    
	@Override
	public MemberBean clone() {
		MemberBean copy = null;
		try {
			copy = (MemberBean)super.clone();
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
		MemberBean other = (MemberBean) obj;
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
