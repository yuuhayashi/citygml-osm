package osm.surveyor.osm;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * CityGMLファイルをパースする
 * @param gmlFile
 * @code{
 * <osm>
 *   <bounds minlat='35.4345061' minlon='139.410131' maxlat='35.4347116' maxlon='139.4104367' origin='CGImap 0.8.3 (2061540 spike-08.openstreetmap.org)' />
 *         :
 * </osm>
 * }
 * 
 */
public class ElementBounds {
	public String minlat = null;
	public String minlon = null;
	public String maxlat = null;
	public String maxlon = null;
	public String origin = "CGImap 0.8.3 (2061540 spike-08.openstreetmap.org)";
    
	/*
	 * 
	 	<bounds minlat='35.4345061' minlon='139.410131' 
	 		maxlat='35.4347116' maxlon='139.4104367' 
	 		origin='CGImap 0.8.3 (2061540 spike-08.openstreetmap.org)' />
	 */
    public Node toNode(Document doc) {
		Element node = doc.createElement("bounds");
        node.setAttribute("minlat", minlat);
        node.setAttribute("minlon", minlon);
        node.setAttribute("maxlat", maxlat);
        node.setAttribute("maxlon", maxlon);
        node.setAttribute("origin", origin);
        return (Node)node;
    }
}
