package osm.surveyor.osm.api;

import org.junit.Test;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.OsmDom;

/**
 * Issue #79
 * 館林市 
 * @author hayashi
 *
 */
public class Test_79 extends GmlLoadRouteTest {

	@Test
	public void testSample_e() {
        try {
            OsmDom osm = testdo("./src/test/resources/Issue_79.gml");
			checkSample_79(osm);
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
    
	public static void checkSample_79(OsmDom osm) {
        try {
			assertNotNull(osm);
			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
		        	for (MemberBean member : relation.members) {
		        		if (member.isWay()) {
		        			ElementWay way = (ElementWay)osm.ways.get(member.getRef());
			        		assertNotNull(way);
		        			if (member.getRole().equals("outline")) {
		                		assertEquals("retail", way.getTagValue("building"));
		                		assertEquals("18.7", way.getTagValue("ele"));
		                		assertEquals("9.7", way.getTagValue("height"));
		        			}
		        			else if (member.getRole().equals("part")) {
		        				if (way.getTagValue("ref:MLIT_PLATEAU").equals("10207-bldg-63561")) {
			                		assertEquals("yes", way.getTagValue("building:part"));
			                		assertEquals("19.4", way.getTagValue("ele"));
			                		assertEquals("4.9", way.getTagValue("height"));
		        				}
		        				else if (way.getTagValue("ref:MLIT_PLATEAU").equals("10207-bldg-45841")) {
			                		assertEquals("retail", way.getTagValue("building:part"));
			                		assertEquals("18.7", way.getTagValue("ele"));
			                		assertEquals("9.7", way.getTagValue("height"));
		        				}
		        				else {
		        					fail();
		        				}
		        			}
		        			else {
		        				fail();
		        			}
		        		}
		        		else {
		        			fail();
		        		}
		        	}

		        	assertEquals("retail", relation.getTagValue("building"));
					assertEquals("18.7", relation.getTagValue("ele"));
					assertEquals("9.7", relation.getTagValue("height"));
				}
				else {
					fail();
				}
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
