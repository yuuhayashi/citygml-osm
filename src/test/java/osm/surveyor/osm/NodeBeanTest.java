package osm.surveyor.osm;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class NodeBeanTest {

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
	public void test01() {
		NodeBean a = new NodeBean();
		NodeBean b = null;
		assertFalse(a.equals(b));
	}

	@Test
	public void test02() {
		NodeBean a = new NodeBean();
		assertTrue(a.equals(new NodeBean()));
		assertFalse(a.equals(new PoiBean()));
	}

	@Test
	public void test03() {
		NodeBean a = new NodeBean();
		a.setId(-107065);
		a.setAction("modify");
		a.setVisible(true);
		NodeBean b = a.clone();
		assertTrue(a.equals(b));
		
		PoiBean c = new PoiBean();
		c.setId(-107065);
		c.setAction("modify");
		c.setVisible(true);
		assertFalse(a.equals(c));
	}
	
	@Test
	public void test04() {
		NodeBean a = new NodeBean();
		a.setId(1803576119);
		a.setTimestamp("2012-06-27T05:23:26Z");
		a.setUid("618878");
		a.setUser("nakanao");
		a.setVisible(true);
		a.setVersion("1");
		a.setChangeset("12032994");
		a.setLat("35.43464696576");
		a.setLon("139.4102145808");
		
		NodeBean b = a.clone();
		assertTrue(a.equals(b));
		
		b.setTimestamp("2012-06-27T05:23:26Z");
		b.setChangeset(null);
		assertFalse(a.equals(b));

		b.setChangeset("12032994");
		assertTrue(a.equals(b));

		a.setLat("35.43464696");
		a.setLon("139.4102145");
		assertFalse(a.equals(b));
		
		a.setLat("35.43464696576");
		a.setLon("139.4102145808");
		assertTrue(a.equals(b));
	}

}
