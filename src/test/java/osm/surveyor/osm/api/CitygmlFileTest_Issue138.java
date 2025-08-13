package osm.surveyor.osm.api;

import org.junit.Test;
import osm.surveyor.osm.BoundsBean;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_Issue138 extends GmlLoadRouteTest {

	/**
	 * Issue138 「同じ位置を持つウェイ」マルチポリゴンのINNER
	 * https://github.com/yuuhayashi/citygml-osm/issues/138
	 */
	@Test
	public void test51357441() {
		OsmDom osm = testdo("./src/test/resources/51357441_bldg_Issue138.gml");
		try {
	        assertNotNull(osm);
	        BoundsBean bound = osm.getBounds();
			assertNotNull(bound);
	        
			assertNotNull(osm.getWayMap());
			assertEquals(3, osm.getWayMap().size());
			for (String id : osm.getWayMap().keySet()) {
				ElementWay way = (ElementWay)osm.getWayMap().get(id);
				assertNotNull(way);
			}
			assertNotNull(osm.getRelations());
			assertEquals(1, osm.getRelations().size());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
