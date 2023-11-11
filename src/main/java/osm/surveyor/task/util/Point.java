package osm.surveyor.task.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Point extends JsonNumberArray {
	
	private String lng = "0.0";
	
	private String lat = "0.0";
	
	public void setLng(String str) {
		this.lng = str;
	}
	
	public void setLng(double d) {
		setLng(new BigDecimal(d).setScale(5, RoundingMode.HALF_EVEN));
	}

	public String getLng() {
		return lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLng(BigDecimal dec) {
		this.lng = dec.toString();
	}

	public void setLat(String str) {
		this.lat = str;
	}

	public void setLat(double d) {
		setLat(new BigDecimal(d).setScale(6, RoundingMode.HALF_EVEN));
	}

	public void setLat(BigDecimal dec) {
		this.lat = dec.toString();
	}
	
	private void store() {
		List<String> list = new ArrayList<>();
		list.add(getLng());
		list.add(getLat());
		setList(list);
	}
	
	/*
	 * [141.35625,42.90416666666667]
	 */
	@Override
	public String toString() {
		store();
		return super.toString();
	}
	
	public void parse(JsonNode node) {
		if (node != null) {
			boolean one = false;
			for (JsonNode node2 : node) {
				if (!one) {
					setLng(node2.asText());
					one = true;
				}
				else {
					setLat(node2.asText());
				}
			}
		}
	}
	
	/*
	 *	{
	 *		"geometry":{
	 *			"coordinates":[141.35625,42.90416666666667],
	 *			"type":"Point"
	 *		},
	 *		"type":"Feature",
	 *		"properties":{
	 *			"path":"64412288_bldg_6697_op.zip",
	 *			"id":"64412288",
	 *			"version":"1.4.6"
	 *		}
	 *	}
	 */
	public String getGeometry() {
		store();
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"geometry\":{");
			sb.append("\"type\":\"Point\"");
			sb.append(",\"coordinates\":"+ toString());
			sb.append("}");
		sb.append("}");
		return sb.toString();
	}
}
