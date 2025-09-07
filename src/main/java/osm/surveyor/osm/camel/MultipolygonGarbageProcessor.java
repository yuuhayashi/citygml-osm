package osm.surveyor.osm.camel;

import java.time.LocalTime;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.marge.BuildingGarbage;

public class MultipolygonGarbageProcessor implements Processor {

	/**
	 * (3) メンバーが一つしかないRelation:multipolygon と polygon:member を削除する
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println(LocalTime.now() +"\tBuildingGarbageProcessor.garbageMultipolygon()");
		OsmDom osm = exchange.getIn().getBody(OsmDom.class);
		(new BuildingGarbage(osm)).garbageMultipolygon();
		exchange.getIn().setBody(osm);
	}

}
