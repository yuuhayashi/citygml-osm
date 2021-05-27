<?xml version="1.0" encoding="UTF-8"?>
<core:CityModel xmlns:grp="http://www.opengis.net/citygml/cityobjectgroup/2.0" xmlns:core="http://www.opengis.net/citygml/2.0" xmlns:pbase="http://www.opengis.net/citygml/profiles/base/2.0" xmlns:smil20lang="http://www.w3.org/2001/SMIL20/Language" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:smil20="http://www.w3.org/2001/SMIL20/" xmlns:bldg="http://www.opengis.net/citygml/building/2.0" xmlns:xAL="urn:oasis:names:tc:ciq:xsdschema:xAL:2.0" xmlns:uro="http://www.kantei.go.jp/jp/singi/tiiki/toshisaisei/itoshisaisei/iur/uro/1.4" xmlns:luse="http://www.opengis.net/citygml/landuse/2.0" xmlns:app="http://www.opengis.net/citygml/appearance/2.0" xmlns:gen="http://www.opengis.net/citygml/generics/2.0" xmlns:dem="http://www.opengis.net/citygml/relief/2.0" xmlns:tex="http://www.opengis.net/citygml/texturedsurface/2.0" xmlns:tun="http://www.opengis.net/citygml/tunnel/2.0" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:sch="http://www.ascc.net/xml/schematron" xmlns:veg="http://www.opengis.net/citygml/vegetation/2.0" xmlns:frn="http://www.opengis.net/citygml/cityfurniture/2.0" xmlns:gml="http://www.opengis.net/gml" xmlns:tran="http://www.opengis.net/citygml/transportation/2.0" xmlns:wtr="http://www.opengis.net/citygml/waterbody/2.0" xmlns:brid="http://www.opengis.net/citygml/bridge/2.0" xsi:schemaLocation="http://www.kantei.go.jp/jp/singi/tiiki/toshisaisei/itoshisaisei/iur/uro/1.4 http://www.kantei.go.jp/jp/singi/tiiki/toshisaisei/itoshisaisei/iur/schemas/uro/1.4/urbanObject.xsd http://www.opengis.net/citygml/2.0 http://schemas.opengis.net/citygml/2.0/cityGMLBase.xsd http://www.opengis.net/citygml/landuse/2.0 http://schemas.opengis.net/citygml/landuse/2.0/landUse.xsd http://www.opengis.net/citygml/building/2.0 http://schemas.opengis.net/citygml/building/2.0/building.xsd http://www.opengis.net/citygml/transportation/2.0 http://schemas.opengis.net/citygml/transportation/2.0/transportation.xsd http://www.opengis.net/citygml/generics/2.0 http://schemas.opengis.net/citygml/generics/2.0/generics.xsd http://www.opengis.net/citygml/cityobjectgroup/2.0 http://schemas.opengis.net/citygml/cityobjectgroup/2.0/cityObjectGroup.xsd http://www.opengis.net/gml http://schemas.opengis.net/gml/3.1.1/base/gml.xsd http://www.opengis.net/citygml/appearance/2.0 http://schemas.opengis.net/citygml/appearance/2.0/appearance.xsd">
	<gml:boundedBy>
		<gml:Envelope srsName="http://www.opengis.net/def/crs/EPSG/0/6697" srsDimension="3">
			<gml:lowerCorner>35.56633413507695 139.73733775689777 0.121</gml:lowerCorner>
			<gml:upperCorner>35.57505092590962 139.75013607178525 31.288</gml:upperCorner>
		</gml:Envelope>
	</gml:boundedBy>
	<core:cityObjectMember>
		<bldg:Building gml:id="BLD_badd7611-fe0b-4be8-935d-095bba500da7">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-55333</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="大字・町コード">
				<gen:value>7</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="町・丁目コード">
				<gen:value>4</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
				<gen:value>13111007004</gen:value>
			</gen:stringAttribute>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>2</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">1.260</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">4.33</gen:value>
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
					<gen:value uom="m">0.113</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">3.7</bldg:measuredHeight>
			<bldg:storeysAboveGround>3</bldg:storeysAboveGround>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.56900805345228 139.73779422855804 5.5360000000000005 35.56903670207654 139.73760421779656 5.5360000000000005 35.568998160932175 139.73759565842656 5.5360000000000005 35.568946914258746 139.73793330836284 5.5360000000000005 35.56898613168212 139.73794219805734 5.5360000000000005 35.56899850637794 139.73786076482028 5.5360000000000005 35.569002833823774 139.73786175259602 5.5360000000000005 35.56900295749851 139.73784718977757 5.5360000000000005 35.5690040281526 139.73783328777495 5.5360000000000005 35.56900618046998 139.73781938448823 5.5360000000000005 35.56900927976703 139.73780614201638 5.5360000000000005 35.56901265131486 139.73779521600926 5.5360000000000005 35.56900805345228 139.73779422855804 5.5360000000000005</gml:posList>
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
											<gml:posList>35.56900805345228 139.73779422855804 1.933 35.56901265131486 139.73779521600926 1.933 35.56900927976703 139.73780614201638 1.933 35.56900618046998 139.73781938448823 1.933 35.5690040281526 139.73783328777495 1.933 35.56900295749851 139.73784718977757 1.933 35.569002833823774 139.73786175259602 1.933 35.56899850637794 139.73786076482028 1.933 35.56898613168212 139.73794219805734 1.933 35.568946914258746 139.73793330836284 1.933 35.568998160932175 139.73759565842656 1.933 35.56903670207654 139.73760421779656 1.933 35.56900805345228 139.73779422855804 1.933</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56900805345228 139.73779422855804 1.933 35.56903670207654 139.73760421779656 1.933 35.56903670207654 139.73760421779656 5.5360000000000005 35.56900805345228 139.73779422855804 5.5360000000000005 35.56900805345228 139.73779422855804 1.933</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56903670207654 139.73760421779656 1.933 35.568998160932175 139.73759565842656 1.933 35.568998160932175 139.73759565842656 5.5360000000000005 35.56903670207654 139.73760421779656 5.5360000000000005 35.56903670207654 139.73760421779656 1.933</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.568998160932175 139.73759565842656 1.933 35.568946914258746 139.73793330836284 1.933 35.568946914258746 139.73793330836284 5.5360000000000005 35.568998160932175 139.73759565842656 5.5360000000000005 35.568998160932175 139.73759565842656 1.933</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.568946914258746 139.73793330836284 1.933 35.56898613168212 139.73794219805734 1.933 35.56898613168212 139.73794219805734 5.5360000000000005 35.568946914258746 139.73793330836284 5.5360000000000005 35.568946914258746 139.73793330836284 1.933</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56898613168212 139.73794219805734 1.933 35.56899850637794 139.73786076482028 1.933 35.56899850637794 139.73786076482028 5.5360000000000005 35.56898613168212 139.73794219805734 5.5360000000000005 35.56898613168212 139.73794219805734 1.933</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56899850637794 139.73786076482028 1.933 35.569002833823774 139.73786175259602 1.933 35.569002833823774 139.73786175259602 5.5360000000000005 35.56899850637794 139.73786076482028 5.5360000000000005 35.56899850637794 139.73786076482028 1.933</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.569002833823774 139.73786175259602 1.933 35.56900295749851 139.73784718977757 1.933 35.56900295749851 139.73784718977757 5.5360000000000005 35.569002833823774 139.73786175259602 5.5360000000000005 35.569002833823774 139.73786175259602 1.933</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56900295749851 139.73784718977757 1.933 35.5690040281526 139.73783328777495 1.933 35.5690040281526 139.73783328777495 5.5360000000000005 35.56900295749851 139.73784718977757 5.5360000000000005 35.56900295749851 139.73784718977757 1.933</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.5690040281526 139.73783328777495 1.933 35.56900618046998 139.73781938448823 1.933 35.56900618046998 139.73781938448823 5.5360000000000005 35.5690040281526 139.73783328777495 5.5360000000000005 35.5690040281526 139.73783328777495 1.933</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56900618046998 139.73781938448823 1.933 35.56900927976703 139.73780614201638 1.933 35.56900927976703 139.73780614201638 5.5360000000000005 35.56900618046998 139.73781938448823 5.5360000000000005 35.56900618046998 139.73781938448823 1.933</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56900927976703 139.73780614201638 1.933 35.56901265131486 139.73779521600926 1.933 35.56901265131486 139.73779521600926 5.5360000000000005 35.56900927976703 139.73780614201638 5.5360000000000005 35.56900927976703 139.73780614201638 1.933</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56901265131486 139.73779521600926 1.933 35.56900805345228 139.73779422855804 1.933 35.56900805345228 139.73779422855804 5.5360000000000005 35.56901265131486 139.73779521600926 5.5360000000000005 35.56901265131486 139.73779521600926 1.933</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56900805345228 139.73779422855804 5.5360000000000005 35.56903670207654 139.73760421779656 5.5360000000000005 35.568998160932175 139.73759565842656 5.5360000000000005 35.568946914258746 139.73793330836284 5.5360000000000005 35.56898613168212 139.73794219805734 5.5360000000000005 35.56899850637794 139.73786076482028 5.5360000000000005 35.569002833823774 139.73786175259602 5.5360000000000005 35.56900295749851 139.73784718977757 5.5360000000000005 35.5690040281526 139.73783328777495 5.5360000000000005 35.56900618046998 139.73781938448823 5.5360000000000005 35.56900927976703 139.73780614201638 5.5360000000000005 35.56901265131486 139.73779521600926 5.5360000000000005 35.56900805345228 139.73779422855804 5.5360000000000005</gml:posList>
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
									<xAL:LocalityName Type="Town">東京都大田区大森東四丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">138.16237</uro:buildingRoofEdgeArea>
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
		<bldg:Building gml:id="BLD_7b7ea393-86ee-43c0-adfd-da0dff6f69b4">
			<gml:name>大田病院</gml:name>
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-56522</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="大字・町コード">
				<gen:value>7</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="町・丁目コード">
				<gen:value>4</gen:value>
			</gen:stringAttribute>
			<gen:stringAttribute name="13_区市町村コード_大字・町コード_町・丁目コード">
				<gen:value>13111007004</gen:value>
			</gen:stringAttribute>
			<gen:genericAttributeSet name="多摩水系多摩川、浅川、大栗川洪水浸水想定区域（想定最大規模）">
				<gen:stringAttribute name="規模">
					<gen:value>L2</gen:value>
				</gen:stringAttribute>
				<gen:stringAttribute name="浸水ランク">
					<gen:value>1</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">0.380</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">1.37</gen:value>
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
					<gen:value uom="m">0.630</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">21.6</bldg:measuredHeight>
			<bldg:storeysAboveGround>2</bldg:storeysAboveGround>
			<bldg:storeysBelowGround>1</bldg:storeysBelowGround>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.56942771002788 139.73775666154864 17.199 35.56942999649453 139.73774143414107 17.199 35.56941187519735 139.73773715303633 17.199 35.56941335408767 139.73772656019162 17.199 35.56934249218028 139.73771042678584 17.199 35.56929651328693 139.73770022131598 17.199 35.569324489139845 139.73751451326567 17.199 35.569001147876634 139.73744109197492 17.199 35.568994288583944 139.73748677397694 17.199 35.568991180902465 139.73748942543614 17.199 35.56898577810351 139.737496382235 17.199 35.56898213406191 139.7375046608176 17.199 35.56897997730896 139.73751293762862 17.199 35.56897930810764 139.73752154363797 17.199 35.56898026192908 139.73753080965454 17.199 35.56898310866331 139.73754007341816 17.199 35.56898541061991 139.73754437328478 17.199 35.568978416868454 139.7375910483382 17.199 35.568998160932175 139.73759565842656 17.199 35.56903670207654 139.73760421779656 17.199 35.569109321969655 139.73762067992757 17.199 35.569102328451535 139.73766768601624 17.199 35.569039715803456 139.73765352873616 17.199 35.56902155826198 139.73777402336276 17.199 35.56901818330593 139.73778064676765 17.199 35.56901265131486 139.73779521600926 17.199 35.56900927976703 139.73780614201638 17.199 35.56900618046998 139.73781938448823 17.199 35.5690040281526 139.73783328777495 17.199 35.56900295749851 139.73784718977757 17.199 35.569002833823774 139.73786175259602 17.199 35.56900379076392 139.73787499025343 17.199 35.56900487924221 139.73788359417767 17.199 35.5689954639224 139.73794582766342 17.199 35.569022375311754 139.73795208419457 17.199 35.56901268849366 139.73801299412807 17.199 35.56903621915338 139.73801859274707 17.199 35.5690169855926 139.73814670086628 17.199 35.56914667342359 139.73817600384254 17.199 35.569265948653715 139.73820333339555 17.199 35.569267965805516 139.7381894302332 17.199 35.5692859518791 139.7381937115921 17.199 35.56928971837528 139.73816921528595 17.199 35.56931825224912 139.738175469996 17.199 35.56932363183105 139.73813905681058 17.199 35.569352301175726 139.7381456423236 17.199 35.56935781647951 139.73810989090273 17.199 35.569363766681725 139.73811120774747 17.199 35.56936780148535 139.73808406332572 17.199 35.569377673507276 139.73808636844115 17.199 35.56942771002788 139.73775666154864 17.199</gml:posList>
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
											<gml:posList>35.56942771002788 139.73775666154864 1.617 35.569377673507276 139.73808636844115 1.617 35.56936780148535 139.73808406332572 1.617 35.569363766681725 139.73811120774747 1.617 35.56935781647951 139.73810989090273 1.617 35.569352301175726 139.7381456423236 1.617 35.56932363183105 139.73813905681058 1.617 35.56931825224912 139.738175469996 1.617 35.56928971837528 139.73816921528595 1.617 35.5692859518791 139.7381937115921 1.617 35.569267965805516 139.7381894302332 1.617 35.569265948653715 139.73820333339555 1.617 35.56914667342359 139.73817600384254 1.617 35.5690169855926 139.73814670086628 1.617 35.56903621915338 139.73801859274707 1.617 35.56901268849366 139.73801299412807 1.617 35.569022375311754 139.73795208419457 1.617 35.5689954639224 139.73794582766342 1.617 35.56900487924221 139.73788359417767 1.617 35.56900379076392 139.73787499025343 1.617 35.569002833823774 139.73786175259602 1.617 35.56900295749851 139.73784718977757 1.617 35.5690040281526 139.73783328777495 1.617 35.56900618046998 139.73781938448823 1.617 35.56900927976703 139.73780614201638 1.617 35.56901265131486 139.73779521600926 1.617 35.56901818330593 139.73778064676765 1.617 35.56902155826198 139.73777402336276 1.617 35.569039715803456 139.73765352873616 1.617 35.569102328451535 139.73766768601624 1.617 35.569109321969655 139.73762067992757 1.617 35.56903670207654 139.73760421779656 1.617 35.568998160932175 139.73759565842656 1.617 35.568978416868454 139.7375910483382 1.617 35.56898541061991 139.73754437328478 1.617 35.56898310866331 139.73754007341816 1.617 35.56898026192908 139.73753080965454 1.617 35.56897930810764 139.73752154363797 1.617 35.56897997730896 139.73751293762862 1.617 35.56898213406191 139.7375046608176 1.617 35.56898577810351 139.737496382235 1.617 35.568991180902465 139.73748942543614 1.617 35.568994288583944 139.73748677397694 1.617 35.569001147876634 139.73744109197492 1.617 35.569324489139845 139.73751451326567 1.617 35.56929651328693 139.73770022131598 1.617 35.56934249218028 139.73771042678584 1.617 35.56941335408767 139.73772656019162 1.617 35.56941187519735 139.73773715303633 1.617 35.56942999649453 139.73774143414107 1.617 35.56942771002788 139.73775666154864 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56942771002788 139.73775666154864 1.617 35.56942999649453 139.73774143414107 1.617 35.56942999649453 139.73774143414107 17.199 35.56942771002788 139.73775666154864 17.199 35.56942771002788 139.73775666154864 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56942999649453 139.73774143414107 1.617 35.56941187519735 139.73773715303633 1.617 35.56941187519735 139.73773715303633 17.199 35.56942999649453 139.73774143414107 17.199 35.56942999649453 139.73774143414107 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56941187519735 139.73773715303633 1.617 35.56941335408767 139.73772656019162 1.617 35.56941335408767 139.73772656019162 17.199 35.56941187519735 139.73773715303633 17.199 35.56941187519735 139.73773715303633 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56941335408767 139.73772656019162 1.617 35.56934249218028 139.73771042678584 1.617 35.56934249218028 139.73771042678584 17.199 35.56941335408767 139.73772656019162 17.199 35.56941335408767 139.73772656019162 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56934249218028 139.73771042678584 1.617 35.56929651328693 139.73770022131598 1.617 35.56929651328693 139.73770022131598 17.199 35.56934249218028 139.73771042678584 17.199 35.56934249218028 139.73771042678584 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56929651328693 139.73770022131598 1.617 35.569324489139845 139.73751451326567 1.617 35.569324489139845 139.73751451326567 17.199 35.56929651328693 139.73770022131598 17.199 35.56929651328693 139.73770022131598 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.569324489139845 139.73751451326567 1.617 35.569001147876634 139.73744109197492 1.617 35.569001147876634 139.73744109197492 17.199 35.569324489139845 139.73751451326567 17.199 35.569324489139845 139.73751451326567 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.569001147876634 139.73744109197492 1.617 35.568994288583944 139.73748677397694 1.617 35.568994288583944 139.73748677397694 17.199 35.569001147876634 139.73744109197492 17.199 35.569001147876634 139.73744109197492 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.568994288583944 139.73748677397694 1.617 35.568991180902465 139.73748942543614 1.617 35.568991180902465 139.73748942543614 17.199 35.568994288583944 139.73748677397694 17.199 35.568994288583944 139.73748677397694 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.568991180902465 139.73748942543614 1.617 35.56898577810351 139.737496382235 1.617 35.56898577810351 139.737496382235 17.199 35.568991180902465 139.73748942543614 17.199 35.568991180902465 139.73748942543614 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56898577810351 139.737496382235 1.617 35.56898213406191 139.7375046608176 1.617 35.56898213406191 139.7375046608176 17.199 35.56898577810351 139.737496382235 17.199 35.56898577810351 139.737496382235 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56898213406191 139.7375046608176 1.617 35.56897997730896 139.73751293762862 1.617 35.56897997730896 139.73751293762862 17.199 35.56898213406191 139.7375046608176 17.199 35.56898213406191 139.7375046608176 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56897997730896 139.73751293762862 1.617 35.56897930810764 139.73752154363797 1.617 35.56897930810764 139.73752154363797 17.199 35.56897997730896 139.73751293762862 17.199 35.56897997730896 139.73751293762862 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56897930810764 139.73752154363797 1.617 35.56898026192908 139.73753080965454 1.617 35.56898026192908 139.73753080965454 17.199 35.56897930810764 139.73752154363797 17.199 35.56897930810764 139.73752154363797 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56898026192908 139.73753080965454 1.617 35.56898310866331 139.73754007341816 1.617 35.56898310866331 139.73754007341816 17.199 35.56898026192908 139.73753080965454 17.199 35.56898026192908 139.73753080965454 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56898310866331 139.73754007341816 1.617 35.56898541061991 139.73754437328478 1.617 35.56898541061991 139.73754437328478 17.199 35.56898310866331 139.73754007341816 17.199 35.56898310866331 139.73754007341816 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56898541061991 139.73754437328478 1.617 35.568978416868454 139.7375910483382 1.617 35.568978416868454 139.7375910483382 17.199 35.56898541061991 139.73754437328478 17.199 35.56898541061991 139.73754437328478 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.568978416868454 139.7375910483382 1.617 35.568998160932175 139.73759565842656 1.617 35.568998160932175 139.73759565842656 17.199 35.568978416868454 139.7375910483382 17.199 35.568978416868454 139.7375910483382 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.568998160932175 139.73759565842656 1.617 35.56903670207654 139.73760421779656 1.617 35.56903670207654 139.73760421779656 17.199 35.568998160932175 139.73759565842656 17.199 35.568998160932175 139.73759565842656 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56903670207654 139.73760421779656 1.617 35.569109321969655 139.73762067992757 1.617 35.569109321969655 139.73762067992757 17.199 35.56903670207654 139.73760421779656 17.199 35.56903670207654 139.73760421779656 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.569109321969655 139.73762067992757 1.617 35.569102328451535 139.73766768601624 1.617 35.569102328451535 139.73766768601624 17.199 35.569109321969655 139.73762067992757 17.199 35.569109321969655 139.73762067992757 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.569102328451535 139.73766768601624 1.617 35.569039715803456 139.73765352873616 1.617 35.569039715803456 139.73765352873616 17.199 35.569102328451535 139.73766768601624 17.199 35.569102328451535 139.73766768601624 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.569039715803456 139.73765352873616 1.617 35.56902155826198 139.73777402336276 1.617 35.56902155826198 139.73777402336276 17.199 35.569039715803456 139.73765352873616 17.199 35.569039715803456 139.73765352873616 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56902155826198 139.73777402336276 1.617 35.56901818330593 139.73778064676765 1.617 35.56901818330593 139.73778064676765 17.199 35.56902155826198 139.73777402336276 17.199 35.56902155826198 139.73777402336276 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56901818330593 139.73778064676765 1.617 35.56901265131486 139.73779521600926 1.617 35.56901265131486 139.73779521600926 17.199 35.56901818330593 139.73778064676765 17.199 35.56901818330593 139.73778064676765 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56901265131486 139.73779521600926 1.617 35.56900927976703 139.73780614201638 1.617 35.56900927976703 139.73780614201638 17.199 35.56901265131486 139.73779521600926 17.199 35.56901265131486 139.73779521600926 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56900927976703 139.73780614201638 1.617 35.56900618046998 139.73781938448823 1.617 35.56900618046998 139.73781938448823 17.199 35.56900927976703 139.73780614201638 17.199 35.56900927976703 139.73780614201638 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56900618046998 139.73781938448823 1.617 35.5690040281526 139.73783328777495 1.617 35.5690040281526 139.73783328777495 17.199 35.56900618046998 139.73781938448823 17.199 35.56900618046998 139.73781938448823 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.5690040281526 139.73783328777495 1.617 35.56900295749851 139.73784718977757 1.617 35.56900295749851 139.73784718977757 17.199 35.5690040281526 139.73783328777495 17.199 35.5690040281526 139.73783328777495 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56900295749851 139.73784718977757 1.617 35.569002833823774 139.73786175259602 1.617 35.569002833823774 139.73786175259602 17.199 35.56900295749851 139.73784718977757 17.199 35.56900295749851 139.73784718977757 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.569002833823774 139.73786175259602 1.617 35.56900379076392 139.73787499025343 1.617 35.56900379076392 139.73787499025343 17.199 35.569002833823774 139.73786175259602 17.199 35.569002833823774 139.73786175259602 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56900379076392 139.73787499025343 1.617 35.56900487924221 139.73788359417767 1.617 35.56900487924221 139.73788359417767 17.199 35.56900379076392 139.73787499025343 17.199 35.56900379076392 139.73787499025343 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56900487924221 139.73788359417767 1.617 35.5689954639224 139.73794582766342 1.617 35.5689954639224 139.73794582766342 17.199 35.56900487924221 139.73788359417767 17.199 35.56900487924221 139.73788359417767 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.5689954639224 139.73794582766342 1.617 35.569022375311754 139.73795208419457 1.617 35.569022375311754 139.73795208419457 17.199 35.5689954639224 139.73794582766342 17.199 35.5689954639224 139.73794582766342 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.569022375311754 139.73795208419457 1.617 35.56901268849366 139.73801299412807 1.617 35.56901268849366 139.73801299412807 17.199 35.569022375311754 139.73795208419457 17.199 35.569022375311754 139.73795208419457 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56901268849366 139.73801299412807 1.617 35.56903621915338 139.73801859274707 1.617 35.56903621915338 139.73801859274707 17.199 35.56901268849366 139.73801299412807 17.199 35.56901268849366 139.73801299412807 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56903621915338 139.73801859274707 1.617 35.5690169855926 139.73814670086628 1.617 35.5690169855926 139.73814670086628 17.199 35.56903621915338 139.73801859274707 17.199 35.56903621915338 139.73801859274707 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.5690169855926 139.73814670086628 1.617 35.56914667342359 139.73817600384254 1.617 35.56914667342359 139.73817600384254 17.199 35.5690169855926 139.73814670086628 17.199 35.5690169855926 139.73814670086628 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56914667342359 139.73817600384254 1.617 35.569265948653715 139.73820333339555 1.617 35.569265948653715 139.73820333339555 17.199 35.56914667342359 139.73817600384254 17.199 35.56914667342359 139.73817600384254 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.569265948653715 139.73820333339555 1.617 35.569267965805516 139.7381894302332 1.617 35.569267965805516 139.7381894302332 17.199 35.569265948653715 139.73820333339555 17.199 35.569265948653715 139.73820333339555 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.569267965805516 139.7381894302332 1.617 35.5692859518791 139.7381937115921 1.617 35.5692859518791 139.7381937115921 17.199 35.569267965805516 139.7381894302332 17.199 35.569267965805516 139.7381894302332 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.5692859518791 139.7381937115921 1.617 35.56928971837528 139.73816921528595 1.617 35.56928971837528 139.73816921528595 17.199 35.5692859518791 139.7381937115921 17.199 35.5692859518791 139.7381937115921 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56928971837528 139.73816921528595 1.617 35.56931825224912 139.738175469996 1.617 35.56931825224912 139.738175469996 17.199 35.56928971837528 139.73816921528595 17.199 35.56928971837528 139.73816921528595 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56931825224912 139.738175469996 1.617 35.56932363183105 139.73813905681058 1.617 35.56932363183105 139.73813905681058 17.199 35.56931825224912 139.738175469996 17.199 35.56931825224912 139.738175469996 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56932363183105 139.73813905681058 1.617 35.569352301175726 139.7381456423236 1.617 35.569352301175726 139.7381456423236 17.199 35.56932363183105 139.73813905681058 17.199 35.56932363183105 139.73813905681058 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.569352301175726 139.7381456423236 1.617 35.56935781647951 139.73810989090273 1.617 35.56935781647951 139.73810989090273 17.199 35.569352301175726 139.7381456423236 17.199 35.569352301175726 139.7381456423236 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56935781647951 139.73810989090273 1.617 35.569363766681725 139.73811120774747 1.617 35.569363766681725 139.73811120774747 17.199 35.56935781647951 139.73810989090273 17.199 35.56935781647951 139.73810989090273 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.569363766681725 139.73811120774747 1.617 35.56936780148535 139.73808406332572 1.617 35.56936780148535 139.73808406332572 17.199 35.569363766681725 139.73811120774747 17.199 35.569363766681725 139.73811120774747 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56936780148535 139.73808406332572 1.617 35.569377673507276 139.73808636844115 1.617 35.569377673507276 139.73808636844115 17.199 35.56936780148535 139.73808406332572 17.199 35.56936780148535 139.73808406332572 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.569377673507276 139.73808636844115 1.617 35.56942771002788 139.73775666154864 1.617 35.56942771002788 139.73775666154864 17.199 35.569377673507276 139.73808636844115 17.199 35.569377673507276 139.73808636844115 1.617</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.56942771002788 139.73775666154864 17.199 35.56942999649453 139.73774143414107 17.199 35.56941187519735 139.73773715303633 17.199 35.56941335408767 139.73772656019162 17.199 35.56934249218028 139.73771042678584 17.199 35.56929651328693 139.73770022131598 17.199 35.569324489139845 139.73751451326567 17.199 35.569001147876634 139.73744109197492 17.199 35.568994288583944 139.73748677397694 17.199 35.568991180902465 139.73748942543614 17.199 35.56898577810351 139.737496382235 17.199 35.56898213406191 139.7375046608176 17.199 35.56897997730896 139.73751293762862 17.199 35.56897930810764 139.73752154363797 17.199 35.56898026192908 139.73753080965454 17.199 35.56898310866331 139.73754007341816 17.199 35.56898541061991 139.73754437328478 17.199 35.568978416868454 139.7375910483382 17.199 35.568998160932175 139.73759565842656 17.199 35.56903670207654 139.73760421779656 17.199 35.569109321969655 139.73762067992757 17.199 35.569102328451535 139.73766768601624 17.199 35.569039715803456 139.73765352873616 17.199 35.56902155826198 139.73777402336276 17.199 35.56901818330593 139.73778064676765 17.199 35.56901265131486 139.73779521600926 17.199 35.56900927976703 139.73780614201638 17.199 35.56900618046998 139.73781938448823 17.199 35.5690040281526 139.73783328777495 17.199 35.56900295749851 139.73784718977757 17.199 35.569002833823774 139.73786175259602 17.199 35.56900379076392 139.73787499025343 17.199 35.56900487924221 139.73788359417767 17.199 35.5689954639224 139.73794582766342 17.199 35.569022375311754 139.73795208419457 17.199 35.56901268849366 139.73801299412807 17.199 35.56903621915338 139.73801859274707 17.199 35.5690169855926 139.73814670086628 17.199 35.56914667342359 139.73817600384254 17.199 35.569265948653715 139.73820333339555 17.199 35.569267965805516 139.7381894302332 17.199 35.5692859518791 139.7381937115921 17.199 35.56928971837528 139.73816921528595 17.199 35.56931825224912 139.738175469996 17.199 35.56932363183105 139.73813905681058 17.199 35.569352301175726 139.7381456423236 17.199 35.56935781647951 139.73810989090273 17.199 35.569363766681725 139.73811120774747 17.199 35.56936780148535 139.73808406332572 17.199 35.569377673507276 139.73808636844115 17.199 35.56942771002788 139.73775666154864 17.199</gml:posList>
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
									<xAL:LocalityName Type="Town">東京都大田区大森東四丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">2436.70072</uro:buildingRoofEdgeArea>
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
