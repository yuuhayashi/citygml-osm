package osm.surveyor.osm.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.nio.file.Paths;
import java.util.StringTokenizer;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import osm.surveyor.DetailTests;
import osm.surveyor.osm.ElementMember;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.RelationMap;

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
			
			// Issue #37
			testIssue37(osm, osm.relations);
			
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				assertEquals("building", relation.getTagValue("type"));
				
				// Issue #36
				testIssue36(osm, relation);
				
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
			System.out.println("relations.size(): "+ osm.relations.size());
			assertTrue(osm.relations.size() > 5);
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
	
	/**
	 * 複雑系の複合ビルが一つのOUTLINEにまとまらない #37
	 * https://github.com/yuuhayashi/citygml-osm/issues/37
	 * 
	 * @param osm
	 * @param relation
	 */
	void testIssue37(OsmDom osm, RelationMap map) {
		int i = 0;
		for (String key : map.keySet()) {
			ElementRelation relation = map.get(key);
			for (ElementMember mem : relation.members) {
				if (mem.role.equals("part")) {
					assertEquals("way", mem.type);
					ElementWay way = osm.ways.get(Long.toString(mem.ref));
					
					// "40205-bldg-81197"をメンバに持つリレーションは 20個以上の建物パーツを持つべき
					if (way.getTagValue("source").endsWith("40205-bldg-81197")) {
						System.out.println("members: "+ relation.members.size());
						assertTrue(relation.members.size() >= 20);
						i++;
					}
				}
			}
		}

		assertEquals(1, i);
	}
	
	/**
	 * Issue #36
	 * eleの有効桁数は小数点以下２桁までであること
	 * @param relation
	 */
	void testIssue36(OsmDom osm, ElementRelation relation) {
		check(relation.getTagValue("ele"));
		for (ElementMember mem : relation.members) {
			if (mem.role.equals("part")) {
				assertEquals("way", mem.type);
				ElementWay way = osm.ways.get(Long.toString(mem.ref));
				check(way.getTagValue("ele"));
			}
		}
	}

	/**
	 * strの有効桁数は小数点以下２桁までであること
	 * @param str
	 */
	void check(String str) {
		if (str != null) {
			StringTokenizer st = new StringTokenizer(str, ".");
			int cnt = 0;
			while(st.hasMoreTokens()) {
				String token = st.nextToken();
				cnt++;
				if (cnt == 2) {
					assertTrue(token.length() <= 2);
				}
			}
		}
	}

	/**
	 * `mvn test -Dtest=CitygmlFileTest_A#testSample_a2_margePart`
	 * 
	 */
	@Test
	@Category(DetailTests.class)
	public void testSample_a2_margePart() {
		CitygmlFileTest.test_doRelationMarge(Paths.get("src/test/resources","Issue28_50303525_op.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.parse(Paths.get("Issue28_50303525_op_2.osm").toFile());
			int i = 0;

			assertNotNull(osm.relations);
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertNotNull(relation);
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("part")) {
							assertEquals("way", mem.type);
							ElementWay way = osm.ways.get(Long.toString(mem.ref));

							// "40205-bldg-81197"をメンバに持つリレーションは 20個以上の建物パーツを持つべき
							if (way.getTagValue("source").endsWith("40205-bldg-81197")) {
								System.out.println("members: "+ relation.members.size());
								assertTrue(relation.members.size() >= 20);
								i++;
							}
						}
					}
				}
			}
			assertEquals(1, i);
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
}
