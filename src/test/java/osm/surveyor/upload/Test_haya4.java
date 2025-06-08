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
import osm.surveyor.osm.TagBean;
import osm.surveyor.osm.WayBean;
import osm.surveyor.osm.way.WayModel;

/**
 * `mvn test -Dtest=osm.surveyor.upload.Test_haya4`
 * @author hayashi
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_haya4 extends OsmUploadTest {
	
	/**
	 * "haya4_bldg_6697.checked.osm"
	 * 6-7 remove
	 * 6-6 remove
	 * 11-1-2 remove
	 * 7-5 remove (relation)
	 */
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
	 * 神奈川県 綾瀬市
	 * org:WAY:289757586 = "NODE:entrance=yes"を持つウェイ
	 * org:WAY:241755306 = 林宅
	 */
	@Test
	public void test_4th() {
		BodyMap map = testdo("./checked.osm");
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
	        for (WayModel way : ways) {
	        	for (NdBean nd : way.getNdList()) {
	        		assertNotNull(release.getNode(nd.getRef()));
	        	}
	        	
	        	// `fixme`タグは除去されていること
				assertNull(way.getTag("MLIT_PLATEAU:fixme"));

	        	if (way.getId() == 289757586l) {
					// 6-5 タグありNODEを持つ建物(6-5)は存在しないこと
	        		assertTrue(false);
				}
	        	else if (way.getId() == 241755306l) {
					// 7-7 リレーションメンバーの建物は存在しないこと
	        		assertTrue(false);
				}
	        	else if (way.getId() == 289897904) {
					// 11-20 アパート「宮久保 93」
					assertEquals("modify", way.getAction());
	        		assertEquals("宮久保 93", way.getTag("name").v);
	        		assertEquals("apartments", way.getTag("building").getValue());
	        		assertNull(way.getTag("ref:MLIT_PLATEAU"));		// #117
	        		assertNotNull(way.getTag("source"));		// #117
	        		assertNotNull(way.getTag("addr:housenumber"));
	        		assertEquals("11-20", way.getTag("addr:housenumber").getValue());
	        		checkCnt++;
				}
				else if (way.getId() == 289757595) {
					// 6-7 delete ６−６に吸収される　→ 除去された
	        		assertTrue(false);
				}
				else if (way.getId() == 289757584) {
					// 6-6 modify　重複なし→変更なし
	        		assertTrue(false);
				}
				else if (way.getId() == 289757601) {
					// 6-8 delete  6-9に吸収される
					assertEquals("delete", way.getAction());
	        		checkCnt++;
				}
				else if (way.getId() == 289757565) {
					// 6-9 modify
					assertEquals("modify", way.getAction());
	        		assertEquals("house", way.getTag("building").getValue());
	        		assertNotNull(way.getTag("addr:housenumber"));
	        		assertEquals("6-9", way.getTag("addr:housenumber").getValue());
	        		// Issue #117
	        		TagBean source = way.getTag("source");
	        		assertNotNull(source);
	        		assertEquals("MLIT_PLATEAU 11111-bldg-185842", source.getValue());
	        		checkCnt++;
				}
				else if (way.getId() == 289897936) {
					// 11-1-2 modify PLATEAUで形状変更される -> 除去された
	        		assertTrue(false);
				}
				else if (way.getId() == 289757568) {
					// 7-6 modify PLATEAUでリレーションメンバーに変更される
					assertEquals("modify", way.getAction());
					assertNull(way.getTag("building"));
					assertNotNull(way.getTag("building:part"));
					TagBean part = way.getTag("building:part");
					assertNotNull(part);
	        		assertEquals("house", part.getValue());
	        		assertNotNull(way.getTag("addr:housenumber"));
	        		assertEquals("7-6", way.getTag("addr:housenumber").getValue());
	        		checkCnt++;
				}
				else if (way.getId() == 289757576) {
					// 7-5 modify PLATEAUでリレーションメンバー"outer"に変更される -> 除去された
	        		assertTrue(false);
				}
				else {
					TagBean refTag = way.getTag("source");
					if (refTag != null) {
						String ref = refTag.getValue();
						assertNotNull(ref);
						if (ref.equals("MLIT_PLATEAU 11111-bldg-101977")) {
							// 既存建物と重複しない
			        		checkCnt++;
						}
					}
				}
	        }
	        assertEquals(5, checkCnt);
	        
	        List<RelationBean> relations = release.getRelationList();
	        assertNotNull(relations);
	        assertEquals(2, release.getRelationList().size());
	        
	        checkCnt = 0;
	        for (RelationBean relation : relations) {
	        	if (relation.isMultipolygon()) {
	        		// 7-6 Relation:multipolygon
	        		assertTrue(relation.isMultipolygon());
	        		assertEquals(2, relation.getMemberList().size());
		        	for (MemberBean member : relation.getMemberList()) {
		        		assertTrue(member.isWay());
		        		if (member.getRole().equals("outer")) {
		        			WayModel way = release.getWay(member.getRef());
		        			assertNotNull(way);
		        			assertNull(way.getTag("building:part"));
		        		}
		        		if (member.getRole().equals("inner")) {
		        			assertTrue(member.getRef() < 0);
		        		}
		        	}
	        		checkCnt++;
	        	}
	        	else if (relation.isBuilding()) {
	        		// 7-6 Relation:building
	        		assertTrue(relation.isBuilding());
	        		assertEquals(3, relation.getMemberList().size());
		        	for (MemberBean member : relation.getMemberList()) {
		        		if (member.getRole().equals("outline")) {
		        			assertTrue(member.getRef() < 0);
		        		}
		        		if (member.getRole().equals("part")) {
			        		assertTrue(member.isWay());
			        		WayModel way = release.getWay(member.getRef());
		        			assertNotNull(way);
		        			assertNotNull(way.getTag("building:part"));
		        			assertNull(way.getTag("building"));
		        		}
		        	}
	        		checkCnt++;
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
	        
	        assertEquals(8, ways.size());
	        assertEquals(2, checkCnt);
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
