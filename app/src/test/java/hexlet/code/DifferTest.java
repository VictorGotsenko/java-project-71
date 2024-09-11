// App test module
package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class DifferTest {

    private static String pathFile1;
    private static String pathFile2;
    private static String expectedString;

    static Path getAbsolutePath(String fileName) {
        Path result = Paths.get("").toAbsolutePath();
        Path filePath = Paths.get("src", "test", "resources", fileName);
        return result.resolve(filePath).normalize();
    }

    static String readFixture(String fileName) throws IOException {
        return Files.readString(getAbsolutePath(fileName));
    }

    @Test
    @DisplayName("Test compare nested json files without formatter")
    void genDiffJsonTest01() throws Exception {
        Assertions.assertEquals(readFixture("result_stylish(default).txt"),
                                Differ.generate(getAbsolutePath("file1NestStrct.json").toString(),
                                                getAbsolutePath("file2NestStrct.json").toString()));
    }

    @Test
    @DisplayName("Test compare nested json files with stylish formatter")
    void genDiffJsonTest02() throws Exception {
        Assertions.assertEquals(readFixture("result_stylish(default).txt"),
                                Differ.generate(getAbsolutePath("file1NestStrct.json").toString(),
                                                getAbsolutePath("file2NestStrct.json").toString(),
                                                "stylish"));
    }

    @Test
    @DisplayName("Test compare nested json files with plain formatter")
    void genDiffJsonTest03() throws Exception {
        Assertions.assertEquals(readFixture("result_plain_format.txt"),
                                Differ.generate(getAbsolutePath("file1NestStrct.json").toString(),
                                                getAbsolutePath("file2NestStrct.json").toString(),
                                                "plain"));
    }

    @Test
    @DisplayName("Test compare nested json files and json formatter by object")
    void genDiffJsonTest04() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> fixture = objectMapper.readValue(readFixture("result_json_format.txt"),
                                                                   new TypeReference<>() { });
        String resultDiffGenerate = Differ.generate(getAbsolutePath("file1NestStrct.json").toString(),
                                                    getAbsolutePath("file2NestStrct.json").toString(),
                                                    "json");
        List<Map<String, Object>> result = objectMapper.readValue(resultDiffGenerate, new TypeReference<>() { });
        Assertions.assertEquals(fixture, result);
    }

    @Test
    @DisplayName("Test compare nested yml files without formatter")
    void genDiffYmlTest01() throws Exception {
        Assertions.assertEquals(Files.readString(getAbsolutePath("result_stylish(default).txt")),
                                Differ.generate(getAbsolutePath("file1NestStrct.yml").toString(),
                                                getAbsolutePath("file2NestStrct.yml").toString()));
    }

    @Test
    @DisplayName("Test compare nested yml files with stylish(default) formatter")
    void genDiffYmlTest02() throws Exception {
        Assertions.assertEquals(Files.readString(getAbsolutePath("result_stylish(default).txt")),
                                Differ.generate(getAbsolutePath("file1NestStrct.yml").toString(),
                                                getAbsolutePath("file2NestStrct.yml").toString(),
                                                "stylish"));
    }

    @Test
    @DisplayName("Test compare nested yml files with plain formatter")
    void genDiffYmlTest03() throws Exception {
        Assertions.assertEquals(Files.readString(getAbsolutePath("result_plain_format.txt")),
                                Differ.generate(getAbsolutePath("file1NestStrct.yml").toString(),
                                                getAbsolutePath("file2NestStrct.yml").toString(),
                                                "plain"));
    }

    @Test
    @DisplayName("Test compare nested yml files with json formatter")
    void genDiffYmlTest04() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> fixture = objectMapper.readValue(readFixture("result_json_format.txt"),
                                                                   new TypeReference<>() { });
        String resultDiffGenerate = Differ.generate(getAbsolutePath("file1NestStrct.yml").toString(),
                                                    getAbsolutePath("file2NestStrct.yml").toString(),
                                                    "json");
        List<Map<String, Object>> result = objectMapper.readValue(resultDiffGenerate, new TypeReference<>() { });
        Assertions.assertEquals(fixture, result);
    }
}
