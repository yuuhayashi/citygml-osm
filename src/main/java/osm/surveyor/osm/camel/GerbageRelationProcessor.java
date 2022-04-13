package osm.surveyor.osm.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import osm.surveyor.osm.OsmDom;

public class GerbageRelationProcessor implements Processor {

	/**
	 * オブジェクトが存在しないメンバーをRELATIONから削除する
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		OsmDom osm = exchange.getIn().getBody(OsmDom.class);
		osm.gerbageWay();
		osm.toOutline();		// Issue #40
		osm.gerbageMember();	// Issue #76
		exchange.getIn().setBody(osm);
	}

}
