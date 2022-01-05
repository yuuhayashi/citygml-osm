package osm.surveyor.update;

import java.io.File;
import java.nio.file.Paths;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.FileEndpoint;

import osm.surveyor.download.OsmFiles;
import osm.surveyor.osm.BodyMap;
import osm.surveyor.osm.OsmBean;

public class MrgExportProcessor implements Processor {

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
		BodyMap map = exchange.getIn().getBody(BodyMap.class);

		FileEndpoint endpoint = (FileEndpoint)exchange.getFromEndpoint();
		File file = endpoint.getFile();
		String name = file.getName();
		if (name.endsWith(OsmFiles.SUFFIX_OSM)) {
			String filename = name.substring(0, name.length() - OsmFiles.SUFFIX_OSM.length());
			File mrgf = (Paths.get(".", filename + OsmFiles.SUFFIX_MRG_OSM).toFile());

			OsmBean mrg = (OsmBean) map.get("mrg");
			mrg.export(mrgf);
		}
		else {
			throw new Exception("変換元のOSMファイルが設定されていません");
		}
	}

}
