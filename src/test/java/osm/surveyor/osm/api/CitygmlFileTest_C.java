package osm.surveyor.osm.api;

import static org.hamcrest.CoreMatchers.startsWith;

import org.junit.Test;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.TagBean;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_C extends CitygmlFileTest {
	
	@Test
	public void testSample_c() {
        OsmDom osm = testdo("./src/test/resources/sample_c_bldg_6697_op2.gml");
        try {
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
	    OsmDom osm = testdo("./src/test/resources/sample_cc_bldg_6697_op2.gml");
	    try {
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
