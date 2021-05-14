package osm.surveyor.update;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runners.MethodSorters;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.ElementBounds;
import osm.surveyor.osm.ElementMember;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.api.CitygmlFileTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OsmUpdaterTest_B {
	static final String SOURCE = "sample_b_bldg_6697_op2";

	@Test
	public void testSample_b() {
		CitygmlFileTest.test(Paths.get("src/test/resources", SOURCE+".gml"));
		
        try {
			ArrayList<String> args = new ArrayList<>();
			args.add(SOURCE+".osm");
			OsmUpdater.main(args.toArray(new String[args.size()]));

			OsmDom osm = new OsmDom();
			osm.parse(Paths.get(SOURCE+".mrg.osm").toFile());
			
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertThat(relation, notNullValue());
				String type = relation.getTagValue("type");
				if (type.equals("multipolygon")) {
					assertThat(relation.action, is("modify"));
					assertThat(relation.getTagValue("type"), is("multipolygon"));
					assertThat(relation.getTagValue("building"), is("yes"));
					assertThat(relation.getTagValue("addr:full"), is("東京都大田区大森西五丁目"));
					assertThat(relation.getTagValue("addr:ref"), is("13111006005"));
					assertThat(relation.getTagValue("height"), is("17.582"));
					assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					
					int outerCnt = 0;
					int innerCnt = 0;
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outer")) {
							outerCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertThat(way.action, is("modify"));
							assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
							assertThat(way.getTagValue("fixme"), is("PLATEAUデータで更新されています"));
							assertThat(way.getTagValue("building"), is(nullValue()));
							assertThat(way.tags.size(), is(2));
						}
						if (mem.role.equals("inner")) {
							innerCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertThat(way.action, is("modify"));
							assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-61384"));
							assertThat(way.getTagValue("building"), is(nullValue()));
							assertThat(way.tags.size(), is(1));
						}
					}
					assertThat(outerCnt, is(1));
					assertThat(innerCnt, is(1));
					assertThat(relation.members.size(), is(2));
				}
			}
			assertThat(osm.relations.size(), is(1));
			assertThat(osm.ways.size(), is(2));
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	/**
	 * "sample_b_bldg_6697_op2.org.osm"の出力
	 */
	@Test
	@Category(DetailTests.class)
	public void testSample_b1() {
        try {
    		// 指定されたOSMファイルの<bound/>を取得する
        	OsmDom dom = new OsmDom();
        	dom.parse(Paths.get("src/test/resources/", SOURCE+".osm").toFile());
    		ElementBounds bounds = dom.getBounds();

    		// OSMから<bound>範囲内の現在のデータを取得する
    		OsmDom sdom = new OsmDom();
    		sdom.setBounds(bounds);
    		sdom.downloadMap();
    		sdom.export(Paths.get(SOURCE+".org.osm").toFile());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	/**
	 * "sample_b_bldg_6697_op2.org.osm"のチェック
	 */
	@Test
	@Category(DetailTests.class)
	public void testSample_b1check() {
        try {
			OsmDom osm = new OsmDom();
			osm.parse(Paths.get(SOURCE+".org.osm").toFile());
			assertThat(osm.relations.size() >= 1, is(true));
			assertThat(osm.ways.size() >= 10, is(true));
			assertThat(osm.nodes.size() > 100, is(true));
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
