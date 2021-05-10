package osm.surveyor.osm.update;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.Test;

import osm.surveyor.osm.ElementMember;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.api.CitygmlFileTest;

public class OsmUpdaterTest_B {

	@Test
	public void testSample_b() {
		CitygmlFileTest.test(Paths.get("src/test/resources","sample_b_bldg_6697_op2.gml"));
		
        try {
			ArrayList<String> args = new ArrayList<>();
			args.add("sample_a_bldg_6697_op2.osm");
			OsmUpdater.main(args.toArray(new String[args.size()]));

			OsmDom osm = new OsmDom();
			osm.load(Paths.get("sample_b_bldg_6697_op2.mrg.osm").toFile());
			
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
							assertThat(relation.getTagValue("fixme"), is("PLATEAUデータで更新されています"));
							assertThat(way.tags.size(), is(2));
						}
						if (mem.role.equals("inner")) {
							innerCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertThat(way.action, is("modify"));
							assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-61384"));
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
}
