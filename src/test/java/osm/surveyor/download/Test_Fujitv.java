package osm.surveyor.download;

import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

import osm.surveyor.osm.BodyMap;
import osm.surveyor.osm.BoundsBean;
import osm.surveyor.osm.NodeBean;
import osm.surveyor.osm.OsmBean;

public class Test_Fujitv extends DownloadTest {
	
	/**
	 * `mvn test -Dtest=FujitvTest#test_download`
	 * 
	 * OSMから「フジテレビ」ビル周辺のデータをダウンロード
	 * 
	 */
	@Test
	public void test_download() {
		BodyMap map = testdo(Paths.get("./src/test/resources/fujitv_bldg_6697_op2.osm"));
		OsmBean org = (OsmBean) map.get("org");
		try {
	        assertNotNull(org);
	        BoundsBean bound = org.getBounds();
	        assertNotNull(bound);
	        assertEquals("35.6282000", bound.maxlat);
	        assertEquals("139.7782000", bound.maxlon);
	        assertEquals("139.7713000", bound.minlon);
	        assertEquals("35.6255000", bound.minlat);
	        
	        List<NodeBean> nodes = org.getNodeList();
	        assertNotNull(nodes);
	        for (NodeBean node : nodes) {
	        	assertNotEquals(0, node.getId());
	        }
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
