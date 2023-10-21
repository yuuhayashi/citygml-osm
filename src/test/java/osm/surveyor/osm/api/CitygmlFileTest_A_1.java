package osm.surveyor.osm.api;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_A_1 extends CitygmlFileTest1 {

	@Test
	@Category(DetailTests.class)
	public void testSample_a1_parse() {
	    try {
	        OsmDom osm = testdo("./src/test/resources/sample_a_bldg_6697_op2.gml");
			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					if (relation.getTagValue("ele").endsWith("2.8")) {
						assertEquals(relation.getTagValue("type"), ("building"));
						assertEquals(relation.getTagValue("building"), ("yes"));
						assertNull(relation.getTagValue("addr:full"));
						assertEquals("2.4", relation.getTagValue("height"));
	
						int outlineCnt = 0;
						int partCnt = 0;
						for (MemberBean mem : relation.members) {
							if (mem.getRole().equals("outline")) {
								outlineCnt++;
								assertEquals(mem.getType(), ("relation"));
								ElementRelation outline = osm.relations.get(mem.getRef());
								assertNotNull(outline);
								assertEquals(outline.getTagValue("type"), ("multipolygon"));
								//assertEquals("yes", outline.getTagValue("building"));
								assertNull(outline.getTagValue("addr:full"));
								assertEquals(outline.getTagValue("height"), ("2.4"));
								assertEquals("2.8", outline.getTagValue("ele"));
								assertEquals(4, outline.getTagList().size());
							}
							if (mem.getRole().equals("part")) {
								partCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertNull(way.getTagValue("addr:full"));
								assertEquals(way.getTagValue("height"), ("2.4"));
								assertEquals("2.8", way.getTagValue("ele"));
								assertEquals(way.getTagValue("ref:MLIT_PLATEAU"), ("13111-bldg-365"));
								assertEquals(4, way.getTagList().size());
							}
						}
						assertEquals(1, outlineCnt);
						assertEquals(1, partCnt);
					}
					else if (relation.getTagValue("ele").endsWith("2.7")) {
						assertEquals(relation.getTagValue("type"), ("building"));
						assertNull(relation.getTagValue("addr:full"));
						assertEquals("4.6", relation.getTagValue("height"));
						assertEquals("2.7", relation.getTagValue("ele"));
						assertEquals(relation.getTagValue("start_date"), ("1976"));
						assertEquals(relation.getTagValue("building"), ("house"));
						assertEquals(relation.getTagValue("building:levels"), ("2"));
						assertEquals(relation.getTagValue("building:levels:underground"), ("1"));
	
						int outlineCnt = 0;
						int partCnt = 0;
						for (MemberBean mem : relation.members) {
							if (mem.getRole().equals("outline")) {
								outlineCnt++;
								assertEquals(mem.getType(), ("relation"));
								ElementRelation outline = osm.relations.get(mem.getRef());
								assertNotNull(outline);
								assertEquals(outline.getTagValue("type"), ("multipolygon"));
								//assertThat(outline.getTagValue("building"), is("house"));
								assertEquals(outline.getTagValue("building:levels"), ("2"));
								assertEquals(outline.getTagValue("building:levels:underground"), ("1"));
								assertNull(outline.getTagValue("addr:full"));
								assertEquals(outline.getTagValue("height"), ("4.6"));
								assertEquals("2.7", outline.getTagValue("ele"));
								//assertThat(outline.getTagValue("start_date"), is("1976"));
								assertEquals(6, outline.getTagList().size());
							}
							if (mem.getRole().equals("part")) {
								partCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertEquals(way.getTagValue("ref:MLIT_PLATEAU"), ("13111-bldg-466"));
								assertNull(way.getTagValue("addr:full"));
								assertEquals(way.getTagValue("height"), ("4.6"));
								assertEquals("2.7", way.getTagValue("ele"));
								assertEquals(way.getTagValue("start_date"), ("1976"));
								assertEquals(way.getTagValue("building:part"), ("house"));
								assertEquals(way.getTagValue("building:levels"), ("2"));
								assertEquals(way.getTagValue("building:levels:underground"), ("1"));
								assertEquals(7, way.getTagList().size());
							}
						}
						assertEquals(1, outlineCnt);
						assertEquals(1, partCnt);
					}
					assertEquals(2, relation.members.size());
				}
				else if (type.equals("multipolygon")) {
					if (relation.getTagValue("ele").endsWith("2.8")) {
						assertEquals(relation.getTagValue("type"), ("multipolygon"));
						assertNull(relation.getTagValue("addr:full"));	// "東京都大田区南六郷三丁目"
						assertEquals(relation.getTagValue("height"), ("2.4"));
						assertEquals("2.8", relation.getTagValue("ele"));
						//assertThat(relation.getTagValue("building"), is("yes"));
	
						int outerCnt = 0;
						int innerCnt = 0;
						for (MemberBean mem : relation.members) {
							if (mem.getRole().equals("outer")) {
								outerCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertEquals(way.getTagValue("ref:MLIT_PLATEAU"), ("13111-bldg-365"));
								assertEquals(1, way.getTagList().size());
							}
							if (mem.getRole().equals("inner")) {
								innerCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertEquals(way.getTagValue("ref:MLIT_PLATEAU"), ("13111-bldg-365"));
								assertEquals(1, way.getTagList().size());
							}
						}
						assertEquals(1, outerCnt);
						assertEquals(0, innerCnt);
					}
					else if (relation.getTagValue("ele").endsWith("2.7")) {
						assertEquals(relation.getTagValue("type"), ("multipolygon"));
						assertNull(relation.getTagValue("addr:full"));	// "東京都大田区南六郷三丁目"
						assertEquals(relation.getTagValue("height"), ("4.6"));
						assertEquals("2.7", relation.getTagValue("ele"));
						//assertThat(relation.getTagValue("start_date"), is("1976"));
						//assertThat(relation.getTagValue("building"), is("house"));
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
								assertEquals(way.getTagValue("ref:MLIT_PLATEAU"), ("13111-bldg-466"));
								assertEquals(1, way.getTagList().size());
							}
							if (mem.getRole().equals("inner")) {
								innerCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertEquals(way.getTagValue("ref:MLIT_PLATEAU"), ("13111-bldg-466"));
								assertEquals(1, way.getTagList().size());
							}
						}
						assertEquals(1, outerCnt);
						assertEquals(0, innerCnt);
					}
					assertEquals(1, relation.members.size());
				}
			}
			assertEquals(4, osm.relations.size());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
