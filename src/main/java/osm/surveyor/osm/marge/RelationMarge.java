package osm.surveyor.osm.marge;

import java.util.ArrayList;
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
		RelationMap checked = new RelationMap();
		
		// 接触しているBUILDINGのWAYをくっつけて"Relation:building"をつくる
		for (String rKey : osm.relations.keySet()) {
			ElementRelation relation = osm.relations.get(rKey);
			if (relation.isBuilding()) {
				RelationMap marged = relationMarge1((RelationBuilding)relation, checked);
				if (marged.size() > 0) {
					for (String key : marged.keySet()) {
						RelationBuilding del = (RelationBuilding)osm.relations.get(key);
						del.setMarged(true);
						osm.removeWay(del.getOutlineWay(osm));
					
						osm.relations.remove(del.getMultiPolygon(osm));
						osm.relations.remove(del);
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
	 * @param checked
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
	
	void matomeru(RelationBuilding relation, RelationBuilding b) {
		// 接続するリレーションのメンバーを取り込む
		// Wayメンバーは全て取り込む
		// RelationメンバーはInnerのみ取り込む。Outerは除外する
		ElementRelation multi = relation.getMultiPolygon(osm);
		for (MemberBean mem : b.members) {
			if (mem.getRole().equals("part")) {
				if (mem.getType().equals("way")) {
					ElementWay memway = (ElementWay)osm.ways.get(Long.toString(mem.getRef()));
					relation.addMember(memway, mem.getRole());
				}
			}
			else if (mem.getRole().equals("outline")) {
				if (mem.getType().equals(ElementRelation.RELATION)) {
					ElementRelation polygon = osm.relations.get(Long.toString(mem.getRef()));
					if (polygon != null) {
						for (MemberBean polymem : polygon.members) {
							if (polymem.getType().equals("way") && polymem.getRole().equals("inner")) {
								if (multi != null) {
									multi.addMember((ElementWay)osm.ways.get(Long.toString(polymem.getRef())), "inner");
								}
							}
						}
					}
				}
			}
		}
		ArrayList<MemberBean> deloutline = new ArrayList<>();
		for (MemberBean mem : relation.members) {
			if (mem.getType().equals(ElementRelation.RELATION)) {
				if (mem.getRef() != multi.getId()) {
					deloutline.add(mem);
				}
			}
		}
		for (MemberBean mem : deloutline) {
			relation.removeMember(mem.getRef());
		}
		relation = (new OutlineFactory(osm)).createOutline(relation);
		relation.margeTagValue(osm);
	}
}
