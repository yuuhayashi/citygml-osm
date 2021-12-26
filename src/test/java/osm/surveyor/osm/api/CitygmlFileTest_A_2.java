package osm.surveyor.osm.api;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_A_2 extends CitygmlFileTest2 {

	/**
	 * `mvn test -Dtest=CitygmlFileTest_A#testSample_a2_margePart`
	 * 
	 */
	@SuppressWarnings("deprecation")
	@Test
	@Category(DetailTests.class)
	public void testSample_a2_margePart() {
		try {
			OsmDom osm = testdo("./src/test/resources/sample_a_bldg_6697_op2.gml");
			assertNotNull(osm);
			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					if (relation.getTagValue("ele").equals("2.75")) {
						assertThat(relation.getTagValue("type"), is("building"));
						assertThat(relation.getTagValue("building"), is("yes"));
						assertThat(relation.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
						assertThat(relation.getTagValue("height"), is("2.4"));
						assertThat(relation.getTagValue("ele"), is("2.75"));
						
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
								assertThat(outline.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
								assertThat(outline.getTagValue("height"), is("2.4"));
								assertThat(outline.getTagValue("ele"), is("2.75"));
								assertEquals(5, outline.getTagList().size());
							}
							if (mem.getRole().equals("part")) {
								partCnt++;
								assertThat(mem.getType(), is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
								assertThat(way.getTagValue("height"), is("2.4"));
								assertThat(way.getTagValue("ele"), is("2.75"));
								assertThat(way.getTagValue("ref:MLIT_PLATEAU"), is("13111-bldg-365"));
								assertEquals(5, way.getTagList().size());
							}
						}
						assertThat(outlineCnt, is(1));
						assertThat(partCnt, is(1));
					}
					else if (relation.getTagValue("ele").equals("2.67")) {
						assertThat(relation.getTagValue("type"), is("building"));
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
								assertEquals(8, outline.getTagList().size());
							}
							if (mem.getRole().equals("part")) {
								partCnt++;
								assertThat(mem.getType(), is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertThat(way.getTagValue("ref:MLIT_PLATEAU"), is("13111-bldg-466"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
								assertThat(way.getTagValue("height"), is("4.6"));
								assertThat(way.getTagValue("ele"), is("2.67"));
								assertThat(way.getTagValue("start_date"), is("1976"));
								assertThat(way.getTagValue("building:part"), is("house"));
								assertThat(way.getTagValue("building:levels"), is("2"));
								assertThat(way.getTagValue("building:levels:underground"), is("1"));
								assertEquals(8, way.getTagList().size());
							}
						}
						assertEquals(1, outlineCnt);
						assertEquals(1, partCnt);
					}
					assertEquals(2, relation.members.size());
				}
				else if (type.equals("multipolygon")) {
					if (relation.getTagValue("ele").endsWith("2.75")) {
						assertThat(relation.getTagValue("type"), is("multipolygon"));
						assertThat(relation.getTagValue("building"), is("yes"));
						assertThat(relation.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
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
					else if (relation.getTagValue("type").endsWith("multipolygon")
							&& relation.getTagValue("ele").endsWith("2.67")) {
						assertThat(relation.getTagValue("type"), is("multipolygon"));
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
								assertThat(way.getTagValue("ref:MLIT_PLATEAU"), is("13111-bldg-466"));
								assertThat(way.getTagList().size(), is(1));
							}
							if (mem.getRole().equals("inner")) {
								innerCnt++;
								assertThat(mem.getType(), is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertThat(way.getTagValue("ref:MLIT_PLATEAU"), is("13111-bldg-466"));
								assertThat(way.getTagList().size(), is(1));
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
