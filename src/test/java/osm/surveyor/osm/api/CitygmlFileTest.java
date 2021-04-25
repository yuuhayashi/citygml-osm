package osm.surveyor.osm.api;

import static org.junit.Assert.fail;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import osm.surveyor.citygml.CitygmlFile;

public class CitygmlFileTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAll() {
		try {
			CitygmlFile.main(null);
		}
		catch(Exception e) {
			fail("だめ");
		}
	}

	@Test
	public void test53392547() {
		test(Paths.get(".","53392547_bldg_6697_op2.gml"));
	}

	@Test
	public void test53392588() {
		test(Paths.get(".","53392588_bldg_6697_op2.gml"));
	}

	public void test(Path a) {
		CitygmlFile.doConvert(a);
	}
}
