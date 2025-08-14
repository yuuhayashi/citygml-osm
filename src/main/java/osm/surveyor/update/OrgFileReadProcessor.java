package osm.surveyor.update;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalTime;

import javax.xml.bind.JAXB;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

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
		
		String name = (String) exchange.getProperty(Exchange.FILE_NAME);
		if (name.endsWith(OsmFiles.SUFFIX)) {
			String filename = name.substring(0, name.length() - OsmFiles.SUFFIX.length());
			File orgf = (Paths.get(".", filename + OsmOrgFiles.SUFFIX).toFile());

			System.out.println(LocalTime.now() +"\tOrgFileReadProcessor (\""+ orgf.getName() +"\")");
			OsmBean org = JAXB.unmarshal(orgf, OsmBean.class);
			org.convertToWeyMap();
			org.build();
			map.put("org", org);
			exchange.getIn().setBody(map);
		}
		else {
			throw new Exception("変換元のOSMファイルが設定されていません : \""+ name +"\"");
		}
		
		exchange.getIn().setBody(map);
		exchange.setProperty(Exchange.FILE_NAME, name);
	}
}
