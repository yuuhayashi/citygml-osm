package osm.surveyor.download;

import static org.junit.Assert.*;

import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.main.Main;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import osm.surveyor.osm.BoundsBean;
import osm.surveyor.osm.camel.GetBoundProcessor;
import osm.surveyor.osm.camel.OsmFileReadProcessor;

public class Test_Fujitv {
	public static Main camel;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		camel = new Main();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		camel.start();
	}

	@After
	public void tearDown() throws Exception {
		camel.stop();
	}

	/**
	 * `mvn test -Dtest=FujitvTest#test_download`
	 * 
	 * OSMから「フジテレビ」ビル周辺のデータをダウンロード
	 * 
	 */
	@Test
	public void test_download() {
		try {
			ProducerTemplate producer = camel.getCamelTemplate();
			
			// カレントディレクトリを処理する
	        Exchange result1 = producer.send(
	        		"file:./src/test/resources/fujitv_bldg_6697_op2.osm", 
	        		new OsmFileReadProcessor()
	        );
	        (new GetBoundProcessor()).process(result1);
	        
	        BoundsBean bound = result1.getIn().getBody(BoundsBean.class);
	        assertNotNull(bound);
	        assertEquals("35.6282", bound.maxlat);
	        assertEquals("139.7782", bound.maxlon);
	        assertEquals("139.7713", bound.minlon);
	        assertEquals("35.6255", bound.minlat);
	        
		
			//OsmUpdater updater = new OsmUpdater(Paths.get("src/test/resources/", "fujitv_bldg_6697_op2.osm").toFile());
			//updater.download();
			//updater.sdom.export(Paths.get("fujitv_bldg_6697_op2.org.osm").toFile());
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
