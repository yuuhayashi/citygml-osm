package osm.surveyor.osm.way;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.geotools.geometry.jts.JTSFactoryFinder;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;

import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.NdBean;
import osm.surveyor.osm.PoiBean;
import osm.surveyor.osm.RelationBean;
import osm.surveyor.osm.boxcel.BoxcellMappable;

public abstract class WayModel extends PoiBean implements Cloneable, Serializable, Wayable {
	private static final long serialVersionUID = -7346140905805747739L;
	
	public WayModel(long id) {
		super(id);
	}

	public PoiBean getPoiBean() {
		return (PoiBean)this;
	}
	
	/**
	 * fix=true 更新しないもの、fix=false 更新対象を示す。
	 */
	private boolean fix = false;
	public boolean getFix() {
		return this.fix;
	}
	@XmlAttribute(name="fix")
	public void setFix(boolean b) {
		this.fix = b;
	}

	/**
	 * area=# 面積
	 */
	private double area = 0d;	
	public double getArea() {
		if (this.area == 0d) {
			this.area = getAreaValue();
		}
		return this.area;
	}
	@XmlAttribute(name="area")
	public void setArea(double d) {
		this.area = d;
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
    
	/**
	 * WAY IndexCellメンバー
	 */
	private List<Integer> boxels = new ArrayList<>();
	public List<Integer> getBoxels() {
		return this.boxels;
	}
	public void addBoxel(Integer boxelId) {
		this.boxels.add(boxelId);
	}
	
	@Override
	public WayModel clone() {
		WayModel copy = null;
		try {
			copy = (WayModel) super.clone();
			
			copy.boxels = this.boxels;
			
			ArrayList<NdBean> nds = new ArrayList<>();
			for (NdBean nd : this.ndList) {
				nds.add((NdBean) nd.clone());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			copy = null;
		}
		return copy;
	}
	
	//--------------------------------------
	
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
		try {
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
		catch (Exception e) {
			return null;
		}
	}

	/**
	 * 指定のAREAと重複する領域の面積を取得する
	 * @param way
	 * @return
	 */
	@Override
	public double getIntersectArea(Wayable way) {
		List<Integer> list = getIntersectBoxels(way.getBoxels());
		if (list.size() > 0) {
	        Polygon polygon = this.getPolygon();
	        if (polygon == null) {
	        	return 0.0d;
	        }
	        Polygon polygon2 = way.getPolygon();
	        if (polygon2 == null) {
	        	return 0.0d;
	        }
	        Geometry intersect = polygon.intersection(polygon2);
			if (intersect == null) {
				return 0.0d;
			}
			if (intersect.isValid()) {
				return intersect.getArea();
			}
		}
		return 0.0d;
	}
	
	public static List<WayModel> toModelList(List<WayModel> beans) {
		List<WayModel> models = new ArrayList<>();
		for (WayModel bean : beans) {
			models.add(bean);
		}
		return models;
	}
	
	private List<Integer> getIntersectBoxels(List<Integer> boxcels) {
		List<Integer> list = new ArrayList<>();
		for (Integer key1 : this.boxels) {
			for (Integer key2 : boxcels) {
				if (key1.intValue() == key2.intValue()) {
					list.add(key1);
				}
			}
		}
		return list;
	}
	
	/**
	 * このWAYと重複する面積が最大の WAY.id を返す
	 * @param db
	 * @param where　例: "WHERE (tWay.orignal=true)"
	 * @return
	 * @throws Exception
	 */
	public long getIntersectMaxArea(BoxcellMappable bean) throws Exception {
		double max = 0.0d;
		long maxid = 0;
		
        for (Wayable way : bean.getWayList(this)) {
        	if (!way.getFix()) {
    			if (way.getDuplicateArea() > max) {
    				max = way.getDuplicateArea();
    				maxid = way.getId();
    			}
        	}
        }
        return maxid;
	}
	
	/**
	 * AREAの面積を求める。ただし、面積の単位は直行座標（メートルではない）
	 * @return	ラインが閉じたエリアでない場合は0.0d
	 */
	private double getAreaValue() {
        Polygon polygon = getPolygon();
        if (polygon != null) {
            return polygon.getArea();
        }
        else {
        	return 0.0d;
        }
	}
	
	/**
	 * あるエリアと承服している面積を一時的に記録する領域
	 */
	private double duplicateArea = 0.0;
	public double getDuplicateArea() {
		return this.duplicateArea;
	}
	public void setDuplicateArea(double area){
		this.duplicateArea = area;
	}

}
