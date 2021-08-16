package osm.surveyor.osm.api;

import org.apache.camel.builder.RouteBuilder;

public class CitygmlFileTest4 extends CitygmlFileTest {

    @Override
    protected RouteBuilder[] createRouteBuilders() throws Exception {
        return new RouteBuilder[] {
    		new GmlLoad4_Route()
        };
    }
}
