package osm.surveyor.osm.api;

import org.junit.Test;

import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_53375768 extends CitygmlFileTest {

	/**
	 * Issue #12 「v1.2.4 単独の建物でもbuilding:part」
	 * https://github.com/yuuhayashi/citygml-osm/issues/12
	 */
	@Test
	public void test53375768() {
		try {
			OsmDom osm = testdo("./src/test/resources/53375768_bldg_6697_op.gml");
			assertNotNull(osm.ways);
			for (String id : osm.ways.keySet()) {
				ElementWay way = osm.ways.get(id);
				assertNotNull(way);

				if (way.getTagValue("source").endsWith("20209-bldg-69160")) {
					assertNull(way.getTagValue("building:part"));
					assertNotNull(way.getTagValue("building"));
				}
				if (way.getTagValue("source").endsWith("20209-bldg-69158")) {
					assertNull(way.getTagValue("building:part"));
					assertNotNull(way.getTagValue("building"));
				}
				if (way.getTagValue("source").endsWith("20209-bldg-69157")) {
					assertNull(way.getTagValue("building:part"));
					assertNotNull(way.getTagValue("building"));
				}
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
	
}
