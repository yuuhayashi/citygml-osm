package osm.surveyor.osm.camel;

import java.io.File;
import java.io.FileWriter;

import javax.xml.bind.JAXB;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.FileEndpoint;

import osm.surveyor.osm.ElementOsm;

public class OsmExportFileProcessor implements Processor {

	/**
	 * ファイルに書き出す
	 * from("direct:str-export")
	 * .process(new ToOrgOsmFileProcessor())
	 * .process(new OsmExportFileProcessor())
	 * 
	 * Exchange.FromEndpoint.FileEndPoint : 出力先ファイル
	 * Exchange.body : ElementOsm 変換元
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		FileEndpoint endpoint = (FileEndpoint)exchange.getFromEndpoint();
		File file = endpoint.getFile();
		
		ElementOsm osm = exchange.getIn().getBody(ElementOsm.class);
		try (FileWriter fw = new FileWriter(file)) {
			JAXB.marshal(osm, fw);
		}
		
		exchange.getIn().setBody(osm);
	}

}
