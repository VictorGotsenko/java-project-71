// Logical module
package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import static hexlet.code.Formatter.formattingResult;
import static hexlet.code.Parser.parsingFile;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String outFormat) throws Exception {
        String dataFile1 = readDataFromFile(filePath1);
        String dataFile2 = readDataFromFile(filePath2);
        String file1Extention = filePath1.substring(filePath1.lastIndexOf('.') + 1, filePath1.length());
        String file2Extention = filePath2.substring(filePath2.lastIndexOf('.') + 1, filePath2.length());

        Map<String, Object> mapFromFile1 = parsingFile(dataFile1, file1Extention.toUpperCase());
        Map<String, Object> mapFromFile2 = parsingFile(dataFile2, file2Extention.toUpperCase());

        Map<String, Object> mapCompareResult = genDiff(mapFromFile1, mapFromFile2);

        return formattingResult(mapCompareResult, outFormat);
    }

    public static String readDataFromFile(String pathFile) throws Exception {
        Path absolutePathFile = Paths.get(pathFile).toAbsolutePath().normalize();
        if (Files.notExists(absolutePathFile)) {
            throw new Exception("File " + pathFile + " does not exist");
        }
        return Files.readString(absolutePathFile);
    }

//  Вычисляю разницу двух словарей (структур )
    public static Map<String, Object>  genDiff(Map<String, Object> dict01, Map<String, Object> dict02) {
        Map<String, Object> result = new LinkedHashMap<>();

        if (dict01.isEmpty() && dict02.isEmpty()) {
            return result;
        }

//  Получаю уникальное множество ключей из всех словарей
        Set<String> uniqKeys = new TreeSet<>();
        uniqKeys.addAll(dict01.keySet());
        uniqKeys.addAll(dict02.keySet());

//  Search diff *** этот код работает для плоских структур ***
        uniqKeys.forEach(unqKey -> {
            if (!dict01.containsKey(unqKey)) {
                result.put("  + " + unqKey, dict02.get(unqKey));
            }
            if (dict01.containsKey(unqKey) && dict02.containsKey(unqKey)) {
                if (Objects.equals(dict01.get(unqKey), dict02.get(unqKey))) {
                    result.put("    " + unqKey, dict01.get(unqKey));
                } else {
                    result.put("  - " + unqKey, dict01.get(unqKey));
                    result.put("  + " + unqKey, dict02.get(unqKey));
                }
            }
            if (dict01.containsKey(unqKey) && !dict02.containsKey(unqKey)) {
                result.put("  - " + unqKey, dict01.get(unqKey));
            }
        });
        return result;
    }
}

