package osm.surveyor.citygml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
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

	CityObjectMember member = null;
	File sourcefile;
	GmlParser gmlparse;
	
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
	public static ArrayList<ElementWay> roofEdges = null;
    
    /**
     * 要素の開始タグ読み込み時に毎回呼ばれる
     */
    public void startElement(String uri,String localName, String qName, Attributes atts) {
    	if (qName.startsWith("gml:")) {
    		gmlparse.startElement(uri, localName, qName, atts);
    	}
		else if(qName.equals("core:CityModel")){
		}
		else if(qName.equals("core:cityObjectMember")){
		}
		else if(qName.equals("bldg:Building")){
			building = new ElementBuilding(CitygmlFile.getId());
			
			for (int i = 0; i < atts.getLength(); i++) {
				String aname = atts.getQName(i);
				if (aname.equals("gml:id")) {
					building.addTag("source:ref:id", atts.getValue(i));
				}
			}
		}
		
		else if(qName.equals("bldg:lod0RoofEdge")){
			roofEdges = new ArrayList<>();
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

		else if(qName.equals("core:CityModel")){
        }
		else if(qName.equals("core:cityObjectMember")){
		}
		else if(qName.equals("bldg:Building")){
			if (building != null) {
				building.printout(osmfile);
			}
			building = null;
		}
		else if(qName.equals("bldg:lod0RoofEdge")){
			if (roofEdges != null) {
				for (ElementWay way : roofEdges) {
					way.addTag("building:part", "yes");
					way.printout(osmfile);
					building.addMember(way, "part");
				}
				roofEdges = null;
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
    	else if (outSb != null) {
    		outSb.append(new String(ch, offset, length));
    	}
    	else {
    	}
    }
}
