package osm.surveyor.osm;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import osm.surveyor.gis.point.NdModel;
import osm.surveyor.osm.boxcel.IndexMap;
import osm.surveyor.osm.way.WayModel;

/**
 * 
 * <way id='96350144' timestamp='2018-08-25T08:34:33Z' uid='7548722' user='Unnown' visible='true' version='17' changeset='61979354'>
 * @author hayashi
 *
 */
public class ElementWay extends WayModel implements Cloneable {
	private static final long serialVersionUID = 1L;
	
	@XmlTransient
	boolean ring = false;
	
	@XmlTransient
	public boolean member = false;	// 単独のWAYか、RELATIONのメンバーかを示す。
	
	public ElementWay(long id) {
		super(id);
	}
	
	@XmlElement(name="nd")
    public void setNdList(List<NdBean> ndList) {
		super.setNdList(ndList);
    }
    public List<NdBean> getNdList() {
    	return super.getNdList();
    }
	
    //------ ElementWay original methods -------------------
    
	public void addNode(NdBean node) {
		getNdList().add(node);
	}
	
	public void addNode(NodeBean node) {
		getNdList().add((NdBean) (new NdBean()).set(node.getId(), (OsmPoint) node.getPoint()));
	}

	/**
	 * LINEをAREAに変更します
	 * 最期のノードが最初のノードと同じなら、削除する
	 * 最期のノードが最初のノードと異なるなら、つなげる
	 */
	public void toArea(IndexMap indexMap) {
		int size = getNdList().size();
		NdBean frst = getNdList().get(0);
		NdBean last = getNdList().get(size - 1);
		if (size > 3) {
			if (frst.getRef() != last.getRef()) {
				if ((frst.point.getLat().equals(last.point.getLat())) && (frst.point.getLon().equals(last.point.getLon()))) {
					getNdList().remove(size - 1);
					getNdList().add(frst);
				}
			}
			
			indexMap.putElementWay(this);
			this.ring = true;
		}
		else if (size > 2) {
			if ((frst.point.getLat().equals(last.point.getLat())) && (frst.point.getLon().equals(last.point.getLon()))) {
				getNdList().remove(size - 1);
				ring = false;
				return;
			}
			else {
				if (frst.getRef() != last.getRef()) {
					getNdList().add(frst);
				}
				indexMap.putElementWay(this);
				ring = true;
			}
		}
		else {
			ring = false;
		}
	}
	
	/*
	 * 
	 * <way id='96350144' timestamp='2018-08-25T08:34:33Z' uid='7548722' user='Unnown' visible='true' version='17' changeset='61979354'>
		<way id='-2' action='modify' visible='true'>
			<nd ref='-3'/>
			<nd ref='-4'/>
			<nd ref='-5'/>
			<tag k='height' v='14.072000000000001' />
			<tag k='building:part' v='yes' />
		</way>
	 */
    public Node toNode(Document doc) {
    	Element element = super.toElement(doc, "way");
		for (NdBean nd : getNdList()) {
			element.appendChild(nd.toNodeNd(doc));
		}
		for (TagBean tag : this.getTagList()) {
			if (tag != null) {
				element.appendChild(tag.toNodeNd(doc));
			}
		}
        return (Node)element;
    }
    
    /**
     * 	<way id='-2' action='modify' visible='true'>
	 * 	  <nd ref='-3'/>
	 * 	  <nd ref='-4'/>
	 * 	  <nd ref='-5'/>
	 * 	  <tag k='height' v='14.072000000000001' />
	 * 	  <tag k='building:part' v='yes' />
	 * 	</way>
     * @param doc
     * @param ways
     */
    ElementWay loadWay(Node nNode) {
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			loadElement((Element) nNode);
			
			NodeList list2 = nNode.getChildNodes();
		    for (int temp2 = 0; temp2 < list2.getLength(); temp2++) {
				Node node2 = list2.item(temp2);
				if (node2.getNodeType() == Node.ELEMENT_NODE) {
					if (node2.getNodeName().equals("nd")) {
						getNdList().add((NdBean) (new NdBean()).loadElement((Element) node2));
					}
					else if (node2.getNodeName().equals("tag")) {
						Element e2 = (Element) node2;
						String k = e2.getAttribute("k");
						String v = e2.getAttribute("v");
						this.addTag(k, v);
					}
				}
		    }
		    return this;
		}
		return null;
    }
    
    /**
     * 線分(TwoPoint)リスト
     */
    public OsmLine getPointList() {
    	OsmLine pointlist = new OsmLine();
    	NdBean a = null;
    	NdBean b = null;
    	for (NdBean node : getNdList()) {
    		if (a == null) {
    			a = node;
    		}
    		else {
    			if (b == null) {
    				b = node;
    			}
    			else {
    				a = (NdBean) b.clone();
    				b = node;
    			}
    		}
    		if ((a != null) && (b != null)) {
        		pointlist.add(new TwoPoint().set(a, b));
    		}
    	}
    	return pointlist;
    }
    
    /**
     * wayのLINEを OsmLine に書き換える
     * @param newline
     */
    public void replaceNds(OsmLine newline) {
    	setNdList(new ArrayList<>());
    	for (TwoPoint segment : newline) {
    		if (getNdList().isEmpty()) {
    			getNdList().add(segment.a);
    		}
    		getNdList().add(segment.b);
    	}
    }
    
	public String getGeomText() {
		String geom = null;
		for (NdModel node : getNdList()) {
			if (node.point == null) {
				return null;
			}
			String str = node.point.getGeom();
			if (str == null) {
				return null;
			}
			if (geom == null) {
				geom = "POLYGON((" + str;
			}
			else {
				geom += ","+ str;
			}
		}
		if (geom != null) {
			geom += "))";
		}
		return geom;
	}
	
	/**
	 * このWAYと重複する面積が最大の WAY.id を返す
	 * @param db
	 * @param where　例: "WHERE (tWay.orignal=true)"
	 * @return
	 * @throws Exception
	 */
	public long getIntersectMaxArea(WayMap ways) throws Exception {
		double max = 0.0d;
		long maxid = 0;
        for (String k : ways.keySet()) {
        	ElementWay way = ways.get(k);
        	double area = getIntersectArea(way);
			if (area > max) {
				max = area;
				maxid = way.getId(); 
			}
        }
        return maxid;
	}

	/**
	 * 同じ位置WAYかどうか
	 * @param obj
	 * @return
	 */
	public boolean isSame(ElementWay other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!this.ring || !other.ring) {
			return false;
		}
		return isSame(other.getPointList());
	}
	
	public boolean isSame(OsmLine line2) {
		OsmLine line1 = this.getPointList();
		if (line1.equal(line2)) {
			return true;
		}
		if (line1.size() != line2.size()) {
			return false;
		}
		for (TwoPoint seg : line1) {
			if (!line2.hasNd(seg.a)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * このWAYがマルチポリゴンのINNERであるかどうか
	 * @param relations
	 * @return
	 */
	public boolean isInnerWay(RelationMap relations) {
		ElementRelation relation = relations.hasMembersWay(Long.toString(this.getId()));
		if (relation != null) {
			if (relation.isMultipolygon()) {
				for (MemberBean member : relation.members) {
					if (member.getRole().equals("inner")) {
						if (member.getRef() == this.getId()) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	//--------- Cloneable -------------------------------------------
    
	@Override
	public ElementWay copy(long newid) {
		ElementWay copy = this.clone();
		copy.setId(newid);
		return copy;
	}
	
	@Override
	public ElementWay clone() {
		ElementWay copy = null;
		try {
			copy = (ElementWay)super.clone();
			copy.ring = this.ring;
			copy.member = this.member;
			copy.setNdList(new ArrayList<NdBean>());
			if (getNdList() != null) {
				for (NdModel nd : getNdList()) {
					copy.getNdList().add((NdBean) nd.clone());
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
		result = prime * result + (ring ? 1231 : 1237);
		result = prime * result + (member ? 1231 : 1237);
		result = prime * result + ((getNdList() == null) ? 0 : getNdList().hashCode());
		result = prime * result + ((getTagList() == null) ? 0 : getTagList().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ElementWay other = (ElementWay) obj;
		if (ring != other.ring) {
			return false;
		}
		if (member != other.member) {
			return false;
		}
		if (getNdList() == null) {
			if (other.getNdList() != null) {
				return false;
			}
		}
		else if (!getNdList().equals(other.getNdList())) {
			return false;
		}
		if (this.getTagList() == null) {
			if (other.getTagList() != null) {
				return false;
			}
		} else if (!getTagList().equals(other.getTagList())) {
			return false;
		}
		return true;
	}
}
