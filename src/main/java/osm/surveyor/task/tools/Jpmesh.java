package osm.surveyor.task.tools;

import java.awt.geom.Rectangle2D;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.geotools.geometry.DirectPosition2D;

public class Jpmesh {

	/**
	 * 指定された位置の地域メッシュコードを取得する。
	 * @param lon	緯度、緯度の有効桁数は小数点以下７桁
	 * @param lat	経度、経度の有効桁数は小数点以下６桁
	 * @param level	取得するメッシュコードのレベル[1,2,3]
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getMesh(double lon, double lat, int level) {
		if (level < 1) {
			return null;
		}

		// １次メッシュ
		BigDecimal lat0 = BigDecimal.valueOf(lat);
		BigDecimal lat1 = lat0.multiply(BigDecimal.valueOf(3)).divide(BigDecimal.valueOf(2));
		lat1 = lat1.setScale(6, BigDecimal.ROUND_HALF_UP);
		String y1 = get2(lat1);

		BigDecimal lon1 = BigDecimal.valueOf(lon).subtract(BigDecimal.valueOf(100));
		lon1 = lon1.setScale(7, BigDecimal.ROUND_HALF_UP);
		String x1 = get2(lon1);
		
		if (level < 2) {
			return (y1 + x1);
		}
		
		// 2次メッシュ
		BigDecimal lat2 = lat1.subtract(BigDecimal.valueOf(Integer.parseInt(y1)));
		lat2 = lat2.multiply(BigDecimal.valueOf(8));
		String y2 = get1(lat2);
		
		BigDecimal lon2 = lon1.subtract(BigDecimal.valueOf(Double.parseDouble(x1)));
		lon2 = lon2.multiply(BigDecimal.valueOf(8));
		String x2 = get1(lon2);
		
		if (level < 3) {
			return (y1 + x1 + y2 + x2);
		}
		
		// 3次メッシュ
		BigDecimal lat3 = lat2.subtract(BigDecimal.valueOf(Double.parseDouble(y2)));
		lat3 = lat3.multiply(BigDecimal.valueOf(10));
		String y3 = get1(lat3);
		
		BigDecimal lon3 = lon2.subtract(BigDecimal.valueOf(Double.parseDouble(x2)));
		lon3 = lon3.multiply(BigDecimal.valueOf(10));
		String x3 = get1(lon3);
		
		return (y1 + x1 + y2 + x2 + y3 + x3);
	}
	
	private static String get2(BigDecimal d) {
		DecimalFormat s2 = new DecimalFormat("##");
		s2.setRoundingMode(RoundingMode.DOWN);
		return s2.format(d);
	}

	private static String get1(BigDecimal d) {
		DecimalFormat s1 = new DecimalFormat("#");
		s1.setRoundingMode(RoundingMode.DOWN);
		return s1.format(d);
	}
	
	/**
	 * 地域メッシュコードの位置（南西角）を取得する。
	 * ただし、緯度の有効桁数は小数点以下７桁、経度の有効桁数は小数点以下６桁まで。
	 * @param str	地域メッシュコード（１～３次コード）
	 * @return	地域メッシュコードの南西角位置。変換できなかった場合にはNULL。
	 */
	public static DirectPosition2D getPosition(String str) {
		if (str != null) {
        	try {
        		Long.parseLong(str);
    			if (str.length() >= 4) {
    				double lat1 = Double.parseDouble(str.substring(0, 2));
    				double lon1 = Double.parseDouble(str.substring(2, 4));
    				double lat = lat1 * (2d / 3d) * 3600;
    				double lon = (lon1 + 100) * 3600;

        			if (str.length() >= 6) {
        				double lat2 = Double.parseDouble(str.substring(4, 5));
        				double lon2 = Double.parseDouble(str.substring(5, 6));
        				lat = lat + (lat2 * 300);
        				lon = lon + (lon2 * 450);
        			}
    				
        			if (str.length() >= 8) {
        				double lat3 = Double.parseDouble(str.substring(6, 7));
        				double lon3 = Double.parseDouble(str.substring(7, 8));
        				lat = lat + (lat3 * 30);
        				lon = lon + (lon3 * 45);
        			}
        			
    				return new DirectPosition2D((lon / 3600), (lat / 3600));
    			}
        	}
        	catch (NumberFormatException e) {
        		return null;
        	}
		}
		return null;
	}

	/**
	 * 地域メッシュコードの位置（中央）を取得する
	 * ただし、緯度の有効桁数は小数点以下７桁、経度の有効桁数は小数点以下６桁まで。
	 * @param str	地域メッシュコード（１～３次コード）
	 * @return	地域メッシュコードの中央位置。変換できなかった場合にはNULL。
	 */
	public static DirectPosition2D getCenterPosition(String str) {
		DirectPosition2D ret = getPosition(str);
		if (ret != null) {
			double lat = (ret.getY());
			double lon = (ret.getX());
			if (str.length() >= 8) {
				lat = (lat + (15.0d / 3600.0d));
				lon = (lon + (22.5d / 3600.0d));
			}
			else if (str.length() >= 6) {
				lat = (lat + (30.0d / 3600.0d));
				lon = (lon + (45.0d / 3600.0d));
			}
			else if (str.length() >= 4) {
				lat = (lat + (60.0d / 3600.0d));
				lon = (lon + (90.0d / 3600.0d));
			}
			return new DirectPosition2D(lon, lat);
		}
		return null;
	}
	
	/**
	 * 地域メッシュコードの矩形領域を取得する
	 * ただし、緯度の有効桁数は小数点以下７桁、経度の有効桁数は小数点以下６桁まで。
	 * @param str	地域メッシュコード（３次コード）
	 * @return	地域メッシュコードの矩形領域。変換できなかった場合にはNULL。
	 */
	public static Rectangle2D getRectangle(String str) {
		DirectPosition2D ret = getPosition(str);
		if (ret != null) {
			double lat = ret.getY();
			double lon = ret.getX();
			double lat2 = 0;
			double lon2 = 0;
			if (str.length() >= 8) {
				lat2 = 30.0d / 3600.0d;
				lon2 = 45.0d / 3600.0d;
			}
			else if (str.length() >= 6) {
				lat2 = 60.0d / 3600.0d;
				lon2 = 90.0d / 3600.0d;
			}
			else if (str.length() >= 4) {
				lat2 = 120.0d / 3600.0d;
				lon2 = 180.0d / 3600.0d;
			}
			return new Rectangle2D.Double(lon, lat, lon2, lat2);
		}
		return null;
	}
}
