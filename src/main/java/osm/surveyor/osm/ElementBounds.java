package osm.surveyor.osm;

/**
 * CityGMLファイルをパースする
 * @param gmlFile
 * @code{
 * <osm>
 *   <bounds minlat='35.4345061' minlon='139.410131' maxlat='35.4347116' maxlon='139.4104367' origin='CGImap 0.8.3 (2061540 spike-08.openstreetmap.org)' />
 *         :
 * </osm>
 * }
 * 
 */
public class ElementBounds {
	public String minlat = null;
	public String minlon = null;
	public String maxlat = null;
	public String maxlon = null;
	String origin = "CGImap 0.8.3 (2061540 spike-08.openstreetmap.org)";
    
    public void printout(OsmFile file) {
    	String str = "<bounds";
    	str += out("minlat", minlat);
    	str += out("minlon", minlon);
    	str += out("maxlat", maxlat);
    	str += out("maxlon", maxlon);
    	str += out("origin", origin);
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
