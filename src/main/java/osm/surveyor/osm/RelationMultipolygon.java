package osm.surveyor.osm;

/**
 * https://wiki.openstreetmap.org/wiki/JA:Relation:multipolygon
 * 	role:outer "1..*" --> ElementRelation or ElementWay --> <gml:exterior/>
 *  role:inner "0..*" --> ElementWay[area=yes] --> <gml:interior/>
 */
public class RelationMultipolygon extends ElementRelation implements Cloneable {
	private static final long serialVersionUID = -1694207136692136023L;

	public RelationMultipolygon(long id) {
		super(id);
		addTag("type", "multipolygon");
	}
	
}
