package osm.surveyor.upload;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
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
public class Test_haya4 extends OsmUploadTest {
	
	@Before
	public void setup() {
		try {
			Files.copy(
				Paths.get("./src/test/resources/haya4_bldg_6697.checked.osm"),
				Paths.get("./checked.osm"), REPLACE_EXISTING
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
	public void test_4th() {
		BodyMap map = testdo(Paths.get("./checked.osm"));
		OsmBean release = (OsmBean) map.get("release");
        try {
	        assertNotNull(release);
	        BoundsBean bound = release.getBounds();
	        assertNotNull(bound);
	        
	        List<NodeBean> nodes = release.getNodeList();
	        assertNotNull(nodes);
	        for (NodeBean node : nodes) {
	        	assertNotEquals(0, node.getId());
	        }
	        
	        int checkCnt = 0;
	        List<WayBean> ways = release.getWayList();
	        assertNotNull(ways);
	        for (WayBean way : ways) {
	        	for (NdBean nd : way.getNdList()) {
	        		assertNotNull(release.getNode(nd.getRef()));
	        	}
	        	
	        	// `fixme`タグは除去されていること
				assertNull(way.getTag("MLIT_PLATEAU:fixme"));

	        	if (way.getId() == 289757586l) {
					// タグありNODEを持つWAYは、WAYは存在しないこと
	        		assertTrue(false);
				}
	        	else if (way.getId() == 241755306l) {
					// リレーションメンバーのWAYは WAYは存在しないこと
	        		assertTrue(false);
				}
				if (way.getId() == 289897904) {
					// アパート「宮久保 ９２」
					assertEquals("modify", way.getAction());
	        		assertEquals("宮久保 ９２", way.getTag("name").v);
	        		assertEquals("apartments", way.getTag("building").getValue());
	        		assertNotNull(way.getTag("ref:MLIT_PLATEAU"));
	        		assertEquals("11111-bldg-185615", way.getTag("ref:MLIT_PLATEAU").getValue());
	        		checkCnt++;
				}
				else if (way.getId() == 289757595) {
					// delete
					assertEquals("delete", way.getAction());
	        		assertEquals("house", way.getTag("building").getValue());
	        		checkCnt++;
				}
				else if (way.getId() == 289757584) {
					// modify
					assertEquals("modify", way.getAction());
	        		assertEquals("house", way.getTag("building").getValue());
	        		checkCnt++;
				}
				else if (way.getId() == 289757601) {
					// delete
					assertEquals("delete", way.getAction());
	        		assertEquals("house", way.getTag("building").getValue());
	        		checkCnt++;
				}
				else if (way.getId() == 289757565) {
					// modify
					assertEquals("modify", way.getAction());
	        		assertEquals("house", way.getTag("building").getValue());
	        		checkCnt++;
				}
	        }
	        assertEquals(5, checkCnt);
	        
	        List<RelationBean> relations = release.getRelationList();
	        assertNotNull(relations);
	        for (RelationBean relation : relations) {
	        	if (relation.getId() == -178479) {
	        		assertTrue(relation.isMultipolygon());
	        		assertEquals(3, relation.getMemberList().size());
		        	for (MemberBean member : relation.getMemberList()) {
		        		assertTrue(member.isWay());
		        		if (member.getRole().equals("outer")) {
		        			assertEquals(289757576, member.getRef());
		        			WayBean way = release.getWay(member.getRef());
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
		        			WayBean way = release.getWay(member.getRef());
		        			assertNotNull(way);
		        			assertNotNull(way.getTag("building:part"));
		        			assertNull(way.getTag("building"));
		        		}
		        	}
	        	}
	        	for (MemberBean member : relation.getMemberList()) {
	        		if (member.isWay()) {
		        		assertNotNull(release.getWay(member.getRef()));
	        		}
	        		if (member.isRelation()) {
		        		assertNotNull(release.getRelation(member.getRef()));
	        		}
	        	}
	        }
	        
	        assertEquals(14, ways.size());
	        assertEquals(3, release.getRelationList().size());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
