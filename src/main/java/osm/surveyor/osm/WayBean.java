package osm.surveyor.osm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.geotools.geometry.jts.JTSFactoryFinder;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;

/**
 * @code{
 * <osm>
 *  <way id='96350144'
 *   timestamp='2018-08-25T08:34:33Z'
 *   uid='7548722' user='Unnown' visible='true' version='17' changeset='61979354'/>
 *  <way id='-2' action='modify' visible='true'>
 *    <nd ref='-3'/>
 *    <nd ref='-4'/>
 *    <nd ref='-5'/>
 *    <tag k='height' v='14.07' />
 *    <tag k='building:part' v='yes' />
 *  </way>
 *         :
 * </osm>
 * }
 * 
 */
@XmlRootElement(name="way")
public class WayBean extends PoiBean implements Cloneable, Serializable {
	private static final long serialVersionUID = 5518601165141588723L;
	
	public WayBean() {
		this(0);
	}
	
	public WayBean(long id) {
		super(id);
	}
	
	/**
	 * fix=true 更新しないもの、fix=false 更新対象を示す。
	 */
	private boolean fix = false;
	
	@XmlAttribute(name="fix")
	public boolean getFix() {
		return this.fix;
	}
	public void setFix(boolean b) {
		this.fix = b;
	}

	/**
	 * WAYノードメンバー
	 */
    private List<NdBean> ndList = new ArrayList<>();
    
    @XmlElement(name="nd")
    public List<NdBean> getNdList() {
    	return this.ndList;
    }

    public void setNdList(List<NdBean> ndList) {
    	this.ndList = ndList;
    }
    
	@Override
	public WayBean clone() {
		WayBean copy = null;
		try {
			copy = (WayBean) super.clone();
			
			copy.fix = this.fix;
			
			ArrayList<NdBean> nds = new ArrayList<>();
			for (NdBean nd : this.ndList) {
				nds.add(nd.clone());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			copy = null;
		}
		return copy;
	}
	
	//--------------------------------------
	
	public boolean isBuilding() {
		for (TagBean tag : this.getTagList()) {
			if (tag.k.startsWith("building")) {
				return true;
			}
		}
		return false;
	}
	
	public Coordinate[] getCoordinates() {
		ArrayList<Coordinate> list = new ArrayList<>();
    	for (NdBean nd : this.getNdList()) {
    		list.add(nd.getCoordinate());
    	}
		return list.toArray(new Coordinate[list.size()]);
	}
	
	/**
	 * このWAYがマルチポリゴンのINNERであるかどうか
	 * @param relations
	 * @return
	 */
	public boolean isInnerWay(List<RelationBean> relations) {
		for (RelationBean relation : relations) {
			if (relation.hasMember(this.getId())) {
				if (relation.isMultipolygon()) {
					for (MemberBean member : relation.getMemberList()) {
						if (member.getRole().equals("inner")) {
							if (member.getRef() == this.getId()) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
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
	 * 指定のAREAと重複する領域の面積を取得する
	 * @param way
	 * @return
	 */
	public double getIntersectArea(WayBean way) {
        Polygon polygon = this.getPolygon();
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
	public boolean isIntersect(List<WayBean> ways) throws Exception {
        for (WayBean way : ways) {
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
	public long getIntersect(List<WayBean> ways) throws Exception {
		double max = 0.0d;
		long maxid = 0;
        for (WayBean way : ways) {
        	double area = getIntersectArea(way);
			if (area > max) {
				max = area;
				maxid = way.getId(); 
			}
        }
        return maxid;
	}

}
