package osm.surveyor.citygml;

import java.util.Map;
import org.apache.camel.Exchange;

public class ZipFiles {
	public static String SUFFIX = ".zip";

	public boolean filter(Exchange exchange) {
		Map<String, Object> headers = exchange.getIn().getHeaders();
		String name = (String)headers.get("CamelFileName");
		return filter(name);
	}

	public static boolean filter(String name) {
		if (name.endsWith(SUFFIX)) {
			return true;
		}
		return false;
	}
}