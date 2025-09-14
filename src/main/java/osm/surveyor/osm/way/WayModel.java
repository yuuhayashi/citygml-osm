package osm.surveyor.osm.way;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import osm.surveyor.gis.point.NdModel;

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

	@XmlTransient
	private boolean member = false;  // 単独のWAYか、RELATIONのメンバーかを示す。

	public boolean isMemberWay() {
		return this.member;
	}
	
	public void setMemberWay(boolean b) {
		this.member = b;
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
		
        for (Areable way : bean.getDuplicateWayList(this)) {
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
	
    public boolean existSamePosition(List<WayModel> solids) {
    	if (solids != null) {
        	for (WayModel solid : solids) {
        		if (this.isSamePositionWay(solid)) {
        			return true;
        		}
        	}
    	}
    	return false;
    }

    public WayModel getSamePositionWay(List<WayModel> solids) {
    	if (solids != null) {
        	for (WayModel solid : solids) {
        		if (this.isSamePositionWay(solid)) {
        			return solid;
        		}
        	}
    	}
    	return null;
    }

    public boolean isSamePositionWay(WayModel b) {
    	List<NdBean> aNds = this.getNdList();
    	List<NdBean> bNds = b.getNdList();
		if (aNds.size() == bNds.size()) {
	    	Set<Long> set = new HashSet<>();
	    	for (NdBean nd : bNds) {
	    		set.add(nd.getRef());
	    	}
	    	for (NdBean nd : aNds) {
	    		set.remove(nd.getRef());
	    	}
	    	if (set.isEmpty()) {
				return true;
	    	}
		}
		return false;
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

    /**
     * 必要なタグを上書きする
     *  - "type"はコピーしない
     *  - コピー先に"building:part=*"が存在すれば"building=*"とする
     *  - コピー先とコピー元に"bilding"が存在しなければ"building=yes"を補完する
     *  - "addr"と"addr:*"はコピーしない
     *  - "height"はコピーしない
     *  - "ele"はコピーしない
     *  - "source"と"source:*"はコピーしない
     *  - "ref"と"ref:*"はコピーしない
     * @param tags
     */
    public void copyTag(List<TagBean> tags) {
        if (tags == null) {
            return;
        }
        String building = "yes";
        TagBean buildingPartTag = this.getTag("building:part");
        if (buildingPartTag != null) {
        	if (building.equals("yes")) {
                building = buildingPartTag.v;
        	}
            this.removeTag("building:part");
        }
        String buildingTag = this.getTagValue("building");
        if (buildingTag != null) {
        	if (building.equals("yes")) {
                building = buildingTag;
        	}
        }
        for (TagBean tag : tags) {
            if (tag.k.equals("type")) {
            }
            else if (tag.k.equals("building:part")) {
            	if (building.equals("yes")) {
                    building = tag.v;
            	}
            }
            else if (tag.k.equals("building")) {
            	if (building.equals("yes")) {
                    building = tag.v;
            	}
            }
            else if (tag.k.equals("addr")) {
            }
            else if (tag.k.startsWith("addr:")) {
            }
            else if (tag.k.equals("source")) {
            }
            else if (tag.k.startsWith("source:")) {
            }
            else if (tag.k.equals("ref")) {
            }
            else if (tag.k.startsWith("ref:")) {
            }
            else if (tag.k.equals("height")) {
            }
            else if (tag.k.equals("ele")) {
            }
            else {
                this.addTag(tag.k, tag.v);
            }
        }
        this.addTag("building", building);
    }
                                                                                                                   
	//--------- Cloneable -------------------------------------------
    
	@SuppressWarnings("removal")
	@Override
	public WayModel clone() {
		WayModel copy = null;
		try {
			copy = (WayModel) super.clone();
			copy.member = this.member;
			copy.boxels = new ArrayList<Integer>();
			if (this.boxels != null) {
				for (Integer boxelid : this.boxels) {
					copy.addBoxel(new Integer(boxelid.intValue()));
				}
			}
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
        result = prime * result + (member ? 1231 : 1237);
        result = prime * result + ((getNdList() == null) ? 0 : getNdList().hashCode());
        result = prime * result + ((getBoxels() == null) ? 0 : getBoxels().hashCode());
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
        WayModel other = (WayModel) obj;
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
        if (this.getBoxels() == null) {
            if (other.getBoxels() != null) {
                return false;
            }
        } else if (!getBoxels().equals(other.getBoxels())) {
            return false;
        }
        return true;
    }
}
