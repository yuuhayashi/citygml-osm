package osm.surveyor.osm.camel;

import org.apache.camel.builder.RouteBuilder;

public class DownloadRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		onException(Exception.class)
        .handled(false)
        .log("Error")
        .setBody().constant("[2nd:DownloadRoute] なにかエラーが発生")
        .log("Error: ${body}")
        ;
		
		// (1)指定されたOSMファイルをLOADする
		from("direct:osm-file-read")
		.streamCaching()
		.process(new OsmFileReadProcessor())
        .to("direct:osm-download")
        ;

		// (2) OSMから<bound>範囲内の現在のデータをダウンロードする
		from("direct:osm-download")
		.streamCaching()
		.process(new OsmDownloadProcessor())
		.process(new OsmBuildingFilterProcessor())	// "building"関係のPOIのみに絞る
        .to("direct:osm-org-export")
        ;
		
		// (3) データをファイルに書き出す
		from("direct:osm-org-export")
		.streamCaching()
		.process(new OrgExportFileProcessor())	// データをファイルに書き出す
        //.to("stream:out")
        ;
	}
}
