package osm.surveyor.osm.api;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import osm.surveyor.osm.BoundsBean;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.TagBean;
import osm.surveyor.osm.way.WayModel;

/**
 * 山口県周南市 "51310664_bldg_6697_op.gml"
 * 
 * v2.3.5
 *   1st "51310664_bldg_6697_op.gml"で、なにかエラーが発生
 * v2.3.6
 *   1st "51311624_bldg_6697_op.gml"で、なにかエラーが発生
 *   
 * @author hayashi
 */
public class CitygmlFileTest_Issue139 extends GmlLoadRouteTest {

	/**
	 * Issue #139
	 */
	@Test
	public void issue139_51311624() {
		OsmDom dom = testdo("./src/test/resources/51311624_bldg_Issue139.gml");
		try {
	        assertNotNull(dom);
	        BoundsBean bound = dom.getBounds();
	        assertNotNull(bound);
	        
	        List<WayModel> wayList = dom.getWayList();
			assertNotNull(wayList);
			assertNotEquals(0, wayList.size());
	        assertEquals(109, wayList.size());
	        assertEquals(0, dom.getRelations().size());
	        
			for (ElementRelation relation : dom.getRelations()) {
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
			for (WayModel way : dom.getWayList()) {
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
			
	        
	        // OSMファイルに書き出す
			File domfile = new File("OsmDom.osm");
	        dom.export(domfile);
	        assertTrue(domfile.exists());
	        
	        // TODO OSMファイルをパースする
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void issue139_51312664() {
		OsmDom dom = testdo("./src/test/resources/51312664_bldg_Issue139.gml");
		try {
	        assertNotNull(dom);
	        BoundsBean bound = dom.getBounds();
	        assertNotNull(bound);
	        
	        List<WayModel> wayList = dom.getWayList();
			assertNotNull(wayList);
			assertNotEquals(0, wayList.size());
	        assertEquals(192, wayList.size());
	        assertEquals(9, dom.getRelations().size());
	        
			for (ElementRelation relation : dom.getRelations()) {
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
			for (WayModel way : dom.getWayList()) {
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
			
	        
	        // OSMファイルに書き出す
			File domfile = new File("OsmDom.osm");
	        dom.export(domfile);
	        assertTrue(domfile.exists());
	        
	        // TODO OSMファイルをパースする
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
