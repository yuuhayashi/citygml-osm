package osm.surveyor.osm.api;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.TagBean;
import osm.surveyor.osm.way.WayModel;

/**
 * 横浜市 "53391550_bldg_6697_op.gml"
 * 
 * @author hayashi
 */
public class CitygmlFileTest_53391550 extends GmlLoadRouteTest {

	/**
	 * Issue #76　横浜の都市部で1st処理がエラー終了する #76
	 */
	@Test
	public void issue76() {
		OsmDom osm = testdo("./src/test/resources/53391550_bldg_6697_op.gml");
		try {
			for (ElementRelation relation : osm.getRelations()) {
				List<TagBean> tags = relation.getTagList();
				for (TagBean tag : tags) {
					if (tag.k.equals("height")) {
						checkValue(tag.v);
					}
					else if (tag.k.equals("ele")) {
						checkValue(tag.v);
					}
				}
			}
			for (WayModel way : osm.getWayMap().values()) {
				List<TagBean> tags = way.getTagList();
				for (TagBean tag : tags) {
					if (tag.k.equals("height")) {
						checkValue(tag.v);
					}
					else if (tag.k.equals("ele")) {
						checkValue(tag.v);
					}
				}
			}
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	void checkValue(String v) {
		BigDecimal bd = new BigDecimal(v);
		System.out.println(v);
		assertFalse(v.endsWith("."));
		if (bd.scale() > 2) {
			System.out.println("桁数オーバー: ["+ bd.scale() + "] "+ v);
			fail();
		}
		if (bd.scale() > 0) {
			if (v.endsWith("0")) {
				System.out.println("末尾に0 ["+ bd.scale() + "] : "+ v);
				fail();
			}
		}
	}
}
