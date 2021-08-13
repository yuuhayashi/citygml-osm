package osm.surveyor.update;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.nio.file.Paths;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runners.MethodSorters;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.ElementBounds;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OsmUpdaterTest_A {

	/**
	 * 東京都大田区南六郷三丁目
	 * `mvn test -Dtest=OsmUpdaterTest_A#test_a`
	 */
	@Test
	public void test_a() {
        try {
			AllTests.accept(Paths.get("src/test/resources", "sample_a_bldg_6697_op2.osm"));

			OsmDom osm = new OsmDom();
	        osm.parse(Paths.get("sample_a_bldg_6697_op2.mrg.osm").toFile());
			assertNotNull(osm.relations);
			assertEquals(0, osm.relations.size());
			for (String id : osm.ways.keySet()) {
				ElementWay way = osm.ways.get(id);
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
			assertEquals(2, osm.ways.size());
			assertEquals(0, osm.relations.size());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}


	/**
	 * `mvn test -Dtest=OsmUpdaterTest_A#test_b1`
	 * "sample_b_bldg_6697_op2.org.osm"の出力
	 */
	@Test
	@Category(DetailTests.class)
	public void test_b1() {
        try {
    		// 指定されたOSMファイルの<bound/>を取得する
        	OsmDom dom = new OsmDom();
        	dom.parse(Paths.get("src/test/resources/", "sample_a_bldg_6697_op2.osm").toFile());
    		ElementBounds bounds = dom.getBounds();

    		// OSMから<bound>範囲内の現在のデータを取得する
    		OsmDom sdom = new OsmDom();
    		sdom.setBounds(bounds);
    		sdom.downloadMap();
    		sdom.export(Paths.get("sample_a_bldg_6697_op2.org.osm").toFile());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	/**
	 * `mvn test -Dtest=OsmUpdaterTest_A#test_b1check`
	 * 
	 * "sample_a_bldg_6697_op2.org.osm"のチェック
	 * 'filterBuilding()'のテスト
	 * 
	 * INPUT： "sample_a_bldg_6697_op2.org.osm"
	 */
	@Test
	@Category(DetailTests.class)
	public void test_b1check() {
        try {
			OsmDom osm = new OsmDom();
			osm.parse(Paths.get("src/test/resources/sample_a_bldg_6697_op2.org.osm").toFile());
			assertTrue(osm.ways.size() > 0);
			assertTrue(osm.nodes.size() > 10);

			OsmDom sdom = new OsmDom();
			osm.filterBuilding(sdom);

			assertTrue(sdom.ways.size() > 0);
			assertEquals(0, sdom.relations.size());
			assertTrue(sdom.nodes.size() >= 4);
        } catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	/**
	 * `mvn test -Dtest=OsmUpdaterTest_A#test_b2`
	 * 
	 * 'filterBuilding()'のテスト
	 * 
	 * INPUT： "sample_a_bldg_6697_op2.org.osm"
	 */
	@Test
	@Category(DetailTests.class)
	public void test_b2() {
        try {
    		// 指定されたOSMファイルの<bound/>を取得する
        	OsmDom sdom = new OsmDom();
        	OsmDom org = new OsmDom();
        	org.parse(Paths.get("src/test/resources/sample_a_bldg_6697_op2.org.osm").toFile());
    		org.filterBuilding(sdom);

			assertTrue(sdom.ways.size() >= 1);
			assertEquals(0, sdom.relations.size());
			assertTrue(sdom.nodes.size() >= 4);
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	/**
	 * `mvn test -Dtest=OsmUpdaterTest_A#test_b3`
	 * 
	 * 'filterBuilding()'のテスト
	 * 
	 * INPUT： "sample_a_bldg_6697_op2.osm"
	 * INPUT： "sample_a_bldg_6697_op2.org.osm"
	 */
	@Test
	@Category(DetailTests.class)
	public void test_b3() {
        try {
			String filename = "sample_a_bldg_6697_op2";
			OsmUpdater updater = new OsmUpdater(Paths.get("src/test/resources/"+ filename +".osm").toFile());

			// 疑似ダウンロード
			AllTests.download(updater, Paths.get("src/test/resources/"+ filename +".org.osm").toFile());
			
    		// 既存POIとマージする
			updater.load();
			
			updater.ddom.export(Paths.get(filename +".mrg.osm").toFile());
    		
			int waycnt = 0;
			for (String id : updater.ddom.ways.keySet()) {
				ElementWay way = updater.ddom.ways.get(id);
				assertNotNull(way);
				
				if (way.getTagValue("source").endsWith("; 13111-bldg-365")) {
					waycnt++;
					assertEquals("modify", way.getAction());
					assertTrue(way.getId() < 0);
					assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365", way.getTagValue("source"));
					assertEquals("東京都大田区南六郷三丁目", way.getTagValue("addr:full"));
					assertEquals("13111058003", way.getTagValue("addr:ref"));
					assertEquals("2.4", way.getTagValue("height"));
					assertEquals("2.75", way.getTagValue("ele"));
					assertEquals("yes", way.getTagValue("building"));
					assertEquals(6, way.getTagList().size());
				}
				else if (way.getTagValue("source").endsWith("; 13111-bldg-466")) {
					waycnt++;
					assertEquals("modify", way.getAction());
					assertEquals(169195173l, way.getId());
					assertEquals("12032994", way.getChangeset());
					assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466", way.getTagValue("source"));
					assertEquals("東京都大田区南六郷三丁目", way.getTagValue("addr:full"));
					assertEquals("4.6", way.getTagValue("height"));
					assertEquals("2.67", way.getTagValue("ele"));
					assertEquals("parking", way.getTagValue("building"));
					assertEquals("1976", way.getTagValue("start_date"));
					assertEquals("2", way.getTagValue("building:levels"));
					assertEquals("1", way.getTagValue("building:levels:underground"));
					assertEquals("PLATEAUデータで更新されています", way.getTagValue("fixme"));
					assertEquals(9, way.getTagList().size());
				}
			}
			assertEquals(2, waycnt);
			assertEquals(2, updater.ddom.ways.size());
			assertNotNull(updater.ddom.relations);
			assertEquals(0, updater.ddom.relations.size());
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
	public void test_d1() {
        try {
    		// 指定されたOSMファイルの<bound/>を取得する
        	OsmDom org = new OsmDom();
        	OsmDom sdom = new OsmDom();
        	org.parse(Paths.get("src/test/resources/", "sample_a3_bldg_6697_op2.org.osm").toFile());
    		org.filterBuilding(sdom);

    		sdom.export(Paths.get("org.xml").toFile());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
