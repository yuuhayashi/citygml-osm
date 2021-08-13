package osm.surveyor.osm.api;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.nio.file.Paths;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_E {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Category(DetailTests.class)
	public void testSample_e1_parse() {
		CitygmlFileTest.test_doParse(Paths.get("src/test/resources","sample_e_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.parse(Paths.get("sample_e_bldg_6697_op2_1.osm").toFile());

			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					if (relation.getTagValue("source").endsWith("; 13111-bldg-56522")) {
						assertEquals(relation.getTagValue("type"), ("building"));						
						assertEquals(relation.getTagValue("building"), ("yes"));
						assertEquals(relation.getTagValue("building:levels"), ("2"));
						assertEquals(relation.getTagValue("building:levels:underground"), ("1"));
						assertEquals(relation.getTagValue("name"), ("大田病院"));
						assertEquals(relation.getTagValue("height"), ("21.6"));
						assertEquals("1.62", relation.getTagValue("ele"));
						assertEquals(relation.getTagValue("addr:full"), ("東京都大田区大森東四丁目"));
						assertEquals(relation.getTagValue("addr:ref"), ("13111007004"));
						assertEquals(relation.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-56522"));
						assertEquals(relation.getTagList().size(), is(11));

						int outlineCnt = 0;
						int partCnt = 0;
						for (MemberBean mem : relation.members) {
							if (mem.getRole().equals("outline")) {
								outlineCnt++;
								assertEquals(mem.getType(), ("relation"));
								ElementRelation outline = osm.relations.get(mem.getRef());
								assertNotNull(outline);
								assertEquals(outline.getTagValue("type"), ("multipolygon"));
								assertEquals(outline.getTagValue("building"), ("yes"));
								assertEquals(outline.getTagValue("building:levels"), ("2"));
								assertEquals(outline.getTagValue("building:levels:underground"), ("1"));
								assertEquals(outline.getTagValue("name"), ("大田病院"));
								assertEquals(outline.getTagValue("height"), ("21.6"));
								assertEquals("1.62", outline.getTagValue("ele"));
								assertEquals(outline.getTagValue("addr:full"), ("東京都大田区大森東四丁目"));
								assertEquals(outline.getTagValue("addr:ref"), ("13111007004"));
								assertEquals(outline.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-56522"));
								assertEquals(outline.getTagList().size(), is(11));
							}
							if (mem.getRole().equals("part")) {
								partCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels"), ("2"));
								assertEquals(way.getTagValue("building:levels:underground"), ("1"));
								assertEquals(way.getTagValue("name"), ("大田病院"));
								assertEquals(way.getTagValue("height"), ("21.6"));
								assertEquals("1.62", way.getTagValue("ele"));
								assertEquals(way.getTagValue("start_date"), ("1976"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区大森東四丁目"));
								assertEquals(way.getTagValue("addr:ref"), ("13111007004"));
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-56522"));
								assertEquals(way.getTagList().size(), is(10));
							}
						}
						assertEquals(outlineCnt, is(1));
						assertEquals(partCnt, is(1));
						assertEquals(relation.members.size(), is(2));
					}
					else if (relation.getTagValue("source").endsWith("; 13111-bldg-55333")) {
						assertEquals(relation.getTagValue("type"), ("building"));
						assertEquals(relation.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-55333"));
						assertEquals(relation.getTagValue("addr:full"), ("東京都大田区大森東四丁目"));
						assertEquals(relation.getTagValue("addr:ref"), ("13111007004"));
						assertEquals(relation.getTagValue("height"), ("3.7"));
						assertEquals("1.93", relation.getTagValue("ele"));
						assertEquals(relation.getTagValue("building"), ("yes"));
						assertEquals(relation.getTagValue("building:levels"), ("3"));
						assertEquals(relation.getTagList().size(), is(9));

						int outlineCnt = 0;
						int partCnt = 0;
						for (MemberBean mem : relation.members) {
							if (mem.getRole().equals("outline")) {
								outlineCnt++;
								assertEquals(mem.getType(), ("relation"));
								ElementRelation outline = osm.relations.get(mem.getRef());
								assertNotNull(outline);
								assertEquals(outline.getTagValue("type"), ("multipolygon"));
								assertEquals(outline.getTagValue("building"), ("yes"));
								assertEquals(outline.getTagValue("building:levels"), ("3"));
								assertEquals(outline.getTagValue("addr:full"), ("東京都大田区大森東四丁目"));
								assertEquals(outline.getTagValue("addr:ref"), ("13111007004"));
								assertEquals(outline.getTagValue("height"), ("3.7"));
								assertEquals("1.93", outline.getTagValue("ele"));
								assertEquals(outline.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-55333"));
								assertEquals(outline.getTagList().size(), is(9));
							}
							if (mem.getRole().equals("part")) {
								partCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区大森東四丁目"));
								assertEquals(way.getTagValue("addr:ref"), ("13111007004"));
								assertEquals(way.getTagValue("height"), ("3.7"));
								assertEquals("1.93", way.getTagValue("ele"));
								assertEquals(way.getTagValue("start_date"), ("1977"));
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-55333"));
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels"), ("3"));
								assertEquals(way.getTagList().size(), is(8));
							}
						}
						assertEquals(outlineCnt, is(1));
						assertEquals(partCnt, is(1));
						assertEquals(relation.members.size(), is(2));
					}
				}
				else if (type.equals("multipolygon")) {
					if (relation.getTagValue("source").endsWith("; 13111-bldg-56522")) {
						assertEquals(relation.getTagValue("type"), ("multipolygon"));
						assertEquals(relation.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-56522"));
						assertEquals(relation.getTagValue("name"), ("大田病院"));
						assertEquals(relation.getTagValue("addr:full"), ("東京都大田区大森東四丁目"));
						assertEquals(relation.getTagValue("addr:ref"), ("13111007004"));
						assertEquals(relation.getTagValue("height"), ("21.6"));
						assertEquals("1.62", relation.getTagValue("ele"));
						assertEquals(relation.getTagValue("start_date"), ("1976"));
						assertEquals(relation.getTagValue("building"), ("yes"));
						assertEquals(relation.getTagValue("building:levels"), ("2"));
						assertEquals(relation.getTagValue("building:levels:underground"), ("1"));

						int outerCnt = 0;
						int innerCnt = 0;
						for (MemberBean mem : relation.members) {
							if (mem.getRole().equals("outer")) {
								outerCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-56522"));
								assertEquals(way.getTagList().size(), is(1));
							}
							if (mem.getRole().equals("inner")) {
								innerCnt++;
							}
						}
						assertEquals(outerCnt, is(1));
						assertEquals(innerCnt, is(0));
						assertEquals(relation.members.size(), is(1));
					}
					else if (relation.getTagValue("source").endsWith("; 13111-bldg-55333")) {
						assertEquals(relation.getTagValue("type"), ("multipolygon"));
						assertEquals(relation.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-55333"));
						assertEquals(relation.getTagValue("addr:full"), ("東京都大田区大森東四丁目"));
						assertEquals(relation.getTagValue("addr:ref"), ("13111007004"));
						assertEquals(relation.getTagValue("height"), ("3.7"));
						assertEquals("1.93", relation.getTagValue("ele"));
						assertEquals(relation.getTagValue("start_date"), ("1977"));
						assertEquals(relation.getTagValue("building"), ("yes"));
						assertEquals(relation.getTagValue("building:levels"), ("3"));

						int outerCnt = 0;
						int innerCnt = 0;
						for (MemberBean mem : relation.members) {
							if (mem.getRole().equals("outer")) {
								outerCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-55333"));
								assertEquals(way.getTagList().size(), is(1));
							}
							if (mem.getRole().equals("inner")) {
								innerCnt++;
							}
						}
						assertEquals(outerCnt, is(1));
						assertEquals(innerCnt, is(0));
						assertEquals(relation.members.size(), is(1));
					}
				}
			}
			assertEquals(osm.relations.size(), is(4));
			
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
	
	@Test
	@Category(DetailTests.class)
	public void testSample_e2_margePart() {
		CitygmlFileTest.test_doRelationMarge(Paths.get("src/test/resources","sample_e_bldg_6697_op2.gml"));
		
        OsmDom osm = new OsmDom();
        try {
			osm.parse(Paths.get("sample_e_bldg_6697_op2_2.osm").toFile());

			assertNotNull(osm.relations);
			int partCnt = 0;
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				if (relation.isBuilding()) {
					assertEquals(relation.getTagValue("type"), ("building"));
					assertEquals(relation.getTagValue("building"), ("yes"));
					assertEquals(relation.getTagValue("name"), ("大田病院"));
					assertEquals(relation.getTagValue("addr:full"), ("東京都大田区大森東四丁目"));
					assertEquals(relation.getTagValue("addr:ref"), ("13111007004"));
					assertNotNull(relation.getTagValue("height"));
					assertNotNull(relation.getTagValue("ele"));
					assertEquals(relation.getTagValue("source"), startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
	
					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outline")) {
							assertEquals(mem.getType(), ("relation"));
							ElementRelation outline = osm.relations.get(mem.getRef());
							assertNotNull(outline);
						}
						if (mem.getRole().equals("part")) {
							partCnt++;
							assertEquals(mem.getType(), ("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
						}
					}
				}
				else if (relation.isMultipolygon()) {
					if (relation.getTagValue("source").endsWith("; 13111-bldg-55333")) {
						assertEquals(relation.getTagValue("type"), ("multipolygon"));
						assertEquals(relation.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-55333"));
						assertEquals(relation.getTagValue("addr:full"), ("東京都大田区大森東四丁目"));
						assertEquals(relation.getTagValue("addr:ref"), ("13111007004"));
						assertEquals(relation.getTagValue("height"), ("3.7"));
						assertEquals("1.93", relation.getTagValue("ele"));
						assertNull(relation.getTagValue("start_date"));
						assertEquals(relation.getTagValue("building"), ("yes"));
						assertEquals(relation.getTagValue("building:levels"), ("3"));

						int outerCnt = 0;
						int innerCnt = 0;
						for (MemberBean mem : relation.members) {
							if (mem.getRole().equals("outer")) {
								outerCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-55333"));
								assertEquals(way.getTagList().size(), is(1));
							}
							if (mem.getRole().equals("inner")) {
								innerCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-55333"));
								assertEquals(way.getTagList().size(), is(1));
							}
						}
						assertEquals(1, outerCnt);
						assertEquals(0, innerCnt);
						assertEquals(1, relation.members.size());
					}
				}
			}
			assertEquals(2, partCnt);
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
	
	@Test
	@Category(DetailTests.class)
	public void testSample_e3_removeSinglePart() {
		CitygmlFileTest.test_doRemoveSinglePart(Paths.get("src/test/resources","sample_e_bldg_6697_op2.gml"));
		
        OsmDom osm = new OsmDom();
        try {
			osm.parse(Paths.get("sample_e_bldg_6697_op2_3.osm").toFile());

			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					assertEquals(relation.getTagValue("type"), ("building"));
					assertEquals(relation.getTagValue("building"), ("yes"));
					assertEquals(relation.getTagValue("building:levels"), ("3"));
					assertEquals(relation.getTagValue("building:levels:underground"), ("1"));
					assertEquals(relation.getTagValue("name"), ("大田病院"));
					assertNotNull(relation.getTagValue("height"));
					assertEquals(relation.getTagValue("addr:ref"), ("13111007004"));
					assertEquals(relation.getTagValue("addr:full"), ("東京都大田区大森東四丁目"));
					assertEquals(relation.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));

					int outlineCnt = 0;
					int partCnt = 0;
					int outerCnt = 0;
					int innerCnt = 0;
					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outline")) {
							outlineCnt++;
						}
						if (mem.getRole().equals("part")) {
							partCnt++;
							assertEquals(mem.getType(), ("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							if (way.getTagValue("source").endsWith("; 13111-bldg-56522")) {
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels"), ("2"));
								assertEquals(way.getTagValue("building:levels:underground"), ("1"));
								assertEquals(way.getTagValue("name"), ("大田病院"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区大森東四丁目"));
								assertEquals(way.getTagValue("addr:ref"), ("13111007004"));
								assertEquals(way.getTagValue("height"), ("21.6"));
								assertEquals("1.62", way.getTagValue("ele"));
								assertEquals(way.getTagValue("start_date"), ("1976"));
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-56522"));
								assertEquals(way.getTagList().size(), is(10));
							}
							else if (way.getTagValue("source").endsWith("; 13111-bldg-55333")) {
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-55333"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区大森東四丁目"));
								assertEquals(way.getTagValue("addr:ref"), ("13111007004"));
								assertEquals(way.getTagValue("height"), ("3.7"));
								assertEquals("1.93", way.getTagValue("ele"));
								assertEquals(way.getTagValue("start_date"), ("1977"));
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels"), ("3"));
								assertEquals(way.getTagList().size(), is(8));
							}
						}
					}
					assertEquals(1, outlineCnt);
					assertEquals(0, innerCnt);
					assertEquals(0, outerCnt);
					assertEquals(2, partCnt);
				}
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	@Test
	@Category(DetailTests.class)
	public void testSample_e4_createOutline() {
		CitygmlFileTest.test4_doCreateOutline(Paths.get("src/test/resources","sample_e_bldg_6697_op2.gml"));
		
        OsmDom osm = new OsmDom();
        try {
			osm.parse(Paths.get("sample_e_bldg_6697_op2_4.osm").toFile());
			checkSample_e(osm);
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testSample_e() {
		CitygmlFileTest.test(Paths.get("src/test/resources","sample_e_bldg_6697_op2.gml"));
		
        OsmDom osm = new OsmDom();
        try {
			osm.parse(Paths.get("sample_e_bldg_6697_op2.osm").toFile());
			checkSample_e(osm);
			assertEquals(2, osm.relations.size());
			assertEquals(4, osm.ways.size());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
    
	public void checkSample_e(OsmDom osm) {
        try {
			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					assertEquals("building", relation.getTagValue("type"));
					assertEquals(relation.getTagValue("building"), ("yes"));
					assertEquals(relation.getTagValue("building:levels"), ("3"));
					assertEquals(relation.getTagValue("building:levels:underground"), ("1"));
					assertEquals(relation.getTagValue("name"), ("大田病院"));
					assertEquals(relation.getTagValue("addr:full"), ("東京都大田区大森東四丁目"));
					assertEquals(relation.getTagValue("addr:ref"), ("13111007004"));
					assertEquals(relation.getTagValue("height"), ("21.6"));
					assertEquals("1.62", relation.getTagValue("ele"));
					assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697", relation.getTagValue("source"));
					assertEquals(10, relation.getTagList().size());

					int outlineCnt = 0;
					int partCnt = 0;
					int outerCnt = 0;
					int innerCnt = 0;
					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outline")) {
							outlineCnt++;
							assertEquals("relation", mem.getType());
							ElementRelation polygon = osm.relations.get(Long.toString(mem.getRef()));
							assertNotNull(polygon);
							assertEquals("multipolygon", polygon.getTagValue("type"));
							assertEquals("yes", polygon.getTagValue("building"));
							assertEquals("大田病院", polygon.getTagValue("building:name"));
							assertNull(polygon.getTagValue("name"));
							assertEquals("3", polygon.getTagValue("building:levels"));
							assertEquals("1", polygon.getTagValue("building:levels:underground"));
							assertEquals("東京都大田区大森東四丁目", polygon.getTagValue("addr:full"));
							assertEquals("13111007004", polygon.getTagValue("addr:ref"));
							assertEquals("21.6", polygon.getTagValue("height"));
							assertEquals("1.62", polygon.getTagValue("ele"));
							assertEquals("1976", polygon.getTagValue("start_date"));
							assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697", polygon.getTagValue("source"));
							assertEquals(11, polygon.getTagList().size());
							
							for (MemberBean member : polygon.members) {
								if (member.getRole().equals("outer")) {
									outerCnt++;
									assertEquals("way", member.getType());
									ElementWay way = osm.ways.get(Long.toString(member.getRef()));
									assertNotNull(way);
									assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697", way.getTagValue("source"));
									assertEquals(1, way.getTagList().size());
								}
								if (member.getRole().equals("inner")) {
									innerCnt++;
									assertEquals("way", member.getType());
									ElementWay way = osm.ways.get(Long.toString(member.getRef()));
									assertNotNull(way);
									assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697", way.getTagValue("source"));
									assertEquals(1, way.getTagList().size());
								}
							}
							assertEquals(2, polygon.members.size());
						}
						if (mem.getRole().equals("part")) {
							partCnt++;
							assertEquals(mem.getType(), ("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							if (way.getTagValue("source").endsWith("; 13111-bldg-56522")) {
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels"), ("2"));
								assertEquals(way.getTagValue("building:levels:underground"), ("1"));
								assertEquals(way.getTagValue("name"), ("大田病院"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区大森東四丁目"));
								assertEquals(way.getTagValue("addr:ref"), ("13111007004"));
								assertEquals(way.getTagValue("height"), ("21.6"));
								assertEquals("1.62", way.getTagValue("ele"));
								assertEquals(way.getTagValue("start_date"), ("1976"));
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-56522"));
								assertEquals(10, way.getTagList().size());
							}
							else if (way.getTagValue("source").endsWith("; 13111-bldg-55333")) {
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels"), ("3"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区大森東四丁目"));
								assertEquals(way.getTagValue("addr:ref"), ("13111007004"));
								assertEquals(way.getTagValue("height"), ("3.7"));
								assertEquals("1.93", way.getTagValue("ele"));
								assertEquals(way.getTagValue("start_date"), ("1977"));
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-55333"));
								assertEquals(8, way.getTagList().size());
							}
						}
					}
					assertEquals(1, innerCnt);
					assertEquals(1, outerCnt);
					assertEquals(1, outlineCnt);
					assertEquals(2, partCnt);
					assertEquals(3, relation.members.size());
				}
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
