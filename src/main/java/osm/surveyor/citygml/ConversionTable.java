package osm.surveyor.citygml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class ConversionTable {

	public static String fileName = "conversion.json";
	
	public static void main(String[] args) {
		
		ConversionTable table = new ConversionTable(Paths.get(ConversionTable.fileName).toFile());
		if (table.version != null) {
			System.out.println(LocalTime.now() +"version: "+ table.version + ",\t");
		}
		for (Usage usage : table.usageList) {
			System.out.print(usage.code + ",\t");
			System.out.print(usage.name + ",\t");
			System.out.print(usage.building + "\n");
		}
	}
	
	/**
	 * コンストラクタ
	 * @param file	JSON source file.
	 */
	public ConversionTable(File file) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String strLine;
			StringBuilder sbSentence = new StringBuilder();
			while ((strLine = br.readLine()) != null) {
				sbSentence.append(strLine);
			}
			
			parseUsage(sbSentence);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String version = null;
	public ArrayList<Usage> usageList = new ArrayList<>();
	
	void parseUsage(StringBuilder sbSentence) {
		JSONObject jsonObj = new JSONObject(sbSentence.toString());
		version = jsonObj.getString("version");
		JSONArray usageList = jsonObj.getJSONArray("usage");
		for (int i = 0; i < usageList.length(); i++) {
			Usage usage = new Usage(usageList.getJSONObject(i));
			this.usageList.add(usage);
		}
	}
	
	public String getUsageBuilding(String code) {
		for (Usage usage : usageList) {
			if (usage.code.equals(code)) {
				return usage.building;
			}
		}
		return "yes";
	}
	
	public class Usage {
		public String code = null;
		public String name = null;
		public String building = null;
		
		Usage(JSONObject usage) {
			this.code = (String) usage.get("code");
			this.name = (String) usage.get("name");
			this.building = (String) usage.get("building");
		}
	}
}
