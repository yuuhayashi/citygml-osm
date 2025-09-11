package osm.surveyor.osm;

import org.w3c.dom.Element;

import java.io.Serializable;
import java.util.*;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import osm.surveyor.osm.tag.Tagable;
import osm.surveyor.citygml.CityModelParser;

/**
 * CityGMLファイルをパースする
 * @param gmlFile
 * @code{
 * <POI id='-107065' action='modify' visible='true' />
 * <POI id='1803576119' timestamp='2012-06-27T05:23:26Z' uid='618878' user='nakanao' visible='true' version='1' changeset='12032994' />
 * }
 * 
 */
public class PoiBean implements Cloneable,Serializable, Tagable {
	private static final long serialVersionUID = 1L;

	private long id;
	private String action = null;
	private boolean visible = true;
	private String timestamp;
	private String uid;
	private String user;
	private String version;
	private String changeset;
    private List<TagBean> tags = new ArrayList<>();
    
	@XmlTransient
	public boolean orignal = false;

	public PoiBean() {
		this(0);
	}

    public PoiBean(long id) {
		this.id = id;
	}
	
	@XmlAttribute(name="id")
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@XmlAttribute(name="action")
	public String getAction() {
		return this.action;
	}
	public void setAction(String str) {
		this.action = str;
	}

	@XmlAttribute(name="visible")
	public boolean getVisible() {
		return this.visible;
	}

	public void setVisible(boolean bool) {
		this.visible = bool;
	}

	public void setVisible(String boolStr) {
		setVisible(Boolean.parseBoolean(boolStr));
	}

	@XmlAttribute(name="timestamp")
	public String getTimestamp() {
		return this.timestamp;
	}
	public void setTimestamp(String str) {
		this.timestamp = str;
	}

	@XmlAttribute(name="uid")
	public String getUid() {
		return this.uid;
	}
	public void setUid(String str) {
		this.uid = str;
	}

	@XmlAttribute(name="user")
	public String getUser() {
		return this.user;
	}
	public void setUser(String str) {
		this.user = str;
	}
	
	@XmlAttribute(name="version")
	public String getVersion() {
		return this.version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

	@XmlAttribute(name="changeset")
	public String getChangeset() {
		return this.changeset;
	}
	public void setChangeset(String str) {
		this.changeset = str;
	}

    @XmlElement(name="tag")
    public List<TagBean> getTagList() {
    	return this.tags;
    }

    public void setTagList(List<TagBean> tagList) {
    	this.tags = tagList;
    }

	@XmlTransient
	public String getIdstr() {
		return Long.toString(getId());
	}

	public void setIdstr(String id) {
		setId(Long.parseLong(id));
	}

	@Override
	public PoiBean clone() {
		PoiBean copy = null;
		try {
			copy = (PoiBean)super.clone();
			copy.id = this.id;
			copy.action = (this.action==null ? null : new String(this.action));
			copy.visible = this.visible;
			copy.timestamp = (this.timestamp==null ? null : new String(this.timestamp));
			copy.uid = (this.uid==null ? null : new String(this.uid));
			copy.user = (this.user==null ? null : new String(this.user));
			copy.version = (this.version==null ? null : new String(this.version));
			copy.changeset = (this.changeset==null ? null : new String(this.changeset));
			copy.tags = new ArrayList<>();
			for (TagBean tag : this.tags) {
				copy.tags.add(tag.clone());
			}
			copy.orignal = this.orignal;
		}
		catch (Exception e) {
			e.printStackTrace();
			copy = null;
		}
		return copy;
	}
	
	public PoiBean copy(long newid) {
		PoiBean copy = this.clone();
		copy.id = newid;
		return copy;
	}
    
	//---------------------
	
	/**
	 * タグを取り込む
	 * @param tags
	 * @param dest
	 */
	public void copyTag(PoiBean source) {
		if (source.tags == null) {
			return;
		}
		for (TagBean t : source.tags) {
			TagBean tag = t.clone();
			addTag(tag);
		}
	}

	/**
     * keyと一致するTAGを、destに置き換える
     * @param key
     * @param dest
     */
    public void replaceTag(String key, TagBean dest) {
		TagBean tag = getTag(key);
		if (tag == null) {
			return;
		}
		tags.remove(tag);
		if (dest != null) {
			if (dest.v == null) {
				tags.remove(tag);
			}
			else {
				tags.add(dest);
			}
		}
    }
    
	/**
	 * オブジェクトマップの中から、指定したタグの最大値を取得する
	 * タグの形式は数値型のみ
	 * @param poiMap	対象範囲
	 * @param k		指定するタグのk
	 * @return	指定したタグが存在しない場合はNULL
	 */
	public String getMaxValue(Map<String,PoiBean> poiMap, String k) {
		String max = null;
		for (PoiBean poi : poiMap.values()) {
			String v = poi.getTagValue(k);
			if (v != null) {
				if (max == null) {
					max = v;
				}
				else if (Double.parseDouble(max) < Double.parseDouble(v)) {
					max = v;
				}
			}
		}
		return max;
	}
	
	/**
	 * オブジェクトマップの中から、指定したタグの最小値を取得する
	 * タグの形式は数値型のみ
	 * @param poiMap	対象メンバー
	 * @param k		指定するタグのk
	 * @return	指定したタグが存在しない場合はNULL
	 */
	public static String getMinValue(Map<String,PoiBean> poiMap, String k) {
		String min = null;
		for (PoiBean poi : poiMap.values()) {
			String v = poi.getTagValue(k);
			if (v != null) {
				if (min == null) {
					min = v;
				}
				else if (Double.parseDouble(min) > Double.parseDouble(v)) {
					min = v;
				}
			}
		}
		return min;
	}
	
	public void margeTagset(Map<String,PoiBean> poiMap) {
		// 'name='
		this.margeName(poiMap);

		// 'height' and 'ele'
		this.margeEleHeight(poiMap);
		
		// 地上階 & 地下階
		String maxLevels = this.getMaxValue(poiMap, "building:levels");
		String maxup = this.getMaxValue(poiMap, "building:levels");
		if ((maxLevels != null) && !maxLevels.equals("0")) {
			this.addTag("building:levels", maxLevels);

			String maxLevelsUnderground = this.getMaxValue(poiMap, "building:levels:underground");	// Issue #38
			if ((maxLevelsUnderground != null) && !maxLevelsUnderground.equals("0")) {
				this.addTag("building:levels:underground", maxLevelsUnderground);
			}
		}
	}
	
	static String calcHeight(String minele, String ele, String hi) {
		if (hi == null) {
			return null;
		}
		else {
			if (minele == null) {
				return hi;
			}
			else {
				if (ele == null) {
					return hi;
				}
				else {
					try {
						return CityModelParser.rounding(2, (new BigDecimal(ele)).subtract(new BigDecimal(minele)).add(new BigDecimal(hi)).toString());
					}
					catch(Exception e) {
						return null;
					}
				}
			}
		}
	}
	
	void margeEleHeight(Map<String,PoiBean> poiMap) {
		// 'height' and 'ele'
		String minele = this.getMinValue(poiMap, "ele");
		String maxele = null;
		for (PoiBean poi : poiMap.values()) {
			String height = calcHeight(minele, poi.getTagValue("ele"), poi.getTagValue("height"));
			if (height != null) {
				if (maxele == null) {
					maxele = height;
				}
				else {
					if (Double.parseDouble(maxele) < Double.parseDouble(height)) {
						maxele = height;
					}
				}
			}
		}
		if (maxele != null) {
			this.addTag("height", maxele);
		}
		if (minele != null) {
			this.addTag("ele", minele);
		}
	}

	/**
	 * 
	 * @param building
	 * @param ways
	 */
	public void margeName(Map<String, PoiBean> poiMap) {
		String tagkey = "name";
		String maxname = this.getLongerValue(poiMap, tagkey);
		if (!maxname.isEmpty()) {
			this.addTag(tagkey, maxname);
		}
	}
	
	String getLongerValue(Map<String, PoiBean> poiMap, String tagkey) {
		String maxname = "";
		for (PoiBean poi : poiMap.values()) {
			String name = poi.getTagValue(tagkey);
			if ((name != null) && (name.length() > maxname.length())) {
				maxname = name;
			}
		}
		return maxname;
	}

	public void toBuilding() {
		String part = getTagValue("building:part");
		if (part != null) {
			TagBean building = new TagBean("building", part);
			replaceTag("building:part", building);
		}
		else {
			String buildingValue = getTagValue("building");
			if (buildingValue == null) {
				addTag("building", "yes");
			}
		}
	}

	public void toPart() {
		String buildingValue = getTagValue("building");
		if (buildingValue != null) {
			TagBean part = new TagBean("building:part", buildingValue);
			replaceTag("building", part);
		}
		else {
			String partValue = getTagValue("building:part");
			if (partValue == null) {
				addTag("building:part", "yes");
			}
		}
	}

    /**
     * マルチポリゴンメンバー用のタグに変更する
     * OUTER and INNER
     * 		- remove NOT "source=*"
     * 		- remove NOT "ref:MLIT_PLATEAU=*"
     * 		- remove NOT "fixme=*"
     */
    public void toMultipolygonMemberTag() {
    	List<TagBean> killList = new ArrayList<>();
    	for (TagBean tag : this.tags) {
    		switch (tag.k) {
    		case "source":
    			break;
    		case "ref:MLIT_PLATEAU":
    			break;
    		case "fixme":
    			break;
    		default:
    			killList.add(tag);
    		}	
    	}
    	for (TagBean tag : killList) {
    		removeTag(tag.k);
    	}
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
    public PoiBean loadElement(Element eElement) {
		this.id = Long.parseLong(getAttri(eElement, "id"));
		this.action = getAttri(eElement, "action");
		this.timestamp = getAttri(eElement, "timestamp");
		this.uid = getAttri(eElement, "uid");
		this.user = getAttri(eElement, "user");
		this.visible = Boolean.valueOf(eElement.getAttribute("visible"));
		this.version = getAttri(eElement, "version");
		this.changeset = getAttri(eElement, "changeset");
		
		NodeList list2 = eElement.getChildNodes();
	    for (int temp2 = 0; temp2 < list2.getLength(); temp2++) {
			Node node2 = list2.item(temp2);
			if (node2.getNodeType() == Node.ELEMENT_NODE) {
				Element e2 = (Element) node2;
				if (e2.getNodeName().equals("tag")) {
					TagBean tag = (new TagBean()).loadElement(e2);
					this.tags.add(tag);
				}
			}
	    }
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
		result = prime * result + (orignal ? 1231 : 1237);
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
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
		PoiBean other = (PoiBean) obj;
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
		if (orignal != other.orignal)
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
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
