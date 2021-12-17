package osm.surveyor.download;

import java.util.Map;
import org.apache.camel.Exchange;

public class OsmFiles {
	public static String SUFFIX_OSM = ".osm";
	public static String SUFFIX_MRG_OSM = ".mrg.osm";
	public static String SUFFIX_ORG_OSM = ".org.osm";

	public boolean filter(Exchange exchange) {
		Map<String, Object> headers = exchange.getIn().getHeaders();
		String name = (String)headers.get("CamelFileName");
		return filter(name);
	}
	
	public static boolean filter(String name) {
		if (name.endsWith(SUFFIX_OSM)) {
			if (!name.endsWith(SUFFIX_MRG_OSM) && !name.endsWith(SUFFIX_ORG_OSM)) {
				return true;
			}
		}
		return false;
	}
}