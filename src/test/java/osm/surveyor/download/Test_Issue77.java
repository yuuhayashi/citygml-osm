package osm.surveyor.download;

import java.nio.file.Paths;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import osm.surveyor.osm.BodyMap;
import osm.surveyor.osm.OsmBean;
import osm.surveyor.osm.WayBean;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_Issue77 extends DownloadTest {

	/**
	 * `mvn test -Dtest=Test_Issue77#test_way241755306`
	 * ISSUE #77 : https://github.com/yuuhayashi/citygml-osm/issues/77
	 * 
		館林市 54392491_bldg_6697_op
		ref:MLIT_PLATEAU=10207-bldg-26881
		https://www.openstreetmap.org/way/768009718
		
		AREA:{amenity=community_centre＋building=no}のエリアの中にもう一つAREA:{building=civic}がある。
		大外のbuilding=noのAREAにPLATEAUがマージされ、マージ後のオブジェクトがAREA:{building=no}になってしまう。	 
	 */
	@Test
	public void test() {
        try {
        	// (1)指定されたOSMファイルをLOADする
        	// (2) <bound/>を取得する
    		// (3) OSMから<bound>範囲内の現在のデータをダウンロードする
        		// (4) ダウンロードしたデータをパースする
        	// (5) "building"関係のPOIのみに絞る
        	BodyMap map = testdo(Paths.get("src/test/resources", "54392491_bldg_6697_op.osm"));
    		OsmBean org = (OsmBean) map.get("org");

    		assertNotNull(org.getBounds());
			assertNotNull(org.getRelationList());
			
			// Issue #77 "building=no"
			assertNull(find(org, 768009718));	// 存在しないこと {amenity=community_centre＋building=no}

			// Issue #77 "building=civic"
			assertNotNull(find(org, 768009719));	// 存在すること {building=civic}

        } catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
	
	static WayBean find(OsmBean org, long id) {
		for (WayBean way : org.getWayList()) {
			if (way.getId() == id) {
				return way;
			}
		}
		return null;
	}
}
