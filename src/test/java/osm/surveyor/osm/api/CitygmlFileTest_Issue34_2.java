package osm.surveyor.osm.api;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_Issue34_2 extends GmlLoadRouteTest {

	@Test
	@Category(DetailTests.class)
	public void test50303525_2() {
		try {
			OsmDom osm = testdo("./src/test/resources/Issue34_bldg_6697_op.gml");
			CitygmlFileTest_Issue34.checkIssue34_1(osm);
		} catch (Exception e) {
			fail(e.toString());
		}
	}

}
