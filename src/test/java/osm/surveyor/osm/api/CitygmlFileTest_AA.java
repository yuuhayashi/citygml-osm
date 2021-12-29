package osm.surveyor.osm.api;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;

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
				if (way.getTagValue("ref:MLIT_PLATEAU").endsWith("13111-bldg-141846")) {
					assertThat(way.getTagValue("ref:MLIT_PLATEAU"), is("13111-bldg-141846"));
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
				assertThat(way.getTagValue("ref:MLIT_PLATEAU"), startsWith("13111-bldg-"));
				assertThat(way.getTagValue("addr:full"), is("東京都大田区大森西五丁目"));
				assertNotNull(way.getTagValue("height"));
				assertNotNull(way.getTagValue("ele"));
				assertThat(way.getTagValue("addr:ref"), is("13111006005"));
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
