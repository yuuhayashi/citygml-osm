package osm.surveyor.osm.camel;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalTime;

import javax.xml.bind.JAXB;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import osm.surveyor.osm.BodyMap;
import osm.surveyor.osm.BoundsBean;
import osm.surveyor.osm.OsmBean;
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
		BodyMap map = exchange.getIn().getBody(BodyMap.class);
		OsmBean osm = (OsmBean) map.get("osm");
		BoundsBean bound = osm.getBounds();
		System.out.println(LocalTime.now() +"\tOsmDownloadProcessor : " + bound);
		
		// OSMから<bound>範囲内の現在のデータを取得する
		HttpGet http = new HttpGet();
		String urlstr = http.getHost() + "/api/0.6/map?"+ bound.getBbox();
        System.out.println(LocalTime.now() +"\tURL: " + urlstr);
        URL url = new URL(urlstr);
        
        HttpURLConnection urlconn = (HttpURLConnection)url.openConnection();
        try {
            urlconn.setRequestMethod("GET");
            urlconn.setInstanceFollowRedirects(false);
            urlconn.setRequestProperty("Accept-Language", "ja;q=0.7,en;q=0.3");
            urlconn.connect();

    		GetResponse res = new GetResponse();
            res.setCode(urlconn.getResponseCode());
    		res.setMessage(urlconn.getResponseMessage());
    		
            // Parse from XML
    		OsmBean org = JAXB.unmarshal(urlconn.getInputStream(), OsmBean.class);
    		org.convertToWeyMap();
    		org.build();
            map.put("org", org);
        }
        finally {
            urlconn.disconnect();
        }

        exchange.getIn().setBody(map);
	}

}
