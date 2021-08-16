package osm.surveyor.osm.api;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_E_4 extends CitygmlFileTest4 {

	@Test
	@Category(DetailTests.class)
	public void testSample_e4_createOutline() {
        try {
            OsmDom osm = testdo("./src/test/resources/sample_e_bldg_6697_op2.gml");
			CitygmlFileTest_E.checkSample_e(osm);
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

}
