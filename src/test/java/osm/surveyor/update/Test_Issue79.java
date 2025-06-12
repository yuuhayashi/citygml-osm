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

import osm.surveyor.osm.BodyMap;
import osm.surveyor.osm.BoundsBean;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.NdBean;
import osm.surveyor.osm.NodeBean;
import osm.surveyor.osm.OsmBean;
import osm.surveyor.osm.RelationBean;
import osm.surveyor.osm.WayBean;
import osm.surveyor.osm.way.Wayable;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_Issue79 extends OsmUpdaterTest {
	
	@Before
	public void setup() {
		try {
			Files.copy(
				Paths.get("./src/test/resources/Issue_79.osm"),
				Paths.get("./Issue_79.osm"), REPLACE_EXISTING
			);
			Files.copy(
				Paths.get("./src/test/resources/Issue_79.org.osm"),
				Paths.get("./Issue_79.org.osm"), REPLACE_EXISTING
			);
		}
		catch (FileAlreadyExistsException ee) {}
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void test510741270() {
		BodyMap map = testdo(Paths.get("./Issue_79.osm"));
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
	        for (Wayable way : ways) {
	        	for (NdBean nd : way.getNdList()) {
	        		assertNotNull(mrg.getNode(nd.getRef()));
	        	}
	        }
	        
	        List<RelationBean> relations = mrg.getRelationList();
	        assertNotNull(relations);
	        assertEquals(1, relations.size());
	        for (RelationBean relation : relations) {
	        	int outlineCnt = 0;
	        	int partCnt = 0;
	        	for (MemberBean member : relation.getMemberList()) {
	        		if (member.isWay()) {
	        			Wayable way = mrg.getWay(member.getRef());
		        		assertNotNull(way);
	        			if (member.getRole().equals("outline")) {
	        				outlineCnt++;
	                		assertEquals("yes", way.getTagValue("building"));	// fix #119
	                		assertEquals("18.7", way.getTagValue("ele"));
	                		assertEquals("9.7", way.getTagValue("height"));
	                		assertEquals("adult_gaming_centre", way.getTagValue("leisure"));
	        			}
	        			else if (member.getRole().equals("part")) {
	        				partCnt++;
	        				if (way.getTagValue("ref:MLIT_PLATEAU").equals("10207-bldg-63561")) {
		                		assertEquals("yes", way.getTagValue("building:part"));
		                		assertEquals("19.4", way.getTagValue("ele"));
		                		assertEquals("4.9", way.getTagValue("height"));
	        				}
	        				else if (way.getTagValue("ref:MLIT_PLATEAU").equals("10207-bldg-45841")) {
		                		assertEquals("retail", way.getTagValue("building:part"));
		                		assertEquals("18.7", way.getTagValue("ele"));
		                		assertEquals("9.7", way.getTagValue("height"));
		                		assertEquals("adult_gaming_centre", way.getTagValue("leisure"));
		                		assertEquals("PLATEAUデータで更新されています", way.getTagValue("MLIT_PLATEAU:fixme"));
	        				}
	        				else {
	        					fail();
	        				}
	        			}
	        			else {
	        				fail();
	        			}
	        		}
	        		else {
	        			fail();
	        		}
	        	}
	        	assertEquals(1, outlineCnt);
	        	assertEquals(2, partCnt);
	        	
        		assertEquals("building", relation.getTagValue("type"));
        		assertEquals("yes", relation.getTagValue("building"));
        		assertEquals("18.7", relation.getTagValue("ele"));
        		assertEquals("9.7", relation.getTagValue("height"));
        		assertEquals("adult_gaming_centre", relation.getTagValue("leisure"));
	        }
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
