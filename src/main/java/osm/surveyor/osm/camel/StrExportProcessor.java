package osm.surveyor.osm.camel;

import java.io.File;
import java.io.FileWriter;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.FileEndpoint;

public class StrExportProcessor implements Processor {

	/**
	 * テキストデータをファイルに書き出す
	 * from("direct:str-export")
	 * .process(new ToOrgOsmFileProcessor())
	 * .process(new StrExportProcessor())
	 * 
	 * Exchange.FromEndpoint.FileEndPoint : 出力先ファイル
	 * Exchange.body : String 変換元の文字列
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		FileEndpoint endpoint = (FileEndpoint)exchange.getFromEndpoint();
		File file = endpoint.getFile();
		
		String str = exchange.getIn().getBody(String.class);
		try (FileWriter fw = new FileWriter(file)) {
	        fw.write(str);
		}
		
		exchange.getIn().setBody(str);
	}

}
