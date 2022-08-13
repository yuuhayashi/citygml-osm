package osm.surveyor.osm.api;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_Issue28_2 extends CitygmlFileTest2 {

	/**
	 * `mvn test -Dtest=CitygmlFileTest_A#testSample_a2_margePart`
	 * 
	 */
	@Test
	@Category(DetailTests.class)
	public void testSample_a2_margePart() {
		try {
			OsmDom osm = testdo("./src/test/resources/Issue28_50303525_op.gml");

			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("part")) {
							assertEquals("way", mem.getType());

							// "40205-bldg-81197"をメンバに持つリレーションは 20個以上の建物パーツを持つべき
							/*
							 * 
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							if (way.getTagValue("source").endsWith("40205-bldg-81197")) {
								System.out.println("members: "+ relation.members.size());
								assertTrue(relation.members.size() >= 20);
								i++;
							}
							 */
						}
					}
				}
			}
			//assertEquals(1, i);
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
