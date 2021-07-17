package osm.surveyor.osm.api;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.nio.file.Paths;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.ElementMember;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementTag;
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
						assertThat(relation.getTagValue("type"), is("building"));
						assertThat(relation.getTagValue("building"), is("yes"));
						assertThat(relation.getTagValue("building:levels"), is("2"));
						assertThat(relation.getTagValue("height"), is("42.7"));
						assertThat(relation.getTagValue("ele"), is("2.81"));
						assertThat(relation.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
						assertThat(relation.getTagValue("addr:ref"), is("13111058003"));
						assertThat(relation.getTagValue("start_date"), is("1976"));
						assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));

						int outlineCnt = 0;
						int partCnt = 0;
						for (ElementMember mem : relation.members) {
							if (mem.role.equals("outline")) {
								outlineCnt++;
								assertThat(mem.type, is("relation"));
								ElementRelation outline = osm.relations.get(mem.ref);
								assertNotNull(outline);
								assertThat(outline.getTagValue("type"), is("multipolygon"));
								assertThat(outline.getTagValue("building"), is("yes"));
								assertThat(outline.getTagValue("building:levels"), is("2"));
								assertThat(outline.getTagValue("height"), is("42.7"));
								assertThat(outline.getTagValue("ele"), is("2.81"));
								assertThat(outline.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
								assertThat(outline.getTagValue("addr:ref"), is("13111058003"));
								assertThat(outline.getTagValue("start_date"), is("1976"));
								assertThat(outline.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));
								assertThat(outline.tags.size(), is(9));
							}
							if (mem.role.equals("part")) {
								partCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertNotNull(way);
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.getTagValue("building:levels"), is("2"));
								assertThat(way.getTagValue("height"), is("42.7"));
								assertThat(way.getTagValue("ele"), is("2.81"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
								assertThat(way.getTagValue("addr:ref"), is("13111058003"));
								assertThat(way.getTagValue("start_date"), is("1976"));
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));
								assertThat(way.tags.size(), is(8));
							}
						}
						assertThat(outlineCnt, is(1));
						assertThat(partCnt, is(1));
					}
					else if (relation.getTagValue("source").endsWith("; 13111-bldg-386")) {
						assertThat(relation.getTagValue("type"), is("building"));
						assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-386"));
						assertThat(relation.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
						assertThat(relation.getTagValue("addr:ref"), is("13111058003"));
						assertThat(relation.getTagValue("height"), is("7.3"));
						assertThat(relation.getTagValue("ele"), is("2.81"));
						assertThat(relation.getTagValue("start_date"), is("2003"));
						assertThat(relation.getTagValue("building"), is("yes"));
						assertThat(relation.getTagValue("building:levels:underground"), is("1"));

						int outlineCnt = 0;
						int partCnt = 0;
						for (ElementMember mem : relation.members) {
							if (mem.role.equals("outline")) {
								outlineCnt++;
								assertThat(mem.type, is("relation"));
								ElementRelation outline = osm.relations.get(mem.ref);
								assertNotNull(outline);
								assertThat(outline.getTagValue("type"), is("multipolygon"));
								assertThat(outline.getTagValue("building"), is("yes"));
								assertThat(outline.getTagValue("building:levels:underground"), is("1"));
								assertThat(outline.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
								assertThat(outline.getTagValue("addr:ref"), is("13111058003"));
								assertThat(outline.getTagValue("height"), is("7.3"));
								assertThat(outline.getTagValue("ele"), is("2.81"));
								assertThat(outline.getTagValue("start_date"), is("2003"));
								assertThat(outline.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-386"));
								assertThat(outline.tags.size(), is(9));
							}
							if (mem.role.equals("part")) {
								partCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertNotNull(way);
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-386"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
								assertThat(way.getTagValue("addr:ref"), is("13111058003"));
								assertThat(way.getTagValue("height"), is("7.3"));
								assertThat(way.getTagValue("ele"), is("2.81"));
								assertThat(way.getTagValue("start_date"), is("2003"));
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.getTagValue("building:levels:underground"), is("1"));
								assertThat(way.tags.size(), is(8));
							}
						}
						assertThat(outlineCnt, is(1));
						assertThat(partCnt, is(1));
					}
					assertThat(relation.members.size(), is(2));
				}
			}
			assertThat(osm.relations.size(), is(4));
			
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
			int outlineCnt = 0;
			int partCnt = 0;
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					if (relation.getTagValue("source").endsWith("; 13111-bldg-473")) {
					}
					else {
						assertThat(relation.getTagValue("type"), is("building"));
						assertThat(relation.getTagValue("source"), startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
						assertThat(relation.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
						assertThat(relation.getTagValue("addr:ref"), is("13111058003"));
						assertThat(relation.getTagValue("height"), is("42.7"));
						assertThat(relation.getTagValue("ele"), is("2.81"));
						assertThat(relation.getTagValue("start_date"), is("1976"));
						assertThat(relation.getTagValue("building"), is("yes"));
						assertThat(relation.getTagValue("building:levels"), is("2"));
						assertThat(relation.getTagValue("building:levels:underground"), is("1"));

						for (ElementMember mem : relation.members) {
							if (mem.role.equals("outline")) {
								outlineCnt++;
								assertThat(mem.type, is("relation"));
								ElementRelation outline = osm.relations.get(mem.ref);
								assertNotNull(outline);
								if (outline.getTagValue("source").endsWith("; 13111-bldg-473")) {
									assertThat(outline.getTagValue("type"), is("multipolygon"));
									assertThat(outline.getTagValue("building"), is("yes"));
									assertThat(outline.getTagValue("building:levels"), is("2"));
									assertThat(outline.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));
									assertThat(outline.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
									assertThat(outline.getTagValue("addr:ref"), is("13111058003"));
									assertThat(outline.getTagValue("height"), is("42.7"));
									assertThat(outline.getTagValue("ele"), is("2.81"));
									assertThat(outline.getTagValue("start_date"), is("1976"));
									assertThat(outline.tags.size(), is(9));
								}
								else if (outline.getTagValue("source").endsWith("; 13111-bldg-386")) {
									assertThat(outline.getTagValue("type"), is("multipolygon"));
									assertThat(outline.getTagValue("building"), is("yes"));
									assertThat(outline.getTagValue("building:levels:underground"), is("1"));
									assertThat(outline.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
									assertThat(outline.getTagValue("addr:ref"), is("13111058003"));
									assertThat(outline.getTagValue("height"), is("7.3"));
									assertThat(outline.getTagValue("ele"), is("2.81"));
									assertThat(outline.getTagValue("start_date"), is("2003"));
									assertThat(outline.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-386"));
									assertThat(outline.tags.size(), is(9));
								}
							}
							if (mem.role.equals("part")) {
								partCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertNotNull(way);
								if (way.getTagValue("source").endsWith("; 13111-bldg-473")) {
									assertThat(way.getTagValue("building:part"), is("yes"));
									assertThat(way.getTagValue("building:levels"), is("2"));
									assertThat(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
									assertThat(way.getTagValue("addr:ref"), is("13111058003"));
									assertThat(way.getTagValue("height"), is("42.7"));
									assertThat(way.getTagValue("ele"), is("2.81"));
									assertThat(way.getTagValue("start_date"), is("1976"));
									assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));
									assertThat(way.tags.size(), is(8));
								}
								else if (way.getTagValue("source").endsWith("; 13111-bldg-386")) {
									assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-386"));
									assertThat(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
									assertThat(way.getTagValue("addr:ref"), is("13111058003"));
									assertThat(way.getTagValue("height"), is("7.3"));
									assertThat(way.getTagValue("ele"), is("2.81"));
									assertThat(way.getTagValue("start_date"), is("2003"));
									assertThat(way.getTagValue("building:part"), is("yes"));
									assertThat(way.getTagValue("building:levels:underground"), is("1"));
									assertThat(way.tags.size(), is(8));
								}
							}
						}
					}
				}
				else if (type.equals("multipolygon")) {
					if (relation.getTagValue("source").endsWith("; 13111-bldg-473")) {
						assertThat(relation.getTagValue("type"), is("multipolygon"));
						assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));
						assertThat(relation.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
						assertThat(relation.getTagValue("addr:ref"), is("13111058003"));
						assertThat(relation.getTagValue("height"), is("42.7"));
						assertThat(relation.getTagValue("ele"), is("2.81"));
						assertThat(relation.getTagValue("start_date"), is("1976"));
						assertThat(relation.getTagValue("building"), is("yes"));

						int outerCnt = 0;
						int innerCnt = 0;
						for (ElementMember mem : relation.members) {
							if (mem.role.equals("outer")) {
								outerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertNotNull(way);
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));
								assertThat(way.tags.size(), is(1));
							}
							if (mem.role.equals("inner")) {
								innerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertNotNull(way);
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));
								assertThat(way.tags.size(), is(1));
							}
						}
						assertThat(outerCnt, is(1));
						assertThat(innerCnt, is(0));
						assertThat(relation.members.size(), is(1));
					}
					else if (relation.getTagValue("source").endsWith("; 13111-bldg-386")) {
						assertThat(relation.getTagValue("type"), is("multipolygon"));
						assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-386"));
						assertThat(relation.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
						assertThat(relation.getTagValue("addr:ref"), is("13111058003"));
						assertThat(relation.getTagValue("height"), is("7.3"));
						assertThat(relation.getTagValue("ele"), is("2.81"));
						assertThat(relation.getTagValue("start_date"), is("2003"));
						assertThat(relation.getTagValue("building"), is("yes"));
						assertThat(relation.getTagValue("building:levels:underground"), is("1"));

						int outerCnt = 0;
						int innerCnt = 0;
						for (ElementMember mem : relation.members) {
							if (mem.role.equals("outer")) {
								outerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertNotNull(way);
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-386"));
								assertThat(way.tags.size(), is(1));
							}
							if (mem.role.equals("inner")) {
								innerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertNotNull(way);
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-386"));
								assertThat(way.tags.size(), is(1));
							}
						}
						assertThat(outerCnt, is(1));
						assertThat(innerCnt, is(0));
						assertThat(relation.members.size(), is(1));
					}
				}
			}
			if (outlineCnt < 2) {
				fail();
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
			int outlineCnt = 0;
			int partCnt = 0;
			int outerCnt = 0;
			int innerCnt = 0;
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					assertThat(relation.getTagValue("type"), is("building"));
					assertThat(relation.getTagValue("source"), startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					assertThat(relation.getTagValue("addr:full"), is("東京都大田区大森中一丁目"));
					assertThat(relation.getTagValue("addr:ref"), is("13111005001"));
					assertNotNull(relation.getTagValue("height"));
					assertThat(relation.getTagValue("building"), is("yes"));

					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outline")) {
							outlineCnt++;
							assertThat(mem.type, is("relation"));
							ElementRelation outline = osm.relations.get(mem.ref);
							assertNotNull(outline);
							if (outline.getTagValue("source").endsWith("; 13111-bldg-59928")) {
								assertThat(outline.getTagValue("type"), is("multipolygon"));
								assertThat(outline.getTagValue("building"), is("yes"));
								assertThat(outline.getTagValue("building:levels"), is("2"));
								assertThat(outline.getTagValue("building:levels:underground"), is("2"));
								assertThat(outline.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-59928"));
								assertThat(outline.getTagValue("addr:full"), is("東京都大田区大森中一丁目"));
								assertThat(outline.getTagValue("addr:ref"), is("13111005001"));
								assertThat(outline.getTagValue("height"), is("7.1"));
								assertThat(outline.getTagValue("ele"), is("2.48"));
								assertThat(outline.tags.size(), is(9));
							}
							else if (outline.getTagValue("source").endsWith("; 13111-bldg-59955")) {
								assertThat(outline.getTagValue("type"), is("multipolygon"));
								assertThat(outline.getTagValue("building"), is("yes"));
								assertThat(outline.getTagValue("building:levels"), is("3"));
								assertThat(outline.getTagValue("building:levels:underground"), is("1"));
								assertThat(outline.getTagValue("addr:full"), is("東京都大田区大森中一丁目"));
								assertThat(outline.getTagValue("addr:ref"), is("13111005001"));
								assertThat(outline.getTagValue("height"), is("5.5"));
								assertThat(outline.getTagValue("ele"), is("2.53"));
								assertThat(outline.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-59955"));
								assertThat(outline.tags.size(), is(9));
							}
							else if (outline.getTagValue("source").endsWith("; 13111-bldg-59680")) {
								assertThat(outline.getTagValue("type"), is("multipolygon"));
								assertThat(outline.getTagValue("building"), is("yes"));
								assertThat(outline.getTagValue("addr:full"), is("東京都大田区大森中一丁目"));
								assertThat(outline.getTagValue("addr:ref"), is("13111005001"));
								assertThat(outline.getTagValue("height"), is("6.8"));
								assertThat(outline.getTagValue("ele"), is("2.54"));
								assertThat(outline.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-59680"));
								assertThat(outline.tags.size(), is(7));
							}
						}
						if (mem.role.equals("part")) {
							partCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertNotNull(way);
							if (way.getTagValue("source").endsWith("; 13111-bldg-59928")) {
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.getTagValue("building:levels"), is("2"));
								assertThat(way.getTagValue("building:levels:underground"), is("2"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区大森中一丁目"));
								assertThat(way.getTagValue("addr:ref"), is("13111005001"));
								assertThat(way.getTagValue("height"), is("7.1"));
								assertThat(way.getTagValue("ele"), is("2.48"));
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-59928"));
								assertThat(way.tags.size(), is(8));
							}
							else if (way.getTagValue("source").endsWith("; 13111-bldg-59955")) {
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-59955"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区大森中一丁目"));
								assertThat(way.getTagValue("addr:ref"), is("13111005001"));
								assertThat(way.getTagValue("height"), is("5.5"));
								assertEquals("2.53", way.getTagValue("ele"));
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.getTagValue("building:levels"), is("3"));
								assertThat(way.getTagValue("building:levels:underground"), is("1"));
								assertThat(way.tags.size(), is(8));
							}
							else if (way.getTagValue("source").endsWith("; 13111-bldg-59680")) {
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区大森中一丁目"));
								assertThat(way.getTagValue("addr:ref"), is("13111005001"));
								assertThat(way.getTagValue("height"), is("6.8"));
								assertEquals("2.54", way.getTagValue("ele"));
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-59680"));
								assertThat(way.tags.size(), is(6));
							}
						}
					}
				}
				else if (type.equals("multipolygon")) {
					assertThat(relation.getTagValue("type"), is("multipolygon"));
					assertThat(relation.getTagValue("source"), startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					assertThat(relation.getTagValue("addr:full"), is("東京都大田区大森中一丁目"));
					assertThat(relation.getTagValue("addr:ref"), is("13111005001"));
					assertNotNull(relation.getTagValue("height"));
					assertEquals("yes", relation.getTagValue("building"));

					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outer")) {
							outerCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertNotNull(way);
							assertThat(way.getTagValue("source"), startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
							assertThat(way.tags.size(), is(1));
						}
						if (mem.role.equals("inner")) {
							innerCnt++;
						}
					}
					assertEquals(1, relation.members.size());
				}
			}
			assertEquals(3, outerCnt);
			assertEquals(0, innerCnt);
			assertEquals(3, outlineCnt);
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
					assertThat(relation.getTagValue("type"), is("building"));
					assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					assertThat(relation.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
					assertThat(relation.getTagValue("addr:ref"), is("13111058003"));
					assertThat(relation.getTagValue("height"), is("42.7"));
					assertThat(relation.getTagValue("ele"), is("2.81"));
					assertThat(relation.getTagValue("start_date"), is("1976"));
					assertThat(relation.getTagValue("building"), is("yes"));
					assertThat(relation.getTagValue("building:levels"), is("2"));
					assertThat(relation.getTagValue("building:levels:underground"), is("1"));

					int outlineCnt = 0;
					int partCnt = 0;
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outline")) {
							outlineCnt++;
						}
						if (mem.role.equals("part")) {
							partCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertNotNull(way);
							if (way.getTagValue("source").endsWith("; 13111-bldg-473")) {
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.getTagValue("building:levels"), is("2"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
								assertThat(way.getTagValue("addr:ref"), is("13111058003"));
								assertThat(way.getTagValue("height"), is("42.7"));
								assertThat(way.getTagValue("ele"), is("2.81"));
								assertThat(way.getTagValue("start_date"), is("1976"));								
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));
								assertThat(way.tags.size(), is(8));
							}
							else if (way.getTagValue("source").endsWith("; 13111-bldg-386")) {
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-386"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
								assertThat(way.getTagValue("addr:ref"), is("13111058003"));
								assertThat(way.getTagValue("height"), is("7.3"));
								assertThat(way.getTagValue("ele"), is("2.81"));
								assertThat(way.getTagValue("start_date"), is("2003"));
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.getTagValue("building:levels:underground"), is("1"));
								assertThat(way.tags.size(), is(8));
							}
						}
					}
					assertThat(outlineCnt, is(0));
					assertThat(partCnt, is(2));
					assertThat(relation.members.size(), is(2));
				}
			}
			assertThat(osm.relations.size(), is(1));
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
					assertThat(relation.getTagValue("type"), is("building"));
					assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					assertThat(relation.getTagValue("addr:full"), is("東京都大田区大森中一丁目"));
					assertThat(relation.getTagValue("addr:ref"), is("13111005001"));
					assertNotNull(relation.getTagValue("height"));
					assertThat(relation.getTagValue("building"), is("yes"));
					assertThat(relation.getTagValue("building:levels"), is("3"));
					assertThat(relation.getTagValue("building:levels:underground"), is("2"));

					int outlineCnt = 0;
					int partCnt = 0;
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outline")) {
							outlineCnt++;
						}
						if (mem.role.equals("part")) {
							partCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertNotNull(way);
							if (way.getTagValue("source").endsWith("; 13111-bldg-59928")) {
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.getTagValue("building:levels"), is("2"));
								assertThat(way.getTagValue("building:levels:underground"), is("2"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区大森中一丁目"));
								assertThat(way.getTagValue("addr:ref"), is("13111005001"));
								assertThat(way.getTagValue("height"), is("7.1"));
								assertThat(way.getTagValue("ele"), is("2.48"));
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-59928"));
								assertThat(way.tags.size(), is(8));
							}
							else if (way.getTagValue("source").endsWith("; 13111-bldg-59955")) {
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-59955"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区大森中一丁目"));
								assertThat(way.getTagValue("addr:ref"), is("13111005001"));
								assertThat(way.getTagValue("height"), is("5.5"));
								assertThat(way.getTagValue("ele"), is("2.53"));
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.getTagValue("building:levels"), is("3"));
								assertThat(way.getTagValue("building:levels:underground"), is("1"));
								assertThat(way.tags.size(), is(8));
							}
							else if (way.getTagValue("source").endsWith("; 13111-bldg-59680")) {
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-59680"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区大森中一丁目"));
								assertThat(way.getTagValue("addr:ref"), is("13111005001"));
								assertThat(way.getTagValue("height"), is("6.8"));
								assertThat(way.getTagValue("ele"), is("2.54"));
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.tags.size(), is(6));
							}
						}
					}
					assertThat(outlineCnt, is(0));
					assertThat(partCnt, is(3));
					assertThat(relation.members.size(), is(3));
				}
			}
			assertThat(osm.relations.size(), is(1));
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
					assertThat(relation.getTagValue("type"), is("building"));
					assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					assertThat(relation.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
					assertThat(relation.getTagValue("addr:ref"), is("13111058003"));
					assertThat(relation.getTagValue("height"), is("42.7"));
					assertThat(relation.getTagValue("ele"), is("2.81"));
					assertThat(relation.getTagValue("start_date"), is("1976"));					
					assertThat(relation.getTagValue("building"), is("yes"));
					assertThat(relation.getTagValue("building:levels"), is("2"));
					assertThat(relation.getTagValue("building:levels:underground"), is("1"));

					int outlineCnt = 0;
					int partCnt = 0;
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outline")) {
							outlineCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertNotNull(way);
							assertThat(way.getTagValue("building"), is("yes"));
							assertThat(way.getTagValue("building:levels"), is("2"));
							assertThat(way.getTagValue("building:levels:underground"), is("1"));
							assertThat(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
							assertThat(way.getTagValue("addr:ref"), is("13111058003"));
							assertThat(way.getTagValue("height"), is("42.7"));
							assertThat(way.getTagValue("ele"), is("2.81"));
							assertThat(way.getTagValue("start_date"), is("1976"));
							assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
							assertThat(way.tags.size(), is(9));
						}
						if (mem.role.equals("part")) {
							partCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertNotNull(way);
							if (way.getTagValue("source").endsWith("; 13111-bldg-473")) {
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.getTagValue("building:levels"), is("2"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
								assertThat(way.getTagValue("addr:ref"), is("13111058003"));
								assertThat(way.getTagValue("height"), is("42.7"));
								assertThat(way.getTagValue("ele"), is("2.81"));
								assertThat(way.getTagValue("start_date"), is("1976"));
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));
								assertThat(way.tags.size(), is(8));
							}
							else if (way.getTagValue("source").endsWith("; 13111-bldg-386")) {
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-386"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
								assertThat(way.getTagValue("addr:ref"), is("13111058003"));
								assertThat(way.getTagValue("height"), is("7.3"));
								assertThat(way.getTagValue("ele"), is("2.81"));
								assertThat(way.getTagValue("start_date"), is("2003"));
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.getTagValue("building:levels:underground"), is("1"));
								assertThat(way.tags.size(), is(8));
							}
						}
					}
					assertThat(outlineCnt, is(1));
					assertThat(partCnt, is(2));
					assertThat(relation.members.size(), is(3));
				}
			}
			assertThat(osm.relations.size(), is(1));
			
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
					assertThat(relation.getTagValue("type"), is("building"));
					assertThat(relation.getTagValue("building"), is("yes"));
					assertThat(relation.getTagValue("building:levels"), is("3"));
					assertThat(relation.getTagValue("building:levels:underground"), is("2"));
					assertThat(relation.getTagValue("height"), is("7.1"));
					assertThat(relation.getTagValue("ele"), is("2.48"));
					assertThat(relation.getTagValue("addr:full"), is("東京都大田区大森中一丁目"));
					assertThat(relation.getTagValue("addr:ref"), is("13111005001"));
					assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					assertThat(relation.tags.size(), is(9));
					
					int outlineCnt = 0;
					int partCnt = 0;
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outline")) {
							outlineCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertNotNull(way);
							assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
							assertThat(way.getTagValue("addr:full"), is("東京都大田区大森中一丁目"));
							assertThat(way.getTagValue("addr:ref"), is("13111005001"));
							assertThat(way.getTagValue("height"), is("7.1"));
							assertThat(way.getTagValue("ele"), is("2.48"));
							assertThat(way.getTagValue("building"), is("yes"));
							assertThat(way.getTagValue("building:levels"), is("3"));
							assertThat(way.getTagValue("building:levels:underground"), is("2"));
							assertThat(way.tags.size(), is(8));
						}
						if (mem.role.equals("part")) {
							partCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertNotNull(way);
							assertThat(way.getTagValue("source"), startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-"));
							assertThat(way.getTagValue("addr:full"), is("東京都大田区大森中一丁目"));
							assertThat(way.getTagValue("addr:ref"), is("13111005001"));
							assertNotNull(way.getTagValue("height"));
							assertNotNull(way.getTagValue("ele"));
							assertThat(way.getTagValue("building:part"), is("yes"));
						}
					}
					assertThat(outlineCnt, is(1));
					assertThat(partCnt, is(3));
					assertThat(relation.members.size(), is(4));
				}
			}
			assertThat(osm.relations.size(), is(1));
			
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
			assertThat(osm.relations.size(), is(1));
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
					assertThat(relation.getTagValue("type"), is("building"));
					assertThat(relation.getTagValue("building"), is("yes"));
					assertThat(relation.getTagValue("building:levels"), is("2"));
					assertThat(relation.getTagValue("building:levels:underground"), is("1"));
					assertThat(relation.getTagValue("height"), is("42.7"));
					assertEquals("2.81", relation.getTagValue("ele"));
					assertThat(relation.getTagValue("start_date"), is("1976"));
					assertThat(relation.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
					assertThat(relation.getTagValue("addr:ref"), is("13111058003"));
					assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					int outlineCnt = 0;
					int partCnt = 0;
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outline")) {
							outlineCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertNotNull(way);
							assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
							assertThat(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
							assertThat(way.getTagValue("addr:ref"), is("13111058003"));
							assertThat(way.getTagValue("height"), is("42.7"));
							assertEquals("2.81", way.getTagValue("ele"));
							assertThat(way.getTagValue("start_date"), is("1976"));
							assertThat(way.getTagValue("building"), is("yes"));
							assertThat(way.getTagValue("building:levels"), is("2"));
							assertThat(way.getTagValue("building:levels:underground"), is("1"));
							assertThat(way.tags.size(), is(9));
						}
						if (mem.role.equals("part")) {
							partCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertNotNull(way);
							if (way.getTagValue("source").endsWith("; 13111-bldg-473")) {
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
								assertThat(way.getTagValue("addr:ref"), is("13111058003"));
								assertThat(way.getTagValue("height"), is("42.7"));
								assertEquals("2.81", way.getTagValue("ele"));
								assertThat(way.getTagValue("start_date"), is("1976"));
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.tags.size(), is(8));
							}
						}
					}
					assertThat(relation.members.size(), is(3));
					assertThat(outlineCnt, is(1));
					assertThat(partCnt, is(2));
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
					assertThat(relation.getTagValue("type"), is("multipolygon"));
					assertThat(relation.getTagValue("building"), is("yes"));
					assertThat(relation.getTagValue("building:levels"), is("2"));
					assertThat(relation.getTagValue("building:levels:underground"), is("1"));
					assertThat(relation.getTagValue("start_date"), is("1976"));
					assertNotNull(relation.getTagValue("height"));
					assertThat(relation.getTagValue("height"), startsWith("32.852000000"));
					assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					assertThat(relation.members.size(), is(5));
					int outerCnt = 0;
					int innerCnt = 0;
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outer")) {
							outerCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertNotNull(way);
							assertThat(way.tags.size(), is(1));
							ElementTag source = way.tags.get("source");
							assertThat(source.v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
						}
						if (mem.role.equals("inner")) {
							innerCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertNotNull(way);
							assertThat(way.tags.size(), is(1));
							ElementTag source = way.tags.get("source");
							assertThat(source.v, startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; BLD_"));
						}
					}
					assertThat(outerCnt, is(1));
					assertThat(innerCnt, is(2));
				}
				else {
					assertThat(type, is("building"));
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
					assertThat(relation.getTagValue("type"), is("building"));
					assertThat(relation.getTagValue("building"), is("yes"));
					assertThat(relation.getTagValue("building:levels"), is("3"));
					assertThat(relation.getTagValue("building:levels:underground"), is("2"));
					assertThat(relation.getTagValue("height"), is("7.1"));
					assertEquals("2.48", relation.getTagValue("ele"));
					assertThat(relation.getTagValue("addr:full"), is("東京都大田区大森中一丁目"));
					assertThat(relation.getTagValue("addr:ref"), is("13111005001"));
					assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					assertThat(relation.tags.size(), is(9));
					
					int outlineCnt = 0;
					int partCnt = 0;
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outline")) {
							outlineCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertNotNull(way);
							assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
							assertThat(way.getTagValue("addr:full"), is("東京都大田区大森中一丁目"));
							assertThat(way.getTagValue("addr:ref"), is("13111005001"));
							assertThat(way.getTagValue("height"), is("7.1"));
							assertEquals("2.48", way.getTagValue("ele"));
							assertThat(way.getTagValue("building"), is("yes"));
							assertThat(way.getTagValue("building:levels"), is("3"));
							assertThat(way.getTagValue("building:levels:underground"), is("2"));
							assertThat(way.tags.size(), is(8));
						}
						if (mem.role.equals("part")) {
							partCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertNotNull(way);
							assertThat(way.getTagValue("source"), startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-"));
							assertThat(way.getTagValue("addr:full"), is("東京都大田区大森中一丁目"));
							assertThat(way.getTagValue("addr:ref"), is("13111005001"));
							assertNotNull(way.getTagValue("height"));
							assertNotNull(way.getTagValue("ele"));
							assertThat(way.getTagValue("building:part"), is("yes"));
							assertThat(way.tags.size() >= 6, is(true));
						}
					}
					assertThat(outlineCnt, is(1));
					assertThat(partCnt, is(3));
					assertThat(relation.members.size(), is(4));
				}
			}
			assertThat(osm.relations.size(), is(1));
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
