package osm.surveyor.download;

import java.io.File;
import java.nio.file.Files;

import org.apache.camel.Exchange;

public class OsmOrgFiles extends OsmFiles {
	public boolean filter(Exchange exchange) {
		File file = exchange.getIn().getBody(File.class);
		if (Files.isRegularFile(file.toPath())) {
			String name = file.getName();
			if (name.endsWith(SUFFIX_ORG_OSM)) {
				return true;
			}
		}
		return false;
	}
}