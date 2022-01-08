package osm.surveyor.update;

import java.nio.file.Paths;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runners.MethodSorters;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.BodyMap;
import osm.surveyor.osm.OsmBean;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_A extends OsmUpdaterTest {

	/**
	 * 東京都大田区南六郷三丁目 "53392547_bldg_6697_op2"
	 * `mvn test -Dtest=OsmUpdaterTest_A#test_a`
	 * 東京都大田区 多摩川右岸付近
	 * <bounds minlat="35.5407086" minlon="139.7124522" maxlat="35.5422523" maxlon="139.7156384"/>
	 * ・ビルディングリレーションは存在しない
	 * ・タグありのノードを含むWAYは存在しない
	 * - "highway"WAYは存在しないこと
	 * - "landuse"WAYは存在しないこと
	 */
	@Test
	public void test_53392547() {
		BodyMap map = testdo(Paths.get("./src/test/resources/53392547_bldg_6697_op2.osm"));
        try {
    		// (1)指定されたOSMファイルをLOADする
        	// (2) <bound/>を取得する
    		// (3) OSMから<bound>範囲内の現在のデータをダウンロードする
        		// (4) ダウンロードしたデータをパースする
        	// (5) "building"関係のPOIのみに絞る
    		OsmBean mrg = (OsmBean) map.get("mrg");
	        assertNotNull(mrg);
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	/**
	 * `mvn test -Dtest=OsmUpdaterTest_A#test_d1`
	 * 
	 * INPUT: "sample_a_bldg_6697_op2.osm"
	 * INPUT: "sample_a3_bldg_6697_op2.org.osm"
	 */
	@Test
	@Category(DetailTests.class)
	public void test_a3() {
        try {
        	// (1)指定されたOSMファイルをLOADする
        	// (2) <bound/>を取得する
    		// (3) OSMから<bound>範囲内の現在のデータをダウンロードする
        		// (4) ダウンロードしたデータをパースする
        	// (5) "building"関係のPOIのみに絞る
    		BodyMap map = testdo(Paths.get("./src/test/resources/sample_a_bldg_6697_op2.osm"));
    		OsmBean mrg = (OsmBean) map.get("mrg");
	        assertNotNull(mrg);
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
