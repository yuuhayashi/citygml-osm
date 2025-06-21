package osm.surveyor.osm.api;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.TagBean;
import osm.surveyor.osm.way.WayModel;

/**
 * 埼玉県新座市 "53395404_bldg_6697_op.gml"
 * 
 * @author hayashi
 */
public class CitygmlFileTest_53395404 extends GmlLoadRouteTest {

	/**
	 * Issue #49
建物がマルチポリゴンリレーションとして表現される際に、リレーションに対して適用される height の値が、例えば 22.560000000000002 など、小数点以下15位まで記録されています。

リレーションに所属するメンバーのウェイ・オブジェクトに対しては、 22.5 など、小数点1位までの値が入力されます。

この場合、リレーションに対する height も、小数点以下1位まででよいかと思われます。

例えば、埼玉県新座市の 53395404_bldg_6697_op.gml に存在する、建物ID 11230-bldg-1190 (<gen:value>11230-bldg-1190</gen:value>) が所属するリレーションなどで発生します。
	 */
	@Test
	public void issue49() {
		OsmDom osm = testdo("./src/test/resources/53395404_bldg_6697_op.gml");
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
			for (WayModel way : osm.getWays()) {
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
