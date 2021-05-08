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
	boolean area = false;
	public boolean member = false;
	
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
		for (String key  : this.tags.keySet()) {
			ElementTag tag = tags.get(key);
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
	
	/**
	 * このWAYと重複するWAYが存在するかどうか
	 * @param db
	 * @param where
	 * @return
	 * @throws Exception
	 */
	public boolean isIntersect(Postgis db, String where) throws Exception {
        String geom = getGeomText();
        if (geom == null) {
        	return false;
        }
        
        String sqlStr = "WITH source AS (SELECT ST_GeometryFromText('"+ geom +"', 4326) AS geo) "
        		+ "SELECT ST_Intersects(s.geo, tWay.geom) AS intersect "
        		+ "FROM tWay, source AS s "
        		+ (where!=null ? where : "" );
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
	
	/**
	 * このWAYと重複する面積が最大の WAY.id を返す
	 * @param db
	 * @param where　例: "WHERE (tWay.orignal=true)"
	 * @return
	 * @throws Exception
	 */
	public long getIntersect(Postgis db, String where) throws Exception {
        String geom = getGeomText();
        if (geom == null) {
        	return 0;
        }
        
        String sqlStr = "WITH source AS (SELECT ST_GeometryFromText('"+ geom +"', 4326) AS geo) "
        		+ "SELECT tWay.id as id, ST_AREA(ST_Intersection(s.geo, tWay.geom)) AS area "
        		+ "FROM tWay, source AS s "
        		+ (where!=null ? where : "")
        		+ " ORDER BY area DESC";
        try (Statement st = db.con.createStatement()) {
        	ResultSet resultSet = st.executeQuery(sqlStr);
			while (resultSet.next()) {
				//float area = resultSet.getFloat("area");
				return resultSet.getLong("id");
			}
        }
        return 0;
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

	/**
	 * 同じ位置WAYかどうか
	 * @param obj
	 * @return
	 */
	public boolean isSame(Object obj) {
		if (this == obj) {
			return true;
		}
		ElementWay other = (ElementWay) obj;
		if (nds == null) {
			if (other.nds != null) {
				return false;
			}
		}
		else if (nds.equals(other.nds)) {
			return true;
		}
		else if (this.nds.size() == other.nds.size()) {
			for (int i = 0; i < this.nds.size(); i++) {
				OsmNd nda = this.nds.get(i);
				OsmNd ndb = other.nds.get(i);
				if (nda.id != ndb.id) {
					return false;
				}
				if (!nda.point.equals(ndb.point)) {
					return false;
				}
			}
			return true;
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
