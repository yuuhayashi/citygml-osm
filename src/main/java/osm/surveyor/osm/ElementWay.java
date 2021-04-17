package osm.surveyor.osm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import osm.surveyor.osm.update.ImplPostgis;
import osm.surveyor.osm.update.Postgis;

/**
 * 
 * <way id='96350144' timestamp='2018-08-25T08:34:33Z' uid='7548722' user='Unnown' visible='true' version='17' changeset='61979354'>
 * @author hayashi
 *
 */
public class ElementWay extends ElementOsmapi implements Cloneable, ImplPostgis {
	public ArrayList<OsmNd> nds;
	public ArrayList<ElementTag> tags;
	boolean area = false;
	public boolean member = false;
	
	public ElementWay(long id) {
		super(id);
		nds = new ArrayList<OsmNd>();
		tags = new ArrayList<ElementTag>();
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
			copy.tags = new ArrayList<ElementTag>();
			if (this.tags != null) {
				for (ElementTag tag : this.tags) {
					copy.tags.add(tag.clone());
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			copy = null;
		}
		return copy;
	}
	
	public void addTag(String k, String v) {
		this.tags.add(new ElementTag(k, v));
	}
	
	public void addNode(OsmNd node) {
		this.nds.add(node);
	}
	
	public void addNode(ElementNode node) {
		this.nds.add((new OsmNd()).set(node.id, node.point));
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
		for (ElementTag tag : this.tags) {
			element.appendChild(tag.toNodeNd(doc));
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
     * sourceと一致するTAGを、destに置き換える
     * @param source
     * @param dest
     */
    public void replaceTag(ElementTag source, ElementTag dest) {
    	for (ElementTag tag : tags) {
    		if (tag.k.equals(source.k)) {
    			if (tag.v.equals(source.v)) {
    				tags.add(dest);
    				tags.remove(tags.indexOf(tag));
    				return;
    			}
    		}
    	}
    }
    
    /**
     * 線分(TwoPoint)リスト
     */
    public ArrayList<TwoPoint> getPointList() {
    	ArrayList<TwoPoint> pointlist = new ArrayList<>();
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
     * WAYを他のWAYと合成する;
     */
    public ElementWay marge(ElementWay bWay) {
    	// ２つのWAYから共通する線分を削除する
		removeDuplicatedSegment(bWay);
    	return this;
    }
    
    /**
     * slistの末尾に接続可能な線分をalistから取り出してslistに追加する
     */
    private TwoPoint connectSegments(ArrayList<TwoPoint> slist, ArrayList<TwoPoint> alist) {
    	TwoPoint p;
    	TwoPoint segment = slist.get(slist.size()-1);
    	while ((p = getAndRemoveConnectableSegments(alist, segment.b)) != null) {
    		if (!p.a.equals(segment.b)) {
    			p.reverse();
    		}
    		slist.add(p);
    		return p;
    	}
    	return null;
    }
    
    /**
     * 'segment.b'に接続可能なsegmentを'list'から取得,
     * 取得したsegmentはlistから削除される
     * @param list
     * @param segment
     * @return 接続可能なsegment, 存在しなければnull
     */
    private TwoPoint getAndRemoveConnectableSegments(ArrayList<TwoPoint> list, OsmNd point) {
    	for (TwoPoint p : list) {
    		if (p.a.equals(point) || p.b.equals(point)) {
    			list.remove(list.indexOf(p));
    			return p;
    		}
    	}
    	return null;
    }
    
    /**
     * ２つのWAYから共通する線分を削除する
     * @param sList		対象のライン
     * @param segment	削除するセグメント
     * @return	削除したらTrue
     */
    private void removeDuplicatedSegment(ElementWay bWay) {
    	ArrayList<TwoPoint> list = new ArrayList<>();
    	ArrayList<TwoPoint> aList = getPointList();
    	ArrayList<TwoPoint> bList = bWay.getPointList();
    	for (TwoPoint aSegment : aList) {
    		list.add(aSegment);
    	}
    	for (TwoPoint aSegment : list) {
    		while (removeSegment(bList, aSegment)) {
        		while (removeSegment(aList, aSegment));
    		}
    	}

		// aListにbWayを統合する
    	for (TwoPoint bSegment : bList) {
    		aList.add(bSegment);
    	}

    	ArrayList<TwoPoint> sList = new ArrayList<>();
    	for (TwoPoint aSegment : aList) {
    		// 先頭の線分をLISTから取り出す。
    		sList.add(aSegment);
    		aList.remove(aList.indexOf(aSegment));
        	break;
    	}
    	while (connectSegments(sList, aList) != null);
    	
    	// wayのLINEを sList に書き換える
    	this.nds = new ArrayList<>();
    	for (TwoPoint segment : sList) {
    		if (this.nds.isEmpty()) {
    			this.nds.add(segment.a);
    		}
			this.nds.add(segment.b);
    	}
    }

    /**
     * List<LineSegment>から指定されたセグメントを削除する
     * @param sList		対象のライン
     * @param segment	削除するセグメント
     * @return	削除したらTrue
     */
    private boolean removeSegment(ArrayList<TwoPoint> sList, TwoPoint segment) {
    	if (sList == null) {
    		return false;
    	}
    	for (TwoPoint sPoint : sList) {
    		if (sPoint.equal(segment)) {
    			sList.remove(sList.indexOf(sPoint));
    			return true;
    		}
    	}
    	return false;
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
	
	public boolean isIntersect(Postgis db) throws Exception {
        String geom = getGeomText();
        if (geom == null) {
        	return false;
        }
        
        String sqlStr = "WITH source AS (SELECT ST_GeometryFromText('"+ geom +"', 4326) AS geo) "
        		+ "SELECT ST_Intersects(s.geo, tWay.geom) AS intersect "
        		+ "FROM tWay, source AS s "
        		+ "WHERE (tWay.orignal=false)";
        try (Statement st = db.con.createStatement()) {
        	ResultSet resultSet = st.executeQuery(sqlStr);
			while (resultSet.next()) {
				if (resultSet.getBoolean("intersect")) {
					return true;
				}
			}
        }
        return false;
	}
	
    //---------------------------------------------------------
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (area ? 1231 : 1237);
		result = prime * result + (member ? 1231 : 1237);
		result = prime * result + ((nds == null) ? 0 : nds.hashCode());
		result = prime * result + ((tableName == null) ? 0 : tableName.hashCode());
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
		if (tableName == null) {
			if (other.tableName != null)
				return false;
		} else if (!tableName.equals(other.tableName))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		return true;
	}

    String tableName = "tWay";

    @Override
	public void initTable(Postgis db) throws Exception {
        db.sql("DROP INDEX IF EXISTS ix_"+ tableName +" CASCADE;");
        db.sql("DROP TABLE IF EXISTS "+ tableName +" CASCADE;");
        db.sql("CREATE TABLE "+ tableName 
            +" ("
                + "id SERIAL PRIMARY KEY, "
                + "action varchar(24), "
                + "timestamp varchar(36), "
                + "uid varchar(16), "
                + "username varchar(64), "
                + "visible boolean, "
                + "version varchar(8), "
                + "changeset varchar(16), "
                + "orignal boolean, "
                + "member boolean, "
                + "geom GEOMETRY(POLYGON, 4326)"
            + ");");
        db.sql("CREATE INDEX ix_"+ tableName +"_geom ON "+ tableName +" USING GiST (geom);");
	}
    
	@Override
	public void insertTable(Postgis db) throws Exception {
        String geom = getGeomText();
        geom = (geom==null ? null : "ST_GeomFromText('"+ geom +"',4326)");
        
        String sqlStr = "INSERT INTO "+ tableName 
                +" (id,action,timestamp,uid,username,visible,version,changeset,orignal,member"+ (geom==null ? "" : ",geom") +") "
                + "VALUES (?,?,?,?,?,?,?,?,?,?"+ (geom==null ? "" : (","+ geom)) +")";
        try (PreparedStatement ps = db.con.prepareStatement(sqlStr)) {
            ps.setLong(1, id);   // id
            ps.setString(2, action);   // action
            ps.setString(3, timestamp);       // timestamp
            ps.setString(4, uid);      // uid
            ps.setString(5, user);		// username
            ps.setBoolean(6, visible);       // visible
            ps.setString(7, version);		// version
            ps.setString(8, changeset);	// changeset
            ps.setBoolean(9, orignal);       // orignal
            ps.setBoolean(10, member);       // member
            ps.executeUpdate();
        }
	}
	
	public void delete(Postgis db) throws Exception {
        String sqlStr = "DELETE FROM "+ tableName 
                +" WHERE (id=?)";
        try (PreparedStatement ps = db.con.prepareStatement(sqlStr)) {
            ps.setLong(1, id);   // id
            ps.executeUpdate();
        }
	}
}
