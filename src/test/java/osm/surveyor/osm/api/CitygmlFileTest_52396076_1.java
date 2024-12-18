package osm.surveyor.osm.api;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_52396076_1 extends CitygmlFileTest1 {

	/**
	 * Issue #13 「v1.2.4 「箱根町」のデータを変換できない」
	 * https://github.com/yuuhayashi/citygml-osm/issues/13
	 * map "A :建物" as A {
	 *  bldg:Building:id => bldg-cf368523-863d-4bf8-8931-3a9cf99e4e58
	 *  bldg:Building:stringAttribute[name="建物ID"] => 14382-bldg-10718
	 *  bldg:measuredHeight => 13.3
	 *  bldg:lod0FootPrint:Polygon:posList:height => 728.31
	 *  Envelope:srsName => http://www.opengis.net/def/crs/EPSG/0/6697
	 * }
	 */
	@Test
	@Category(DetailTests.class)
	public void test52396075_a1_parse() {
        try {
    		OsmDom osm = testdo("./src/test/resources/52396075_bldg_6697_op.gml");

			assertNotNull(osm);
			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					assertEquals(relation.getTagValue("type"), "building");
					assertEquals(relation.getTagValue("building"), ("yes"));
					assertEquals(relation.getTagValue("height"), ("13.3"));
					assertEquals(relation.getTagValue("ele"), ("728.3"));

					int outlineCnt = 0;
					int partCnt = 0;
					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outline")) {
							outlineCnt++;
							assertEquals(mem.getType(), ("relation"));
							ElementRelation outline = osm.relations.get(mem.getRef());
							assertNotNull(outline);
							assertEquals(outline.getTagValue("type"), ("multipolygon"));
							//assertThat(outline.getTagValue("building"), is("yes"));
							assertNull(outline.getTagValue("ref:MLIT_PLATEAU"));
							assertEquals(outline.getTagValue("height"), ("13.3"));
							assertEquals(outline.getTagValue("ele"), ("728.3"));
							assertTrue(outline.getTagList().size() >= 5);
						}
						if (mem.getRole().equals("part")) {
							partCnt++;
							assertEquals(mem.getType(), ("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							assertEquals(way.getTagValue("building:part"), ("yes"));
							assertEquals(way.getTagValue("building:levels"), ("1"));
							assertEquals(way.getTagValue("height"), ("13.3"));
							assertEquals(way.getTagValue("ele"), ("728.3"));
							assertEquals(way.getTagValue("ref:MLIT_PLATEAU"), ("14382-bldg-10718"));
							assertTrue(way.getTagList().size() >= 5);
						}
					}
					assertEquals(outlineCnt, (1));
					assertEquals(partCnt, (1));
					assertEquals(relation.members.size(), (2));
				}
				else if (type.equals("multipolygon")) {
					assertEquals(relation.getTagValue("type"), ("multipolygon"));
					assertNull(relation.getTagValue("ref:MLIT_PLATEAU"));
					assertEquals(relation.getTagValue("height"), ("13.3"));
					assertEquals(relation.getTagValue("ele"), ("728.3"));
					//assertThat(relation.getTagValue("building"), is("yes"));

					int outerCnt = 0;
					int innerCnt = 0;
					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outer")) {
							outerCnt++;
							assertEquals("way", mem.getType());
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							assertEquals("14382-bldg-10718", way.getTagValue("ref:MLIT_PLATEAU"));
							assertEquals(1, way.getTagList().size());
						}
					}
					assertEquals(1, outerCnt);
					assertEquals(0, innerCnt);
					assertEquals(1, relation.members.size());
				}
			}
			assertEquals(2, osm.relations.size());
			
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
