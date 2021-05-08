package osm.surveyor.osm.api;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
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
import osm.surveyor.osm.ElementWay;
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

	/**
	 * Issue #13 「v1.2.4 「箱根町」のデータを変換できない」
	 * https://github.com/yuuhayashi/citygml-osm/issues/13
	 * map "A :建物" as A {
	 *  bldg:Building:id => bldg-cf368523-863d-4bf8-8931-3a9cf99e4e58
	 *  bldg:Building:stringAttribute[name="建物ID"] => 14382-bldg-10718
	 *  bldg:measuredHeight => 13.3
	 *  bldg:lod0FootPrint:Polygon:posList:height => 728.31
	 *  Envelope:srsName => http://www.opengis.net/def/crs/EPSG/0/6697
	 * }
	 */
	@Test
	public void test52396075() {
		test_do(Paths.get("src/test/resources","52396075_bldg_6697_op.gml"));
		
		OsmDom osm = new OsmDom();
		try {
			osm.load(Paths.get("52396075_bldg_6697_op.osm").toFile());

			assertThat(osm.ways, notNullValue());
			for (String id : osm.ways.keySet()) {
				ElementWay way = osm.ways.get(id);
				assertThat(way, notNullValue());
				assertThat(way.getTagValue("building"), is("yes"));
				assertThat(way.getTagValue("height"), is("13.3"));
				assertThat(way.getTagValue("ele"), is("728.31"));
				assertThat(way.getTagValue("source"), is("MLIT_PLATEAU; http://www.opengis.net/def/crs/EPSG/0/6697; 14382-bldg-10718"));
				assertThat(way.tags.size(), is(4));
			}
			assertThat(osm.ways.size(), is(1));
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
	
	/**
	 * Issue #12 「v1.2.4 単独の建物でもbuilding:part」
	 * https://github.com/yuuhayashi/citygml-osm/issues/12
	 */
	@Test
	public void test53375768() {
		test_do(Paths.get("src/test/resources","53375768_bldg_6697_op.gml"));
		
		OsmDom osm = new OsmDom();
		try {
			osm.load(Paths.get("53375768_bldg_6697_op.osm").toFile());

			assertThat(osm.ways, notNullValue());
			for (String id : osm.ways.keySet()) {
				ElementWay way = osm.ways.get(id);
				assertThat(way, notNullValue());

				if (way.getTagValue("source").endsWith("20209-bldg-69160")) {
					assertThat(way.getTagValue("building:part"), nullValue());
					assertThat(way.getTagValue("building"), notNullValue());
				}
				if (way.getTagValue("source").endsWith("20209-bldg-69158")) {
					assertThat(way.getTagValue("building:part"), nullValue());
					assertThat(way.getTagValue("building"), notNullValue());
				}
				if (way.getTagValue("source").endsWith("20209-bldg-69157")) {
					assertThat(way.getTagValue("building:part"), nullValue());
					assertThat(way.getTagValue("building"), notNullValue());
				}
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			fail(e.toString());
		}
	}
	
	@Test
	public void test53392547() {
		test_do(Paths.get("src/test/resources","53392547_bldg_6697_op2.gml"));
	}

	@Test
	public void test53392588() {
		test_do(Paths.get("src/test/resources","53392588_bldg_6697_op2.gml"));
	}

	@Test
	public void test53392589() {
		test_do(Paths.get("src/test/resources","53392589_bldg_6697_op2.gml"));
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
			if (filename.endsWith(".gml")) {
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
			if (filename.endsWith(".gml")) {
				try {
					filename = filename.substring(0, filename.length()-4);
			        
			        OsmDom osm = new OsmDom();
		            CitygmlFile target = new CitygmlFile(file, osm);
		            target.parse();
		            
		            // 各WAYのノードで、他のWAYと共有されたノードを探す
			    	// 接触しているBUILDINGのWAYをくっつけて"Relation:building"をつくる
			    	// Relation:multipolygon の MaxHeightを outline->Multipolygonへ設定する
			    	(new RelationMarge(osm)).relationMarge();
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
			if (filename.endsWith(".gml")) {
				try {
					filename = filename.substring(0, filename.length()-4);
			        
			        OsmDom osm = new OsmDom();
		            CitygmlFile target = new CitygmlFile(file, osm);
		            target.parse();
		            
			    	(new RelationMarge(osm)).relationMarge();
		            
		            // (3) メンバーが一つしかないRelation:building を削除する
			    	// (3) メンバーが一つしかないRelation:multipolygon と polygon:member を削除する
			    	(new BuildingGarbage(osm)).garbage();
			    	
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
			if (filename.endsWith(".gml")) {
				try {
					filename = filename.substring(0, filename.length()-4);
			        
			        OsmDom osm = new OsmDom();
		            CitygmlFile target = new CitygmlFile(file, osm);
		            target.parse();
		            
			    	(new RelationMarge(osm)).relationMarge();

			    	(new BuildingGarbage(osm)).garbage();

		            // (4) Relation:building->member:role=port のWay:outlineを作成する
		            // (4) Relation:multipolygon->outerにWay:outline
			    	(new OutlineFactory(osm)).relationOutline();
			    	
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
			if (filename.endsWith(".gml")) {
				try {
					filename = filename.substring(0, filename.length()-4);
			        
			        OsmDom osm = new OsmDom();

			        // (1) GMLファイルをパースする
		            CitygmlFile target = new CitygmlFile(file, osm);
		            target.parse();
		            
		            // (2) 各WAYのノードで、他のWAYと共有されたノードを探す
			    	// 接触しているBUILDINGのWAYをくっつけて"Relation:building"をつくる
			    	// Relation:multipolygon の MaxHeightを outline->Multipolygonへ設定する
			    	(new RelationMarge(osm)).relationMarge();
		            
		            // (3) メンバーが一つしかないRelation:building を削除する
			    	// (3) メンバーが一つしかないRelation:multipolygon と polygon:member を削除する
			    	(new BuildingGarbage(osm)).garbage();
		            
		            // (4) Relation:building->member:role=port のWay:outlineを作成する
		            // (4) Relation:multipolygon->outerにWay:outline
			    	(new OutlineFactory(osm)).relationOutline();
		            
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
