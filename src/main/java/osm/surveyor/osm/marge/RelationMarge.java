package osm.surveyor.osm.marge;

import java.util.HashMap;

import osm.surveyor.osm.ElementMember;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementTag;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.OsmNd;
import osm.surveyor.osm.RelationMultipolygon;

public class RelationMarge {

    /**
     * 各WAYのノードで、他のWAYと共有されたノードを探す
     * 接触しているBUILDINGのWAYをくっつけて"Relation:building"をつくる
     * Relation:multipolygon の MaxHeightを outline->Multipolygonへ設定する
     * OsmDom osm
     */
	public static void relationMarge(OsmDom osm) {
		// 接触しているBUILDINGのWAYをくっつけて"Relation:building"をつくる
		while(!relationMarge1(osm));
	}

	static boolean relationMarge1(OsmDom osm) {
		HashMap<String, String> ndMap = new HashMap<>();	// ndMap(key= node.id, v= relation.id)
		
		for (String rKey : osm.relations.keySet()) {
			ElementRelation relation = osm.relations.get(rKey);
			ElementTag typeTag = relation.tags.get("type");
			if ((typeTag == null) || !typeTag.v.equals("building")) {
				continue;
			}
			ElementMember polygonMember = null;
			for (ElementMember member : relation.members) {
				if (member.role.equals("outline")) {
					polygonMember = member;
					break;
				}
			}
			String maxheight = "0";
			String maxname = "";
			for (ElementMember member : relation.members) {
				if (!member.role.equals("part")) {
					continue;
				}
				String memberRef = Long.toString(member.ref);
				ElementWay way = osm.ways.get(memberRef);
				for (OsmNd node : way.nds) {
					String ndId = Long.toString(node.id);
					if (ndMap.containsKey(ndId)) {
						/* 'node :OsmNd'は、他のリレーションのWAYノードと共有している */
						// カレントリレーションのメンバーを共有先リレーション(v)へ追加
						String v = ndMap.get(ndId);	// 共有先のrelation.idstr
						ElementRelation destRelation = osm.relations.get(v);
						if (!relation.getIdstr().equals(v)) {
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

							if (polygonMember != null) {
								RelationMultipolygon polygon = (RelationMultipolygon)osm.relations.get(Long.toString(polygonMember.ref));
								destRelation.addMember(polygon, "outline");
								i = relation.members.indexOf(polygonMember);	// カレントリレーションからメンバーを削除
								relation.members.remove(i);
							}
							
							return false;
						}
					}
					else {
						ndMap.put(ndId, relation.getIdstr());
					}
				}
			}
		}
		return true;
	}
	
}
