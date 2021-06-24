package osm.surveyor.osm.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.nio.file.Paths;

import org.junit.Test;

import osm.surveyor.osm.ElementMember;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;

/**
 * 
 * v1.3.0 リレーションでbldg:usageが未反映のものがある。 #35
V1.3.0で試して見ました。
最初の例の(3)、(4)とも、(目視で)一番大きなbuilding:partがindustrialなのですが、リレーション自体はまだbuilding=yesでした。。

	v1.2.9 リレーションでbldg:usageが反映されないものがある。 #28
	
	福岡県飯塚市、50303525_bldg_6697_opですが、
	建物リレーションでbuilding:partにindustrialとyesが混在している時に
	relation自体がbuilding=industrialになったり、building=yesになったりしているようです。
	
	なぜでしょうね？
	
	例えば下記のものです。
	(1)
	relation: building=industrial
	outline: building=industrial
	40205-bldg-80498:building:part=industrial
	40205-bldg-81341:building:part=yes
	JOSMのリレーションの編集ウインドウで見ると、outlineと2つのパートは接続している
	
	(2)
	relation: building=industrial
	outline: building=industrial
	40205-bldg-80414 :building:part=industrial
	40205-bldg-81351: building:part=yes
	リレーションの編集ウインドウで見ると、outlineとindustrialのpartが接続している。
	
	(3)
	relation: building=yes
	outline: building=yes
	40205-bldg-80732: building:part=industrial
	40205-bldg-81307: building:part=yes
	リレーションの編集ウインドウで見ると、outlineと2つのパートは接続していない
	
	(4)
	relation: building=yes
	outline: building=yes
	40205-bldg-80317: building:part=industrial
	40205-bldg-81329: building:part=yes
	40205-bldg-81375: building:part=yes
	リレーションの編集ウインドウで見ると、outlineと3つのパートは各々接続していない

 */
public class CitygmlFileTest_Issue28 {

	@Test
	public void test50303525() {
		CitygmlFileTest.test_do(Paths.get("src/test/resources","Issue28_50303525_op.gml"));
		
		OsmDom osm = new OsmDom();
		try {
			osm.parse(Paths.get("Issue28_50303525_op.osm").toFile());
			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				assertEquals("building", relation.getTagValue("type"));
				
				for (ElementMember mem : relation.members) {
					if (mem.role.equals("part")) {
						assertEquals("way", mem.type);
						ElementWay way = osm.ways.get(Long.toString(mem.ref));
						
						// (1) "40205-bldg-80498"をメンバに持つリレーションは building=industrial であるべき、
						if (way.getTagValue("source").endsWith("40205-bldg-80498")) {
							assertEquals("industrial", relation.getTagValue("building"));
						}
						
						// (2) "40205-bldg-80414"をメンバに持つリレーションは building=industrial であるべき、
						if (way.getTagValue("source").endsWith("40205-bldg-80414")) {
							assertEquals("industrial", relation.getTagValue("building"));
						}
						
						// (3) "40205-bldg-80732"をメンバに持つリレーションは building=industrial であるべき、
						if (way.getTagValue("source").endsWith("40205-bldg-80732")) {
							assertEquals("industrial", relation.getTagValue("building"));
						}
						
						// (４) "40205-bldg-80317"をメンバに持つリレーションは building=industrial であるべき、
						if (way.getTagValue("source").endsWith("40205-bldg-80317")) {
							assertEquals("industrial", relation.getTagValue("building"));
						}
					}
				}
			}
			assertTrue(osm.relations.size() > 5);
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

}
