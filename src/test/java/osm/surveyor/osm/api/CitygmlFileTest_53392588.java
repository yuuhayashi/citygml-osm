package osm.surveyor.osm.api;

import org.junit.Test;

import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_53392588 extends GmlLoadRouteTest {

	@Test
	public void test53392588() {
		try {
			OsmDom osm = testdo("./src/test/resources/53392588_bldg_6697_op2.gml");
			assertNotNull(osm);
			assertNotNull(osm.ways);
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
	
}
