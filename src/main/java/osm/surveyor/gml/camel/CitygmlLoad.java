package osm.surveyor.gml.camel;

import java.io.File;
import java.util.List;
import java.util.stream.Stream;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

import com.google.common.collect.Lists;

public class CitygmlLoad {
	public static CamelContext camel;
	public static ProducerTemplate producer;

	public static void main(String[] args) throws Exception {
		if (args.length > 0) {
			if (args[0].equals("1st")) {
				if (args.length > 1) {
					loadDir(args[1]);
				}
				else {
					loadDir(".");
				}
			}
			else if (args[0].equals("2nd")) {
				if (args.length > 1) {
					osm.surveyor.osm.camel.OsmDownload.osmDownload(args[1]);
				}
				else {
					osm.surveyor.osm.camel.OsmDownload.osmDownload(".");
				}
			}
			else if (args[0].equals("3rd")) {
				osm.surveyor.update.OsmUpdater.osmUpdate();
			}
			else if (args[0].equals("4th")) {
				osm.surveyor.upload.OsmUploaderRoute.osmUploader();
			}
			else if (args[0].equals("pack")) {
				osm.surveyor.tools.Pack.gmlPackage();
			}
			else if (args[0].equals("unpack")) {
				osm.surveyor.tools.Pack.unpack();
			}
		}
		else {
			loadDir(".");
		}
	}

	public static void loadDir(String dPath) throws Exception {
		camel = new DefaultCamelContext();
		camel.getStreamCachingStrategy().setSpoolThreshold(80 * 256 * 4096);
		camel.getStreamCachingStrategy().setBufferSize(20 * 256 * 4096);
		camel.addRoutes(new GmlLoadDirRoute());
		camel.addRoutes(new GmlLoadRoute());
		
		System.out.println("gml.camel.start();");
		
        camel.start();
        camel.createProducerTemplate().sendBody("direct:gml-files", dPath);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                camel.stop();
            } catch (Exception e) {}
    		System.out.println("gml.camel.stop();");
        }));
        
		System.out.println("gml.camel.end();");
	}
	
	/**
	 * 指定のディレクトリに指定のファイル名のファイルが存在するかどうか
	 * @param directory
	 * @param osmName
	 * @return true:存在する
	 */
	public static boolean isExit(File directory, String osmName) {
        Stream<File> stream = Stream.of(directory.listFiles((dir, name) -> {
            return !(name.contains("RECYCLE.BIN") || name.contains("System Volume Information"));
        }));
        List<File> osmfiles = Lists.newArrayList();
        stream.forEach(file -> {
            if (!file.isDirectory()) {
                if (file.getName().equals(osmName)) {
                	osmfiles.add(file);
                };
            }
        });
		return (osmfiles.size() > 0);
	}
}
