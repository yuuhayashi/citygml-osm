package osm.surveyor.osm.camel;

import java.io.StringReader;

import javax.xml.bind.JAXB;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import osm.surveyor.osm.OsmBean;

public class OsmParseProcessor implements Processor {

	/**
	 * Camel:body -- OSM(XML) string
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		String str = exchange.getIn().getBody(String.class);
		StringReader r = new StringReader(str);
		OsmBean osm = JAXB.unmarshal(r, OsmBean.class);
		
		System.out.println("OsmParseProcessor : "+ osm.getNodeList().size());
		exchange.getIn().setBody(osm);
	}
}
