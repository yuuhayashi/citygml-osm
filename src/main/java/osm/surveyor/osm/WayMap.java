package osm.surveyor.osm;

import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * hash key = way.id
 * 
 */
@SuppressWarnings("serial")
public class WayMap extends HashMap<String, ElementWay> {
	
	public void put(ElementWay way) {
		put(Long.toString(way.id), way);
	}
	
	public ElementWay get(long id) {
		return get(Long.toString(id));
	}

    /**
     * <way changeset="61979354" id="96350144" timestamp="2018-08-25T08:34:33Z" uid="7548722" user="Unnown" version="17" visible="true">
     * 	<way id='-2' action='modify' visible='true'>
	 * 	  <nd ref='-3'/>
	 * 	  <nd ref='-4'/>
	 * 	  <nd ref='-5'/>
	 * 	  <tag k='height' v='14.072000000000001' />
	 * 	  <tag k='building:part' v='yes' />
	 * 	</way>
     * @param doc
     * @param ways
     */
    void load(Document doc) {
	    NodeList nList = doc.getElementsByTagName("way");
	    for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			ElementWay way = (new ElementWay(0)).loadWay(nNode);
			if (way != null) {
			    put(way);
			}
	    }
    }
    
    public void remove(ElementWay way) {
    	remove(Long.toString(way.id));
    }

}
