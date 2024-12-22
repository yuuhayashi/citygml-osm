package osm.surveyor.osm.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import osm.surveyor.download.OsmLoadDirRoute;

public class OsmDownload {
	public static CamelContext camel;

	public static void main(String[] args) throws Exception {
		if (args.length > 0) {
			osmDownload(args[0]);
		}
		else {
			osmDownload(".");
		}
	}

	public static void osmDownload(String dPath) throws Exception {
		camel = new DefaultCamelContext();
		camel.getStreamCachingStrategy().setBufferSize(64 * 4096);
		camel.addRoutes(new OsmLoadDirRoute());
		camel.addRoutes(new DownloadRoute());
		
		System.out.println("osm-2nd.camel.start();");
		
        camel.start();
        camel.createProducerTemplate().sendBody("direct:osm-files", dPath);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                camel.stop();
            } catch (Exception e) {}
    		System.out.println("osm-2nd.camel.stop();");
        }));
        
		System.out.println("osm-2nd.camel.end();");
	}

}
