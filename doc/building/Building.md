# 中空部分を持つ建物の書き方

![例題](buildingPart1.png)


## (1) 全体を Maltipolygon にする

![1.outline](buildingPart2.png)

まずはじめに、建物部位全体を囲む **輪郭線** と、中空部分／空白部分をメンバーに持つ **Relation:Multipolygon** を作成する。

- 輪郭線や、中空エリアにはタグ付けしない。
- 輪郭線のロールは 'outer'
- 中空エリア、空白エリアのロールは、'inner'

![multipolygon](multipolygon.png)

## (2) "building :リレーション" を作る

- 先程作成した multipolygon をメンバー登録[role=outline]する
- 構成する建物部位をメンバー登録[role=part]する

![building :リレーション](building.png)

----

### 参考

 - [JA:基本的な3Dの建物](https://wiki.openstreetmap.org/wiki/JA:%E5%9F%BA%E6%9C%AC%E7%9A%84%E3%81%AA3D%E3%81%AE%E5%BB%BA%E7%89%A9)
 
 - [JA:Relation:multipolygon](https://wiki.openstreetmap.org/wiki/JA:Relation:multipolygon)

