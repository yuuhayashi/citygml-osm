package osm.surveyor.osm.update;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class OsmUpdaterTest {

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
	public void testMain() {
		try {
			ArrayList<String> args = new ArrayList<>();
			args.add("53392547_bldg_6697_op2.osm");
			//fail();
			OsmUpdater.main(args.toArray(new String[args.size()]));
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
