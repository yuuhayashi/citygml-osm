package osm.surveyor.upload;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import osm.surveyor.osm.camel.OsmFileReadProcessor;

public class OsmUploaderRoute extends RouteBuilder {
	public static CamelContext camel;

	public static void main(String[] args) throws Exception {
		osmUploader();
	}
	
	public static void osmUploader() throws Exception {
		camel = new DefaultCamelContext();
		camel.addRoutes(new OsmUploaderRoute());
		
		System.out.println("osm-4th.camel.start();");
		
        camel.start();
        camel.createProducerTemplate().sendBody("direct:checked-file-read", "./checked.osm");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                camel.stop();
            } catch (Exception e) {}
    		System.out.println("osm-4th.camel.stop();");
        }));
        
		System.out.println("osm-4th.camel.end();");
	}

	@Override
	public void configure() throws Exception {
		onException(Exception.class)
        .handled(false)
        .log("Error")
        .setBody().constant("[4th：OsmUploaderRoute]なにかエラーが発生")
        .log("Error: ${body}")
        ;
		
		// (1) ファイル`checked.osm`をLOADする
		from("direct:checked-file-read")
		.process(new CheckedFileProcessor())
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
