package osm.surveyor.gml.camel;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.TagBean;
import osm.surveyor.osm.way.WayModel;

public class RelationProcessor implements Processor {

	/**
	 * buildingとINNERが重なっているINNERを削除する。
	 * リレーションの"name"を決定する
	 * 
	 * from : "direct:inOsmMargeWay",GerbageWayProcessor()
	 * to: "direct:osm-export"
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println(LocalTime.now() +"\tRelationProcessor.getRelations()");

		OsmDom osm = exchange.getIn().getBody(OsmDom.class);
		
		List<ElementRelation> relations = osm.getRelations();
		for (ElementRelation relation : relations) {
			if (relation.getTagValue("type").equals("multipolygon")) {
				List<MemberBean> removeMember = new ArrayList<>();
				List<ElementWay> partMembers = new ArrayList<>();
				for (MemberBean member : relation.members) {
					if (member.getRole().equals("inner")) {
						if (member.isWay()) {
							long id = member.getRef();
							ElementWay way = (ElementWay)osm.getWayMap().get(id);
							if (way != null) {

								// Issue #138
								WayModel part = way.getSamePositionWay(osm.getDuplicateWayList(way));
								if (part != null) {
									String str = part.getTagValue("building");
									if (str != null) {
										part.removeTag("building");
										part.addTag("building:part", str);
									}
									removeMember.add(member);
									partMembers.add(way);
								}
							}
						}
					}
				}
				
				for (MemberBean member : removeMember) {
					if (member.isWay()) {
						ElementWay way = (ElementWay)osm.getWayMap().get(member.getRef());
						osm.removeWay(way);
						relation.removeMember(member.getRef());
					}
				}
				
				for (ElementWay member : partMembers) {
					relation.addMember(member, "part");
				}
			}
		}
		
		// "outer"だけのマルチポリゴンは "type=building"に置き換える
		for (ElementRelation relation : relations) {
			if (relation.getTagValue("type").equals("multipolygon")) {
				boolean exitInner = false;
				for (MemberBean member : relation.members) {
					if (member.getRole().equals("inner")) {
						exitInner = true;
					}
				}

				if (!exitInner) {
					for (MemberBean member : relation.members) {
						if (member.isWay()) {
							if (member.getRole().equals("outer")) {
								ElementWay way = (ElementWay)osm.getWayMap().get(member.getRef());
								way.copyTag(relation);
								way.removeTag("type");
								way.removeTag("building:part");
								way.addTag("building", "yes");
							}
							member.setRole("outline");
						}
					}
					relation.addTag("type", "building");
				}
			}
		}
		
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
