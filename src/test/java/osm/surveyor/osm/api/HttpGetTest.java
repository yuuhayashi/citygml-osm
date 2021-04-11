package osm.surveyor.osm.api;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HttpGetTest {
	public String host = "https://www.openstreetmap.org";

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
	public void testVersions() {
		try {
			HttpGET obj = new HttpGET();
			GetResponse res = obj.getVersions();
			res.printout();
		} catch (Exception e) {
			e.printStackTrace();
			fail("エラー");
		}
	}

	@Test
	public void testCapabilities() {
		try {
			HttpGET obj = new HttpGET();
			GetResponse res = obj.getCapabilities();
			res.printout();
		} catch (Exception e) {
			e.printStackTrace();
			fail("エラー");
		}
	}
	
	/**
	 * 	<node id="-4131" timestamp="2011-01-21T16:47:41Z" lat="35.4350644157973" lon="139.423684433498">
			<tag k="name" v="あやせ荘"/>
		</node>
		<node id="-4152" timestamp="2011-01-21T16:47:41Z" lat="35.4341675801122" lon="139.418362759267">
			<tag k="name" v="武者奇橋"/>
		</node>
		<node id="-4155" timestamp="2011-01-21T16:47:41Z" lat="35.4369651010672" lon="139.426400070915">
			<tag k="name" v="綾瀬市役所"/>
		</node>
        double minlon = 139.4197591d;
		double maxlon = 139.4279939d;
		double minlat = 35.4320438d;
		double maxlat = 35.4375923d;
		HttpGET.getMap(minlon, minlat, maxlon, maxlat);
	 */
	@Test
	public void testMap() {
		try {
	        double minlon = 139.4197591d;
			double maxlon = 139.4279939d;
			double minlat = 35.4320438d;
			double maxlat = 35.4375923d;
			HttpGET obj = new HttpGET();
			GetResponse res = obj.getMap(minlon, minlat, maxlon, maxlat);
			res.printout();
		} catch (Exception e) {
			e.printStackTrace();
			fail("エラー");
		}
	}
}
