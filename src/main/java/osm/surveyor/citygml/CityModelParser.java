package osm.surveyor.citygml;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import lombok.Getter;
import osm.surveyor.osm.BoundsBean;
import osm.surveyor.osm.MemberBean;
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
	String buildingId = null;						// <bldg:Building gml:id="buildingId" />
	String name = null;								// <gml:name/>
	TagBean usage = null;						// <bldg:usage/>	用途
	String yearOfConstruction = null;				// <bldg:yearOfConstruction/>	建築年
	String measuredHeight = null;					// <bldg:measuredHeight/>
	int storeysAboveGround = 0;						// <bldg:storeysAboveGround/>
	int storeysBelowGround = 0;						// <bldg:storeysBelowGround/>
	boolean edgeFull = false;						// 建物形状がセットされたら true
	RelationBuilding roof = null;					// <bldg:lod0RoofEdge/>
	RelationBuilding footPrint = null;				// <bldg:lod0FootPrint/>
	ArrayList<ElementWay> solids = null;			// <bldg:lod1Solid/>
	RelationMultipolygon multipolygon = null;		// <gml:Polygon/>
	MemberBean member = null;					// <gml:exterior/>,<gml:interior/>
    ElementWay way = null;							// <gml:LinearRing/>
	
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
				NodeBean node = nodes.get(key);
				osm.nodes.put(node);
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
				if (usage == null) {
					usage = new TagBean("building", "yes");
				}
				String ele = building.getTagValue("ele");
				String maxele = building.getTagValue("maxele");
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
						ElementWay way = osm.ways.get(mem.getRef());
						way.removeTag("maxele");
						way.addTag("height", rounding(2, building.getTagValue("height")));
						way.addTag("ele", rounding(1, building.getTagValue("ele")));
						way.addTag("addr:full", building.getTagValue("addr:full"));
						if ((name != null) && !name.isEmpty()) {
							way.addTag("name", name);
						}
						way.addTag("start_date", building.getTagValue("start_date"));
						way.addTag("building:part", usage.v);
						way.addTag("building:levels", building.getTagValue("building:levels"));
						way.addTag("building:levels:underground", building.getTagValue("building:levels:underground"));
						way.addTag("ref:MLIT_PLATEAU", buildingId);
					}
					else if (mem.getType().equals("relation")) {
						ElementRelation relation = osm.relations.get(mem.getRef());
						relation.removeTag("maxele");
						// Issue #39 relation.addTag("start_date", building.getTagValue("start_date"));
						relation.addTag("building:levels", building.getTagValue("building:levels"));
						relation.addTag("building:levels:underground", building.getTagValue("building:levels:underground"));
						relation.addTag("height", rounding(2, building.getTagValue("height")));
						relation.addTag("ele", rounding(1, building.getTagValue("ele")));
						relation.addTag("addr:full", building.getTagValue("addr:full"));
						if ((name != null) && !name.isEmpty()) {
							relation.addTag("name", name);
						}
						relation.addTag(usage);
					}
				}
				building.removeTag("ref:MLIT_PLATEAU");
				building.addTag(usage);
				osm.relations.put(building);
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
			if ((surveyYear != null) && (surveyYear.isEmpty()) && (outSb != null)) {
				surveyYear = outSb.toString();
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
				yearOfConstruction = outSb.toString();
				if (building != null) {
					building.addTag("start_date", yearOfConstruction);
				}
				yearOfConstruction = null;
			}
			outSb = null;
		}
    	else if(qName.equals("bldg:measuredHeight")){
			if ((measuredHeight != null) && (measuredHeight.isEmpty()) && (outSb != null)) {
				measuredHeight = outSb.toString();
				if (building != null) {
					building.addTag("height", rounding(1, measuredHeight));
				}
			}
			outSb = null;
		}
    	else if(qName.equals("bldg:storeysAboveGround")){
			if ((storeysAboveGround == 0) && (outSb != null)) {
				storeysAboveGround = Integer.parseInt(outSb.toString());
				if (building != null) {
					building.addTag("building:levels", Integer.toString(storeysAboveGround));
				}
			}
			outSb = null;
		}
    	else if(qName.equals("bldg:storeysBelowGround")){
			if ((storeysBelowGround == 0) && (outSb != null)) {
				storeysBelowGround = Integer.parseInt(outSb.toString());
				if (building != null) {
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
		
    	else if(qName.equals("bldg:lod0RoofEdge")){
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
					String elestr = way.getTagValue("ele");
					String histr = way.getTagValue("maxele");
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
			if (way != null) {
				if (member != null) {
					way.addTag("ref:MLIT_PLATEAU", buildingId);
					if (!edgeFull) {
						if (roof != null) {
							if ((name != null) && !name.isEmpty()) {
								way.addTag("name", name);
							}
							ElementWay part = way.copy(osm.getNewId());
							osm.ways.put(part);
							roof.copyTag(part);
							roof.addMember(part, "part");
							edgeFull = true;
						}
						else if (footPrint != null) {
							if ((name != null) && !name.isEmpty()) {
								way.addTag("name", name);
							}
							ElementWay part = way.copy(osm.getNewId());
							osm.ways.put(part);
							footPrint.copyTag(part);
							footPrint.addMember(part, "part");
							edgeFull = true;
						}
					}
					if (multipolygon != null) {
						ElementWay outer = way.copy(osm.getNewId());
						multipolygon.copyTag(outer);
						multipolygon.removeTag("ref:MLIT_PLATEAU");
						outer.removeTag("name");
						outer.removeTag("height");
						outer.removeTag("maxele");
						outer.removeTag("ele");
						osm.ways.put(outer);
						multipolygon.addMember(outer, "outer");
					}
					member = null;
				}
				if (solids != null) {
					solids.add(way.clone());
				}
				way = null;
			}
		}
		else if (qName.equals("gml:interior")){
			if (way != null) {
				if (member != null) {
					if (multipolygon != null) {
						way.removeTag("height");
						way.removeTag("maxele");
						way.removeTag("ele");
						way.addTag("ref:MLIT_PLATEAU", buildingId);
						osm.ways.put(way);
						multipolygon.addMember(way, "inner");
					}
					member = null;
				}
				way = null;
			}
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
				String maxele = "-9999.9";
				String minele = "99999.9";
				if (way != null) {
					String ele = way.getTagValue("ele");
					String hi = way.getTagValue("maxele");
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
						node.getPoint().lon = st.nextToken();
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
						if (way != null) {
							way.addNode(putNode(node.clone()));
						}
					}
					else {
						break;
					}
				}
				if (way != null) {
					double min = Double.parseDouble(minele);
					double max = Double.parseDouble(maxele);
					if (min < 90000.0d) {
						way.addTag("ele", rounding(1, minele));
					}
					if (max > -1000.0d) {
						way.addTag("maxele", Double.toString(max));
					}
				}
			}
			outSb = null;
		}
		

		else if(qName.equals("xAL:LocalityName")) {
			// <xAL:LocalityName>東京都大田区南六郷三丁目</xAL:LocalityName>
			if (outSb != null) {
				if (building != null) {
					// building.addTag("addr:full", outSb.toString());
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
