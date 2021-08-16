package osm.surveyor.osm.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.OsmMargeWay;

public class OsmMargeWayProcessor implements Processor {

	/**
	 * (5) "outline"と"part"が重複しているPART を削除する
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		OsmDom osm = exchange.getIn().getBody(OsmDom.class);
		OsmMargeWay.partGabegi(osm);
		exchange.getIn().setBody(osm);
	}

}
