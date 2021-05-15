package osm.surveyor.update;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import org.xml.sax.SAXException;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.ElementBounds;
import osm.surveyor.osm.OsmDom;

@RunWith(Categories.class)
@ExcludeCategory(DetailTests.class)
@SuiteClasses({ OsmUpdaterTest_A.class, OsmUpdaterTest.class, PostgisTest.class })
public class AllTests {
	
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
	public static void download(OsmUpdater updater, File orgFile) throws ParserConfigurationException, SAXException, IOException, ParseException {
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

}
