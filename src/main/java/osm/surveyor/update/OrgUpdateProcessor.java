package osm.surveyor.update;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import osm.surveyor.osm.BodyMap;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.NodeBean;
import osm.surveyor.osm.OsmBean;
import osm.surveyor.osm.RelationBean;
import osm.surveyor.osm.TagBean;
import osm.surveyor.osm.WayBean;

public class OrgUpdateProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		BodyMap map = exchange.getIn().getBody(BodyMap.class);
		OsmBean osm = (OsmBean) map.get("osm");		// dom
		OsmBean org = (OsmBean) map.get("org");		// sdom
		OsmBean mrg = (OsmBean) map.get("mrg");		// ddom
		
		// インポートデータの内で、Fix=trueの既存データと重複するものを削除
		removeFixed(osm, org);

		// 既存データの内で、インポートデータと重複しないものを削除
		removeNotDuplicated(osm, org);
    		
		// 既存データのすべてのノードを'modify'に確定する
		fixToModify(org);

		mrg.setNodeList(osm.getNodeList());
		
		// インポートデータの内で、既存データと重複しないものを'modify'に確定する
		notDuplicatedToModify(osm, org, mrg);
    		
		// インポートデータの内で、既存データと重複するWAYを'modify'に確定する
		// インポートデータの内で、既存データと重複するMultipolygonを'modify'に確定する
		// インポートデータの内で、既存データと重複するbuilding RELATIONを'modify'に確定する
		OsmBean overlapping = new OsmBean();
		duplicatedToModify(osm, org, mrg, overlapping);
		fixToModify(osm, org, mrg, overlapping);
		
		// actionが未定のまま残存しているWAYを削除する
		removeNoAction(mrg);

		// WAYに所属していないNODEを削除する
		mrg.gerbageNode();
	}
	
	/**
	 * インポートデータの内で、Fix=trueの既存データと重複するものを削除
	 * @throws Exception 
	 */
	private void removeFixed(OsmBean osm, OsmBean org) throws Exception {
		for (WayBean obj : org.getWayList()) {
			if (obj.getFix()) {
				// `fix=true`
				while (obj.isIntersect(osm.getWayList())) {
					long wayid = obj.getIntersect(osm.getWayList());
					WayBean way = osm.getWay(wayid);
					List<RelationBean> relations = osm.getParents(way);
					for (RelationBean relation : relations) {
						osm.removeRelation(relation);
					}
					osm.removeWay(osm.getWay(wayid));
				}
			}
		}
	}
	
	/**
	 * 既存データの内で、インポートデータと重複しないものを削除
	 * @throws Exception 
	 */
	@SuppressWarnings("unlikely-arg-type")
	private void removeNotDuplicated(OsmBean osm, OsmBean org) throws Exception {
		List<WayBean> list = new ArrayList<>();
		for (WayBean way : org.getWayList()) {
			if (!way.isIntersect(osm.getWayList())) {
				list.add(way);
			}
		}
		for (WayBean way : list) {
			org.getWayList().remove(way.getId());
		}
	}
	
	/**
	 * 既存データのすべてのノードを'modify'に確定する
	 */
	private void fixToModify(OsmBean org) {
		for (NodeBean node : org.getNodeList()) {
			node.setAction("modify");
			node.orignal = false;
		}
	}
	
	/**
	 * インポートデータの内で、既存データと重複しないものを'modify'に確定する
	 * @throws Exception 
	 */
	private void notDuplicatedToModify(OsmBean osm, OsmBean org, OsmBean mrg) throws Exception {
		List<WayBean> modifyList = new ArrayList<>();
		
		// リレーションに所属しないWAYを処理する
		for (WayBean way : osm.getWayList()) {
			if (!way.isInnerWay(osm.getRelationList())) {
				if (!way.isIntersect(org.getWayList())) {
					modifyList.add(way);
				}
			}
		}
		for (WayBean way : modifyList) {
			way.setAction("modify");
			way.orignal = false;
			mrg.putWay(way);
			osm.removeWay(way);
		}
		
		// TODO リレーションに所属するWAYも同様に処理する
	}
	
	/**
	 * インポートデータの内で、既存データと重複するWAYを'modify'に確定する
	 * @throws Exception 
	 */
	private void duplicatedToModify(OsmBean osm, OsmBean org, OsmBean mrg, OsmBean overlapping) throws Exception {
		for (WayBean way : osm.getWayList()) {
			long wayid = way.getIntersect(org.getWayList());
			if (wayid > 0) {
				WayBean sWay = org.getWay(wayid);
				sWay.setAction("modify");
				sWay.orignal = false;
				sWay.setNdList(way.getNdList());
        		sWay.addTag(new TagBean("MLIT_PLATEAU:fixme", "PLATEAUデータで更新されています"));
        		sWay.addTag(way.getTag("ref:MLIT_PLATEAU"));
        		sWay.addTag(way.getTag("height"));
				way.copyTag(sWay);
        		sWay.copyTag(way);
        		mrg.putWay(sWay);
        		overlapping.putWay(sWay);
			}
		}
	}
	
	/**
	 * インポートデータの内で、既存データと重複するMultipolygonを'modify'に確定する
	 * インポートデータの内で、既存データと重複するbuilding RELATIONを'modify'に確定する
	 */
	private void fixToModify(OsmBean osm, OsmBean org, OsmBean mrg, OsmBean overlapping) throws Exception {
		for (RelationBean relation : osm.getRelationList()) {
			if (relation.isMultipolygon()) {
    			for (MemberBean member : relation.getMemberList()) {
					if (member.getType().equals("way")) {
        				WayBean sWay = overlapping.getWay(member.getRef());
            			if (sWay != null) {
                    		sWay.toMultipolygonMemberTag();
            				member.setRef(sWay.getId());
            			}
					}
    			}
			}
			else if (relation.isBuilding()) {
				for (MemberBean member : relation.getMemberList()) {
					if (member.getType().equals("way")) {
        				WayBean sWay = overlapping.getWay(member.getRef());
        				if (sWay != null) {
            				member.setRef(sWay.getId());
        				}
					}
				}
			}
			relation.setAction("modify");
			mrg.putRelation(relation);
		}
	}

	/**
	 * actionが未定のまま残存しているWAYを削除する
	 */
	private void removeNoAction(OsmBean mrg) {
		List<WayBean> removeList = new ArrayList<>();
		for (WayBean sWay : mrg.getWayList()) {
			if (sWay.getAction() == null) {
				removeList.add(sWay);
			}
		}
    	for (WayBean obj : removeList) {
    		mrg.getWayList().remove(mrg.getWayList().indexOf(obj));
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
