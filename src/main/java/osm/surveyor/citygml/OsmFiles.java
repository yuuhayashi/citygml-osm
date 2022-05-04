package osm.surveyor.citygml;

import java.util.Map;
import org.apache.camel.Exchange;

public class OsmFiles {
	public static String SUFFIX = ".osm";

	public boolean filter(Exchange exchange) {
		Map<String, Object> headers = exchange.getIn().getHeaders();
		String name = (String)headers.get("CamelFileName");
		return filter(name);
	}
	
	public static boolean filter(String name) {
		if (name.endsWith(SUFFIX)) {
			if (!name.endsWith(OsmOrgFiles.SUFFIX) && !name.endsWith(OsmMrgFiles.SUFFIX)) {
				return true;
			}
		}
		return false;
	}
}