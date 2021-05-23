package osm.surveyor.osm.api;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.nio.file.Paths;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.ElementMember;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_A {

	/**
	 * `mvn test -Dtest=CitygmlFileTest_A#testSample_a2_margePart`
	 * 
	 */
	@Test
	@Category(DetailTests.class)
	public void testSample_a2_margePart() {
		CitygmlFileTest.test_doRelationMarge(Paths.get("src/test/resources","sample_a_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.parse(Paths.get("sample_a_bldg_6697_op2_2.osm").toFile());

			assertThat(osm.relations, notNullValue());
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertThat(relation, notNullValue());
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					if (relation.getTagValue("source").endsWith("; 13111-bldg-365")) {
						assertThat(relation.getTagValue("type"), is("building"));
						assertThat(relation.getTagValue("building"), is("yes"));
						assertThat(relation.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
						assertThat(relation.getTagValue("addr:ref"), is("13111058003"));
						assertThat(relation.getTagValue("height"), is("2.4"));
						assertThat(relation.getTagValue("ele"), is("2.749"));
						assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));

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
								assertThat(outline.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
								assertThat(outline.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
								assertThat(outline.getTagValue("addr:ref"), is("13111058003"));
								assertThat(outline.getTagValue("height"), is("2.4"));
								assertThat(outline.getTagValue("ele"), is("2.749"));
								assertThat(outline.tags.size(), is(7));
							}
							if (mem.role.equals("part")) {
								partCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
								assertThat(way.getTagValue("addr:ref"), is("13111058003"));
								assertThat(way.getTagValue("height"), is("2.4"));
								assertThat(way.getTagValue("ele"), is("2.749"));
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
						assertThat(relation.getTagValue("ele"), is("2.671"));
						assertThat(relation.getTagValue("building"), is("house"));

						int outlineCnt = 0;
						int partCnt = 0;
						for (ElementMember mem : relation.members) {
							if (mem.role.equals("outline")) {
								outlineCnt++;
								assertThat(mem.type, is("relation"));
								ElementRelation outline = osm.relations.get(mem.ref);
								assertThat(outline, notNullValue());
								assertThat(outline.getTagValue("type"), is("multipolygon"));
								assertThat(outline.getTagValue("building"), is("house"));
								assertThat(outline.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
								assertThat(outline.getTagValue("height"), is("4.6"));
								assertThat(outline.getTagValue("ele"), is("2.671"));
								assertThat(outline.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
								assertThat(outline.tags.size(), is(6));
							}
							if (mem.role.equals("part")) {
								partCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
								assertThat(way.getTagValue("height"), is("4.6"));
								assertThat(way.getTagValue("ele"), is("2.671"));
								assertThat(way.getTagValue("building:part"), is("house"));
								assertThat(way.tags.size(), is(5));
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
						assertThat(relation.getTagValue("ele"), is("2.749"));

						int outerCnt = 0;
						int innerCnt = 0;
						for (ElementMember mem : relation.members) {
							if (mem.role.equals("outer")) {
								outerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
								assertThat(way.tags.size(), is(1));
							}
							if (mem.role.equals("inner")) {
								innerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
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
						assertThat(relation.getTagValue("ele"), is("2.671"));
						assertThat(relation.getTagValue("building"), is("house"));

						int outerCnt = 0;
						int innerCnt = 0;
						for (ElementMember mem : relation.members) {
							if (mem.role.equals("outer")) {
								outerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
								assertThat(way.tags.size(), is(1));
							}
							if (mem.role.equals("inner")) {
								innerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
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
	@Test
	@Category(DetailTests.class)
	public void testSample_a3_removeSinglePart() {
		CitygmlFileTest.test_doRemoveSinglePart(Paths.get("src/test/resources","sample_a_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.parse(Paths.get("sample_a_bldg_6697_op2_3.osm").toFile());

			assertThat(osm.relations, notNullValue());
			assertThat(osm.relations.size(), is(0));
			
			for (String id : osm.ways.keySet()) {
				ElementWay way = osm.ways.get(id);
				assertThat(way, notNullValue());

				if (way.getTagValue("source").endsWith("; 13111-bldg-365")) {
					assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
					assertThat(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
					assertThat(way.getTagValue("addr:ref"), is("13111058003"));
					assertThat(way.getTagValue("height"), is("2.4"));
					assertThat(way.getTagValue("ele"), is("2.749"));
					assertThat(way.tags.size(), is(6));
				}
				else if (way.getTagValue("source").endsWith("; 13111-bldg-466")) {
					assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
					assertThat(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
					assertThat(way.getTagValue("height"), is("4.6"));
					assertThat(way.getTagValue("ele"), is("2.671"));
					assertThat(way.tags.size(), is(5));
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
	@Test
	@Category(DetailTests.class)
	public void testSample_a4_createOutline() {
		CitygmlFileTest.test4_doCreateOutline(Paths.get("src/test/resources","sample_a_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.parse(Paths.get("sample_a_bldg_6697_op2_4.osm").toFile());

			for (String id : osm.ways.keySet()) {
				ElementWay way = osm.ways.get(id);
				assertThat(way, notNullValue());
				if (way.getTagValue("source").endsWith("; 13111-bldg-365")) {
					assertThat(way.getTagValue("building"), is("yes"));
					assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
					assertThat(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
					assertThat(way.getTagValue("addr:ref"), is("13111058003"));
					assertThat(way.getTagValue("height"), is("2.4"));
					assertThat(way.getTagValue("ele"), is("2.749"));
					assertThat(way.tags.size(), is(6));
				}
				else if (way.getTagValue("source").endsWith("; 13111-bldg-466")) {
					assertThat(way.getTagValue("building"), is("house"));
					assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
					assertThat(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
					assertThat(way.getTagValue("height"), is("4.6"));
					assertThat(way.getTagValue("ele"), is("2.671"));
					assertThat(way.tags.size(), is(5));
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
	@Test
	public void testSample_a() {
		CitygmlFileTest.test(Paths.get("src/test/resources","sample_a_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.parse(Paths.get("sample_a_bldg_6697_op2.osm").toFile());
			assertThat(osm.relations, notNullValue());
			assertThat(osm.relations.size(), is(0));
			for (String id : osm.ways.keySet()) {
				ElementWay way = osm.ways.get(id);
				assertThat(way, notNullValue());
				
				if (way.getTagValue("source").endsWith("; 13111-bldg-365")) {
					assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
					assertThat(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
					assertThat(way.getTagValue("addr:ref"), is("13111058003"));
					assertThat(way.getTagValue("height"), is("2.4"));
					assertThat(way.getTagValue("ele"), is("2.749"));
					assertThat(way.getTagValue("building"), is("yes"));
					assertThat(way.tags.size(), is(6));
				}
				else if (way.getTagValue("source").endsWith("; 13111-bldg-466")) {
					assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
					assertThat(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
					assertThat(way.getTagValue("height"), is("4.6"));
					assertThat(way.getTagValue("ele"), is("2.671"));
					assertThat(way.getTagValue("building"), is("house"));
					assertThat(way.tags.size(), is(5));
				}
			}
			assertThat(osm.ways.size(), is(2));
			assertThat(osm.relations.size(), is(0));
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	/**
	 * 値のない'addr:ref'
	 * 13111-bldg-141846
	 */
	@Test
	@Category(DetailTests.class)
	public void testSample_aa() {
		CitygmlFileTest.test(Paths.get("src/test/resources","sample_aa_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.parse(Paths.get("sample_aa_bldg_6697_op2.osm").toFile());
			assertThat(osm.relations, notNullValue());
			assertThat(osm.relations.size(), is(0));
			for (String id : osm.ways.keySet()) {
				ElementWay way = osm.ways.get(id);
				assertThat(way, notNullValue());
				if (way.getTagValue("source").endsWith("; 13111-bldg-141846")) {
					assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-141846"));
					assertThat(way.getTagValue("addr:full"), is("東京都大田区大森東一丁目"));
					assertThat(way.getTagValue("height"), is("7.1"));
					assertThat(way.getTagValue("ele"), is("1.905"));
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
	@Test
	@Category(DetailTests.class)
	public void testSample_ab() {
		CitygmlFileTest.test(Paths.get("src/test/resources","sample_ab_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.parse(Paths.get("sample_ab_bldg_6697_op2.osm").toFile());
			assertThat(osm.relations, notNullValue());
			for (String id : osm.ways.keySet()) {
				ElementWay way = osm.ways.get(id);
				assertThat(way, notNullValue());
				assertThat(way.getTagValue("source"), startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-"));
				assertThat(way.getTagValue("addr:full"), is("東京都大田区大森西五丁目"));
				assertThat(way.getTagValue("addr:ref"), is("13111006005"));
				assertThat(way.getTagValue("height"), notNullValue());
				assertThat(way.getTagValue("ele"), notNullValue());
				assertThat(way.getTagValue("building"), is("yes"));
				assertThat(way.tags.size(), is(6));
			}
			assertThat(osm.ways.size(), is(2));
			assertThat(osm.relations.size(), is(0));
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	@Test
	@Category(DetailTests.class)
	public void testSample_a1_parse() {
		CitygmlFileTest.test_doParse(Paths.get("src/test/resources","sample_a_bldg_6697_op2.gml"));
	    OsmDom osm = new OsmDom();
	    try {
			osm.parse(Paths.get("sample_a_bldg_6697_op2_1.osm").toFile());
	
			assertThat(osm.relations, notNullValue());
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertThat(relation, notNullValue());
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					if (relation.getTagValue("source").endsWith("; 13111-bldg-365")) {
						assertThat(relation.getTagValue("type"), is("building"));
						assertThat(relation.getTagValue("building"), is("yes"));
						assertThat(relation.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
						assertThat(relation.getTagValue("addr:ref"), is("13111058003"));
						assertThat(relation.getTagValue("height"), is("2.4"));
						assertThat(relation.getTagValue("ele"), is("2.749"));
						assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
	
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
								assertThat(outline.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
								assertThat(outline.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
								assertThat(outline.getTagValue("addr:ref"), is("13111058003"));
								assertThat(outline.getTagValue("height"), is("2.4"));
								assertThat(outline.getTagValue("ele"), is("2.749"));
								assertThat(outline.tags.size(), is(7));
							}
							if (mem.role.equals("part")) {
								partCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
								assertThat(way.getTagValue("addr:ref"), is("13111058003"));
								assertThat(way.getTagValue("height"), is("2.4"));
								assertThat(way.getTagValue("ele"), is("2.749"));
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
						assertThat(relation.getTagValue("ele"), is("2.671"));
						assertThat(relation.getTagValue("building"), is("house"));
	
						int outlineCnt = 0;
						int partCnt = 0;
						for (ElementMember mem : relation.members) {
							if (mem.role.equals("outline")) {
								outlineCnt++;
								assertThat(mem.type, is("relation"));
								ElementRelation outline = osm.relations.get(mem.ref);
								assertThat(outline, notNullValue());
								assertThat(outline.getTagValue("type"), is("multipolygon"));
								assertThat(outline.getTagValue("building"), is("house"));
								assertThat(outline.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
								assertThat(outline.getTagValue("height"), is("4.6"));
								assertThat(outline.getTagValue("ele"), is("2.671"));
								assertThat(outline.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
								assertThat(outline.tags.size(), is(6));
							}
							if (mem.role.equals("part")) {
								partCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
								assertThat(way.getTagValue("height"), is("4.6"));
								assertThat(way.getTagValue("ele"), is("2.671"));
								assertThat(way.getTagValue("building:part"), is("house"));
								assertThat(way.tags.size(), is(5));
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
						assertThat(relation.getTagValue("ele"), is("2.749"));
						assertThat(relation.getTagValue("building"), is("yes"));
	
						int outerCnt = 0;
						int innerCnt = 0;
						for (ElementMember mem : relation.members) {
							if (mem.role.equals("outer")) {
								outerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
								assertThat(way.tags.size(), is(1));
							}
							if (mem.role.equals("inner")) {
								innerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
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
						assertThat(relation.getTagValue("ele"), is("2.671"));
						assertThat(relation.getTagValue("building"), is("house"));
	
						int outerCnt = 0;
						int innerCnt = 0;
						for (ElementMember mem : relation.members) {
							if (mem.role.equals("outer")) {
								outerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
								assertThat(way.tags.size(), is(1));
							}
							if (mem.role.equals("inner")) {
								innerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
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
