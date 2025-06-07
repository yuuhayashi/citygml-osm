package osm.surveyor.osm;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

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
@XmlRootElement(name="bounds")
public class BoundsBean implements Cloneable,Serializable {
	private static final long serialVersionUID = -6421750776457411407L;

	@XmlAttribute(name="minlat")
	public String minlat = null;
	
	@XmlAttribute(name="minlon")
	public String minlon = null;
	
	@XmlAttribute(name="maxlat")
	public String maxlat = null;
	
	@XmlAttribute(name="maxlon")
	public String maxlon = null;
	
	@XmlAttribute(name="origin")
	public String origin = "CGImap 0.8.3 (2061540 spike-08.openstreetmap.org)";
    
	/*
	 * 
	 	<bounds minlat='35.4345061' minlon='139.410131' 
	 		maxlat='35.4347116' maxlon='139.4104367' 
	 		origin='CGImap 0.8.3 (2061540 spike-08.openstreetmap.org)' />
	 */
    
    public String getBbox() {
		return "bbox="+ minlon +","+ minlat +","+ maxlon +","+ maxlat;
    }
    
    //--------------------------------------

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((minlat == null) ? 0 : minlat.hashCode());
		result = prime * result + ((minlon == null) ? 0 : minlon.hashCode());
		result = prime * result + ((maxlat == null) ? 0 : maxlat.hashCode());
		result = prime * result + ((maxlon == null) ? 0 : maxlon.hashCode());
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
		return result;
	}
	
	/**
	 * Boundsを拡大する
	 * 
	 * @param other
	 * @return	拡大されたboundsを返す。拡大されなかった場合は null を返す
	 */
	public BoundsBean expand(BoundsBean other) {
		boolean change = false;
		if (Double.parseDouble(this.maxlat) < Double.parseDouble(other.maxlat)) {
			change = true;
			this.maxlat = other.maxlat;
		}
		if (Double.parseDouble(this.maxlon) < Double.parseDouble(other.maxlon)) {
			change = true;
			this.maxlon = other.maxlon;
		}
		if (Double.parseDouble(this.minlat) > Double.parseDouble(other.minlat)) {
			change = true;
			this.minlat = other.minlat;
		}
		if (Double.parseDouble(this.minlon) > Double.parseDouble(other.minlon)) {
			change = true;
			this.minlon = other.minlon;
		}
		if (change) {
			return this;
		}
		else {
			return null;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BoundsBean other = (BoundsBean) obj;
		if (!equalsItem(minlat, other.minlat)) {
			return false;
		}
		else if (!equalsItem(minlon, other.minlon)) {
			return false;
		}
		else if (!equalsItem(maxlat, other.maxlat)) {
			return false;
		}
		else if (!equalsItem(maxlon, other.maxlon)) {
			return false;
		}
		else if (!equalsItem(origin, other.origin)) {
			return false;
		}
		return true;
	}
	
	boolean equalsItem(String me, String other) {
		if ((me == null) && (other != null)) {
			return false;
		}
		else if (!me.equals(other)) {
			return false;
		}
		return true;
	}

	@Override
	public BoundsBean clone() {
		BoundsBean b = new BoundsBean();
		b.minlat = new String(this.minlat);
		b.minlon = new String(this.minlon);
		b.maxlat = new String(this.maxlat);
		b.maxlon = new String(this.maxlon);
		b.origin = new String(this.origin);
		return b;
	}
}
