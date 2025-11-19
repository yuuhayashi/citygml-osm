package osm.surveyor;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import lombok.Getter;

/**
 * POM.xmlファイルをパースする
 * @param gmlFile
 * 
 */
@Getter
public class PomParser extends DefaultHandler {
	public StringBuffer outSb = null;		// TEXT待ちじゃない場合は NULL

    public PomParser() {
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
	String name = null;			// <name>
	
    /**
     * 要素の開始タグ読み込み時に毎回呼ばれる
     */
    public void startElement(String uri,String localName, String qName, Attributes atts) {
		if (qName.equals("version")) {
	    	outSb = new StringBuffer();
		}
		else if (qName.equals("name")) {
	    	outSb = new StringBuffer();
		}
	}

    /**
     * 要素の終了タグ読み込み時に毎回呼ばれる
     */
    public void endElement(String uri, String localName, String qName) {
		if (qName.equals("version")) {
			if ((outSb != null) && (version == null)) {
				version = outSb.toString();
			}
			outSb = null;
		}
		else if (qName.equals("name")){
			if ((outSb != null) && (name == null)) {
				name = outSb.toString();
			}
			outSb = null;
		}
    }
    
    /**
     * テキストデータ読み込み時に毎回呼ばれる
     */
    public void characters(char[] ch, int offset, int length) {
    	if (outSb != null) {
    		outSb.append(new String(ch, offset, length));
    	}
    }
}
