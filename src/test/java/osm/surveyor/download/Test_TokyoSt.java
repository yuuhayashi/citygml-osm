package osm.surveyor.download;

import java.nio.file.Paths;

import org.junit.Test;

import osm.surveyor.osm.BodyMap;
import osm.surveyor.osm.OsmBean;
import osm.surveyor.osm.way.Wayable;

public class Test_TokyoSt extends DownloadTest {

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
			BodyMap map = testdo(Paths.get("src/test/resources", "tokyost_bldg_6697_op2.osm"));
    		OsmBean org = (OsmBean) map.get("org");

    		assertNotNull(org.getBounds());
			assertNotNull(org.getRelationList());
			assertTrue(org.getRelationList().size() >= 2);
			
			assertTrue(org.getWayList().size() > 10);
			for (Wayable way : org.getWayList()) {
				// "highway"WAYは存在しないこと
				assertNull(way.getPoiBean().getTagValue("highway"));
				// "landuse"WAYは存在しないこと
				assertNull(way.getPoiBean().getTagValue("landuse"));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
