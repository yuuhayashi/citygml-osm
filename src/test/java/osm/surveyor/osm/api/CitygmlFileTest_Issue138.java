package osm.surveyor.osm.api;

import org.junit.Test;
import osm.surveyor.osm.BoundsBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_Issue138 extends GmlLoadRouteTest {

	/**
	 * Issue138 「同じ位置を持つウェイ」マルチポリゴンのINNER
	 * https://github.com/yuuhayashi/citygml-osm/issues/138
	 */
	@Test
	public void test51357441() {
		OsmDom osm = testdo("./src/test/resources/51357441_bldg_Issue138.gml");
		try {
	        assertNotNull(osm);
	        BoundsBean bound = osm.getBounds();
			assertNotNull(bound);
	        
			assertNotNull(osm.getWayMap());
			assertEquals(3, osm.getWayMap().size());
			
			// Issue138 innerと重なっていたbuildingは"building:part=yes"にする
			int part = 0;
			int building = 0;
			for (String id : osm.getWayMap().keySet()) {
				ElementWay way = (ElementWay)osm.getWayMap().get(id);
				assertNotNull(way);
				String v = way.getTagValue("building");
				if (v != null) {
					building ++;
					assertEquals("7.6", way.getTagValue("ele"));
					assertEquals("4.2", way.getTagValue("height"));
					assertEquals("2017", way.getTagValue("survey:date"));
				}
				String partv = way.getTagValue("building:part");
				if (partv != null) {
					part ++;
				}
			}
			assertEquals(1, building);
			assertEquals(2, part);
			
			// outerしかないマルチポリゴンは削除されて "type=building"に置き換わること
			assertNotNull(osm.getRelations());
			assertEquals(1, osm.getRelations().size());
			for (ElementRelation relation : osm.getRelations()) {
				assertEquals("building", relation.getTagValue("type"));
				assertEquals("7.6", relation.getTagValue("ele"));
				assertEquals("4.2", relation.getTagValue("height"));
				int outline = 0;
				int parts = 0;
				assertEquals("Relation.member.size()", 3, relation.members.size());
				for (MemberBean member : relation.members) {
					String role = member.getRole();
					if (role.equals("outline")) {
						outline++;
					}
					else if (role.equals("part")) {
						parts++;
					}
				}
				assertEquals("ROLE:outline count", 1, outline);
				assertEquals("count of ROLE:part", 2, parts );
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void test51357441a() {
		OsmDom osm = testdo("./src/test/resources/51357441_bldg_Issue138-1.gml");
		try {
	        assertNotNull(osm);
	        BoundsBean bound = osm.getBounds();
			assertNotNull(bound);
	        
			assertNotNull(osm.getWayMap());
			assertEquals("osm.getWayMap().size()", 4, osm.getWayMap().size());
			
			// Issue138 innerと重なっていたbuildingは"building:part=yes"にする
			int part = 0;
			int building = 0;
			for (String id : osm.getWayMap().keySet()) {
				ElementWay way = (ElementWay)osm.getWayMap().get(id);
				assertNotNull(way);
				String v = way.getTagValue("building:part");
				if (v != null) {
					part ++;
					assertEquals("7.6", way.getTagValue("ele"));
					assertEquals("2.99", way.getTagValue("height"));
					assertEquals("2017", way.getTagValue("survey:date"));
				}
				else if (way.getTagValue("building") != null) {
					building ++;
				}
			}
			assertEquals("count of WAY:building", 1, building);
			assertEquals("count of WAY:part", 1, part);
			
			assertNotNull(osm.getRelations());
			assertEquals("osm.getRelations().size()", 2, osm.getRelations().size());
			for (ElementRelation relation : osm.getRelations()) {
				String type = relation.getTagValue("type");
				if (type.equals("multipolygon")) {
					assertEquals("7.6", relation.getTagValue("ele"));
					assertEquals("4.2", relation.getTagValue("height"));
					int outer = 0;
					int inner = 0;
					assertEquals("relation.members.size()", 2, relation.members.size());
					for (MemberBean member : relation.members) {
						String role = member.getRole();
						if (role.equals("outer")) {
							outer++;
						}
						else if (role.equals("inner")) {
							inner++;
						}
					}
					assertEquals("count of Multipolygon:MEMBER:outer", 1, outer);
					assertEquals("count of Multipolygon:MEMBER:inner", 1, inner);					
				}
				else if (type.equals("building")) {
					assertEquals("7.6", relation.getTagValue("ele"));
					assertEquals("4.2", relation.getTagValue("height"));
					int outline = 0;
					int parts = 0;
					assertEquals(3, relation.members.size());
					for (MemberBean member : relation.members) {
						String role = member.getRole();
						if (role.equals("outline")) {
							outline++;
						}
						else if (role.equals("part")) {
							parts++;
						}
					}
					assertEquals("count of building:MEMBER:outline", 1, outline);
					assertEquals("count of building:MEMBER:part", 2, parts);
				}
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void test51357441b() {
		OsmDom osm = testdo("./src/test/resources/51357441_bldg_Issue138-2.gml");
		try {
	        assertNotNull(osm);
	        BoundsBean bound = osm.getBounds();
			assertNotNull(bound);
	        
			assertNotNull(osm.getWayMap());
			assertEquals(3, osm.getWayMap().size());
			
			// Issue138 innerと重なっていたbuildingは"building:part=yes"にする
			int part = 0;
			int building = 0;
			for (String id : osm.getWayMap().keySet()) {
				ElementWay way = (ElementWay)osm.getWayMap().get(id);
				assertNotNull(way);
				String v = way.getTagValue("building");
				if (v != null) {
					building ++;
					assertEquals("7.6", way.getTagValue("ele"));
					assertEquals("4.2", way.getTagValue("height"));
					assertEquals("2017", way.getTagValue("survey:date"));
				}
				String partv = way.getTagValue("building:part");
				if (partv != null) {
					part ++;
				}
			}
			assertEquals("count of WAY:building", 1, building);
			assertEquals("count of WAY:building:part", 2, part);
			
			assertNotNull(osm.getRelations());
			assertEquals(2, osm.getRelations().size());
			for (ElementRelation relation : osm.getRelations()) {
				String type = relation.getTagValue("type");
				if (type.equals("multipolygon")) {
					assertEquals("7.6", relation.getTagValue("ele"));
					assertEquals("4.2", relation.getTagValue("height"));
					int outer = 0;
					int inner = 0;
					assertEquals(3, relation.members.size());
					for (MemberBean member : relation.members) {
						String role = member.getRole();
						if (role.equals("outer")) {
							outer++;
						}
						else if (role.equals("inner")) {
							inner++;
						}
					}
					assertEquals("count of Multipolygon:MEMBER:outer", 1, outer);
					assertEquals("count of Multipolygon:MEMBER:inner", 1, inner);	
				}
				else if (type.equals("building")) {
					assertEquals("7.6", relation.getTagValue("ele"));
					assertEquals("4.2", relation.getTagValue("height"));
					int outline = 0;
					int parts = 0;
					assertEquals(3, relation.members.size());
					for (MemberBean member : relation.members) {
						String role = member.getRole();
						if (role.equals("outline")) {
							outline++;
						}
						else if (role.equals("part")) {
							parts++;
						}
					}
					assertEquals("count of Building:MEMBER:outline", 1, outline);
					assertEquals("count of Building:MEMBER:part", 2, parts);
				}
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
