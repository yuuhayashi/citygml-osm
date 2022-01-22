package osm.surveyor.upload;

import org.apache.camel.builder.RouteBuilder;

import osm.surveyor.osm.camel.OsmFileReadProcessor;

public class OsmUploaderRoute extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		onException(Exception.class)
        .handled(false)
        .log("Error")
        .setBody().constant("[３ｒｄ：OsmUpdaterRoute]なにかエラーが発生")
        .log("Error: ${body}")
        ;
		
		// (1) ファイル`checked.osm`をLOADする
		from("direct:checked-file-read")
		.process(new OsmFileReadProcessor())
        .to("direct:checked-convert")
        ;

		// (2) OpenStreetMapへのアップロード用に変換する
		from("direct:checked-convert")
		.process(new CheckedConvertProcessor())
        .to("direct:release-export")
        ;

		// (3) RELEASEファイル(`upload.osm`)に出力する
		from("direct:release-export")
		.process(new ReleaseExportProcessor())
		//.to("stream:out")
        ;
	}
}
