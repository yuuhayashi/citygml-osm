package osm.surveyor.osm.api;

import org.apache.camel.builder.RouteBuilder;

public class CitygmlFileTest1 extends GmlLoadRouteTest {

    // (1) GMLファイルをパースする
    // RELATIONに所属していないWAYを削除する
    // WAYに所属しないNODEを削除する
    @Override
    protected RouteBuilder[] createRouteBuilders() throws Exception {
        return new RouteBuilder[] {
    		new GmlLoad1_Route()
        };
    }
    
}
