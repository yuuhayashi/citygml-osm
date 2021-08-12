package osm.surveyor.download;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

public class OsmDownload {
	public static CamelContext camel;
	public static ProducerTemplate producer;

	public static void main(String[] args) throws Exception {
		camel = new DefaultCamelContext();
		camel.addRoutes(new DownloadRoute());
		
		System.out.println("camel.start();");
        camel.start();
        Thread.sleep(6000);
        camel.stop();
		System.out.println("camel.stop();");
	}

}
