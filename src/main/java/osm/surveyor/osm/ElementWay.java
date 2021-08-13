package osm.surveyor.osm;

import java.util.ArrayList;

import org.geotools.geometry.jts.JTSFactoryFinder;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * <way id='96350144' timestamp='2018-08-25T08:34:33Z' uid='7548722' user='Unnown' visible='true' version='17' changeset='61979354'>
 * @author hayashi
 *
 */
public class ElementWay extends PoiBean implements Cloneable {
	private static final long serialVersionUID = 1L;
	public ArrayList<OsmNd> nds;
	boolean area = false;
	public boolean member = false;	// 単独のWAYか、RELATIONのメンバーかを示す。
	
	public ElementWay(long id) {
		super(id);
		nds = new ArrayList<OsmNd>();
	}
	
	@Override
	public ElementWay clone() {
		ElementWay copy = null;
		try {
			copy = (ElementWay)super.clone();
			copy.area = area;
			copy.member = member;
			copy.nds = new ArrayList<OsmNd>();
			if (this.nds != null) {
				for (OsmNd nd : this.nds) {
					copy.nds.add(nd.clone());
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
	public ElementWay copy(long newid) {
		ElementWay copy = this.clone();
		copy.setId(newid);
		return copy;
	}

	public void addNode(OsmNd node) {
		this.nds.add(node);
	}
	
	public void addNode(ElementNode node) {
		this.nds.add((new OsmNd()).set(node.getId(), node.point));
	}

	/**
	 * LINEをAREAに変更します
	 * 最期のノードが最初のノードと同じなら、削除する
	 * 最期のノードが最初のノードが異なるなら、つなげる
	 */
	public void toArea() {
		int size = nds.size();
		OsmNd frst = nds.get(0);
		OsmNd last = nds.get(size - 1);
		if (size > 3) {
			if (frst.id != last.id) {
				if ((frst.point.lat.equals(last.point.lat)) && (frst.point.lon.equals(last.point.lon))) {
					nds.remove(size - 1);
					nds.add(frst);
				}
			}
			area = true;
		}
		if (size == 3) {
			if ((frst.point.lat.equals(last.point.lat)) && (frst.point.lon.equals(last.point.lon))) {
				nds.remove(size - 1);
				area = false;
				return;
			}
			else {
				if (frst.id != last.id) {
					nds.add(frst);
				}
				area = true;
			}
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
		for (OsmNd nd : this.nds) {
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
			Element eElement = (Element) nNode;
			loadElement(eElement);
			
			NodeList list2 = nNode.getChildNodes();
		    for (int temp2 = 0; temp2 < list2.getLength(); temp2++) {
				Node node2 = list2.item(temp2);
				if (node2.getNodeType() == Node.ELEMENT_NODE) {
					Element e2 = (Element) node2;
					if (node2.getNodeName().equals("nd")) {
						OsmNd nd = new OsmNd();
						nd.loadElement(e2);
						this.addNode(nd);
					}
					if (node2.getNodeName().equals("tag")) {
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
    	OsmNd a = null;
    	OsmNd b = null;
    	for (OsmNd node : this.nds) {
    		if (a == null) {
    			a = node;
    		}
    		else {
    			if (b == null) {
    				b = node;
    			}
    			else {
    				a = b.clone();
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
    	this.nds = new ArrayList<>();
    	for (TwoPoint segment : newline) {
    		if (this.nds.isEmpty()) {
    			this.nds.add(segment.a);
    		}
			this.nds.add(segment.b);
    	}
    }
    
	public String getGeomText() {
		String geom = null;
		for (OsmNd node : this.nds) {
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
	
	public Coordinate[] getCoordinates() {
		ArrayList<Coordinate> list = new ArrayList<>();
    	for (OsmNd node : this.nds) {
    		list.add(node.getCoordinate());
    	}
		return list.toArray(new Coordinate[list.size()]);
	}
	
	/**
	 * AREAの面積を求める。ただし、面積の単位は直行座標（メートルではない）
	 * @return	ラインが閉じたエリア出ない場合は0.0d
	 */
	public Polygon getPolygon() {
        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
        LinearRing ring = geometryFactory.createLinearRing(getCoordinates());
        Polygon polygon = geometryFactory.createPolygon(ring, null);
        if (polygon.isValid()) {
            return polygon;
        }
        else {
        	return null;
        }
	}

	/**
	 * AREAの面積を求める。ただし、面積の単位は直行座標（メートルではない）
	 * @return	ラインが閉じたエリア出ない場合は0.0d
	 */
	public double getArea() {
        Polygon polygon = getPolygon();
        if (polygon != null) {
            return polygon.getArea();
        }
        else {
        	return 0.0d;
        }
	}
	
	/**
	 * 指定のAREAと重複する領域の面積を取得する
	 * @param way
	 * @return
	 */
	public double getIntersectArea(ElementWay way) {
        Polygon polygon = getPolygon();
        Polygon polygon2 = way.getPolygon();
        Geometry intersect = polygon.intersection(polygon2);
		if (intersect == null) {
			return 0.0d;
		}
		if (intersect.isValid()) {
			return intersect.getArea();
		}
		return 0.0d;
	}
	
	/**
	 * このWAYと重複するWAYが存在するかどうか
	 * @param db
	 * @param where
	 * @return
	 * @throws Exception
	 */
	public boolean isIntersect(WayMap ways) throws Exception {
        for (String k : ways.keySet()) {
        	ElementWay way = ways.get(k);
        	double area = getIntersectArea(way);
			if (area > 0.0d) {
				return true;
			}
        }
        return false;
	}
	
	/**
	 * このWAYと重複する面積が最大の WAY.id を返す
	 * @param db
	 * @param where　例: "WHERE (tWay.orignal=true)"
	 * @return
	 * @throws Exception
	 */
	public long getIntersect(WayMap ways) throws Exception {
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

	//---------------------------------------------------------
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (area ? 1231 : 1237);
		result = prime * result + (member ? 1231 : 1237);
		result = prime * result + ((nds == null) ? 0 : nds.hashCode());
		result = prime * result + ((getTagList() == null) ? 0 : getTagList().hashCode());
		return result;
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
		if (!this.area || !other.area) {
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
	
	public boolean isBuilding() {
		for (TagBean tag : this.getTagList()) {
			if (tag.k.startsWith("building")) {
				return true;
			}
		}
		return false;
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
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElementWay other = (ElementWay) obj;
		if (area != other.area)
			return false;
		if (member != other.member)
			return false;
		if (nds == null) {
			if (other.nds != null)
				return false;
		} else if (!nds.equals(other.nds))
			return false;
		if (this.getTagList() == null) {
			if (other.getTagList() != null)
				return false;
		} else if (!getTagList().equals(other.getTagList()))
			return false;
		return true;
	}
}
