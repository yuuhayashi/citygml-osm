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

import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.NdBean;
import osm.surveyor.osm.NodeBean;
import osm.surveyor.osm.OsmBean;
import osm.surveyor.osm.BodyMap;
import osm.surveyor.osm.BoundsBean;
import osm.surveyor.osm.RelationBean;
import osm.surveyor.osm.WayBean;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_haya4_7_5 extends OsmUpdaterTest {

	/*
		@startuml
		storage 既存データ {
		 map "Area: 7-5" as 289757576 {
		  building => house
		  building:levels => 2
		  addr:housenumber => 7-5
		  amenity => cafe
		 }
		}
		
		storage 変換結果 {
		 map "Relation: multipolygon" as outline {
		  type => multipolygon
		  building => house
		  building:levels = 2
		  addr:housenumber = 7-5
		  amenity = cafe
		 }
		 note left of outline
		 end note
		
		 map "Area: outer" as outer {
		  ref:MLIT_PLATEAU => 11111-bldg-101981
		  MLIT_PLATEAU:fixme => PLATEAUデータで更新されています
		 }
		
		 map "Area: inner" as inner1 {
		  ref:MLIT_PLATEAU => 11111-bldg-101982
		 }
		
		 map "Area: inner" as inner2 {
		  ref:MLIT_PLATEAU => 11111-bldg-101982
		 }
		
		 outline o-- outer : role=outer
		 outline o-- inner1 : role=inner
		 outline o-- inner2 : role=inner
		}
		
		289757576 ..> outer : 更新される
		
		@enduml
	 */
	@Before
	public void setup() {
		try {
			Files.copy(
				Paths.get("./src/test/resources/haya4_bldg_7-5.osm"),
				Paths.get("./haya4_bldg_7-5.osm"), REPLACE_EXISTING
			);
		}
		catch (FileAlreadyExistsException ee) {}
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		try {
			Files.copy(
				Paths.get("./src/test/resources/haya4_bldg_7-5.org.osm"),
				Paths.get("./haya4_bldg_7-5.org.osm"), REPLACE_EXISTING
			);
		}
		catch (FileAlreadyExistsException ee) {}
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testSample_b() {
		BodyMap map = testdo(Paths.get("./haya4_bldg_7-5.osm"));
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
	        }
	        
	        List<RelationBean> relations = mrg.getRelationList();
	        assertNotNull(relations);
			assertEquals(1, relations.size());
	        for (RelationBean relation : relations) {
				assertNotNull(relation);
				assertEquals("multipolygon", relation.getTagValue("type"));
				assertEquals("house", relation.getTagValue("building"));
				assertEquals("2", relation.getTagValue("building:levels"));
				assertEquals("7-5", relation.getTagValue("addr:housenumber"));
				assertEquals("cafe", relation.getTagValue("amenity"));
				assertNull(relation.getTagValue("ref:MLIT_PLATEAU"));
				assertNull(relation.getTagValue("MLIT_PLATEAU:fixme"));

				int outerCnt = 0;
				int innerCnt = 0;
				for (MemberBean mem : relation.getMemberList()) {
					if (mem.getRole().equals("outer")) {
						outerCnt++;
						assertEquals("way", mem.getType());
						WayBean way = mrg.getWay(mem.getRef());
						assertNotNull(way);
					}
					if (mem.getRole().equals("inner")) {
						innerCnt++;
						assertEquals(mem.getType(), ("way"));
						WayBean way = mrg.getWay(mem.getRef());
						assertNotNull(way);
					}
				}
				assertEquals(1, outerCnt);
				assertEquals(2, innerCnt);
				assertEquals(3, relation.getMemberList().size());
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
