package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {

    public static String formatter(List<Map<String, Object>> compareResult) {
        StringBuilder result = new StringBuilder("{\n");
        for (Map mapDescribeKey : compareResult) {
            result.append(convertMap2String(mapDescribeKey) + "\n");
        }
        result.append("}");
        return result.toString();
    }

    private static String convertMap2String(Map<String, Object> mapDescribeKey) {
        String result = "";
        String keyStatus = (String) mapDescribeKey.get("status");
        switch (keyStatus) {
            case "ADDED" -> {
                result = "  + " + mapDescribeKey.get("key") + ": " + (mapDescribeKey.get("value"));
            }
            case "CHANGED" -> {
                result = "  - " + mapDescribeKey.get("key") + ": " + mapDescribeKey.get("value1") + "\n"
                       + "  + " + mapDescribeKey.get("key") + ": " + mapDescribeKey.get("value2");
            }
            case "UNCHANGED" -> {
                result = "    " + mapDescribeKey.get("key") + ": " + (mapDescribeKey.get("value"));
            }
            case "DELETED" -> {
                result = "  - " + mapDescribeKey.get("key") + ": " + (mapDescribeKey.get("value"));
            }
            default -> {
                result = "";
            }
        }
        return result;
    }
}
