package osm.surveyor.osm.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.marge.RelationMarge;

public class RelationMargeProcessor implements Processor {

	/**
	 * (2) 各WAYのノードで、他のWAYと共有されたノードを探す
	 * 接触しているBUILDINGのWAYをくっつけて"Relation:building"をつくる
	 * Relation:multipolygon の MaxHeightを outline->Multipolygonへ設定する
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		OsmDom osm = exchange.getIn().getBody(OsmDom.class);
		while((new RelationMarge(osm)).relationMarge());
		exchange.getIn().setBody(osm);
	}

}
