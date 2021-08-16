package osm.surveyor.osm.api;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import osm.surveyor.DetailTests;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_D_2 extends CitygmlFileTest2 {

	@Test
	@Category(DetailTests.class)
	public void testSample_d2_margePart() {
		
        try {
        	OsmDom osm = testdo("./src/test/resources/sample_d_bldg_6697_op2.gml");
    		
			assertNotNull(osm.relations);
			int partCnt = 0;
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				if (relation.isBuilding()) {
					assertEquals(relation.getTagValue("type"), ("building"));
					assertEquals(relation.getTagValue("building"), ("yes"));
					assertEquals(relation.getTagValue("addr:full"), ("東京都大田区大森西三丁目"));
					assertEquals(relation.getTagValue("addr:ref"), ("13111006003"));
					assertNotNull(relation.getTagValue("height"));
					assertNotNull(relation.getTagValue("ele"));
					assertTrue(relation.getTagValue("source").startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
	
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
					if (relation.getTagValue("source").endsWith("; 13111-bldg-72601")) {
						assertEquals(relation.getTagValue("type"), ("multipolygon"));
						assertEquals(relation.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));
						assertEquals(relation.getTagValue("addr:full"), ("東京都大田区大森西三丁目"));
						assertEquals(relation.getTagValue("addr:ref"), ("13111006003"));
						assertEquals(relation.getTagValue("height"), ("34.7"));
						assertEquals(relation.getTagValue("ele"), ("2.682"));
						assertEquals(relation.getTagValue("building"), ("yes"));

						int outerCnt = 0;
						int innerCnt = 0;
						for (MemberBean mem : relation.members) {
							if (mem.getRole().equals("outer")) {
								outerCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));
								assertEquals(1, way.getTagList().size());
							}
							if (mem.getRole().equals("inner")) {
								innerCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));
								assertEquals(1, way.getTagList().size());
							}
						}
						assertEquals(1, outerCnt);
						assertEquals(2, innerCnt);
						assertEquals(3, relation.members.size());
					}
					else if (relation.getTagValue("source").endsWith("; 13111-bldg-71799")) {
						assertEquals(relation.getTagValue("type"), ("multipolygon"));
						assertEquals(relation.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-71799"));
						assertEquals(relation.getTagValue("addr:full"), ("東京都大田区大森西三丁目"));
						assertEquals(relation.getTagValue("addr:ref"), ("13111006003"));
						assertEquals(relation.getTagValue("height"), ("30.2"));
						assertEquals(relation.getTagValue("ele"), ("3.126"));
						assertEquals(relation.getTagValue("building"), ("yes"));

						int outerCnt = 0;
						int innerCnt = 0;
						for (MemberBean mem : relation.members) {
							if (mem.getRole().equals("outer")) {
								outerCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-71799"));
								assertEquals(1, way.getTagList().size());
							}
							if (mem.getRole().equals("inner")) {
								innerCnt++;
								assertEquals(mem.getType(), ("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-71799"));
								assertEquals(1, way.getTagList().size());
							}
						}
						assertEquals(1, outerCnt);
						assertEquals(0, innerCnt);
						assertEquals(1, relation.members.size());
					}
				}
			}
			assertEquals(2, partCnt);
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
	
}
