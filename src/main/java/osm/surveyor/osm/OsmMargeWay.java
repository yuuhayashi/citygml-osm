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
		HashMap<String, String> ndMap = new HashMap<>();	// ndMap(key= node.id, v= relation.id)
		
		for (String rKey : relations.keySet()) {
			ElementRelation relation = relations.get(rKey);
			ElementTag typeTag = relation.tags.get("type");
			if ((typeTag == null) || !typeTag.v.equals("building")) {
				continue;
			}
			ElementMember polygonMember = null;
			for (ElementMember member : relation.members) {
				if (member.role.equals("outline")) {
					polygonMember = member;
					break;
				}
			}
			String maxheight = "0";
			for (ElementMember member : relation.members) {
				if (!member.role.equals("part")) {
					continue;
				}
				String memberRef = Long.toString(member.ref);
				ElementWay way = ways.get(memberRef);
				for (OsmNd node : way.nds) {
					String ndId = Long.toString(node.id);
					if (ndMap.containsKey(ndId)) {
						/* 'node :OsmNd'は、他のリレーションのWAYノードと共有している */
						// カレントリレーションのメンバーを共有先リレーション(v)へ追加
						String v = ndMap.get(ndId);	// 共有先のrelation.idstr
						ElementRelation destRelation = relations.get(v);
						if (!relation.getIdstr().equals(v)) {
							way.member = true;
							ElementTag height = way.tags.get("height");
							if (Double.parseDouble(height.v) > Double.parseDouble(maxheight)) {
								maxheight = height.v;
								destRelation.addTag("height", maxheight);
							}
							destRelation.addMember(way, "part");
							int i = relation.members.indexOf(member);	// カレントリレーションからメンバーを削除
							relation.members.remove(i);

							if (polygonMember != null) {
								RelationMultipolygon polygon = (RelationMultipolygon)relations.get(Long.toString(polygonMember.ref));
								destRelation.addMember(polygon, "outline");
								i = relation.members.indexOf(polygonMember);	// カレントリレーションからメンバーを削除
								relation.members.remove(i);
							}
							
							return false;
						}
					}
					else {
						ndMap.put(ndId, relation.getIdstr());
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
			if (relation.members.size() < 1) {
				relations.remove(rKey);
				return true;
			}
			if (relation.members.size() < 2) {
				for (ElementMember member : relation.members) {
					ArrayList<ElementTag> tags = new ArrayList<>();
					for (String k : relation.tags.keySet()) {
						ElementTag tag = relation.tags.get(k);
						if (!tag.k.equals("type")) {
							tags.add(tag);
						}
					}
					
					String memberRef = Long.toString(member.ref);
					if (member.role.equals("part")) {
						ElementWay way = ways.get(memberRef);
						way.member = true;
						for (ElementTag tag : tags) {
							way.addTag(tag.k, tag.v);
						}
						way.replaceTag(new ElementTag("building:part", "yes"), new ElementTag("building", "yes"));
						relations.remove(rKey);
						return true;
					}
					if (member.role.equals("outline")) {
						RelationMultipolygon polygon = (RelationMultipolygon)relations.get(memberRef);
						for (ElementTag tag : tags) {
							polygon.addTag(tag.k, tag.v);
						}
						polygon.addTag("building", "yes");
						polygon.replaceTag(new ElementTag("building:part", "yes"), new ElementTag("building", "yes"));
						relations.remove(rKey);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * "outline"と"part"が重複している`part` を削除する
	 * @param relations
	 * @param ways
	 */
	public static void partGabegi(
			HashMap<String, ElementRelation> relations, 
			HashMap<String, ElementWay> ways) 
	{
		while (partRemove(relations, ways));
		while (outlineRemove(relations, ways));
	}
	
	static boolean partRemove(
			HashMap<String, ElementRelation> relations, 
			HashMap<String, ElementWay> ways) 
	{
		for (String rKey : relations.keySet()) {
			ElementRelation relation = relations.get(rKey);
			RelationMultipolygon polygon = null;
			ElementWay outline = null;
			for (ElementMember member : relation.members) {
				if (member.role.equals("outline") && member.type.equals("relation")) {
					polygon = (RelationMultipolygon)relations.get(Long.toString(member.ref));
					for (ElementMember plgMem : polygon.members) {
						if (plgMem.role.equals("outer") && plgMem.type.equals("way")) {
							outline = ways.get(Long.toString(plgMem.ref));
							break;
						}
					}
				}
			}
			if (outline != null) {
				for (ElementMember member : relation.members) {
					if (member.role.equals("part") && member.type.equals("way")) {
						ElementWay partWay = ways.get(Long.toString(member.ref));
						if (partWay.isSame(outline)) {
							ElementTag ele = partWay.tags.get("height");
							if (ele != null) {
								outline.addTag("height", ele.v);
								polygon.addTag("height", ele.v);
							}
							if (partWay.id != outline.id) {
								ways.remove(partWay.getIdstr());
							}
							relation.members.remove(member);
							if (relation.members.isEmpty()) {
								for (String key : relation.tags.keySet()) {
									ElementTag tag = relation.tags.get(key);
									polygon.tags.put(key, tag);
									polygon.replaceTag(relation.tags.get("building:part"), new ElementTag("building", "yes"));
								}
								relations.remove(relation.getIdstr());
							}
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * "outline"しかない"building:part"を削除する
	 * @param relations
	 * @param ways
	 * @return
	 */
	static boolean outlineRemove(
			HashMap<String, ElementRelation> relations, 
			HashMap<String, ElementWay> ways) 
	{
		for (String rKey : relations.keySet()) {
			ElementRelation relation = relations.get(rKey);
			RelationMultipolygon polygon = null;
			ElementWay outline = null;
			if (relation.members.size() == 1) {
				for (ElementMember member : relation.members) {
					if (member.role.equals("outline") && member.type.equals("relation")) {
						polygon = (RelationMultipolygon)relations.get(Long.toString(member.ref));
						for (ElementMember plgMem : polygon.members) {
							if (plgMem.role.equals("outer") && plgMem.type.equals("way")) {
								outline = ways.get(Long.toString(plgMem.ref));
								break;
							}
						}
					}
				}
				if (outline != null) {
					for (String key : relation.tags.keySet()) {
						ElementTag tag = relation.tags.get(key);
						if (tag.k.equals("type")) {
						}
						else if (tag.k.equals("building:part")) {
						}
						else {
							polygon.addTag(key, tag.v);
						}
					}
					relations.remove(relation.getIdstr());
					return true;
				}
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
			if (aWay == null) {
				continue;
			}
			
			// OUTLINEをWAYリストに登録
			aWay.member = true;
			aWay.tags = new HashMap<String,ElementTag>();
			ways.put(Long.toString(aWay.id), aWay);
			
			// "outline"が存在する場合は、マルチポリゴンにaWayを追加する
			boolean done = false;
			for (ElementMember mem : relation.members) {
				if (mem.type.equals("relation")) {
					RelationMultipolygon multi = (RelationMultipolygon)relations.get(Long.toString(mem.ref));
					if (multi != null) {
						multi.addMember(aWay, "outer");
						done = true;
					}
				}
			}
			if (!done) {
				// "outline"が存在しない場合は、WAYをMEMBERとして追加する
				aWay.addTag("building:part", "yes");
				relation.addMember(aWay, "outline");
			}
		}
	}
}
