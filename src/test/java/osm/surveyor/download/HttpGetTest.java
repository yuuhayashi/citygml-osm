package osm.surveyor.download;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import osm.surveyor.OnlineTests;
import osm.surveyor.osm.api.GetResponse;
import osm.surveyor.osm.api.HttpGet;

public class HttpGetTest {
	public String host = "https://www.openstreetmap.org";

	@Test
	@Category(OnlineTests.class)
	public void testVersions() {
		try {
			HttpGet obj = new HttpGet();
			GetResponse res = obj.getVersions();
			res.printout();
		} catch (Exception e) {
			e.printStackTrace();
			fail("エラー");
		}
	}

	@Test
	@Category(OnlineTests.class)
	public void testCapabilities() {
		try {
			HttpGet obj = new HttpGet();
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
	@Category(OnlineTests.class)
	public void testMap() {
		try {
	        double minlon = 139.4197591d;
			double maxlon = 139.4279939d;
			double minlat = 35.4320438d;
			double maxlat = 35.4375923d;
			HttpGet obj = new HttpGet();
			GetResponse res = obj.getMap(minlon, minlat, maxlon, maxlat);
			res.printout();
		} catch (Exception e) {
			e.printStackTrace();
			fail("エラー");
		}
	}
}
