package osm.surveyor.osm.way;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;

import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.NdBean;
import osm.surveyor.osm.PoiBean;
import osm.surveyor.osm.RelationBean;
import osm.surveyor.osm.TagBean;
import osm.surveyor.osm.boxcel.BoxcellMappable;

@XmlRootElement(name="way")
public abstract class WayModel extends PoiBean implements Cloneable, Serializable, Areable {
	private static final long serialVersionUID = -7346140905805747739L;
	
	public WayModel(long id) {
		super(id);
		ndList = new ArrayList<>();
	}

	public WayModel() {
		this(0);
		ndList = new ArrayList<>();
	}

	public PoiBean getPoiBean() {
		return (PoiBean)this;
	}
	
	/**
	 * WAYノードメンバー
	 */
    private List<NdBean> ndList = new ArrayList<>();
	
    public void setNdList(List<NdBean> ndList) {
		this.ndList = ndList;
    }
    
	@XmlElement(name="nd")
    public List<NdBean> getNdList() {
    	return this.ndList;
    }
	
	public Coordinate[] getCoordinates() {
		ArrayList<Coordinate> list = new ArrayList<>();
		for (NdBean nd : getNdList()) {
			list.add(nd.getCoordinate());
		}
		return list.toArray(new Coordinate[list.size()]);
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
	@XmlAttribute(name="area")
	@Override
	public void setArea(double d) {
		this.area = d;
	}

	/**
	 * AREAの面積を求め、内部変数に保持する。
	 * ただし、面積の単位は直行座標（メートルではない）
	 * @return	ラインが閉じたエリアでない場合は 0.0
	 */
	@Override
	public double getArea() {
		if (this.area == 0d) {
			this.area = getAreaValue();
		}
		return this.area;
	}
    
	/**
	 * WAY IndexCellメンバー
	 */
	private List<Integer> boxels = new ArrayList<>();
	@Override
	public List<Integer> getBoxels() {
		return this.boxels;
	}
	public void addBoxel(Integer boxelId) {
		this.boxels.add(boxelId);
	}
	
	//--------------------------------------
	
	
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
	
	public static List<WayModel> toModelList(List<WayModel> beans) {
		List<WayModel> models = new ArrayList<>();
		for (WayModel bean : beans) {
			models.add(bean);
		}
		return models;
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
		
        for (Areable way : bean.getWayList(this)) {
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
	 * あるエリアと重複している面積を一時的に記録する領域
	 */
	private double duplicateArea = 0.0;
	
	@XmlTransient
	public double getDuplicateArea() {
		return this.duplicateArea;
	}
	public void setDuplicateArea(double area){
		this.duplicateArea = area;
	}
	
    //---------- PoiBean ----------------------------
	
	public boolean isBuilding() {
		for (TagBean tag : this.getTagList()) {
			if (tag.k.startsWith("building")) {
				if (!tag.v.equals("no")) {
					return true;
				}
			}
		}
		return false;
	}
	//--------- Cloneable -------------------------------------------
    
	@Override
	public WayModel clone() {
		WayModel copy = null;
		try {
			copy = (WayModel) super.clone();
			copy.boxels = this.boxels;
		}
		catch (Exception e) {
			e.printStackTrace();
			copy = null;
		}
		return copy;
	}
}
