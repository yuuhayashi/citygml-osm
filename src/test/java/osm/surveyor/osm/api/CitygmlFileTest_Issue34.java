package osm.surveyor.osm.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.nio.file.Paths;

import org.junit.Test;

import osm.surveyor.osm.ElementMember;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

public class CitygmlFileTest_Issue34 {

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
	public void test50303525() {
		CitygmlFileTest.test_do(Paths.get("src/test/resources","50303525_bldg_6697_op.gml"));
		
		OsmDom osm = new OsmDom();
		try {
			osm.parse(Paths.get("50303525_bldg_6697_op.osm").toFile());
			assertNotNull(osm.relations);
			for (String relationid : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(relationid);
				for (ElementMember mem : relation.members) {
					if (mem.role.equals("inner")) {
						assertEquals("way", mem.type);
						ElementWay inner = osm.ways.get(mem.ref);
						assertNotNull(inner);
					}
				}
			}
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void test50303525_2() {
		CitygmlFileTest.test_doRelationMarge(Paths.get("src/test/resources","50303525_bldg_6697_op.gml"));
		
		OsmDom osm = new OsmDom();
		try {
			osm.parse(Paths.get("50303525_bldg_6697_op_2.osm").toFile());
			assertNotNull(osm.relations);
			for (String relationid : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(relationid);
				for (ElementMember mem : relation.members) {
					if (mem.role.equals("inner")) {
						assertEquals("way", mem.type);
						ElementWay inner = osm.ways.get(mem.ref);
						assertNotNull(inner);
					}
				}
			}
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void test50303525_3() {
		CitygmlFileTest.test_doRemoveSinglePart(Paths.get("src/test/resources","50303525_bldg_6697_op.gml"));
		
		OsmDom osm = new OsmDom();
		try {
			osm.parse(Paths.get("50303525_bldg_6697_op_3.osm").toFile());
			assertNotNull(osm.relations);
			for (String relationid : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(relationid);
				for (ElementMember mem : relation.members) {
					if (mem.role.equals("inner")) {
						assertEquals("way", mem.type);
						ElementWay inner = osm.ways.get(mem.ref);
						assertNotNull(inner);
					}
				}
			}
		} catch (Exception e) {
			fail(e.toString());
		}
	}
}
