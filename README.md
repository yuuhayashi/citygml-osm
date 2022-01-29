# citygml-osm

PLATEAUの「3D都市モデル」の"GMLファイル"から、OpenStreetMapへのJOSM用のOSMデータを生成します。

![Screenshot.png](https://yuuhayashi.github.io/citygml-osm/doc/Screenshot.png)

- PLATEAUの「3D都市モデル」の"GMLファイル"から、OpenStreetMap形式に変換します。
  - 複合建物は「[リレーション:building](https://wiki.openstreetmap.org/wiki/JA:%E5%9F%BA%E6%9C%AC%E7%9A%84%E3%81%AA3D%E3%81%AE%E5%BB%BA%E7%89%A9)」へ変換します。
  - 中空（Inner）部分がある建物も「[リレーション:マルチポリゴン](https://wiki.openstreetmap.org/wiki/JA:Relation:multipolygon)」に変換します。
    - 変換方式は"[リレーション / マルチポリゴン構成](doc/building/Relations.md)"を参照してください。
- 既存データとの調整も行います。
  - OpenStreetMapから現在の建物データ(既存データ)をダウンロードして、変換データと重複チェックを自動的に行います。
  - アップデートするとデータの欠損が発生するような既存データ(リレーションのメンバーやエントランスなどのノードを持つもの等)と重複するPOIはアップデート対象から自動的に除外します。
  - 既存データのタグはそのまま継承されます。
  - 既存データのアップデートを行うので既存データの履歴も継承されます。
  - 複数の既存データと重複する場合は、重複面積が最大の既存データをアップデートします。
- JOSM上で変換後の状況を確認することができます。
  - 既存データと重複するPOI場合は、`MLIT_PLATEAU:fixme=*`が付加され、重複しないPOIと区別できます。
  - 他の建物に統合されて削除されてしまう予定の既存建物もJOSM上で確認可能です。
- アップデートを行いたくないPOIを手動で指定することができるようにしています。
- アップロード前にJOSM上で最終的な目視チェックを行うことができる様にしています。


## 生成物の例

- 変換元ファイル: [53392547_bldg_6697_op2.gml](https://yuuhayashi.github.io/citygml-osm/src/test/resources/53392547_bldg_6697_op2.gml) ... 変換元のCityGMLファイル

- 第一段階生成ファイル: [53392547_bldg_6697_op2.osm](https://yuuhayashi.github.io/citygml-osm/src/test/resources/53392547_bldg_6697_op2.osm) ... CityGMLファイルからOSMファイルへ変換

- 第二段階生成ファイル: [53392547_bldg_6697_op2.org.osm](https://yuuhayashi.github.io/citygml-osm/src/test/resources/53392547_bldg_6697_op2.org.osm) ... 既存のOSMデータ

- 第三段階生成ファイル: [53392547_bldg_6697_op2.mrg.osm](https://yuuhayashi.github.io/citygml-osm/src/test/resources/53392547_bldg_6697_op2.mrg.osm) ... 既存のOSMデータとマージしたOSMファイル


# Release

- 2022-01-27 [v1.4.2-SNAPSHOT](https://github.com/yuuhayashi/citygml-osm/releases/tag/v1.4.2)
  - [Fixed #60](https://github.com/yuuhayashi/citygml-osm/issues/60) 取り壊された建物に重複するデータは対象外にする
  - [Fixed #56](https://github.com/yuuhayashi/citygml-osm/issues/56) `source=*`に`[S|s]urvey`、が含まれる場合は、`source=survey`に変換する
  - [Fixed #63](https://github.com/yuuhayashi/citygml-osm/issues/63) 既存building との「ele=*」タグの値の扱い
  - [Fixed #22](https://github.com/yuuhayashi/citygml-osm/issues/22) 既存building との「building=*」タグの値の扱い
  - [Fixed #62](https://github.com/yuuhayashi/citygml-osm/issues/62) 既存building との「building:levels=*」タグの値の扱い

- 2022-01-23 [v1.4.1](https://github.com/yuuhayashi/citygml-osm/releases/tag/v1.4.1)<br/>[Fixed #42](https://github.com/yuuhayashi/citygml-osm/issues/42) 'fixme'による手動選別<br/>[Fixed #24](https://github.com/yuuhayashi/citygml-osm/issues/24) 削除される既存POIがJOSMで確認できない<br/>[Fixed #22](https://github.com/yuuhayashi/citygml-osm/issues/22) 既存building との「building=*」タグの値の扱い
  - [v1.4.1](https://github.com/yuuhayashi/citygml-osm/releases/tag/v1.4.1)より、第三段階生成物(`*.mrg.osm`)の出力形式を変更しています。（`*.mrg.osm`をそのままOSMへアップロードできなくなりました）

- 2022-01-16 [v1.3.7](https://github.com/yuuhayashi/citygml-osm/releases/tag/v1.3.7)<br/>[Fixed #58](https://github.com/yuuhayashi/citygml-osm/issues/58) Windows 10 に対応<br/>[Fixed #22](https://github.com/yuuhayashi/citygml-osm/issues/22) 既存building との「building=*」タグの値の扱い<br/>[Fixed #21](https://github.com/yuuhayashi/citygml-osm/issues/21) 既存'relation:building'には上書きしないようにする

- 2022-01-15 [リリース v1.3.6](https://github.com/yuuhayashi/citygml-osm/releases/tag/v1.3.6)<br/>第三段階：既存データとの統合機能<br/>第二段階：既存データのダウンロード機能<br/>[Fixed #54](https://github.com/yuuhayashi/citygml-osm/issues/54) 第二段階が起動できません<br/>[Fixed #33](https://github.com/yuuhayashi/citygml-osm/issues/33) v1.3.0 第2段階の変換でjavaのエラーが発生<br/>[Fixed #53](https://github.com/yuuhayashi/citygml-osm/issues/53) 既存データのダウンロード<br/>[Fixed #40](https://github.com/yuuhayashi/citygml-osm/issues/40) バリデーション警告「重なった建物」が発生する

- 2021-12-30 [リリース v1.3.5](https://github.com/yuuhayashi/citygml-osm/releases/tag/v1.3.5)<br/>[Issue #51](https://github.com/yuuhayashi/citygml-osm/issues/51) マルチポリゴン建物への name タグ付与<br/>[Issue #49](https://github.com/yuuhayashi/citygml-osm/issues/49) Multipolygonリレーションに対するheightの値

- 2021-11-28 リリース v1.3.4 / [citygml-osm v1.3.4](https://github.com/yuuhayashi/citygml-osm/releases/tag/v1.3.4)<br/>[Issue #43](https://github.com/yuuhayashi/citygml-osm/issues/43) `building:name`タグを`name`タグに変更する<br/>[Issue #44](https://github.com/yuuhayashi/citygml-osm/issues/44) オブジェクトへのsourceタグ付与を削除したい<br/>[Issue #45](https://github.com/yuuhayashi/citygml-osm/issues/45) オブジェクトへの建物IDの格納について<br/>[Issue #46](https://github.com/yuuhayashi/citygml-osm/issues/46) 住所コードデータの扱いについて<br/>[Issue #48](https://github.com/yuuhayashi/citygml-osm/issues/48) conversion.jsonを"MULTI_PLATEAU"に合わせる

- 2021-07-29 リリース v1.3.1 / [citygml-osm v1.3.1](https://github.com/yuuhayashi/citygml-osm/releases/tag/v1.3.1)<br/>第一段階を完成

- 2021-05-29 リリース v1.3.0 / [citygml-osm v1.3.0](https://github.com/yuuhayashi/citygml-osm/releases/tag/v1.3.0)<br/>複合ビルの場合の「用途」の扱いについて<br/>PostGISの利用を廃止<br/>GISライブラリに`GeoTools v25.1`を採用

- 2021-05-29 リリース v1.2.10 / [citygml-osm v1.2.10](https://github.com/yuuhayashi/citygml-osm/releases/tag/v1.2.10)<br/>[Issue #30](https://github.com/yuuhayashi/citygml-osm/issues/30) 「建築物:建築年」「地上階」「地下階」に対応。

- 2021-05-23 リリース v1.2.9 / [citygml-osm v1.2.9](https://github.com/yuuhayashi/citygml-osm/releases/tag/v1.2.9)<br/>[Issue #26](https://github.com/yuuhayashi/citygml-osm/issues/26) 「建築物:用途」に対応。「建築物:計測高」に対応。

- 2021-05-16 リリース v1.2.8 / [citygml-osm v1.2.8](https://github.com/yuuhayashi/citygml-osm/releases/tag/v1.2.8)<br/>[Issue #18](https://github.com/yuuhayashi/citygml-osm/issues/18) 既存POIとのマージ機能を強化

- 2021-05-08 リリース v1.2.7 / [citygml-osm v1.2.7](https://github.com/yuuhayashi/citygml-osm/releases/download/v1.2.7/citygml-osm-1.2.7-jar-with-dependencies.jar)<br/>[Issue #13](https://github.com/yuuhayashi/citygml-osm/issues/13) ／ 「箱根町」の `bldg:lod0FootPrint`(床形状)に対応<br/>[Issue #13](https://github.com/yuuhayashi/citygml-osm/issues/13) `bldg:lod0RoofEdge`が存在する場合は`bldg:lod0RoofEdge`を建物形状とする<br/>[Issue #16](https://github.com/yuuhayashi/citygml-osm/issues/16) / `bldg:lod0FootPrint`を建物形状とした場合には`bldg:measuredHeight`を`osm:{building:height}`にする<br/>[Issue #16](https://github.com/yuuhayashi/citygml-osm/issues/16) `bldg:lod0FootPrint`を建物形状とした場合には`gml:posList`の高度を`osm:{building:ele}`にする

- 2021-05-08 リリース v1.2.6 / [citygml-osm v1.2.6](https://github.com/yuuhayashi/citygml-osm/releases/download/v1.2.6/citygml-osm-1.2.6-jar-with-dependencies.jar)<br/>[Issue #12](https://github.com/yuuhayashi/citygml-osm/issues/12) 単独の建物でも"building:part"<br/>マルチスレッド対策： 複合ビルディングなどで"building:part"が統合されたり、されなかったりする問題を解決しました。

- 2021-05-07 リリース v1.2.5 / [citygml-osm v1.2.5]<br/>[Issue #12](https://github.com/yuuhayashi/citygml-osm/issues/12) 単独の建物でもbuilding:part

- 2021-05-05 リリース v1.2.4 / [citygml-osm v1.2.4]<br/>[Issue #10](https://github.com/yuuhayashi/citygml-osm/issues/10) 「箱根町」のデータファイル名に対応した。

- 2021-05-05 リリース v1.2.3 / [citygml-osm v1.2.3]<br/>第一段階（OSMへの変換）／複数の建物が接触している場合の変換に対応した。

- 2021-04-18 リリース v1.2.2 / [citygml-osm v1.2.2]<br/>既存データとの重複をチェックして、既存データと重複する場合には既存データを修復する。

- 2021-04-11 リリース v0.2.0 [fixed #7](https://github.com/yuuhayashi/citygml-osm/issues/7) [citygml-osm v0.2.0]<br/>JOSMと同じ"Java8"で実行可能

- 2021-04-10 リリース v0.1.1 [citygml-osm v0.1.1]
- 2021-04-10<br/> "source='MLIT_PLATEAU'"<br/>"source:name='http://www.opengis.net/def/crs/EPSG/0/6697'"
- 2021-04-10<br/> [fixed #3](https://github.com/yuuhayashi/citygml-osm/issues/3) 自治体コード「全国地方公共団体コード」を"addr:ref"としてタグ付け 
- 2021-04-10<br/> [fixed #7](https://github.com/yuuhayashi/citygml-osm/issues/7) 'relasion'メンバーのタグを"building=yes"に修正<br/>"outline"-> "building" には "height"をつけない
- 2021-04-10<br/> [fixed #5](https://github.com/yuuhayashi/citygml-osm/issues/5) 「bouns」を復活
- 2021-04-10<br/> [fixed #4](https://github.com/yuuhayashi/citygml-osm/issues/4) ロール「outlineがありません（警告）」を解決
- 2021-04-08<br/> [fixed #1](https://github.com/yuuhayashi/citygml-osm/issues/1) 接触した建物はリレーション building:part=yes にまとめる、単独の建物は buildhing=yes にする
- 2021-04-05<br/> [fixed #2](https://github.com/yuuhayashi/citygml-osm/issues/2) Issue#2を解決
- 2021-04-04<br/> リリース: 'citygml-osm v0.0.4'


# 使い方

## Step 1 : GMLからOSMへの変換

第一段階ではPLATEAUの「3D都市モデル」の"GMLファイル"をOpenStreetMapの形式に変換した"OSMファイル"を生成します。

![startup.pu](doc/startup.png)

- (1) [GitHub-Release](https://github.com/yuuhayashi/citygml-osm/releases) から 'citygml-osm-1.3.x.zip' をダウンロードして、'任意のフォルダ'に解凍する<br/> `citygml-osm-jar-with-dependencies.jar` と `conversion.json` が解凍されます
  - [GitHub](https://github.com/yuuhayashi/citygml-osm)の右側にある「[Release](https://github.com/yuuhayashi/citygml-osm/releases)」欄から最新'Latest'版をダウンロードしてください

- (2) [オープンデータ公開サイト](https://www.geospatial.jp/ckan/dataset/plateau)から「3D都市データ」をダウンロードする
  - 「3D都市データ」（地域を選択）→「CityGML」→ ダウンロード

- (3) ダウンロードしたZIPファイルを'任意のフォルダ'に解凍する。<br/>ファイル末尾が「`*.gml`」のファイルをため置きます。
  - 解凍されたフォルダ → 「udx」 → 「bldg」 にZIPファイルがあり、このZIPを解凍すると 「`*.gml`」が得られます。

- (4) コマンドターミナルから`run.sh`または`run.bat`を実行<br/>「`*.osm`」、「`*.org.osm`」、「`*.mrg.osm`」ファイルが生成される

```:run.sh
  $ cd (解凍先フォルダ)
  $ java -Dfile.encoding=utf-8 -jar citygml-osm-jar-with-dependencies.jar 1st
  $ java -Dfile.encoding=utf-8 -jar citygml-osm-jar-with-dependencies.jar 2nd
  $ java -Dfile.encoding=utf-8 -jar citygml-osm-jar-with-dependencies.jar 3rd
```

- (5) JOSMを起動して、「`*.mrg.osm`」ファイルをJOSMにドロップしてください。<br/>生成されたデータを確認することができます。


## Step 2 : 変換されたPOIの確認

`run.sh`を実行して `*.mrg.osm` を作成した後に、JOSMで `*.mrg.osm` を開くことで既存データと重複するPOIが確認できるようになります。JOSMのフィルタ機能を使って `MLIT_PLATEAU:fixme` がついたPOIを表示することで確認できます。
（但し、この重複確認機能のために `*.mrg.osm` をOpenStreetMapへアップロードすることはできなくなりました。）


- (6) JOSM上で、変換後のPOIを確認します。<br/>不要な（アップロードしたくない）POIは削除してください。（JOSM上から消えたPOIは更新されません）
  - JOSMで 既存データとの重複を確認し、PLATEAUデータのインポートをやめたい場合には その部分のPOIをJOSM上で「削除」してください。削除予定のPOIもそのPOIを「削除」することで削除を取りやめることができます。（削除予定のPOIをJOSM状に残すとそのPOIが削除されます）

- (7) 不要な（アップロードしたくない）POIを削除したら、レイヤ「`*.mrg.osm`」を『名前をつけて保存』してください。保存ファイル名は「`checked.osm`」とします。
  - 保存ファイル名は「`checked.osm`」固定です。
  - 「`checked.osm`」や「`*.mrg.osm`」は、そのままではOSMにアップロードすることはできません（できるけど既存データを壊してしまいます）。

## Step 3 : OpenStreetMapへアップロード

- (8) コマンドターミナルから`uploader.sh`または`uploader.bat`を実行してください。<br/>「`upload.osm`」ファイルが生成されます。

```:uploader.sh
  $ cd (解凍先フォルダ)
  $ java -Dfile.encoding=utf-8 -jar citygml-osm-jar-with-dependencies.jar 4th
```

- (9) JOSMを起動して、「`upload.osm`」ファイルをJOSMにドロップしてください。
  - 生成された`upload.osm`は、JOSMで開くことで、OpenStreetMapへアップロードすることができます。

- (10) レイヤ「`upload.osm`」を"アップロード"してください。
  - アップロード実行前に、妥当性検証などを行って最終調整を行ってからアップロードしてください。


# データフォーマット変換

## CityGMLフォーマット

![citygml](doc/citygml/citygml.mind.png)

「3D都市モデル」から読み取る項目

| 項目		| GMLタグ			| 説明											|
| --------	| ----------------- | -------------------------------------------	|
| データ範囲	| `gml:boundedBy`	| データ対象範囲									|
| ソース名	| `gml:Envelope`-`srsName`	| データソース名称						|
| 建築物		| `bldg:Building`	| ビルディングPOIに相当								|
| 屋根外形	| `bldg:lod0RoofEdge`	| 建築物の屋根形状								|
| 接地面		| `bldg:lod0FootPrint` | 建築物の床形状								|
| 建築物形状	| `bldg:lod1Solid`	| 建築物の形状を示す立体							|
| ID		| `gml:id`			| GMLでの管理ID									|
| 名称		| `gml:name`		| 重要な建物にのみ設定されている						|
| 建物ID		| `gen:stringAttribute`-`name="建物ID"`	| 建築物に付与された識別ID	|
| 自治体コード	| `gen:stringAttribute`-`name="13_*"`	| "13_区市町村コード_大字・町コード_町・丁目コード" |
| 分類		| `bldg:class`		| 建築物の形態による区分 '[Building_class.xml](doc/citygml/codelists/Building_class.xml)'	|
| 用途		| `bldg:usage`		| 建築物の主な使いみち。代表的な用途 '[Building_usage.xml](doc/citygml/codelists/Building_usage.xml)'	|
| 建築年		| `bldg:yearOfConstruction`	| 建築物が建築された年					|
| 計測高さ	| `bldg:measuredHeight`	| 地盤面からの建築物の高さ(m)				|
| 地上階数	| `bldg:storeysAboveGround`	| 建物の地上部分の階数（日本的な数え方）正の整数値	|
| 地下階数	| `bldg:storeysBelowGround`	| 建物の地下部分の階数 正の整数値			|
| 住所		| `xAL:LocalityName`-`Type="Town"`	| 							|

### **建築物**<br/>「建物POI」に相当する。

- **屋根外形**<br/>形状には外郭線（`gml:exterior`）と内線(`gml:interior`)があり、'[{緯度、経度、高度}]'で表される<br/>'高度'は*屋根の標高*を表す

- **接地面**<br/>形状には外郭線（`gml:exterior`）と内線(`gml:interior`)があり、'[{緯度、経度、高度}]'で表される<br/>'高度'は建物床の*標高*を表す

- **建築物形状**<br/>建築物の水平的な位置を示す面に、一律の高さを与えた立体。'[{緯度、経度、高度}]'で表される<br/>'高度'は立体面の*標高*を表す。この標高から建築物の「最低標高=`ele`」と「最高標高」を取得して「建物の`height`＝（最高標高）ー（最低標高）」を算出する。

- **計測高さ**<br/>計測により取得した建築物の地上の最低点から最高点までの高さ(m)。

- **地上階数**<br/>建築物の地上部分の階数(自然数)。階数の数え方は日本式。

- **地下階数**<br/>建築物の地上部分の階数(自然数)。階数の数え方は日本式。

- **建築年**<br/>建築物が建築された年(自然数)。

### **分類**<br/>`bldg:class`」 建築物の形態による区分 --> コードリスト: '[Building_class.xml](doc/citygml/codelists/Building_class.xml)'

| id	| name	| description	|
| -----	| -----	| -------------	|
| id1	| 3001	| 普通建物		|
| id2	| 3002	| 堅ろう建物		|
| id3	| 3003	| 普通無壁舎		|
| id4	| 3004	| 堅ろう無壁舎		|
| id5	| 3000	| 分類しない建物	|

	記述例: `<bldg:class codeSpace="../../codelists/Building_class.xml">3001</bldg:class>`

### **用途**<br/>`bldg:usage` 建築物の主な使い道 --> コードリスト: '[Building_usage.xml](doc/citygml/codelists/Building_usage.xml)'

| id	| name	| description		| OSM Tag		|
| -----	| -----	| -----------------	| -------------	|
| id1	| 401	| 業務施設			| `commercial`		|
| id2	| 402	| 商業施設			| `retail`	|
| id3	| 403	| 宿泊施設			| `hotel`		|
| id4	| 404	| 商業系複合施設		| `retail`				|
| id5	| 411	| 住宅				| `house`		|
| id6	| 412	| 共同住宅			| `apartments`	|
| id7	| 413	| 店舗等併用住宅		| `residential`				|
| id8	| 414	| 店舗等併用共同住宅	| `apartments`				|
| id9	| 415	| 作業所併用住宅		| `residential`				|
| id10	| 421	| 官公庁施設			| `government`	|
| id11	| 422	| 文教厚生施設			| `public`		|
| id12	| 431	| 運輸倉庫施設			| `warehouse`	|
| id13	| 441	| 工場				| `industrial`	|
| id14	| 451	| 農林漁業用施設		| -				|
| id15	| 452	| 供給処理施設			| -				|
| id16	| 453	| 防衛施設			| `military`	|
| id17	| 454	| その他				| -				|
| id18	| 461	| 不明				| -				|

 注: 分類が定義されていない建築物は、`building=yes`とする

記述例: `<bldg:usage codeSpace="../../codelists/Building_usage.xml">411</bldg:usage>`

- '[conversion.json](conversion.json)'にOSMタグへの変換表を記述して、`building=*`の値に割り当てる。
	
- 変換表に記載がない`usage`コードは、`building=yes`とする

```
{
	"usage" : [
		{"code":"401","name":"業務施設",	"building":"commercial"},
		{"code":"402","name":"商業施設",	"building":"retail"},
		{"code":"403","name":"宿泊施設",	"building":"hotel"},
		{"code":"404","name":"商業系複合施設",	"building":"retail"},
		{"code":"411","name":"住宅",		"building":"house"},
		{"code":"412","name":"共同住宅",	"building":"apartments"},
		{"code":"413","name":"店舗等併用住宅",	"building":"residential"},
		{"code":"414","name":"店舗等併用共同住宅",	"building":"apartments"},
		{"code":"415","name":"作業所併用住宅",	"building":"residential"},
		{"code":"421","name":"官公庁施設",	"building":"government"},
		{"code":"422","name":"文教厚生施設",	"building":"public"},
		{"code":"431","name":"運輸倉庫施設",	"building":"warehouse"},
		{"code":"441","name":"工場",		"building":"industrial"},
		{"code":"451","name":"農林漁施設", "building":"agricultural"},
		{"code":"453","name":"防衛施設",	"building":"military"}
	]
}
```

### その他

仕様には記載されているがデータで確認が取れないもの

- 「解体年 `bldg:yearOfDemolition`」 建築物が解体された年

- 「屋根の種別 `bldg:roofType`」 建築物の屋根形状の種類 --> 'Building_roofType.xml'

- 「`gen::stringAttribute 建物用途コード番号`」 --> コードリスト: 'Building_usageDetail.xml'


## "building" OSMフォーマットへの変換

## タグ付け

OSMファイルへの変換項目

| 項目		| エレメント			| 説明											|
| --------	| ----------------- | -------------------------------------------	|
| データ範囲	| `bounds`			| `maxlat`,`maxlon`,`minlat`,`minlon`		|

閉じたWAYへのタグ付け項目

| 項目		| GMLタグ					| 説明												|
| --------	| ------------------------- | -------------------------------------------------	|
| 建物ID		| `k="ref:MLIT_PLATEAU"`		| **建物ID**	|
| 建物		| `k="building"`			| リレーション:buildingのメンバーの場合は `k="building:part"。**v**の値は'[bldg:usage 用途](conversion.json)'から取得する	|
| 建物名称	| `k="name"`		| リレーション:buildingのメンバーの場合は、`k="name"`		|
| 住所コード	| -			| **自治体コード** ("13_区市町村コード_大字・町コード_町・丁目コード") 	|
| 住所		| `k="addr:full"`			| **住所**											|
| 建物高さ	| `k="height"`				| **計測高さ**`bldg:measuredHeight`, `bldg:measuredHeight`がない場合は、`lod1Solid`と`lod0[RoofEdge,FoodPrint`から算出する	|
| 標高		| `k="ele"`				| **建築物形状の高度**	`bldg:lod1Solid`の最低高度		|
| 地上階数	| `k="building:levels"`	| 建物の地上部分の階数（日本的な数え方） 正の整数値			|
| 地下階数	| `k="building:levels:underground"`	| 建物の地下部分の階数 正の整数値				|

### 建物種別 `k="building"`
- GML::`bldg:usage`の値を'[conversion.json](conversion.json)'で変換して、`building=*`に割り当てる。	
- 変換表に記載がない`usage`コードは、`building=yes`とする
- 複合ビルの場合は、最大面積のビルパーツの'usage'をリレーションの「`building=*`」とする。

### 建物名称 `k="name"`
- 建物ポリゴン `Relation::type="multipolygon"` の場合は、`k="name"`とする
- 建物リレーション `Relation::type="building"`の場合は、`k="name"`とする
- 建物パーツ `member::role="part"`->`building=yes` の場合は、`k="name"`とする
- <del>複合ビルの場合は、構成部位の名称のうちで、最も文字数が多いものを「合成名称」にする（Relation:`k="name"`の`v=合成名称`）</del> --> [Issue #51](https://github.com/yuuhayashi/citygml-osm/issues/51)

### 建物高さ `ele`
- `bldg:lod1Solid`の'高度'値の最低値
- 複合ビルの場合は、全ビルパーツの最低値

### 建物高さ `height`
- `building:height`とはしない
- 単体ビルの場合は、`bldg:measuredHeight`。
- 単体ビルで、`bldg:measuredHeight`がない場合は、`lod1Solid`と`lod0[RoofEdge,FoodPrint`から算出する
- 複合ビルの場合は、複合ビルの'最低標高'を求め、全ビルパーツの'最低標高'からの'高さ'の最大値。'標高'が設定されていないビルパーツは全ビルパーツの'最低標高’からの高さが設定されているとみなす。

### 地上階 `building:levels`
- 単体ビルの場合は、`building:levels`。
- 複合ビルの場合は、全ビルパーツの最大'地上階'をリレーションの「地上階'building:levels'」とする。

### 地下階 `building:levels:underground`
- 単体ビルの場合は、`building:levels:underground`。
- 複合ビルの場合は、全ビルパーツの最大'地下階'をリレーションの「地下階'building:levels:underground'」とする。

### 建築年 `start_date`
- GML::`bldg:yearOfConstruction`の値を`start_date`の値とする
- ビルパーツの'建築年'を「建築年`start_date`」とする。
- ビルリレーションには「建築年」を反映させない。[Issue 39](https://github.com/yuuhayashi/citygml-osm/issues/39)


## POI構成

- 接触した建物は「リレーション:type=building」に変換します　－＞ [リレーション / マルチポリゴン構成](doc/building/Relations.md)


# 既存POIとのオーバーラップ

## オーバーラップパターン

### pattern A-1 オーバーラップする既存WAYが存在しない場合

* 例: '13111-bldg-365'
* WAYを"action=modify"にする

### pattern A-2 オーバーラップする既存WAYが存在する場合

* 例: '13111-bldg-466'
* WAYを"action=modify"にする
* WAY.IDを既存WAYのWAY.IDに変更する
* WAY.TAGsに既存WAY.TAGsを上書きする

	* この際に、既存WAY.TAGsに"height=*"があれば WAY.TAGの値は上書きされる
	* TAG "building"のvalue は、既存WAY.TAGの値に上書き
	* "source"TAGは WAYで書き換えられる。 --> [Issue #56](https://github.com/yuuhayashi/citygml-osm/issues/56)

* WAY.TAGsに"fixme=PLATEAUデータで更新されています"を追加

### pattern A-3 オーバーラップする既存WAYが複数存在する場合

* 重なる面積が最大の既存WAYを'更新'する
* 他の重複する既存WAYは'削除'する。'fixme=PLATEAUデータで置き換えられました'


## 既存データとPLATEAUデータとの合成

既存building とのタグの値の扱い --> [Issue #22](https://github.com/yuuhayashi/citygml-osm/issues/22)

### 建物種別 `k="building"`

- `building=*` は、PLATEAUデータの値を優先する --> [Issue #22](https://github.com/yuuhayashi/citygml-osm/issues/22)

| import POI | 既存POI         |  合成後    | 説明 |
| -------------- | ------------------- | ------------------- | -------------- |
| 'building=yes' | 'building=yes' |  'building=yes'   |  |
| 'building=yes' | 'building=parking' |  'building=parking' |  |
| 'building=public' | 'building=yes' |  'building=public'   |  |
| 'building=public' | 'building=parking' |  'building=public' | 既存POIの値を書き換える |

### 建物名称 `k="name"`

- `name=*` は、既存データの値を優先する --> [Issue #22](https://github.com/yuuhayashi/citygml-osm/issues/22), [Issue #64](https://github.com/yuuhayashi/citygml-osm/issues/64)

| PLATEAU POI | 既存POI         |  マージPOI    | 説明 |
| -------------- | ------------------- | ------------------- | -------------- |
| なし            | なし                 |  なし    |      |
|  なし            | 'name=A棟' |  'name=A棟'   |    |
| 'name=大口病院' | なし                |  'name=大口病院'    | 既存POIがにタグがない場合は補完する |
| 'name=市立栗原小学校' | 'name=新座市立栗原小学校' |  'name=新座市立栗原小学校'   |  既存データを優先する |


### 建物高さ `ele`

- `ele=*` は、PLATEAUデータの値を優先する --> [Issue #63](https://github.com/yuuhayashi/citygml-osm/issues/63)

| import POI | 既存POI         |  合成後    | 説明 |
| -------------- | ------------------- | ------------------- | -------------- |
| なし            | なし                 |  なし    |  |
|  なし            | 'ele=7.5' |  'ele=7.5'   |  |
| 'ele=8.9' | なし                |  'ele=8.9'    | 既存POIにタグがない場合は補完する |
| 'ele=8.9' | 'ele=7.5' |  'ele=8.9' | 既存POIの値を書き換える |


### 建物高さ `height`

- `height=*` は、PLATEAUデータの値を優先する --> [Issue #22](https://github.com/yuuhayashi/citygml-osm/issues/22)

| import POI | 既存POI         |  合成後    | 説明 |
| -------------- | ------------------- | ------------------- | -------------- |
| なし            | なし                 |  なし    |  |
|  なし            | 'height=7.5' |  'height=7.5'   | PLATEAUにタグがない場合は既存データのまま |
| 'height=8.9' | なし                |  'height=8.9'    | 既存POIにタグがない場合は補完する |
| 'height=8.9' | 'height=7.5' |  'height=8.9' | 既存POIの値を書き換える |

### 地上階 `building:levels`

- `building:levels=*` は、PLATEAUデータの値を優先する --> [Issue #62](https://github.com/yuuhayashi/citygml-osm/issues/62)

| import POI | 既存POI         |  合成後    | 説明 |
| -------------- | ------------------- | ------------------- | -------------- |
| なし            | なし                 |  なし    |  |
|  なし            | 'building:levels=7' |  'building:levels=7'   | PLATEAUにタグがない場合は既存データのまま |
| 'building:levels=8' | なし                |  'building:levels=8'    | 既存POIにタグがない場合は補完する |
| 'building:levels=8' | 'building:levels=7' |  'building:levels=8' | 既存POIの値を書き換える |

### 地下階 `building:levels:underground`

- `building:levels:underground=*` は、PLATEAUデータの値を優先する --> [Issue #62](https://github.com/yuuhayashi/citygml-osm/issues/62)

| import POI | 既存POI         |  合成後    | 説明 |
| -------------- | ------------------- | ------------------- | -------------- |
| なし            | なし                 |  なし    |  |
|  なし            | 'building:levels:underground=2' |  'building:levels:underground=2'   | PLATEAUにタグがない場合は既存データのまま |
| 'building:levels:underground=1' | なし                |  'building:levels:underground=1'    | 既存POIにタグがない場合は補完する |
| 'building:levels:underground=1' | 'building:levels:underground=2' |  'building:levels:underground=1' | 既存POIの値を書き換える |


### 建築年 `start_date`

- `start_date=*` は、既存データを優先する --> [Issue #61](https://github.com/yuuhayashi/citygml-osm/issues/61)

| PLATEAU POI | 既存POI         |  マージPOI    | 説明 |
| -------------- | ------------------- | ------------------- | -------------- |
| なし            | なし                 |  なし    |      |
|  なし            | 'start_date=2021-03' |  'start_date=2021-03'   |    |
| 'start_date=2019' | なし                |  'start_date=2019'    | 既存POIがにタグがない場合は補完する |
| 'start_date=2019' | 'start_date=2021-03' |  'start_date=2021-03'   |  既存データを優先する |


### SOURCE `source=GSI/KIBAN 25000;NARO`

- `source=*`に`[S|s]urvey`、が含まれる場合は、`source=survey`に変換する --> [Issue #56](https://github.com/yuuhayashi/citygml-osm/issues/56)

| import POI | 既存POI         |  合成後    | 説明 |
| -------------- | ------------------- | ------------------- | -------------- |
| なし           | なし                 |  なし    |  |
|  なし          | 'source=GSI/KIBAN 25000;NARO' |  なし   | PLATEAUによって書き換えられたので、`source=GSI/KIBAN 25000;NARO` は無効になった |
| なし           | 'source=GSI/KIBAN 25000;Survey' |  'source=survey' | `survey`だけ残す |


------

## 内部資料

- [クラス関連図](doc/citygml/class.png)

- [データベース テーブル 関連図](doc/citygml/dbTables.png)


----------------

## 関連サイト

 - [3D都市モデル（Project PLATEAU）ポータルサイト](https://www.geospatial.jp/ckan/dataset/plateau)

 - [OpenStreetMap Wiki - JA:MLIT_PLATEAU](https://wiki.openstreetmap.org/wiki/JA:MLIT_PLATEAU)

 - [210327PLATEAUを触ってみよう](https://hackmd.io/@geopythonjp/HkZOmNpqL/%2FhfZTkl5FQGy8YHrgzc7ohQ)

 - [オープンデータ公開サイト](https://www.geospatial.jp/ckan/dataset/plateau)

 - [GitHub yuuhayashi/citygml-osm](https://github.com/yuuhayashi/citygml-osm)

 - [中空部分がある複合ビルディングの書き方](doc/building/Building.md)

 - [JA:基本的な3Dの建物](https://wiki.openstreetmap.org/wiki/JA:%E5%9F%BA%E6%9C%AC%E7%9A%84%E3%81%AA3D%E3%81%AE%E5%BB%BA%E7%89%A9)
 
 - [JA:Relation:multipolygon](https://wiki.openstreetmap.org/wiki/JA:Relation:multipolygon)
 
 - [osmbuilding 東京駅](https://osmbuildings.org/?lat=35.62651&lon=139.77533&zoom=17.0&tilt=45&rotation=109)

