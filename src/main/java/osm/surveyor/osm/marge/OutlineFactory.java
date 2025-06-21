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

public class OutlineFactory {
	
	OsmDom osm;
	
	public OutlineFactory(OsmDom osm) {
		this.osm = osm;
	}
	
	/**
	 * - Relation->member:role=port のoutlineを作成する
	 * - 
	 * 
	 * @param relations
	 */
	public void relationOutline() {
		RelationMap map = new RelationMap();
				
		for (String rKey : osm.relations.keySet()) {
			ElementRelation relation = osm.relations.get(rKey);
			if (relation.isBuilding()) {
				map.put(createOutline((RelationBuilding)relation));
			}
		}
		
		while (outlineRelation(map));
	}
	
	public RelationBuilding createOutline(RelationBuilding building) {
		// Relationのメンバーから"height"の最大値を取得
		String minele = osm.getMinEle(building);
		String maxheight = osm.getMaxHeight(building);
		RelationMultipolygon multi = null;
		MargeFactory factory = (new MargeFactory(osm, osm.ways)).createOutline(building);
		OsmLine outer = factory.getOuter();
		if (outer != null) {
			// OUTLINEをWAYリストに登録
			ElementWay aWay = new ElementWay(osm.getNewId());
			aWay.replaceNds(outer);
			aWay.toArea(this.osm.indexMap);
			aWay.member = true;
			
			ArrayList<MemberBean> delPolygonlist = new ArrayList<>();
			for (MemberBean mem : building.members) {
				if (mem.getType().equals(ElementRelation.RELATION)) {
					if (multi == null) {
						multi = (RelationMultipolygon)osm.relations.get(mem.getRef());
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
						if (mem.getRef() != multi.getId()) {
							RelationMultipolygon addMulti = (RelationMultipolygon)osm.relations.get(mem.getRef());
							ArrayList<MemberBean> addlist = new ArrayList<>();
							for (MemberBean addMem : addMulti.members) {
								if (addMem.getRole().equals("inner")) {
									addlist.add(addMem);
								}
							}
							for (MemberBean addMem : addlist) {
								multi.addMember((ElementWay)osm.ways.get(addMem.getRef()), "inner");
							}
							delPolygonlist.add(mem);
						}
					}
				}
			}
			for (MemberBean delMem : delPolygonlist) {
				building.removeMember(delMem.getRef());
			}

			if (multi != null) {
				// マルチポリゴンが存在する場合は、マルチポリゴンにaWayを追加する
				osm.ways.put(aWay);
				multi.addMember(aWay, "outer");
			}
			else {
				// マルチポリゴンが存在しない場合は、"bulding:Relation"に"outline"WAYをMEMBERとして追加する
				aWay.copyTag(building);
				aWay.removeTag("type");
				aWay.removeTag("building:part");
				aWay.addTag("height", maxheight);
				aWay.addTag("ele", minele);
				osm.ways.put(aWay);
				
				building.addMember(aWay, "outline");
			}
		}
		
		// マルチポリゴンにINNERを追加する
		ArrayList<OsmLine> inners = factory.getInners();
		for (OsmLine inner : inners) {
			ElementWay iWay = (ElementWay)osm.ways.get(inner);
			if (iWay == null) {
				iWay = new ElementWay(osm.getNewId());
				iWay.replaceNds(inner);
				iWay.member = true;
				osm.ways.put(iWay);
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
						ElementWay outlineWay = (ElementWay)osm.ways.get(mem.getRef());
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
			osm.relations.put(relation);
			map.remove(rKey);
			return true;
		}
		return false;
	}

}
