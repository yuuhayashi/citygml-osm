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
			put(Long.toString(relation.getId()), relation);
		}
	}

	public ElementRelation get(long id) {
		return get(Long.toString(id));
	}
    
    public void remove(ElementRelation relation) {
    	if (relation != null) {
        	this.remove(Long.toString(relation.getId()));
    	}
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
			ElementRelation relation = (new ElementRelation(0)).loadRelation(nNode);
			if (relation != null) {
			    put(relation);
			}
	    }
    }
    
    /**
     * 指定されたWAYをメンバーに持つリレーションを取得する
     * @param wKey	WAYのID
     * @return	目的のリレーションが見つからなかったらNULL
     */
    public ElementRelation hasMembersWay(String wKey) {
    	ElementRelation relation = null;
    	for (String rKey : this.keySet()) {
    		relation = this.get(rKey);
    		if (relation != null) {
    			for (MemberBean member : relation.members) {
    				if (member.getRef() == Long.parseLong(wKey)) {
    					return relation;
    				}
    			}
    		}
    	}
		return null;
    }

    /**
     * 指定されたWAYをOUTLINEメンバーに持つMultipolygonリレーションを取得する
     * @param wKey	WAYのID
     * @return	目的のリレーションが見つからなかったらNULL
     */
    public ElementRelation hasOutlineWay(String wKey) {
    	ElementRelation relation = null;
    	for (String rKey : this.keySet()) {
    		relation = this.get(rKey);
    		if (relation.isMultipolygon()) {
    			for (MemberBean member : relation.members) {
    				if (member.getType().equals("way") 
						&& member.getRole().equals("outer") 
						&& (member.getRef() == Long.parseLong(wKey))) 
    				{
        					return relation;
        			}
    			}
    		}
    	}
		return null;
    }
}
