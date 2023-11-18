package osm.surveyor.citygml;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import osm.surveyor.osm.OsmDom;

public class CityModelParserTest {

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
	        
	        String surveyYear = target.getGml().getSurveyYear();
	        assertNotNull(surveyYear);
	        assertFalse(surveyYear.isEmpty());
			assertEquals("2016", surveyYear);
		}
		catch(Exception e) {
			fail();
		}
	}
}
