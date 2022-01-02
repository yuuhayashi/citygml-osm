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
public class WayBean extends PoiBean implements Serializable {
	private static final long serialVersionUID = 5518601165141588723L;
	
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

    private List<NdBean> ndList;
    
    @XmlElement(name="nd")
    public List<NdBean> getNdList() {
    	return this.ndList;
    }

    public void setNdList(List<NdBean> ndList) {
    	this.ndList = ndList;
    }
    
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
	
}
