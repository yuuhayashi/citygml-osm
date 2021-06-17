package osm.surveyor.osm.api;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.nio.file.Paths;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.ElementMember;
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
	@SuppressWarnings("deprecation")
	@Test
	public void test53394610() {
		CitygmlFileTest.test_do(Paths.get("src/test/resources","Issue32_13101-bldg-365.gml"));
		
		OsmDom osm = new OsmDom();
		try {
			osm.parse(Paths.get("Issue32_13101-bldg-365.osm").toFile());
			assertThat(osm.ways, notNullValue());
			assertThat(osm.nodes, notNullValue());
			assertThat(osm.nodes.size() < 15, is(true));
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void test50303564() {
		CitygmlFileTest.test_do(Paths.get("src/test/resources","Issue32_40205-bldg-95937.gml"));
		
		OsmDom osm = new OsmDom();
		try {
			osm.parse(Paths.get("Issue32_40205-bldg-95937.osm").toFile());
			assertThat(osm.ways, notNullValue());
			assertThat(osm.nodes, notNullValue());
			assertThat(osm.nodes.size() < 30, is(true));
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
	
	@SuppressWarnings("deprecation")
	@Test
	@Category(DetailTests.class)
	public void test52396075_a1_parse() {
		CitygmlFileTest.test_doParse(Paths.get("src/test/resources","52396075_bldg_6697_op.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.parse(Paths.get("52396075_bldg_6697_op_1.osm").toFile());

			assertThat(osm.relations, notNullValue());
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertThat(relation, notNullValue());
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					if (relation.getTagValue("source").endsWith("; 14382-bldg-10718")) {
						assertThat(relation.getTagValue("type"), is("building"));
						assertThat(relation.getTagValue("building"), is("yes"));
						assertThat(relation.getTagValue("height"), is("13.3"));
						assertThat(relation.getTagValue("ele"), is("728.31"));
						assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 14382-bldg-10718"));

						int outlineCnt = 0;
						int partCnt = 0;
						for (ElementMember mem : relation.members) {
							if (mem.role.equals("outline")) {
								outlineCnt++;
								assertThat(mem.type, is("relation"));
								ElementRelation outline = osm.relations.get(mem.ref);
								assertThat(outline, notNullValue());
								assertThat(outline.getTagValue("type"), is("multipolygon"));
								assertThat(outline.getTagValue("building"), is("yes"));
								assertThat(outline.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 14382-bldg-10718"));
								assertThat(outline.getTagValue("height"), is("13.3"));
								assertThat(outline.getTagValue("ele"), is("728.31"));
								assertThat(outline.tags.size() >= 5, is(true));
							}
							if (mem.role.equals("part")) {
								partCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.getTagValue("building:levels"), is("1"));
								assertThat(way.getTagValue("height"), is("13.3"));
								assertThat(way.getTagValue("ele"), is("728.31"));
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 14382-bldg-10718"));
								assertThat(way.tags.size() >= 5, is(true));
							}
						}
						assertThat(outlineCnt, is(1));
						assertThat(partCnt, is(1));
					}
					assertThat(relation.members.size(), is(2));
				}
				else if (type.equals("multipolygon")) {
					if (relation.getTagValue("source").endsWith("; 14382-bldg-10718")) {
						assertThat(relation.getTagValue("type"), is("multipolygon"));
						assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 14382-bldg-10718"));
						assertThat(relation.getTagValue("height"), is("13.3"));
						assertThat(relation.getTagValue("ele"), is("728.31"));
						assertThat(relation.getTagValue("building"), is("yes"));

						int outerCnt = 0;
						int innerCnt = 0;
						for (ElementMember mem : relation.members) {
							if (mem.role.equals("outer")) {
								outerCnt++;
								assertThat(mem.type, is("way"));
								ElementWay way = osm.ways.get(Long.toString(mem.ref));
								assertThat(way, notNullValue());
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 14382-bldg-10718"));
								assertThat(way.tags.size(), is(1));
							}
						}
						assertThat(outerCnt, is(1));
						assertThat(innerCnt, is(0));
					}
					assertThat(relation.members.size(), is(1));
				}
			}
			assertThat(osm.relations.size(), is(2));
			
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
