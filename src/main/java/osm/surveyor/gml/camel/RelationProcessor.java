package osm.surveyor.gml.camel;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.OsmDom;

public class RelationProcessor implements Processor {

	/**
	 * リレーションの"name"を決定する
	 * 
	 * from : "direct:inOsmMargeWay",GerbageWayProcessor()
	 * to: "direct:osm-export"
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		OsmDom osm = exchange.getIn().getBody(OsmDom.class);
		
		List<ElementRelation> relations = osm.getRelations();
		for (ElementRelation relation : relations) {
			if (relation.getTagValue("type").equals("building")) {
				double max = 0;
				String name = null;
				for (MemberBean member : relation.members) {
					if (member.isWay()) {
						ElementWay way = osm.ways.get(member.getRef());
						if (way.getArea() > max) {
							max = way.getArea();
							if (way.getTagValue("name") != null) {
								name = way.getTagValue("name");
							}
						}
					}
				}
				relation.removeTag("name");
				if (name != null) {
					relation.addTag("name", name);
				}
			}
		}
		
		exchange.getIn().setBody(osm);
	}

}
