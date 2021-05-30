package osm.surveyor.osm.marge;

import osm.surveyor.osm.ElementMember;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.RelationMap;
import osm.surveyor.osm.RelationMultipolygon;
import osm.surveyor.osm.WayMap;

public class RelationMarge {
	OsmDom osm;
	
	public RelationMarge(OsmDom osm) {
		this.osm = osm;
	}

    /**
     * 各WAYのノードで、他のWAYと共有されたノードを探す
     * 接触しているBUILDINGのWAYをくっつけて"Relation:building"をつくる
     * Relation:multipolygon の MaxHeightを outline->Multipolygonへ設定する
     * OsmDom osm
     */
	public void relationMarge() {
		RelationMap checked = new RelationMap();
		
		// 接触しているBUILDINGのWAYをくっつけて"Relation:building"をつくる
		while(relationMarge1(checked));
		
		// "ele"と"height"を統合してリレーションに設定する
		// "building:levels"と"building:levels:underground"を統合してリレーションに設定する
		margeTagValue(checked);
	}

	boolean relationMarge1(RelationMap checked) {
		for (String rKey : osm.relations.keySet()) {
			ElementRelation relation = osm.relations.get(rKey);
			if (relation.isBuilding()) {
				if (checked.get(relation.id) == null) {
					// relationは、未チェック
					for (ElementMember member : relation.members) {
						if (member.role.equals("part")) {
							String memberRef = Long.toString(member.ref);
							ElementWay way = osm.ways.get(memberRef);
							ElementRelation destRelation = null;
							if ((destRelation = checkParts(checked, way)) != null) {
								way.member = true;
								
								String maxname = destRelation.getTagValue("name");
								if (maxname == null) {
									maxname = "";
								}
								String name = way.getTagValue("name");
								if ((name != null) && (name.length() > maxname.length())) {
									if (!name.isEmpty()) {
										destRelation.addTag("name", name);
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

	/**
	 * "ele"と"height"を統合してリレーションに設定する
	 * "building:levels"と"building:levels:underground"を統合してリレーションに設定する
	 * @param relations
	 */
	void margeTagValue(RelationMap relations) {
		for (String key : relations.keySet()) {
			ElementRelation relation = relations.get(key);
			
			// 'height' and 'ele'
			String minele = relation.getMinValue(osm.ways, "ele");
			String maxele = null;
			for (ElementMember member : relation.members) {
				if (member.type.equals("way")) {
					ElementWay way = osm.ways.get(member.ref);
					String height = calcHeight(minele, way.getTagValue("ele"), way.getTagValue("height"));
					if (height != null) {
						if (maxele == null) {
							maxele = height;
						}
						else {
							if (Double.parseDouble(maxele) < Double.parseDouble(height)) {
								maxele = height;
							}
						}
					}
				}
			}
			if (maxele != null) {
				relation.addTag("height", maxele);
			}
			if (minele != null) {
				relation.addTag("ele", minele);
			}
			
			// 用途
			ElementWay maxway = relation.getMaxArea(osm.ways);
			if (maxway != null) {
				relation.addTag("building", maxway.getTagValue("building"));
			}
			
			// 建築年
			String minyear = relation.getMinValue(osm.ways, "start_date");
			if (minyear != null) {
				relation.addTag("start_date", minyear);
			}

			// 地上階
			String maxup = relation.getMaxValue(osm.ways, "building:levels");
			if (maxup != null) {
				relation.addTag("building:levels", maxup);
			}

			// 地下階
			String maxdown = relation.getMaxValue(osm.ways, "building:levels:underground");
			if (maxup != null) {
				relation.addTag("building:levels:underground", maxdown);
			}
		}
	}
	
	String calcHeight(String minele, String ele, String hi) {
		if (hi == null) {
			return null;
		}
		else {
			if (minele == null) {
				return hi;
			}
			else {
				if (ele == null) {
					return hi;
				}
				else {
					double h = Double.parseDouble(ele) - Double.parseDouble(minele) + Double.parseDouble(hi);
					return Double.toString(h);
				}
			}
		}
	}

	
	private ElementRelation checkParts(RelationMap checked, ElementWay way) {
		for (String relationid : checked.keySet()) {
			ElementRelation relation = checked.get(relationid);
			for (ElementMember member : relation.members) {
				if (member.role.equals("part")) {
					String memberRef = Long.toString(member.ref);
					ElementWay memberway = osm.ways.get(memberRef);
					WayMap ways = new WayMap();
					ways.put(way);
					ways.put(memberway);
					
					if ((new MargeFactory(osm, ways)).isDuplicateSegment()) {
						return relation;
					}
				}
			}
		}
		return null;
	}
	
	private ElementMember getPolygonMember(ElementRelation relation) {
		for (ElementMember member : relation.members) {
			if (member.role.equals("outline")) {
				return member;
			}
		}
		return null;
	}
	
}
