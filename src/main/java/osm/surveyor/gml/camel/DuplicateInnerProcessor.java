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
import osm.surveyor.osm.way.WayModel;

public class DuplicateInnerProcessor implements Processor {

	/**
	 * buildingとINNERが重なっているINNERを削除する。
	 * 
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println(LocalTime.now() +"\tDuplicateInnerProcessor.process()");

		OsmDom osm = exchange.getIn().getBody(OsmDom.class);
		
		List<ElementRelation> relations = osm.getRelations();
		for (ElementRelation relation : relations) {
			if (relation.getTagValue("type").equals("multipolygon")) {
				List<MemberBean> removeMember = new ArrayList<>();
				List<WayModel> partMembers = new ArrayList<>();
				for (MemberBean member : relation.members) {
					if (member.getRole().equals("inner")) {
						if (member.isWay()) {
							ElementWay way = (ElementWay)osm.getWayMap().get(member.getRef());
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
									partMembers.add(part);
								}
							}
						}
					}
				}
				
				for (MemberBean member : removeMember) {
					if (member.isWay()) {
						ElementWay way = (ElementWay)osm.getWayMap().get(member.getRef());
						relation.removeMember(member.getRef());
						osm.removeWay(way);
					}
				}
				
				for (WayModel member : partMembers) {
					relation.addMember(member, "part");
				}
			}
		}
		
		// "outer"だけのマルチポリゴンは "type=building"に置き換える
		for (ElementRelation relation : osm.relationMap.values()) {
			if (relation.getTagValue("type").equals("multipolygon")) {
				boolean exitInner = false;
				for (MemberBean member : relation.members) {
					if (member.getRole().equals("inner")) {
						exitInner = true;
					}
				}

				if (!exitInner) {
					for (MemberBean member : relation.members) {
						if (member.getRole().equals("outer")) {
							member.setRole("outline");
						}
					}
					relation.addTag("type", "building");
				}
			}
		}
		exchange.getIn().setBody(osm);
	}
}
