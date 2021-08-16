package osm.surveyor.osm.api;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_C_1 extends CitygmlFileTest1 {

	@Test
	@Category(DetailTests.class)
	public void testSample_c1_parse() {
		
        try {
            OsmDom osm = testdo("./src/test/resources/sample_c_bldg_6697_op2.gml");

			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					if (relation.getTagValue("source").endsWith("; 13111-bldg-473")) {
						assertEquals(relation.getTagValue("type"), ("building"));
						assertEquals(relation.getTagValue("building"), ("yes"));
						assertEquals(relation.getTagValue("building:levels"), ("2"));
						assertEquals(relation.getTagValue("height"), ("42.7"));
						assertEquals(relation.getTagValue("ele"), ("2.81"));
						assertEquals(relation.getTagValue("addr:full"), ("東京都大田区南六郷三丁目"));
						assertEquals(relation.getTagValue("addr:ref"), ("13111058003"));
						assertEquals(relation.getTagValue("start_date"), ("1976"));
						assertEquals(relation.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));

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
								assertEquals(outline.getTagValue("height"), ("42.7"));
								assertEquals(outline.getTagValue("ele"), ("2.81"));
								assertEquals(outline.getTagValue("addr:full"), ("東京都大田区南六郷三丁目"));
								assertEquals(outline.getTagValue("addr:ref"), ("13111058003"));
								assertEquals(outline.getTagValue("start_date"), ("1976"));
								assertEquals(outline.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));
								assertEquals(9, outline.getTagList().size());
							}
							if (mem.getRole().equals("part")) {
								partCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels"), ("2"));
								assertEquals(way.getTagValue("height"), ("42.7"));
								assertEquals(way.getTagValue("ele"), ("2.81"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区南六郷三丁目"));
								assertEquals(way.getTagValue("addr:ref"), ("13111058003"));
								assertEquals(way.getTagValue("start_date"), ("1976"));
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));
								assertEquals(8, way.getTagList().size());
							}
						}
						assertEquals(1, outlineCnt);
						assertEquals(1, partCnt);
					}
					else if (relation.getTagValue("source").endsWith("; 13111-bldg-386")) {
						assertEquals("building", relation.getTagValue("type"));
						assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-386", relation.getTagValue("source"));
						assertEquals(relation.getTagValue("addr:full"), ("東京都大田区南六郷三丁目"));
						assertEquals(relation.getTagValue("addr:ref"), ("13111058003"));
						assertEquals(relation.getTagValue("height"), ("7.3"));
						assertEquals(relation.getTagValue("ele"), ("2.81"));
						assertEquals(relation.getTagValue("start_date"), ("2003"));
						assertEquals(relation.getTagValue("building"), ("yes"));
						assertEquals(relation.getTagValue("building:levels:underground"), ("1"));

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
								assertEquals(outline.getTagValue("building:levels:underground"), ("1"));
								assertEquals(outline.getTagValue("addr:full"), ("東京都大田区南六郷三丁目"));
								assertEquals(outline.getTagValue("addr:ref"), ("13111058003"));
								assertEquals(outline.getTagValue("height"), ("7.3"));
								assertEquals(outline.getTagValue("ele"), ("2.81"));
								assertEquals(outline.getTagValue("start_date"), ("2003"));
								assertEquals(outline.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-386"));
								assertEquals(9, outline.getTagList().size());
							}
							if (mem.getRole().equals("part")) {
								partCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-386"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区南六郷三丁目"));
								assertEquals(way.getTagValue("addr:ref"), ("13111058003"));
								assertEquals(way.getTagValue("height"), ("7.3"));
								assertEquals(way.getTagValue("ele"), ("2.81"));
								assertEquals(way.getTagValue("start_date"), ("2003"));
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels:underground"), ("1"));
								assertEquals(8, way.getTagList().size());
							}
						}
						assertEquals(1, outlineCnt);
						assertEquals(1, partCnt);
					}
					assertEquals(2, relation.members.size());
				}
			}
			assertEquals(4, osm.relations.size());
			
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

}
