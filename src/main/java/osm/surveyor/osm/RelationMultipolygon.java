package osm.surveyor.osm;

/**
 * https://wiki.openstreetmap.org/wiki/JA:Relation:multipolygon
 * 	role:outer "1..*" --> ElementRelation or ElementWay --> <gml:exterior/>
 *  role:inner "0..*" --> ElementWay[area=yes] --> <gml:interior/>
 */
public class RelationMultipolygon extends ElementRelation implements Cloneable {
	
	public RelationMultipolygon() {
		super();
		addTag("type", "multipolygon");
	}
	
}