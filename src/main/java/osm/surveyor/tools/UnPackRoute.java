package osm.surveyor.tools;

import org.apache.camel.builder.RouteBuilder;

public class UnPackRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		onException(Exception.class)
        .handled(false)
        .log("Error")
        .setBody().constant("なにかエラーが発生")
        .log("Error: ${body}")
        ;
		
		// カレントディレクトリを処理する
		from("direct:zip-files")
			.process(new ZipFileListProcessor())
			.split()
				.simple("${body}")
				.process(new UnPackProcessor())
			.end()
		;
	}

}
