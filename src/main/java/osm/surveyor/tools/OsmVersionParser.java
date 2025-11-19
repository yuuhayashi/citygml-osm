package osm.surveyor.tools;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import lombok.Getter;

/**
 * OSMファイルをパースする
 * @param gmlFile
 * 
 */
@Getter
public class OsmVersionParser extends DefaultHandler {

    public OsmVersionParser() {
        super();
    }
    
	/**
     * ドキュメント開始
	 * @throws SAXException 
     */
    public void startDocument() throws SAXException {
    	super.startDocument();
    }
 
    /**
     * ドキュメント終了
     * @throws SAXException 
     */
    public void endDocument() throws SAXException {
    	super.endDocument();
    }
    
	String version = null;		// <version/>
	
    /**
     * 要素の開始タグ読み込み時に毎回呼ばれる
     */
    public void startElement(String uri,String localName, String qName, Attributes atts) {
		if (qName.equals("osm")) {
			version = atts.getValue("version");
		}
	}
}
