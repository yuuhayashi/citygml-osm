package osm.surveyor.update;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import osm.surveyor.gis.point.NdModel;
import osm.surveyor.osm.BodyMap;
import osm.surveyor.osm.BoundsBean;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.NodeBean;
import osm.surveyor.osm.OsmBean;
import osm.surveyor.osm.RelationBean;
import osm.surveyor.osm.WayBean;
import osm.surveyor.osm.way.WayModel;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_D extends OsmUpdaterTest {
	
	/*
 	map "13111-bldg-71799 : building" as building {
	 bldg:measuredHeight => 30.2
	 bldg:yearOfConstruction => 1977
	 bldg:storeysAboveGround => 3
	 gml:posList => 6.038
	 xAL:LocalityName => 東京都大田区大森西三丁目
	}
 	map "13111-bldg-72601 : building" as building {
	 bldg:measuredHeight => 34.7
	 bldg:yearOfConstruction => 1976
	 bldg:storeysAboveGround => 2
	 bldg:storeysBelowGround => 1
	 bldg:lod1Solid => 2.682
	 xAL:LocalityName => 東京都大田区大森西三丁目
	}
	*/
	@Before
	public void setup() {
		try {
			Files.copy(
				Paths.get("./src/test/resources/sample_d_bldg_6697_op2.osm"),
				Paths.get("./sample_d_bldg_6697_op2.osm"), REPLACE_EXISTING
			);
		}
		catch (FileAlreadyExistsException ee) {}
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		try {
			Files.copy(
				Paths.get("./src/test/resources/sample_d_bldg_6697_op2.org.osm"),
				Paths.get("./sample_d_bldg_6697_op2.org.osm"), REPLACE_EXISTING
			);
		}
		catch (FileAlreadyExistsException ee) {}
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
	}

	/**
	 * `mvn test -Dtest=Test_53392547#test`
	 * 東京都大田区 多摩川左岸 モデル地域
	 * Issue #55
	 * Issue #40 [バリデーション警告「重なった建物」が発生する]
	 */
	@Test
	public void test() {
		BodyMap map = testdo(Paths.get("./sample_d_bldg_6697_op2.osm"));
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
	        
	        List<WayBean> ways = mrg.getWays();
	        assertNotNull(ways);
	        for (WayModel way : ways) {
	        	for (NdModel nd : way.getNdList()) {
	        		assertNotNull(mrg.getNode(nd.getRef()));
	        	}
	        }
	        
	        List<RelationBean> relations = mrg.getRelationList();
	        assertNotNull(relations);
			assertEquals(2, relations.size());
	        for (RelationBean relation : relations) {
				if (relation.isBuilding()) {
					/*
					 	map "building : Relation" as building {
						 type => building
						 building => yes
						 height => 34.7
						}
					 */
					assertEquals("building", relation.getTagValue("type"));
					assertEquals("都営大森西三丁目第2アパート", relation.getTagValue("name"));
					assertEquals("34.7", relation.getTagValue("height"));
					assertEquals("2.68", relation.getTagValue("ele"));
					assertEquals("yes", relation.getTagValue("building"));		// Issue #119 建物リレーション(RELATION:type=building) の outline メンバーは、「building=yes」でなければならない
					assertEquals("3", relation.getTagValue("building:levels"));
					assertEquals("1", relation.getTagValue("building:levels:underground"));
					assertNull(relation.getTagValue("addr:full"));		// Issue #93 
					assertNull(relation.getTagValue("source"));
					assertNull(relation.getTagValue("ref:MLIT_PLATEAU"));
					assertNull(relation.getTagValue("start_date"));		// Issue #39
					assertEquals(7, relation.getTagList().size());

					int outlineCnt = 0;
					int partCnt = 0;
					assertEquals(3, relation.getMemberList().size());
					for (MemberBean mem : relation.getMemberList()) {
						if (mem.getRole().equals("outline")) {
							outlineCnt++;
							assertEquals("way", mem.getType());	// Issue #40 Sample_Dで、バリデーション警告「重なった建物」が発生する
						}
						if (mem.getRole().equals("part")) {
							partCnt++;
							if (mem.getType().equals("way")) {
								WayModel way = mrg.getWay(mem.getRef());
								assertNotNull(way);
								if (way.getTagValue("ref:MLIT_PLATEAU").endsWith("13111-bldg-72601")) {
									assertEquals("13111-bldg-72601", way.getTagValue("ref:MLIT_PLATEAU"));
									assertEquals("PLATEAUデータで更新されています", way.getTagValue("MLIT_PLATEAU:fixme"));
									assertEquals("34.7", way.getTagValue("height"));
									assertEquals("2.68", way.getTagValue("ele"));
									assertEquals("1976", way.getTagValue("start_date"));
									assertEquals("apartments", way.getTagValue("building:part"));
									assertEquals("2", way.getTagValue("building:levels"));
									assertEquals("1", way.getTagValue("building:levels:underground"));
									assertEquals("都営大森西三丁目第2アパート", way.getTagValue("name"));		// Issue #55
									assertNull(way.getTagValue("addr:full"));		// Issue #93
									assertNull(way.getTag("source"));
									assertEquals(9, way.getTagList().size());
								}
								else if (way.getTagValue("ref:MLIT_PLATEAU").endsWith("13111-bldg-71799")) {
									assertEquals("13111-bldg-71799", way.getTagValue("ref:MLIT_PLATEAU"));
									assertEquals("PLATEAUデータで更新されています", way.getTagValue("MLIT_PLATEAU:fixme"));
									assertEquals("3", way.getTagValue("building:levels"));
									assertEquals("30.2", way.getTagValue("height"));
									assertEquals("3.13", way.getTagValue("ele"));
									assertEquals("1977", way.getTagValue("start_date"));
									assertEquals("yes", way.getTagValue("building:part"));
									assertNull(way.getTagValue("addr:full"));		// Issue #93
									assertNull(way.getTag("source"));
									assertEquals(7, way.getTagList().size());
								}
							}
							else if (mem.getType().equals("relation")) {
								RelationBean multiporygon = mrg.getRelation(mem.getRef());
								assertNotNull(multiporygon);
								assertTrue(multiporygon.isMultipolygon());
								assertNotNull(multiporygon.getTag("building"));	// Issue #40 [バリデーション警告「重なった建物」が発生する]
								
								/*
									map "multipolygon :Relation" as multipolygon {
									 type => multipolygon
									 building:part => yes		// PLATEAUの値
									 height => 34.7
									}
								*/
								assertEquals("multipolygon", multiporygon.getTagValue("type"));
								assertEquals("yes", multiporygon.getTagValue("building:part"));		// Issue #119 建物リレーション(RELATION:type=building) の outline メンバーは、「building=yes」でなければならない
								assertEquals("3", multiporygon.getTagValue("building:levels"));
								assertEquals("1", multiporygon.getTagValue("building:levels:underground"));
								assertEquals("34.7", multiporygon.getTagValue("height"));
								assertEquals("2.68", multiporygon.getTagValue("ele"));
								assertEquals("都営大森西三丁目第2アパート", multiporygon.getTagValue("name"));		// Issue #55
								assertNotNull(multiporygon.getTag("start_date"));			// Issue #39 複合ビルでの”建築年”の扱い
								assertNull(multiporygon.getTagValue("addr:full"));		// Issue #93 
								assertNull(multiporygon.getTagValue("ref:MLIT_PLATEAU"));
								assertNull(multiporygon.getTagValue("addr:ref"));
								assertEquals(8, multiporygon.getTagList().size());
								int outerCnt = 0;
								int innerCnt = 0;
								assertEquals(3, multiporygon.getMemberList().size());
								for (MemberBean member : multiporygon.getMemberList()) {
									if (member.getRole().equals("outer")) {
										outerCnt++;
										assertEquals("way", member.getType());
										WayModel way = mrg.getWay(member.getRef());
										assertNotNull(way);
										assertNull(way.getTagValue("ref:MLIT_PLATEAU"));
										assertEquals(0, way.getTagList().size());
									}
									if (member.getRole().equals("inner")) {
										innerCnt++;
										assertEquals("way", member.getType());
										WayModel way = mrg.getWay(member.getRef());
										assertNotNull(way);
										assertNotNull(way.getTagValue("ref:MLIT_PLATEAU"));
										assertEquals(1, way.getTagList().size());
									}
								}
								assertEquals(1, outerCnt);
								assertEquals(2, innerCnt);
							}
						}
					}
					assertEquals(1, outlineCnt);
					assertEquals(2, partCnt);
				}
	        }
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
