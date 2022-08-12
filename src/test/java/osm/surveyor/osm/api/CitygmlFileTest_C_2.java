package osm.surveyor.osm.api;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_C_2 extends CitygmlFileTest2 {

	@Test
	@Category(DetailTests.class)
	public void testSample_c2_margePart() {
		
        try {
            OsmDom osm = testdo("./src/test/resources/sample_c_bldg_6697_op2.gml");
    		
			assertNotNull(osm.relations);
			int partCnt = 0;
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					/*
					 * 
					if (relation.getTagValue("source").endsWith("; 13111-bldg-473")) {
					}
					else {
						assertEquals("building", relation.getTagValue("type"));
						assertTrue(relation.getTagValue("source").startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
						assertNull(relation.getTagValue("addr:full"));
						assertEquals("42.7", relation.getTagValue("height"));
						assertEquals("2.8", relation.getTagValue("ele"));
						assertEquals("yes", relation.getTagValue("building"));
						assertEquals("2", relation.getTagValue("building:levels"));
						assertEquals("1", relation.getTagValue("building:levels:underground"));

						for (MemberBean mem : relation.members) {
							if (mem.getRole().equals("outline")) {
								assertEquals(mem.getType(), ("relation"));
								ElementRelation outline = osm.relations.get(mem.getRef());
								assertNotNull(outline);
								if (outline.getTagValue("source").endsWith("; 13111-bldg-473")) {
									assertEquals("multipolygon", outline.getTagValue("type"));
									assertEquals("yes", outline.getTagValue("building"));
									assertEquals("2", outline.getTagValue("building:levels"));
									assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473", outline.getTagValue("source"));
									assertNull(outline.getTagValue("addr:full"));
									assertEquals("42.7", outline.getTagValue("height"));
									assertEquals("2.81", outline.getTagValue("ele"));
									assertEquals(8, outline.getTagList().size());
								}
								else if (outline.getTagValue("source").endsWith("; 13111-bldg-386")) {
									assertEquals(outline.getTagValue("type"), ("multipolygon"));
									assertEquals(outline.getTagValue("building"), ("yes"));
									assertEquals(outline.getTagValue("building:levels:underground"), ("1"));
									assertNull(outline.getTagValue("addr:full"));
									assertEquals(outline.getTagValue("height"), ("7.3"));
									assertEquals(outline.getTagValue("ele"), ("2.81"));
									assertEquals(outline.getTagValue("start_date"), ("2003"));
									assertEquals(outline.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-386"));
									assertEquals(9, outline.getTagList().size());
								}
							}
							if (mem.getRole().equals("part")) {
								partCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								if (way.getTagValue("source").endsWith("; 13111-bldg-473")) {
									assertEquals(way.getTagValue("building:part"), ("yes"));
									assertEquals(way.getTagValue("building:levels"), ("2"));
									assertNull(way.getTagValue("addr:full"));
									assertEquals(way.getTagValue("height"), ("42.7"));
									assertEquals(way.getTagValue("ele"), ("2.81"));
									//assertEquals(way.getTagValue("start_date"), ("1976"));
									assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));
									//assertEquals(8, way.getTagList().size());
								}
								else if (way.getTagValue("source").endsWith("; 13111-bldg-386")) {
									assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-386"));
									assertNull(way.getTagValue("addr:full"));
									assertEquals(way.getTagValue("height"), ("7.3"));
									assertEquals(way.getTagValue("ele"), ("2.81"));
									assertEquals(way.getTagValue("start_date"), ("2003"));
									assertEquals(way.getTagValue("building:part"), ("yes"));
									assertEquals(way.getTagValue("building:levels:underground"), ("1"));
									assertEquals(8, way.getTagList().size());
								}
							}
						}
					}
					 */
				}
				else if (type.equals("multipolygon")) {
					/*
					 * 
					if (relation.getTagValue("source").endsWith("; 13111-bldg-473")) {
						assertEquals(relation.getTagValue("type"), ("multipolygon"));
						assertEquals(relation.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));
						assertNull(relation.getTagValue("addr:full"));
						assertEquals(relation.getTagValue("height"), ("42.7"));
						assertEquals(relation.getTagValue("ele"), ("2.81"));
						assertEquals(relation.getTagValue("start_date"), ("1976"));
						assertEquals(relation.getTagValue("building"), ("yes"));

						int outerCnt = 0;
						int innerCnt = 0;
						for (MemberBean mem : relation.members) {
							if (mem.getRole().equals("outer")) {
								outerCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));
								assertEquals(1, way.getTagList().size());
							}
							if (mem.getRole().equals("inner")) {
								innerCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));
								assertEquals(1, way.getTagList().size());
							}
						}
						assertEquals(1, outerCnt);
						assertEquals(0, innerCnt);
						assertEquals(1, relation.members.size());
					}
					else if (relation.getTagValue("source").endsWith("; 13111-bldg-386")) {
						assertEquals(relation.getTagValue("type"), ("multipolygon"));
						assertEquals(relation.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-386"));
						assertNull(relation.getTagValue("addr:full"));
						assertEquals(relation.getTagValue("height"), ("7.3"));
						assertEquals(relation.getTagValue("ele"), ("2.81"));
						assertEquals(relation.getTagValue("start_date"), ("2003"));
						assertEquals(relation.getTagValue("building"), ("yes"));
						assertEquals(relation.getTagValue("building:levels:underground"), ("1"));

						int outerCnt = 0;
						int innerCnt = 0;
						for (MemberBean mem : relation.members) {
							if (mem.getRole().equals("outer")) {
								outerCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-386"));
								assertEquals(1, way.getTagList().size());
							}
							if (mem.getRole().equals("inner")) {
								innerCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-386"));
								assertEquals(1, way.getTagList().size());
							}
						}
						assertEquals(1, outerCnt);
						assertEquals(0, innerCnt);
						assertEquals(1, relation.members.size());
					}
					 */
				}
			}
			//assertEquals(2, partCnt);
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	@Test
	@Category(DetailTests.class)
	public void testSample_cc2_margePart() {
		
        try {
        	OsmDom osm = testdo("src/test/resources/sample_cc_bldg_6697_op2.gml");
    		
			assertNotNull(osm.relations);
			int partCnt = 0;
			int innerCnt = 0;
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					assertNull(relation.getTagValue("source"));
					assertNull(relation.getTagValue("addr:full"));
					assertNotNull(relation.getTagValue("height"));
					assertEquals(relation.getTagValue("building"), ("yes"));

					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outline")) {
							assertEquals(mem.getType(), ("relation"));
							ElementRelation outline = osm.relations.get(mem.getRef());
							assertNotNull(outline);
							/*
							 * 
							if (outline.getTagValue("source").endsWith("; 13111-bldg-59928")) {
								assertEquals(outline.getTagValue("type"), ("multipolygon"));
								assertEquals(outline.getTagValue("building"), ("yes"));
								assertEquals(outline.getTagValue("building:levels"), ("2"));
								assertEquals(outline.getTagValue("building:levels:underground"), ("2"));
								assertNull(outline.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-59928"));
								assertNull(outline.getTagValue("addr:full"));
								assertEquals(outline.getTagValue("height"), ("7.1"));
								assertEquals(outline.getTagValue("ele"), ("2.48"));
								assertEquals(9, outline.getTagList().size());
							}
							else if (outline.getTagValue("source").endsWith("; 13111-bldg-59955")) {
								assertEquals(outline.getTagValue("type"), ("multipolygon"));
								assertEquals(outline.getTagValue("building"), ("yes"));
								assertEquals(outline.getTagValue("building:levels"), ("3"));
								assertEquals(outline.getTagValue("building:levels:underground"), ("1"));
								assertNull(outline.getTagValue("addr:full"));
								assertEquals(outline.getTagValue("height"), ("5.5"));
								assertEquals(outline.getTagValue("ele"), ("2.53"));
								assertNull(outline.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-59955"));
								assertEquals(9, outline.getTagList().size());
							}
							else if (outline.getTagValue("source").endsWith("; 13111-bldg-59680")) {
								assertEquals(outline.getTagValue("type"), ("multipolygon"));
								assertEquals(outline.getTagValue("building"), ("yes"));
								assertNull(outline.getTagValue("addr:full"));
								assertEquals(outline.getTagValue("height"), ("6.8"));
								assertEquals(outline.getTagValue("ele"), ("2.54"));
								assertNull(outline.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-59680"));
								assertEquals(7, outline.getTagList().size());
							}
							 */
						}
						if (mem.getRole().equals("part")) {
							partCnt++;
							assertEquals(mem.getType(), ("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							/*
							 * 
							if (way.getTagValue("source").endsWith("; 13111-bldg-59928")) {
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels"), ("2"));
								assertEquals(way.getTagValue("building:levels:underground"), ("2"));
								assertNull(way.getTagValue("addr:full"));
								assertEquals(way.getTagValue("height"), ("7.1"));
								assertEquals(way.getTagValue("ele"), ("2.48"));
								assertNull(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-59928"));
								assertEquals(8, way.getTagList().size());
							}
							else if (way.getTagValue("source").endsWith("; 13111-bldg-59955")) {
								assertNull(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-59955"));
								assertNull(way.getTagValue("addr:full"));
								assertEquals(way.getTagValue("height"), ("5.5"));
								assertEquals("2.53", way.getTagValue("ele"));
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels"), ("3"));
								assertEquals(way.getTagValue("building:levels:underground"), ("1"));
								assertEquals(8, way.getTagList().size());
							}
							else if (way.getTagValue("source").endsWith("; 13111-bldg-59680")) {
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertNull(way.getTagValue("addr:full"));
								assertEquals(way.getTagValue("height"), ("6.8"));
								assertEquals("2.54", way.getTagValue("ele"));
								assertNull(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-59680"));
								assertEquals(6, way.getTagList().size());
							}
							 */
						}
					}
				}
				else if (type.equals("multipolygon")) {
					assertEquals(relation.getTagValue("type"), ("multipolygon"));
					//assertTrue(relation.getTagValue("source").startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					assertNull(relation.getTagValue("addr:full"));
					assertNotNull(relation.getTagValue("height"));
					assertEquals("yes", relation.getTagValue("building"));

					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outer")) {
							assertEquals(mem.getType(), ("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							assertNull(way.getTagValue("source"));
							assertEquals(0, way.getTagList().size());
						}
						if (mem.getRole().equals("inner")) {
							innerCnt++;
						}
					}
					assertEquals(1, relation.members.size());
				}
			}
			assertEquals(0, innerCnt);
			assertEquals(3, partCnt);
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
	
}
