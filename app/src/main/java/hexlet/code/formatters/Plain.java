package hexlet.code.formatters;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Plain {
    public static String formatter(List<Map<String, Object>> compareResult) {
        StringBuilder result = new StringBuilder();
        for (Map mapDescribeKey : compareResult) {
            result.append(convertMap2String(mapDescribeKey));
        }
        return result.toString().trim();
    }

    private static String convertMap2String(Map<String, Object> mapDescribeKey) {
        String result = "";
        String keyStatus = (String) mapDescribeKey.get("status");
        switch (keyStatus) {
            case "ADDED" -> {
                result = "Property " + "'" + mapDescribeKey.get("key") + "'"
                        + " was added with value: " + convertObjToString(mapDescribeKey.get("value")) + "\n";
            }
            case "CHANGED" -> {
                result = "Property " + "'" + mapDescribeKey.get("key") + "'" + " was updated."
                        + " From " + convertObjToString(mapDescribeKey.get("value1"))
                        + " to " + convertObjToString(mapDescribeKey.get("value2")) + "\n";
            }
            case "UNCHANGED" -> {
                result = "";
            }
            case "DELETED" -> {
                result = "Property " + "'" + mapDescribeKey.get("key") + "'" + " was removed" + "\n";
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
