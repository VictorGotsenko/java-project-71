package hexlet.code;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Comparison {
    public static List<Map<String, Object>> find(Map<String, Object> mapFromFile1, Map<String, Object> mapFromFile2) {
        List<Map<String, Object>> result = new ArrayList<>();

        if (mapFromFile1.isEmpty() && mapFromFile2.isEmpty()) {
            return result;
        }

        Set<String> uniqueKeys = new TreeSet<>();
        uniqueKeys.addAll(mapFromFile1.keySet());
        uniqueKeys.addAll(mapFromFile2.keySet());

        for (String key : uniqueKeys ) {
            Map<String, Object> mapDescribeKey = new LinkedHashMap<>();
            mapDescribeKey.put("key", key);
            if (!mapFromFile1.containsKey(key)) {
                mapDescribeKey.put("status", "ADDED");
                mapDescribeKey.put("value", mapFromFile2.get(key));
                result.add(mapDescribeKey);
                continue;
            }
            if (mapFromFile1.containsKey(key) && !mapFromFile2.containsKey(key)) {
                mapDescribeKey.put("status", "DELETED");
                mapDescribeKey.put("value", mapFromFile1.get(key));
                result.add(mapDescribeKey);
                continue;
            }

            if (Objects.equals(mapFromFile1.get(key), mapFromFile2.get(key))) {
                mapDescribeKey.put("status", "UNCHANGED");
                mapDescribeKey.put("value", mapFromFile1.get(key));
                result.add(mapDescribeKey);
            } else {
                mapDescribeKey.put("status", "CHANGED");
                mapDescribeKey.put("value1", mapFromFile1.get(key));
                mapDescribeKey.put("value2", mapFromFile2.get(key));
                result.add(mapDescribeKey);
            }
        };
        return result;
    }
}
