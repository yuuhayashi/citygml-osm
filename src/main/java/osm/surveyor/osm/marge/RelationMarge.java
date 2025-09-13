package osm.surveyor.osm.marge;

import java.util.List;

import osm.surveyor.osm.MemberBean;
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
		System.out.print(".");
		RelationMap checked = new RelationMap();	// 調査済みのリレーション一覧
		
		// 接触しているBUILDINGのWAYをくっつけて"Relation:building"をつくる
		for (String rKey : osm.relationMap.keySet()) {
			ElementRelation relation = osm.relationMap.get(rKey);
			if (relation.isBuilding()) {
				RelationMap marged = relationMarge1((RelationBuilding)relation, checked);
				if (marged.size() > 0) {
					for (String key : marged.keySet()) {
						RelationBuilding del = (RelationBuilding)osm.relationMap.get(key);
						del.setMarged(true);
						osm.removeWay(del.getOutlineWay(osm));
					
						osm.relationMap.remove(del.getMultiPolygon(osm));
						osm.relationMap.remove(del);
						return true;
					}
				}
				else {
					relation.setComplete(true);
					checked.put(relation);
				}
			}
		}
		return false;
	}

	/**
	 * 接触しているBUILDINGのWAYをくっつけて"Relation:building"をつくる
	 * @param relation
	 * @param checked	調査済みのリレーション一覧
	 * @return	マージされて取り込まれたリレーションのリストを返す（呼び出し側で削除する必要がある）
	 */
	RelationMap relationMarge1(RelationBuilding relation, RelationMap checked) {
		RelationMap marged = new RelationMap();
		if (checked.get(relation.getId()) == null) {
			// outline:WAYに接触するbuildingを抽出
			RelationBuilding margedBuilding = null;
			while ((margedBuilding = checkParts(checked, relation)) != null) {
				checked.remove(margedBuilding);
				marged.put(margedBuilding);
			}
		}
		return marged;
	}
	
	/**
	 * `src`に接続するリレーションを`src`にマージする
	 * @param checked	調査対象のリレーションリスト
	 * @param src	マージ先のBuilding
	 * @return	マージされたリレーション
	 */
	private RelationBuilding checkParts(RelationMap checked, RelationBuilding src) {
		ElementWay srcWay =  src.getOutlineWay(osm);
		if (srcWay == null) {
			return null;
		}

		for (String relationid : checked.keySet()) {
			RelationBuilding relation = (RelationBuilding)checked.get(relationid);
			ElementWay targetWay =  relation.getOutlineWay(osm);
			List<Integer> list = srcWay.getIntersectBoxels(targetWay.getBoxels());
			if (list.size() > 0) {
				WayMap ways = new WayMap();
				ways.put(srcWay);
				ways.put(targetWay);
				if ((new MargeFactory(osm, ways)).isDuplicateSegment()) {
					matomeru(src, relation);
					return relation;
				}
			}
		}
		return null;
	}
	
	/**
	 * 接続するリレーションのメンバーを取り込む
	 * - role=part メンバーは全て取り込む
	 * - role=outline メンバーは除外する
	 * 
	 * @param aBuilding	取り込みの元
	 * @param bBuilding		取り込まれるリレーション
	 */
	void matomeru(RelationBuilding aBuilding, RelationBuilding bBuilding) {
		WayMap memberways = new WayMap();
		
		if (bBuilding.members.size() == 1) {
			for (MemberBean mem : bBuilding.members) {
				if (mem.getRole().equals("outline")) {
					if (mem.isWay()) {
						ElementWay memway = (ElementWay)osm.getWayMap().get(Long.toString(mem.getRef()));
						memway.toBuilding();
						mem.setRole("part");
						aBuilding.addMember(memway, "part");
						memberways.put(memway.clone());
					}
					else if (mem.isRelation()) {
						ElementRelation polygon = osm.relationMap.get(mem.getRef());
						if (polygon != null) {
							polygon.toBuilding();
							aBuilding.addMember(polygon, "part");
							for (MemberBean polygonMember : polygon.members) {
								if (polygonMember.getRole().equals("outer")) {
									memberways.put(osm.getWayMap().get(polygonMember.getRef()).clone());
								}
							}
						}
					}
				}
			}
		}
		
		for (MemberBean mem : bBuilding.members) {
			if (mem.getRole().equals("part")) {
				// role=part メンバーは全て取り込む, role=outline は取り込まない
				if (mem.getType().equals("way")) {
					ElementWay memway = (ElementWay)osm.getWayMap().get(Long.toString(mem.getRef()));
					aBuilding.addMember(memway, "part");
				}
				else if (mem.isRelation()) {
					ElementRelation polygon = osm.relationMap.get(mem.getRef());
					if (polygon != null) {
						aBuilding.addMember(polygon, "part");
					}
				}
			}
			else if (mem.getRole().equals("outline")) {
				if (bBuilding.members.size() == 1) {
					// role=outline をpartとして取り込む
					if (mem.isWay()) {
						ElementWay memway = (ElementWay)osm.getWayMap().get(Long.toString(mem.getRef())).clone();
						memway.toBuilding();
						aBuilding.addMember(memway, "part");
						memberways.put(memway.clone());
					}
					else if (mem.isRelation()) {
						ElementRelation polygon = osm.relationMap.get(mem.getRef());
						if (polygon != null) {
							polygon.toBuilding();
							aBuilding.addMember(polygon, "part");
							for (MemberBean polygonMember : polygon.members) {
								if (polygonMember.getRole().equals("outer")) {
									memberways.put(osm.getWayMap().get(polygonMember.getRef()).clone());
								}
							}
						}
					}
				}
				else {
					// role=outline は取り込まずに'outline:WAY'だけ取り出す
					if (mem.isWay()) {
						ElementWay memway = (ElementWay)osm.getWayMap().get(Long.toString(mem.getRef()));
						memberways.put(memway.clone());
					}
					else if (mem.isRelation()) {
						ElementRelation polygon = osm.relationMap.get(mem.getRef());
						if (polygon != null) {
							for (MemberBean polygonMember : polygon.members) {
								if (polygonMember.getRole().equals("outer")) {
									memberways.put(osm.getWayMap().get(polygonMember.getRef()).clone());
								}
							}
						}
					}
				}
			}
		}
		aBuilding = (new OutlineFactory(osm)).createOutline(aBuilding, memberways);
		aBuilding.margeTagValue(osm);
	}
}
