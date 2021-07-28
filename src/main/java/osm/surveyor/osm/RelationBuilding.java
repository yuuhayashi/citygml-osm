package osm.surveyor.osm;

/**
 * https://wiki.openstreetmap.org/wiki/JA:Relations/Proposed/Buildings
 *  type=building
 *   role:outline "1..*" --> ElementWay[area=yes]
 *   role:part "1..*" --> RelationMultipolygon or ElementWay[area=yes]
 */
public class RelationBuilding extends ElementRelation implements Cloneable {
	
	public RelationBuilding(long id) {
		super(id);
		addTag("type", "building");
	}
	
	public ElementRelation getMultiPolygon(OsmDom osm) {
		ElementMember outlineMember = getOutlineMember();
		if (outlineMember != null) {
			return osm.relations.get(outlineMember.ref);
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
		ElementMember outlineMember = getOutlineMember();
		if (outlineMember == null) {
			return null;
		}
		else {
			if (outlineMember.type.equals("way")) {
				return osm.ways.get(outlineMember.ref);
			}
			else if (outlineMember.type.equals("relation")) {
				ElementRelation polygon = osm.relations.get(outlineMember.ref);
				if (polygon != null) {
					for (ElementMember outlinemember : polygon.members) {
						if (outlinemember.role.equals("outer")) {
							return osm.ways.get(outlinemember.ref);
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
		for (ElementMember member : this.members) {
			if (member.role.equals("part") && member.type.equals("way")) {
				ElementWay way = osm.ways.get(member.ref);
				parts.put(way);
			}
		}
		
		RelationMultipolygon multi = (RelationMultipolygon)this.getMultiPolygon(osm);
		
		// 'source='
		this.addTag("source", osm.getSource());
		if (multi != null) {
			multi.addTag("source", osm.getSource());
		}
		
		// 'name='
		this.margeName(parts);
		if (multi != null) {
			multi.replaceTag("name", new ElementTag("building:name", this.getTagValue("name")));
		}

		// 'height' and 'ele'
		margeEleHeight(parts);
		if (multi != null) {
			multi.addTag("height", this.getTagValue("height"));
			multi.addTag("ele", this.getTagValue("ele"));
		}
		
		String maxLevels = this.getMaxValue(parts, "building:levels");
		if ((maxLevels != null) && !maxLevels.equals("0")) {
			this.addTag("building:levels", maxLevels);
			if (multi != null) {
				multi.addTag("building:levels", maxLevels);
			}
		}
		
		String maxLevelsUnderground = this.getMaxValue(parts, "building:levels:underground");
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
				multi.tags.remove("building:part");
			}
		}
		this.tags.remove("building:part");
		
		// 建築年
		String minyear = this.getMinValue(parts, "start_date");
		if (minyear != null) {
			this.addTag("start_date", minyear);
			if (multi != null) {
				multi.replaceTag("start_date", new ElementTag("start_date", this.getTagValue("start_date")));				
			}
		}

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
					double h = Double.parseDouble(ele) - Double.parseDouble(minele) + Double.parseDouble(hi);
					return Double.toString(h);
				}
			}
		}
	}
	
	void margeEleHeight(WayMap ways) {
		// 'height' and 'ele'
		String minele = this.getMinValue(ways, "ele");
		String maxele = null;
		for (ElementMember member : this.members) {
			if (member.type.equals("way")) {
				ElementWay way = ways.get(member.ref);
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
		for (ElementMember member : this.members) {
			if (member.type.equals("way")) {
				ElementWay way = ways.get(member.ref);
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
		for (ElementMember member : this.members) {
			if (member.role.equals("part") && member.type.equals("way")) {
				ElementWay way = ways.get(member.ref);
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
		for (ElementMember member : this.members) {
			if (member.role.equals("part") && member.type.equals("way")) {
				ElementWay way = ways.get(member.ref);
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
		for (ElementMember member : this.members) {
			if (member.type.equals("way")) {
				ElementWay way = ways.get(member.ref);
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
