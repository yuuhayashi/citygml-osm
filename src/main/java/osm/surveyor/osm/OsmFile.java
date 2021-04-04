package osm.surveyor.osm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class OsmFile extends File {
	private static final long serialVersionUID = 1L;

	public OsmFile(String filepath) {
		super(filepath);
	}
	
	PrintWriter pw = null;

	public void open() throws IOException {
		this.pw = new PrintWriter(new BufferedWriter(new FileWriter(this)));
		pw.println("<?xml version='1.0' encoding='UTF-8'?>");
		pw.println("<osm version='0.6' generator='JOSM'>");
	}
	
	public void println(String str) {
		pw.println(str);
	}
	
	public void close() {
		pw.println("</osm>");
		pw.close();
	}
}
