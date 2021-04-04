package osm.surveyor.osm;

import java.util.ArrayList;

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
	 */
	public void toArea() {
		int size = nodes.size();
		ElementNode frst = nodes.get(0);
		ElementNode last = nodes.get(size - 1);
		if (size > 3) {
			if ((frst.lat.equals(last.lat)) && (frst.lon.equals(last.lon))) {
				nodes.remove(size - 1);
			}
			area = true;
		}
		if (size == 3) {
			if ((frst.lat.equals(last.lat)) && (frst.lon.equals(last.lon))) {
				area = false;
			}
			else {
				area = true;
			}
		}
	}
	
    public void printout(OsmFile file) {
		for (ElementNode node : this.nodes) {
			node.printout(file);
		}
    	String str = "<way";
    	str += out("id", Long.toString(id));
    	str += out("action", action);
    	str += out("visible", Boolean.toString(visible));
    	str += ">";

    	System.out.println(str);
    	file.println(str);
    	
		for (ElementNode node : this.nodes) {
			node.printRd(file);
		}
		if (area) {
			ElementNode frst = this.nodes.get(0);
			frst.printRd(file);
		}

		for (ElementTag tag : this.tags) {
			tag.printout(file);
		}
		
    	file.println("</way>");
    }
    
    private String out(String key, String value) {
    	String str = "";
    	if (value != null) {
    		str = " " + key +"='" + value +"'";
    	}
    	return str;
    }
}
