package osm.surveyor.osm.marge;

import osm.surveyor.osm.ElementMember;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementTag;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.RelationMap;
import osm.surveyor.osm.RelationMultipolygon;
import osm.surveyor.osm.WayMap;

public class RelationMarge {

    /**
     * 各WAYのノードで、他のWAYと共有されたノードを探す
     * 接触しているBUILDINGのWAYをくっつけて"Relation:building"をつくる
     * Relation:multipolygon の MaxHeightを outline->Multipolygonへ設定する
     * OsmDom osm
     */
	public static void relationMarge(OsmDom osm) {
		RelationMap checked = new RelationMap();
		
		// 接触しているBUILDINGのWAYをくっつけて"Relation:building"をつくる
		while(relationMarge1(checked, osm));
	}

	static boolean relationMarge1(RelationMap checked, OsmDom osm) {
		for (String rKey : osm.relations.keySet()) {
			ElementRelation relation = osm.relations.get(rKey);
			if (relation.isBuilding()) {
				String maxheight = "0";
				String maxname = "";
				if (checked.get(relation.id) == null) {
					// relationは、未チェック
					for (ElementMember member : relation.members) {
						if (member.role.equals("part")) {
							String memberRef = Long.toString(member.ref);
							ElementWay way = osm.ways.get(memberRef);
							ElementRelation destRelation = null;
							if ((destRelation = checkParts(osm, checked, way)) != null) {
								way.member = true;
								String name = "";
								ElementTag height = way.tags.get("height");
								ElementTag nameTag = way.tags.get("name");
								if (Double.parseDouble(height.v) > Double.parseDouble(maxheight)) {
									maxheight = height.v;
									destRelation.addTag("height", maxheight);
								}
								if (nameTag != null) {
									name = nameTag.v;
								}
								if (name.length() > maxname.length()) {
									maxname = name;
									if (!maxname.isEmpty()) {
										destRelation.addTag("name", maxname);
									}
								}
								destRelation.addTag("source", osm.getSource());
								destRelation.addMember(way, "part");
								int i = relation.members.indexOf(member);	// カレントリレーションからメンバーを削除
								relation.members.remove(i);

								ElementMember polygonMember = getPolygonMember(relation);
								if (polygonMember != null) {
									RelationMultipolygon polygon = (RelationMultipolygon)osm.relations.get(Long.toString(polygonMember.ref));
									destRelation.addMember(polygon, "outline");
									i = relation.members.indexOf(polygonMember);	// カレントリレーションからメンバーを削除
									relation.members.remove(i);
								}
							}
							checked.put(relation);
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	private static ElementRelation checkParts(OsmDom osm, RelationMap checked, ElementWay way) {
		for (String relationid : checked.keySet()) {
			ElementRelation relation = checked.get(relationid);
			for (ElementMember member : relation.members) {
				if (member.role.equals("part")) {
					String memberRef = Long.toString(member.ref);
					ElementWay memberway = osm.ways.get(memberRef);
					WayMap ways = new WayMap();
					ways.put(way);
					ways.put(memberway);
					
					if ((new MargeFactory(ways)).isDuplicateSegment()) {
						return relation;
					}
				}
			}
		}
		return null;
	}
	
	private static ElementMember getPolygonMember(ElementRelation relation) {
		for (ElementMember member : relation.members) {
			if (member.role.equals("outline")) {
				return member;
			}
		}
		return null;
	}
	
}
