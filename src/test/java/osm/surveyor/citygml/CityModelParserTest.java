package osm.surveyor.citygml;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CityModelParserTest {
	static File gmlfile;

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
		File gmlfile = new File("src/test/resources/50302469_bldg_6697_op.gml");
				
        // GMLファイルをパースする
		try {
	        OsmDom osm = new OsmDom();
	        CitygmlFile target = new CitygmlFile(gmlfile, osm);
	        target.parse();
	        
	        
	        assertNotNull(target.getGml());
	        
	        assertNotNull(osm);
	        assertNotNull(osm.getWays());
	        
	        // GMLv2
	        List<ElementWay> ways = osm.getWay("40205-bldg-91547");
	        assertEquals(2, ways.size());
	        for (ElementWay way : ways) {
	        	if (way.getTagValue("building:part") != null) {
	        		assertEquals("2016", way.getTagValue("survey:date"));
	        		assertEquals("4.2", way.getTagValue("height"));
	        		assertNull(way.getTagValue("building:levels")); //  <bldg:storeysAboveGround>-9999</bldg:storeysAboveGround>
	        		assertEquals("2", way.getTagValue("building:levels:underground")); // <bldg:storeysBelowGround>2</bldg:storeysBelowGround>
	        	}
	        	else {
	        		assertNull(way.getTagValue("survey:date"));
	        	}
	        }

	        // GMLv4 埼玉県新座市 bldg_c040f49a-2685-43be-989c-446bfd863039
	        ways = osm.getWay("11230-bldg-28587");
	        //ways = osm.getWay("bldg_c040f49a-2685-43be-989c-446bfd863039");
	        assertEquals(1, ways.size());
	        for (ElementWay way : ways) {
	        	if (way.getTagValue("building:part") != null) {
	        		assertEquals("2024", way.getTagValue("survey:date"));
	        		assertEquals("1993", way.getTagValue("start_date"));
	        		assertEquals("7.5", way.getTagValue("height"));
	        		assertEquals("2", way.getTagValue("building:levels"));
	        		assertNull(way.getTagValue("building:levels:underground")); // <bldg:storeysBelowGround>9999</bldg:storeysBelowGround>
	        	}
	        	else {
	        		assertNull(way.getTagValue("survey:date"));
	        	}
	        }

	        String surveyYear = target.getGml().getSurveyYear();
	        assertNotNull(surveyYear);
	        assertFalse(surveyYear.isEmpty());
			assertEquals("2024", surveyYear);
		}
		catch(Exception e) {
			fail();
		}
	}
}
