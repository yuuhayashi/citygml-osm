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

import osm.surveyor.osm.NdBean;
import osm.surveyor.osm.NodeBean;
import osm.surveyor.osm.OsmBean;
import osm.surveyor.osm.BodyMap;
import osm.surveyor.osm.BoundsBean;
import osm.surveyor.osm.WayBean;

/**
 * `mvn test -Dtest=osm.surveyor.update.Test_Test_70`
 * 新座市（11230_niiza-shi_CityGML2）
 * 
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_70 extends OsmUpdaterTest {

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
				Paths.get("./src/test/resources/Issue_70.osm"),
				Paths.get("./Issue_70.osm"), REPLACE_EXISTING
			);
		}
		catch (FileAlreadyExistsException ee) {}
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		try {
			Files.copy(
				Paths.get("./src/test/resources/53395403_bldg_6697_op.org.osm"),
				Paths.get("./Issue_70.org.osm"), REPLACE_EXISTING
			);
		}
		catch (FileAlreadyExistsException ee) {}
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * 11230-bldg-1163
	 * [Issue #71 v1.4.2: 既存建物から引き継ぐ際にPlateau側に複数の建物がある場合に小さな建物に引き継がれる](https://github.com/yuuhayashi/citygml-osm/issues/71)
	 */
	@Test
	public void test_71() {
		BodyMap map = testdo(Paths.get("./Issue_70.osm"));
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
	        	String ref = way.getTagValue("ref:MLIT_PLATEAU");
	        	if (ref != null) {
		        	if (ref.equals("11230-bldg-1")) {
		        		assertEquals("D’クラディアひばりケ丘ミッドレジデンス", way.getTagValue("name"));
		    	        assertNotNull(way.getTagValue("MLIT_PLATEAU:fixme"));
		        	}
		        	else if (ref.equals("11230-bldg-1165")) {
		        		assertNull(way.getTagValue("name"));
		    	        assertNull(way.getTagValue("MLIT_PLATEAU:fixme"));
		        	}
		        	
		        	if (ref.equals("11230-bldg-2")) {
		        		assertEquals("東京ドーム後楽園スイミングスクールひばりが丘", way.getTagValue("name"));
		    	        assertNotNull(way.getTagValue("MLIT_PLATEAU:fixme"));
		        	}
		        	else if (ref.equals("11230-bldg-1163")) {
		        		assertNull(way.getTagValue("name"));
		    	        assertNull(way.getTagValue("MLIT_PLATEAU:fixme"));
		        	}
	        	}
	        }
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
