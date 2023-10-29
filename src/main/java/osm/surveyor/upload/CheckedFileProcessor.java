package osm.surveyor.upload;

import java.nio.file.Paths;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.FileEndpoint;

public class CheckedFileProcessor implements Processor {

	/**
	 * from("direct:checked-file")
	 * 
	 * Exchange.body : BodyMap 変換元のオブジェクト
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		FileEndpoint endpoint = new FileEndpoint();
		endpoint.setFile(Paths.get(".", "checked.osm").toFile());
		exchange.setProperty(Exchange.TO_ENDPOINT, endpoint);
	}

}
