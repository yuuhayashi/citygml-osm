package osm.surveyor.osm.api;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_E_3 extends CitygmlFileTest3 {

	@Test
	@Category(DetailTests.class)
	public void testSample_e3_removeSinglePart() {
        try {
        	OsmDom osm = testdo("./src/test/resources/sample_e_bldg_6697_op2.gml");
    		
			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					assertEquals(relation.getTagValue("type"), ("building"));
					assertEquals(relation.getTagValue("building"), ("yes"));
					assertEquals(relation.getTagValue("building:levels"), ("3"));
					assertEquals(relation.getTagValue("building:levels:underground"), ("1"));
					assertEquals(relation.getTagValue("name"), ("大田病院"));
					assertNotNull(relation.getTagValue("height"));
					assertEquals(relation.getTagValue("addr:ref"), ("13111007004"));
					assertEquals(relation.getTagValue("addr:full"), ("東京都大田区大森東四丁目"));
					assertEquals(relation.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));

					int outlineCnt = 0;
					int partCnt = 0;
					int outerCnt = 0;
					int innerCnt = 0;
					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outline")) {
							outlineCnt++;
						}
						if (mem.getRole().equals("part")) {
							partCnt++;
							assertEquals(mem.getType(), ("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							if (way.getTagValue("source").endsWith("; 13111-bldg-56522")) {
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels"), ("2"));
								assertEquals(way.getTagValue("building:levels:underground"), ("1"));
								assertEquals(way.getTagValue("name"), ("大田病院"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区大森東四丁目"));
								assertEquals(way.getTagValue("addr:ref"), ("13111007004"));
								assertEquals(way.getTagValue("height"), ("21.6"));
								assertEquals("1.62", way.getTagValue("ele"));
								assertEquals(way.getTagValue("start_date"), ("1976"));
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-56522"));
								assertEquals(10, way.getTagList().size());
							}
							else if (way.getTagValue("source").endsWith("; 13111-bldg-55333")) {
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-55333"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区大森東四丁目"));
								assertEquals(way.getTagValue("addr:ref"), ("13111007004"));
								assertEquals(way.getTagValue("height"), ("3.7"));
								assertEquals("1.93", way.getTagValue("ele"));
								assertEquals(way.getTagValue("start_date"), ("1977"));
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels"), ("3"));
								assertEquals(8, way.getTagList().size());
							}
						}
					}
					assertEquals(1, outlineCnt);
					assertEquals(0, innerCnt);
					assertEquals(0, outerCnt);
					assertEquals(2, partCnt);
				}
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

}
