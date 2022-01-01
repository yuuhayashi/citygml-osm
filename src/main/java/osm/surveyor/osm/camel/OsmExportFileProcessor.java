package osm.surveyor.osm.camel;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;

import javax.xml.bind.JAXB;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.FileEndpoint;

import osm.surveyor.download.OsmFiles;
import osm.surveyor.osm.BodyMap;
import osm.surveyor.osm.OsmBean;

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
		BodyMap map = exchange.getIn().getBody(BodyMap.class);
		OsmBean org = (OsmBean) map.get("org");
		if (org == null) {
			throw new Exception("ORGが設定されていません");
		}

		FileEndpoint endpoint = (FileEndpoint)exchange.getFromEndpoint();
		File file = endpoint.getFile();
		
		String name = file.getName();
		if (name.endsWith(OsmFiles.SUFFIX_OSM)) {
			String filename = name.substring(0, name.length() - OsmFiles.SUFFIX_OSM.length());
			File outf = (Paths.get(".", filename + OsmFiles.SUFFIX_ORG_OSM).toFile());

			try (FileWriter fw = new FileWriter(outf)) {
				JAXB.marshal(org, fw);
			}
		}
		else {
			throw new Exception("変換元のOSMファイルが設定されていません");
		}
	}

}
