package osm.surveyor.osm;

import org.w3c.dom.Element;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import osm.surveyor.citygml.CityModelParser;

/**
 * CityGMLファイルをパースする
 * @param gmlFile
 * @code{
 * <osm>
 *    <tag k='height' v='14.072000000000001' />
 *    <tag k='building:part' v='yes' />
 *         :
 * </osm>
 * }
 * 
 */
@XmlRootElement(name="tag")
public class TagBean implements Cloneable,Serializable {

	private static final long serialVersionUID = 8620019014904386928L;
	
	@XmlAttribute(name="k")
	public String k = null;

	@XmlAttribute(name="v")
	public String v = null;
	
	public TagBean() {
		this(null, null);
	}

	public TagBean(String key, String value) {
		this.k = key;
		this.v = value;
	}

	@Override
	public TagBean clone() {
		try {
			return (TagBean)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@XmlTransient
	public String getKey() {
		return k;
	}
	
	public void setKey(String k) {
		this.k = k;
	}
	
	@XmlTransient
	public String getValue() {
		return v;
	}
	
	public void setValue(String v) {
		this.v = v;
	}
	
    /*
		<tag k='height' v='14.072000000000001' />
     */
    public Node toNodeNd(Document doc) {
		Element node = (Element) doc.createElement("tag");
        node.setAttribute("k", k);
        node.setAttribute("v", v);
        return (Node)node;
    }
    
    public TagBean loadElement(Element eElement) {
		if (eElement.getNodeName().equals("tag")) {
			String k = eElement.getAttribute("k");
			String v = eElement.getAttribute("v");
			if ((k != null) && !k.isEmpty()) {
				this.k = k;
				if ((v != null) && !v.isEmpty()) {
					this.v = v;
					if (k.equals("height")) {
						if (CityModelParser.checkNumberString(v) == null) {
							return null;
						}
					}
				}
				else {
					this.v = null;
				}
				return this;
			}
	    }
		return null;
    }    
}
