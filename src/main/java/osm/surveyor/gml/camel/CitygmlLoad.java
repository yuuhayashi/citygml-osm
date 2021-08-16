package osm.surveyor.gml.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

public class CitygmlLoad {
	public static CamelContext camel;
	public static ProducerTemplate producer;

	public static void main(String[] args) throws Exception {
		loadDir();
	}

	public static void loadDir() throws Exception {
		camel = new DefaultCamelContext();
		camel.addRoutes(new GmlLoadDirRoute());
		camel.addRoutes(new GmlLoadRoute());
		
		System.out.println("gml.camel.start();");
        camel.start();
        Thread.sleep(3000);
        camel.stop();
		System.out.println("gml.camel.stop();");
	}

}
