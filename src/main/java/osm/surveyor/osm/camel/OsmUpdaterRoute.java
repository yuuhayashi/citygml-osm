package osm.surveyor.osm.camel;

import org.apache.camel.builder.RouteBuilder;

import osm.surveyor.update.MrgExportProcessor;
import osm.surveyor.update.OrgFileReadProcessor;
import osm.surveyor.update.OrgUpdateProcessor;

public class OsmUpdaterRoute extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		onException(Exception.class)
        .handled(false)
        .log("Error")
        .setBody().constant("[３ｒｄ：OsmUpdaterRoute]なにかエラーが発生")
        .log("Error: ${body}")
        ;
		
		// (1) OSMファイルとORGファイルををLOADする
		from("direct:org-file-read")
		.streamCaching()
		.process(new OsmFileReadProcessor())
		.process(new OrgFileReadProcessor())
        .to("direct:org-updater")
        ;

		// (2) 既存POIとマージする
		from("direct:org-updater")
		.streamCaching()
		.process(new OrgUpdateProcessor())
        .to("direct:mrg-export")
        ;

		// (3) MRGファイルに出力する
		from("direct:mrg-export")
		.streamCaching()
		.process(new MrgExportProcessor())
        ;
	}
}
