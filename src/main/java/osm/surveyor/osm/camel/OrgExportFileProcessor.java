package osm.surveyor.osm.camel;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;

import javax.xml.bind.JAXB;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.FileEndpoint;

import osm.surveyor.citygml.OsmFiles;
import osm.surveyor.citygml.OsmOrgFiles;
import osm.surveyor.osm.BodyMap;
import osm.surveyor.osm.OsmBean;

public class OrgExportFileProcessor implements Processor {

	/**
	 * ファイルに書き出す
	 * from("direct:osm-org-export")
	 * .process(new OsmExportFileProcessor())
	 * 
	 * Exchange.FromEndpoint.FileEndPoint : 入力元OSMファイル
	 * (OsmBean) Exchange.Body.BodyMap.get("org") : 出力内容
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
		if (name.endsWith(OsmFiles.SUFFIX)) {
			String filename = name.substring(0, name.length() - OsmFiles.SUFFIX.length());
			File outf = (Paths.get(".", filename + OsmOrgFiles.SUFFIX).toFile());

			try (FileWriter fw = new FileWriter(outf)) {
				JAXB.marshal(org, fw);
			}
		}
		else {
			throw new Exception("変換元のOSMファイルが設定されていません");
		}
	}

}
