<?xml version='1.0' encoding='UTF-8'?>
<core:CityModel xmlns:brid="http://www.opengis.net/citygml/bridge/2.0" xmlns:tran="http://www.opengis.net/citygml/transportation/2.0" xmlns:frn="http://www.opengis.net/citygml/cityfurniture/2.0" xmlns:wtr="http://www.opengis.net/citygml/waterbody/2.0" xmlns:sch="http://www.ascc.net/xml/schematron" xmlns:veg="http://www.opengis.net/citygml/vegetation/2.0" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:tun="http://www.opengis.net/citygml/tunnel/2.0" xmlns:tex="http://www.opengis.net/citygml/texturedsurface/2.0" xmlns:gml="http://www.opengis.net/gml" xmlns:app="http://www.opengis.net/citygml/appearance/2.0" xmlns:gen="http://www.opengis.net/citygml/generics/2.0" xmlns:dem="http://www.opengis.net/citygml/relief/2.0" xmlns:luse="http://www.opengis.net/citygml/landuse/2.0" xmlns:uro="https://www.geospatial.jp/iur/uro/3.1" xmlns:xAL="urn:oasis:names:tc:ciq:xsdschema:xAL:2.0" xmlns:bldg="http://www.opengis.net/citygml/building/2.0" xmlns:smil20="http://www.w3.org/2001/SMIL20/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:smil20lang="http://www.w3.org/2001/SMIL20/Language" xmlns:pbase="http://www.opengis.net/citygml/profiles/base/2.0" xmlns:core="http://www.opengis.net/citygml/2.0" xmlns:grp="http://www.opengis.net/citygml/cityobjectgroup/2.0" xsi:schemaLocation="https://www.geospatial.jp/iur/uro/3.1 ../../schemas/iur/uro/3.1/urbanObject.xsd  http://www.opengis.net/citygml/2.0 http://schemas.opengis.net/citygml/2.0/cityGMLBase.xsd http://www.opengis.net/citygml/landuse/2.0 http://schemas.opengis.net/citygml/landuse/2.0/landUse.xsd  http://www.opengis.net/citygml/building/2.0 http://schemas.opengis.net/citygml/building/2.0/building.xsd  http://www.opengis.net/citygml/transportation/2.0 http://schemas.opengis.net/citygml/transportation/2.0/transportation.xsd  http://www.opengis.net/citygml/generics/2.0 http://schemas.opengis.net/citygml/generics/2.0/generics.xsd  http://www.opengis.net/citygml/relief/2.0 http://schemas.opengis.net/citygml/relief/2.0/relief.xsd  http://www.opengis.net/citygml/cityobjectgroup/2.0 http://schemas.opengis.net/citygml/cityobjectgroup/2.0/cityObjectGroup.xsd  http://www.opengis.net/gml http://schemas.opengis.net/gml/3.1.1/base/gml.xsd http://www.opengis.net/citygml/appearance/2.0 http://schemas.opengis.net/citygml/appearance/2.0/appearance.xsd">
	<gml:boundedBy>
		<gml:Envelope srsName="http://www.opengis.net/def/crs/EPSG/0/6697" srsDimension="3">
			<gml:lowerCorner>34.616555554793614 135.51216873982492 0</gml:lowerCorner>
			<gml:upperCorner>34.62507442485661 135.52509604728158 56.88</gml:upperCorner>
		</gml:Envelope>
	</gml:boundedBy>

	<core:cityObjectMember>
		<bldg:Building gml:id="bldg_a0e57a54-6cd1-4971-a21e-24d6fce85adf">
			<core:creationDate>2023-03-22</core:creationDate>
			<bldg:class codeSpace="../../codelists/Building_class.xml">3001</bldg:class>
			<bldg:usage codeSpace="../../codelists/Building_usage.xml">461</bldg:usage>
			<bldg:measuredHeight uom="m">-9999</bldg:measuredHeight>
			<bldg:storeysAboveGround>9999</bldg:storeysAboveGround>
			<bldg:lod0FootPrint>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>34.62241613420931 135.51590234041402 0 34.622430531585266 135.51590464509346 0 34.62243400240284 135.51587283563285 0 34.62241959605465 135.51587054191634 0 34.62241613420931 135.51590234041402 0 </gml:posList>
								</gml:LinearRing>
							</gml:exterior>
						</gml:Polygon>
					</gml:surfaceMember>
				</gml:MultiSurface>
			</bldg:lod0FootPrint>
			<bldg:lod1Solid>
				<gml:Solid>
					<gml:exterior>
						<gml:CompositeSurface>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>34.62241613420931 135.51590234041402 7.61930000001 34.62241959605465 135.51587054191634 7.61930000001 34.62243400240284 135.51587283563285 7.61930000001 34.622430531585266 135.51590464509346 7.61930000001 34.62241613420931 135.51590234041402 7.61930000001</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>34.62241613420931 135.51590234041402 7.61930000001 34.622430531585266 135.51590464509346 7.61930000001 34.622430531585266 135.51590464509346 10.61930000001 34.62241613420931 135.51590234041402 10.61930000001 34.62241613420931 135.51590234041402 7.61930000001</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>34.622430531585266 135.51590464509346 7.61930000001 34.62243400240284 135.51587283563285 7.61930000001 34.62243400240284 135.51587283563285 10.61930000001 34.622430531585266 135.51590464509346 10.61930000001 34.622430531585266 135.51590464509346 7.61930000001</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>34.62243400240284 135.51587283563285 7.61930000001 34.62241959605465 135.51587054191634 7.61930000001 34.62241959605465 135.51587054191634 10.61930000001 34.62243400240284 135.51587283563285 10.61930000001 34.62243400240284 135.51587283563285 7.61930000001</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>34.62241959605465 135.51587054191634 7.61930000001 34.62241613420931 135.51590234041402 7.61930000001 34.62241613420931 135.51590234041402 10.61930000001 34.62241959605465 135.51587054191634 10.61930000001 34.62241959605465 135.51587054191634 7.61930000001</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>34.62241613420931 135.51590234041402 10.61930000001 34.622430531585266 135.51590464509346 10.61930000001 34.62243400240284 135.51587283563285 10.61930000001 34.62241959605465 135.51587054191634 10.61930000001 34.62241613420931 135.51590234041402 10.61930000001</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
						</gml:CompositeSurface>
					</gml:exterior>
				</gml:Solid>
			</bldg:lod1Solid>
			<uro:buildingDetailAttribute>
				<uro:BuildingDetailAttribute>
					<uro:buildingRoofEdgeArea uom="m2">-9999</uro:buildingRoofEdgeArea>
					<uro:buildingStructureType codeSpace="../../codelists/BuildingDetailAttribute_buildingStructureType.xml">611</uro:buildingStructureType>
					<uro:buildingStructureOrgType codeSpace="../../codelists/BuildingDetailAttribute_buildingStructureOrgType.xml">9</uro:buildingStructureOrgType>
					<uro:detailedUsage codeSpace="../../codelists/BuildingDetailAttribute_detailedUsage.xml">461</uro:detailedUsage>
					<uro:groundFloorUsage codeSpace="../../codelists/BuildingDetailAttribute_groundFloorUsage.xml">9999</uro:groundFloorUsage>
					<uro:secondFloorUsage codeSpace="../../codelists/BuildingDetailAttribute_secondFloorUsage.xml">9999</uro:secondFloorUsage>
					<uro:thirdFloorUsage codeSpace="../../codelists/BuildingDetailAttribute_thirdFloorUsage.xml">9999</uro:thirdFloorUsage>
					<uro:basementFirstUsage codeSpace="../../codelists/BuildingDetailAttribute_basementFirstUsage.xml">9999</uro:basementFirstUsage>
					<uro:basementSecondUsage codeSpace="../../codelists/BuildingDetailAttribute_basementSecondUsage.xml">9999</uro:basementSecondUsage>
					<uro:specifiedBuildingCoverageRate>80</uro:specifiedBuildingCoverageRate>
					<uro:surveyYear>2017</uro:surveyYear>
				</uro:BuildingDetailAttribute>
			</uro:buildingDetailAttribute>
			<uro:buildingIDAttribute>
				<uro:BuildingIDAttribute>
					<uro:buildingID>27100-bldg-80286</uro:buildingID>
					<uro:branchID>1</uro:branchID>
					<uro:prefecture codeSpace="../../codelists/Common_localPublicAuthorities.xml">27</uro:prefecture>
					<uro:city codeSpace="../../codelists/Common_localPublicAuthorities.xml">27119</uro:city>
				</uro:BuildingIDAttribute>
			</uro:buildingIDAttribute>
			<uro:bldgDataQualityAttribute>
				<uro:DataQualityAttribute>
					<uro:geometrySrcDescLod0 codeSpace="../../codelists/DataQualityAttribute_geometrySrcDesc.xml">000</uro:geometrySrcDescLod0>
					<uro:geometrySrcDescLod1 codeSpace="../../codelists/DataQualityAttribute_geometrySrcDesc.xml">000</uro:geometrySrcDescLod1>
					<uro:geometrySrcDescLod2 codeSpace="../../codelists/DataQualityAttribute_geometrySrcDesc.xml">999</uro:geometrySrcDescLod2>
					<uro:geometrySrcDescLod3 codeSpace="../../codelists/DataQualityAttribute_geometrySrcDesc.xml">999</uro:geometrySrcDescLod3>
					<uro:thematicSrcDesc codeSpace="../../codelists/DataQualityAttribute_thematicSrcDesc.xml">000</uro:thematicSrcDesc>
					<uro:thematicSrcDesc codeSpace="../../codelists/DataQualityAttribute_thematicSrcDesc.xml">100</uro:thematicSrcDesc>
					<uro:thematicSrcDesc codeSpace="../../codelists/DataQualityAttribute_thematicSrcDesc.xml">201</uro:thematicSrcDesc>
					<uro:thematicSrcDesc codeSpace="../../codelists/DataQualityAttribute_thematicSrcDesc.xml">400</uro:thematicSrcDesc>
					<uro:appearanceSrcDescLod2 codeSpace="../../codelists/DataQualityAttribute_appearanceSrcDesc.xml">99</uro:appearanceSrcDescLod2>
					<uro:appearanceSrcDescLod3 codeSpace="../../codelists/DataQualityAttribute_appearanceSrcDesc.xml">99</uro:appearanceSrcDescLod3>
					<uro:lod1HeightType codeSpace="../../codelists/DataQualityAttribute_lod1HeightType.xml">0</uro:lod1HeightType>
					<uro:publicSurveyDataQualityAttribute>
						<uro:PublicSurveyDataQualityAttribute>
							<uro:srcScaleLod0 codeSpace="../../codelists/PublicSurveyDataQualityAttribute_srcScale.xml">1</uro:srcScaleLod0>
							<uro:srcScaleLod1 codeSpace="../../codelists/PublicSurveyDataQualityAttribute_srcScale.xml">1</uro:srcScaleLod1>
							<uro:publicSurveySrcDescLod0 codeSpace="../../codelists/PublicSurveyDataQualityAttribute_publicSurveySrcDesc.xml">023</uro:publicSurveySrcDescLod0>
							<uro:publicSurveySrcDescLod1 codeSpace="../../codelists/PublicSurveyDataQualityAttribute_publicSurveySrcDesc.xml">023</uro:publicSurveySrcDescLod1>
						</uro:PublicSurveyDataQualityAttribute>
					</uro:publicSurveyDataQualityAttribute>
				</uro:DataQualityAttribute>
			</uro:bldgDataQualityAttribute>
			<uro:bldgKeyValuePairAttribute>
				<uro:KeyValuePairAttribute>
					<uro:key codeSpace="../../codelists/KeyValuePairAttribute_key.xml">100</uro:key>
					<uro:codeValue codeSpace="../../codelists/KeyValuePairAttribute_key100.xml">99999</uro:codeValue>
				</uro:KeyValuePairAttribute>
			</uro:bldgKeyValuePairAttribute>
			<uro:bldgKeyValuePairAttribute>
				<uro:KeyValuePairAttribute>
					<uro:key codeSpace="../../codelists/KeyValuePairAttribute_key.xml">101</uro:key>
					<uro:codeValue codeSpace="../../codelists/KeyValuePairAttribute_key101.xml">99999999</uro:codeValue>
				</uro:KeyValuePairAttribute>
			</uro:bldgKeyValuePairAttribute>
			<uro:bldgKeyValuePairAttribute>
				<uro:KeyValuePairAttribute>
					<uro:key codeSpace="../../codelists/KeyValuePairAttribute_key.xml">102</uro:key>
					<uro:codeValue codeSpace="../../codelists/KeyValuePairAttribute_key102.xml">9999</uro:codeValue>
				</uro:KeyValuePairAttribute>
			</uro:bldgKeyValuePairAttribute>
			<uro:bldgKeyValuePairAttribute>
				<uro:KeyValuePairAttribute>
					<uro:key codeSpace="../../codelists/KeyValuePairAttribute_key.xml">103</uro:key>
					<uro:codeValue codeSpace="../../codelists/KeyValuePairAttribute_key103.xml">9999</uro:codeValue>
				</uro:KeyValuePairAttribute>
			</uro:bldgKeyValuePairAttribute>
			<uro:bldgKeyValuePairAttribute>
				<uro:KeyValuePairAttribute>
					<uro:key codeSpace="../../codelists/KeyValuePairAttribute_key.xml">104</uro:key>
					<uro:codeValue codeSpace="../../codelists/KeyValuePairAttribute_key104.xml">9999</uro:codeValue>
				</uro:KeyValuePairAttribute>
			</uro:bldgKeyValuePairAttribute>
			<uro:bldgKeyValuePairAttribute>
				<uro:KeyValuePairAttribute>
					<uro:key codeSpace="../../codelists/KeyValuePairAttribute_key.xml">105</uro:key>
					<uro:codeValue codeSpace="../../codelists/KeyValuePairAttribute_key105.xml">9999</uro:codeValue>
				</uro:KeyValuePairAttribute>
			</uro:bldgKeyValuePairAttribute>
			<uro:bldgKeyValuePairAttribute>
				<uro:KeyValuePairAttribute>
					<uro:key codeSpace="../../codelists/KeyValuePairAttribute_key.xml">106</uro:key>
					<uro:codeValue codeSpace="../../codelists/KeyValuePairAttribute_key106.xml">9999</uro:codeValue>
				</uro:KeyValuePairAttribute>
			</uro:bldgKeyValuePairAttribute>
		</bldg:Building>
	</core:cityObjectMember>
	<core:cityObjectMember>
		<bldg:Building gml:id="bldg_65e93d25-12a0-45e5-90d4-60afd16bbf90">
			<core:creationDate>2023-03-22</core:creationDate>
			<bldg:class codeSpace="../../codelists/Building_class.xml">3001</bldg:class>
			<bldg:usage codeSpace="../../codelists/Building_usage.xml">461</bldg:usage>
			<bldg:measuredHeight uom="m">-9999</bldg:measuredHeight>
			<bldg:storeysAboveGround>9999</bldg:storeysAboveGround>
			<bldg:lod0FootPrint>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>34.62242256981012 135.51584978253635 0 34.62243631108486 135.5158525926668 0 34.62243933572409 135.51583102598522 0 34.62242558539064 135.51582820500545 0 34.62242256981012 135.51584978253635 0 </gml:posList>
								</gml:LinearRing>
							</gml:exterior>
						</gml:Polygon>
					</gml:surfaceMember>
				</gml:MultiSurface>
			</bldg:lod0FootPrint>
			<bldg:lod1Solid>
				<gml:Solid>
					<gml:exterior>
						<gml:CompositeSurface>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>34.62242256981012 135.51584978253635 7.5871 34.62242558539064 135.51582820500545 7.5871 34.62243933572409 135.51583102598522 7.5871 34.62243631108486 135.5158525926668 7.5871 34.62242256981012 135.51584978253635 7.5871</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>34.62242256981012 135.51584978253635 7.5871 34.62243631108486 135.5158525926668 7.5871 34.62243631108486 135.5158525926668 10.5871 34.62242256981012 135.51584978253635 10.5871 34.62242256981012 135.51584978253635 7.5871</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>34.62243631108486 135.5158525926668 7.5871 34.62243933572409 135.51583102598522 7.5871 34.62243933572409 135.51583102598522 10.5871 34.62243631108486 135.5158525926668 10.5871 34.62243631108486 135.5158525926668 7.5871</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>34.62243933572409 135.51583102598522 7.5871 34.62242558539064 135.51582820500545 7.5871 34.62242558539064 135.51582820500545 10.5871 34.62243933572409 135.51583102598522 10.5871 34.62243933572409 135.51583102598522 7.5871</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>34.62242558539064 135.51582820500545 7.5871 34.62242256981012 135.51584978253635 7.5871 34.62242256981012 135.51584978253635 10.5871 34.62242558539064 135.51582820500545 10.5871 34.62242558539064 135.51582820500545 7.5871</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>34.62242256981012 135.51584978253635 10.5871 34.62243631108486 135.5158525926668 10.5871 34.62243933572409 135.51583102598522 10.5871 34.62242558539064 135.51582820500545 10.5871 34.62242256981012 135.51584978253635 10.5871</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
						</gml:CompositeSurface>
					</gml:exterior>
				</gml:Solid>
			</bldg:lod1Solid>
			<uro:buildingDetailAttribute>
				<uro:BuildingDetailAttribute>
					<uro:buildingRoofEdgeArea uom="m2">-9999</uro:buildingRoofEdgeArea>
					<uro:buildingStructureType codeSpace="../../codelists/BuildingDetailAttribute_buildingStructureType.xml">611</uro:buildingStructureType>
					<uro:buildingStructureOrgType codeSpace="../../codelists/BuildingDetailAttribute_buildingStructureOrgType.xml">9</uro:buildingStructureOrgType>
					<uro:detailedUsage codeSpace="../../codelists/BuildingDetailAttribute_detailedUsage.xml">461</uro:detailedUsage>
					<uro:groundFloorUsage codeSpace="../../codelists/BuildingDetailAttribute_groundFloorUsage.xml">9999</uro:groundFloorUsage>
					<uro:secondFloorUsage codeSpace="../../codelists/BuildingDetailAttribute_secondFloorUsage.xml">9999</uro:secondFloorUsage>
					<uro:thirdFloorUsage codeSpace="../../codelists/BuildingDetailAttribute_thirdFloorUsage.xml">9999</uro:thirdFloorUsage>
					<uro:basementFirstUsage codeSpace="../../codelists/BuildingDetailAttribute_basementFirstUsage.xml">9999</uro:basementFirstUsage>
					<uro:basementSecondUsage codeSpace="../../codelists/BuildingDetailAttribute_basementSecondUsage.xml">9999</uro:basementSecondUsage>
					<uro:specifiedBuildingCoverageRate>80</uro:specifiedBuildingCoverageRate>
					<uro:surveyYear>2017</uro:surveyYear>
				</uro:BuildingDetailAttribute>
			</uro:buildingDetailAttribute>
			<uro:buildingIDAttribute>
				<uro:BuildingIDAttribute>
					<uro:buildingID>27100-bldg-80281</uro:buildingID>
					<uro:branchID>1</uro:branchID>
					<uro:prefecture codeSpace="../../codelists/Common_localPublicAuthorities.xml">27</uro:prefecture>
					<uro:city codeSpace="../../codelists/Common_localPublicAuthorities.xml">27119</uro:city>
				</uro:BuildingIDAttribute>
			</uro:buildingIDAttribute>
			<uro:bldgDataQualityAttribute>
				<uro:DataQualityAttribute>
					<uro:geometrySrcDescLod0 codeSpace="../../codelists/DataQualityAttribute_geometrySrcDesc.xml">000</uro:geometrySrcDescLod0>
					<uro:geometrySrcDescLod1 codeSpace="../../codelists/DataQualityAttribute_geometrySrcDesc.xml">000</uro:geometrySrcDescLod1>
					<uro:geometrySrcDescLod2 codeSpace="../../codelists/DataQualityAttribute_geometrySrcDesc.xml">999</uro:geometrySrcDescLod2>
					<uro:geometrySrcDescLod3 codeSpace="../../codelists/DataQualityAttribute_geometrySrcDesc.xml">999</uro:geometrySrcDescLod3>
					<uro:thematicSrcDesc codeSpace="../../codelists/DataQualityAttribute_thematicSrcDesc.xml">000</uro:thematicSrcDesc>
					<uro:thematicSrcDesc codeSpace="../../codelists/DataQualityAttribute_thematicSrcDesc.xml">100</uro:thematicSrcDesc>
					<uro:thematicSrcDesc codeSpace="../../codelists/DataQualityAttribute_thematicSrcDesc.xml">201</uro:thematicSrcDesc>
					<uro:thematicSrcDesc codeSpace="../../codelists/DataQualityAttribute_thematicSrcDesc.xml">400</uro:thematicSrcDesc>
					<uro:appearanceSrcDescLod2 codeSpace="../../codelists/DataQualityAttribute_appearanceSrcDesc.xml">99</uro:appearanceSrcDescLod2>
					<uro:appearanceSrcDescLod3 codeSpace="../../codelists/DataQualityAttribute_appearanceSrcDesc.xml">99</uro:appearanceSrcDescLod3>
					<uro:lod1HeightType codeSpace="../../codelists/DataQualityAttribute_lod1HeightType.xml">2</uro:lod1HeightType>
					<uro:publicSurveyDataQualityAttribute>
						<uro:PublicSurveyDataQualityAttribute>
							<uro:srcScaleLod0 codeSpace="../../codelists/PublicSurveyDataQualityAttribute_srcScale.xml">1</uro:srcScaleLod0>
							<uro:srcScaleLod1 codeSpace="../../codelists/PublicSurveyDataQualityAttribute_srcScale.xml">1</uro:srcScaleLod1>
							<uro:publicSurveySrcDescLod0 codeSpace="../../codelists/PublicSurveyDataQualityAttribute_publicSurveySrcDesc.xml">023</uro:publicSurveySrcDescLod0>
							<uro:publicSurveySrcDescLod1 codeSpace="../../codelists/PublicSurveyDataQualityAttribute_publicSurveySrcDesc.xml">023</uro:publicSurveySrcDescLod1>
						</uro:PublicSurveyDataQualityAttribute>
					</uro:publicSurveyDataQualityAttribute>
				</uro:DataQualityAttribute>
			</uro:bldgDataQualityAttribute>
			<uro:bldgKeyValuePairAttribute>
				<uro:KeyValuePairAttribute>
					<uro:key codeSpace="../../codelists/KeyValuePairAttribute_key.xml">100</uro:key>
					<uro:codeValue codeSpace="../../codelists/KeyValuePairAttribute_key100.xml">99999</uro:codeValue>
				</uro:KeyValuePairAttribute>
			</uro:bldgKeyValuePairAttribute>
			<uro:bldgKeyValuePairAttribute>
				<uro:KeyValuePairAttribute>
					<uro:key codeSpace="../../codelists/KeyValuePairAttribute_key.xml">101</uro:key>
					<uro:codeValue codeSpace="../../codelists/KeyValuePairAttribute_key101.xml">99999999</uro:codeValue>
				</uro:KeyValuePairAttribute>
			</uro:bldgKeyValuePairAttribute>
			<uro:bldgKeyValuePairAttribute>
				<uro:KeyValuePairAttribute>
					<uro:key codeSpace="../../codelists/KeyValuePairAttribute_key.xml">102</uro:key>
					<uro:codeValue codeSpace="../../codelists/KeyValuePairAttribute_key102.xml">9999</uro:codeValue>
				</uro:KeyValuePairAttribute>
			</uro:bldgKeyValuePairAttribute>
			<uro:bldgKeyValuePairAttribute>
				<uro:KeyValuePairAttribute>
					<uro:key codeSpace="../../codelists/KeyValuePairAttribute_key.xml">103</uro:key>
					<uro:codeValue codeSpace="../../codelists/KeyValuePairAttribute_key103.xml">9999</uro:codeValue>
				</uro:KeyValuePairAttribute>
			</uro:bldgKeyValuePairAttribute>
			<uro:bldgKeyValuePairAttribute>
				<uro:KeyValuePairAttribute>
					<uro:key codeSpace="../../codelists/KeyValuePairAttribute_key.xml">104</uro:key>
					<uro:codeValue codeSpace="../../codelists/KeyValuePairAttribute_key104.xml">9999</uro:codeValue>
				</uro:KeyValuePairAttribute>
			</uro:bldgKeyValuePairAttribute>
			<uro:bldgKeyValuePairAttribute>
				<uro:KeyValuePairAttribute>
					<uro:key codeSpace="../../codelists/KeyValuePairAttribute_key.xml">105</uro:key>
					<uro:codeValue codeSpace="../../codelists/KeyValuePairAttribute_key105.xml">9999</uro:codeValue>
				</uro:KeyValuePairAttribute>
			</uro:bldgKeyValuePairAttribute>
			<uro:bldgKeyValuePairAttribute>
				<uro:KeyValuePairAttribute>
					<uro:key codeSpace="../../codelists/KeyValuePairAttribute_key.xml">106</uro:key>
					<uro:codeValue codeSpace="../../codelists/KeyValuePairAttribute_key106.xml">9999</uro:codeValue>
				</uro:KeyValuePairAttribute>
			</uro:bldgKeyValuePairAttribute>
		</bldg:Building>
	</core:cityObjectMember>
	<core:cityObjectMember>
		<bldg:Building gml:id="bldg_af5a6580-1385-461d-bc39-c45107fb2469">
			<core:creationDate>2023-03-22</core:creationDate>
			<gen:intAttribute name="ユーザーID">
				<gen:value>3406</gen:value>
			</gen:intAttribute>
			<bldg:class codeSpace="../../codelists/Building_class.xml">3001</bldg:class>
			<bldg:usage codeSpace="../../codelists/Building_usage.xml">461</bldg:usage>
			<bldg:measuredHeight uom="m">4.2</bldg:measuredHeight>
			<bldg:storeysAboveGround>9999</bldg:storeysAboveGround>
			<bldg:lod0FootPrint>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>34.62239051466228 135.51594363532652 0 34.62252674297724 135.51596178703176 0 34.62252975808406 135.51592872605792 0 34.622520765178635 135.51592752415365 0 34.62252893732908 135.51583776859547 0 34.62254539870203 135.51583995222472 0 34.6225485777016 135.51580503636305 0 34.62240488081206 135.51578588137124 0 34.62239051466228 135.51594363532652 0 </gml:posList>
								</gml:LinearRing>
							</gml:exterior>
							<gml:interior>
								<gml:LinearRing>
									<gml:posList>34.62241613420931 135.51590234041402 0 34.62241959605465 135.51587054191634 0 34.62243400240284 135.51587283563285 0 34.622430531585266 135.51590464509346 0 34.62241613420931 135.51590234041402 0 </gml:posList>
								</gml:LinearRing>
							</gml:interior>
							<gml:interior>
								<gml:LinearRing>
									<gml:posList>34.62242256981012 135.51584978253635 0 34.62242558539064 135.51582820500545 0 34.62243933572409 135.51583102598522 0 34.62243631108486 135.5158525926668 0 34.62242256981012 135.51584978253635 0 </gml:posList>
								</gml:LinearRing>
							</gml:interior>
						</gml:Polygon>
					</gml:surfaceMember>
				</gml:MultiSurface>
			</bldg:lod0FootPrint>
			<bldg:lod1Solid>
				<gml:Solid>
					<gml:exterior>
						<gml:CompositeSurface>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>34.62239051466228 135.51594363532652 7.5526 34.62240488081206 135.51578588137124 7.5526 34.6225485777016 135.51580503636305 7.5526 34.62254539870203 135.51583995222472 7.5526 34.62252893732908 135.51583776859547 7.5526 34.622520765178635 135.51592752415365 7.5526 34.62252975808406 135.51592872605792 7.5526 34.62252674297724 135.51596178703176 7.5526 34.62239051466228 135.51594363532652 7.5526</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
									<gml:interior>
										<gml:LinearRing>
											<gml:posList>34.62241613420931 135.51590234041402 7.5526 34.622430531585266 135.51590464509346 7.5526 34.62243400240284 135.51587283563285 7.5526 34.62241959605465 135.51587054191634 7.5526 34.62241613420931 135.51590234041402 7.5526</gml:posList>
										</gml:LinearRing>
									</gml:interior>
									<gml:interior>
										<gml:LinearRing>
											<gml:posList>34.62242256981012 135.51584978253635 7.5526 34.62243631108486 135.5158525926668 7.5526 34.62243933572409 135.51583102598522 7.5526 34.62242558539064 135.51582820500545 7.5526 34.62242256981012 135.51584978253635 7.5526</gml:posList>
										</gml:LinearRing>
									</gml:interior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>34.62239051466228 135.51594363532652 7.5526 34.62252674297724 135.51596178703176 7.5526 34.62252674297724 135.51596178703176 11.75 34.62239051466228 135.51594363532652 11.75 34.62239051466228 135.51594363532652 7.5526</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>34.62252674297724 135.51596178703176 7.5526 34.62252975808406 135.51592872605792 7.5526 34.62252975808406 135.51592872605792 11.75 34.62252674297724 135.51596178703176 11.75 34.62252674297724 135.51596178703176 7.5526</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>34.62252975808406 135.51592872605792 7.5526 34.622520765178635 135.51592752415365 7.5526 34.622520765178635 135.51592752415365 11.75 34.62252975808406 135.51592872605792 11.75 34.62252975808406 135.51592872605792 7.5526</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>34.622520765178635 135.51592752415365 7.5526 34.62252893732908 135.51583776859547 7.5526 34.62252893732908 135.51583776859547 11.75 34.622520765178635 135.51592752415365 11.75 34.622520765178635 135.51592752415365 7.5526</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>34.62252893732908 135.51583776859547 7.5526 34.62254539870203 135.51583995222472 7.5526 34.62254539870203 135.51583995222472 11.75 34.62252893732908 135.51583776859547 11.75 34.62252893732908 135.51583776859547 7.5526</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>34.62254539870203 135.51583995222472 7.5526 34.6225485777016 135.51580503636305 7.5526 34.6225485777016 135.51580503636305 11.75 34.62254539870203 135.51583995222472 11.75 34.62254539870203 135.51583995222472 7.5526</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>34.6225485777016 135.51580503636305 7.5526 34.62240488081206 135.51578588137124 7.5526 34.62240488081206 135.51578588137124 11.75 34.6225485777016 135.51580503636305 11.75 34.6225485777016 135.51580503636305 7.5526</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>34.62240488081206 135.51578588137124 7.5526 34.62239051466228 135.51594363532652 7.5526 34.62239051466228 135.51594363532652 11.75 34.62240488081206 135.51578588137124 11.75 34.62240488081206 135.51578588137124 7.5526</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>34.62241613420931 135.51590234041402 7.5526 34.62241959605465 135.51587054191634 7.5526 34.62241959605465 135.51587054191634 11.75 34.62241613420931 135.51590234041402 11.75 34.62241613420931 135.51590234041402 7.5526</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>34.62241959605465 135.51587054191634 7.5526 34.62243400240284 135.51587283563285 7.5526 34.62243400240284 135.51587283563285 11.75 34.62241959605465 135.51587054191634 11.75 34.62241959605465 135.51587054191634 7.5526</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>34.62243400240284 135.51587283563285 7.5526 34.622430531585266 135.51590464509346 7.5526 34.622430531585266 135.51590464509346 11.75 34.62243400240284 135.51587283563285 11.75 34.62243400240284 135.51587283563285 7.5526</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>34.622430531585266 135.51590464509346 7.5526 34.62241613420931 135.51590234041402 7.5526 34.62241613420931 135.51590234041402 11.75 34.622430531585266 135.51590464509346 11.75 34.622430531585266 135.51590464509346 7.5526</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>34.62242256981012 135.51584978253635 7.5526 34.62242558539064 135.51582820500545 7.5526 34.62242558539064 135.51582820500545 11.75 34.62242256981012 135.51584978253635 11.75 34.62242256981012 135.51584978253635 7.5526</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>34.62242558539064 135.51582820500545 7.5526 34.62243933572409 135.51583102598522 7.5526 34.62243933572409 135.51583102598522 11.75 34.62242558539064 135.51582820500545 11.75 34.62242558539064 135.51582820500545 7.5526</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>34.62243933572409 135.51583102598522 7.5526 34.62243631108486 135.5158525926668 7.5526 34.62243631108486 135.5158525926668 11.75 34.62243933572409 135.51583102598522 11.75 34.62243933572409 135.51583102598522 7.5526</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>34.62243631108486 135.5158525926668 7.5526 34.62242256981012 135.51584978253635 7.5526 34.62242256981012 135.51584978253635 11.75 34.62243631108486 135.5158525926668 11.75 34.62243631108486 135.5158525926668 7.5526</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>34.62239051466228 135.51594363532652 11.75 34.62252674297724 135.51596178703176 11.75 34.62252975808406 135.51592872605792 11.75 34.622520765178635 135.51592752415365 11.75 34.62252893732908 135.51583776859547 11.75 34.62254539870203 135.51583995222472 11.75 34.6225485777016 135.51580503636305 11.75 34.62240488081206 135.51578588137124 11.75 34.62239051466228 135.51594363532652 11.75</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
									<gml:interior>
										<gml:LinearRing>
											<gml:posList>34.62241613420931 135.51590234041402 11.75 34.62241959605465 135.51587054191634 11.75 34.62243400240284 135.51587283563285 11.75 34.622430531585266 135.51590464509346 11.75 34.62241613420931 135.51590234041402 11.75</gml:posList>
										</gml:LinearRing>
									</gml:interior>
									<gml:interior>
										<gml:LinearRing>
											<gml:posList>34.62242256981012 135.51584978253635 11.75 34.62242558539064 135.51582820500545 11.75 34.62243933572409 135.51583102598522 11.75 34.62243631108486 135.5158525926668 11.75 34.62242256981012 135.51584978253635 11.75</gml:posList>
										</gml:LinearRing>
									</gml:interior>
								</gml:Polygon>
							</gml:surfaceMember>
						</gml:CompositeSurface>
					</gml:exterior>
				</gml:Solid>
			</bldg:lod1Solid>
			<uro:buildingDetailAttribute>
				<uro:BuildingDetailAttribute>
					<uro:buildingRoofEdgeArea uom="m2">-9999</uro:buildingRoofEdgeArea>
					<uro:buildingStructureType codeSpace="../../codelists/BuildingDetailAttribute_buildingStructureType.xml">611</uro:buildingStructureType>
					<uro:buildingStructureOrgType codeSpace="../../codelists/BuildingDetailAttribute_buildingStructureOrgType.xml">9</uro:buildingStructureOrgType>
					<uro:detailedUsage codeSpace="../../codelists/BuildingDetailAttribute_detailedUsage.xml">461</uro:detailedUsage>
					<uro:groundFloorUsage codeSpace="../../codelists/BuildingDetailAttribute_groundFloorUsage.xml">9999</uro:groundFloorUsage>
					<uro:secondFloorUsage codeSpace="../../codelists/BuildingDetailAttribute_secondFloorUsage.xml">9999</uro:secondFloorUsage>
					<uro:thirdFloorUsage codeSpace="../../codelists/BuildingDetailAttribute_thirdFloorUsage.xml">9999</uro:thirdFloorUsage>
					<uro:basementFirstUsage codeSpace="../../codelists/BuildingDetailAttribute_basementFirstUsage.xml">9999</uro:basementFirstUsage>
					<uro:basementSecondUsage codeSpace="../../codelists/BuildingDetailAttribute_basementSecondUsage.xml">9999</uro:basementSecondUsage>
					<uro:specifiedBuildingCoverageRate>80</uro:specifiedBuildingCoverageRate>
					<uro:surveyYear>2017</uro:surveyYear>
				</uro:BuildingDetailAttribute>
			</uro:buildingDetailAttribute>
			<uro:buildingIDAttribute>
				<uro:BuildingIDAttribute>
					<uro:buildingID>27100-bldg-80114</uro:buildingID>
					<uro:branchID>1</uro:branchID>
					<uro:prefecture codeSpace="../../codelists/Common_localPublicAuthorities.xml">27</uro:prefecture>
					<uro:city codeSpace="../../codelists/Common_localPublicAuthorities.xml">27119</uro:city>
				</uro:BuildingIDAttribute>
			</uro:buildingIDAttribute>
			<uro:bldgDataQualityAttribute>
				<uro:DataQualityAttribute>
					<uro:geometrySrcDescLod0 codeSpace="../../codelists/DataQualityAttribute_geometrySrcDesc.xml">000</uro:geometrySrcDescLod0>
					<uro:geometrySrcDescLod1 codeSpace="../../codelists/DataQualityAttribute_geometrySrcDesc.xml">000</uro:geometrySrcDescLod1>
					<uro:geometrySrcDescLod2 codeSpace="../../codelists/DataQualityAttribute_geometrySrcDesc.xml">999</uro:geometrySrcDescLod2>
					<uro:geometrySrcDescLod3 codeSpace="../../codelists/DataQualityAttribute_geometrySrcDesc.xml">999</uro:geometrySrcDescLod3>
					<uro:thematicSrcDesc codeSpace="../../codelists/DataQualityAttribute_thematicSrcDesc.xml">000</uro:thematicSrcDesc>
					<uro:thematicSrcDesc codeSpace="../../codelists/DataQualityAttribute_thematicSrcDesc.xml">100</uro:thematicSrcDesc>
					<uro:thematicSrcDesc codeSpace="../../codelists/DataQualityAttribute_thematicSrcDesc.xml">201</uro:thematicSrcDesc>
					<uro:thematicSrcDesc codeSpace="../../codelists/DataQualityAttribute_thematicSrcDesc.xml">400</uro:thematicSrcDesc>
					<uro:appearanceSrcDescLod2 codeSpace="../../codelists/DataQualityAttribute_appearanceSrcDesc.xml">99</uro:appearanceSrcDescLod2>
					<uro:appearanceSrcDescLod3 codeSpace="../../codelists/DataQualityAttribute_appearanceSrcDesc.xml">99</uro:appearanceSrcDescLod3>
					<uro:lod1HeightType codeSpace="../../codelists/DataQualityAttribute_lod1HeightType.xml">2</uro:lod1HeightType>
					<uro:publicSurveyDataQualityAttribute>
						<uro:PublicSurveyDataQualityAttribute>
							<uro:srcScaleLod0 codeSpace="../../codelists/PublicSurveyDataQualityAttribute_srcScale.xml">1</uro:srcScaleLod0>
							<uro:srcScaleLod1 codeSpace="../../codelists/PublicSurveyDataQualityAttribute_srcScale.xml">1</uro:srcScaleLod1>
							<uro:publicSurveySrcDescLod0 codeSpace="../../codelists/PublicSurveyDataQualityAttribute_publicSurveySrcDesc.xml">023</uro:publicSurveySrcDescLod0>
							<uro:publicSurveySrcDescLod1 codeSpace="../../codelists/PublicSurveyDataQualityAttribute_publicSurveySrcDesc.xml">023</uro:publicSurveySrcDescLod1>
						</uro:PublicSurveyDataQualityAttribute>
					</uro:publicSurveyDataQualityAttribute>
				</uro:DataQualityAttribute>
			</uro:bldgDataQualityAttribute>
			<uro:bldgKeyValuePairAttribute>
				<uro:KeyValuePairAttribute>
					<uro:key codeSpace="../../codelists/KeyValuePairAttribute_key.xml">100</uro:key>
					<uro:codeValue codeSpace="../../codelists/KeyValuePairAttribute_key100.xml">99999</uro:codeValue>
				</uro:KeyValuePairAttribute>
			</uro:bldgKeyValuePairAttribute>
			<uro:bldgKeyValuePairAttribute>
				<uro:KeyValuePairAttribute>
					<uro:key codeSpace="../../codelists/KeyValuePairAttribute_key.xml">101</uro:key>
					<uro:codeValue codeSpace="../../codelists/KeyValuePairAttribute_key101.xml">99999999</uro:codeValue>
				</uro:KeyValuePairAttribute>
			</uro:bldgKeyValuePairAttribute>
			<uro:bldgKeyValuePairAttribute>
				<uro:KeyValuePairAttribute>
					<uro:key codeSpace="../../codelists/KeyValuePairAttribute_key.xml">102</uro:key>
					<uro:codeValue codeSpace="../../codelists/KeyValuePairAttribute_key102.xml">9999</uro:codeValue>
				</uro:KeyValuePairAttribute>
			</uro:bldgKeyValuePairAttribute>
			<uro:bldgKeyValuePairAttribute>
				<uro:KeyValuePairAttribute>
					<uro:key codeSpace="../../codelists/KeyValuePairAttribute_key.xml">103</uro:key>
					<uro:codeValue codeSpace="../../codelists/KeyValuePairAttribute_key103.xml">9999</uro:codeValue>
				</uro:KeyValuePairAttribute>
			</uro:bldgKeyValuePairAttribute>
			<uro:bldgKeyValuePairAttribute>
				<uro:KeyValuePairAttribute>
					<uro:key codeSpace="../../codelists/KeyValuePairAttribute_key.xml">104</uro:key>
					<uro:codeValue codeSpace="../../codelists/KeyValuePairAttribute_key104.xml">9999</uro:codeValue>
				</uro:KeyValuePairAttribute>
			</uro:bldgKeyValuePairAttribute>
			<uro:bldgKeyValuePairAttribute>
				<uro:KeyValuePairAttribute>
					<uro:key codeSpace="../../codelists/KeyValuePairAttribute_key.xml">105</uro:key>
					<uro:codeValue codeSpace="../../codelists/KeyValuePairAttribute_key105.xml">9999</uro:codeValue>
				</uro:KeyValuePairAttribute>
			</uro:bldgKeyValuePairAttribute>
			<uro:bldgKeyValuePairAttribute>
				<uro:KeyValuePairAttribute>
					<uro:key codeSpace="../../codelists/KeyValuePairAttribute_key.xml">106</uro:key>
					<uro:codeValue codeSpace="../../codelists/KeyValuePairAttribute_key106.xml">9999</uro:codeValue>
				</uro:KeyValuePairAttribute>
			</uro:bldgKeyValuePairAttribute>
		</bldg:Building>
	</core:cityObjectMember>
</core:CityModel>
