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
import osm.surveyor.osm.WayBean;
import osm.surveyor.osm.way.WayModel;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_Issue33 extends OsmUpdaterTest {
	
	@Before
	public void setup() {
		try {
			Files.copy(
				Paths.get("./src/test/resources/50303524_bldg_6697_op.osm"),
				Paths.get("./50303524_bldg_6697_op.osm"), REPLACE_EXISTING
			);
			Files.copy(
				Paths.get("./src/test/resources/50303524_bldg_6697_op.org.osm"),
				Paths.get("./50303524_bldg_6697_op.org.osm"), REPLACE_EXISTING
			);
		}
		catch (FileAlreadyExistsException ee) {}
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void test50303524() {
		BodyMap map = testdo(Paths.get("./50303524_bldg_6697_op.osm"));
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
	        	for (MemberBean member : relation.getMemberList()) {
	        		if (member.isWay()) {
		        		assertNotNull(mrg.getWay(member.getRef()));
	        		}
	        		if (member.isRelation()) {
		        		assertNotNull(mrg.getRelation(member.getRef()));
	        		}
	        	}
	        }
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
