package osm.surveyor.osm.camel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import com.google.common.collect.Lists;

import osm.surveyor.osm.BodyMap;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.NdBean;
import osm.surveyor.osm.NodeBean;
import osm.surveyor.osm.OsmBean;
import osm.surveyor.osm.RelationBean;
import osm.surveyor.osm.WayBean;

public class OsmBuildingFilterProcessor implements Processor {

	/**
	 * "building"関係のPOIのみに絞る
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		String filename = (String) exchange.getProperty(Exchange.FILE_NAME);
		BodyMap map = exchange.getIn().getBody(BodyMap.class);
		OsmBean org = (OsmBean) map.get("org");
		
		Map<Long,RelationBean> relationmap = new HashMap<>();
		Map<Long,WayBean> waymap = new HashMap<>();
		Map<Long,NodeBean> nodemap = new HashMap<>();

		// "building"リレーションのみ抽出する
		List<RelationBean> buildingRelations = Lists.newArrayList();
		for (RelationBean relation : org.getRelationList()) {
			if (relation.isBuilding()) {
				buildingRelations.add(relation);
			}
			else if (relation.isMultipolygon()) {
				if (relation.getTagValue("building") != null) {
					buildingRelations.add(relation);
				}
				else if (relation.getTagValue("building:part") != null) {
					buildingRelations.add(relation);
				}
			}
		}

		// "building"リレーションのメンバーリレーションを抽出
		for (RelationBean relation : buildingRelations) {
			List<MemberBean> members = Lists.newArrayList();
			members.addAll(relation.getMemberList());
			for (MemberBean member : members) {
				if (member.isRelation()) {
					long id = member.getRef();
					RelationBean v = org.getRelation(id);
					if (v != null) {
						relationmap.putIfAbsent(id, v);
					}
				}
			}
			relationmap.putIfAbsent(relation.getId(), relation);
		}
		
		// "building"WAYを抽出
		for (WayBean way : org.getWayList()) {
			if (way.isBuilding()) {
				waymap.putIfAbsent(way.getId(), way);
			}
		}
		
		// ビルディングリレーションのメンバーウェイを抽出
		for(HashMap.Entry<Long, RelationBean> entry : relationmap.entrySet()) {
			RelationBean relation = entry.getValue();
			List<MemberBean> members = Lists.newArrayList();
			members.addAll(relation.getMemberList());
			for (MemberBean member : members) {
				if (member.isWay()) {
					long id = member.getRef();
					WayBean v = org.getWay(id);
					if (v != null) {
						waymap.put(id, v);
					}
				}
			}
        }
		
		// ウェイのメンバーノードを抽出
		for(HashMap.Entry<Long, WayBean> entry : waymap.entrySet()) {
			WayBean way = entry.getValue();
			List<NdBean> nds = Lists.newArrayList();
			nds.addAll(way.getNdList());
			for (NdBean nd : nds) {
				long ref = nd.getRef();
				NodeBean v = org.getNode(ref);
				if (v != null) {
					nodemap.putIfAbsent(ref, v);
				}
			}
		}

		// リレーションをMapからListに変換して格納
		List<RelationBean> newRelations = Lists.newArrayList();
		for(HashMap.Entry<Long, RelationBean> entry : relationmap.entrySet()) {
			newRelations.add(entry.getValue());
        }
		org.setRelationList(newRelations);
		
		// ウェイをMapからListに変換して格納
		List<WayBean> newWays = Lists.newArrayList();
		for(HashMap.Entry<Long, WayBean> entry : waymap.entrySet()) {
			newWays.add(entry.getValue());
        }
		org.setWayList(newWays);

		// ノードをMapからListに変換して格納
		List<NodeBean> newNodes = Lists.newArrayList();
		for(HashMap.Entry<Long, NodeBean> entry : nodemap.entrySet()) {
			newNodes.add(entry.getValue());
        }
		org.setNodeList(newNodes);

        map.put("org", org);
		exchange.getIn().setBody(map);
		exchange.setProperty(Exchange.FILE_NAME, filename);
	}
}
