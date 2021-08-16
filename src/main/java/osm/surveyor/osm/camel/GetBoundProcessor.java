package osm.surveyor.osm.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import osm.surveyor.osm.ElementBounds;
import osm.surveyor.osm.ElementOsm;

public class GetBoundProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println("GetBoundProcessor : ");
		
		ElementOsm osm = exchange.getIn().getBody(ElementOsm.class);
		ElementBounds bound = osm.getBounds();
		exchange.getIn().setBody(bound);
	}

}
