package osm.surveyor.tools;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import com.google.common.collect.Lists;

import osm.surveyor.citygml.ConversionTable;
import osm.surveyor.citygml.GmlFiles;
import osm.surveyor.gml.camel.CitygmlLoad;
import osm.surveyor.tools.geojson.GeoJson;

public class GmlFileListProcessor implements Processor {

	// "direct:gml-files"
	@Override
	public void process(Exchange exchange) throws Exception {
		List<File> gmlfiles = getFiles(exchange.getIn().getBody(String.class));
        exchange.getIn().setBody(gmlfiles);
        
		// インデックス用のGeojsonファイルを作成する
		String path = null;
		String name = "";
		GeoJson json = new GeoJson();
		ConversionTable table = new ConversionTable(Paths.get(ConversionTable.fileName).toFile());
		if (table != null) {
			if (table.version != null) {
				json.setVersion(table.version);
			}
		}
		for (File gmlfile : gmlfiles) {
			if (path == null) {
				List<File> stack = getDir(gmlfile);
				path = "";
				for (File dir : stack) {
					name = dir.getName();
					path = name + "/" + path;
				}
			}
        	String code = new String();
        	String filename = gmlfile.getName();
        	json.setPath(withoutExt(filename) + ".zip");
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
			json.export(new File("index.geojson"));
		}
	}
	
	String withoutExt(String filename) {
		int i = filename.lastIndexOf('.');
		if (i > 0) {
		    return filename.substring(0, i);
		}
		return filename;
	}
	
	List<File> getDir(File file) {
		ArrayList<File> stack = new ArrayList<>();
		String name = ".";
		File dir = file;
		do {
			dir = new File(Paths.get(dir.getAbsolutePath()).getParent().toString());
			name = dir.getName();
			if (!name.equals(".")) {
				stack.add(dir);
			}
		}
		while (name.equals("bldg") || name.equals("."));
		return stack;
	}

	/**
	 * "GML"に対応する"OSM"ファイルが存在するかどうかをしらべ、存在しなければ処理をスキップする。
	 * @param path
	 * @return
	 */
	private List<File> getFiles(String path) {
        File directory = new File(path);
        List<File> gmlfiles = Lists.newArrayList();
        Stream<File> stream = Stream.of(directory.listFiles((dir, name) -> {
            return !(name.contains("RECYCLE.BIN") || name.contains("System Volume Information"))
                && GmlFiles.filter(name);
        }));
        stream.forEach(file -> {
            if (file.isDirectory()) {
                gmlfiles.addAll(getFiles(file.getAbsolutePath()));
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
                    gmlfiles.add(file);
            	}
            	else {
            		System.out.println("SKIP: Not exists  '"+ osmName +"'.");
            	}
            }
        });
        return gmlfiles;
    }
}
