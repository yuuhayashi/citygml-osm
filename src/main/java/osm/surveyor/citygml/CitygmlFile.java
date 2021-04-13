package osm.surveyor.citygml;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.function.Consumer;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.OsmMargeWay;

@SuppressWarnings("serial")
public class CitygmlFile extends File {
	CityModelParser gml;
    OsmDom osm;
    public static long idno = 0;
    
	public static void main(String[] args) {
        try {
			File dir = new File(".");
			Files.list(dir.toPath()).forEach(new Consumer<Path>() {
				@Override
				public void accept(Path a) {
					if (Files.isRegularFile(a)) {
						File file = a.toFile();
						String filename = file.getName();
						System.out.println(filename);
						if (filename.endsWith("_op2.gml")) {
							try {
								filename = filename.substring(0, filename.length()-4);
						        
						        OsmDom osm = new OsmDom();
					            CitygmlFile target = new CitygmlFile(file, osm);
					            target.parse();
					            
						    	osm.export(new File(filename + ".0.osm"));		// [DEBUG]
					            
					            // 各WAYのノードで、他のWAYと共有されたノードを探す
					            OsmMargeWay.relationMarge(osm.relations, osm.ways);
					            
						    	osm.export(new File(filename + ".1.osm"));	// [DEBUG]
					            
					            // メンバーが一つしかないRelation:building を削除する
					            OsmMargeWay.relationGabegi(osm.relations, osm.ways);
					            
						    	osm.export(new File(filename + ".2.osm"));	// [DEBUG]

						    	// Relation->member:role=port のoutlineを作成する
					            OsmMargeWay.relationOutline(osm.relations);
					            
					            // `*.osm`に書き出す
						    	File osmfile = new File(filename + ".osm");
						    	osm.export(osmfile);
							} catch (ParserConfigurationException e) {
								e.printStackTrace();
							} catch (SAXException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							} catch (ParseException e) {
								e.printStackTrace();
							}
						}
					}
				}
			});
            
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public CitygmlFile(File file, OsmDom osm) throws ParserConfigurationException, SAXException, IOException, ParseException {
        super(file.getParentFile(), file.getName());
        this.osm = osm;
        this.gml = new CityModelParser(osm);
    }
    
    public static long getId() {
    	return --CitygmlFile.idno;
    }
    
    /**
     * XMLパースを実行する
     * 
     */
    public void parse() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(false);
        
        SAXParser parser = factory.newSAXParser();
        try {
			parser.parse(this, gml);
		} catch (SAXParseException e) {}
    }
}
