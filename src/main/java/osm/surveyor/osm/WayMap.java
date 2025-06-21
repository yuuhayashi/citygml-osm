package osm.surveyor.osm;

import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import osm.surveyor.osm.way.WayModel;

/**
 * hash key = way.id
 * 
 */
@SuppressWarnings("serial")
public class WayMap extends HashMap<String, WayModel> {
	
	public void put(WayModel way) {
		if (way != null) {
			put(Long.toString(way.getId()), way);
		}
	}
	
	public WayModel get(long id) {
		return get(Long.toString(id));
	}
	
	public WayModel get(OsmLine line) {
		for (String id : this.keySet()) {
			WayModel model = this.get(id);
			if (model.getClass() == ElementWay.class) {
				ElementWay way = (ElementWay)model;
				if (way.isSame(line)) {
					return way;
				}
			}
		}
		return null;
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
    
    public void remove(long wayid) {
    	remove(Long.toString(wayid));
    }

    public void remove(ElementWay way) {
    	remove(Long.toString(way.getId()));
    }
}
