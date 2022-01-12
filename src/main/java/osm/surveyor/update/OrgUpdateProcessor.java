package osm.surveyor.update;

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
		
		// インポートデータの内で、Fix=trueの既存データと重複するものを削除
		// その後、既存データからFix=trueのPOIを削除する
		removeFixed(osm, org);

		// 既存データの内で、インポートデータと重複しないものを削除
		removeNotDuplicated(osm, org);
    		
		// すべてのノードを'modify'に確定する
		fixToModify(osm);

		// インポートデータの内で、既存データと重複するWAYを'modify'に確定する
		duplicatedToModify(osm, org);

		for (RelationBean relation : osm.getRelationList()) {
			relation.setAction("modify");
			for (MemberBean member : relation.getMemberList()) {
				WayBean way = osm.getWay(member.getRef());
				if (way != null) {
					if (member.getRole().equals("part")) {
						toPart(way);
					}
					else if (member.getRole().equals("outline")) {
						toPart(way);
					}
					else if (member.getRole().equals("outer")) {
						List<TagBean> tags = new ArrayList<>();
						for (TagBean tag : way.getTagList()) {
							if (tag.k.startsWith("building")) {
								relation.addTag(tag.k, tag.v);
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
		}

		// WAYに所属していないNODEを削除する
		osm.gerbageNode();
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
					WayBean sWay = org.getWay(wayid);
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
