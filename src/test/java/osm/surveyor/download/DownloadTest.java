package osm.surveyor.download;

import java.nio.file.Path;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.file.FileEndpoint;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;

import osm.surveyor.osm.BodyMap;
import osm.surveyor.osm.camel.DownloadRoute;

public class DownloadTest extends CamelTestSupport {

    @EndpointInject(uri = "mock:result")
    protected MockEndpoint resultEndpoint;

    @Produce(uri = "direct:start")
    protected ProducerTemplate template;
    
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
		return new DownloadRoute();
    }
    
    /**
      * メイン処理
     * 指定されたOSMファイルの既存データをダウンロードする
     * @param source
     * @return
     */
    public BodyMap testdo(Path source) {
		Exchange exchange = createExchangeWithBody("");
		FileEndpoint endpoint = new FileEndpoint();
		endpoint.setFile(source.toFile());
		exchange.setFromEndpoint(endpoint);
		
    	// (1)指定されたOSMファイルをLOADする
    	// (2) <bound/>を取得する
		// (3) OSMから<bound>範囲内の現在のデータをダウンロードする
    		// (4) ダウンロードしたデータをパースする
    	// (5) "building"関係のPOIのみに絞る
        template.send(
        		"direct:osm-file-read",
        		exchange
        );
        
        return exchange.getIn().getBody(BodyMap.class);
    }
}
