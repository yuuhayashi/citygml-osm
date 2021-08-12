package osm.surveyor.osm.api;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.nio.file.Paths;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_A {

	/**
	 * `mvn test -Dtest=CitygmlFileTest_A#testSample_a2_margePart`
	 * 
	 */
	@SuppressWarnings("deprecation")
	@Test
	@Category(DetailTests.class)
	public void testSample_a2_margePart() {
		CitygmlFileTest.test_doRelationMarge(Paths.get("src/test/resources","sample_a_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.parse(Paths.get("sample_a_bldg_6697_op2_2.osm").toFile());

			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					if (relation.getTagValue("source").endsWith("; 13111-bldg-365")) {
						assertThat(relation.getTagValue("type"), is("building"));
						assertThat(relation.getTagValue("building"), is("yes"));
						assertThat(relation.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
						assertThat(relation.getTagValue("addr:ref"), is("13111058003"));
						assertThat(relation.getTagValue("height"), is("2.4"));
						assertThat(relation.getTagValue("ele"), is("2.75"));
						assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));

						int outlineCnt = 0;
						int partCnt = 0;
						for (MemberBean mem : relation.members) {
							if (mem.getRole().equals("outline")) {
								outlineCnt++;
								assertThat(mem.getType(), is("relation"));
								ElementRelation outline = osm.relations.get(mem.getRef());
								assertNotNull(outline);
								assertThat(outline.getTagValue("type"), is("multipolygon"));
								assertThat(outline.getTagValue("building"), is("yes"));
								assertThat(outline.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
								assertThat(outline.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
								assertThat(outline.getTagValue("addr:ref"), is("13111058003"));
								assertThat(outline.getTagValue("height"), is("2.4"));
								assertThat(outline.getTagValue("ele"), is("2.75"));
								assertThat(outline.tags.size(), is(7));
							}
							if (mem.getRole().equals("part")) {
								partCnt++;
								assertThat(mem.getType(), is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
								assertThat(way.getTagValue("addr:ref"), is("13111058003"));
								assertThat(way.getTagValue("height"), is("2.4"));
								assertThat(way.getTagValue("ele"), is("2.75"));
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
								assertThat(way.tags.size(), is(6));
							}
						}
						assertThat(outlineCnt, is(1));
						assertThat(partCnt, is(1));
					}
					else if (relation.getTagValue("source").endsWith("; 13111-bldg-466")) {
						assertThat(relation.getTagValue("type"), is("building"));
						assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
						assertThat(relation.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
						assertThat(relation.getTagValue("height"), is("4.6"));
						assertThat(relation.getTagValue("ele"), is("2.67"));
						assertThat(relation.getTagValue("start_date"), is("1976"));
						assertThat(relation.getTagValue("building"), is("house"));
						assertThat(relation.getTagValue("building:levels"), is("2"));
						assertThat(relation.getTagValue("building:levels:underground"), is("1"));

						int outlineCnt = 0;
						int partCnt = 0;
						for (MemberBean mem : relation.members) {
							if (mem.getRole().equals("outline")) {
								outlineCnt++;
								assertThat(mem.getType(), is("relation"));
								ElementRelation outline = osm.relations.get(mem.getRef());
								assertNotNull(outline);
								assertThat(outline.getTagValue("type"), is("multipolygon"));
								assertThat(outline.getTagValue("building"), is("house"));
								assertThat(outline.getTagValue("building:levels"), is("2"));
								assertThat(outline.getTagValue("building:levels:underground"), is("1"));
								assertThat(outline.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
								assertThat(outline.getTagValue("height"), is("4.6"));
								assertThat(outline.getTagValue("ele"), is("2.67"));
								assertThat(outline.getTagValue("start_date"), is("1976"));
								assertThat(outline.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
								assertThat(outline.tags.size(), is(9));
							}
							if (mem.getRole().equals("part")) {
								partCnt++;
								assertThat(mem.getType(), is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
								assertThat(way.getTagValue("height"), is("4.6"));
								assertThat(way.getTagValue("ele"), is("2.67"));
								assertThat(way.getTagValue("start_date"), is("1976"));
								assertThat(way.getTagValue("building:part"), is("house"));
								assertThat(way.getTagValue("building:levels"), is("2"));
								assertThat(way.getTagValue("building:levels:underground"), is("1"));
								assertThat(way.tags.size(), is(8));
							}
						}
						assertThat(outlineCnt, is(1));
						assertThat(partCnt, is(1));
					}
					assertThat(relation.members.size(), is(2));
				}
				else if (type.equals("multipolygon")) {
					if (relation.getTagValue("source").endsWith("; 13111-bldg-365")) {
						assertThat(relation.getTagValue("type"), is("multipolygon"));
						assertThat(relation.getTagValue("building"), is("yes"));
						assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
						assertThat(relation.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
						assertThat(relation.getTagValue("addr:ref"), is("13111058003"));
						assertThat(relation.getTagValue("height"), is("2.4"));
						assertThat(relation.getTagValue("ele"), is("2.75"));

						int outerCnt = 0;
						int innerCnt = 0;
						for (MemberBean mem : relation.members) {
							if (mem.getRole().equals("outer")) {
								outerCnt++;
								assertThat(mem.getType(), is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
								assertThat(way.tags.size(), is(1));
							}
							if (mem.getRole().equals("inner")) {
								innerCnt++;
								assertThat(mem.getType(), is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
								assertThat(way.tags.size(), is(1));
							}
						}
						assertThat(outerCnt, is(1));
						assertThat(innerCnt, is(0));
					}
					else if (relation.getTagValue("source").endsWith("; 13111-bldg-466")) {
						assertThat(relation.getTagValue("type"), is("multipolygon"));
						assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
						assertThat(relation.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
						assertThat(relation.getTagValue("height"), is("4.6"));
						assertThat(relation.getTagValue("ele"), is("2.67"));
						assertThat(relation.getTagValue("start_date"), is("1976"));
						assertThat(relation.getTagValue("building"), is("house"));
						assertThat(relation.getTagValue("building:levels"), is("2"));
						assertThat(relation.getTagValue("building:levels:underground"), is("1"));

						int outerCnt = 0;
						int innerCnt = 0;
						for (MemberBean mem : relation.members) {
							if (mem.getRole().equals("outer")) {
								outerCnt++;
								assertThat(mem.getType(), is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
								assertThat(way.tags.size(), is(1));
							}
							if (mem.getRole().equals("inner")) {
								innerCnt++;
								assertThat(mem.getType(), is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
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

	/**
	 * `mvn test -Dtest=CitygmlFileTest_A#testSample_a3_removeSinglePart`
	 * 
	 */
	@SuppressWarnings("deprecation")
	@Test
	@Category(DetailTests.class)
	public void testSample_a3_removeSinglePart() {
		CitygmlFileTest.test_doRemoveSinglePart(Paths.get("src/test/resources","sample_a_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.parse(Paths.get("sample_a_bldg_6697_op2_3.osm").toFile());

			assertNotNull(osm.relations);
			assertThat(osm.relations.size(), is(0));
			
			for (String id : osm.ways.keySet()) {
				ElementWay way = osm.ways.get(id);
				assertNotNull(way);

				if (way.getTagValue("source").endsWith("; 13111-bldg-365")) {
					assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
					assertThat(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
					assertThat(way.getTagValue("addr:ref"), is("13111058003"));
					assertThat(way.getTagValue("height"), is("2.4"));
					assertThat(way.getTagValue("ele"), is("2.75"));
					assertThat(way.tags.size(), is(6));
				}
				else if (way.getTagValue("source").endsWith("; 13111-bldg-466")) {
					assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
					assertThat(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
					assertThat(way.getTagValue("height"), is("4.6"));
					assertThat(way.getTagValue("ele"), is("2.67"));
					assertThat(way.getTagValue("start_date"), is("1976"));
					assertThat(way.getTagValue("building:levels"), is("2"));
					assertThat(way.getTagValue("building:levels:underground"), is("1"));
					assertThat(way.tags.size(), is(8));
				}
			}
			assertThat(osm.ways.size(), is(2));
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	/**
	 * `mvn test -Dtest=CitygmlFileTest_A#testSample_a4_createOutline`
	 * 
	 */
	@SuppressWarnings("deprecation")
	@Test
	@Category(DetailTests.class)
	public void testSample_a4_createOutline() {
		CitygmlFileTest.test4_doCreateOutline(Paths.get("src/test/resources","sample_a_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.parse(Paths.get("sample_a_bldg_6697_op2_4.osm").toFile());

			for (String id : osm.ways.keySet()) {
				ElementWay way = osm.ways.get(id);
				assertNotNull(way);
				if (way.getTagValue("source").endsWith("; 13111-bldg-365")) {
					assertThat(way.getTagValue("building"), is("yes"));
					assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
					assertThat(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
					assertThat(way.getTagValue("addr:ref"), is("13111058003"));
					assertThat(way.getTagValue("height"), is("2.4"));
					assertThat(way.getTagValue("ele"), is("2.75"));
					assertThat(way.tags.size(), is(6));
				}
				else if (way.getTagValue("source").endsWith("; 13111-bldg-466")) {
					assertThat(way.getTagValue("building"), is("house"));
					assertThat(way.getTagValue("building:levels"), is("2"));
					assertThat(way.getTagValue("building:levels:underground"), is("1"));
					assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
					assertThat(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
					assertThat(way.getTagValue("height"), is("4.6"));
					assertThat(way.getTagValue("ele"), is("2.67"));
					assertThat(way.getTagValue("start_date"), is("1976"));
					assertThat(way.tags.size(), is(8));
				}
				else {
					fail(way.getTagValue("source"));
				}
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
	
	/**
	 * `mvn test -Dtest=CitygmlFileTest_A#testSample_a`
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testSample_a() {
		CitygmlFileTest.test(Paths.get("src/test/resources","sample_a_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.parse(Paths.get("sample_a_bldg_6697_op2.osm").toFile());
			assertNotNull(osm.relations);
			assertThat(osm.relations.size(), is(0));
			for (String id : osm.ways.keySet()) {
				ElementWay way = osm.ways.get(id);
				assertNotNull(way);
				
				if (way.getTagValue("source").endsWith("; 13111-bldg-365")) {
					assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365", way.getTagValue("source"));
					assertEquals("東京都大田区南六郷三丁目", way.getTagValue("addr:full"));
					assertEquals("13111058003", way.getTagValue("addr:ref"));
					assertEquals("2.4", way.getTagValue("height"));
					assertEquals("2.75", way.getTagValue("ele"));
					assertEquals("yes", way.getTagValue("building"));
					assertEquals(6, way.tags.size());
				}
				else if (way.getTagValue("source").endsWith("; 13111-bldg-466")) {
					assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466", way.getTagValue("source"));
					assertEquals("東京都大田区南六郷三丁目", way.getTagValue("addr:full"));
					assertEquals("4.6", way.getTagValue("height"));
					assertEquals("2.67", way.getTagValue("ele"));
					assertEquals("1976", way.getTagValue("start_date"));
					assertEquals("house", way.getTagValue("building"));
					assertEquals("2", way.getTagValue("building:levels"));
					assertEquals("1", way.getTagValue("building:levels:underground"));
					assertEquals(8, way.tags.size());
				}
			}
			assertEquals(2, osm.ways.size());
			assertEquals(0, osm.relations.size());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	/**
	 * 値のない'addr:ref'
	 * 13111-bldg-141846
	 */
	@SuppressWarnings("deprecation")
	@Test
	@Category(DetailTests.class)
	public void testSample_aa() {
		CitygmlFileTest.test(Paths.get("src/test/resources","sample_aa_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.parse(Paths.get("sample_aa_bldg_6697_op2.osm").toFile());
			assertNotNull(osm.relations);
			assertThat(osm.relations.size(), is(0));
			for (String id : osm.ways.keySet()) {
				ElementWay way = osm.ways.get(id);
				assertNotNull(way);
				if (way.getTagValue("source").endsWith("; 13111-bldg-141846")) {
					assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-141846"));
					assertThat(way.getTagValue("addr:full"), is("東京都大田区大森東一丁目"));
					assertThat(way.getTagValue("height"), is("7.1"));
					assertEquals("1.91", way.getTagValue("ele"));
					assertThat(way.getTagValue("building"), is("yes"));
					assertThat(way.tags.size(), is(5));
				}
			}
			assertThat(osm.ways.size(), is(1));
			assertThat(osm.relations.size(), is(0));
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	/**
	 * 接触しているビルディング
	 * 13111-bldg-64135
	 */
	@SuppressWarnings("deprecation")
	@Test
	@Category(DetailTests.class)
	public void testSample_ab() {
		CitygmlFileTest.test(Paths.get("src/test/resources","sample_ab_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.parse(Paths.get("sample_ab_bldg_6697_op2.osm").toFile());
			assertNotNull(osm.relations);
			for (String id : osm.ways.keySet()) {
				ElementWay way = osm.ways.get(id);
				assertNotNull(way);
				assertThat(way.getTagValue("source"), startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-"));
				assertThat(way.getTagValue("addr:full"), is("東京都大田区大森西五丁目"));
				assertThat(way.getTagValue("addr:ref"), is("13111006005"));
				assertNotNull(way.getTagValue("height"));
				assertNotNull(way.getTagValue("ele"));
				assertThat(way.getTagValue("building"), is("yes"));
				assertThat(way.tags.size() >= 6, is(true));
			}
			assertThat(osm.ways.size(), is(2));
			assertThat(osm.relations.size(), is(0));
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	@Category(DetailTests.class)
	public void testSample_a1_parse() {
		CitygmlFileTest.test_doParse(Paths.get("src/test/resources","sample_a_bldg_6697_op2.gml"));
	    OsmDom osm = new OsmDom();
	    try {
			osm.parse(Paths.get("sample_a_bldg_6697_op2_1.osm").toFile());
	
			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					if (relation.getTagValue("source").endsWith("; 13111-bldg-365")) {
						assertThat(relation.getTagValue("type"), is("building"));
						assertThat(relation.getTagValue("building"), is("yes"));
						assertThat(relation.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
						assertThat(relation.getTagValue("addr:ref"), is("13111058003"));
						assertThat(relation.getTagValue("height"), is("2.4"));
						assertEquals("2.75", relation.getTagValue("ele"));
						assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
	
						int outlineCnt = 0;
						int partCnt = 0;
						for (MemberBean mem : relation.members) {
							if (mem.getRole().equals("outline")) {
								outlineCnt++;
								assertThat(mem.getType(), is("relation"));
								ElementRelation outline = osm.relations.get(mem.getRef());
								assertNotNull(outline);
								assertThat(outline.getTagValue("type"), is("multipolygon"));
								assertThat(outline.getTagValue("building"), is("yes"));
								assertThat(outline.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
								assertThat(outline.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
								assertThat(outline.getTagValue("addr:ref"), is("13111058003"));
								assertThat(outline.getTagValue("height"), is("2.4"));
								assertEquals("2.75", outline.getTagValue("ele"));
								assertThat(outline.tags.size(), is(7));
							}
							if (mem.getRole().equals("part")) {
								partCnt++;
								assertThat(mem.getType(), is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
								assertThat(way.getTagValue("addr:ref"), is("13111058003"));
								assertThat(way.getTagValue("height"), is("2.4"));
								assertEquals("2.75", way.getTagValue("ele"));
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
								assertThat(way.tags.size(), is(6));
							}
						}
						assertThat(outlineCnt, is(1));
						assertThat(partCnt, is(1));
					}
					else if (relation.getTagValue("source").endsWith("; 13111-bldg-466")) {
						assertThat(relation.getTagValue("type"), is("building"));
						assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
						assertThat(relation.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
						assertThat(relation.getTagValue("height"), is("4.6"));
						assertEquals("2.67", relation.getTagValue("ele"));
						assertThat(relation.getTagValue("start_date"), is("1976"));
						assertThat(relation.getTagValue("building"), is("house"));
						assertThat(relation.getTagValue("building:levels"), is("2"));
						assertThat(relation.getTagValue("building:levels:underground"), is("1"));
	
						int outlineCnt = 0;
						int partCnt = 0;
						for (MemberBean mem : relation.members) {
							if (mem.getRole().equals("outline")) {
								outlineCnt++;
								assertThat(mem.getType(), is("relation"));
								ElementRelation outline = osm.relations.get(mem.getRef());
								assertNotNull(outline);
								assertThat(outline.getTagValue("type"), is("multipolygon"));
								assertThat(outline.getTagValue("building"), is("house"));
								assertThat(outline.getTagValue("building:levels"), is("2"));
								assertThat(outline.getTagValue("building:levels:underground"), is("1"));
								assertThat(outline.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
								assertThat(outline.getTagValue("height"), is("4.6"));
								assertEquals("2.67", outline.getTagValue("ele"));
								assertThat(outline.getTagValue("start_date"), is("1976"));
								assertThat(outline.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
								assertThat(outline.tags.size(), is(9));
							}
							if (mem.getRole().equals("part")) {
								partCnt++;
								assertThat(mem.getType(), is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
								assertThat(way.getTagValue("height"), is("4.6"));
								assertEquals("2.67", way.getTagValue("ele"));
								assertThat(way.getTagValue("start_date"), is("1976"));
								assertThat(way.getTagValue("building:part"), is("house"));
								assertThat(way.getTagValue("building:levels"), is("2"));
								assertThat(way.getTagValue("building:levels:underground"), is("1"));
								assertThat(way.tags.size(), is(8));
							}
						}
						assertThat(outlineCnt, is(1));
						assertThat(partCnt, is(1));
					}
					assertThat(relation.members.size(), is(2));
				}
				else if (type.equals("multipolygon")) {
					if (relation.getTagValue("source").endsWith("; 13111-bldg-365")) {
						assertThat(relation.getTagValue("type"), is("multipolygon"));
						assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
						assertThat(relation.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
						assertThat(relation.getTagValue("addr:ref"), is("13111058003"));
						assertThat(relation.getTagValue("height"), is("2.4"));
						assertEquals("2.75", relation.getTagValue("ele"));
						assertThat(relation.getTagValue("building"), is("yes"));
	
						int outerCnt = 0;
						int innerCnt = 0;
						for (MemberBean mem : relation.members) {
							if (mem.getRole().equals("outer")) {
								outerCnt++;
								assertThat(mem.getType(), is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
								assertThat(way.tags.size(), is(1));
							}
							if (mem.getRole().equals("inner")) {
								innerCnt++;
								assertThat(mem.getType(), is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
								assertThat(way.tags.size(), is(1));
							}
						}
						assertThat(outerCnt, is(1));
						assertThat(innerCnt, is(0));
					}
					else if (relation.getTagValue("source").endsWith("; 13111-bldg-466")) {
						assertThat(relation.getTagValue("type"), is("multipolygon"));
						assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
						assertThat(relation.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
						assertThat(relation.getTagValue("height"), is("4.6"));
						assertEquals("2.67", relation.getTagValue("ele"));
						assertThat(relation.getTagValue("start_date"), is("1976"));
						assertThat(relation.getTagValue("building"), is("house"));
						assertThat(relation.getTagValue("building:levels"), is("2"));
						assertThat(relation.getTagValue("building:levels:underground"), is("1"));
	
						int outerCnt = 0;
						int innerCnt = 0;
						for (MemberBean mem : relation.members) {
							if (mem.getRole().equals("outer")) {
								outerCnt++;
								assertThat(mem.getType(), is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
								assertThat(way.tags.size(), is(1));
							}
							if (mem.getRole().equals("inner")) {
								innerCnt++;
								assertThat(mem.getType(), is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
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
}
