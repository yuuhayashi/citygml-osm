package osm.surveyor.download;

import java.io.File;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import com.google.common.collect.Lists;

import osm.surveyor.citygml.OsmMrgFiles;
import osm.surveyor.citygml.OsmOrgFiles;
import osm.surveyor.gml.camel.CitygmlLoad;

public class OrgFileListProcessor implements Processor {

	// "direct:org-files"
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
                && OsmOrgFiles.filter(name);
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
            	osmName += OsmMrgFiles.SUFFIX;
            	
            	if (CitygmlLoad.isExit(directory, osmName)) {
            		System.out.println("SKIP: '"+ osmName +"' already exists.");
            	}
            	else {
            		System.out.println("INFO: Not exists '"+ osmName +"'.");
                    files.add(file);
            	}
            }
        });
        return files;
    }
}
