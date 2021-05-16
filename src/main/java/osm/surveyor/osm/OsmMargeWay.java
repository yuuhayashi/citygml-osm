package osm.surveyor.osm;

import java.util.ArrayList;
import java.util.HashMap;

public class OsmMargeWay {

	/**
	 * "role->outline->Relation"を取得する
	 */
	static RelationMultipolygon getOutline(ElementRelation relation, OsmDom osm) {
		for (ElementMember member : relation.members) {
			if (member.role.equals("outline") && member.type.equals("relation")) {
				return (RelationMultipolygon)osm.relations.get(Long.toString(member.ref));
			}
		}
		return null;
	}
	
	/*
	 * 
	static String getMaxHeight(ElementRelation relation, OsmDom osm) {
		String maxheight = "0";
		for (ElementMember member : relation.members) {
			if (member.role.equals("part")) {
				ElementWay way = osm.ways.get(Long.toString(member.ref));
				String ele = getHeight(way);
				if (ele != null) {
					if (Double.parseDouble(ele) > Double.parseDouble(maxheight)) {
						maxheight = ele;
					}
				}
			}
		}
		return maxheight;
	}
	 */
	
	static String getHeight(ElementWay way) {
		ElementTag tag = way.tags.get("height");
		if (tag == null) {
			return null;
		}
		return tag.v;
		
	}
	
	
	/**
	 * "outline"と"part"が重複している`part` を削除する
	 * @param relations
	 * @param ways
	 */
	public static OsmDom partGabegi(OsmDom osm) {
		while (partRemove(osm));
		while (outlineRemove(osm));
		return osm;
	}
	
	static boolean partRemove(OsmDom osm) {
		for (String rKey : osm.relations.keySet()) {
			ElementRelation relation = osm.relations.get(rKey);
			RelationMultipolygon polygon = null;
			ElementWay outer = null;
			if (relation.isMultipolygon()) {
				polygon = (RelationMultipolygon)osm.relations.get(rKey);
				for (ElementMember plgMem : polygon.members) {
					if (plgMem.role.equals("outer") && plgMem.type.equals("way")) {
						outer = osm.ways.get(Long.toString(plgMem.ref));
						break;
					}
				}
			}

			if (outer != null) {
				for (String wayid : osm.ways.keySet()) {
					ElementWay partWay = osm.ways.get(wayid);
					if ((partWay != null) && (outer != null)) {
						if (partWay.id != outer.id) {
							if (partWay.isSame(outer)) {
								ElementTag ele = partWay.tags.get("height");
								if (ele != null) {
									polygon.addTag("height", ele.v);
								}
								ArrayList<ElementRelation> list = osm.getParents(partWay);
								for (ElementRelation parent : list) {
									if (parent.isBuilding()) {
										parent.removeMember(partWay.id);
										if (parent.members.isEmpty()) {
											copyTag(parent.tags, polygon);
											osm.relations.remove(parent.getIdstr());
										}
									}
								}
								osm.ways.remove(partWay);
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * "outline"しかない"building:part"を削除する
	 * @param relations
	 * @param ways
	 * @return
	 */
	static boolean outlineRemove(OsmDom osm) {
		for (String rKey : osm.relations.keySet()) {
			ElementRelation relation = osm.relations.get(rKey);
			RelationMultipolygon polygon = null;
			ElementWay outline = null;
			if (relation.members.size() == 1) {
				for (ElementMember member : relation.members) {
					if (member.role.equals("outline") && member.type.equals("relation")) {
						polygon = (RelationMultipolygon)osm.relations.get(Long.toString(member.ref));
						for (ElementMember plgMem : polygon.members) {
							if (plgMem.role.equals("outer") && plgMem.type.equals("way")) {
								outline = osm.ways.get(Long.toString(plgMem.ref));
								break;
							}
						}
					}
				}
				if (outline != null) {
					copyTag(relation.tags, polygon);
					osm.relations.remove(relation.getIdstr());
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
	 * 	- "height"はコピーしない
	 * 	- "ele"はコピーしない
	 * 	- "source"はコピーしない
	 * @param tags
	 * @param dest
	 */
	static void copyTag(HashMap<String, ElementTag> tags, ElementOsmapi dest) {
		if (tags == null) {
			return;
		}
		ElementTag buildingPartTag = dest.tags.get("building:part");
		if (buildingPartTag != null) {
			dest.tags.remove("building:part");
			dest.addTag("building", buildingPartTag.v);
		}
		for (String key : tags.keySet()) {
			ElementTag tag = tags.get(key);
			if (tag.k.equals("type")) {
			}
			else if (tag.k.equals("building:part")) {
			}
			else if (tag.k.equals("addr:full")) {
			}
			else if (tag.k.equals("addr:ref")) {
			}
			else if (tag.k.equals("source")) {
			}
			else if (tag.k.equals("height")) {
			}
			else if (tag.k.equals("ele")) {
			}
			else {
				dest.addTag(key, tag.v);
			}
		}
		ElementTag buildingTag = dest.tags.get("building");
		if (buildingTag == null) {
			dest.addTag("building", "yes");
		}
	}
}
