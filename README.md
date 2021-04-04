# CityGML

CityGMLからOSMデータを生成する

# Release

- [citygml-osm v0.0.4](http://surveyor.mydns.jp/archiva/#artifact-details-download-content~haya4/haya4/citygml-get/0.0.4)

# 使い方

- (1) [ダウンロード](http://surveyor.mydns.jp/archiva/#artifact-details-download-content~haya4/haya4/citygml-get/0.0.4) から 'jar-with-dependencies' をダウンロードする

- `citygml-get-0.0.4-jar-with-dependencies.jar` がダウンロードされます

- (2) [オープンデータ公開サイト](https://www.geospatial.jp/ckan/dataset/plateau)から「3D都市データ」をダウンロードする

- (3) 任意のフォルダに解凍する。ファイル末尾が「*.gml」のファイルをため置きます。

- (4) 「*.gml」のファイルがあるフォルダに `citygml-get-0.0.4-jar-with-dependencies.jar` を移動させる

- (5) コマンドターミナルから実行

  ```
  $ cd (解凍先フォルダ)
  $ java -jar citygml-get-0.0.4-jar-with-dependencies.jar
  ```

- (6) 「*.osm」ファイルが生成される

- (7) JOSMを起動して、「*.osm」ファイルをJOSMにドロップしてください。生成されたデータを確認することができます。

**絶対にOSMへの「アップロード」は実行しないこと！！**

**確認のみ！！  確認し終わったら破棄すること！！**

------

- [生成されたOSMファイル](53392547_bldg_6697_op2.osm)

- [クラス関連図](doc/class.png)

----------------

参考

- [210327PLATEAUを触ってみよう](https://hackmd.io/@geopythonjp/HkZOmNpqL/%2FhfZTkl5FQGy8YHrgzc7ohQ)

- [オープンデータ公開サイト](https://www.geospatial.jp/ckan/dataset/plateau)

 
