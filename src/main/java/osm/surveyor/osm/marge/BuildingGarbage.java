package osm.surveyor.osm.marge;

import java.util.HashMap;

import osm.surveyor.osm.ElementMember;
import osm.surveyor.osm.ElementOsmapi;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementTag;
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
					for (ElementMember member : relation.members) {
						ElementWay way = osm.ways.get(member.ref);
						if (way != null) {
							preDeleteMembers(way.id);
							osm.ways.remove(way);
							return true;
						}
					}
				}
				if (relation.isBuilding()) {
					for (ElementMember member : relation.members) {
						ElementWay way = osm.ways.get(member.ref);
						if (way != null) {
							way.member = true;
							copyTag(relation.tags, way);
							preDeleteMembers(Long.parseLong(rKey));
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
			for (ElementMember member : relation.members) {
				if (member.ref == id) {
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
	 * 	- "addr:ref"はコピーしない
	 * 	- "addr:full"はコピーしない
	 * 	- "source"はコピーしない
	 * @param tags
	 * @param dest
	 */
	void copyTag(HashMap<String, ElementTag> tags, ElementOsmapi dest) {
		if (tags == null) {
			return;
		}
		String building = "yes";
		ElementTag buildingPartTag = dest.tags.get("building:part");
		if (buildingPartTag != null) {
			building = buildingPartTag.v;
			dest.tags.remove("building:part");
		}
		for (String key : tags.keySet()) {
			ElementTag tag = tags.get(key);
			if (tag.k.equals("type")) {
			}
			else if (tag.k.equals("building:part")) {
				building = tag.v;
			}
			else if (tag.k.equals("building")) {
				building = tag.v;
			}
			else if (tag.k.equals("addr:full")) {
			}
			else if (tag.k.equals("addr:ref")) {
			}
			else if (tag.k.equals("source")) {
			}
			else if (tag.k.equals("height")) {
			}
			else {
				dest.addTag(key, tag.v);
			}
		}
		String buildingTag = dest.getTagValue("building");
		if (buildingTag == null) {
			dest.addTag("building", building);
		}
	}
}
