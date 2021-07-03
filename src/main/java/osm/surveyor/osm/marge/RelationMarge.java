package osm.surveyor.osm.marge;

import osm.surveyor.osm.ElementMember;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.RelationBuilding;
import osm.surveyor.osm.RelationMap;
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
		for (String rKey : osm.relations.keySet()) {
			ElementRelation relation = osm.relations.get(rKey);
			if (relation.isBuilding()) {
				checked = relationMarge1((RelationBuilding)relation, checked);
			}
		}

		// 'osm.relations'からcheckedのリレーションを取り除く
		for (String rKey : checked.keySet()) {
			osm.relations.remove(checked.get(rKey));
		}
		osm.relations.clear();
		
		// "ele"と"height"を統合してリレーションに設定する
		// "building:levels"と"building:levels:underground"を統合してリレーションに設定する
		margeTagValue(checked);

		// 'osm.relations'にcheckedの内容をセットする
		for (String rKey : checked.keySet()) {
			osm.relations.put(checked.get(rKey));
		}
	}

	/**
	 * 接触しているBUILDINGのWAYをくっつけて"Relation:building"をつくる
	 * @param relation
	 * @param checked
	 * @return
	 */
	RelationMap relationMarge1(RelationBuilding relation, RelationMap checked) {
		// 未チェックのrelationを処理する
		if (checked.get(relation.id) == null) {
			// map = checkedの中から[relation.member->way]に接続するリレーションを取得
			RelationMap map = new RelationMap();
			ElementWay outer = getOuterWay(relation);
			if (outer != null) {
				outer.member = true;

				RelationMap duplicateMap = checkParts(checked, outer);
				for (String key : duplicateMap.keySet()) {
					map.put(duplicateMap.get(key));
					checked.remove(duplicateMap.get(key));
				}
			}

			map.put(relation);
			ElementRelation duplicate = matomeru(map);
			if (duplicate != null) {
				checked.put(getMultiPolygon(duplicate));
				checked.put(duplicate);
			}
			else {
				checked.put(getMultiPolygon(relation));
				checked.put(relation);
			}
		}
		return checked;
	}
	
	RelationBuilding matomeru(RelationMap map) {
		margeTagValue(map);
		
		RelationBuilding ret = null;
		for (String key : map.keySet()) {
			RelationBuilding relation = (RelationBuilding)map.get(key);
			if (ret == null) {
				ret = relation;
			}
			else {
				// 接続するリレーションのすべてのメンバーを取り込む
				for (ElementMember mem : relation.members) {
					if (mem.type.equals("way")) {
						ElementWay memway = osm.ways.get(Long.toString(mem.ref));
						ret.addMember(memway, mem.role);
					}
				}
				ret = (new OutlineFactory(osm)).createOutline(ret);
			}
		}
		
		return ret;
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
	
	/**
	 * 指定されたリレーションの"outline"メンバーを取得する
	 * 前提： "outline"メンバーは一つにまとめられていること
	 * @param relation	指定のリレーション
	 * @return	"outline"がないときはNULL
	 */
	private ElementMember getOutlineMember(ElementRelation relation) {
		for (ElementMember member : relation.members) {
			if (member.role.equals("outline")) {
				return member;
			}
		}
		return null;
	}
	
	private ElementRelation getMultiPolygon(ElementRelation relation) {
		ElementMember outlineMember = getOutlineMember(relation);
		if (outlineMember != null) {
			return osm.relations.get(outlineMember.ref);
		}
		return null;
	}
	
	private ElementWay getOuterWay(ElementRelation relation) {
		ElementRelation outline = getMultiPolygon(relation);
		if (outline != null) {
			for (ElementMember outlinemember : outline.members) {
				if (outlinemember.role.equals("outer")) {
					ElementWay outer = osm.ways.get(outlinemember.ref);
					return outer;
				}
			}
		}
		return null;
	}
	
}
