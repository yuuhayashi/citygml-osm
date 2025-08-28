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
		
		List<ElementRelation> removeRelations = new ArrayList<>();
		
		for (ElementRelation relation : osm.relationMap.values()) {
			if (relation.getTagValue("type").equals("multipolygon")) {
				boolean noInner = true;
				List<MemberBean> innerMembers = new ArrayList<>();
				List<WayModel> partWays = new ArrayList<>();
				
				for (MemberBean member : relation.members) {
					ElementWay memberWay = (ElementWay)osm.getWayMap().get(member.getRef());
					if (member.getRole().equals("inner") && member.isWay() && (memberWay != null)) {

						// Issue #138
						WayModel partWay = memberWay.getSamePositionWay(osm.getDuplicateWayList(memberWay));
						if (partWay != null) {
							String str = partWay.getTagValue("building");
							if (str != null) {
								partWay.removeTag("building");
								partWay.addTag("building:part", str);
							}
							innerMembers.add(member);
							partWays.add(partWay);
							
							// Issue #138 マルチポリゴン内の"building:part"の親リレーション(type=building)は削除する
							List<ElementRelation> parentBuildingRelations = osm.relationMap.hasMembersRelation(partWay.getIdstr());
							for (ElementRelation parentBuildingRelation : parentBuildingRelations) {
								if (parentBuildingRelation.isBuilding()) {
									parentBuildingRelation.removeMember(partWay.getId());
								}
							}
						}
						else {
							noInner = false;
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
				
				// Issue #138 マルチポリゴンの親リレーション(type=building)に"part"メンバーを追加する
				List<ElementRelation> parentBuildingRelations = osm.relationMap.hasMembersRelation(relation.getIdstr());
				for (ElementRelation parentBuildingRelation : parentBuildingRelations) {
					if (parentBuildingRelation.isBuilding()) {
						for (WayModel member : partWays) {
							parentBuildingRelation.addMember(member, "part");
						}
					}
				}
				margeBuilgingRelation(osm, relation);
				if (noInner) {
					removeRelations.add(relation);
				}
			}
		}
		
		// Issue #138 "inner"なしのマルチポリゴンは削除する
		// Issue #138 マルチポリゴン内の"building:part"の親リレーション(type=building)は削除する
		// マルチポリゴンのOUTERは、マルチポリゴンの親リレーションにOUTLINEとして追加する
		for (ElementRelation multi : removeRelations) {
			List<MemberBean> outers = new ArrayList<>();
			for (MemberBean multiMember : multi.members) {
				if (multiMember.getRole().equals("outer")) {
					outers.add(multiMember);
				}
			}
			List<ElementRelation> parentBuildingRelations = osm.relationMap.hasMembersRelation(multi.getIdstr());
			for (ElementRelation parentBuildingRelation : parentBuildingRelations) {
				List<MemberBean> deleteMembers = new ArrayList<>();
				for (MemberBean parentMember : parentBuildingRelation.members) {
					if (parentMember.getRole().equals("outline")) {
						if (parentMember.isRelation()) {
							if (parentMember.getRef() == multi.getId()) {
								deleteMembers.add(parentMember);
							}
						}
					}
				}
				for (MemberBean member : deleteMembers) {
					parentBuildingRelation.removeMember(member.getRef());
				}
				for (MemberBean outer : outers) {
					parentBuildingRelation.addMember(osm.getWayMap().get(outer.getRef()), "outline");
				}
			}
			for (MemberBean outer : outers) {
				multi.removeMember(outer.getRef());
			}

			osm.relationMap.remove(multi);
		}
		exchange.getIn().setBody(osm);
	}
	
	/**
	 * マルチポリゴンの"part"メンバーを親リレーションにマージする
	 * マルチポリゴンの"part"メンバーを削除する
	 */
	private void margeBuilgingRelation(OsmDom dom, ElementRelation multipolygon) {
		if (multipolygon.isMultipolygon()) {
			long multipolygonid = multipolygon.getId();
			List<MemberBean> parts = new ArrayList<>();
			for (MemberBean mem : multipolygon.members) {
				if (mem.getRole().equals("part")) {
					parts.add(mem);
				}
			}
			
			for (ElementRelation parent : dom.relationMap.values()) {
				if (parent.isBuilding()) {
					boolean isParent = false;
					for (MemberBean member : parent.members) {
						if (member.isRelation() && (member.getRef() == multipolygonid)) {
							isParent = true;
							break;
						}
					}
					if (isParent) {
						for (MemberBean part : parts) {
							multipolygon.addMember(part);
						}
					}
				}
			}

			for (MemberBean part : parts) {
				multipolygon.members.remove(part);
			}
		}
	}
}
