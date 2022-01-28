package osm.surveyor.update;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import osm.surveyor.osm.BodyMap;
import osm.surveyor.osm.BoundsBean;
import osm.surveyor.osm.NodeBean;
import osm.surveyor.osm.OsmBean;
import osm.surveyor.osm.WayBean;

public class Test_Fujitv extends OsmUpdaterTest {

	@Before
	public void setup() {
		try {
			Files.copy(
				Paths.get("./src/test/resources/fujitv_bldg_6697_op2.osm"),
				Paths.get("./fujitv_bldg_6697_op2.osm"), REPLACE_EXISTING
			);
		}
		catch (FileAlreadyExistsException ee) {}
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		try {
			Files.copy(
				Paths.get("./src/test/resources/fujitv_bldg_6697_op2.org.osm"),
				Paths.get("./fujitv_bldg_6697_op2.org.osm"), REPLACE_EXISTING
			);
		}
		catch (FileAlreadyExistsException ee) {}
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * `mvn test -Dtest=osm.surveyor.update.FujitvTest#test_download`
	 * 
	 * OSMから「フジテレビ」ビル周辺のデータをダウンロード
	 * 
	 */
	@Test
	public void test_download() {
		BodyMap map = testdo(Paths.get("./fujitv_bldg_6697_op2.osm"));
		OsmBean osm = (OsmBean) map.get("osm");
		OsmBean org = (OsmBean) map.get("org");
		OsmBean mrg = (OsmBean) map.get("mrg");
		try {
	        assertNotNull(osm);
	        assertNotNull(org);
	        assertNotNull(mrg);
	        BoundsBean bound = mrg.getBounds();
	        assertNotNull(bound);
	        assertEquals("35.6282", bound.maxlat);
	        assertEquals("139.7782", bound.maxlon);
	        assertEquals("139.7713", bound.minlon);
	        assertEquals("35.6255", bound.minlat);
	        
	        List<NodeBean> nodes = mrg.getNodeList();
	        assertNotNull(nodes);
	        for (NodeBean node : nodes) {
	        	assertNotEquals(0, node.getId());
	        }
	        
	        List<WayBean> ways = mrg.getWayList();
	        assertNotNull(ways);
	        for (WayBean way : ways) {
	        	assertFalse(way.getFix());
	        }
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
