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
 * 山口県周南市 "51311568_bldg_6697_op.gml"
 * 
 * @author hayashi
 */
public class CitygmlFileTest_Issue137 extends GmlLoadRouteTest {

	/**
	 * Issue #137
	 */
	@Test
	public void issue137() {
		OsmDom dom = testdo("./src/test/resources/51311568_bldg_6697_op.gml");
		try {
	        assertNotNull(dom);
	        BoundsBean bound = dom.getBounds();
	        assertEquals("34.141432923094364", bound.maxlat);
	        assertEquals("131.73367235934688", bound.maxlon);
	        assertEquals("131.72495547089684", bound.minlon);
	        assertEquals("34.13499400739385", bound.minlat);
	        
	        List<WayModel> wayList = dom.getWayList();
			assertNotNull(wayList);
			assertNotEquals(0, wayList.size());
	        assertTrue(225 <= wayList.size());
	        
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
