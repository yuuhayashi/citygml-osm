package osm.surveyor.osm;

import java.util.HashMap;
import java.util.Map;

import osm.surveyor.osm.way.WayModel;

/**
 * https://wiki.openstreetmap.org/wiki/JA:Relations/Proposed/Buildings
 *  type=building
 *   role:outline "1..*" --> ElementWay[area=yes]
 *   role:part "1..*" --> RelationMultipolygon or ElementWay[area=yes]
 */
public class RelationBuilding extends ElementRelation implements Cloneable {
	private static final long serialVersionUID = 1L;

	public RelationBuilding(long id) {
		super(id);
		addTag("type", "building");
	}
	
	public ElementRelation getMultiPolygon(OsmDom osm) {
		MemberBean outlineMember = getOutlineMember();
		if (outlineMember != null) {
			return osm.relationMap.get(outlineMember.getRef());
		}
		return null;
	}
	
	/**
	 * "outline"WAYを取得する
	 * 前提： "outline"メンバーは一つのWAYにまとめられていること
	 * @param osm
	 * @return	"outline"がないときはNULL
	 */
	public ElementWay getOutlineWay(OsmDom osm) {
		MemberBean outlineMember = getOutlineMember();
		if (outlineMember == null) {
			return null;
		}
		else {
			if (outlineMember.isWay()) {
				return (ElementWay)osm.getWayMap().get(outlineMember.getRef());
			}
			else if (outlineMember.isRelation()) {
				ElementRelation polygon = osm.relationMap.get(outlineMember.getRef());
				if (polygon != null) {
					for (MemberBean outermember : polygon.members) {
						if (outermember.getRole().equals("outer")) {
							return (ElementWay)osm.getWayMap().get(outermember.getRef());
						}
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * "ele"と"height"を統合してリレーションに設定する
	 * "building:levels"と"building:levels:underground"を統合してリレーションに設定する
	 * @param relationMap
	 */
	public void margeTagValue(OsmDom osm) {
		Map<String, PoiBean> parts = new HashMap<>();
		ElementWay outline = null; 
		for (MemberBean member : this.members) {
			if (member.getRole().equals("outline")) {
				if (member.isWay()) {
					outline = (ElementWay)osm.getWayMap().get(member.getRef());
				}
			}
			else if (member.getRole().equals("part")) {
				if (member.isWay()) {
					ElementWay way = (ElementWay)osm.getWayMap().get(member.getRef());
					parts.put(way.getIdstr(), way);
				}
				else if (member.isRelation()) {
					ElementRelation relation = osm.relationMap.get(member.getRef());
					parts.put(relation.getIdstr(), relation);
				}
			}
		}

		if (outline != null) {
			outline.margeTagset(parts);
			outline.toBuilding();
			outline.removeTag("start_date");	// issue #39
		}
		
		this.margeTagset(parts);
		this.toBuilding();

		// 建築年はリレーションに反映させない
		// [Issue39](https://github.com/yuuhayashi/citygml-osm/issues/39)
		this.removeTag("start_date");
	}

	/**
	 * 所属メンバーの中から、最大面積のAREAを取得する
	 * @param ways	メンバーの実態
	 * @return	指定したタグが存在しない場合はNULL
	 */
	public WayModel getMaxArea(OsmDom dom) {
		WayModel max = null;
		double maxarea = 0.0d;
		for (MemberBean member : this.members) {
			if (member.getType().equals("way")) {
				WayModel way = dom.getWayMap().get(member.getRef());
				double area = way.getArea();
				if ((max == null) || (maxarea < area)) {
					max = way;
					maxarea = area;
				}
			}
		}
		return max;
	}
}
