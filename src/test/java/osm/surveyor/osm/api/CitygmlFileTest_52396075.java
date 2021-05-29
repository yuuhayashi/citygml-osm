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

public class CitygmlFileTest_52396075 {

	/**
	 * Issue #13 「v1.2.4 「箱根町」のデータを変換できない」
	 * https://github.com/yuuhayashi/citygml-osm/issues/13
	 * map "A :建物" as A {
	 *  bldg:Building:id => bldg-cf368523-863d-4bf8-8931-3a9cf99e4e58
	 *  bldg:Building:stringAttribute[name="建物ID"] => 14382-bldg-10718
	 *  bldg:measuredHeight => 13.3
	 *  bldg:lod0FootPrint:Polygon:posList:height => 728.31
	 *  Envelope:srsName => http://www.opengis.net/def/crs/EPSG/0/6697
	 * }
	 */
	@Test
	public void test52396075() {
		CitygmlFileTest.test_do(Paths.get("src/test/resources","52396075_bldg_6697_op.gml"));
		
		OsmDom osm = new OsmDom();
		try {
			osm.parse(Paths.get("52396075_bldg_6697_op.osm").toFile());

			assertThat(osm.ways, notNullValue());
			for (String id : osm.ways.keySet()) {
				ElementWay way = osm.ways.get(id);
				assertThat(way, notNullValue());
				assertThat(way.getTagValue("building"), is("yes"));
				assertThat(way.getTagValue("height"), is("13.3"));
				assertThat(way.getTagValue("ele"), is("728.31"));
				assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 14382-bldg-10718"));
				assertThat(way.tags.size() >= 4, is(true));
			}
			assertThat(osm.ways.size(), is(1));
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
	
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
