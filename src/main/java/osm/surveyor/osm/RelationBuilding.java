package osm.surveyor.osm;

import java.math.BigDecimal;

import osm.surveyor.citygml.CityModelParser;

/**
 * https://wiki.openstreetmap.org/wiki/JA:Relations/Proposed/Buildings
 *  type=building
 *   role:outline "1..*" --> ElementWay[area=yes]
 *   role:part "1..*" --> RelationMultipolygon or ElementWay[area=yes]
 */
public class RelationBuilding extends ElementRelation implements Cloneable {
	private static final long serialVersionUID = 1L;

	public RelationBuilding(long id) {
		super(id);
		addTag("type", "building");
	}
	
	public ElementRelation getMultiPolygon(OsmDom osm) {
		MemberBean outlineMember = getOutlineMember();
		if (outlineMember != null) {
			return osm.relations.get(outlineMember.getRef());
		}
		return null;
	}
	
	/**
	 * "outline"WAYを取得する
	 * 前提： "outline"メンバーは一つのWAYにまとめられていること
	 * @param osm
	 * @return	"outline"がないときはNULL
	 */
	public ElementWay getOutlineWay(OsmDom osm) {
		MemberBean outlineMember = getOutlineMember();
		if (outlineMember == null) {
			return null;
		}
		else {
			if (outlineMember.getType().equals("way")) {
				return osm.ways.get(outlineMember.getRef());
			}
			else if (outlineMember.getType().equals("relation")) {
				ElementRelation polygon = osm.relations.get(outlineMember.getRef());
				if (polygon != null) {
					for (MemberBean outlinemember : polygon.members) {
						if (outlinemember.getRole().equals("outer")) {
							return osm.ways.get(outlinemember.getRef());
						}
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * "ele"と"height"を統合してリレーションに設定する
	 * "building:levels"と"building:levels:underground"を統合してリレーションに設定する
	 * @param relations
	 */
	public void margeTagValue(OsmDom osm) {
		WayMap parts = new WayMap();
		for (MemberBean member : this.members) {
			if (member.getRole().equals("part") && member.getType().equals("way")) {
				ElementWay way = osm.ways.get(member.getRef());
				parts.put(way);
			}
		}
		
		RelationMultipolygon multi = (RelationMultipolygon)this.getMultiPolygon(osm);
		
		// 'name='
		this.margeName(parts);
		if (multi != null) {
			multi.replaceTag("name", new TagBean("name", this.getTagValue("name")));
		}

		// 'height' and 'ele'
		margeEleHeight(parts);
		if (multi != null) {
			multi.addTag("height", CityModelParser.rounding(1, this.getTagValue("height")));
			multi.addTag("ele", this.getTagValue("ele"));
		}
		
		String maxLevels = this.getMaxValue(parts, "building:levels");
		if ((maxLevels != null) && !maxLevels.equals("0")) {
			this.addTag("building:levels", maxLevels);
			if (multi != null) {
				multi.addTag("building:levels", maxLevels);
			}
		}
		
		String maxLevelsUnderground = this.getMaxValue(parts, "building:levels:underground");	// Issue #38
		if ((maxLevelsUnderground != null) && !maxLevelsUnderground.equals("0")) {
			this.addTag("building:levels:underground", maxLevelsUnderground);
			if (multi != null) {
				multi.addTag("building:levels:underground", maxLevelsUnderground);
			}
		}
		
		// 用途
		ElementWay maxway = this.getMaxArea(parts);
		if (maxway != null) {
			this.addTag("building", maxway.getTagValue("building:part"));
			if (multi != null) {
				multi.addTag("building", maxway.getTagValue("building:part"));
				multi.removeTag("building:part");
			}
		}
		this.removeTag("building:part");
		
		// 建築年はリレーションに反映させない
		// [Issue39](https://github.com/yuuhayashi/citygml-osm/issues/39)
		this.removeTag("start_date");

		// 地上階
		String maxup = this.getMaxValue(parts, "building:levels");
		if (maxup != null) {
			this.addTag("building:levels", maxup);
			if (multi != null) {
				multi.addTag("building:levels", maxup);
			}
		}

		// 地下階
		String maxdown = this.getMaxValue(parts, "building:levels:underground");
		if (maxup != null) {
			this.addTag("building:levels:underground", maxdown);
			if (multi != null) {
				multi.addTag("building:levels:underground", maxdown);
			}
		}
	}
	
	String calcHeight(String minele, String ele, String hi) {
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
					return CityModelParser.rounding(2, (new BigDecimal(ele)).subtract(new BigDecimal(minele)).add(new BigDecimal(hi)).toString());
				}
			}
		}
	}
	
	void margeEleHeight(WayMap ways) {
		// 'height' and 'ele'
		String minele = this.getMinValue(ways, "ele");
		String maxele = null;
		for (MemberBean member : this.members) {
			if (member.getType().equals("way")) {
				ElementWay way = ways.get(member.getRef());
				String height = calcHeight(minele, way.getTagValue("ele"), way.getTagValue("height"));
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
	public void margeName(WayMap ways) {
		String tagkey = "name";
		String maxname = this.getLongerValue(ways, tagkey);
		if (!maxname.isEmpty()) {
			this.addTag(tagkey, maxname);
		}
	}
	
	String getLongerValue(WayMap ways, String tagkey) {
		String maxname = "";
		for (MemberBean member : this.members) {
			if (member.getType().equals("way")) {
				ElementWay way = ways.get(member.getRef());
				String name = way.getTagValue(tagkey);
				if ((name != null) && (name.length() > maxname.length())) {
					maxname = name;
				}
			}
		}
		return maxname;
	}
	
	
	/**
	 * 所属メンバーの中から、指定したタグの最小値を取得する
	 * タグの形式は数値型のみ
	 * @param ways	メンバーの実態
	 * @param k		指定するタグのk
	 * @return	指定したタグが存在しない場合はNULL
	 */
	public String getMinValue(WayMap ways, String k) {
		String min = null;
		for (MemberBean member : this.members) {
			if (member.getRole().equals("part") && member.getType().equals("way")) {
				ElementWay way = ways.get(member.getRef());
				String v = way.getTagValue(k);
				if (v != null) {
					if (min == null) {
						min = v;
					}
					else {
						if (Double.parseDouble(min) > Double.parseDouble(v)) {
							min = v;
						}
					}
				}
			}
		}
		return min;
	}
	
	/**
	 * 所属メンバーの中から、指定したタグの最大値を取得する
	 * タグの形式は数値型のみ
	 * @param ways	メンバーの実態
	 * @param k		指定するタグのk
	 * @return	指定したタグが存在しない場合はNULL
	 */
	public String getMaxValue(WayMap ways, String k) {
		String max = null;
		for (MemberBean member : this.members) {
			if (member.getRole().equals("part") && member.getType().equals("way")) {
				ElementWay way = ways.get(member.getRef());
				String v = way.getTagValue(k);
				if (v != null) {
					if (max == null) {
						max = v;
					}
					else {
						if (Double.parseDouble(max) < Double.parseDouble(v)) {
							max = v;
						}
					}
				}
			}
		}
		return max;
	}
	
	/**
	 * 所属メンバーの中から、最大面積のAREAを取得する
	 * @param ways	メンバーの実態
	 * @return	指定したタグが存在しない場合はNULL
	 */
	public ElementWay getMaxArea(WayMap ways) {
		ElementWay max = null;
		double maxarea = 0.0d;
		for (MemberBean member : this.members) {
			if (member.getType().equals("way")) {
				ElementWay way = ways.get(member.getRef());
				double area = way.getArea();
				if (max == null) {
					max = way;
					maxarea = area;
				}
				else {
					if (maxarea < area) {
						max = way;
						maxarea = area;
					}
				}
			}
		}
		return max;
	}
}
