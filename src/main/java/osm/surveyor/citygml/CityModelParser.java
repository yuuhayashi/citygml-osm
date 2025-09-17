package osm.surveyor.citygml;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import lombok.Getter;
import osm.surveyor.osm.BoundsBean;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.NdBean;
import osm.surveyor.osm.NodeBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.TagBean;
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
@Getter
public class CityModelParser extends DefaultHandler {
	public StringBuffer outSb = null;		// TEXT待ちじゃない場合は NULL
    HashMap<String, NodeBean> nodes = null;	// k= node.point.getGeom()

	OsmDom osm;
	ConversionTable conversionTable;
	
    public CityModelParser(OsmDom osm) {
        super();
        this.osm = osm;
        this.storeysAboveGround = 0;
        this.storeysBelowGround = 0;
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
    
    BoundsBean bounds = null;					// <gml:boundedBy/>
	String surveyYear = null;					// <uro:surveyYear/> 測量年
	RelationBuilding building = null;				// <bldg:Building/>
	int buildingCount = 10;
	String buildingId = null;						// <bldg:Building gml:id="buildingId" />
	String name = null;								// <gml:name/>
	TagBean usage = null;						// <bldg:usage/>	用途
	String yearOfConstruction = null;				// <bldg:yearOfConstruction/>	建築年
	String measuredHeight = null;					// <bldg:measuredHeight/>
	int storeysAboveGround = 0;						// <bldg:storeysAboveGround/>
	int storeysBelowGround = 0;						// <bldg:storeysBelowGround/>
	boolean edgeFull = false;						// 建物形状がセットされたら true
	boolean lod0 = false;							// lod0RoofEdge or lod0FootPrint があれば True
	RelationBuilding nonLod0 = null; 				// <bldg:lod0RoofEdge/>
	RelationBuilding roof = null;					// <bldg:lod0RoofEdge/>
	RelationBuilding footPrint = null;				// <bldg:lod0FootPrint/>
	boolean solidWay = false;						// 
	ArrayList<ElementWay> solids = null;			// <bldg:lod1Solid/>
	RelationMultipolygon multipolygon = null;		// <gml:Polygon/>
	MemberBean member = null;					// <gml:exterior/>,<gml:interior/>
    ElementWay elementWay = null;							// <gml:LinearRing/>
	
    /*
     * 全国地方公共団体コード
     * addr:ref = 13111058003
     * 
     * <gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
     * 	<gen:value>13111058003</gen:value>
     * </gen:stringAttribute>
     */
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
			bounds = new BoundsBean();
		}
		else if(qName.equals("uro:surveyYear")){
			surveyYear = "";
	    	outSb = new StringBuffer();
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
			for (int i = 0; i < atts.getLength(); i++) {
				String aname = atts.getQName(i);
				if (aname.equals("gml:id")) {
					buildingId = atts.getValue(i);
				}
			}
		}
    	else if(qName.equals("gen:stringAttribute")){
			if (getAttributes("name", atts).startsWith(BLDG_ID)) {
				buildingId = "";
			}
		}
    	else if(qName.equals("gen:value")){
    		//   <gen:value>13111058003</gen:value>
			if ((buildingId != null) && buildingId.isEmpty()) {
		    	outSb = new StringBuffer();
			}
		}
    	else if(qName.equals("uro:buildingID")){
    		//   [GMLv4] <uro:buildingID>11230-bldg-28587</uro:buildingID>
    		buildingId = "";
    		outSb = new StringBuffer();
		}
		else if(qName.equals("gml:name")){
			name = "";
	    	outSb = new StringBuffer();
		}
		else if(qName.equals("bldg:usage")){
			usage = new TagBean("building", "yes");
	    	outSb = new StringBuffer();
		}
		else if(qName.equals("bldg:yearOfConstruction")){
			yearOfConstruction = "";
	    	outSb = new StringBuffer();
		}
		else if(qName.equals("bldg:measuredHeight")){
			measuredHeight = "";
	    	outSb = new StringBuffer();
		}
		else if(qName.equals("bldg:storeysAboveGround")){
			storeysAboveGround = 0;
	    	outSb = new StringBuffer();
		}
		else if(qName.equals("bldg:storeysBelowGround")){
			storeysBelowGround = 0;
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
		else if(qName.equals("bldg:lod1Solid")){
			solidWay = false;
			solids = new ArrayList<>();
		}
		else if(qName.equals("gml:Polygon")){
			if (!edgeFull) {
				multipolygon = new RelationMultipolygon(osm.getNewId());
			}
		}
		else if(qName.equals("gml:exterior")){
			if (multipolygon != null) {
				member = new MemberBean();
				member.setRole("outer");
			}
		}
		else if(qName.equals("gml:interior")){
			if (multipolygon != null) {
				member = new MemberBean();
				member.setRole("inner");
			}
		}
		else if(qName.equals("gml:LinearRing")){
			elementWay = new ElementWay(osm.getNewId());
		}
		else if(qName.equals("gml:posList")){
			outSb = new StringBuffer();
		}
	}

    /**
     * 要素の終了タグ読み込み時に毎回呼ばれる
     */
    public void endElement(String uri, String localName, String qName) {
		if(qName.equals("core:CityModel")){
			for (String key : nodes.keySet()) {
				NodeBean node = nodes.get(key);
				osm.nodes.put(node);
			}
			System.out.println();
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
				if (usage == null) {
					usage = new TagBean("building", "yes");
				}
				String ele = checkNumberString(building.getTagValue("ele"));
				String maxele = checkNumberString(building.getTagValue("maxele"));
				if (maxele != null) {
					building.removeTag("maxele");
				}
				if (building.getTagValue("height") == null) {
					if (maxele != null) {
						if (ele != null) {
							building.addTag("height", rounding(2, new BigDecimal(maxele).subtract(new BigDecimal(ele)).toString()));
						}
						else {
							building.addTag("height", rounding(2, new BigDecimal(maxele).toString()));
						}
					}
				}
				for (MemberBean mem : building.members) {
					if (mem.getType().equals("way")) {
						ElementWay way = (ElementWay)osm.getWayMap().get(mem.getRef());
						way.removeTag("maxele");
						String num = checkNumberString(rounding(2, building.getTagValue("height")));
						if (num != null) {
							way.addTag("height", num);
						}
						num = checkNumberString(rounding(1, building.getTagValue("ele")));
						if (num != null) {
							way.addTag("ele", num);
						}
						if ((name != null) && !name.isEmpty()) {
							way.addTag("name", name);
						}
						way.addTag("survey:date", building.getTagValue("survey:date"));
						way.addTag("start_date", building.getTagValue("start_date"));
						if (mem.getRole().equals("part")) {
							way.addTag("building:part", usage.v);
						}
						else {
							way.addTag("building", usage.v);							
						}
						way.addTag("building:levels", building.getTagValue("building:levels"));
						way.addTag("building:levels:underground", building.getTagValue("building:levels:underground"));
						way.addTag("ref:MLIT_PLATEAU", buildingId);
					}
					else if (mem.getType().equals("relation")) {
						ElementRelation relation = osm.relationMap.get(mem.getRef());
						relation.removeTag("maxele");
						relation.addTag("start_date", building.getTagValue("start_date"));
						relation.addTag("survey:date", building.getTagValue("survey:date"));
						String num = checkNumberString(building.getTagValue("building:levels"));
						if (num != null) {
							relation.addTag("building:levels", num);
						}
						num = checkNumberString(building.getTagValue("building:levels:underground"));
						if (num != null) {
							relation.addTag("building:levels:underground", num);
						}
						num = checkNumberString(rounding(2, building.getTagValue("height")));
						if (num != null) {
							relation.addTag("height", num);
						}
						num = checkNumberString(rounding(1, building.getTagValue("ele")));
						if (num != null) {
							relation.addTag("ele", num);
						}
						if ((name != null) && !name.isEmpty()) {
							relation.addTag("name", name);
						}
						if (mem.getRole().equals("part")) {
							relation.addTag("building:part", usage.v);
						}
						else {
							relation.addTag("building", usage.v);
						}
					}
				}
				building.addTag(usage);
				osm.relationMap.put(building);
				buildingCount--;
				if (buildingCount < 1) {
					buildingCount = 10;
					System.out.print(":");
				}
			}
			building = null;
			usage = null;
			buildingId = "";
			name = "";
		}
    	else if(qName.equals("gen:stringAttribute")){
		}
    	else if(qName.equals("gml:name")){
			if ((name != null) && (name.isEmpty()) && (outSb != null)) {
				name = outSb.toString();
			}
			outSb = null;
		}		
    	else if(qName.equals("uro:surveyYear")){
			if ((surveyYear != null) && (outSb != null)) {
				surveyYear = checkYearString(outSb.toString());
				if ((building != null) && (surveyYear != null)) {
					building.addTag("survey:date", surveyYear);
				}
			}
			outSb = null;
		}
    	else if(qName.equals("bldg:usage")){
			if ((usage != null) && (outSb != null)) {
				String code = outSb.toString();
				if (building != null) {
					usage.v = this.conversionTable.getUsageBuilding(code);
					building.addTag(usage);
				}
			}
			outSb = null;
		}
    	else if(qName.equals("bldg:yearOfConstruction")){
			if ((yearOfConstruction != null) && (outSb != null)) {
				yearOfConstruction = checkYearString(outSb.toString());
				if (building != null) {
					building.addTag("start_date", yearOfConstruction);
				}
				yearOfConstruction = null;
			}
			outSb = null;
		}
    	else if(qName.equals("bldg:measuredHeight")){
			if ((measuredHeight != null) && (measuredHeight.isEmpty()) && (outSb != null)) {
				measuredHeight = checkNumberString(outSb.toString());
				if (building != null) {
					building.addTag("height", rounding(1, measuredHeight));
				}
			}
			outSb = null;
		}
    	else if(qName.equals("bldg:storeysAboveGround")){
			if ((storeysAboveGround == 0) && (outSb != null) && (checkNumberString(outSb.toString()) != null)) {
				storeysAboveGround = Integer.parseInt(outSb.toString());
				if ((building != null) && (storeysAboveGround != 0)) {
					building.addTag("building:levels", Integer.toString(storeysAboveGround));
				}
			}
			outSb = null;
		}
    	else if(qName.equals("bldg:storeysBelowGround")){
    		// GMLv4	<bldg:storeysBelowGround>0</bldg:storeysBelowGround>
    		// 			<bldg:storeysBelowGround>9999</bldg:storeysBelowGround>
			if ((storeysBelowGround == 0) && (outSb != null) && (checkNumberString(outSb.toString()) != null)) {
				storeysBelowGround = Integer.parseInt(outSb.toString());
				if ((building != null) && (storeysBelowGround != 0)) {
					building.addTag("building:levels:underground", Integer.toString(storeysBelowGround));
				}
			}
			outSb = null;
		}
    	else if(qName.equals("gen:value")){
    		//   <gen:value>13111058003</gen:value>
			if ((buildingId != null) && (buildingId.isEmpty()) && (outSb != null)) {
				buildingId = outSb.toString();
			}
			outSb = null;
		}
    	else if(qName.equals("uro:buildingID")){
    		//   [GMLv4] <uro:buildingID>11230-bldg-28587</uro:buildingID>
    		if ((buildingId != null) && buildingId.isEmpty() && (outSb != null)) {
    			buildingId = outSb.toString();
			}
    		outSb = null;
		}
    	else if(qName.equals("bldg:lod0RoofEdge")){
    		lod0 = true;
    		if (building != null) {
    			if (roof != null) {
    				for (MemberBean mem : roof.members) {
    					building.members.add(mem);
    				}
    				building.copyTag(roof);
    				roof = null;
    			}
    		}
		}
    	else if(qName.equals("bldg:lod0FootPrint")){
    		lod0 = true;
    		if (building != null) {
    			if (footPrint != null) {
    				for (MemberBean mem : footPrint.members) {
    					building.members.add(mem);
    				}
    				building.copyTag(footPrint);
    				footPrint = null;
    			}
    		}
		}
    	else if(qName.equals("bldg:lod1Solid")){
    		if (solids != null) {
				String maxheight = "-9999.9";
				String minheight = "99999.9";
				for (ElementWay way : solids) {
					String elestr = checkNumberString(way.getTagValue("ele"));
					String histr = checkNumberString(way.getTagValue("maxele"));
					if (elestr != null) {
						double ele = Double.parseDouble(elestr);
						double min = Double.parseDouble(minheight);
						if (ele < min) {
							minheight = elestr;
						}
						
						if (histr != null) {
							double hi = Double.parseDouble(histr);
							double max = Double.parseDouble(maxheight);
							if (hi > max) {
								maxheight = histr;
							}
						}
					}
				}
				double min = Double.parseDouble(minheight);
				double max = Double.parseDouble(maxheight);
				if (min < 90000d) {
					building.addTag("ele", rounding(1, minheight));
				}
				if (max > -9000d) {
					if ((max - min) > 1d) {
						building.addTag("maxele", Double.toString(max));
					}
				}
				solids = null;
				
				if (!lod0) {
		    		if (building != null) {
						for (MemberBean mem : nonLod0.members) {
							building.members.add(mem);
						}
						building.copyTag(nonLod0);
			    		nonLod0 = null;
		    		}
					lod0 = false;
				}
    		}
		}
		else if(qName.equals("gml:Polygon")){
			if ((multipolygon != null) && !multipolygon.members.isEmpty()) {
				if (roof != null) {
					osm.relationMap.put(multipolygon);
					roof.addMember(multipolygon, "part");
				}
				else if (footPrint != null) {
					osm.relationMap.put(multipolygon);
					footPrint.addMember(multipolygon, "part");
				}
				else {
					osm.relationMap.put(multipolygon);
					if (nonLod0 == null) {
						nonLod0 = new RelationBuilding(osm.getNewId());
					}
					nonLod0.addMember(multipolygon, "part");
				}
				multipolygon = null;
			}
		}
		else if (qName.equals("gml:exterior")){
			if (elementWay != null) {
				if (member != null) {
					elementWay.addTag("ref:MLIT_PLATEAU", buildingId);
					if (!edgeFull) {
						if (roof != null) {
							if ((name != null) && !name.isEmpty()) {
								elementWay.addTag("name", name);
							}
							ElementWay part = elementWay.copy(osm.getNewId());
							osm.getWayMap().put(part);
							roof.copyTag(part);
							roof.addMember(part, "outline");
							edgeFull = true;
						}
						else if (footPrint != null) {
							if ((name != null) && !name.isEmpty()) {
								elementWay.addTag("name", name);
							}
							ElementWay part = elementWay.copy(osm.getNewId());
							osm.getWayMap().put(part);
							footPrint.copyTag(part);
							footPrint.addMember(part, "outline");
							edgeFull = true;
						}
						else {
							// Issue #137
							if (!elementWay.existSamePositionWay(solids)) {
								if ((name != null) && !name.isEmpty()) {
									elementWay.addTag("name", name);
								}
								ElementWay part = elementWay.copy(elementWay.getId());
								osm.getWayMap().put(part);
								
								if (nonLod0 == null) {
									nonLod0 = new RelationBuilding(osm.getNewId());
								}
								nonLod0.copyTag(part);
								nonLod0.addMember(part, "outline");
							}
							else {
								osm.removeWay(elementWay);
								elementWay = null;
							}
						}
					}
					if ((multipolygon != null) && (elementWay != null)) {
						ElementWay outer = elementWay.copy(osm.getNewId());
						multipolygon.copyTag(outer);
						outer.removeTag("name");
						outer.removeTag("height");
						outer.removeTag("maxele");
						outer.removeTag("ele");
						outer.removeTag("ref:MLIT_PLATEAU");
						osm.getWayMap().put(outer);
						multipolygon.addMember(outer, "outer");
					}
					member = null;
				}
				if ((solids != null) && (elementWay != null)) {
					solids.add(elementWay.clone());
				}
				elementWay = null;
			}
		}
		else if (qName.equals("gml:interior")){
			if (elementWay != null) {
				if (member != null) {
					if (multipolygon != null) {
						elementWay.removeTag("height");
						elementWay.removeTag("maxele");
						elementWay.removeTag("ele");
						elementWay.removeTag("ref:MLIT_PLATEAU");
						osm.getWayMap().put(elementWay);
						multipolygon.addMember(elementWay, "inner");
					}
					member = null;
				}
				elementWay = null;
			}
		}
		else if(qName.equals("gml:LinearRing")){
			// <gml:LinearRing>
			// <gml:posList>35.541657275471835 139.7156383865409 14.072000000000001 35.542252321638614 139.71535363948732 14.072000000000001 35.54210367440277 139.7148860223014 14.072000000000001 35.54206164434519 139.71490626649856 14.072000000000001 35.5420440155531 139.7148536858433 14.072000000000001 35.541981356256336 139.7146575788015 14.072000000000001 35.54142914946131 139.71491844541285 14.072000000000001 35.54153100551663 139.71523889596378 14.072000000000001 35.541657275471835 139.7156383865409 14.072000000000001</gml:posList>
			// <gml:LinearRing>
			// AREAに変更する
			if (elementWay != null) {
				if (isOverlapped(elementWay.getNdList())) {
					elementWay = null;
				}
				else {
					elementWay.toArea(this.osm.getIndexMap());
				}
			}
		}
		else if(qName.equals("gml:posList")){
			// <gml:posList>35.541657275471835 139.7156383865409 14.072000000000001 35.542252321638614 139.71535363948732 14.072000000000001 35.54210367440277 139.7148860223014 14.072000000000001 35.54206164434519 139.71490626649856 14.072000000000001 35.5420440155531 139.7148536858433 14.072000000000001 35.541981356256336 139.7146575788015 14.072000000000001 35.54142914946131 139.71491844541285 14.072000000000001 35.54153100551663 139.71523889596378 14.072000000000001 35.541657275471835 139.7156383865409 14.072000000000001</gml:posList>
			if (outSb != null) {
				String height = null;
				String maxele = "-9999.9";
				String minele = "99999.9";
				if (elementWay != null) {
					String ele = checkNumberString(elementWay.getTagValue("ele"));
					String hi = checkNumberString(elementWay.getTagValue("maxele"));
					if (ele != null) {
						minele = ele;
					}
					if (hi != null) {
						maxele = hi;
					}
				}
				
				StringTokenizer st = new StringTokenizer(outSb.toString(), " ");
				while(true) {
					NodeBean node;
					// lat
					if (st.hasMoreTokens()) {
						node = new NodeBean(osm.getNewId());
						String lat = st.nextToken();
						node.getPoint().setLat(lat);
					}
					else {
						break;
					}
					
					// lon
					if (st.hasMoreTokens()) {
						node.getPoint().setLon(st.nextToken());
					}
					else {
						break;
					}
					
					// height
					if (st.hasMoreTokens()) {
						height = st.nextToken();
						if (Double.parseDouble(height) > Double.parseDouble(maxele)) {
							maxele = CityModelParser.rounding(2, height);
						}
						if (Double.parseDouble(height) < Double.parseDouble(minele)) {
							minele = CityModelParser.rounding(2, height);
						}
						if (elementWay != null) {
							elementWay.addNode(putNode(node.clone()));
						}
					}
					else {
						break;
					}
				}
				if (elementWay != null) {
					String minStr = checkNumberString(minele);
					if (minStr != null) {
						double min = Double.parseDouble(minele);
						if (min < 90000.0d) {
							elementWay.addTag("ele", rounding(1, minele));
						}
					}
					String maxeleStr = checkNumberString(maxele);
					if (maxeleStr != null) {
						double max = Double.parseDouble(maxele);
						if (max > -1000.0d) {
							elementWay.addTag("maxele", Double.toString(max));
						}
					}
				}
			}
			outSb = null;
		}
    }
    
    /**
     * 建物ID　"13111-bldg-60802"
     * @param poi
     * @return
     */
    String getBuildingId() {
    	return buildingId;
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
     * 「ウェイが同じ区間を二度含んでいる」に該当するかどうかをチェックする
     * 「自身で交差するウェイ」
     * 「ウェイが閉じていない」
     * @return  重なっていればTrue
     */
    boolean isOverlapped(List<NdBean> nds) {
    	if (nds.size() <= 2) {
    		return true;
    	}
    	NdBean[] array = nds.toArray(new NdBean[nds.size()]);
    	if (!array[0].isSamePosition(array[nds.size()-1])) {
    		return true;	// 「ウェイが閉じていない」
    	}
    	for (int i = 1; i < nds.size(); i++) {
        	for (int j = (i+1); j < nds.size(); j++) {
        		if (array[i].isSamePosition(array[j])) {
        			return true;	// 「自身で交差するウェイ」「ウェイが同じ区間を二度含んでいる(1514)」
        		}
        	}
    	}
    	return false;
    }
    
    /**
     * 同位置NODEの二重登録を防ぐ
     * @param node
     * @return
     */
    NodeBean putNode(NodeBean node) {
    	if (this.nodes == null) {
    		this.nodes = new HashMap<>();
    	}
    	NodeBean pre = this.nodes.get(node.getPoint().getGeom());
    	if (pre != null) {
    		return pre;
    	}
    	this.nodes.put(node.getPoint().getGeom(), node);
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
    
    public static String checkYearString(String numberStr) {
    	try {
    		Integer i = Integer.valueOf(numberStr);
    		if (i.intValue() >= 2100) {
    			return null;
    		}
    		else if (i.intValue() <= 1800) {
    			return null;
    		}
    		return trim0(i.toString());
    	}
    	catch(Exception e) {
    		return null;
    	}
    }
    
    public static String checkNumberString(String numberStr) {
    	try {
    		BigDecimal bd = new BigDecimal(numberStr);
    		if (bd.compareTo(new BigDecimal(9999.0)) >= 0) {
    			return null;
    		}
    		else if (bd.compareTo(new BigDecimal(-9999.0)) <= 0) {
    			return null;
    		}
    		return trim0(bd.toString());
    	}
    	catch(Exception e) {
    		return null;
    	}
    }
    
    /**
     * 小数点以下２桁の数値に丸めた値を返す
     * @param str
     * @return
     */
    public static String rounding(int scale, String str) {
    	try {
    		BigDecimal bd = new BigDecimal(str).setScale(scale, RoundingMode.HALF_UP);
    		return trim0(bd.toString());
    	}
    	catch(Exception e) {
    		return null;
    	}
    }
    public static String trim0(String src) {
    	String ret = src;
		if (ret.endsWith(".")) {
			ret = ret.substring(0, ret.length() - 1);
		}
    	BigDecimal d = new BigDecimal(ret);
    	if (d.scale() > 0) {
    		if (src.endsWith("0")) {
    			ret = ret.substring(0, ret.length() - 1);
    			return trim0(ret);
    		}
    	}
    	return ret;
    }
}
