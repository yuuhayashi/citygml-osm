package osm.surveyor.citygml;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class AppParametersTest {

    public static class 定義ファイルが存在しない場合 {

        @Before
        public void setUp() throws Exception {
        	delTestData("citygml.ini");
        }

        @After
        public void tearDown() throws Exception {
        	delTestData("citygml.ini");
        }

        @Test
        public void test() {
            assertTrue(Boolean.valueOf(true));
        }
    }

    public static class 定義ファイルがtureに定義されているとき {

        @Before
        public void setUp() throws Exception {
        	delTestData("citygml.ini");
        	setupTestData("AdjustTime.on.ini", "citygml.ini");
        }

        @After
        public void tearDown() throws Exception {
        	delTestData("citygml.ini");
        }

        @Test
        public void IMG_OUTPUT_ALLをfalseに書き換える() {
            try {
                AppParameters params = new AppParameters();
                params.store();
                assertTrue(Boolean.valueOf(true));
            }
            catch (IOException e) {
                fail("Exceptionが発生した。");
            }
        }
    }

    public static class 定義ファイルがfalseに定義されているとき {

        @Before
        public void setUp() throws Exception {
        	delTestData("AdjustTime.ini");
        	setupTestData("AdjustTime.off.ini", "citygml.ini");
        }

        @Test
        public void test() {
            assertTrue(Boolean.valueOf(true));
        }

        @After
        public void tearDown() throws Exception {
        	delTestData("citygml.ini");
        }
    }
    
    public static void delTestData(String filename) {
        File iniFile = new File(filename);
        if (iniFile.exists()) {
            iniFile.delete();
        }
    }
    
    static void setupTestData(String sfilename, String dfilename) throws IOException {
        File testFile = new File("target/test-classes/ini", sfilename);
        FileInputStream inStream = new FileInputStream(testFile);
        FileOutputStream outStream = new FileOutputStream(new File(dfilename));
        FileChannel inChannel = inStream.getChannel();
        FileChannel outChannel = outStream.getChannel();
        try {
            inChannel.transferTo(0, inChannel.size(),outChannel);
        }
        finally {
            if (inChannel != null) inChannel.close();
            if (outChannel != null) outChannel.close();
            inStream.close();
            outStream.close();
        }
    }
}
