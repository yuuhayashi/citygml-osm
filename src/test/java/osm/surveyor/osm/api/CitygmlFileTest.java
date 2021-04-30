package osm.surveyor.osm.api;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import osm.surveyor.citygml.CitygmlFile;
import osm.surveyor.osm.ElementMember;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementTag;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSample_a() {
		test(Paths.get(".","sample_a_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.load(Paths.get("sample_a_bldg_6697_op2.osm").toFile());
			assertThat(osm.relations.size(), is(0));
			for (String id : osm.ways.keySet()) {
				ElementWay way = osm.ways.get(id);
				assertThat(way, notNullValue());
				assertThat(way.tags.size(), is(5));
				if (way.tags.get("source").v.endsWith("; 13111-bldg-365")) {
					assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
					assertThat(way.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
					assertThat(way.tags.get("addr:ref").v, is("13111058003"));
					assertThat(way.tags.get("height").v, is("5.127"));
					assertThat(way.tags.get("building").v, is("yes"));
				}
				else if (way.tags.get("source").v.endsWith("; 13111-bldg-466")) {
					assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
					assertThat(way.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
					assertThat(way.tags.get("addr:ref").v, is("13111058003"));
					assertThat(way.tags.get("height").v, is("6.84"));
					assertThat(way.tags.get("building").v, is("yes"));
				}
				else {
					fail(way.tags.get("source").v);
				}
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testSample_b() {
		test(Paths.get(".","sample_b_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.load(Paths.get("sample_b_bldg_6697_op2.osm").toFile());
			assertThat(osm.relations.size(), is(1));
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertThat(relation, notNullValue());
				String type = relation.tags.get("type").v;
				if (type.equals("multipolygon")) {
					/*
						map "multipolygon :Relation" as multipolygon {
						 type => multipolygon
						 building => yes
						 height => 17.582
						 source => MLIT_PLATEAU;\n http://www.opengis.net/def/crs/EPSG/0/6697
						}
					 */
					assertThat(relation.tags.get("type").v, is("multipolygon"));
					assertThat(relation.tags.get("building").v, is("yes"));
					assertThat(relation.tags.get("addr:full").v, is("東京都大田区大森西五丁目"));
					assertThat(relation.tags.get("addr:ref").v, is("13111006005"));
					assertThat(relation.tags.get("height").v, is("17.582"));
					assertThat(relation.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					assertThat(relation.members.size(), is(2));
					int outerCnt = 0;
					int innerCnt = 0;
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outer")) {
							outerCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
							assertThat(way.tags.size(), is(1));
						}
						if (mem.role.equals("inner")) {
							innerCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-61384"));
							assertThat(way.tags.size(), is(1));
						}
					}
					assertThat(outerCnt, is(1));
					assertThat(innerCnt, is(1));
				}
				else {
					assertThat(type, is("multipolygon"));
				}
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testSample_c() {
		test(Paths.get(".","sample_c_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.load(Paths.get("sample_c_bldg_6697_op2.osm").toFile());
			assertThat(osm.relations.size(), is(1));
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertThat(relation, notNullValue());
				String type = relation.tags.get("type").v;
				if (type.equals("building")) {
					/*
					 	map "building : Relation" as building {
						 type => building
						 building => yes
						 height => 40.492
						 source => MLIT_PLATEAU;\n http://www.opengis.net/def/crs/EPSG/0/6697
						}
					 */
					assertThat(relation.tags.get("type").v, is("building"));
					assertThat(relation.tags.get("building").v, is("yes"));
					assertThat(relation.tags.get("height").v, startsWith("40.492"));
					assertThat(relation.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
					assertThat(relation.tags.get("addr:ref").v, is("13111058003"));
					assertThat(relation.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					int outlineCnt = 0;
					int partCnt = 0;
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outline")) {
							outlineCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertThat(way, notNullValue());
							assertThat(way.tags.get("source").v, startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
							assertThat(way.tags.get("height").v, is("40.492"));
							assertThat(way.tags.get("building").v, is("yes"));
							assertThat(way.tags.size(), is(5));
						}
						if (mem.role.equals("part")) {
							partCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertThat(way, notNullValue());
							ElementTag tag = way.tags.get("source");
							if (tag.v.endsWith("; 13111-bldg-473")) {
								assertThat(tag.v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-473"));
								assertThat(way.tags.get("addr:full").v, is("東京都大田区南六郷三丁目"));
								assertThat(way.tags.get("addr:ref").v, is("13111058003"));
								assertThat(way.tags.get("height").v, is("40.492"));
								assertThat(way.tags.get("building:part").v, is("yes"));
								assertThat(way.tags.size(), is(5));
							}
						}
					}
					assertThat(relation.members.size(), is(3));
					assertThat(outlineCnt, is(1));
					assertThat(partCnt, is(2));
				}
				else if (type.equals("multipolygon")) {
					/*
						map "multipolygon :Relation" as multipolygon {
						 type => multipolygon
						 building => yes
						 height => 32.852000000000004
						 source => MLIT_PLATEAU;\n http://www.opengis.net/def/crs/EPSG/0/6697
						}
					 */
					assertThat(relation.tags.get("type").v, is("multipolygon"));
					assertThat(relation.tags.get("building").v, is("yes"));
					assertThat(relation.tags.get("height"), notNullValue());
					assertThat(relation.tags.get("height").v, startsWith("32.852000000"));
					assertThat(relation.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					assertThat(relation.members.size(), is(3));
					int outerCnt = 0;
					int innerCnt = 0;
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outer")) {
							outerCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertThat(way, notNullValue());
							assertThat(way.tags.size(), is(1));
							ElementTag source = way.tags.get("source");
							assertThat(source.v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
						}
						if (mem.role.equals("inner")) {
							innerCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertThat(way, notNullValue());
							assertThat(way.tags.size(), is(1));
							ElementTag source = way.tags.get("source");
							assertThat(source.v, startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; BLD_"));
						}
					}
					assertThat(outerCnt, is(1));
					assertThat(innerCnt, is(2));
				}
				else {
					assertThat(type, is("building"));
				}
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testSample_d() {
		test(Paths.get(".","sample_d_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.load(Paths.get("sample_d_bldg_6697_op2.osm").toFile());
			assertThat(osm.relations.size(), is(2));
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertThat(relation, notNullValue());
				String type = relation.tags.get("type").v;
				if (type.equals("building")) {
					/*
					 	map "building : Relation" as building {
						 type => building
						 building => yes
						 height => 32.852000000000004
						 source => MLIT_PLATEAU;\n http://www.opengis.net/def/crs/EPSG/0/6697
						}
					 */
					assertThat(relation.tags.get("type").v, is("building"));
					assertThat(relation.tags.get("building").v, is("yes"));
					assertThat(relation.tags.get("height").v, startsWith("32.852000000"));
					assertThat(relation.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					int outlineCnt = 0;
					int partCnt = 0;
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outline")) {
							outlineCnt++;
							assertThat(mem.type, is("relation"));
							ElementRelation multiporygon = osm.relations.get(Long.toString(mem.ref));
							assertThat(multiporygon, notNullValue());
						}
						if (mem.role.equals("part")) {
							partCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertThat(way, notNullValue());
							ElementTag tag = way.tags.get("source");
							if (tag.v.endsWith("; 13111-bldg-72601")) {
								assertThat(tag.v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));
								assertThat(way.tags.get("addr:full").v, is("東京都大田区大森西三丁目"));
								assertThat(way.tags.get("addr:ref").v, is("13111006003"));
								assertThat(way.tags.get("height").v, is("32.852000000000004"));
								assertThat(way.tags.get("building:part").v, is("yes"));
								assertThat(way.tags.size(), is(5));
							}
							else if (tag.v.endsWith("; 13111-bldg-71799")) {
								assertThat(tag.v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-71799"));
								assertThat(way.tags.get("addr:full").v, is("東京都大田区大森西三丁目"));
								assertThat(way.tags.get("addr:ref").v, is("13111006003"));
								assertThat(way.tags.get("height").v, is("6.038"));
								assertThat(way.tags.get("building:part").v, is("yes"));
								assertThat(way.tags.size(), is(5));
							}
						}
					}
					assertThat(relation.members.size(), is(3));
					assertThat(outlineCnt, is(1));
					assertThat(partCnt, is(2));
				}
				else if (type.equals("multipolygon")) {
					/*
						map "multipolygon :Relation" as multipolygon {
						 type => multipolygon
						 building => yes
						 height => 32.852000000000004
						 source => MLIT_PLATEAU;\n http://www.opengis.net/def/crs/EPSG/0/6697
						}
					 */
					assertThat(relation.tags.get("type").v, is("multipolygon"));
					assertThat(relation.tags.get("building").v, is("yes"));
					assertThat(relation.tags.get("height"), notNullValue());
					assertThat(relation.tags.get("height").v, startsWith("32.852000000"));
					assertThat(relation.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					assertThat(relation.members.size(), is(3));
					int outerCnt = 0;
					int innerCnt = 0;
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outer")) {
							outerCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertThat(way, notNullValue());
							assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
							assertThat(way.tags.size(), is(1));
						}
						if (mem.role.equals("inner")) {
							innerCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertThat(way, notNullValue());
							assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-72601"));
							assertThat(way.tags.size(), is(1));
						}
					}
					assertThat(outerCnt, is(1));
					assertThat(innerCnt, is(2));
				}
				else {
					assertThat(type, is("building"));
				}
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}


	@Test
	public void testSample_e() {
		test(Paths.get(".","sample_e_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.load(Paths.get("sample_e_bldg_6697_op2.osm").toFile());
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertThat(relation, notNullValue());
				String type = relation.tags.get("type").v;
				if (type.equals("building")) {
					/*
					 	map "building : Relation" as building {
						 type => building
						 building => yes
						 height => 17.199
						 name => "大田病院"
						 source => MLIT_PLATEAU;\n http://www.opengis.net/def/crs/EPSG/0/6697
						}
					 */
					assertThat(relation.tags.get("type").v, is("building"));
					assertThat(relation.tags.get("building").v, is("yes"));
					assertThat(relation.tags.get("height").v, startsWith("17.199"));
					assertThat(relation.tags.get("name").v, startsWith("大田病院"));
					assertThat(relation.tags.get("addr:full").v, is("東京都大田区大森東四丁目"));
					assertThat(relation.tags.get("addr:ref").v, is("13111007004"));
					assertThat(relation.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					assertThat(relation.tags.size(), is(7));
					int outlineCnt = 0;
					int partCnt = 0;
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outline")) {
							outlineCnt++;
							assertThat(mem.type, is("relation"));
							ElementRelation multiporygon = osm.relations.get(Long.toString(mem.ref));
							assertThat(multiporygon, notNullValue());
						}
						if (mem.role.equals("part")) {
							partCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertThat(way, notNullValue());
							ElementTag tag = way.tags.get("source");
							if (tag.v.endsWith("; 13111-bldg-56522")) {
								assertThat(tag.v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-56522"));
								assertThat(way.tags.get("addr:full").v, is("東京都大田区大森東四丁目"));
								assertThat(way.tags.get("addr:ref").v, is("13111007004"));
								assertThat(way.tags.get("height").v, is("17.199"));
								assertThat(way.tags.get("building:part").v, is("yes"));
								assertThat(way.tags.get("name").v, startsWith("大田病院"));
								assertThat(way.tags.size(), is(6));
							}
							else if (tag.v.endsWith("; 13111-bldg-55333")) {
								assertThat(tag.v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-55333"));
								assertThat(way.tags.get("addr:full").v, is("東京都大田区大森東四丁目"));
								assertThat(way.tags.get("addr:ref").v, is("13111007004"));
								assertThat(way.tags.get("height").v, is("5.5360000000000005"));
								assertThat(way.tags.get("building:part").v, is("yes"));
								assertThat(way.tags.size(), is(5));
							}
						}
					}
					assertThat(relation.members.size(), is(3));
					assertThat(outlineCnt, is(1));
					assertThat(partCnt, is(2));
				}
				else if (type.equals("multipolygon")) {
					/*
						map "multipolygon :Relation" as multipolygon {
						 type => multipolygon
						 building => yes
						 height => 32.852000000000004
						 source => MLIT_PLATEAU;\n http://www.opengis.net/def/crs/EPSG/0/6697
						}
					 */
					assertThat(relation.tags.get("type").v, is("multipolygon"));
					assertThat(relation.tags.get("building").v, is("yes"));
					assertThat(relation.tags.get("height").v, is("17.199"));
					assertThat(relation.tags.get("name").v, is("大田病院"));
					assertThat(relation.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					assertThat(relation.members.size(), is(3));
					int outerCnt = 0;
					int innerCnt = 0;
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outer")) {
							outerCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertThat(way, notNullValue());
							assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
							assertThat(way.tags.size(), is(1));
						}
						if (mem.role.equals("inner")) {
							innerCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertThat(way, notNullValue());
							assertThat(way.tags.get("source").v, is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
							assertThat(way.tags.size(), is(1));
						}
					}
					assertThat(outerCnt, is(1));
					assertThat(innerCnt, is(2));
				}
				else {
					assertThat(type, is("building"));
				}
			}
			assertThat(osm.relations.size(), is(2));
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void test53392547() {
		test(Paths.get(".","53392547_bldg_6697_op2.gml"));
	}

	@Test
	public void test53392588() {
		test(Paths.get(".","53392588_bldg_6697_op2.gml"));
	}

	public void test(Path a) {
		CitygmlFile.doConvert(a);
	}
}
