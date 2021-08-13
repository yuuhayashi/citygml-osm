package osm.surveyor.osm;

import java.io.Serializable;
import java.util.List;

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
public class RelationBean extends PoiBean implements Serializable {
	private static final long serialVersionUID = -4269249218172121296L;

    private List<MemberBean> memberList;
    
    @XmlElement(name="member")
    public List<MemberBean> getMemberList() {
    	return memberList;
    }

    public void setMemberList(List<MemberBean> memberList) {
    	this.memberList = memberList;
    }
    
}
