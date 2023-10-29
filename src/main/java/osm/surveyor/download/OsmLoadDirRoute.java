package osm.surveyor.download;

import org.apache.camel.builder.RouteBuilder;

public class OsmLoadDirRoute extends RouteBuilder {

	/**
	 * from("direct:osm-files")
	 * 	to "direct:osm-file-read"
	 */
	@Override
	public void configure() throws Exception {
		onException(Exception.class)
        .handled(false)
        .log("Error")
        .setBody().constant("なにかエラーが発生")
        .log("Error: ${body}")
        ;
		
		// カレントディレクトリを処理する
		from("direct:osm-files")
			.process(new OsmFileListProcessor())
			.streamCaching()
			.split()
				.simple("${body}")
		        .to("direct:osm-file-read")
			.end()
		;
	}

}
