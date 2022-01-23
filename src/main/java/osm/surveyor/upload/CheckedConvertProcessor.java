package osm.surveyor.upload;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import osm.surveyor.osm.BodyMap;
import osm.surveyor.osm.NdBean;
import osm.surveyor.osm.NodeBean;
import osm.surveyor.osm.OsmBean;
import osm.surveyor.osm.TagBean;
import osm.surveyor.osm.WayBean;

public class CheckedConvertProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		BodyMap map = exchange.getIn().getBody(BodyMap.class);
		OsmBean osm = (OsmBean) map.get("osm");		// dom

		// POI `action=delete` を削除する
		removeActionDelete(osm);
		osm.gerbageNode();
		
		// POI `MLIT_PLATEAU:fixme=delete *` を`action=delete`に変換する
		convertToActionDelete(osm);
		
		// タグ `MLIT_PLATEAU:fixme=*` を除去する
		removeFixmeTag(osm);
		
		map.put("release", osm);
	}
	
	/**
	 * POI `action=delete` を削除する
	 * @throws Exception
	 */
	private void removeActionDelete(OsmBean osm) throws Exception {
		List<WayBean> work = new ArrayList<>();
		for (WayBean way : osm.getWayList()) {
			String action = way.getAction();
			if (action != null && action.equals("delete")) {
				work.add(way);
			}
		}
		for (WayBean way : work) {
			for (NdBean nd : way.getNdList()) {
				NodeBean node = osm.getNode(nd.getRef());
				node.setAction("delete");
				osm.putNode(node);
			}
			osm.removeWay(way);
		}
	}
	
	/**
	 * POI `MLIT_PLATEAU:fixme=delete *` を`action=delete`に変換する
	 * @param poi
	 */
	private void convertToActionDelete(OsmBean osm) {
		for (WayBean way : osm.getWayList()) {
			TagBean fixme = way.getTag("MLIT_PLATEAU:fixme");
			if (fixme != null) {
				String v = fixme.getValue();
				if (v != null && v.startsWith("delete")) {
					way.setAction("delete");
				}
			}
		}
	}
	
	/**
	 * タグ `MLIT_PLATEAU:fixme=*` を除去する
	 * @throws Exception
	 */
	private void removeFixmeTag(OsmBean osm) throws Exception {
		for (WayBean way : osm.getWayList()) {
			TagBean fixme = way.getTag("MLIT_PLATEAU:fixme");
			if (fixme != null) {
				way.removeTag("MLIT_PLATEAU:fixme");
			}
		}
	}
}
