package osm.surveyor.osm.api;

import org.junit.Test;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_E extends GmlLoadRouteTest {

	@Test
	public void testSample_e() {
        try {
            OsmDom osm = testdo("./src/test/resources/sample_e_bldg_6697_op2.gml");
			checkSample_e(osm);
			assertEquals(2, osm.relations.size());
			assertEquals(4, osm.ways.size());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
    
	public static void checkSample_e(OsmDom osm) {
        try {
			assertNotNull(osm);
			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					assertEquals("building", relation.getTagValue("type"));
					assertEquals(relation.getTagValue("building"), ("yes"));
					assertEquals(relation.getTagValue("building:levels"), ("3"));
					assertEquals(relation.getTagValue("building:levels:underground"), ("1"));
					assertEquals(relation.getTagValue("name"), ("大田病院"));
					assertEquals(relation.getTagValue("addr:full"), ("東京都大田区大森東四丁目"));
					assertNull(relation.getTagValue("addr:ref"));
					assertEquals(relation.getTagValue("height"), ("21.6"));
					assertEquals("1.62", relation.getTagValue("ele"));
					assertNull(relation.getTagValue("ref:MLIT_PLATEAU"));
					assertEquals(8, relation.getTagList().size());

					int outlineCnt = 0;
					int partCnt = 0;
					int outerCnt = 0;
					int innerCnt = 0;
					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outline")) {
							outlineCnt++;
							assertEquals("relation", mem.getType());
							ElementRelation polygon = osm.relations.get(Long.toString(mem.getRef()));
							assertNotNull(polygon);
							assertEquals("multipolygon", polygon.getTagValue("type"));
							assertEquals("yes", polygon.getTagValue("building"));
							assertEquals("大田病院", polygon.getTagValue("name"));
							assertEquals("3", polygon.getTagValue("building:levels"));
							assertEquals("1", polygon.getTagValue("building:levels:underground"));
							assertEquals("東京都大田区大森東四丁目", polygon.getTagValue("addr:full"));
							assertNull(polygon.getTagValue("addr:ref"));
							assertEquals("21.6", polygon.getTagValue("height"));
							assertEquals("1.62", polygon.getTagValue("ele"));
							assertEquals("1976", polygon.getTagValue("start_date"));
							assertNull(polygon.getTagValue("ref:MLIT_PLATEAU"));
							assertEquals(9, polygon.getTagList().size());
							
							for (MemberBean member : polygon.members) {
								if (member.getRole().equals("outer")) {
									outerCnt++;
									assertEquals("way", member.getType());
									ElementWay way = osm.ways.get(Long.toString(member.getRef()));
									assertNotNull(way);
									assertNull(way.getTagValue("ref:MLIT_PLATEAU"));
									assertEquals(0, way.getTagList().size());
								}
								if (member.getRole().equals("inner")) {
									innerCnt++;
									assertEquals("way", member.getType());
									ElementWay way = osm.ways.get(Long.toString(member.getRef()));
									assertNotNull(way);
									assertNull(way.getTagValue("ref:MLIT_PLATEAU"));
									assertEquals(0, way.getTagList().size());
								}
							}
							assertEquals(2, polygon.members.size());
						}
						if (mem.getRole().equals("part")) {
							partCnt++;
							assertEquals(mem.getType(), ("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							if (way.getTagValue("ref:MLIT_PLATEAU").endsWith("13111-bldg-56522")) {
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels"), ("2"));
								assertEquals(way.getTagValue("building:levels:underground"), ("1"));
								assertEquals(way.getTagValue("name"), ("大田病院"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区大森東四丁目"));
								assertNull(way.getTagValue("addr:ref"));
								assertEquals(way.getTagValue("height"), ("21.6"));
								assertEquals("1.62", way.getTagValue("ele"));
								assertEquals(way.getTagValue("start_date"), ("1976"));
								assertEquals(way.getTagValue("ref:MLIT_PLATEAU"), "13111-bldg-56522");
								assertEquals(9, way.getTagList().size());
							}
							else if (way.getTagValue("ref:MLIT_PLATEAU").endsWith("13111-bldg-55333")) {
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels"), ("3"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区大森東四丁目"));
								assertNull(way.getTagValue("addr:ref"));
								assertEquals(way.getTagValue("height"), ("3.7"));
								assertEquals("1.93", way.getTagValue("ele"));
								assertEquals(way.getTagValue("start_date"), ("1977"));
								assertEquals(way.getTagValue("ref:MLIT_PLATEAU"), "13111-bldg-55333");
								assertEquals(7, way.getTagList().size());
							}
						}
					}
					assertEquals(1, innerCnt);
					assertEquals(1, outerCnt);
					assertEquals(1, outlineCnt);
					assertEquals(2, partCnt);
					assertEquals(3, relation.members.size());
				}
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
