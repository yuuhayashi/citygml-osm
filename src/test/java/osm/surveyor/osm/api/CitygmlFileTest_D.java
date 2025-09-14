package osm.surveyor.osm.api;

import org.junit.Test;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_D extends GmlLoadRouteTest {

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
			for (String id : osm.relationMap.keySet()) {
				ElementRelation relation = osm.relationMap.get(id);
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
					assertNull(relation.getTagValue("ref:MLIT_PLATEAU"));
					assertNull(relation.getTagValue("addr:full"));
					assertEquals("34.7", relation.getTagValue("height"));
					assertEquals("2.7", relation.getTagValue("ele"));
					assertEquals("yes", relation.getTagValue("building"));
					assertEquals("3", relation.getTagValue("building:levels"));
					assertEquals("1", relation.getTagValue("building:levels:underground"));
					assertNull(relation.getTagValue("start_date"));		// Issue #39
					//assertEquals(7, relation.getTagList().size());

					int outlineCnt = 0;
					int partCnt = 0;
					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outline")) {
							outlineCnt++;
							assertEquals("way", mem.getType());
							ElementWay way = (ElementWay)osm.getWayMap().get(Long.toString(mem.getRef()));
							assertNull(way.getTagValue("addr:full"));
							assertNull(way.getTagValue("start_date"));
							assertEquals("34.7", way.getTagValue("height"));
							assertEquals("2.7", way.getTagValue("ele"));
							assertEquals("yes", way.getTagValue("building"));	// Issue #40 [バリデーション警告「重なった建物」が発生する]
							assertEquals("3", way.getTagValue("building:levels"));
							assertEquals("1", way.getTagValue("building:levels:underground"));
							assertNull(way.getTagValue("survey:date"));
							assertEquals(5, way.getTagList().size());
						}
						else if (mem.getRole().equals("part")) {
							partCnt++;
							if (mem.isWay()) {
								assertEquals("way", mem.getType());
								ElementWay way = (ElementWay)osm.getWayMap().get(Long.toString(mem.getRef()));
								assertNotNull(way);
								if (way.getTagValue("ref:MLIT_PLATEAU").endsWith("13111-bldg-72601")) {
									assertNull(way.getTagValue("addr:full"));
									assertEquals("30.2", way.getTagValue("height"));
									assertEquals("3.1", way.getTagValue("ele"));
									assertEquals("1977", way.getTagValue("start_date"));
									assertEquals("yes", way.getTagValue("building:part"));
									assertEquals("3", way.getTagValue("building:levels"));
									assertEquals("2016", way.getTagValue("survey:date"));
									assertEquals(7, way.getTagList().size());
								}
								if (way.getTagValue("ref:MLIT_PLATEAU").endsWith("13111-bldg-71799")) {
									assertNull(way.getTagValue("addr:full"));
									assertEquals("30.2", way.getTagValue("height"));
									assertEquals("3.1", way.getTagValue("ele"));
									assertEquals("1977", way.getTagValue("start_date"));
									assertEquals("yes", way.getTagValue("building:part"));
									assertEquals("3", way.getTagValue("building:levels"));
									assertEquals("2016", way.getTagValue("survey:date"));
									assertEquals(7, way.getTagList().size());
								}
							}
							else if (mem.isRelation()) {
								assertEquals("relation", mem.getType());
								ElementRelation multiporygon = osm.relationMap.get(Long.toString(mem.getRef()));
								assertNotNull(multiporygon);
								assertTrue(multiporygon.isMultipolygon());
								assertNull(multiporygon.getTag("building"));	// Issue #40 [バリデーション警告「重なった建物」が発生する]
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
						 ref:MLIT_PLATEAU => null
						}
					 */
					assertEquals("multipolygon", relation.getTagValue("type"));
					assertEquals("yes", relation.getTagValue("building:part"));		// Issue #40 [バリデーション警告「重なった建物」が発生する]
					assertEquals("34.7", relation.getTagValue("height"));
					assertEquals("2.7", relation.getTagValue("ele"));
					assertEquals("1976", relation.getTagValue("start_date"));				// Issue #39 複合ビルでの”建築年”の扱い
					assertEquals("2", relation.getTagValue("building:levels"));
					assertEquals("1", relation.getTagValue("building:levels:underground"));
					assertEquals("2016", relation.getTagValue("survey:date"));
					assertEquals("13111-bldg-72601", relation.getTagValue("ref:MLIT_PLATEAU"));
					assertNull(relation.getTagValue("addr:full"));
					assertNull(relation.getTagValue("addr:ref"));
					assertEquals(9, relation.getTagList().size());
					
					int outerCnt = 0;
					int innerCnt = 0;
					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outer")) {
							outerCnt++;
							assertEquals("way", mem.getType());
							ElementWay way = (ElementWay)osm.getWayMap().get(Long.toString(mem.getRef()));
							assertNotNull(way);
							assertEquals("13111-bldg-72601", way.getTagValue("ref:MLIT_PLATEAU"));
							assertEquals(1, way.getTagList().size());
						}
						if (mem.getRole().equals("inner")) {
							innerCnt++;
							assertEquals("way", mem.getType());
							ElementWay way = (ElementWay)osm.getWayMap().get(Long.toString(mem.getRef()));
							assertNotNull(way);
							assertEquals("13111-bldg-72601", way.getTagValue("ref:MLIT_PLATEAU"));
							assertEquals(1, way.getTagList().size());
						}
					}
					assertEquals(1, outerCnt);
					assertEquals(2, innerCnt);
					assertEquals(3, relation.members.size());
				}
			}
			assertEquals(2, osm.relationMap.size());
			assertEquals(5, osm.getWayMap().size());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
