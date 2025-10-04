package osm.surveyor.osm.api;

import java.io.File;
import java.util.List;

import org.junit.Test;

import osm.surveyor.osm.BoundsBean;
import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.way.WayModel;

/**
 *  "14150_sagamihara-shi_2024" "53392278_bldg_6697_op.gml"
 * 
 * v3.0.3
 *   1st NullPointerException
 *   
 * @author hayashi
 */
public class CitygmlFileTest_Issue144 extends GmlLoadRouteTest {

	/**
	 * Issue #144
	 *  "14150_sagamihara-shi_2024" "53392278_bldg_6697_op.gml"
	 */
	@Test
	public void issue144_53392278() {
		OsmDom dom = testdo("./src/test/resources/Issue144/53392278_bldg_6697_op.gml");
		try {
	        assertNotNull(dom);
	        BoundsBean bound = dom.getBounds();
	        assertNotNull(bound);
	        
	        List<WayModel> wayList = dom.getWayList();
			assertNotNull(wayList);
			assertNotEquals(0, wayList.size());
			assertNotNull(dom.getRelations());
	        assertNotEquals(0, dom.getRelations().size());
	        
	        // OSMファイルに書き出す
			File domfile = new File("OsmDom.osm");
	        dom.export(domfile);
	        assertTrue(domfile.exists());
		} catch (Exception e) {
			fail(e.toString());
		}
	}
}
