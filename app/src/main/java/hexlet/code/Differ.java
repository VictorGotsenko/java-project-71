// Logical module

package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static hexlet.code.Parser.parsingFile;

public class Differ {
    public static String generate(String filePath1, String filePath2, String outFormat) throws Exception {
        String dataFile1 = readDataFromFile(filePath1);
        String dataFile2 = readDataFromFile(filePath2);
        Map<String, Object> mapFromFile1 = parsingFile(dataFile1, outFormat);
        Map<String, Object> mapFromFile2 = parsingFile(dataFile2, outFormat);
        var resultMap = genDiff(mapFromFile1, mapFromFile2);
        return convertWithStream(resultMap);


//        System.out.println("temp_break");
//        String result = "Test";
//        return result;
    }


    public static String readDataFromFile(String pathFile) throws Exception {
        Path absolutePathFile = Paths.get(pathFile).toAbsolutePath().normalize();
        if (Files.notExists(absolutePathFile)) {
            throw new Exception("File " + pathFile + " does not exist");
        }
// Читаем файл
//        String result = Files.readString(absolutePathFile);
        return Files.readString(absolutePathFile);
// Выводим содержимое
//        System.out.println(result);
//        return result;
    }

    public static Map<String, String> genDiff(Map<String, Object> dict01, Map<String, Object> dict02) {
        Map<String, String> result = new TreeMap<String, String>();
        Map<String, Object> dict02copy = new HashMap<>(dict02);
        if (dict01.isEmpty() && dict02.isEmpty()) {
            return result;
        }

        for (Map.Entry<String, Object> m01 : dict01.entrySet()) {
            var keyDict1 = m01.getKey();
            var valueDict1 = m01.getValue();
//            проверить есть ли ключ keyDict1 в словаре_2 ?
            if (dict02copy.containsKey(keyDict1)) {
//            да, вытащить значение в словаре_2 по keyDict1 и сравнить со значением valueDict1
                for (final Map.Entry<String, Object> d02 : dict02.entrySet()) {
                    if (d02.getKey().equalsIgnoreCase(keyDict1)) {
//                        если да -> добавить в результ -> ключ_1 == unchanged
                        if (d02.getValue().equals(valueDict1)) {
                            result.put(keyDict1, "unchanged");
                        } else {
//                      нет -> добавить в результ -> ключ_1 == changed
                            result.put(keyDict1, "changed");
                        }
                        dict02copy.remove(keyDict1);
                    }
                }

            } else {
//                нет, добавить в результ -> ключ_1 == deleted
                result.put(keyDict1, "deleted");
            }
        }
        if (!dict02copy.isEmpty()) {
//        оставшиеся ключи в словаре_2 добавить в резулт, потому что они уникальные с пометкой added
            for (Map.Entry<String, Object> d02 : dict02copy.entrySet()) {
                result.put(d02.getKey(), "added");
            }
        }

        return result;
    }
    // Convert Map<> to String
    public static String convertWithStream(Map<String, String> map) {
        String mapAsString = map.keySet().stream()
                .map(key -> key + "=" + map.get(key))
                .collect(Collectors.joining(", \n", "{\n", "\n}"));
        return mapAsString;
    }





}

