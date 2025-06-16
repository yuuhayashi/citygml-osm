package osm.surveyor.upload;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import osm.surveyor.gis.point.NdModel;
import osm.surveyor.osm.BodyMap;
import osm.surveyor.osm.NodeBean;
import osm.surveyor.osm.OsmBean;
import osm.surveyor.osm.TagBean;
import osm.surveyor.osm.way.WayModel;

public class CheckedConvertProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		BodyMap map = exchange.getIn().getBody(BodyMap.class);
		OsmBean osm = (OsmBean) map.get("osm");		// dom

		// (1) POI `action=delete` を削除する
		removeActionDelete(osm);
		osm.gerbageNode();
		
		// (2-1) `MLIT_PLATEAU:fixme="更新前です"` がついたWAYに紐づくNODEをaction="delete" に変換する
		// (2-2) MLIT_PLATEAU:fixme="更新前です"がついたWAYを除去する。
		convertNodeToActionDelete(osm);
		
		// (3) POI `MLIT_PLATEAU:fixme=delete *` を`action=delete`に変換する
		convertToActionDelete(osm);
		
		// (4) タグ `MLIT_PLATEAU:fixme=*` を除去する
		removeFixmeTag(osm);
		
		// (5) タグ `ref:MLIT_PLATEAU=*` を`source`に移行する
		moveRefTag(osm);
		
		map.put("release", osm);
	}
	
	/**
	 * POI `action=delete` を削除する
	 * @throws Exception
	 */
	private void removeActionDelete(OsmBean osm) throws Exception {
		List<WayModel> work = new ArrayList<>();
		for (WayModel way : osm.getWays()) {
			String action = way.getPoiBean().getAction();
			if (action != null && action.equals("delete")) {
				work.add(way);
			}
		}
		for (WayModel way : work) {
			osm.removeWay(way);
		}
		
		List<NodeBean> list = new ArrayList<>();
		for (NodeBean node : osm.getNodeList()) {
			String action = node.getAction();
			if (action != null && action.equals("delete")) {
				list.add(node);
			}
		}
		for (NodeBean node : list) {
			osm.removeNode(node);
		}
	}
	
	/**
	 * POI `MLIT_PLATEAU:fixme="更新前です"`に紐づくNODEをaction="delete" に変換する
	 * POI `MLIT_PLATEAU:fixme="更新前です"`がついたWAYを除去する。
	 * @param poi
	 */
	private void convertNodeToActionDelete(OsmBean osm) {
		List<WayModel> removeList = new ArrayList<>();
		for (WayModel way : osm.getWays()) {
			TagBean fixme = way.getTag("MLIT_PLATEAU:fixme");
			if (fixme != null) {
				String v = fixme.getValue();
				if (v != null && v.startsWith("更新前です")) {
					for (NdModel nd : way.getNdList()) {
						
						// `MLIT_PLATEAU:fixme="更新前です"`に紐づくNODEをaction="delete" に変換する
						NodeBean node = osm.getNode(nd.getRef());
						if (node != null) {
							node.setAction("delete");
						}
					}
					
					// 除去対象に追加する。
					removeList.add(way);
				}
			}
		}
		
		// `MLIT_PLATEAU:fixme="更新前です"`がついたWAYを除去する。
		for (WayModel way : removeList) {
			osm.removeWay(way);
		}
	}
	
	/**
	 * POI `MLIT_PLATEAU:fixme=delete *` を`action=delete`に変換する
	 * @param poi
	 */
	private void convertToActionDelete(OsmBean osm) {
		for (WayModel way : osm.getWays()) {
			TagBean fixme = way.getTag("MLIT_PLATEAU:fixme");
			if (fixme != null) {
				String v = fixme.getValue();
				if (v != null && v.startsWith("delete")) {
					for (NdModel nd : way.getNdList()) {
						NodeBean node = osm.getNode(nd.getRef());
						if (node != null) {
							node.setAction("delete");
						}
					}
					way.getPoiBean().setAction("delete");
				}
			}
		}
	}
	
	/**
	 * タグ `MLIT_PLATEAU:fixme=*` を除去する
	 * @throws Exception
	 */
	private void removeFixmeTag(OsmBean osm) throws Exception {
		for (WayModel way : osm.getWays()) {
			TagBean fixme = way.getTag("MLIT_PLATEAU:fixme");
			if (fixme != null) {
				way.getPoiBean().removeTag("MLIT_PLATEAU:fixme");
			}
		}
	}
	
	
	/**
	 * タグ `ref:MLIT_PLATEAU=*` を`source`に移行する
	 * @throws Exception
	 */
	private void moveRefTag(OsmBean osm) throws Exception {
		for (WayModel way : osm.getWays()) {
			TagBean refTag = way.getTag("ref:MLIT_PLATEAU");
			if (refTag != null) {
				String ref = refTag.getValue();
				way.getPoiBean().removeTag("ref:MLIT_PLATEAU");
				TagBean sourceTag = way.getTag("source");
				if (sourceTag != null) {
					String source = sourceTag.getValue();
					if ((source != null) && !source.isEmpty()) {
						sourceTag.setValue(String.format("%s; MLIT_PLATEAU %s",  source, ref));
					}
					else {
						sourceTag.setValue(String.format("MLIT_PLATEAU %s", ref));
					}
				}
				else {
					way.getPoiBean().addTag("source", String.format("MLIT_PLATEAU %s", ref));
				}
			}
		}
	}
}
