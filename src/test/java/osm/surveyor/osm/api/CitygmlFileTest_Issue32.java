package osm.surveyor.osm.api;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.nio.file.Paths;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_Issue32 {

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
		CitygmlFileTest.test_do(Paths.get("src/test/resources","Issue32_13101-bldg-365.gml"));
		
		OsmDom osm = new OsmDom();
		try {
			osm.parse(Paths.get("Issue32_13101-bldg-365.osm").toFile());
			assertNotNull(osm.ways);
			assertSame(1, osm.ways.size());
			for (String wayid : osm.ways.keySet()) {
				ElementWay way = osm.ways.get(wayid);
				assertSame(16, way.nds.size());
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
		CitygmlFileTest.test_do(Paths.get("src/test/resources","Issue32_40205-bldg-95937.gml"));
		
		OsmDom osm = new OsmDom();
		try {
			osm.parse(Paths.get("Issue32_40205-bldg-95937.osm").toFile());
			assertNotNull(osm.ways);
			assertNotNull(osm.nodes);
			System.out.println("osm.nodes.size() = "+ osm.nodes.size());
			assertSame(66, osm.nodes.size());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
	
	@SuppressWarnings("deprecation")
	@Test
	@Category(DetailTests.class)
	public void test52396075_a1_parse() {
		CitygmlFileTest.test_doParse(Paths.get("src/test/resources","Issue32_13101-bldg-365.gml"));

		System.out.println("Issue32_13101-bldg-365_1.osm");
		OsmDom osm = new OsmDom();
        try {
			osm.parse(Paths.get("Issue32_13101-bldg-365_1.osm").toFile());
			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					if (relation.getTagValue("source").endsWith("; 13101-bldg-365")) {
						assertEquals("building", relation.getTagValue("type"));
						assertEquals("yes", relation.getTagValue("building"));
						assertEquals("114.7", relation.getTagValue("height"));
						assertEquals("2.14", relation.getTagValue("ele"));
						assertEquals("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13101-bldg-365", relation.getTagValue("source"));

						int outlineCnt = 0;
						int partCnt = 0;
						for (MemberBean mem : relation.members) {
							if (mem.getRole().equals("outline")) {
								outlineCnt++;
								assertEquals("relation", mem.getType());
								ElementRelation outline = osm.relations.get(mem.getRef());
								assertNotNull(outline);
								assertThat(outline.getTagValue("type"), is("multipolygon"));
								assertThat(outline.getTagValue("building"), is("yes"));
								assertThat(outline.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13101-bldg-365"));
								assertThat(outline.getTagValue("height"), is("114.7"));
								assertEquals("2.14", outline.getTagValue("ele"));
								assertTrue(outline.getTagList().size() >= 5);
							}
							if (mem.getRole().equals("part")) {
								partCnt++;
								assertThat(mem.getType(), is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.getRef()));
								assertNotNull(way);
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.getTagValue("height"), is("114.7"));
								assertEquals("2.14", way.getTagValue("ele"));
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13101-bldg-365"));
								assertTrue(way.getTagList().size() >= 5);
							}
						}
						assertEquals(1, outlineCnt);
						assertEquals(1, partCnt);
					}
					assertEquals(2, relation.members.size());
				}
			}
			assertEquals(2, osm.relations.size());
			
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
