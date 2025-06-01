package osm.surveyor.gml.camel;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalTime;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import osm.surveyor.citygml.GmlFiles;
import osm.surveyor.citygml.OsmFiles;

public class GmlFileToOsmProcessor implements Processor {

	/**
	 * "direct:osm-export"
	 * to: OsmExportProcessor()
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println(LocalTime.now() +"\tGmlFileToOsmProcessor.process()");

		String name = (String) exchange.getProperty(Exchange.FILE_NAME);
		if (name.endsWith(GmlFiles.SUFFIX)) {
			String filename = name.substring(0, name.length() - GmlFiles.SUFFIX.length());
			File outf = (Paths.get(".", filename + OsmFiles.SUFFIX).toFile());
			exchange.setProperty(Exchange.FILE_NAME, outf.getName());
		}
	}

}
