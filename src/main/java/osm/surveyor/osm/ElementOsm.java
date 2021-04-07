package osm.surveyor.osm;

import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * CityGMLファイルをパースする
 * @param gmlFile
 * @code{
 * <osm version='0.6' generator='JOSM'>
 *         :
 * </osm>
 * }
 */
public class ElementOsm {
	public String version = "0.6";
	public String generator = "JOSM";
	
	public Node toNode(Document document) throws ParserConfigurationException {
		Element osm = document.createElement("osm");
        osm.setAttribute("version", version);
        osm.setAttribute("generator", generator);
        return (Node)osm;
    }
}
