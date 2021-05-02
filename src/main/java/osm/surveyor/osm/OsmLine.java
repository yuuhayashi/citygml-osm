package osm.surveyor.osm;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class OsmLine extends ArrayList<TwoPoint> {
	
	public OsmLine() {
		super();
	}

	public double getMaxLat() {
		double max = -90.0d;
		for (TwoPoint segment : this) {
			String lat = segment.a.point.lat;
			if (max < Double.parseDouble(lat)) {
				max = Double.parseDouble(lat);
			}
			lat = segment.b.point.lat;
			if (max < Double.parseDouble(lat)) {
				max = Double.parseDouble(lat);
			}
		}
		return max;
	}
}
