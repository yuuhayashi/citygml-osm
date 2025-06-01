package osm.surveyor.download;

import java.io.File;
import java.time.LocalTime;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import com.google.common.collect.Lists;

import osm.surveyor.citygml.OsmFiles;
import osm.surveyor.citygml.OsmOrgFiles;
import osm.surveyor.gml.camel.CitygmlLoad;

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
            	String osmName = new String();
            	String filename = file.getName();
            	StringTokenizer st = new StringTokenizer(filename, ".");
            	if (st.countTokens() > 1) {
                	for (int i = 0; i < (st.countTokens() - 1); i++) {
                        String token = st.nextElement().toString();
                		osmName += token +".";
                	}
            	}
            	osmName += OsmOrgFiles.SUFFIX;
            	
            	if (CitygmlLoad.isExit(directory, osmName)) {
            		System.out.println(LocalTime.now() +"SKIP: '"+ osmName +"' already exists.");
            	}
            	else {
            		System.out.println(LocalTime.now() +"INFO: Not exists '"+ osmName +"'.");
                    files.add(file);
            	}
            }
        });
        return files;
    }
}
