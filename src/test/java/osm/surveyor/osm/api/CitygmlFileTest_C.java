package osm.surveyor.osm.api;

import static org.hamcrest.CoreMatchers.startsWith;

import org.junit.Test;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_C extends GmlLoadRouteTest {
	
	@Test
	public void testSample_c() {
        OsmDom osm = testdo("./src/test/resources/sample_c_bldg_6697_op2.gml");
        try {
			assertEquals(1, osm.relations.size());
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					/*
					 	map "building : Relation" as building {
						 type => building
						 building => yes
						 height => 40.492
						 source => MLIT_PLATEAU;\n http://www.opengis.net/def/crs/EPSG/0/6697
						}
					 */
					assertEquals("building", relation.getTagValue("type"));
					assertEquals("yes", relation.getTagValue("building"));
					assertEquals("2", relation.getTagValue("building:levels"));
					assertEquals("1", relation.getTagValue("building:levels:underground"));
					assertEquals("42.7", relation.getTagValue("height"));
					assertEquals("2.8", relation.getTagValue("ele"));
					assertNull(relation.getTagValue("addr:full"));
					assertNull(relation.getTagValue("ref:MLIT_PLATEAU"));
					int outlineCnt = 0;
					int partCnt = 0;
					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outline")) {
							outlineCnt++;
							assertEquals(mem.getType(), ("way"));
							ElementWay way = (ElementWay)osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							assertNull(way.getTagValue("ref:MLIT_PLATEAU"));
							assertNull(way.getTagValue("addr:full"));
							assertEquals("42.7", way.getTagValue("height"));
							assertEquals("2.8", way.getTagValue("ele"));
							assertEquals(way.getTagValue("building"), ("yes"));
							assertEquals(way.getTagValue("building:levels"), ("2"));
							assertEquals("1", way.getTagValue("building:levels:underground"));
							//assertEquals(5, way.getTagList().size());
						}
						if (mem.getRole().equals("part")) {
							partCnt++;
							assertEquals(mem.getType(), ("way"));
							ElementWay way = (ElementWay)osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							if (way.getTagValue("ref:MLIT_PLATEAU") != null) {
								assertNotNull(way.getTagValue("ref:MLIT_PLATEAU"));
								assertNull(way.getTagValue("addr:full"));
								assertNotNull(way.getTagValue("height"));
								assertNotNull(way.getTagValue("ele"));
								assertNotNull(way.getTagValue("start_date"));
								assertEquals("yes", way.getTagValue("building:part"));
								//assertEquals(7, way.getTagList().size());
							}
						}
					}
					assertEquals(3, relation.members.size());
					assertEquals(1, outlineCnt);
					assertEquals(2, partCnt);
				}
				else if (type.equals("multipolygon")) {
					/*
						map "multipolygon :Relation" as multipolygon {
						 type => multipolygon
						 building => yes
						 height => 32.852000000000004
						 source => MLIT_PLATEAU;\n http://www.opengis.net/def/crs/EPSG/0/6697
						}
					 */
					assertEquals(relation.getTagValue("type"), ("multipolygon"));
					assertEquals(relation.getTagValue("building"), ("yes"));
					assertEquals(relation.getTagValue("building:levels"), ("2"));
					assertEquals(relation.getTagValue("building:levels:underground"), ("1"));
					assertEquals(relation.getTagValue("start_date"), ("1976"));
					assertNotNull(relation.getTagValue("height"));
					assertEquals(relation.getTagValue("height"), startsWith("32.852000000"));
					assertNull(relation.getTagValue("ref:MLIT_PLATEAU"));
					assertEquals(5, relation.members.size());
					int outerCnt = 0;
					int innerCnt = 0;
					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outer")) {
							outerCnt++;
							assertEquals(mem.getType(), ("way"));
							ElementWay way = (ElementWay)osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							assertEquals(1, way.getTagList().size());
							assertNull(way.getTagValue("ref:MLIT_PLATEAU"));
						}
						if (mem.getRole().equals("inner")) {
							innerCnt++;
							assertEquals(mem.getType(), ("way"));
							ElementWay way = (ElementWay)osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							assertEquals(1, way.getTagList().size());
						}
					}
					assertEquals(1, outerCnt);
					assertEquals(2, innerCnt);
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
	    OsmDom osm = testdo("./src/test/resources/sample_cc_bldg_6697_op2.gml");
	    try {
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					assertEquals("building", relation.getTagValue("type"));
					assertEquals("yes", relation.getTagValue("building"));
					assertEquals("3", relation.getTagValue("building:levels"));
					assertEquals("2", relation.getTagValue("building:levels:underground"));
					assertEquals("7.1", relation.getTagValue("height"));
					assertEquals("2.5", relation.getTagValue("ele"));
					assertNull(relation.getTagValue("addr:full"));
					assertNull(relation.getTagValue("ref:MLIT_PLATEAU"));
					//assertEquals(7, relation.getTagList().size());
					
					int outlineCnt = 0;
					int partCnt = 0;
					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outline")) {
							outlineCnt++;
							assertEquals(mem.getType(), ("way"));
							ElementWay way = (ElementWay)osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							assertNull(way.getTagValue("ref:MLIT_PLATEAU"));
							assertNull(way.getTagValue("addr:full"));
							assertEquals(way.getTagValue("height"), ("7.1"));
							assertEquals("2.5", way.getTagValue("ele"));
							assertEquals(way.getTagValue("building"), ("yes"));
							assertEquals(way.getTagValue("building:levels"), ("3"));
							assertEquals(way.getTagValue("building:levels:underground"), ("2"));
							assertEquals("2016", way.getTagValue("survey:date"));
							assertEquals(6, way.getTagList().size());
						}
						if (mem.getRole().equals("part")) {
							partCnt++;
							assertEquals(mem.getType(), ("way"));
							ElementWay way = (ElementWay)osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							assertNotNull(way.getTagValue("ref:MLIT_PLATEAU"));
							assertNull(way.getTagValue("addr:full"));
							assertNotNull(way.getTagValue("height"));
							assertNotNull(way.getTagValue("ele"));
							assertEquals(way.getTagValue("building:part"), ("yes"));
							//assertTrue(way.getTagList().size() >= 5);
						}
					}
					assertEquals(1, outlineCnt);
					assertEquals(3, partCnt);
					assertEquals(4, relation.members.size());
				}
			}
			assertEquals(1, osm.relations.size());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
