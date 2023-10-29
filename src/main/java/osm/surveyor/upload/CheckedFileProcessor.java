package osm.surveyor.upload;

import java.nio.file.Paths;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class CheckedFileProcessor implements Processor {

	/**
	 * from("direct:checked-file")
	 * In.Body --> 入力ファイルのパス名 "./checked.osm"
	 * Body <-- File(入力ファイル)
	 * Exchange.body : BodyMap 変換元のオブジェクト
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		String filepath = exchange.getIn().getBody(String.class);
		exchange.getIn().setBody(Paths.get(filepath).toFile());
	}

}
