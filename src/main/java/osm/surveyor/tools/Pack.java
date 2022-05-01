package osm.surveyor.tools;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class Pack {
	public static CamelContext camel;

	public static void main(String[] args) throws Exception {
		gmlPackage();
	}

	public static void gmlPackage() throws Exception {
		camel = new DefaultCamelContext();
		camel.addRoutes(new PackRoute());
		
		System.out.println("pack.camel.start();");
		
        camel.start();
        camel.createProducerTemplate().sendBody("direct:gml-files", ".");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                camel.stop();
            } catch (Exception e) {}
    		System.out.println("pack.camel.stop();");
        }));
        
		System.out.println("pack.camel.end();");
	}

}
