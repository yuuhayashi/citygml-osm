package osm.surveyor.osm;

/**
 * https://wiki.openstreetmap.org/wiki/JA:Relations/Proposed/Buildings
 *  type=building
 *   role:outline "1..*" --> ElementWay[area=yes]
 *   role:part "1..*" --> RelationMultipolygon or ElementWay[area=yes]
 */
public class RelationBuilding extends ElementRelation implements Cloneable {
	
	public RelationBuilding() {
		super();
		addTag("type", "building");
	}
	
}
