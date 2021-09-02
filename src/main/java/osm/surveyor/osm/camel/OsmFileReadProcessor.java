package osm.surveyor.osm.camel;

import java.io.File;

import javax.xml.bind.JAXB;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.FileEndpoint;

import osm.surveyor.osm.ElementOsm;

public class OsmFileReadProcessor implements Processor {

	/**
	 * .send("direct:osm-file-read", exchange);
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		FileEndpoint endpoint = (FileEndpoint)exchange.getFromEndpoint();
		File file = endpoint.getFile();
		
		ElementOsm osm = JAXB.unmarshal(file, ElementOsm.class);
		exchange.getIn().setBody(osm);
	}

}
