package osm.surveyor.osm.camel;

import java.io.File;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.FileEndpoint;

import osm.surveyor.osm.OsmDom;

public class OsmExportProcessor implements Processor {

	/**
	 * from("direct:osm-export")
	 * .process(new GmlFileToOsmProcessor())
	 * .process(new OsmExportProcessor())
	 * 
	 * Exchange.FromEndpoint.FileEndPoint : 出力先ファイル
	 * Exchange.body : ElementOsm 変換元のオブジェクト
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		FileEndpoint endpoint = (FileEndpoint)exchange.getFromEndpoint();
		File file = endpoint.getFile();
		
		OsmDom osm = exchange.getIn().getBody(OsmDom.class);
		osm.export(file);
	}

}
