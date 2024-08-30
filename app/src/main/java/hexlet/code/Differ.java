// Logical module
package hexlet.code;

import hexlet.code.formatters.Stylish;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import static hexlet.code.Parser.parsingFile;

public class Differ {
    public static String generate(String filePath1, String filePath2, String outFormat) throws Exception {
        String dataFile1 = readDataFromFile(filePath1);
        String dataFile2 = readDataFromFile(filePath2);

        Map<String, Object> mapFromFile1 = parsingFile(dataFile1, outFormat);
        Map<String, Object> mapFromFile2 = parsingFile(dataFile2, outFormat);

        Map<String, Object> mapDiff = genDiff(mapFromFile1, mapFromFile2);
        return Stylish.formatter(mapDiff);
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
    public static Map<String, Object>  genDiff(Map<String, Object> dict01, Map<String, Object> dict02) {
        Map<String, Object> result = new LinkedHashMap<>();

        if (dict01.isEmpty() && dict02.isEmpty()) {
            return result;
        }
// Получить уникальное множество ключей из всех словарей
        Set<String> uniqKeys = new TreeSet<>();
        uniqKeys.addAll(dict01.keySet());
        uniqKeys.addAll(dict02.keySet());
//        Search diff
//******************** этот код работает для плоских структур ****************
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

