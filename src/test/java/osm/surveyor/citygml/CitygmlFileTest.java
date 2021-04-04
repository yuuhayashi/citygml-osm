package osm.surveyor.citygml;

import static org.junit.Assert.*;
import java.io.File;
import org.junit.Test;

public class CitygmlFileTest {

	@Test
	public void test() {
        try {
			AppParameters params = new AppParameters();
			File dir = new File("target/src/main/resources/tokyo23ku/533925");
			File file = new File(dir, "53392547_bldg_6697_op2.gml");
			System.out.println(file.getAbsolutePath());
            assertTrue(file.exists());
			
            CitygmlFile target = new CitygmlFile(params, file);
            target.parse();
            
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
}
