package osm.surveyor.osm;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * CityGMLファイルをパースする
 * @param gmlFile
 * @code{
 * <osm>
 *   <bounds minlat='35.4345061' minlon='139.410131' maxlat='35.4347116' maxlon='139.4104367' origin='CGImap 0.8.3 (2061540 spike-08.openstreetmap.org)' />
 *         :
 * </osm>
 * }
 * 
 */
@XmlRootElement(name="bounds")
public class ElementBounds implements Serializable {
	private static final long serialVersionUID = -6421750776457411407L;

	@XmlAttribute(name="minlat")
	public String minlat = null;
	
	@XmlAttribute(name="minlon")
	public String minlon = null;
	
	@XmlAttribute(name="maxlat")
	public String maxlat = null;
	
	@XmlAttribute(name="maxlon")
	public String maxlon = null;
	
	@XmlAttribute(name="origin")
	public String origin = "CGImap 0.8.3 (2061540 spike-08.openstreetmap.org)";
    
	/*
	 * 
	 	<bounds minlat='35.4345061' minlon='139.410131' 
	 		maxlat='35.4347116' maxlon='139.4104367' 
	 		origin='CGImap 0.8.3 (2061540 spike-08.openstreetmap.org)' />
	 */
    
    public String getBbox() {
		return "bbox="+ minlon +","+ minlat +","+ maxlon +","+ maxlat;
    }
}
