package osm.surveyor.update;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

/**
 * `mvn test -Dtest=osm.surveyor.update.AllTests`
 * 
 */
@RunWith(Categories.class)
@ExcludeCategory(DetailTests.class)
@SuiteClasses({ OsmUpdaterTest_A.class, OsmUpdaterTest.class })
public class AllTests {

	/**
	 * OsmUpdater.main(Paths.get(args[0]));
	 * 
	 * @param a
	 */
	public static OsmUpdater accept(Path a) {
		String suffix1 = ".osm";
		String suffix2 = ".mrg.osm";
		String suffix3 = ".org.osm";
		if (Files.isRegularFile(a)) {
			File file = a.toFile();
			String filename = file.getName();
			System.out.println(filename);
			if (filename.endsWith(suffix1) && !filename.endsWith(suffix2) && !filename.endsWith(suffix3)) {
				try {
					OsmUpdater updater = new OsmUpdater(file);
					updater.download();
					updater.load();
					filename = filename.substring(0, filename.length() - suffix1.length());
					//updater.ddom.export(Paths.get(filename + suffix2).toFile());
					//updater.sdom.export(Paths.get(filename + suffix3).toFile());
					return updater;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		fail();
		return null;
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
