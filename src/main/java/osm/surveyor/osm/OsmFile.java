package osm.surveyor.osm;

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

@SuppressWarnings("serial")
public class OsmFile extends File {
	OsmParser parser;
    OsmDom osm;
    
	public static void main(String[] args) throws Exception {
		File dir = new File(".");
		Files.list(dir.toPath()).forEach(new Consumer<Path>() {
			@Override
			public void accept(Path path) {
				try {
		            OsmFile target = new OsmFile(path.toFile(), new OsmDom());
		            target.parse();
				} catch (ParserConfigurationException | SAXException | IOException | ParseException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
    public OsmFile(File file, OsmDom osm) throws ParserConfigurationException, SAXException, IOException, ParseException {
        super(file.getParentFile(), file.getName());
        this.osm = osm;
        this.parser = new OsmParser(osm);
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
			parser.parse(this, this.parser);
		} catch (SAXParseException e) {}
    }
}
