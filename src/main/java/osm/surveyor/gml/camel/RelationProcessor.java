package osm.surveyor.gml.camel;

import java.time.LocalTime;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import osm.surveyor.osm.OsmDom;

public class RelationProcessor implements Processor {
	

	/**
	 * オブジェクトが存在しないメンバーをRELATIONから削除する
	 * 
	 * from : "direct:inOsmMargeWay",GerbageWayProcessor()
	 * to: "direct:osm-export"
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		OsmDom osm = exchange.getIn().getBody(OsmDom.class);
		System.out.println(LocalTime.now() +"\tRelationProcessor.getRelations()");
		
		osm.fixTagset();	// "name"を解決する
		
		System.out.println(LocalTime.now() +"\tRelationProcessor.gerbageMember()");
		osm.gerbageMember();	// Issue #76 オブジェクトが存在しないメンバーをRELATIONから削除する
		
		exchange.getIn().setBody(osm);
	}

}
