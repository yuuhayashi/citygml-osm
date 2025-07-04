package osm.surveyor.osm.marge;

import java.util.ArrayList;
import java.util.HashMap;

import osm.surveyor.osm.MemberBean;
import osm.surveyor.gis.point.NdModel;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.OsmLine;
import osm.surveyor.osm.TwoPoint;
import osm.surveyor.osm.WayMap;

public class MargeFactory {
	WayMap wayMap;
	OsmLine list;
	OsmLine outer;
	ArrayList<OsmLine> inners;
	OsmDom osm;
	
	public MargeFactory(OsmDom osm, WayMap wayMap) {
		super();
		this.osm = osm;
		this.wayMap = wayMap;
		this.list = new OsmLine();
		this.outer = null;
		this.inners = new ArrayList<>();
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isDuplicateSegment() {
		for (String wayid : wayMap.keySet()) {
			ElementWay way = (ElementWay)wayMap.get(wayid);
			if (way != null) {
		    	OsmLine bList = way.getPointList();
		    	if (checkSegment(bList)) {
		    		return true;
		    	}

				// listにbWayを統合する
		    	for (TwoPoint bSegment : bList) {
		    		this.list.add(bSegment);
		    	}
			}
		}
		return false;
	}

    /**
     * List<LineSegment>と重複するセグメントを探す
     * @param sList		対象のライン
     * @return	削除したらTrue
     */
    private boolean checkSegment(OsmLine bList) {
    	for (TwoPoint aSegment : this.list) {
    		while (checkSegment(bList, aSegment)) {
    			return true;
    		}
    	}
    	return false;
    }

    /**
     * List<LineSegment>とセグメントが重複するかを調べる
     * @param sList		対象のライン
     * @param segment	調査対象セグメント
     * @return	削除したらTrue
     */
    private boolean checkSegment(OsmLine sList, TwoPoint segment) {
    	if (sList == null) {
    		return false;
    	}
    	for (TwoPoint sPoint : sList) {
    		if (sPoint.equal(segment)) {
    			return true;
    		}
    	}
    	return false;
    }
    
	/**
	 * Relation->member:role=port のoutlineを作成する
	 * @param building
	 * @param ways
	 */
	public MargeFactory createOutline(ElementRelation relation) {
		WayMap memberways = new WayMap();
		for (MemberBean member : relation.members) {
			if (member.getRole().equals("part")) {
				memberways.put(wayMap.get(member.getRef()).clone());
			}
		}
		
		// WAYを他のWAYと合成する;
		MargeFactory factory = new MargeFactory(this.osm, memberways);
		factory.marge();
		return factory;
	}
	
    /**
     * wayMapのWAYを合成して、OUTERとINNERを作成する;
     * WayMap wayMap --> 
     */
	public void marge() {
		for (String wayid : wayMap.keySet()) {
			ElementWay way = (ElementWay)wayMap.get(wayid);
			if (way != null) {
				// ２つのWAYから共通する線分を削除して統合する
				removeDuplicatedSegment(way.copy(osm.getNewId()));
			}
		}
		
		HashMap<String,OsmLine> lines = new HashMap<>();
		int i = 0;
		do {
			OsmLine line = new OsmLine();
			TwoPoint segment = getFirstSegment();
			if (segment != null) {
		    	line.add(segment);	// 先頭の線分をLISTから取り出す。

				// lineの末尾に接続可能な線分をlistから取り出してlineに追加する
				// 接続可能な線分がなくなるまで続ける
				while (connectSegments(line) != null);
				lines.put(Integer.toString(++i), line);
			}
		} while(!this.list.isEmpty());

		// linesをOUTERとINNERに分別する
		String lineId = null;
		double max = -90.0d;
		for (String key : lines.keySet()) {
			OsmLine line = lines.get(key);
			if (line.getMaxLat() > max) {
				lineId = key;
				max = line.getMaxLat();
			}
		}
		if (lineId != null) {
			this.outer = lines.get(lineId);
			for (String key : lines.keySet()) {
				if (!key.equals(lineId)) {
					this.inners.add(lines.get(key));
				}
			}
		}
	}
	
	public OsmLine getOuter() {
		return this.outer;
	}
	
	public ArrayList<OsmLine> getInners() {
		return this.inners;
	}
	
    /**
     * ２つのWAYから共通する線分を削除する
     * @param sList		対象のライン
     * @param segment	削除するセグメント
     * @return	削除したらTrue
     */
    private void removeDuplicatedSegment(final ElementWay bWay) {
    	OsmLine bList = bWay.getPointList();
    	
    	// 削除する線分がなくなるまで続ける
    	while (removeSegment(bList));

		// listにbWayを統合する
    	for (TwoPoint bSegment : bList) {
    		list.add(bSegment);
    	}
    }
    
    /**
     * 先頭の線分をLISTから取り出す。
     * @return
     */
    private TwoPoint getFirstSegment() {
    	for (TwoPoint aSegment : list) {
    		// 取り出した線分をLISTから削除する
    		list.remove(aSegment);
    		return aSegment;
    	}
    	return null;
    }

    /**
     * slistの末尾に接続可能な線分をlistから取り出してslistに追加する
     */
    private TwoPoint connectSegments(OsmLine slist) {
    	TwoPoint p;
    	if (slist.isEmpty()) {
    		return null;
    	}
    	TwoPoint segment = slist.get(slist.size()-1);
    	while ((p = getAndRemoveConnectableSegments(list, segment.b)) != null) {
    		if (!p.a.equals(segment.b)) {
    			p.reverse();
    		}
    		slist.add(p);
    		return p;
    	}
    	return null;
    }
    
    /**
     * List<LineSegment>から指定されたセグメントをひとつ削除する
     * @param sList		対象のライン
     * @param segment	削除するセグメント
     * @return	削除したらTrue
     */
    private boolean removeSegment(OsmLine bList) {
    	for (TwoPoint aSegment : list) {
    		while (removeSegment(bList, aSegment)) {
    			list.remove(list.indexOf(aSegment));
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * List<LineSegment>から指定されたセグメントを削除する
     * @param sList		対象のライン
     * @param segment	削除するセグメント
     * @return	削除したらTrue
     */
    private boolean removeSegment(OsmLine sList, TwoPoint segment) {
    	if (sList == null) {
    		return false;
    	}
    	for (TwoPoint sPoint : sList) {
    		if (sPoint.equal(segment)) {
    			sList.remove(sList.indexOf(sPoint));
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * 'segment.b'に接続可能なsegmentを'list'から取得,
     * 取得したsegmentはlistから削除される
     * @param list
     * @param segment
     * @return 接続可能なsegment, 存在しなければnull
     */
    private TwoPoint getAndRemoveConnectableSegments(OsmLine list, NdModel point) {
    	for (TwoPoint p : list) {
    		if (p.a.equals(point) || p.b.equals(point)) {
    			list.remove(list.indexOf(p));
    			return p;
    		}
    	}
    	return null;
    }
}
