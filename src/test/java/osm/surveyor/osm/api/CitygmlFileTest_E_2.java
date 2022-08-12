package osm.surveyor.osm.api;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_E_2 extends CitygmlFileTest2 {

	@Test
	@Category(DetailTests.class)
	public void testSample_e2_margePart() {
		
        try {
        	OsmDom osm = testdo("./src/test/resources/sample_e_bldg_6697_op2.gml");
    		
			assertNotNull(osm.relations);
			int partCnt = 0;
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				if (relation.isBuilding()) {
					assertEquals(relation.getTagValue("type"), ("building"));
					assertEquals(relation.getTagValue("building"), ("yes"));
					assertEquals(relation.getTagValue("name"), ("大田病院"));
					assertNull(relation.getTagValue("addr:full"));
					assertNull(relation.getTagValue("addr:ref"));
					assertNotNull(relation.getTagValue("height"));
					assertNotNull(relation.getTagValue("ele"));
					//assertNull(relation.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
	
					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outline")) {
							assertEquals(mem.getType(), ("relation"));
							ElementRelation outline = osm.relations.get(mem.getRef());
							assertNotNull(outline);
						}
						if (mem.getRole().equals("part")) {
							partCnt++;
							assertEquals(mem.getType(), ("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
						}
					}
				}
				else if (relation.isMultipolygon()) {
					/*
					 * 
					if (relation.getTagValue("source").endsWith("; 13111-bldg-55333")) {
						assertEquals(relation.getTagValue("type"), ("multipolygon"));
						assertNull(relation.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-55333"));
						assertNull(relation.getTagValue("addr:full"));
						assertNull(relation.getTagValue("addr:ref"));
						assertEquals(relation.getTagValue("height"), ("3.7"));
						assertEquals("1.93", relation.getTagValue("ele"));
						assertNull(relation.getTagValue("start_date"));
						assertEquals(relation.getTagValue("building"), ("yes"));
						assertEquals(relation.getTagValue("building:levels"), ("3"));

						int outerCnt = 0;
						int innerCnt = 0;
						for (MemberBean mem : relation.members) {
							if (mem.getRole().equals("outer")) {
								outerCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertNull(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-55333"));
								assertEquals(1, way.getTagList().size());
							}
							if (mem.getRole().equals("inner")) {
								innerCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertNull(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-55333"));
								assertEquals(1, way.getTagList().size());
							}
						}
						assertEquals(1, outerCnt);
						assertEquals(0, innerCnt);
						assertEquals(1, relation.members.size());
					}
					 */
				}
			}
			assertEquals(2, partCnt);
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
	
}
