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
import osm.surveyor.osm.NodeBean;
import osm.surveyor.osm.OsmBean;
import osm.surveyor.gis.point.NdModel;
import osm.surveyor.osm.BodyMap;
import osm.surveyor.osm.BoundsBean;
import osm.surveyor.osm.RelationBean;
import osm.surveyor.osm.WayBean;
import osm.surveyor.osm.way.WayModel;
import osm.surveyor.osm.way.Wayable;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_B extends OsmUpdaterTest {

	/*

		@startuml
		storage 既存データ {
		 map "Area: A1" as A1 {
		  building => yes
		 }
		 map "Area: A2" as A2 {
		  building => yes
		 }
		 map "Area: A3" as A3 {
		  building => yes
		 }
		}
		
		storage 変換結果 {
		 map "Relation: multipolygon" as outline {
		  type => multipolygon
		  building => yes
		  ele => 2.51
		  height => 16.9
		  start_date => 1976
		  addr:full => 東京都大田区大森西五丁目
		 }
		
		 map "Area: outer" as outer {
		  ref:MLIT_PLATEAU => 13111-bldg-61384
		  MLIT_PLATEAU:fixme => PLATEAUデータで更新されています
		 }
		
		 map "Area: inner" as inner1 {
		  ref:MLIT_PLATEAU => 13111-bldg-61384
		 }
		
		 outline o-- outer : role=outer
		 outline o-- inner1 : role=inner
		
		 map "Area: A2" as A2 {
		  building => yes
		 }
		 map "Area: A3" as A3 {
		  building => yes
		 }
		
		 map "Area: A2" as A2b {
		  building => yes
		  MLIT_PLATEAU:fixme => delete
		 }
		 map "Area: A3" as A3b {
		  building => yes
		  MLIT_PLATEAU:fixme => delete
		 }
		}
		
		A1 ..> outer : 更新される
		A2 ..> A2b : DELETE
		A3 ..> A3b : DELETE
		
		@enduml
	 */
	@Before
	public void setup() {
		try {
			Files.copy(
				Paths.get("./src/test/resources/sample_b_bldg_6697_op2.osm"),
				Paths.get("./sample_b_bldg_6697_op2.osm"), REPLACE_EXISTING
			);
		}
		catch (FileAlreadyExistsException ee) {}
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		try {
			Files.copy(
				Paths.get("./src/test/resources/sample_b_bldg_6697_op2.org.osm"),
				Paths.get("./sample_b_bldg_6697_op2.org.osm"), REPLACE_EXISTING
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
		BodyMap map = testdo(Paths.get("./sample_b_bldg_6697_op2.osm"));
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
			assertEquals(1, relations.size());
	        for (RelationBean relation : relations) {
				assertNotNull(relation);
				assertEquals("multipolygon", relation.getTagValue("type"));
				assertEquals("yes", relation.getTagValue("building"));
				assertEquals("16.9", relation.getTagValue("height"));
				assertEquals("2.51", relation.getTagValue("ele"));
				assertEquals("1976", relation.getTagValue("start_date"));
				assertEquals("東京都大田区大森西五丁目", relation.getTagValue("addr:full"));

				int outerCnt = 0;
				int innerCnt = 0;
				for (MemberBean mem : relation.getMemberList()) {
					if (mem.getRole().equals("outer")) {
						outerCnt++;
						assertEquals("way", mem.getType());
						Wayable way = mrg.getWay(mem.getRef());
						assertNotNull(way);
					}
					if (mem.getRole().equals("inner")) {
						innerCnt++;
						assertEquals(mem.getType(), ("way"));
						Wayable way = mrg.getWay(mem.getRef());
						assertNotNull(way);
					}
				}
				assertEquals(1, outerCnt);
				assertEquals(1, innerCnt);
				assertEquals(2, relation.getMemberList().size());
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
