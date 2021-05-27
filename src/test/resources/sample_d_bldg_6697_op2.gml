<?xml version="1.0" encoding="UTF-8"?>
<core:CityModel xmlns:grp="http://www.opengis.net/citygml/cityobjectgroup/2.0" xmlns:core="http://www.opengis.net/citygml/2.0" xmlns:pbase="http://www.opengis.net/citygml/profiles/base/2.0" xmlns:smil20lang="http://www.w3.org/2001/SMIL20/Language" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:smil20="http://www.w3.org/2001/SMIL20/" xmlns:bldg="http://www.opengis.net/citygml/building/2.0" xmlns:xAL="urn:oasis:names:tc:ciq:xsdschema:xAL:2.0" xmlns:uro="http://www.kantei.go.jp/jp/singi/tiiki/toshisaisei/itoshisaisei/iur/uro/1.4" xmlns:luse="http://www.opengis.net/citygml/landuse/2.0" xmlns:app="http://www.opengis.net/citygml/appearance/2.0" xmlns:gen="http://www.opengis.net/citygml/generics/2.0" xmlns:dem="http://www.opengis.net/citygml/relief/2.0" xmlns:tex="http://www.opengis.net/citygml/texturedsurface/2.0" xmlns:tun="http://www.opengis.net/citygml/tunnel/2.0" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:sch="http://www.ascc.net/xml/schematron" xmlns:veg="http://www.opengis.net/citygml/vegetation/2.0" xmlns:frn="http://www.opengis.net/citygml/cityfurniture/2.0" xmlns:gml="http://www.opengis.net/gml" xmlns:tran="http://www.opengis.net/citygml/transportation/2.0" xmlns:wtr="http://www.opengis.net/citygml/waterbody/2.0" xmlns:brid="http://www.opengis.net/citygml/bridge/2.0" xsi:schemaLocation="http://www.kantei.go.jp/jp/singi/tiiki/toshisaisei/itoshisaisei/iur/uro/1.4 http://www.kantei.go.jp/jp/singi/tiiki/toshisaisei/itoshisaisei/iur/schemas/uro/1.4/urbanObject.xsd http://www.opengis.net/citygml/2.0 http://schemas.opengis.net/citygml/2.0/cityGMLBase.xsd http://www.opengis.net/citygml/landuse/2.0 http://schemas.opengis.net/citygml/landuse/2.0/landUse.xsd http://www.opengis.net/citygml/building/2.0 http://schemas.opengis.net/citygml/building/2.0/building.xsd http://www.opengis.net/citygml/transportation/2.0 http://schemas.opengis.net/citygml/transportation/2.0/transportation.xsd http://www.opengis.net/citygml/generics/2.0 http://schemas.opengis.net/citygml/generics/2.0/generics.xsd http://www.opengis.net/citygml/cityobjectgroup/2.0 http://schemas.opengis.net/citygml/cityobjectgroup/2.0/cityObjectGroup.xsd http://www.opengis.net/gml http://schemas.opengis.net/gml/3.1.1/base/gml.xsd http://www.opengis.net/citygml/appearance/2.0 http://schemas.opengis.net/citygml/appearance/2.0/appearance.xsd">
	<gml:boundedBy>
		<gml:Envelope srsName="http://www.opengis.net/def/crs/EPSG/0/6697" srsDimension="3">
			<gml:lowerCorner>35.56647195757135 139.7241207469562 1.009</gml:lowerCorner>
			<gml:upperCorner>35.57509621366029 139.737560968697 46.259</gml:upperCorner>
		</gml:Envelope>
	</gml:boundedBy>
	<core:cityObjectMember>
		<bldg:Building gml:id="BLD_4ebf61e7-bbc4-493c-aa9c-5afc8f7b975d">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-71799</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="大字・町コード">
				<gen:value>6</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="町・丁目コード">
				<gen:value>3</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
				<gen:value>13111006003</gen:value>
			</gen:stringAttribute>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>1</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">0.220</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">0.30</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<gen:genericAttributeSet name="城南地区河川流域洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>1</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">0.162</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">30.2</bldg:measuredHeight>
			<bldg:storeysAboveGround>3</bldg:storeysAboveGround>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.57466593925221 139.7278593732507 6.038 35.57475990877071 139.7278592500697 6.038 35.57475975734357 139.72784071463457 6.038 35.57466592303307 139.72784083765995 6.038 35.57466593925221 139.7278593732507 6.038</gml:posList>
								</gml:LinearRing>
							</gml:exterior>
						</gml:Polygon>
					</gml:surfaceMember>
				</gml:MultiSurface>
			</bldg:lod0RoofEdge>
			<bldg:lod1Solid>
				<gml:Solid>
					<gml:exterior>
						<gml:CompositeSurface>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57466593925221 139.7278593732507 3.126 35.57466592303307 139.72784083765995 3.126 35.57475975734357 139.72784071463457 3.126 35.57475990877071 139.7278592500697 3.126 35.57466593925221 139.7278593732507 3.126</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57466593925221 139.7278593732507 3.126 35.57475990877071 139.7278592500697 3.126 35.57475990877071 139.7278592500697 6.038 35.57466593925221 139.7278593732507 6.038 35.57466593925221 139.7278593732507 3.126</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57475990877071 139.7278592500697 3.126 35.57475975734357 139.72784071463457 3.126 35.57475975734357 139.72784071463457 6.038 35.57475990877071 139.7278592500697 6.038 35.57475990877071 139.7278592500697 3.126</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57475975734357 139.72784071463457 3.126 35.57466592303307 139.72784083765995 3.126 35.57466592303307 139.72784083765995 6.038 35.57475975734357 139.72784071463457 6.038 35.57475975734357 139.72784071463457 3.126</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57466592303307 139.72784083765995 3.126 35.57466593925221 139.7278593732507 3.126 35.57466593925221 139.7278593732507 6.038 35.57466592303307 139.72784083765995 6.038 35.57466592303307 139.72784083765995 3.126</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57466593925221 139.7278593732507 6.038 35.57475990877071 139.7278592500697 6.038 35.57475975734357 139.72784071463457 6.038 35.57466592303307 139.72784083765995 6.038 35.57466593925221 139.7278593732507 6.038</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
						</gml:CompositeSurface>
					</gml:exterior>
				</gml:Solid>
			</bldg:lod1Solid>
			<bldg:address>
				<core:Address>
					<core:xalAddress>
						<xAL:AddressDetails>
							<xAL:Country>
								<xAL:CountryName>日本</xAL:CountryName>
								<xAL:Locality>
									<xAL:LocalityName Type="Town">東京都大田区大森西三丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">17.50140</uro:buildingRoofEdgeArea>
					<uro:districtsAndZonesType codeSpace="../../codelists/Common_districtsAndZonesType.xml">11</uro:districtsAndZonesType>
					<uro:prefecture codeSpace="../../codelists/Common_prefecture.xml">13</uro:prefecture>
					<uro:city codeSpace="../../codelists/Common_localPublicAuthorities.xml">13111</uro:city>
					<uro:surveyYear>2016</uro:surveyYear>
				</uro:BuildingDetails>
			</uro:buildingDetails>
			<uro:extendedAttribute>
				<uro:KeyValuePair>
					<uro:key codeSpace="../../codelists/extendedAttribute_key.xml">105</uro:key>
					<uro:codeValue codeSpace="../../codelists/extendedAttribute_key105.xml">2</uro:codeValue>
				</uro:KeyValuePair>
			</uro:extendedAttribute>
			<uro:extendedAttribute>
				<uro:KeyValuePair>
					<uro:key codeSpace="../../codelists/extendedAttribute_key.xml">106</uro:key>
					<uro:codeValue codeSpace="../../codelists/extendedAttribute_key106.xml">20</uro:codeValue>
				</uro:KeyValuePair>
			</uro:extendedAttribute>
		</bldg:Building>
	</core:cityObjectMember>
	<core:cityObjectMember>
		<bldg:Building gml:id="BLD_4ba3c7be-18a0-461a-9305-464fa2cfc57f">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-72601</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="大字・町コード">
				<gen:value>6</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="町・丁目コード">
				<gen:value>3</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
				<gen:value>13111006003</gen:value>
			</gen:stringAttribute>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>1</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">0.160</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">0.00</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<gen:genericAttributeSet name="城南地区河川流域洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>1</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">0.403</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">34.7</bldg:measuredHeight>
			<bldg:storeysAboveGround>2</bldg:storeysAboveGround>
			<bldg:storeysBelowGround>1</bldg:storeysBelowGround>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.57475975734357 139.72784071463457 32.852000000000004 35.57503842032038 139.7278396872893 32.852000000000004 35.575038186560214 139.7275729059462 32.852000000000004 35.57443786418458 139.7275746879966 32.852000000000004 35.57443890947473 139.72784179727742 32.852000000000004 35.57466592303307 139.72784083765995 32.852000000000004 35.57475975734357 139.72784071463457 32.852000000000004</gml:posList>
								</gml:LinearRing>
							</gml:exterior>
							<gml:interior>
								<gml:LinearRing>
									<gml:posList>35.57476926611107 139.7277367702098 32.852000000000004 35.57476907550811 139.7276735507023 32.852000000000004 35.57499122156624 139.72767259699572 32.852000000000004 35.574991277251996 139.72773614784924 32.852000000000004 35.57476926611107 139.7277367702098 32.852000000000004</gml:posList>
								</gml:LinearRing>
							</gml:interior>
							<gml:interior>
								<gml:LinearRing>
									<gml:posList>35.57448865510807 139.72767491191715 32.852000000000004 35.57466645413269 139.72767534042688 32.852000000000004 35.574666237081175 139.72773624343722 32.852000000000004 35.57448857297453 139.72773548362352 32.852000000000004 35.57448865510807 139.72767491191715 32.852000000000004</gml:posList>
								</gml:LinearRing>
							</gml:interior>
						</gml:Polygon>
					</gml:surfaceMember>
				</gml:MultiSurface>
			</bldg:lod0RoofEdge>
			<bldg:lod1Solid>
				<gml:Solid>
					<gml:exterior>
						<gml:CompositeSurface>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57475975734357 139.72784071463457 2.682 35.57466592303307 139.72784083765995 2.682 35.57443890947473 139.72784179727742 2.682 35.57443786418458 139.7275746879966 2.682 35.575038186560214 139.7275729059462 2.682 35.57503842032038 139.7278396872893 2.682 35.57475975734357 139.72784071463457 2.682</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
									<gml:interior>
										<gml:LinearRing>
											<gml:posList>35.57476926611107 139.7277367702098 2.682 35.574991277251996 139.72773614784924 2.682 35.57499122156624 139.72767259699572 2.682 35.57476907550811 139.7276735507023 2.682 35.57476926611107 139.7277367702098 2.682</gml:posList>
										</gml:LinearRing>
									</gml:interior>
									<gml:interior>
										<gml:LinearRing>
											<gml:posList>35.57448865510807 139.72767491191715 2.682 35.57448857297453 139.72773548362352 2.682 35.574666237081175 139.72773624343722 2.682 35.57466645413269 139.72767534042688 2.682 35.57448865510807 139.72767491191715 2.682</gml:posList>
										</gml:LinearRing>
									</gml:interior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57475975734357 139.72784071463457 2.682 35.57503842032038 139.7278396872893 2.682 35.57503842032038 139.7278396872893 32.852000000000004 35.57475975734357 139.72784071463457 32.852000000000004 35.57475975734357 139.72784071463457 2.682</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57503842032038 139.7278396872893 2.682 35.575038186560214 139.7275729059462 2.682 35.575038186560214 139.7275729059462 32.852000000000004 35.57503842032038 139.7278396872893 32.852000000000004 35.57503842032038 139.7278396872893 2.682</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.575038186560214 139.7275729059462 2.682 35.57443786418458 139.7275746879966 2.682 35.57443786418458 139.7275746879966 32.852000000000004 35.575038186560214 139.7275729059462 32.852000000000004 35.575038186560214 139.7275729059462 2.682</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57443786418458 139.7275746879966 2.682 35.57443890947473 139.72784179727742 2.682 35.57443890947473 139.72784179727742 32.852000000000004 35.57443786418458 139.7275746879966 32.852000000000004 35.57443786418458 139.7275746879966 2.682</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57443890947473 139.72784179727742 2.682 35.57466592303307 139.72784083765995 2.682 35.57466592303307 139.72784083765995 32.852000000000004 35.57443890947473 139.72784179727742 32.852000000000004 35.57443890947473 139.72784179727742 2.682</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57466592303307 139.72784083765995 2.682 35.57475975734357 139.72784071463457 2.682 35.57475975734357 139.72784071463457 32.852000000000004 35.57466592303307 139.72784083765995 32.852000000000004 35.57466592303307 139.72784083765995 2.682</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57476926611107 139.7277367702098 2.682 35.57476907550811 139.7276735507023 2.682 35.57476907550811 139.7276735507023 32.852000000000004 35.57476926611107 139.7277367702098 32.852000000000004 35.57476926611107 139.7277367702098 2.682</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57476907550811 139.7276735507023 2.682 35.57499122156624 139.72767259699572 2.682 35.57499122156624 139.72767259699572 32.852000000000004 35.57476907550811 139.7276735507023 32.852000000000004 35.57476907550811 139.7276735507023 2.682</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57499122156624 139.72767259699572 2.682 35.574991277251996 139.72773614784924 2.682 35.574991277251996 139.72773614784924 32.852000000000004 35.57499122156624 139.72767259699572 32.852000000000004 35.57499122156624 139.72767259699572 2.682</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.574991277251996 139.72773614784924 2.682 35.57476926611107 139.7277367702098 2.682 35.57476926611107 139.7277367702098 32.852000000000004 35.574991277251996 139.72773614784924 32.852000000000004 35.574991277251996 139.72773614784924 2.682</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57448865510807 139.72767491191715 2.682 35.57466645413269 139.72767534042688 2.682 35.57466645413269 139.72767534042688 32.852000000000004 35.57448865510807 139.72767491191715 32.852000000000004 35.57448865510807 139.72767491191715 2.682</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57466645413269 139.72767534042688 2.682 35.574666237081175 139.72773624343722 2.682 35.574666237081175 139.72773624343722 32.852000000000004 35.57466645413269 139.72767534042688 32.852000000000004 35.57466645413269 139.72767534042688 2.682</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.574666237081175 139.72773624343722 2.682 35.57448857297453 139.72773548362352 2.682 35.57448857297453 139.72773548362352 32.852000000000004 35.574666237081175 139.72773624343722 32.852000000000004 35.574666237081175 139.72773624343722 2.682</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57448857297453 139.72773548362352 2.682 35.57448865510807 139.72767491191715 2.682 35.57448865510807 139.72767491191715 32.852000000000004 35.57448857297453 139.72773548362352 32.852000000000004 35.57448857297453 139.72773548362352 2.682</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57475975734357 139.72784071463457 32.852000000000004 35.57503842032038 139.7278396872893 32.852000000000004 35.575038186560214 139.7275729059462 32.852000000000004 35.57443786418458 139.7275746879966 32.852000000000004 35.57443890947473 139.72784179727742 32.852000000000004 35.57466592303307 139.72784083765995 32.852000000000004 35.57475975734357 139.72784071463457 32.852000000000004</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
									<gml:interior>
										<gml:LinearRing>
											<gml:posList>35.57476926611107 139.7277367702098 32.852000000000004 35.57476907550811 139.7276735507023 32.852000000000004 35.57499122156624 139.72767259699572 32.852000000000004 35.574991277251996 139.72773614784924 32.852000000000004 35.57476926611107 139.7277367702098 32.852000000000004</gml:posList>
										</gml:LinearRing>
									</gml:interior>
									<gml:interior>
										<gml:LinearRing>
											<gml:posList>35.57448865510807 139.72767491191715 32.852000000000004 35.57466645413269 139.72767534042688 32.852000000000004 35.574666237081175 139.72773624343722 32.852000000000004 35.57448857297453 139.72773548362352 32.852000000000004 35.57448865510807 139.72767491191715 32.852000000000004</gml:posList>
										</gml:LinearRing>
									</gml:interior>
								</gml:Polygon>
							</gml:surfaceMember>
						</gml:CompositeSurface>
					</gml:exterior>
				</gml:Solid>
			</bldg:lod1Solid>
			<bldg:address>
				<core:Address>
					<core:xalAddress>
						<xAL:AddressDetails>
							<xAL:Country>
								<xAL:CountryName>日本</xAL:CountryName>
								<xAL:Locality>
									<xAL:LocalityName Type="Town">東京都大田区大森西三丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">1360.04175</uro:buildingRoofEdgeArea>
					<uro:districtsAndZonesType codeSpace="../../codelists/Common_districtsAndZonesType.xml">11</uro:districtsAndZonesType>
					<uro:prefecture codeSpace="../../codelists/Common_prefecture.xml">13</uro:prefecture>
					<uro:city codeSpace="../../codelists/Common_localPublicAuthorities.xml">13111</uro:city>
					<uro:surveyYear>2016</uro:surveyYear>
				</uro:BuildingDetails>
			</uro:buildingDetails>
			<uro:extendedAttribute>
				<uro:KeyValuePair>
					<uro:key codeSpace="../../codelists/extendedAttribute_key.xml">105</uro:key>
					<uro:codeValue codeSpace="../../codelists/extendedAttribute_key105.xml">2</uro:codeValue>
				</uro:KeyValuePair>
			</uro:extendedAttribute>
			<uro:extendedAttribute>
				<uro:KeyValuePair>
					<uro:key codeSpace="../../codelists/extendedAttribute_key.xml">106</uro:key>
					<uro:codeValue codeSpace="../../codelists/extendedAttribute_key106.xml">20</uro:codeValue>
				</uro:KeyValuePair>
			</uro:extendedAttribute>
		</bldg:Building>
	</core:cityObjectMember>
</core:CityModel>
