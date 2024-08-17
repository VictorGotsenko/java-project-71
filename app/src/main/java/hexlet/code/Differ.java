// Logical module

package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static hexlet.code.Parser.parsingFile;

public class Differ {
    public static String generate(String filePath1, String filePath2, String outFormat) throws Exception {
        String dataFile1 = readDataFromFile(filePath1);
        String dataFile2 = readDataFromFile(filePath2);
        Map<String, Object> mapFromFile1 = parsingFile(dataFile1, outFormat);
        Map<String, Object> mapFromFile2 = parsingFile(dataFile2, outFormat);

        return genDiff(mapFromFile1, mapFromFile2);
    }


    public static String readDataFromFile(String pathFile) throws Exception {
        Path absolutePathFile = Paths.get(pathFile).toAbsolutePath().normalize();
        if (Files.notExists(absolutePathFile)) {
            throw new Exception("File " + pathFile + " does not exist");
        }
// Читаем файл
        return Files.readString(absolutePathFile);
    }
// Вычисляю разницу двух словарей (структур )
    public static String genDiff(Map<String, Object> dict01, Map<String, Object> dict02) {
        String result = "";
        if (dict01.isEmpty() && dict02.isEmpty()) {
            return result;
        }
// Получить уникальное множество ключей из всех словарей
        Set<String> uniqKeys = new TreeSet<>();
        uniqKeys.addAll(dict01.keySet());
        uniqKeys.addAll(dict02.keySet());
//        Search diff
        Map<String, Object> diffMap = new LinkedHashMap<>();
        uniqKeys.forEach(unqKey -> {
            if (!dict01.containsKey(unqKey)) {
                diffMap.put("+ " + unqKey, dict02.get(unqKey));
            }
            if (dict01.containsKey(unqKey) && dict02.containsKey(unqKey)) {
                if (dict01.get(unqKey).equals(dict02.get(unqKey))) {
                    diffMap.put("  " + unqKey, dict01.get(unqKey));
                } else {
                    diffMap.put("- " + unqKey, dict01.get(unqKey));
                    diffMap.put("+ " + unqKey, dict02.get(unqKey));
                }
            }
            if (dict01.containsKey(unqKey) && !dict02.containsKey(unqKey)) {
                diffMap.put("- " + unqKey, dict01.get(unqKey));
            }
        });
//        Convert Map -> String
        result = diffMap.keySet().stream()
                .map(key -> key + ": " + diffMap.get(key))
                .collect(Collectors.joining(", \n", "{\n", "\n}"));


/*
        for (Map.Entry<String, Object> m01 : dict01.entrySet()) {
            String forModifyKey;
            String keyDict1 = m01.getKey();
            var valueDict1 = m01.getValue();
//            проверить есть ли ключ keyDict1 в словаре_2 ?
            if (dict02copy.containsKey(keyDict1)) {
//            да, вытащить значение в словаре_2 по keyDict1 и сравнить со значением valueDict1
                for (Map.Entry<String, Object> d02 : dict02.entrySet()) {
                    if (d02.getKey().equalsIgnoreCase(keyDict1)) {
//                        если да -> добавить в результ -> ключ_1 == unchanged
                        if (d02.getValue().equals(valueDict1)) {
//                            result.put(keyDict1, "unchanged");
//                            result.put(keyDict1, valueDict1.toString());
                            forModifyKey = "  " + keyDict1;
                            result.put(forModifyKey, valueDict1.toString());
                        } else {Set<String> set = new TreeSet<>();
//                      нет -> добавить в результ -> ключ_1 == changed
//                            result.put(keyDict1, "changed");
//                            result.put(keyDict1, valueDict1.toString() + "-");
                            forModifyKey = "- " + keyDict1;
                            result.put(forModifyKey, valueDict1.toString());
                            forModifyKey = "+ " + keyDict1;// d02.getKey();
                            result.put(forModifyKey, d02.getValue().toString());
                        }
                        dict02copy.remove(keyDict1);
                    }
                }
            } else {
//                нет, добавить в результ -> ключ_1 == deleted
//                result.put(keyDict1, "deleted");
//                result.put(keyDict1, valueDict1.toString() + " -");
                forModifyKey = "- " + keyDict1;
                result.put(forModifyKey, valueDict1.toString());
            }
        }
        if (!dict02copy.isEmpty()) {
//        оставшиеся ключи в словаре_2 добавить в резулт, потому что они уникальные с пометкой added
            String forModifyKey;
            for (Map.Entry<String, Object> d02 : dict02copy.entrySet()) {
//                result.put(d02.getKey(), "added");
//                result.put(d02.getKey(), d02.getValue().toString() + " +");
                forModifyKey = "+ " + d02.getKey();
                result.put(forModifyKey, d02.getValue().toString());
            }
        }
        */

        return result;
    }

    // Convert Map<> to String
    public static String convertWithStream(Map<String, String> map) {
        String mapAsString = map.keySet().stream()
                .map(key -> key + ": " + map.get(key))
                .collect(Collectors.joining(", \n", "{\n", "\n}"));
        return mapAsString;
    }



}

