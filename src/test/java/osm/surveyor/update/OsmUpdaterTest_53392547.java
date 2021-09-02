package osm.surveyor.update;

import java.nio.file.Paths;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import osm.surveyor.osm.OsmBean;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OsmUpdaterTest_53392547 extends OsmUpdaterTest {
	static final String SOURCE = "53392547_bldg_6697_op2";

	/**
	 * `mvn test -Dtest=OsmUpdaterTest_53392547#test_53392547`
	 * 
	 */
	@Test
	public void test_53392547() {
        try {
        	OsmBean osm = testdo(Paths.get("src/test/resources",  SOURCE+".osm"));
			assertNotNull(osm.getRelationList());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
