package osm.surveyor.osm.api;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.MemberBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_Issue34_1 extends GmlLoadRouteTest {

	/**
	 * 
		v1.3.0 mrg.osmファイルで読み込めないものがある。 #34
		
		飯塚市の50303525_bldg_6697_op.mrg.osmのファイルをJOSMにドロップして読み込もうとすると、
		添付のようなエラーダイアログが発生して読み込めませんでした。
		---エラーメッセージ----
		外部ID '-36358' のリレーションは外部ID '-36358' の存在しないプリミティブを参照しています。
		外部ID '-24864' のリレーションは外部ID '-40509' の存在しないプリミティブを参照しています。
		---エラーメッセージ----
		
		multipolygonのinnerに実在しないものがある。
	 */
	@Test
	@Category(DetailTests.class)
	public void test50303525_1() {
		try {
			OsmDom osm = testdo("./src/test/resources/Issue34_bldg_6697_op.gml");
			checkIssue34_1(osm);
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	
	/**
	 *	multipolygonのinnerに実在しないものがないこと。
	 * @param osm
	 */
	static public void checkIssue34_1(OsmDom osm) {
		assertNotNull(osm.relations);
		for (String relationid : osm.relations.keySet()) {
			ElementRelation relation = osm.relations.get(relationid);
			for (MemberBean mem : relation.members) {
				if (mem.getRole().equals("inner")) {
					assertEquals("way", mem.getType());
					ElementWay inner = (ElementWay)osm.ways.get(mem.getRef());
					assertNotNull(inner);
				}
			}
		}
	}

}
