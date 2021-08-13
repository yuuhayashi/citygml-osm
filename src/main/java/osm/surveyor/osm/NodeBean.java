package osm.surveyor.osm;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @code{
 * <osm>
 *  <node id='-107065' action='modify' visible='true' lat='35.43464696576' lon='139.4102145808' />
 *  <node id='1803576119' timestamp='2012-06-27T05:23:26Z' uid='618878' user='nakanao' visible='true' version='1' changeset='12032994' lat='35.5420516' lon='139.7149118' />
 *         :
 * </osm>
 * }
 * 
 */
@XmlRootElement(name="node")
public class NodeBean extends PoiBean implements Cloneable,Serializable {
	private static final long serialVersionUID = -6012637985828366692L;

	private String lat;
	private String lon;
	
	@XmlAttribute(name="lat")
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}

	@XmlAttribute(name="lon")
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((lat == null) ? 0 : lat.hashCode());
		result = prime * result + ((lon == null) ? 0 : lon.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		NodeBean other = (NodeBean) obj;
		if (lat == null) {
			if (other.lat != null)
				return false;
		} else if (!lat.equals(other.lat))
			return false;
		if (lon == null) {
			if (other.lon != null)
				return false;
		} else if (!lon.equals(other.lon))
			return false;
		return true;
	}

	@Override
	public NodeBean clone() {
		NodeBean b = (NodeBean) super.clone();
		b.setLat(this.getLat());
		b.setLon(this.getLon());
		return b;
	}

}
