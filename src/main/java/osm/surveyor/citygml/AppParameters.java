package osm.surveyor.citygml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

@SuppressWarnings("serial")
public class AppParameters extends Properties {
    static final String FILE_PATH = "citygml.ini";
    
    File file;

    public AppParameters() throws FileNotFoundException, IOException {
        super();
        this.file = new File(FILE_PATH);
        syncFile();
    }

    public AppParameters(Properties defaults) throws FileNotFoundException, IOException {
        super(defaults);
        this.file = new File(FILE_PATH);
        syncFile();
    }

    public AppParameters(String iniFileName) throws FileNotFoundException, IOException {
        super();
        this.file = new File(iniFileName);
        syncFile();
    }

    private void syncFile() throws FileNotFoundException, IOException {
        boolean update = false;

        if (this.file.exists()) {
            // ファイルが存在すれば、その内容をロードする。
            this.load(new FileInputStream(file));
        }
        else {
            update = true;
        }

        if (update) {
            // ・ファイルがなければ新たに作る
            // ・項目が足りない時は書き足す。
            this.store(new FileOutputStream(this.file), "defuilt settings");
        }
    }

    public void store() throws FileNotFoundException, IOException {
        this.store(new FileOutputStream(this.file), "by citygml-get");
    }
    
    public void printout() {
    }
    
	boolean isParam(String item) {
    	String valueStr = getProperty(item);
    	if ((valueStr != null) && valueStr.equals(Boolean.toString(true))) {
    		return true;
    	}
    	return false;
	}
}
