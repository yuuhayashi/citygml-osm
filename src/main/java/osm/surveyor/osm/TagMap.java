package osm.surveyor.osm;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * hash key = way.id
 * 
 */
@SuppressWarnings("serial")
public class TagMap extends HashMap<String, ElementTag> {
	
	public void put(ElementTag tag) {
		put(tag.k, tag);
	}
	
    public void remove(ElementTag tag) {
    	remove(tag.k);
    }
    
    /**
     * マルチポリゴンメンバー用のタグに変更する
     * OUTER and INNER
     * 		- remove NOT "source=*"
     * 		- remove NOT "fixme=*"
     */
    public void toMultipolygonMember() {
    	ArrayList<ElementTag> killList = new ArrayList<>();
    	for (String key : this.keySet()) {
    		ElementTag tag = this.get(key);
    		switch (tag.k) {
    		case "source":
    			break;
    		case "fixme":
    			break;
    		default:
    			killList.add(tag);
    		}	
    	}
    	for (ElementTag tag : killList) {
    		this.remove(tag);
    	}
    }
}
