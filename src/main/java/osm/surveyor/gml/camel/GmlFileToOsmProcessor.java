package osm.surveyor.gml.camel;

import java.io.File;
import java.nio.file.Paths;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.FileEndpoint;

import osm.surveyor.citygml.GmlFiles;
import osm.surveyor.download.OsmFiles;

public class GmlFileToOsmProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		FileEndpoint endpoint = (FileEndpoint)exchange.getFromEndpoint();
		File file = endpoint.getFile();
		
		String name = file.getAbsolutePath();
		if (name.endsWith(GmlFiles.SUFFIX_GML)) {
			String filename = name.substring(0, name.length() - GmlFiles.SUFFIX_GML.length());
			File outf = (Paths.get(filename + OsmFiles.SUFFIX_ORG_OSM).toFile());
			endpoint.setFile(outf);
			exchange.setFromEndpoint(endpoint);
		}
	}

}
