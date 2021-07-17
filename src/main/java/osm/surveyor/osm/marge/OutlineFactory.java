package osm.surveyor.osm.marge;

import java.util.ArrayList;
import java.util.List;

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
		MargeFactory factory = (new MargeFactory(osm, osm.ways)).createOutline(building);
		OsmLine outer = factory.getOuter();
		if (outer != null) {
			// Relationのメンバーから"height"の最大値を取得
			String maxheight = osm.getMaxHeight(building);
			String maxLevels = building.getMaxValue(osm.ways, "building:levels");
			String maxLevelsUnderground = building.getMaxValue(osm.ways, "building:levels:underground");

			// OUTLINEをWAYリストに登録
			ElementWay aWay = new ElementWay(osm.getNewId());
			aWay.replaceNds(outer);
			aWay.toArea();
			aWay.member = true;
			
			String polygonRef = building.getRelationMemberId();
			if (polygonRef != null) {
				// マルチポリゴンが存在する場合は、マルチポリゴンにaWayを追加する
				RelationMultipolygon multi = (RelationMultipolygon)osm.relations.get(polygonRef);
				if (multi != null) {
					List<ElementMember> dellist = new ArrayList<>();
					for (ElementMember mem : multi.members) {
						if (mem.role.equals("outer")) {
							dellist.add(mem);
						}
					}
					for (ElementMember mem : dellist) {
						multi.removeMember(mem.ref);
						osm.ways.remove(osm.ways.get(mem.ref));
					}
					aWay.addTag("source", osm.getSource());
					osm.ways.put(aWay);
					multi.addMember(aWay, "outer");
					multi.addTag("height", maxheight);
					multi.addTag("source", osm.getSource());
					multi.addTag("building:levels", maxLevels);
					multi.addTag("building:levels:underground", maxLevelsUnderground);
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
				ElementWay iWay = osm.ways.get(inner);
				if (iWay == null) {
					iWay = new ElementWay(osm.getNewId());
					iWay.replaceNds(inner);
					iWay.member = true;
				}
				osm.ways.put(iWay);
				
				if (polygonRef != null) {
					// マルチポリゴンが存在する場合は、マルチポリゴンにiWayを追加する
					RelationMultipolygon multi = (RelationMultipolygon)osm.relations.get(polygonRef);
					if (multi != null) {
						iWay.addTag("source", osm.getSource());
						multi.addMember(iWay, "inner");
						multi.addTag("building:levels", maxLevels);
						multi.addTag("building:levels:underground", maxLevelsUnderground);
						multi.addTag("building:name", building.getTagValue("name"));
						multi.replaceTag("start_date", new ElementTag("start_date", building.getTagValue("start_date")));
					}
				}
				else {
					// マルチポリゴンが存在しない場合は、マルチポリゴンを作成する
					RelationMultipolygon multi = new RelationMultipolygon(osm.getNewId());
					multi.copyTag(building);
					multi.replaceTag("type", new ElementTag("type","multipolygon"));
					multi.replaceTag("name", new ElementTag("building:name", building.getTagValue("name")));
					multi.replaceTag("start_date", new ElementTag("start_date", building.getTagValue("start_date")));
					
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
					multi.addTag("building:levels", maxLevels);
					multi.addTag("building:levels:underground", maxLevelsUnderground);
					//map.put(multi);
					building.addMember(multi, "outline");
				}
			}
			
			building.tags.remove("building:part");
			building.addTag("source", osm.getSource());
			building.addTag("height", maxheight);
			building.addTag("building:levels", maxLevels);
			building.addTag("building:levels:underground", maxLevelsUnderground);
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
