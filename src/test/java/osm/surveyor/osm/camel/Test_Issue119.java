package osm.surveyor.osm.camel;

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
import osm.surveyor.osm.TagBean;
import osm.surveyor.osm.WayBean;
import osm.surveyor.osm.way.WayModel;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_Issue119 extends OsmUpdaterTest {
	
	@Before
	public void setup() {
		try {
			Files.copy(
				Paths.get("./src/test/resources/Issue119.osm"),
				Paths.get("./Issue119.osm"), REPLACE_EXISTING
			);
			Files.copy(
				Paths.get("./src/test/resources/Issue119.org.osm"),
				Paths.get("./Issue119.org.osm"), REPLACE_EXISTING
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
		BodyMap map = testdo(Paths.get("./Issue119.osm"));
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
	        assertEquals(340, relations.size());
	        for (RelationBean relation : relations) {
	        	TagBean buildingTag = relation.getTag("building");
	        	for (MemberBean member : relation.getMemberList()) {
	        		if (member.isWay()) {
	        			WayModel way = mrg.getWay(member.getRef());
		        		assertNotNull(way);
	        			if (member.getRole().equals("outline")) {
	                		assertEquals(buildingTag.getValue(), way.getTagValue("building"));
	        			}
	        		}
	        		else if (member.isRelation()) {
	        			RelationBean polygon = mrg.getRelation(member.getRef());
		        		assertNotNull(polygon);
	        			if (member.getRole().equals("outline")) {
	                		assertEquals(buildingTag.getValue(), polygon.getTagValue("building"));
	        			}
	        		}
	        	}
	        }
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
