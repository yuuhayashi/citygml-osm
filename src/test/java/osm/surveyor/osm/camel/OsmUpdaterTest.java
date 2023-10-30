package osm.surveyor.osm.camel;

import java.nio.file.Path;

import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;

import osm.surveyor.osm.BodyMap;

public class OsmUpdaterTest extends CamelTestSupport {

    @Produce("direct:org-file-read")
    protected ProducerTemplate template;
    
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
		return new OsmUpdaterRoute();
    }
    
    /**
      * メイン処理
     * 指定されたOSMファイルの既存データをダウンロードする
     * @param source
     * @return
     */
    public BodyMap testdo(Path source) {
		Exchange exchange = createExchangeWithBody("");
		exchange.getIn().setBody(source.toFile());
		
    	// (1)指定されたOSMファイルをLOADする
    	// (2) <bound/>を取得する
		// (3) OSMから<bound>範囲内の現在のデータをダウンロードする
    	// (4) ダウンロードしたデータをパースする
    	// (5) "building"関係のPOIのみに絞る
        template.send("direct:org-file-read",exchange);
        
        return exchange.getIn().getBody(BodyMap.class);
    }
    
}
