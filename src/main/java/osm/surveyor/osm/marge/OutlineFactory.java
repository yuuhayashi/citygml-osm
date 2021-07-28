package osm.surveyor.osm.marge;

import java.util.ArrayList;
import osm.surveyor.osm.ElementMember;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementTag;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.OsmLine;
import osm.surveyor.osm.RelationBuilding;
import osm.surveyor.osm.RelationMap;
import osm.surveyor.osm.RelationMultipolygon;
import osm.surveyor.osm.TagMap;

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
		String maxheight = osm.getMaxHeight(building);
		RelationMultipolygon multi = null;
		MargeFactory factory = (new MargeFactory(osm, osm.ways)).createOutline(building);
		OsmLine outer = factory.getOuter();
		if (outer != null) {
			// OUTLINEをWAYリストに登録
			ElementWay aWay = new ElementWay(osm.getNewId());
			aWay.replaceNds(outer);
			aWay.toArea();
			aWay.member = true;
			
			ArrayList<ElementMember> delPolygonlist = new ArrayList<>();
			for (ElementMember mem : building.members) {
				if (mem.type.equals(ElementRelation.RELATION)) {
					if (multi == null) {
						multi = (RelationMultipolygon)osm.relations.get(mem.ref);
						if (multi != null) {
							ArrayList<ElementMember> dellist = new ArrayList<>();
							for (ElementMember outerMem : multi.members) {
								if (outerMem.role.equals("outer")) {
									dellist.add(outerMem);
								}
							}
							for (ElementMember delMem : dellist) {
								multi.removeMember(delMem.ref);
							}
						}
					}
					else {
						if (mem.ref != multi.id) {
							RelationMultipolygon addMulti = (RelationMultipolygon)osm.relations.get(mem.ref);
							ArrayList<ElementMember> addlist = new ArrayList<>();
							for (ElementMember addMem : addMulti.members) {
								if (addMem.role.equals("inner")) {
									addlist.add(addMem);
								}
							}
							for (ElementMember addMem : addlist) {
								multi.addMember(osm.ways.get(addMem.ref), "inner");
							}
							delPolygonlist.add(mem);
						}
					}
				}
			}
			for (ElementMember delMem : delPolygonlist) {
				building.removeMember(delMem.ref);
			}

			if (multi != null) {
				// マルチポリゴンが存在する場合は、マルチポリゴンにaWayを追加する
				aWay.addTag("source", osm.getSource());
				osm.ways.put(aWay);
				multi.addMember(aWay, "outer");
			}
			else {
				// マルチポリゴンが存在しない場合は、"bulding:Relation"に"outline"WAYをMEMBERとして追加する
				aWay.copyTag(building);
				aWay.tags.remove("type");
				aWay.tags.remove("building:part");
				aWay.addTag("height", maxheight);
				osm.ways.put(aWay);
				
				building.addMember(aWay, "outline");
			}
		}
		
		// マルチポリゴンにINNERを追加する
		ArrayList<OsmLine> inners = factory.getInners();
		for (OsmLine inner : inners) {
			ElementWay iWay = osm.ways.get(inner);
			if (iWay == null) {
				iWay = new ElementWay(osm.getNewId());
				iWay.replaceNds(inner);
				iWay.member = true;
				osm.ways.put(iWay);
			}
			if (multi != null) {
				// マルチポリゴンが存在する場合は、マルチポリゴンにiWayを追加する
				iWay.addTag("source", osm.getSource());
				multi.addMember(iWay, "inner");
				building.addMember(multi, "outline");
			}
			else {
				// マルチポリゴンが存在しない場合は、マルチポリゴンを作成する
				multi = new RelationMultipolygon(osm.getNewId());
				multi.copyTag(building);
				multi.replaceTag("type", new ElementTag("type","multipolygon"));
				
				// "outer"に、"bulding:Relation"の"outline"WAYをMEMBERとして追加する
				for (ElementMember mem : building.members) {
					if (mem.role.equals("outline") && mem.type.equals("way")) {
						ElementWay outlineWay = osm.ways.get(mem.ref);
						outlineWay.tags = new TagMap();
						outlineWay.addTag("source", osm.getSource());
						multi.addMember(outlineWay, "outer");
						building.removeMember(mem.ref);
						break;
					}
				}
				iWay.addTag("source", osm.getSource());
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
