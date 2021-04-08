package osm.surveyor.osm;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ElementWay {
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
				if ((frst.lat.equals(last.lat)) && (frst.lon.equals(last.lon))) {
					nodes.remove(size - 1);
					nodes.add(frst);
				}
			}
			area = true;
		}
		if (size == 3) {
			if ((frst.lat.equals(last.lat)) && (frst.lon.equals(last.lon))) {
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
}
