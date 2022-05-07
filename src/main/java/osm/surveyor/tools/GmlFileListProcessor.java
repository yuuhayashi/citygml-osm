package osm.surveyor.tools;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import com.google.common.collect.Lists;

import osm.surveyor.citygml.GmlFiles;
import osm.surveyor.gml.camel.CitygmlLoad;
import osm.surveyor.tools.geojson.GeoJson;

public class GmlFileListProcessor implements Processor {

	// "direct:gml-files"
	@Override
	public void process(Exchange exchange) throws Exception {
		List<File> files = getFiles(exchange.getIn().getBody(String.class));
        exchange.getIn().setBody(files);
		
		// インデックス用のGeojsonファイルを作成する
		String name = null;
		GeoJson json = new GeoJson();
		for (File file : files) {
			if (name == null) {
				File dir = getDir(file);
				name = dir.getName();
				json.setName(name);
			}
        	String code = new String();
        	String filename = file.getName();
        	StringTokenizer st = new StringTokenizer(filename, "_.-");
        	if (st.countTokens() > 1) {
                code = st.nextElement().toString();
        	}
        	try {
        		Long.parseLong(code);
            	json.put(code);
        	}
        	catch (NumberFormatException e) {
        		// 何もしない
        	}
		}
		if (name != null) {
			json.export(new File(name + ".geojson"));
		}
	}
	
	File getDir(File file) {
		File dir = new File(Paths.get(file.getAbsolutePath()).getParent().toString());
		if (dir.getName().equals("bldg")) {
			return getDir(new File(dir.getParent()));
		}
		if (dir.getName().equals(".")) {
			return getDir(new File(dir.getParent()));
		}
		return dir;
	}

	/**
	 * "GML"に対応する"OSM"ファイルが存在するかどうかをしらべ、存在しなければ処理をスキップする。
	 * @param path
	 * @return
	 */
	private List<File> getFiles(String path) {
        File directory = new File(path);
        List<File> files = Lists.newArrayList();
        Stream<File> stream = Stream.of(directory.listFiles((dir, name) -> {
            return !(name.contains("RECYCLE.BIN") || name.contains("System Volume Information"))
                && GmlFiles.filter(name);
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
            	osmName += "osm";
            	
            	if (CitygmlLoad.isExit(directory, osmName)) {
                    files.add(file);
            	}
            	else {
            		System.out.println("SKIP: Not exists  '"+ osmName +"'.");
            	}
            }
        });
        return files;
    }
}
