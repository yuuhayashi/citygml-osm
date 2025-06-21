package osm.surveyor.osm.api;

import org.junit.Test;
import osm.surveyor.osm.BoundsBean;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

/**
 * Issue #33 第2段階のmrgファイルの作成処理の途中で、いくつかのファイルで下記のようなエラーが発生し、mrg.osmファイルが作成されません。
 * 50303545_bldg_6697_op、
 * 50303554_bldg_6697_op、
 * 50303555_bldg_6697_op
 */
public class CitygmlFileTest_Issue33 extends GmlLoadRouteTest {

	/**
	 * 飯塚市
	 */
	@Test
	public void test50303524() {
		OsmDom osm = testdo("./src/test/resources/50303524_bldg_6697_op.gml");
		try {
	        assertNotNull(osm);
	        BoundsBean bound = osm.getBounds();
			assertNotNull(bound);
	        
			assertNotNull(osm.ways);
			for (String id : osm.ways.keySet()) {
				ElementWay way = (ElementWay)osm.ways.get(id);
				assertNotNull(way);
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void test53360576() {
		OsmDom osm = testdo("./src/test/resources/53360576_bldg_6697_op.gml");
		try {
	        assertNotNull(osm);
	        BoundsBean bound = osm.getBounds();
			assertNotNull(bound);
	        
			assertNotNull(osm.ways);
			for (String id : osm.ways.keySet()) {
				ElementWay way = (ElementWay)osm.ways.get(id);
				assertNotNull(way);
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
