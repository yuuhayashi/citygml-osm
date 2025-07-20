package osm.surveyor.osm;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import osm.surveyor.gis.point.NdModel;

/**
 * @code{
 *    <nd ref='-3'/>
 *         :
 * }
 */
@XmlRootElement(name="nd")
public class NdBean extends NdModel implements Serializable, Cloneable {
	private static final long serialVersionUID = 4936895079170613027L;

	@XmlAttribute(name="ref")
	public long getRef() {
		return super.getRef();
	}
	
	public void setRef(long ref) {
		super.setRef(ref);
	}
}
