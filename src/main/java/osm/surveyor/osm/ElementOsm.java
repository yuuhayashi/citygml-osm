package osm.surveyor.osm;

import javax.xml.parsers.ParserConfigurationException;
import org.jdom.Element;
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
public class ElementOsm extends Element {
	private static final long serialVersionUID = -1848531320972472013L;
	public String version = "0.6";
	public String generator = "JOSM";
	
	public ElementOsm() {
		//DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		//document = documentBuilder.newDocument();
        //document.appendChild(toNode(document));
    }
	
	public Node toNode(Document document) throws ParserConfigurationException {
		Element osm = (Element) document.createElement("osm");
        osm.setAttribute("version", version);
        osm.setAttribute("generator", generator);
        return (Node)osm;
    }
}
