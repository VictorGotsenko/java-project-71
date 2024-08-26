package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public class Parser {

    public static Map<String, Object> parsingFile(String rawData, String typeData) throws Exception {

//        ObjectMapper mapper = new ObjectMapper(); // create once, re-use
//        Map<String, Object> result;

        switch (typeData) {
            case "JSON" -> {
                try {
                    return new ObjectMapper().readValue(rawData, new TypeReference<>() {
                    });
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }

            case "YAML" -> {
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

//
//        if (typeData.equalsIgnoreCase("JSON")) {
//            result = mapper.readValue(rawData, new TypeReference<>() {
//            });
//        } else {
//            throw new RuntimeException("Unknown format");
//        }
//        return result;
    }
}
