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
	public boolean relationMarge() {
		RelationMap checked = new RelationMap();
		
		// 接触しているBUILDINGのWAYをくっつけて"Relation:building"をつくる
		for (String rKey : osm.relations.keySet()) {
			ElementRelation relation = osm.relations.get(rKey);
			if (relation.isBuilding()) {
				RelationMap marged = new RelationMap();
				marged = relationMarge1((RelationBuilding)relation, checked);
				for (String key : marged.keySet()) {
					RelationBuilding del = (RelationBuilding)osm.relations.get(key);
					osm.ways.remove(del.getOutlineWay(osm));
					osm.relations.remove(del.getMultiPolygon(osm));
					osm.relations.remove(del);
					return true;
				}
				checked.put(relation);
			}
		}
		return false;
	}

	/**
	 * 接触しているBUILDINGのWAYをくっつけて"Relation:building"をつくる
	 * @param relation
	 * @param checked
	 * @return	マージされて取り込まれたリレーションのリストを返す（呼び出し側で削除する必要がある）
	 */
	RelationMap relationMarge1(RelationBuilding relation, RelationMap checked) {
		RelationMap marged = new RelationMap();
		if (checked.get(relation.id) != null) {
			return marged;	// リレーションが処理済みなら何もしない
		}
		
		// outline:WAYに接触するbuildingを抽出
		RelationBuilding margedBuilding = null;
		while ((margedBuilding = checkParts(checked, relation)) != null) {
			checked.remove(margedBuilding);
			marged.put(margedBuilding);
		}
		return marged;
	}
	
	/**
	 * リレーションMAPの中から指定したBuildingに接続するリレーションを取得する
	 * @param checked	調査対象のリレーションリスト
	 * @param source	指定のBuilding
	 * @return	接続するリレーションがない場合はNULL
	 */
	private RelationBuilding checkParts(RelationMap checked, RelationBuilding source) {
		for (String relationid : checked.keySet()) {
			RelationBuilding relation = (RelationBuilding)checked.get(relationid);
			WayMap ways = new WayMap();
			ways.put(source.getOutlineWay(osm));
			ways.put(relation.getOutlineWay(osm));
			if ((new MargeFactory(osm, ways)).isDuplicateSegment()) {
				source = matomeru(source, relation);
				return relation;
			}
		}
		return null;
	}
		
	RelationBuilding matomeru(RelationBuilding relation, RelationBuilding b) {
		// "ele"と"height"を統合してリレーションに設定する
		// "building:levels"と"building:levels:underground"を統合してリレーションに設定する
		RelationMap map = new RelationMap();
		map.put(relation);
		map.put(b);
		margeTagValue(map);
		
		// 接続するリレーションのメンバーを取り込む
		// Wayメンバーは全て取り込む
		// RelationメンバーはInnerのみ取り込む。Outerは除外する
		ElementRelation multi = relation.getMultiPolygon(osm);
		for (ElementMember mem : b.members) {
			if (mem.role.equals("part")) {
				if (mem.type.equals("way")) {
					ElementWay memway = osm.ways.get(Long.toString(mem.ref));
					relation.addMember(memway, mem.role);
				}
			}
			else if (mem.role.equals("outline")) {
				if (mem.type.equals(ElementRelation.RELATION)) {
					ElementRelation polygon = osm.relations.get(Long.toString(mem.ref));
					if (polygon != null) {
						for (ElementMember polymem : polygon.members) {
							if (polymem.type.equals("way") && polymem.role.equals("inner")) {
								if (multi != null) {
									multi.addMember(osm.relations.get(Long.toString(polymem.ref)), "inner");
								}
							}
						}
					}
				}
			}
		}
		relation = (new OutlineFactory(osm)).createOutline(relation);
		return relation;
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
}
