package osm.surveyor.update;

import java.nio.file.Paths;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runners.MethodSorters;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.OsmBean;
import osm.surveyor.osm.WayBean;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OsmUpdaterTest_A extends OsmUpdaterTest {

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

			assertEquals(2, osm.getWayList().size());
			for (WayBean way : osm.getWayList()) {
				assertNotNull(way);
				
				if (way.getTagValue("source").endsWith("; 13111-bldg-365")) {
					assertEquals("modify", way.getAction());
					assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365", way.getTagValue("source"));
					assertEquals("東京都大田区南六郷三丁目", way.getTagValue("addr:full"));
					assertEquals("13111058003", way.getTagValue("addr:ref"));
					assertEquals("2.4", way.getTagValue("height"));
					assertEquals("2.75", way.getTagValue("ele"));
					assertEquals("yes", way.getTagValue("building"));
					assertEquals(6, way.getTagList().size());
				}
				else if (way.getTagValue("source").endsWith("; 13111-bldg-466")) {
					assertEquals("modify", way.getAction());
					assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466", way.getTagValue("source"));
					assertEquals("東京都大田区南六郷三丁目", way.getTagValue("addr:full"));
					assertEquals("4.6", way.getTagValue("height"));
					assertEquals("2.67", way.getTagValue("ele"));
					assertEquals("1976", way.getTagValue("start_date"));
					assertEquals("yes", way.getTagValue("building"));
					assertEquals("2", way.getTagValue("building:levels"));
					assertEquals("1", way.getTagValue("building:levels:underground"));
					assertEquals("PLATEAUデータで更新されています", way.getTagValue("fixme"));
					assertEquals(9, way.getTagList().size());
				}
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
}
