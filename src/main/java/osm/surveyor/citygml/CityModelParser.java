package osm.surveyor.citygml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import osm.surveyor.gml.CoreParser;
import osm.surveyor.gml.GmlParser;
import osm.surveyor.osm.ElementBounds;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmFile;

/**
 * CityGMLファイルをパースする
 * @param gmlFile
 * @code{
 * <core:CityModel>
 *   <core:cityObjectMember/>
 *     <bldg:Building gml:id="BLD_fc50c7d9-76ac-4576-bfbd-f37c74410928">
 *       <bldg:lod0RoofEdge>
 *       </bldg:lod0RoofEdge>
 *     </bldg:Building>
 *   <core:cityObjectMember/>
 * </core:CityModel>
 * }
 * 
 */
public class CityModelParser extends DefaultHandler {
	OsmFile osmfile;
	public StringBuffer outSb = null;		// TEXT待ちじゃない場合は NULL

	File sourcefile;
	GmlParser gmlparse;
	CoreParser xalparse;
	
    public CityModelParser(File sourcefile) {
        super();
        this.sourcefile = sourcefile;
    }
    
	/**
     * ドキュメント開始
	 * @throws SAXException 
     */
    public void startDocument() throws SAXException {
        String name = sourcefile.getName();
        name = name.substring(0, name.length()-4);
        osmfile = new OsmFile(name + ".osm");
        try {
			osmfile.open();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		gmlparse = new GmlParser(osmfile);
		gmlparse.startDocument();
		xalparse = new CoreParser();
    }
 
    /**
     * ドキュメント終了
     * @throws SAXException 
     */
    public void endDocument() throws SAXException {
    	gmlparse.endDocument();
    	osmfile.close();
    }
    
    ElementBounds bounds = null;
	ElementBuilding building = null;

	/*
     * List<roofEdge>
     */
	ArrayList<ElementWay> roofEdges = null;
    
    /**
     * 要素の開始タグ読み込み時に毎回呼ばれる
     */
    public void startElement(String uri,String localName, String qName, Attributes atts) {
    	if (qName.startsWith("gml:")) {
    		gmlparse.startElement(uri, localName, qName, atts);
    	}
    	else if (qName.startsWith("xAL:")) {
    		xalparse.startElement(uri, localName, qName, atts);
    	}
		else if(qName.equals("core:CityModel")){
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
    	if (qName.startsWith("gml:")) {
    		gmlparse.endElement(uri, localName, qName);
    	}
    	else if (qName.startsWith("xAL:")) {
    		xalparse.endElement(uri, localName, qName);
    	}

		else if(qName.equals("core:CityModel")){
        }
		else if(qName.equals("core:cityObjectMember")){
			if (building != null) {
				building.printout(osmfile);
			}
			building = null;
		}
		else if(qName.equals("bldg:Building")){
			if (building.source_ref != null) {
				building.addTag("source:ref:id", building.source_ref);
			}
			if (roofEdges != null) {
				for (ElementWay way : this.roofEdges) {
					way.printout(osmfile);
					building.addMember(way, "part");
				}
		    	this.roofEdges = null;
			}
			if (xalparse.localityName != null) {
				building.addTag("addr:full", xalparse.localityName);
				xalparse.localityName = null;
			}
		}
		else if(qName.equals("bldg:lod0RoofEdge")){
			if (gmlparse.isWay()) {
				ElementWay roofEdge = gmlparse.getWay();
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
    	if (gmlparse.outSb != null) {
    		gmlparse.characters(ch, offset, length);
    	}
    	if (xalparse.outSb != null) {
    		xalparse.characters(ch, offset, length);
    	}
    	else if (outSb != null) {
    		outSb.append(new String(ch, offset, length));
    	}
    	else {
    	}
    }
}
