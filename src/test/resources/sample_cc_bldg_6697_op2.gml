<?xml version="1.0" encoding="UTF-8"?>
<core:CityModel xmlns:grp="http://www.opengis.net/citygml/cityobjectgroup/2.0" xmlns:core="http://www.opengis.net/citygml/2.0" xmlns:pbase="http://www.opengis.net/citygml/profiles/base/2.0" xmlns:smil20lang="http://www.w3.org/2001/SMIL20/Language" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:smil20="http://www.w3.org/2001/SMIL20/" xmlns:bldg="http://www.opengis.net/citygml/building/2.0" xmlns:xAL="urn:oasis:names:tc:ciq:xsdschema:xAL:2.0" xmlns:uro="http://www.kantei.go.jp/jp/singi/tiiki/toshisaisei/itoshisaisei/iur/uro/1.4" xmlns:luse="http://www.opengis.net/citygml/landuse/2.0" xmlns:app="http://www.opengis.net/citygml/appearance/2.0" xmlns:gen="http://www.opengis.net/citygml/generics/2.0" xmlns:dem="http://www.opengis.net/citygml/relief/2.0" xmlns:tex="http://www.opengis.net/citygml/texturedsurface/2.0" xmlns:tun="http://www.opengis.net/citygml/tunnel/2.0" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:sch="http://www.ascc.net/xml/schematron" xmlns:veg="http://www.opengis.net/citygml/vegetation/2.0" xmlns:frn="http://www.opengis.net/citygml/cityfurniture/2.0" xmlns:gml="http://www.opengis.net/gml" xmlns:tran="http://www.opengis.net/citygml/transportation/2.0" xmlns:wtr="http://www.opengis.net/citygml/waterbody/2.0" xmlns:brid="http://www.opengis.net/citygml/bridge/2.0" xsi:schemaLocation="http://www.kantei.go.jp/jp/singi/tiiki/toshisaisei/itoshisaisei/iur/uro/1.4 http://www.kantei.go.jp/jp/singi/tiiki/toshisaisei/itoshisaisei/iur/schemas/uro/1.4/urbanObject.xsd http://www.opengis.net/citygml/2.0 http://schemas.opengis.net/citygml/2.0/cityGMLBase.xsd http://www.opengis.net/citygml/landuse/2.0 http://schemas.opengis.net/citygml/landuse/2.0/landUse.xsd http://www.opengis.net/citygml/building/2.0 http://schemas.opengis.net/citygml/building/2.0/building.xsd http://www.opengis.net/citygml/transportation/2.0 http://schemas.opengis.net/citygml/transportation/2.0/transportation.xsd http://www.opengis.net/citygml/generics/2.0 http://schemas.opengis.net/citygml/generics/2.0/generics.xsd http://www.opengis.net/citygml/cityobjectgroup/2.0 http://schemas.opengis.net/citygml/cityobjectgroup/2.0/cityObjectGroup.xsd http://www.opengis.net/gml http://schemas.opengis.net/gml/3.1.1/base/gml.xsd http://www.opengis.net/citygml/appearance/2.0 http://schemas.opengis.net/citygml/appearance/2.0/appearance.xsd">
	<gml:boundedBy>
		<gml:Envelope srsName="http://www.opengis.net/def/crs/EPSG/0/6697" srsDimension="3">
			<gml:lowerCorner>35.56647195757135 139.7241207469562 1.009</gml:lowerCorner>
			<gml:upperCorner>35.57509621366029 139.737560968697 46.259</gml:upperCorner>
		</gml:Envelope>
	</gml:boundedBy>
	<core:cityObjectMember>
		<bldg:Building gml:id="BLD_140b39ca-b71b-4e69-9103-5a32409f02aa">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-59928</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="大字・町コード">
				<gen:value>5</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="町・丁目コード">
				<gen:value>1</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
				<gen:value>13111005001</gen:value>
			</gen:stringAttribute>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">0.720</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">3.73</gen:value>
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
					<gen:value uom="m">0.410</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">7.1</bldg:measuredHeight>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.57053034415612 139.73327128798127 8.736 35.570530555487515 139.73320012786132 8.736 35.57045943549931 139.73319955440357 8.736 35.57045930908795 139.73321014578605 8.736 35.57044578828207 139.73321016260812 8.736 35.57044555359296 139.73325318974508 8.736 35.57046110251974 139.73325317040798 8.736 35.5704609942342 139.73328560617045 8.736 35.57052129757752 139.73328619315174 8.736 35.57052142042433 139.7332712990771 8.736 35.57053034415612 139.73327128798127 8.736</gml:posList>
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
											<gml:posList>35.57053034415612 139.73327128798127 2.483 35.57052142042433 139.7332712990771 2.483 35.57052129757752 139.73328619315174 2.483 35.5704609942342 139.73328560617045 2.483 35.57046110251974 139.73325317040798 2.483 35.57044555359296 139.73325318974508 2.483 35.57044578828207 139.73321016260812 2.483 35.57045930908795 139.73321014578605 2.483 35.57045943549931 139.73319955440357 2.483 35.570530555487515 139.73320012786132 2.483 35.57053034415612 139.73327128798127 2.483</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57053034415612 139.73327128798127 2.483 35.570530555487515 139.73320012786132 2.483 35.570530555487515 139.73320012786132 8.736 35.57053034415612 139.73327128798127 8.736 35.57053034415612 139.73327128798127 2.483</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.570530555487515 139.73320012786132 2.483 35.57045943549931 139.73319955440357 2.483 35.57045943549931 139.73319955440357 8.736 35.570530555487515 139.73320012786132 8.736 35.570530555487515 139.73320012786132 2.483</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57045943549931 139.73319955440357 2.483 35.57045930908795 139.73321014578605 2.483 35.57045930908795 139.73321014578605 8.736 35.57045943549931 139.73319955440357 8.736 35.57045943549931 139.73319955440357 2.483</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57045930908795 139.73321014578605 2.483 35.57044578828207 139.73321016260812 2.483 35.57044578828207 139.73321016260812 8.736 35.57045930908795 139.73321014578605 8.736 35.57045930908795 139.73321014578605 2.483</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57044578828207 139.73321016260812 2.483 35.57044555359296 139.73325318974508 2.483 35.57044555359296 139.73325318974508 8.736 35.57044578828207 139.73321016260812 8.736 35.57044578828207 139.73321016260812 2.483</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57044555359296 139.73325318974508 2.483 35.57046110251974 139.73325317040798 2.483 35.57046110251974 139.73325317040798 8.736 35.57044555359296 139.73325318974508 8.736 35.57044555359296 139.73325318974508 2.483</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57046110251974 139.73325317040798 2.483 35.5704609942342 139.73328560617045 2.483 35.5704609942342 139.73328560617045 8.736 35.57046110251974 139.73325317040798 8.736 35.57046110251974 139.73325317040798 2.483</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.5704609942342 139.73328560617045 2.483 35.57052129757752 139.73328619315174 2.483 35.57052129757752 139.73328619315174 8.736 35.5704609942342 139.73328560617045 8.736 35.5704609942342 139.73328560617045 2.483</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57052129757752 139.73328619315174 2.483 35.57052142042433 139.7332712990771 2.483 35.57052142042433 139.7332712990771 8.736 35.57052129757752 139.73328619315174 8.736 35.57052129757752 139.73328619315174 2.483</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57052142042433 139.7332712990771 2.483 35.57053034415612 139.73327128798127 2.483 35.57053034415612 139.73327128798127 8.736 35.57052142042433 139.7332712990771 8.736 35.57052142042433 139.7332712990771 2.483</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57053034415612 139.73327128798127 8.736 35.570530555487515 139.73320012786132 8.736 35.57045943549931 139.73319955440357 8.736 35.57045930908795 139.73321014578605 8.736 35.57044578828207 139.73321016260812 8.736 35.57044555359296 139.73325318974508 8.736 35.57046110251974 139.73325317040798 8.736 35.5704609942342 139.73328560617045 8.736 35.57052129757752 139.73328619315174 8.736 35.57052142042433 139.7332712990771 8.736 35.57053034415612 139.73327128798127 8.736</gml:posList>
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
									<xAL:LocalityName Type="Town">東京都大田区大森中一丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">65.55128</uro:buildingRoofEdgeArea>
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
		<bldg:Building gml:id="BLD_47feba7b-40a2-4b37-a133-c3acb1ce4e29">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-59955</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="大字・町コード">
				<gen:value>5</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="町・丁目コード">
				<gen:value>1</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
				<gen:value>13111005001</gen:value>
			</gen:stringAttribute>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">0.520</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">3.73</gen:value>
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
					<gen:value uom="m">0.410</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">5.5</bldg:measuredHeight>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.57045724191217 139.73332598988046 5.436 35.57053985321204 139.7333248942888 5.436 35.57053941518254 139.73328583965142 5.436 35.57052129757752 139.73328619315174 5.436 35.5704609942342 139.73328560617045 5.436 35.570456532368276 139.73328561171755 5.436 35.57045724191217 139.73332598988046 5.436</gml:posList>
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
											<gml:posList>35.57045724191217 139.73332598988046 2.534 35.570456532368276 139.73328561171755 2.534 35.5704609942342 139.73328560617045 2.534 35.57052129757752 139.73328619315174 2.534 35.57053941518254 139.73328583965142 2.534 35.57053985321204 139.7333248942888 2.534 35.57045724191217 139.73332598988046 2.534</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57045724191217 139.73332598988046 2.534 35.57053985321204 139.7333248942888 2.534 35.57053985321204 139.7333248942888 5.436 35.57045724191217 139.73332598988046 5.436 35.57045724191217 139.73332598988046 2.534</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57053985321204 139.7333248942888 2.534 35.57053941518254 139.73328583965142 2.534 35.57053941518254 139.73328583965142 5.436 35.57053985321204 139.7333248942888 5.436 35.57053985321204 139.7333248942888 2.534</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57053941518254 139.73328583965142 2.534 35.57052129757752 139.73328619315174 2.534 35.57052129757752 139.73328619315174 5.436 35.57053941518254 139.73328583965142 5.436 35.57053941518254 139.73328583965142 2.534</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57052129757752 139.73328619315174 2.534 35.5704609942342 139.73328560617045 2.534 35.5704609942342 139.73328560617045 5.436 35.57052129757752 139.73328619315174 5.436 35.57052129757752 139.73328619315174 2.534</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.5704609942342 139.73328560617045 2.534 35.570456532368276 139.73328561171755 2.534 35.570456532368276 139.73328561171755 5.436 35.5704609942342 139.73328560617045 5.436 35.5704609942342 139.73328560617045 2.534</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.570456532368276 139.73328561171755 2.534 35.57045724191217 139.73332598988046 2.534 35.57045724191217 139.73332598988046 5.436 35.570456532368276 139.73328561171755 5.436 35.570456532368276 139.73328561171755 2.534</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57045724191217 139.73332598988046 5.436 35.57053985321204 139.7333248942888 5.436 35.57053941518254 139.73328583965142 5.436 35.57052129757752 139.73328619315174 5.436 35.5704609942342 139.73328560617045 5.436 35.570456532368276 139.73328561171755 5.436 35.57045724191217 139.73332598988046 5.436</gml:posList>
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
									<xAL:LocalityName Type="Town">東京都大田区大森中一丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">32.89658</uro:buildingRoofEdgeArea>
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
		<bldg:Building gml:id="BLD_8db234d1-edd7-45d0-88aa-497e07290217">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-59680</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="大字・町コード">
				<gen:value>5</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="町・丁目コード">
				<gen:value>1</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
				<gen:value>13111005001</gen:value>
			</gen:stringAttribute>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">0.530</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">1.63</gen:value>
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
					<gen:value uom="m">0.249</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">6.8</bldg:measuredHeight>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.57035881236623 139.73332842902852 8.814 35.57045724191217 139.73332598988046 8.814 35.570456532368276 139.73328561171755 8.814 35.570456105316666 139.73325979613813 8.814 35.57034861710682 139.733262577607 8.814 35.570349189527896 139.73330063905863 8.814 35.570358383676066 139.73330062762997 8.814 35.57035881236623 139.73332842902852 8.814</gml:posList>
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
											<gml:posList>35.57035881236623 139.73332842902852 2.536 35.570358383676066 139.73330062762997 2.536 35.570349189527896 139.73330063905863 2.536 35.57034861710682 139.733262577607 2.536 35.570456105316666 139.73325979613813 2.536 35.570456532368276 139.73328561171755 2.536 35.57045724191217 139.73332598988046 2.536 35.57035881236623 139.73332842902852 2.536</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57035881236623 139.73332842902852 2.536 35.57045724191217 139.73332598988046 2.536 35.57045724191217 139.73332598988046 8.814 35.57035881236623 139.73332842902852 8.814 35.57035881236623 139.73332842902852 2.536</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57045724191217 139.73332598988046 2.536 35.570456532368276 139.73328561171755 2.536 35.570456532368276 139.73328561171755 8.814 35.57045724191217 139.73332598988046 8.814 35.57045724191217 139.73332598988046 2.536</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.570456532368276 139.73328561171755 2.536 35.570456105316666 139.73325979613813 2.536 35.570456105316666 139.73325979613813 8.814 35.570456532368276 139.73328561171755 8.814 35.570456532368276 139.73328561171755 2.536</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.570456105316666 139.73325979613813 2.536 35.57034861710682 139.733262577607 2.536 35.57034861710682 139.733262577607 8.814 35.570456105316666 139.73325979613813 8.814 35.570456105316666 139.73325979613813 2.536</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57034861710682 139.733262577607 2.536 35.570349189527896 139.73330063905863 2.536 35.570349189527896 139.73330063905863 8.814 35.57034861710682 139.733262577607 8.814 35.57034861710682 139.733262577607 2.536</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.570349189527896 139.73330063905863 2.536 35.570358383676066 139.73330062762997 2.536 35.570358383676066 139.73330062762997 8.814 35.570349189527896 139.73330063905863 8.814 35.570349189527896 139.73330063905863 2.536</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.570358383676066 139.73330062762997 2.536 35.57035881236623 139.73332842902852 2.536 35.57035881236623 139.73332842902852 8.814 35.570358383676066 139.73330062762997 8.814 35.570358383676066 139.73330062762997 2.536</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57035881236623 139.73332842902852 8.814 35.57045724191217 139.73332598988046 8.814 35.570456532368276 139.73328561171755 8.814 35.570456105316666 139.73325979613813 8.814 35.57034861710682 139.733262577607 8.814 35.570349189527896 139.73330063905863 8.814 35.570358383676066 139.73330062762997 8.814 35.57035881236623 139.73332842902852 8.814</gml:posList>
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
									<xAL:LocalityName Type="Town">東京都大田区大森中一丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">68.97465</uro:buildingRoofEdgeArea>
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
