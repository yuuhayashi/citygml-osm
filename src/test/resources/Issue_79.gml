<?xml version='1.0' encoding='utf-8'?>
<core:CityModel xmlns:xal="urn:oasis:names:tc:ciq:xsdschema:xAL:2.0" xmlns:gml="http://www.opengis.net/gml" xmlns:wtr="http://www.opengis.net/citygml/waterbody/2.0" xmlns:app="http://www.opengis.net/citygml/appearance/2.0" xmlns:tex="http://www.opengis.net/citygml/texturedsurface/2.0" xmlns="http://www.opengis.net/citygml/2.0" xmlns:veg="http://www.opengis.net/citygml/vegetation/2.0" xmlns:tran="http://www.opengis.net/citygml/transportation/2.0" xmlns:dem="http://www.opengis.net/citygml/relief/2.0" xmlns:bldg="http://www.opengis.net/citygml/building/2.0" xmlns:tun="http://www.opengis.net/citygml/tunnel/2.0" xmlns:frn="http://www.opengis.net/citygml/cityfurniture/2.0" xmlns:gen="http://www.opengis.net/citygml/generics/2.0" xmlns:brid="http://www.opengis.net/citygml/bridge/2.0" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:luse="http://www.opengis.net/citygml/landuse/2.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:core="http://www.opengis.net/citygml/2.0" xmlns:grp="http://www.opengis.net/citygml/cityobjectgroup/2.0" xmlns:uro="http://www.kantei.go.jp/jp/singi/tiiki/toshisaisei/itoshisaisei/iur/uro/1.4" xmlns:urf="http://www.kantei.go.jp/jp/singi/tiiki/toshisaisei/itoshisaisei/iur/urf/1.4" xmlns:xs="http://www.w3.org/2001/XMLSchema" xsi:schemaLocation="http://www.opengis.net/citygml/waterbody/2.0 http://schemas.opengis.net/citygml/waterbody/2.0/waterBody.xsd http://www.opengis.net/citygml/appearance/2.0 http://schemas.opengis.net/citygml/appearance/2.0/appearance.xsd http://www.opengis.net/citygml/texturedsurface/2.0 http://schemas.opengis.net/citygml/texturedsurface/2.0/texturedSurface.xsd http://www.opengis.net/citygml/vegetation/2.0 http://schemas.opengis.net/citygml/vegetation/2.0/vegetation.xsd http://www.opengis.net/citygml/transportation/2.0 http://schemas.opengis.net/citygml/transportation/2.0/transportation.xsd http://www.opengis.net/citygml/relief/2.0 http://schemas.opengis.net/citygml/relief/2.0/relief.xsd http://www.opengis.net/citygml/building/2.0 http://schemas.opengis.net/citygml/building/2.0/building.xsd http://www.opengis.net/citygml/cityobjectgroup/2.0 http://schemas.opengis.net/citygml/cityobjectgroup/2.0/cityObjectGroup.xsd http://www.opengis.net/citygml/tunnel/2.0 http://schemas.opengis.net/citygml/tunnel/2.0/tunnel.xsd http://www.opengis.net/citygml/cityfurniture/2.0 http://schemas.opengis.net/citygml/cityfurniture/2.0/cityFurniture.xsd http://www.opengis.net/citygml/generics/2.0 http://schemas.opengis.net/citygml/generics/2.0/generics.xsd http://www.opengis.net/citygml/bridge/2.0 http://schemas.opengis.net/citygml/bridge/2.0/bridge.xsd http://www.opengis.net/citygml/landuse/2.0 http://schemas.opengis.net/citygml/landuse/2.0/landUse.xsd http://www.kantei.go.jp/jp/singi/tiiki/toshisaisei/itoshisaisei/iur/uro/1.4 http://www.kantei.go.jp/jp/singi/tiiki/toshisaisei/itoshisaisei/iur/schemas/uro/1.4/urbanObject.xsd http://www.kantei.go.jp/jp/singi/tiiki/toshisaisei/itoshisaisei/iur/urf/1.4 http://www.kantei.go.jp/jp/singi/tiiki/toshisaisei/itoshisaisei/iur/schemas/urf/1.4/urbanFunction.xsd">
  <gml:boundedBy>
    <gml:Envelope srsDimension="3" srsName="http://www.opengis.net/def/crs/EPSG/0/6697">
      <gml:lowerCorner>36.2249771525870 139.5124381264420 16.8899899999990</gml:lowerCorner>
      <gml:upperCorner>36.2335123237290 139.5250815366990 37.2000107629020</gml:upperCorner>
    </gml:Envelope>
  </gml:boundedBy>
  <core:cityObjectMember>
    <bldg:Building gml:id="BLD_a733197e-7c0b-4b05-b951-d21b8f22c904">
      <gen:stringAttribute name="建物ID">
        <gen:value>10207-bldg-45841</gen:value>
      </gen:stringAttribute>
      <gen:intAttribute name="枝番">
        <gen:value>1</gen:value>
      </gen:intAttribute>
      <gen:genericAttributeSet name="利根川水系渡良瀬川洪水浸水想定区域（想定最大規模）">
        <gen:stringAttribute name="規模">
          <gen:value>L2</gen:value>
        </gen:stringAttribute>
        <gen:stringAttribute name="浸水ランク">
          <gen:value>1</gen:value>
        </gen:stringAttribute>
        <gen:measureAttribute name="浸水深">
          <gen:value uom="m">0.2</gen:value>
        </gen:measureAttribute>
      </gen:genericAttributeSet>
      <gen:genericAttributeSet name="利根川水系利根川洪水浸水想定区域（計画規模）">
        <gen:stringAttribute name="規模">
          <gen:value>L1</gen:value>
        </gen:stringAttribute>
        <gen:stringAttribute name="浸水ランク">
          <gen:value>3</gen:value>
        </gen:stringAttribute>
        <gen:measureAttribute name="浸水深">
          <gen:value uom="m">3.192</gen:value>
        </gen:measureAttribute>
      </gen:genericAttributeSet>
      <gen:genericAttributeSet name="利根川水系利根川洪水浸水想定区域（想定最大規模）">
        <gen:stringAttribute name="規模">
          <gen:value>L2</gen:value>
        </gen:stringAttribute>
        <gen:stringAttribute name="浸水ランク">
          <gen:value>3</gen:value>
        </gen:stringAttribute>
        <gen:measureAttribute name="浸水深">
          <gen:value uom="m">4.549</gen:value>
        </gen:measureAttribute>
      </gen:genericAttributeSet>
      <bldg:usage codeSpace="../../codelists/Building_usage.xml">402</bldg:usage>
      <bldg:measuredHeight uom="m">9.7</bldg:measuredHeight>
      <bldg:lod0FootPrint>
        <gml:MultiSurface srsDimension="3" srsName="http://www.opengis.net/def/crs/EPSG/0/6697">
          <gml:surfaceMember>
            <gml:Polygon>
              <gml:exterior>
                <gml:LinearRing>
                  <gml:posList>36.22942341347024 139.52018621656882 18.720000000001164 36.229607204501875 139.52026235294733 18.720000000001164 36.229739502678115 139.5197764700725 18.720000000001164 36.22976994373876 139.51966477124 18.720000000001164 36.22960275389624 139.51959546687797 18.720000000001164 36.229423226473365 139.51959874259137 18.720000000001164 36.229421887101765 139.5196035313939 18.720000000001164 36.229421352169744 139.5196057583922 18.720000000001164 36.22941554929295 139.5196269177546 18.720000000001164 36.2293749472589 139.5196100597415 18.720000000001164 36.229249613583846 139.52007066073506 18.720000000001164 36.22929003500849 139.5200874084375 18.720000000001164 36.22927941203888 139.52012649705512 18.720000000001164 36.22942287183135 139.52018588499908 18.720000000001164 36.22942341347024 139.52018621656882 18.720000000001164</gml:posList>
                </gml:LinearRing>
              </gml:exterior>
            </gml:Polygon>
          </gml:surfaceMember>
        </gml:MultiSurface>
      </bldg:lod0FootPrint>
      <bldg:lod1Solid>
        <gml:Solid srsDimension="3" srsName="http://www.opengis.net/def/crs/EPSG/0/6697">
          <gml:exterior>
            <gml:CompositeSurface>
              <gml:surfaceMember>
                <gml:Polygon>
                  <gml:exterior>
                    <gml:LinearRing>
                      <gml:posList>36.22942341347024 139.52018621656882 18.720000000001164 36.22942287183135 139.52018588499908 18.720000000001164 36.22927941203888 139.52012649705512 18.720000000001164 36.22929003500849 139.5200874084375 18.720000000001164 36.229249613583846 139.52007066073506 18.720000000001164 36.2293749472589 139.5196100597415 18.720000000001164 36.22941554929295 139.5196269177546 18.720000000001164 36.229421352169744 139.5196057583922 18.720000000001164 36.229421887101765 139.5196035313939 18.720000000001164 36.229423226473365 139.51959874259137 18.720000000001164 36.22960275389624 139.51959546687797 18.720000000001164 36.22976994373876 139.51966477124 18.720000000001164 36.229739502678115 139.5197764700725 18.720000000001164 36.229607204501875 139.52026235294733 18.720000000001164 36.22942341347024 139.52018621656882 18.720000000001164</gml:posList>
                    </gml:LinearRing>
                  </gml:exterior>
                </gml:Polygon>
              </gml:surfaceMember>
              <gml:surfaceMember>
                <gml:Polygon>
                  <gml:exterior>
                    <gml:LinearRing>
                      <gml:posList>36.22942341347024 139.52018621656882 18.720000000001164 36.229607204501875 139.52026235294733 18.720000000001164 36.229607204501875 139.52026235294733 26.200000762901162 36.22942341347024 139.52018621656882 26.200000762901162 36.22942341347024 139.52018621656882 18.720000000001164</gml:posList>
                    </gml:LinearRing>
                  </gml:exterior>
                </gml:Polygon>
              </gml:surfaceMember>
              <gml:surfaceMember>
                <gml:Polygon>
                  <gml:exterior>
                    <gml:LinearRing>
                      <gml:posList>36.229607204501875 139.52026235294733 18.720000000001164 36.229739502678115 139.5197764700725 18.720000000001164 36.229739502678115 139.5197764700725 26.200000762901162 36.229607204501875 139.52026235294733 26.200000762901162 36.229607204501875 139.52026235294733 18.720000000001164</gml:posList>
                    </gml:LinearRing>
                  </gml:exterior>
                </gml:Polygon>
              </gml:surfaceMember>
              <gml:surfaceMember>
                <gml:Polygon>
                  <gml:exterior>
                    <gml:LinearRing>
                      <gml:posList>36.229739502678115 139.5197764700725 18.720000000001164 36.22976994373876 139.51966477124 18.720000000001164 36.22976994373876 139.51966477124 26.200000762901162 36.229739502678115 139.5197764700725 26.200000762901162 36.229739502678115 139.5197764700725 18.720000000001164</gml:posList>
                    </gml:LinearRing>
                  </gml:exterior>
                </gml:Polygon>
              </gml:surfaceMember>
              <gml:surfaceMember>
                <gml:Polygon>
                  <gml:exterior>
                    <gml:LinearRing>
                      <gml:posList>36.22976994373876 139.51966477124 18.720000000001164 36.22960275389624 139.51959546687797 18.720000000001164 36.22960275389624 139.51959546687797 26.200000762901162 36.22976994373876 139.51966477124 26.200000762901162 36.22976994373876 139.51966477124 18.720000000001164</gml:posList>
                    </gml:LinearRing>
                  </gml:exterior>
                </gml:Polygon>
              </gml:surfaceMember>
              <gml:surfaceMember>
                <gml:Polygon>
                  <gml:exterior>
                    <gml:LinearRing>
                      <gml:posList>36.22960275389624 139.51959546687797 18.720000000001164 36.229423226473365 139.51959874259137 18.720000000001164 36.229423226473365 139.51959874259137 26.200000762901162 36.22960275389624 139.51959546687797 26.200000762901162 36.22960275389624 139.51959546687797 18.720000000001164</gml:posList>
                    </gml:LinearRing>
                  </gml:exterior>
                </gml:Polygon>
              </gml:surfaceMember>
              <gml:surfaceMember>
                <gml:Polygon>
                  <gml:exterior>
                    <gml:LinearRing>
                      <gml:posList>36.229423226473365 139.51959874259137 18.720000000001164 36.229421887101765 139.5196035313939 18.720000000001164 36.229421887101765 139.5196035313939 26.200000762901162 36.229423226473365 139.51959874259137 26.200000762901162 36.229423226473365 139.51959874259137 18.720000000001164</gml:posList>
                    </gml:LinearRing>
                  </gml:exterior>
                </gml:Polygon>
              </gml:surfaceMember>
              <gml:surfaceMember>
                <gml:Polygon>
                  <gml:exterior>
                    <gml:LinearRing>
                      <gml:posList>36.229421887101765 139.5196035313939 18.720000000001164 36.229421352169744 139.5196057583922 18.720000000001164 36.229421352169744 139.5196057583922 26.200000762901162 36.229421887101765 139.5196035313939 26.200000762901162 36.229421887101765 139.5196035313939 18.720000000001164</gml:posList>
                    </gml:LinearRing>
                  </gml:exterior>
                </gml:Polygon>
              </gml:surfaceMember>
              <gml:surfaceMember>
                <gml:Polygon>
                  <gml:exterior>
                    <gml:LinearRing>
                      <gml:posList>36.229421352169744 139.5196057583922 18.720000000001164 36.22941554929295 139.5196269177546 18.720000000001164 36.22941554929295 139.5196269177546 26.200000762901162 36.229421352169744 139.5196057583922 26.200000762901162 36.229421352169744 139.5196057583922 18.720000000001164</gml:posList>
                    </gml:LinearRing>
                  </gml:exterior>
                </gml:Polygon>
              </gml:surfaceMember>
              <gml:surfaceMember>
                <gml:Polygon>
                  <gml:exterior>
                    <gml:LinearRing>
                      <gml:posList>36.22941554929295 139.5196269177546 18.720000000001164 36.2293749472589 139.5196100597415 18.720000000001164 36.2293749472589 139.5196100597415 26.200000762901162 36.22941554929295 139.5196269177546 26.200000762901162 36.22941554929295 139.5196269177546 18.720000000001164</gml:posList>
                    </gml:LinearRing>
                  </gml:exterior>
                </gml:Polygon>
              </gml:surfaceMember>
              <gml:surfaceMember>
                <gml:Polygon>
                  <gml:exterior>
                    <gml:LinearRing>
                      <gml:posList>36.2293749472589 139.5196100597415 18.720000000001164 36.229249613583846 139.52007066073506 18.720000000001164 36.229249613583846 139.52007066073506 26.200000762901162 36.2293749472589 139.5196100597415 26.200000762901162 36.2293749472589 139.5196100597415 18.720000000001164</gml:posList>
                    </gml:LinearRing>
                  </gml:exterior>
                </gml:Polygon>
              </gml:surfaceMember>
              <gml:surfaceMember>
                <gml:Polygon>
                  <gml:exterior>
                    <gml:LinearRing>
                      <gml:posList>36.229249613583846 139.52007066073506 18.720000000001164 36.22929003500849 139.5200874084375 18.720000000001164 36.22929003500849 139.5200874084375 26.200000762901162 36.229249613583846 139.52007066073506 26.200000762901162 36.229249613583846 139.52007066073506 18.720000000001164</gml:posList>
                    </gml:LinearRing>
                  </gml:exterior>
                </gml:Polygon>
              </gml:surfaceMember>
              <gml:surfaceMember>
                <gml:Polygon>
                  <gml:exterior>
                    <gml:LinearRing>
                      <gml:posList>36.22929003500849 139.5200874084375 18.720000000001164 36.22927941203888 139.52012649705512 18.720000000001164 36.22927941203888 139.52012649705512 26.200000762901162 36.22929003500849 139.5200874084375 26.200000762901162 36.22929003500849 139.5200874084375 18.720000000001164</gml:posList>
                    </gml:LinearRing>
                  </gml:exterior>
                </gml:Polygon>
              </gml:surfaceMember>
              <gml:surfaceMember>
                <gml:Polygon>
                  <gml:exterior>
                    <gml:LinearRing>
                      <gml:posList>36.22927941203888 139.52012649705512 18.720000000001164 36.22942287183135 139.52018588499908 18.720000000001164 36.22942287183135 139.52018588499908 26.200000762901162 36.22927941203888 139.52012649705512 26.200000762901162 36.22927941203888 139.52012649705512 18.720000000001164</gml:posList>
                    </gml:LinearRing>
                  </gml:exterior>
                </gml:Polygon>
              </gml:surfaceMember>
              <gml:surfaceMember>
                <gml:Polygon>
                  <gml:exterior>
                    <gml:LinearRing>
                      <gml:posList>36.22942287183135 139.52018588499908 18.720000000001164 36.22942341347024 139.52018621656882 18.720000000001164 36.22942341347024 139.52018621656882 26.200000762901162 36.22942287183135 139.52018588499908 26.200000762901162 36.22942287183135 139.52018588499908 18.720000000001164</gml:posList>
                    </gml:LinearRing>
                  </gml:exterior>
                </gml:Polygon>
              </gml:surfaceMember>
              <gml:surfaceMember>
                <gml:Polygon>
                  <gml:exterior>
                    <gml:LinearRing>
                      <gml:posList>36.22942341347024 139.52018621656882 26.200000762901162 36.229607204501875 139.52026235294733 26.200000762901162 36.229739502678115 139.5197764700725 26.200000762901162 36.22976994373876 139.51966477124 26.200000762901162 36.22960275389624 139.51959546687797 26.200000762901162 36.229423226473365 139.51959874259137 26.200000762901162 36.229421887101765 139.5196035313939 26.200000762901162 36.229421352169744 139.5196057583922 26.200000762901162 36.22941554929295 139.5196269177546 26.200000762901162 36.2293749472589 139.5196100597415 26.200000762901162 36.229249613583846 139.52007066073506 26.200000762901162 36.22929003500849 139.5200874084375 26.200000762901162 36.22927941203888 139.52012649705512 26.200000762901162 36.22942287183135 139.52018588499908 26.200000762901162 36.22942341347024 139.52018621656882 26.200000762901162</gml:posList>
                    </gml:LinearRing>
                  </gml:exterior>
                </gml:Polygon>
              </gml:surfaceMember>
            </gml:CompositeSurface>
          </gml:exterior>
        </gml:Solid>
      </bldg:lod1Solid>
      <uro:buildingDetails>
        <uro:BuildingDetails>
          <uro:prefecture codeSpace="../../codelists/Common_prefecture.xml">10</uro:prefecture>
          <uro:city codeSpace="../../codelists/Common_localPublicAuthorities.xml">10207</uro:city>
          <uro:surveyYear>2016</uro:surveyYear>
        </uro:BuildingDetails>
      </uro:buildingDetails>
      <uro:extendedAttribute>
        <uro:KeyValuePair>
          <uro:key codeSpace="../../codelists/extendedAttribute_key.xml">2</uro:key>
          <uro:codeValue codeSpace="../../codelists/extendedAttribute_key2.xml">2</uro:codeValue>
        </uro:KeyValuePair>
      </uro:extendedAttribute>
      <uro:extendedAttribute>
        <uro:KeyValuePair>
          <uro:key codeSpace="../../codelists/extendedAttribute_key.xml">5</uro:key>
          <uro:codeValue codeSpace="../../codelists/extendedAttribute_key5.xml">2</uro:codeValue>
        </uro:KeyValuePair>
      </uro:extendedAttribute>
    </bldg:Building>
  </core:cityObjectMember>
  <core:cityObjectMember>
    <bldg:Building gml:id="BLD_daf59d49-2c98-4f30-ba52-a684873be69f">
      <gen:stringAttribute name="建物ID">
        <gen:value>10207-bldg-63561</gen:value>
      </gen:stringAttribute>
      <gen:intAttribute name="枝番">
        <gen:value>1</gen:value>
      </gen:intAttribute>
      <gen:genericAttributeSet name="利根川水系渡良瀬川洪水浸水想定区域（想定最大規模）">
        <gen:stringAttribute name="規模">
          <gen:value>L2</gen:value>
        </gen:stringAttribute>
      </gen:genericAttributeSet>
      <gen:genericAttributeSet name="利根川水系利根川洪水浸水想定区域（計画規模）">
        <gen:stringAttribute name="規模">
          <gen:value>L1</gen:value>
        </gen:stringAttribute>
        <gen:stringAttribute name="浸水ランク">
          <gen:value>2</gen:value>
        </gen:stringAttribute>
        <gen:measureAttribute name="浸水深">
          <gen:value uom="m">2.392</gen:value>
        </gen:measureAttribute>
      </gen:genericAttributeSet>
      <gen:genericAttributeSet name="利根川水系利根川洪水浸水想定区域（想定最大規模）">
        <gen:stringAttribute name="規模">
          <gen:value>L2</gen:value>
        </gen:stringAttribute>
        <gen:stringAttribute name="浸水ランク">
          <gen:value>3</gen:value>
        </gen:stringAttribute>
        <gen:measureAttribute name="浸水深">
          <gen:value uom="m">3.75</gen:value>
        </gen:measureAttribute>
      </gen:genericAttributeSet>
      <bldg:measuredHeight uom="m">4.9</bldg:measuredHeight>
      <bldg:lod0FootPrint>
        <gml:MultiSurface srsDimension="3" srsName="http://www.opengis.net/def/crs/EPSG/0/6697">
          <gml:surfaceMember>
            <gml:Polygon>
              <gml:exterior>
                <gml:LinearRing>
                  <gml:posList>36.229739502678115 139.5197764700725 19.35000000000582 36.22975637519125 139.51978352230094 19.35000000000582 36.2297868162583 139.51967182344708 19.35000000000582 36.22976994373876 139.51966477124 19.35000000000582 36.229739502678115 139.5197764700725 19.35000000000582</gml:posList>
                </gml:LinearRing>
              </gml:exterior>
            </gml:Polygon>
          </gml:surfaceMember>
        </gml:MultiSurface>
      </bldg:lod0FootPrint>
      <bldg:lod1Solid>
        <gml:Solid srsDimension="3" srsName="http://www.opengis.net/def/crs/EPSG/0/6697">
          <gml:exterior>
            <gml:CompositeSurface>
              <gml:surfaceMember>
                <gml:Polygon>
                  <gml:exterior>
                    <gml:LinearRing>
                      <gml:posList>36.229739502678115 139.5197764700725 19.35000000000582 36.22976994373876 139.51966477124 19.35000000000582 36.2297868162583 139.51967182344708 19.35000000000582 36.22975637519125 139.51978352230094 19.35000000000582 36.229739502678115 139.5197764700725 19.35000000000582</gml:posList>
                    </gml:LinearRing>
                  </gml:exterior>
                </gml:Polygon>
              </gml:surfaceMember>
              <gml:surfaceMember>
                <gml:Polygon>
                  <gml:exterior>
                    <gml:LinearRing>
                      <gml:posList>36.229739502678115 139.5197764700725 19.35000000000582 36.22975637519125 139.51978352230094 19.35000000000582 36.22975637519125 139.51978352230094 22.35000000000582 36.229739502678115 139.5197764700725 22.35000000000582 36.229739502678115 139.5197764700725 19.35000000000582</gml:posList>
                    </gml:LinearRing>
                  </gml:exterior>
                </gml:Polygon>
              </gml:surfaceMember>
              <gml:surfaceMember>
                <gml:Polygon>
                  <gml:exterior>
                    <gml:LinearRing>
                      <gml:posList>36.22975637519125 139.51978352230094 19.35000000000582 36.2297868162583 139.51967182344708 19.35000000000582 36.2297868162583 139.51967182344708 22.35000000000582 36.22975637519125 139.51978352230094 22.35000000000582 36.22975637519125 139.51978352230094 19.35000000000582</gml:posList>
                    </gml:LinearRing>
                  </gml:exterior>
                </gml:Polygon>
              </gml:surfaceMember>
              <gml:surfaceMember>
                <gml:Polygon>
                  <gml:exterior>
                    <gml:LinearRing>
                      <gml:posList>36.2297868162583 139.51967182344708 19.35000000000582 36.22976994373876 139.51966477124 19.35000000000582 36.22976994373876 139.51966477124 22.35000000000582 36.2297868162583 139.51967182344708 22.35000000000582 36.2297868162583 139.51967182344708 19.35000000000582</gml:posList>
                    </gml:LinearRing>
                  </gml:exterior>
                </gml:Polygon>
              </gml:surfaceMember>
              <gml:surfaceMember>
                <gml:Polygon>
                  <gml:exterior>
                    <gml:LinearRing>
                      <gml:posList>36.22976994373876 139.51966477124 19.35000000000582 36.229739502678115 139.5197764700725 19.35000000000582 36.229739502678115 139.5197764700725 22.35000000000582 36.22976994373876 139.51966477124 22.35000000000582 36.22976994373876 139.51966477124 19.35000000000582</gml:posList>
                    </gml:LinearRing>
                  </gml:exterior>
                </gml:Polygon>
              </gml:surfaceMember>
              <gml:surfaceMember>
                <gml:Polygon>
                  <gml:exterior>
                    <gml:LinearRing>
                      <gml:posList>36.229739502678115 139.5197764700725 22.35000000000582 36.22975637519125 139.51978352230094 22.35000000000582 36.2297868162583 139.51967182344708 22.35000000000582 36.22976994373876 139.51966477124 22.35000000000582 36.229739502678115 139.5197764700725 22.35000000000582</gml:posList>
                    </gml:LinearRing>
                  </gml:exterior>
                </gml:Polygon>
              </gml:surfaceMember>
            </gml:CompositeSurface>
          </gml:exterior>
        </gml:Solid>
      </bldg:lod1Solid>
      <uro:buildingDetails>
        <uro:BuildingDetails>
          <uro:prefecture codeSpace="../../codelists/Common_prefecture.xml">10</uro:prefecture>
          <uro:city codeSpace="../../codelists/Common_localPublicAuthorities.xml">10207</uro:city>
        </uro:BuildingDetails>
      </uro:buildingDetails>
      <uro:extendedAttribute>
        <uro:KeyValuePair>
          <uro:key codeSpace="../../codelists/extendedAttribute_key.xml">2</uro:key>
          <uro:codeValue codeSpace="../../codelists/extendedAttribute_key2.xml">2</uro:codeValue>
        </uro:KeyValuePair>
      </uro:extendedAttribute>
    </bldg:Building>
  </core:cityObjectMember>
</core:CityModel>
