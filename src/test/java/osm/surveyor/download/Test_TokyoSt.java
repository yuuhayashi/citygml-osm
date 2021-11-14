package osm.surveyor.download;

import java.nio.file.Paths;

import org.junit.Test;

import osm.surveyor.osm.OsmBean;
import osm.surveyor.osm.WayBean;

public class Test_TokyoSt extends TestDownload {

	/**
	 * `mvn test -Dtest=TokyoStTest#test_download`
	 * 
	 * OSMから「東京駅」ビル周辺のデータをダウンロード
	 * 
	 * (2021-09-03 時点)
	 * - リレーションのメンバーでないWAYは８個のみ
	 * 
	 */
	@Test
	public void test_download() {
		try {
        	// (1)指定されたOSMファイルをLOADする
        	// (2) <bound/>を取得する
    		// (3) OSMから<bound>範囲内の現在のデータをダウンロードする
        		// (4) ダウンロードしたデータをパースする
        	// (5) "building"関係のPOIのみに絞る
        	OsmBean osm = testdo(Paths.get("src/test/resources", "tokyost_bldg_6697_op2.osm"));
    		assertNotNull(osm.getBounds());
			assertNotNull(osm.getRelationList());
			assertTrue(osm.getRelationList().size() >= 2);
			
			int wcnt = 0;
			assertTrue(osm.getWayList().size() > 10);
			for (WayBean way : osm.getWayList()) {
				// "highway"WAYは存在しないこと
				assertNull(way.getTagValue("highway"));
				// "landuse"WAYは存在しないこと
				assertNull(way.getTagValue("landuse"));
				if (!way.getFix()) {
					wcnt++;
				}
			}
			// リレーションのメンバーでないWAYは８個のみ
			assertEquals(8, wcnt);
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
