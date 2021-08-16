package osm.surveyor.osm.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import osm.surveyor.osm.OsmDom;

public class GerbageNodeProcessor implements Processor {

	/**
	 * WAYに所属しないNODEを削除する
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		OsmDom osm = exchange.getIn().getBody(OsmDom.class);
		osm.gerbageNode();
		exchange.getIn().setBody(osm);
	}

}
