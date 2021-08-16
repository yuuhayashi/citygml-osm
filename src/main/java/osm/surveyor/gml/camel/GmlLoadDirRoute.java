package osm.surveyor.gml.camel;

import org.apache.camel.builder.RouteBuilder;

import osm.surveyor.citygml.GmlFiles;

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
		from("file:.?noop=true")
		.split()
			.simple("${body}")
			.filter().method(new GmlFiles(), "filter")
			.process(new GmlFileReadProcessor())
	        .to("direct:gerbage-way")
	    .end()
		.log("Body-after:")
		;
	}

}
