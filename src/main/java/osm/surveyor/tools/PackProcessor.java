package osm.surveyor.tools;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import osm.surveyor.citygml.GmlFiles;
import osm.surveyor.citygml.OsmFiles;
import osm.surveyor.citygml.OsmMrgFiles;
import osm.surveyor.citygml.OsmOrgFiles;
import osm.surveyor.gml.camel.CitygmlLoad;

public class PackProcessor implements Processor {

	/**
	 * from("direct:gml-files").process(new GmlFileListProcessor()).split()
	 * 
	 * to("direct:gml-file-pack")
	 */
	@SuppressWarnings("unused")
	@Override
	public void process(Exchange exchange) throws Exception {
		List<File> files = new ArrayList<File>();
		File file = exchange.getIn().getBody(File.class);
		File directory = new File(file.getParent());
		
    	String filename = file.getName();
    	String gmlName = new String();
    	StringTokenizer st = new StringTokenizer(filename, ".");
    	if (st.countTokens() > 1) {
        	for (int i = 0; i < (st.countTokens() - 1); i++) {
                gmlName = st.nextElement().toString();
        		break;
        	}
    	}
    	
    	if (CitygmlLoad.isExit(directory, gmlName + GmlFiles.SUFFIX)) {
    		System.out.println("INFO: exists '"+ gmlName +".gml'");
    		files.add(file);
    		
    		String osmName = gmlName + OsmFiles.SUFFIX;
        	if (CitygmlLoad.isExit(directory, osmName)) {
        		System.out.println("INFO: '"+ osmName +"' exists.");
        		files.add(new File(directory, osmName));
        	}
    		
    		String orgName = gmlName + OsmOrgFiles.SUFFIX;
        	if (CitygmlLoad.isExit(directory, orgName)) {
        		System.out.println("INFO: '"+ orgName +"' exists.");
        		files.add(new File(directory, orgName));
        	}
    		
    		String mrgName = gmlName + OsmMrgFiles.SUFFIX;
        	if (CitygmlLoad.isExit(directory, mrgName)) {
        		System.out.println("INFO: '"+ mrgName +"' exists.");
        		files.add(new File(directory, mrgName));
        	}
    	}
    	try {
        	compress(gmlName, files);
        	delete(files);
    	}
    	catch(Exception e) {
    		throw e;
    	}
	}
	
    public void compress(String name, List<File> files) throws IOException {
    	ZipArchiveOutputStream os = new ZipArchiveOutputStream(new File(name +".zip"));
    	
    	System.out.println("pack '"+ name +".zip' : {");
    	
    	for (File file : files) {
        	System.out.println("\t'"+ file.getName() +"';");
    		ZipArchiveEntry zae = new ZipArchiveEntry(file.getName());
    		os.putArchiveEntry(zae);
    		
            byte[] btoRead = new byte[2048];
            try (BufferedInputStream bin = new BufferedInputStream(Files.newInputStream(Paths.get(file.getName())))) {
                int len;
                while ((len = bin.read(btoRead)) != -1) {
                    os.write(btoRead, 0, len);
                }
            }
            os.closeArchiveEntry();
    	}
    	os.close();
    	System.out.println("}");
    }

    public void delete(List<File> files) throws IOException {
    	for (File file : files) {
        	try {
            	System.out.println("[INFO] delete file \t'"+ file.getName() +"';");
            	Files.delete(file.toPath());
        	}
        	catch (Exception e) {
        		System.out.println(e.toString());
        	}
    	}
    }
}
