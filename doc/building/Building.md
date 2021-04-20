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
- 構成する建物部位をメッンー登録[role=part]する

![building :リレーション](building.png)
