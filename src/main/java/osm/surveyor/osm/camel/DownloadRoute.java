package osm.surveyor.osm.camel;

import org.apache.camel.builder.RouteBuilder;

import osm.surveyor.download.OsmFiles;

public class DownloadRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		onException(Exception.class)
        .handled(false)
        .log("Error")
        .setBody().constant("なにかエラーが発生")
        .log("Error: ${body}")
        ;
		
		// カレントディレクトリを処理する
		from("file:.?noop=true")
		.split()
			.simple("${body}")
			.filter().method(new OsmFiles(), "filter")
	        .to("direct:osm-file-read")
	    .end()
		.log("Body-after:")
		;
		
		// (1)指定されたOSMファイルをLOADする
		from("direct:osm-file-read")
		.process(new OsmFileReadProcessor())
        .to("direct:get-bound")
        ;

		// (2) <bound/>を取得する
		from("direct:get-bound")
		.process(new GetBoundProcessor())
        .to("direct:osm-download")
        ;

		// (3) OSMから<bound>範囲内の現在のデータをダウンロードする
		from("direct:osm-download")
		.process(new OsmDownloadProcessor())
		//.process(new StrExportProcessor())		// 文字データをファイルに書き出す
        .to("direct:osm-parse")
        ;

		// (4) ダウンロードしたデータをパースする
		from("direct:osm-parse")
		.process(new OsmParseProcessor())
        .to("direct:osm-building-filter")
        ;

		// (5) "building"関係のPOIのみに絞る
		//	更新対象のビルディングと更新しないビルディングに分割する
		from("direct:osm-building-filter")
		.process(new OsmBuildingFilterProcessor())
        .to("direct:osm-org-export")
        ;
		
		from("direct:osm-org-export")
		.process(new ToOrgOsmFileProcessor())	// ファイル名 "*.osm" を "*.org.osm" に変換する
		.process(new OsmExportFileProcessor())	// データをファイルに書き出す
        .to("stream:out")
        ;
		
		// (3) OSMから<bound>範囲内の現在のデータをダウンロードする
		// (4) ダウンロードしたデータをパースする
		from("direct:test-file-load")
		.process(new OsmFileReadProcessor())
        .to("direct:osm-building-filter")
        ;
	}

}
