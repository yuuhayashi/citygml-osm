package osm.surveyor.osm.camel;

import java.io.File;
import java.util.Map;

import javax.xml.bind.JAXB;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import osm.surveyor.osm.ElementBounds;
import osm.surveyor.osm.ElementOsm;

public class GetBoundProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		Map<String, Object> headers = exchange.getIn().getHeaders();
		String filepath = (String)headers.get("CamelFileAbsolutePath");
		System.out.println("GetBoundProcessor : " + filepath);
		
		ElementOsm osm = JAXB.unmarshal(new File(filepath), ElementOsm.class);
		ElementBounds bound = osm.getBounds();
		exchange.getIn().setBody(bound);
	}

}
