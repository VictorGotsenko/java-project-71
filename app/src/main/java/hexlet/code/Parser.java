package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public class Parser {

    public static Map<String, Object> parsingFile(String rawData, String typeData) throws Exception {
        switch (typeData.toUpperCase()) {
            case "JSON" -> {
                return new ObjectMapper().readValue(rawData, new TypeReference<>() { });
            }
            case "YAML", "YML" -> {
                return new ObjectMapper(new YAMLFactory()).readValue(rawData, new TypeReference<>() { });
            }
            default -> {
                throw new RuntimeException("Unknown format: " + typeData);
            }
        }
    }
}
