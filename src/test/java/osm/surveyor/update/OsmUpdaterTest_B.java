package osm.surveyor.update;

import static org.hamcrest.CoreMatchers.is;

import java.nio.file.Paths;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runners.MethodSorters;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.OsmBean;
import osm.surveyor.osm.RelationBean;
import osm.surveyor.osm.WayBean;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OsmUpdaterTest_B extends OsmUpdaterTest {
	static final String SOURCE = "sample_b_bldg_6697_op2";

	/**
	 * `mvn test -Dtest=OsmUpdaterTest_B#testSample_b`
	 * 
	 */
	@Test
	public void testSample_b() {
        try {
        	OsmBean osm = testdo(Paths.get("src/test/resources",  SOURCE+".osm"));
			assertNotNull(osm.getRelationList());
			assertTrue(osm.getRelationList().size() > 1);
			assertTrue(osm.getWayList().size() >= 2);
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	/**
	 * `mvn test -Dtest=OsmUpdaterTest_B#testSample_b1`
	 * 
	 * "sample_b_bldg_6697_op2.org.osm"のDownload
	 * 		https://www.openstreetmap.org/way/757291544
	 * 		https://www.openstreetmap.org/way/757291552
	 * 		https://www.openstreetmap.org/way/757291554
	 */
	@Test
	@Category(DetailTests.class)
	public void testSample_b1() {
        try {
			String filename = "sample_b_bldg_6697_op2";

			// 疑似ダウンロード
    		// 既存POIとマージする
			OsmBean osm = dummyDownload(Paths.get("src/test/resources/"+ filename +".org.osm"));
			
			int delCnt = 0;
			int modifyCnt = 0;
			for (WayBean way : osm.getWayList()) {
				if (way.getAction() != null) {
					switch (way.getAction()) {
					case "delete":
						// https://www.openstreetmap.org/way/757291554
						// https://www.openstreetmap.org/way/757291552
						delCnt++;
						break;
					case "modify":
						modifyCnt++;
						break;
					}
				}
			}
			assertEquals(modifyCnt, is(2));
			assertEquals(delCnt, is(2));
			
			for (RelationBean relation : osm.getRelationList()) {
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("multipolygon")) {
					assertEquals(relation.getAction(), is("modify"));
					assertEquals(relation.getTagValue("type"), is("multipolygon"));
					assertEquals(relation.getTagValue("building"), is("yes"));
					assertEquals(relation.getTagValue("addr:full"), is("東京都大田区大森西五丁目"));
					assertEquals(relation.getTagValue("addr:ref"), is("13111006005"));
					assertEquals(relation.getTagValue("height"), is("16.9"));
					assertEquals(relation.getTagValue("ele"), is("2.507"));
					assertEquals(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					
					int outerCnt = 0;
					int innerCnt = 0;
					for (MemberBean mem : relation.getMemberList()) {
						if (mem.getRole().equals("outer")) {
							outerCnt++;
							assertEquals(mem.getType(), is("way"));
							WayBean way = osm.getWay(mem.getRef());
							assertEquals(way.getAction(), is("modify"));
							assertEquals(way.getId(), is(757291544l));
							assertEquals(way.getChangeset(), is("78683298"));
							assertEquals(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
							assertEquals(way.getTagValue("fixme"), is("PLATEAUデータで更新されています"));
							assertNull(way.getTagValue("building"));
							assertEquals(way.getTagList().size(), is(2));
						}
						if (mem.getRole().equals("inner")) {
							innerCnt++;
							assertEquals(mem.getType(), is("way"));
							WayBean way = osm.getWay(mem.getRef());
							assertEquals(way.getAction(), is("modify"));
							assertTrue(way.getId() < 0);
							assertEquals(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-61384"));
							assertNull(way.getTagValue("building"));
							assertEquals(way.getTagList().size(), is(1));
						}
					}
					assertEquals(outerCnt, is(1));
					assertEquals(innerCnt, is(1));
					assertEquals(relation.getMemberList().size(), is(2));
				}
			}
			assertEquals(osm.getRelationList().size(), is(1));
			assertEquals(osm.getWayList().size(), is(4));
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
