package osm.surveyor.osm.marge;

import java.util.ArrayList;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.OsmLine;
import osm.surveyor.osm.RelationBuilding;
import osm.surveyor.osm.RelationMap;
import osm.surveyor.osm.RelationMultipolygon;
import osm.surveyor.osm.TagBean;
import osm.surveyor.osm.WayMap;

public class OutlineFactory {
	
	OsmDom osm;
	
	public OutlineFactory(OsmDom osm) {
		this.osm = osm;
	}
	
	/**
	 * - Relation->member:role=port のoutlineを作成する
	 * - 
	 * 
	 * @param relationMap
	 */
	public void relationOutline() {
		RelationMap map = new RelationMap();
				
		for (ElementRelation relation : osm.relationMap.values()) {
			if (relation.isBuilding()) {
				boolean hasOutline = false;
				for (MemberBean member : relation.members) {
					if (member.getRole().equals("outline")) {
						hasOutline = true;
					}
				}
				if (hasOutline) {
					// Relationのメンバーから"height"の最大値を取得
					relation.addTag("height", osm.getMaxHeight(relation));
					relation.addTag("ele", osm.getMinEle(relation));
					relation.removeTag("ref:MLIT_PLATEAU");
				}
				else {
					WayMap memberways = new WayMap();
					for (MemberBean member : relation.members) {
						if (member.getRole().equals("part")) {
							if (member.isWay()) {
								memberways.put(osm.getWayMap().get(member.getRef()).clone());
							}
							else if (member.isRelation()) {
								ElementRelation polygon = osm.relationMap.get(member.getRef());
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
					map.put(createOutline((RelationBuilding)relation, memberways));
				}
			}
		}
		
		while (outlineRelation(map));
	}
	
	/**
	 * 
	 * @param building	合成の元
	 * @param outlineways	取り込むアウトラインWAY
	 * @return	合成済みのBUILDINGリレーション(合成の元)
	 */
	public RelationBuilding createOutline(RelationBuilding building, WayMap outlineways) {
		// Relationのメンバーから"height"の最大値を取得
		String minele = osm.getMinEle(building);
		String maxheight = osm.getMaxHeight(building);
		RelationMultipolygon multi = null;
		
		MargeFactory factory = (new MargeFactory(osm, outlineways));
		factory.addSourceRelation(building);
		factory.marge();
		OsmLine outer = factory.getOuter();
		if (outer != null) {
			// OUTLINEをWAYリストに登録
			ElementWay aWay = new ElementWay(osm.getNewId());
			aWay.replaceNds(outer);
			aWay.toArea(this.osm.getIndexMap());
			aWay.setMemberWay(true);
			
			ArrayList<MemberBean> delPolygonlist = new ArrayList<>();
			boolean hasPart = false;		// 有効な part メンバーの有無
			for (MemberBean aMember : building.members) {
				if (aMember.getRole().equals("part")) {
					if (aMember.isRelation()) {
						RelationMultipolygon polygon = (RelationMultipolygon)osm.relationMap.get(aMember.getRef());
						for (MemberBean polygonMember : polygon.members) {
							if (polygonMember.getRole().equals("inner")) {
								hasPart = true;
								break;
							}
						}
					}
					else if (aMember.isWay()) {
						hasPart = true;
						break;
					}
				}
			}
			for (MemberBean aMember : building.members) {
				if (aMember.getRole().equals("outline")) {
					if (aMember.isRelation()) {
						if (multi == null) {
							multi = (RelationMultipolygon)osm.relationMap.get(aMember.getRef());
							if (multi != null) {
								ArrayList<MemberBean> dellist = new ArrayList<>();
								for (MemberBean outerMem : multi.members) {
									if (outerMem.getRole().equals("outer")) {
										dellist.add(outerMem);
									}
								}
								for (MemberBean delMem : dellist) {
									multi.removeMember(delMem.getRef());
								}
							}
						}
						else {
							if (aMember.getRef() != multi.getId()) {
								RelationMultipolygon addMulti = (RelationMultipolygon)osm.relationMap.get(aMember.getRef());
								ArrayList<MemberBean> addlist = new ArrayList<>();
								for (MemberBean addMem : addMulti.members) {
									if (addMem.getRole().equals("inner")) {
										addlist.add(addMem);
									}
								}
								for (MemberBean addMem : addlist) {
									multi.addMember((ElementWay)osm.getWayMap().get(addMem.getRef()), "inner");
								}
								delPolygonlist.add(aMember);
							}
						}
					}
					else if (aMember.isWay()) {
						if (hasPart) {
							delPolygonlist.add(aMember);
						}
						else {
							aMember.setRole("part");
						}
					}
				}
			}
			for (MemberBean delMem : delPolygonlist) {
				building.removeMember(delMem.getRef());
			}

			if (multi != null) {
				// マルチポリゴンが'outline'の場合は、マルチポリゴンにaWayを追加する
				osm.getWayMap().put(aWay);
				multi.addMember(aWay, "outer");
			}
			else {
				// マルチポリゴンが存在しない場合は、"bulding:Relation"に"outline"WAYをMEMBERとして追加する
				aWay.copyTag(building);
				aWay.removeTag("type");
				aWay.addTag("height", maxheight);
				aWay.addTag("ele", minele);
				aWay.toPart();
				osm.getWayMap().put(aWay);
				
				building.addMember(aWay, "outline");
			}
		}
		
		// マルチポリゴンにINNERを追加する
		ArrayList<OsmLine> inners = factory.getInners();
		for (OsmLine inner : inners) {
			ElementWay iWay = (ElementWay)osm.getWayMap().get(inner);
			if (iWay == null) {
				iWay = new ElementWay(osm.getNewId());
				iWay.replaceNds(inner);
				iWay.setMemberWay(true);
				osm.getWayMap().put(iWay);
			}
			if (multi != null) {
				// マルチポリゴンが存在する場合は、マルチポリゴンにiWayを追加する
				multi.addMember(iWay, "inner");
				TagBean buil = multi.getTag("building");
				if (buil != null) {
					multi.replaceTag("building", new TagBean("building:part", buil.v));
				}
				building.addMember(multi, "outline");
			}
			else {
				// マルチポリゴンが存在しない場合は、マルチポリゴンを作成する
				multi = new RelationMultipolygon(osm.getNewId());
				TagBean buil = multi.getTag("building");
				if (buil != null) {
					multi.replaceTag("building", new TagBean("building:part", buil.v));
				}
				
				// "outer"に、"bulding:Relation"の"outline"WAYをMEMBERとして追加する
				for (MemberBean mem : building.members) {
					if (mem.getRole().equals("outline") && mem.getType().equals("way")) {
						ElementWay outlineWay = (ElementWay)osm.getWayMap().get(mem.getRef());
						multi.addMember(outlineWay, "outer");
						building.removeMember(mem.getRef());
						break;
					}
				}
				multi.addMember(iWay, "inner");
				building.addMember(multi, "outline");
			}
		}
		
		return building;
	}
	
	private boolean outlineRelation(RelationMap map) {
		for (String rKey : map.keySet()) {
			ElementRelation relation = map.get(rKey);
			osm.relationMap.put(relation);
			map.remove(rKey);
			return true;
		}
		return false;
	}

}
