package osm.surveyor.osm.api;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_C_3 extends CitygmlFileTest3 {

	@Test
	@Category(DetailTests.class)
	public void testSample_c3_removeSinglePart() {
        try {
        	OsmDom osm = testdo("./src/test/resources/sample_c_bldg_6697_op2.gml");
    		
			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697", relation.getTagValue("source"));
					assertEquals(relation.getTagValue("addr:full"), ("東京都大田区南六郷三丁目"));
					assertEquals(relation.getTagValue("height"), ("42.7"));
					assertEquals(relation.getTagValue("ele"), ("2.81"));
					assertEquals(relation.getTagValue("building"), ("yes"));
					assertEquals(relation.getTagValue("building:levels"), ("2"));
					assertEquals(relation.getTagValue("building:levels:underground"), ("1"));

					int outlineCnt = 0;
					int partCnt = 0;
					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outline")) {
							outlineCnt++;
						}
						if (mem.getRole().equals("part")) {
							partCnt++;
							assertEquals(mem.getType(), ("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							if (way.getTagValue("source").endsWith("; 13111-bldg-473")) {
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels"), ("2"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区南六郷三丁目"));
								assertEquals(way.getTagValue("height"), ("42.7"));
								assertEquals(way.getTagValue("ele"), ("2.81"));
								assertEquals(way.getTagValue("start_date"), ("1976"));								
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));
								assertEquals(8, way.getTagList().size());
							}
							else if (way.getTagValue("source").endsWith("; 13111-bldg-386")) {
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-386"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区南六郷三丁目"));
								assertEquals(way.getTagValue("height"), ("7.3"));
								assertEquals(way.getTagValue("ele"), ("2.81"));
								assertEquals(way.getTagValue("start_date"), ("2003"));
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels:underground"), ("1"));
								assertEquals(8, way.getTagList().size());
							}
						}
					}
					assertEquals(0, outlineCnt);
					assertEquals(2, partCnt);
					assertEquals(2, relation.members.size());
				}
			}
			assertEquals(1, osm.relations.size());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	@Test
	@Category(DetailTests.class)
	public void testSample_cc3_removeSinglePart() {
        try {
        	OsmDom osm = testdo("./src/test/resources/sample_cc_bldg_6697_op2.gml");
    		
			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697", relation.getTagValue("source"));
					assertEquals(relation.getTagValue("addr:full"), ("東京都大田区大森中一丁目"));
					assertNotNull(relation.getTagValue("height"));
					assertEquals(relation.getTagValue("building"), ("yes"));
					assertEquals(relation.getTagValue("building:levels"), ("3"));
					assertEquals(relation.getTagValue("building:levels:underground"), ("2"));

					int outlineCnt = 0;
					int partCnt = 0;
					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outline")) {
							outlineCnt++;
						}
						if (mem.getRole().equals("part")) {
							partCnt++;
							assertEquals(mem.getType(), ("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
							assertNotNull(way);
							if (way.getTagValue("source").endsWith("; 13111-bldg-59928")) {
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels"), ("2"));
								assertEquals(way.getTagValue("building:levels:underground"), ("2"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区大森中一丁目"));
								assertEquals(way.getTagValue("height"), ("7.1"));
								assertEquals(way.getTagValue("ele"), ("2.48"));
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-59928"));
								assertEquals(8, way.getTagList().size());
							}
							else if (way.getTagValue("source").endsWith("; 13111-bldg-59955")) {
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-59955"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区大森中一丁目"));
								assertEquals(way.getTagValue("height"), ("5.5"));
								assertEquals(way.getTagValue("ele"), ("2.53"));
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(way.getTagValue("building:levels"), ("3"));
								assertEquals(way.getTagValue("building:levels:underground"), ("1"));
								assertEquals(8, way.getTagList().size());
							}
							else if (way.getTagValue("source").endsWith("; 13111-bldg-59680")) {
								assertEquals(way.getTagValue("source"), ("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-59680"));
								assertEquals(way.getTagValue("addr:full"), ("東京都大田区大森中一丁目"));
								assertEquals(way.getTagValue("height"), ("6.8"));
								assertEquals(way.getTagValue("ele"), ("2.54"));
								assertEquals(way.getTagValue("building:part"), ("yes"));
								assertEquals(6, way.getTagList().size());
							}
						}
					}
					assertEquals(0, outlineCnt);
					assertEquals(3, partCnt);
					assertEquals(3, relation.members.size());
				}
			}
			assertEquals(1, osm.relations.size());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

}
