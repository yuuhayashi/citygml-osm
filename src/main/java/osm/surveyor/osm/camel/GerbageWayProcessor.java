package osm.surveyor.osm.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import osm.surveyor.osm.OsmDom;

public class GerbageWayProcessor implements Processor {

	/**
	 * RELATIONに所属していないWAYを削除する
	 * from OsmMargeWayProcessor()
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		OsmDom osm = exchange.getIn().getBody(OsmDom.class);
		osm.gerbageWay();
		exchange.getIn().setBody(osm);
	}

}
