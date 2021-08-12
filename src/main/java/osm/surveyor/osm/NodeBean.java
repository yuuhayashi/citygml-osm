package osm.surveyor.osm;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

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
public class NodeBean extends PoiBean implements Serializable {
	private static final long serialVersionUID = -6012637985828366692L;

	@XmlAttribute(name="lat")
	private String lat;
	
	@XmlAttribute(name="lon")
	private String lon;
}
