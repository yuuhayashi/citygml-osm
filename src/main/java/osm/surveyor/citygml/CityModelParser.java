package osm.surveyor.citygml;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import osm.surveyor.osm.ElementBounds;
import osm.surveyor.osm.ElementNode;
import osm.surveyor.osm.ElementNodeList;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

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
 *     </bldg:Building>
 *   <core:cityObjectMember/>
 * </core:CityModel>
 * }
 * 
 */
public class CityModelParser extends DefaultHandler {
	public StringBuffer outSb = null;		// TEXT待ちじゃない場合は NULL

	OsmDom osm;
	
    public CityModelParser(OsmDom osm) {
        super();
        this.osm = osm;
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
    
	ElementNodeList nodelist = null;
    ElementBounds bounds = null;
	ElementBuilding building = null;

	/*
     * List<roofEdge>
     */
	ArrayList<ElementWay> roofEdges = null;

    /*
     * roofEdge
     */
    ElementWay way = null;
    public ElementWay getWay() {
    	return way;
    }
    public boolean existWay() {
    	return (way != null);
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
    	else if(qName.equals("xAL:LocalityName")){
			// <xAL:LocalityName Type="Town"></xAL:LocalityName>
			localityNameType = getAttributes("Type", atts);
	    	outSb = new StringBuffer();
		}
		else if(qName.equals("core:CityModel")){
			nodelist = new ElementNodeList();
		}
		else if(qName.equals("core:cityObjectMember")){
			building = new ElementBuilding(CitygmlFile.getId());
		}
		else if(qName.equals("bldg:Building")){
			for (int i = 0; i < atts.getLength(); i++) {
				String aname = atts.getQName(i);
				if (aname.equals("gml:id")) {
					building.source_ref = atts.getValue(i);
				}
			}
	    	this.roofEdges = new ArrayList<>();
		}
		else if(qName.equals("bldg:lod0RoofEdge")){
		}
		else {
		}
	}

    /**
     * 要素の終了タグ読み込み時に毎回呼ばれる
     */
    public void endElement(String uri, String localName, String qName) {
		if(qName.equals("gml:boundedBy")){
			osm.setBounds(bounds);
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
						String lat = st.nextToken();
						node.point.setLat(lat);
					}
					else {
						break;
					}
					
					// lon
					if (st.hasMoreTokens()) {
						node.point.lon = st.nextToken();
					}
					else {
						break;
					}
					
					// height
					if (st.hasMoreTokens()) {
						height = st.nextToken();
						if (way != null) {
							way.addNode(nodelist.append(node));
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
    	else if(qName.equals("xAL:LocalityName")){
			// <xAL:LocalityName>東京都大田区南六郷三丁目</xAL:LocalityName>
			localityName = outSb.toString();
			outSb = null;
		}

		else if(qName.equals("core:CityModel")){
			if (nodelist != null) {
				for (String key : nodelist.keySet()) {
					osm.addNode(nodelist.get(key));
				}
				nodelist = null;
			}
        }
		else if(qName.equals("core:cityObjectMember")){
			if (building != null) {
				osm.addRelations(building);
				building = null;
			}
		}
		else if(qName.equals("bldg:Building")){
			if (building.source_ref != null) {
				building.addTag("source:ref:id", building.source_ref);
			}
			if (roofEdges != null) {
				for (ElementWay way : this.roofEdges) {
					osm.addWay(way);
					building.addMember(way, "part");
				}
		    	this.roofEdges = null;
			}
			if (localityName != null) {
				building.addTag("addr:full", localityName);
				localityName = null;
			}
		}
		else if(qName.equals("bldg:lod0RoofEdge")){
			if (existWay()) {
				ElementWay roofEdge = getWay();
				roofEdge.addTag("building:part", "yes");
				this.roofEdges.add(roofEdge);
			}
		}
        else {
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
