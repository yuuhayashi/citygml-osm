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
			.process(new OsmFileReadProcessor())
			.process(new GetBoundProcessor())
			.process(new OsmDownloadProcessor())
	        .to("direct:osm-parse")
	    .end()
		.log("Body-after:")
		;
		
		// osm.surveyor.osm.camel.OsmParseProcessor
		from("direct:osm-parse")
		.process(new OsmParseProcessor())
        .to("stream:out")
        ;

		// "building"関係のPOIのみに絞る
		
		/*
		org.filterBuilding(sdom);
		
		filename = filename.substring(0, filename.length() - OsmFiles.SUFFIX_OSM.length());
		sdom.export(Paths.get(filename + OsmFiles.SUFFIX_ORG_OSM).toFile());

		exchange.getIn().setBody(org);
		 */
	}

}
