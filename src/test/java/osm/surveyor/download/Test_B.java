package osm.surveyor.download;

import java.nio.file.Paths;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import osm.surveyor.osm.OsmBean;
import osm.surveyor.osm.WayBean;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_B extends TestDownload {
	static final String SOURCE = "sample_b_bldg_6697_op2";

	/**
	 * `mvn test -Dtest=OsmUpdaterTest_B#testSample_b`
	 * 
	 */
	@Test
	public void testSample_b() {
        try {
        	OsmBean osm = testdo(Paths.get("src/test/resources",  SOURCE+".osm"));
    		assertNotNull(osm.getBounds());
			assertNotNull(osm.getRelationList());
			assertTrue(osm.getWayList().size() > 10);
			for (WayBean way : osm.getWayList()) {
				assertNull(way.getTagValue("highway"));
				assertNull(way.getTagValue("landuse"));
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
