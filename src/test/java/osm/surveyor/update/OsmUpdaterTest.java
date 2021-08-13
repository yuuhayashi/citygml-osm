package osm.surveyor.update;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.nio.file.Paths;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.ElementWay;

public class OsmUpdaterTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * `mvn test -Dtest=OsmUpdaterTest#test_53392547`
	 * 
	 */
	@Test
	public void test_53392547() {
		try {
        	AllTests.accept(Paths.get("src/test/resources", "53392547_bldg_6697_op2.osm"));
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * `mvn test -Dtest=OsmUpdaterTest#test_241755306`
	 * https://www.openstreetmap.org/ウェイ/241755306
	 * 神奈川県 綾瀬市
	 */
	@Category(DetailTests.class)
	public void test_241755306() {
        try {
			String filename = "haya4_bldg_6697_op2";
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
					assertEquals(way.getAction(), is("modify"));
					assertTrue(way.getId() < 0);
					assertEquals(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
					assertEquals(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
					assertEquals(way.getTagValue("addr:ref"), is("13111058003"));
					assertEquals(way.getTagValue("height"), is("2.4"));
					assertEquals(way.getTagValue("ele"), is("2.749"));
					assertEquals(way.getTagValue("building"), is("yes"));
					assertEquals(way.getTagList().size(), is(6));
				}
				else if (way.getTagValue("source").endsWith("; 13111-bldg-466")) {
					waycnt++;
					assertEquals(way.getAction(), is("modify"));
					assertEquals(169195173l, way.getId());
					assertEquals("12032994", way.getChangeset());
					assertEquals(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
					assertEquals(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
					assertEquals(way.getTagValue("height"), is("4.6"));
					assertEquals(way.getTagValue("ele"), is("2.671"));
					assertEquals(way.getTagValue("building"), is("parking"));
					assertEquals(way.getTagValue("fixme"), is("PLATEAUデータで更新されています"));
					assertEquals(way.getTagList().size(), is(6));
				}
			}
			assertEquals(waycnt, is(2));
			assertEquals(updater.ddom.ways.size(), is(2));
			assertNotNull(updater.ddom.relations);
			assertEquals(updater.ddom.relations.size(), is(0));
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
