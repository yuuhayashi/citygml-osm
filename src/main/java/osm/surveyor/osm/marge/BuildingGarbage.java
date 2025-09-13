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
						if (member.isRelation()) {
							ElementRelation childRelation = osm.relationMap.get(member.getRef());
							if (childRelation != null) {
								childRelation.toBuilding();
								relation.removeMember(childRelation.getId());
								return true;
							}
						}
						else if (member.isWay()) {
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
			}
			else if (memberCnt == 2) {
				if (relation.isBuilding()) {
					int partCnt = 0;
					MemberBean outlineMember = null;
					MemberBean partMember = null;
					for (MemberBean member : relation.members) {
						if (member.getRole().equals("part")) {
							partCnt++;
							partMember = member;
						}
						else if (member.getRole().equals("outline")) {
							outlineMember = member;
						}
					}
					if (partCnt <= 1) {
						MemberBean removeMember = null;
						if (partMember != null) {
							if (partMember.isRelation()) {
								ElementRelation childRelation = osm.relationMap.get(partMember.getRef());
								if ((childRelation != null) && (childRelation.members.size() > 1)) {
									childRelation.toBuilding();
									removeMember = outlineMember;
									outlineMember = null;
								}
							}
							else if (partMember.isWay()) {
								ElementWay way = (ElementWay)osm.getWayMap().get(partMember.getRef());
								if (way != null) {
									way.toBuilding();
									removeMember = partMember;
								}
							}
						}
						if (outlineMember != null) {
							if (outlineMember.isRelation()) {
								// "outline"がリレーションになることはない
							}
							else if (outlineMember.isWay()) {
								ElementWay way = (ElementWay)osm.getWayMap().get(outlineMember.getRef());
								if (way != null) {
									removeMember = outlineMember;
								}
							}
						}
						if (removeMember != null) {
							if (removeMember.isRelation()) {
								ElementRelation childRelation = osm.relationMap.get(removeMember.getRef());
								List<MemberBean> removeMembers = new ArrayList<>();
								for (MemberBean childMember : childRelation.members) {
									ElementWay way = (ElementWay)osm.getWayMap().get(childMember.getRef());
									if (way != null) {
										way.setMemberWay(false);
										osm.getWayMap().remove(way.getId());
									}
									removeMembers.add(childMember);
								}
								for (MemberBean childMember : childRelation.members) {
									relation.removeMember(childMember.getRef());
								}
								return true;
							}
							else if (removeMember.isWay()) {
								ElementWay way = (ElementWay)osm.getWayMap().get(removeMember.getRef());
								if (way != null) {
									way.setMemberWay(false);
									osm.getWayMap().remove(way.getId());
									relation.removeMember(removeMember.getRef());
									return true;
								}
							}
						}
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
