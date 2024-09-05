// Logical module
package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static hexlet.code.Formatter.formattingResult;
import static hexlet.code.Parser.parsingFile;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String outFormat) throws Exception {
        String dataFile1 = readDataFromFile(filePath1);
        String dataFile2 = readDataFromFile(filePath2);
        String file1Extention = filePath1.substring(filePath1.lastIndexOf('.') + 1, filePath1.length()).toUpperCase();
        String file2Extention = filePath2.substring(filePath2.lastIndexOf('.') + 1, filePath2.length()).toUpperCase();

        if (file1Extention.equalsIgnoreCase("YAML")) {
            file1Extention = "YML";
        }
        if (file2Extention.equalsIgnoreCase("YAML")) {
            file2Extention = "YML";
        }

        Map<String, Object> mapFromFile1 = parsingFile(dataFile1, file1Extention);
        Map<String, Object> mapFromFile2 = parsingFile(dataFile2, file2Extention);

        List<Map<String, Object>> comparisonsResult = Comparison.find(mapFromFile1, mapFromFile2);
        return formattingResult(comparisonsResult, outFormat);
    }

    static String readDataFromFile(String pathFile) throws Exception {
        Path absolutePathFile = Paths.get(pathFile).toAbsolutePath().normalize();
        if (Files.notExists(absolutePathFile)) {
            throw new Exception("File " + pathFile + " does not exist");
        }
        return Files.readString(absolutePathFile);
    }
}

