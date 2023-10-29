package osm.surveyor.osm.camel;

import java.io.File;

import javax.xml.bind.JAXB;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import osm.surveyor.osm.BodyMap;
import osm.surveyor.osm.OsmBean;

public class OsmFileReadProcessor implements Processor {

	/**
	 * .send("direct:osm-file-read", exchange);
	 * In.Body --> File(入力ファイル/"./checked.osm")
	 * Body <-- map.put("osm", osm);
	 * Property.TO_ENDPOINT <-- File(入力ファイル/"./checked.osm")
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		File file = exchange.getIn().getBody(File.class);

		System.out.println("OsmFileProcessor : \""+ file.getName() +"\"");
		
		OsmBean osm = JAXB.unmarshal(file, OsmBean.class);
		osm.build();
		
		BodyMap map = new BodyMap();
		map.put("osm", osm);
		exchange.getIn().setBody(map);
		
		// ENDPOINTに入力ファイルを登録する
		exchange.setProperty(Exchange.FILE_NAME, file.getName());
	}

}
