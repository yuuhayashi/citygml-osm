package osm.surveyor.osm.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.marge.BuildingGarbage;

public class BuildingGarbageProcessor implements Processor {

	/**
	 * (3) メンバーが一つしかないRelation:building を削除する
	 * (3) メンバーが一つしかないRelation:multipolygon と polygon:member を削除する
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		OsmDom osm = exchange.getIn().getBody(OsmDom.class);
		(new BuildingGarbage(osm)).garbage();
		exchange.getIn().setBody(osm);
	}

}
