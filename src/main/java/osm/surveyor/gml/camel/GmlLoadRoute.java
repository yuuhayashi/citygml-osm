package osm.surveyor.gml.camel;

import org.apache.camel.builder.RouteBuilder;

import osm.surveyor.osm.camel.BuildingGarbageProcessor;
import osm.surveyor.osm.camel.GerbageNodeProcessor;
import osm.surveyor.osm.camel.GerbageWayProcessor;
import osm.surveyor.osm.camel.OsmExportProcessor;
import osm.surveyor.osm.camel.OsmMargeWayProcessor;
import osm.surveyor.osm.camel.OutlineFactoryProcessor;
import osm.surveyor.osm.camel.RelationMargeProcessor;

public class GmlLoadRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		onException(Exception.class)
        .handled(false)
        .log("Error")
        .setBody().constant("なにかエラーが発生")
        .log("Error: ${body}")
        ;
		
		// (1) GMLファイルをパースする
		from("direct:gml-file-read")
		.process(new GmlFileReadProcessor())	// GMLファイルをパース
		.process(new GerbageWayProcessor())		// RELATIONに所属していないWAYを削除する
		.process(new GerbageNodeProcessor())	// WAYに所属しないNODEを削除する
        .to("direct:inRelationMarge")
        ;
		
        // (2) 各WAYのノードで、他のWAYと共有されたノードを探す
    	// 接触しているBUILDINGのWAYをくっつけて"Relation:building"をつくる
    	// Relation:multipolygon の MaxHeightを outline->Multipolygonへ設定する
		from("direct:inRelationMarge")
		.process(new RelationMargeProcessor())
        .to("direct:inBuildingGarbage")
        ;
		
		// (3) メンバーが一つしかないRelation:building を削除する
		// (3) メンバーが一つしかないRelation:multipolygon と polygon:member を削除する
		from("direct:inBuildingGarbage")
		.process(new BuildingGarbageProcessor())
        .to("direct:inOutlineFactory")
        ;
		
        // (4) Relation:building->member:role=port のWay:outlineを作成する
        // (4) Relation:multipolygon->outerにWay:outline
		from("direct:inOutlineFactory")
		.process(new OutlineFactoryProcessor())
        .to("direct:inOsmMargeWay")
        ;
		
        // (5) "outline"と"part"が重複しているPART を削除する
		from("direct:inOsmMargeWay")
		.process(new OsmMargeWayProcessor())
		.process(new GerbageWayProcessor())		// RELATIONに所属していないWAYを削除する
		.process(new RelationProcessor())		// RELATIONの"name"を決定する。#76:オブジェクトが存在しないメンバーをRELATIONから削除する
        .to("direct:osm-export")
        ;
		
		// (6) OSMファイルに出力する
		from("direct:osm-export")
		.process(new GmlFileToOsmProcessor())
		.process(new OsmExportProcessor())
		//.to("stream:out")
        ;
	}

}
