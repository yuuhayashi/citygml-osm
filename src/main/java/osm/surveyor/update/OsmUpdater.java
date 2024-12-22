package osm.surveyor.update;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import osm.surveyor.osm.camel.OrgLoadDirRoute;
import osm.surveyor.osm.camel.OsmUpdaterRoute;

public class OsmUpdater {
	public static CamelContext camel;

	public static void main(String[] args) throws Exception {
		if (args.length > 0) {
			osmUpdate(args[0]);
		}
		else {
			osmUpdate(".");
		}
	}
	
	public static void osmUpdate(String dPath) throws Exception {
		camel = new DefaultCamelContext();
		camel.getStreamCachingStrategy().setBufferSize(64 * 4096);
		camel.addRoutes(new OrgLoadDirRoute());
		camel.addRoutes(new OsmUpdaterRoute());
		
		System.out.println("osm-3rd.camel.start();");
		
        camel.start();
        camel.createProducerTemplate().sendBody("direct:org-files", dPath);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                camel.stop();
            } catch (Exception e) {}
    		System.out.println("osm-3rd.camel.stop();");
        }));
        
		System.out.println("osm-3rd.camel.end();");
	}

}
