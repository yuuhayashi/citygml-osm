package osm.surveyor.osm.marge;

import java.time.LocalTime;
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
		System.out.println(LocalTime.now() +"\tRelationMarge.relationMarge()");
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
		if (checked.get(relation.getId()) != null) {
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
	 * @param src	指定のBuilding
	 * @return	接続するリレーションがない場合はNULL
	 */
	private RelationBuilding checkParts(RelationMap checked, RelationBuilding src) {
		ElementWay srcWay =  src.getOutlineWay(osm);

		for (String relationid : checked.keySet()) {
			RelationBuilding relation = (RelationBuilding)checked.get(relationid);
			ElementWay targetWay =  relation.getOutlineWay(osm);
			List<Integer> list = srcWay.getIntersectBoxels(targetWay.getBoxels());
			if (list.size() > 0) {
				WayMap ways = new WayMap();
				ways.put(srcWay);
				ways.put(targetWay);
				if ((new MargeFactory(osm, ways)).isDuplicateSegment()) {
					src = matomeru(src, relation);
					return relation;
				}
			}
		}
		return null;
	}
		
	RelationBuilding matomeru(RelationBuilding relation, RelationBuilding b) {
		// 接続するリレーションのメンバーを取り込む
		// Wayメンバーは全て取り込む
		// RelationメンバーはInnerのみ取り込む。Outerは除外する
		ElementRelation multi = relation.getMultiPolygon(osm);
		for (MemberBean mem : b.members) {
			if (mem.getRole().equals("part")) {
				if (mem.getType().equals("way")) {
					ElementWay memway = osm.ways.get(Long.toString(mem.getRef()));
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
									multi.addMember(osm.ways.get(Long.toString(polymem.getRef())), "inner");
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
		return relation;
	}
}
