<?xml version="1.0" encoding="UTF-8"?>
<core:CityModel xmlns:grp="http://www.opengis.net/citygml/cityobjectgroup/2.0" xmlns:core="http://www.opengis.net/citygml/2.0" xmlns:pbase="http://www.opengis.net/citygml/profiles/base/2.0" xmlns:smil20lang="http://www.w3.org/2001/SMIL20/Language" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:smil20="http://www.w3.org/2001/SMIL20/" xmlns:bldg="http://www.opengis.net/citygml/building/2.0" xmlns:xAL="urn:oasis:names:tc:ciq:xsdschema:xAL:2.0" xmlns:uro="http://www.kantei.go.jp/jp/singi/tiiki/toshisaisei/itoshisaisei/iur/uro/1.4" xmlns:luse="http://www.opengis.net/citygml/landuse/2.0" xmlns:app="http://www.opengis.net/citygml/appearance/2.0" xmlns:gen="http://www.opengis.net/citygml/generics/2.0" xmlns:dem="http://www.opengis.net/citygml/relief/2.0" xmlns:tex="http://www.opengis.net/citygml/texturedsurface/2.0" xmlns:tun="http://www.opengis.net/citygml/tunnel/2.0" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:sch="http://www.ascc.net/xml/schematron" xmlns:veg="http://www.opengis.net/citygml/vegetation/2.0" xmlns:frn="http://www.opengis.net/citygml/cityfurniture/2.0" xmlns:gml="http://www.opengis.net/gml" xmlns:tran="http://www.opengis.net/citygml/transportation/2.0" xmlns:wtr="http://www.opengis.net/citygml/waterbody/2.0" xmlns:brid="http://www.opengis.net/citygml/bridge/2.0" xsi:schemaLocation="http://www.kantei.go.jp/jp/singi/tiiki/toshisaisei/itoshisaisei/iur/uro/1.4 http://www.kantei.go.jp/jp/singi/tiiki/toshisaisei/itoshisaisei/iur/schemas/uro/1.4/urbanObject.xsd http://www.opengis.net/citygml/2.0 http://schemas.opengis.net/citygml/2.0/cityGMLBase.xsd http://www.opengis.net/citygml/landuse/2.0 http://schemas.opengis.net/citygml/landuse/2.0/landUse.xsd http://www.opengis.net/citygml/building/2.0 http://schemas.opengis.net/citygml/building/2.0/building.xsd http://www.opengis.net/citygml/transportation/2.0 http://schemas.opengis.net/citygml/transportation/2.0/transportation.xsd http://www.opengis.net/citygml/generics/2.0 http://schemas.opengis.net/citygml/generics/2.0/generics.xsd http://www.opengis.net/citygml/cityobjectgroup/2.0 http://schemas.opengis.net/citygml/cityobjectgroup/2.0/cityObjectGroup.xsd http://www.opengis.net/gml http://schemas.opengis.net/gml/3.1.1/base/gml.xsd http://www.opengis.net/citygml/appearance/2.0 http://schemas.opengis.net/citygml/appearance/2.0/appearance.xsd">
	<gml:boundedBy>
		<gml:Envelope srsName="http://www.opengis.net/def/crs/EPSG/0/6697" srsDimension="3">
			<gml:lowerCorner>35.54070862164589 139.71245218213994 2.02</gml:lowerCorner>
			<gml:upperCorner>35.542252321638614 139.7156383865409 40.492</gml:upperCorner>
		</gml:Envelope>
	</gml:boundedBy>
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
			<bldg:yearOfConstruction>1976</bldg:yearOfConstruction>
			<bldg:measuredHeight uom="m">42.7</bldg:measuredHeight>
			<bldg:storeysAboveGround>2</bldg:storeysAboveGround>
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
			<bldg:yearOfConstruction>2003</bldg:yearOfConstruction>
			<bldg:measuredHeight uom="m">7.3</bldg:measuredHeight>
			<bldg:storeysBelowGround>1</bldg:storeysBelowGround>
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
</core:CityModel>
