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
public class Test_A extends TestDownload {

	/**
	 * 東京都大田区南六郷三丁目 "53392547_bldg_6697_op2"
	 * `mvn test -Dtest=OsmUpdaterTest_A#test_a`
	 * 東京都大田区 多摩川右岸付近
	 * <bounds minlat="35.5407086" minlon="139.7124522" maxlat="35.5422523" maxlon="139.7156384"/>
	 * ・ビルディングリレーションは存在しない
	 * ・タグありのノードを含むWAYは存在しない
	 * - "highway"WAYは存在しないこと
	 * - "landuse"WAYは存在しないこと
	 */
	@Test
	public void test_a() {
        try {
        	// (1)指定されたOSMファイルをLOADする
        	// (2) <bound/>を取得する
    		// (3) OSMから<bound>範囲内の現在のデータをダウンロードする
        		// (4) ダウンロードしたデータをパースする
        	// (5) "building"関係のPOIのみに絞る
        	OsmBean osm = testdo(Paths.get("src/test/resources", "53392547_bldg_6697_op2.osm"));
    		assertNotNull(osm.getBounds());
			assertNotNull(osm.getRelationList());
			
			// ビルディングリレーションは存在しない
			assertEquals(0, osm.getRelationList().size());

			assertTrue(osm.getWayList().size() > 10);
			for (WayBean way : osm.getWayList()) {
				// "highway"WAYは存在しないこと
				assertNull(way.getTagValue("highway"));
				// "landuse"WAYは存在しないこと
				assertNull(way.getTagValue("landuse"));
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	/**
	 * `mvn test -Dtest=OsmUpdaterTest_A#test_d1`
	 * 
	 * INPUT: "sample_a_bldg_6697_op2.osm"
	 * INPUT: "sample_a3_bldg_6697_op2.org.osm"
	 */
	@Test
	@Category(DetailTests.class)
	public void test_a3() {
        try {
        	// (1)指定されたOSMファイルをLOADする
        	// (2) <bound/>を取得する
    		// (3) OSMから<bound>範囲内の現在のデータをダウンロードする
        		// (4) ダウンロードしたデータをパースする
        	// (5) "building"関係のPOIのみに絞る
        	OsmBean osm = testdo(Paths.get("src/test/resources", "sample_a3_bldg_6697_op2.org.osm"));
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
