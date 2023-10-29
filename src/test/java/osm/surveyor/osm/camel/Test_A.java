package osm.surveyor.osm.camel;

import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import osm.surveyor.osm.BodyMap;
import osm.surveyor.osm.OsmBean;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_A extends OsmUpdaterTest {

	@Before
	public void setup() {
		try {
			Files.copy(
				Paths.get("./src/test/resources/sample_a_bldg_6697_op2.osm"),
				Paths.get("./sample_a_bldg_6697_op2.osm"), REPLACE_EXISTING
			);
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		try {
			Files.copy(
				Paths.get("./src/test/resources/sample_a_bldg_6697_op2.org.osm"),
				Paths.get("./sample_a_bldg_6697_op2.org.osm"), REPLACE_EXISTING
			);
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * `mvn test -Dtest=OsmUpdaterTest_A`
	 * 
	 * INPUT: "sample_a_bldg_6697_op2.osm"
	 * INPUT: "sample_a_bldg_6697_op2.org.osm"
	 */
	@Test
	public void test_a() {
        try {
        	// (1)指定されたOSMファイルをLOADする
        	// (2) <bound/>を取得する
    		// (3) OSMから<bound>範囲内の現在のデータをダウンロードする
        		// (4) ダウンロードしたデータをパースする
        	// (5) "building"関係のPOIのみに絞る
    		BodyMap map = testdo(Paths.get("./sample_a_bldg_6697_op2.osm"));
    		OsmBean mrg = (OsmBean) map.get("mrg");
	        assertNotNull(mrg);
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
