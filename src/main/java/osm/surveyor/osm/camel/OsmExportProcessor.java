package osm.surveyor.osm.camel;

import java.io.File;
import java.io.PrintStream;

import javax.xml.bind.JAXB;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.FileEndpoint;

import osm.surveyor.osm.ElementOsm;

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
		
		ElementOsm osm = exchange.getIn().getBody(ElementOsm.class);
		
		try (PrintStream ps = new PrintStream(file, "utf-8")) {
			JAXB.marshal(osm, ps);
		}
	}

}
