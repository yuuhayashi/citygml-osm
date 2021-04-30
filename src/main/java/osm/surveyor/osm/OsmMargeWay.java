package osm.surveyor.osm;

import java.util.HashMap;

public class OsmMargeWay {

    /**
     * 各WAYのノードで、他のWAYと共有されたノードを探す
     * 接触しているBUILDINGのWAYをくっつけて"Relation:building"をつくる
     * Relation:multipolygon の MaxHeightを outline->Multipolygonへ設定する
     * OsmDom osm
     */
	public static void relationMarge(OsmDom osm) {
		// 接触しているBUILDINGのWAYをくっつけて"Relation:building"をつくる
		while(!relationMarge1(osm));
		
		// Relation:multipolygon の MaxHeightを outline->Multipolygonへ設定する
		//setHeightToOutline(osm);
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
	
	/**
	 * Relation:multipolygon の MaxHeightを outline->Multipolygonへ設定する
	public static void removeHeightFromOuter(OsmDom osm) {
		for (String rKey : osm.relations.keySet()) {
			ElementRelation relation = osm.relations.get(rKey);
			ElementTag typeTag = relation.tags.get("type");
			if ((typeTag != null) && typeTag.v.equals("multipolygon")) {
				for (ElementMember member : relation.members) {
					if (member.role.equals("outer") && member.type.equals("way")) {
						ElementWay way = osm.ways.get(Long.toString(member.ref));
						way.tags.remove("height");
					}
				}
			}
		}
	}
	 */
	
	/**
	 * Relation:multipolygon の MaxHeightを outline->Multipolygonへ設定する
	static void setHeightToOutline(OsmDom osm) {
		for (String rKey : osm.relations.keySet()) {
			ElementRelation building = osm.relations.get(rKey);
			ElementTag typeTag = building.tags.get("type");
			if ((typeTag != null) && typeTag.v.equals("building")) {
				String ele = getMaxHeight(building, osm);
				RelationMultipolygon polygon = getOutline(building, osm);
				if (polygon != null) {
					polygon.addTag("height", ele);
				}
			}
		}
	}
	 */
	
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
	
	static String getHeight(ElementWay way) {
		ElementTag tag = way.tags.get("height");
		if (tag == null) {
			return null;
		}
		return tag.v;
		
	}
	
	/**
	 * メンバーが一つしかないRelation:building を削除する
	 * @param relations
	 * @param ways
	 */
	public static void relationGabegi(OsmDom osm) {
		while (relationRemove(osm));
	}
	
	static boolean relationRemove(OsmDom osm) {
		for (String rKey : osm.relations.keySet()) {
			ElementRelation relation = osm.relations.get(rKey);
			if (relation.members.size() < 1) {
				osm.relations.remove(rKey);
				return true;
			}
			if (relation.members.size() < 2) {
				for (ElementMember member : relation.members) {
					String memberRef = Long.toString(member.ref);
					if (member.role.equals("part")) {
						ElementWay way = osm.ways.get(memberRef);
						way.member = true;
						copyTag(relation.tags, way);
						osm.relations.remove(rKey);
						return true;
					}
					if (member.role.equals("outline")) {
						RelationMultipolygon polygon = (RelationMultipolygon)osm.relations.get(memberRef);
						copyTag(relation.tags, polygon);
						osm.relations.remove(rKey);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * "outline"と"part"が重複している`part` を削除する
	 * @param relations
	 * @param ways
	 */
	public static void partGabegi(OsmDom osm) {
		while (partRemove(osm));
		while (outlineRemove(osm));
	}
	
	static boolean partRemove(OsmDom osm) {
		for (String rKey : osm.relations.keySet()) {
			ElementRelation relation = osm.relations.get(rKey);
			RelationMultipolygon polygon = null;
			ElementWay outline = null;
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
				for (ElementMember member : relation.members) {
					if (member.role.equals("part") && member.type.equals("way")) {
						ElementWay partWay = osm.ways.get(Long.toString(member.ref));
						if (partWay.isSame(outline)) {
							ElementTag ele = partWay.tags.get("height");
							if (ele != null) {
								//outline.addTag("height", ele.v);
								polygon.addTag("height", ele.v);
							}
							if (partWay.id != outline.id) {
								osm.ways.remove(partWay.getIdstr());
							}
							relation.members.remove(member);
							if (relation.members.isEmpty()) {
								copyTag(relation.tags, polygon);
								osm.relations.remove(relation.getIdstr());
							}
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
			else {
				dest.addTag(key, tag.v);
			}
		}
		ElementTag buildingTag = dest.tags.get("building");
		if (buildingTag == null) {
			dest.addTag("building", "yes");
		}
	}

	/**
	 * - Relation->member:role=port のoutlineを作成する
	 * - 
	 * 
	 * @param relations
	 */
	public static void relationOutline(OsmDom osm) {
		for (String rKey : osm.relations.keySet()) {
			ElementRelation relation = osm.relations.get(rKey);
			ElementWay aWay = relation.createOutline(osm.ways);
			if (aWay == null) {
				continue;
			}
			
			// Relationのメンバーから"height"の最大値を取得
			String polygonRef = null;
			String maxheight = "0";
			for (ElementMember mem : relation.members) {
				if (mem.type.equals("relation")) {
					RelationMultipolygon multi = (RelationMultipolygon)osm.relations.get(Long.toString(mem.ref));
					if (multi != null) {
						polygonRef = Long.toString(mem.ref);
					}
				}
				if (mem.type.equals("way")) {
					ElementWay way = osm.ways.get(Long.toString(mem.ref));
					ElementTag ele = way.tags.get("height");
					if (ele != null) {
						if (Double.parseDouble(maxheight) < Double.parseDouble(ele.v)) {
							maxheight = ele.v;
						}
					}
				}
			}

			// OUTLINEをWAYリストに登録
			aWay.member = true;
			//aWay.tags = new HashMap<String,ElementTag>();
			osm.ways.put(Long.toString(aWay.id), aWay);
			
			//aWay.addTag("source", osm.getSource());
			if (polygonRef != null) {
				// "outline"が存在する場合は、マルチポリゴンにaWayを追加する
				RelationMultipolygon multi = (RelationMultipolygon)osm.relations.get(polygonRef);
				if (multi != null) {
					aWay.tags.remove("building");
					aWay.tags.remove("addr:ref");
					aWay.tags.remove("addr:full");
					aWay.tags.remove("height");
					aWay.addTag("source", osm.getSource());
					multi.addMember(aWay, "outer");
					multi.addTag("height", maxheight);
					multi.addTag("source", osm.getSource());
				}
			}
			else {
				// "outline"が存在しない場合は、"bulding:Relation"にWAYをMEMBERとして追加する
				aWay.addTag("height", maxheight);
				relation.addMember(aWay, "outline");
			}
			relation.addTag("source", osm.getSource());
			relation.addTag("height", maxheight);
		}
	}
}
