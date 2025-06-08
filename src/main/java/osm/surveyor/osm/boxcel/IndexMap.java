package osm.surveyor.osm.boxcel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import osm.surveyor.osm.BoundsBean;
import osm.surveyor.osm.ElementWay;
import osm.surveyor.osm.way.WayModel;

/**
 * hash key = cell.id
 * 
 */
public class IndexMap extends HashMap<Integer, BoundsCellBean> implements Cloneable, Serializable {
	
	private static final long serialVersionUID = 3201342709343657144L;
	static final int X = 16;
	static final int Y = 16;

	public void put(BoundsCellBean cell) {
		if (cell != null) {
			put(cell.getId(), cell);
		}
	}
	
	public BoundsCellBean get(Integer id) {
		return super.get(id);
	}
	
    public void remove(Integer id) {
    	remove(id);
    }

    public void remove(BoundsCellBean cell) {
    	remove(cell.getId());
    }
    
    public void removeWayBean(WayModel way) {
    	for (Map.Entry<Integer,BoundsCellBean> entry : this.entrySet()) {
    		BoundsCellBean cell = entry.getValue();
    		cell.removeWay(way);
    	}
    }
    
    public void putWayType(WayModel way) {
    	for (Map.Entry<Integer,BoundsCellBean> entry : this.entrySet()) {
    		BoundsCellBean cell = entry.getValue();
    		if (cell.getIntersectArea(way.getPolygon()) > 0.0d) {
    			way.addBoxel(cell.getId());
    			cell.putWay(way);
    		}
    	}
    }

    public void putElementWay(ElementWay way) {
    	for (Map.Entry<Integer,BoundsCellBean> entry : this.entrySet()) {
    		BoundsCellBean cell = entry.getValue();
    		if (cell.getIntersectArea(way.getPolygon()) > 0.0d) {
    			way.addBoxel(cell.getId());
    			cell.putWay(way);
    		}
    	}
    }
    
    
    public void setBounds(BoundsBean bounds) {
    	this.clear();
    	double maxlat = Double.parseDouble(bounds.maxlat);
    	double minlat = Double.parseDouble(bounds.minlat);
    	double maxlon = Double.parseDouble(bounds.maxlon);
    	double minlon = Double.parseDouble(bounds.minlon);
    	double dlat = (maxlat - minlat) / Y;
    	double dlon = (maxlon - minlon) / X;

    	int i = 0;
    	double lon = minlon;
    	for (int xx = 0; xx < X; xx++) {
        	double lat = minlat;
    		for (int yy = 0; yy < Y; yy++) {
    			this.put(new BoundsCellBean(Integer.valueOf(i), (lat + dlat), (lon + dlon), lat, lon));
    			lat += dlat;
    			i++;
    		}
    		lon += dlon;
    	}
    }
}
