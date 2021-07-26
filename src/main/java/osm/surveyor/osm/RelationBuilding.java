package osm.surveyor.osm;

/**
 * https://wiki.openstreetmap.org/wiki/JA:Relations/Proposed/Buildings
 *  type=building
 *   role:outline "1..*" --> ElementWay[area=yes]
 *   role:part "1..*" --> RelationMultipolygon or ElementWay[area=yes]
 */
public class RelationBuilding extends ElementRelation implements Cloneable {
	
	public RelationBuilding(long id) {
		super(id);
		addTag("type", "building");
	}
	
	public ElementRelation getMultiPolygon(OsmDom osm) {
		ElementMember outlineMember = getOutlineMember();
		if (outlineMember != null) {
			return osm.relations.get(outlineMember.ref);
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
		ElementMember outlineMember = getOutlineMember();
		if (outlineMember == null) {
			return null;
		}
		else {
			if (outlineMember.type.equals("way")) {
				return osm.ways.get(outlineMember.ref);
			}
			else if (outlineMember.type.equals("relation")) {
				ElementRelation polygon = osm.relations.get(outlineMember.ref);
				if (polygon != null) {
					for (ElementMember outlinemember : polygon.members) {
						if (outlinemember.role.equals("outer")) {
							return osm.ways.get(outlinemember.ref);
						}
					}
				}
			}
		}
		return null;
	}
}
