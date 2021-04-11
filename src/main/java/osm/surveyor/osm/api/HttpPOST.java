package osm.surveyor.osm.api;

import java.net.*;
import java.io.*;

/**
 * Java HTTP クライアントサンプル - HttpURLConnection 版 -
 *
 * @author 68user http://X68000.q-e-d.net/~68user/
 */
public class HttpPOST {
	//public static String host = "http://api06.dev.openstreetmap.org";
	//public static String host = "http://api.openstreetmap.org";
	public static String host = "http://overpass-api.de";
	
	public static void main(String[] args) throws MalformedURLException, ProtocolException, IOException {
		double minlat = 35.13d;
		double maxlat = 35.66d;
        double minlon = 138.99d;
		double maxlon = 139.79d;
        getCapabilities(new File("."), minlat, maxlat, minlon, maxlon);
    }
	
	public static void getCapabilities(File oFile, double minLat, double maxLat, double minLon, double maxLon) throws MalformedURLException, ProtocolException, IOException {
		System.out.println(host + "/api/interpreter");
        URL url = new URL(host + "/api/interpreter");

        HttpURLConnection urlconn = (HttpURLConnection)url.openConnection();
        urlconn.setRequestMethod("POST");
        urlconn.setDoOutput(true);              		// POSTのデータを後ろに付ける
        urlconn.setInstanceFollowRedirects(false);		// 勝手にリダイレクトさせない
        urlconn.setRequestProperty("Accept-Language", "ja;q=0.7,en;q=0.3");
        urlconn.setRequestProperty("Content-Type","text/xml;charset=utf-8");
        urlconn.connect();

        // 送信
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(urlconn.getOutputStream(), "utf-8")));
        pw.print("<osm-script timeout=\"900\" element-limit=\"1073741824\">");
        pw.print("<union>");
        pw.print("<query type=\"node\">");
        pw.print("<has-kv k=\"highway\" v=\"bus_stop\"/>");
        pw.print("<bbox-query s=\"" + minLat + "\" n=\"" + maxLat + "\" w=\"" + minLon + "\" e=\"" + maxLon + "\"/>");
        pw.print("</query>");
        pw.print("<query type=\"node\">");
        pw.print("<has-kv k=\"public_transport\" v=\"platform\"/>");
        pw.print("<has-kv k=\"bus\" v=\"yes\"/>");
        pw.print("<bbox-query s=\"" + minLat + "\" n=\"" + maxLat + "\" w=\"" + minLon + "\" e=\"" + maxLon + "\"/>");
        pw.print("</query>");
        pw.print("</union>");
        pw.print("<print/>");
        pw.print("</osm-script>");
        pw.close();       		// closeで送信完了
        
        System.out.println("レスポンスコード[" + urlconn.getResponseCode() + "] " +
                           "レスポンスメッセージ[" + urlconn.getResponseMessage() + "]");
        System.out.println("\n---- ボディ ----");

        BufferedReader reader = new BufferedReader(new InputStreamReader(urlconn.getInputStream(), "UTF-8"));

		BufferedWriter hw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(oFile), "UTF-8"));
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
    		hw.write(line);
    		hw.newLine();
        }
        hw.close();
        reader.close();
        urlconn.disconnect();
	}
}
