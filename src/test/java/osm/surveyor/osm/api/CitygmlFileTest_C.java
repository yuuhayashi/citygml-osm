package osm.surveyor.osm.api;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.nio.file.Paths;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
	public void testSample_c1_parse() {
		CitygmlFileTest.test_doParse(Paths.get(".","sample_c_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.load(Paths.get("sample_c_bldg_6697_op2_1.osm").toFile());

			assertThat(osm.relations, notNullValue());
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertThat(relation, notNullValue());
				String type = relation.tags.get("type").v;
				if (type.equals("building")) {
					if (relation.tags.get("source").v.endsWith("; 13111-bldg-473")) {
						assertThat(relation.tags.get("type").v, is("building"));
						assertThat(relation.tags.get("building").v, is("yes"));
						assertThat(relation.tags.get("height").v, startsWith("40.492"));
						assertThat(relation.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
						assertThat(relation.tags.get("addr:ref").v, is("13111058003"));
						assertThat(relation.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));

						int outlineCnt = 0;
						int partCnt = 0;
						for (ElementMember mem : relation.members) {
							if (mem.role.equals("outline")) {
								outlineCnt++;
								assertThat(mem.type, is("relation"));
								ElementRelation outline = osm.relations.get(mem.ref);
								assertThat(outline, notNullValue());
								assertThat(outline.tags.get("type").v, is("multipolygon"));
								assertThat(outline.tags.get("building").v, is("yes"));
								assertThat(outline.tags.get("height").v, startsWith("40.492"));
								assertThat(outline.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
								assertThat(outline.tags.get("addr:ref").v, is("13111058003"));
								assertThat(outline.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));
								assertThat(outline.tags.size(), is(6));
							}
							if (mem.role.equals("part")) {
								partCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.tags.get("building:part").v, is("yes"));
								assertThat(way.tags.get("height").v, startsWith("40.492"));
								assertThat(way.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
								assertThat(way.tags.get("addr:ref").v, is("13111058003"));
								assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));
								assertThat(way.tags.size(), is(5));
							}
						}
						assertThat(outlineCnt, is(1));
						assertThat(partCnt, is(1));
					}
					else if (relation.tags.get("source").v.endsWith("; 13111-bldg-386")) {
						assertThat(relation.tags.get("type").v, is("building"));
						assertThat(relation.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-386"));
						assertThat(relation.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
						assertThat(relation.tags.get("addr:ref").v, is("13111058003"));
						assertThat(relation.tags.get("height").v, is("6.58"));
						assertThat(relation.tags.get("building").v, is("yes"));

						int outlineCnt = 0;
						int partCnt = 0;
						for (ElementMember mem : relation.members) {
							if (mem.role.equals("outline")) {
								outlineCnt++;
								assertThat(mem.type, is("relation"));
								ElementRelation outline = osm.relations.get(mem.ref);
								assertThat(outline, notNullValue());
								assertThat(outline.tags.get("type").v, is("multipolygon"));
								assertThat(outline.tags.get("building").v, is("yes"));
								assertThat(outline.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
								assertThat(outline.tags.get("addr:ref").v, is("13111058003"));
								assertThat(outline.tags.get("height").v, is("6.58"));
								assertThat(outline.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-386"));
								assertThat(outline.tags.size(), is(6));
							}
							if (mem.role.equals("part")) {
								partCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-386"));
								assertThat(way.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
								assertThat(way.tags.get("addr:ref").v, is("13111058003"));
								assertThat(way.tags.get("height").v, is("6.58"));
								assertThat(way.tags.get("building:part").v, is("yes"));
								assertThat(way.tags.size(), is(5));
							}
						}
						assertThat(outlineCnt, is(1));
						assertThat(partCnt, is(1));
					}
					assertThat(relation.members.size(), is(2));
				}
				else if (type.equals("multipolygon")) {
					if (relation.tags.get("source").v.endsWith("; 13111-bldg-365")) {
						assertThat(relation.tags.get("type").v, is("multipolygon"));
						assertThat(relation.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
						assertThat(relation.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
						assertThat(relation.tags.get("addr:ref").v, is("13111058003"));
						assertThat(relation.tags.get("height").v, is("40.492"));
						assertThat(relation.tags.get("building").v, is("yes"));

						int outerCnt = 0;
						int innerCnt = 0;
						for (ElementMember mem : relation.members) {
							if (mem.role.equals("outer")) {
								outerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
								assertThat(way.tags.size(), is(1));
							}
							if (mem.role.equals("inner")) {
								innerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
								assertThat(way.tags.size(), is(1));
							}
						}
						assertThat(outerCnt, is(1));
						assertThat(innerCnt, is(0));
					}
					else if (relation.tags.get("source").v.endsWith("; 13111-bldg-466")) {
						assertThat(relation.tags.get("type").v, is("multipolygon"));
						assertThat(relation.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
						assertThat(relation.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
						assertThat(relation.tags.get("addr:ref").v, is("13111058003"));
						assertThat(relation.tags.get("height").v, is("6.68"));
						assertThat(relation.tags.get("building").v, is("yes"));

						int outerCnt = 0;
						int innerCnt = 0;
						for (ElementMember mem : relation.members) {
							if (mem.role.equals("outer")) {
								outerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
								assertThat(way.tags.size(), is(1));
							}
							if (mem.role.equals("inner")) {
								innerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
								assertThat(way.tags.size(), is(1));
							}
						}
						assertThat(outerCnt, is(1));
						assertThat(innerCnt, is(0));
					}
					assertThat(relation.members.size(), is(1));
				}
			}
			assertThat(osm.relations.size(), is(4));
			
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testSample_c2_margePart() {
		CitygmlFileTest.test_doRelationMarge(Paths.get(".","sample_c_bldg_6697_op2.gml"));
		
        OsmDom osm = new OsmDom();
        try {
			osm.load(Paths.get("sample_c_bldg_6697_op2_2.osm").toFile());

			assertThat(osm.relations, notNullValue());
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertThat(relation, notNullValue());
				String type = relation.tags.get("type").v;
				if (type.equals("building")) {
					if (relation.tags.get("source").v.endsWith("; 13111-bldg-473")) {
					}
					else {
						assertThat(relation.tags.get("type").v, is("building"));
						assertThat(relation.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
						assertThat(relation.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
						assertThat(relation.tags.get("addr:ref").v, is("13111058003"));
						assertThat(relation.tags.get("height").v, is("40.492"));
						assertThat(relation.tags.get("building").v, is("yes"));

						int outlineCnt = 0;
						int partCnt = 0;
						for (ElementMember mem : relation.members) {
							if (mem.role.equals("outline")) {
								outlineCnt++;
								assertThat(mem.type, is("relation"));
								ElementRelation outline = osm.relations.get(mem.ref);
								assertThat(outline, notNullValue());
								if (outline.tags.get("source").v.endsWith("; 13111-bldg-473")) {
									assertThat(outline.tags.get("type").v, is("multipolygon"));
									assertThat(outline.tags.get("building").v, is("yes"));
									assertThat(outline.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));
									assertThat(outline.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
									assertThat(outline.tags.get("addr:ref").v, is("13111058003"));
									assertThat(outline.tags.get("height").v, is("40.492"));
									assertThat(outline.tags.size(), is(6));
								}
								else if (outline.tags.get("source").v.endsWith("; 13111-bldg-386")) {
									assertThat(outline.tags.get("type").v, is("multipolygon"));
									assertThat(outline.tags.get("building").v, is("yes"));
									assertThat(outline.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
									assertThat(outline.tags.get("addr:ref").v, is("13111058003"));
									assertThat(outline.tags.get("height").v, is("6.58"));
									assertThat(outline.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-386"));
									assertThat(outline.tags.size(), is(6));
								}
							}
							if (mem.role.equals("part")) {
								partCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								if (way.tags.get("source").v.endsWith("; 13111-bldg-473")) {
									assertThat(way.tags.get("building:part").v, is("yes"));
									assertThat(way.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
									assertThat(way.tags.get("addr:ref").v, is("13111058003"));
									assertThat(way.tags.get("height").v, is("40.492"));
									assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));
									assertThat(way.tags.size(), is(5));
								}
								else if (way.tags.get("source").v.endsWith("; 13111-bldg-386")) {
									assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-386"));
									assertThat(way.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
									assertThat(way.tags.get("addr:ref").v, is("13111058003"));
									assertThat(way.tags.get("height").v, is("6.58"));
									assertThat(way.tags.get("building:part").v, is("yes"));
									assertThat(way.tags.size(), is(5));
								}
							}
						}
						assertThat(outlineCnt, is(2));
						assertThat(partCnt, is(2));
						assertThat(relation.members.size(), is(4));
					}
				}
				else if (type.equals("multipolygon")) {
					if (relation.tags.get("source").v.endsWith("; 13111-bldg-473")) {
						assertThat(relation.tags.get("type").v, is("multipolygon"));
						assertThat(relation.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));
						assertThat(relation.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
						assertThat(relation.tags.get("addr:ref").v, is("13111058003"));
						assertThat(relation.tags.get("height").v, is("40.492"));
						assertThat(relation.tags.get("building").v, is("yes"));

						int outerCnt = 0;
						int innerCnt = 0;
						for (ElementMember mem : relation.members) {
							if (mem.role.equals("outer")) {
								outerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));
								assertThat(way.tags.size(), is(1));
							}
							if (mem.role.equals("inner")) {
								innerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));
								assertThat(way.tags.size(), is(1));
							}
						}
						assertThat(outerCnt, is(1));
						assertThat(innerCnt, is(0));
						assertThat(relation.members.size(), is(1));
					}
					else if (relation.tags.get("source").v.endsWith("; 13111-bldg-386")) {
						assertThat(relation.tags.get("type").v, is("multipolygon"));
						assertThat(relation.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-386"));
						assertThat(relation.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
						assertThat(relation.tags.get("addr:ref").v, is("13111058003"));
						assertThat(relation.tags.get("height").v, is("6.58"));
						assertThat(relation.tags.get("building").v, is("yes"));

						int outerCnt = 0;
						int innerCnt = 0;
						for (ElementMember mem : relation.members) {
							if (mem.role.equals("outer")) {
								outerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-386"));
								assertThat(way.tags.size(), is(1));
							}
							if (mem.role.equals("inner")) {
								innerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-386"));
								assertThat(way.tags.size(), is(1));
							}
						}
						assertThat(outerCnt, is(1));
						assertThat(innerCnt, is(0));
						assertThat(relation.members.size(), is(1));
					}
				}
			}
			assertThat(osm.relations.size(), is(4));
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testSample_a4_createOutline() {
		CitygmlFileTest.test_doCreateOutline(Paths.get(".","sample_a_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.load(Paths.get("sample_a_bldg_6697_op2_4.osm").toFile());

			assertThat(osm.relations, notNullValue());
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertThat(relation, notNullValue());
				String type = relation.tags.get("type").v;
				if (type.equals("multipolygon")) {
					if (relation.tags.get("source").v.endsWith("; 13111-bldg-365")) {
						assertThat(relation.tags.get("type").v, is("multipolygon"));
						assertThat(relation.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
						assertThat(relation.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
						assertThat(relation.tags.get("addr:ref").v, is("13111058003"));
						assertThat(relation.tags.get("height").v, is("40.492"));
						assertThat(relation.tags.get("building").v, is("yes"));

						int outerCnt = 0;
						int innerCnt = 0;
						for (ElementMember mem : relation.members) {
							if (mem.role.equals("outer")) {
								outerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
								assertThat(way.tags.size(), is(1));
							}
							if (mem.role.equals("inner")) {
								innerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
								assertThat(way.tags.size(), is(1));
							}
						}
						assertThat(outerCnt, is(1));
						assertThat(innerCnt, is(0));
					}
					else if (relation.tags.get("source").v.endsWith("; 13111-bldg-466")) {
						assertThat(relation.tags.get("type").v, is("multipolygon"));
						assertThat(relation.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
						assertThat(relation.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
						assertThat(relation.tags.get("addr:ref").v, is("13111058003"));
						assertThat(relation.tags.get("height").v, is("6.68"));
						assertThat(relation.tags.get("building").v, is("yes"));

						int outerCnt = 0;
						int innerCnt = 0;
						for (ElementMember mem : relation.members) {
							if (mem.role.equals("outer")) {
								outerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
								assertThat(way.tags.size(), is(1));
							}
							if (mem.role.equals("inner")) {
								innerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
								assertThat(way.tags.size(), is(1));
							}
						}
						assertThat(outerCnt, is(1));
						assertThat(innerCnt, is(0));
					}
					assertThat(relation.members.size(), is(1));
				}
			}
			assertThat(osm.relations.size(), is(2));
			
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testSample_c3_removeSinglePart() {
		CitygmlFileTest.test_doRemoveSinglePart(Paths.get(".","sample_c_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.load(Paths.get("sample_c_bldg_6697_op2_3.osm").toFile());

			assertThat(osm.relations, notNullValue());
			assertThat(osm.relations.size(), is(0));
			
			for (String id : osm.ways.keySet()) {
				ElementWay way = osm.ways.get(id);
				assertThat(way, notNullValue());

				if (way.tags.get("source").v.endsWith("; 13111-bldg-365")) {
					assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
					assertThat(way.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
					assertThat(way.tags.get("addr:ref").v, is("13111058003"));
					assertThat(way.tags.get("height").v, is("40.492"));
					assertThat(way.tags.get("building").v, is("yes"));
					assertThat(way.tags.size(), is(5));
				}
				else if (way.tags.get("source").v.endsWith("; 13111-bldg-466")) {
					assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
					assertThat(way.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
					assertThat(way.tags.get("addr:ref").v, is("13111058003"));
					assertThat(way.tags.get("height").v, is("6.68"));
					assertThat(way.tags.get("building").v, is("yes"));
					assertThat(osm.ways.size(), is(2));
					assertThat(way.tags.size(), is(5));
				}
				assertThat(osm.ways.size(), is(2));
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
	
    
	@Test
	public void testSample_c() {
		CitygmlFileTest.test(Paths.get(".","sample_c_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.load(Paths.get("sample_c_bldg_6697_op2.osm").toFile());
			assertThat(osm.relations.size(), is(1));
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertThat(relation, notNullValue());
				String type = relation.tags.get("type").v;
				if (type.equals("building")) {
					/*
					 	map "building : Relation" as building {
						 type => building
						 building => yes
						 height => 40.492
						 source => MLIT_PLATEAU;\n http://www.opengis.net/def/crs/EPSG/0/6697
						}
					 */
					assertThat(relation.tags.get("type").v, is("building"));
					assertThat(relation.tags.get("building").v, is("yes"));
					assertThat(relation.tags.get("height").v, startsWith("40.492"));
					assertThat(relation.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
					assertThat(relation.tags.get("addr:ref").v, is("13111058003"));
					assertThat(relation.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					int outlineCnt = 0;
					int partCnt = 0;
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outline")) {
							outlineCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertThat(way, notNullValue());
							assertThat(way.tags.get("source").v, startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
							assertThat(way.tags.get("height").v, is("40.492"));
							assertThat(way.tags.get("building").v, is("yes"));
							assertThat(way.tags.size(), is(5));
						}
						if (mem.role.equals("part")) {
							partCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertThat(way, notNullValue());
							ElementTag tag = way.tags.get("source");
							if (tag.v.endsWith("; 13111-bldg-473")) {
								assertThat(tag.v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));
								assertThat(way.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
								assertThat(way.tags.get("addr:ref").v, is("13111058003"));
								assertThat(way.tags.get("height").v, is("40.492"));
								assertThat(way.tags.get("building:part").v, is("yes"));
								assertThat(way.tags.size(), is(5));
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
					assertThat(relation.tags.get("type").v, is("multipolygon"));
					assertThat(relation.tags.get("building").v, is("yes"));
					assertThat(relation.tags.get("height"), notNullValue());
					assertThat(relation.tags.get("height").v, startsWith("32.852000000"));
					assertThat(relation.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					assertThat(relation.members.size(), is(3));
					int outerCnt = 0;
					int innerCnt = 0;
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outer")) {
							outerCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertThat(way, notNullValue());
							assertThat(way.tags.size(), is(1));
							ElementTag source = way.tags.get("source");
							assertThat(source.v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
						}
						if (mem.role.equals("inner")) {
							innerCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertThat(way, notNullValue());
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
}
