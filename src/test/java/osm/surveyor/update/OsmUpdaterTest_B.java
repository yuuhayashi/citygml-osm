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
			assertThat(osm.ways.size() >= 2, is(true));
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
			String filename = "sample_b_bldg_6697_op2";
			OsmUpdater updater = new OsmUpdater(Paths.get("src/test/resources/"+ filename +".osm").toFile());

			// 疑似ダウンロード
			AllTests.download(updater, Paths.get("src/test/resources/"+ filename +".org.osm").toFile());
			
    		// 既存POIとマージする
			updater.load();
			
			updater.ddom.export(Paths.get(filename +".mrg.osm").toFile());
			
			int delCnt = 0;
			int modifyCnt = 0;
			for (String id : updater.ddom.ways.keySet()) {
				ElementWay way = updater.ddom.ways.get(id);
				if (way.action != null) {
					switch (way.action) {
					case "delete":
						delCnt++;
						break;
					case "modify":
						modifyCnt++;
						break;
					}
				}
			}
			assertThat(modifyCnt, is(2));
			assertThat(delCnt, is(2));
			
			for (String id : updater.ddom.relations.keySet()) {
				ElementRelation relation = updater.ddom.relations.get(id);
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
							ElementWay way = updater.ddom.ways.get(Long.toString(mem.ref));
							assertThat(way.action, is("modify"));
							assertThat(way.id, is(757291544l));
							assertThat(way.changeset, is("78683298"));
							assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
							assertThat(way.getTagValue("fixme"), is("PLATEAUデータで更新されています"));
							assertThat(way.getTagValue("building"), is(nullValue()));
							assertThat(way.tags.size(), is(2));
						}
						if (mem.role.equals("inner")) {
							innerCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = updater.ddom.ways.get(Long.toString(mem.ref));
							assertThat(way.action, is("modify"));
							assertThat(way.id < 0, is(true));
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
			assertThat(updater.ddom.relations.size(), is(1));
			assertThat(updater.ddom.ways.size(), is(4));
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
