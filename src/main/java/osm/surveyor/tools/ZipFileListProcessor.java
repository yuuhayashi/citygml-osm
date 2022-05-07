package osm.surveyor.tools;

import java.io.File;
import java.util.List;
import java.util.stream.Stream;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import com.google.common.collect.Lists;

import osm.surveyor.citygml.ZipFiles;

public class ZipFileListProcessor implements Processor {

	// "direct:zip-files"
	@Override
	public void process(Exchange exchange) throws Exception {
		List<File> files = getFiles(exchange.getIn().getBody(String.class));
        exchange.getIn().setBody(files);
	}

	/**
	 * "zip"ファイル処理。
	 * @param path
	 * @return
	 */
	private List<File> getFiles(String path) {
        File directory = new File(path);
        List<File> files = Lists.newArrayList();
        Stream<File> stream = Stream.of(directory.listFiles((dir, name) -> {
            return !(name.contains("RECYCLE.BIN") || name.contains("System Volume Information"))
                && ZipFiles.filter(name);
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
