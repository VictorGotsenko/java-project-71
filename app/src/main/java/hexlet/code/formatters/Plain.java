package hexlet.code.formatters;

import java.util.Map;
import java.util.stream.Collectors;

public class Plain {
    public static String formatter(Map<String, Object> diffMap) {


        int a = 1;

        return diffMap.keySet().stream()
                .map(key -> key + ": " + diffMap.get(key))
                .collect(Collectors.joining("\n", "{\n", "\n}\n"));
    }
}
