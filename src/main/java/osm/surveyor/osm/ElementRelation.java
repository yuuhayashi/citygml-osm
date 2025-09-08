package osm.surveyor.osm;

import osm.surveyor.osm.way.WayModel;
import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ElementRelation extends PoiBean implements Cloneable,Serializable {
	private static final long serialVersionUID = 1L;
	public static final String RELATION = "relation";
	public static final String MULTIPOLYGON = "multipolygon";
	
	/*
	 * complete : このリレーションが他のリレーションと重複していない(独立)状態になっていることを示す
	 */
	@XmlTransient
	private boolean complete = false;
	public boolean isComplete() {
		return this.complete;
	}
	public void setComplete(boolean f) {
		this.complete = f;
	}
	
	/*
	 * このリレーションが他のリレーションに取り込まれた状態であることを示す（無効なリレーション）
	 */
	@XmlTransient
	private boolean marged = false;
	public boolean isMarged() {
		return this.marged;
	}
	public void setMarged(boolean b) {
		this.marged = b;
	}
	
	@XmlElement(name="member")
	public ArrayList<MemberBean> members;
	
	public ElementRelation(long id) {
		super(id);
		members = new ArrayList<MemberBean>();
	}
	
	public void addMember(MemberBean member) {
		long memberRef = member.getRef();
		boolean is = false;
		for (MemberBean bean : members) {
			if (bean.getRef() == memberRef) {
				bean = member;
				is = true;
			}
		}
		if (!is) {
			this.members.add(member);
		}
	}
	
	public void addMember(WayModel way, String role) {
		if (way == null) {
			return;
		}
		MemberBean member = new MemberBean();
		way.setMemberWay(true);
		if (role.equals("outline")) {
			way.toBuilding();
		}
		else if (role.equals("part")) {
			way.toPart();
		}
		member.setWay(way);
		member.setRole(role);
		addMember(member);
	}

	public void addMember(ElementRelation relation, String role) {
		MemberBean member = new MemberBean();
		if (role.equals("outline")) {
			relation.toBuilding();
		}
		else if (role.equals("part")) {
			relation.toPart();
		}
		member.setRelation(relation);
		member.setRole(role);
		addMember(member);
	}

	/*
	 * 
		<relation id='-1654' action='modify' visible='true'>
			<member ref='-1655' type='way' role='part' />
			<tag k='type' v='building' />
			<tag k='addr:full' v='東京都大田区南六郷三丁目' />
		</relation>
	 */
    public Node toNode(Document doc) {
    	Element element = super.toElement(doc, ElementRelation.RELATION);
    	for (MemberBean member : this.members) {
    		element.appendChild(member.toNode(doc));
		}
		for (TagBean tag  : getTagList()) {
			element.appendChild(tag.toNodeNd(doc));
		}
        return (Node)element;
    }
    
	/**
	 * member:role="outline"が存在しないリレーションを返す
	 * @param building
	 * @return
	 */
	ElementRelation getNoOutline() {
		for (MemberBean member : members) {
			if (member.getRole().equals("outline")) {
				return null;
			}
		}
		return this;
	}
	
    /**
     * 	<relation id='-1654' action='modify' visible='true'>
     *	  <member ref='-1655' type='way' role='part' />
     *	  <tag k='type' v='building' />
     *	  <tag k='addr:full' v='東京都大田区南六郷三丁目' />
     *	</relation>
     * @param doc
     * @param relations
     */
    public ElementRelation loadRelation(Node nNode) {
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			return loadElement(eElement);
		}
    	return null;
    }
    
    @Override
    public ElementRelation loadElement(Element eElement) {
    	super.loadElement(eElement);
    	
		NodeList list2 = eElement.getChildNodes();
	    for (int temp2 = 0; temp2 < list2.getLength(); temp2++) {
			Node node2 = list2.item(temp2);
			if (node2.getNodeType() == Node.ELEMENT_NODE) {
				Element e2 = (Element) node2;
				if (e2.getNodeName().equals("member")) {
					MemberBean member = new MemberBean();
					member.loadElement(e2);
					this.members.add(member);
				}
			}
	    }
    	return this;
    }

	/**
	 * メンバーの中から member:type="relation" のIDを返す
	 * @return	リレーションメンバーが存在しない場合はNULL
	 */
	public String getRelationMemberId() {
		for (MemberBean mem : members) {
			if (mem.getType().equals(ElementRelation.RELATION)) {
				return Long.toString(mem.getRef());
			}
		}
		return null;
	}
	
	/**
	 * リレーションの"outline"メンバーを取得する
	 * 前提： "outline"メンバーは一つにまとめられていること
	 * @return	"outline"がないときはNULL
	 */
	public MemberBean getOutlineMember() {
		for (MemberBean member : members) {
			if (member.getRole().equals("outline")) {
				return member;
			}
		}
		return null;
	}
		
	public void removeMember(long id) {
		for (MemberBean mem : members) {
			if (mem.getRef() == id) {
				members.remove(members.indexOf(mem));
				return;
			}
		}
	}
	
	/**
	 * member->wayのすべてのLINEをListにする
	 * @param building
	 * @return
		ArrayList<ElementWay> getLines() {
			ArrayList<ElementWay> lineList = new ArrayList<>();
			for (ElementMember member : members) {
				lineList.add(member.way.clone());
			}
			return lineList;
		}
	 */
    
	public boolean isBuilding() {
		for (TagBean tag : this.getTagList()) {
			if (tag.k.equals("type")) {
				if (tag.v.equals("building")) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isMultipolygon() {
		for (TagBean tag : this.getTagList()) {
			if (tag.k.equals("type")) {
				if (tag.v.equals(ElementRelation.MULTIPOLYGON)) {
					return true;
				}
			}
		}
		return false;
	}

	//------------------------------------
	
	@Override
	public ElementRelation clone() {
		ElementRelation copy = null;
		try {
			copy = (ElementRelation)super.clone();
			copy.members = new ArrayList<MemberBean>();
			if (copy.members != null) {
				for (MemberBean member : this.members) {
					copy.members.add(member.clone());
				}
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
		int result = super.hashCode();
		result = prime * result + ((members == null) ? 0 : members.hashCode());
		result = prime * result + ((getTagList() == null) ? 0 : getTagList().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElementRelation other = (ElementRelation) obj;
		if (members == null) {
			if (other.members != null)
				return false;
		} else if (!members.equals(other.members))
			return false;
		if (getTagList() == null) {
			if (other.getTagList() != null)
				return false;
		} else if (!getTagList().equals(other.getTagList()))
			return false;
		return true;
	}
}
