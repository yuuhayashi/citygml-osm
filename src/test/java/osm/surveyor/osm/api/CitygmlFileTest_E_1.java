package osm.surveyor.osm.api;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_E_1 extends CitygmlFileTest1 {

	@Test
	@Category(DetailTests.class)
	public void testSample_e1_parse() {
		try {
			OsmDom osm = testdo("./src/test/resources/sample_e_bldg_6697_op2.gml");
	        
			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					/*
					 * 
					if (relation.getTagValue("source").endsWith("; 13111-bldg-56522")) {
						assertEquals(relation.getTagValue("type"), ("building"));						
						assertEquals(relation.getTagValue("building"), ("yes"));
						assertEquals(relation.getTagValue("building:levels"), ("2"));
						assertEquals(relation.getTagValue("building:levels:underground"), ("1"));
						assertEquals(relation.getTagValue("name"), ("大田病院"));
						assertEquals(relation.getTagValue("height"), ("21.6"));
						assertEquals("1.62", relation.getTagValue("ele"));
						assertNull(relation.getTagValue("addr:full"));
						assertNull(relation.getTagValue("addr:ref"));
						assertNull(relation.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-56522"));
						assertEquals(11, relation.getTagList().size());

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
								assertEquals(outline.getTagValue("building:levels:underground"), ("1"));
								assertEquals(outline.getTagValue("name"), ("大田病院"));
								assertEquals(outline.getTagValue("height"), ("21.6"));
								assertEquals("1.62", outline.getTagValue("ele"));
								assertNull(outline.getTagValue("addr:full"));
								assertNull(outline.getTagValue("addr:ref"));
								assertNull(outline.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-56522"));
								assertEquals(11, outline.getTagList().size());
							}
							if (mem.getRole().equals("part")) {
								partCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels"), ("2"));
								assertEquals(way.getTagValue("building:levels:underground"), ("1"));
								assertEquals(way.getTagValue("name"), ("大田病院"));
								assertEquals(way.getTagValue("height"), ("21.6"));
								assertEquals("1.62", way.getTagValue("ele"));
								assertEquals(way.getTagValue("start_date"), ("1976"));
								assertNull(way.getTagValue("addr:full"));
								assertNull(way.getTagValue("addr:ref"));
								assertNull(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-56522"));
								assertEquals(10, way.getTagList().size());
							}
						}
						assertEquals(1, outlineCnt);
						assertEquals(1, partCnt);
						assertEquals(2, relation.members.size());
					}
					else if (relation.getTagValue("source").endsWith("; 13111-bldg-55333")) {
						assertEquals(relation.getTagValue("type"), ("building"));
						assertNull(relation.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-55333"));
						assertNull(relation.getTagValue("addr:full"));
						assertNull(relation.getTagValue("addr:ref"));
						assertEquals(relation.getTagValue("height"), ("3.7"));
						assertEquals("1.93", relation.getTagValue("ele"));
						assertEquals(relation.getTagValue("building"), ("yes"));
						assertEquals(relation.getTagValue("building:levels"), ("3"));
						assertEquals(9, relation.getTagList().size());

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
								assertEquals(outline.getTagValue("building:levels"), ("3"));
								assertNull(outline.getTagValue("addr:full"));
								assertNull(outline.getTagValue("addr:ref"));
								assertEquals(outline.getTagValue("height"), ("3.7"));
								assertEquals("1.93", outline.getTagValue("ele"));
								assertNull(outline.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-55333"));
								assertEquals(9, outline.getTagList().size());
							}
							if (mem.getRole().equals("part")) {
								partCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertNull(way.getTagValue("addr:full"));
								assertNull(way.getTagValue("addr:ref"));
								assertEquals(way.getTagValue("height"), ("3.7"));
								assertEquals("1.93", way.getTagValue("ele"));
								assertEquals(way.getTagValue("start_date"), ("1977"));
								assertNull(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-55333"));
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels"), ("3"));
								assertEquals(8, way.getTagList().size());
							}
						}
						assertEquals(1, outlineCnt);
						assertEquals(1, partCnt);
						assertEquals(2, relation.members.size());
					}
					 */
				}
				else if (type.equals("multipolygon")) {
					/*
					 * 
					if (relation.getTagValue("source").endsWith("; 13111-bldg-56522")) {
						assertEquals(relation.getTagValue("type"), ("multipolygon"));
						assertNull(relation.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-56522"));
						assertEquals(relation.getTagValue("name"), ("大田病院"));
						assertNull(relation.getTagValue("addr:full"));
						assertNull(relation.getTagValue("addr:ref"));
						assertEquals(relation.getTagValue("height"), ("21.6"));
						assertEquals("1.62", relation.getTagValue("ele"));
						assertEquals(relation.getTagValue("start_date"), ("1976"));
						assertEquals(relation.getTagValue("building"), ("yes"));
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
								assertNull(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-56522"));
								assertEquals(1, way.getTagList().size());
							}
							if (mem.getRole().equals("inner")) {
								innerCnt++;
							}
						}
						assertEquals(1, outerCnt);
						assertEquals(0, innerCnt);
						assertEquals(1, relation.members.size());
					}
					else if (relation.getTagValue("source").endsWith("; 13111-bldg-55333")) {
						assertEquals(relation.getTagValue("type"), ("multipolygon"));
						assertNull(relation.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-55333"));
						assertNull(relation.getTagValue("addr:full"));
						assertNull(relation.getTagValue("addr:ref"));
						assertEquals(relation.getTagValue("height"), ("3.7"));
						assertEquals("1.93", relation.getTagValue("ele"));
						assertEquals(relation.getTagValue("start_date"), ("1977"));
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
							}
						}
						assertEquals(1, outerCnt);
						assertEquals(0, innerCnt);
						assertEquals(1, relation.members.size());
					}
					 */
				}
			}
			assertEquals(4, osm.relations.size());
			
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
	
}
