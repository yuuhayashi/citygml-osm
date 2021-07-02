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
					RelationMap map = new RelationMap();
					for (ElementMember member : relation.members) {
						if (member.role.equals("part") && member.type.equals("way")) {
							String memberRef = Long.toString(member.ref);
							ElementWay way = osm.ways.get(memberRef);
							way.member = true;

							// destRelation <-- checkedの中からwayに接続するリレーションを取得
							RelationMap duplicateMap = checkParts(checked, way);
							for (String key : duplicateMap.keySet()) {
								map.put(duplicateMap.get(key));
							}
						}
					}

					// destRelation <-- checkedの中からwayに接続するリレーションを取得
					for (String key : map.keySet()) {
						ElementRelation destRelation = map.get(key);
						
						// 接続するリレーションのすべてのメンバーを取り込む
						for (ElementMember mem : destRelation.members) {
							if (mem.type.equals("way")) {
								ElementWay memway = osm.ways.get(Long.toString(mem.ref));
								relation.addMember(memway, mem.role);
							}
							else {
								//ElementRelation memway = osm.relations.get(Long.toString(mem.ref));
								//relation.addMember(memway, mem.role);
							}
						}

						checked.remove(Long.toString(destRelation.id));
					}
				}
			}
			checked.put(relation);
		}
		
		// 'osm.relations'からcheckedのリレーションを取り除く
		
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
			
			// 'source='
			relation.addTag("source", osm.getSource());
			
			// 'height' and 'ele' and 'name'
			String maxname = relation.getTagValue("name");
			if (maxname == null) {
				maxname = "";
			}
			String minele = relation.getMinValue(osm.ways, "ele");
			String maxele = null;
			for (ElementMember member : relation.members) {
				if (member.type.equals("way")) {
					// 'height' and 'ele'
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
					
					// 'name='
					String name = way.getTagValue("name");
					if ((name != null) && (name.length() > maxname.length())) {
						maxname = name;
					}
				}
			}
			if (maxele != null) {
				relation.addTag("height", maxele);
			}
			if (minele != null) {
				relation.addTag("ele", minele);
			}
			if (!maxname.isEmpty()) {
				relation.addTag("name", maxname);
			}
			
			// 用途
			ElementWay maxway = relation.getMaxArea(osm.ways);
			if (maxway != null) {
				relation.addTag("building", maxway.getTagValue("building:part"));
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

	/**
	 * リレーションMAPの中から指定したwayに接続するリレーションを取得する
	 * @param checked	調査対象のリレーションリスト
	 * @param way	指定のWAY
	 * @return	接続するリレーションがない場合はNULL
	 */
	private RelationMap checkParts(RelationMap checked, ElementWay way) {
		RelationMap ret = new RelationMap();
		for (String relationid : checked.keySet()) {
			ElementRelation relation = checked.get(relationid);
			for (ElementMember member : relation.members) {
				if (member.role.equals("part") && member.type.equals("way")) {
					WayMap ways = new WayMap();
					ways.put(way);
					ways.put(osm.ways.get(Long.toString(member.ref)));
					if ((new MargeFactory(osm, ways)).isDuplicateSegment()) {
						ret.put(relation);
					}
				}
			}
		}
		return ret;
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
