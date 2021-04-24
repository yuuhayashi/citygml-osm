package osm.surveyor.osm.api;

import static org.junit.Assert.fail;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import osm.surveyor.citygml.CitygmlFile;
import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.OsmMargeWay;

public class CitygmlFileTest {

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
	public void testAll() {
		try {
			CitygmlFile.main(null);
		}
		catch(Exception e) {
			fail("だめ");
		}
	}

	@Test
	public void test53392547() {
		File file = new File("53392547_bldg_6697_op2.gml");
		test(file);
	}

	@Test
	public void test53392588() {
		File file = new File("53392588_bldg_6697_op2.gml");
		test(file);
	}

	public void test(File file) {
		String filename = file.getName();
		System.out.println(filename);
		if (filename.endsWith("_op2.gml")) {
			try {
				filename = filename.substring(0, filename.length()-4);
		        
		        OsmDom osm = new OsmDom();
	            CitygmlFile target = new CitygmlFile(file, osm);
	            target.parse();
		    	osm.export(new File(filename + "_0.osm"));
	            
	            // 各WAYのノードで、他のWAYと共有されたノードを探す
	            OsmMargeWay.relationMarge(osm.relations, osm.ways);
		    	osm.export(new File(filename + "_1.osm"));
	            
	            // メンバーが一つしかないRelation:building を削除する
	            OsmMargeWay.relationGabegi(osm.relations, osm.ways);
		    	osm.export(new File(filename + "_2.osm"));
	            
	            // Relation->member:role=port のoutlineを作成する
	            OsmMargeWay.relationOutline(osm.relations, osm.ways);
	            
	            // `*.osm`に書き出す
		    	File osmfile = new File(filename + ".osm");
		    	osm.export(osmfile);
			}
			catch(Exception e) {
				fail("だめ");
			}
		}
	}
}
