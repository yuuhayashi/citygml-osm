package osm.surveyor.osm.camel;

import org.apache.camel.builder.RouteBuilder;

public class LoadFileRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:load-file")
		//.process(new DownloadProcessor())
        .to("stream:out");
	}

}
