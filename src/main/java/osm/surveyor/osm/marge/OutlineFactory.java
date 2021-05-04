package osm.surveyor.osm.marge;

import java.util.ArrayList;
import java.util.HashMap;

import osm.surveyor.osm.ElementMember;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementTag;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.OsmLine;
import osm.surveyor.osm.RelationBuilding;
import osm.surveyor.osm.RelationMap;
import osm.surveyor.osm.RelationMultipolygon;

public class OutlineFactory {
	
	/**
	 * - Relation->member:role=port のoutlineを作成する
	 * - 
	 * 
	 * @param relations
	 */
	public static OsmDom relationOutline(OsmDom osm) {
		RelationMap map = new RelationMap();
				
		for (String rKey : osm.relations.keySet()) {
			ElementRelation relation = osm.relations.get(rKey);
			if (!relation.isBuilding()) {
				continue;
			}
			RelationBuilding building = (RelationBuilding)relation;
			MargeFactory factory = building.createOutline(osm.ways);
			OsmLine outer = factory.getOuter();
			if (outer == null) {
				continue;
			}
			
			// Relationのメンバーから"height"の最大値を取得
			String maxheight = osm.getMaxHeight(building);

			// OUTLINEをWAYリストに登録
			ElementWay aWay = new ElementWay();
			aWay.replaceNds(outer);
			aWay.member = true;
			
			String polygonRef = building.getRelationMemberId();
			if (polygonRef != null) {
				// マルチポリゴンが存在する場合は、マルチポリゴンにaWayを追加する
				RelationMultipolygon multi = (RelationMultipolygon)osm.relations.get(polygonRef);
				if (multi != null) {
					for (ElementMember mem : multi.members) {
						if (mem.role.equals("outer")) {
							multi.removeMember(mem.ref);
							osm.ways.remove(osm.ways.get(mem.ref));
							break;
						}
					}
					aWay.addTag("source", osm.getSource());
					osm.ways.put(aWay);
					multi.addMember(aWay, "outer");
					multi.addTag("height", maxheight);
					multi.addTag("source", osm.getSource());
				}
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
			
			// マルチポリゴンにINNERを追加する
			ArrayList<OsmLine> inners = factory.getInners();
			for (OsmLine inner : inners) {
				ElementWay iWay = new ElementWay();
				iWay.replaceNds(inner);
				iWay.member = true;
				osm.ways.put(iWay);
				
				if (polygonRef != null) {
					// マルチポリゴンが存在する場合は、マルチポリゴンにaWayを追加する
					RelationMultipolygon multi = (RelationMultipolygon)osm.relations.get(polygonRef);
					if (multi != null) {
						iWay.addTag("source", osm.getSource());
						multi.addMember(iWay, "inner");
					}
				}
				else {
					// マルチポリゴンが存在しない場合は、マルチポリゴンを作成する
					RelationMultipolygon multi = new RelationMultipolygon();
					multi.copyTag(building);
					multi.replaceTag("type", new ElementTag("type","multipolygon"));
					multi.replaceTag("name", new ElementTag("building:name", building.getTagValue("name")));
					
					// "outer"に、"bulding:Relation"の"outline"WAYをMEMBERとして追加する
					for (ElementMember mem : building.members) {
						if (mem.role.equals("outline") && mem.type.equals("way")) {
							ElementWay outlineWay = osm.ways.get(mem.ref);
							outlineWay.tags = new HashMap<String,ElementTag>();
							outlineWay.addTag("source", osm.getSource());
							multi.addMember(outlineWay, "outer");
							building.removeMember(mem.ref);
							break;
						}
					}
					iWay.addTag("source", osm.getSource());
					multi.addMember(iWay, "inner");
					map.put(multi);
					building.addMember(multi, "outline");
				}
			}
			
			building.tags.remove("building:part");
			building.addTag("source", osm.getSource());
			building.addTag("height", maxheight);
		}
		
		while (outlineRelation(osm, map));
		return osm;
	}
	
	private static boolean outlineRelation(OsmDom osm, RelationMap map) {
		for (String rKey : map.keySet()) {
			ElementRelation relation = map.get(rKey);
			osm.relations.put(relation);
			map.remove(rKey);
			return true;
		}
		return false;
	}

}
