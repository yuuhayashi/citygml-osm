package osm.surveyor.osm;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ElementWay implements Cloneable {
	public long id = 0;
	public String action = "modify";
	public boolean visible = true;
	public ArrayList<ElementNode> nodes;
	public ArrayList<ElementTag> tags;
	boolean area = false;
	
	public ElementWay(long id) {
		this.id = id;
		nodes = new ArrayList<ElementNode>();
		tags = new ArrayList<ElementTag>();
	}
	
	@Override
	public ElementWay clone() {
		ElementWay copy = null;
		try {
			copy = (ElementWay)super.clone();
			copy.nodes = new ArrayList<ElementNode>();
			if (this.nodes != null) {
				for (ElementNode node : this.nodes) {
					copy.nodes.add(node.clone());
				}
			}
			copy.tags = new ArrayList<ElementTag>();
			if (this.tags != null) {
				for (ElementTag tag : this.tags) {
					copy.tags.add(tag.clone());
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			copy = null;
		}
		return copy;
	}
	
	public void addTag(String k, String v) {
		this.tags.add(new ElementTag(k, v));
	}
	
	public void addNode(ElementNode node) {
		this.nodes.add(node);
	}
	
	/**
	 * LINEをAREAに変更します
	 * 最期のノードが最初のノードと同じなら、削除する
	 * 最期のノードが最初のノードが異なるなら、つなげる
	 */
	public void toArea() {
		int size = nodes.size();
		ElementNode frst = nodes.get(0);
		ElementNode last = nodes.get(size - 1);
		if (size > 3) {
			if (frst.id != last.id) {
				if ((frst.point.lat.equals(last.point.lat)) && (frst.point.lon.equals(last.point.lon))) {
					nodes.remove(size - 1);
					nodes.add(frst);
				}
			}
			area = true;
		}
		if (size == 3) {
			if ((frst.point.lat.equals(last.point.lat)) && (frst.point.lon.equals(last.point.lon))) {
				nodes.remove(size - 1);
				area = false;
				return;
			}
			else {
				if (frst.id != last.id) {
					nodes.add(frst);
				}
				area = true;
			}
		}
	}
	
	/*
	 * 
		<way id='-2' action='modify' visible='true'>
			<nd ref='-3'/>
			<nd ref='-4'/>
			<nd ref='-5'/>
			<tag k='height' v='14.072000000000001' />
			<tag k='building:part' v='yes' />
		</way>
	 */
    public Node toNode(Document doc) {
		Element node = doc.createElement("way");
        node.setAttribute("id", Long.toString(id));
        node.setAttribute("action", action);
        node.setAttribute("visible", Boolean.toString(visible));
		for (ElementNode nd : this.nodes) {
			node.appendChild(nd.toNodeNd(doc));
		}
		for (ElementTag tag : this.tags) {
			node.appendChild(tag.toNodeNd(doc));
		}
        return (Node)node;
    }
    
    /**
     * sourceと一致するTAGを、destに置き換える
     * @param source
     * @param dest
     */
    public void replaceTag(ElementTag source, ElementTag dest) {
    	for (ElementTag tag : tags) {
    		if (tag.k.equals(source.k)) {
    			if (tag.v.equals(source.v)) {
    				tags.add(dest);
    				tags.remove(tags.indexOf(tag));
    				return;
    			}
    		}
    	}
    }
    
    /**
     * 線分(TwoPoint)リスト
     */
    public ArrayList<TwoPoint> getPointList() {
    	ArrayList<TwoPoint> pointlist = new ArrayList<>();
    	ElementNode a = null;
    	ElementNode b = null;
    	for (ElementNode node : this.nodes) {
    		if (a == null) {
    			a = node;
    		}
    		else {
    			if (b == null) {
    				b = node;
    			}
    			else {
    				a = b.clone();
    				b = node;
    			}
    		}
    		if ((a != null) && (b != null)) {
        		pointlist.add(new TwoPoint().set(a, b));
    		}
    	}
    	return pointlist;
    }
    
    /**
     * WAYを他のWAYと合成する;
     */
    public ElementWay marge(ElementWay bWay) {
    	// ２つのWAYから共通する線分を削除する
		removeDuplicatedSegment(bWay);
    	return this;
    }
    
    /**
     * slistの末尾に接続可能な線分をalistから取り出してslistに追加する
     */
    private TwoPoint connectSegments(ArrayList<TwoPoint> slist, ArrayList<TwoPoint> alist) {
    	TwoPoint p;
    	TwoPoint segment = slist.get(slist.size()-1);
    	while ((p = getAndRemoveConnectableSegments(alist, segment.b)) != null) {
    		if (!p.a.equals(segment.b)) {
    			p.reverse();
    		}
    		slist.add(p);
    		return p;
    	}
    	return null;
    }
    
    /**
     * 'segment.b'に接続可能なsegmentを'list'から取得,
     * 取得したsegmentはlistから削除される
     * @param list
     * @param segment
     * @return 接続可能なsegment, 存在しなければnull
     */
    private TwoPoint getAndRemoveConnectableSegments(ArrayList<TwoPoint> list, ElementNode point) {
    	for (TwoPoint p : list) {
    		if (p.a.equals(point) || p.b.equals(point)) {
    			list.remove(list.indexOf(p));
    			return p;
    		}
    	}
    	return null;
    }
    
    /**
     * ２つのWAYから共通する線分を削除する
     * @param sList		対象のライン
     * @param segment	削除するセグメント
     * @return	削除したらTrue
     */
    private void removeDuplicatedSegment(ElementWay bWay) {
    	ArrayList<TwoPoint> list = new ArrayList<>();
    	ArrayList<TwoPoint> aList = getPointList();
    	ArrayList<TwoPoint> bList = bWay.getPointList();
    	for (TwoPoint aSegment : aList) {
    		list.add(aSegment);
    	}
    	for (TwoPoint aSegment : list) {
    		while (removeSegment(bList, aSegment)) {
        		while (removeSegment(aList, aSegment));
    		}
    	}

		// aListにbWayを統合する
    	for (TwoPoint bSegment : bList) {
    		aList.add(bSegment);
    	}

    	ArrayList<TwoPoint> sList = new ArrayList<>();
    	for (TwoPoint aSegment : aList) {
    		// 先頭の線分をLISTから取り出す。
    		sList.add(aSegment);
    		aList.remove(aList.indexOf(aSegment));
        	break;
    	}
    	while (connectSegments(sList, aList) != null);
    	
    	// wayのLINEを sList に書き換える
    	this.nodes = new ArrayList<>();
    	for (TwoPoint segment : sList) {
    		if (this.nodes.isEmpty()) {
    			this.nodes.add(segment.a);
    		}
			this.nodes.add(segment.b);
    	}
    }

    /**
     * List<LineSegment>から指定されたセグメントを削除する
     * @param sList		対象のライン
     * @param segment	削除するセグメント
     * @return	削除したらTrue
     */
    private boolean removeSegment(ArrayList<TwoPoint> sList, TwoPoint segment) {
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
    
}
