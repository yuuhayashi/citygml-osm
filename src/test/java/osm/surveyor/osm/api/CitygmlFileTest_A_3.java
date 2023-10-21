package osm.surveyor.osm.api;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_A_3 extends CitygmlFileTest3 {

	/**
	 * `mvn test -Dtest=CitygmlFileTest_A#testSample_a3_removeSinglePart`
	 * 
	 */
	@Test
	@Category(DetailTests.class)
	public void testSample_a3_removeSinglePart() {
		try {
			OsmDom osm = testdo("./src/test/resources/sample_a_bldg_6697_op2.gml");
	        
			assertNotNull(osm.relations);
			assertEquals(osm.relations.size(), (0));
			
			for (String id : osm.ways.keySet()) {
				ElementWay way = osm.ways.get(id);
				assertNotNull(way);

				if (way.getTagValue("ref:MLIT_PLATEAU").endsWith("13111-bldg-365")) {
					assertEquals(way.getTagValue("building"), ("yes"));
					assertEquals(way.getTagValue("ref:MLIT_PLATEAU"), ("13111-bldg-365"));
					assertNull(way.getTagValue("addr:full"));
					assertEquals(way.getTagValue("height"), ("2.4"));
					assertEquals(way.getTagValue("ele"), ("2.8"));
					assertEquals(4, way.getTagList().size());
				}
				else if (way.getTagValue("ref:MLIT_PLATEAU").endsWith("13111-bldg-466")) {
					assertEquals(way.getTagValue("ref:MLIT_PLATEAU"), ("13111-bldg-466"));
					assertNull(way.getTagValue("addr:full"));
					assertEquals(way.getTagValue("height"), ("4.6"));
					assertEquals(way.getTagValue("ele"), ("2.7"));
					assertEquals(way.getTagValue("start_date"), ("1976"));
					assertEquals(way.getTagValue("building"), ("house"));
					assertEquals(way.getTagValue("building:levels"), ("2"));
					assertEquals(way.getTagValue("building:levels:underground"), ("1"));
					assertEquals(7, way.getTagList().size());
				}
			}
			assertEquals(osm.ways.size(), (2));
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

}
