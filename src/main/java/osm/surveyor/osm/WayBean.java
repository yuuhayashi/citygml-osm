package osm.surveyor.osm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import osm.surveyor.osm.way.WayModel;

/**
 * @code{
 * <osm>
 *  <way id='96350144'
 *   timestamp='2018-08-25T08:34:33Z'
 *   uid='7548722' user='Unnown' visible='true' version='17' changeset='61979354'/>
 *  <way id='-2' action='modify' visible='true'>
 *    <nd ref='-3'/>
 *    <nd ref='-4'/>
 *    <nd ref='-5'/>
 *    <tag k='height' v='14.07' />
 *    <tag k='building:part' v='yes' />
 *  </way>
 *         :
 * </osm>
 * }
 * 
 */
@XmlRootElement(name="way")
public class WayBean extends WayModel implements Cloneable, Serializable {
	private static final long serialVersionUID = 5518601165141588723L;
	
	public WayBean() {
		this(0);
	}
	
	public WayBean(long id) {
		super(id);
	}
	
	public PoiBean getPoiBean() {
		return (PoiBean)this;
	}

	/**
	 * WAYノードメンバー
	 */
	@XmlElement(name="nd")
    public void setNdList(List<NdBean> ndList) {
		super.setNdList(ndList);
    }
    public List<NdBean> getNdList() {
    	return super.getNdList();
    }
    	
    //------ WayBean original methods -------------------
    
	//--------Cloneable methods -----------------------------

	@Override
	public WayBean clone() {
		WayBean copy = null;
		try {
			copy = (WayBean) super.clone();
			ArrayList<NdBean> nds = new ArrayList<>();
			for (NdBean nd : getNdList()) {
				nds.add((NdBean) nd.clone());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			copy = null;
		}
		return copy;
	}
}
