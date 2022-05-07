package osm.surveyor.tools;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import haya4.tools.files.compless.UnZip;

public class UnPackProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
    	try {
    		File file = exchange.getIn().getBody(File.class);
    		File directory = new File(file.getParent());
    		UnZip.decode(Paths.get(file.getPath()), directory.getPath());
        	delete(file);
    	}
    	catch(Exception e) {
    		throw e;
    	}
	}
	
    public void delete(File file) throws IOException {
    	try {
        	System.out.println("[INFO] delete file \t'"+ file.getName() +"';");
        	Files.delete(file.toPath());
    	}
    	catch (Exception e) {
    		System.out.println(e.toString());
    	}
    }
}
