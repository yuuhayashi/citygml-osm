package osm.surveyor.osm.api;

import org.junit.Test;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_Issue32 extends GmlLoadRouteTest {

	/**
	 * 
		v1.3.0 LOD2の情報を持つ建物で、建物内に単独ノードがある。 #32
		
		plateauviewで見た時にLOD2の情報を持つ建物について、LOD2の形状の端点と思われるものが、単独ノードとして出てきます。
		
		例：
		東京 533946>53394610_bldg_6697_op2>13101-bldg-365 など
		飯塚市 50303564_bldg_6697_op>40205-bldg-95937 など

	 */
	@Test
	public void test53394610() {
		OsmDom osm = testdo("./src/test/resources/Issue32_13101-bldg-365.gml");
		try {
			assertNotNull(osm.ways);
			assertSame(1, osm.ways.size());
			for (String wayid : osm.ways.keySet()) {
				ElementWay way = (ElementWay)osm.ways.get(wayid);
				assertSame(16, way.getNdList().size());
			}
			assertNotNull(osm.nodes);
			assertSame(15, osm.nodes.size());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void test50303564() {
		OsmDom osm = testdo("./src/test/resources/Issue32_40205-bldg-95937.gml");
		try {
			assertNotNull(osm.ways);
			assertNotNull(osm.nodes);
			System.out.println("osm.nodes.size() = "+ osm.nodes.size());
			assertSame(66, osm.nodes.size());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
	
}
