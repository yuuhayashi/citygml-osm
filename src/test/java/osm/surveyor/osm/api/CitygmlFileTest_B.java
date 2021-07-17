package osm.surveyor.osm.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.nio.file.Paths;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.ElementMember;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_B {

	@Test
	public void testSample_b() {
		CitygmlFileTest.test(Paths.get("src/test/resources","sample_b_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.parse(Paths.get("sample_b_bldg_6697_op2.osm").toFile());
			
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("multipolygon")) {
					/*
						map "multipolygon :Relation" as multipolygon {
						 type => multipolygon
						 building => yes
						 height => 17.582
						 source => MLIT_PLATEAU;\n http://www.opengis.net/def/crs/EPSG/0/6697
						}
					 */
					assertEquals("multipolygon", relation.getTagValue("type"));
					assertEquals("yes", relation.getTagValue("building"));
					assertEquals("東京都大田区大森西五丁目", relation.getTagValue("addr:full"));
					assertEquals("13111006005", relation.getTagValue("addr:ref"));
					assertEquals("16.9", relation.getTagValue("height"));
					assertEquals("2.51", relation.getTagValue("ele"));
					assertEquals("1976", relation.getTagValue("start_date"));
					assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697", relation.getTagValue("source"));
					int outerCnt = 0;
					int innerCnt = 0;
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outer")) {
							outerCnt++;
							assertEquals("way", mem.type);
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697", way.getTagValue("source"));
							assertEquals(1, way.tags.size());
						}
						if (mem.role.equals("inner")) {
							innerCnt++;
							assertEquals("way", mem.type);
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-61384", way.getTagValue("source"));
							assertEquals(1, way.tags.size());
						}
					}
					assertEquals(1, outerCnt);
					assertEquals(1, innerCnt);
					assertEquals(2, relation.members.size());
				}
			}
			assertEquals(1, osm.relations.size());
			assertEquals(2, osm.ways.size());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	@Test
	@Category(DetailTests.class)
	public void testSample_b1_parse() {
		CitygmlFileTest.test_doParse(Paths.get("src/test/resources","sample_b_bldg_6697_op2.gml"));
		
        try {
            OsmDom osm = new OsmDom();
			osm.parse(Paths.get("sample_b_bldg_6697_op2_1.osm").toFile());

			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("multipolygon")) {
					int outerCnt = 0;
					int innerCnt = 0;
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outer")) {
							outerCnt++;
						}
						if (mem.role.equals("inner")) {
							innerCnt++;
						}
					}
					assertEquals(1, outerCnt);
					assertEquals(1, innerCnt);
					assertEquals(2, relation.members.size());
				}
				else {
					assertEquals("building", type);
				}
			}
			assertEquals(2, osm.relations.size());
			
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	@Test
	@Category(DetailTests.class)
	public void testSample_b2_margePart() {
		CitygmlFileTest.test_doRelationMarge(Paths.get("src/test/resources","sample_b_bldg_6697_op2.gml"));
		
        try {
            OsmDom osm = new OsmDom();
			osm.parse(Paths.get("sample_b_bldg_6697_op2_2.osm").toFile());

			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("multipolygon")) {
					int outerCnt = 0;
					int innerCnt = 0;
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outer")) {
							outerCnt++;
						}
						if (mem.role.equals("inner")) {
							innerCnt++;
						}
					}
					assertEquals(1, outerCnt);
					assertEquals(1, innerCnt);
					assertEquals(2, relation.members.size());
				}
				else {
					assertEquals("building", type);
				}
			}
			assertEquals(2, osm.relations.size());
			
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	@Test
	@Category(DetailTests.class)
	public void testSample_b3_removeSinglePart() {
		CitygmlFileTest.test_doRemoveSinglePart(Paths.get("src/test/resources","sample_b_bldg_6697_op2.gml"));
		
        try {
            OsmDom osm = new OsmDom();
			osm.parse(Paths.get("sample_b_bldg_6697_op2_3.osm").toFile());

			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("multipolygon")) {
					int outerCnt = 0;
					int innerCnt = 0;
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outer")) {
							outerCnt++;
						}
						if (mem.role.equals("inner")) {
							innerCnt++;
						}
					}
					assertEquals(1, outerCnt);
					assertEquals(1, innerCnt);
					assertEquals(2, relation.members.size());
				}
				else {
					assertEquals("building", type);
				}
			}
			assertEquals(2, osm.relations.size());
			
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
	

	@Test
	@Category(DetailTests.class)
	public void testSample_b4_createOutline() {
		CitygmlFileTest.test4_doCreateOutline(Paths.get("src/test/resources","sample_b_bldg_6697_op2.gml"));
		
        try {
            OsmDom osm = new OsmDom();
			osm.parse(Paths.get("sample_b_bldg_6697_op2_4.osm").toFile());

			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("multipolygon")) {
					int outerCnt = 0;
					int innerCnt = 0;
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outer")) {
							outerCnt++;
						}
						if (mem.role.equals("inner")) {
							innerCnt++;
						}
					}
					assertEquals(1, outerCnt);
					assertEquals(1, innerCnt);
					assertEquals(2, relation.members.size());
				}
				else {
					assertEquals("building", type);
				}
			}
			assertEquals(2, osm.relations.size());
			
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
