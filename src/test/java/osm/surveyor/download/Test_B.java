package osm.surveyor.download;

import java.nio.file.Paths;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import osm.surveyor.osm.BodyMap;
import osm.surveyor.osm.OsmBean;
import osm.surveyor.osm.way.WayModel;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_B extends DownloadTest {
	static final String SOURCE = "sample_b_bldg_6697_op2";

	/**
	 * `mvn test -Dtest=OsmUpdaterTest_B#testSample_b`
	 * 
	 * ・中空を持つビルディングリレーションが存在する
	 * ・タグありのノードを含むWAYの存在は不明
	 * 
	 * - "highway"WAYは存在しないこと
	 * - "landuse"WAYは存在しないこと
	 */
	@Test
	public void testSample_b() {
        try {
        	BodyMap map = testdo(Paths.get("src/test/resources/", SOURCE +".osm"));
    		OsmBean org = (OsmBean) map.get("org");

    		assertNotNull(org.getBounds());
			assertNotNull(org.getRelationList());
			
			// 中空を持つビルディングリレーションが存在する
			assertTrue(org.getRelationList().size() >= 1);
			assertTrue(org.getWayList().size() > 100);
			for (WayModel way : org.getWayList()) {
				// "highway"WAYは存在しないこと
				assertNull(way.getPoiBean().getTagValue("highway"));
				// "landuse"WAYは存在しないこと
				assertNull(way.getPoiBean().getTagValue("landuse"));
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
