package osm.surveyor.update;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalTime;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import osm.surveyor.citygml.OsmFiles;
import osm.surveyor.citygml.OsmMrgFiles;
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
		String name = (String) exchange.getProperty(Exchange.FILE_NAME);
		if (name.endsWith(OsmFiles.SUFFIX)) {
			String filename = name.substring(0, name.length() - OsmFiles.SUFFIX.length());
			File mrgf = (Paths.get(".", filename + OsmMrgFiles.SUFFIX).toFile());

			System.out.println(LocalTime.now() +"\tMrgExportProcessor (\""+ mrgf.getName() +"\")");
			OsmBean mrg = (OsmBean) map.get("mrg");
			mrg.export(mrgf);
		}
		else {
			throw new Exception("変換元のOSMファイルが設定されていません");
		}
	}

}
