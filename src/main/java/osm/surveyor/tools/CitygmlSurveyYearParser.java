package osm.surveyor.tools;

import java.nio.file.Paths;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import lombok.Getter;
import osm.surveyor.citygml.ConversionTable;

/**
 * CityGMLファイルをパースする
 * @param gmlFile
 * @code{
	<gml:boundedBy>
		<gml:Envelope srsName="http://www.opengis.net/def/crs/EPSG/0/6697" srsDimension="3">
			<gml:lowerCorner>35.54070862164589 139.71245218213994 2.02</gml:lowerCorner>
			<gml:upperCorner>35.542252321638614 139.7156383865409 40.492</gml:upperCorner>
		</gml:Envelope>
	</gml:boundedBy>
 * <core:CityModel>
 *   <core:cityObjectMember/>
 *     <bldg:Building gml:id="BLD_fc50c7d9-76ac-4576-bfbd-f37c74410928">
 *       <bldg:lod0RoofEdge>
 *         <gml:posList>35.541657275471835 139.7156383865409 14.072000000000001 35.542252321638614 139.71535363948732 14.072000000000001 35.54210367440277 139.7148860223014 14.072000000000001 35.54206164434519 139.71490626649856 14.072000000000001 35.5420440155531 139.7148536858433 14.072000000000001 35.541981356256336 139.7146575788015 14.072000000000001 35.54142914946131 139.71491844541285 14.072000000000001 35.54153100551663 139.71523889596378 14.072000000000001 35.541657275471835 139.7156383865409 14.072000000000001
 *         </gml:posList>
 *       </bldg:lod0RoofEdge>
 *       <gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
 * 	       <gen:value>13111058003</gen:value>
 *       </gen:stringAttribute>
 *       <xAL:LocalityName Type="Town"></xAL:LocalityName>
 *     </bldg:Building>
 *   <core:cityObjectMember/>
 * </core:CityModel>
 * }
 * 
 */
@Getter
public class CitygmlSurveyYearParser extends DefaultHandler {
	public StringBuffer outSb = null;		// TEXT待ちじゃない場合は NULL
    
	String surveyYear = null;					// <uro:surveyYear/> 測量年

	ConversionTable conversionTable;
	
    public CitygmlSurveyYearParser() {
        super();
        this.conversionTable = new ConversionTable(Paths.get(ConversionTable.fileName).toFile());
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
	
    /**
     * 要素の開始タグ読み込み時に毎回呼ばれる
     */
    public void startElement(String uri,String localName, String qName, Attributes atts) {
		if (qName.equals("uro:surveyYear")){
			surveyYear = "";
	    	outSb = new StringBuffer();
		}
	}

    /**
     * 要素の終了タグ読み込み時に毎回呼ばれる
     */
    public void endElement(String uri, String localName, String qName) {
		if (qName.equals("uro:surveyYear")){
			if ((surveyYear != null) && (surveyYear.isEmpty()) && (outSb != null)) {
				surveyYear = outSb.toString();
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

