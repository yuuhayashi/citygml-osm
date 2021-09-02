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
	 * 東京都大田区南六郷三丁目
	 * `mvn test -Dtest=OsmUpdaterTest_A#test_a`
	 */
	@Test
	public void test_a() {
        try {
        	// (1)指定されたOSMファイルをLOADする
        	// (2) <bound/>を取得する
    		// (3) OSMから<bound>範囲内の現在のデータをダウンロードする
        		// (4) ダウンロードしたデータをパースする
        	// (5) "building"関係のPOIのみに絞る
        	OsmBean osm = testdo(Paths.get("src/test/resources", "sample_a_bldg_6697_op2.osm"));
			
        	// 既存ファイルとマージする
	        //osm.parse(Paths.get("sample_a_bldg_6697_op2.mrg.osm").toFile());
        	
			assertNotNull(osm.getRelationList());
			assertEquals(0, osm.getRelationList().size());
			
    		assertNotNull(osm.getBounds());
    		
			assertNotNull(osm.getRelationList());
			assertEquals(0, osm.getRelationList().size());
			
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
        	OsmBean osm = testdo(Paths.get("src/test/resources", "sample_a3_bldg_6697_op2.osm"));
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
