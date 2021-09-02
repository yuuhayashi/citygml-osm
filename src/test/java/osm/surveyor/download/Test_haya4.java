package osm.surveyor.download;

import java.nio.file.Paths;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runners.MethodSorters;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.OsmBean;
import osm.surveyor.osm.WayBean;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_haya4 extends TestDownload {
	static final String SOURCE = "haya4_bldg_6697_op2";

	/**
	 * `mvn test -Dtest=OsmUpdaterTest_haya4#test_241755306`
	 * https://www.openstreetmap.org/ウェイ/241755306
	 * 神奈川県 綾瀬市
	 */
	@Test
	@Category(DetailTests.class)
	public void test_241755306() {
        try {
        	// (1)指定されたOSMファイルをLOADする
        	// (2) <bound/>を取得する
    		// (3) OSMから<bound>範囲内の現在のデータをダウンロードする
        		// (4) ダウンロードしたデータをパースする
        	// (5) "building"関係のPOIのみに絞る
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
