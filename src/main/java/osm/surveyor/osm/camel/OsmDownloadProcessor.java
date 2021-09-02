package osm.surveyor.osm.camel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import osm.surveyor.osm.BoundsBean;
import osm.surveyor.osm.api.GetResponse;
import osm.surveyor.osm.api.HttpGet;

public class OsmDownloadProcessor implements Processor {

	/**
	 * from("direct:osm-download")
	 * .process(new OsmDownloadProcessor())
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		// 指定されたOSMファイルの<bound/>を取得する
		BoundsBean bound = exchange.getIn().getBody(BoundsBean.class);
		System.out.println("OsmDownloadProcessor : " + bound);
		
		// OSMから<bound>範囲内の現在のデータを取得する
		HttpGet http = new HttpGet();
		String urlstr = http.getHost() + "/api/0.6/map?"+ bound.getBbox();
        System.out.println("URL: " + urlstr);
        URL url = new URL(urlstr);
        
		StringBuilder sbBody = new StringBuilder();
        HttpURLConnection urlconn = (HttpURLConnection)url.openConnection();
        try {
            urlconn.setRequestMethod("GET");
            urlconn.setInstanceFollowRedirects(false);
            urlconn.setRequestProperty("Accept-Language", "ja;q=0.7,en;q=0.3");
            urlconn.connect();

    		GetResponse res = new GetResponse();
            res.setCode(urlconn.getResponseCode());
    		res.setMessage(urlconn.getResponseMessage());
    		
    		BufferedReader reader = new BufferedReader(new InputStreamReader(urlconn.getInputStream(), "utf-8"));
    		String s;
            while ((s = reader.readLine()) != null) {
                sbBody.append(s);
                sbBody.append("\n");
            }
        }
        finally {
            urlconn.disconnect();
        }
		exchange.getIn().setBody(sbBody.toString());

        /*
		 * 
		OsmDom org = new OsmDom();
		org.setBounds(bound);
		org.downloadMap();
		 */
	}

}
