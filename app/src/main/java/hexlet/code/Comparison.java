package hexlet.code;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Comparison {
    public static List<Map<String, Object>> find(Map<String, Object> data1, Map<String, Object> data2) {
        List<Map<String, Object>> result = new ArrayList<>();

        if (data1.isEmpty() && data2.isEmpty()) {
            return result;
        }

        Set<String> uniqueKeys = new TreeSet<>();
        uniqueKeys.addAll(data1.keySet());
        uniqueKeys.addAll(data2.keySet());

        Object valueFromData1;
        Object valueFromData2;
        Map<String, Object> mapExplainsKey;
        for (String key : uniqueKeys) {
            valueFromData1 = data1.get(key);
            valueFromData2 = data2.get(key);
            mapExplainsKey = new LinkedHashMap<>();
            mapExplainsKey.put("key", key);
            if (!data1.containsKey(key)) {
                mapExplainsKey.put("status", "ADDED");
                mapExplainsKey.put("value", valueFromData2);
                result.add(mapExplainsKey);
            } else if (!data2.containsKey(key)) {
                mapExplainsKey.put("status", "DELETED");
                mapExplainsKey.put("value", valueFromData1);
                result.add(mapExplainsKey);
            } else if (Objects.equals(valueFromData1, valueFromData2)) {
                mapExplainsKey.put("status", "UNCHANGED");
                mapExplainsKey.put("value", valueFromData1);
                result.add(mapExplainsKey);
            } else {
                mapExplainsKey.put("status", "CHANGED");
                mapExplainsKey.put("value1", valueFromData1);
                mapExplainsKey.put("value2", valueFromData2);
                result.add(mapExplainsKey);
            }
        }
        return result;
    }
}
