package osm.surveyor.citygml;

import osm.surveyor.osm.ElementRelation;

/**
 * CityGMLファイルをパースする
 * @param gmlFile
 * @code{
 *     <bldg:Building gml:id="BLD_fc50c7d9-76ac-4576-bfbd-f37c74410928">
 *       <bldg:lod0RoofEdge>
 *       </bldg:lod0RoofEdge>
 *     </bldg:Building>
 * }
 * 
 */
public class ElementBuilding extends ElementRelation {
    
    public ElementBuilding(long id) {
        super(id);
		this.addTag("type", "building");
    }
}
