package osm.surveyor.osm.api;

import org.apache.camel.builder.RouteBuilder;

public class CitygmlFileTest2 extends GmlLoadRouteTest {

    @Override
    protected RouteBuilder[] createRouteBuilders() throws Exception {
        return new RouteBuilder[] {
    		new GmlLoad2_Route()
        };
    }
    
}
