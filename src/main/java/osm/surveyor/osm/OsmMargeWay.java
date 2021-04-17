package osm.surveyor.osm;

import java.util.ArrayList;
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
				for (OsmNd node : way.nds) {
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
	
	/**
	 * メンバーが一つしかないRelation:building を削除する
	 * @param relations
	 * @param ways
	 */
	public static void relationGabegi(
			HashMap<String, ElementRelation> relations, 
			HashMap<String, ElementWay> ways) 
	{
		while (relationRemove(relations, ways));
	}
	
	static boolean relationRemove(
			HashMap<String, ElementRelation> relations, 
			HashMap<String, ElementWay> ways) 
	{
		for (String rKey : relations.keySet()) {
			ElementRelation relation = relations.get(rKey);
			if (relation.members.size() < 2) {
				ArrayList<ElementTag> tags = new ArrayList<>();
				for (ElementTag tag : relation.tags) {
					if (!tag.k.equals("type")) {
						tags.add(tag);
					}
				}
				
				for (ElementMember member : relation.members) {
					String memberRef = Long.toString(member.ref);
					ElementWay way = ways.get(memberRef);
					for (ElementTag tag : tags) {
						way.addTag(tag.k, tag.v);
					}
					way.replaceTag(new ElementTag("building:part", "yes"), new ElementTag("building", "yes"));
				}
				
				relations.remove(rKey);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * - Relation->member:role=port のoutlineを作成する
	 * - 
	 * 
	 * @param relations
	 */
	public static void relationOutline(
			HashMap<String, ElementRelation> relations,
			HashMap<String, ElementWay> ways 
	) {
		for (String rKey : relations.keySet()) {
			ElementRelation relation = relations.get(rKey);
			ElementWay aWay = relation.createOutline(ways);
			
			// WAYをMEMBERとして追加する
			ElementMember member = new ElementMember();
			member.setWay(aWay);
			member.setRole("outline");
			relation.addMember(aWay, "outline");
			
			// OUTLINEをWAYリストに登録
			ways.put(Long.toString(aWay.id), aWay);
		}
	}
	
	
}
