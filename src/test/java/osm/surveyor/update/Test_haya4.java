package osm.surveyor.update;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import osm.surveyor.osm.BodyMap;
import osm.surveyor.osm.BoundsBean;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.NdBean;
import osm.surveyor.osm.NodeBean;
import osm.surveyor.osm.OsmBean;
import osm.surveyor.osm.RelationBean;
import osm.surveyor.osm.WayBean;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_haya4 extends OsmUpdaterTest {
	static final String SOURCE = "haya4_bldg_6697_op2";
	
	@Before
	public void setup() {
		try {
			Files.copy(
				Paths.get("./src/test/resources/haya4_bldg_6697_op2.osm"),
				Paths.get("./haya4_bldg_6697_op2.osm")
			);
		}
		catch (FileAlreadyExistsException ee) {}
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		try {
			Files.copy(
				Paths.get("./src/test/resources/haya4_bldg_6697_op2.org.osm"),
				Paths.get("./haya4_bldg_6697_op2.org.osm")
			);
		}
		catch (FileAlreadyExistsException ee) {}
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
	}

	/**
	 * `mvn test -Dtest=OsmUpdaterTest_haya4#test_way241755306`
	 * https://www.openstreetmap.org/ウェイ/241755306
	 * 神奈川県 綾瀬市
	 * org:WAY:289757586 = "NODE:entrance=yes"を持つウェイ
	 * org:WAY:241755306 = 林宅
	 */
	@Test
	public void test_way241755306() {
		BodyMap map = testdo(Paths.get("./haya4_bldg_6697_op2.osm"));
		OsmBean osm = (OsmBean) map.get("osm");
		OsmBean org = (OsmBean) map.get("org");
		OsmBean mrg = (OsmBean) map.get("mrg");
        try {
	        assertNotNull(osm);
	        assertNotNull(org);
	        assertNotNull(mrg);
	        BoundsBean bound = mrg.getBounds();
	        assertNotNull(bound);
	        
	        List<NodeBean> nodes = mrg.getNodeList();
	        assertNotNull(nodes);
	        for (NodeBean node : nodes) {
	        	assertNotEquals(0, node.getId());
	        }
	        
	        List<WayBean> ways = mrg.getWayList();
	        assertNotNull(ways);
	        for (WayBean way : ways) {
	        	for (NdBean nd : way.getNdList()) {
	        		assertNotNull(mrg.getNode(nd.getRef()));
	        	}

	        	if (way.getId() == 289757586l) {
					// タグありNODEを持つWAYは、WAYは存在しないこと
	        		assertTrue(false);
				}
				if (way.getId() == 241755306l) {
					// リレーションメンバーのWAYは WAYは存在しないこと
	        		assertTrue(false);
				}
	        }
	        
	        List<RelationBean> relations = mrg.getRelationList();
	        assertNotNull(relations);
	        for (RelationBean relation : relations) {
	        	if (relation.getId() == -178479) {
	        		assertTrue(relation.isMultipolygon());
	        		assertEquals(3, relation.getMemberList().size());
		        	for (MemberBean member : relation.getMemberList()) {
		        		assertTrue(member.isWay());
		        		if (member.getRole().equals("outer")) {
		        			assertEquals(289757576, member.getRef());
		        			WayBean way = mrg.getWay(member.getRef());
		        			assertNotNull(way);
		        			assertNull(way.getTag("building:part"));
		        		}
		        		if (member.getRole().equals("inner")) {
		        			assertTrue(member.getRef() < 0);
		        		}
		        	}
	        	}
	        	if (relation.getId() == -178478) {
	        		assertTrue(relation.isBuilding());
	        		assertEquals(3, relation.getMemberList().size());
		        	for (MemberBean member : relation.getMemberList()) {
		        		if (member.getRole().equals("outline")) {
		        			assertTrue(member.getRef() < 0);
		        		}
		        		if (member.getRole().equals("part")) {
			        		assertTrue(member.isWay());
		        			WayBean way = mrg.getWay(member.getRef());
		        			assertNotNull(way);
		        			assertNotNull(way.getTag("building:part"));
		        			assertNull(way.getTag("building"));
		        		}
		        	}
	        	}
	        	for (MemberBean member : relation.getMemberList()) {
	        		if (member.isWay()) {
		        		assertNotNull(mrg.getWay(member.getRef()));
	        		}
	        		if (member.isRelation()) {
		        		assertNotNull(mrg.getRelation(member.getRef()));
	        		}
	        	}
	        }
	        
	        assertEquals(9, ways.size());
	        assertEquals(3, mrg.getRelationList().size());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
