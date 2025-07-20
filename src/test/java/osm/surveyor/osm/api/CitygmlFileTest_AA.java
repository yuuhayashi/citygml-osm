package osm.surveyor.osm.api;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_AA extends GmlLoadRouteTest {
	
	/**
	 * 値のない'addr:ref'
	 * 13111-bldg-141846
	 */
	@Test
	@Category(DetailTests.class)
	public void testSample_aa() {
        OsmDom osm = testdo("./src/test/resources/sample_aa_bldg_6697_op2.gml");
        try {
			assertNotNull(osm.relationMap);
			assertEquals(0, osm.relationMap.size());
			for (String id : osm.getWayMap().keySet()) {
				ElementWay way = (ElementWay)osm.getWayMap().get(id);
				assertNotNull(way);
				if (way.getTagValue("ref:MLIT_PLATEAU").endsWith("13111-bldg-141846")) {
					assertEquals(way.getTagValue("ref:MLIT_PLATEAU"), ("13111-bldg-141846"));
					assertNull(way.getTagValue("addr:full"));
					assertEquals(way.getTagValue("height"), ("7.1"));
					assertEquals("1.9", way.getTagValue("ele"));
					assertEquals(way.getTagValue("building"), ("yes"));
					//assertEquals(5, way.getTagList().size());
				}
			}
			assertEquals(1, osm.getWayMap().size());
			assertEquals(0, osm.relationMap.size());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	/**
	 * 接触しているビルディング
	 * 13111-bldg-64135
	 */
	@Test
	@Category(DetailTests.class)
	public void testSample_ab() {
        OsmDom osm = testdo("./src/test/resources/sample_ab_bldg_6697_op2.gml");
        try {
			assertNotNull(osm.relationMap);
			for (String id : osm.getWayMap().keySet()) {
				ElementWay way = (ElementWay)osm.getWayMap().get(id);
				assertNotNull(way);
				assertTrue(way.getTagValue("ref:MLIT_PLATEAU").startsWith("13111-bldg-"));
				assertNull(way.getTagValue("addr:full"));
				assertNotNull(way.getTagValue("height"));
				assertNotNull(way.getTagValue("ele"));
				//assertNull(way.getTagValue("addr:ref"), ("13111006005"));
				assertEquals(way.getTagValue("building"), ("yes"));
				//assertTrue(way.getTagList().size() >= 6);
			}
			assertEquals(2, osm.getWayMap().size());
			assertEquals(0, osm.relationMap.size());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
