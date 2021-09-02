package osm.surveyor.update;

import java.nio.file.Paths;

import org.junit.Test;

import osm.surveyor.osm.OsmBean;

public class TokyoStTest extends OsmUpdaterTest {

	/**
	 * `mvn test -Dtest=TokyoStTest#test_download`
	 * 
	 * OSMから「東京駅」ビル周辺のデータをダウンロード
	 * 
	 */
	@Test
	public void test_download() {
		try {
        	OsmBean osm = testdo(Paths.get("src/test/resources", "tokyost_bldg_6697_op2.osm"));
        	assertNotNull(osm);
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
