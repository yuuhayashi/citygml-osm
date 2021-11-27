package osm.surveyor.gml.camel;

import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.FileEndpoint;

public class GmlFileProcessor implements Processor {

	/**
	 * from("direct:gml-files").process(new GmlFileListProcessor()).split()
	 * 
	 * to("direct:gml-file-read")
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		File file = exchange.getIn().getBody(File.class);

        FileEndpoint endpoint = new FileEndpoint();
		endpoint.setFile(file);
		exchange.setFromEndpoint(endpoint);
	}
}
