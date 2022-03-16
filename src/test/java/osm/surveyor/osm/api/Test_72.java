package osm.surveyor.osm.api;

import org.junit.Test;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

/**
 * 飯塚市 40205_iizuka-shi_CityGML の50302469_bldg_6697_op.gmlで
 * ref:MLIT_PLATEAU=40205-bldg-91809が属する建物
 * ref:MLIT_PLATEAU=40205-bldg-91812が属する建物
 * @author hayashi
 *
 */
public class Test_72 extends GmlLoadRouteTest {

	@Test
	public void testSample_e() {
        try {
            OsmDom osm = testdo("./src/test/resources/50302469_bldg_6697_op.gml");
			checkSample_72(osm);
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
    
	public static void checkSample_72(OsmDom osm) {
        try {
			assertNotNull(osm);
			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					String relationHeight = relation.getTagValue("height");
					String relationEle = relation.getTagValue("ele");

					int outlineCnt = 0;
					for (MemberBean mem : relation.members) {
						if (mem.getRole().equals("outline")) {
							outlineCnt++;
							if (mem.getType().equals("way")) {
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertEquals(relationEle, way.getTagValue("ele"));
								assertEquals(relationHeight, way.getTagValue("height"));
							}
						}
					}
					assertTrue(outlineCnt > 0);
				}
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
