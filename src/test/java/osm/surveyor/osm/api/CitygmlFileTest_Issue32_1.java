package osm.surveyor.osm.api;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_Issue32_1 extends CitygmlFileTest1 {

	/**
	 * 
		v1.3.0 LOD2の情報を持つ建物で、建物内に単独ノードがある。 #32
		
		plateauviewで見た時にLOD2の情報を持つ建物について、LOD2の形状の端点と思われるものが、単独ノードとして出てきます。
		
		例：
		東京 533946>53394610_bldg_6697_op2>13101-bldg-365 など
		飯塚市 50303564_bldg_6697_op>40205-bldg-95937 など

	 */
	@Test
	@Category(DetailTests.class)
	public void test52396075_a1_parse() {
		
        try {
    		OsmDom osm = testdo("./src/test/resources/Issue32_13101-bldg-365.gml");
			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					if (relation.getTagValue("source").endsWith("; 13101-bldg-365")) {
						assertEquals("building", relation.getTagValue("type"));
						assertEquals("yes", relation.getTagValue("building"));
						assertEquals("114.7", relation.getTagValue("height"));
						assertEquals("2.1", relation.getTagValue("ele"));
						assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13101-bldg-365", relation.getTagValue("source"));

						int outlineCnt = 0;
						int partCnt = 0;
						for (MemberBean mem : relation.members) {
							if (mem.getRole().equals("outline")) {
								outlineCnt++;
								assertEquals("relation", mem.getType());
								ElementRelation outline = osm.relations.get(mem.getRef());
								assertNotNull(outline);
								assertEquals(outline.getTagValue("type"), ("multipolygon"));
								assertEquals(outline.getTagValue("building"), ("yes"));
								assertEquals(outline.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13101-bldg-365"));
								assertEquals(outline.getTagValue("height"), ("114.7"));
								assertEquals("2.1", outline.getTagValue("ele"));
								assertTrue(outline.getTagList().size() >= 5);
							}
							if (mem.getRole().equals("part")) {
								partCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("height"), ("114.7"));
								assertEquals("2.1", way.getTagValue("ele"));
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13101-bldg-365"));
								assertTrue(way.getTagList().size() >= 5);
							}
						}
						assertEquals(1, outlineCnt);
						assertEquals(1, partCnt);
					}
					assertEquals(2, relation.members.size());
				}
			}
			assertEquals(2, osm.relations.size());
			
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
