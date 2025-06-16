package osm.surveyor.osm;

import java.util.ArrayList;

import osm.surveyor.gis.point.NdModel;

@SuppressWarnings("serial")
public class OsmLine extends ArrayList<TwoPoint> {
	
	public OsmLine() {
		super();
	}

	public double getMaxLat() {
		double max = -90.0d;
		for (TwoPoint segment : this) {
			String lat = segment.a.point.getLat();
			if (max < Double.parseDouble(lat)) {
				max = Double.parseDouble(lat);
			}
			lat = segment.b.point.getLat();
			if (max < Double.parseDouble(lat)) {
				max = Double.parseDouble(lat);
			}
		}
		return max;
	}
	
	/**
	 * 同一ラインか？
	 * 逆向きラインも同一とみなす
	 * @param obj
	 * @return
	 */
	public boolean equal(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		OsmLine line = (OsmLine)obj;
		if (this.size() != line.size())
			return false;

		boolean ret = true;
		for (int i = 0; i < this.size(); i++) {
			TwoPoint p1 = this.get(i);
			TwoPoint p2 = line.get(i);
			if (!p1.equals(p2)) {
				ret = false;
				break;
			}
		}
		if (ret) {
			return true;
		}
		
		// 逆向きチェック
		for (int i1 = 0; i1 < this.size(); i1++) {
			int i2 = line.size() - i1;
			TwoPoint p1 = this.get(i1);
			TwoPoint p2 = line.get(i2 - 1);
			if (!p1.equals(p2)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 指定のNdModelを持っているかどうかを調べる
	 * @param nd
	 * @return
	 */
	boolean hasNd(NdModel nd) {
		for (TwoPoint seg : this) {
			if (seg.isConnectable(nd)) {
				return true;
			}
		}
		return false;
	}
}
