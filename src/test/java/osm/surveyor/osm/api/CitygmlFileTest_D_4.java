package osm.surveyor.osm.api;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import osm.surveyor.DetailTests;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_D_4 extends CitygmlFileTest4 {

	@Test
	@Category(DetailTests.class)
	public void testSample_d4_createOutline() {
		
        try {
        	OsmDom osm = testdo("./src/test/resources/sample_d_bldg_6697_op2.gml");
    		
			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					assertEquals(relation.getTagValue("type"), ("building"));
					assertNull(relation.getTagValue("source"));
					assertNull(relation.getTagValue("addr:full"));
					assertEquals(relation.getTagValue("height"), ("34.7"));
					assertEquals(relation.getTagValue("ele"), ("2.7"));
					assertEquals(relation.getTagValue("building"), ("yes"));
					assertEquals(relation.getTagValue("building:levels"), ("3"));
					assertEquals(relation.getTagValue("building:levels:underground"), ("1"));
					assertEquals(6, relation.getTagList().size());

					int outlineCnt = 0;
					int partCnt = 0;
					int outerCnt = 0;
					int innerCnt = 0;
					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outline")) {
							outlineCnt++;
							assertEquals(mem.getType(), ("relation"));
							ElementRelation polygon = osm.relations.get(Long.toString(mem.getRef()));
							assertNotNull(polygon);
							assertEquals(polygon.getTagValue("type"), ("multipolygon"));
							assertNull(polygon.getTagValue("source"));
							assertNull(polygon.getTagValue("addr:full"));
							assertEquals(polygon.getTagValue("height"), ("34.7"));
							assertEquals("2.7", polygon.getTagValue("ele"));
							assertEquals(polygon.getTagValue("building"), ("yes"));
							for (MemberBean member : polygon.members) {
								if (member.getRole().equals("outer")) {
									outerCnt++;
									assertEquals(member.getType(), ("way"));
									ElementWay way = osm.ways.get(Long.toString(member.getRef()));
									assertNotNull(way);
									assertNull(way.getTagValue("source"));
									assertEquals(0, way.getTagList().size());
								}
								if (member.getRole().equals("inner")) {
									innerCnt++;
									assertEquals(member.getType(), ("way"));
									ElementWay way = osm.ways.get(Long.toString(member.getRef()));
									assertNotNull(way);
									assertNull(way.getTagValue("source"));
									assertEquals(1, way.getTagList().size());
								}
							}
							assertEquals(3, polygon.members.size());
						}
						if (mem.getRole().equals("part")) {
							partCnt++;
							assertEquals(mem.getType(), ("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							/*
							 * 
							if (way.getTagValue("source").endsWith("; 13111-bldg-72601")) {
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels"), ("2"));
								assertEquals(way.getTagValue("building:levels:underground"), ("1"));
								assertNull(way.getTagValue("addr:full"));
								assertEquals(way.getTagValue("height"), ("34.7"));
								assertEquals(way.getTagValue("ele"), ("2.68"));
								assertEquals(way.getTagValue("start_date"), ("1976"));
								assertNull(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));
								assertEquals(9, way.getTagList().size());
							}
							else if (way.getTagValue("source").endsWith("; 13111-bldg-71799")) {
								assertNull(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-71799"));
								assertNull(way.getTagValue("addr:full"));
								assertEquals(way.getTagValue("height"), ("30.2"));
								assertEquals(way.getTagValue("ele"), ("3.13"));
								assertEquals(way.getTagValue("start_date"), ("1977"));
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels"), ("3"));
								assertEquals(8, way.getTagList().size());
							}
							 */
						}
					}
					assertEquals(2, innerCnt);
					assertEquals(1, outerCnt);
					assertEquals(1, outlineCnt);
					assertEquals(2, partCnt);
					assertEquals(3, relation.members.size());
				}
			}
			assertEquals(2, osm.relations.size());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
    
}
