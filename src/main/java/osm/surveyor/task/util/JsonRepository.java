package osm.surveyor.task.util;

import com.fasterxml.jackson.databind.JsonNode;

public interface JsonRepository {

	public String toString();
	
	public void parse(JsonNode node);
}
