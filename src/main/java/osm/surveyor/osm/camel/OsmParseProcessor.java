package osm.surveyor.osm.camel;

import java.io.StringReader;

import javax.xml.bind.JAXB;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import osm.surveyor.osm.ElementOsm;

public class OsmParseProcessor implements Processor {

	/**
	 * Camel:body -- OSM(XML) string
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		String str = exchange.getIn().getBody(String.class);
		StringReader r = new StringReader(str);
		ElementOsm osm = JAXB.unmarshal(r, ElementOsm.class);
		exchange.getIn().setBody(osm);
		
		System.out.println("OsmParseProcessor : "+ osm.getNodeList().size());
	}

}
