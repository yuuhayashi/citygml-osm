package osm.surveyor.update;

import java.io.File;
import java.nio.file.Paths;

import javax.xml.bind.JAXB;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.FileEndpoint;

import osm.surveyor.citygml.OsmFiles;
import osm.surveyor.citygml.OsmOrgFiles;
import osm.surveyor.osm.BodyMap;
import osm.surveyor.osm.OsmBean;

public class OrgFileReadProcessor implements Processor {

	/**
	 * ORGファイルをロードする
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		BodyMap map = exchange.getIn().getBody(BodyMap.class);
		
		FileEndpoint endpoint = (FileEndpoint)exchange.getProperty(Exchange.TO_ENDPOINT);
		File file = endpoint.getFile();
		
		String name = file.getName();
		if (name.endsWith(OsmFiles.SUFFIX)) {
			String filename = name.substring(0, name.length() - OsmFiles.SUFFIX.length());
			File orgf = (Paths.get(".", filename + OsmOrgFiles.SUFFIX).toFile());

			OsmBean org = JAXB.unmarshal(orgf, OsmBean.class);
			org.build();
			map.put("org", org);
			
			OsmBean mrg = new OsmBean();
			mrg.setBounds(org.getBounds());
			map.put("mrg", mrg);
		}
		else {
			throw new Exception("変換元のOSMファイルが設定されていません");
		}
		
		exchange.getIn().setBody(map);
	}
}
