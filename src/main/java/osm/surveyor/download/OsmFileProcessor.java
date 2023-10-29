package osm.surveyor.download;

import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class OsmFileProcessor implements Processor {

	/**
	 * from("direct:osm-files").process(new OsmFileListProcessor()).split()
	 * 
	 * In.Body --> File "org-file"
	 * .property.TO_ENDPOINT <-- File "org-file"
	 * 
	 * to("direct:osm-file-read")
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		File file = exchange.getIn().getBody(File.class);

		System.out.println("OsmFileProcessor : \""+ file.getName() +"\"");
		exchange.setProperty(Exchange.FILE_NAME, file.getName());
	}
}
