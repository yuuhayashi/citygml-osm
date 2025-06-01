package osm.surveyor.osm.camel;

import java.time.LocalTime;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.marge.OutlineFactory;

public class OutlineFactoryProcessor implements Processor {

	/**
	 * (4) Relation:building->member:role=port のWay:outlineを作成する
	 * (4) Relation:multipolygon->outerにWay:outline
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println(LocalTime.now() +"\tOutlineFactoryProcessor.relationOutline()");
		OsmDom osm = exchange.getIn().getBody(OsmDom.class);
		(new OutlineFactory(osm)).relationOutline();
		exchange.getIn().setBody(osm);
	}

}
