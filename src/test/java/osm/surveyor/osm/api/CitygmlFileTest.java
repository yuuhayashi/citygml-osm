package osm.surveyor.osm.api;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import osm.surveyor.citygml.CitygmlFile;
import osm.surveyor.osm.ElementMember;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementTag;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.OsmMargeWay;
import osm.surveyor.osm.marge.BuildingGarbage;
import osm.surveyor.osm.marge.RelationMarge;

public class CitygmlFileTest {

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
	public void testSample_a() {
		test(Paths.get(".","sample_a_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.load(Paths.get("sample_a_bldg_6697_op2.osm").toFile());
			assertThat(osm.relations, notNullValue());
			assertThat(osm.relations.size(), is(0));
			for (String id : osm.ways.keySet()) {
				ElementWay way = osm.ways.get(id);
				assertThat(way, notNullValue());
				assertThat(way.tags.size(), is(5));
				if (way.tags.get("source").v.endsWith("; 13111-bldg-365")) {
					assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
					assertThat(way.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
					assertThat(way.tags.get("addr:ref").v, is("13111058003"));
					assertThat(way.tags.get("height").v, is("5.127"));
					assertThat(way.tags.get("building").v, is("yes"));
				}
				else if (way.tags.get("source").v.endsWith("; 13111-bldg-466")) {
					assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
					assertThat(way.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
					assertThat(way.tags.get("addr:ref").v, is("13111058003"));
					assertThat(way.tags.get("height").v, is("6.84"));
					assertThat(way.tags.get("building").v, is("yes"));
				}
				else {
					fail(way.tags.get("source").v);
				}
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testSample_a1_parse() {
		test_doParse(Paths.get(".","sample_a_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.load(Paths.get("sample_a_bldg_6697_op2_1.osm").toFile());

			assertThat(osm.relations, notNullValue());
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertThat(relation, notNullValue());
				String type = relation.tags.get("type").v;
				if (type.equals("building")) {
					if (relation.tags.get("source").v.endsWith("; 13111-bldg-365")) {
						assertThat(relation.tags.get("type").v, is("building"));
						assertThat(relation.tags.get("building").v, is("yes"));
						assertThat(relation.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
						assertThat(relation.tags.get("addr:ref").v, is("13111058003"));
						assertThat(relation.tags.get("height").v, is("5.127"));
						assertThat(relation.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));

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
								assertThat(outline.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
								assertThat(outline.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
								assertThat(outline.tags.get("addr:ref").v, is("13111058003"));
								assertThat(outline.tags.get("height").v, is("5.127"));
								assertThat(outline.tags.size(), is(6));
							}
							if (mem.role.equals("part")) {
								partCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.tags.get("building:part").v, is("yes"));
								assertThat(way.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
								assertThat(way.tags.get("addr:ref").v, is("13111058003"));
								assertThat(way.tags.get("height").v, is("5.127"));
								assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
								assertThat(way.tags.size(), is(5));
							}
						}
						assertThat(outlineCnt, is(1));
						assertThat(partCnt, is(1));
					}
					else if (relation.tags.get("source").v.endsWith("; 13111-bldg-466")) {
						assertThat(relation.tags.get("type").v, is("building"));
						assertThat(relation.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
						assertThat(relation.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
						assertThat(relation.tags.get("addr:ref").v, is("13111058003"));
						assertThat(relation.tags.get("height").v, is("6.84"));
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
								assertThat(outline.tags.get("height").v, is("6.84"));
								assertThat(outline.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
								assertThat(outline.tags.size(), is(6));
							}
							if (mem.role.equals("part")) {
								partCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
								assertThat(way.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
								assertThat(way.tags.get("addr:ref").v, is("13111058003"));
								assertThat(way.tags.get("height").v, is("6.84"));
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
						assertThat(relation.tags.get("height").v, is("5.127"));
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
						assertThat(relation.tags.get("height").v, is("6.84"));
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
	public void testSample_a2_margePart() {
		test_doMargePart(Paths.get(".","sample_a_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.load(Paths.get("sample_a_bldg_6697_op2_2.osm").toFile());

			assertThat(osm.relations, notNullValue());
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertThat(relation, notNullValue());
				String type = relation.tags.get("type").v;
				if (type.equals("building")) {
					if (relation.tags.get("source").v.endsWith("; 13111-bldg-365")) {
						assertThat(relation.tags.get("type").v, is("building"));
						assertThat(relation.tags.get("building").v, is("yes"));
						assertThat(relation.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
						assertThat(relation.tags.get("addr:ref").v, is("13111058003"));
						assertThat(relation.tags.get("height").v, is("5.127"));
						assertThat(relation.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));

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
								assertThat(outline.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
								assertThat(outline.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
								assertThat(outline.tags.get("addr:ref").v, is("13111058003"));
								assertThat(outline.tags.get("height").v, is("5.127"));
								assertThat(outline.tags.size(), is(6));
							}
							if (mem.role.equals("part")) {
								partCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.tags.get("building:part").v, is("yes"));
								assertThat(way.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
								assertThat(way.tags.get("addr:ref").v, is("13111058003"));
								assertThat(way.tags.get("height").v, is("5.127"));
								assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
								assertThat(way.tags.size(), is(5));
							}
						}
						assertThat(outlineCnt, is(1));
						assertThat(partCnt, is(1));
					}
					else if (relation.tags.get("source").v.endsWith("; 13111-bldg-466")) {
						assertThat(relation.tags.get("type").v, is("building"));
						assertThat(relation.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
						assertThat(relation.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
						assertThat(relation.tags.get("addr:ref").v, is("13111058003"));
						assertThat(relation.tags.get("height").v, is("6.84"));
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
								assertThat(outline.tags.get("height").v, is("6.84"));
								assertThat(outline.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
								assertThat(outline.tags.size(), is(6));
							}
							if (mem.role.equals("part")) {
								partCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
								assertThat(way.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
								assertThat(way.tags.get("addr:ref").v, is("13111058003"));
								assertThat(way.tags.get("height").v, is("6.84"));
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
						assertThat(relation.tags.get("height").v, is("5.127"));
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
						assertThat(relation.tags.get("height").v, is("6.84"));
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
	public void testSample_a4_createOutline() {
		test_doCreateOutline(Paths.get(".","sample_a_bldg_6697_op2.gml"));
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
						assertThat(relation.tags.get("height").v, is("5.127"));
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
						assertThat(relation.tags.get("height").v, is("6.84"));
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
	public void testSample_a3_removeSinglePart() {
		test_doRemoveSinglePart(Paths.get(".","sample_a_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.load(Paths.get("sample_a_bldg_6697_op2_3.osm").toFile());

			assertThat(osm.relations, notNullValue());
			assertThat(osm.relations.size(), is(0));
			
			for (String id : osm.ways.keySet()) {
				ElementWay way = osm.ways.get(id);
				assertThat(way, notNullValue());

				if (way.tags.get("source").v.endsWith("; 13111-bldg-365")) {
					assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
					assertThat(way.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
					assertThat(way.tags.get("addr:ref").v, is("13111058003"));
					assertThat(way.tags.get("height").v, is("5.127"));
					assertThat(way.tags.get("building").v, is("yes"));
					assertThat(way.tags.size(), is(5));
				}
				else if (way.tags.get("source").v.endsWith("; 13111-bldg-466")) {
					assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
					assertThat(way.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
					assertThat(way.tags.get("addr:ref").v, is("13111058003"));
					assertThat(way.tags.get("height").v, is("6.84"));
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
	public void testSample_b() {
		test(Paths.get(".","sample_b_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.load(Paths.get("sample_b_bldg_6697_op2.osm").toFile());
			
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertThat(relation, notNullValue());
				String type = relation.tags.get("type").v;
				if (type.equals("multipolygon")) {
					/*
						map "multipolygon :Relation" as multipolygon {
						 type => multipolygon
						 building => yes
						 height => 17.582
						 source => MLIT_PLATEAU;\n http://www.opengis.net/def/crs/EPSG/0/6697
						}
					 */
					assertThat(relation.tags.get("type").v, is("multipolygon"));
					assertThat(relation.tags.get("building").v, is("yes"));
					assertThat(relation.tags.get("addr:full").v, is("東京都大田区大森西五丁目"));
					assertThat(relation.tags.get("addr:ref").v, is("13111006005"));
					assertThat(relation.tags.get("height").v, is("17.582"));
					assertThat(relation.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-61384"));
					int outerCnt = 0;
					int innerCnt = 0;
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outer")) {
							outerCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-61384"));
							assertThat(way.tags.size(), is(1));
						}
						if (mem.role.equals("inner")) {
							innerCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-61384"));
							assertThat(way.tags.size(), is(1));
						}
					}
					assertThat(outerCnt, is(1));
					assertThat(innerCnt, is(1));
					assertThat(relation.members.size(), is(2));
				}
				else {
					assertThat(type, is("multipolygon"));
				}
			}
			assertThat(osm.relations.size(), is(1));
			assertThat(osm.ways.size(), is(2));
			
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

    
	@Test
	public void testSample_c() {
		test(Paths.get(".","sample_c_bldg_6697_op2.gml"));
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

	@Test
	public void testSample_d() {
		test(Paths.get(".","sample_d_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.load(Paths.get("sample_d_bldg_6697_op2.osm").toFile());
			assertThat(osm.relations.size(), is(2));
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertThat(relation, notNullValue());
				String type = relation.tags.get("type").v;
				if (type.equals("building")) {
					/*
					 	map "building : Relation" as building {
						 type => building
						 building => yes
						 height => 32.852000000000004
						 source => MLIT_PLATEAU;\n http://www.opengis.net/def/crs/EPSG/0/6697
						}
					 */
					assertThat(relation.tags.get("type").v, is("building"));
					assertThat(relation.tags.get("building").v, is("yes"));
					assertThat(relation.tags.get("height").v, startsWith("32.852000000"));
					assertThat(relation.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					int outlineCnt = 0;
					int partCnt = 0;
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outline")) {
							outlineCnt++;
							assertThat(mem.type, is("relation"));
							ElementRelation multiporygon = osm.relations.get(Long.toString(mem.ref));
							assertThat(multiporygon, notNullValue());
						}
						if (mem.role.equals("part")) {
							partCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertThat(way, notNullValue());
							ElementTag tag = way.tags.get("source");
							if (tag.v.endsWith("; 13111-bldg-72601")) {
								assertThat(tag.v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));
								assertThat(way.tags.get("addr:full").v, is("東京都大田区大森西三丁目"));
								assertThat(way.tags.get("addr:ref").v, is("13111006003"));
								assertThat(way.tags.get("height").v, is("32.852000000000004"));
								assertThat(way.tags.get("building:part").v, is("yes"));
								assertThat(way.tags.size(), is(5));
							}
							else if (tag.v.endsWith("; 13111-bldg-71799")) {
								assertThat(tag.v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-71799"));
								assertThat(way.tags.get("addr:full").v, is("東京都大田区大森西三丁目"));
								assertThat(way.tags.get("addr:ref").v, is("13111006003"));
								assertThat(way.tags.get("height").v, is("6.038"));
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
							assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
							assertThat(way.tags.size(), is(1));
						}
						if (mem.role.equals("inner")) {
							innerCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertThat(way, notNullValue());
							assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));
							assertThat(way.tags.size(), is(1));
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
	public void testSample_e() {
		test(Paths.get(".","sample_e_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.load(Paths.get("sample_e_bldg_6697_op2.osm").toFile());
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertThat(relation, notNullValue());
				String type = relation.tags.get("type").v;
				if (type.equals("building")) {
					/*
					 	map "building : Relation" as building {
						 type => building
						 building => yes
						 height => 17.199
						 name => "大田病院"
						 source => MLIT_PLATEAU;\n http://www.opengis.net/def/crs/EPSG/0/6697
						}
					 */
					assertThat(relation.tags.get("type").v, is("building"));
					assertThat(relation.tags.get("building").v, is("yes"));
					assertThat(relation.tags.get("height").v, startsWith("17.199"));
					assertThat(relation.tags.get("name").v, startsWith("大田病院"));
					assertThat(relation.tags.get("addr:full").v, is("東京都大田区大森東四丁目"));
					assertThat(relation.tags.get("addr:ref").v, is("13111007004"));
					assertThat(relation.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					assertThat(relation.tags.size(), is(7));
					int outlineCnt = 0;
					int partCnt = 0;
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outline")) {
							outlineCnt++;
							assertThat(mem.type, is("relation"));
							ElementRelation multiporygon = osm.relations.get(Long.toString(mem.ref));
							assertThat(multiporygon, notNullValue());
						}
						if (mem.role.equals("part")) {
							partCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertThat(way, notNullValue());
							ElementTag tag = way.tags.get("source");
							if (tag.v.endsWith("; 13111-bldg-56522")) {
								assertThat(tag.v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-56522"));
								assertThat(way.tags.get("addr:full").v, is("東京都大田区大森東四丁目"));
								assertThat(way.tags.get("addr:ref").v, is("13111007004"));
								assertThat(way.tags.get("height").v, is("17.199"));
								assertThat(way.tags.get("building:part").v, is("yes"));
								assertThat(way.tags.get("name").v, startsWith("大田病院"));
								assertThat(way.tags.size(), is(6));
							}
							else if (tag.v.endsWith("; 13111-bldg-55333")) {
								assertThat(tag.v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-55333"));
								assertThat(way.tags.get("addr:full").v, is("東京都大田区大森東四丁目"));
								assertThat(way.tags.get("addr:ref").v, is("13111007004"));
								assertThat(way.tags.get("height").v, is("5.5360000000000005"));
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
					assertThat(relation.tags.get("height").v, is("17.199"));
					assertThat(relation.tags.get("name").v, is("大田病院"));
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
							assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
							assertThat(way.tags.size(), is(1));
						}
						if (mem.role.equals("inner")) {
							innerCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertThat(way, notNullValue());
							assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
							assertThat(way.tags.size(), is(1));
						}
					}
					assertThat(outerCnt, is(1));
					assertThat(innerCnt, is(2));
				}
				else {
					assertThat(type, is("building"));
				}
			}
			assertThat(osm.relations.size(), is(2));
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void test53392547() {
		test(Paths.get(".","53392547_bldg_6697_op2.gml"));
	}

	@Test
	public void test53392588() {
		test(Paths.get(".","53392588_bldg_6697_op2.gml"));
	}

	public void test(Path a) {
		CitygmlFile.doConvert(a);
	}


	/**
     * (1) メイン処理
     * 指定されたGMLファイルをOSMファイルに変換する
     * @param a
     */
    public static void test_doParse(Path a) {
		if (Files.isRegularFile(a)) {
			File file = a.toFile();
			String filename = file.getName();
			System.out.println(filename);
			if (filename.endsWith("_op2.gml")) {
				try {
					filename = filename.substring(0, filename.length()-4);
			        
			        OsmDom osm = new OsmDom();
		            CitygmlFile target = new CitygmlFile(file, osm);
		            target.parse();
			    	osm.export(new File(filename + "_1.osm"));
				} catch (Exception e) {
					e.printStackTrace();
					fail(e.toString());
				}
			}
		}
    }

    /**
     * (2) メイン処理
     * 接触しているBUILDINGのWAYをくっつけて"Relation:building"をつくる
     * @param a
     */
    public static void test_doMargePart(Path a) {
		if (Files.isRegularFile(a)) {
			File file = a.toFile();
			String filename = file.getName();
			System.out.println(filename);
			if (filename.endsWith("_op2.gml")) {
				try {
					filename = filename.substring(0, filename.length()-4);
			        
			        OsmDom osm = new OsmDom();
		            CitygmlFile target = new CitygmlFile(file, osm);
		            target.parse();
		            
		            // 各WAYのノードで、他のWAYと共有されたノードを探す
			    	// 接触しているBUILDINGのWAYをくっつけて"Relation:building"をつくる
			    	// Relation:multipolygon の MaxHeightを outline->Multipolygonへ設定する
			    	RelationMarge.relationMarge(osm);
			    	osm.export(new File(filename + "_2.osm"));
				} catch (Exception e) {
					e.printStackTrace();
					fail(e.toString());
				}
			}
		}
    }

    /**
     * (4) メイン処理
     * Relation:building->member:role=port のWay:outlineを作成する
     * Relation:multipolygon->outerにWay:outline
     * @param a
     */
    public static void test_doCreateOutline(Path a) {
		if (Files.isRegularFile(a)) {
			File file = a.toFile();
			String filename = file.getName();
			System.out.println(filename);
			if (filename.endsWith("_op2.gml")) {
				try {
					filename = filename.substring(0, filename.length()-4);
			        
			        OsmDom osm = new OsmDom();
		            CitygmlFile target = new CitygmlFile(file, osm);
		            target.parse();
		            
			    	RelationMarge.relationMarge(osm);

			    	BuildingGarbage.garbage(osm);

		            // Relation:building->member:role=port のWay:outlineを作成する
		            // Relation:multipolygon->outerにWay:outline
		            osm.relationOutline();
			    	osm.export(new File(filename + "_4.osm"));
			    	
				} catch (Exception e) {
					e.printStackTrace();
					fail(e.toString());
				}
			}
		}
    }


    /**
     * (3) メイン処理
     * メンバーが一つしかないRelation:building を削除する
     * メンバーが一つしかないRelation:multipolygon と polygon:member を削除する
     * @param a
     */
    public static void test_doRemoveSinglePart(Path a) {
		if (Files.isRegularFile(a)) {
			File file = a.toFile();
			String filename = file.getName();
			System.out.println(filename);
			if (filename.endsWith("_op2.gml")) {
				try {
					filename = filename.substring(0, filename.length()-4);
			        
			        OsmDom osm = new OsmDom();
		            CitygmlFile target = new CitygmlFile(file, osm);
		            target.parse();
		            
			    	RelationMarge.relationMarge(osm);
		            
		            // メンバーが一つしかないRelation:building を削除する
			    	// メンバーが一つしかないRelation:multipolygon と polygon:member を削除する
			    	BuildingGarbage.garbage(osm);
			    	osm.export(new File(filename + "_3.osm"));
		            
				} catch (Exception e) {
					e.printStackTrace();
					fail(e.toString());
				}
			}
		}
    }

    /**
     * メイン処理
     * 指定されたGMLファイルをOSMファイルに変換する
     * @param a
     */
    public static void test_do(Path a) {
		if (Files.isRegularFile(a)) {
			File file = a.toFile();
			String filename = file.getName();
			System.out.println(filename);
			if (filename.endsWith("_op2.gml")) {
				try {
					filename = filename.substring(0, filename.length()-4);
			        
					// (1) パース
			        OsmDom osm = new OsmDom();
		            CitygmlFile target = new CitygmlFile(file, osm);
		            target.parse();
		            
		            // (2) 各WAYのノードで、他のWAYと共有されたノードを探す
			    	// 接触しているBUILDINGのWAYをくっつけて"Relation:building"をつくる
			    	// Relation:multipolygon の MaxHeightを outline->Multipolygonへ設定する
			    	RelationMarge.relationMarge(osm);
		            
		            // (3) メンバーが一つしかないRelation:building を削除する
			    	// (3) メンバーが一つしかないRelation:multipolygon と polygon:member を削除する
			    	BuildingGarbage.garbage(osm);
		            
		            // (4) Relation:building->member:role=port のWay:outlineを作成する
		            // (4) Relation:multipolygon->outerにWay:outline
		            osm.relationOutline();
		            
		            // Relation:multipolygon の MaxHeightを outline->Multipolygonへ設定する
		            //OsmMargeWay.removeHeightFromOuter(osm);

		            // (5) "outline"と"part"が重複しているPART を削除する
		            OsmMargeWay.partGabegi(osm);
		            
		            // ファイルへエクスポートする
			    	osm.export(new File(filename + ".osm"));
				} catch (Exception e) {
					e.printStackTrace();
					fail(e.toString());
				}
			}
		}
    }
}
