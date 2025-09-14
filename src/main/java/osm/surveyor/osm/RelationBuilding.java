package osm.surveyor.osm;

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
