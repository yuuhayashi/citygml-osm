package osm.surveyor.gml.camel;

import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.FileEndpoint;

import osm.surveyor.citygml.CitygmlFile;
import osm.surveyor.osm.OsmDom;

public class GmlFileReadProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		FileEndpoint endpoint = (FileEndpoint)exchange.getFromEndpoint();
		File file = endpoint.getFile();
		
        // (1) GMLファイルをパースする
        OsmDom osm = new OsmDom();
        CitygmlFile target = new CitygmlFile(file, osm);
        target.parse();
        
        //OsmDom osm = CitygmlFile.fileConvert(file);
		exchange.getIn().setBody(osm);
	}

}
