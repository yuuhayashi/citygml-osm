package osm.surveyor.osm;

import java.util.List;

import osm.surveyor.citygml.CityModelParser;

public class OsmMargeWay {

	/**
	 * "role->outline->Relation"を取得する
	 */
	static RelationMultipolygon getOutline(ElementRelation relation, OsmDom osm) {
		for (MemberBean member : relation.members) {
			if (member.getRole().equals("outline") && member.getType().equals("relation")) {
				return (RelationMultipolygon)osm.relations.get(Long.toString(member.getRef()));
			}
		}
		return null;
	}
	
	static String getHeight(ElementWay way) {
		TagBean tag = way.getTag("height");
		if (tag == null) {
			return null;
		}
		return CityModelParser.rounding(2, String.valueOf(tag.v));
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
			if (partRemove(osm, relation)) {
				return true;
			}
		}
		return false;
	}
	
	static boolean partRemove(OsmDom osm, ElementRelation relation) {
		if (relation.isMultipolygon()) {
			return false;
		}
		WayMap parts = new WayMap();
		RelationMap polygons = new RelationMap();
		for (MemberBean plgMem : relation.members) {
			if (plgMem.getRole().equals("part") && plgMem.getType().equals("way")) {
				parts.put(osm.ways.get(Long.toString(plgMem.getRef())));
			}
			else if (plgMem.getRole().equals("outline") && plgMem.getType().equals("relation")) {
				polygons.put(osm.relations.get(Long.toString(plgMem.getRef())));
			}
		}
		
		for (String wayid : parts.keySet()) {
			ElementWay partWay = (ElementWay)parts.get(wayid);
			for (String outerid : polygons.keySet()) {
				RelationMultipolygon polygon = (RelationMultipolygon)polygons.get(outerid);
				for (MemberBean plgMem : polygon.members) {
					if (plgMem.getRole().equals("outer") && plgMem.getType().equals("way")) {
						ElementWay outer = (ElementWay)osm.ways.get(Long.toString(plgMem.getRef()));
						if (partWay.isSame(outer)) {
							TagBean ele = partWay.getTag("height");
							if (ele != null) {
								polygon.addTag("height", CityModelParser.rounding(2, String.valueOf(ele.v)));
							}
							relation.removeMember(partWay.getId());
							osm.ways.remove(partWay);
							return true;
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
				for (MemberBean member : relation.members) {
					if (member.getRole().equals("outline") && member.getType().equals("relation")) {
						polygon = (RelationMultipolygon)osm.relations.get(Long.toString(member.getRef()));
						for (MemberBean plgMem : polygon.members) {
							if (plgMem.getRole().equals("outer") && plgMem.getType().equals("way")) {
								outline = (ElementWay)osm.ways.get(Long.toString(plgMem.getRef()));
								break;
							}
						}
					}
				}
				if (outline != null) {
					copyTag(relation.getTagList(), polygon);
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
	static void copyTag(List<TagBean> tags, PoiBean dest) {
		if (tags == null) {
			return;
		}
		TagBean buildingPartTag = dest.getTag("building:part");
		if (buildingPartTag != null) {
			dest.removeTag("building:part");
			dest.addTag("building", buildingPartTag.v);
		}
		for (TagBean tag : tags) {
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
				dest.addTag(tag.k, tag.v);
			}
		}
		TagBean buildingTag = dest.getTag("building");
		if (buildingTag == null) {
			dest.addTag("building", "yes");
		}
	}
}
