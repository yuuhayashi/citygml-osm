package osm.surveyor.osm.camel;

import java.io.File;
import java.nio.file.Paths;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.FileEndpoint;

import osm.surveyor.download.OsmFiles;

public class ToOrgOsmFileProcessor implements Processor {

	/**
	 * ファイル名 "*.osm" を "*.org.osm" に変換する
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		FileEndpoint endpoint = (FileEndpoint)exchange.getFromEndpoint();
		File file = endpoint.getFile();
		
		String name = file.getName();
		if (name.endsWith(OsmFiles.SUFFIX_OSM)) {
			String filename = name.substring(0, name.length() - OsmFiles.SUFFIX_OSM.length());
			File outf = (Paths.get(".", filename + OsmFiles.SUFFIX_ORG_OSM).toFile());
			endpoint.setFile(outf);
			exchange.setFromEndpoint(endpoint);
		}
	}

}
