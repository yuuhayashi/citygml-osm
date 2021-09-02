package osm.surveyor.update;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import osm.surveyor.osm.camel.OsmUpdaterRoute;

public class OsmUpdater {
	public static CamelContext camel;

	public static void main(String[] args) throws Exception {
		camel = new DefaultCamelContext();
		camel.addRoutes(new OsmUpdaterRoute());
		
		System.out.println("camel.start();");
        camel.start();
        Thread.sleep(6000);
        camel.stop();
		System.out.println("camel.stop();");
	}
}
