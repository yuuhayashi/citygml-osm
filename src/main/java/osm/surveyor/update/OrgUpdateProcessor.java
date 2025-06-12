package osm.surveyor.update;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import osm.surveyor.citygml.CityModelParser;
import osm.surveyor.osm.BodyMap;
import osm.surveyor.osm.BoundsBean;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.NdBean;
import osm.surveyor.osm.NodeBean;
import osm.surveyor.osm.OsmBean;
import osm.surveyor.osm.PoiBean;
import osm.surveyor.osm.RelationBean;
import osm.surveyor.osm.TagBean;
import osm.surveyor.osm.WayBean;
import osm.surveyor.osm.way.Wayable;

public class OrgUpdateProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		BodyMap map = exchange.getIn().getBody(BodyMap.class);
		OsmBean osm = (OsmBean) map.get("osm");		// dom
		OsmBean org = (OsmBean) map.get("org");		// sdom
		String filename = (String) exchange.getProperty(Exchange.FILE_NAME);
		System.out.println(LocalTime.now() +"\tOrgUpdateProcessor (\""+ filename +"\")");
		
		BoundsBean bounds = osm.getBounds().expand(org.getBounds());
		if (bounds != null) {
			osm.setBounds(bounds);
			osm.reindex();
		}
		bounds = org.getBounds().expand(osm.getBounds());
		if (bounds != null) {
			org.setBounds(bounds);
			org.reindex();
		}

		// タグありのノードをメンバーに持つウェイは「非更新対象」(fix=true)にする
		fixWayWithNode(org);
		
		// リレーションとそのメンバーをFIXにする
		fixRelation(org);
		
		// Issue #60 "end_date"と"demolished:building"
		fixEndDate(org);
		
		// Plateauデータの内で、v=[-9999 .. 9999]のタグを削除
		fix128(osm);
		
		// Plateauデータの内で、Fix=trueの既存データと重複するものを削除
		// その後、既存データからFix=trueのPOIを削除する
		removeFixed(osm, org);

		// 既存データの内で、Plateauデータと重複しないものを削除
		removeNotDuplicated(osm, org);
    		
		// すべてのノードを'modify'に確定する
		fixToModify(osm);

		// Plateauデータの内で、既存データと重複するWAYを'modify'に確定する
		duplicatedToModify(osm, org);

		// WAYに所属していないNODEを削除する
		osm.gerbageNode();
		
		// 建物リレーションのタグを決定する
		fixBuildingRelation(osm);
		
		// リレーションのメンバーWAYには"building"タグをつけない
		fixMemberWaysTag(osm);
		
		// Issue #119 
		fix119(osm);
		
		map.put("mrg", osm);
		exchange.getIn().setBody(map);
		exchange.setProperty(Exchange.FILE_NAME, filename);
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
				Wayable way = org.getWay(member.getRef());
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
		for (Wayable way : org.getWayList()) {
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
		for (Wayable way : org.getWayList()) {
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
	private void fixWay(OsmBean org, Wayable way) {
		way.setFix(true);
		for (RelationBean relation : org.getParents(way.getPoiBean())) {
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
		List<Wayable> fixedway = new ArrayList<>();
		for (Wayable obj : osm.getWayList()) {
			for (Wayable way2 : org.getWayList()) {
				if (way2.getFix()) {
					// `fix=true`
					if (obj.getIntersectArea(way2) > 0.0d) {
						fixedway.add(obj);
						List<RelationBean> relations = osm.getParents(obj.getPoiBean());
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
		for (Wayable obj : fixedway) {
			osm.removeWay(obj);
		}
	}
	
	/**
	 * 既存データの内で、インポートデータと重複しないものを削除
	 * @throws Exception 
	 */
	private void removeNotDuplicated(OsmBean osm, OsmBean org) throws Exception {
		List<WayBean> list = new ArrayList<>();
		List<Wayable> orgList = WayBean.toModelList(org.getWayList());
		for (WayBean way : org.getWayList()) {
			if (!way.isIntersect(orgList)) {
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
		for (Wayable way : osm.getWayList()) {
			way.setFix(false);
		}
		for (RelationBean relation : osm.getRelationList()) {
			for (MemberBean member : relation.getMemberList()) {
				if (member.isWay()) {
					if (member.getRole().equals("inner")) {
						Wayable way = osm.getWay(member.getRef());
						way.setFix(true);
					}
					else {
						Wayable way = osm.getWay(member.getRef());
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
	 * ORGデータの内で、Plateauデータと重複するWAYをMRGへ登録
	 * @throws Exception
	 */
	private void duplicatedToModify(OsmBean osm, OsmBean org) throws Exception {
		List<WayBean> work = osm.getWayList();
		Comparator<Wayable> comparator = Comparator.comparing(Wayable::getArea).reversed();
		work.stream().sorted(comparator)
			.forEach(osmway -> duplicatedToModify(osmway, osm, org));
	}
	
	/**
	 * 
	 * @param way	処理対象のPlateauのWay
	 * @param osm	Plateauデータ
	 * @param org	ORGデータ
	 */
	private void duplicatedToModify(Wayable osmway, OsmBean osm, OsmBean org) {
		long oldid = osmway.getId();
		if (!osmway.getFix()) {
			// osm.WAYと重複する面積が最大の org.WAY.id を返す
			long wayid;
			try {
				wayid = osmway.getIntersectMaxArea(org);
			} catch (Exception e) {
				wayid = 0;
			}
			if (wayid > 0) {
				try {
					List<NodeBean> nodes = org.getNodeList();
					for (Wayable sWay : org.getWayList(osmway)) {
						if (sWay.getId() == wayid) {
							
							// MLIT_PLATEAU:fixme="更新前です"
							// Issue #115
							Wayable preObject = sWay.clone();
							preObject.setId(preObject.getId() * -1);
							preObject.addTag(new TagBean("MLIT_PLATEAU:fixme", "更新前です"));
							List<NdBean> ndlist = preObject.getNdList();
							for (NdBean nd : ndlist) {
								for (NodeBean node : nodes) {
									if (nd.getRef() == node.getId()) {
										osm.putNode(node);
									}
								}
							}
							osm.putWay(preObject);
							
							// MLIT_PLATEAU:fixme="PLATEAUデータで更新されています"
							sWay.setFix(true);
							if (sWay.getTag("source") != null) {
								// Issue #56
								TagBean sourceTag = sWay.getTag("source");
								sWay.getPoiBean().removeTag("source");
								if (sourceTag.getValue().toLowerCase().contains("survey")) {
									sWay.getPoiBean().addTag("source", "survey");
								}
							}
							if (osmway.getTag("building") != null) {
								// Issue #56
								TagBean buildingTag = osmway.getTag("building");
								if (buildingTag.getValue().toLowerCase().equals("yes")) {
									osmway.getPoiBean().removeTag("building");
								}
							}
							if (osmway.getTag("start_date") != null) {
								// Issue #61 「start_date」は既存データ優先とする
								TagBean startTag = sWay.getTag("start_date");
								if (startTag != null) {
									osmway.getPoiBean().removeTag("start_date");
								}
							}
							if (osmway.getTag("name") != null) {
								// Issue #64 「name」は既存データ優先とする
								TagBean nameTag = sWay.getTag("name");
								if (nameTag != null) {
									osmway.getPoiBean().removeTag("name");
								}
							}
							sWay.getPoiBean().copyTag(osmway.getPoiBean());
							osmway.getPoiBean().copyTag(sWay.getPoiBean());
							sWay.addTag(new TagBean("MLIT_PLATEAU:fixme", "PLATEAUデータで更新されています"));
							sWay.getPoiBean().setAction("modify");
							sWay.getPoiBean().orignal = false;
							sWay.setNdList(osmway.getNdList());
							osm.removeWay(osmway);
							osm.putWay(sWay);
							
							for (RelationBean relation : osm.getRelationList()) {
								relation.setAction("modify");
								for (MemberBean member : relation.getMemberList()) {
									if (member.getRef() == oldid) {
										member.setWay(sWay);
									}
								}
								String buildingValue = sWay.getPoiBean().getTagValue("building");
								if (buildingValue == null || buildingValue.isEmpty()) {
									buildingValue = "yes";
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
							sWay.getPoiBean().setAction("modify");
							sWay.getPoiBean().orignal = true;
							for (NdBean nd : sWay.getNdList()) {
								NodeBean node = org.getNode(nd.getRef());
								if (node != null) {
									osm.putNode(node);
								}
							}
							osm.putWay(sWay);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 建物リレーションのタグを決定する
	 * 
	 */
	private void fixBuildingRelation(OsmBean mrg) throws Exception {
		// Relation: type=Multipolygon
		for (RelationBean relation : mrg.getRelationList()) {
			if (relation.isMultipolygon() ) {
				Wayable outer = null;
				Wayable way = null;
				for (MemberBean member : relation.getMemberList()) {
					if (member.getRole().equals("outer")) {
						if (member.isWay()) {
							outer = mrg.getWay(member.getRef());
							way = outer.clone();
							String building = outer.getPoiBean().getTagValue("building");
							if (building != null && building.equals("yes")) {
								way.addTag(new TagBean("building", outer.getPoiBean().getTagValue("building:part")));
							}
							way.getPoiBean().removeTag("building:part");
							way.getPoiBean().removeTag("MLIT_PLATEAU:fixme");
							way.getPoiBean().removeTag("ref:MLIT_PLATEAU");
						}
					}
				}
				if (way != null) {
					relation.copyTag(way.getPoiBean());
				}
			}
		}
		
		// Relation: type=building
		for (RelationBean relation : mrg.getRelationList()) {
			Wayable maxway = getMaxPart(relation, mrg);
			if (relation.isBuilding() ) {
				Wayable outline = null;
				RelationBean outer = null;
				for (MemberBean member : relation.getMemberList()) {
					if (member.getRole().equals("outline")) {
						if (member.isWay()) {
							outline = mrg.getWay(member.getRef());
						}
						else if (member.isRelation()) {
							outer = mrg.getRelation(member.getRef());
						}
					}
				}
				
				if (maxway != null) {
					Wayable way = maxway.clone();
					String build = maxway.getPoiBean().getTagValue("building");
					if (build == null || build.equals("yes")) {
						way.addTag(new TagBean("building", maxway.getPoiBean().getTagValue("building:part")));
					}
					PoiBean poi = way.getPoiBean();
					poi.removeTag("building:part");
					poi.removeTag("building:levels");
					poi.removeTag("building:levels:underground");
					poi.removeTag("height");
					poi.removeTag("ele");
					poi.removeTag("MLIT_PLATEAU:fixme");
					poi.removeTag("ref:MLIT_PLATEAU");
					poi.removeTag("start_date");		// Issue #39 複合ビルでの”建築年”の扱い
					if (outline != null) {
						outline.getPoiBean().copyTag(poi);
					}
					if (outer != null) {
						outer.copyTag(poi);
					}
					relation.copyTag(poi);
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
	 * マルチポリゴンの"outer"メンバーWAYには"building"タグをつけない[Issue #40]
	 * @param mrg
	 */
	private void fixMemberWaysTag(OsmBean mrg) {
		for (RelationBean relation : mrg.getRelationList()) {
			relation.setAction("modify");
			for (MemberBean member : relation.getMemberList()) {
				if (member.isWay()) {
					Wayable way = mrg.getWay(member.getRef());
					if (way != null) {
						if (member.getRole().equals("part")) {
							toPart(way.getPoiBean());
						}
						else if (member.getRole().equals("outline")) {
							List<TagBean> tags = new ArrayList<>();
							for (TagBean tag : way.getPoiBean().getTagList()) {
								if (tag.k.startsWith("MLIT_PLATEAU:fixme")) {
									tags.add(tag);
								}
								else if (tag.k.equals("addr:full")) {
									tags.add(tag);
								}
								else if (tag.k.equals("building:part")) {
									tags.add(tag);
									way.addTag(new TagBean("building", relation.getTag("building").getValue()));
								}
								else if (tag.k.equals("ele")) {
									tag.v = relation.getTag("ele").getValue();
								}
								else if (tag.k.equals("height")) {
									tag.v = relation.getTag("height").getValue();
								}
							}
							for (TagBean tag : tags) {
								way.getPoiBean().removeTag(tag.k);
							}
						}
						else if (member.getRole().equals("outer")) {
							List<TagBean> tags = new ArrayList<>();
							for (TagBean tag : way.getPoiBean().getTagList()) {
								if (tag.k.startsWith("MLIT_PLATEAU:fixme")) {
									// Do nothing
								}
								else {
									tags.add(tag);
								}
							}
							// {Issue#40]
							for (TagBean tag : tags) {
								way.getPoiBean().removeTag(tag.k);
							}
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
	 * 妥当性検証エラー：「リレーションメンバーのロールがプリセットBuildingのテンプレートの式'building=*'に含まれていません。」の対策。[Issue #119]
	 * @param mrg
	 */
	private void fix119(OsmBean mrg) {
		for (RelationBean relation : mrg.getRelationList()) {
			changeBuildingYes(relation.getTagList());
			for (MemberBean member : relation.getMemberList()) {
				if (member.getRole().equals("outline")) {
					if (member.isWay()) {
						Wayable way = mrg.getWay(member.getRef());
						changeBuildingYes(way.getPoiBean().getTagList());
					}
				}
			}
		}
	}

	void fix128(OsmBean mrg) {
		for (Wayable way : mrg.getWayList()) {
			List<TagBean> blacklist = new ArrayList<>();
			for (TagBean tag : way.getPoiBean().getTagList()) {
				String key = tag.getKey();
				try {
					if (key.equals("ele") || key.equals("height")) {
						if (CityModelParser.checkNumberString(tag.getValue()) == null) {
							blacklist.add(tag);
						}
					}
					else if (key.equals("building:levels") || key.equals("building:levels:underground")) {
						if (CityModelParser.checkNumberString(tag.getValue()) == null) {
							blacklist.add(tag);
						}
						else if (Integer.parseInt(tag.getValue()) == 0) {
							blacklist.add(tag);
						}
					}
				}
				catch (Exception e) {
					blacklist.add(tag);
				}
			}
			for (TagBean tag : blacklist) {
				way.getPoiBean().removeTag(tag.k);
			}
		}
	}
	
	private void changeBuildingYes(List<TagBean> tags) {
		List<TagBean> dels = new ArrayList<>();
		for (TagBean tag : tags) {
			if (tag.getKey().equals("building:part")) {
				dels.add(tag);
			}
			else if (tag.getKey().equals("building")) {
				dels.add(tag);
			}
		}
		if (dels.size() > 0) {
			for (TagBean tag : dels) {
				tags.remove(tag);
			}
		}
		tags.add(new TagBean("building","yes"));
	}

	/**
	 * 建物リレーションの"part"メンバー内で最大のWAYを取得する
	 * @param polygon
	 * @param mrg
	 * @return
	 */
	private Wayable getMaxPart(RelationBean polygon, OsmBean mrg) {
		double max = 0;
		Wayable maxway = null;
		for (MemberBean member : polygon.getMemberList()) {
			if (member.getRole().equals("part")) {
				if (member.isWay()) {
					Wayable way = mrg.getWay(member.getRef());
					if (way.getArea() > max) {
						maxway = way.clone();
					}
				}
			}
			else if (member.getRole().equals("outer")) {
				if (member.isWay()) {
					Wayable way = mrg.getWay(member.getRef());
					if (way.getArea() > max) {
						maxway = way.clone();
					}
				}
			}
		}
		return maxway;
	}
}
