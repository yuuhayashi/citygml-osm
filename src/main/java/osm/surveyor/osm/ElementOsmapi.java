package osm.surveyor.osm;

import org.w3c.dom.Element;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * CityGMLファイルをパースする
 * @param gmlFile
 * @code{
 * <node id='-107065' action='modify' visible='true' lat='35.43464696576' lon='139.4102145808' />
 * <node id='1803576119' timestamp='2012-06-27T05:23:26Z' uid='618878' user='nakanao' visible='true' version='1' changeset='12032994' lat='35.5420516' lon='139.7149118' />
 * }
 * 
 */
public class ElementOsmapi implements Cloneable {
	public long id = 0;
	public String action = null;
	public String timestamp = null;
	public String uid = null;
	public String user = null;
	public boolean visible = true;
	public String version = null;
	public String changeset = null;
	
	public ElementOsmapi(long id) {
		this.id = id;
	}
	
	@Override
	public ElementOsmapi clone() {
		ElementOsmapi copy = null;
		try {
			copy = (ElementOsmapi)super.clone();
		}
		catch (Exception e) {
			e.printStackTrace();
			copy = null;
		}
		return copy;
	}
    
	/**
     * <node id='-107065' action='modify' visible='true' lat='35.43464696576' lon='139.4102145808' />
     * <node id='1803576119' timestamp='2012-06-27T05:23:26Z' uid='618878' user='nakanao' visible='true' version='1' changeset='12032994' lat='35.5420516' lon='139.7149118' />
     * @param doc
     * @return
     */
    public Element toElement(Document doc, String elementName) {
		Element node = (Element) doc.createElement(elementName);
        node.setAttribute("id", Long.toString(id));
        if (action != null) {
            node.setAttribute("action", action);
        }
        if (timestamp != null) {
            node.setAttribute("timestamp", timestamp);
        }
        if (uid != null) {
            node.setAttribute("uid", uid);
        }
        if (user != null) {
            node.setAttribute("user", user);
        }
        node.setAttribute("visible", Boolean.toString(visible));
        if (version != null) {
            node.setAttribute("version", version);
        }
        if (changeset != null) {
            node.setAttribute("changeset", changeset);
        }
        doc.appendChild((Node) node);
        return node;
    }

    /**
     * <node id='-107065' action='modify' visible='true' lat='35.43464696576' lon='139.4102145808' />
     * <node id='1803576119' timestamp='2012-06-27T05:23:26Z' uid='618878' user='nakanao' visible='true' version='1' changeset='12032994' lat='35.5420516' lon='139.7149118' />
     * 
     * @param nNode		doc.getElementsByTagName("node")
     * @param nodes		結果を格納するMAP
     */
    public ElementOsmapi loadElement(Element eElement) {
		this.id = Long.parseLong(getAttri(eElement, "id"));
		this.action = getAttri(eElement, "action");
		this.timestamp = getAttri(eElement, "timestamp");
		this.uid = getAttri(eElement, "uid");
		this.user = getAttri(eElement, "user");
		this.visible = Boolean.valueOf(eElement.getAttribute("visible"));
		this.version = getAttri(eElement, "version");
		this.changeset = getAttri(eElement, "changeset");
		return this;
    }
    
    private String getAttri(Element eElement, String attrKey) {
		String str = eElement.getAttribute(attrKey);
		if (str != null && !str.isEmpty()) {
			return str;
		}
		return null;
    }

    
    //--------------------------------------

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((changeset == null) ? 0 : changeset.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		result = prime * result + (visible ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElementOsmapi other = (ElementOsmapi) obj;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (changeset == null) {
			if (other.changeset != null)
				return false;
		} else if (!changeset.equals(other.changeset))
			return false;
		if (id != other.id)
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		if (visible != other.visible)
			return false;
		return true;
	}
}