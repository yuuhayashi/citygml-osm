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
import osm.surveyor.osm.marge.BuildingGarbage;
import osm.surveyor.osm.marge.OutlineFactory;
import osm.surveyor.osm.marge.RelationMarge;

@SuppressWarnings("serial")
public class CitygmlFile extends File {
	CityModelParser gml;
    OsmDom osm;
    
	public static void main(String[] args) throws Exception {
		File dir = new File(".");
		Files.list(dir.toPath()).forEach(new Consumer<Path>() {
			@Override
			public void accept(Path a) {
				CitygmlFile.doConvert(a);
			}
		});
	}
	
    public CitygmlFile(File file, OsmDom osm) throws ParserConfigurationException, SAXException, IOException, ParseException {
        super(file.getParentFile(), file.getName());
        this.osm = osm;
        this.gml = new CityModelParser(osm);
    }
    
    /**
     * メイン処理
     * 指定されたGMLファイルをOSMファイルに変換する
     * @param a
     */
    public static void doConvert(Path a) {
		if (Files.isRegularFile(a)) {
			File file = a.toFile();
			String filename = file.getName();
			System.out.println(filename);
			if (filename.endsWith(".gml")) {
				try {
					filename = filename.substring(0, filename.length()-4);
			        
			        OsmDom osm = new OsmDom();
			        
			        // (1) GMLファイルをパースする
		            CitygmlFile target = new CitygmlFile(file, osm);
		            target.parse();
		            
		            // (2) 各WAYのノードで、他のWAYと共有されたノードを探す
			    	// 接触しているBUILDINGのWAYをくっつけて"Relation:building"をつくる
			    	// Relation:multipolygon の MaxHeightを outline->Multipolygonへ設定する
			    	(new RelationMarge(osm)).relationMarge();
		            
					// (3) メンバーが一つしかないRelation:building を削除する
				    	// (3) メンバーが一つしかないRelation:multipolygon と polygon:member を削除する
			    	(new BuildingGarbage(osm)).garbage();
		            
		            // (4) Relation:building->member:role=port のWay:outlineを作成する
		            // (4) Relation:multipolygon->outerにWay:outline
			    	(new OutlineFactory(osm)).relationOutline();
		            
		            // Relation:multipolygon の MaxHeightを outline->Multipolygonへ設定する
		            //OsmMargeWay.removeHeightFromOuter(osm);

		            // (5) "outline"と"part"が重複しているPART を削除する
		            OsmMargeWay.partGabegi(osm);
		            
					// ファイルへエクスポートする
			    	osm.export(new File(filename + ".osm"));
			    	
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
