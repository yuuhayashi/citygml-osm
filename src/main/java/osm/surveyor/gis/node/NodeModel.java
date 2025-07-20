package osm.surveyor.gis.node;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.locationtech.jts.geom.Coordinate;

import osm.surveyor.gis.point.PointModel;
import osm.surveyor.osm.PoiBean;

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
public abstract class NodeModel extends PoiBean implements Cloneable,Serializable {
	private static final long serialVersionUID = -6012637985828366692L;
	
	public NodeModel(long id) {
		super(id);
	}
	
	public NodeModel() {
		this(0l);
	}

	private PointModel point = null;

	@XmlTransient
	public PointModel getPoint() {
		return point;
	}
	public void setPoint(PointModel point) {
		this.point = point;
	}
	public Coordinate getCoordinate() {
		return this.point.getCoordinate();
	}
	
	@XmlAttribute(name="lat")
	public String getLat() {
		return this.point.getLat();
	}
	public void setLat(String lat) {
		this.point.setLat(lat);
	}

	@XmlAttribute(name="lon")
	public String getLon() {
		return this.point.getLon();
	}
	public void setLon(String lon) {
		this.point.setLon(lon);
	}

    //--------------------------------------

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((point == null) ? 0 : point.hashCode());
		return result;
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
		NodeModel other = (NodeModel) obj;
		if (point == null) {
			if (other.point != null) {
				return false;
			}
		}
		else if (!point.equals(other.point)) {
			return false;
		}
		return true;
	}

	@Override
	public NodeModel clone() {
		NodeModel b = (NodeModel) super.clone();
		b.point = this.point.clone();
		return b;
	}
}
