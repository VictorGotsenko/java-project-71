package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public class Parser {

    public static Map<String, Object> parsingFile(String rawData, String typeData) throws Exception {
        switch (typeData.toUpperCase()) {
            case "JSON" -> {
                try {
                    return new ObjectMapper().readValue(rawData, new TypeReference<>() {
                    });
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            case "YML" -> {
                try {
                    return new ObjectMapper(new YAMLFactory()).readValue(rawData, new TypeReference<>() { });
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            default -> {
                throw new RuntimeException("Unknown format: " + typeData);
            }
        }
    }

}
