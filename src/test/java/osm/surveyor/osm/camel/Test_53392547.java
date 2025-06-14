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
import osm.surveyor.osm.WayBean;
import osm.surveyor.osm.way.Wayable;

/**
 * `mvn test -Dtest=Test_53392547`
 * 
 * @author hayashi
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_53392547 extends OsmUpdaterTest {
	
	@Before
	public void setup() {
		try {
			Files.copy(
				Paths.get("./src/test/resources/53392547_bldg_6697_op2.osm"),
				Paths.get("./53392547_bldg_6697_op2.osm"), REPLACE_EXISTING
			);
		}
		catch (FileAlreadyExistsException ee) {}
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		try {
			Files.copy(
				Paths.get("./src/test/resources/53392547_bldg_6697_op2.org.osm"),
				Paths.get("./53392547_bldg_6697_op2.org.osm"), REPLACE_EXISTING
			);
		}
		catch (FileAlreadyExistsException ee) {}
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * 東京都大田区南六郷三丁目 "53392547_bldg_6697_op2"
	 * 東京都大田区 多摩川右岸付近
	 * <bounds minlat="35.5407086" minlon="139.7124522" maxlat="35.5422523" maxlon="139.7156384"/>
	 * ・ビルディングリレーションは存在しない
	 * ・タグありのノードを含むWAYは存在しない
	 * - "highway"WAYは存在しないこと
	 * - "landuse"WAYは存在しないこと
	 * 
	 * 東京都大田区 多摩川左岸 モデル地域
	 */
	@Test
	public void test() {
		BodyMap map = testdo(Paths.get("./53392547_bldg_6697_op2.osm"));
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
