// formatter module - type Plain
package hexlet.code.formatters;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Plain {
    public static String formatter(Map<String, Object> mapCompareResult) {

        StringBuilder result = new StringBuilder();
        Map<String, Object> minusKeys = new LinkedHashMap<>();
        Map<String, Object> plusKeys = new LinkedHashMap<>();
        mapCompareResult.forEach((key, value) -> {
            if (key.charAt(2) == '-') {
                minusKeys.put(key.substring(4, key.length()), mapCompareResult.get(key));
            }
            if (key.charAt(2) == '+') {
                plusKeys.put(key.substring(4, key.length()), mapCompareResult.get(key));
            }
        });

        String clrearFirsKey = "";
        String clrearSecondKey = "";

        for (String key : mapCompareResult.keySet()) {
            clrearFirsKey = key.substring(4, key.length());
            if (!clrearSecondKey.equals(clrearFirsKey)) {
                if (minusKeys.containsKey(clrearFirsKey) && plusKeys.containsKey(clrearFirsKey)) {
                    result.append("Property '" + clrearFirsKey + "' was updated. "
                            + "From " + prepareString(minusKeys.get(clrearFirsKey))
                            + " to " + prepareString(plusKeys.get(clrearFirsKey)) + "\n");
                    clrearSecondKey = clrearFirsKey;    // во избежании повторного сравнения
                }
            }
            if (minusKeys.containsKey(clrearFirsKey) && !plusKeys.containsKey(clrearFirsKey)) {
                result.append("Property '" + clrearFirsKey + "' was removed\n");
            }
            if (!minusKeys.containsKey(clrearFirsKey) && plusKeys.containsKey(clrearFirsKey)) {
                result.append("Property '" + clrearFirsKey + "' was added with value: "
                        + prepareString(plusKeys.get(clrearFirsKey)) + "\n");
            }
        }
        return result.toString();
    }

    static String prepareString(Object o) {

        if (null == o) {
            return "null";
        }
        if (o instanceof String) {
            return String.format("'%s'", o);
        }
        if (o instanceof Collection) {
            return "[complex value]";
        }
        if (o instanceof Map) {
            return "[complex value]";
        }
        if (o.getClass().isArray()) {
            return "[complex value]";
        }
        return String.valueOf(o);
    }
}
