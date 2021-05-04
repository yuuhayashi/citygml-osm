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
import org.junit.experimental.categories.Category;

import osm.surveyor.osm.ElementMember;
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
			osm.load(Paths.get("sample_e_bldg_6697_op2_1.osm").toFile());

			assertThat(osm.relations, notNullValue());
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertThat(relation, notNullValue());
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					if (relation.getTagValue("source").endsWith("; 13111-bldg-56522")) {
						assertThat(relation.getTagValue("type"), is("building"));						
						assertThat(relation.getTagValue("building"), is("yes"));
						assertThat(relation.getTagValue("name"), is("大田病院"));
						assertThat(relation.getTagValue("height"), startsWith("17.199"));
						assertThat(relation.getTagValue("addr:full"), is("東京都大田区大森東四丁目"));
						assertThat(relation.getTagValue("addr:ref"), is("13111007004"));
						assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-56522"));
						assertThat(relation.tags.size(), is(7));

						int outlineCnt = 0;
						int partCnt = 0;
						for (ElementMember mem : relation.members) {
							if (mem.role.equals("outline")) {
								outlineCnt++;
								assertThat(mem.type, is("relation"));
								ElementRelation outline = osm.relations.get(mem.ref);
								assertThat(outline, notNullValue());
								assertThat(outline.getTagValue("type"), is("multipolygon"));
								assertThat(outline.getTagValue("building"), is("yes"));
								assertThat(outline.getTagValue("name"), is("大田病院"));
								assertThat(outline.getTagValue("height"), startsWith("17.199"));
								assertThat(outline.getTagValue("addr:full"), is("東京都大田区大森東四丁目"));
								assertThat(outline.getTagValue("addr:ref"), is("13111007004"));
								assertThat(outline.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-56522"));
								assertThat(outline.tags.size(), is(7));
							}
							if (mem.role.equals("part")) {
								partCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.getTagValue("name"), is("大田病院"));
								assertThat(way.getTagValue("height"), startsWith("17.199"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区大森東四丁目"));
								assertThat(way.getTagValue("addr:ref"), is("13111007004"));
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-56522"));
								assertThat(way.tags.size(), is(6));
							}
						}
						assertThat(outlineCnt, is(1));
						assertThat(partCnt, is(1));
						assertThat(relation.members.size(), is(2));
					}
					else if (relation.getTagValue("source").endsWith("; 13111-bldg-55333")) {
						assertThat(relation.getTagValue("type"), is("building"));
						assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-55333"));
						assertThat(relation.getTagValue("addr:full"), is("東京都大田区大森東四丁目"));
						assertThat(relation.getTagValue("addr:ref"), is("13111007004"));
						assertThat(relation.getTagValue("height"), is("5.5360000000000005"));
						assertThat(relation.getTagValue("building"), is("yes"));
						assertThat(relation.tags.size(), is(6));

						int outlineCnt = 0;
						int partCnt = 0;
						for (ElementMember mem : relation.members) {
							if (mem.role.equals("outline")) {
								outlineCnt++;
								assertThat(mem.type, is("relation"));
								ElementRelation outline = osm.relations.get(mem.ref);
								assertThat(outline, notNullValue());
								assertThat(outline.getTagValue("type"), is("multipolygon"));
								assertThat(outline.getTagValue("building"), is("yes"));
								assertThat(outline.getTagValue("addr:full"), is("東京都大田区大森東四丁目"));
								assertThat(outline.getTagValue("addr:ref"), is("13111007004"));
								assertThat(outline.getTagValue("height"), is("5.5360000000000005"));
								assertThat(outline.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-55333"));
								assertThat(outline.tags.size(), is(6));
							}
							if (mem.role.equals("part")) {
								partCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.getTagValue("addr:full"), is("東京都大田区大森東四丁目"));
								assertThat(way.getTagValue("addr:ref"), is("13111007004"));
								assertThat(way.getTagValue("height"), is("5.5360000000000005"));
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-55333"));
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.tags.size(), is(5));
							}
						}
						assertThat(outlineCnt, is(1));
						assertThat(partCnt, is(1));
						assertThat(relation.members.size(), is(2));
					}
				}
				else if (type.equals("multipolygon")) {
					if (relation.getTagValue("source").endsWith("; 13111-bldg-56522")) {
						assertThat(relation.getTagValue("type"), is("multipolygon"));
						assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-56522"));
						assertThat(relation.getTagValue("name"), is("大田病院"));
						assertThat(relation.getTagValue("addr:full"), is("東京都大田区大森東四丁目"));
						assertThat(relation.getTagValue("addr:ref"), is("13111007004"));
						assertThat(relation.getTagValue("height"), is("17.199"));
						assertThat(relation.getTagValue("building"), is("yes"));

						int outerCnt = 0;
						int innerCnt = 0;
						for (ElementMember mem : relation.members) {
							if (mem.role.equals("outer")) {
								outerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-56522"));
								assertThat(way.tags.size(), is(1));
							}
							if (mem.role.equals("inner")) {
								innerCnt++;
							}
						}
						assertThat(outerCnt, is(1));
						assertThat(innerCnt, is(0));
						assertThat(relation.members.size(), is(1));
					}
					else if (relation.getTagValue("source").endsWith("; 13111-bldg-55333")) {
						assertThat(relation.getTagValue("type"), is("multipolygon"));
						assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-55333"));
						assertThat(relation.getTagValue("addr:full"), is("東京都大田区大森東四丁目"));
						assertThat(relation.getTagValue("addr:ref"), is("13111007004"));
						assertThat(relation.getTagValue("height"), is("5.5360000000000005"));
						assertThat(relation.getTagValue("building"), is("yes"));

						int outerCnt = 0;
						int innerCnt = 0;
						for (ElementMember mem : relation.members) {
							if (mem.role.equals("outer")) {
								outerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-55333"));
								assertThat(way.tags.size(), is(1));
							}
							if (mem.role.equals("inner")) {
								innerCnt++;
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
	@Category(DetailTests.class)
	public void testSample_e2_margePart() {
		CitygmlFileTest.test_doRelationMarge(Paths.get("src/test/resources","sample_e_bldg_6697_op2.gml"));
		
        OsmDom osm = new OsmDom();
        try {
			osm.load(Paths.get("sample_e_bldg_6697_op2_2.osm").toFile());

			assertThat(osm.relations, notNullValue());
			int outlineCnt = 0;
			int partCnt = 0;
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertThat(relation, notNullValue());
				if (relation.isBuilding()) {
					assertThat(relation.getTagValue("type"), is("building"));
					assertThat(relation.getTagValue("building"), is("yes"));
					assertThat(relation.getTagValue("name"), is("大田病院"));
					assertThat(relation.getTagValue("addr:full"), is("東京都大田区大森東四丁目"));
					assertThat(relation.getTagValue("addr:ref"), is("13111007004"));
					assertThat(relation.getTagValue("height"), notNullValue());
					assertThat(relation.getTagValue("source"), startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
	
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outline")) {
							outlineCnt++;
							assertThat(mem.type, is("relation"));
							ElementRelation outline = osm.relations.get(mem.ref);
							assertThat(outline, notNullValue());
						}
						if (mem.role.equals("part")) {
							partCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertThat(way, notNullValue());
						}
					}
				}
				else if (relation.isMultipolygon()) {
					if (relation.getTagValue("source").endsWith("; 13111-bldg-72601")) {
						assertThat(relation.getTagValue("type"), is("multipolygon"));
						assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));
						assertThat(relation.getTagValue("name"), is("大田病院"));
						assertThat(relation.getTagValue("addr:full"), is("東京都大田区大森東四丁目"));
						assertThat(relation.getTagValue("addr:ref"), is("13111007004"));
						assertThat(relation.getTagValue("height"), is("17.199"));
						assertThat(relation.getTagValue("building"), is("yes"));

						int outerCnt = 0;
						int innerCnt = 0;
						for (ElementMember mem : relation.members) {
							if (mem.role.equals("outer")) {
								outerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));
								assertThat(way.tags.size(), is(1));
							}
							if (mem.role.equals("inner")) {
								innerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));
								assertThat(way.tags.size(), is(1));
							}
						}
						assertThat(outerCnt, is(1));
						assertThat(innerCnt, is(2));
						assertThat(relation.members.size(), is(3));
					}
					else if (relation.getTagValue("source").endsWith("; 13111-bldg-55333")) {
						assertThat(relation.getTagValue("type"), is("multipolygon"));
						assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-55333"));
						assertThat(relation.getTagValue("addr:full"), is("東京都大田区大森東四丁目"));
						assertThat(relation.getTagValue("addr:ref"), is("13111007004"));
						assertThat(relation.getTagValue("height"), is("5.5360000000000005"));
						assertThat(relation.getTagValue("building"), is("yes"));

						int outerCnt = 0;
						int innerCnt = 0;
						for (ElementMember mem : relation.members) {
							if (mem.role.equals("outer")) {
								outerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-55333"));
								assertThat(way.tags.size(), is(1));
							}
							if (mem.role.equals("inner")) {
								innerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-55333"));
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
			assertThat(partCnt, is(2));
			assertThat(osm.relations.size(), is(4));
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
			osm.load(Paths.get("sample_e_bldg_6697_op2_3.osm").toFile());

			assertThat(osm.relations, notNullValue());
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertThat(relation, notNullValue());
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					assertThat(relation.getTagValue("type"), is("building"));
					assertThat(relation.getTagValue("building"), is("yes"));
					assertThat(relation.getTagValue("name"), is("大田病院"));
					assertThat(relation.getTagValue("height"), notNullValue());
					assertThat(relation.getTagValue("addr:ref"), is("13111007004"));
					assertThat(relation.getTagValue("addr:full"), is("東京都大田区大森東四丁目"));
					assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));

					int outlineCnt = 0;
					int partCnt = 0;
					int outerCnt = 0;
					int innerCnt = 0;
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outline")) {
							outlineCnt++;
						}
						if (mem.role.equals("part")) {
							partCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertThat(way, notNullValue());
							if (way.getTagValue("source").endsWith("; 13111-bldg-56522")) {
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.getTagValue("name"), is("大田病院"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区大森東四丁目"));
								assertThat(way.getTagValue("addr:ref"), is("13111007004"));
								assertThat(way.getTagValue("height"), is("17.199"));
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-56522"));
								assertThat(way.tags.size(), is(6));
							}
							else if (way.getTagValue("source").endsWith("; 13111-bldg-55333")) {
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-55333"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区大森東四丁目"));
								assertThat(way.getTagValue("addr:ref"), is("13111007004"));
								assertThat(way.getTagValue("height"), is("5.5360000000000005"));
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.tags.size(), is(5));
							}
						}
					}
					assertThat(innerCnt, is(0));
					assertThat(outerCnt, is(0));
					assertThat(outlineCnt, is(0));
					assertThat(partCnt, is(2));
					assertThat(relation.members.size(), is(2));
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
	@Category(DetailTests.class)
	public void testSample_e4_createOutline() {
		CitygmlFileTest.test4_doCreateOutline(Paths.get("src/test/resources","sample_e_bldg_6697_op2.gml"));
		
        OsmDom osm = new OsmDom();
        try {
			osm.load(Paths.get("sample_e_bldg_6697_op2_4.osm").toFile());
			checkSample_e4_createOutline(osm);
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
    
	public void checkSample_e4_createOutline(OsmDom osm) {
        try {
			assertThat(osm.relations, notNullValue());
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertThat(relation, notNullValue());
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					assertThat(relation.getTagValue("type"), is("building"));
					assertThat(relation.getTagValue("building"), is("yes"));
					assertThat(relation.getTagValue("name"), is("大田病院"));
					assertThat(relation.getTagValue("addr:full"), is("東京都大田区大森東四丁目"));
					assertThat(relation.getTagValue("addr:ref"), is("13111007004"));
					assertThat(relation.getTagValue("height"), is("17.199"));
					assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					assertThat(relation.tags.size(), is(7));

					int outlineCnt = 0;
					int partCnt = 0;
					int outerCnt = 0;
					int innerCnt = 0;
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outline")) {
							outlineCnt++;
							assertThat(mem.type, is("relation"));
							ElementRelation polygon = osm.relations.get(Long.toString(mem.ref));
							assertThat(polygon, notNullValue());
							assertThat(polygon.getTagValue("type"), is("multipolygon"));
							assertThat(polygon.getTagValue("building"), is("yes"));
							assertThat(polygon.getTagValue("building:name"), is("大田病院"));
							assertThat(polygon.getTagValue("addr:full"), is("東京都大田区大森東四丁目"));
							assertThat(polygon.getTagValue("addr:ref"), is("13111007004"));
							assertThat(polygon.getTagValue("height"), is("17.199"));
							assertThat(polygon.getTagValue("source"), startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
							assertThat(polygon.tags.size(), is(7));
							
							for (ElementMember member : polygon.members) {
								if (member.role.equals("outer")) {
									outerCnt++;
									assertThat(member.type, is("way"));
									ElementWay way = osm.ways.get(Long.toString(member.ref));
									assertThat(way, notNullValue());
									assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
									assertThat(way.tags.size(), is(1));
								}
								if (member.role.equals("inner")) {
									innerCnt++;
									assertThat(member.type, is("way"));
									ElementWay way = osm.ways.get(Long.toString(member.ref));
									assertThat(way, notNullValue());
									assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
									assertThat(way.tags.size(), is(1));
								}
							}
							assertThat(polygon.members.size(), is(2));
						}
						if (mem.role.equals("part")) {
							partCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertThat(way, notNullValue());
							if (way.getTagValue("source").endsWith("; 13111-bldg-56522")) {
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.getTagValue("name"), is("大田病院"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区大森東四丁目"));
								assertThat(way.getTagValue("addr:ref"), is("13111007004"));
								assertThat(way.getTagValue("height"), is("17.199"));
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-56522"));
								assertThat(way.tags.size(), is(6));
							}
							else if (way.getTagValue("source").endsWith("; 13111-bldg-55333")) {
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区大森東四丁目"));
								assertThat(way.getTagValue("addr:ref"), is("13111007004"));
								assertThat(way.getTagValue("height"), is("5.5360000000000005"));
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-55333"));
								assertThat(way.tags.size(), is(5));
							}
						}
					}
					assertThat(innerCnt, is(1));
					assertThat(outerCnt, is(1));
					assertThat(outlineCnt, is(1));
					assertThat(partCnt, is(2));
					assertThat(relation.members.size(), is(3));
				}
			}
			assertThat(osm.relations.size(), is(2));
			assertThat(osm.ways.size(), is(4));
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
			osm.load(Paths.get("sample_e_bldg_6697_op2.osm").toFile());
			checkSample_e4_createOutline(osm);
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
