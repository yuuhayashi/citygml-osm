package osm.surveyor.osm.camel;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.camel.builder.RouteBuilder;

import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.NodeBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.TagBean;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.OsmNd;
import osm.surveyor.osm.WayMap;

public class OsmUpdaterRoute extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		onException(Exception.class)
        .handled(false)
        .log("Error")
        .setBody().constant("なにかエラーが発生")
        .log("Error: ${body}")
        ;
		
		// TODO Auto-generated method stub
		
	}
	
	// TODO

	public OsmDom dom;		// source "*.osm" file.	
	public OsmDom sdom;
	public OsmDom ddom;
	
	/**
	 * 既存POIとマージする
	 * @throws Exception 
	 */
	public void load() throws Exception {
		// 既存データの内で、インポートデータと重複しないものを削除
		ArrayList<ElementWay> list = new ArrayList<>();
		for (String rKey : sdom.ways.keySet()) {
			ElementWay way = sdom.ways.get(rKey);
			if (!way.isIntersect(dom.ways)) {
				list.add(way);
			}
		}
		for (ElementWay way : list) {
			sdom.ways.remove(way.getId());
		}
    		
		// インポートデータのすべてのノードを'modify'に確定する
		for (NodeBean node : dom.nodes) {
			node.setAction("modify");
			node.orignal = false;
			ddom.nodes.put(node.clone());
		}

    		// インポートデータの内で、既存データと重複しないものを'modify'に確定する
		ArrayList<ElementWay> modifyList = new ArrayList<>();
		for (String rKey : dom.ways.keySet()) {
			ElementWay way = dom.ways.get(rKey);
			if (way.isInnerWay(dom.relations)) {
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
