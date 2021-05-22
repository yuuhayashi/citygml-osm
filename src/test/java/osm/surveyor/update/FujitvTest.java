package osm.surveyor.update;

import static org.junit.Assert.*;

import java.nio.file.Paths;

import org.junit.Test;

public class FujitvTest {

	/**
	 * `mvn test -Dtest=FujitvTest#test_download`
	 * 
	 * OSMから「フジテレビ」ビル周辺のデータをダウンロード
	 * 
	 */
	@Test
	public void test_download() {
		try {
			OsmUpdater updater = new OsmUpdater(Paths.get("src/test/resources/", "fujitv_bldg_6697_op2.osm").toFile());
			updater.download();
			updater.sdom.export(Paths.get("fujitv_bldg_6697_op2.org.osm").toFile());
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
