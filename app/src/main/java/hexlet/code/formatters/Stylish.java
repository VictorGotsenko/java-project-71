// formatter module - type Stulish
package hexlet.code.formatters;

import java.util.Map;
import java.util.stream.Collectors;

public class Stylish {

    public static String formatter(Map<String, Object> mapCompareResult) {

        return mapCompareResult.keySet().stream()
                .map(key -> key + ": " + mapCompareResult.get(key))
                .collect(Collectors.joining("\n", "{\n", "\n}"));
    }
}
