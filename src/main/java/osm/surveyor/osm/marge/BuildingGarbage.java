package osm.surveyor.osm.marge;

import java.util.*;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class BuildingGarbage {
	OsmDom osm;
	
	public BuildingGarbage(OsmDom osm) {
		this.osm = osm;
	}

	/**
	 * メンバーが一つしかないRelation:multipolygon とその polygon:member を削除する
	 */
	public void garbageMultipolygon() {
		while (multipolygonRemove());
	}

	/**
	 *  メンバーが一つしかないRelation:multipolygon と polygon:member を削除する
	 * @return
	 */
	boolean multipolygonRemove() {
		for (ElementRelation relation : osm.relationMap.values()) {
			if (relation.isMultipolygon()) {
				int memberCnt = relation.members.size();
				if (memberCnt == 0) {
					preDeleteMembers(relation.getId());
					osm.relationMap.remove(relation.getIdstr());
					return true;
				}
				else if (memberCnt == 1) {
					for (MemberBean member : relation.members) {
						ElementWay way = (ElementWay)osm.getWayMap().get(member.getRef());
						if (way != null) {
							way.setMemberWay(true);
							relation.removeMember(way.getId());
							osm.removeWay(way);
							return true;
						}
					}
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * メンバーが一つもないRelation を削除する
	 * メンバーが一つしかないRelation:multipolygon と polygon:member を削除する
	 */
	public void garbage() {
		while (relationRemove());
	}
	
    // メンバーが一つしかないRelation:building を削除する
	// メンバーが一つしかないRelation:multipolygon と polygon:member を削除する
	boolean relationRemove() {
		for (ElementRelation relation : osm.relationMap.values()) {
			int memberCnt = relation.members.size();
			if (memberCnt == 0) {
				preDeleteMembers(relation.getId());
				osm.relationMap.remove(relation.getIdstr());
				return true;
			}
			else if (memberCnt == 1) {
				if (relation.isMultipolygon()) {
					for (MemberBean member : relation.members) {
						ElementWay way = (ElementWay)osm.getWayMap().get(member.getRef());
						if (way != null) {
							way.setMemberWay(true);
							relation.removeMember(way.getId());
							osm.removeWay(way);
							return true;
						}
					}
				}
				else if (relation.isBuilding()) {
					for (MemberBean member : relation.members) {
						ElementWay way = (ElementWay)osm.getWayMap().get(member.getRef());
						if (way != null) {
							way.setMemberWay(false);
							way.copyTag(relation.getTagList());
							relation.removeMember(way.getId());
							return true;
						}
					}
				}
			}
			else if (memberCnt == 2) {
				if (relation.isBuilding()) {
					int partCnt = 0;
					for (MemberBean member : relation.members) {
						if (member.getRole().equals("part")) {
							partCnt++;
						}
					}
					if (partCnt <= 1) {
						List<MemberBean> removeMembers = new ArrayList<>();
						for (MemberBean member : relation.members) {
							if (member.getRole().equals("part")) {
								if (member.isRelation()) {
									ElementRelation childRelation = osm.relationMap.get(member.getRef());
									if (childRelation != null) {
										childRelation.toBuilding();
										removeMembers.add(member);
									}
								}
								else if (member.isWay()) {
									ElementWay way = (ElementWay)osm.getWayMap().get(member.getRef());
									if (way != null) {
										way.toBuilding();
										removeMembers.add(member);
									}
								}
							}
							else if (member.getRole().equals("outline")) {
								if (member.isRelation()) {
									// "outline"がリレーションになることはない
								}
								else if (member.isWay()) {
									ElementWay way = (ElementWay)osm.getWayMap().get(member.getRef());
									if (way != null) {
										way.setMemberWay(false);
										osm.getWayMap().remove(way.getId());
										removeMembers.add(member);
									}
								}
							}
						}
						for (MemberBean mem : removeMembers) {
							relation.removeMember(mem.getRef());
						}
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * IDをリレーションのメンバーから外す
	 * @param osm
	 * @param id
	 */
	private void preDeleteMembers(long id) {
		while(preDeleteMember(id));
	}
	private boolean preDeleteMember(long id) {
		for (String relationid : osm.relationMap.keySet()) {
			ElementRelation relation = osm.relationMap.get(relationid);
			for (MemberBean member : relation.members) {
				if (member.getRef() == id) {
					relation.removeMember(id);
					return true;
				}
			}
		}
		return false;
	}
}
