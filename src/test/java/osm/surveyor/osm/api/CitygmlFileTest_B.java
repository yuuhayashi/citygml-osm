package osm.surveyor.osm.api;

import org.junit.Test;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_B extends GmlLoadRouteTest {

	@Test
	public void testSample_b() {
        OsmDom osm = testdo("./src/test/resources/sample_b_bldg_6697_op2.gml");
        try {
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("multipolygon")) {
					/*
						map "multipolygon :Relation" as multipolygon {
						 type => multipolygon
						 building => yes
						 height => 17.582
						 ref:MLIT_PLATEAU => null
						}
					 */
					assertEquals("multipolygon", relation.getTagValue("type"));
					assertEquals("yes", relation.getTagValue("building"));
					assertNull(relation.getTagValue("addr:full"));
					assertEquals("16.9", relation.getTagValue("height"));
					assertEquals("2.5", relation.getTagValue("ele"));
					assertEquals("1976", relation.getTagValue("start_date"));
					assertNull(relation.getTagValue("ref:MLIT_PLATEAU"));
					int outerCnt = 0;
					int innerCnt = 0;
					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outer")) {
							outerCnt++;
							assertEquals("way", mem.getType());
							ElementWay way = (ElementWay)osm.ways.get(Long.toString(mem.getRef()));
							assertNull(way.getTagValue("ref:MLIT_PLATEAU"));
							assertNull(way.getTagValue("source"));
							assertEquals(0, way.getTagList().size());
						}
						if (mem.getRole().equals("inner")) {
							innerCnt++;
							assertEquals("way", mem.getType());
							ElementWay way = (ElementWay)osm.ways.get(Long.toString(mem.getRef()));
							assertEquals("13111-bldg-61384", way.getTagValue("ref:MLIT_PLATEAU"));
							assertEquals(1, way.getTagList().size());
						}
					}
					assertEquals(1, outerCnt);
					assertEquals(1, innerCnt);
					assertEquals(2, relation.members.size());
				}
			}
			assertEquals(1, osm.relations.size());
			assertEquals(2, osm.ways.size());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

}
