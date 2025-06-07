package osm.surveyor.osm.camel;

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

/**
 * `mvn test -Dtest=osm.surveyor.update.Test_haya4`
 * 
 * @author hayashi
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_haya4 extends OsmUpdaterTest {
	static final String SOURCE = "haya4_bldg_6697_op2";
	
	@Before
	public void setup() {
		try {
			Files.copy(
				Paths.get("./src/test/resources/haya4_bldg_6697_op2.osm"),
				Paths.get("./haya4_bldg_6697_op2.osm"), REPLACE_EXISTING
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
				Paths.get("./haya4_bldg_6697_op2.org.osm"), REPLACE_EXISTING
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
	 * org:WAY:289757586 = 6-5 "NODE:entrance=yes"を持つウェイ
	 * org:WAY:241755306 = 7-7 林宅
	 */
	@Test
	public void test_3rd() {
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
	        
	        int checkCnt = 0;
	        List<WayBean> ways = mrg.getWayList();
	        assertNotNull(ways);
	        for (WayBean way : ways) {
	        	for (NdBean nd : way.getNdList()) {
	        		assertNotNull(mrg.getNode(nd.getRef()));
	        	}

	        	if (way.getId() == 289757586l) {
					// 6-5 タグありNODEを持つ建物(6-5)は存在しないこと
	        		assertTrue(false);
				}
	        	else if (way.getId() == 241755306l) {
					// 7-7 リレーションメンバーの建物は存在しないこと
	        		assertTrue(false);
				}
	        	else if (way.getId() == 289757569) {
					// 5-6 Issue #60 "end_date"
	        		assertTrue(false);
				}
	        	else if (way.getId() == 289757602) {
					// 5-5 Issue #60 "demolished:building"
	        		assertTrue(false);
				}
				else if (way.getId() == 289897904) {
					// 11-20 アパート「宮久保 93」
					assertEquals("modify", way.getAction());
					assertEquals("PLATEAUデータで更新されています", way.getTag("MLIT_PLATEAU:fixme").getValue());
	        		assertEquals("宮久保 93", way.getTag("name").getValue());
	        		assertEquals("apartments", way.getTag("building").getValue());
	        		assertNotNull(way.getTag("ref:MLIT_PLATEAU"));
	        		assertEquals("11111-bldg-185615", way.getTag("ref:MLIT_PLATEAU").getValue());
	        		assertNotNull(way.getTag("addr:housenumber"));
	        		assertEquals("11-20", way.getTag("addr:housenumber").getValue());
	        		assertEquals("-9998.9", way.getTag("ele").getValue());		// Issue#128 height=-9999
	        		assertEquals("9998.9", way.getTag("height").getValue());		// Issue#128 height=-9999
	        		assertEquals("9998", way.getTag("building:levels").getValue());		// Issue#128 building:levels=9999
	        		assertEquals("-9998", way.getTag("building:levels:underground").getValue());		// Issue#128 building:levels:underground=9999
	        		checkCnt++;
				}
				else if (way.getId() == 289897935) {
					// 11-9 アパート「ハイツ宮久保」
					assertEquals("modify", way.getAction());
					assertEquals("PLATEAUデータで更新されています", way.getTag("MLIT_PLATEAU:fixme").getValue());
	        		assertEquals("ハイツ宮久保", way.getTag("name").getValue());
	        		assertEquals("apartments", way.getTag("building").getValue());
	        		assertNotNull(way.getTag("ref:MLIT_PLATEAU"));
	        		assertEquals("11111-bldg-99999", way.getTag("ref:MLIT_PLATEAU").getValue());
	        		assertNotNull(way.getTag("addr:housenumber"));
	        		assertEquals("11-9", way.getTag("addr:housenumber").getValue());
	        		assertNull(way.getTag("ele"));		// Issue#128 height=-9999.0
	        		assertNull(way.getTag("height"));		// Issue#128 height=9999.0
	        		assertNull(way.getTag("building:levels"));		// Issue#128 building:levels=9999
	        		assertNull(way.getTag("building:levels:underground"));		// Issue#128 building:levels:underground=9999
	        		checkCnt++;
				}
	        	else if (way.getId() == 289757583) {
					// 5-4 `source=`
					assertEquals("modify", way.getAction());
	        		assertEquals("house", way.getTag("building").getValue());
	        		assertNotNull(way.getTag("ref:MLIT_PLATEAU"));
	        		assertEquals("5555-bldg-444444", way.getTag("ref:MLIT_PLATEAU").getValue());
	        		assertNotNull(way.getTag("addr:housenumber"));
	        		assertEquals("5-4", way.getTag("addr:housenumber").getValue());
	        		assertNull(way.getTag("source"));
	        		assertNotNull(way.getTag("ele"));
	        		assertEquals("7.5", way.getTag("ele").getValue());
	        		assertNotNull(way.getTag("building:levels"));
	        		assertEquals("7", way.getTag("building:levels").getValue());
	        		assertNotNull(way.getTag("building:levels:underground"));
	        		assertEquals("2", way.getTag("building:levels:underground").getValue());
	        		assertNotNull(way.getTag("start_date"));
	        		assertEquals("2021-03", way.getTag("start_date").getValue());
	        		checkCnt++;
				}
	        	else if (way.getId() == 289757585) {
					// 5-3 `source=`
					assertEquals("modify", way.getAction());
	        		assertEquals("house", way.getTag("building").getValue());
	        		assertNotNull(way.getTag("ref:MLIT_PLATEAU"));
	        		assertEquals("5555-bldg-333333", way.getTag("ref:MLIT_PLATEAU").getValue());
	        		assertNotNull(way.getTag("addr:housenumber"));
	        		assertEquals("5-3", way.getTag("addr:housenumber").getValue());
	        		assertNotNull(way.getTag("source"));
	        		assertEquals("survey", way.getTag("source").getValue());
	        		assertNotNull(way.getTag("ele"));
	        		assertEquals("8.9", way.getTag("ele").getValue());
	        		assertNotNull(way.getTag("building:levels"));
	        		assertEquals("8", way.getTag("building:levels").getValue());
	        		assertNotNull(way.getTag("building:levels:underground"));
	        		assertEquals("1", way.getTag("building:levels:underground").getValue());
	        		assertNotNull(way.getTag("start_date"));
	        		assertEquals("2019", way.getTag("start_date").getValue());
	        		checkCnt++;
				}
				else if (way.getId() == 289757595) {
					// 6-7 modify
					assertEquals("modify", way.getAction());
					assertEquals("PLATEAUデータで更新されています", way.getTag("MLIT_PLATEAU:fixme").getValue());
	        		assertEquals("house", way.getTag("building").getValue());
	        		assertNotNull(way.getTag("addr:housenumber"));
	        		assertEquals("6-7", way.getTag("addr:housenumber").getValue());
	        		assertNotNull(way.getTag("ele"));
	        		assertEquals("8.9", way.getTag("ele").getValue());
	        		assertNotNull(way.getTag("building:levels"));
	        		assertEquals("8", way.getTag("building:levels").getValue());
	        		assertNotNull(way.getTag("building:levels:underground"));
	        		assertEquals("1", way.getTag("building:levels:underground").getValue());
	        		assertNotNull(way.getTag("start_date"));
	        		assertEquals("2021-03", way.getTag("start_date").getValue());
	        		assertNotNull(way.getTag("name"));
	        		assertEquals("建て替え", way.getTag("name").getValue());
	        		checkCnt++;
				}
				else if (way.getId() == 289757601) {
					// 6-8 delete  6-9に吸収される
					assertEquals("modify", way.getAction());
					assertEquals("delete 削除されます", way.getTag("MLIT_PLATEAU:fixme").getValue());
	        		assertEquals("house", way.getTag("building").getValue());
	        		assertNotNull(way.getTag("addr:housenumber"));
	        		assertEquals("6-8", way.getTag("addr:housenumber").getValue());
	        		checkCnt++;
				}
				else if (way.getId() == 289757565) {
					// 6-9 modify
					assertEquals("modify", way.getAction());
					assertEquals("PLATEAUデータで更新されています", way.getTag("MLIT_PLATEAU:fixme").getValue());
	        		assertEquals("house", way.getTag("building").getValue());
	        		assertNotNull(way.getTag("addr:housenumber"));
	        		assertEquals("6-9", way.getTag("addr:housenumber").getValue());
	        		checkCnt++;
				}
				else if (way.getId() == 289897936) {
					// 11-1-2 modify PLATEAUで形状変更される
					assertEquals("modify", way.getAction());
					assertEquals("PLATEAUデータで更新されています", way.getTag("MLIT_PLATEAU:fixme").getValue());
	        		assertEquals("house", way.getTag("building").getValue());
	        		assertNotNull(way.getTag("addr:housenumber"));
	        		assertEquals("11-1-2", way.getTag("addr:housenumber").getValue());
	        		checkCnt++;
				}
				else if (way.getId() == 289757568) {
					// 7-6 modify PLATEAUでリレーションメンバーに変更される
	        		assertEquals("11111-bldg-101979", way.getTag("ref:MLIT_PLATEAU").getValue());
					assertEquals("modify", way.getAction());
					assertEquals("PLATEAUデータで更新されています", way.getTag("MLIT_PLATEAU:fixme").getValue());
	        		assertEquals("15", way.getTag("height").getValue());
	        		assertEquals("house", way.getTag("building:part").getValue());
	        		assertEquals("7-6", way.getTag("addr:housenumber").getValue());
	        		assertEquals("2", way.getTag("building:levels").getValue());	// .org
	        		assertNull(way.getTag("building:levels:underground"));			// .osm
	        		assertNull(way.getTag("building"));
	        		checkCnt++;
				}
				else if (way.getId() == 289757576) {
					// 7-5 modify PLATEAUでリレーションメンバー"outer"に変更される
					assertEquals("modify", way.getAction());
					assertEquals("PLATEAUデータで更新されています", way.getTag("MLIT_PLATEAU:fixme").getValue());
					assertNull(way.getTag("building"));	// "outer"だから"building"は無い
	        		assertNull(way.getTag("building:levels"));
	        		assertNull(way.getTag("addr:housenumber"));
	        		checkCnt++;
				}
				else {
					TagBean refTag = way.getTag("ref:MLIT_PLATEAU");
					if (refTag != null) {
						String ref = refTag.getValue();
						assertNotNull(ref);
						if (ref.equals("11111-bldg-101977")) {
							// 既存建物と重複しない
			        		checkCnt++;
						}
						else if (ref.equals("11111-bldg-101982")) {
							// 7-5 に重複するリレーションの"inner"
			        		checkCnt++;		// ２つ存在する
						}
					}
				}
	        }
	        assertEquals(13, checkCnt);
	        
	        List<RelationBean> relations = mrg.getRelationList();
	        assertNotNull(relations);
	        checkCnt = 0;
	        for (RelationBean relation : relations) {
	        	if (relation.getId() == -178513) {
	        		checkCnt++;
	        		// 7-6 Relation:building
	        		assertTrue(relation.isBuilding());
	        		assertNull(relation.getTag("building:part"));
	        		assertNotNull(relation.getTag("building"));
	        		assertEquals("yes", relation.getTag("building").getValue());
					assertNotNull(relation.getTag("addr:housenumber"));
	        		assertEquals("7-6", relation.getTag("addr:housenumber").getValue());
	        		assertNull(relation.getTag("building:levels"));
	        		assertNotNull(relation.getTag("amenity"));
	        		assertEquals("veterinary", relation.getTag("amenity").getValue());
	        		
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
	        	else if (relation.getId() == -178512) {
	        		checkCnt++;
	        		// 7-6 Relation:multipolygon
	        		assertTrue(relation.isMultipolygon());
	        		assertNotNull(relation.getTag("building"));	// fix #119
					assertEquals(2, relation.getMemberList().size());
		        	for (MemberBean member : relation.getMemberList()) {
		        		assertTrue(member.isWay());
		        		if (member.getRole().equals("outer")) {
		        			WayBean way = mrg.getWay(member.getRef());
		        			assertNotNull(way);
		        			assertNull(way.getTag("building:part"));
		        		}
		        		if (member.getRole().equals("inner")) {
		        			assertTrue(member.getRef() < 0);
		        		}
		        	}
	        	}
	        	else if (relation.getId() == -178514) {
	        		checkCnt++;
	        		// 7-5 Relation:multipolygon
	        		assertTrue(relation.isMultipolygon());
	        		assertEquals(3, relation.getMemberList().size());
        			assertNull(relation.getTag("building:part"));
					assertNotNull(relation.getTag("building"));
	        		assertEquals("yes", relation.getTag("building").getValue());
					assertNotNull(relation.getTag("addr:housenumber"));
	        		assertEquals("7-5", relation.getTag("addr:housenumber").getValue());
	        		assertNotNull(relation.getTag("building:levels"));
	        		assertEquals("2", relation.getTag("building:levels").getValue());
	        		assertNotNull(relation.getTag("amenity"));
	        		assertEquals("cafe", relation.getTag("amenity").getValue());
	        		
		        	for (MemberBean member : relation.getMemberList()) {
		        		assertTrue(member.isWay());
		        		if (member.getRole().equals("outer")) {
							// 7-5 modify PLATEAUでリレーションメンバー"outer"に変更される
		        			assertEquals(289757576, member.getRef());
		        		}
		        		if (member.getRole().equals("inner")) {
		        			assertTrue(member.getRef() < 0);
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
	        
	        assertEquals(25, ways.size());
	        assertEquals(3, mrg.getRelationList().size());
	        assertEquals(3, checkCnt);
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
