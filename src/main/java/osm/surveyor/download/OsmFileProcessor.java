package osm.surveyor.download;

import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.FileEndpoint;

public class OsmFileProcessor implements Processor {

	/**
	 * from("direct:osm-files").process(new OsmFileListProcessor()).split()
	 * 
	 * to("direct:osm-file-read")
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		File file = exchange.getIn().getBody(File.class);

        FileEndpoint endpoint = new FileEndpoint();
		endpoint.setFile(file);
		exchange.setFromEndpoint(endpoint);
	}
}
