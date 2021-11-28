package osm.surveyor.update;

import static org.hamcrest.CoreMatchers.is;

import java.nio.file.Paths;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runners.MethodSorters;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.OsmBean;
import osm.surveyor.osm.WayBean;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OsmUpdaterTest_haya4 extends OsmUpdaterTest {
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
        	OsmBean osm = testdo(Paths.get("src/test/resources",  SOURCE+".osm"));
			int waycnt = 0;
			for (WayBean way : osm.getWayList()) {
				if (way.getTagValue("source").endsWith("; 13111-bldg-365")) {
					waycnt++;
					assertEquals(way.getAction(), is("modify"));
					assertTrue(way.getId() < 0);
					assertEquals(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
					assertEquals(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
					assertNull(way.getTagValue("addr:ref"));
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
			assertEquals(osm.getWayList().size(), is(2));
			assertNotNull(osm.getRelationList());
			assertEquals(osm.getRelationList().size(), is(0));
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
