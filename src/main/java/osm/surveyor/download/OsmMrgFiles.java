package osm.surveyor.download;

import java.io.File;
import java.nio.file.Files;

import org.apache.camel.Exchange;

public class OsmMrgFiles extends OsmFiles {
	public boolean filter(Exchange exchange) {
		File file = exchange.getIn().getBody(File.class);
		if (Files.isRegularFile(file.toPath())) {
			String name = file.getName();
			if (name.endsWith(SUFFIX_MRG_OSM)) {
				return true;
			}
		}
		return false;
	}
}