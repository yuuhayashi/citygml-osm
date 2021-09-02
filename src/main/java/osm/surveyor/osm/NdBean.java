package osm.surveyor.osm;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @code{
 *    <nd ref='-3'/>
 *         :
 * }
 */
@XmlRootElement(name="nd")
public class NdBean implements Serializable {
	private static final long serialVersionUID = 4936895079170613027L;

	private Long ref;
	
	@XmlAttribute(name="ref")
	public long getRef() {
		return ref.longValue();
	}
	
	public void setRef(long ref) {
		this.ref = new Long(ref);
	}
	
}
