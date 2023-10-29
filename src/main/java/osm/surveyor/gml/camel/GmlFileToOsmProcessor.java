package osm.surveyor.gml.camel;

import java.io.File;
import java.nio.file.Paths;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.FileEndpoint;

import osm.surveyor.citygml.GmlFiles;
import osm.surveyor.citygml.OsmFiles;

public class GmlFileToOsmProcessor implements Processor {

	/**
	 * "direct:osm-export"
	 * to: OsmExportProcessor()
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		FileEndpoint endpoint = (FileEndpoint)exchange.getFromEndpoint();
		File file = endpoint.getFile();
		
		String name = file.getName();
		if (name.endsWith(GmlFiles.SUFFIX)) {
			String filename = name.substring(0, name.length() - GmlFiles.SUFFIX.length());
			File outf = (Paths.get(".", filename + OsmFiles.SUFFIX).toFile());
			endpoint.setFile(outf);
			exchange.setProperty(Exchange.TO_ENDPOINT, endpoint);
		}
	}

}
