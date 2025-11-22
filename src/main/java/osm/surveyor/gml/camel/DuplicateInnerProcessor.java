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
	 * Issue #138
	 * buildingとINNERが重なっているINNERを削除する。
	 * 
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println(LocalTime.now() +"\tDuplicateInnerProcessor.process()");

		OsmDom osm = exchange.getIn().getBody(OsmDom.class);
		for (ElementRelation relation : osm.relationMap.values()) {
			if (relation.getTagValue("type").equals("multipolygon")) {
				List<MemberBean> innerMembers = new ArrayList<>();
				List<WayModel> partWays = new ArrayList<>();
				int innerCnt = 0;
				for (MemberBean member : relation.members) {
					if (member.isWay()) {
						ElementWay memberWay = (ElementWay)osm.getWayMap().get(member.getRef());
						if (member.getRole().equals("inner") && (memberWay != null)) {
							innerCnt ++;
	
							// Issue #138
							WayModel partWay = memberWay.getSamePositionWay(osm.getDuplicateWayList(memberWay));
							if (partWay != null) {
								partWay.toPart();
								innerMembers.add(member);
								partWays.add(partWay);
								
								// Issue #138 マルチポリゴン内の"building:part"の親リレーション(type=building)は削除する
								List<ElementRelation> parentBuildingRelations = osm.relationMap.hasMembersRelation(partWay.getIdstr());
								for (ElementRelation parentBuildingRelation : parentBuildingRelations) {
									if (parentBuildingRelation.isBuilding()) {
										if (parentBuildingRelation.members.size() == 1) {
											for (MemberBean outline : parentBuildingRelation.members) {
												if (outline.getRole().equals("part") && outline.isWay()) {
													outline.setRole("outline");
												}
											}
										}
										parentBuildingRelation.removeMember(partWay.getId());
									}
								}
							}
						}
					}
				}
				
				for (MemberBean inner : innerMembers) {
					if (inner.isWay()) {
						ElementWay way = (ElementWay)osm.getWayMap().get(inner.getRef());
						relation.removeMember(inner.getRef());
						osm.removeWay(way);
					}
				}
				
				if ((partWays.size() > 0) && (partWays.size() == innerMembers.size())) {
					// Issue #138 マルチポリゴンの親リレーション(type=building)に"part"メンバーを追加する
					List<ElementRelation> parentBuildingRelations = osm.relationMap.hasMembersRelation(relation.getIdstr());
					for (ElementRelation parentBuildingRelation : parentBuildingRelations) {
						if (parentBuildingRelation.isBuilding()) {
							boolean hasOutlineWay = false;
							MemberBean partMember = null;
							MemberBean outlineMember = null;
							
							for (MemberBean parentMember : parentBuildingRelation.members) {
								if (parentMember.isWay()) {
									if (parentMember.getRole().equals("outline")) {
										hasOutlineWay = true;
									}
									else if (parentMember.getRole().equals("part")) {
										partMember = parentMember;
									}
								}
								else if (parentMember.isRelation()) {
									if (parentMember.getRole().equals("outline")) {
										outlineMember = parentMember;
									}
								}
							}
							if ((!hasOutlineWay) && (innerCnt == innerMembers.size())) {
								if (outlineMember != null) {
									parentBuildingRelation.removeMember(outlineMember.getRef());
								}
								if (partMember != null) {
									partMember.setRole("outline");
									WayModel way = osm.getWay(partMember.getRef());
									if (way != null) {
										way.toBuilding();
									}
								}
							}
							for (WayModel member : partWays) {
								parentBuildingRelation.addMember(member, "part");
							}
						}
					}
				}
			}
		}
		
		exchange.getIn().setBody(osm);
	}
}
