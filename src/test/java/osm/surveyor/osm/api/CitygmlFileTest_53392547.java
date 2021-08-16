package osm.surveyor.osm.api;

import org.junit.Test;

import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_53392547 extends CitygmlFileTest {

	@Test
	public void test53392547() {
		try {
			OsmDom osm = testdo("./src/test/resources/53392547_bldg_6697_op2.gml");
			assertNotNull(osm);
			assertNotNull(osm.ways);
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
	
}
