package osm.surveyor.osm.api;

import org.junit.Test;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_A extends GmlLoadRouteTest {
	
	/**
	 * `mvn test -Dtest=CitygmlFileTest_A#testSample_a`
	 */
	@Test
	public void testSample_a() {
        OsmDom osm = testdo("./src/test/resources/sample_a_bldg_6697_op2.gml");
        try {
			assertNotNull(osm.relations);
			assertEquals(0, osm.relations.size());
			for (String id : osm.ways.keySet()) {
				ElementWay way = osm.ways.get(id);
				assertNotNull(way);
				
				if (way.getTagValue("ref:MLIT_PLATEAU") != null) {
					if (way.getTagValue("ref:MLIT_PLATEAU").equals("13111-bldg-365")) {
						assertEquals("13111-bldg-365", way.getTagValue("ref:MLIT_PLATEAU"));
						assertEquals("東京都大田区南六郷三丁目", way.getTagValue("addr:full"));
						assertEquals("2.4", way.getTagValue("height"));
						assertEquals("2.8", way.getTagValue("ele"));
						assertEquals("yes", way.getTagValue("building"));
						assertEquals(5, way.getTagList().size());
					}
					else if (way.getTagValue("ref:MLIT_PLATEAU").equals("13111-bldg-466")) {
						assertEquals("13111-bldg-466", way.getTagValue("ref:MLIT_PLATEAU"));
						assertEquals("東京都大田区南六郷三丁目", way.getTagValue("addr:full"));
						assertEquals("4.6", way.getTagValue("height"));
						assertEquals("2.7", way.getTagValue("ele"));
						assertEquals("1976", way.getTagValue("start_date"));
						assertEquals("house", way.getTagValue("building"));
						assertEquals("2", way.getTagValue("building:levels"));
						assertEquals("1", way.getTagValue("building:levels:underground"));
						assertEquals(8, way.getTagList().size());
					}
				}
			}
			assertEquals(2, osm.ways.size());
			assertEquals(0, osm.relations.size());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
