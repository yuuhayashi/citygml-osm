package osm.surveyor.osm.update;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import osm.surveyor.osm.DetailTests;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.api.CitygmlFileTest;

public class OsmUpdaterTest_A {

	/**
	 * 東京都大田区南六郷三丁目
	 */
	@Test
	public void testSample_a() {
		CitygmlFileTest.test(Paths.get("src/test/resources","sample_a_bldg_6697_op2.gml"));
		
        try {
			ArrayList<String> args = new ArrayList<>();
			args.add("sample_a_bldg_6697_op2.osm");
			OsmUpdater.main(args.toArray(new String[args.size()]));

			OsmDom osm = new OsmDom();
	        osm.load(Paths.get("sample_a_bldg_6697_op2.mrg.osm").toFile());
			assertThat(osm.relations, notNullValue());
			assertThat(osm.relations.size(), is(0));
			for (String id : osm.ways.keySet()) {
				ElementWay way = osm.ways.get(id);
				assertThat(way, notNullValue());
				
				if (way.getTagValue("source").endsWith("; 13111-bldg-365")) {
					assertThat(way.action, is("modify"));
					assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-365"));
					assertThat(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
					assertThat(way.getTagValue("addr:ref"), is("13111058003"));
					assertThat(way.getTagValue("height"), is("5.127"));
					assertThat(way.getTagValue("building"), is("yes"));
					assertThat(way.tags.size(), is(5));
				}
				else if (way.getTagValue("source").endsWith("; 13111-bldg-466")) {
					assertThat(way.action, is("modify"));
					assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-466"));
					assertThat(way.getTagValue("addr:full"), is("東京都大田区南六郷三丁目"));
					assertThat(way.getTagValue("height"), is("6.84"));
					assertThat(way.getTagValue("building"), is("yes"));
					assertThat(way.getTagValue("fixme"), is("PLATEAUデータで更新されています"));
					assertThat(way.tags.size(), is(5));
				}
			}
			assertThat(osm.ways.size(), is(2));
			assertThat(osm.relations.size(), is(0));
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	/**
	 * 値のない'addr:ref'
	 * 13111-bldg-141846
	 * 東京都大田区大森東一丁目
	 */
	@Test
	@Category(DetailTests.class)
	public void testSample_aa() {
		CitygmlFileTest.test(Paths.get("src/test/resources","sample_aa_bldg_6697_op2.gml"));

		try {
			ArrayList<String> args = new ArrayList<>();
			args.add("sample_aa_bldg_6697_op2.osm");
			OsmUpdater.main(args.toArray(new String[args.size()]));

			OsmDom osm = new OsmDom();
	        osm.load(Paths.get("sample_aa_bldg_6697_op2.mrg.osm").toFile());
			assertThat(osm.relations, notNullValue());
			assertThat(osm.relations.size(), is(0));
			for (String id : osm.ways.keySet()) {
				ElementWay way = osm.ways.get(id);
				assertThat(way, notNullValue());
				if (way.getTagValue("source").endsWith("; 13111-bldg-141846")) {
					assertThat(way.action, is("modify"));
					assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-141846"));
					assertThat(way.getTagValue("addr:full"), is("東京都大田区大森東一丁目"));
					assertThat(way.getTagValue("height"), is("8.454"));
					assertThat(way.getTagValue("building"), is("yes"));
					assertThat(way.tags.size(), is(4));
				}
			}
			assertThat(osm.ways.size(), is(1));
			assertThat(osm.relations.size(), is(0));
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	/**
	 * 接触しているビルディング
	 * 13111-bldg-64135
	 */
	@Test
	@Category(DetailTests.class)
	public void testSample_ab() {
		CitygmlFileTest.test(Paths.get("src/test/resources","sample_ab_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.load(Paths.get("sample_ab_bldg_6697_op2.osm").toFile());
			assertThat(osm.relations, notNullValue());
			for (String id : osm.ways.keySet()) {
				ElementWay way = osm.ways.get(id);
				assertThat(way, notNullValue());
				assertThat(way.getTagValue("source"), startsWith("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-"));
				assertThat(way.getTagValue("addr:full"), is("東京都大田区大森西五丁目"));
				assertThat(way.getTagValue("addr:ref"), is("13111006005"));
				assertThat(way.getTagValue("height"), notNullValue());
				assertThat(way.getTagValue("building"), is("yes"));
				assertThat(way.tags.size(), is(5));
			}
			assertThat(osm.ways.size(), is(2));
			assertThat(osm.relations.size(), is(0));
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
