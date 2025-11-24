package osm.surveyor.citygml;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Paths;
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
import osm.surveyor.osm.NodeBeans;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.TagBean;
import osm.surveyor.osm.boxcel.IndexMap;
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
	boolean solidWay = false;
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
		if(qName.equals("gml:boundedBy")){
			this.bounds = startGmlBoundedBy();
		}
		else if(qName.equals("gml:Envelope")){
			startGmlEnvelope(atts, this.osm);
		}
		else if(qName.equals("gml:lowerCorner")){
			startGmlLowerCorner();
		}
		else if(qName.equals("gml:upperCorner")){
			startGmlUpperCorner();
		}
		else if(qName.equals("core:CityModel")){
			startCoreCityModel();
		}
		else if(qName.equals("uro:surveyYear")){
			startUroSurveyYear(this.surveyYear);
		}
		else if(qName.equals("bldg:Building")){
			startBldgBuilding(this.building, atts, this.osm);
		}
    	else if(qName.equals("gen:stringAttribute")){
    		startGenStringAttribute(atts, this.buildingId);
		}
    	else if(qName.equals("gen:value")){
    		startGenValue(this.buildingId);
		}
    	else if(qName.equals("uro:buildingID")){
    		startUroBuildingID(this.buildingId);
		}
		else if(qName.equals("gml:name")){
			startGmlName(this.name);
		}
		else if(qName.equals("bldg:usage")){
			startBldgUsage(this.usage);
		}
		else if(qName.equals("bldg:yearOfConstruction")){
			startBldgYearOfConstruction(this.yearOfConstruction);
		}
		else if(qName.equals("bldg:measuredHeight")){
			startBldgMeasuredHeight(this.measuredHeight);
		}
		else if(qName.equals("bldg:storeysAboveGround")){
			this.storeysAboveGround = startBldgStoreysAboveGround();
		}
		else if(qName.equals("bldg:storeysBelowGround")){
			this.storeysBelowGround = startBldgStoreysBelowGround();
		}
		else if(qName.equals("bldg:lod0RoofEdge")){
			startBldgLod0RoofEdge(this.roof, this.osm);
		}
		else if(qName.equals("bldg:lod0FootPrint")){
			startBldgLod0FootPrint(this.footPrint, this.osm);
		}
		else if(qName.equals("bldg:lod1Solid")){
			startBldgLod1Solid(this.solids);
		}
		else if(qName.equals("gml:Polygon")){
			startGmlPolygon(this.multipolygon, this.osm);
		}
		else if(qName.equals("gml:exterior")){
			startGmlExterior(this.multipolygon, this.member);
		}
		else if(qName.equals("gml:interior")){
			startGmlInterior(this.multipolygon, this.member);
		}
		else if(qName.equals("gml:LinearRing")){
			startGmlLinearRing(this.elementWay, this.osm);
		}
		else if(qName.equals("gml:posList")){
			startGmlPosList();
		}
	}

    /**
     * 要素の終了タグ読み込み時に毎回呼ばれる
     */
    public void endElement(String uri, String localName, String qName) {
		if(qName.equals("gml:boundedBy")){
			endGmlBoundedBy(this.osm, this.bounds);
		}
		else if(qName.equals("gml:lowerCorner")){
			endGmlLowerCorner(this.bounds);
		}
		else if(qName.equals("gml:upperCorner")){
			endGmlUpperCorner(this.bounds);
		}
		else if(qName.equals("core:CityModel")){
			endCoreCityModel(this.osm.nodes);
			System.out.println();
		}
		else if(qName.equals("bldg:Building")){
			endBldgBuilding(this.building, this.usage, this.buildingId, this.name);
		}
    	else if(qName.equals("gml:name")){
    		endGmlName(this.name);
		}		
    	else if(qName.equals("uro:surveyYear")){
    		endUroSurveyYear(this.building, this.surveyYear);
		}
    	else if(qName.equals("bldg:usage")){
    		endBldgUsage(this.building, this.usage);
		}
    	else if(qName.equals("bldg:yearOfConstruction")){
    		endBldgYearOfConstruction(this.building, this.yearOfConstruction);
		}
    	else if(qName.equals("bldg:measuredHeight")){
    		endBldgMeasuredHeight(this.building, this.measuredHeight);
		}
    	else if(qName.equals("bldg:storeysAboveGround")){
    		this.storeysAboveGround = endBldgStoreysAboveGround(this.building, this.storeysAboveGround);
		}
    	else if(qName.equals("bldg:storeysBelowGround")){
    		this.storeysBelowGround = bldgStoreysBelowGround(this.building, this.storeysBelowGround);
		}
    	else if(qName.equals("gen:value")){
    		endGenValue(this.buildingId);
		}
    	else if(qName.equals("uro:buildingID")){
    		endUroBuildingID(this.buildingId);
		}
    	else if(qName.equals("bldg:lod0RoofEdge")){
    		endBldgLod0RoofEdge(this.building, this.roof);
		}
    	else if(qName.equals("bldg:lod0FootPrint")){
    		endBldgLod0FootPrint(this.building, this.footPrint);
		}
    	else if(qName.equals("bldg:lod1Solid")){
    		endBldgLod1Solid(this.building, this.nonLod0, this.solids);
		}
		else if(qName.equals("gml:Polygon")){
			endGmlPolygon(this.roof, this.footPrint, this.nonLod0, this.multipolygon, this.osm);
		}
		else if (qName.equals("gml:exterior")){
			endGmlExterior(this.roof, this.footPrint, this.nonLod0, this.elementWay, this.multipolygon, this.solids, 
					this.osm, this.member, this.name);
		}
		else if (qName.equals("gml:interior")){
			endGmlInterior(this.elementWay, this.member, this.multipolygon, this.osm);
		}
		else if(qName.equals("gml:LinearRing")){
			endGmlLinearRing(this.elementWay, this.osm.getIndexMap());
		}
		else if(qName.equals("gml:posList")){
			endGmlPosList(this.elementWay, this.osm);
		}
    }
    
    // <gml:boundedBy>
    private BoundsBean startGmlBoundedBy() {
		return new BoundsBean();
    }
    // </gml:boundedBy>
    private void endGmlBoundedBy(OsmDom osm, BoundsBean bounds) {
    	this.osm.setBounds(bounds);
    }

    // <gml:Envelope>
    private void startGmlEnvelope(Attributes atts, OsmDom osm) {
		// <gml:Envelope srsName="http://www.opengis.net/def/crs/EPSG/0/6697">
    	this.osm.srsName = getAttributes("srsName", atts);
    }

    // <gml:lowerCorner>
    private void startGmlLowerCorner() {
		this.outSb = new StringBuffer();
    }
    // </gml:lowerCorner>
    private void endGmlLowerCorner(BoundsBean bounds) {
		// <gml:lowerCorner>35.53956274455546 139.701140502832 1.627</gml:lowerCorner>
		StringTokenizer st = new StringTokenizer(this.outSb.toString(), " ");
		if (st.hasMoreTokens()) {
			String lat = st.nextToken();
			this.bounds.minlat = lat;
		}
		if (st.hasMoreTokens()) {
			String lon = st.nextToken();
			this.bounds.minlon = lon;
		}
		this.outSb = null;
    }

    // <gml:upperCorner>
    private void startGmlUpperCorner() {
		this.outSb = new StringBuffer();
    }
    // </gml:upperCorner>
    private void endGmlUpperCorner(BoundsBean bounds) {
		// <gml:upperCorner>35.541755325236224 139.71239981225776 43.802</gml:upperCorner>
		StringTokenizer st = new StringTokenizer(this.outSb.toString(), " ");
		if (st.hasMoreTokens()) {
			String lat = st.nextToken();
			this.bounds.maxlat = lat;
		}
		if (st.hasMoreTokens()) {
			String lon = st.nextToken();
			this.bounds.maxlon = lon;
		}
		this.outSb = null;
    }

    // <core:CityModel>
    private void startCoreCityModel() {
		this.nodes = new HashMap<>();
    }
    // </core:CityModel>
    private void endCoreCityModel(NodeBeans domNodes) {
		for (String key : this.nodes.keySet()) {
			NodeBean node = this.nodes.get(key);
			domNodes.put(node);
		}
    }

    // <bldg:Building>
    private void startBldgBuilding(RelationBuilding building, Attributes atts, OsmDom osm) {
    	this.building = new RelationBuilding(osm.getNewId());
    	this.building.addTag("type", "building");
		for (int i = 0; i < atts.getLength(); i++) {
			String aname = atts.getQName(i);
			if (aname.equals("gml:id")) {
				this.buildingId = atts.getValue(i);
			}
		}
    }
    // </bldg:Building>
    private void endBldgBuilding(RelationBuilding building, TagBean usage, String buildingId, String name) {
		if (building != null) {
			if (usage == null) {
				usage = new TagBean("building", "yes");
			}
			String ele = checkNumberString(building.getTagValue("ele"));
			String maxele = checkNumberString(building.getTagValue("maxele"));
			if (maxele != null) {
				this.building.removeTag("maxele");
			}
			if (building.getTagValue("height") == null) {
				if (maxele != null) {
					if (ele != null) {
						this.building.addTag("height", rounding(2, new BigDecimal(maxele).subtract(new BigDecimal(ele)).toString()));
					}
					else {
						this.building.addTag("height", rounding(2, new BigDecimal(maxele).toString()));
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
			this.buildingCount--;
			if (this.buildingCount < 1) {
				this.buildingCount = 10;
				System.out.print(":");
			}
		}
		this.building = null;
		this.usage = null;
		this.buildingId = "";
		this.name = "";
    }
    
    // <gen:stringAttribute>
    private void startGenStringAttribute(Attributes atts, String buildingId) {
		if (getAttributes("name", atts).startsWith(BLDG_ID)) {
			this.buildingId = "";
		}
    }

	// <gen:value>13111058003</gen:value>
    private void startGenValue(String buildingId) {
		if ((buildingId != null) && buildingId.isEmpty()) {
			this.outSb = new StringBuffer();
		}
    }
    // </gen:value>
    private void endGenValue(String buildingId) {
		if ((buildingId != null) && (buildingId.isEmpty()) && (this.outSb != null)) {
			this.buildingId = this.outSb.toString();
		}
		this.outSb = null;
    }
    
    // <bldg:usage>
    private void startBldgUsage(TagBean usage) {
		this.outSb = new StringBuffer();
		this.usage = new TagBean("building", "yes");
    }
    // </bldg:usage>
    private void endBldgUsage(RelationBuilding building, TagBean usage) {
		if ((usage != null) && (this.outSb != null)) {
			String code = this.outSb.toString();
			if (building != null) {
				this.usage.v = this.conversionTable.getUsageBuilding(code);
				this.building.addTag(this.usage);
			}
		}
		this.outSb = null;
    }

    // <gml:name>
    private void startGmlName(String name) {
    	this.outSb = new StringBuffer();
    	this.name = "";
    }
    // </gml:name>
    private void endGmlName(String name) {
		if ((name != null) && (name.isEmpty()) && (this.outSb != null)) {
			this.name = this.outSb.toString();
		}
		this.outSb = null;
    }

    // <bldg:yearOfConstruction>
    private void startBldgYearOfConstruction(String yearOfConstruction) {
    	this.outSb = new StringBuffer();
    	this.yearOfConstruction = "";
    }
    // </bldg:yearOfConstruction>
    private void endBldgYearOfConstruction(RelationBuilding building, String yearOfConstruction) {
		if ((yearOfConstruction != null) && (this.outSb != null)) {
			this.yearOfConstruction = checkYearString(this.outSb.toString());
			if (building != null) {
				this.building.addTag("start_date", yearOfConstruction);
			}
			this.yearOfConstruction = null;
		}
		this.outSb = null;
    }

    // <bldg:measuredHeight>
    private void startBldgMeasuredHeight(String measuredHeight) {
    	this.outSb = new StringBuffer();
    	this.measuredHeight = "";
    }
    // </bldg:measuredHeight>
    private void endBldgMeasuredHeight(RelationBuilding building, String measuredHeight) {
		if ((measuredHeight != null) && (measuredHeight.isEmpty()) && (this.outSb != null)) {
			this.measuredHeight = checkNumberString(this.outSb.toString());
			if (building != null) {
				this.building.addTag("height", rounding(1, this.measuredHeight));
			}
		}
		this.outSb = null;
    }

    // <bldg:storeysAboveGround>
    private int startBldgStoreysAboveGround() {
		this.outSb = new StringBuffer();
		int storeysAboveGround = 0;
		return storeysAboveGround;
    }
    // </bldg:storeysAboveGround>
    private int endBldgStoreysAboveGround(RelationBuilding building, int storeysAboveGround) {
		if ((storeysAboveGround == 0) && (this.outSb != null) && (checkNumberString(this.outSb.toString()) != null)) {
			storeysAboveGround = Integer.parseInt(this.outSb.toString());
			if ((building != null) && (storeysAboveGround != 0)) {
				this.building.addTag("building:levels", Integer.toString(storeysAboveGround));
			}
		}
		this.outSb = null;
		return storeysAboveGround;
    }

    // <bldg:storeysBelowGround>
    private int startBldgStoreysBelowGround() {
    	this.outSb = new StringBuffer();
		int storeysBelowGround = 0;
		return storeysBelowGround;
    }
    // </bldg:storeysBelowGround>
    private int bldgStoreysBelowGround(RelationBuilding building, int storeysBelowGround) {
		// GMLv4	<bldg:storeysBelowGround>0</bldg:storeysBelowGround>
		// 			<bldg:storeysBelowGround>9999</bldg:storeysBelowGround>
		if ((storeysBelowGround == 0) && (this.outSb != null) && (checkNumberString(this.outSb.toString()) != null)) {
			storeysBelowGround = Integer.parseInt(this.outSb.toString());
			if ((building != null) && (storeysBelowGround != 0)) {
				this.building.addTag("building:levels:underground", Integer.toString(storeysBelowGround));
			}
		}
		this.outSb = null;
		return storeysBelowGround;
    }

    // <uro:surveyYear>
    private void startUroSurveyYear(String surveyYear) {
		this.outSb = new StringBuffer();
		this.surveyYear = "";
    }
    // </uro:surveyYear>
    private void endUroSurveyYear(RelationBuilding building, String surveyYear) {
		if ((surveyYear != null) && (this.outSb != null)) {
			this.surveyYear = checkYearString(this.outSb.toString());
			if ((building != null) && (surveyYear != null)) {
				this.building.addTag("survey:date", this.surveyYear);
			}
		}
		this.outSb = null;
    }

    // <uro:buildingID>
    private void startUroBuildingID(String buildingId) {
    	//  [GMLv4] <uro:buildingID>11230-bldg-28587</uro:buildingID>
		this.outSb = new StringBuffer();
		this.buildingId = "";
    }
    // </uro:buildingID>
    private void endUroBuildingID(String buildingId) {
		if ((buildingId != null) && buildingId.isEmpty() && (this.outSb != null)) {
			this.buildingId = this.outSb.toString();
		}
		this.outSb = null;
    }

    // <bldg:lod0RoofEdge>
    private void startBldgLod0RoofEdge(RelationBuilding roof, OsmDom osm) {
    	this.roof = new RelationBuilding(osm.getNewId());
		this.edgeFull = false;
    }
    // </bldg:lod0RoofEdge>
    private void endBldgLod0RoofEdge(RelationBuilding building, RelationBuilding roof) {
		this.lod0 = true;
		if (building != null) {
			if (roof != null) {
				for (MemberBean mem : roof.members) {
					this.building.members.add(mem);
				}
				this.building.copyTag(roof);
				this.roof = null;
			}
		}
    }
    
    // <bldg:lod0FootPrint>
    private void startBldgLod0FootPrint(RelationBuilding footPrint, OsmDom osm) {
    	this.footPrint = new RelationBuilding(osm.getNewId());
		this.edgeFull = false;
    }
    // </bldg:lod0FootPrint>
    private void endBldgLod0FootPrint(RelationBuilding building, RelationBuilding footPrint) {
		this.lod0 = true;
		if (this.building != null) {
			if (footPrint != null) {
				for (MemberBean mem : footPrint.members) {
					this.building.members.add(mem);
				}
				this.building.copyTag(footPrint);
				this.footPrint = null;
			}
		}
    }

    // <bldg:lod1Solid>
    private void startBldgLod1Solid(ArrayList<ElementWay> solids) {
		this.solidWay = false;
		this.solids = new ArrayList<>();
    }
    // </bldg:lod1Solid>
    private void endBldgLod1Solid(RelationBuilding building, RelationBuilding nonLod0, ArrayList<ElementWay> solids) {
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
				this.building.addTag("ele", rounding(1, minheight));
			}
			if (max > -9000d) {
				if ((max - min) > 1d) {
					this.building.addTag("maxele", Double.toString(max));
				}
			}
			this.solids = null;
			
			if (!this.lod0) {
	    		if (building != null) {
					for (MemberBean mem : nonLod0.members) {
						building.members.add(mem);
					}
					building.copyTag(nonLod0);
					this.nonLod0 = null;
	    		}
				this.lod0 = false;
			}
		}
    }
    
    // <gml:Polygon>
    private void startGmlPolygon(RelationMultipolygon multipolygon, OsmDom osm) {
		if (!this.edgeFull) {
			this.multipolygon = new RelationMultipolygon(osm.getNewId());
		}
    }
    // </gml:Polygon>
    private void endGmlPolygon(RelationBuilding roof,  RelationBuilding footPrint, RelationBuilding nonLod0, 
    		RelationMultipolygon multipolygon, OsmDom osm) {
		if ((multipolygon != null) && !multipolygon.members.isEmpty()) {
			if (roof != null) {
				this.osm.relationMap.put(multipolygon);
				this.roof.addMember(multipolygon, "part");
			}
			else if (footPrint != null) {
				this.osm.relationMap.put(multipolygon);
				this.footPrint.addMember(multipolygon, "part");
			}
			else {
				this.osm.relationMap.put(multipolygon);
				if (nonLod0 == null) {
					this.nonLod0 = new RelationBuilding(osm.getNewId());
				}
				this.nonLod0.addMember(multipolygon, "part");
			}
			this.multipolygon = null;
		}
    }
    
    // <gml:exterior>
    private void startGmlExterior(RelationMultipolygon multipolygon, MemberBean member) {
		if (multipolygon != null) {
			this.member = new MemberBean();
			this.member.setRole("outer");
		}
    }
    // </gml:exterior>
    private void endGmlExterior(RelationBuilding roof, RelationBuilding footPrint, RelationBuilding nonLod0, 
    		ElementWay elementWay, RelationMultipolygon multipolygon, ArrayList<ElementWay> solids, OsmDom osm, 
    		MemberBean member, String name) {
		if (elementWay != null) {
			if (member != null) {
				elementWay.addTag("ref:MLIT_PLATEAU", buildingId);
				if (!this.edgeFull) {
					if (roof != null) {
						if ((name != null) && !name.isEmpty()) {
							elementWay.addTag("name", name);
						}
						ElementWay part = elementWay.copy(osm.getNewId());
						this.osm.getWayMap().put(part);
						this.roof.copyTag(part);
						this.roof.addMember(part, "outline");
						this.edgeFull = true;
					}
					else if (footPrint != null) {
						if ((name != null) && !name.isEmpty()) {
							elementWay.addTag("name", name);
						}
						ElementWay part = elementWay.copy(osm.getNewId());
						this.osm.getWayMap().put(part);
						this.footPrint.copyTag(part);
						this.footPrint.addMember(part, "outline");
						this.edgeFull = true;
					}
					else {
						// Issue #137
						if (!elementWay.existSamePositionWay(solids)) {
							if ((name != null) && !name.isEmpty()) {
								elementWay.addTag("name", name);
							}
							ElementWay part = elementWay.copy(elementWay.getId());
							this.osm.getWayMap().put(part);
							
							if (nonLod0 == null) {
								this.nonLod0 = new RelationBuilding(osm.getNewId());
							}
							this.nonLod0.copyTag(part);
							this.nonLod0.addMember(part, "outline");
						}
						else {
							this.osm.removeWay(elementWay);
							this.elementWay = null;
						}
					}
				}
				if ((multipolygon != null) && (elementWay != null)) {
					ElementWay outer = elementWay.copy(osm.getNewId());
					this.multipolygon.copyTag(outer);
					outer.removeTag("name");
					outer.removeTag("height");
					outer.removeTag("maxele");
					outer.removeTag("ele");
					outer.removeTag("ref:MLIT_PLATEAU");
					this.osm.getWayMap().put(outer);
					this.multipolygon.addMember(outer, "outer");
				}
				this.member = null;
			}
			if ((solids != null) && (elementWay != null)) {
				this.solids.add(elementWay.clone());
			}
			this.elementWay = null;
		}
    }

    // <gml:interior>
    private void startGmlInterior(RelationMultipolygon multipolygon, MemberBean member) {
		if (multipolygon != null) {
			this.member = new MemberBean();
			this.member.setRole("inner");
		}
    }
    // </gml:interior>
    private void endGmlInterior(ElementWay elementWay, MemberBean member, RelationMultipolygon multipolygon, OsmDom osm) {
		if (elementWay != null) {
			if (member != null) {
				if (multipolygon != null) {
					this.elementWay.removeTag("height");
					this.elementWay.removeTag("maxele");
					this.elementWay.removeTag("ele");
					this.elementWay.removeTag("ref:MLIT_PLATEAU");
					this.osm.getWayMap().put(elementWay);
					this.multipolygon.addMember(elementWay, "inner");
				}
				this.member = null;
			}
			this.elementWay = null;
		}
    }
    
    // <gml:LinearRing>
    private void startGmlLinearRing(ElementWay elementWay, OsmDom osm) {
		this.elementWay = new ElementWay(osm.getNewId());
    }
    // </gml:LinearRing>
    private void endGmlLinearRing(ElementWay elementWay, IndexMap idxmap) {
		// <gml:LinearRing>
		// <gml:posList>35.541657275471835 139.7156383865409 14.072000000000001 35.542252321638614 139.71535363948732 14.072000000000001 35.54210367440277 139.7148860223014 14.072000000000001 35.54206164434519 139.71490626649856 14.072000000000001 35.5420440155531 139.7148536858433 14.072000000000001 35.541981356256336 139.7146575788015 14.072000000000001 35.54142914946131 139.71491844541285 14.072000000000001 35.54153100551663 139.71523889596378 14.072000000000001 35.541657275471835 139.7156383865409 14.072000000000001</gml:posList>
		// <gml:LinearRing>
		// AREAに変更する
		if (elementWay != null) {
			if (isOverlapped(elementWay.getNdList())) {
				this.elementWay = null;
			}
			else {
				this.elementWay.toArea(idxmap);
			}
		}
    }
    
    // <gml:posList>
    private void startGmlPosList() {
		this.outSb = new StringBuffer();
    }
    private void endGmlPosList(ElementWay elementWay, OsmDom osm) {
		// <gml:posList>35.541657275471835 139.7156383865409 14.072000000000001 35.542252321638614 139.71535363948732 14.072000000000001 35.54210367440277 139.7148860223014 14.072000000000001 35.54206164434519 139.71490626649856 14.072000000000001 35.5420440155531 139.7148536858433 14.072000000000001 35.541981356256336 139.7146575788015 14.072000000000001 35.54142914946131 139.71491844541285 14.072000000000001 35.54153100551663 139.71523889596378 14.072000000000001 35.541657275471835 139.7156383865409 14.072000000000001</gml:posList>
		if (this.outSb != null) {
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
			
			StringTokenizer st = new StringTokenizer(this.outSb.toString(), " ");
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
						this.elementWay.addNode(putNode(node.clone()));
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
						this.elementWay.addTag("ele", rounding(1, minele));
					}
				}
				String maxeleStr = checkNumberString(maxele);
				if (maxeleStr != null) {
					double max = Double.parseDouble(maxele);
					if (max > -1000.0d) {
						this.elementWay.addTag("maxele", Double.toString(max));
					}
				}
			}
		}
		this.outSb = null;
    }

    //----------------------------------------------------
    
    /**
     * 建物ID　"13111-bldg-60802"
     * @param poi
     * @return
     */
    String getBuildingId() {
    	return this.buildingId;
    }

    /**
     * テキストデータ読み込み時に毎回呼ばれる
     */
    public void characters(char[] ch, int offset, int length) {
    	if (this.outSb != null) {
    		this.outSb.append(new String(ch, offset, length));
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
