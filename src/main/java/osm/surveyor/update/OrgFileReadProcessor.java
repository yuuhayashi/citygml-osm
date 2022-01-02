package osm.surveyor.update;

import java.io.File;
import java.nio.file.Paths;

import javax.xml.bind.JAXB;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.FileEndpoint;

import osm.surveyor.download.OsmFiles;
import osm.surveyor.osm.BodyMap;
import osm.surveyor.osm.OsmBean;

public class OrgFileReadProcessor implements Processor {

	/**
	 * ORGファイルをロードする
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		FileEndpoint endpoint = (FileEndpoint)exchange.getFromEndpoint();
		File file = endpoint.getFile();
		
		String name = file.getName();
		if (name.endsWith(OsmFiles.SUFFIX_OSM)) {
			String filename = name.substring(0, name.length() - OsmFiles.SUFFIX_OSM.length());
			File orgf = (Paths.get(".", filename + OsmFiles.SUFFIX_ORG_OSM).toFile());

			OsmBean org = JAXB.unmarshal(orgf, OsmBean.class);
			BodyMap map = new BodyMap();
			map.put("org", org);
			exchange.getIn().setBody(map);
		}
		else {
			throw new Exception("変換元のOSMファイルが設定されていません");
		}
	}
}
