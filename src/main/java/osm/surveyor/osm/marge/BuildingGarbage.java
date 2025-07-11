package osm.surveyor.osm.marge;

import java.util.List;

import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.PoiBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.TagBean;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class BuildingGarbage {
	OsmDom osm;
	
	public BuildingGarbage(OsmDom osm) {
		this.osm = osm;
	}

	/**
	 * メンバーが一つしかないRelation:building を削除する
	 * @param relations
	 * @param ways
	 */
	public void garbage() {
		while (relationRemove());
	}
	
    // メンバーが一つしかないRelation:building を削除する
	// メンバーが一つしかないRelation:multipolygon と polygon:member を削除する
	boolean relationRemove() {
		for (String rKey : osm.relations.keySet()) {
			ElementRelation relation = osm.relations.get(rKey);
			int memberCnt = relation.members.size();
			if (memberCnt == 0) {
				preDeleteMembers(Long.parseLong(rKey));
				osm.relations.remove(rKey);
				return true;
			}
			else if (memberCnt == 1) {
				if (relation.isMultipolygon()) {
					for (MemberBean member : relation.members) {
						ElementWay way = (ElementWay)osm.ways.get(member.getRef());
						if (way != null) {
							way.member = true;
							relation.removeMember(way.getId());
							osm.ways.remove(way);
							return true;
						}
					}
				}
				if (relation.isBuilding()) {
					for (MemberBean member : relation.members) {
						ElementWay way = (ElementWay)osm.ways.get(member.getRef());
						if (way != null) {
							way.member = false;
							copyTag(relation.getTagList(), way);
							relation.removeMember(way.getId());
							return true;
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
		for (String relationid : osm.relations.keySet()) {
			ElementRelation relation = osm.relations.get(relationid);
			for (MemberBean member : relation.members) {
				if (member.getRef() == id) {
					relation.removeMember(id);
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * タグをdestへ移す
	 * 	- "type"はコピーしない
	 *  - コピー先に"building:part=*"が存在すれば"building=*"とする
	 * 	- コピー先とコピー元に"bilding"が存在しなければ"building=yes"を補完する
	 * 	- "addr"と"addr:*"はコピーしない
	 * 	- "height"はコピーしない
	 * 	- "ele"はコピーしない
	 * 	- "source"と"source:*"はコピーしない
	 * 	- "ref"と"ref:*"はコピーしない
	 * @param tags
	 * @param dest
	 */
	void copyTag(List<TagBean> tags, PoiBean dest) {
		if (tags == null) {
			return;
		}
		String building = "yes";
		TagBean buildingPartTag = dest.getTag("building:part");
		if (buildingPartTag != null) {
			building = buildingPartTag.v;
			dest.removeTag("building:part");
		}
		for (TagBean tag : tags) {
			if (tag.k.equals("type")) {
			}
			else if (tag.k.equals("building:part")) {
				building = tag.v;
			}
			else if (tag.k.equals("building")) {
				building = tag.v;
			}
			else if (tag.k.equals("addr")) {
			}
			else if (tag.k.startsWith("addr:*")) {
			}
			else if (tag.k.equals("source")) {
			}
			else if (tag.k.startsWith("source:")) {
			}
			else if (tag.k.equals("ref")) {
			}
			else if (tag.k.startsWith("ref:*")) {
			}
			else if (tag.k.equals("height")) {
			}
			else if (tag.k.equals("ele")) {
			}
			else {
				dest.addTag(tag.k, tag.v);
			}
		}
		String buildingTag = dest.getTagValue("building");
		if (buildingTag == null) {
			dest.addTag("building", building);
		}
	}
}
