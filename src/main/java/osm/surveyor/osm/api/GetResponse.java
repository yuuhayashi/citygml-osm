package osm.surveyor.osm.api;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class GetResponse {
	int code;
	String message;
	Document body;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Document getBody() {
		return body;
	}
	public void setBody(Document body) {
		this.body = body;
	}
	
	public GetResponse printout() throws IOException, ParserConfigurationException, SAXException {
        System.out.println("レスポンスコード[" + getCode() + "] " +
                "レスポンスメッセージ[" + getMessage() + "]");
        System.out.println("\n---- ボディ ----");
        export();
		
		return this;
	}
	
    public void export() throws ParserConfigurationException {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
	        transformer.setOutputProperty("indent", "yes"); //改行指定
	        transformer.setOutputProperty("encoding", "UTF-8"); // エンコーディング
            transformer.transform(new DOMSource(getBody()), new StreamResult(System.out));
		}
		catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		catch (TransformerException e) {
			e.printStackTrace();
		}
    }

}
