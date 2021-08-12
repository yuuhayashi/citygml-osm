package osm.surveyor.download;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.HashMap;
import java.util.function.Consumer;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import osm.surveyor.osm.ElementBounds;
import osm.surveyor.osm.TagBean;
import osm.surveyor.osm.OsmDom;

public class OsmDownloader {
	static final String suffix1 = ".osm";
	static final String suffix2 = ".fixed.org.osm";
	static final String suffix3 = ".target.org.osm";

	public static void main(String[] args) throws Exception {
        try {
			File dir = new File(".");
			Files.list(dir.toPath()).forEach(new Consumer<Path>() {
				@Override
				public void accept(Path a) {
					if (Files.isRegularFile(a)) {
						File file = a.toFile();
						String filename = file.getName();
						System.out.println(filename);
						if (filename.endsWith(suffix1) && !filename.endsWith(suffix2) && !filename.endsWith(suffix3)) {
							try {
								OsmDownloader updater = new OsmDownloader(file);
								updater.download();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			});
            
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public OsmDom dom;		// source "*.osm" file.	
	public OsmDom sdom;
	public OsmDom ddom;
	
	public OsmDownloader(String filepath) throws ParserConfigurationException, SAXException, IOException, ParseException {
		this(Paths.get(filepath).toFile());
	}

	public OsmDownloader(File file) throws ParserConfigurationException, SAXException, IOException, ParseException {
		dom = new OsmDom();
		dom.parse(file);
	}
	
	/**
	 * OpenStreetMapから 既存データを収録する
	 * 
	 * @throws MalformedURLException
	 * @throws ProtocolException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	public void download() throws MalformedURLException, ProtocolException, IOException, ParserConfigurationException, SAXException {
		// 指定されたOSMファイルの<bound/>を取得する
		ElementBounds bounds = dom.getBounds();

		OsmDom org = new OsmDom();
		org.setBounds(bounds);
		
		// エクスポート用のOsmDomを生成する
		this.ddom = new OsmDom();
		this.ddom.setBounds(bounds);

		// OSMから<bound>範囲内の現在のデータを取得する
		org.downloadMap();
		
		// "building"関係のPOIのみに絞る
		this.sdom = new OsmDom();
		org.filterBuilding(this.sdom);
	}
	
	/**
	 * tag.key=`building*` を有するPOIを'building'POIとみなす
	 * 
	 */
	static boolean isBuildingTag(HashMap<String,TagBean> tags) {
		for (String k : tags.keySet()) {
			TagBean tag = tags.get(k);
			if (tag.k.startsWith("building")) {
				return true;
			}
		}
		return false;
	}

	public void export(File out) throws ParserConfigurationException {
		ddom.export(out);
	}
	
}
