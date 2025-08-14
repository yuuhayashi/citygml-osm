package osm.surveyor.osm;

import static org.junit.Assert.*;

import java.io.File;

import javax.xml.bind.JAXB;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import osm.surveyor.osm.way.Areable;

public class OsmBeanTest {

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
	public void test() {
		File osmfile = new File("src/test/resources/50302469_bldg_6697_op.osm");
				
        // OSMファイルをパースする
		try {
			OsmBean osm = JAXB.unmarshal(osmfile, OsmBean.class);
			osm.convertToWeyMap();
			osm.build();
	        
	        assertNotNull(osm);
	        
	        // GMLv2
	        Areable way = osm.getWay(-101805);
	        assertNotNull(way);
    		assertEquals("yes", way.getPoiBean().getTagValue("building"));
    		assertEquals("125.37", way.getPoiBean().getTagValue("ele"));
    		assertEquals("10.6", way.getPoiBean().getTagValue("height"));
    		assertEquals("2024", way.getPoiBean().getTagValue("survey:date"));
    		assertEquals("1993", way.getPoiBean().getTagValue("start_date"));
    		assertEquals("40205-bldg-92587", way.getPoiBean().getTagValue("ref:MLIT_PLATEAU"));
    		assertEquals("2", way.getPoiBean().getTagValue("building:levels")); // <bldg:storeysBelowGround>2</bldg:storeysBelowGround>
    		assertEquals("9999", way.getPoiBean().getTagValue("building:levels:underground")); //  <bldg:storeysAboveGround>-9999</bldg:storeysAboveGround>
    		//assertNull(way.getTagValue("building:levels:underground")); //  <bldg:storeysAboveGround>-9999</bldg:storeysAboveGround>

	        Areable way101821 = osm.getWay(-101821);
	        assertNotNull(way101821);
    		assertEquals("40205-bldg-91805", way101821.getPoiBean().getTagValue("ref:MLIT_PLATEAU"));
    		assertEquals("yes", way101821.getPoiBean().getTagValue("building:part"));
    		assertEquals("98.32", way101821.getPoiBean().getTagValue("ele"));
    		assertEquals("9999", way101821.getPoiBean().getTagValue("height"));			// <tag k='height' v='9999' />
    		assertEquals("0", way101821.getPoiBean().getTagValue("building:levels")); 	//  <tag k='building:levels' v='0' />
    		assertEquals("0", way101821.getPoiBean().getTagValue("building:levels:underground")); //  <tag k='building:levels:underground' v='0' />
		}
		catch(Exception e) {
			fail();
		}
	}
}
