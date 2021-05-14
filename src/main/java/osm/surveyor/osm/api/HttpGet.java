package osm.surveyor.osm.api;

import java.net.*;

import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Java HTTP クライアントサンプル - HttpURLConnection 版 -
 *
 * @author 68user http://X68000.q-e-d.net/~68user/
 */
public class HttpGet {
	//public static String host = "http://api06.dev.openstreetmap.org";
	//public static String host = "http://api.openstreetmap.org";
	public static String host = "https://www.openstreetmap.org";
	
	public static void main(String[] args) throws MalformedURLException, ProtocolException, IOException, ParserConfigurationException, SAXException {
		HttpGet obj = new HttpGet();
		GetResponse res = obj.getCapabilities();
		res.printout();
        
        /*
		<node id="-4131" timestamp="2011-01-21T16:47:41Z" lat="35.4350644157973" lon="139.423684433498">
			<tag k="name" v="あやせ荘"/>
		</node>
		<node id="-4152" timestamp="2011-01-21T16:47:41Z" lat="35.4341675801122" lon="139.418362759267">
			<tag k="name" v="武者奇橋"/>
		</node>
		<node id="-4155" timestamp="2011-01-21T16:47:41Z" lat="35.4369651010672" lon="139.426400070915">
			<tag k="name" v="綾瀬市役所"/>
		</node>
        double minlon = 139.4197591d;
		double maxlon = 139.4279939d;
		double minlat = 35.4320438d;
		double maxlat = 35.4375923d;
		HttpGET.getMap(minlon, minlat, maxlon, maxlat);
        
		HttpGET.getMap(35.4350644157973d, 139.423684433498d, 50);	// あやせ荘
		HttpGET.getMap(35.4341675801122d, 139.418362759267d, 50);	// 武者奇橋
		HttpGET.getMap(35.4369651010672d, 139.426400070915d, 50);	// 綾瀬市役所
         */
    }
	
	public GetResponse getVersions() throws IOException, ParserConfigurationException, SAXException {
		String urlstr = host + "/api/versions";
        System.out.println("URL: " + urlstr);
        URL url = new URL(urlstr);

        HttpURLConnection urlconn = (HttpURLConnection)url.openConnection();
        try {
            urlconn.setRequestMethod("GET");
            urlconn.setInstanceFollowRedirects(false);
            urlconn.setRequestProperty("Accept-Language", "ja;q=0.7,en;q=0.3");
            urlconn.connect();
            
    		GetResponse res = new GetResponse();
            res.setCode(urlconn.getResponseCode());
    		res.setMessage(urlconn.getResponseMessage());
    		res.setBody(parse(urlconn.getInputStream()));
            return res;
        }
        finally {
            urlconn.disconnect();
        }
	}
	
	public GetResponse getCapabilities() throws IOException, ParserConfigurationException, SAXException {
		String urlstr = host + "/api/capabilities";
        System.out.println("URL: " + urlstr);
        URL url = new URL(urlstr);

        HttpURLConnection urlconn = (HttpURLConnection)url.openConnection();
        try {
            urlconn.setRequestMethod("GET");
            urlconn.setInstanceFollowRedirects(false);
            urlconn.setRequestProperty("Accept-Language", "ja;q=0.7,en;q=0.3");
            urlconn.connect();
            
    		GetResponse res = new GetResponse();
            res.setCode(urlconn.getResponseCode());
    		res.setMessage(urlconn.getResponseMessage());
    		res.setBody(parse(urlconn.getInputStream()));
            return res;
        }
        finally {
            urlconn.disconnect();
        }
	}


	public static final double BIG_Y = (6378137.0d + 6356752.314d) / 2d;	// 地球の平均半径（ｍ）
	public static final double LAT1 = Math.PI * 2d * BIG_Y / 360d;			// 緯度１度の距離（ｍ）
	
	public GetResponse getMap(double lat, double lon, int m) throws MalformedURLException, ProtocolException, IOException, ParserConfigurationException, SAXException {
		double dLat = m / LAT1;						// 距離を表す緯度（差分）
		double minlat = lat - dLat;		// 底辺（緯度）
		double maxlat = lat + dLat;		// 上辺（緯度）
		
		double y = Math.cos(lat / 180.0d * Math.PI) * BIG_Y;	// 緯線上の地球の半径
		double lon1 = (y * 2.0d * Math.PI) / 360;			// 経度１度の距離（ｍ）
		double dLon = m / lon1;						// 距離を表す経度（差分）
		double minlon = lon - dLon;		// 左辺
		double maxlon = lon + dLon;		// 右辺

		System.out.println("緯線上の地球の半径= "+ y);
		System.out.println("緯度1秒の長さ（ｍ）= "+ LAT1 / 3600);
		System.out.println("経度1秒の長さ（ｍ）= "+ lon1 / 3600);

		return getMap(minlon, minlat, maxlon, maxlat);
	}
	
	public GetResponse getMap(RectArea center) throws MalformedURLException, ProtocolException, IOException, ParserConfigurationException, SAXException {
		return getMap(center.minlon, center.minlat, center.maxlon, center.maxlat);
	}
	
	public GetResponse getMap(double minlon, double minlat, double maxlon, double maxlat) throws MalformedURLException, ProtocolException, IOException, ParserConfigurationException, SAXException {
		String urlstr = host + "/api/0.6/map" + "?bbox="+ Double.toString(minlon) +","+ Double.toString(minlat) +","+ Double.toString(maxlon) +","+ Double.toString(maxlat);
        System.out.println("URL: " + urlstr);
        URL url = new URL(urlstr);
        
        HttpURLConnection urlconn = (HttpURLConnection)url.openConnection();
        try {
            urlconn.setRequestMethod("GET");
            urlconn.setInstanceFollowRedirects(false);
            urlconn.setRequestProperty("Accept-Language", "ja;q=0.7,en;q=0.3");
            urlconn.connect();

    		GetResponse res = new GetResponse();
            res.setCode(urlconn.getResponseCode());
    		res.setMessage(urlconn.getResponseMessage());
    		Document doc = parse(urlconn.getInputStream());
    		res.setBody(doc);
            return res;
        }
        finally {
            urlconn.disconnect();
        }
	}

	Document parse(InputStream is) throws IOException, ParserConfigurationException, SAXException {
		DocumentBuilder builder = DocumentBuilderFactory
				.newInstance()
				.newDocumentBuilder();
		return builder.parse(is);
	}
	
	public void sendCMD(String api) throws MalformedURLException, ProtocolException, IOException {
		System.out.println(host + api);
        URL url = new URL(host + api);

        HttpURLConnection urlconn = (HttpURLConnection)url.openConnection();
        urlconn.setRequestMethod("GET");
        urlconn.setInstanceFollowRedirects(false);
        urlconn.setRequestProperty("Accept-Language", "ja;q=0.7,en;q=0.3");
        urlconn.connect();

        System.out.println("レスポンスコード[" + urlconn.getResponseCode() + "] " +
                           "レスポンスメッセージ[" + urlconn.getResponseMessage() + "]");
        System.out.println("\n---- ボディ ----");

        BufferedReader reader = new BufferedReader(new InputStreamReader(urlconn.getInputStream()));

        while (true){
            String line = reader.readLine();
            if ( line == null ){
                break;
            }
            System.out.println(line);
        }

        reader.close();
        urlconn.disconnect();
    }
	
	public static int counter;
	public static void checkNodes(Node node) {
		if (isBusstop(node)) {
			counter++;
			showNode(node);
		}
		
		NodeList nodes = node.getChildNodes();
		for (int i=0; i<nodes.getLength(); i++) {
			Node node2 = nodes.item(i);
			checkNodes(node2);
		}
	}
	
	/**
	 * [バス停]か？
	 * @param node
	 * @throws IOException
	 * @throws SQLException
	 */
	public static boolean isBusstop(Node node) {
		NodeList nodes = node.getChildNodes();
		for (int i=0; i < nodes.getLength(); i++) {
			Node node2 = nodes.item(i);
			if (isBusstopTag(node2)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * [bus_stop]タグか？
	 * @param node
	 * @return
	 */
	private static boolean isBusstopTag(Node node) {
		if (node.getNodeName().equals("tag")) {
			boolean highway = false;
			boolean bus_stop = false;
			boolean public_transport = false;
			boolean plathome = false;
			boolean stop_position = false;
			NamedNodeMap nodeMap = node.getAttributes();
			if ( null != nodeMap ) {
				for (int j=0; j < nodeMap.getLength(); j++ ) {
					String key = nodeMap.item(j).getNodeName();
					String value = nodeMap.item(j).getNodeValue();
					if (key.equals("k") && value.equals("highway")){
						highway = true;
					}
					if (key.equals("k") && value.equals("public_transport")){
						public_transport = true;
					}
					if (key.equals("v") && value.equals("bus_stop")){
						bus_stop = true;
					}
					if (key.equals("v") && value.equals("plathome")){
						plathome = true;
					}
					if (key.equals("v") && value.equals("stop_position")){
						stop_position = true;
					}
				}
				if (highway && bus_stop) {
					return true;
				}
				if (public_transport && (plathome || stop_position)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 指定されたノードを再帰的に表示する。
	 * 
	 * @param node
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void showNode(Node node) {
		System.out.print("<"+ node.getNodeName());
		NamedNodeMap nodeMap = node.getAttributes();
		if ( null != nodeMap ) {
			for ( int j=0; j < nodeMap.getLength(); j++ ) {
				System.out.print(" "+ nodeMap.item(j).getNodeName() +"=\""+ nodeMap.item(j).getNodeValue() +"\"");
			}
		}

		System.out.print(">"+ node.getNodeValue());
		
		NodeList nodes = node.getChildNodes();
		for (int i=0; i<nodes.getLength(); i++) {
			showNode(nodes.item(i));
		}
		System.out.println("</"+ node.getNodeName() +">");
	}
	
	class RectArea {
        public double minlon;
        public double maxlon;
        public double minlat;
        public double maxlat;

        /**
         * 矩形領域を中心点と中心点からの距離（メートル）でセットする
         * 
         * @param lat	// 中心点の緯度
         * @param lon	// 中心点の経度
         * @param m		// 距離　領域の一辺の長さの半分
         */
		public RectArea(double lat, double lon, int m) {
			double BIG_Y = (40000000.0d / 2.0d / Math.PI);		// 地球の半径
			double LAT1 = (10000000.0d / 90.0d);		// 緯度１度の距離（ｍ）
			double dLat = m / LAT1;						// 距離を表す緯度（差分）
			minlat = lat - dLat;		// 底辺（緯度）
			maxlat = lat + dLat;		// 上辺（緯度）
			double y = Math.sin((90.0d - lat) / 180.0d) * BIG_Y;	// 緯線上の地球の半径
			double lon1 = y * 2.0d * Math.PI;			// 経度１度の距離（ｍ）
			double dLon = m / lon1;						// 距離を表す経度（差分）
			minlon = lon - dLon;		// 左辺
			maxlon = lon + dLon;		// 右辺
		}
	}
}
