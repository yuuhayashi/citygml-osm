package osm.surveyor.upload;

import java.io.File;
import java.nio.file.Paths;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import osm.surveyor.osm.BodyMap;
import osm.surveyor.osm.OsmBean;

public class ReleaseExportProcessor implements Processor {

	/**
	 * from("direct:release-export")
	 * 
	 * Exchange.body : BodyMap 変換元のオブジェクト
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		BodyMap map = exchange.getIn().getBody(BodyMap.class);
		OsmBean release = (OsmBean) map.get("release");
		File mrgf = (Paths.get(".", "upload.osm").toFile());
		release.export(mrgf);
	}

}
