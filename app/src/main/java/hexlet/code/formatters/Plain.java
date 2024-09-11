package hexlet.code.formatters;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Plain {
    public static String formatter(List<Map<String, Object>> compareResult) {
        StringBuilder result = new StringBuilder();
        for (Map mapExplainsKey : compareResult) {
            result.append(convertMap2String(mapExplainsKey));
        }
        return result.toString().trim();
    }

    private static String convertMap2String(Map<String, Object> mapExplainsKey) {
        String result = "";
        String keyStatus = (String) mapExplainsKey.get("status");
        switch (keyStatus) {
            case "ADDED" -> {
                result = String.format("Property '%s' was added with value: %s\n", mapExplainsKey.get("key"),
                                                            convertObjToString(mapExplainsKey.get("value")));
            }
            case "DELETED" -> {
                result = String.format("Property '%s' was removed\n", mapExplainsKey.get("key"));
            }
            case "CHANGED" -> {
                result = String.format("Property '%s' was updated. From %s to %s\n", mapExplainsKey.get("key"),
                                                             convertObjToString(mapExplainsKey.get("value1")),
                                                             convertObjToString(mapExplainsKey.get("value2")));

            }
            default -> {
                result = "";
            }
        }
        return result;
    }

    static String convertObjToString(Object o) {
        if (null == o) {
            return "null";
        }
        if (o instanceof String) {
            return String.format("'%s'", o);
        }
        if ((o instanceof Collection)
            || (o instanceof Map)
            || (o.getClass().isArray())) {
            return "[complex value]";
        }
        return String.valueOf(o);
    }
}
