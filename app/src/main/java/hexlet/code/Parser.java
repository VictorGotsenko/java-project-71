package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.TreeMap;

public class Parser {

    public static Map<String, Object> parsingFile(String rawData, String typeData) throws Exception {

        Map<String, Object> result = new TreeMap<>();
        ObjectMapper mapper = new ObjectMapper(); // create once, reuse
        if (typeData.equalsIgnoreCase("JSON")) {
            result = mapper.readValue(rawData, new TypeReference<>() {
            });
        } else {
            throw new RuntimeException("Unknown format");
        }
        return result;
    }
}
