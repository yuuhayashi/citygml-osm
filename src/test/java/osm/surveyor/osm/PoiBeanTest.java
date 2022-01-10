package osm.surveyor.osm;

import static org.junit.Assert.*;

import org.junit.Test;

public class PoiBeanTest {

	@Test
	public void test01() {
		PoiBean a = new PoiBean();
		PoiBean b = null;
		assertFalse(a.equals(b));
	}

	@Test
	public void test02() {
		PoiBean a = new PoiBean();
		PoiBean b = new PoiBean();
		assertTrue(a.equals(b));
	}

	@Test
	public void test03() {
		PoiBean a = new PoiBean();
		a.setId(-107065);
		a.setAction("modify");
		a.setVisible(true);
		PoiBean b = a.clone();
		assertTrue(a.equals(b));
		
		b.setVisible(false);
		assertFalse(a.equals(b));
	}
	
	@Test
	public void test04() {
		PoiBean a = new PoiBean();
		a.setId(1803576119);
		a.setTimestamp("2012-06-27T05:23:26Z");
		a.setUid("618878");
		a.setUser("nakanao");
		a.setVisible(true);
		a.setVersion("1");
		a.setChangeset("12032994");
		PoiBean b = a.clone();
		assertTrue(a.equals(b));
		
		b.setTimestamp("2012-06-27T05:23:26Z");
		b.setChangeset(null);
		assertFalse(a.equals(b));
	}
}
