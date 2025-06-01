package osm.surveyor.update;

import java.time.LocalTime;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import osm.surveyor.osm.camel.OrgLoadDirRoute;
import osm.surveyor.osm.camel.OsmUpdaterRoute;

public class OsmUpdater {
	public static CamelContext camel;

	public static void main(String[] args) throws Exception {
		osmUpdate();
	}
	
	public static void osmUpdate() throws Exception {
		camel = new DefaultCamelContext();
		camel.getStreamCachingStrategy().setBufferSize(64 * 4096);
		camel.addRoutes(new OrgLoadDirRoute());
		camel.addRoutes(new OsmUpdaterRoute());
		
		System.out.println(LocalTime.now() +"\tosm-3rd.camel.start();");
		
        camel.start();
        camel.createProducerTemplate().sendBody("direct:org-files", ".");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                camel.stop();
            } catch (Exception e) {}
    		System.out.println(LocalTime.now() +"\tosm-3rd.camel.stop();");
        }));
        
		System.out.println(LocalTime.now() +"\tosm-3rd.camel.end();");
	}

}
