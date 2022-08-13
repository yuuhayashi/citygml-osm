package osm.surveyor.osm.api;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_A_1 extends CitygmlFileTest1 {

	@SuppressWarnings("deprecation")
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
						assertThat(relation.getTagValue("type"), is("building"));
						assertThat(relation.getTagValue("building"), is("yes"));
						assertNull(relation.getTagValue("addr:full"));
						assertEquals("2.4", relation.getTagValue("height"));
	
						int outlineCnt = 0;
						int partCnt = 0;
						for (MemberBean mem : relation.members) {
							if (mem.getRole().equals("outline")) {
								outlineCnt++;
								assertThat(mem.getType(), is("relation"));
								ElementRelation outline = osm.relations.get(mem.getRef());
								assertNotNull(outline);
								assertThat(outline.getTagValue("type"), is("multipolygon"));
								//assertEquals("yes", outline.getTagValue("building"));
								assertNull(outline.getTagValue("addr:full"));
								assertThat(outline.getTagValue("height"), is("2.4"));
								assertEquals("2.8", outline.getTagValue("ele"));
								assertEquals(4, outline.getTagList().size());
							}
							if (mem.getRole().equals("part")) {
								partCnt++;
								assertThat(mem.getType(), is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertNull(way.getTagValue("addr:full"));
								assertThat(way.getTagValue("height"), is("2.4"));
								assertEquals("2.8", way.getTagValue("ele"));
								assertThat(way.getTagValue("ref:MLIT_PLATEAU"), is("13111-bldg-365"));
								assertEquals(4, way.getTagList().size());
							}
						}
						assertEquals(1, outlineCnt);
						assertEquals(1, partCnt);
					}
					else if (relation.getTagValue("ele").endsWith("2.7")) {
						assertThat(relation.getTagValue("type"), is("building"));
						assertNull(relation.getTagValue("addr:full"));
						assertEquals("4.6", relation.getTagValue("height"));
						assertEquals("2.7", relation.getTagValue("ele"));
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
								//assertThat(outline.getTagValue("building"), is("house"));
								assertThat(outline.getTagValue("building:levels"), is("2"));
								assertThat(outline.getTagValue("building:levels:underground"), is("1"));
								assertNull(outline.getTagValue("addr:full"));
								assertThat(outline.getTagValue("height"), is("4.6"));
								assertEquals("2.7", outline.getTagValue("ele"));
								//assertThat(outline.getTagValue("start_date"), is("1976"));
								assertEquals(6, outline.getTagList().size());
							}
							if (mem.getRole().equals("part")) {
								partCnt++;
								assertThat(mem.getType(), is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertThat(way.getTagValue("ref:MLIT_PLATEAU"), is("13111-bldg-466"));
								assertNull(way.getTagValue("addr:full"));
								assertThat(way.getTagValue("height"), is("4.6"));
								assertEquals("2.7", way.getTagValue("ele"));
								assertThat(way.getTagValue("start_date"), is("1976"));
								assertThat(way.getTagValue("building:part"), is("house"));
								assertThat(way.getTagValue("building:levels"), is("2"));
								assertThat(way.getTagValue("building:levels:underground"), is("1"));
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
						assertThat(relation.getTagValue("type"), is("multipolygon"));
						assertNull(relation.getTagValue("addr:full"));	// "東京都大田区南六郷三丁目"
						assertThat(relation.getTagValue("height"), is("2.4"));
						assertEquals("2.8", relation.getTagValue("ele"));
						//assertThat(relation.getTagValue("building"), is("yes"));
	
						int outerCnt = 0;
						int innerCnt = 0;
						for (MemberBean mem : relation.members) {
							if (mem.getRole().equals("outer")) {
								outerCnt++;
								assertThat(mem.getType(), is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertThat(way.getTagValue("ref:MLIT_PLATEAU"), is("13111-bldg-365"));
								assertEquals(1, way.getTagList().size());
							}
							if (mem.getRole().equals("inner")) {
								innerCnt++;
								assertThat(mem.getType(), is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertThat(way.getTagValue("ref:MLIT_PLATEAU"), is("13111-bldg-365"));
								assertEquals(1, way.getTagList().size());
							}
						}
						assertEquals(1, outerCnt);
						assertEquals(0, innerCnt);
					}
					else if (relation.getTagValue("ele").endsWith("2.7")) {
						assertThat(relation.getTagValue("type"), is("multipolygon"));
						assertNull(relation.getTagValue("addr:full"));	// "東京都大田区南六郷三丁目"
						assertThat(relation.getTagValue("height"), is("4.6"));
						assertEquals("2.7", relation.getTagValue("ele"));
						//assertThat(relation.getTagValue("start_date"), is("1976"));
						//assertThat(relation.getTagValue("building"), is("house"));
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
								assertThat(way.getTagValue("ref:MLIT_PLATEAU"), is("13111-bldg-466"));
								assertEquals(1, way.getTagList().size());
							}
							if (mem.getRole().equals("inner")) {
								innerCnt++;
								assertThat(mem.getType(), is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertThat(way.getTagValue("ref:MLIT_PLATEAU"), is("13111-bldg-466"));
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
