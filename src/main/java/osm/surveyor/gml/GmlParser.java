package osm.surveyor.gml;

import java.util.StringTokenizer;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import osm.surveyor.citygml.CityModelParser;
import osm.surveyor.citygml.CitygmlFile;
import osm.surveyor.osm.ElementBounds;
import osm.surveyor.osm.ElementNode;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmFile;

/**
 * CityGMLファイルをパースする
 * @param gmlFile
 * @code{
 * <core:CityModel>
 *   <core:cityObjectMember/>
 *     <bldg:Building gml:id="BLD_fc50c7d9-76ac-4576-bfbd-f37c74410928">
 *       <gml:posList>35.541657275471835 139.7156383865409 14.072000000000001 35.542252321638614 139.71535363948732 14.072000000000001 35.54210367440277 139.7148860223014 14.072000000000001 35.54206164434519 139.71490626649856 14.072000000000001 35.5420440155531 139.7148536858433 14.072000000000001 35.541981356256336 139.7146575788015 14.072000000000001 35.54142914946131 139.71491844541285 14.072000000000001 35.54153100551663 139.71523889596378 14.072000000000001 35.541657275471835 139.7156383865409 14.072000000000001
 *       </gml:posList>
 *     </bldg:Building>
 *   <core:cityObjectMember/>
 * </core:CityModel>
 * }
 * 
 */
public class GmlParser extends DefaultHandler {
	public StringBuffer outSb = null;		// TEXT待ちじゃない場合は NULL
	OsmFile osmfile;
	
    public GmlParser(OsmFile osmfile) {
        super();
        this.osmfile = osmfile;
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
    
    public ElementBounds bounds = null;
    public ElementWay way = null;
    
    /**
     * 要素の開始タグ読み込み時に毎回呼ばれる
     */
    public void startElement(String uri,String localName, String qName, Attributes atts) {
		if(qName.equals("gml:boundedBy")){
			bounds = new ElementBounds();
		}
		else if(qName.equals("gml:lowerCorner")){
			outSb = new StringBuffer();
		}
		else if(qName.equals("gml:upperCorner")){
			outSb = new StringBuffer();
		}
		
		else if(qName.equals("gml:LinearRing")){
			way = new ElementWay(CitygmlFile.getId());
		}
		else if(qName.equals("gml:posList")){
			outSb = new StringBuffer();
		}
		else {
		}
	}

    /**
     * 要素の終了タグ読み込み時に毎回呼ばれる
     */
    public void endElement(String uri,String localName,String qName) {
		if(qName.equals("gml:boundedBy")){
			bounds.printout(osmfile);
		}
		else if(qName.equals("gml:lowerCorner")){
			// <gml:lowerCorner>35.53956274455546 139.701140502832 1.627</gml:lowerCorner>
			StringTokenizer st = new StringTokenizer(outSb.toString(), " ");
			if (st.hasMoreTokens()) {
				String lat = st.nextToken();
				bounds.minlat = lat;
			}
			if (st.hasMoreTokens()) {
				String lon = st.nextToken();
				bounds.minlon = lon;
			}
			outSb = null;
		}
		else if(qName.equals("gml:upperCorner")){
			// <gml:upperCorner>35.541755325236224 139.71239981225776 43.802</gml:upperCorner>
			StringTokenizer st = new StringTokenizer(outSb.toString(), " ");
			if (st.hasMoreTokens()) {
				String lat = st.nextToken();
				bounds.maxlat = lat;
			}
			if (st.hasMoreTokens()) {
				String lon = st.nextToken();
				bounds.maxlon = lon;
			}
			outSb = null;
		}
		
		else if(qName.equals("gml:LinearRing")){
			// <gml:LinearRing>
			// <gml:posList>35.541657275471835 139.7156383865409 14.072000000000001 35.542252321638614 139.71535363948732 14.072000000000001 35.54210367440277 139.7148860223014 14.072000000000001 35.54206164434519 139.71490626649856 14.072000000000001 35.5420440155531 139.7148536858433 14.072000000000001 35.541981356256336 139.7146575788015 14.072000000000001 35.54142914946131 139.71491844541285 14.072000000000001 35.54153100551663 139.71523889596378 14.072000000000001 35.541657275471835 139.7156383865409 14.072000000000001</gml:posList>
			// <gml:LinearRing>
			// AREAに変更する
			if (way != null) {
				way.toArea();
				if (CityModelParser.roofEdges != null) {
					CityModelParser.roofEdges.add(way);
				}
				way = null;
			}
		}
		else if(qName.equals("gml:posList")){
			// <gml:posList>35.541657275471835 139.7156383865409 14.072000000000001 35.542252321638614 139.71535363948732 14.072000000000001 35.54210367440277 139.7148860223014 14.072000000000001 35.54206164434519 139.71490626649856 14.072000000000001 35.5420440155531 139.7148536858433 14.072000000000001 35.541981356256336 139.7146575788015 14.072000000000001 35.54142914946131 139.71491844541285 14.072000000000001 35.54153100551663 139.71523889596378 14.072000000000001 35.541657275471835 139.7156383865409 14.072000000000001</gml:posList>
			if (outSb != null) {
				String height = null;
				StringTokenizer st = new StringTokenizer(outSb.toString(), " ");
				while(true) {
					ElementNode node;
					// lat
					if (st.hasMoreTokens()) {
						node = new ElementNode(CitygmlFile.getId());
						node.lat = st.nextToken();
					}
					else {
						break;
					}
					
					// lon
					if (st.hasMoreTokens()) {
						node.lon = st.nextToken();
					}
					else {
						break;
					}
					
					// height
					if (st.hasMoreTokens()) {
						height = st.nextToken();
						if (way != null) {
							way.addNode(node);
						}
					}
					else {
						break;
					}
				}
				if (way != null) {
					way.addTag("height", height);
				}
			}
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
