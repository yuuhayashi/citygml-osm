package osm.surveyor.update;

import static org.junit.Assert.*;

import java.nio.file.Paths;

import org.junit.Test;

public class TokyoStTest {

	/**
	 * `mvn test -Dtest=TokyoStTest#test_download`
	 * 
	 * OSMから「東京駅」ビル周辺のデータをダウンロード
	 * 
	 */
	@Test
	public void test_download() {
		try {
			OsmUpdater updater = new OsmUpdater(Paths.get("src/test/resources/", "tokyost_bldg_6697_op2.osm").toFile());
			updater.download();
			updater.sdom.export(Paths.get("tokyost_bldg_6697_op2.org.osm").toFile());
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
