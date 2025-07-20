package osm.surveyor.osm.api;

import org.junit.Test;

import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_53375768 extends GmlLoadRouteTest {

	/**
	 * Issue #12 「v1.2.4 単独の建物でもbuilding:part」
	 * https://github.com/yuuhayashi/citygml-osm/issues/12
	 */
	@Test
	public void test53375768() {
		try {
			OsmDom osm = testdo("./src/test/resources/53375768_bldg_6697_op.gml");
			assertNotNull(osm.getWayMap());
			for (String id : osm.getWayMap().keySet()) {
				ElementWay way = (ElementWay)osm.getWayMap().get(id);
				assertNotNull(way);

				if (way.getTagValue("ref:MLIT_PLATEAU") != null) {
					if (way.getTagValue("ref:MLIT_PLATEAU").equals("20209-bldg-69160")) {
						assertNull(way.getTagValue("building:part"));
						assertNotNull(way.getTagValue("building"));
					}
					if (way.getTagValue("ref:MLIT_PLATEAU").endsWith("20209-bldg-69158")) {
						assertNull(way.getTagValue("building:part"));
						assertNotNull(way.getTagValue("building"));
					}
					if (way.getTagValue("ref:MLIT_PLATEAU").endsWith("20209-bldg-69157")) {
						assertNull(way.getTagValue("building:part"));
						assertNotNull(way.getTagValue("building"));
					}
				}
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
	
}
