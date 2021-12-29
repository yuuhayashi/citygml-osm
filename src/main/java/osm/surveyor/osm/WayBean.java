package osm.surveyor.osm;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
public class WayBean extends PoiBean implements Serializable {
	private static final long serialVersionUID = 5518601165141588723L;
	
	/**
	 * fix=true 更新しないもの、fix=false 更新対象を示す。
	 */
	private boolean fix = false;
	
	@XmlAttribute(name="fix")
	public boolean getFix() {
		return this.fix;
	}
	public void setFix(boolean b) {
		this.fix = b;
	}

    private List<NdBean> ndList;
    
    @XmlElement(name="nd")
    public List<NdBean> getNdList() {
    	return this.ndList;
    }

    public void setNdList(List<NdBean> ndList) {
    	this.ndList = ndList;
    }
    
	public boolean isBuilding() {
		for (TagBean tag : this.getTagList()) {
			if (tag.k.startsWith("building")) {
				return true;
			}
		}
		return false;
	}
}
