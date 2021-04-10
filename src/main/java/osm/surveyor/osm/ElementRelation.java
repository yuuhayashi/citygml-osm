package osm.surveyor.osm;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ElementRelation {
	public long id = 0;
	public String action = "modify";
	public boolean visible = true;
	public ArrayList<ElementMember> members;
	public ArrayList<ElementTag> tags;
	
	public ElementRelation(long id) {
		this.id = id;
		members = new ArrayList<ElementMember>();
		tags = new ArrayList<ElementTag>();
	}
	
	public void addTag(String k, String v) {
		this.tags.add(new ElementTag(k, v));
	}
	
	public void addMember(ElementWay way, String role) {
		ElementMember member = new ElementMember();
		member.setWay(way);
		member.setRole(role);
		this.members.add(member);
	}

	/*
	 * 
		<relation id='-1654' action='modify' visible='true'>
			<member ref='-1655' type='way' role='part' />
			<tag k='type' v='building' />
			<tag k='source:ref:id' v='BLD_57cd4ea7-0fb7-4b0e-a600-9982cf3b60ca' />
			<tag k='addr:full' v='東京都大田区南六郷三丁目' />
		</relation>
	 */
    public Node toNode(Document doc) {
		Element node = doc.createElement("relation");
        node.setAttribute("id", Long.toString(id));
        node.setAttribute("action", action);
        node.setAttribute("visible", Boolean.toString(visible));
    	for (ElementMember member : this.members) {
			node.appendChild(member.toNode(doc));
		}
		for (ElementTag tag : this.tags) {
			node.appendChild(tag.toNodeNd(doc));
		}
        return (Node)node;
    }
    
	/**
	 * member:role="outline"が存在しないリレーションを返す
	 * @param relation
	 * @return
	 */
	ElementRelation getNoOutline() {
		for (ElementMember member : members) {
			String role = member.role;
			if (role.equals("outline")) {
				return null;
			}
		}
		return this;
	}
	
	/**
	 * member->wayのすべてのLINEをListにする
	 * @param relation
	 * @return
	 */
	ArrayList<ElementWay> getLines() {
		ArrayList<ElementWay> lineList = new ArrayList<>();
		for (ElementMember member : members) {
			lineList.add(member.way.clone());
		}
		return lineList;
	}
}
