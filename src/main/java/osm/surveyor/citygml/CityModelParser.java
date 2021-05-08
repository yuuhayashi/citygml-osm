package osm.surveyor.citygml;

import java.util.HashMap;
import java.util.StringTokenizer;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import osm.surveyor.osm.ElementBounds;
import osm.surveyor.osm.ElementMember;
import osm.surveyor.osm.ElementNode;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementTag;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.RelationMultipolygon;
import osm.surveyor.osm.RelationBuilding;

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
public class CityModelParser extends DefaultHandler {
	public StringBuffer outSb = null;		// TEXT待ちじゃない場合は NULL
    HashMap<String, ElementNode> nodes = null;	// k= node.point.getGeom()

	OsmDom osm;
	
    public CityModelParser(OsmDom osm) {
        super();
        this.osm = osm;
        this.osm.source = "MLIT_PLATEAU";
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
    
    ElementBounds bounds = null;					// <gml:boundedBy/>
	RelationBuilding building = null;				// <bldg:Building/>
	String buildingId = null;						// <bldg:Building gml:id="buildingId" />
	String name = null;								// <gml:name/>
	String measuredHeight = null;					// <bldg:measuredHeight/>
	boolean edgeFull = false;						// 建物形状がセットされたら true
	RelationBuilding roof = null;					// <bldg:lod0RoofEdge/>
	RelationBuilding footPrint = null;				// <bldg:lod0FootPrint/>
	RelationMultipolygon multipolygon = null;		// <gml:Polygon/>
	ElementMember member = null;					// <gml:Polygon/>
    ElementWay way = null;							// <gml:LinearRing/>
	
    /*
     * 全国地方公共団体コード
     * addr:ref = 13111058003
     * 
     * <gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
     * 	<gen:value>13111058003</gen:value>
     * </gen:stringAttribute>
     */
    String addr_ref = null;
    static final String ADDR_REF_CODE13 = "13_区市町村コード_";
    static final String BLDG_ID = "建物ID";
    
    /**
     * 要素の開始タグ読み込み時に毎回呼ばれる
     */
    public void startElement(String uri,String localName, String qName, Attributes atts) {
		if(qName.equals("core:CityModel")){
			nodes = new HashMap<>();
		}
		else if(qName.equals("gml:boundedBy")){
			bounds = new ElementBounds();
		}
		else if(qName.equals("gml:Envelope")){
			// <gml:Envelope srsName="http://www.opengis.net/def/crs/EPSG/0/6697">
			osm.srsName = getAttributes("srsName", atts);
		}
		else if(qName.equals("gml:lowerCorner")){
			outSb = new StringBuffer();
		}
		else if(qName.equals("gml:upperCorner")){
			outSb = new StringBuffer();
		}
		
		else if(qName.equals("bldg:Building")){
			building = new RelationBuilding(osm.getNewId());
			building.addTag("type", "building");
			building.addTag("building", "yes");
			for (int i = 0; i < atts.getLength(); i++) {
				String aname = atts.getQName(i);
				if (aname.equals("gml:id")) {
					buildingId = atts.getValue(i);
				}
			}
		}
    	else if(qName.equals("gen:stringAttribute")){
			// <gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
			if (getAttributes("name", atts).startsWith(ADDR_REF_CODE13)) {
				addr_ref = "";
			}
			if (getAttributes("name", atts).startsWith(BLDG_ID)) {
				buildingId = "";
			}
		}
    	else if(qName.equals("gen:value")){
			// <gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
    		//   <gen:value>13111058003</gen:value>
			if ((addr_ref != null) && addr_ref.isEmpty()) {
		    	outSb = new StringBuffer();
			}
			if ((buildingId != null) && buildingId.isEmpty()) {
		    	outSb = new StringBuffer();
			}
		}
		
		else if(qName.equals("gml:name")){
			name = "";
	    	outSb = new StringBuffer();
		}
		else if(qName.equals("bldg:measuredHeight")){
			measuredHeight = "";
	    	outSb = new StringBuffer();
		}
		else if(qName.equals("bldg:lod0RoofEdge")){
			roof = new RelationBuilding(osm.getNewId());
			edgeFull = false;
		}
		else if(qName.equals("bldg:lod0FootPrint")){
			footPrint = new RelationBuilding(osm.getNewId());
			edgeFull = false;
		}
		else if(qName.equals("gml:Polygon")){
			if (!edgeFull) {
				multipolygon = new RelationMultipolygon(osm.getNewId());
				multipolygon.addTag("building", "yes");
			}
		}
		else if(qName.equals("gml:exterior")){
			if (multipolygon != null) {
				member = new ElementMember();
				member.setRole("outer");
			}
		}
		else if(qName.equals("gml:interior")){
			if (multipolygon != null) {
				member = new ElementMember();
				member.setRole("inner");
			}
		}
		else if(qName.equals("gml:LinearRing")){
			way = new ElementWay(osm.getNewId());
		}
		else if(qName.equals("gml:posList")){
			outSb = new StringBuffer();
		}

		else if(qName.equals("xAL:LocalityName")){
			// <xAL:LocalityName Type="Town"></xAL:LocalityName>
			//localityNameType = getAttributes("Type", atts);
	    	outSb = new StringBuffer();
		}
		else {
		}
	}

    /**
     * 要素の終了タグ読み込み時に毎回呼ばれる
     */
    public void endElement(String uri, String localName, String qName) {
		if(qName.equals("core:CityModel")){
			for (String key : nodes.keySet()) {
				ElementNode node = nodes.get(key);
				osm.nodes.put(Long.toString(node.id), node);
			}
		}
		else if(qName.equals("gml:boundedBy")){
			osm.setBounds(bounds);
		}
		else if(qName.equals("gml:Envelope")){
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

		else if(qName.equals("bldg:Building")){
			if (building != null) {
				for (ElementMember mem : building.members) {
					if (mem.type.equals("way")) {
						ElementWay way = osm.ways.get(Long.toString(mem.ref));
						way.addTag("addr:ref", building.getTagValue("addr:ref"));
						way.addTag("addr:full", building.getTagValue("addr:full"));
						way.addTag("source", getSourceStr(buildingId));
						if ((name != null) && !name.isEmpty()) {
							way.addTag("name", name);
						}
					}
					else if (mem.type.equals("relation")) {
						ElementRelation relation = osm.relations.get(Long.toString(mem.ref));
						relation.addTag("addr:ref", building.getTagValue("addr:ref"));
						relation.addTag("addr:full", building.getTagValue("addr:full"));
						relation.addTag("source", getSourceStr(buildingId));
						if ((name != null) && !name.isEmpty()) {
							relation.addTag("name", name);
						}
					}
				}
				building.addTag("source", getSourceStr(buildingId));
				osm.relations.put(building);
			}
			building = null;
			buildingId = "";
			name = "";
		}
    	else if(qName.equals("gen:stringAttribute")){
			if (building != null) {
				// <gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
				if ((addr_ref != null) && !addr_ref.isEmpty()) {
					building.addTag("addr:ref", addr_ref);
					addr_ref = null;
				}
			}
		}
    	else if(qName.equals("gml:name")){
			if ((name != null) && (name.isEmpty()) && (outSb != null)) {
				name = outSb.toString();
			}
			outSb = null;
		}
    	else if(qName.equals("bldg:measuredHeight")){
			if ((measuredHeight != null) && (measuredHeight.isEmpty()) && (outSb != null)) {
				measuredHeight = outSb.toString();
			}
			outSb = null;
		}
    	else if(qName.equals("gen:value")){
			// <gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
    		//   <gen:value>13111058003</gen:value>
			if ((addr_ref != null) && addr_ref.isEmpty() && (outSb != null)) {
				addr_ref = outSb.toString();
				if (addr_ref.isEmpty()) {
					addr_ref = null;
				}
			}
			if ((buildingId != null) && (buildingId.isEmpty()) && (outSb != null)) {
				buildingId = outSb.toString();
			}
			outSb = null;
		}
		
    	else if(qName.equals("bldg:lod0RoofEdge")){
    		if (building != null) {
    			if (roof != null) {
    				for (ElementMember mem : roof.members) {
    					building.members.add(mem);
    				}
    				building.copyTag(roof);
    				roof = null;
    			}
    		}
		}
    	else if(qName.equals("bldg:lod0FootPrint")){
    		if (building != null) {
    			if (footPrint != null) {
    				for (ElementMember mem : footPrint.members) {
    					building.members.add(mem);
    				}
    				building.copyTag(footPrint);
    				footPrint = null;
    			}
    		}
		}
		else if(qName.equals("gml:Polygon")){
			if ((multipolygon != null) && !multipolygon.members.isEmpty()) {
				if (roof != null) {
					osm.relations.put(multipolygon);
					roof.addMember(multipolygon, "outline");
				}
				else if (footPrint != null) {
					osm.relations.put(multipolygon);
					footPrint.addMember(multipolygon, "outline");
				}
				multipolygon = null;
			}
		}
		else if (qName.equals("gml:exterior")){
			if ((way != null) && (member != null)) {
				way.addTag("source", getSourceStr(buildingId));
				if (!edgeFull) {
					if (roof != null) {
						if ((name != null) && !name.isEmpty()) {
							way.addTag("name", name);
						}
						ElementWay part = way.copy(osm.getNewId());
						part.addTag("building:part", "yes");
						osm.ways.put(part);
						roof.copyTag(part);
						roof.replaceTag("building:part", new ElementTag("building", "yes"));
						roof.addMember(part, "part");
						edgeFull = true;
					}
					else if (footPrint != null) {
						if ((name != null) && !name.isEmpty()) {
							way.addTag("name", name);
						}
						String ele = way.getTagValue("height");
						if (ele != null) {
							way.tags.remove("height");
							way.addTag("ele", ele);
						}
	    				if ((measuredHeight != null) && !measuredHeight.isEmpty()) {
	        				way.addTag("height", new String(measuredHeight));
	        				measuredHeight = null;
	    				}
						ElementWay part = way.copy(osm.getNewId());
						part.addTag("building:part", "yes");
						osm.ways.put(part);
						footPrint.copyTag(part);
						footPrint.replaceTag("building:part", new ElementTag("building", "yes"));
						footPrint.addMember(part, "part");
						edgeFull = true;
					}
				}
				if (multipolygon != null) {
					ElementWay outer = way.copy(osm.getNewId());
					multipolygon.copyTag(outer);
					outer.tags.remove("name");
					outer.tags.remove("height");
					outer.tags.remove("ele");
					osm.ways.put(outer);
					multipolygon.addMember(outer, "outer");
				}
			}
			way = null;
			member = null;
		}
		else if (qName.equals("gml:interior")){
			if ((way != null) && (member != null)) {
				if (multipolygon != null) {
					way.tags.remove("height");
					way.addTag("source", getSourceStr(buildingId));
					osm.ways.put(way);
					multipolygon.addMember(way, "inner");
				}
			}
			way = null;
			member = null;
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
						node = new ElementNode(osm.getNewId());
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
							way.addNode(putNode(node.clone()));
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
			if (outSb != null) {
				if (building != null) {
					building.addTag("addr:full", outSb.toString());
				}
			}
			outSb = null;
		}

		else if(qName.equals("core:CityModel")){
        }
		else if(qName.equals("core:cityObjectMember")){
		}
        else {
        }
    }
    
    /**
     * 
     * @param poi
     * @return
     */
    String getSourceStr(String buildingId) {
    	String src = osm.getSource();
    	if (buildingId != null) {
    		src += "; "+ buildingId;
    	}
    	return src;
    }
    String getSourceStr() {
    	return osm.getSource();
    }

    /**
     * テキストデータ読み込み時に毎回呼ばれる
     */
    public void characters(char[] ch, int offset, int length) {
    	if (outSb != null) {
    		outSb.append(new String(ch, offset, length));
    	}
    }
    
    /**
     * 同位置NODEの二重登録を防ぐ
     * @param node
     * @return
     */
    ElementNode putNode(ElementNode node) {
    	if (this.nodes == null) {
    		this.nodes = new HashMap<>();
    	}
    	ElementNode pre = this.nodes.get(node.point.getGeom());
    	if (pre != null) {
    		return pre;
    	}
    	this.nodes.put(node.point.getGeom(), node);
    	return node;
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
