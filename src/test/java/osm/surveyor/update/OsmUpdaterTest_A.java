package osm.surveyor.update;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runners.MethodSorters;
import org.xml.sax.SAXException;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.ElementBounds;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.api.CitygmlFileTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OsmUpdaterTest_A {

	/**
	 * 東京都大田区南六郷三丁目
	 */
	@Test
	public void test_a() {
		CitygmlFileTest.test(Paths.get("src/test/resources","sample_a_bldg_6697_op2.gml"));
		
        try {
			ArrayList<String> args = new ArrayList<>();
			args.add("sample_a_bldg_6697_op2.osm");
			OsmUpdater.main(args.toArray(new String[args.size()]));

			OsmDom osm = new OsmDom();
	        osm.parse(Paths.get("sample_a_bldg_6697_op2.mrg.osm").toFile());
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
	 * "sample_b_bldg_6697_op2.org.osm"の出力
	 */
	@Test
	@Category(DetailTests.class)
	public void test_b1() {
        try {
    		// 指定されたOSMファイルの<bound/>を取得する
        	OsmDom dom = new OsmDom();
        	dom.parse(Paths.get("src/test/resources/", "sample_a_bldg_6697_op2.osm").toFile());
    		ElementBounds bounds = dom.getBounds();

    		// OSMから<bound>範囲内の現在のデータを取得する
    		OsmDom sdom = new OsmDom();
    		sdom.setBounds(bounds);
    		sdom.downloadMap();
    		sdom.export(Paths.get("sample_a_bldg_6697_op2.org.osm").toFile());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	/**
	 * "sample_a_bldg_6697_op2.org.osm"のチェック
	 * 'filterBuilding()'のテスト
	 * 
	 * INPUT： "sample_a_bldg_6697_op2.org.osm"
	 */
	@Test
	@Category(DetailTests.class)
	public void test_b1check() {
        try {
			OsmDom osm = new OsmDom();
			osm.parse(Paths.get("src/test/resources/sample_a_bldg_6697_op2.org.osm").toFile());
			assertThat(osm.ways.size() > 0, is(true));
			assertThat(osm.nodes.size() > 10, is(true));

			OsmDom sdom = new OsmDom();
			osm.filterBuilding(sdom);

			assertThat(sdom.ways.size() > 0, is(true));
			assertThat(sdom.relations.size(), is(0));
			assertThat(sdom.nodes.size() >= 4, is(true));
        } catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	/**
	 * 'filterBuilding()'のテスト
	 * 
	 * INPUT： "sample_a_bldg_6697_op2.org.osm"
	 */
	@Test
	@Category(DetailTests.class)
	public void test_b2() {
        try {
    		// 指定されたOSMファイルの<bound/>を取得する
        	OsmDom sdom = new OsmDom();
        	OsmDom org = new OsmDom();
        	org.parse(Paths.get("src/test/resources/sample_a_bldg_6697_op2.org.osm").toFile());
    		org.filterBuilding(sdom);

			assertThat(sdom.ways.size() >= 1, is(true));
			assertThat(sdom.relations.size(), is(0));
			assertThat(sdom.nodes.size() >= 4, is(true));
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	/**
	 * 'filterBuilding()'のテスト
	 * 
	 * INPUT： "sample_a_bldg_6697_op2.osm"
	 * INPUT： "sample_a_bldg_6697_op2.org.osm"
	 */
	@Test
	@Category(DetailTests.class)
	public void test_b3() {
        try {
			String filename = "sample_a_bldg_6697_op2";
			OsmUpdater updater = new OsmUpdater(Paths.get("src/test/resources/"+ filename +".osm").toFile());

			// 疑似ダウンロード
			download(updater, Paths.get("src/test/resources/"+ filename +".org.osm").toFile());
			
    		// 既存POIとマージする
			updater.load();
    		
			assertThat(updater.ddom.relations, notNullValue());
			assertThat(updater.ddom.relations.size(), is(0));
			for (String id : updater.ddom.ways.keySet()) {
				ElementWay way = updater.ddom.ways.get(id);
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
			assertThat(updater.ddom.ways.size(), is(2));
			assertThat(updater.ddom.relations.size(), is(0));
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
	
	/**
	 * 疑似ダウンロード
	 * 
	 * @param updater
	 * @param orgFile
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParseException
	 */
	private void download(OsmUpdater updater, File orgFile) throws ParserConfigurationException, SAXException, IOException, ParseException {
		// 指定されたOSMファイルの<bound/>を取得する
		ElementBounds bounds = updater.dom.getBounds();

		// エクスポート用のOsmDomを生成する
		updater.ddom = new OsmDom();
		updater.ddom.setBounds(bounds);

		// 指定されたOSMファイルの<bound/>を取得する
		// org.downloadMap();
		updater.sdom = new OsmDom();
    	OsmDom org = new OsmDom();
    	org.parse(orgFile);
		org.filterBuilding(updater.sdom);
	}

	/**
	 * INPUT: "sample_a_bldg_6697_op2.osm"
	 * INPUT: "sample_a3_bldg_6697_op2.org.osm"
	 */
	@Test
	@Category(DetailTests.class)
	public void test_d1() {
        try {
    		// 指定されたOSMファイルの<bound/>を取得する
        	OsmDom org = new OsmDom();
        	OsmDom sdom = new OsmDom();
        	org.parse(Paths.get("src/test/resources/", "sample_a3_bldg_6697_op2.org.osm").toFile());
    		org.filterBuilding(sdom);

    		sdom.export(Paths.get("org.xml").toFile());
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
