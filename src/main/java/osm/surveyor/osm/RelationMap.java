package osm.surveyor.osm;

import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@SuppressWarnings("serial")
public class RelationMap extends HashMap<String, ElementRelation> {
	
	public RelationMap() {
		super();
	}
	
	public void put(ElementRelation relation) {
		if (relation != null) {
			put(Long.toString(relation.id), relation);
		}
	}

	public ElementRelation get(long id) {
		return get(Long.toString(id));
	}
	
    /**
     * 	<relation id='-1654' action='modify' visible='true'>
     *	  <member ref='-1655' type='way' role='part' />
     *	  <tag k='type' v='building' />
     *	  <tag k='source:ref:id' v='BLD_57cd4ea7-0fb7-4b0e-a600-9982cf3b60ca' />
     *	  <tag k='addr:full' v='東京都大田区南六郷三丁目' />
     *	</relation>
     * @param doc
     * @param relations
     */
    public void load(Document doc) {
	    NodeList nList = doc.getElementsByTagName("relation");
	    for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			ElementRelation relation = (new ElementRelation()).loadRelation(nNode);
			if (relation != null) {
			    put(relation);
			}
	    }
    }
    
}
