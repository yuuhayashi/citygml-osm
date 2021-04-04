package osm.surveyor.osm;

/**
 * @code{
 *   <member type='way' ref='-102077' role='part' />
 * }
 */
public class ElementMember {
	public long ref = 0;
	public String type = "way";
	public String role = "";
	public ElementWay way;
	
	public ElementMember() {
	}
	
	public void setWay(ElementWay way) {
		this.way = way;
		this.ref = way.id;
		this.type = "way";
	}
	
	public void setRole(String role) {
		this.role = role;
	}
    
    public void printout(OsmFile file) {
    	String str = "<member";
    	str += out("ref", Long.toString(ref));
    	str += out("type", type);
    	str += out("role", role);
    	str += " />";
    	System.out.println(str);
    	file.println(str);
    }
    
    private String out(String key, String value) {
    	String str = "";
    	if (value != null) {
    		str = " " + key +"='" + value +"'";
    	}
    	return str;
    }
}
