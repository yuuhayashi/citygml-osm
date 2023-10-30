package osm.surveyor.osm.api;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;

import osm.surveyor.citygml.CitygmlFile;
import osm.surveyor.gml.camel.GmlLoadRoute;
import osm.surveyor.osm.OsmDom;
import osm.surveyor.osm.marge.BuildingGarbage;
import osm.surveyor.osm.marge.OutlineFactory;
import osm.surveyor.osm.marge.RelationMarge;

public abstract class GmlLoadRouteTest extends CamelTestSupport {
	
    @EndpointInject("mock:result")
    protected MockEndpoint resultEndpoint;

    @Produce("direct:start")
    protected ProducerTemplate template;
    
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new GmlLoadRoute();
    }
    
    /**
     * メイン処理
    * 指定されたGMLファイルをOSMファイルに変換する
    * @param source
    * @return
    */
    public OsmDom testdo(String source) {
		Exchange exchange = createExchangeWithBody("");
		exchange.setProperty(Exchange.FILE_NAME, source);
		exchange.getIn().setBody(new File(source));
	
		// (1) GMLファイルをパースする
		// (2) 各WAYのノードで、他のWAYと共有されたノードを探す
		// (3) メンバーが一つしかないRelation:building を削除する
		// (3) メンバーが一つしかないRelation:multipolygon と polygon:member を削除する
		// (4) Relation:building->member:role=port のWay:outlineを作成する
		// (4) Relation:multipolygon->outerにWay:outline
		// (5) "outline"と"part"が重複しているPART を削除する
		//template.sendBody("direct:gml-file-read", exchange);
		//List<Exchange> list = resultEndpoint.getReceivedExchanges();
		//return list.get(0).getIn().getBody(OsmDom.class);
		
		template.send("direct:gml-file-read", exchange);
		return exchange.getIn().getBody(OsmDom.class);
	}

    /**
      * (2) メイン処理
     * 接触しているBUILDINGのWAYをくっつけて"Relation:building"をつくる
     * @param a
     */
    public static void test2_doRelationMarge(Path a) {
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
		            
		            // RELATIONに所属していないWAYを削除する
		            osm.gerbageWay();
		            
		            // WAYに所属しないNODEを削除する
		            osm.gerbageNode();
		            
		            // (2) 各WAYのノードで、他のWAYと共有されたノードを探す
			    	// 接触しているBUILDINGのWAYをくっつけて"Relation:building"をつくる
			    	// Relation:multipolygon の MaxHeightを outline->Multipolygonへ設定する
			    	while((new RelationMarge(osm)).relationMarge());
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
			        // (1) GMLファイルをパースする
			        CitygmlFile target = new CitygmlFile(file, osm);
		            target.parse();
		            
		            // RELATIONに所属していないWAYを削除する
		            osm.gerbageWay();
		            
		            // WAYに所属しないNODEを削除する
		            osm.gerbageNode();
		            
		            // (2) 各WAYのノードで、他のWAYと共有されたノードを探す
		            while((new RelationMarge(osm)).relationMarge());
		            
					// (3) メンバーが一つしかないRelation:building を削除する
					// (3) メンバーが一つしかないRelation:multipolygon と polygon:member を削除する
			    	(new BuildingGarbage(osm)).garbage();
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
		            
			        // (1) GMLファイルをパースする
			        CitygmlFile target = new CitygmlFile(file, osm);
		            target.parse();
		            
		            // RELATIONに所属していないWAYを削除する
		            osm.gerbageWay();
		            
		            // WAYに所属しないNODEを削除する
		            osm.gerbageNode();
		            
		            // (2) 各WAYのノードで、他のWAYと共有されたノードを探す
		            while((new RelationMarge(osm)).relationMarge());

					// (3) メンバーが一つしかないRelation:building を削除する
					// (3) メンバーが一つしかないRelation:multipolygon と polygon:member を削除する
			    	(new BuildingGarbage(osm)).garbage();

		            // (4) Relation:building->member:role=port のWay:outlineを作成する
		            // (4) Relation:multipolygon->outerにWay:outline
			    	(new OutlineFactory(osm)).relationOutline();
				} catch (Exception e) {
					e.printStackTrace();
					fail(e.toString());
				}
			}
		}
    }

}
