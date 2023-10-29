package osm.surveyor.osm.camel;

import org.apache.camel.builder.RouteBuilder;

import osm.surveyor.download.OrgFileListProcessor;

public class OrgLoadDirRoute extends RouteBuilder {

	/**
	 * from("direct:osm-files")
	 * 	to "direct:org-file-read"
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
		from("direct:org-files")
			.process(new OrgFileListProcessor())
			.split()
				.simple("${body}")
		        .to("direct:org-file-read")
			.end()
		;
	}

}
