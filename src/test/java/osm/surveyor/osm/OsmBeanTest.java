package osm.surveyor.osm;

import static org.junit.Assert.*;

import java.io.File;

import javax.xml.bind.JAXB;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
			osm.build();
	        
	        assertNotNull(osm);
	        
	        // GMLv2
	        WayBean way = osm.getWay(-101805);
	        assertNotNull(way);
    		assertEquals("yes", way.getTagValue("building"));
    		assertEquals("125.37", way.getTagValue("ele"));
    		assertEquals("10.6", way.getTagValue("height"));
    		assertEquals("2024", way.getTagValue("survey:date"));
    		assertEquals("1993", way.getTagValue("start_date"));
    		assertEquals("40205-bldg-92587", way.getTagValue("ref:MLIT_PLATEAU"));
    		assertEquals("2", way.getTagValue("building:levels")); // <bldg:storeysBelowGround>2</bldg:storeysBelowGround>
    		assertEquals("9999", way.getTagValue("building:levels:underground")); //  <bldg:storeysAboveGround>-9999</bldg:storeysAboveGround>
    		//assertNull(way.getTagValue("building:levels:underground")); //  <bldg:storeysAboveGround>-9999</bldg:storeysAboveGround>

	        WayBean way101821 = osm.getWay(-101821);
	        assertNotNull(way101821);
    		assertEquals("yes", way101821.getTagValue("building:part"));
    		assertEquals("98.32", way101821.getTagValue("ele"));
    		assertEquals("9999", way101821.getTagValue("height"));			// <tag k='height' v='9999' />
    		assertEquals("40205-bldg-91805", way101821.getTagValue("ref:MLIT_PLATEAU"));
		}
		catch(Exception e) {
			fail();
		}
	}
}
