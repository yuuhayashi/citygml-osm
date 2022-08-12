package osm.surveyor.osm.api;

import static org.hamcrest.CoreMatchers.is;

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
	@SuppressWarnings("deprecation")
	@Test
	@Category(DetailTests.class)
	public void testSample_a3_removeSinglePart() {
		try {
			OsmDom osm = testdo("./src/test/resources/sample_a_bldg_6697_op2.gml");
	        
			assertNotNull(osm.relations);
			assertThat(osm.relations.size(), is(0));
			
			for (String id : osm.ways.keySet()) {
				ElementWay way = osm.ways.get(id);
				assertNotNull(way);

				if (way.getTagValue("ref:MLIT_PLATEAU").endsWith("13111-bldg-365")) {
					assertThat(way.getTagValue("building"), is("yes"));
					assertThat(way.getTagValue("ref:MLIT_PLATEAU"), is("13111-bldg-365"));
					assertNull(way.getTagValue("addr:full"));
					assertThat(way.getTagValue("height"), is("2.4"));
					assertThat(way.getTagValue("ele"), is("2.8"));
					assertEquals(4, way.getTagList().size());
				}
				else if (way.getTagValue("ref:MLIT_PLATEAU").endsWith("13111-bldg-466")) {
					assertThat(way.getTagValue("ref:MLIT_PLATEAU"), is("13111-bldg-466"));
					assertNull(way.getTagValue("addr:full"));
					assertThat(way.getTagValue("height"), is("4.6"));
					assertThat(way.getTagValue("ele"), is("2.7"));
					assertThat(way.getTagValue("start_date"), is("1976"));
					assertThat(way.getTagValue("building"), is("house"));
					assertThat(way.getTagValue("building:levels"), is("2"));
					assertThat(way.getTagValue("building:levels:underground"), is("1"));
					assertEquals(7, way.getTagList().size());
				}
			}
			assertThat(osm.ways.size(), is(2));
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

}
