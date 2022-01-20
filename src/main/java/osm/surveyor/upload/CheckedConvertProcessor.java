package osm.surveyor.upload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import osm.surveyor.osm.BodyMap;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.NdBean;
import osm.surveyor.osm.NodeBean;
import osm.surveyor.osm.OsmBean;
import osm.surveyor.osm.PoiBean;
import osm.surveyor.osm.RelationBean;
import osm.surveyor.osm.TagBean;
import osm.surveyor.osm.WayBean;

public class CheckedConvertProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		BodyMap map = exchange.getIn().getBody(BodyMap.class);
		OsmBean osm = (OsmBean) map.get("osm");		// dom

		// TODO
		duplicatedToModify(osm);

		// WAYに所属していないNODEを削除する
		osm.gerbageNode();
		
		// リレーションの"name"を決定する
		fixRelationsName(osm);
		
		map.put("mrg", osm);
	}
	
	/**
	 * TODO
	 * @throws Exception
	 */
	private void duplicatedToModify(OsmBean osm) throws Exception {
		List<WayBean> work = new ArrayList<>();
		for (WayBean way : osm.getWayList()) {
			work.add(way);
		}
		for (WayBean way : work) {
			long oldid = way.getId();
			if (!way.getFix()) {
				// org.WAYと重複する面積が最大の osm.WAY.id を返す
				long wayid = way.getIntersect(org.getWayList());
				if (wayid > 0) {
					for (WayBean sWay : way.getIntersects(org.getWayList())) {
						if (sWay.getId() == wayid) {
							sWay.setFix(true);
							way.copyTag(sWay);
							sWay.copyTag(way);
							sWay.addTag(new TagBean("MLIT_PLATEAU:fixme", "PLATEAUデータで更新されています"));
							sWay.setAction("modify");
							sWay.orignal = false;
							sWay.setNdList(way.getNdList());
							osm.removeWay(way);
							osm.putWay(sWay);
			        		
			        		for (RelationBean relation : osm.getRelationList()) {
			        			relation.setAction("modify");
			        			for (MemberBean member : relation.getMemberList()) {
			        				if (member.getRef() == oldid) {
			        					if (member.getRole().equals("part")) {
			        						toPart(sWay);
			        					}
			        					else if (member.getRole().equals("outline")) {
			        						toPart(sWay);
			        					}
			        					else if (member.getRole().equals("outer")) {
			        						List<TagBean> tags = new ArrayList<>();
			        						for (TagBean tag : sWay.getTagList()) {
			        							if (tag.k.startsWith("building")) {
			        								relation.addTag(tag.k, tag.v);
			        								tags.add(tag);
			        							}
			        						}
			        						for (TagBean tag : tags) {
			        							sWay.removeTag(tag.k);
			        						}
			        						toPart(sWay);
			        					}
			        					member.setWay(sWay);
			        				}
			        			}
			        		}
						}
						else {
							sWay.setFix(true);
							sWay.addTag(new TagBean("MLIT_PLATEAU:fixme", "delete 削除されます"));
							sWay.setAction("modify");
							sWay.orignal = true;
							for (NdBean nd : sWay.getNdList()) {
								NodeBean node = org.getNode(nd.getRef());
								if (node != null) {
									osm.putNode(node);
								}
							}
							osm.putWay(sWay);
						}
					}
				}
			}
		}
	}
	
	private void toPart(PoiBean poi) {
		TagBean part = poi.getTag("building:part");
		TagBean building = poi.getTag("building");
		if (building == null) {
			return;
		}
		else {
			poi.removeTag("building");
			if (part == null) {
				poi.addTag("building:part", building.v);
			}
			else {
				if (part.v.equals("yes")) {
					part.v = building.v;
				}
			}
		}
	}
	
	/**
	 * リレーションの"name=xxxx"を決定する
	 * リレーションの"building=xxxx"を決定する
	 * 
	 */
	private void fixRelationsName(OsmBean mrg) throws Exception {
		for (RelationBean relation : mrg.getRelationList()) {
			if (relation.isBuilding()) {
				double max = 0;
				double maxBuilding = 0;
				String name = null;
				String building = "yes";
				for (MemberBean member : relation.getMemberList()) {
					if (member.isWay()) {
						WayBean way = mrg.getWay(member.getRef());
						if (way.getArea() > max) {
							if (way.getTagValue("name") != null) {
								name = way.getTagValue("name");
								max = way.getArea();
							}
						}
						if (way.getArea() > maxBuilding) {
							String buildingValue = way.getTagValue("building:part");
							if ((buildingValue != null) && !buildingValue.isEmpty() && !buildingValue.equals("yes")) {
								building = new String(buildingValue);
								maxBuilding = way.getArea();
							}
							buildingValue = way.getTagValue("building");
							if ((buildingValue != null) && !buildingValue.isEmpty() && !buildingValue.equals("yes")) {
								building = new String(buildingValue);
								maxBuilding = way.getArea();
							}
						}
					}
					else if (member.isRelation()) {
						RelationBean polygon = mrg.getRelation(member.getRef());
						if (polygon.isMultipolygon()) {
							for (MemberBean mmem : polygon.getMemberList()) {
								WayBean way = mrg.getWay(mmem.getRef());
								if (way.getArea() > max) {
									if (way.getTagValue("name") != null) {
										name = way.getTagValue("name");
										max = way.getArea();
									}
								}
								if (way.getArea() > maxBuilding) {
									String buildingValue = way.getTagValue("building:part");
									if ((buildingValue != null) && !buildingValue.isEmpty() && !buildingValue.equals("yes")) {
										building = new String(buildingValue);
										maxBuilding = way.getArea();
									}
									buildingValue = way.getTagValue("building");
									if ((buildingValue != null) && !buildingValue.isEmpty() && !buildingValue.equals("yes")) {
										building = new String(buildingValue);
										maxBuilding = way.getArea();
									}
								}
							}
						}
					}
				}
				relation.removeTag("name");
				if (name != null) {
					relation.addTag("name", name);
				}
				relation.removeTag("building");
				if (building != null) {
					relation.addTag("building", building);
				}
			}
			else if (relation.isMultipolygon()) {
				double max = 0;
				double maxBuilding = 0;
				String name = null;
				String building = "yes";
				for (MemberBean member : relation.getMemberList()) {
					if (member.isWay()) {
						WayBean way = mrg.getWay(member.getRef());
						if (way.getArea() > max) {
							if (way.getTagValue("name") != null) {
								name = way.getTagValue("name");
								max = way.getArea();
							}
						}
						if (way.getArea() > maxBuilding) {
							String buildingValue = way.getTagValue("building:part");
							if ((buildingValue != null) && !buildingValue.isEmpty() && !buildingValue.equals("yes")) {
								building = new String(buildingValue);
								maxBuilding = way.getArea();
							}
							buildingValue = way.getTagValue("building");
							if ((buildingValue != null) && !buildingValue.isEmpty() && !buildingValue.equals("yes")) {
								building = new String(buildingValue);
								maxBuilding = way.getArea();
							}
						}
					}
				}
				relation.removeTag("name");
				if (name != null) {
					relation.addTag("name", name);
				}
				if (mrg.getParents(relation).size() > 0) {
					relation.removeTag("building:part");
					if (building != null) {
						relation.addTag("building:part", building);
					}
				}
				else {
					relation.removeTag("building");
					if (building != null) {
						relation.addTag("building", building);
					}
				}
			}
		}
	}
	
	/**
	 * tag.key=`building*` を有するPOIを'building'POIとみなす
	 * 
	 */
	static boolean isBuildingTag(HashMap<String,TagBean> tags) {
		for (String k : tags.keySet()) {
			TagBean tag = tags.get(k);
			if (tag.k.startsWith("building")) {
				return true;
			}
		}
		return false;
	}
}
