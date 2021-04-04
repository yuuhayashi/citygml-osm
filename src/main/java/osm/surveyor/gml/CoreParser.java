package osm.surveyor.gml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * CityGMLファイルをパースする
 * @param gmlFile
 * @code{
 * <xAL:LocalityName Type="Town">東京都大田区南六郷三丁目</xAL:LocalityName>
 * }
 * 
 */
public class CoreParser extends DefaultHandler {
	public StringBuffer outSb = null;		// TEXT待ちじゃない場合は NULL
	
    public CoreParser() {
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
    
    /*
     * addr:full = *
     */
    String localityNameType = null;
    public String localityName = null;
    
    /**
     * 要素の開始タグ読み込み時に毎回呼ばれる
     */
    public void startElement(String uri,String localName, String qName, Attributes atts) {
		if(qName.equals("xAL:LocalityName")){
			// <xAL:LocalityName Type="Town"></xAL:LocalityName>
			localityNameType = getAttributes("Type", atts);
	    	outSb = new StringBuffer();
		}
		else {
		}
	}

    /**
     * 要素の終了タグ読み込み時に毎回呼ばれる
     */
    public void endElement(String uri,String localName,String qName) {
		if(qName.equals("xAL:LocalityName")){
			// <xAL:LocalityName>東京都大田区南六郷三丁目</xAL:LocalityName>
			localityName = outSb.toString();
			outSb = null;
		}
        else {
        }
    }

    /**
     * テキストデータ読み込み時に毎回呼ばれる
     */
    public void characters(char[] ch, int offset, int length) {
        outSb.append(new String(ch, offset, length));
    }
    
    String getAttributes(String key, Attributes atts) {
		for (int i = 0; i < atts.getLength(); i++) {
			String aname = atts.getQName(i);
			if (aname.equals(key)) {
				return (atts.getValue(i));
			}
		}
    	return null;
    }
}
