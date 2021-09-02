package osm.surveyor.download;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.file.FileEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;

import osm.surveyor.osm.OsmBean;
import osm.surveyor.osm.camel.DownloadRoute;

public class TestDownload extends CamelTestSupport {

    @Override
    protected RouteBuilder[] createRouteBuilders() throws Exception {
        return new RouteBuilder[] {
    		new DownloadRoute()
        };
    }
    
    /**
      * メイン処理
     * 指定されたOSMファイルの既存データをダウンロードする
     * @param source
     * @return
     */
    public OsmBean testdo(Path source) {
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
        
        return exchange.getIn().getBody(OsmBean.class);
    }
    
    public OsmBean output() {
		Exchange exchange = createExchangeWithBody("");
		FileEndpoint endpoint = new FileEndpoint();
		endpoint.setFile(Paths.get(".", "out.xml").toFile());
		exchange.setFromEndpoint(endpoint);
		
    	// (1)指定されたOSMファイルをLOADする
    	// (2) <bound/>を取得する
		// (3) OSMから<bound>範囲内の現在のデータをダウンロードする
    		// (4) ダウンロードしたデータをパースする
    	// (5) "building"関係のPOIのみに絞る
        template.send(
        		"direct:osm-org-export",
        		exchange
        );
        
        return exchange.getIn().getBody(OsmBean.class);
    }

    public OsmBean dummyDownload(Path source) {
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
        		"direct:test-file-load",
        		exchange
        );
        
        return exchange.getIn().getBody(OsmBean.class);
    }
}
