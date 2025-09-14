package osm.surveyor.osm.camel;

import java.time.LocalTime;

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
		System.out.println(LocalTime.now() +"\tRelationMargeProcessor.relationMarge()");
		String name = (String) exchange.getProperty(Exchange.FILE_NAME);
		OsmDom osm = exchange.getIn().getBody(OsmDom.class);
		System.out.print(LocalTime.now() +"\t");
		
		RelationMarge marge = new RelationMarge(osm);
		while(marge.relationMarge());
		
		System.out.println();
		exchange.getIn().setBody(osm);
		exchange.setProperty(Exchange.FILE_NAME, name);
	}

}
