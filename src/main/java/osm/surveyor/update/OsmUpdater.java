package osm.surveyor.update;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import osm.surveyor.download.OsmLoadDirRoute;
import osm.surveyor.osm.camel.OsmUpdaterRoute;

public class OsmUpdater {
	public static CamelContext camel;

	public static void main(String[] args) throws Exception {
		osmUpdate();
	}
	
	public static void osmUpdate() throws Exception {
		camel = new DefaultCamelContext();
		camel.addRoutes(new OsmLoadDirRoute());
		camel.addRoutes(new OsmUpdaterRoute());
		
		System.out.println("osm-3rd.camel.start();");
		
        camel.start();
        camel.createProducerTemplate().sendBody("direct:osm-files", ".");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                camel.stop();
            } catch (Exception e) {}
    		System.out.println("osm-3rd.camel.stop();");
        }));
        
		System.out.println("osm-3rd.camel.end();");
	}

}
