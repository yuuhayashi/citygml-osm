package osm.surveyor.download;

import java.nio.file.Paths;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import osm.surveyor.osm.OsmBean;
import osm.surveyor.osm.WayBean;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_haya4 extends TestDownload {
	static final String SOURCE = "haya4_bldg_6697_op2";

	/**
	 * `mvn test -Dtest=OsmUpdaterTest_haya4#test_way241755306`
	 * https://www.openstreetmap.org/ウェイ/241755306
	 * 神奈川県 綾瀬市
	 * WAY:289757586 = "NODE:entrance=yes"を持つウェイ
	 * WAY:241755306 = 林宅
	 */
	@Test
	public void test_way241755306() {
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
				// "highway"WAYは存在しないこと
				assertNull(way.getTagValue("highway"));
				// "landuse"WAYは存在しないこと
				assertNull(way.getTagValue("landuse"));
				if (way.getId() == 289757586l) {
					// タグありNODEを持つWAYは、 'FIX=true' であること
					assertTrue(way.getFix());
				}
				if (way.getId() == 241755306l) {
					// リレーションメンバーのWAYは 'FIX=true' であること
					assertTrue(way.getFix());
				}
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}