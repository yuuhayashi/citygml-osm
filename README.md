# CityGML

CityGMLから、OpenStreetMapへのJOSM用のOSMデータを生成する

- 生成されたOSMファイル: [53392547_bldg_6697_op2.osm](53392547_bldg_6697_op2.osm)

![Screenshot.png](doc/Screenshot.png)


# Release

- 2021-04-11 リリース v0.2.0 fixed #7 / [citygml-osm v0.2.0](http://surveyor.mydns.jp/archiva/#artifact-details-download-content~haya4/osm.surveyor/citygml-osm/0.2.0)<br/>JOSMと同じ"Java8"で実行可能
- 2021-04-10 リリース v0.1.1 [citygml-osm v0.1.1](http://surveyor.mydns.jp/archiva/#artifact-details-download-content~haya4/osm.surveyor/citygml-osm/0.1.1)
- 2021-04-10<br/> "source='MLIT_PLATEAU'"<br/>"source:name='http://www.opengis.net/def/crs/EPSG/0/6697'"
- 2021-04-10<br/> fixed #3 / 自治体コード「全国地方公共団体コード」を"addr:ref"としてタグ付け 
- 2021-04-10<br/> fixed #7 / 'relasion'メンバーのタグを"building=yes"に修正<br/>"outline"-> "building" には "height"をつけない
- 2021-04-10<br/> fixed #5 / 「bouns」を復活
- 2021-04-10<br/> fixed #4 / ロール「outlineがありません（警告）」を解決

- 2021-04-08<br/> fixed #1 / Issue#1を解決<br/> 接触した建物はリレーション building:part=yes にまとめる、単独の建物は buildhing=yes にする

- 2021-04-05<br/> fixed #2 / Issue#2を解決

- 2021-04-04<br/> リリース: 'citygml-osm v0.0.4'

# 使い方

![startup.pu](doc/startup.png)

- (1) [ダウンロード](http://surveyor.mydns.jp/archiva/#artifact-details-download-content~haya4/osm.surveyor/citygml-osm/0.2.0) から 'jar-with-dependencies' を'任意のフォルダ'にダウンロードする<br/> `citygml-get-0.2.0-jar-with-dependencies.jar` がダウンロードされます

- (2) [オープンデータ公開サイト](https://www.geospatial.jp/ckan/dataset/plateau)から「3D都市データ」をダウンロードする

- (3) ダウンロードしたZIPファイルを'任意のフォルダ'に解凍する。<br/>ファイル末尾が「*.gml」のファイルをため置きます。

- (4) コマンドターミナルから実行<br/>「*.osm」ファイルが生成される

  ```
  $ cd (解凍先フォルダ)
  $ java -jar citygml-get-0.2.0-jar-with-dependencies.jar
  ```

- (5) JOSMを起動して、「*.osm」ファイルをJOSMにドロップしてください。<br/>生成されたデータを確認することができます。

**絶対にOSMへの「アップロード」は実行しないこと！！**

**確認のみ！！  確認し終わったら破棄すること！！**

------

- 生成されたOSMファイル: [53392547_bldg_6697_op2.osm](53392547_bldg_6697_op2.osm)

- [クラス関連図](doc/class.png)

----------------

参考

- [210327PLATEAUを触ってみよう](https://hackmd.io/@geopythonjp/HkZOmNpqL/%2FhfZTkl5FQGy8YHrgzc7ohQ)

- [オープンデータ公開サイト](https://www.geospatial.jp/ckan/dataset/plateau)

 
