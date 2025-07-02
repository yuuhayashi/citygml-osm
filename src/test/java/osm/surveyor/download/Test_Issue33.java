package osm.surveyor.download;

import java.nio.file.Paths;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import osm.surveyor.osm.BodyMap;
import osm.surveyor.osm.OsmBean;
import osm.surveyor.osm.way.Areable;

/**
 * Issue #33 第2段階のmrgファイルの作成処理の途中で、いくつかのファイルで下記のようなエラーが発生し、mrg.osmファイルが作成されません。
 * 50303545_bldg_6697_op、
 * 50303554_bldg_6697_op、
 * 50303555_bldg_6697_op
 * 岐阜市：53360576_bldg_6697_op、
 * 岐阜市：53361528_bldg_6697_op、
 * 岐阜市：53361618_bldg_6697_op
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_Issue33 extends DownloadTest {
	static final String SOURCE = "50303524_bldg_6697_op";

	/**
	 * `mvn test -Dtest=osm.surveyor.download.Test_Issue33#test50303524`
	 */
	@Test
	public void test50303524() {
        try {
        	// (1)指定されたOSMファイルをLOADする
        	// (2) <bound/>を取得する
    		// (3) OSMから<bound>範囲内の現在のデータをダウンロードする
        		// (4) ダウンロードしたデータをパースする
        	// (5) "building"関係のPOIのみに絞る
        	BodyMap map = testdo(Paths.get("src/test/resources",  SOURCE+".osm"));
    		OsmBean org = (OsmBean) map.get("org");

    		assertNotNull(org.getBounds());
			assertNotNull(org.getRelationList());

			for (Areable way : org.getWays()) {
				// "highway"WAYは存在しないこと
				assertNull(way.getPoiBean().getTagValue("highway"));
				// "landuse"WAYは存在しないこと
				assertNull(way.getPoiBean().getTagValue("landuse"));
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
	
	/**
	 * `mvn test -Dtest=osm.surveyor.download.Test_Issue33#test53360576`
	 * 岐阜市：53360576_bldg_6697_op
	 */
	@Test
	public void test53360576() {
        try {
        	BodyMap map = testdo(Paths.get("src/test/resources",  "53360576_bldg_6697_op.osm"));
    		OsmBean org = (OsmBean) map.get("org");

    		assertNotNull(org.getBounds());
			assertNotNull(org.getRelationList());

			for (Areable way : org.getWays()) {
				// "highway"WAYは存在しないこと
				assertNull(way.getPoiBean().getTagValue("highway"));
				// "landuse"WAYは存在しないこと
				assertNull(way.getPoiBean().getTagValue("landuse"));
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
