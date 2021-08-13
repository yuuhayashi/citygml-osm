package osm.surveyor.osm.api;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
import osm.surveyor.osm.TagBean;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_C {

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
	public void testSample_c1_parse() {
		CitygmlFileTest.test_doParse(Paths.get("src/test/resources","sample_c_bldg_6697_op2.gml"));
		
        try {
            OsmDom osm = new OsmDom();
			osm.parse(Paths.get("sample_c_bldg_6697_op2_1.osm").toFile());

			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					if (relation.getTagValue("source").endsWith("; 13111-bldg-473")) {
						assertEquals(relation.getTagValue("type"), ("building"));
						assertEquals(relation.getTagValue("building"), ("yes"));
						assertEquals(relation.getTagValue("building:levels"), ("2"));
						assertEquals(relation.getTagValue("height"), ("42.7"));
						assertEquals(relation.getTagValue("ele"), ("2.81"));
						assertEquals(relation.getTagValue("addr:full"), ("東京都大田区南六郷三丁目"));
						assertEquals(relation.getTagValue("addr:ref"), ("13111058003"));
						assertEquals(relation.getTagValue("start_date"), ("1976"));
						assertEquals(relation.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));

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
								assertEquals(outline.getTagValue("height"), ("42.7"));
								assertEquals(outline.getTagValue("ele"), ("2.81"));
								assertEquals(outline.getTagValue("addr:full"), ("東京都大田区南六郷三丁目"));
								assertEquals(outline.getTagValue("addr:ref"), ("13111058003"));
								assertEquals(outline.getTagValue("start_date"), ("1976"));
								assertEquals(outline.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));
								assertEquals(9, outline.getTagList().size());
							}
							if (mem.getRole().equals("part")) {
								partCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels"), ("2"));
								assertEquals(way.getTagValue("height"), ("42.7"));
								assertEquals(way.getTagValue("ele"), ("2.81"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区南六郷三丁目"));
								assertEquals(way.getTagValue("addr:ref"), ("13111058003"));
								assertEquals(way.getTagValue("start_date"), ("1976"));
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));
								assertEquals(8, way.getTagList().size());
							}
						}
						assertEquals(1, outlineCnt);
						assertEquals(1, partCnt);
					}
					else if (relation.getTagValue("source").endsWith("; 13111-bldg-386")) {
						assertEquals("building", relation.getTagValue("type"));
						assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-386", relation.getTagValue("source"));
						assertEquals(relation.getTagValue("addr:full"), ("東京都大田区南六郷三丁目"));
						assertEquals(relation.getTagValue("addr:ref"), ("13111058003"));
						assertEquals(relation.getTagValue("height"), ("7.3"));
						assertEquals(relation.getTagValue("ele"), ("2.81"));
						assertEquals(relation.getTagValue("start_date"), ("2003"));
						assertEquals(relation.getTagValue("building"), ("yes"));
						assertEquals(relation.getTagValue("building:levels:underground"), ("1"));

						int outlineCnt = 0;
						int partCnt = 0;
						for (MemberBean mem : relation.members) {
							if (mem.getRole().equals("outline")) {
								outlineCnt++;
								assertEquals("relation", mem.getType());
								ElementRelation outline = osm.relations.get(mem.getRef());
								assertNotNull(outline);
								assertEquals(outline.getTagValue("type"), ("multipolygon"));
								assertEquals(outline.getTagValue("building"), ("yes"));
								assertEquals(outline.getTagValue("building:levels:underground"), ("1"));
								assertEquals(outline.getTagValue("addr:full"), ("東京都大田区南六郷三丁目"));
								assertEquals(outline.getTagValue("addr:ref"), ("13111058003"));
								assertEquals(outline.getTagValue("height"), ("7.3"));
								assertEquals(outline.getTagValue("ele"), ("2.81"));
								assertEquals(outline.getTagValue("start_date"), ("2003"));
								assertEquals(outline.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-386"));
								assertEquals(9, outline.getTagList().size());
							}
							if (mem.getRole().equals("part")) {
								partCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-386"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区南六郷三丁目"));
								assertEquals(way.getTagValue("addr:ref"), ("13111058003"));
								assertEquals(way.getTagValue("height"), ("7.3"));
								assertEquals(way.getTagValue("ele"), ("2.81"));
								assertEquals(way.getTagValue("start_date"), ("2003"));
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels:underground"), ("1"));
								assertEquals(8, way.getTagList().size());
							}
						}
						assertEquals(1, outlineCnt);
						assertEquals(1, partCnt);
					}
					assertEquals(2, relation.members.size());
				}
			}
			assertEquals(4, osm.relations.size());
			
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	@Test
	@Category(DetailTests.class)
	public void testSample_c2_margePart() {
		CitygmlFileTest.test_doRelationMarge(Paths.get("src/test/resources","sample_c_bldg_6697_op2.gml"));
		
        try {
            OsmDom osm = new OsmDom();
			osm.parse(Paths.get("sample_c_bldg_6697_op2_2.osm").toFile());

			assertNotNull(osm.relations);
			int partCnt = 0;
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					if (relation.getTagValue("source").endsWith("; 13111-bldg-473")) {
					}
					else {
						assertEquals("building", relation.getTagValue("type"));
						assertTrue(relation.getTagValue("source").startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
						assertEquals("東京都大田区南六郷三丁目", relation.getTagValue("addr:full"));
						assertEquals("13111058003", relation.getTagValue("addr:ref"));
						assertEquals("42.7", relation.getTagValue("height"));
						assertEquals("2.81", relation.getTagValue("ele"));
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
									assertEquals("東京都大田区南六郷三丁目", outline.getTagValue("addr:full"));
									assertEquals("13111058003", outline.getTagValue("addr:ref"));
									assertEquals("42.7", outline.getTagValue("height"));
									assertEquals("2.81", outline.getTagValue("ele"));
									assertEquals(8, outline.getTagList().size());
								}
								else if (outline.getTagValue("source").endsWith("; 13111-bldg-386")) {
									assertEquals(outline.getTagValue("type"), ("multipolygon"));
									assertEquals(outline.getTagValue("building"), ("yes"));
									assertEquals(outline.getTagValue("building:levels:underground"), ("1"));
									assertEquals(outline.getTagValue("addr:full"), ("東京都大田区南六郷三丁目"));
									assertEquals(outline.getTagValue("addr:ref"), ("13111058003"));
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
									assertEquals(way.getTagValue("addr:full"), ("東京都大田区南六郷三丁目"));
									assertEquals(way.getTagValue("addr:ref"), ("13111058003"));
									assertEquals(way.getTagValue("height"), ("42.7"));
									assertEquals(way.getTagValue("ele"), ("2.81"));
									assertEquals(way.getTagValue("start_date"), ("1976"));
									assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));
									assertEquals(8, way.getTagList().size());
								}
								else if (way.getTagValue("source").endsWith("; 13111-bldg-386")) {
									assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-386"));
									assertEquals(way.getTagValue("addr:full"), ("東京都大田区南六郷三丁目"));
									assertEquals(way.getTagValue("addr:ref"), ("13111058003"));
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
				}
				else if (type.equals("multipolygon")) {
					if (relation.getTagValue("source").endsWith("; 13111-bldg-473")) {
						assertEquals(relation.getTagValue("type"), ("multipolygon"));
						assertEquals(relation.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));
						assertEquals(relation.getTagValue("addr:full"), ("東京都大田区南六郷三丁目"));
						assertEquals(relation.getTagValue("addr:ref"), ("13111058003"));
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
						assertEquals(relation.getTagValue("addr:full"), ("東京都大田区南六郷三丁目"));
						assertEquals(relation.getTagValue("addr:ref"), ("13111058003"));
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
	public void testSample_cc2_margePart() {
		CitygmlFileTest.test_doRelationMarge(Paths.get("src/test/resources","sample_cc_bldg_6697_op2.gml"));
		
        OsmDom osm = new OsmDom();
        try {
			osm.parse(Paths.get("sample_cc_bldg_6697_op2_2.osm").toFile());

			assertNotNull(osm.relations);
			int partCnt = 0;
			int innerCnt = 0;
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					assertTrue(relation.getTagValue("source").startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					assertEquals(relation.getTagValue("addr:full"), ("東京都大田区大森中一丁目"));
					assertEquals(relation.getTagValue("addr:ref"), ("13111005001"));
					assertNotNull(relation.getTagValue("height"));
					assertEquals(relation.getTagValue("building"), ("yes"));

					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outline")) {
							assertEquals(mem.getType(), ("relation"));
							ElementRelation outline = osm.relations.get(mem.getRef());
							assertNotNull(outline);
							if (outline.getTagValue("source").endsWith("; 13111-bldg-59928")) {
								assertEquals(outline.getTagValue("type"), ("multipolygon"));
								assertEquals(outline.getTagValue("building"), ("yes"));
								assertEquals(outline.getTagValue("building:levels"), ("2"));
								assertEquals(outline.getTagValue("building:levels:underground"), ("2"));
								assertEquals(outline.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-59928"));
								assertEquals(outline.getTagValue("addr:full"), ("東京都大田区大森中一丁目"));
								assertEquals(outline.getTagValue("addr:ref"), ("13111005001"));
								assertEquals(outline.getTagValue("height"), ("7.1"));
								assertEquals(outline.getTagValue("ele"), ("2.48"));
								assertEquals(9, outline.getTagList().size());
							}
							else if (outline.getTagValue("source").endsWith("; 13111-bldg-59955")) {
								assertEquals(outline.getTagValue("type"), ("multipolygon"));
								assertEquals(outline.getTagValue("building"), ("yes"));
								assertEquals(outline.getTagValue("building:levels"), ("3"));
								assertEquals(outline.getTagValue("building:levels:underground"), ("1"));
								assertEquals(outline.getTagValue("addr:full"), ("東京都大田区大森中一丁目"));
								assertEquals(outline.getTagValue("addr:ref"), ("13111005001"));
								assertEquals(outline.getTagValue("height"), ("5.5"));
								assertEquals(outline.getTagValue("ele"), ("2.53"));
								assertEquals(outline.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-59955"));
								assertEquals(9, outline.getTagList().size());
							}
							else if (outline.getTagValue("source").endsWith("; 13111-bldg-59680")) {
								assertEquals(outline.getTagValue("type"), ("multipolygon"));
								assertEquals(outline.getTagValue("building"), ("yes"));
								assertEquals(outline.getTagValue("addr:full"), ("東京都大田区大森中一丁目"));
								assertEquals(outline.getTagValue("addr:ref"), ("13111005001"));
								assertEquals(outline.getTagValue("height"), ("6.8"));
								assertEquals(outline.getTagValue("ele"), ("2.54"));
								assertEquals(outline.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-59680"));
								assertEquals(7, outline.getTagList().size());
							}
						}
						if (mem.getRole().equals("part")) {
							partCnt++;
							assertEquals(mem.getType(), ("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							if (way.getTagValue("source").endsWith("; 13111-bldg-59928")) {
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels"), ("2"));
								assertEquals(way.getTagValue("building:levels:underground"), ("2"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区大森中一丁目"));
								assertEquals(way.getTagValue("addr:ref"), ("13111005001"));
								assertEquals(way.getTagValue("height"), ("7.1"));
								assertEquals(way.getTagValue("ele"), ("2.48"));
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-59928"));
								assertEquals(8, way.getTagList().size());
							}
							else if (way.getTagValue("source").endsWith("; 13111-bldg-59955")) {
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-59955"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区大森中一丁目"));
								assertEquals(way.getTagValue("addr:ref"), ("13111005001"));
								assertEquals(way.getTagValue("height"), ("5.5"));
								assertEquals("2.53", way.getTagValue("ele"));
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels"), ("3"));
								assertEquals(way.getTagValue("building:levels:underground"), ("1"));
								assertEquals(8, way.getTagList().size());
							}
							else if (way.getTagValue("source").endsWith("; 13111-bldg-59680")) {
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区大森中一丁目"));
								assertEquals(way.getTagValue("addr:ref"), ("13111005001"));
								assertEquals(way.getTagValue("height"), ("6.8"));
								assertEquals("2.54", way.getTagValue("ele"));
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-59680"));
								assertEquals(6, way.getTagList().size());
							}
						}
					}
				}
				else if (type.equals("multipolygon")) {
					assertEquals(relation.getTagValue("type"), ("multipolygon"));
					assertTrue(relation.getTagValue("source").startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					assertEquals(relation.getTagValue("addr:full"), ("東京都大田区大森中一丁目"));
					assertEquals(relation.getTagValue("addr:ref"), ("13111005001"));
					assertNotNull(relation.getTagValue("height"));
					assertEquals("yes", relation.getTagValue("building"));

					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outer")) {
							assertEquals(mem.getType(), ("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							assertTrue(way.getTagValue("source").startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
							assertEquals(1, way.getTagList().size());
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
	
	@Test
	@Category(DetailTests.class)
	public void testSample_c3_removeSinglePart() {
		CitygmlFileTest.test_doRemoveSinglePart(Paths.get("src/test/resources","sample_c_bldg_6697_op2.gml"));
		
        OsmDom osm = new OsmDom();
        try {
			osm.parse(Paths.get("sample_c_bldg_6697_op2_3.osm").toFile());

			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697", relation.getTagValue("source"));
					assertEquals(relation.getTagValue("addr:full"), ("東京都大田区南六郷三丁目"));
					assertEquals(relation.getTagValue("addr:ref"), ("13111058003"));
					assertEquals(relation.getTagValue("height"), ("42.7"));
					assertEquals(relation.getTagValue("ele"), ("2.81"));
					assertEquals(relation.getTagValue("building"), ("yes"));
					assertEquals(relation.getTagValue("building:levels"), ("2"));
					assertEquals(relation.getTagValue("building:levels:underground"), ("1"));

					int outlineCnt = 0;
					int partCnt = 0;
					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outline")) {
							outlineCnt++;
						}
						if (mem.getRole().equals("part")) {
							partCnt++;
							assertEquals(mem.getType(), ("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							if (way.getTagValue("source").endsWith("; 13111-bldg-473")) {
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels"), ("2"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区南六郷三丁目"));
								assertEquals(way.getTagValue("addr:ref"), ("13111058003"));
								assertEquals(way.getTagValue("height"), ("42.7"));
								assertEquals(way.getTagValue("ele"), ("2.81"));
								assertEquals(way.getTagValue("start_date"), ("1976"));								
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));
								assertEquals(8, way.getTagList().size());
							}
							else if (way.getTagValue("source").endsWith("; 13111-bldg-386")) {
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-386"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区南六郷三丁目"));
								assertEquals(way.getTagValue("addr:ref"), ("13111058003"));
								assertEquals(way.getTagValue("height"), ("7.3"));
								assertEquals(way.getTagValue("ele"), ("2.81"));
								assertEquals(way.getTagValue("start_date"), ("2003"));
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels:underground"), ("1"));
								assertEquals(8, way.getTagList().size());
							}
						}
					}
					assertEquals(0, outlineCnt);
					assertEquals(2, partCnt);
					assertEquals(2, relation.members.size());
				}
			}
			assertEquals(1, osm.relations.size());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	
	@Test
	@Category(DetailTests.class)
	public void testSample_cc3_removeSinglePart() {
		CitygmlFileTest.test_doRemoveSinglePart(Paths.get("src/test/resources","sample_cc_bldg_6697_op2.gml"));
		
        OsmDom osm = new OsmDom();
        try {
			osm.parse(Paths.get("sample_cc_bldg_6697_op2_3.osm").toFile());

			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697", relation.getTagValue("source"));
					assertEquals(relation.getTagValue("addr:full"), ("東京都大田区大森中一丁目"));
					assertEquals(relation.getTagValue("addr:ref"), ("13111005001"));
					assertNotNull(relation.getTagValue("height"));
					assertEquals(relation.getTagValue("building"), ("yes"));
					assertEquals(relation.getTagValue("building:levels"), ("3"));
					assertEquals(relation.getTagValue("building:levels:underground"), ("2"));

					int outlineCnt = 0;
					int partCnt = 0;
					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outline")) {
							outlineCnt++;
						}
						if (mem.getRole().equals("part")) {
							partCnt++;
							assertEquals(mem.getType(), ("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							if (way.getTagValue("source").endsWith("; 13111-bldg-59928")) {
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels"), ("2"));
								assertEquals(way.getTagValue("building:levels:underground"), ("2"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区大森中一丁目"));
								assertEquals(way.getTagValue("addr:ref"), ("13111005001"));
								assertEquals(way.getTagValue("height"), ("7.1"));
								assertEquals(way.getTagValue("ele"), ("2.48"));
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-59928"));
								assertEquals(8, way.getTagList().size());
							}
							else if (way.getTagValue("source").endsWith("; 13111-bldg-59955")) {
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-59955"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区大森中一丁目"));
								assertEquals(way.getTagValue("addr:ref"), ("13111005001"));
								assertEquals(way.getTagValue("height"), ("5.5"));
								assertEquals(way.getTagValue("ele"), ("2.53"));
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels"), ("3"));
								assertEquals(way.getTagValue("building:levels:underground"), ("1"));
								assertEquals(8, way.getTagList().size());
							}
							else if (way.getTagValue("source").endsWith("; 13111-bldg-59680")) {
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-59680"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区大森中一丁目"));
								assertEquals(way.getTagValue("addr:ref"), ("13111005001"));
								assertEquals(way.getTagValue("height"), ("6.8"));
								assertEquals(way.getTagValue("ele"), ("2.54"));
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(6, way.getTagList().size());
							}
						}
					}
					assertEquals(0, outlineCnt);
					assertEquals(3, partCnt);
					assertEquals(3, relation.members.size());
				}
			}
			assertEquals(1, osm.relations.size());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	@Test
	@Category(DetailTests.class)
	public void testSample_c4_createOutline() {
		CitygmlFileTest.test4_doCreateOutline(Paths.get("src/test/resources","sample_c_bldg_6697_op2.gml"));
		
        OsmDom osm = new OsmDom();
        try {
			osm.parse(Paths.get("sample_c_bldg_6697_op2_4.osm").toFile());

			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				if (relation.isBuilding()) {
					assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697", relation.getTagValue("source"));
					assertEquals(relation.getTagValue("addr:full"), ("東京都大田区南六郷三丁目"));
					assertEquals(relation.getTagValue("addr:ref"), ("13111058003"));
					assertEquals(relation.getTagValue("height"), ("42.7"));
					assertEquals(relation.getTagValue("ele"), ("2.81"));
					assertEquals(relation.getTagValue("building"), ("yes"));
					assertEquals(relation.getTagValue("building:levels"), ("2"));
					assertEquals(relation.getTagValue("building:levels:underground"), ("1"));

					int outlineCnt = 0;
					int partCnt = 0;
					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outline")) {
							outlineCnt++;
							assertEquals(mem.getType(), ("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							assertEquals(way.getTagValue("building"), ("yes"));
							assertEquals(way.getTagValue("building:levels"), ("2"));
							assertEquals(way.getTagValue("building:levels:underground"), ("1"));
							assertEquals(way.getTagValue("addr:full"), ("東京都大田区南六郷三丁目"));
							assertEquals(way.getTagValue("addr:ref"), ("13111058003"));
							assertEquals(way.getTagValue("height"), ("42.7"));
							assertEquals(way.getTagValue("ele"), ("2.81"));
							assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
							assertEquals(8, way.getTagList().size());
						}
						if (mem.getRole().equals("part")) {
							partCnt++;
							assertEquals(mem.getType(), ("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							if (way.getTagValue("source").endsWith("; 13111-bldg-473")) {
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels"), ("2"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区南六郷三丁目"));
								assertEquals(way.getTagValue("addr:ref"), ("13111058003"));
								assertEquals(way.getTagValue("height"), ("42.7"));
								assertEquals(way.getTagValue("ele"), ("2.81"));
								assertEquals(way.getTagValue("start_date"), ("1976"));
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));
								assertEquals(8, way.getTagList().size());
							}
							else if (way.getTagValue("source").endsWith("; 13111-bldg-386")) {
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-386"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区南六郷三丁目"));
								assertEquals(way.getTagValue("addr:ref"), ("13111058003"));
								assertEquals(way.getTagValue("height"), ("7.3"));
								assertEquals(way.getTagValue("ele"), ("2.81"));
								assertEquals(way.getTagValue("start_date"), ("2003"));
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels:underground"), ("1"));
								assertEquals(8, way.getTagList().size());
							}
						}
					}
					assertEquals(1, outlineCnt);
					assertEquals(2, partCnt);
					assertEquals(3, relation.members.size());
				}
			}
			assertEquals(1, osm.relations.size());
			
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}


	@Test
	@Category(DetailTests.class)
	public void testSample_cc4_createOutline() {
		CitygmlFileTest.test4_doCreateOutline(Paths.get("src/test/resources","sample_cc_bldg_6697_op2.gml"));
		
        OsmDom osm = new OsmDom();
        try {
			osm.parse(Paths.get("sample_cc_bldg_6697_op2_4.osm").toFile());

			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					assertEquals("building", relation.getTagValue("type"));
					assertEquals("yes", relation.getTagValue("building"));
					assertEquals(relation.getTagValue("building:levels"), ("3"));
					assertEquals(relation.getTagValue("building:levels:underground"), ("2"));
					assertEquals(relation.getTagValue("height"), ("7.1"));
					assertEquals(relation.getTagValue("ele"), ("2.48"));
					assertEquals(relation.getTagValue("addr:full"), ("東京都大田区大森中一丁目"));
					assertEquals(relation.getTagValue("addr:ref"), ("13111005001"));
					assertEquals(relation.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					assertEquals(9, relation.getTagList().size());
					
					int outlineCnt = 0;
					int partCnt = 0;
					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outline")) {
							outlineCnt++;
							assertEquals(mem.getType(), ("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
							assertEquals(way.getTagValue("addr:full"), ("東京都大田区大森中一丁目"));
							assertEquals(way.getTagValue("addr:ref"), ("13111005001"));
							assertEquals(way.getTagValue("height"), ("7.1"));
							assertEquals(way.getTagValue("ele"), ("2.48"));
							assertEquals(way.getTagValue("building"), ("yes"));
							assertEquals(way.getTagValue("building:levels"), ("3"));
							assertEquals(way.getTagValue("building:levels:underground"), ("2"));
							assertEquals(8, way.getTagList().size());
						}
						if (mem.getRole().equals("part")) {
							partCnt++;
							assertEquals(mem.getType(), ("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							assertTrue(way.getTagValue("source").startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-"));
							assertEquals(way.getTagValue("addr:full"), ("東京都大田区大森中一丁目"));
							assertEquals(way.getTagValue("addr:ref"), ("13111005001"));
							assertNotNull(way.getTagValue("height"));
							assertNotNull(way.getTagValue("ele"));
							assertEquals(way.getTagValue("building:part"), ("yes"));
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

	@Test
	public void testSample_c() {
		CitygmlFileTest.test(Paths.get("src/test/resources","sample_c_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.parse(Paths.get("sample_c_bldg_6697_op2.osm").toFile());
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
					assertEquals("2.81", relation.getTagValue("ele"));
					assertEquals("東京都大田区南六郷三丁目", relation.getTagValue("addr:full"));
					assertEquals("13111058003", relation.getTagValue("addr:ref"));
					assertEquals(relation.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					int outlineCnt = 0;
					int partCnt = 0;
					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outline")) {
							outlineCnt++;
							assertEquals(mem.getType(), ("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
							assertEquals(way.getTagValue("addr:full"), ("東京都大田区南六郷三丁目"));
							assertEquals(way.getTagValue("addr:ref"), ("13111058003"));
							assertEquals(way.getTagValue("height"), ("42.7"));
							assertEquals("2.81", way.getTagValue("ele"));
							assertEquals(way.getTagValue("building"), ("yes"));
							assertEquals(way.getTagValue("building:levels"), ("2"));
							assertEquals("1", way.getTagValue("building:levels:underground"));
							assertEquals(8, way.getTagList().size());
						}
						if (mem.getRole().equals("part")) {
							partCnt++;
							assertEquals(mem.getType(), ("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							if (way.getTagValue("source").endsWith("; 13111-bldg-473")) {
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区南六郷三丁目"));
								assertEquals(way.getTagValue("addr:ref"), ("13111058003"));
								assertEquals(way.getTagValue("height"), ("42.7"));
								assertEquals("2.81", way.getTagValue("ele"));
								assertEquals(way.getTagValue("start_date"), ("1976"));
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(8, way.getTagList().size());
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
					assertEquals(relation.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					assertEquals(5, relation.members.size());
					int outerCnt = 0;
					int innerCnt = 0;
					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outer")) {
							outerCnt++;
							assertEquals(mem.getType(), ("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							assertEquals(1, way.getTagList().size());
							TagBean source = way.getTag("source");
							assertEquals(source.v, ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
						}
						if (mem.getRole().equals("inner")) {
							innerCnt++;
							assertEquals(mem.getType(), ("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							assertEquals(1, way.getTagList().size());
							TagBean source = way.getTag("source");
							assertEquals(source.v, startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; BLD_"));
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
		CitygmlFileTest.test(Paths.get("src/test/resources","sample_cc_bldg_6697_op2.gml"));
	    OsmDom osm = new OsmDom();
	    try {
			osm.parse(Paths.get("sample_cc_bldg_6697_op2.osm").toFile());
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
					assertEquals("2.48", relation.getTagValue("ele"));
					assertEquals("東京都大田区大森中一丁目", relation.getTagValue("addr:full"));
					assertEquals("13111005001", relation.getTagValue("addr:ref"));
					assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697", relation.getTagValue("source"));
					assertEquals(9, relation.getTagList().size());
					
					int outlineCnt = 0;
					int partCnt = 0;
					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outline")) {
							outlineCnt++;
							assertEquals(mem.getType(), ("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
							assertEquals(way.getTagValue("addr:full"), ("東京都大田区大森中一丁目"));
							assertEquals(way.getTagValue("addr:ref"), ("13111005001"));
							assertEquals(way.getTagValue("height"), ("7.1"));
							assertEquals("2.48", way.getTagValue("ele"));
							assertEquals(way.getTagValue("building"), ("yes"));
							assertEquals(way.getTagValue("building:levels"), ("3"));
							assertEquals(way.getTagValue("building:levels:underground"), ("2"));
							assertEquals(8, way.getTagList().size());
						}
						if (mem.getRole().equals("part")) {
							partCnt++;
							assertEquals(mem.getType(), ("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							assertTrue(way.getTagValue("source").startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-"));
							assertEquals(way.getTagValue("addr:full"), ("東京都大田区大森中一丁目"));
							assertEquals(way.getTagValue("addr:ref"), ("13111005001"));
							assertNotNull(way.getTagValue("height"));
							assertNotNull(way.getTagValue("ele"));
							assertEquals(way.getTagValue("building:part"), ("yes"));
							assertTrue(way.getTagList().size() >= 6);
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
