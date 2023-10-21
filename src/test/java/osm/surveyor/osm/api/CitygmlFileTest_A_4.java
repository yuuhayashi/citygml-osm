package osm.surveyor.osm.api;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_A_4 extends CitygmlFileTest4 {

	/**
	 * `mvn test -Dtest=CitygmlFileTest_A#testSample_a4_createOutline`
	 * 
	 */
	@Test
	@Category(DetailTests.class)
	public void testSample_a4_createOutline() {
		try {
			OsmDom osm = testdo("./src/test/resources/sample_a_bldg_6697_op2.gml");
	        
			for (String id : osm.ways.keySet()) {
				ElementWay way = osm.ways.get(id);
				assertNotNull(way);
				if (way.getTagValue("ref:MLIT_PLATEAU").equals("13111-bldg-365")) {
					assertEquals(way.getTagValue("building"), ("yes"));
					assertEquals(way.getTagValue("ref:MLIT_PLATEAU"), ("13111-bldg-365"));
					assertNull(way.getTagValue("addr:full"));
					assertEquals(way.getTagValue("height"), ("2.4"));
					assertEquals(way.getTagValue("ele"), ("2.8"));
					assertEquals(4, way.getTagList().size());
				}
				else if (way.getTagValue("ref:MLIT_PLATEAU").endsWith("13111-bldg-466")) {
					assertEquals(way.getTagValue("building"), ("house"));
					assertEquals(way.getTagValue("building:levels"), ("2"));
					assertEquals(way.getTagValue("building:levels:underground"), ("1"));
					assertEquals(way.getTagValue("ref:MLIT_PLATEAU"), ("13111-bldg-466"));
					assertNull(way.getTagValue("addr:full"));
					assertEquals(way.getTagValue("height"), ("4.6"));
					assertEquals(way.getTagValue("ele"), ("2.7"));
					assertEquals(way.getTagValue("start_date"), ("1976"));
					assertEquals(way.getTagList().size(), (7));
				}
				else {
					fail(way.getTagValue("source"));
				}
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
	
}
