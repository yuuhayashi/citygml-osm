package osm.surveyor.osm;

import java.io.Serializable;
import java.util.Date;
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
 *    <tag k='height' v='14.072000000000001' />
 *    <tag k='building:part' v='yes' />
 *  </way>
 *         :
 * </osm>
 * }
 * 
 */
@XmlRootElement(name="way")
public class WayBean implements Serializable {
	private static final long serialVersionUID = 5518601165141588723L;

	@XmlAttribute(name="id")
	private Long id;
	
	@XmlAttribute(name="action")
	private String action;
	
	@XmlAttribute(name="visible")
	private Boolean visible;
	
	@XmlAttribute(name="timestamp")
	private Date timestamp;
	
	@XmlAttribute(name="uid")
	private Long uid;
	
	@XmlAttribute(name="version")
	private Integer version;
	
	@XmlAttribute(name="changeset")
	private Long changeset;
	
	@XmlAttribute(name="user")
	private String user;
	
    private List<NdBean> ndList;
    
    @XmlElement(name="nd")
    public List<NdBean> getNdList() {
    	return ndList;
    }

    public void setNdList(List<NdBean> ndList) {
    	this.ndList = ndList;
    }
    
    private List<TagBean> tagList;
    
    @XmlElement(name="tag")
    public List<TagBean> getTagList() {
    	return tagList;
    }

    public void setTagList(List<TagBean> tagList) {
    	this.tagList = tagList;
    }
}
