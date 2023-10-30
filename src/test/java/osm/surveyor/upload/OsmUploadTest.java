package osm.surveyor.upload;

import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;

import osm.surveyor.osm.BodyMap;

public class OsmUploadTest extends CamelTestSupport {

    @Produce("direct:start")
    protected ProducerTemplate template;
    
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
		return new OsmUploaderRoute();
    }
    
    /**
      * メイン処理
     * 指定されたOSMファイルの既存データをダウンロードする
     * @param source
     * @return
     */
    public BodyMap testdo(String filepath) {
    	// Bodyに ファイル名をセットする
		Exchange exchange = createExchangeWithBody("");
		exchange.getIn().setBody(filepath);
		
    	// 指定されたOSMファイルをLOADする
        template.send("direct:checked-file-read", exchange);
        
        return exchange.getIn().getBody(BodyMap.class);
    }
    
}
