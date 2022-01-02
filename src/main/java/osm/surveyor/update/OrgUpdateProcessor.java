package osm.surveyor.update;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import osm.surveyor.osm.BodyMap;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.NodeBean;
import osm.surveyor.osm.OsmBean;
import osm.surveyor.osm.OsmNd;
import osm.surveyor.osm.PoiBean;
import osm.surveyor.osm.TagBean;
import osm.surveyor.osm.WayBean;
import osm.surveyor.osm.WayMap;

public class OrgUpdateProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		BodyMap map = exchange.getIn().getBody(BodyMap.class);
		OsmBean osm = (OsmBean) map.get("osm");		// dom
		OsmBean org = (OsmBean) map.get("org");		// sdom
		OsmBean mrg = new OsmBean();				// ddom
		
		
		// TODO Auto-generated method stub
		
		// 既存データの内で、インポートデータと重複しないものを削除
		List<WayBean> list = new ArrayList<>();
		for (WayBean way : org.getWayList()) {
			if (!way.isIntersect(osm.getWayList())) {
				list.add(way);
			}
		}
		for (WayBean way : list) {
			org.getWayList().remove(way.getId());
		}
    		
		// インポートデータのすべてのノードを'modify'に確定する
		for (NodeBean node : osm.getNodeList()) {
			node.setAction("modify");
			node.orignal = false;
			replacePoi(mrg.getNodeList(), node.clone());
		}

    		// インポートデータの内で、既存データと重複しないものを'modify'に確定する
		List<WayBean> modifyList = new ArrayList<>();
		for (WayBean way : osm.getWayList()) {
			if (way.isInnerWay(osm.getRelationList())) {
				modifyList.add(way);
			}
			else if (!way.isIntersect(sdom.ways)) {
				modifyList.add(way);
			}
		}
		for (ElementWay way : modifyList) {
			way.setAction("modify");
			way.orignal = false;
			ddom.ways.put(way);
		}
    		
		// OverlappingMap:WayMap {key=dom.way.id, v=ddom.way}
		WayMap overlappingMap = new WayMap();
		
    		// インポートデータの内で、既存データと重複するWAYを'modify'に確定する
		for (String rKey : dom.ways.keySet()) {
			ElementWay way = dom.ways.get(rKey).clone();
			if ((way.getAction() == null) || !way.getAction().equals("modify")) {
    			long wayid = way.getIntersect(sdom.ways);
    			if (wayid > 0) {
    				ElementWay sWay = sdom.ways.get(wayid);
    				sWay.setAction("modify");
    				sWay.orignal = false;
    				sWay.nds = way.nds;
            		TagBean tag = sWay.getTag("fixme");
            		if (tag != null) {
            			String fixme = tag.v;
            			tag.v = fixme +"; PLATEAUデータで更新されています";
            		}
            		else {
            			tag = new TagBean("fixme", "PLATEAUデータで更新されています");
            		}
            		TagBean ref = way.getTag("ref:MLIT_PLATEAU");
            		TagBean height = way.getTag("height");
            		sWay.addTag(tag);
            		sWay.addTag(ref);
            		sWay.addTag(height);
    				way.copyTag(sWay);
            		sWay.copyTag(way);
            		ddom.ways.put(sWay);
            		overlappingMap.put(Long.toString(way.getId()), sWay);
    			}
			}
		}
		
    		// インポートデータの内で、既存データと重複するMultipolygonを'modify'に確定する
		// インポートデータの内で、既存データと重複するbuilding RELATIONを'modify'に確定する
		for (String rKey : dom.relations.keySet()) {
			ElementRelation relation = dom.relations.get(rKey);
			if (relation.isMultipolygon()) {
    			for (MemberBean member : relation.members) {
					if (member.getType().equals("way")) {
        				ElementWay sWay = overlappingMap.get(member.getRef());
            			if (sWay != null) {
                    		sWay.toMultipolygonMemberTag();
            				member.setRef(sWay.getId());
            			}
					}
    			}
			}
			else if (relation.isBuilding()) {
				for (MemberBean member : relation.members) {
					if (member.getType().equals("way")) {
        				ElementWay sWay = overlappingMap.get(member.getRef());
        				if (sWay != null) {
            				member.setRef(sWay.getId());
        				}
					}
				}
			}
			relation.setAction("modify");
			ddom.relations.put(relation);
		}
		
		// 既存データで、actionが未定のまま残存しているWAYを削除する
		for (String rKey : sdom.ways.keySet()) {
			ElementWay sWay = sdom.ways.get(rKey);
			if (sWay.getAction() == null) {
				sWay.setAction("delete");
				sWay.orignal = false;
        		TagBean tag = sWay.getTag("fixme");
        		if (tag != null) {
        			String fixme = tag.v;
        			tag.v = fixme +"; PLATEAUデータで置き換えられました";
        		}
        		else {
        			tag = new TagBean("fixme", "PLATEAUデータで置き換えられました");
        		}
        		sWay.addTag(tag);
        		for (OsmNd nd : sWay.nds) {
        			NodeBean node = sdom.nodes.get(nd.id);
        			node.setAction("delete");
        			ddom.nodes.put(node.clone());
        		}
        		ddom.ways.put(sWay.clone());
			}
		}
	}

	void replaceNode(List<NodeBean> list, NodeBean poi) {
		for (NodeBean w : list) {
			if (w.getId() == poi.getId()) {
				list.remove(w);
				break;
			}
		}
		list.add(poi);
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
