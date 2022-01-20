package osm.surveyor.upload;

import org.apache.camel.builder.RouteBuilder;

import osm.surveyor.osm.camel.OsmFileReadProcessor;
import osm.surveyor.update.MrgExportProcessor;
import osm.surveyor.update.OrgFileReadProcessor;
import osm.surveyor.update.OrgUpdateProcessor;

public class OsmUploaderRoute extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		onException(Exception.class)
        .handled(false)
        .log("Error")
        .setBody().constant("[３ｒｄ：OsmUpdaterRoute]なにかエラーが発生")
        .log("Error: ${body}")
        ;
		
		// (1) OSMファイルとORGファイルををLOADする
		from("direct:checked-file-read")
		.process(new OsmFileReadProcessor())
        .to("direct:checked-convert")
        ;

		// (2) 既存POIとマージする
		from("direct:checked-convert")
		.process(new CheckedConvertProcessor())
        .to("direct:release-export")
        ;

		// (3) RELEASEファイルに出力する
		from("direct:release-export")
		.process(new ReleaseExportProcessor())
		//.to("stream:out")
        ;
	}
}
