package osm.surveyor.osm.api;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.nio.file.Paths;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import osm.surveyor.DetailTests;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_D {

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
						assertEquals(relation.getTagValue("type"), ("building"));
						assertEquals(relation.getTagValue("building"), ("yes"));
						assertEquals(relation.getTagValue("building:levels"), ("2"));
						assertEquals(relation.getTagValue("building:levels:underground"), ("1"));
						assertEquals(relation.getTagValue("height"), ("34.7"));
						assertEquals(relation.getTagValue("ele"), ("2.68"));
						assertEquals(relation.getTagValue("start_date"), ("1976"));
						assertEquals(relation.getTagValue("addr:full"), ("東京都大田区大森西三丁目"));
						assertEquals(relation.getTagValue("addr:ref"), ("13111006003"));
						assertEquals(relation.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));

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
								assertEquals(outline.getTagValue("height"), ("34.7"));
								assertEquals(outline.getTagValue("ele"), ("2.68"));
								assertEquals(outline.getTagValue("start_date"), ("1976"));
								assertEquals(outline.getTagValue("addr:full"), ("東京都大田区大森西三丁目"));
								assertEquals(outline.getTagValue("addr:ref"), ("13111006003"));
								assertEquals(outline.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));
								assertEquals(outline.getTagList().size(), is(10));
							}
							if (mem.getRole().equals("part")) {
								partCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("height"), ("34.7"));
								assertEquals(way.getTagValue("ele"), ("2.68"));
								assertEquals(way.getTagValue("start_date"), ("1976"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区大森西三丁目"));
								assertEquals(way.getTagValue("addr:ref"), ("13111006003"));
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));
								assertEquals(way.getTagList().size() >= 6, is(true));
							}
						}
						assertEquals(outlineCnt, is(1));
						assertEquals(partCnt, is(1));
						assertEquals(relation.members.size(), is(2));
					}
					else if (relation.getTagValue("source").endsWith("; 13111-bldg-71799")) {
						assertEquals(relation.getTagValue("type"), ("building"));
						assertEquals(relation.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-71799"));
						assertEquals(relation.getTagValue("addr:full"), ("東京都大田区大森西三丁目"));
						assertEquals(relation.getTagValue("addr:ref"), ("13111006003"));
						assertEquals(relation.getTagValue("height"), ("30.2"));
						assertEquals(relation.getTagValue("ele"), ("3.13"));
						assertEquals(relation.getTagValue("start_date"), ("1977"));
						assertEquals(relation.getTagValue("building"), ("yes"));
						assertEquals(relation.getTagValue("building:levels"), ("3"));

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
								assertEquals(outline.getTagValue("addr:full"), ("東京都大田区大森西三丁目"));
								assertEquals(outline.getTagValue("addr:ref"), ("13111006003"));
								assertEquals(outline.getTagValue("height"), ("30.2"));
								assertEquals(outline.getTagValue("start_date"), ("1977"));
								assertEquals(outline.getTagValue("ele"), ("3.13"));
								assertEquals(outline.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-71799"));
								assertEquals(outline.getTagList().size(), is(9));
							}
							if (mem.getRole().equals("part")) {
								partCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区大森西三丁目"));
								assertEquals(way.getTagValue("addr:ref"), ("13111006003"));
								assertEquals(way.getTagValue("height"), ("30.2"));
								assertEquals(way.getTagValue("ele"), ("3.13"));
								assertEquals(way.getTagValue("start_date"), ("1977"));
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-71799"));
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
					if (relation.getTagValue("source").endsWith("; 13111-bldg-72601")) {
						assertEquals(relation.getTagValue("type"), ("multipolygon"));
						assertEquals(relation.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));
						assertEquals(relation.getTagValue("addr:full"), ("東京都大田区大森西三丁目"));
						assertEquals(relation.getTagValue("addr:ref"), ("13111006003"));
						assertEquals(relation.getTagValue("height"), ("34.7"));
						assertEquals(relation.getTagValue("ele"), ("2.68"));
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
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));
								assertEquals(way.getTagList().size(), is(1));
							}
							if (mem.getRole().equals("inner")) {
								innerCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));
								assertEquals(way.getTagList().size(), is(1));
							}
						}
						assertEquals(outerCnt, is(1));
						assertEquals(innerCnt, is(2));
						assertEquals(relation.members.size(), is(3));
					}
					else if (relation.getTagValue("source").endsWith("; 13111-bldg-71799")) {
						assertEquals(relation.getTagValue("type"), ("multipolygon"));
						assertEquals(relation.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-71799"));
						assertEquals(relation.getTagValue("addr:full"), ("東京都大田区大森西三丁目"));
						assertEquals(relation.getTagValue("addr:ref"), ("13111006003"));
						assertEquals(relation.getTagValue("height"), ("30.2"));
						assertEquals(relation.getTagValue("ele"), ("3.13"));
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
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-71799"));
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
	public void testSample_d2_margePart() {
		CitygmlFileTest.test_doRelationMarge(Paths.get("src/test/resources","sample_d_bldg_6697_op2.gml"));
		
        OsmDom osm = new OsmDom();
        try {
			osm.parse(Paths.get("sample_d_bldg_6697_op2_2.osm").toFile());

			assertNotNull(osm.relations);
			int partCnt = 0;
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				if (relation.isBuilding()) {
					assertEquals(relation.getTagValue("type"), ("building"));
					assertEquals(relation.getTagValue("building"), ("yes"));
					assertEquals(relation.getTagValue("addr:full"), ("東京都大田区大森西三丁目"));
					assertEquals(relation.getTagValue("addr:ref"), ("13111006003"));
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
					if (relation.getTagValue("source").endsWith("; 13111-bldg-72601")) {
						assertEquals(relation.getTagValue("type"), ("multipolygon"));
						assertEquals(relation.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));
						assertEquals(relation.getTagValue("addr:full"), ("東京都大田区大森西三丁目"));
						assertEquals(relation.getTagValue("addr:ref"), ("13111006003"));
						assertEquals(relation.getTagValue("height"), ("34.7"));
						assertEquals(relation.getTagValue("ele"), ("2.682"));
						assertEquals(relation.getTagValue("building"), ("yes"));

						int outerCnt = 0;
						int innerCnt = 0;
						for (MemberBean mem : relation.members) {
							if (mem.getRole().equals("outer")) {
								outerCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));
								assertEquals(way.getTagList().size(), is(1));
							}
							if (mem.getRole().equals("inner")) {
								innerCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));
								assertEquals(way.getTagList().size(), is(1));
							}
						}
						assertEquals(outerCnt, is(1));
						assertEquals(innerCnt, is(2));
						assertEquals(relation.members.size(), is(3));
					}
					else if (relation.getTagValue("source").endsWith("; 13111-bldg-71799")) {
						assertEquals(relation.getTagValue("type"), ("multipolygon"));
						assertEquals(relation.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-71799"));
						assertEquals(relation.getTagValue("addr:full"), ("東京都大田区大森西三丁目"));
						assertEquals(relation.getTagValue("addr:ref"), ("13111006003"));
						assertEquals(relation.getTagValue("height"), ("30.2"));
						assertEquals(relation.getTagValue("ele"), ("3.126"));
						assertEquals(relation.getTagValue("building"), ("yes"));

						int outerCnt = 0;
						int innerCnt = 0;
						for (MemberBean mem : relation.members) {
							if (mem.getRole().equals("outer")) {
								outerCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-71799"));
								assertEquals(way.getTagList().size(), is(1));
							}
							if (mem.getRole().equals("inner")) {
								innerCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-71799"));
								assertEquals(way.getTagList().size(), is(1));
							}
						}
						assertEquals(outerCnt, is(1));
						assertEquals(innerCnt, is(0));
						assertEquals(relation.members.size(), is(1));
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
					assertEquals(relation.getTagValue("type"), ("building"));
					assertEquals(relation.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					assertEquals(relation.getTagValue("addr:full"), ("東京都大田区大森西三丁目"));
					assertEquals(relation.getTagValue("addr:ref"), ("13111006003"));
					assertNotNull(relation.getTagValue("height"));
					assertNotNull(relation.getTagValue("ele"));
					assertNull(relation.getTagValue("start_date"));		// Issue #39
					assertEquals(relation.getTagValue("building"), ("yes"));

					int outlineCnt = 0;
					int partCnt = 0;
					int outerCnt = 0;
					int innerCnt = 0;
					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outline")) {
							outlineCnt++;
							assertEquals(mem.getType(), ("relation"));
							ElementRelation polygon = osm.relations.get(Long.toString(mem.getRef()));
							assertNotNull(polygon);
							assertEquals(polygon.getTagValue("type"), ("multipolygon"));
							assertEquals(polygon.getTagValue("source"), startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
							assertEquals(polygon.getTagValue("addr:full"), ("東京都大田区大森西三丁目"));
							assertEquals(polygon.getTagValue("addr:ref"), ("13111006003"));
							assertNotNull(polygon.getTagValue("height"));
							assertNotNull(polygon.getTagValue("ele"));
							assertEquals(polygon.getTagValue("building"), ("yes"));
							for (MemberBean member : polygon.members) {
								if (member.getRole().equals("outer")) {
									outerCnt++;
								}
								if (member.getRole().equals("inner")) {
									innerCnt++;
								}
							}
						}
						if (mem.getRole().equals("part")) {
							partCnt++;
							assertEquals(mem.getType(), ("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							if (way.getTagValue("source").endsWith("; 13111-bldg-72601")) {
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels"), ("2"));
								assertEquals(way.getTagValue("building:levels:underground"), ("1"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区大森西三丁目"));
								assertEquals(way.getTagValue("addr:ref"), ("13111006003"));
								assertEquals(way.getTagValue("height"), ("34.7"));
								assertEquals(way.getTagValue("ele"), ("2.68"));
								assertEquals(way.getTagValue("start_date"), ("1976"));
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));
								assertEquals(way.getTagList().size(), is(9));
							}
							else if (way.getTagValue("source").endsWith("; 13111-bldg-71799")) {
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-71799"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区大森西三丁目"));
								assertEquals(way.getTagValue("addr:ref"), ("13111006003"));
								assertEquals(way.getTagValue("height"), ("30.2"));
								assertEquals(way.getTagValue("ele"), ("3.13"));
								assertEquals(way.getTagValue("start_date"), ("1977"));
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels"), ("3"));
								assertEquals(way.getTagList().size(), is(8));
							}
						}
					}
					assertEquals(innerCnt, is(2));
					assertEquals(outerCnt, is(1));
					assertEquals(outlineCnt, is(1));
					assertEquals(partCnt, is(2));
					assertEquals(relation.members.size(), is(3));
				}
			}
			assertEquals(osm.relations.size(), is(2));
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

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
					assertEquals(relation.getTagValue("type"), ("building"));
					assertEquals(relation.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					assertEquals(relation.getTagValue("addr:full"), ("東京都大田区大森西三丁目"));
					assertEquals(relation.getTagValue("addr:ref"), ("13111006003"));
					assertEquals(relation.getTagValue("height"), ("34.7"));
					assertEquals(relation.getTagValue("ele"), ("2.68"));
					assertEquals(relation.getTagValue("building"), ("yes"));
					assertEquals(relation.getTagValue("building:levels"), ("3"));
					assertEquals(relation.getTagValue("building:levels:underground"), ("1"));
					assertEquals(9, relation.getTagList().size());

					int outlineCnt = 0;
					int partCnt = 0;
					int outerCnt = 0;
					int innerCnt = 0;
					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outline")) {
							outlineCnt++;
							assertEquals(mem.getType(), ("relation"));
							ElementRelation polygon = osm.relations.get(Long.toString(mem.getRef()));
							assertNotNull(polygon);
							assertEquals(polygon.getTagValue("type"), ("multipolygon"));
							assertEquals(polygon.getTagValue("source"), startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
							assertEquals(polygon.getTagValue("addr:full"), ("東京都大田区大森西三丁目"));
							assertEquals(polygon.getTagValue("addr:ref"), ("13111006003"));
							assertEquals(polygon.getTagValue("height"), ("34.7"));
							assertEquals(polygon.getTagValue("ele"), ("2.68"));
							assertEquals(polygon.getTagValue("building"), ("yes"));
							for (MemberBean member : polygon.members) {
								if (member.getRole().equals("outer")) {
									outerCnt++;
									assertEquals(member.getType(), ("way"));
									ElementWay way = osm.ways.get(Long.toString(member.getRef()));
									assertNotNull(way);
									assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
									assertEquals(way.getTagList().size(), is(1));
								}
								if (member.getRole().equals("inner")) {
									innerCnt++;
									assertEquals(member.getType(), ("way"));
									ElementWay way = osm.ways.get(Long.toString(member.getRef()));
									assertNotNull(way);
									assertEquals(way.getTagValue("source"), startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; "));
									assertEquals(way.getTagList().size(), is(1));
								}
							}
							assertEquals(polygon.members.size(), is(3));
						}
						if (mem.getRole().equals("part")) {
							partCnt++;
							assertEquals(mem.getType(), ("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							if (way.getTagValue("source").endsWith("; 13111-bldg-72601")) {
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels"), ("2"));
								assertEquals(way.getTagValue("building:levels:underground"), ("1"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区大森西三丁目"));
								assertEquals(way.getTagValue("addr:ref"), ("13111006003"));
								assertEquals(way.getTagValue("height"), ("34.7"));
								assertEquals(way.getTagValue("ele"), ("2.68"));
								assertEquals(way.getTagValue("start_date"), ("1976"));
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));
								assertEquals(way.getTagList().size(), is(9));
							}
							else if (way.getTagValue("source").endsWith("; 13111-bldg-71799")) {
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-71799"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区大森西三丁目"));
								assertEquals(way.getTagValue("addr:ref"), ("13111006003"));
								assertEquals(way.getTagValue("height"), ("30.2"));
								assertEquals(way.getTagValue("ele"), ("3.13"));
								assertEquals(way.getTagValue("start_date"), ("1977"));
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels"), ("3"));
								assertEquals(way.getTagList().size(), is(8));
							}
						}
					}
					assertEquals(innerCnt, is(2));
					assertEquals(outerCnt, is(1));
					assertEquals(outlineCnt, is(1));
					assertEquals(partCnt, is(2));
					assertEquals(relation.members.size(), is(3));
				}
			}
			assertEquals(osm.relations.size(), is(2));
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
					assertEquals("building", relation.getTagValue("type"));
					assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697", relation.getTagValue("source"));
					assertEquals("東京都大田区大森西三丁目", relation.getTagValue("addr:full"));
					assertEquals("13111006003", relation.getTagValue("addr:ref"));
					assertEquals("34.7", relation.getTagValue("height"));
					assertEquals("2.68", relation.getTagValue("ele"));
					assertEquals("yes", relation.getTagValue("building"));
					assertEquals("3", relation.getTagValue("building:levels"));
					assertEquals("1", relation.getTagValue("building:levels:underground"));
					assertNull(relation.getTagValue("start_date"));		// Issue #39
					assertEquals(9, relation.getTagList().size());

					int outlineCnt = 0;
					int partCnt = 0;
					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outline")) {
							outlineCnt++;
							assertEquals("relation", mem.getType());
							ElementRelation multiporygon = osm.relations.get(Long.toString(mem.getRef()));
							assertNotNull(multiporygon);
						}
						if (mem.getRole().equals("part")) {
							partCnt++;
							assertEquals("way", mem.getType());
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
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
								assertEquals(9, way.getTagList().size());
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
								assertEquals(8, way.getTagList().size());
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
					assertEquals("東京都大田区大森西三丁目", relation.getTagValue("addr:full"));
					assertEquals("13111006003", relation.getTagValue("addr:ref"));
					assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697", relation.getTagValue("source"));
					assertTrue(9 < relation.getTagList().size());
					int outerCnt = 0;
					int innerCnt = 0;
					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outer")) {
							outerCnt++;
							assertEquals("way", mem.getType());
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697", way.getTagValue("source"));
							assertEquals(1, way.getTagList().size());
						}
						if (mem.getRole().equals("inner")) {
							innerCnt++;
							assertEquals("way", mem.getType());
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601", way.getTagValue("source"));
							assertEquals(1, way.getTagList().size());
						}
					}
					assertEquals(1, outerCnt);
					assertEquals(2, innerCnt);
					assertEquals(3, relation.members.size());
				}
			}
			assertEquals(2, osm.relations.size());
			assertEquals(5, osm.ways.size());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
