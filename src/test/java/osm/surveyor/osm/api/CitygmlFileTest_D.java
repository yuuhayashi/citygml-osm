package osm.surveyor.osm.api;

import org.junit.Test;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_D extends CitygmlFileTest {

	/*
 	map "13111-bldg-71799 : building" as building {
	 bldg:measuredHeight => 30.2
	 bldg:yearOfConstruction => 1977
	 bldg:storeysAboveGround => 3
	 gml:posList => 6.038
	 xAL:LocalityName => 東京都大田区大森西三丁目
	}
 	map "13111-bldg-72601 : building" as building {
	 bldg:measuredHeight => 34.7
	 bldg:yearOfConstruction => 1976
	 bldg:storeysAboveGround => 2
	 bldg:storeysBelowGround => 1
	 bldg:lod1Solid => 2.682
	 xAL:LocalityName => 東京都大田区大森西三丁目
	}
	*/
	@Test
	public void testSample_d() {
        OsmDom osm = testdo("./src/test/resources/sample_d_bldg_6697_op2.gml");
        try {
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				if (relation.isBuilding()) {
					/*
					 	map "building : Relation" as building {
						 type => building
						 building => yes
						 height => 32.852000000000004
						 source => MLIT_PLATEAU;\n http://www.opengis.net/def/crs/EPSG/0/6697
						}
					 */
					assertEquals("building", relation.getTagValue("type"));
					assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697", relation.getTagValue("source"));
					assertEquals("東京都大田区大森西三丁目", relation.getTagValue("addr:full"));
					assertEquals("34.7", relation.getTagValue("height"));
					assertEquals("2.68", relation.getTagValue("ele"));
					assertEquals("yes", relation.getTagValue("building"));
					assertEquals("3", relation.getTagValue("building:levels"));
					assertEquals("1", relation.getTagValue("building:levels:underground"));
					assertNull(relation.getTagValue("start_date"));		// Issue #39
					assertEquals(8, relation.getTagList().size());

					int outlineCnt = 0;
					int partCnt = 0;
					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outline")) {
							outlineCnt++;
							assertEquals("relation", mem.getType());
							ElementRelation multiporygon = osm.relations.get(Long.toString(mem.getRef()));
							assertNotNull(multiporygon);
						}
						if (mem.getRole().equals("part")) {
							partCnt++;
							assertEquals("way", mem.getType());
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							if (way.getTagValue("source").endsWith("; 13111-bldg-72601")) {
								assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601", way.getTagValue("source"));
								assertEquals("東京都大田区大森西三丁目", way.getTagValue("addr:full"));
								assertEquals("34.7", way.getTagValue("height"));
								assertEquals("2.68", way.getTagValue("ele"));
								assertEquals("1976", way.getTagValue("start_date"));
								assertEquals("yes", way.getTagValue("building:part"));
								assertEquals("2", way.getTagValue("building:levels"));
								assertEquals("1", way.getTagValue("building:levels:underground"));
								assertEquals(8, way.getTagList().size());
							}
							else if (way.getTagValue("source").endsWith("; 13111-bldg-71799")) {
								assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-71799", way.getTagValue("source"));
								assertEquals("東京都大田区大森西三丁目", way.getTagValue("addr:full"));
								assertEquals("30.2", way.getTagValue("height"));
								assertEquals("3.13", way.getTagValue("ele"));
								assertEquals("1977", way.getTagValue("start_date"));
								assertEquals("yes", way.getTagValue("building:part"));
								assertEquals("3", way.getTagValue("building:levels"));
								assertEquals(7, way.getTagList().size());
							}
						}
					}
					assertEquals(1, outlineCnt);
					assertEquals(2, partCnt);
					assertEquals(3, relation.members.size());
				}
				else if (relation.isMultipolygon()) {
					/*
						map "multipolygon :Relation" as multipolygon {
						 type => multipolygon
						 building => yes
						 height => 32.852000000000004
						 source => MLIT_PLATEAU;\n http://www.opengis.net/def/crs/EPSG/0/6697
						}
					 */
					assertEquals("multipolygon", relation.getTagValue("type"));
					assertEquals("yes", relation.getTagValue("building"));
					assertEquals("3", relation.getTagValue("building:levels"));
					assertEquals("1", relation.getTagValue("building:levels:underground"));
					assertEquals("34.7", relation.getTagValue("height"));
					assertEquals("2.68", relation.getTagValue("ele"));
					assertEquals("東京都大田区大森西三丁目", relation.getTagValue("addr:full"));
					assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697", relation.getTagValue("source"));
					assertEquals(9, relation.getTagList().size());
					assertNull(relation.getTagValue("addr:ref"));
					int outerCnt = 0;
					int innerCnt = 0;
					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outer")) {
							outerCnt++;
							assertEquals("way", mem.getType());
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697", way.getTagValue("source"));
							assertEquals(1, way.getTagList().size());
						}
						if (mem.getRole().equals("inner")) {
							innerCnt++;
							assertEquals("way", mem.getType());
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601", way.getTagValue("source"));
							assertEquals(1, way.getTagList().size());
						}
					}
					assertEquals(1, outerCnt);
					assertEquals(2, innerCnt);
					assertEquals(3, relation.members.size());
				}
			}
			assertEquals(2, osm.relations.size());
			assertEquals(5, osm.ways.size());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
