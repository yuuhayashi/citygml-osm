package osm.surveyor.osm.api;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import osm.surveyor.citygml.CitygmlFile;
import osm.surveyor.osm.ElementMember;
import osm.surveyor.osm.ElementRelation;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.OsmMargeWay;
import osm.surveyor.osm.marge.BuildingGarbage;
import osm.surveyor.osm.marge.RelationMarge;

public class CitygmlFileTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSample_e() {
		test(Paths.get(".","sample_e_bldg_6697_op2.gml"));
        OsmDom osm = new OsmDom();
        try {
			osm.load(Paths.get("sample_e_bldg_6697_op2.osm").toFile());
			for (String id : osm.relations.keySet()) {
				ElementRelation relation = osm.relations.get(id);
				assertThat(relation, notNullValue());
				String type = relation.getTagValue("type");
				if (type.equals("building")) {
					/*
					 	map "building : Relation" as building {
						 type => building
						 building => yes
						 height => 17.199
						 name => "大田病院"
						 source => MLIT_PLATEAU;\n http://www.opengis.net/def/crs/EPSG/0/6697
						}
					 */
					assertThat(relation.getTagValue("type"), is("building"));
					assertThat(relation.getTagValue("building"), is("yes"));
					assertThat(relation.getTagValue("height"), startsWith("17.199"));
					assertThat(relation.getTagValue("name"), startsWith("大田病院"));
					assertThat(relation.getTagValue("addr:full"), is("東京都大田区大森東四丁目"));
					assertThat(relation.getTagValue("addr:ref"), is("13111007004"));
					assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					assertThat(relation.tags.size(), is(7));
					int outlineCnt = 0;
					int partCnt = 0;
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outline")) {
							outlineCnt++;
							assertThat(mem.type, is("relation"));
							ElementRelation multiporygon = osm.relations.get(Long.toString(mem.ref));
							assertThat(multiporygon, notNullValue());
						}
						if (mem.role.equals("part")) {
							partCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertThat(way, notNullValue());
							if (way.getTagValue("source").endsWith("; 13111-bldg-56522")) {
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-56522"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区大森東四丁目"));
								assertThat(way.getTagValue("addr:ref"), is("13111007004"));
								assertThat(way.getTagValue("height"), is("17.199"));
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.getTagValue("name"), startsWith("大田病院"));
								assertThat(way.tags.size(), is(6));
							}
							else if (way.getTagValue("source").endsWith("; 13111-bldg-55333")) {
								assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 13111-bldg-55333"));
								assertThat(way.getTagValue("addr:full"), is("東京都大田区大森東四丁目"));
								assertThat(way.getTagValue("addr:ref"), is("13111007004"));
								assertThat(way.getTagValue("height"), is("5.5360000000000005"));
								assertThat(way.getTagValue("building:part"), is("yes"));
								assertThat(way.tags.size(), is(5));
							}
						}
					}
					assertThat(relation.members.size(), is(3));
					assertThat(outlineCnt, is(1));
					assertThat(partCnt, is(2));
				}
				else if (type.equals("multipolygon")) {
					/*
						map "multipolygon :Relation" as multipolygon {
						 type => multipolygon
						 building => yes
						 height => 32.852000000000004
						 source => MLIT_PLATEAU;\n http://www.opengis.net/def/crs/EPSG/0/6697
						}
					 */
					assertThat(relation.getTagValue("type"), is("multipolygon"));
					assertThat(relation.getTagValue("building"), is("yes"));
					assertThat(relation.getTagValue("height"), is("17.199"));
					assertThat(relation.getTagValue("name"), is("大田病院"));
					assertThat(relation.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
					assertThat(relation.members.size(), is(3));
					int outerCnt = 0;
					int innerCnt = 0;
					for (ElementMember mem : relation.members) {
						if (mem.role.equals("outer")) {
							outerCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertThat(way, notNullValue());
							assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
							assertThat(way.tags.size(), is(1));
						}
						if (mem.role.equals("inner")) {
							innerCnt++;
							assertThat(mem.type, is("way"));
							ElementWay way = osm.ways.get(Long.toString(mem.ref));
							assertThat(way, notNullValue());
							assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697"));
							assertThat(way.tags.size(), is(1));
						}
					}
					assertThat(outerCnt, is(1));
					assertThat(innerCnt, is(2));
				}
				else {
					assertThat(type, is("building"));
				}
			}
			assertThat(osm.relations.size(), is(2));
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void test53392547() {
		test(Paths.get(".","53392547_bldg_6697_op2.gml"));
	}

	@Test
	public void test53392588() {
		test(Paths.get(".","53392588_bldg_6697_op2.gml"));
	}

	public static void test(Path a) {
		CitygmlFile.doConvert(a);
	}


	/**
     * (1) メイン処理
     * 指定されたGMLファイルをOSMファイルに変換する
     * @param a
     */
    public static void test_doParse(Path a) {
		if (Files.isRegularFile(a)) {
			File file = a.toFile();
			String filename = file.getName();
			System.out.println(filename);
			if (filename.endsWith("_op2.gml")) {
				try {
					filename = filename.substring(0, filename.length()-4);
			        
			        OsmDom osm = new OsmDom();
		            CitygmlFile target = new CitygmlFile(file, osm);
		            target.parse();
			    	osm.export(new File(filename + "_1.osm"));
				} catch (Exception e) {
					e.printStackTrace();
					fail(e.toString());
				}
			}
		}
    }

    /**
     * (2) メイン処理
     * 接触しているBUILDINGのWAYをくっつけて"Relation:building"をつくる
     * @param a
     */
    public static void test_doRelationMarge(Path a) {
		if (Files.isRegularFile(a)) {
			File file = a.toFile();
			String filename = file.getName();
			System.out.println(filename);
			if (filename.endsWith("_op2.gml")) {
				try {
					filename = filename.substring(0, filename.length()-4);
			        
			        OsmDom osm = new OsmDom();
		            CitygmlFile target = new CitygmlFile(file, osm);
		            target.parse();
		            
		            // 各WAYのノードで、他のWAYと共有されたノードを探す
			    	// 接触しているBUILDINGのWAYをくっつけて"Relation:building"をつくる
			    	// Relation:multipolygon の MaxHeightを outline->Multipolygonへ設定する
			    	RelationMarge.relationMarge(osm);
			    	osm.export(new File(filename + "_2.osm"));
			    	
				} catch (Exception e) {
					e.printStackTrace();
					fail(e.toString());
				}
			}
		}
    }

    /**
     * (3) メイン処理
     * メンバーが一つしかないRelation:building を削除する
     * メンバーが一つしかないRelation:multipolygon と polygon:member を削除する
     * @param a
     */
    public static void test_doRemoveSinglePart(Path a) {
		if (Files.isRegularFile(a)) {
			File file = a.toFile();
			String filename = file.getName();
			System.out.println(filename);
			if (filename.endsWith("_op2.gml")) {
				try {
					filename = filename.substring(0, filename.length()-4);
			        
			        OsmDom osm = new OsmDom();
		            CitygmlFile target = new CitygmlFile(file, osm);
		            target.parse();
		            
			    	RelationMarge.relationMarge(osm);
		            
		            // メンバーが一つしかないRelation:building を削除する
			    	// メンバーが一つしかないRelation:multipolygon と polygon:member を削除する
			    	BuildingGarbage.garbage(osm);
			    	osm.export(new File(filename + "_3.osm"));
		            
				} catch (Exception e) {
					e.printStackTrace();
					fail(e.toString());
				}
			}
		}
    }

    /**
     * (4) メイン処理
     * Relation:building->member:role=port のWay:outlineを作成する
     * Relation:multipolygon->outerにWay:outline
     * @param a
     */
    public static void test_doCreateOutline(Path a) {
		if (Files.isRegularFile(a)) {
			File file = a.toFile();
			String filename = file.getName();
			System.out.println(filename);
			if (filename.endsWith("_op2.gml")) {
				try {
					filename = filename.substring(0, filename.length()-4);
			        
			        OsmDom osm = new OsmDom();
		            CitygmlFile target = new CitygmlFile(file, osm);
		            target.parse();
		            
			    	RelationMarge.relationMarge(osm);

		            // (3) メンバーが一つしかないRelation:building を削除する
			    	// (3) メンバーが一つしかないRelation:multipolygon と polygon:member を削除する
			    	BuildingGarbage.garbage(osm);

		            // Relation:building->member:role=port のWay:outlineを作成する
		            // Relation:multipolygon->outerにWay:outline
		            osm.relationOutline();
			    	osm.export(new File(filename + "_4.osm"));
			    	
				} catch (Exception e) {
					e.printStackTrace();
					fail(e.toString());
				}
			}
		}
    }

    /**
     * メイン処理
     * 指定されたGMLファイルをOSMファイルに変換する
     * @param a
     */
    public static void test_do(Path a) {
		if (Files.isRegularFile(a)) {
			File file = a.toFile();
			String filename = file.getName();
			
			System.out.println(filename);
			if (filename.endsWith("_op2.gml")) {
				try {
					filename = filename.substring(0, filename.length()-4);
			        
					// (1) パース
			        OsmDom osm = new OsmDom();
		            CitygmlFile target = new CitygmlFile(file, osm);
		            target.parse();
		            
		            // (2) 各WAYのノードで、他のWAYと共有されたノードを探す
			    	// 接触しているBUILDINGのWAYをくっつけて"Relation:building"をつくる
			    	// Relation:multipolygon の MaxHeightを outline->Multipolygonへ設定する
			    	RelationMarge.relationMarge(osm);
		            
		            // (3) メンバーが一つしかないRelation:building を削除する
			    	// (3) メンバーが一つしかないRelation:multipolygon と polygon:member を削除する
			    	BuildingGarbage.garbage(osm);
		            
		            // (4) Relation:building->member:role=port のWay:outlineを作成する
		            // (4) Relation:multipolygon->outerにWay:outline
		            osm.relationOutline();
		            
		            // Relation:multipolygon の MaxHeightを outline->Multipolygonへ設定する
		            //OsmMargeWay.removeHeightFromOuter(osm);

		            // (5) "outline"と"part"が重複しているPART を削除する
		            OsmMargeWay.partGabegi(osm);
		            
		            // ファイルへエクスポートする
			    	osm.export(new File(filename + ".osm"));
				} catch (Exception e) {
					e.printStackTrace();
					fail(e.toString());
				}
			}
		}
    }
}
