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
 * 	<relation id='-1654' action='modify' visible='true'>
 *	  <member ref='-1655' type='way' role='part' />
 *	  <tag k='type' v='building' />
 *	  <tag k='source:ref:id' v='BLD_57cd4ea7-0fb7-4b0e-a600-9982cf3b60ca' />
 *	  <tag k='addr:full' v='東京都大田区南六郷三丁目' />
 *	</relation>
 *         :
 * </osm>
 * }
 * 
 */
@XmlRootElement(name="relation")
public class RelationBean implements Serializable {

	private static final long serialVersionUID = -4269249218172121296L;

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
	
    private List<MemberBean> memberList;
    
    @XmlElement(name="member")
    public List<MemberBean> getMemberList() {
    	return memberList;
    }

    public void setNdList(List<MemberBean> memberList) {
    	this.memberList = memberList;
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
