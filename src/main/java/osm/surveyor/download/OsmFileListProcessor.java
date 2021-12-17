package osm.surveyor.download;

import java.io.File;
import java.util.List;
import java.util.stream.Stream;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import com.google.common.collect.Lists;

public class OsmFileListProcessor implements Processor {

	// "direct:osm-files"
	@Override
	public void process(Exchange exchange) throws Exception {
		List<File> files = getFiles(exchange.getIn().getBody(String.class));
        exchange.getIn().setBody(files);
	}

	private List<File> getFiles(String path) {
        File directory = new File(path);
        List<File> files = Lists.newArrayList();
        Stream<File> stream = Stream.of(directory.listFiles((dir, name) -> {
            return !(name.contains("RECYCLE.BIN") || name.contains("System Volume Information"))
                && OsmFiles.filter(name);
        }));
        stream.forEach(file -> {
            if (file.isDirectory()) {
                files.addAll(getFiles(file.getAbsolutePath()));
            } else {
                files.add(file);
            }
        });
        return files;
    }
}
