package osm.surveyor.gml.camel;

import org.apache.camel.builder.RouteBuilder;

public class GmlLoadDirRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		onException(Exception.class)
        .handled(false)
        .log("Error")
        .setBody().constant("なにかエラーが発生")
        .log("Error: ${body}")
        ;
		
		// カレントディレクトリを処理する
		from("direct:gml-files")
			.process(new GmlFileListProcessor())
			.split()
				.simple("${body}")
				.process(new GmlFileProcessor())
		        .to("direct:gml-file-read")
			.end()
		;
	}

}
