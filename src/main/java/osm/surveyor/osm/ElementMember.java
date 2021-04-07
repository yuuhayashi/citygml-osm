package osm.surveyor.osm;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @code{
 *   <member type='way' ref='-102077' role='part' />
 * }
 */
public class ElementMember {
	public long ref = 0;
	public String type = "way";
	public String role = "";
	public ElementWay way;
	
	public ElementMember() {
	}
	
	public void setWay(ElementWay way) {
		this.way = way;
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
}
