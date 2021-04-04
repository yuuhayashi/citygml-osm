package osm.surveyor.osm;

import java.util.ArrayList;

public class ElementRelation {
	public long id = 0;
	public String action = "modify";
	public boolean visible = true;
	public ArrayList<ElementMember> members;
	public ArrayList<ElementTag> tags;
	
	public ElementRelation(long id) {
		this.id = id;
		members = new ArrayList<ElementMember>();
		tags = new ArrayList<ElementTag>();
	}
	
	public void addTag(String k, String v) {
		this.tags.add(new ElementTag(k, v));
	}
	
	public void addMember(ElementWay way, String role) {
		ElementMember member = new ElementMember();
		member.setWay(way);
		member.setRole(role);
		this.members.add(member);
	}
    
    public void printout(OsmFile file) {
    	String str = "<relation";
    	str += out("id", Long.toString(id));
    	str += out("action", action);
    	str += out("visible", Boolean.toString(visible));
    	str += ">";
    	file.println(str);

    	for (ElementMember member : this.members) {
    		member.printout(file);
		}
    	
		for (ElementTag tag : this.tags) {
	    	String str2 = "<tag";
	    	str2 += out("k", tag.k);
	    	str2 += out("v", tag.v);
	    	str2 += " />";
	    	file.println(str2);
		}

    	file.println("</relation>");
    }
    
    protected String out(String key, String value) {
    	String str = "";
    	if (value != null) {
    		str = " " + key +"='" + value +"'";
    	}
    	return str;
    }
}
