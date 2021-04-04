package osm.surveyor.citygml;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * CityGMLファイルをパースする
 * @param gmlFile
 * @code{
 * <core:CityModel>
 *   <core:cityObjectMember/>
 *     :
 *   <core:cityObjectMember/>
 * </core:CityModel>
 * }
 * 
 */
public class CityObjectMember extends DefaultHandler {
	StringBuffer outSb;
	CityObjectMember member = null;
    AppParameters params;

    public CityObjectMember(AppParameters params) {
        super();
        this.params = params;
    }
    
	/**
     * ドキュメント開始
     */
    public void startDocument() {
        outSb = new StringBuffer();
    }
 
    /**
     * ドキュメント終了
     */
    public void endDocument() {
    }
    
    /**
     * 要素の開始タグ読み込み時に毎回呼ばれる
     */
    public void startElement(String uri,String localName, String qName, Attributes atts) {
		if(qName.equals("core:cityObjectMember")){
		}
		else {
			if (member != null) {
			}
		}
	}

    /**
     * 要素の終了タグ読み込み時に毎回呼ばれる
     */
    public void endElement(String uri,String localName,String qName) {
        if(qName.equals("core:cityObjectMember")){
			if (member != null) {
			}
        }
		outSb = new StringBuffer();
    }

    /**
     * テキストデータ読み込み時に毎回呼ばれる
     */
    public void characters(char[] ch, int offset, int length) {
        outSb.append(new String(ch, offset, length));
    }
 }
