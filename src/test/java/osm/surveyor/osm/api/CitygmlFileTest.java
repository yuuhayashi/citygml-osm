package osm.surveyor.osm.api;

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
import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.OsmMargeWay;
import osm.surveyor.osm.marge.BuildingGarbage;
import osm.surveyor.osm.marge.OutlineFactory;
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
	public void test53392547() {
		test_do(Paths.get("src/test/resources","53392547_bldg_6697_op2.gml"));
	}

	@Test
	public void test53392588() {
		test_do(Paths.get("src/test/resources","53392588_bldg_6697_op2.gml"));
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
		            
		            // (3) メンバーが一つしかないRelation:building を削除する
			    	// (3) メンバーが一つしかないRelation:multipolygon と polygon:member を削除する
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
     * (4) OUTLINEを作成
     * Relation:building->member:role=port のWay:outlineを作成する
     * Relation:multipolygon->outerにWay:outline
     * @param a
     */
    public static void test4_doCreateOutline(Path a) {
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

			    	BuildingGarbage.garbage(osm);

		            // (4) Relation:building->member:role=port のWay:outlineを作成する
		            // (4) Relation:multipolygon->outerにWay:outline
			    	OutlineFactory.relationOutline(osm);
			    	
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
			        
			        OsmDom osm = new OsmDom();

			        // (1) GMLファイルをパースする
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
			    	OutlineFactory.relationOutline(osm);
		            
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
