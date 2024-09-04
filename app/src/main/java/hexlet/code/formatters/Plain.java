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
        final int startPositionTextLine = 4;
        mapCompareResult.forEach((key, value) -> {
            if (key.charAt(2) == '-') {
                minusKeys.put(key.substring(startPositionTextLine, key.length()),
                                            mapCompareResult.get(key));
            }
            if (key.charAt(2) == '+') {
                plusKeys.put(key.substring(startPositionTextLine, key.length()),
                                           mapCompareResult.get(key));
            }
        });

        String clrearFirsKey = "";
//        String clrearSecondKey = "";
        boolean isKeyAlreadyTakenIntoResult = false;

        for (String key : mapCompareResult.keySet()) {
            clrearFirsKey = key.substring(startPositionTextLine, key.length());
//            if (!clrearSecondKey.equals(clrearFirsKey)) {
            if (!isKeyAlreadyTakenIntoResult) {
                if (minusKeys.containsKey(clrearFirsKey) && plusKeys.containsKey(clrearFirsKey)) {
                    result.append("Property '" + clrearFirsKey + "' was updated. "
                            + "From " + convertObjToString(minusKeys.get(clrearFirsKey))
                            + " to " + convertObjToString(plusKeys.get(clrearFirsKey)) + "\n");
//                    clrearSecondKey = clrearFirsKey;    // во избежании повторного сравнения
                    isKeyAlreadyTakenIntoResult = true;
                    continue;
                }
            }
            if (minusKeys.containsKey(clrearFirsKey) && !plusKeys.containsKey(clrearFirsKey)) {
                result.append("Property '" + clrearFirsKey + "' was removed\n");
            }
            if (!minusKeys.containsKey(clrearFirsKey) && plusKeys.containsKey(clrearFirsKey)) {
                result.append("Property '" + clrearFirsKey + "' was added with value: "
                        + convertObjToString(plusKeys.get(clrearFirsKey)) + "\n");
            }
            isKeyAlreadyTakenIntoResult = false;
        }
        return result.deleteCharAt(result.length() - 1).toString();
    }

    static String convertObjToString(Object o) {
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
