<?xml version="1.0" encoding="UTF-8"?>
<core:CityModel xmlns:grp="http://www.opengis.net/citygml/cityobjectgroup/2.0" xmlns:core="http://www.opengis.net/citygml/2.0" xmlns:pbase="http://www.opengis.net/citygml/profiles/base/2.0" xmlns:smil20lang="http://www.w3.org/2001/SMIL20/Language" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:smil20="http://www.w3.org/2001/SMIL20/" xmlns:bldg="http://www.opengis.net/citygml/building/2.0" xmlns:xAL="urn:oasis:names:tc:ciq:xsdschema:xAL:2.0" xmlns:uro="http://www.kantei.go.jp/jp/singi/tiiki/toshisaisei/itoshisaisei/iur/uro/1.4" xmlns:luse="http://www.opengis.net/citygml/landuse/2.0" xmlns:app="http://www.opengis.net/citygml/appearance/2.0" xmlns:gen="http://www.opengis.net/citygml/generics/2.0" xmlns:dem="http://www.opengis.net/citygml/relief/2.0" xmlns:tex="http://www.opengis.net/citygml/texturedsurface/2.0" xmlns:tun="http://www.opengis.net/citygml/tunnel/2.0" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:sch="http://www.ascc.net/xml/schematron" xmlns:veg="http://www.opengis.net/citygml/vegetation/2.0" xmlns:frn="http://www.opengis.net/citygml/cityfurniture/2.0" xmlns:gml="http://www.opengis.net/gml" xmlns:tran="http://www.opengis.net/citygml/transportation/2.0" xmlns:wtr="http://www.opengis.net/citygml/waterbody/2.0" xmlns:brid="http://www.opengis.net/citygml/bridge/2.0" xsi:schemaLocation="http://www.kantei.go.jp/jp/singi/tiiki/toshisaisei/itoshisaisei/iur/uro/1.4 http://www.kantei.go.jp/jp/singi/tiiki/toshisaisei/itoshisaisei/iur/schemas/uro/1.4/urbanObject.xsd http://www.opengis.net/citygml/2.0 http://schemas.opengis.net/citygml/2.0/cityGMLBase.xsd http://www.opengis.net/citygml/landuse/2.0 http://schemas.opengis.net/citygml/landuse/2.0/landUse.xsd http://www.opengis.net/citygml/building/2.0 http://schemas.opengis.net/citygml/building/2.0/building.xsd http://www.opengis.net/citygml/transportation/2.0 http://schemas.opengis.net/citygml/transportation/2.0/transportation.xsd http://www.opengis.net/citygml/generics/2.0 http://schemas.opengis.net/citygml/generics/2.0/generics.xsd http://www.opengis.net/citygml/cityobjectgroup/2.0 http://schemas.opengis.net/citygml/cityobjectgroup/2.0/cityObjectGroup.xsd http://www.opengis.net/gml http://schemas.opengis.net/gml/3.1.1/base/gml.xsd http://www.opengis.net/citygml/appearance/2.0 http://schemas.opengis.net/citygml/appearance/2.0/appearance.xsd">
	<gml:boundedBy>
		<gml:Envelope srsName="http://www.opengis.net/def/crs/EPSG/0/6697" srsDimension="3">
			<gml:lowerCorner>35.56647195757135 139.7241207469562 1.009</gml:lowerCorner>
			<gml:upperCorner>35.57509621366029 139.737560968697 46.259</gml:upperCorner>
		</gml:Envelope>
	</gml:boundedBy>
	<core:cityObjectMember>
		<bldg:Building gml:id="BLD_78ca68b0-51ba-4699-9378-54310edbedee">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-64174</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="大字・町コード">
				<gen:value>6</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="町・丁目コード">
				<gen:value>5</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
				<gen:value>13111006005</gen:value>
			</gen:stringAttribute>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">0.770</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">3.98</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<gen:genericAttributeSet name="城南地区河川流域洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">0.697</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">2</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">7.6</bldg:measuredHeight>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.572121804100114 139.72779187776362 9.636000000000001 35.572122476662805 139.72778790509022 9.636000000000001 35.57212220566725 139.72778724348046 9.636000000000001 35.57206649243709 139.7277787110137 9.636000000000001 35.57205577225188 139.7278889421933 9.636000000000001 35.57211161981401 139.7278964816115 9.636000000000001 35.572121804100114 139.72779187776362 9.636000000000001</gml:posList>
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
											<gml:posList>35.572121804100114 139.72779187776362 2.621 35.57211161981401 139.7278964816115 2.621 35.57205577225188 139.7278889421933 2.621 35.57206649243709 139.7277787110137 2.621 35.57212220566725 139.72778724348046 2.621 35.572122476662805 139.72778790509022 2.621 35.572121804100114 139.72779187776362 2.621</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.572121804100114 139.72779187776362 2.621 35.572122476662805 139.72778790509022 2.621 35.572122476662805 139.72778790509022 9.636 35.572121804100114 139.72779187776362 9.636 35.572121804100114 139.72779187776362 2.621</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.572122476662805 139.72778790509022 2.621 35.57212220566725 139.72778724348046 2.621 35.57212220566725 139.72778724348046 9.636 35.572122476662805 139.72778790509022 9.636 35.572122476662805 139.72778790509022 2.621</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57212220566725 139.72778724348046 2.621 35.57206649243709 139.7277787110137 2.621 35.57206649243709 139.7277787110137 9.636 35.57212220566725 139.72778724348046 9.636 35.57212220566725 139.72778724348046 2.621</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57206649243709 139.7277787110137 2.621 35.57205577225188 139.7278889421933 2.621 35.57205577225188 139.7278889421933 9.636 35.57206649243709 139.7277787110137 9.636 35.57206649243709 139.7277787110137 2.621</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57205577225188 139.7278889421933 2.621 35.57211161981401 139.7278964816115 2.621 35.57211161981401 139.7278964816115 9.636 35.57205577225188 139.7278889421933 9.636 35.57205577225188 139.7278889421933 2.621</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57211161981401 139.7278964816115 2.621 35.572121804100114 139.72779187776362 2.621 35.572121804100114 139.72779187776362 9.636 35.57211161981401 139.7278964816115 9.636 35.57211161981401 139.7278964816115 2.621</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.572121804100114 139.72779187776362 9.636 35.572122476662805 139.72778790509022 9.636 35.57212220566725 139.72778724348046 9.636 35.57206649243709 139.7277787110137 9.636 35.57205577225188 139.7278889421933 9.636 35.57211161981401 139.7278964816115 9.636 35.572121804100114 139.72779187776362 9.636</gml:posList>
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
									<xAL:LocalityName Type="Town">東京都大田区大森西五丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">62.44493</uro:buildingRoofEdgeArea>
					<uro:districtsAndZonesType codeSpace="../../codelists/Common_districtsAndZonesType.xml">5</uro:districtsAndZonesType>
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
		<bldg:Building gml:id="BLD_ab67288d-ca91-43a2-9036-9f00b98d18cb">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-64135</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="大字・町コード">
				<gen:value>6</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="町・丁目コード">
				<gen:value>5</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
				<gen:value>13111006005</gen:value>
			</gen:stringAttribute>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">0.850</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">4.65</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<gen:genericAttributeSet name="城南地区河川流域洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">0.697</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">2</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">9.4</bldg:measuredHeight>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.57209507256327 139.72799182625204 11.556000000000001 35.5721068895604 139.72789880468724 11.556000000000001 35.57205577225188 139.7278889421933 11.556000000000001 35.57204928139935 139.72788795775173 11.556000000000001 35.572037464121145 139.72798064826958 11.556000000000001 35.57209507256327 139.72799182625204 11.556000000000001</gml:posList>
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
											<gml:posList>35.57209507256327 139.72799182625204 2.665 35.572037464121145 139.72798064826958 2.665 35.57204928139935 139.72788795775173 2.665 35.57205577225188 139.7278889421933 2.665 35.5721068895604 139.72789880468724 2.665 35.57209507256327 139.72799182625204 2.665</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57209507256327 139.72799182625204 2.665 35.5721068895604 139.72789880468724 2.665 35.5721068895604 139.72789880468724 11.556000000000001 35.57209507256327 139.72799182625204 11.556000000000001 35.57209507256327 139.72799182625204 2.665</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.5721068895604 139.72789880468724 2.665 35.57205577225188 139.7278889421933 2.665 35.57205577225188 139.7278889421933 11.556000000000001 35.5721068895604 139.72789880468724 11.556000000000001 35.5721068895604 139.72789880468724 2.665</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57205577225188 139.7278889421933 2.665 35.57204928139935 139.72788795775173 2.665 35.57204928139935 139.72788795775173 11.556000000000001 35.57205577225188 139.7278889421933 11.556000000000001 35.57205577225188 139.7278889421933 2.665</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57204928139935 139.72788795775173 2.665 35.572037464121145 139.72798064826958 2.665 35.572037464121145 139.72798064826958 11.556000000000001 35.57204928139935 139.72788795775173 11.556000000000001 35.57204928139935 139.72788795775173 2.665</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.572037464121145 139.72798064826958 2.665 35.57209507256327 139.72799182625204 2.665 35.57209507256327 139.72799182625204 11.556000000000001 35.572037464121145 139.72798064826958 11.556000000000001 35.572037464121145 139.72798064826958 2.665</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57209507256327 139.72799182625204 11.556000000000001 35.5721068895604 139.72789880468724 11.556000000000001 35.57205577225188 139.7278889421933 11.556000000000001 35.57204928139935 139.72788795775173 11.556000000000001 35.572037464121145 139.72798064826958 11.556000000000001 35.57209507256327 139.72799182625204 11.556000000000001</gml:posList>
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
									<xAL:LocalityName Type="Town">東京都大田区大森西五丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">55.16730</uro:buildingRoofEdgeArea>
					<uro:districtsAndZonesType codeSpace="../../codelists/Common_districtsAndZonesType.xml">5</uro:districtsAndZonesType>
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
