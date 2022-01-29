package osm.surveyor.update;

import java.util.ArrayList;
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

public class OrgUpdateProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		BodyMap map = exchange.getIn().getBody(BodyMap.class);
		OsmBean osm = (OsmBean) map.get("osm");		// dom
		OsmBean org = (OsmBean) map.get("org");		// sdom

		// タグありのノードをメンバーに持つウェイは「非更新対象」(fix=true)にする
		fixWayWithNode(org);
		
		// リレーションとそのメンバーをFIXにする
		fixRelation(org);
		
		// Issue #60 "end_date"と"demolished:building"
		fixEndDate(org);
		
		// インポートデータの内で、Fix=trueの既存データと重複するものを削除
		// その後、既存データからFix=trueのPOIを削除する
		removeFixed(osm, org);

		// 既存データの内で、インポートデータと重複しないものを削除
		removeNotDuplicated(osm, org);
    		
		// すべてのノードを'modify'に確定する
		fixToModify(osm);

		// インポートデータの内で、既存データと重複するWAYを'modify'に確定する
		duplicatedToModify(osm, org);

		// WAYに所属していないNODEを削除する
		osm.gerbageNode();
		
		// 建物リレーションのタグを決定する
		fixBuildingRelation(osm);
		
		// リレーションのメンバーWAYには"building"タグをつけない
		fixMemberWaysTag(osm);
		
		map.put("mrg", osm);
	}
	
	/**
	 * 全てのリレーションとそのメンバーをFIXにする
	 * リレーションのメンバーウェイは「非更新対象」(fix=true)にする
	 */
	private void fixRelation(OsmBean org) {
		for (RelationBean relation : org.getRelationList()) {
			fixRelation(org, relation);
        }
	}

	/**
	 * 指定のリレーションとそのメンバーをFIXにする
	 * リレーションのメンバーウェイは「非更新対象」(fix=true)にする
	 */
	private void fixRelation(OsmBean org, RelationBean relation) {
		relation.setFix(true);
		for (MemberBean member : relation.getMemberList()) {
			if (member.isWay()) {
				WayBean way = org.getWay(member.getRef());
				if (way != null) {
					way.setFix(true);
				}
			}
		}
	}
	
	/**
	 * タグありのノードをメンバーに持つウェイは「非更新対象」(fix=true)にする
	 * @param org
	 */
	private void fixWayWithNode(OsmBean org) {
		for (WayBean way : org.getWayList()) {
			for (NdBean nd : way.getNdList()) {
				NodeBean node = org.getNode(nd.getRef());
				if (node != null) {
					if (node.getTagList().size() > 0) {
						fixWay(org, way);
					}
				}
			}
		}
	}
	
	/**
	 * Issue #60 取り壊された建物に重複するデータは「非更新対象」(fix=true)
	 * (5) end_date=* タグがある"WAY:building"
	 * (6) WAY demolished:building=*
	 * @param org
	 */
	private void fixEndDate(OsmBean org) {
		for (WayBean way : org.getWayList()) {
			if (way.getTag("end_date") != null) {
				fixWay(org, way);
			}
			if (way.getTag("demolished:building") != null) {
				fixWay(org, way);
			}
		}
	}
	
	/**
	 * 指定のWAYとそのWAYをメンバーに持つリレーションをFIXにする
	 * @param org
	 * @param way
	 */
	private void fixWay(OsmBean org, WayBean way) {
		way.setFix(true);
		for (RelationBean relation : org.getParents(way)) {
			fixRelation(org, relation);
		}
	}
	
	/**
	 * インポートデータの内で、Fix=trueの既存データと重複するものを削除
	 * その後、既存データからFix=trueのPOIを削除する
	 * @throws Exception 
	 */
	private void removeFixed(OsmBean osm, OsmBean org) throws Exception {
		List<RelationBean> fixedrelation = new ArrayList<>();
		List<WayBean> fixedway = new ArrayList<>();
		for (WayBean obj : osm.getWayList()) {
			for (WayBean way2 : org.getWayList()) {
				if (way2.getFix()) {
					// `fix=true`
					if (obj.getIntersectArea(way2) > 0.0d) {
						fixedway.add(obj);
						List<RelationBean> relations = osm.getParents(obj);
						for (RelationBean relation : relations) {
							fixedrelation.add(relation);
						}
					}
				}
				
			}
		}
		for (RelationBean obj : fixedrelation) {
			osm.removeRelation(obj);
		}
		for (WayBean obj : fixedway) {
			osm.removeWay(obj);
		}
	}
	
	/**
	 * 既存データの内で、インポートデータと重複しないものを削除
	 * @throws Exception 
	 */
	private void removeNotDuplicated(OsmBean osm, OsmBean org) throws Exception {
		List<WayBean> list = new ArrayList<>();
		for (WayBean way : org.getWayList()) {
			if (!way.isIntersect(osm.getWayList())) {
				list.add(way);
			}
		}
		for (WayBean way : list) {
			org.removeWay(way);
		}
	}
	
	/**
	 * 既存データのすべてのノードを'modify'に確定する
	 * リレーションのINNER/OUTER:WAYとOUTLINE:wayはFix=trueにする。それ以外のWAYはFix=falseにする
	 */
	private void fixToModify(OsmBean osm) {
		for (NodeBean node : osm.getNodeList()) {
			node.setAction("modify");
			node.orignal = false;
			//osm.putNode(node);
		}
		for (WayBean way : osm.getWayList()) {
			way.setFix(false);
		}
		for (RelationBean relation : osm.getRelationList()) {
			for (MemberBean member : relation.getMemberList()) {
				if (member.isWay()) {
					if (member.getRole().equals("inner")) {
						WayBean way = osm.getWay(member.getRef());
						way.setFix(true);
					}
					else {
						WayBean way = osm.getWay(member.getRef());
						TagBean tag = way.getTag("ref:MLIT_PLATEAU");
						if (tag == null) {
							way.setFix(true);
						}
					}
				}
			}
		}
	}
	
	/**
	 * インポートデータの内で、既存データと重複するWAYをインポートデータへマージする
	 * @throws Exception
	 */
	private void duplicatedToModify(OsmBean osm, OsmBean org) throws Exception {
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
							if (sWay.getTag("source") != null) {
								// Issue #56
								TagBean sourceTag = sWay.getTag("source");
								sWay.removeTag("source");
								if (sourceTag.getValue().toLowerCase().contains("survey")) {
									sWay.addTag("source", "survey");
								}
							}
							if (way.getTag("building") != null) {
								// Issue #56
								TagBean buildingTag = way.getTag("building");
								if (buildingTag.getValue().toLowerCase().equals("yes")) {
									way.removeTag("building");
								}
							}
							if (way.getTag("start_date") != null) {
								// Issue #61 「start_date」は既存データ優先とする
								TagBean startTag = sWay.getTag("start_date");
								if (startTag != null) {
									way.removeTag("start_date");
								}
							}
							if (way.getTag("name") != null) {
								// Issue #64 「name」は既存データ優先とする
								TagBean startTag = sWay.getTag("name");
								if (startTag != null) {
									way.removeTag("name");
								}
							}
							sWay.copyTag(way);
							way.copyTag(sWay);
							sWay.addTag(new TagBean("MLIT_PLATEAU:fixme", "PLATEAUデータで更新されています"));
							sWay.setAction("modify");
							sWay.orignal = false;
							sWay.setNdList(way.getNdList());
							osm.removeWay(way);
							osm.putWay(sWay);
			        		
			        		for (RelationBean relation : osm.getRelationList()) {
			        			relation.setAction("modify");
			        			String buildingValue = "yes";
			        			for (MemberBean member : relation.getMemberList()) {
			        				if (member.getRef() == oldid) {
			        					member.setWay(sWay);
			        				}
			        			}
			        			if (relation.getTag("building") == null) {
			        				relation.addTag("building", buildingValue);
			        			}
			        			else {
			        				relation.getTag("building").setValue(buildingValue);
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

	/**
	 * 建物リレーションのタグを決定する
	 * 
	 */
	private void fixBuildingRelation(OsmBean mrg) throws Exception {
		for (RelationBean relation : mrg.getRelationList()) {
			if (relation.isBuilding() ) {
				WayBean maxway = getMaxPart(relation, mrg);
				if (maxway != null) {
					maxway.removeTag("building:levels");
					maxway.removeTag("building:part");
					maxway.removeTag("height");
					maxway.removeTag("MLIT_PLATEAU:fixme");
					maxway.removeTag("ref:MLIT_PLATEAU");
					maxway.removeTag("start_date");		// Issue #39 複合ビルでの”建築年”の扱い
					relation.copyTag(maxway);
				}
			}
			else if (relation.isMultipolygon()) {
				WayBean maxway = getMaxPart(relation, mrg);
				if (maxway != null) {
					maxway.removeTag("MLIT_PLATEAU:fixme");
					maxway.removeTag("ref:MLIT_PLATEAU");
					maxway.removeTag("start_date");		// Issue #39 複合ビルでの”建築年”の扱い
					relation.copyTag(maxway);
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
	 * リレーションのメンバーWAYには"building"タグをつけない
	 * @param mrg
	 */
	private void fixMemberWaysTag(OsmBean mrg) {
		for (RelationBean relation : mrg.getRelationList()) {
			relation.setAction("modify");
			for (MemberBean member : relation.getMemberList()) {
				if (member.isWay()) {
					WayBean way = mrg.getWay(member.getRef());
					if (way != null) {
						if (member.getRole().equals("part")) {
							toPart(way);
						}
						else if (member.getRole().equals("outline")) {
							List<TagBean> tags = new ArrayList<>();
							for (TagBean tag : way.getTagList()) {
								if (tag.k.equals("building")) {
									tags.add(tag);
								}
								else if (tag.k.equals("building:part")) {
									tags.add(tag);
								}
								else {
									tags.add(tag);
								}
							}
							for (TagBean tag : tags) {
								way.removeTag(tag.k);
							}
							toPart(way);
						}
						else if (member.getRole().equals("outer")) {
							List<TagBean> tags = new ArrayList<>();
							for (TagBean tag : way.getTagList()) {
								if (tag.k.startsWith("MLIT_PLATEAU:fixme")) {
									// Do nothing
								}
								else if (tag.k.equals("building")) {
									tags.add(tag);
								}
								else if (tag.k.equals("building:part")) {
									tags.add(tag);
								}
								else {
									tags.add(tag);
								}
							}
							for (TagBean tag : tags) {
								way.removeTag(tag.k);
							}
							toPart(way);
						}
						member.setWay(way);
					}
					
				}
				else if (member.isRelation()) {
					RelationBean outline = mrg.getRelation(member.getRef());
					if (outline != null) {
						toPart(outline);
					}
				}
			}
		}
	}

	/**
	 * 建物リレーションの"part"メンバー内で最大のWAYを取得する
	 * @param polygon
	 * @param mrg
	 * @return
	 */
	private WayBean getMaxPart(RelationBean polygon, OsmBean mrg) {
		double max = 0;
		WayBean maxway = null;
		for (MemberBean member : polygon.getMemberList()) {
			if (member.getRole().equals("part")) {
				if (member.isWay()) {
					WayBean way = mrg.getWay(member.getRef());
					if (way.getArea() > max) {
						maxway = way.clone();
					}
				}
			}
			else if (member.getRole().equals("outer")) {
				if (member.isWay()) {
					WayBean way = mrg.getWay(member.getRef());
					if (way.getArea() > max) {
						maxway = way.clone();
					}
				}
			}
		}
		return maxway;
	}
}
