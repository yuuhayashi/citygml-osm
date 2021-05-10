package osm.surveyor.osm.update;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import osm.surveyor.osm.ElementWay;
import osm.surveyor.sql.Postgis;

public class PostgisTest {
	static Postgis db = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		db = new Postgis("osmdb");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		db.close();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
    	try {
    		ElementWay way = new ElementWay(-1);
			way.initTable(db);
			way.insertTable(db);
		} catch (Exception e) {
			e.printStackTrace();
			fail("例外発生");
		}
	}
}
