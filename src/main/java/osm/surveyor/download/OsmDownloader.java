package osm.surveyor.download;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import osm.surveyor.osm.TagBean;
import osm.surveyor.osm.OsmDom;

public class OsmDownloader {
	static final String suffix1 = ".osm";
	static final String suffix2 = ".fixed.org.osm";
	static final String suffix3 = ".target.org.osm";


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
}
