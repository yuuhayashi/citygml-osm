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
import osm.surveyor.osm.ElementMember;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_D {

	@SuppressWarnings("deprecation")
	@Test
	@Category(DetailTests.class)
	public void testSample_d1_parse() {
		CitygmlFileTest.test_doParse(Paths.get("src/test/resources","sample_d_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.parse(Paths.get("sample_d_bldg_6697_op2_1.osm").toFile());

			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					if (relation.getTagValue("source").endsWith("; 13111-bldg-72601")) {
						assertThat(relation.getTagValue("type"), is("building"));
						assertThat(relation.getTagValue("building"), is("yes"));
						assertThat(relation.getTagValue("building:levels"), is("2"));
						assertThat(relation.getTagValue("building:levels:underground"), is("1"));
						assertThat(relation.getTagValue("height"), is("34.7"));
						assertThat(relation.getTagValue("ele"), is("2.68"));
						assertThat(relation.getTagValue("start_date"), is("1976"));
						assertThat(relation.getTagValue("addr:full"), is("東京都大田区大森西三丁目"));
						assertThat(relation.getTagValue("addr:ref"), is("13111006003"));
						assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));

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
								assertThat(outline.getTagValue("building:levels:underground"), is("1"));
								assertThat(outline.getTagValue("height"), is("34.7"));
								assertThat(outline.getTagValue("ele"), is("2.68"));
								assertThat(outline.getTagValue("start_date"), is("1976"));
								assertThat(outline.getTagValue("addr:full"), is("東京都大田区大森西三丁目"));
								assertThat(outline.getTagValue("addr:ref"), is("13111006003"));
								assertThat(outline.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));
								assertThat(outline.tags.size(), is(10));
							}
							if (mem.role.equals("part")) {
								partCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertNotNull(way);
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.getTagValue("height"), is("34.7"));
								assertThat(way.getTagValue("ele"), is("2.68"));
								assertThat(way.getTagValue("start_date"), is("1976"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区大森西三丁目"));
								assertThat(way.getTagValue("addr:ref"), is("13111006003"));
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));
								assertThat(way.tags.size() >= 6, is(true));
							}
						}
						assertThat(outlineCnt, is(1));
						assertThat(partCnt, is(1));
						assertThat(relation.members.size(), is(2));
					}
					else if (relation.getTagValue("source").endsWith("; 13111-bldg-71799")) {
						assertThat(relation.getTagValue("type"), is("building"));
						assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-71799"));
						assertThat(relation.getTagValue("addr:full"), is("東京都大田区大森西三丁目"));
						assertThat(relation.getTagValue("addr:ref"), is("13111006003"));
						assertThat(relation.getTagValue("height"), is("30.2"));
						assertThat(relation.getTagValue("ele"), is("3.13"));
						assertThat(relation.getTagValue("start_date"), is("1977"));
						assertThat(relation.getTagValue("building"), is("yes"));
						assertThat(relation.getTagValue("building:levels"), is("3"));

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
								assertThat(outline.getTagValue("building:levels"), is("3"));
								assertThat(outline.getTagValue("addr:full"), is("東京都大田区大森西三丁目"));
								assertThat(outline.getTagValue("addr:ref"), is("13111006003"));
								assertThat(outline.getTagValue("height"), is("30.2"));
								assertThat(outline.getTagValue("start_date"), is("1977"));
								assertThat(outline.getTagValue("ele"), is("3.13"));
								assertThat(outline.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-71799"));
								assertThat(outline.tags.size(), is(9));
							}
							if (mem.role.equals("part")) {
								partCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertNotNull(way);
								assertThat(way.getTagValue("addr:full"), is("東京都大田区大森西三丁目"));
								assertThat(way.getTagValue("addr:ref"), is("13111006003"));
								assertThat(way.getTagValue("height"), is("30.2"));
								assertThat(way.getTagValue("ele"), is("3.13"));
								assertThat(way.getTagValue("start_date"), is("1977"));
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-71799"));
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.getTagValue("building:levels"), is("3"));
								assertThat(way.tags.size(), is(8));
							}
						}
						assertThat(outlineCnt, is(1));
						assertThat(partCnt, is(1));
						assertThat(relation.members.size(), is(2));
					}
				}
				else if (type.equals("multipolygon")) {
					if (relation.getTagValue("source").endsWith("; 13111-bldg-72601")) {
						assertThat(relation.getTagValue("type"), is("multipolygon"));
						assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));
						assertThat(relation.getTagValue("addr:full"), is("東京都大田区大森西三丁目"));
						assertThat(relation.getTagValue("addr:ref"), is("13111006003"));
						assertThat(relation.getTagValue("height"), is("34.7"));
						assertThat(relation.getTagValue("ele"), is("2.68"));
						assertThat(relation.getTagValue("start_date"), is("1976"));
						assertThat(relation.getTagValue("building"), is("yes"));
						assertThat(relation.getTagValue("building:levels"), is("2"));
						assertThat(relation.getTagValue("building:levels:underground"), is("1"));

						int outerCnt = 0;
						int innerCnt = 0;
						for (ElementMember mem : relation.members) {
							if (mem.role.equals("outer")) {
								outerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertNotNull(way);
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));
								assertThat(way.tags.size(), is(1));
							}
							if (mem.role.equals("inner")) {
								innerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertNotNull(way);
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));
								assertThat(way.tags.size(), is(1));
							}
						}
						assertThat(outerCnt, is(1));
						assertThat(innerCnt, is(2));
						assertThat(relation.members.size(), is(3));
					}
					else if (relation.getTagValue("source").endsWith("; 13111-bldg-71799")) {
						assertThat(relation.getTagValue("type"), is("multipolygon"));
						assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-71799"));
						assertThat(relation.getTagValue("addr:full"), is("東京都大田区大森西三丁目"));
						assertThat(relation.getTagValue("addr:ref"), is("13111006003"));
						assertThat(relation.getTagValue("height"), is("30.2"));
						assertThat(relation.getTagValue("ele"), is("3.13"));
						assertThat(relation.getTagValue("start_date"), is("1977"));
						assertThat(relation.getTagValue("building"), is("yes"));
						assertThat(relation.getTagValue("building:levels"), is("3"));

						int outerCnt = 0;
						int innerCnt = 0;
						for (ElementMember mem : relation.members) {
							if (mem.role.equals("outer")) {
								outerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertNotNull(way);
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-71799"));
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
	

	@SuppressWarnings("deprecation")
	@Test
	@Category(DetailTests.class)
	public void testSample_d2_margePart() {
		CitygmlFileTest.test_doRelationMarge(Paths.get("src/test/resources","sample_d_bldg_6697_op2.gml"));
		
        OsmDom osm = new OsmDom();
        try {
			osm.parse(Paths.get("sample_d_bldg_6697_op2_2.osm").toFile());

			assertNotNull(osm.relations);
			int outlineCnt = 0;
			int partCnt = 0;
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				if (relation.isBuilding()) {
					assertThat(relation.getTagValue("type"), is("building"));
					assertThat(relation.getTagValue("building"), is("yes"));
					assertThat(relation.getTagValue("addr:full"), is("東京都大田区大森西三丁目"));
					assertThat(relation.getTagValue("addr:ref"), is("13111006003"));
					assertNotNull(relation.getTagValue("height"));
					assertNotNull(relation.getTagValue("ele"));
					assertNotNull(relation.getTagValue("start_date"));
					assertThat(relation.getTagValue("source"), startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
	
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outline")) {
							outlineCnt++;
							assertThat(mem.type, is("relation"));
							ElementRelation outline = osm.relations.get(mem.ref);
							assertNotNull(outline);
						}
						if (mem.role.equals("part")) {
							partCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertNotNull(way);
						}
					}
				}
				else if (relation.isMultipolygon()) {
					if (relation.getTagValue("source").endsWith("; 13111-bldg-72601")) {
						assertThat(relation.getTagValue("type"), is("multipolygon"));
						assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));
						assertThat(relation.getTagValue("addr:full"), is("東京都大田区大森西三丁目"));
						assertThat(relation.getTagValue("addr:ref"), is("13111006003"));
						assertThat(relation.getTagValue("height"), is("34.7"));
						assertThat(relation.getTagValue("ele"), is("2.682"));
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
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));
								assertThat(way.tags.size(), is(1));
							}
							if (mem.role.equals("inner")) {
								innerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertNotNull(way);
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));
								assertThat(way.tags.size(), is(1));
							}
						}
						assertThat(outerCnt, is(1));
						assertThat(innerCnt, is(2));
						assertThat(relation.members.size(), is(3));
					}
					else if (relation.getTagValue("source").endsWith("; 13111-bldg-71799")) {
						assertThat(relation.getTagValue("type"), is("multipolygon"));
						assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-71799"));
						assertThat(relation.getTagValue("addr:full"), is("東京都大田区大森西三丁目"));
						assertThat(relation.getTagValue("addr:ref"), is("13111006003"));
						assertThat(relation.getTagValue("height"), is("30.2"));
						assertThat(relation.getTagValue("ele"), is("3.126"));
						assertThat(relation.getTagValue("start_date"), is("1977"));
						assertThat(relation.getTagValue("building"), is("yes"));

						int outerCnt = 0;
						int innerCnt = 0;
						for (ElementMember mem : relation.members) {
							if (mem.role.equals("outer")) {
								outerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertNotNull(way);
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-71799"));
								assertThat(way.tags.size(), is(1));
							}
							if (mem.role.equals("inner")) {
								innerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertNotNull(way);
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-71799"));
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
	
	@SuppressWarnings("deprecation")
	@Test
	@Category(DetailTests.class)
	public void testSample_d3_removeSinglePart() {
		CitygmlFileTest.test_doRemoveSinglePart(Paths.get("src/test/resources","sample_d_bldg_6697_op2.gml"));
		
        OsmDom osm = new OsmDom();
        try {
			osm.parse(Paths.get("sample_d_bldg_6697_op2_3.osm").toFile());

			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					assertThat(relation.getTagValue("type"), is("building"));
					assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					assertThat(relation.getTagValue("addr:full"), is("東京都大田区大森西三丁目"));
					assertThat(relation.getTagValue("addr:ref"), is("13111006003"));
					assertNotNull(relation.getTagValue("height"));
					assertNotNull(relation.getTagValue("ele"));
					assertThat(relation.getTagValue("start_date"), is("1976"));
					assertThat(relation.getTagValue("building"), is("yes"));

					int outlineCnt = 0;
					int partCnt = 0;
					int outerCnt = 0;
					int innerCnt = 0;
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outline")) {
							outlineCnt++;
							assertThat(mem.type, is("relation"));
							ElementRelation polygon = osm.relations.get(Long.toString(mem.ref));
							assertNotNull(polygon);
							assertThat(polygon.getTagValue("type"), is("multipolygon"));
							assertThat(polygon.getTagValue("source"), startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
							assertThat(polygon.getTagValue("addr:full"), is("東京都大田区大森西三丁目"));
							assertThat(polygon.getTagValue("addr:ref"), is("13111006003"));
							assertNotNull(polygon.getTagValue("height"));
							assertNotNull(polygon.getTagValue("ele"));
							assertThat(polygon.getTagValue("start_date"), is("1976"));
							assertThat(polygon.getTagValue("building"), is("yes"));
							for (ElementMember member : polygon.members) {
								if (member.role.equals("outer")) {
									outerCnt++;
								}
								if (member.role.equals("inner")) {
									innerCnt++;
								}
							}
						}
						if (mem.role.equals("part")) {
							partCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertNotNull(way);
							if (way.getTagValue("source").endsWith("; 13111-bldg-72601")) {
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.getTagValue("building:levels"), is("2"));
								assertThat(way.getTagValue("building:levels:underground"), is("1"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区大森西三丁目"));
								assertThat(way.getTagValue("addr:ref"), is("13111006003"));
								assertThat(way.getTagValue("height"), is("34.7"));
								assertThat(way.getTagValue("ele"), is("2.68"));
								assertThat(way.getTagValue("start_date"), is("1976"));
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));
								assertThat(way.tags.size(), is(9));
							}
							else if (way.getTagValue("source").endsWith("; 13111-bldg-71799")) {
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-71799"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区大森西三丁目"));
								assertThat(way.getTagValue("addr:ref"), is("13111006003"));
								assertThat(way.getTagValue("height"), is("30.2"));
								assertThat(way.getTagValue("ele"), is("3.13"));
								assertThat(way.getTagValue("start_date"), is("1977"));
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.getTagValue("building:levels"), is("3"));
								assertThat(way.tags.size(), is(8));
							}
						}
					}
					assertThat(innerCnt, is(2));
					assertThat(outerCnt, is(1));
					assertThat(outlineCnt, is(1));
					assertThat(partCnt, is(2));
					assertThat(relation.members.size(), is(3));
				}
			}
			assertThat(osm.relations.size(), is(2));
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	@Category(DetailTests.class)
	public void testSample_d4_createOutline() {
		CitygmlFileTest.test4_doCreateOutline(Paths.get("src/test/resources","sample_d_bldg_6697_op2.gml"));
		
        OsmDom osm = new OsmDom();
        try {
			osm.parse(Paths.get("sample_d_bldg_6697_op2_4.osm").toFile());

			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					assertThat(relation.getTagValue("type"), is("building"));
					assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					assertThat(relation.getTagValue("addr:full"), is("東京都大田区大森西三丁目"));
					assertThat(relation.getTagValue("addr:ref"), is("13111006003"));
					assertThat(relation.getTagValue("height"), is("34.7"));
					assertThat(relation.getTagValue("ele"), is("2.68"));
					assertThat(relation.getTagValue("start_date"), is("1976"));
					assertThat(relation.getTagValue("building"), is("yes"));
					assertThat(relation.getTagValue("building:levels"), is("3"));
					assertThat(relation.getTagValue("building:levels:underground"), is("1"));
					assertThat(relation.tags.size(), is(10));

					int outlineCnt = 0;
					int partCnt = 0;
					int outerCnt = 0;
					int innerCnt = 0;
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outline")) {
							outlineCnt++;
							assertThat(mem.type, is("relation"));
							ElementRelation polygon = osm.relations.get(Long.toString(mem.ref));
							assertNotNull(polygon);
							assertThat(polygon.getTagValue("type"), is("multipolygon"));
							assertThat(polygon.getTagValue("source"), startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
							assertThat(polygon.getTagValue("addr:full"), is("東京都大田区大森西三丁目"));
							assertThat(polygon.getTagValue("addr:ref"), is("13111006003"));
							assertThat(polygon.getTagValue("height"), is("34.7"));
							assertThat(polygon.getTagValue("ele"), is("2.68"));
							assertThat(polygon.getTagValue("start_date"), is("1976"));
							assertThat(polygon.getTagValue("building"), is("yes"));
							for (ElementMember member : polygon.members) {
								if (member.role.equals("outer")) {
									outerCnt++;
									assertThat(member.type, is("way"));
									ElementWay way = osm.ways.get(Long.toString(member.ref));
									assertNotNull(way);
									assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
									assertThat(way.tags.size(), is(1));
								}
								if (member.role.equals("inner")) {
									innerCnt++;
									assertThat(member.type, is("way"));
									ElementWay way = osm.ways.get(Long.toString(member.ref));
									assertNotNull(way);
									assertThat(way.getTagValue("source"), startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; "));
									assertThat(way.tags.size(), is(1));
								}
							}
							assertThat(polygon.members.size(), is(3));
						}
						if (mem.role.equals("part")) {
							partCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertNotNull(way);
							if (way.getTagValue("source").endsWith("; 13111-bldg-72601")) {
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.getTagValue("building:levels"), is("2"));
								assertThat(way.getTagValue("building:levels:underground"), is("1"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区大森西三丁目"));
								assertThat(way.getTagValue("addr:ref"), is("13111006003"));
								assertThat(way.getTagValue("height"), is("34.7"));
								assertThat(way.getTagValue("ele"), is("2.68"));
								assertThat(way.getTagValue("start_date"), is("1976"));
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));
								assertThat(way.tags.size(), is(9));
							}
							else if (way.getTagValue("source").endsWith("; 13111-bldg-71799")) {
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-71799"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区大森西三丁目"));
								assertThat(way.getTagValue("addr:ref"), is("13111006003"));
								assertThat(way.getTagValue("height"), is("30.2"));
								assertThat(way.getTagValue("ele"), is("3.13"));
								assertThat(way.getTagValue("start_date"), is("1977"));
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.getTagValue("building:levels"), is("3"));
								assertThat(way.tags.size(), is(8));
							}
						}
					}
					assertThat(innerCnt, is(2));
					assertThat(outerCnt, is(1));
					assertThat(outlineCnt, is(1));
					assertThat(partCnt, is(2));
					assertThat(relation.members.size(), is(3));
				}
			}
			assertThat(osm.relations.size(), is(2));
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
    
	/*
 	map "13111-bldg-71799 : building" as building {
	 bldg:measuredHeight => 30.2
	 bldg:yearOfConstruction => 1977
	 bldg:storeysAboveGround => 3
	 gml:posList => 6.038
	 xAL:LocalityName => 東京都大田区大森西三丁目
	}
 	map "13111-bldg-72601 : building" as building {
	 bldg:measuredHeight => 34.7
	 bldg:yearOfConstruction => 1976
	 bldg:storeysAboveGround => 2
	 bldg:storeysBelowGround => 1
	 bldg:lod1Solid => 2.682
	 xAL:LocalityName => 東京都大田区大森西三丁目
	}
	*/
	@Test
	public void testSample_d() {
		CitygmlFileTest.test(Paths.get("src/test/resources","sample_d_bldg_6697_op2.gml"));
		
        OsmDom osm = new OsmDom();
        try {
			osm.parse(Paths.get("sample_d_bldg_6697_op2.osm").toFile());
			
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				if (relation.isBuilding()) {
					/*
					 	map "building : Relation" as building {
						 type => building
						 building => yes
						 height => 32.852000000000004
						 source => MLIT_PLATEAU;\n http://www.opengis.net/def/crs/EPSG/0/6697
						}
					 */
					assertThat(relation.getTagValue("type"), is("building"));
					assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					assertThat(relation.getTagValue("addr:full"), is("東京都大田区大森西三丁目"));
					assertThat(relation.getTagValue("addr:ref"), is("13111006003"));
					assertThat(relation.getTagValue("height"), is("34.7"));
					assertEquals("2.68", relation.getTagValue("ele"));
					assertThat(relation.getTagValue("start_date"), is("1976"));
					assertThat(relation.getTagValue("building"), is("yes"));
					assertThat(relation.getTagValue("building:levels"), is("3"));
					assertThat(relation.getTagValue("building:levels:underground"), is("1"));
					assertEquals(10, relation.tags.size());

					int outlineCnt = 0;
					int partCnt = 0;
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outline")) {
							outlineCnt++;
							assertEquals("relation", mem.type);
							ElementRelation multiporygon = osm.relations.get(Long.toString(mem.ref));
							assertNotNull(multiporygon);
						}
						if (mem.role.equals("part")) {
							partCnt++;
							assertEquals("way", mem.type);
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertNotNull(way);
							if (way.getTagValue("source").endsWith("; 13111-bldg-72601")) {
								assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601", way.getTagValue("source"));
								assertEquals("東京都大田区大森西三丁目", way.getTagValue("addr:full"));
								assertEquals("13111006003", way.getTagValue("addr:ref"));
								assertEquals("34.7", way.getTagValue("height"));
								assertEquals("2.68", way.getTagValue("ele"));
								assertEquals("1976", way.getTagValue("start_date"));
								assertEquals("yes", way.getTagValue("building:part"));
								assertEquals("2", way.getTagValue("building:levels"));
								assertEquals("1", way.getTagValue("building:levels:underground"));
								assertEquals(9, way.tags.size());
							}
							else if (way.getTagValue("source").endsWith("; 13111-bldg-71799")) {
								assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-71799", way.getTagValue("source"));
								assertEquals("東京都大田区大森西三丁目", way.getTagValue("addr:full"));
								assertEquals("13111006003", way.getTagValue("addr:ref"));
								assertEquals("30.2", way.getTagValue("height"));
								assertEquals("3.13", way.getTagValue("ele"));
								assertEquals("1977", way.getTagValue("start_date"));
								assertEquals("yes", way.getTagValue("building:part"));
								assertEquals("3", way.getTagValue("building:levels"));
								assertEquals(8, way.tags.size());
							}
						}
					}
					assertEquals(1, outlineCnt);
					assertEquals(2, partCnt);
					assertEquals(3, relation.members.size());
				}
				else if (relation.isMultipolygon()) {
					/*
						map "multipolygon :Relation" as multipolygon {
						 type => multipolygon
						 building => yes
						 height => 32.852000000000004
						 source => MLIT_PLATEAU;\n http://www.opengis.net/def/crs/EPSG/0/6697
						}
					 */
					assertEquals("multipolygon", relation.getTagValue("type"));
					assertEquals("yes", relation.getTagValue("building"));
					assertEquals("3", relation.getTagValue("building:levels"));
					assertEquals("1", relation.getTagValue("building:levels:underground"));
					assertEquals("34.7", relation.getTagValue("height"));
					assertEquals("2.68", relation.getTagValue("ele"));
					assertEquals("1976", relation.getTagValue("start_date"));
					assertThat(relation.getTagValue("addr:full"), is("東京都大田区大森西三丁目"));
					assertThat(relation.getTagValue("addr:ref"), is("13111006003"));
					assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					assertEquals(10, relation.tags.size());
					int outerCnt = 0;
					int innerCnt = 0;
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outer")) {
							outerCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertNotNull(way);
							assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
							assertThat(way.tags.size(), is(1));
						}
						if (mem.role.equals("inner")) {
							innerCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertNotNull(way);
							assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));
							assertThat(way.tags.size(), is(1));
						}
					}
					assertThat(outerCnt, is(1));
					assertThat(innerCnt, is(2));
					assertThat(relation.members.size(), is(3));
				}
			}
			assertThat(osm.relations.size(), is(2));
			assertThat(osm.ways.size(), is(5));
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
