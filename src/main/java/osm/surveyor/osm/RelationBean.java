package osm.surveyor.osm;

import java.io.Serializable;
import java.util.ArrayList;
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
	public static final String RELATION = "relation";
	public static final String MULTIPOLYGON = "multipolygon";

    private List<MemberBean> memberList = new ArrayList<>();
    
    @XmlElement(name="member")
    public List<MemberBean> getMemberList() {
    	return memberList;
    }

    public void setMemberList(List<MemberBean> memberList) {
    	this.memberList = memberList;
    }
    
	public boolean isBuilding() {
		for (TagBean tag : this.getTagList()) {
			if (tag.k.equals("type")) {
				if (tag.v.equals("building")) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isMultipolygon() {
		for (TagBean tag : this.getTagList()) {
			if (tag.k.equals("type")) {
				if (tag.v.equals(RelationBean.MULTIPOLYGON)) {
					return true;
				}
			}
		}
		return false;
	}
}