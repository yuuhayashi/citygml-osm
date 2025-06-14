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
import osm.surveyor.osm.way.WayModel;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_C extends OsmUpdaterTest {

	/*
		map "building : Relation" as building {
		 type => building
		 building => yes
		 height => 40.492
		 source => MLIT_PLATEAU;\n http://www.opengis.net/def/crs/EPSG/0/6697
		}
	 */
	@Before
	public void setup() {
		try {
			Files.copy(
				Paths.get("./src/test/resources/sample_c_bldg_6697_op2.osm"),
				Paths.get("./sample_c_bldg_6697_op2.osm"), REPLACE_EXISTING
			);
		}
		catch (FileAlreadyExistsException ee) {}
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		try {
			Files.copy(
				Paths.get("./src/test/resources/sample_c_bldg_6697_op2.org.osm"),
				Paths.get("./sample_c_bldg_6697_op2.org.osm"), REPLACE_EXISTING
			);
		}
		catch (FileAlreadyExistsException ee) {}
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			Files.copy(
				Paths.get("./src/test/resources/sample_cc_bldg_6697_op2.osm"),
				Paths.get("./sample_cc_bldg_6697_op2.osm"), REPLACE_EXISTING
			);
		}
		catch (FileAlreadyExistsException ee) {}
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		try {
			Files.copy(
				Paths.get("./src/test/resources/sample_cc_bldg_6697_op2.org.osm"),
				Paths.get("./sample_cc_bldg_6697_op2.org.osm"), REPLACE_EXISTING
			);
		}
		catch (FileAlreadyExistsException ee) {}
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testSample_c() {
		BodyMap map = testdo(Paths.get("./sample_c_bldg_6697_op2.osm"));
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
	        for (WayModel way : ways) {
	        	for (NdBean nd : way.getNdList()) {
	        		assertNotNull(mrg.getNode(nd.getRef()));
	        	}
	        }
	        
	        List<RelationBean> relations = mrg.getRelationList();
	        assertNotNull(relations);
			assertEquals(1, relations.size());
	        for (RelationBean relation : relations) {
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					assertEquals("building", relation.getTagValue("type"));
					assertEquals("yes", relation.getTagValue("building"));
					assertEquals("2", relation.getTagValue("building:levels"));
					assertEquals("1", relation.getTagValue("building:levels:underground"));
					assertEquals("42.7", relation.getTagValue("height"));
					assertEquals("2.81", relation.getTagValue("ele"));
					assertEquals("東京都大田区南六郷三丁目", relation.getTagValue("addr:full"));
					assertNull(relation.getTagValue("ref:MLIT_PLATEAU"));

					int outlineCnt = 0;
					int partCnt = 0;
					for (MemberBean mem : relation.getMemberList()) {
						if (mem.getRole().equals("outline")) {
							outlineCnt++;
							assertEquals("way", mem.getType());
							WayModel way = mrg.getWay(mem.getRef());
							assertNotNull(way);
							assertNull(way.getTagValue("ref:MLIT_PLATEAU"));
							assertNull(way.getTagValue("addr:full"));
							assertEquals("42.7", way.getTagValue("height"));
							assertEquals("2.81", way.getTagValue("ele"));
							assertEquals(way.getTagValue("building"), ("yes"));
							assertEquals(way.getTagValue("building:levels"), ("2"));
							assertEquals("1", way.getTagValue("building:levels:underground"));
						}
						if (mem.getRole().equals("part")) {
							partCnt++;
							assertEquals(mem.getType(), ("way"));
							WayModel way = mrg.getWay(mem.getRef());
							assertNotNull(way);
							if (way.getTagValue("ref:MLIT_PLATEAU") != null) {
								assertNotNull(way.getTagValue("ref:MLIT_PLATEAU"));
								assertEquals("東京都大田区南六郷三丁目", way.getTagValue("addr:full"));
								assertNotNull(way.getTagValue("height"));
								assertNotNull(way.getTagValue("ele"));
								assertNotNull(way.getTagValue("start_date"));
								assertEquals("yes", way.getTagValue("building:part"));
							}
						}
					}
					assertEquals(1, outlineCnt);
					assertEquals(2, partCnt);
					assertEquals(3, relation.getMemberList().size());
				}
				else {
					assertEquals("building", type);
				}
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testSample_cc() {
		BodyMap map = testdo(Paths.get("./sample_cc_bldg_6697_op2.osm"));
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
	        for (WayModel way : ways) {
	        	for (NdBean nd : way.getNdList()) {
	        		assertNotNull(mrg.getNode(nd.getRef()));
	        	}
	        }
	        
	        List<RelationBean> relations = mrg.getRelationList();
	        assertNotNull(relations);
	        for (RelationBean relation : relations) {
				if (relation.isBuilding()) {
					assertEquals("building", relation.getTagValue("type"));
					assertEquals("yes", relation.getTagValue("building"));
					assertEquals("3", relation.getTagValue("building:levels"));
					assertEquals("2", relation.getTagValue("building:levels:underground"));
					assertEquals("7.1", relation.getTagValue("height"));
					assertEquals("2.48", relation.getTagValue("ele"));
					assertEquals("東京都大田区大森中一丁目", relation.getTagValue("addr:full"));
					assertNull(relation.getTagValue("ref:MLIT_PLATEAU"));
					assertEquals(7, relation.getTagList().size());
					
					int outlineCnt = 0;
					int partCnt = 0;
					for (MemberBean mem : relation.getMemberList()) {
						if (mem.getRole().equals("outline")) {
							outlineCnt++;
							assertEquals(mem.getType(), ("way"));
							WayModel way = mrg.getWay(mem.getRef());
							assertNotNull(way);
							assertNull(way.getTagValue("ref:MLIT_PLATEAU"));
							assertNull(way.getTagValue("addr:full"));
							assertEquals(way.getTagValue("height"), ("7.1"));
							assertEquals("2.48", way.getTagValue("ele"));
							assertEquals(way.getTagValue("building"), ("yes"));
							assertEquals(way.getTagValue("building:levels"), ("3"));
							assertEquals(way.getTagValue("building:levels:underground"), ("2"));
						}
						if (mem.getRole().equals("part")) {
							partCnt++;
							assertEquals(mem.getType(), ("way"));
							WayModel way = mrg.getWay(mem.getRef());
							assertNotNull(way);
							assertNotNull(way.getTagValue("ref:MLIT_PLATEAU"));
							assertEquals(way.getTagValue("addr:full"), ("東京都大田区大森中一丁目"));
							assertNotNull(way.getTagValue("height"));
							assertNotNull(way.getTagValue("ele"));
							assertEquals(way.getTagValue("building:part"), ("yes"));
							assertTrue(way.getTagList().size() >= 5);
						}
					}
					assertEquals(1, outlineCnt);
					assertEquals(3, partCnt);
					assertEquals(4, relation.getMemberList().size());
				}
			}
			assertEquals(1, relations.size());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
