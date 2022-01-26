## POI構成

「建物Way」の同士の接触状態によって、「連接ビルディング」や「マルチポリゴン」を形成する。

### a. 単純ビルディング

他のビルディングと接触しないもの

![sample a GML](sample-a/sample-a-gml.png)

一つのWAYのみで完結させる

- Step 1. parse GML file [sample-a-osm1](sample-a/sample-a-osm1.png)

- Step 2. 接触しているBUILDINGのWAYをくっつけて"Relation:building"をつくる (Step1から変化なし)

- Step 3. メンバーが一つしかないRelation:building を削除する [sample-a-osm3](sample-a/sample-a-osm3.png)

- Step 4. Relation:building->member:role=port のWay:outlineを作成する
- Step 4. Relation:multipolygon->outerにWay:outline (Step3から変化なし)

- Step 5. 不要なPOIを削除する　(Step3から変化なし)

![sample a OSM](sample-a/sample-a-osm.png)

#### a-1. 線で接触していないビルディング

接触していないが他の建物のノードと共有しているパターン　[接点パターン](sample-a/Sample-a-SharePoint.png)


### b. 中空部を持つ単一ビルディング

中空部分('gml:interior')があるビルディングで、他のビルディングと接触しないもの

![sample b GML](sample-b/sample-b-gml.png)

マルチポリゴン・リレーションでビルディングを描く

- Step 1. parse GML file [sample-b-osm1.png](sample-b/sample-b-osm1.png)

- Step 2. 接触しているBUILDINGのWAYをくっつけて"Relation:building"をつくる (Step1から変化なし)

- Step 3. Relation:building->member:role=port のWay:outlineを作成する

- Step 3. Relation:multipolygon->outerにWay:outline (Step1から変化なし)

- Step 4. メンバーが一つしかないRelation:building を削除する (Step1から変化なし)

- Step 5. 不要なPOIを削除する　[sample a OSM](sample-b/sample-b-osm.pu.png)

![InnerBuilding](sample-b/sample-b-osm.png)


### c. 複数のビルディングが接触しているもの（連接ビルディング）

中空部分('gml:interior')がない建物同士が、接触しているもの。

![sample c GML](sample-c/gml.png)

ビルディング・リレーションを使って描く

- Step 1. parse GML file "[sample-c-osm1](sample-c/osm1-parse.png)"

- Step 2. 接触しているBUILDINGのWAYを"Relation:building"にまとめる "[osm2 RelationMarge](sample-c/osm2-RelationMarge.png)"

- Step 3. メンバーが一つしかないRelation:building を削除する "[osm3 RemoveSinglePart](sample-c/osm3-RemoveSinglePart.png)"

- Step 4. Relation:building->member:role=port のWay:outlineを作成する

- Step 4. Relation:multipolygon->outerにWay:outline "[osm4 Create Outline](sample-c/osm4-CreateOutline.png)"

- Step 5. 不要なPOIを削除する　"[sample a OSM](sample-c/osm.pu.png)"

![sample c OSM](sample-c/osm.png)
 

### d. 中空部分がある連接ビルディング

他のビルディングと接触しているビルディングで、中空部分を含む場合。

![sample d GML](sample-d/sample-d-gml.png)

ビルディング・リレーションのOutlineに、マルチポリゴン・リレーションを使って描く

- Step 1. parse GML file "[sample-D Parse](sample-d/osm1-parse.png)"

- Step 2. 接触しているBUILDINGのWAYを"Relation:building"にまとめる "[osm2 RelationMarge](sample-c/osm2-RelationMarge.png)"

- Step 3. メンバーが一つしかないRelation:building を削除する "[osm3 RemoveSinglePart](sample-c/osm3-RemoveSinglePart.png)"

- Step 4. Relation:building->member:role=port のWay:outlineを作成する

- Step 4. Relation:multipolygon->outerにWay:outline "[osm4 Create Outline](sample-c/osm4-CreateOutline.png)"

- Step 5. 不要なPOIを削除する　"[sample a OSM](sample-c/osm.pu.png)"

![building :リレーション](sample-d/sample-d-osm.png)

- [中空部分がある複合ビルディングの書き方](Building.md)


### e. 空白部分がある連接ビルディング

連接ビルディングに囲まれた「空白」ができたもの

![sample e GML](sample-e/sample-e-gml.png)

* "outline:way"(外郭線)を補完する
* "inner:way"（空白部分）をインナーラインとして補完する
* "name=..."の継承

- Step 1. parse GML file "[sample-D Parse](sample-e/osm1-parse.png)"

- Step 2. 接触しているBUILDINGのWAYを"Relation:building"にまとめる "[osm2 RelationMarge](sample-e/osm2-RelationMarge.png)"

- Step 3. メンバーが一つしかないRelation:building を削除する "[osm3 RemoveSinglePart](sample-e/osm3-RemoveSinglePart.png)"

- Step 4. Relation:building->member:role=port のWay:outlineを作成する

- Step 4. Relation:multipolygon->outerにWay:outline "[osm4 Create Outline](sample-e/osm4-CreateOutline.png)"

- Step 5. 不要なPOIを削除する　(変更なし)

![sample e OSM](sample-e/sample-e-osm.png)


----

## 参考

 - [JA:基本的な3Dの建物](https://wiki.openstreetmap.org/wiki/JA:%E5%9F%BA%E6%9C%AC%E7%9A%84%E3%81%AA3D%E3%81%AE%E5%BB%BA%E7%89%A9)
 
 - [JA:Relation:multipolygon](https://wiki.openstreetmap.org/wiki/JA:Relation:multipolygon)

