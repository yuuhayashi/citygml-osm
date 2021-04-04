package osm.surveyor.osm;

/**
 * CityGMLファイルをパースする
 * @param gmlFile
 * @code{
 * <osm>
 *   <tag k='building' v='yes' />
 *         :
 * </osm>
 * }
 * 
 */
public class ElementTag {
	public String k = null;
	public String v = null;
	
	public ElementTag(String key, String value) {
		this.k = key;
		this.v = value;
	}
	
    public void printout(OsmFile file) {
    	if ((k != null) && (v != null)) {
        	String str = "<tag";
        	str += out("k", k);
        	str += out("v", v);
        	str += " />";
        	System.out.println(str);
        	file.println(str);
    	}
    }
    
    private String out(String key, String value) {
    	String str = "";
    	if (value != null) {
    		str = " " + key +"='" + value +"'";
    	}
    	return str;
    }
}
