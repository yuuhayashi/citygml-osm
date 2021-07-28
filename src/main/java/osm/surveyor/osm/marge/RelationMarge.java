package osm.surveyor.osm.marge;

import java.util.ArrayList;

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
									multi.addMember(osm.ways.get(Long.toString(polymem.ref)), "inner");
								}
							}
						}
					}
				}
			}
		}
		ArrayList<ElementMember> deloutline = new ArrayList<>();
		for (ElementMember mem : relation.members) {
			if (mem.type.equals(ElementRelation.RELATION)) {
				if (mem.ref != multi.id) {
					deloutline.add(mem);
				}
			}
		}
		for (ElementMember mem : deloutline) {
			relation.removeMember(mem.ref);
		}
		relation = (new OutlineFactory(osm)).createOutline(relation);
		relation.margeTagValue(osm);
		return relation;
	}
}
