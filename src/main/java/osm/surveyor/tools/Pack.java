package osm.surveyor.tools;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class Pack {
	public static CamelContext camel;

	public static void main(String[] args) throws Exception {
		if (args.length > 0) {
			gmlPackage(args[0]);
		}
		else {
			gmlPackage(".");
		}
	}

	public static void gmlPackage(String dPath) throws Exception {
		camel = new DefaultCamelContext();
		camel.addRoutes(new PackRoute());
		
		System.out.println("pack.camel.start();");
		
        camel.start();
        camel.createProducerTemplate().sendBody("direct:gml-files", dPath);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                camel.stop();
            } catch (Exception e) {}
    		System.out.println("pack.camel.stop();");
        }));
        
		System.out.println("pack.camel.end();");
	}

	public static void unpack(String dPath) throws Exception {
		camel = new DefaultCamelContext();
		camel.addRoutes(new UnPackRoute());
		
		System.out.println("pack.camel.start();");
		
        camel.start();
        camel.createProducerTemplate().sendBody("direct:zip-files", dPath);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                camel.stop();
            } catch (Exception e) {}
    		System.out.println("pack.camel.stop();");
        }));
        
		System.out.println("pack.camel.end();");
	}
}
