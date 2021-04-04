package osm.surveyor.citygml;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

@SuppressWarnings("serial")
public class CitygmlFile extends File {
	CityModelParser gml;
    AppParameters params;
    public static long idno = 0;

    public CitygmlFile(AppParameters params, File file) throws ParserConfigurationException, SAXException, IOException, ParseException {
        super(file.getParentFile(), file.getName());
        this.params = params;
        this.gml = new CityModelParser(file);
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
    
    /**
     * インスタンス状態の表示（parse()実行後に有効になる）
     * 
     */
    public void printinfo() {
		// 表示
    	System.out.println(String.format("CityGML file: '%s'", getName()));
    }
}
