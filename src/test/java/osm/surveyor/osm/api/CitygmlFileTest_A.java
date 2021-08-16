package osm.surveyor.osm.api;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_A extends CitygmlFileTest {

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
				
				if (way.getTagValue("source").endsWith("; 13111-bldg-365")) {
					assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365", way.getTagValue("source"));
					assertEquals("東京都大田区南六郷三丁目", way.getTagValue("addr:full"));
					assertEquals("13111058003", way.getTagValue("addr:ref"));
					assertEquals("2.4", way.getTagValue("height"));
					assertEquals("2.75", way.getTagValue("ele"));
					assertEquals("yes", way.getTagValue("building"));
					assertEquals(6, way.getTagList().size());
				}
				else if (way.getTagValue("source").endsWith("; 13111-bldg-466")) {
					assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466", way.getTagValue("source"));
					assertEquals("東京都大田区南六郷三丁目", way.getTagValue("addr:full"));
					assertEquals("4.6", way.getTagValue("height"));
					assertEquals("2.67", way.getTagValue("ele"));
					assertEquals("1976", way.getTagValue("start_date"));
					assertEquals("house", way.getTagValue("building"));
					assertEquals("2", way.getTagValue("building:levels"));
					assertEquals("1", way.getTagValue("building:levels:underground"));
					assertEquals(8, way.getTagList().size());
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
	 * 値のない'addr:ref'
	 * 13111-bldg-141846
	 */
	@SuppressWarnings("deprecation")
	@Test
	@Category(DetailTests.class)
	public void testSample_aa() {
        OsmDom osm = testdo("./src/test/resources/sample_aa_bldg_6697_op2.gml");
        try {
			assertNotNull(osm.relations);
			assertEquals(0, osm.relations.size());
			for (String id : osm.ways.keySet()) {
				ElementWay way = osm.ways.get(id);
				assertNotNull(way);
				if (way.getTagValue("source").endsWith("; 13111-bldg-141846")) {
					assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-141846"));
					assertThat(way.getTagValue("addr:full"), is("東京都大田区大森東一丁目"));
					assertThat(way.getTagValue("height"), is("7.1"));
					assertEquals("1.91", way.getTagValue("ele"));
					assertThat(way.getTagValue("building"), is("yes"));
					assertEquals(5, way.getTagList().size());
				}
			}
			assertEquals(1, osm.ways.size());
			assertEquals(0, osm.relations.size());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	/**
	 * 接触しているビルディング
	 * 13111-bldg-64135
	 */
	@SuppressWarnings("deprecation")
	@Test
	@Category(DetailTests.class)
	public void testSample_ab() {
        OsmDom osm = testdo("./src/test/resources/sample_ab_bldg_6697_op2.gml");
        try {
			assertNotNull(osm.relations);
			for (String id : osm.ways.keySet()) {
				ElementWay way = osm.ways.get(id);
				assertNotNull(way);
				assertThat(way.getTagValue("source"), startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-"));
				assertThat(way.getTagValue("addr:full"), is("東京都大田区大森西五丁目"));
				assertThat(way.getTagValue("addr:ref"), is("13111006005"));
				assertNotNull(way.getTagValue("height"));
				assertNotNull(way.getTagValue("ele"));
				assertThat(way.getTagValue("building"), is("yes"));
				assertTrue(way.getTagList().size() >= 6);
			}
			assertEquals(2, osm.ways.size());
			assertEquals(0, osm.relations.size());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
