package osm.surveyor.osm;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * OSMファイルをパースする
 * @param gmlFile
 * @code{
 * }
 * 
 */
public class OsmParser extends DefaultHandler {
	OsmDom osm;
	
    public OsmParser(OsmDom osm) {
        super();
        this.osm = osm;
    }
    
	/**
     * ドキュメント開始
	 * @throws SAXException 
     */
    public void startDocument() throws SAXException {
    	super.startDocument();
    }
 
    /**
     * ドキュメント終了
     * @throws SAXException 
     */
    public void endDocument() throws SAXException {
    	super.endDocument();
    }
    
    ElementBounds bounds = null;					// <bounds/>
    NodeBean node = null;						// <node/>
    OsmNd nd = null;								// <nd/>
    TagBean tag = null;							// <tag/>
	ElementRelation relation = null;				// <relation/>
	MemberBean member = null;					// <gml:Polygon/>
    ElementWay way = null;							// <gml:LinearRing/>
	
    /**
     * 要素の開始タグ読み込み時に毎回呼ばれる
     */
    public void startElement(String uri,String localName, String qName, Attributes atts) {
    	if(qName.equals("osm")){
			osm.bounds = new ElementBounds();
			osm.nodes = new NodeBeans();
			osm.ways = new WayMap();
			osm.relations = new RelationMap();
		}
    	else if(qName.equals("bounds")){
    		bounds = new ElementBounds();
			bounds.maxlat = getAttributes("maxlat", atts);
			bounds.maxlon = getAttributes("maxlon", atts);
			bounds.minlat = getAttributes("minlat", atts);
			bounds.minlon = getAttributes("minlon", atts);
			bounds.origin = getAttributes("origin", atts);
		}
		else if(qName.equals("node")){
			node = new NodeBean(0);
			node.setAction(getAttributes("action", atts));
			node.setChangeset(getAttributes("changeset", atts));
			node.setIdstr(getAttributes("id", atts));
			node.setTimestamp(getAttributes("timestamp", atts));
			node.setUid(getAttributes("uid", atts));
			node.setUser(getAttributes("user", atts));
			node.setVisible(getAttributes("visible", atts));
			node.setVersion(getAttributes("version", atts));

			OsmPoint point = new OsmPoint();
			point.setLat(getAttributes("lat", atts));
			point.setLon(getAttributes("lon", atts));
			node.point = point;
		}
		else if(qName.equals("way")){
			way = new ElementWay(0);
			way.setAction(getAttributes("action", atts));
			way.setChangeset(getAttributes("changeset", atts));
			way.setIdstr(getAttributes("id", atts));
			way.setTimestamp(getAttributes("timestamp", atts));
			way.setUid(getAttributes("uid", atts));
			way.setUser(getAttributes("user", atts));
			way.setVisible(getAttributes("visible", atts));
			way.setVersion(getAttributes("version", atts));
		}
		else if(qName.equals("relation")){
			relation = new ElementRelation(0);
			relation.setAction(getAttributes("action", atts));
			relation.setChangeset(getAttributes("changeset", atts));
			relation.setIdstr(getAttributes("id", atts));
			relation.setTimestamp(getAttributes("timestamp", atts));
			relation.setUid(getAttributes("uid", atts));
			relation.setUser(getAttributes("user", atts));
			relation.setVisible(getAttributes("visible", atts));
			relation.setVersion(getAttributes("version", atts));
		}
		else if(qName.equals("nd")){
			nd = new OsmNd();
			nd.setIdstr(getAttributes("ref", atts));
		}
		else if(qName.equals("member")){
			member = new MemberBean();
			member.setRef(getAttributes("ref", atts));
			member.setRole(getAttributes("role", atts));
			member.setType(getAttributes("type", atts));
		}
		else if(qName.equals("tag")){
			tag = new TagBean();
			tag.k = getAttributes("k", atts);
			tag.v = getAttributes("v", atts);
		}
		else {
		}
	}

    /**
     * 要素の終了タグ読み込み時に毎回呼ばれる
     */
    public void endElement(String uri, String localName, String qName) {
		if (qName.equals("osm")){
		}
		else if(qName.equals("bounds")){
			osm.setBounds(bounds);
		}
		else if(qName.equals("node")){
			if (node != null) {
				osm.nodes.put(node.clone());
				node = null;
			}
		}
		else if(qName.equals("way")){
			if (way != null) {
				osm.ways.put(way.clone());
				way = null;
			}
		}
		else if(qName.equals("relation")){
			if (relation != null) {
				osm.relations.put(relation.clone());
				relation = null;
			}
		}
		else if(qName.equals("nd")){
			if (nd != null) {
				if (way != null) {
					NodeBean node = osm.nodes.get(nd.id);
					if (node != null) {
						way.addNode(node.clone());
					}
				}
				nd = null;
			}
		}
		else if(qName.equals("member")){
			if (member != null) {
				if (relation != null) {
					relation.members.add(member.clone());
				}
				member = null;
			}
		}
		else if(qName.equals("tag")){
			if (tag != null) {
				if (node != null) {
					node.addTag(tag.clone());
				}
				if (way != null) {
					way.addTag(tag.clone());
				}
				if (relation != null) {
					relation.addTag(tag.clone());
				}
				tag = null;
			}
		}
        else {
        }
    }
    
    String getAttributes(String key, Attributes atts) {
		for (int i = 0; i < atts.getLength(); i++) {
			String aname = atts.getQName(i);
			if (aname.equals(key)) {
				return (atts.getValue(i));
			}
		}
    	return null;
    }
}
