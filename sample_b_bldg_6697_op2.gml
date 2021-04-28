<?xml version="1.0" encoding="UTF-8"?>
<core:CityModel xmlns:grp="http://www.opengis.net/citygml/cityobjectgroup/2.0" xmlns:core="http://www.opengis.net/citygml/2.0" xmlns:pbase="http://www.opengis.net/citygml/profiles/base/2.0" xmlns:smil20lang="http://www.w3.org/2001/SMIL20/Language" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:smil20="http://www.w3.org/2001/SMIL20/" xmlns:bldg="http://www.opengis.net/citygml/building/2.0" xmlns:xAL="urn:oasis:names:tc:ciq:xsdschema:xAL:2.0" xmlns:uro="http://www.kantei.go.jp/jp/singi/tiiki/toshisaisei/itoshisaisei/iur/uro/1.4" xmlns:luse="http://www.opengis.net/citygml/landuse/2.0" xmlns:app="http://www.opengis.net/citygml/appearance/2.0" xmlns:gen="http://www.opengis.net/citygml/generics/2.0" xmlns:dem="http://www.opengis.net/citygml/relief/2.0" xmlns:tex="http://www.opengis.net/citygml/texturedsurface/2.0" xmlns:tun="http://www.opengis.net/citygml/tunnel/2.0" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:sch="http://www.ascc.net/xml/schematron" xmlns:veg="http://www.opengis.net/citygml/vegetation/2.0" xmlns:frn="http://www.opengis.net/citygml/cityfurniture/2.0" xmlns:gml="http://www.opengis.net/gml" xmlns:tran="http://www.opengis.net/citygml/transportation/2.0" xmlns:wtr="http://www.opengis.net/citygml/waterbody/2.0" xmlns:brid="http://www.opengis.net/citygml/bridge/2.0" xsi:schemaLocation="http://www.kantei.go.jp/jp/singi/tiiki/toshisaisei/itoshisaisei/iur/uro/1.4 http://www.kantei.go.jp/jp/singi/tiiki/toshisaisei/itoshisaisei/iur/schemas/uro/1.4/urbanObject.xsd http://www.opengis.net/citygml/2.0 http://schemas.opengis.net/citygml/2.0/cityGMLBase.xsd http://www.opengis.net/citygml/landuse/2.0 http://schemas.opengis.net/citygml/landuse/2.0/landUse.xsd http://www.opengis.net/citygml/building/2.0 http://schemas.opengis.net/citygml/building/2.0/building.xsd http://www.opengis.net/citygml/transportation/2.0 http://schemas.opengis.net/citygml/transportation/2.0/transportation.xsd http://www.opengis.net/citygml/generics/2.0 http://schemas.opengis.net/citygml/generics/2.0/generics.xsd http://www.opengis.net/citygml/cityobjectgroup/2.0 http://schemas.opengis.net/citygml/cityobjectgroup/2.0/cityObjectGroup.xsd http://www.opengis.net/gml http://schemas.opengis.net/gml/3.1.1/base/gml.xsd http://www.opengis.net/citygml/appearance/2.0 http://schemas.opengis.net/citygml/appearance/2.0/appearance.xsd">
	<gml:boundedBy>
		<gml:Envelope srsName="http://www.opengis.net/def/crs/EPSG/0/6697" srsDimension="3">
			<gml:lowerCorner>35.56647195757135 139.7241207469562 1.009</gml:lowerCorner>
			<gml:upperCorner>35.57509621366029 139.737560968697 46.259</gml:upperCorner>
		</gml:Envelope>
	</gml:boundedBy>
	<core:cityObjectMember>
		<bldg:Building gml:id="BLD_4c761afc-755e-445c-bcd8-e80e08152724">
			<gen:stringAttribute name="建物ID">
				<gen:value>13111-bldg-61384</gen:value>
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
					<gen:value>1</gen:value>
				</gen:stringAttribute>
				<gen:measureAttribute name="浸水深">
					<gen:value uom="m">0.460</gen:value>
				</gen:measureAttribute>
				<gen:measureAttribute name="継続時間">
					<gen:value uom="hour">0.13</gen:value>
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
					<gen:value uom="m">0.379</gen:value>
				</gen:measureAttribute>
			</gen:genericAttributeSet>
			<bldg:measuredHeight uom="m">16.9</bldg:measuredHeight>
			<bldg:lod0RoofEdge>
				<gml:MultiSurface>
					<gml:surfaceMember>
						<gml:Polygon>
							<gml:exterior>
								<gml:LinearRing>
									<gml:posList>35.57108629650767 139.73146709559785 17.582 35.57109428864688 139.7313247647722 17.582 35.57108887108284 139.73131384935343 17.582 35.5709653392722 139.7312114028242 17.582 35.570930246001495 139.73128326952303 17.582 35.570922940004216 139.73127765216728 17.582 35.5709292807714 139.73126109524026 17.582 35.570931959707245 139.7312313038471 17.582 35.57093016919925 139.731192581726 17.582 35.57092555529879 139.73117272892034 17.582 35.57091201401743 139.73114858474315 17.582 35.57089306889927 139.73112974307858 17.582 35.57087236803812 139.73111322049547 17.582 35.570845319974936 139.73110564237354 17.582 35.57082273826738 139.73110335421887 17.582 35.57080381391276 139.73110900487197 17.582 35.57080205143697 139.73110338049858 17.582 35.57081554501202 139.73107125856416 17.582 35.57062980042639 139.73094903330178 17.582 35.570607254407165 139.7309887791529 17.582 35.57058560987978 139.7309755676279 17.582 35.57056132328071 139.7310355052109 17.582 35.57057932083493 139.73105302407507 17.582 35.57056042820534 139.73109607499254 17.582 35.5706415998955 139.73115124494913 17.582 35.570654296265594 139.73113567292847 17.582 35.57067053048964 139.7311465745412 17.582 35.57068308323285 139.73112107339563 17.582 35.57075072765474 139.7311686481629 17.582 35.57074804032159 139.73118851019265 17.582 35.57074359331789 139.73120605761557 17.582 35.57074172479547 139.73123485498078 17.582 35.57074445137801 139.73126132967775 17.582 35.57075258627652 139.73128779751843 17.582 35.570766123889 139.73130763897439 17.582 35.57078425577109 139.7313241648423 17.582 35.57080765964449 139.73133936012698 17.582 35.570832947468666 139.73134396175894 17.582 35.570857279315916 139.7313373113731 17.582 35.57085268868285 139.73134492967168 17.582 35.570819433944315 139.7313525842944 17.582 35.570752739109544 139.73146851075987 17.582 35.57093052572282 139.73161424688732 17.582 35.570962727575065 139.7316406844118 17.582 35.57098315711033 139.7316562145582 17.582 35.571009208703245 139.7316045490673 17.582 35.570987561451034 139.73158802753474 17.582 35.5709852612383 139.73158604457535 17.582 35.57104033220705 139.73147476627972 17.582 35.57108629650767 139.73146709559785 17.582</gml:posList>
								</gml:LinearRing>
							</gml:exterior>
							<gml:interior>
								<gml:LinearRing>
									<gml:posList>35.570876774080766 139.7312068815304 17.582 35.57087840779193 139.73122011855725 17.582 35.57087706720507 139.73123369033576 17.582 35.57087288612668 139.7312459418073 17.582 35.570866405109136 139.7312565413076 17.582 35.57085775880041 139.73126482671054 17.582 35.57084775788883 139.7312701350333 17.582 35.57083694292673 139.7312721346138 17.582 35.570826124882316 139.73127049344748 17.582 35.570815980075885 139.73126554165526 17.582 35.57080718454725 139.73125727838075 17.582 35.57080041517677 139.73124669569745 17.582 35.5707960781486 139.7312344550442 17.582 35.57079457964705 139.73122121785903 17.582 35.57079592051296 139.73120797707102 17.582 35.57079996637948 139.73119572578088 17.582 35.57080658231937 139.731184795136 17.582 35.570815228622656 139.73117650972972 17.582 35.570825229810644 139.73117153237442 17.582 35.57083604477106 139.73116953278068 17.582 35.570846862816815 139.73117117393363 17.582 35.57085700762706 139.73117612571582 17.582 35.570865802880725 139.7311840580095 17.582 35.57087257225663 139.73119464069606 17.582 35.570876774080766 139.7312068815304 17.582</gml:posList>
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
											<gml:posList>35.57108629650767 139.73146709559785 2.507 35.57104033220705 139.73147476627972 2.507 35.5709852612383 139.73158604457535 2.507 35.570987561451034 139.73158802753474 2.507 35.571009208703245 139.7316045490673 2.507 35.57098315711033 139.7316562145582 2.507 35.570962727575065 139.7316406844118 2.507 35.57093052572282 139.73161424688732 2.507 35.570752739109544 139.73146851075987 2.507 35.570819433944315 139.7313525842944 2.507 35.57085268868285 139.73134492967168 2.507 35.570857279315916 139.7313373113731 2.507 35.570832947468666 139.73134396175894 2.507 35.57080765964449 139.73133936012698 2.507 35.57078425577109 139.7313241648423 2.507 35.570766123889 139.73130763897439 2.507 35.57075258627652 139.73128779751843 2.507 35.57074445137801 139.73126132967775 2.507 35.57074172479547 139.73123485498078 2.507 35.57074359331789 139.73120605761557 2.507 35.57074804032159 139.73118851019265 2.507 35.57075072765474 139.7311686481629 2.507 35.57068308323285 139.73112107339563 2.507 35.57067053048964 139.7311465745412 2.507 35.570654296265594 139.73113567292847 2.507 35.5706415998955 139.73115124494913 2.507 35.57056042820534 139.73109607499254 2.507 35.57057932083493 139.73105302407507 2.507 35.57056132328071 139.7310355052109 2.507 35.57058560987978 139.7309755676279 2.507 35.570607254407165 139.7309887791529 2.507 35.57062980042639 139.73094903330178 2.507 35.57081554501202 139.73107125856416 2.507 35.57080205143697 139.73110338049858 2.507 35.57080381391276 139.73110900487197 2.507 35.57082273826738 139.73110335421887 2.507 35.570845319974936 139.73110564237354 2.507 35.57087236803812 139.73111322049547 2.507 35.57089306889927 139.73112974307858 2.507 35.57091201401743 139.73114858474315 2.507 35.57092555529879 139.73117272892034 2.507 35.57093016919925 139.731192581726 2.507 35.570931959707245 139.7312313038471 2.507 35.5709292807714 139.73126109524026 2.507 35.570922940004216 139.73127765216728 2.507 35.570930246001495 139.73128326952303 2.507 35.5709653392722 139.7312114028242 2.507 35.57108887108284 139.73131384935343 2.507 35.57109428864688 139.7313247647722 2.507 35.57108629650767 139.73146709559785 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
									<gml:interior>
										<gml:LinearRing>
											<gml:posList>35.570876774080766 139.7312068815304 2.507 35.57087257225663 139.73119464069606 2.507 35.570865802880725 139.7311840580095 2.507 35.57085700762706 139.73117612571582 2.507 35.570846862816815 139.73117117393363 2.507 35.57083604477106 139.73116953278068 2.507 35.570825229810644 139.73117153237442 2.507 35.570815228622656 139.73117650972972 2.507 35.57080658231937 139.731184795136 2.507 35.57079996637948 139.73119572578088 2.507 35.57079592051296 139.73120797707102 2.507 35.57079457964705 139.73122121785903 2.507 35.5707960781486 139.7312344550442 2.507 35.57080041517677 139.73124669569745 2.507 35.57080718454725 139.73125727838075 2.507 35.570815980075885 139.73126554165526 2.507 35.570826124882316 139.73127049344748 2.507 35.57083694292673 139.7312721346138 2.507 35.57084775788883 139.7312701350333 2.507 35.57085775880041 139.73126482671054 2.507 35.570866405109136 139.7312565413076 2.507 35.57087288612668 139.7312459418073 2.507 35.57087706720507 139.73123369033576 2.507 35.57087840779193 139.73122011855725 2.507 35.570876774080766 139.7312068815304 2.507</gml:posList>
										</gml:LinearRing>
									</gml:interior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57108629650767 139.73146709559785 2.507 35.57109428864688 139.7313247647722 2.507 35.57109428864688 139.7313247647722 17.582 35.57108629650767 139.73146709559785 17.582 35.57108629650767 139.73146709559785 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57109428864688 139.7313247647722 2.507 35.57108887108284 139.73131384935343 2.507 35.57108887108284 139.73131384935343 17.582 35.57109428864688 139.7313247647722 17.582 35.57109428864688 139.7313247647722 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57108887108284 139.73131384935343 2.507 35.5709653392722 139.7312114028242 2.507 35.5709653392722 139.7312114028242 17.582 35.57108887108284 139.73131384935343 17.582 35.57108887108284 139.73131384935343 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.5709653392722 139.7312114028242 2.507 35.570930246001495 139.73128326952303 2.507 35.570930246001495 139.73128326952303 17.582 35.5709653392722 139.7312114028242 17.582 35.5709653392722 139.7312114028242 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.570930246001495 139.73128326952303 2.507 35.570922940004216 139.73127765216728 2.507 35.570922940004216 139.73127765216728 17.582 35.570930246001495 139.73128326952303 17.582 35.570930246001495 139.73128326952303 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.570922940004216 139.73127765216728 2.507 35.5709292807714 139.73126109524026 2.507 35.5709292807714 139.73126109524026 17.582 35.570922940004216 139.73127765216728 17.582 35.570922940004216 139.73127765216728 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.5709292807714 139.73126109524026 2.507 35.570931959707245 139.7312313038471 2.507 35.570931959707245 139.7312313038471 17.582 35.5709292807714 139.73126109524026 17.582 35.5709292807714 139.73126109524026 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.570931959707245 139.7312313038471 2.507 35.57093016919925 139.731192581726 2.507 35.57093016919925 139.731192581726 17.582 35.570931959707245 139.7312313038471 17.582 35.570931959707245 139.7312313038471 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57093016919925 139.731192581726 2.507 35.57092555529879 139.73117272892034 2.507 35.57092555529879 139.73117272892034 17.582 35.57093016919925 139.731192581726 17.582 35.57093016919925 139.731192581726 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57092555529879 139.73117272892034 2.507 35.57091201401743 139.73114858474315 2.507 35.57091201401743 139.73114858474315 17.582 35.57092555529879 139.73117272892034 17.582 35.57092555529879 139.73117272892034 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57091201401743 139.73114858474315 2.507 35.57089306889927 139.73112974307858 2.507 35.57089306889927 139.73112974307858 17.582 35.57091201401743 139.73114858474315 17.582 35.57091201401743 139.73114858474315 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57089306889927 139.73112974307858 2.507 35.57087236803812 139.73111322049547 2.507 35.57087236803812 139.73111322049547 17.582 35.57089306889927 139.73112974307858 17.582 35.57089306889927 139.73112974307858 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57087236803812 139.73111322049547 2.507 35.570845319974936 139.73110564237354 2.507 35.570845319974936 139.73110564237354 17.582 35.57087236803812 139.73111322049547 17.582 35.57087236803812 139.73111322049547 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.570845319974936 139.73110564237354 2.507 35.57082273826738 139.73110335421887 2.507 35.57082273826738 139.73110335421887 17.582 35.570845319974936 139.73110564237354 17.582 35.570845319974936 139.73110564237354 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57082273826738 139.73110335421887 2.507 35.57080381391276 139.73110900487197 2.507 35.57080381391276 139.73110900487197 17.582 35.57082273826738 139.73110335421887 17.582 35.57082273826738 139.73110335421887 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57080381391276 139.73110900487197 2.507 35.57080205143697 139.73110338049858 2.507 35.57080205143697 139.73110338049858 17.582 35.57080381391276 139.73110900487197 17.582 35.57080381391276 139.73110900487197 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57080205143697 139.73110338049858 2.507 35.57081554501202 139.73107125856416 2.507 35.57081554501202 139.73107125856416 17.582 35.57080205143697 139.73110338049858 17.582 35.57080205143697 139.73110338049858 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57081554501202 139.73107125856416 2.507 35.57062980042639 139.73094903330178 2.507 35.57062980042639 139.73094903330178 17.582 35.57081554501202 139.73107125856416 17.582 35.57081554501202 139.73107125856416 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57062980042639 139.73094903330178 2.507 35.570607254407165 139.7309887791529 2.507 35.570607254407165 139.7309887791529 17.582 35.57062980042639 139.73094903330178 17.582 35.57062980042639 139.73094903330178 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.570607254407165 139.7309887791529 2.507 35.57058560987978 139.7309755676279 2.507 35.57058560987978 139.7309755676279 17.582 35.570607254407165 139.7309887791529 17.582 35.570607254407165 139.7309887791529 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57058560987978 139.7309755676279 2.507 35.57056132328071 139.7310355052109 2.507 35.57056132328071 139.7310355052109 17.582 35.57058560987978 139.7309755676279 17.582 35.57058560987978 139.7309755676279 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57056132328071 139.7310355052109 2.507 35.57057932083493 139.73105302407507 2.507 35.57057932083493 139.73105302407507 17.582 35.57056132328071 139.7310355052109 17.582 35.57056132328071 139.7310355052109 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57057932083493 139.73105302407507 2.507 35.57056042820534 139.73109607499254 2.507 35.57056042820534 139.73109607499254 17.582 35.57057932083493 139.73105302407507 17.582 35.57057932083493 139.73105302407507 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57056042820534 139.73109607499254 2.507 35.5706415998955 139.73115124494913 2.507 35.5706415998955 139.73115124494913 17.582 35.57056042820534 139.73109607499254 17.582 35.57056042820534 139.73109607499254 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.5706415998955 139.73115124494913 2.507 35.570654296265594 139.73113567292847 2.507 35.570654296265594 139.73113567292847 17.582 35.5706415998955 139.73115124494913 17.582 35.5706415998955 139.73115124494913 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.570654296265594 139.73113567292847 2.507 35.57067053048964 139.7311465745412 2.507 35.57067053048964 139.7311465745412 17.582 35.570654296265594 139.73113567292847 17.582 35.570654296265594 139.73113567292847 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57067053048964 139.7311465745412 2.507 35.57068308323285 139.73112107339563 2.507 35.57068308323285 139.73112107339563 17.582 35.57067053048964 139.7311465745412 17.582 35.57067053048964 139.7311465745412 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57068308323285 139.73112107339563 2.507 35.57075072765474 139.7311686481629 2.507 35.57075072765474 139.7311686481629 17.582 35.57068308323285 139.73112107339563 17.582 35.57068308323285 139.73112107339563 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57075072765474 139.7311686481629 2.507 35.57074804032159 139.73118851019265 2.507 35.57074804032159 139.73118851019265 17.582 35.57075072765474 139.7311686481629 17.582 35.57075072765474 139.7311686481629 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57074804032159 139.73118851019265 2.507 35.57074359331789 139.73120605761557 2.507 35.57074359331789 139.73120605761557 17.582 35.57074804032159 139.73118851019265 17.582 35.57074804032159 139.73118851019265 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57074359331789 139.73120605761557 2.507 35.57074172479547 139.73123485498078 2.507 35.57074172479547 139.73123485498078 17.582 35.57074359331789 139.73120605761557 17.582 35.57074359331789 139.73120605761557 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57074172479547 139.73123485498078 2.507 35.57074445137801 139.73126132967775 2.507 35.57074445137801 139.73126132967775 17.582 35.57074172479547 139.73123485498078 17.582 35.57074172479547 139.73123485498078 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57074445137801 139.73126132967775 2.507 35.57075258627652 139.73128779751843 2.507 35.57075258627652 139.73128779751843 17.582 35.57074445137801 139.73126132967775 17.582 35.57074445137801 139.73126132967775 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57075258627652 139.73128779751843 2.507 35.570766123889 139.73130763897439 2.507 35.570766123889 139.73130763897439 17.582 35.57075258627652 139.73128779751843 17.582 35.57075258627652 139.73128779751843 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.570766123889 139.73130763897439 2.507 35.57078425577109 139.7313241648423 2.507 35.57078425577109 139.7313241648423 17.582 35.570766123889 139.73130763897439 17.582 35.570766123889 139.73130763897439 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57078425577109 139.7313241648423 2.507 35.57080765964449 139.73133936012698 2.507 35.57080765964449 139.73133936012698 17.582 35.57078425577109 139.7313241648423 17.582 35.57078425577109 139.7313241648423 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57080765964449 139.73133936012698 2.507 35.570832947468666 139.73134396175894 2.507 35.570832947468666 139.73134396175894 17.582 35.57080765964449 139.73133936012698 17.582 35.57080765964449 139.73133936012698 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.570832947468666 139.73134396175894 2.507 35.570857279315916 139.7313373113731 2.507 35.570857279315916 139.7313373113731 17.582 35.570832947468666 139.73134396175894 17.582 35.570832947468666 139.73134396175894 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.570857279315916 139.7313373113731 2.507 35.57085268868285 139.73134492967168 2.507 35.57085268868285 139.73134492967168 17.582 35.570857279315916 139.7313373113731 17.582 35.570857279315916 139.7313373113731 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57085268868285 139.73134492967168 2.507 35.570819433944315 139.7313525842944 2.507 35.570819433944315 139.7313525842944 17.582 35.57085268868285 139.73134492967168 17.582 35.57085268868285 139.73134492967168 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.570819433944315 139.7313525842944 2.507 35.570752739109544 139.73146851075987 2.507 35.570752739109544 139.73146851075987 17.582 35.570819433944315 139.7313525842944 17.582 35.570819433944315 139.7313525842944 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.570752739109544 139.73146851075987 2.507 35.57093052572282 139.73161424688732 2.507 35.57093052572282 139.73161424688732 17.582 35.570752739109544 139.73146851075987 17.582 35.570752739109544 139.73146851075987 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57093052572282 139.73161424688732 2.507 35.570962727575065 139.7316406844118 2.507 35.570962727575065 139.7316406844118 17.582 35.57093052572282 139.73161424688732 17.582 35.57093052572282 139.73161424688732 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.570962727575065 139.7316406844118 2.507 35.57098315711033 139.7316562145582 2.507 35.57098315711033 139.7316562145582 17.582 35.570962727575065 139.7316406844118 17.582 35.570962727575065 139.7316406844118 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57098315711033 139.7316562145582 2.507 35.571009208703245 139.7316045490673 2.507 35.571009208703245 139.7316045490673 17.582 35.57098315711033 139.7316562145582 17.582 35.57098315711033 139.7316562145582 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.571009208703245 139.7316045490673 2.507 35.570987561451034 139.73158802753474 2.507 35.570987561451034 139.73158802753474 17.582 35.571009208703245 139.7316045490673 17.582 35.571009208703245 139.7316045490673 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.570987561451034 139.73158802753474 2.507 35.5709852612383 139.73158604457535 2.507 35.5709852612383 139.73158604457535 17.582 35.570987561451034 139.73158802753474 17.582 35.570987561451034 139.73158802753474 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.5709852612383 139.73158604457535 2.507 35.57104033220705 139.73147476627972 2.507 35.57104033220705 139.73147476627972 17.582 35.5709852612383 139.73158604457535 17.582 35.5709852612383 139.73158604457535 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57104033220705 139.73147476627972 2.507 35.57108629650767 139.73146709559785 2.507 35.57108629650767 139.73146709559785 17.582 35.57104033220705 139.73147476627972 17.582 35.57104033220705 139.73147476627972 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.570876774080766 139.7312068815304 2.507 35.57087840779193 139.73122011855725 2.507 35.57087840779193 139.73122011855725 17.582 35.570876774080766 139.7312068815304 17.582 35.570876774080766 139.7312068815304 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57087840779193 139.73122011855725 2.507 35.57087706720507 139.73123369033576 2.507 35.57087706720507 139.73123369033576 17.582 35.57087840779193 139.73122011855725 17.582 35.57087840779193 139.73122011855725 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57087706720507 139.73123369033576 2.507 35.57087288612668 139.7312459418073 2.507 35.57087288612668 139.7312459418073 17.582 35.57087706720507 139.73123369033576 17.582 35.57087706720507 139.73123369033576 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57087288612668 139.7312459418073 2.507 35.570866405109136 139.7312565413076 2.507 35.570866405109136 139.7312565413076 17.582 35.57087288612668 139.7312459418073 17.582 35.57087288612668 139.7312459418073 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.570866405109136 139.7312565413076 2.507 35.57085775880041 139.73126482671054 2.507 35.57085775880041 139.73126482671054 17.582 35.570866405109136 139.7312565413076 17.582 35.570866405109136 139.7312565413076 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57085775880041 139.73126482671054 2.507 35.57084775788883 139.7312701350333 2.507 35.57084775788883 139.7312701350333 17.582 35.57085775880041 139.73126482671054 17.582 35.57085775880041 139.73126482671054 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57084775788883 139.7312701350333 2.507 35.57083694292673 139.7312721346138 2.507 35.57083694292673 139.7312721346138 17.582 35.57084775788883 139.7312701350333 17.582 35.57084775788883 139.7312701350333 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57083694292673 139.7312721346138 2.507 35.570826124882316 139.73127049344748 2.507 35.570826124882316 139.73127049344748 17.582 35.57083694292673 139.7312721346138 17.582 35.57083694292673 139.7312721346138 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.570826124882316 139.73127049344748 2.507 35.570815980075885 139.73126554165526 2.507 35.570815980075885 139.73126554165526 17.582 35.570826124882316 139.73127049344748 17.582 35.570826124882316 139.73127049344748 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.570815980075885 139.73126554165526 2.507 35.57080718454725 139.73125727838075 2.507 35.57080718454725 139.73125727838075 17.582 35.570815980075885 139.73126554165526 17.582 35.570815980075885 139.73126554165526 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57080718454725 139.73125727838075 2.507 35.57080041517677 139.73124669569745 2.507 35.57080041517677 139.73124669569745 17.582 35.57080718454725 139.73125727838075 17.582 35.57080718454725 139.73125727838075 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57080041517677 139.73124669569745 2.507 35.5707960781486 139.7312344550442 2.507 35.5707960781486 139.7312344550442 17.582 35.57080041517677 139.73124669569745 17.582 35.57080041517677 139.73124669569745 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.5707960781486 139.7312344550442 2.507 35.57079457964705 139.73122121785903 2.507 35.57079457964705 139.73122121785903 17.582 35.5707960781486 139.7312344550442 17.582 35.5707960781486 139.7312344550442 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57079457964705 139.73122121785903 2.507 35.57079592051296 139.73120797707102 2.507 35.57079592051296 139.73120797707102 17.582 35.57079457964705 139.73122121785903 17.582 35.57079457964705 139.73122121785903 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57079592051296 139.73120797707102 2.507 35.57079996637948 139.73119572578088 2.507 35.57079996637948 139.73119572578088 17.582 35.57079592051296 139.73120797707102 17.582 35.57079592051296 139.73120797707102 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57079996637948 139.73119572578088 2.507 35.57080658231937 139.731184795136 2.507 35.57080658231937 139.731184795136 17.582 35.57079996637948 139.73119572578088 17.582 35.57079996637948 139.73119572578088 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57080658231937 139.731184795136 2.507 35.570815228622656 139.73117650972972 2.507 35.570815228622656 139.73117650972972 17.582 35.57080658231937 139.731184795136 17.582 35.57080658231937 139.731184795136 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.570815228622656 139.73117650972972 2.507 35.570825229810644 139.73117153237442 2.507 35.570825229810644 139.73117153237442 17.582 35.570815228622656 139.73117650972972 17.582 35.570815228622656 139.73117650972972 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.570825229810644 139.73117153237442 2.507 35.57083604477106 139.73116953278068 2.507 35.57083604477106 139.73116953278068 17.582 35.570825229810644 139.73117153237442 17.582 35.570825229810644 139.73117153237442 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57083604477106 139.73116953278068 2.507 35.570846862816815 139.73117117393363 2.507 35.570846862816815 139.73117117393363 17.582 35.57083604477106 139.73116953278068 17.582 35.57083604477106 139.73116953278068 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.570846862816815 139.73117117393363 2.507 35.57085700762706 139.73117612571582 2.507 35.57085700762706 139.73117612571582 17.582 35.570846862816815 139.73117117393363 17.582 35.570846862816815 139.73117117393363 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57085700762706 139.73117612571582 2.507 35.570865802880725 139.7311840580095 2.507 35.570865802880725 139.7311840580095 17.582 35.57085700762706 139.73117612571582 17.582 35.57085700762706 139.73117612571582 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.570865802880725 139.7311840580095 2.507 35.57087257225663 139.73119464069606 2.507 35.57087257225663 139.73119464069606 17.582 35.570865802880725 139.7311840580095 17.582 35.570865802880725 139.7311840580095 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57087257225663 139.73119464069606 2.507 35.570876774080766 139.7312068815304 2.507 35.570876774080766 139.7312068815304 17.582 35.57087257225663 139.73119464069606 17.582 35.57087257225663 139.73119464069606 2.507</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
								</gml:Polygon>
							</gml:surfaceMember>
							<gml:surfaceMember>
								<gml:Polygon>
									<gml:exterior>
										<gml:LinearRing>
											<gml:posList>35.57108629650767 139.73146709559785 17.582 35.57109428864688 139.7313247647722 17.582 35.57108887108284 139.73131384935343 17.582 35.5709653392722 139.7312114028242 17.582 35.570930246001495 139.73128326952303 17.582 35.570922940004216 139.73127765216728 17.582 35.5709292807714 139.73126109524026 17.582 35.570931959707245 139.7312313038471 17.582 35.57093016919925 139.731192581726 17.582 35.57092555529879 139.73117272892034 17.582 35.57091201401743 139.73114858474315 17.582 35.57089306889927 139.73112974307858 17.582 35.57087236803812 139.73111322049547 17.582 35.570845319974936 139.73110564237354 17.582 35.57082273826738 139.73110335421887 17.582 35.57080381391276 139.73110900487197 17.582 35.57080205143697 139.73110338049858 17.582 35.57081554501202 139.73107125856416 17.582 35.57062980042639 139.73094903330178 17.582 35.570607254407165 139.7309887791529 17.582 35.57058560987978 139.7309755676279 17.582 35.57056132328071 139.7310355052109 17.582 35.57057932083493 139.73105302407507 17.582 35.57056042820534 139.73109607499254 17.582 35.5706415998955 139.73115124494913 17.582 35.570654296265594 139.73113567292847 17.582 35.57067053048964 139.7311465745412 17.582 35.57068308323285 139.73112107339563 17.582 35.57075072765474 139.7311686481629 17.582 35.57074804032159 139.73118851019265 17.582 35.57074359331789 139.73120605761557 17.582 35.57074172479547 139.73123485498078 17.582 35.57074445137801 139.73126132967775 17.582 35.57075258627652 139.73128779751843 17.582 35.570766123889 139.73130763897439 17.582 35.57078425577109 139.7313241648423 17.582 35.57080765964449 139.73133936012698 17.582 35.570832947468666 139.73134396175894 17.582 35.570857279315916 139.7313373113731 17.582 35.57085268868285 139.73134492967168 17.582 35.570819433944315 139.7313525842944 17.582 35.570752739109544 139.73146851075987 17.582 35.57093052572282 139.73161424688732 17.582 35.570962727575065 139.7316406844118 17.582 35.57098315711033 139.7316562145582 17.582 35.571009208703245 139.7316045490673 17.582 35.570987561451034 139.73158802753474 17.582 35.5709852612383 139.73158604457535 17.582 35.57104033220705 139.73147476627972 17.582 35.57108629650767 139.73146709559785 17.582</gml:posList>
										</gml:LinearRing>
									</gml:exterior>
									<gml:interior>
										<gml:LinearRing>
											<gml:posList>35.570876774080766 139.7312068815304 17.582 35.57087840779193 139.73122011855725 17.582 35.57087706720507 139.73123369033576 17.582 35.57087288612668 139.7312459418073 17.582 35.570866405109136 139.7312565413076 17.582 35.57085775880041 139.73126482671054 17.582 35.57084775788883 139.7312701350333 17.582 35.57083694292673 139.7312721346138 17.582 35.570826124882316 139.73127049344748 17.582 35.570815980075885 139.73126554165526 17.582 35.57080718454725 139.73125727838075 17.582 35.57080041517677 139.73124669569745 17.582 35.5707960781486 139.7312344550442 17.582 35.57079457964705 139.73122121785903 17.582 35.57079592051296 139.73120797707102 17.582 35.57079996637948 139.73119572578088 17.582 35.57080658231937 139.731184795136 17.582 35.570815228622656 139.73117650972972 17.582 35.570825229810644 139.73117153237442 17.582 35.57083604477106 139.73116953278068 17.582 35.570846862816815 139.73117117393363 17.582 35.57085700762706 139.73117612571582 17.582 35.570865802880725 139.7311840580095 17.582 35.57087257225663 139.73119464069606 17.582 35.570876774080766 139.7312068815304 17.582</gml:posList>
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
									<xAL:LocalityName Type="Town">東京都大田区大森西五丁目</xAL:LocalityName>
								</xAL:Locality>
							</xAL:Country>
						</xAL:AddressDetails>
					</core:xalAddress>
				</core:Address>
			</bldg:address>
			<uro:buildingDetails>
				<uro:BuildingDetails>
					<uro:buildingRoofEdgeArea uom="m2">1417.15238</uro:buildingRoofEdgeArea>
					<uro:districtsAndZonesType codeSpace="../../codelists/Common_districtsAndZonesType.xml">10</uro:districtsAndZonesType>
					<uro:prefecture codeSpace="../../codelists/Common_prefecture.xml">13</uro:prefecture>
					<uro:city codeSpace="../../codelists/Common_localPublicAuthorities.xml">13111</uro:city>
					<uro:surveyYear>2016</uro:surveyYear>
				</uro:BuildingDetails>
			</uro:buildingDetails>
			<uro:extendedAttribute>
				<uro:KeyValuePair>
					<uro:key codeSpace="../../codelists/extendedAttribute_key.xml">106</uro:key>
					<uro:codeValue codeSpace="../../codelists/extendedAttribute_key106.xml">10</uro:codeValue>
				</uro:KeyValuePair>
			</uro:extendedAttribute>
		</bldg:Building>
	</core:cityObjectMember>
</core:CityModel>
