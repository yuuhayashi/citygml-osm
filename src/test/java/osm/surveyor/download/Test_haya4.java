package osm.surveyor.download;

import java.nio.file.Paths;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runners.MethodSorters;

import osm.surveyor.OnlineTests;
import osm.surveyor.osm.BodyMap;
import osm.surveyor.osm.OsmBean;
import osm.surveyor.osm.way.Wayable;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_haya4 extends DownloadTest {
	static final String SOURCE = "haya4_bldg_6697_op2";

	/**
	 * `mvn test -Dtest=OsmUpdaterTest_haya4#test_way241755306`
	 * https://www.openstreetmap.org/ウェイ/241755306
	 * 神奈川県 綾瀬市
	 * WAY:289757586 = "NODE:entrance=yes"を持つウェイ
	 * WAY:241755306 = 林宅
	 */
	@Test
	@Category(OnlineTests.class)
	public void test_way241755306() {
        try {
        	// (1)指定されたOSMファイルをLOADする
        	// (2) <bound/>を取得する
    		// (3) OSMから<bound>範囲内の現在のデータをダウンロードする
        		// (4) ダウンロードしたデータをパースする
        	// (5) "building"関係のPOIのみに絞る
        	BodyMap map = testdo(Paths.get("src/test/resources",  SOURCE+".osm"));
    		OsmBean org = (OsmBean) map.get("org");

    		assertNotNull(org.getBounds());
			assertNotNull(org.getRelationList());

			assertTrue(org.getWays().size() > 10);
			for (Wayable way : org.getWays()) {
				// "highway"WAYは存在しないこと
				assertNull(way.getPoiBean().getTagValue("highway"));
				// "landuse"WAYは存在しないこと
				assertNull(way.getPoiBean().getTagValue("landuse"));
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
