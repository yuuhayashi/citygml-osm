package osm.surveyor.download;

import java.nio.file.Paths;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.OsmBean;
import osm.surveyor.osm.RelationBean;
import osm.surveyor.osm.WayBean;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_B extends TestDownload {
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
        	OsmBean osm = testdo(Paths.get("src/test/resources",  SOURCE+".osm"));
    		assertNotNull(osm.getBounds());
			assertNotNull(osm.getRelationList());
			
			// 中空を持つビルディングリレーションが存在する
			assertTrue(osm.getRelationList().size() >= 1);
			for (RelationBean relation : osm.getRelationList()) {
				List<MemberBean> members = relation.getMemberList();
				for (MemberBean member : members) {
					// リレーションメンバーのWAYは 'FIX=true' であること
					if (member.isWay()) {
						WayBean way = osm.getWay(member.getRef());
						assertTrue(way.getFix());
					}
				}
			}
			
			assertTrue(osm.getWayList().size() > 100);
			for (WayBean way : osm.getWayList()) {
				// "highway"WAYは存在しないこと
				assertNull(way.getTagValue("highway"));
				// "landuse"WAYは存在しないこと
				assertNull(way.getTagValue("landuse"));
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}