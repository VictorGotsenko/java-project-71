// App test  module
package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    public void test() {
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("Format json")
    void genDiffJsonTest() throws Exception {
        Assertions.assertEquals(readFixture("resultCompare.json"),
                                Differ.generate(getAbsolutePath("file1.json").toString(),
                                                getAbsolutePath("file2.json").toString(),
                                                "JSON"));
    }

    @Test
    @DisplayName("Format nested json")
    void genDiffNestJsonTest() throws Exception {
        Assertions.assertEquals(readFixture("resultCompareNestStrct.json"),
                                Differ.generate(getAbsolutePath("file1NestStrct.json").toString(),
                                                getAbsolutePath("file2NestStrct.json").toString(),
                                                "JSON"));
    }

    @Test
    @DisplayName("Format yaml")
    void genDiffYamlTest() throws Exception {
        Assertions.assertEquals(readFixture("resultCompareNestStrct.json"),
                                Differ.generate(getAbsolutePath("file1NestStrct.yaml").toString(),
                                                getAbsolutePath("file2NestStrct.yaml").toString(),
                                                "YAML"));
    }



}
