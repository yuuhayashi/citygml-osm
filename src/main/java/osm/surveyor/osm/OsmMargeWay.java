package osm.surveyor.osm;

import java.util.HashMap;

public class OsmMargeWay {

    /**
     * 各WAYのノードで、他のWAYと共有されたノードを探す
     */
	public static HashMap<String, ElementRelation> relationMarge(HashMap<String, ElementRelation> relations, HashMap<String, ElementWay> ways) {
		while(!relationMarge1(relations, ways));
		return relations;
	}
	
	static boolean relationMarge1(HashMap<String, ElementRelation> relations, HashMap<String, ElementWay> ways) {
		HashMap<String, String> ndMap = new HashMap<>();
		
		for (String rKey : relations.keySet()) {
			ElementRelation relation = relations.get(rKey);
			String relationId = Long.toString(relation.id);
			for (ElementMember member : relation.members) {
				String memberRef = Long.toString(member.ref);
				ElementWay way = ways.get(memberRef);
				for (ElementNode node : way.nodes) {
					String ndId = Long.toString(node.id);
					if (ndMap.containsKey(ndId)) {
						// カレントメンバーをVリレーションへ追加
						String v = ndMap.get(ndId);
						ElementRelation destRelation = relations.get(v);
						if (!relationId.equals(v)) {
							destRelation.addMember(way, "part");
							
							// カレントリレーションからメンバーを削除
							int i = relation.members.indexOf(member);
							relation.members.remove(i);
							return false;
						}
					}
					else {
						ndMap.put(ndId, relationId);
					}
				}
			}
		}
		return true;
	}
}
