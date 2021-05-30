package osm.surveyor.osm;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ElementRelation extends ElementOsmapi implements Cloneable {
	public static final String RELATION = "relation";
	public static final String MULTIPOLYGON = "multipolygon";
	
	public ArrayList<ElementMember> members;
	
	public ElementRelation(long id) {
		super(id);
		members = new ArrayList<ElementMember>();
	}
	
	public void addMember(ElementWay way, String role) {
		ElementMember member = new ElementMember();
		way.member = true;
		member.setWay(way);
		member.setRole(role);
		this.members.add(member);
	}

	public void addMember(ElementRelation relation, String role) {
		ElementMember member = new ElementMember();
		member.setRelation(relation);
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
    	Element element = super.toElement(doc, ElementRelation.RELATION);
    	for (ElementMember member : this.members) {
    		element.appendChild(member.toNode(doc));
		}
		for (String key  : this.tags.keySet()) {
			ElementTag tag = tags.get(key);
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
		for (ElementMember member : members) {
			String role = member.role;
			if (role.equals("outline")) {
				return null;
			}
		}
		return this;
	}
	
    /**
     * 	<relation id='-1654' action='modify' visible='true'>
     *	  <member ref='-1655' type='way' role='part' />
     *	  <tag k='type' v='building' />
     *	  <tag k='source:ref:id' v='BLD_57cd4ea7-0fb7-4b0e-a600-9982cf3b60ca' />
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
					ElementMember member = new ElementMember();
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
		for (ElementMember mem : members) {
			if (mem.type.equals(ElementRelation.RELATION)) {
				return Long.toString(mem.ref);
			}
		}
		return null;
	}
	
	public void removeMember(long id) {
		for (ElementMember mem : members) {
			if (mem.ref == id) {
				members.remove(members.indexOf(mem));
				return;
			}
		}
	}
	
	/**
	 * 所属メンバーの中から、最大面積のAREAを取得する
	 * @param ways	メンバーの実態
	 * @return	指定したタグが存在しない場合はNULL
	 */
	public ElementWay getMaxArea(WayMap ways) {
		ElementWay max = null;
		double maxarea = 0.0d;
		for (ElementMember member : this.members) {
			if (member.type.equals("way")) {
				ElementWay way = ways.get(member.ref);
				double area = way.getArea();
				if (max == null) {
					max = way;
					maxarea = area;
				}
				else {
					if (maxarea < area) {
						max = way;
						maxarea = area;
					}
				}
			}
		}
		return max;
	}
	
	/**
	 * 所属メンバーの中から、指定したタグの最大値を取得する
	 * タグの形式は数値型のみ
	 * @param ways	メンバーの実態
	 * @param k		指定するタグのk
	 * @return	指定したタグが存在しない場合はNULL
	 */
	public String getMaxValue(WayMap ways, String k) {
		String max = null;
		for (ElementMember member : this.members) {
			if (member.type.equals("way")) {
				ElementWay way = ways.get(member.ref);
				String v = way.getTagValue(k);
				if (v != null) {
					if (max == null) {
						max = v;
					}
					else {
						if (Double.parseDouble(max) < Double.parseDouble(v)) {
							max = v;
						}
					}
				}
			}
		}
		return max;
	}
	
	/**
	 * 所属メンバーの中から、指定したタグの最小値を取得する
	 * タグの形式は数値型のみ
	 * @param ways	メンバーの実態
	 * @param k		指定するタグのk
	 * @return	指定したタグが存在しない場合はNULL
	 */
	public String getMinValue(WayMap ways, String k) {
		String min = null;
		for (ElementMember member : this.members) {
			if (member.type.equals("way")) {
				ElementWay way = ways.get(member.ref);
				String v = way.getTagValue(k);
				if (v != null) {
					if (min == null) {
						min = v;
					}
					else {
						if (Double.parseDouble(min) > Double.parseDouble(v)) {
							min = v;
						}
					}
				}
			}
		}
		return min;
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
		for (String k : tags.keySet()) {
			ElementTag tag = tags.get(k);
			if (tag.k.equals("type")) {
				if (tag.v.equals("building")) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isMultipolygon() {
		for (String k : tags.keySet()) {
			ElementTag tag = tags.get(k);
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
			copy.members = new ArrayList<ElementMember>();
			if (copy.members != null) {
				for (ElementMember member : this.members) {
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
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
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
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		return true;
	}
}
