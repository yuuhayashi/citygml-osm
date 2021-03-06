package osm.surveyor.osm.api;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;
import osm.surveyor.osm.BoundsBean;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_52396075 extends GmlLoadRouteTest {

	/**
	 * Issue #13 「v1.2.4 「箱根町」のデータを変換できない」
	 * https://github.com/yuuhayashi/citygml-osm/issues/13
	 * map "A :建物" as A {
	 *  bldg:Building:id => bldg-cf368523-863d-4bf8-8931-3a9cf99e4e58
	 *  bldg:Building:stringAttribute[name="建物ID"] => 14382-bldg-10718
	 *  bldg:measuredHeight => 13.3
	 *  bldg:lod0FootPrint:Polygon:posList:height => 728.31
	 *  Envelope:srsName => http://www.opengis.net/def/crs/EPSG/0/6697
	 * }
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void test52396075() {
		OsmDom osm = testdo("./src/test/resources/52396075_bldg_6697_op.gml");
		try {
	        assertNotNull(osm);
	        BoundsBean bound = osm.getBounds();
	        assertEquals("35.231564611877225", bound.maxlat);
	        assertEquals("139.06625261006525", bound.maxlon);
	        assertEquals("139.06619815002912", bound.minlon);
	        assertEquals("35.23151859137091", bound.minlat);
	        
			assertNotNull(osm.ways);
			for (String id : osm.ways.keySet()) {
				ElementWay way = osm.ways.get(id);
				assertNotNull(way);
				assertThat(way.getTagValue("building"), is("yes"));
				assertThat(way.getTagValue("height"), is("13.3"));
				assertThat(way.getTagValue("ele"), is("728.3"));
				assertThat(way.getTagValue("ref:MLIT_PLATEAU"), is("14382-bldg-10718"));
				assertTrue(way.getTagList().size() >= 4);
			}
			assertEquals(1, osm.ways.size());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
