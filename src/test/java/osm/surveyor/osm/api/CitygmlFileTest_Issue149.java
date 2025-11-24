package osm.surveyor.osm.api;

import java.io.File;
import java.util.List;

import org.junit.Test;

import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.PoiBean;
import osm.surveyor.osm.way.WayModel;

public class CitygmlFileTest_Issue149 extends GmlLoadRouteTest {

	/**
	 * 山口県周南市 "51310664_bldg_6697_op.gml"
	 * 1st v3.0.2 "relation" is null で.osmファイルが作成されない。 #149
	 */
	@Test
	public void gml51310664() {
		OsmDom dom = testdo("./src/test/resources/51310664_bldg_6697_op.gml");
		try {
	        assertNotNull(dom);
	        
	        
	        // OSMファイルに書き出す
			File domfile = new File("51310664_bldg_6697_op.osm");
	        dom.export(domfile);
	        assertTrue(domfile.exists());
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void gml35215_59596() {
		OsmDom dom = testdo("./src/test/resources/35215-bldg-59596.gml");
		try {
	        assertNotNull(dom);
	        
	        // OSMファイルに書き出す
			File domfile = new File("35215-bldg-59596.osm");
	        dom.export(domfile);
	        assertTrue(domfile.exists());
	        
	        List<WayModel> wayList = dom.getWayList();
			assertNotNull(wayList);
			assertEquals(1, wayList.size());
			WayModel way = wayList.get(0);
			PoiBean poi = way.getPoiBean();
			assertEquals("35215-bldg-59596", poi.getTag("ref:MLIT_PLATEAU").v);
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void gml35215_59594() {
		OsmDom dom = testdo("./src/test/resources/35215-bldg-59594.gml");
		try {
	        assertNotNull(dom);
	        
	        // OSMファイルに書き出す
			File domfile = new File("35215-bldg-59594.osm");
	        dom.export(domfile);
	        assertTrue(domfile.exists());

	        List<WayModel> wayList = dom.getWayList();
			assertNotNull(wayList);
			assertEquals(1, wayList.size());
			WayModel way = wayList.get(0);
			PoiBean poi = way.getPoiBean();
			assertEquals("35215-bldg-59594", poi.getTag("ref:MLIT_PLATEAU").v);
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void gml35215_() {
		OsmDom dom = testdo("./src/test/resources/51310664_.gml");
		try {
	        assertNotNull(dom);
	        
	        // OSMファイルに書き出す
			File domfile = new File("35215-bldg-59594.osm");
	        dom.export(domfile);
	        assertTrue(domfile.exists());

	        List<WayModel> wayList = dom.getWayList();
			assertNotNull(wayList);
			assertEquals(1, wayList.size());
			WayModel way = wayList.get(0);
			PoiBean poi = way.getPoiBean();
			assertEquals("35215-bldg-2487", poi.getTag("ref:MLIT_PLATEAU").v);
		} catch (Exception e) {
			fail(e.toString());
		}
	}
}
