package osm.surveyor.osm;

/**
 * CityGMLファイルをパースする
 * @param gmlFile
 * @code{
 * <osm>
 *   <node id='-107065' action='modify' visible='true' lat='35.43464696576' lon='139.4102145808' />
 *         :
 * </osm>
 * }
 * 
 */
public class ElementNode {
	public long id = 0;
	public String action = null;
	public boolean visible = true;
	public String lat = null;
	public String lon = null;
	public String height = null;
	
	public ElementNode(long id) {
		this.id = id;
	}
    
    public void printout(OsmFile file) {
    	String str = "<node";
    	str += out("id", Long.toString(id));
    	str += out("action", action);
    	str += out("visible", Boolean.toString(visible));
    	str += out("lat", lat);
    	str += out("lon", lon);
    	str += " />";

    	System.out.println(str);
    	file.println(str);
    }
    
    public void printRd(OsmFile file) {
    	String str2 = "<nd";
    	str2 += out("ref", Long.toString(id));
    	str2 += "/>";
    	file.println(str2);
    }
    
    private String out(String key, String value) {
    	String str = "";
    	if (value != null) {
    		str = " " + key +"='" + value +"'";
    	}
    	return str;
    }
}
