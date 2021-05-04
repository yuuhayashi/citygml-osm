<?xml version="1.0" encoding="UTF-8"?>
<core:CityModel xmlns:grp="http://www.opengis.net/citygml/cityobjectgroup/2.0" xmlns:core="http://www.opengis.net/citygml/2.0" xmlns:pbase="http://www.opengis.net/citygml/profiles/base/2.0" xmlns:smil20lang="http://www.w3.org/2001/SMIL20/Language" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:smil20="http://www.w3.org/2001/SMIL20/" xmlns:bldg="http://www.opengis.net/citygml/building/2.0" xmlns:xAL="urn:oasis:names:tc:ciq:xsdschema:xAL:2.0" xmlns:uro="http://www.kantei.go.jp/jp/singi/tiiki/toshisaisei/itoshisaisei/iur/uro/1.4" xmlns:luse="http://www.opengis.net/citygml/landuse/2.0" xmlns:app="http://www.opengis.net/citygml/appearance/2.0" xmlns:gen="http://www.opengis.net/citygml/generics/2.0" xmlns:dem="http://www.opengis.net/citygml/relief/2.0" xmlns:tex="http://www.opengis.net/citygml/texturedsurface/2.0" xmlns:tun="http://www.opengis.net/citygml/tunnel/2.0" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:sch="http://www.ascc.net/xml/schematron" xmlns:veg="http://www.opengis.net/citygml/vegetation/2.0" xmlns:frn="http://www.opengis.net/citygml/cityfurniture/2.0" xmlns:gml="http://www.opengis.net/gml" xmlns:tran="http://www.opengis.net/citygml/transportation/2.0" xmlns:wtr="http://www.opengis.net/citygml/waterbody/2.0" xmlns:brid="http://www.opengis.net/citygml/bridge/2.0" xsi:schemaLocation="http://www.kantei.go.jp/jp/singi/tiiki/toshisaisei/itoshisaisei/iur/uro/1.4 http://www.kantei.go.jp/jp/singi/tiiki/toshisaisei/itoshisaisei/iur/schemas/uro/1.4/urbanObject.xsd http://www.opengis.net/citygml/2.0 http://schemas.opengis.net/citygml/2.0/cityGMLBase.xsd http://www.opengis.net/citygml/landuse/2.0 http://schemas.opengis.net/citygml/landuse/2.0/landUse.xsd http://www.opengis.net/citygml/building/2.0 http://schemas.opengis.net/citygml/building/2.0/building.xsd http://www.opengis.net/citygml/transportation/2.0 http://schemas.opengis.net/citygml/transportation/2.0/transportation.xsd http://www.opengis.net/citygml/generics/2.0 http://schemas.opengis.net/citygml/generics/2.0/generics.xsd http://www.opengis.net/citygml/cityobjectgroup/2.0 http://schemas.opengis.net/citygml/cityobjectgroup/2.0/cityObjectGroup.xsd http://www.opengis.net/gml http://schemas.opengis.net/gml/3.1.1/base/gml.xsd http://www.opengis.net/citygml/appearance/2.0 http://schemas.opengis.net/citygml/appearance/2.0/appearance.xsd">
	<gml:boundedBy>
		<gml:Envelope srsName="http://www.opengis.net/def/crs/EPSG/0/6697" srsDimension="3">
			<gml:lowerCorner>35.54070862164589 139.71245218213994 2.02</gml:lowerCorner>
			<gml:upperCorner>35.542252321638614 139.7156383865409 40.492</gml:upperCorner>
		</gml:Envelope>
	</gml:boundedBy>
	<core:cityObjectMember>
		<bldg:Building gml:id="BLD_d61ead86-9f08-4d99-b410-28a576c764f3">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-726</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="大字・町コード">
				<gen:value>58</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="町・丁目コード">
				<gen:value>3</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
				<gen:value>13111058003</gen:value>
			</gen:stringAttribute>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（計画規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L1</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">0.650</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">2.160</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">10.82</gen:value>
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
					<gen:value uom="m">0.034</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">11.5</bldg:measuredHeight>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.541657275471835 139.7156383865409 14.072000000000001 35.542252321638614 139.71535363948732 14.072000000000001 35.54210367440277 139.7148860223014 14.072000000000001 35.54206164434519 139.71490626649856 14.072000000000001 35.5420440155531 139.7148536858433 14.072000000000001 35.541981356256336 139.7146575788015 14.072000000000001 35.54142914946131 139.71491844541285 14.072000000000001 35.54153100551663 139.71523889596378 14.072000000000001 35.541657275471835 139.7156383865409 14.072000000000001</gml:posList>
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
											<gml:posList>35.541657275471835 139.7156383865409 3.396 35.54153100551663 139.71523889596378 3.396 35.54142914946131 139.71491844541285 3.396 35.541981356256336 139.7146575788015 3.396 35.5420440155531 139.7148536858433 3.396 35.54206164434519 139.71490626649856 3.396 35.54210367440277 139.7148860223014 3.396 35.542252321638614 139.71535363948732 3.396 35.541657275471835 139.7156383865409 3.396</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541657275471835 139.7156383865409 3.396 35.542252321638614 139.71535363948732 3.396 35.542252321638614 139.71535363948732 14.072 35.541657275471835 139.7156383865409 14.072 35.541657275471835 139.7156383865409 3.396</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.542252321638614 139.71535363948732 3.396 35.54210367440277 139.7148860223014 3.396 35.54210367440277 139.7148860223014 14.072 35.542252321638614 139.71535363948732 14.072 35.542252321638614 139.71535363948732 3.396</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54210367440277 139.7148860223014 3.396 35.54206164434519 139.71490626649856 3.396 35.54206164434519 139.71490626649856 14.072 35.54210367440277 139.7148860223014 14.072 35.54210367440277 139.7148860223014 3.396</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54206164434519 139.71490626649856 3.396 35.5420440155531 139.7148536858433 3.396 35.5420440155531 139.7148536858433 14.072 35.54206164434519 139.71490626649856 14.072 35.54206164434519 139.71490626649856 3.396</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.5420440155531 139.7148536858433 3.396 35.541981356256336 139.7146575788015 3.396 35.541981356256336 139.7146575788015 14.072 35.5420440155531 139.7148536858433 14.072 35.5420440155531 139.7148536858433 3.396</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541981356256336 139.7146575788015 3.396 35.54142914946131 139.71491844541285 3.396 35.54142914946131 139.71491844541285 14.072 35.541981356256336 139.7146575788015 14.072 35.541981356256336 139.7146575788015 3.396</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54142914946131 139.71491844541285 3.396 35.54153100551663 139.71523889596378 3.396 35.54153100551663 139.71523889596378 14.072 35.54142914946131 139.71491844541285 14.072 35.54142914946131 139.71491844541285 3.396</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54153100551663 139.71523889596378 3.396 35.541657275471835 139.7156383865409 3.396 35.541657275471835 139.7156383865409 14.072 35.54153100551663 139.71523889596378 14.072 35.54153100551663 139.71523889596378 3.396</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541657275471835 139.7156383865409 14.072 35.542252321638614 139.71535363948732 14.072 35.54210367440277 139.7148860223014 14.072 35.54206164434519 139.71490626649856 14.072 35.5420440155531 139.7148536858433 14.072 35.541981356256336 139.7146575788015 14.072 35.54142914946131 139.71491844541285 14.072 35.54153100551663 139.71523889596378 14.072 35.541657275471835 139.7156383865409 14.072</gml:posList>
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
									<xAL:LocalityName Type="Town">東京都大田区南六郷三丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">4824.34920</uro:buildingRoofEdgeArea>
					<uro:districtsAndZonesType codeSpace="../../codelists/Common_districtsAndZonesType.xml">12</uro:districtsAndZonesType>
					<uro:prefecture codeSpace="../../codelists/Common_prefecture.xml">13</uro:prefecture>
					<uro:city codeSpace="../../codelists/Common_localPublicAuthorities.xml">13111</uro:city>
					<uro:surveyYear>2016</uro:surveyYear>
				</uro:BuildingDetails>
			</uro:buildingDetails>
			<uro:extendedAttribute>
				<uro:KeyValuePair>
					<uro:key codeSpace="../../codelists/extendedAttribute_key.xml">106</uro:key>
					<uro:codeValue codeSpace="../../codelists/extendedAttribute_key106.xml">20</uro:codeValue>
				</uro:KeyValuePair>
			</uro:extendedAttribute>
		</bldg:Building>
	</core:cityObjectMember>
	<core:cityObjectMember>
		<bldg:Building gml:id="BLD_e638f827-fbb1-4016-9bc4-a24f8a1ca10a">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-446</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="大字・町コード">
				<gen:value>58</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="町・丁目コード">
				<gen:value>3</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
				<gen:value>13111058003</gen:value>
			</gen:stringAttribute>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（計画規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L1</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">1.640</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">2.710</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">10.93</gen:value>
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
					<gen:value uom="m">0.096</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">2.2</bldg:measuredHeight>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.541437548301566 139.7139744996351 4.973 35.541438333025894 139.71394769910043 4.973 35.54136531676588 139.71394416788792 4.973 35.54136453204228 139.71397096839829 4.973 35.541437548301566 139.7139744996351 4.973</gml:posList>
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
											<gml:posList>35.541437548301566 139.7139744996351 2.829 35.54136453204228 139.71397096839829 2.829 35.54136531676588 139.71394416788792 2.829 35.541438333025894 139.71394769910043 2.829 35.541437548301566 139.7139744996351 2.829</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541437548301566 139.7139744996351 2.829 35.541438333025894 139.71394769910043 2.829 35.541438333025894 139.71394769910043 4.973000000000001 35.541437548301566 139.7139744996351 4.973000000000001 35.541437548301566 139.7139744996351 2.829</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541438333025894 139.71394769910043 2.829 35.54136531676588 139.71394416788792 2.829 35.54136531676588 139.71394416788792 4.973000000000001 35.541438333025894 139.71394769910043 4.973000000000001 35.541438333025894 139.71394769910043 2.829</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54136531676588 139.71394416788792 2.829 35.54136453204228 139.71397096839829 2.829 35.54136453204228 139.71397096839829 4.973000000000001 35.54136531676588 139.71394416788792 4.973000000000001 35.54136531676588 139.71394416788792 2.829</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54136453204228 139.71397096839829 2.829 35.541437548301566 139.7139744996351 2.829 35.541437548301566 139.7139744996351 4.973000000000001 35.54136453204228 139.71397096839829 4.973000000000001 35.54136453204228 139.71397096839829 2.829</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541437548301566 139.7139744996351 4.973000000000001 35.541438333025894 139.71394769910043 4.973000000000001 35.54136531676588 139.71394416788792 4.973000000000001 35.54136453204228 139.71397096839829 4.973000000000001 35.541437548301566 139.7139744996351 4.973000000000001</gml:posList>
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
									<xAL:LocalityName Type="Town">東京都大田区南六郷三丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">19.71270</uro:buildingRoofEdgeArea>
					<uro:districtsAndZonesType codeSpace="../../codelists/Common_districtsAndZonesType.xml">12</uro:districtsAndZonesType>
					<uro:prefecture codeSpace="../../codelists/Common_prefecture.xml">13</uro:prefecture>
					<uro:city codeSpace="../../codelists/Common_localPublicAuthorities.xml">13111</uro:city>
					<uro:surveyYear>2016</uro:surveyYear>
				</uro:BuildingDetails>
			</uro:buildingDetails>
			<uro:extendedAttribute>
				<uro:KeyValuePair>
					<uro:key codeSpace="../../codelists/extendedAttribute_key.xml">106</uro:key>
					<uro:codeValue codeSpace="../../codelists/extendedAttribute_key106.xml">20</uro:codeValue>
				</uro:KeyValuePair>
			</uro:extendedAttribute>
		</bldg:Building>
	</core:cityObjectMember>
	<core:cityObjectMember>
		<bldg:Building gml:id="BLD_9309748b-c3ca-491a-b8d8-e437675a6bfc">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-512</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="大字・町コード">
				<gen:value>58</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="町・丁目コード">
				<gen:value>3</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
				<gen:value>13111058003</gen:value>
			</gen:stringAttribute>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（計画規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L1</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">1.360</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">2.710</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">11.72</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">2.3</bldg:measuredHeight>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.5414799627116 139.71379643593485 5.105 35.5415848357262 139.7137469826227 5.105 35.54157520572637 139.71371655807323 5.105 35.541470332724145 139.713766011419 5.105 35.5414799627116 139.71379643593485 5.105</gml:posList>
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
											<gml:posList>35.5414799627116 139.71379643593485 2.812 35.541470332724145 139.713766011419 2.812 35.54157520572637 139.71371655807323 2.812 35.5415848357262 139.7137469826227 2.812 35.5414799627116 139.71379643593485 2.812</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.5414799627116 139.71379643593485 2.812 35.5415848357262 139.7137469826227 2.812 35.5415848357262 139.7137469826227 5.105 35.5414799627116 139.71379643593485 5.105 35.5414799627116 139.71379643593485 2.812</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.5415848357262 139.7137469826227 2.812 35.54157520572637 139.71371655807323 2.812 35.54157520572637 139.71371655807323 5.105 35.5415848357262 139.7137469826227 5.105 35.5415848357262 139.7137469826227 2.812</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54157520572637 139.71371655807323 2.812 35.541470332724145 139.713766011419 2.812 35.541470332724145 139.713766011419 5.105 35.54157520572637 139.71371655807323 5.105 35.54157520572637 139.71371655807323 2.812</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541470332724145 139.713766011419 2.812 35.5414799627116 139.71379643593485 2.812 35.5414799627116 139.71379643593485 5.105 35.541470332724145 139.713766011419 5.105 35.541470332724145 139.713766011419 2.812</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.5414799627116 139.71379643593485 5.105 35.5415848357262 139.7137469826227 5.105 35.54157520572637 139.71371655807323 5.105 35.541470332724145 139.713766011419 5.105 35.5414799627116 139.71379643593485 5.105</gml:posList>
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
									<xAL:LocalityName Type="Town">東京都大田区南六郷三丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">36.88695</uro:buildingRoofEdgeArea>
					<uro:districtsAndZonesType codeSpace="../../codelists/Common_districtsAndZonesType.xml">12</uro:districtsAndZonesType>
					<uro:prefecture codeSpace="../../codelists/Common_prefecture.xml">13</uro:prefecture>
					<uro:city codeSpace="../../codelists/Common_localPublicAuthorities.xml">13111</uro:city>
					<uro:surveyYear>2016</uro:surveyYear>
				</uro:BuildingDetails>
			</uro:buildingDetails>
			<uro:extendedAttribute>
				<uro:KeyValuePair>
					<uro:key codeSpace="../../codelists/extendedAttribute_key.xml">106</uro:key>
					<uro:codeValue codeSpace="../../codelists/extendedAttribute_key106.xml">20</uro:codeValue>
				</uro:KeyValuePair>
			</uro:extendedAttribute>
		</bldg:Building>
	</core:cityObjectMember>
	<core:cityObjectMember>
		<bldg:Building gml:id="BLD_d0166b26-7838-4a4f-b5b5-81d29a29b0b2">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-324</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="大字・町コード">
				<gen:value>58</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="町・丁目コード">
				<gen:value>3</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
				<gen:value>13111058003</gen:value>
			</gen:stringAttribute>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（計画規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L1</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">2.200</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">2.970</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">11.47</gen:value>
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
					<gen:value uom="m">0.202</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">3.0</bldg:measuredHeight>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.5411374136504 139.71427734597282 5.837 35.541162822093874 139.7142663901982 5.837 35.541143014322714 139.71419826329537 5.837 35.54111747100356 139.71420955014332 5.837 35.5411374136504 139.71427734597282 5.837</gml:posList>
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
											<gml:posList>35.5411374136504 139.71427734597282 2.932 35.54111747100356 139.71420955014332 2.932 35.541143014322714 139.71419826329537 2.932 35.541162822093874 139.7142663901982 2.932 35.5411374136504 139.71427734597282 2.932</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.5411374136504 139.71427734597282 2.932 35.541162822093874 139.7142663901982 2.932 35.541162822093874 139.7142663901982 5.837 35.5411374136504 139.71427734597282 5.837 35.5411374136504 139.71427734597282 2.932</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541162822093874 139.7142663901982 2.932 35.541143014322714 139.71419826329537 2.932 35.541143014322714 139.71419826329537 5.837 35.541162822093874 139.7142663901982 5.837 35.541162822093874 139.7142663901982 2.932</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541143014322714 139.71419826329537 2.932 35.54111747100356 139.71420955014332 2.932 35.54111747100356 139.71420955014332 5.837 35.541143014322714 139.71419826329537 5.837 35.541143014322714 139.71419826329537 2.932</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54111747100356 139.71420955014332 2.932 35.5411374136504 139.71427734597282 2.932 35.5411374136504 139.71427734597282 5.837 35.54111747100356 139.71420955014332 5.837 35.54111747100356 139.71420955014332 2.932</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.5411374136504 139.71427734597282 5.837 35.541162822093874 139.7142663901982 5.837 35.541143014322714 139.71419826329537 5.837 35.54111747100356 139.71420955014332 5.837 35.5411374136504 139.71427734597282 5.837</gml:posList>
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
									<xAL:LocalityName Type="Town">東京都大田区南六郷三丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">19.64003</uro:buildingRoofEdgeArea>
					<uro:districtsAndZonesType codeSpace="../../codelists/Common_districtsAndZonesType.xml">12</uro:districtsAndZonesType>
					<uro:prefecture codeSpace="../../codelists/Common_prefecture.xml">13</uro:prefecture>
					<uro:city codeSpace="../../codelists/Common_localPublicAuthorities.xml">13111</uro:city>
					<uro:surveyYear>2016</uro:surveyYear>
				</uro:BuildingDetails>
			</uro:buildingDetails>
			<uro:extendedAttribute>
				<uro:KeyValuePair>
					<uro:key codeSpace="../../codelists/extendedAttribute_key.xml">106</uro:key>
					<uro:codeValue codeSpace="../../codelists/extendedAttribute_key106.xml">20</uro:codeValue>
				</uro:KeyValuePair>
			</uro:extendedAttribute>
		</bldg:Building>
	</core:cityObjectMember>
	<core:cityObjectMember>
		<bldg:Building gml:id="BLD_6061ba8e-adc5-4f92-9490-7ef1a6b1c0f1">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-441</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="大字・町コード">
				<gen:value>58</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="町・丁目コード">
				<gen:value>3</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
				<gen:value>13111058003</gen:value>
			</gen:stringAttribute>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（計画規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L1</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">1.910</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">2.970</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">11.72</gen:value>
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
					<gen:value uom="m">0.080</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">2.3</bldg:measuredHeight>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.541248635711 139.71326741011697 4.8655 35.541422725768555 139.7132082582225 4.8655 35.541415121124885 139.71317518392723 4.8655 35.541241031083466 139.7132343358875 4.8655 35.541248635711 139.71326741011697 4.8655</gml:posList>
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
											<gml:posList>35.541248635711 139.71326741011697 2.672 35.541241031083466 139.7132343358875 2.672 35.541415121124885 139.71317518392723 2.672 35.541422725768555 139.7132082582225 2.672 35.541248635711 139.71326741011697 2.672</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541248635711 139.71326741011697 2.672 35.541422725768555 139.7132082582225 2.672 35.541422725768555 139.7132082582225 4.866 35.541248635711 139.71326741011697 4.866 35.541248635711 139.71326741011697 2.672</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541422725768555 139.7132082582225 2.672 35.541415121124885 139.71317518392723 2.672 35.541415121124885 139.71317518392723 4.866 35.541422725768555 139.7132082582225 4.866 35.541422725768555 139.7132082582225 2.672</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541415121124885 139.71317518392723 2.672 35.541241031083466 139.7132343358875 2.672 35.541241031083466 139.7132343358875 4.866 35.541415121124885 139.71317518392723 4.866 35.541415121124885 139.71317518392723 2.672</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541241031083466 139.7132343358875 2.672 35.541248635711 139.71326741011697 2.672 35.541248635711 139.71326741011697 4.866 35.541241031083466 139.7132343358875 4.866 35.541241031083466 139.7132343358875 2.672</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541248635711 139.71326741011697 4.866 35.541422725768555 139.7132082582225 4.866 35.541415121124885 139.71317518392723 4.866 35.541241031083466 139.7132343358875 4.866 35.541248635711 139.71326741011697 4.866</gml:posList>
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
									<xAL:LocalityName Type="Town">東京都大田区南六郷三丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">62.44560</uro:buildingRoofEdgeArea>
					<uro:districtsAndZonesType codeSpace="../../codelists/Common_districtsAndZonesType.xml">12</uro:districtsAndZonesType>
					<uro:prefecture codeSpace="../../codelists/Common_prefecture.xml">13</uro:prefecture>
					<uro:city codeSpace="../../codelists/Common_localPublicAuthorities.xml">13111</uro:city>
					<uro:surveyYear>2016</uro:surveyYear>
				</uro:BuildingDetails>
			</uro:buildingDetails>
			<uro:extendedAttribute>
				<uro:KeyValuePair>
					<uro:key codeSpace="../../codelists/extendedAttribute_key.xml">106</uro:key>
					<uro:codeValue codeSpace="../../codelists/extendedAttribute_key106.xml">20</uro:codeValue>
				</uro:KeyValuePair>
			</uro:extendedAttribute>
		</bldg:Building>
	</core:cityObjectMember>
	<core:cityObjectMember>
		<bldg:Building gml:id="BLD_c567335d-ba57-4436-bc40-5ccf613f40b5">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-365</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="大字・町コード">
				<gen:value>58</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="町・丁目コード">
				<gen:value>3</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
				<gen:value>13111058003</gen:value>
			</gen:stringAttribute>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（計画規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L1</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">1.910</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">2.970</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">11.72</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">2.4</bldg:measuredHeight>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.541162172990724 139.71333867286907 5.127 35.54125637323291 139.7132981681648 5.127 35.54123968459845 139.71323996244044 5.127 35.541145348837674 139.71328013655102 5.127 35.541162172990724 139.71333867286907 5.127</gml:posList>
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
											<gml:posList>35.541162172990724 139.71333867286907 2.749 35.541145348837674 139.71328013655102 2.749 35.54123968459845 139.71323996244044 2.749 35.54125637323291 139.7132981681648 2.749 35.541162172990724 139.71333867286907 2.749</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541162172990724 139.71333867286907 2.749 35.54125637323291 139.7132981681648 2.749 35.54125637323291 139.7132981681648 5.127000000000001 35.541162172990724 139.71333867286907 5.127000000000001 35.541162172990724 139.71333867286907 2.749</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54125637323291 139.7132981681648 2.749 35.54123968459845 139.71323996244044 2.749 35.54123968459845 139.71323996244044 5.127000000000001 35.54125637323291 139.7132981681648 5.127000000000001 35.54125637323291 139.7132981681648 2.749</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54123968459845 139.71323996244044 2.749 35.541145348837674 139.71328013655102 2.749 35.541145348837674 139.71328013655102 5.127000000000001 35.54123968459845 139.71323996244044 5.127000000000001 35.54123968459845 139.71323996244044 2.749</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541145348837674 139.71328013655102 2.749 35.541162172990724 139.71333867286907 2.749 35.541162172990724 139.71333867286907 5.127000000000001 35.541145348837674 139.71328013655102 5.127000000000001 35.541145348837674 139.71328013655102 2.749</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541162172990724 139.71333867286907 5.127000000000001 35.54125637323291 139.7132981681648 5.127000000000001 35.54123968459845 139.71323996244044 5.127000000000001 35.541145348837674 139.71328013655102 5.127000000000001 35.541162172990724 139.71333867286907 5.127000000000001</gml:posList>
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
									<xAL:LocalityName Type="Town">東京都大田区南六郷三丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">62.15130</uro:buildingRoofEdgeArea>
					<uro:districtsAndZonesType codeSpace="../../codelists/Common_districtsAndZonesType.xml">12</uro:districtsAndZonesType>
					<uro:prefecture codeSpace="../../codelists/Common_prefecture.xml">13</uro:prefecture>
					<uro:city codeSpace="../../codelists/Common_localPublicAuthorities.xml">13111</uro:city>
					<uro:surveyYear>2016</uro:surveyYear>
				</uro:BuildingDetails>
			</uro:buildingDetails>
			<uro:extendedAttribute>
				<uro:KeyValuePair>
					<uro:key codeSpace="../../codelists/extendedAttribute_key.xml">106</uro:key>
					<uro:codeValue codeSpace="../../codelists/extendedAttribute_key106.xml">20</uro:codeValue>
				</uro:KeyValuePair>
			</uro:extendedAttribute>
		</bldg:Building>
	</core:cityObjectMember>
	<core:cityObjectMember>
		<bldg:Building gml:id="BLD_32708324-2cc9-440f-889b-9ae29a9b57d5">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-436</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="大字・町コード">
				<gen:value>58</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="町・丁目コード">
				<gen:value>3</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
				<gen:value>13111058003</gen:value>
			</gen:stringAttribute>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（計画規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L1</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">1.730</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>3</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">3.160</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">13.58</gen:value>
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
					<gen:value uom="m">0.024</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">3.0</bldg:measuredHeight>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.5413605001386 139.71317857395672 5.4025 35.54141077895425 139.71315964015625 5.4025 35.54139516763394 139.71309713161 5.4025 35.54134475361942 139.7131160656475 5.4025 35.5413605001386 139.71317857395672 5.4025</gml:posList>
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
											<gml:posList>35.5413605001386 139.71317857395672 2.711 35.54134475361942 139.7131160656475 2.711 35.54139516763394 139.71309713161 2.711 35.54141077895425 139.71315964015625 2.711 35.5413605001386 139.71317857395672 2.711</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.5413605001386 139.71317857395672 2.711 35.54141077895425 139.71315964015625 2.711 35.54141077895425 139.71315964015625 5.4030000000000005 35.5413605001386 139.71317857395672 5.4030000000000005 35.5413605001386 139.71317857395672 2.711</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54141077895425 139.71315964015625 2.711 35.54139516763394 139.71309713161 2.711 35.54139516763394 139.71309713161 5.4030000000000005 35.54141077895425 139.71315964015625 5.4030000000000005 35.54141077895425 139.71315964015625 2.711</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54139516763394 139.71309713161 2.711 35.54134475361942 139.7131160656475 2.711 35.54134475361942 139.7131160656475 5.4030000000000005 35.54139516763394 139.71309713161 5.4030000000000005 35.54139516763394 139.71309713161 2.711</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54134475361942 139.7131160656475 2.711 35.5413605001386 139.71317857395672 2.711 35.5413605001386 139.71317857395672 5.4030000000000005 35.54134475361942 139.7131160656475 5.4030000000000005 35.54134475361942 139.7131160656475 2.711</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.5413605001386 139.71317857395672 5.4030000000000005 35.54141077895425 139.71315964015625 5.4030000000000005 35.54139516763394 139.71309713161 5.4030000000000005 35.54134475361942 139.7131160656475 5.4030000000000005 35.5413605001386 139.71317857395672 5.4030000000000005</gml:posList>
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
									<xAL:LocalityName Type="Town">東京都大田区南六郷三丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">34.64370</uro:buildingRoofEdgeArea>
					<uro:districtsAndZonesType codeSpace="../../codelists/Common_districtsAndZonesType.xml">12</uro:districtsAndZonesType>
					<uro:prefecture codeSpace="../../codelists/Common_prefecture.xml">13</uro:prefecture>
					<uro:city codeSpace="../../codelists/Common_localPublicAuthorities.xml">13111</uro:city>
					<uro:surveyYear>2016</uro:surveyYear>
				</uro:BuildingDetails>
			</uro:buildingDetails>
			<uro:extendedAttribute>
				<uro:KeyValuePair>
					<uro:key codeSpace="../../codelists/extendedAttribute_key.xml">106</uro:key>
					<uro:codeValue codeSpace="../../codelists/extendedAttribute_key106.xml">20</uro:codeValue>
				</uro:KeyValuePair>
			</uro:extendedAttribute>
		</bldg:Building>
	</core:cityObjectMember>
	<core:cityObjectMember>
		<bldg:Building gml:id="BLD_7aea1c81-2553-454f-b894-d554fe4247d4">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-315</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="大字・町コード">
				<gen:value>58</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="町・丁目コード">
				<gen:value>3</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
				<gen:value>13111058003</gen:value>
			</gen:stringAttribute>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（計画規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L1</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">2.070</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">2.940</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">11.47</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">2.1</bldg:measuredHeight>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.540894653506456 139.7133814592693 4.9094999999999995 35.54113659761786 139.71330136292485 4.9094999999999995 35.54113062176147 139.7132745725591 4.9094999999999995 35.540888677667766 139.71335466897793 4.9094999999999995 35.540894653506456 139.7133814592693 4.9094999999999995</gml:posList>
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
											<gml:posList>35.540894653506456 139.7133814592693 2.785 35.540888677667766 139.71335466897793 2.785 35.54113062176147 139.7132745725591 2.785 35.54113659761786 139.71330136292485 2.785 35.540894653506456 139.7133814592693 2.785</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.540894653506456 139.7133814592693 2.785 35.54113659761786 139.71330136292485 2.785 35.54113659761786 139.71330136292485 4.909000000000001 35.540894653506456 139.7133814592693 4.909000000000001 35.540894653506456 139.7133814592693 2.785</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54113659761786 139.71330136292485 2.785 35.54113062176147 139.7132745725591 2.785 35.54113062176147 139.7132745725591 4.909000000000001 35.54113659761786 139.71330136292485 4.909000000000001 35.54113659761786 139.71330136292485 2.785</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54113062176147 139.7132745725591 2.785 35.540888677667766 139.71335466897793 2.785 35.540888677667766 139.71335466897793 4.909000000000001 35.54113062176147 139.7132745725591 4.909000000000001 35.54113062176147 139.7132745725591 2.785</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.540888677667766 139.71335466897793 2.785 35.540894653506456 139.7133814592693 2.785 35.540894653506456 139.7133814592693 4.909000000000001 35.540888677667766 139.71335466897793 4.909000000000001 35.540888677667766 139.71335466897793 2.785</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.540894653506456 139.7133814592693 4.909000000000001 35.54113659761786 139.71330136292485 4.909000000000001 35.54113062176147 139.7132745725591 4.909000000000001 35.540888677667766 139.71335466897793 4.909000000000001 35.540894653506456 139.7133814592693 4.909000000000001</gml:posList>
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
									<xAL:LocalityName Type="Town">東京都大田区南六郷三丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">70.01730</uro:buildingRoofEdgeArea>
					<uro:districtsAndZonesType codeSpace="../../codelists/Common_districtsAndZonesType.xml">12</uro:districtsAndZonesType>
					<uro:prefecture codeSpace="../../codelists/Common_prefecture.xml">13</uro:prefecture>
					<uro:city codeSpace="../../codelists/Common_localPublicAuthorities.xml">13111</uro:city>
					<uro:surveyYear>2016</uro:surveyYear>
				</uro:BuildingDetails>
			</uro:buildingDetails>
			<uro:extendedAttribute>
				<uro:KeyValuePair>
					<uro:key codeSpace="../../codelists/extendedAttribute_key.xml">106</uro:key>
					<uro:codeValue codeSpace="../../codelists/extendedAttribute_key106.xml">20</uro:codeValue>
				</uro:KeyValuePair>
			</uro:extendedAttribute>
		</bldg:Building>
	</core:cityObjectMember>
	<core:cityObjectMember>
		<bldg:Building gml:id="BLD_7cf80dbd-1de7-442f-a3a0-24b8dd981cd5">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-486</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="大字・町コード">
				<gen:value>58</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="町・丁目コード">
				<gen:value>3</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
				<gen:value>13111058003</gen:value>
			</gen:stringAttribute>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（計画規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L1</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">1.650</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">2.700</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">11.22</gen:value>
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
					<gen:value uom="m">0.350</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">9.8</bldg:measuredHeight>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.54131041252268 139.71475683139562 11.669 35.54152340205619 139.71465560657532 11.669 35.5413910271429 139.71423859184893 11.669 35.54130183126393 139.714281073262 11.669 35.54134957321918 139.71443154222655 11.669 35.541225779839095 139.71449028637193 11.669 35.54131041252268 139.71475683139562 11.669</gml:posList>
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
											<gml:posList>35.54131041252268 139.71475683139562 2.911 35.541225779839095 139.71449028637193 2.911 35.54134957321918 139.71443154222655 2.911 35.54130183126393 139.714281073262 2.911 35.5413910271429 139.71423859184893 2.911 35.54152340205619 139.71465560657532 2.911 35.54131041252268 139.71475683139562 2.911</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54131041252268 139.71475683139562 2.911 35.54152340205619 139.71465560657532 2.911 35.54152340205619 139.71465560657532 11.668999999999999 35.54131041252268 139.71475683139562 11.668999999999999 35.54131041252268 139.71475683139562 2.911</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54152340205619 139.71465560657532 2.911 35.5413910271429 139.71423859184893 2.911 35.5413910271429 139.71423859184893 11.668999999999999 35.54152340205619 139.71465560657532 11.668999999999999 35.54152340205619 139.71465560657532 2.911</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.5413910271429 139.71423859184893 2.911 35.54130183126393 139.714281073262 2.911 35.54130183126393 139.714281073262 11.668999999999999 35.5413910271429 139.71423859184893 11.668999999999999 35.5413910271429 139.71423859184893 2.911</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54130183126393 139.714281073262 2.911 35.54134957321918 139.71443154222655 2.911 35.54134957321918 139.71443154222655 11.668999999999999 35.54130183126393 139.714281073262 11.668999999999999 35.54130183126393 139.714281073262 2.911</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54134957321918 139.71443154222655 2.911 35.541225779839095 139.71449028637193 2.911 35.541225779839095 139.71449028637193 11.668999999999999 35.54134957321918 139.71443154222655 11.668999999999999 35.54134957321918 139.71443154222655 2.911</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541225779839095 139.71449028637193 2.911 35.54131041252268 139.71475683139562 2.911 35.54131041252268 139.71475683139562 11.668999999999999 35.541225779839095 139.71449028637193 11.668999999999999 35.541225779839095 139.71449028637193 2.911</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54131041252268 139.71475683139562 11.668999999999999 35.54152340205619 139.71465560657532 11.668999999999999 35.5413910271429 139.71423859184893 11.668999999999999 35.54130183126393 139.714281073262 11.668999999999999 35.54134957321918 139.71443154222655 11.668999999999999 35.541225779839095 139.71449028637193 11.668999999999999 35.54131041252268 139.71475683139562 11.668999999999999</gml:posList>
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
									<xAL:LocalityName Type="Town">東京都大田区南六郷三丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">812.61990</uro:buildingRoofEdgeArea>
					<uro:districtsAndZonesType codeSpace="../../codelists/Common_districtsAndZonesType.xml">12</uro:districtsAndZonesType>
					<uro:prefecture codeSpace="../../codelists/Common_prefecture.xml">13</uro:prefecture>
					<uro:city codeSpace="../../codelists/Common_localPublicAuthorities.xml">13111</uro:city>
					<uro:surveyYear>2016</uro:surveyYear>
				</uro:BuildingDetails>
			</uro:buildingDetails>
			<uro:extendedAttribute>
				<uro:KeyValuePair>
					<uro:key codeSpace="../../codelists/extendedAttribute_key.xml">106</uro:key>
					<uro:codeValue codeSpace="../../codelists/extendedAttribute_key106.xml">20</uro:codeValue>
				</uro:KeyValuePair>
			</uro:extendedAttribute>
		</bldg:Building>
	</core:cityObjectMember>
	<core:cityObjectMember>
		<bldg:Building gml:id="BLD_e61455af-37cd-4d75-ba2b-ab419880b805">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-466</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="大字・町コード">
				<gen:value>58</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="町・丁目コード">
				<gen:value>3</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
				<gen:value>13111058003</gen:value>
			</gen:stringAttribute>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（計画規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L1</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">1.580</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">2.840</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">11.78</gen:value>
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
					<gen:value uom="m">0.020</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">4.6</bldg:measuredHeight>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.541382218917 139.7135361971285 6.84 35.54148871794623 139.71349038054228 6.84 35.5414416376926 139.7133266766337 6.84 35.54133500351648 139.71337249361054 6.84 35.541382218917 139.7135361971285 6.84</gml:posList>
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
											<gml:posList>35.541382218917 139.7135361971285 2.671 35.54133500351648 139.71337249361054 2.671 35.5414416376926 139.7133266766337 2.671 35.54148871794623 139.71349038054228 2.671 35.541382218917 139.7135361971285 2.671</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541382218917 139.7135361971285 2.671 35.54148871794623 139.71349038054228 2.671 35.54148871794623 139.71349038054228 6.84 35.541382218917 139.7135361971285 6.84 35.541382218917 139.7135361971285 2.671</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54148871794623 139.71349038054228 2.671 35.5414416376926 139.7133266766337 2.671 35.5414416376926 139.7133266766337 6.84 35.54148871794623 139.71349038054228 6.84 35.54148871794623 139.71349038054228 2.671</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.5414416376926 139.7133266766337 2.671 35.54133500351648 139.71337249361054 2.671 35.54133500351648 139.71337249361054 6.84 35.5414416376926 139.7133266766337 6.84 35.5414416376926 139.7133266766337 2.671</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54133500351648 139.71337249361054 2.671 35.541382218917 139.7135361971285 2.671 35.541382218917 139.7135361971285 6.84 35.54133500351648 139.71337249361054 6.84 35.54133500351648 139.71337249361054 2.671</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541382218917 139.7135361971285 6.84 35.54148871794623 139.71349038054228 6.84 35.5414416376926 139.7133266766337 6.84 35.54133500351648 139.71337249361054 6.84 35.541382218917 139.7135361971285 6.84</gml:posList>
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
									<xAL:LocalityName Type="Town">東京都大田区南六郷三丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">197.21813</uro:buildingRoofEdgeArea>
					<uro:districtsAndZonesType codeSpace="../../codelists/Common_districtsAndZonesType.xml">12</uro:districtsAndZonesType>
					<uro:prefecture codeSpace="../../codelists/Common_prefecture.xml">13</uro:prefecture>
					<uro:city codeSpace="../../codelists/Common_localPublicAuthorities.xml">13111</uro:city>
					<uro:surveyYear>2016</uro:surveyYear>
				</uro:BuildingDetails>
			</uro:buildingDetails>
			<uro:extendedAttribute>
				<uro:KeyValuePair>
					<uro:key codeSpace="../../codelists/extendedAttribute_key.xml">106</uro:key>
					<uro:codeValue codeSpace="../../codelists/extendedAttribute_key106.xml">20</uro:codeValue>
				</uro:KeyValuePair>
			</uro:extendedAttribute>
		</bldg:Building>
	</core:cityObjectMember>
	<core:cityObjectMember>
		<bldg:Building gml:id="BLD_7e82aed0-1348-45d4-93bb-b1e26c910003">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-444</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="大字・町コード">
				<gen:value>58</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="町・丁目コード">
				<gen:value>3</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
				<gen:value>13111058003</gen:value>
			</gen:stringAttribute>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（計画規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L1</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">1.810</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">2.870</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">11.72</gen:value>
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
					<gen:value uom="m">0.010</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">4.5</bldg:measuredHeight>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.54133474471467 139.71379268113824 5.753500000000001 35.54142732424439 139.71375383356497 5.753500000000001 35.541313211888045 139.71334936614474 5.753500000000001 35.54122063281673 139.71338854498205 5.753500000000001 35.54123501526781 139.71343914447652 5.753500000000001 35.541193388442984 139.71345674176507 5.753500000000001 35.54127914248125 139.7137610014878 5.753500000000001 35.54132076935018 139.71374340433744 5.753500000000001 35.54133474471467 139.71379268113824 5.753500000000001</gml:posList>
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
											<gml:posList>35.54133474471467 139.71379268113824 2.819 35.54132076935018 139.71374340433744 2.819 35.54127914248125 139.7137610014878 2.819 35.541193388442984 139.71345674176507 2.819 35.54123501526781 139.71343914447652 2.819 35.54122063281673 139.71338854498205 2.819 35.541313211888045 139.71334936614474 2.819 35.54142732424439 139.71375383356497 2.819 35.54133474471467 139.71379268113824 2.819</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54133474471467 139.71379268113824 2.819 35.54142732424439 139.71375383356497 2.819 35.54142732424439 139.71375383356497 5.754 35.54133474471467 139.71379268113824 5.754 35.54133474471467 139.71379268113824 2.819</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54142732424439 139.71375383356497 2.819 35.541313211888045 139.71334936614474 2.819 35.541313211888045 139.71334936614474 5.754 35.54142732424439 139.71375383356497 5.754 35.54142732424439 139.71375383356497 2.819</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541313211888045 139.71334936614474 2.819 35.54122063281673 139.71338854498205 2.819 35.54122063281673 139.71338854498205 5.754 35.541313211888045 139.71334936614474 5.754 35.541313211888045 139.71334936614474 2.819</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54122063281673 139.71338854498205 2.819 35.54123501526781 139.71343914447652 2.819 35.54123501526781 139.71343914447652 5.754 35.54122063281673 139.71338854498205 5.754 35.54122063281673 139.71338854498205 2.819</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54123501526781 139.71343914447652 2.819 35.541193388442984 139.71345674176507 2.819 35.541193388442984 139.71345674176507 5.754 35.54123501526781 139.71343914447652 5.754 35.54123501526781 139.71343914447652 2.819</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541193388442984 139.71345674176507 2.819 35.54127914248125 139.7137610014878 2.819 35.54127914248125 139.7137610014878 5.754 35.541193388442984 139.71345674176507 5.754 35.541193388442984 139.71345674176507 2.819</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54127914248125 139.7137610014878 2.819 35.54132076935018 139.71374340433744 2.819 35.54132076935018 139.71374340433744 5.754 35.54127914248125 139.7137610014878 5.754 35.54127914248125 139.7137610014878 2.819</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54132076935018 139.71374340433744 2.819 35.54133474471467 139.71379268113824 2.819 35.54133474471467 139.71379268113824 5.754 35.54132076935018 139.71374340433744 5.754 35.54132076935018 139.71374340433744 2.819</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54133474471467 139.71379268113824 5.754 35.54142732424439 139.71375383356497 5.754 35.541313211888045 139.71334936614474 5.754 35.54122063281673 139.71338854498205 5.754 35.54123501526781 139.71343914447652 5.754 35.541193388442984 139.71345674176507 5.754 35.54127914248125 139.7137610014878 5.754 35.54132076935018 139.71374340433744 5.754 35.54133474471467 139.71379268113824 5.754</gml:posList>
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
									<xAL:LocalityName Type="Town">東京都大田区南六郷三丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">563.82863</uro:buildingRoofEdgeArea>
					<uro:districtsAndZonesType codeSpace="../../codelists/Common_districtsAndZonesType.xml">12</uro:districtsAndZonesType>
					<uro:prefecture codeSpace="../../codelists/Common_prefecture.xml">13</uro:prefecture>
					<uro:city codeSpace="../../codelists/Common_localPublicAuthorities.xml">13111</uro:city>
					<uro:surveyYear>2016</uro:surveyYear>
				</uro:BuildingDetails>
			</uro:buildingDetails>
			<uro:extendedAttribute>
				<uro:KeyValuePair>
					<uro:key codeSpace="../../codelists/extendedAttribute_key.xml">106</uro:key>
					<uro:codeValue codeSpace="../../codelists/extendedAttribute_key106.xml">20</uro:codeValue>
				</uro:KeyValuePair>
			</uro:extendedAttribute>
		</bldg:Building>
	</core:cityObjectMember>
	<core:cityObjectMember>
		<bldg:Building gml:id="BLD_07a37e39-0026-4d92-92a4-000ff290901c">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-578</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="大字・町コード">
				<gen:value>58</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="町・丁目コード">
				<gen:value>3</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
				<gen:value>13111058003</gen:value>
			</gen:stringAttribute>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（計画規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L1</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">1.530</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">2.800</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">10.93</gen:value>
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
					<gen:value uom="m">0.031</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">4.4</bldg:measuredHeight>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.541591032283854 139.7141334144839 5.731 35.5417997080234 139.71404575922477 5.731 35.541739734209926 139.71383211396727 5.731 35.54156538752602 139.71390549197884 5.731 35.54157963455137 139.71395609198288 5.731 35.54154544118005 139.71397070035476 5.731 35.54157312128553 139.71406925472792 5.731 35.54156447153297 139.71407290695987 5.731 35.54157831122697 139.71412185331073 5.731 35.54158669056377 139.71411820148353 5.731 35.541591032283854 139.7141334144839 5.731</gml:posList>
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
											<gml:posList>35.541591032283854 139.7141334144839 2.748 35.54158669056377 139.71411820148353 2.748 35.54157831122697 139.71412185331073 2.748 35.54156447153297 139.71407290695987 2.748 35.54157312128553 139.71406925472792 2.748 35.54154544118005 139.71397070035476 2.748 35.54157963455137 139.71395609198288 2.748 35.54156538752602 139.71390549197884 2.748 35.541739734209926 139.71383211396727 2.748 35.5417997080234 139.71404575922477 2.748 35.541591032283854 139.7141334144839 2.748</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541591032283854 139.7141334144839 2.748 35.5417997080234 139.71404575922477 2.748 35.5417997080234 139.71404575922477 5.731 35.541591032283854 139.7141334144839 5.731 35.541591032283854 139.7141334144839 2.748</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.5417997080234 139.71404575922477 2.748 35.541739734209926 139.71383211396727 2.748 35.541739734209926 139.71383211396727 5.731 35.5417997080234 139.71404575922477 5.731 35.5417997080234 139.71404575922477 2.748</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541739734209926 139.71383211396727 2.748 35.54156538752602 139.71390549197884 2.748 35.54156538752602 139.71390549197884 5.731 35.541739734209926 139.71383211396727 5.731 35.541739734209926 139.71383211396727 2.748</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54156538752602 139.71390549197884 2.748 35.54157963455137 139.71395609198288 2.748 35.54157963455137 139.71395609198288 5.731 35.54156538752602 139.71390549197884 5.731 35.54156538752602 139.71390549197884 2.748</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54157963455137 139.71395609198288 2.748 35.54154544118005 139.71397070035476 2.748 35.54154544118005 139.71397070035476 5.731 35.54157963455137 139.71395609198288 5.731 35.54157963455137 139.71395609198288 2.748</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54154544118005 139.71397070035476 2.748 35.54157312128553 139.71406925472792 2.748 35.54157312128553 139.71406925472792 5.731 35.54154544118005 139.71397070035476 5.731 35.54154544118005 139.71397070035476 2.748</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54157312128553 139.71406925472792 2.748 35.54156447153297 139.71407290695987 2.748 35.54156447153297 139.71407290695987 5.731 35.54157312128553 139.71406925472792 5.731 35.54157312128553 139.71406925472792 2.748</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54156447153297 139.71407290695987 2.748 35.54157831122697 139.71412185331073 2.748 35.54157831122697 139.71412185331073 5.731 35.54156447153297 139.71407290695987 5.731 35.54156447153297 139.71407290695987 2.748</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54157831122697 139.71412185331073 2.748 35.54158669056377 139.71411820148353 2.748 35.54158669056377 139.71411820148353 5.731 35.54157831122697 139.71412185331073 5.731 35.54157831122697 139.71412185331073 2.748</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54158669056377 139.71411820148353 2.748 35.541591032283854 139.7141334144839 2.748 35.541591032283854 139.7141334144839 5.731 35.54158669056377 139.71411820148353 5.731 35.54158669056377 139.71411820148353 2.748</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541591032283854 139.7141334144839 5.731 35.5417997080234 139.71404575922477 5.731 35.541739734209926 139.71383211396727 5.731 35.54156538752602 139.71390549197884 5.731 35.54157963455137 139.71395609198288 5.731 35.54154544118005 139.71397070035476 5.731 35.54157312128553 139.71406925472792 5.731 35.54156447153297 139.71407290695987 5.731 35.54157831122697 139.71412185331073 5.731 35.54158669056377 139.71411820148353 5.731 35.541591032283854 139.7141334144839 5.731</gml:posList>
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
									<xAL:LocalityName Type="Town">東京都大田区南六郷三丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">486.20587</uro:buildingRoofEdgeArea>
					<uro:districtsAndZonesType codeSpace="../../codelists/Common_districtsAndZonesType.xml">12</uro:districtsAndZonesType>
					<uro:prefecture codeSpace="../../codelists/Common_prefecture.xml">13</uro:prefecture>
					<uro:city codeSpace="../../codelists/Common_localPublicAuthorities.xml">13111</uro:city>
					<uro:surveyYear>2016</uro:surveyYear>
				</uro:BuildingDetails>
			</uro:buildingDetails>
			<uro:extendedAttribute>
				<uro:KeyValuePair>
					<uro:key codeSpace="../../codelists/extendedAttribute_key.xml">106</uro:key>
					<uro:codeValue codeSpace="../../codelists/extendedAttribute_key106.xml">20</uro:codeValue>
				</uro:KeyValuePair>
			</uro:extendedAttribute>
		</bldg:Building>
	</core:cityObjectMember>
	<core:cityObjectMember>
		<bldg:Building gml:id="BLD_579f02b4-7843-4398-905d-107892187346">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-374</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="大字・町コード">
				<gen:value>58</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="町・丁目コード">
				<gen:value>3</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
				<gen:value>13111058003</gen:value>
			</gen:stringAttribute>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（計画規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L1</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">2.200</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">2.970</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">11.47</gen:value>
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
					<gen:value uom="m">0.202</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">2.2</bldg:measuredHeight>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.54124994630491 139.71429941316995 5.195 35.541274819215964 139.71428834899584 5.195 35.5412680896011 139.71426570414434 5.195 35.541243217593504 139.71427676832317 5.195 35.54124994630491 139.71429941316995 5.195</gml:posList>
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
											<gml:posList>35.54124994630491 139.71429941316995 2.983 35.541243217593504 139.71427676832317 2.983 35.5412680896011 139.71426570414434 2.983 35.541274819215964 139.71428834899584 2.983 35.54124994630491 139.71429941316995 2.983</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54124994630491 139.71429941316995 2.983 35.541274819215964 139.71428834899584 2.983 35.541274819215964 139.71428834899584 5.195 35.54124994630491 139.71429941316995 5.195 35.54124994630491 139.71429941316995 2.983</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541274819215964 139.71428834899584 2.983 35.5412680896011 139.71426570414434 2.983 35.5412680896011 139.71426570414434 5.195 35.541274819215964 139.71428834899584 5.195 35.541274819215964 139.71428834899584 2.983</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.5412680896011 139.71426570414434 2.983 35.541243217593504 139.71427676832317 2.983 35.541243217593504 139.71427676832317 5.195 35.5412680896011 139.71426570414434 5.195 35.5412680896011 139.71426570414434 2.983</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541243217593504 139.71427676832317 2.983 35.54124994630491 139.71429941316995 2.983 35.54124994630491 139.71429941316995 5.195 35.541243217593504 139.71427676832317 5.195 35.541243217593504 139.71427676832317 2.983</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54124994630491 139.71429941316995 5.195 35.541274819215964 139.71428834899584 5.195 35.5412680896011 139.71426570414434 5.195 35.541243217593504 139.71427676832317 5.195 35.54124994630491 139.71429941316995 5.195</gml:posList>
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
									<xAL:LocalityName Type="Town">東京都大田区南六郷三丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">18.19215</uro:buildingRoofEdgeArea>
					<uro:districtsAndZonesType codeSpace="../../codelists/Common_districtsAndZonesType.xml">12</uro:districtsAndZonesType>
					<uro:prefecture codeSpace="../../codelists/Common_prefecture.xml">13</uro:prefecture>
					<uro:city codeSpace="../../codelists/Common_localPublicAuthorities.xml">13111</uro:city>
					<uro:surveyYear>2016</uro:surveyYear>
				</uro:BuildingDetails>
			</uro:buildingDetails>
			<uro:extendedAttribute>
				<uro:KeyValuePair>
					<uro:key codeSpace="../../codelists/extendedAttribute_key.xml">106</uro:key>
					<uro:codeValue codeSpace="../../codelists/extendedAttribute_key106.xml">20</uro:codeValue>
				</uro:KeyValuePair>
			</uro:extendedAttribute>
		</bldg:Building>
	</core:cityObjectMember>
	<core:cityObjectMember>
		<bldg:Building gml:id="BLD_f96ef42c-fad9-444f-9e44-77f7e07726f6">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-405</gen:value>
			</gen:stringAttribute>
			<bldg:measuredHeight uom="m">8.2</bldg:measuredHeight>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.541310888473944 139.71561897175823 9.757 35.54133788986974 139.71560624176954 9.757 35.541303003280085 139.71549548249433 9.757 35.541276002798384 139.71550821361615 9.757 35.541310888473944 139.71561897175823 9.757</gml:posList>
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
											<gml:posList>35.541310888473944 139.71561897175823 2.02 35.541276002798384 139.71550821361615 2.02 35.541303003280085 139.71549548249433 2.02 35.54133788986974 139.71560624176954 2.02 35.541310888473944 139.71561897175823 2.02</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541310888473944 139.71561897175823 2.02 35.54133788986974 139.71560624176954 2.02 35.54133788986974 139.71560624176954 9.757 35.541310888473944 139.71561897175823 9.757 35.541310888473944 139.71561897175823 2.02</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54133788986974 139.71560624176954 2.02 35.541303003280085 139.71549548249433 2.02 35.541303003280085 139.71549548249433 9.757 35.54133788986974 139.71560624176954 9.757 35.54133788986974 139.71560624176954 2.02</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541303003280085 139.71549548249433 2.02 35.541276002798384 139.71550821361615 2.02 35.541276002798384 139.71550821361615 9.757 35.541303003280085 139.71549548249433 9.757 35.541303003280085 139.71549548249433 2.02</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541276002798384 139.71550821361615 2.02 35.541310888473944 139.71561897175823 2.02 35.541310888473944 139.71561897175823 9.757 35.541276002798384 139.71550821361615 9.757 35.541276002798384 139.71550821361615 2.02</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541310888473944 139.71561897175823 9.757 35.54133788986974 139.71560624176954 9.757 35.541303003280085 139.71549548249433 9.757 35.541276002798384 139.71550821361615 9.757 35.541310888473944 139.71561897175823 9.757</gml:posList>
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
									<xAL:LocalityName Type="Town">東京都大田区南六郷三丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">0.00000</uro:buildingRoofEdgeArea>
					<uro:prefecture codeSpace="../../codelists/Common_prefecture.xml">13</uro:prefecture>
					<uro:city codeSpace="../../codelists/Common_localPublicAuthorities.xml">13111</uro:city>
					<uro:surveyYear>2016</uro:surveyYear>
				</uro:BuildingDetails>
			</uro:buildingDetails>
		</bldg:Building>
	</core:cityObjectMember>
	<core:cityObjectMember>
		<bldg:Building gml:id="BLD_37587d5d-7ec1-4012-815b-be282659326d">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-202</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="大字・町コード">
				<gen:value>58</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="町・丁目コード">
				<gen:value>3</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
				<gen:value>13111058003</gen:value>
			</gen:stringAttribute>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（計画規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L1</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">1.810</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">2.900</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">11.20</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">14.2</bldg:measuredHeight>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.54084723764115 139.71316875213742 6.181 35.54085142746041 139.71316709161786 6.181 35.54080593493194 139.71296897802156 6.181 35.54079971797653 139.71297163414053 6.181 35.54080136887468 139.713000085103 6.181 35.54078459210938 139.71298919198784 6.181 35.540776715006594 139.71295413325993 6.181 35.5407638748099 139.71295878439753 6.181 35.54077297374116 139.71299880410083 6.181 35.54080287366528 139.71301761810838 6.181 35.54080628358483 139.71304738986194 6.181 35.540811584768285 139.71307550452556 6.181 35.540818507459264 139.71310262421133 6.181 35.54082637165783 139.71312477968803 6.181 35.540837214069214 139.71315057011978 6.181 35.54084723764115 139.71316875213742 6.181</gml:posList>
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
											<gml:posList>35.54084723764115 139.71316875213742 2.884 35.540837214069214 139.71315057011978 2.884 35.54082637165783 139.71312477968803 2.884 35.540818507459264 139.71310262421133 2.884 35.540811584768285 139.71307550452556 2.884 35.54080628358483 139.71304738986194 2.884 35.54080287366528 139.71301761810838 2.884 35.54077297374116 139.71299880410083 2.884 35.5407638748099 139.71295878439753 2.884 35.540776715006594 139.71295413325993 2.884 35.54078459210938 139.71298919198784 2.884 35.54080136887468 139.713000085103 2.884 35.54079971797653 139.71297163414053 2.884 35.54080593493194 139.71296897802156 2.884 35.54085142746041 139.71316709161786 2.884 35.54084723764115 139.71316875213742 2.884</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54084723764115 139.71316875213742 2.884 35.54085142746041 139.71316709161786 2.884 35.54085142746041 139.71316709161786 6.181 35.54084723764115 139.71316875213742 6.181 35.54084723764115 139.71316875213742 2.884</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54085142746041 139.71316709161786 2.884 35.54080593493194 139.71296897802156 2.884 35.54080593493194 139.71296897802156 6.181 35.54085142746041 139.71316709161786 6.181 35.54085142746041 139.71316709161786 2.884</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54080593493194 139.71296897802156 2.884 35.54079971797653 139.71297163414053 2.884 35.54079971797653 139.71297163414053 6.181 35.54080593493194 139.71296897802156 6.181 35.54080593493194 139.71296897802156 2.884</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54079971797653 139.71297163414053 2.884 35.54080136887468 139.713000085103 2.884 35.54080136887468 139.713000085103 6.181 35.54079971797653 139.71297163414053 6.181 35.54079971797653 139.71297163414053 2.884</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54080136887468 139.713000085103 2.884 35.54078459210938 139.71298919198784 2.884 35.54078459210938 139.71298919198784 6.181 35.54080136887468 139.713000085103 6.181 35.54080136887468 139.713000085103 2.884</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54078459210938 139.71298919198784 2.884 35.540776715006594 139.71295413325993 2.884 35.540776715006594 139.71295413325993 6.181 35.54078459210938 139.71298919198784 6.181 35.54078459210938 139.71298919198784 2.884</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.540776715006594 139.71295413325993 2.884 35.5407638748099 139.71295878439753 2.884 35.5407638748099 139.71295878439753 6.181 35.540776715006594 139.71295413325993 6.181 35.540776715006594 139.71295413325993 2.884</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.5407638748099 139.71295878439753 2.884 35.54077297374116 139.71299880410083 2.884 35.54077297374116 139.71299880410083 6.181 35.5407638748099 139.71295878439753 6.181 35.5407638748099 139.71295878439753 2.884</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54077297374116 139.71299880410083 2.884 35.54080287366528 139.71301761810838 2.884 35.54080287366528 139.71301761810838 6.181 35.54077297374116 139.71299880410083 6.181 35.54077297374116 139.71299880410083 2.884</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54080287366528 139.71301761810838 2.884 35.54080628358483 139.71304738986194 2.884 35.54080628358483 139.71304738986194 6.181 35.54080287366528 139.71301761810838 6.181 35.54080287366528 139.71301761810838 2.884</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54080628358483 139.71304738986194 2.884 35.540811584768285 139.71307550452556 2.884 35.540811584768285 139.71307550452556 6.181 35.54080628358483 139.71304738986194 6.181 35.54080628358483 139.71304738986194 2.884</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.540811584768285 139.71307550452556 2.884 35.540818507459264 139.71310262421133 2.884 35.540818507459264 139.71310262421133 6.181 35.540811584768285 139.71307550452556 6.181 35.540811584768285 139.71307550452556 2.884</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.540818507459264 139.71310262421133 2.884 35.54082637165783 139.71312477968803 2.884 35.54082637165783 139.71312477968803 6.181 35.540818507459264 139.71310262421133 6.181 35.540818507459264 139.71310262421133 2.884</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54082637165783 139.71312477968803 2.884 35.540837214069214 139.71315057011978 2.884 35.540837214069214 139.71315057011978 6.181 35.54082637165783 139.71312477968803 6.181 35.54082637165783 139.71312477968803 2.884</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.540837214069214 139.71315057011978 2.884 35.54084723764115 139.71316875213742 2.884 35.54084723764115 139.71316875213742 6.181 35.540837214069214 139.71315057011978 6.181 35.540837214069214 139.71315057011978 2.884</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54084723764115 139.71316875213742 6.181 35.54085142746041 139.71316709161786 6.181 35.54080593493194 139.71296897802156 6.181 35.54079971797653 139.71297163414053 6.181 35.54080136887468 139.713000085103 6.181 35.54078459210938 139.71298919198784 6.181 35.540776715006594 139.71295413325993 6.181 35.5407638748099 139.71295878439753 6.181 35.54077297374116 139.71299880410083 6.181 35.54080287366528 139.71301761810838 6.181 35.54080628358483 139.71304738986194 6.181 35.540811584768285 139.71307550452556 6.181 35.540818507459264 139.71310262421133 6.181 35.54082637165783 139.71312477968803 6.181 35.540837214069214 139.71315057011978 6.181 35.54084723764115 139.71316875213742 6.181</gml:posList>
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
									<xAL:LocalityName Type="Town">東京都大田区南六郷三丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">37.57162</uro:buildingRoofEdgeArea>
					<uro:districtsAndZonesType codeSpace="../../codelists/Common_districtsAndZonesType.xml">12</uro:districtsAndZonesType>
					<uro:prefecture codeSpace="../../codelists/Common_prefecture.xml">13</uro:prefecture>
					<uro:city codeSpace="../../codelists/Common_localPublicAuthorities.xml">13111</uro:city>
					<uro:surveyYear>2016</uro:surveyYear>
				</uro:BuildingDetails>
			</uro:buildingDetails>
			<uro:extendedAttribute>
				<uro:KeyValuePair>
					<uro:key codeSpace="../../codelists/extendedAttribute_key.xml">106</uro:key>
					<uro:codeValue codeSpace="../../codelists/extendedAttribute_key106.xml">20</uro:codeValue>
				</uro:KeyValuePair>
			</uro:extendedAttribute>
		</bldg:Building>
	</core:cityObjectMember>
	<core:cityObjectMember>
		<bldg:Building gml:id="BLD_6822dfa2-d703-4d28-80a4-19c688fedfaa">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-353</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="大字・町コード">
				<gen:value>58</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="町・丁目コード">
				<gen:value>3</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
				<gen:value>13111058003</gen:value>
			</gen:stringAttribute>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（計画規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L1</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">2.100</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">2.870</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">11.47</gen:value>
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
					<gen:value uom="m">0.109</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">2.2</bldg:measuredHeight>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.54120170467339 139.71420810216776 4.992 35.54122603209489 139.7141978096723 4.992 35.54119482260233 139.7140850341186 4.992 35.541170495190364 139.71409532664407 4.992 35.54120170467339 139.71420810216776 4.992</gml:posList>
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
											<gml:posList>35.54120170467339 139.71420810216776 2.86 35.541170495190364 139.71409532664407 2.86 35.54119482260233 139.7140850341186 2.86 35.54122603209489 139.7141978096723 2.86 35.54120170467339 139.71420810216776 2.86</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54120170467339 139.71420810216776 2.86 35.54122603209489 139.7141978096723 2.86 35.54122603209489 139.7141978096723 4.992 35.54120170467339 139.71420810216776 4.992 35.54120170467339 139.71420810216776 2.86</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54122603209489 139.7141978096723 2.86 35.54119482260233 139.7140850341186 2.86 35.54119482260233 139.7140850341186 4.992 35.54122603209489 139.7141978096723 4.992 35.54122603209489 139.7141978096723 2.86</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54119482260233 139.7140850341186 2.86 35.541170495190364 139.71409532664407 2.86 35.541170495190364 139.71409532664407 4.992 35.54119482260233 139.7140850341186 4.992 35.54119482260233 139.7140850341186 2.86</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541170495190364 139.71409532664407 2.86 35.54120170467339 139.71420810216776 2.86 35.54120170467339 139.71420810216776 4.992 35.541170495190364 139.71409532664407 4.992 35.541170495190364 139.71409532664407 2.86</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54120170467339 139.71420810216776 4.992 35.54122603209489 139.7141978096723 4.992 35.54119482260233 139.7140850341186 4.992 35.541170495190364 139.71409532664407 4.992 35.54120170467339 139.71420810216776 4.992</gml:posList>
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
									<xAL:LocalityName Type="Town">東京都大田区南六郷三丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">30.82950</uro:buildingRoofEdgeArea>
					<uro:districtsAndZonesType codeSpace="../../codelists/Common_districtsAndZonesType.xml">12</uro:districtsAndZonesType>
					<uro:prefecture codeSpace="../../codelists/Common_prefecture.xml">13</uro:prefecture>
					<uro:city codeSpace="../../codelists/Common_localPublicAuthorities.xml">13111</uro:city>
					<uro:surveyYear>2016</uro:surveyYear>
				</uro:BuildingDetails>
			</uro:buildingDetails>
			<uro:extendedAttribute>
				<uro:KeyValuePair>
					<uro:key codeSpace="../../codelists/extendedAttribute_key.xml">106</uro:key>
					<uro:codeValue codeSpace="../../codelists/extendedAttribute_key106.xml">20</uro:codeValue>
				</uro:KeyValuePair>
			</uro:extendedAttribute>
		</bldg:Building>
	</core:cityObjectMember>
	<core:cityObjectMember>
		<bldg:Building gml:id="BLD_566a157b-cc25-4ab1-bfaf-bd5a874bce62">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-519</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="大字・町コード">
				<gen:value>58</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="町・丁目コード">
				<gen:value>3</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
				<gen:value>13111058003</gen:value>
			</gen:stringAttribute>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（計画規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L1</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">1.330</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">2.600</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">10.93</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">2.4</bldg:measuredHeight>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.54151204355187 139.71383311343172 5.113 35.5415996233245 139.71379725095164 5.113 35.541591617152896 139.71376814740879 5.113 35.54150403738886 139.7138040099169 5.113 35.54151204355187 139.71383311343172 5.113</gml:posList>
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
											<gml:posList>35.54151204355187 139.71383311343172 2.824 35.54150403738886 139.7138040099169 2.824 35.541591617152896 139.71376814740879 2.824 35.5415996233245 139.71379725095164 2.824 35.54151204355187 139.71383311343172 2.824</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54151204355187 139.71383311343172 2.824 35.5415996233245 139.71379725095164 2.824 35.5415996233245 139.71379725095164 5.1129999999999995 35.54151204355187 139.71383311343172 5.1129999999999995 35.54151204355187 139.71383311343172 2.824</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.5415996233245 139.71379725095164 2.824 35.541591617152896 139.71376814740879 2.824 35.541591617152896 139.71376814740879 5.1129999999999995 35.5415996233245 139.71379725095164 5.1129999999999995 35.5415996233245 139.71379725095164 2.824</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541591617152896 139.71376814740879 2.824 35.54150403738886 139.7138040099169 2.824 35.54150403738886 139.7138040099169 5.1129999999999995 35.541591617152896 139.71376814740879 5.1129999999999995 35.541591617152896 139.71376814740879 2.824</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54150403738886 139.7138040099169 2.824 35.54151204355187 139.71383311343172 2.824 35.54151204355187 139.71383311343172 5.1129999999999995 35.54150403738886 139.7138040099169 5.1129999999999995 35.54150403738886 139.7138040099169 2.824</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54151204355187 139.71383311343172 5.1129999999999995 35.5415996233245 139.71379725095164 5.1129999999999995 35.541591617152896 139.71376814740879 5.1129999999999995 35.54150403738886 139.7138040099169 5.1129999999999995 35.54151204355187 139.71383311343172 5.1129999999999995</gml:posList>
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
									<xAL:LocalityName Type="Town">東京都大田区南六郷三丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">28.52820</uro:buildingRoofEdgeArea>
					<uro:districtsAndZonesType codeSpace="../../codelists/Common_districtsAndZonesType.xml">12</uro:districtsAndZonesType>
					<uro:prefecture codeSpace="../../codelists/Common_prefecture.xml">13</uro:prefecture>
					<uro:city codeSpace="../../codelists/Common_localPublicAuthorities.xml">13111</uro:city>
					<uro:surveyYear>2016</uro:surveyYear>
				</uro:BuildingDetails>
			</uro:buildingDetails>
			<uro:extendedAttribute>
				<uro:KeyValuePair>
					<uro:key codeSpace="../../codelists/extendedAttribute_key.xml">106</uro:key>
					<uro:codeValue codeSpace="../../codelists/extendedAttribute_key106.xml">20</uro:codeValue>
				</uro:KeyValuePair>
			</uro:extendedAttribute>
		</bldg:Building>
	</core:cityObjectMember>
	<core:cityObjectMember>
		<bldg:Building gml:id="BLD_b1d5558f-2919-42dc-a708-b90cde13c75f">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-473</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="大字・町コード">
				<gen:value>58</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="町・丁目コード">
				<gen:value>3</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
				<gen:value>13111058003</gen:value>
			</gen:stringAttribute>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（計画規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L1</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">2.070</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">2.940</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">11.47</gen:value>
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
					<gen:value uom="m">0.096</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">42.7</bldg:measuredHeight>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.54150107210343 139.71408656594016 40.492 35.54150304722172 139.71403296422943 40.492 35.54149412279802 139.71403231573208 40.492 35.54149530792287 139.71400022087923 40.492 35.54125759708146 139.71398634619936 40.492 35.541252848734416 139.71397014130528 40.492 35.541235347206424 139.71390995151518 40.492 35.541194781810944 139.71377072150702 40.492 35.54121721660215 139.7137607625451 40.492 35.54121043320529 139.71373761272775 40.492 35.54119137699125 139.71374591240297 40.492 35.541184593263374 139.7137224317394 40.492 35.541113365653175 139.7134777047117 40.492 35.54107659876772 139.71335170367303 40.492 35.54096496481712 139.71340050554153 40.492 35.54114595043809 139.7140225748187 40.492 35.541233122682655 139.7139847281788 40.492 35.54124207689941 139.71401548448782 40.492 35.541299273749615 139.7140190391891 40.492 35.541295449224876 139.71411664733156 40.492 35.541479614492594 139.7141276239222 40.492 35.54148119545439 139.71408560279195 40.492 35.54150107210343 139.71408656594016 40.492</gml:posList>
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
											<gml:posList>35.54150107210343 139.71408656594016 2.811 35.54148119545439 139.71408560279195 2.811 35.541479614492594 139.7141276239222 2.811 35.541295449224876 139.71411664733156 2.811 35.541299273749615 139.7140190391891 2.811 35.54124207689941 139.71401548448782 2.811 35.541233122682655 139.7139847281788 2.811 35.54114595043809 139.7140225748187 2.811 35.54096496481712 139.71340050554153 2.811 35.54107659876772 139.71335170367303 2.811 35.541113365653175 139.7134777047117 2.811 35.541184593263374 139.7137224317394 2.811 35.54119137699125 139.71374591240297 2.811 35.54121043320529 139.71373761272775 2.811 35.54121721660215 139.7137607625451 2.811 35.541194781810944 139.71377072150702 2.811 35.541235347206424 139.71390995151518 2.811 35.541252848734416 139.71397014130528 2.811 35.54125759708146 139.71398634619936 2.811 35.54149530792287 139.71400022087923 2.811 35.54149412279802 139.71403231573208 2.811 35.54150304722172 139.71403296422943 2.811 35.54150107210343 139.71408656594016 2.811</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54150107210343 139.71408656594016 2.811 35.54150304722172 139.71403296422943 2.811 35.54150304722172 139.71403296422943 40.492 35.54150107210343 139.71408656594016 40.492 35.54150107210343 139.71408656594016 2.811</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54150304722172 139.71403296422943 2.811 35.54149412279802 139.71403231573208 2.811 35.54149412279802 139.71403231573208 40.492 35.54150304722172 139.71403296422943 40.492 35.54150304722172 139.71403296422943 2.811</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54149412279802 139.71403231573208 2.811 35.54149530792287 139.71400022087923 2.811 35.54149530792287 139.71400022087923 40.492 35.54149412279802 139.71403231573208 40.492 35.54149412279802 139.71403231573208 2.811</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54149530792287 139.71400022087923 2.811 35.54125759708146 139.71398634619936 2.811 35.54125759708146 139.71398634619936 40.492 35.54149530792287 139.71400022087923 40.492 35.54149530792287 139.71400022087923 2.811</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54125759708146 139.71398634619936 2.811 35.541252848734416 139.71397014130528 2.811 35.541252848734416 139.71397014130528 40.492 35.54125759708146 139.71398634619936 40.492 35.54125759708146 139.71398634619936 2.811</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541252848734416 139.71397014130528 2.811 35.541235347206424 139.71390995151518 2.811 35.541235347206424 139.71390995151518 40.492 35.541252848734416 139.71397014130528 40.492 35.541252848734416 139.71397014130528 2.811</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541235347206424 139.71390995151518 2.811 35.541194781810944 139.71377072150702 2.811 35.541194781810944 139.71377072150702 40.492 35.541235347206424 139.71390995151518 40.492 35.541235347206424 139.71390995151518 2.811</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541194781810944 139.71377072150702 2.811 35.54121721660215 139.7137607625451 2.811 35.54121721660215 139.7137607625451 40.492 35.541194781810944 139.71377072150702 40.492 35.541194781810944 139.71377072150702 2.811</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54121721660215 139.7137607625451 2.811 35.54121043320529 139.71373761272775 2.811 35.54121043320529 139.71373761272775 40.492 35.54121721660215 139.7137607625451 40.492 35.54121721660215 139.7137607625451 2.811</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54121043320529 139.71373761272775 2.811 35.54119137699125 139.71374591240297 2.811 35.54119137699125 139.71374591240297 40.492 35.54121043320529 139.71373761272775 40.492 35.54121043320529 139.71373761272775 2.811</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54119137699125 139.71374591240297 2.811 35.541184593263374 139.7137224317394 2.811 35.541184593263374 139.7137224317394 40.492 35.54119137699125 139.71374591240297 40.492 35.54119137699125 139.71374591240297 2.811</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541184593263374 139.7137224317394 2.811 35.541113365653175 139.7134777047117 2.811 35.541113365653175 139.7134777047117 40.492 35.541184593263374 139.7137224317394 40.492 35.541184593263374 139.7137224317394 2.811</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541113365653175 139.7134777047117 2.811 35.54107659876772 139.71335170367303 2.811 35.54107659876772 139.71335170367303 40.492 35.541113365653175 139.7134777047117 40.492 35.541113365653175 139.7134777047117 2.811</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54107659876772 139.71335170367303 2.811 35.54096496481712 139.71340050554153 2.811 35.54096496481712 139.71340050554153 40.492 35.54107659876772 139.71335170367303 40.492 35.54107659876772 139.71335170367303 2.811</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54096496481712 139.71340050554153 2.811 35.54114595043809 139.7140225748187 2.811 35.54114595043809 139.7140225748187 40.492 35.54096496481712 139.71340050554153 40.492 35.54096496481712 139.71340050554153 2.811</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54114595043809 139.7140225748187 2.811 35.541233122682655 139.7139847281788 2.811 35.541233122682655 139.7139847281788 40.492 35.54114595043809 139.7140225748187 40.492 35.54114595043809 139.7140225748187 2.811</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541233122682655 139.7139847281788 2.811 35.54124207689941 139.71401548448782 2.811 35.54124207689941 139.71401548448782 40.492 35.541233122682655 139.7139847281788 40.492 35.541233122682655 139.7139847281788 2.811</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54124207689941 139.71401548448782 2.811 35.541299273749615 139.7140190391891 2.811 35.541299273749615 139.7140190391891 40.492 35.54124207689941 139.71401548448782 40.492 35.54124207689941 139.71401548448782 2.811</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541299273749615 139.7140190391891 2.811 35.541295449224876 139.71411664733156 2.811 35.541295449224876 139.71411664733156 40.492 35.541299273749615 139.7140190391891 40.492 35.541299273749615 139.7140190391891 2.811</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541295449224876 139.71411664733156 2.811 35.541479614492594 139.7141276239222 2.811 35.541479614492594 139.7141276239222 40.492 35.541295449224876 139.71411664733156 40.492 35.541295449224876 139.71411664733156 2.811</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541479614492594 139.7141276239222 2.811 35.54148119545439 139.71408560279195 2.811 35.54148119545439 139.71408560279195 40.492 35.541479614492594 139.7141276239222 40.492 35.541479614492594 139.7141276239222 2.811</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54148119545439 139.71408560279195 2.811 35.54150107210343 139.71408656594016 2.811 35.54150107210343 139.71408656594016 40.492 35.54148119545439 139.71408560279195 40.492 35.54148119545439 139.71408560279195 2.811</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54150107210343 139.71408656594016 40.492 35.54150304722172 139.71403296422943 40.492 35.54149412279802 139.71403231573208 40.492 35.54149530792287 139.71400022087923 40.492 35.54125759708146 139.71398634619936 40.492 35.541252848734416 139.71397014130528 40.492 35.541235347206424 139.71390995151518 40.492 35.541194781810944 139.71377072150702 40.492 35.54121721660215 139.7137607625451 40.492 35.54121043320529 139.71373761272775 40.492 35.54119137699125 139.71374591240297 40.492 35.541184593263374 139.7137224317394 40.492 35.541113365653175 139.7134777047117 40.492 35.54107659876772 139.71335170367303 40.492 35.54096496481712 139.71340050554153 40.492 35.54114595043809 139.7140225748187 40.492 35.541233122682655 139.7139847281788 40.492 35.54124207689941 139.71401548448782 40.492 35.541299273749615 139.7140190391891 40.492 35.541295449224876 139.71411664733156 40.492 35.541479614492594 139.7141276239222 40.492 35.54148119545439 139.71408560279195 40.492 35.54150107210343 139.71408656594016 40.492</gml:posList>
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
									<xAL:LocalityName Type="Town">東京都大田区南六郷三丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">1057.32180</uro:buildingRoofEdgeArea>
					<uro:districtsAndZonesType codeSpace="../../codelists/Common_districtsAndZonesType.xml">12</uro:districtsAndZonesType>
					<uro:prefecture codeSpace="../../codelists/Common_prefecture.xml">13</uro:prefecture>
					<uro:city codeSpace="../../codelists/Common_localPublicAuthorities.xml">13111</uro:city>
					<uro:surveyYear>2016</uro:surveyYear>
				</uro:BuildingDetails>
			</uro:buildingDetails>
			<uro:extendedAttribute>
				<uro:KeyValuePair>
					<uro:key codeSpace="../../codelists/extendedAttribute_key.xml">106</uro:key>
					<uro:codeValue codeSpace="../../codelists/extendedAttribute_key106.xml">20</uro:codeValue>
				</uro:KeyValuePair>
			</uro:extendedAttribute>
		</bldg:Building>
	</core:cityObjectMember>
	<core:cityObjectMember>
		<bldg:Building gml:id="BLD_44140760-3271-4cd7-8280-70b89ee0daab">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-366</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="大字・町コード">
				<gen:value>58</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="町・丁目コード">
				<gen:value>3</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
				<gen:value>13111058003</gen:value>
			</gen:stringAttribute>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（計画規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L1</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">1.790</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>3</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">3.000</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">12.00</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">33.0</bldg:measuredHeight>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.541068157663524 139.71315750440556 17.097 35.54125670905719 139.71309237542178 17.097 35.5412280556082 139.71296768563553 17.097 35.54121108119853 139.7128942610742 17.097 35.54118378558464 139.71277552479435 17.097 35.54104578486628 139.7128230435479 17.097 35.54103831598691 139.71279063095028 17.097 35.54094208017937 139.71282352953085 17.097 35.54093420402065 139.71278946331242 17.097 35.540812017080775 139.7128313337254 17.097 35.54082668340064 139.71289516659633 17.097 35.54079167658971 139.71290746052026 17.097 35.54080593493194 139.71296897802156 17.097 35.54085142746041 139.71316709161786 17.097 35.54088008037469 139.7132914500394 17.097 35.541038490867244 139.713236953812 17.097 35.54103061482526 139.71320288751207 17.097 35.54107494809391 139.7131876020765 17.097 35.541068157663524 139.71315750440556 17.097</gml:posList>
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
											<gml:posList>35.541068157663524 139.71315750440556 2.571 35.54107494809391 139.7131876020765 2.571 35.54103061482526 139.71320288751207 2.571 35.541038490867244 139.713236953812 2.571 35.54088008037469 139.7132914500394 2.571 35.54085142746041 139.71316709161786 2.571 35.54080593493194 139.71296897802156 2.571 35.54079167658971 139.71290746052026 2.571 35.54082668340064 139.71289516659633 2.571 35.540812017080775 139.7128313337254 2.571 35.54093420402065 139.71278946331242 2.571 35.54094208017937 139.71282352953085 2.571 35.54103831598691 139.71279063095028 2.571 35.54104578486628 139.7128230435479 2.571 35.54118378558464 139.71277552479435 2.571 35.54121108119853 139.7128942610742 2.571 35.5412280556082 139.71296768563553 2.571 35.54125670905719 139.71309237542178 2.571 35.541068157663524 139.71315750440556 2.571</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541068157663524 139.71315750440556 2.571 35.54125670905719 139.71309237542178 2.571 35.54125670905719 139.71309237542178 17.097 35.541068157663524 139.71315750440556 17.097 35.541068157663524 139.71315750440556 2.571</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54125670905719 139.71309237542178 2.571 35.5412280556082 139.71296768563553 2.571 35.5412280556082 139.71296768563553 17.097 35.54125670905719 139.71309237542178 17.097 35.54125670905719 139.71309237542178 2.571</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.5412280556082 139.71296768563553 2.571 35.54121108119853 139.7128942610742 2.571 35.54121108119853 139.7128942610742 17.097 35.5412280556082 139.71296768563553 17.097 35.5412280556082 139.71296768563553 2.571</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54121108119853 139.7128942610742 2.571 35.54118378558464 139.71277552479435 2.571 35.54118378558464 139.71277552479435 17.097 35.54121108119853 139.7128942610742 17.097 35.54121108119853 139.7128942610742 2.571</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54118378558464 139.71277552479435 2.571 35.54104578486628 139.7128230435479 2.571 35.54104578486628 139.7128230435479 17.097 35.54118378558464 139.71277552479435 17.097 35.54118378558464 139.71277552479435 2.571</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54104578486628 139.7128230435479 2.571 35.54103831598691 139.71279063095028 2.571 35.54103831598691 139.71279063095028 17.097 35.54104578486628 139.7128230435479 17.097 35.54104578486628 139.7128230435479 2.571</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54103831598691 139.71279063095028 2.571 35.54094208017937 139.71282352953085 2.571 35.54094208017937 139.71282352953085 17.097 35.54103831598691 139.71279063095028 17.097 35.54103831598691 139.71279063095028 2.571</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54094208017937 139.71282352953085 2.571 35.54093420402065 139.71278946331242 2.571 35.54093420402065 139.71278946331242 17.097 35.54094208017937 139.71282352953085 17.097 35.54094208017937 139.71282352953085 2.571</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54093420402065 139.71278946331242 2.571 35.540812017080775 139.7128313337254 2.571 35.540812017080775 139.7128313337254 17.097 35.54093420402065 139.71278946331242 17.097 35.54093420402065 139.71278946331242 2.571</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.540812017080775 139.7128313337254 2.571 35.54082668340064 139.71289516659633 2.571 35.54082668340064 139.71289516659633 17.097 35.540812017080775 139.7128313337254 17.097 35.540812017080775 139.7128313337254 2.571</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54082668340064 139.71289516659633 2.571 35.54079167658971 139.71290746052026 2.571 35.54079167658971 139.71290746052026 17.097 35.54082668340064 139.71289516659633 17.097 35.54082668340064 139.71289516659633 2.571</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54079167658971 139.71290746052026 2.571 35.54080593493194 139.71296897802156 2.571 35.54080593493194 139.71296897802156 17.097 35.54079167658971 139.71290746052026 17.097 35.54079167658971 139.71290746052026 2.571</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54080593493194 139.71296897802156 2.571 35.54085142746041 139.71316709161786 2.571 35.54085142746041 139.71316709161786 17.097 35.54080593493194 139.71296897802156 17.097 35.54080593493194 139.71296897802156 2.571</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54085142746041 139.71316709161786 2.571 35.54088008037469 139.7132914500394 2.571 35.54088008037469 139.7132914500394 17.097 35.54085142746041 139.71316709161786 17.097 35.54085142746041 139.71316709161786 2.571</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54088008037469 139.7132914500394 2.571 35.541038490867244 139.713236953812 2.571 35.541038490867244 139.713236953812 17.097 35.54088008037469 139.7132914500394 17.097 35.54088008037469 139.7132914500394 2.571</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541038490867244 139.713236953812 2.571 35.54103061482526 139.71320288751207 2.571 35.54103061482526 139.71320288751207 17.097 35.541038490867244 139.713236953812 17.097 35.541038490867244 139.713236953812 2.571</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54103061482526 139.71320288751207 2.571 35.54107494809391 139.7131876020765 2.571 35.54107494809391 139.7131876020765 17.097 35.54103061482526 139.71320288751207 17.097 35.54103061482526 139.71320288751207 2.571</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54107494809391 139.7131876020765 2.571 35.541068157663524 139.71315750440556 2.571 35.541068157663524 139.71315750440556 17.097 35.54107494809391 139.7131876020765 17.097 35.54107494809391 139.7131876020765 2.571</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541068157663524 139.71315750440556 17.097 35.54125670905719 139.71309237542178 17.097 35.5412280556082 139.71296768563553 17.097 35.54121108119853 139.7128942610742 17.097 35.54118378558464 139.71277552479435 17.097 35.54104578486628 139.7128230435479 17.097 35.54103831598691 139.71279063095028 17.097 35.54094208017937 139.71282352953085 17.097 35.54093420402065 139.71278946331242 17.097 35.540812017080775 139.7128313337254 17.097 35.54082668340064 139.71289516659633 17.097 35.54079167658971 139.71290746052026 17.097 35.54080593493194 139.71296897802156 17.097 35.54085142746041 139.71316709161786 17.097 35.54088008037469 139.7132914500394 17.097 35.541038490867244 139.713236953812 17.097 35.54103061482526 139.71320288751207 17.097 35.54107494809391 139.7131876020765 17.097 35.541068157663524 139.71315750440556 17.097</gml:posList>
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
									<xAL:LocalityName Type="Town">東京都大田区南六郷三丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">1595.52428</uro:buildingRoofEdgeArea>
					<uro:districtsAndZonesType codeSpace="../../codelists/Common_districtsAndZonesType.xml">12</uro:districtsAndZonesType>
					<uro:prefecture codeSpace="../../codelists/Common_prefecture.xml">13</uro:prefecture>
					<uro:city codeSpace="../../codelists/Common_localPublicAuthorities.xml">13111</uro:city>
					<uro:surveyYear>2016</uro:surveyYear>
				</uro:BuildingDetails>
			</uro:buildingDetails>
			<uro:extendedAttribute>
				<uro:KeyValuePair>
					<uro:key codeSpace="../../codelists/extendedAttribute_key.xml">106</uro:key>
					<uro:codeValue codeSpace="../../codelists/extendedAttribute_key106.xml">20</uro:codeValue>
				</uro:KeyValuePair>
			</uro:extendedAttribute>
		</bldg:Building>
	</core:cityObjectMember>
	<core:cityObjectMember>
		<bldg:Building gml:id="BLD_d152487b-b229-4fe9-a421-02bf68448e0e">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-386</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="大字・町コード">
				<gen:value>58</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="町・丁目コード">
				<gen:value>3</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
				<gen:value>13111058003</gen:value>
			</gen:stringAttribute>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（計画規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L1</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">1.970</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">2.860</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">10.65</gen:value>
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
					<gen:value uom="m">0.010</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">7.3</bldg:measuredHeight>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.541252848734416 139.71397014130528 6.58 35.54129367060523 139.7139588317228 6.58 35.54129242555891 139.713930379965 6.58 35.541287405476595 139.7139128520468 6.58 35.54127846401389 139.71389499908867 6.58 35.541235347206424 139.71390995151518 6.58 35.541252848734416 139.71397014130528 6.58</gml:posList>
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
											<gml:posList>35.541252848734416 139.71397014130528 2.811 35.541235347206424 139.71390995151518 2.811 35.54127846401389 139.71389499908867 2.811 35.541287405476595 139.7139128520468 2.811 35.54129242555891 139.713930379965 2.811 35.54129367060523 139.7139588317228 2.811 35.541252848734416 139.71397014130528 2.811</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541252848734416 139.71397014130528 2.811 35.54129367060523 139.7139588317228 2.811 35.54129367060523 139.7139588317228 6.58 35.541252848734416 139.71397014130528 6.58 35.541252848734416 139.71397014130528 2.811</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54129367060523 139.7139588317228 2.811 35.54129242555891 139.713930379965 2.811 35.54129242555891 139.713930379965 6.58 35.54129367060523 139.7139588317228 6.58 35.54129367060523 139.7139588317228 2.811</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54129242555891 139.713930379965 2.811 35.541287405476595 139.7139128520468 2.811 35.541287405476595 139.7139128520468 6.58 35.54129242555891 139.713930379965 6.58 35.54129242555891 139.713930379965 2.811</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541287405476595 139.7139128520468 2.811 35.54127846401389 139.71389499908867 2.811 35.54127846401389 139.71389499908867 6.58 35.541287405476595 139.7139128520468 6.58 35.541287405476595 139.7139128520468 2.811</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54127846401389 139.71389499908867 2.811 35.541235347206424 139.71390995151518 2.811 35.541235347206424 139.71390995151518 6.58 35.54127846401389 139.71389499908867 6.58 35.54127846401389 139.71389499908867 2.811</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541235347206424 139.71390995151518 2.811 35.541252848734416 139.71397014130528 2.811 35.541252848734416 139.71397014130528 6.58 35.541235347206424 139.71390995151518 6.58 35.541235347206424 139.71390995151518 2.811</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541252848734416 139.71397014130528 6.58 35.54129367060523 139.7139588317228 6.58 35.54129242555891 139.713930379965 6.58 35.541287405476595 139.7139128520468 6.58 35.54127846401389 139.71389499908867 6.58 35.541235347206424 139.71390995151518 6.58 35.541252848734416 139.71397014130528 6.58</gml:posList>
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
									<xAL:LocalityName Type="Town">東京都大田区南六郷三丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">30.45420</uro:buildingRoofEdgeArea>
					<uro:districtsAndZonesType codeSpace="../../codelists/Common_districtsAndZonesType.xml">12</uro:districtsAndZonesType>
					<uro:prefecture codeSpace="../../codelists/Common_prefecture.xml">13</uro:prefecture>
					<uro:city codeSpace="../../codelists/Common_localPublicAuthorities.xml">13111</uro:city>
					<uro:surveyYear>2016</uro:surveyYear>
				</uro:BuildingDetails>
			</uro:buildingDetails>
			<uro:extendedAttribute>
				<uro:KeyValuePair>
					<uro:key codeSpace="../../codelists/extendedAttribute_key.xml">106</uro:key>
					<uro:codeValue codeSpace="../../codelists/extendedAttribute_key106.xml">20</uro:codeValue>
				</uro:KeyValuePair>
			</uro:extendedAttribute>
		</bldg:Building>
	</core:cityObjectMember>
	<core:cityObjectMember>
		<bldg:Building gml:id="BLD_a3879a2f-3a08-43e5-911f-111ac3223757">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-327</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="大字・町コード">
				<gen:value>58</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="町・丁目コード">
				<gen:value>3</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
				<gen:value>13111058003</gen:value>
			</gen:stringAttribute>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（計画規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L1</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">2.200</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">2.970</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">11.47</gen:value>
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
					<gen:value uom="m">0.109</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">3.9</bldg:measuredHeight>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.541112315426226 139.71419169159097 6.449 35.54117570539155 139.71416843796854 6.449 35.54116145158618 139.71411089022885 6.449 35.54109819716753 139.7141344745469 6.449 35.541112315426226 139.71419169159097 6.449</gml:posList>
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
											<gml:posList>35.541112315426226 139.71419169159097 2.882 35.54109819716753 139.7141344745469 2.882 35.54116145158618 139.71411089022885 2.882 35.54117570539155 139.71416843796854 2.882 35.541112315426226 139.71419169159097 2.882</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541112315426226 139.71419169159097 2.882 35.54117570539155 139.71416843796854 2.882 35.54117570539155 139.71416843796854 6.449 35.541112315426226 139.71419169159097 6.449 35.541112315426226 139.71419169159097 2.882</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54117570539155 139.71416843796854 2.882 35.54116145158618 139.71411089022885 2.882 35.54116145158618 139.71411089022885 6.449 35.54117570539155 139.71416843796854 6.449 35.54117570539155 139.71416843796854 2.882</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54116145158618 139.71411089022885 2.882 35.54109819716753 139.7141344745469 2.882 35.54109819716753 139.7141344745469 6.449 35.54116145158618 139.71411089022885 6.449 35.54116145158618 139.71411089022885 2.882</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54109819716753 139.7141344745469 2.882 35.541112315426226 139.71419169159097 2.882 35.541112315426226 139.71419169159097 6.449 35.54109819716753 139.7141344745469 6.449 35.54109819716753 139.7141344745469 2.882</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541112315426226 139.71419169159097 6.449 35.54117570539155 139.71416843796854 6.449 35.54116145158618 139.71411089022885 6.449 35.54109819716753 139.7141344745469 6.449 35.541112315426226 139.71419169159097 6.449</gml:posList>
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
									<xAL:LocalityName Type="Town">東京都大田区南六郷三丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">39.89340</uro:buildingRoofEdgeArea>
					<uro:districtsAndZonesType codeSpace="../../codelists/Common_districtsAndZonesType.xml">12</uro:districtsAndZonesType>
					<uro:prefecture codeSpace="../../codelists/Common_prefecture.xml">13</uro:prefecture>
					<uro:city codeSpace="../../codelists/Common_localPublicAuthorities.xml">13111</uro:city>
					<uro:surveyYear>2016</uro:surveyYear>
				</uro:BuildingDetails>
			</uro:buildingDetails>
			<uro:extendedAttribute>
				<uro:KeyValuePair>
					<uro:key codeSpace="../../codelists/extendedAttribute_key.xml">106</uro:key>
					<uro:codeValue codeSpace="../../codelists/extendedAttribute_key106.xml">20</uro:codeValue>
				</uro:KeyValuePair>
			</uro:extendedAttribute>
		</bldg:Building>
	</core:cityObjectMember>
	<core:cityObjectMember>
		<bldg:Building gml:id="BLD_b5d4a551-2cc1-4500-87d6-f9697bc4bfa2">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-493</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="大字・町コード">
				<gen:value>58</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="町・丁目コード">
				<gen:value>3</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
				<gen:value>13111058003</gen:value>
			</gen:stringAttribute>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（計画規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L1</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">1.840</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>3</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">3.280</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">15.07</gen:value>
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
					<gen:value uom="m">0.080</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">3.4</bldg:measuredHeight>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.54152076217255 139.71281174561736 5.689 35.5415369124557 139.712736947809 5.689 35.54147334356041 139.7127161990047 5.689 35.54144965632675 139.7128257479667 5.689 35.54147237864774 139.712832992822 5.689 35.54146618747908 139.71286145574626 5.689 35.54150703370132 139.71287462893292 5.689 35.541514167036006 139.71284186344832 5.689 35.54152076217255 139.71281174561736 5.689</gml:posList>
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
											<gml:posList>35.54152076217255 139.71281174561736 2.309 35.541514167036006 139.71284186344832 2.309 35.54150703370132 139.71287462893292 2.309 35.54146618747908 139.71286145574626 2.309 35.54147237864774 139.712832992822 2.309 35.54144965632675 139.7128257479667 2.309 35.54147334356041 139.7127161990047 2.309 35.5415369124557 139.712736947809 2.309 35.54152076217255 139.71281174561736 2.309</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54152076217255 139.71281174561736 2.309 35.5415369124557 139.712736947809 2.309 35.5415369124557 139.712736947809 5.689 35.54152076217255 139.71281174561736 5.689 35.54152076217255 139.71281174561736 2.309</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.5415369124557 139.712736947809 2.309 35.54147334356041 139.7127161990047 2.309 35.54147334356041 139.7127161990047 5.689 35.5415369124557 139.712736947809 5.689 35.5415369124557 139.712736947809 2.309</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54147334356041 139.7127161990047 2.309 35.54144965632675 139.7128257479667 2.309 35.54144965632675 139.7128257479667 5.689 35.54147334356041 139.7127161990047 5.689 35.54147334356041 139.7127161990047 2.309</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54144965632675 139.7128257479667 2.309 35.54147237864774 139.712832992822 2.309 35.54147237864774 139.712832992822 5.689 35.54144965632675 139.7128257479667 5.689 35.54144965632675 139.7128257479667 2.309</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54147237864774 139.712832992822 2.309 35.54146618747908 139.71286145574626 2.309 35.54146618747908 139.71286145574626 5.689 35.54147237864774 139.712832992822 5.689 35.54147237864774 139.712832992822 2.309</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54146618747908 139.71286145574626 2.309 35.54150703370132 139.71287462893292 2.309 35.54150703370132 139.71287462893292 5.689 35.54146618747908 139.71286145574626 5.689 35.54146618747908 139.71286145574626 2.309</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54150703370132 139.71287462893292 2.309 35.541514167036006 139.71284186344832 2.309 35.541514167036006 139.71284186344832 5.689 35.54150703370132 139.71287462893292 5.689 35.54150703370132 139.71287462893292 2.309</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.541514167036006 139.71284186344832 2.309 35.54152076217255 139.71281174561736 2.309 35.54152076217255 139.71281174561736 5.689 35.541514167036006 139.71284186344832 5.689 35.541514167036006 139.71284186344832 2.309</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54152076217255 139.71281174561736 5.689 35.5415369124557 139.712736947809 5.689 35.54147334356041 139.7127161990047 5.689 35.54144965632675 139.7128257479667 5.689 35.54147237864774 139.712832992822 5.689 35.54146618747908 139.71286145574626 5.689 35.54150703370132 139.71287462893292 5.689 35.541514167036006 139.71284186344832 5.689 35.54152076217255 139.71281174561736 5.689</gml:posList>
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
									<xAL:LocalityName Type="Town">東京都大田区南六郷三丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">87.38955</uro:buildingRoofEdgeArea>
					<uro:districtsAndZonesType codeSpace="../../codelists/Common_districtsAndZonesType.xml">12</uro:districtsAndZonesType>
					<uro:prefecture codeSpace="../../codelists/Common_prefecture.xml">13</uro:prefecture>
					<uro:city codeSpace="../../codelists/Common_localPublicAuthorities.xml">13111</uro:city>
					<uro:surveyYear>2016</uro:surveyYear>
				</uro:BuildingDetails>
			</uro:buildingDetails>
			<uro:extendedAttribute>
				<uro:KeyValuePair>
					<uro:key codeSpace="../../codelists/extendedAttribute_key.xml">106</uro:key>
					<uro:codeValue codeSpace="../../codelists/extendedAttribute_key106.xml">20</uro:codeValue>
				</uro:KeyValuePair>
			</uro:extendedAttribute>
		</bldg:Building>
	</core:cityObjectMember>
	<core:cityObjectMember>
		<bldg:Building gml:id="BLD_6c9923a4-ee9b-457c-8aa1-d06b84a398c0">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-239</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="大字・町コード">
				<gen:value>58</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="町・丁目コード">
				<gen:value>3</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
				<gen:value>13111058003</gen:value>
			</gen:stringAttribute>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（計画規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L1</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">2.070</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">2.940</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">11.47</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">5.5</bldg:measuredHeight>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.540907913951706 139.71354385036943 4.945 35.54093143105161 139.7135345514793 4.945 35.5409019819405 139.71342508251772 4.945 35.54087846517793 139.71343471229 4.945 35.540907913951706 139.71354385036943 4.945</gml:posList>
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
											<gml:posList>35.540907913951706 139.71354385036943 2.91 35.54087846517793 139.71343471229 2.91 35.5409019819405 139.71342508251772 2.91 35.54093143105161 139.7135345514793 2.91 35.540907913951706 139.71354385036943 2.91</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.540907913951706 139.71354385036943 2.91 35.54093143105161 139.7135345514793 2.91 35.54093143105161 139.7135345514793 4.945 35.540907913951706 139.71354385036943 4.945 35.540907913951706 139.71354385036943 2.91</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54093143105161 139.7135345514793 2.91 35.5409019819405 139.71342508251772 2.91 35.5409019819405 139.71342508251772 4.945 35.54093143105161 139.7135345514793 4.945 35.54093143105161 139.7135345514793 2.91</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.5409019819405 139.71342508251772 2.91 35.54087846517793 139.71343471229 2.91 35.54087846517793 139.71343471229 4.945 35.5409019819405 139.71342508251772 4.945 35.5409019819405 139.71342508251772 2.91</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54087846517793 139.71343471229 2.91 35.540907913951706 139.71354385036943 2.91 35.540907913951706 139.71354385036943 4.945 35.54087846517793 139.71343471229 4.945 35.54087846517793 139.71343471229 2.91</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.540907913951706 139.71354385036943 4.945 35.54093143105161 139.7135345514793 4.945 35.5409019819405 139.71342508251772 4.945 35.54087846517793 139.71343471229 4.945 35.540907913951706 139.71354385036943 4.945</gml:posList>
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
									<xAL:LocalityName Type="Town">東京都大田区南六郷三丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">28.66118</uro:buildingRoofEdgeArea>
					<uro:districtsAndZonesType codeSpace="../../codelists/Common_districtsAndZonesType.xml">12</uro:districtsAndZonesType>
					<uro:prefecture codeSpace="../../codelists/Common_prefecture.xml">13</uro:prefecture>
					<uro:city codeSpace="../../codelists/Common_localPublicAuthorities.xml">13111</uro:city>
					<uro:surveyYear>2016</uro:surveyYear>
				</uro:BuildingDetails>
			</uro:buildingDetails>
			<uro:extendedAttribute>
				<uro:KeyValuePair>
					<uro:key codeSpace="../../codelists/extendedAttribute_key.xml">106</uro:key>
					<uro:codeValue codeSpace="../../codelists/extendedAttribute_key106.xml">20</uro:codeValue>
				</uro:KeyValuePair>
			</uro:extendedAttribute>
		</bldg:Building>
	</core:cityObjectMember>
	<core:cityObjectMember>
		<bldg:Building gml:id="BLD_d5ed1615-6cab-4de8-96d4-71203934bad0">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-146894</gen:value>
			</gen:stringAttribute>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（計画規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L1</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">2.050</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>3</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">3.030</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">11.77</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">20.6</bldg:measuredHeight>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.54077294079812 139.71276276539515 23.298000000000002 35.54087778005558 139.71272593661917 23.298000000000002 35.54083347153819 139.71253712725172 23.298000000000002 35.540850547813456 139.7125311286294 23.298000000000002 35.54084408065918 139.7125035704688 23.298000000000002 35.54082607627865 139.7125098958281 23.298000000000002 35.54081253271364 139.71245218213994 23.298000000000002 35.54070862164589 139.7124886845105 23.298000000000002 35.54077294079812 139.71276276539515 23.298000000000002</gml:posList>
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
											<gml:posList>35.54077294079812 139.71276276539515 2.717 35.54070862164589 139.7124886845105 2.717 35.54081253271364 139.71245218213994 2.717 35.54082607627865 139.7125098958281 2.717 35.54084408065918 139.7125035704688 2.717 35.540850547813456 139.7125311286294 2.717 35.54083347153819 139.71253712725172 2.717 35.54087778005558 139.71272593661917 2.717 35.54077294079812 139.71276276539515 2.717</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54077294079812 139.71276276539515 2.717 35.54087778005558 139.71272593661917 2.717 35.54087778005558 139.71272593661917 23.298 35.54077294079812 139.71276276539515 23.298 35.54077294079812 139.71276276539515 2.717</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54087778005558 139.71272593661917 2.717 35.54083347153819 139.71253712725172 2.717 35.54083347153819 139.71253712725172 23.298 35.54087778005558 139.71272593661917 23.298 35.54087778005558 139.71272593661917 2.717</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54083347153819 139.71253712725172 2.717 35.540850547813456 139.7125311286294 2.717 35.540850547813456 139.7125311286294 23.298 35.54083347153819 139.71253712725172 23.298 35.54083347153819 139.71253712725172 2.717</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.540850547813456 139.7125311286294 2.717 35.54084408065918 139.7125035704688 2.717 35.54084408065918 139.7125035704688 23.298 35.540850547813456 139.7125311286294 23.298 35.540850547813456 139.7125311286294 2.717</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54084408065918 139.7125035704688 2.717 35.54082607627865 139.7125098958281 2.717 35.54082607627865 139.7125098958281 23.298 35.54084408065918 139.7125035704688 23.298 35.54084408065918 139.7125035704688 2.717</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54082607627865 139.7125098958281 2.717 35.54081253271364 139.71245218213994 2.717 35.54081253271364 139.71245218213994 23.298 35.54082607627865 139.7125098958281 23.298 35.54082607627865 139.7125098958281 2.717</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54081253271364 139.71245218213994 2.717 35.54070862164589 139.7124886845105 2.717 35.54070862164589 139.7124886845105 23.298 35.54081253271364 139.71245218213994 23.298 35.54081253271364 139.71245218213994 2.717</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54070862164589 139.7124886845105 2.717 35.54077294079812 139.71276276539515 2.717 35.54077294079812 139.71276276539515 23.298 35.54070862164589 139.7124886845105 23.298 35.54070862164589 139.7124886845105 2.717</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54077294079812 139.71276276539515 23.298 35.54087778005558 139.71272593661917 23.298 35.54083347153819 139.71253712725172 23.298 35.540850547813456 139.7125311286294 23.298 35.54084408065918 139.7125035704688 23.298 35.54082607627865 139.7125098958281 23.298 35.54081253271364 139.71245218213994 23.298 35.54070862164589 139.7124886845105 23.298 35.54077294079812 139.71276276539515 23.298</gml:posList>
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
									<xAL:LocalityName Type="Town">東京都大田区南六郷三丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">0.00000</uro:buildingRoofEdgeArea>
					<uro:districtsAndZonesType codeSpace="../../codelists/Common_districtsAndZonesType.xml">12</uro:districtsAndZonesType>
					<uro:prefecture codeSpace="../../codelists/Common_prefecture.xml">13</uro:prefecture>
					<uro:city codeSpace="../../codelists/Common_localPublicAuthorities.xml">13111</uro:city>
					<uro:surveyYear>2016</uro:surveyYear>
				</uro:BuildingDetails>
			</uro:buildingDetails>
			<uro:extendedAttribute>
				<uro:KeyValuePair>
					<uro:key codeSpace="../../codelists/extendedAttribute_key.xml">106</uro:key>
					<uro:codeValue codeSpace="../../codelists/extendedAttribute_key106.xml">20</uro:codeValue>
				</uro:KeyValuePair>
			</uro:extendedAttribute>
		</bldg:Building>
	</core:cityObjectMember>
	<core:cityObjectMember>
		<bldg:Building gml:id="BLD_57cd4ea7-0fb7-4b0e-a600-9982cf3b60ca">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-224</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="大字・町コード">
				<gen:value>58</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="町・丁目コード">
				<gen:value>3</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
				<gen:value>13111058003</gen:value>
			</gen:stringAttribute>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（計画規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L1</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">2.070</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">2.940</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">11.47</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">5.6</bldg:measuredHeight>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.54086066432969 139.71334573903766 8.398 35.540903645531564 139.7133304557272 8.398 35.54089902868296 139.71331061135066 8.398 35.540855912604165 139.71332622572578 8.398 35.54086066432969 139.71334573903766 8.398</gml:posList>
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
											<gml:posList>35.54086066432969 139.71334573903766 2.846 35.540855912604165 139.71332622572578 2.846 35.54089902868296 139.71331061135066 2.846 35.540903645531564 139.7133304557272 2.846 35.54086066432969 139.71334573903766 2.846</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54086066432969 139.71334573903766 2.846 35.540903645531564 139.7133304557272 2.846 35.540903645531564 139.7133304557272 8.398 35.54086066432969 139.71334573903766 8.398 35.54086066432969 139.71334573903766 2.846</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.540903645531564 139.7133304557272 2.846 35.54089902868296 139.71331061135066 2.846 35.54089902868296 139.71331061135066 8.398 35.540903645531564 139.7133304557272 8.398 35.540903645531564 139.7133304557272 2.846</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54089902868296 139.71331061135066 2.846 35.540855912604165 139.71332622572578 2.846 35.540855912604165 139.71332622572578 8.398 35.54089902868296 139.71331061135066 8.398 35.54089902868296 139.71331061135066 2.846</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.540855912604165 139.71332622572578 2.846 35.54086066432969 139.71334573903766 2.846 35.54086066432969 139.71334573903766 8.398 35.540855912604165 139.71332622572578 8.398 35.540855912604165 139.71332622572578 2.846</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.54086066432969 139.71334573903766 8.398 35.540903645531564 139.7133304557272 8.398 35.54089902868296 139.71331061135066 8.398 35.540855912604165 139.71332622572578 8.398 35.54086066432969 139.71334573903766 8.398</gml:posList>
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
									<xAL:LocalityName Type="Town">東京都大田区南六郷三丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">9.24975</uro:buildingRoofEdgeArea>
					<uro:districtsAndZonesType codeSpace="../../codelists/Common_districtsAndZonesType.xml">12</uro:districtsAndZonesType>
					<uro:prefecture codeSpace="../../codelists/Common_prefecture.xml">13</uro:prefecture>
					<uro:city codeSpace="../../codelists/Common_localPublicAuthorities.xml">13111</uro:city>
					<uro:surveyYear>2016</uro:surveyYear>
				</uro:BuildingDetails>
			</uro:buildingDetails>
			<uro:extendedAttribute>
				<uro:KeyValuePair>
					<uro:key codeSpace="../../codelists/extendedAttribute_key.xml">106</uro:key>
					<uro:codeValue codeSpace="../../codelists/extendedAttribute_key106.xml">20</uro:codeValue>
				</uro:KeyValuePair>
			</uro:extendedAttribute>
		</bldg:Building>
	</core:cityObjectMember>
</core:CityModel>
