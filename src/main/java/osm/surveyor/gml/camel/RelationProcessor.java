package osm.surveyor.gml.camel;

import java.time.LocalTime;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.TagBean;

public class RelationProcessor implements Processor {

	/**
	 * リレーションの"name"を決定する
	 * 
	 * from : "direct:inOsmMargeWay",GerbageWayProcessor()
	 * to: "direct:osm-export"
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println(LocalTime.now() +"\tRelationProcessor.process(gml)");

		OsmDom osm = exchange.getIn().getBody(OsmDom.class);
		
		List<ElementRelation> relations = osm.getRelations();		
		for (ElementRelation relation : relations) {
			if (relation.getTagValue("type").equals("building")) {
				double max = 0;
				long maxid = 0;
				for (MemberBean member : relation.members) {
					if (member.getRole().equals("part")) {
						if (member.isWay()) {
							long id = member.getRef();
							ElementWay way = (ElementWay)osm.getWayMap().get(id);
							if ((way != null) && (way.getArea() > max)) {
								max = way.getArea();
								maxid = id;
							}
						}
					}
				}
				
				if (maxid != 0) {
					ElementWay outline = null;
					ElementWay maxway = (ElementWay)osm.getWayMap().get(maxid);
					for (MemberBean member : relation.members) {
						if (member.getRole().equals("outline")) {
							if (member.isWay()) {
								long id = member.getRef();
								outline = (ElementWay)osm.getWayMap().get(id);
							}
						}
					}
					if (maxway != null) {
						ElementWay way = maxway.clone();
						TagBean tag = maxway.getTag("building:part");
						if (tag != null) {
							way.addTag(new TagBean("building", tag.getValue()));
						}
						way.removeTag("building:levels");
						way.removeTag("building:part");
						way.removeTag("height");
						way.removeTag("MLIT_PLATEAU:fixme");
						way.removeTag("ref:MLIT_PLATEAU");
						way.removeTag("start_date");		// Issue #39 複合ビルでの”建築年”の扱い
						if (outline != null) {
							outline.copyTag(way);
						}
						relation.copyTag(way);
					}
				}
			}
		}
		
		System.out.println(LocalTime.now() +"\tRelationProcessor.gerbageMember()");
		osm.gerbageMember();	// Issue #76 オブジェクトが存在しないメンバーをRELATIONから削除する
		
		exchange.getIn().setBody(osm);
	}

}
