package osm.surveyor.osm.api;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_B_3 extends CitygmlFileTest3 {

	@Test
	@Category(DetailTests.class)
	public void testSample_b3_removeSinglePart() {
		
        try {
            OsmDom osm = testdo("./src/test/resources/sample_b_bldg_6697_op2.gml");
    		
			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("multipolygon")) {
					int outerCnt = 0;
					int innerCnt = 0;
					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outer")) {
							outerCnt++;
						}
						if (mem.getRole().equals("inner")) {
							innerCnt++;
						}
					}
					assertEquals(1, outerCnt);
					assertEquals(1, innerCnt);
					assertEquals(2, relation.members.size());
				}
				else {
					assertEquals("building", type);
				}
			}
			assertEquals(2, osm.relations.size());
			
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
	
}
