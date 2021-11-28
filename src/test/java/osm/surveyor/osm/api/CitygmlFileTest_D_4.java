package osm.surveyor.osm.api;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import osm.surveyor.DetailTests;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_D_4 extends CitygmlFileTest4 {

	@Test
	@Category(DetailTests.class)
	public void testSample_d4_createOutline() {
		
        try {
        	OsmDom osm = testdo("./src/test/resources/sample_d_bldg_6697_op2.gml");
    		
			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					assertEquals(relation.getTagValue("type"), ("building"));
					assertEquals(relation.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					assertEquals(relation.getTagValue("addr:full"), ("東京都大田区大森西三丁目"));
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
							assertTrue(polygon.getTagValue("source").startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
							assertEquals(polygon.getTagValue("addr:full"), ("東京都大田区大森西三丁目"));
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
									assertEquals(1, way.getTagList().size());
								}
								if (member.getRole().equals("inner")) {
									innerCnt++;
									assertEquals(member.getType(), ("way"));
									ElementWay way = osm.ways.get(Long.toString(member.getRef()));
									assertNotNull(way);
									assertTrue(way.getTagValue("source").startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; "));
									assertEquals(1, way.getTagList().size());
								}
							}
							assertEquals(3, polygon.members.size());
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
								assertEquals(way.getTagValue("height"), ("34.7"));
								assertEquals(way.getTagValue("ele"), ("2.68"));
								assertEquals(way.getTagValue("start_date"), ("1976"));
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));
								assertEquals(9, way.getTagList().size());
							}
							else if (way.getTagValue("source").endsWith("; 13111-bldg-71799")) {
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-71799"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区大森西三丁目"));
								assertEquals(way.getTagValue("height"), ("30.2"));
								assertEquals(way.getTagValue("ele"), ("3.13"));
								assertEquals(way.getTagValue("start_date"), ("1977"));
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels"), ("3"));
								assertEquals(8, way.getTagList().size());
							}
						}
					}
					assertEquals(2, innerCnt);
					assertEquals(1, outerCnt);
					assertEquals(1, outlineCnt);
					assertEquals(2, partCnt);
					assertEquals(3, relation.members.size());
				}
			}
			assertEquals(2, osm.relations.size());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
    
}
