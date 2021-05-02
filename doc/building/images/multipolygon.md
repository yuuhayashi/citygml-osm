# 中空部分を持つ建物の書き方

![例題](buildingPart1.png)


## (1) 全体を Maltipolygon にする

![1.outline](buildingPart2.png)

まずはじめに、建物部位全体を囲む **輪郭線** と、中空部分／空白部分をメンバーに持つ **Relation:Multipolygon** を作成する。

![multipolygon](multipolygon.png)

- 輪郭線や、中空エリアにはタグ付けしない。
- 輪郭線のロールは 'outer'
- 中空エリア、空白エリアのロールは、'inner'

