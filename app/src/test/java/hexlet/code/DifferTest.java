// App test  module
package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
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

    @BeforeAll
    static void initEnvironment() throws IOException {
        pathFile1 = getAbsolutePath("file1.json").toString();
        pathFile2 = getAbsolutePath("file2.json").toString();
        expectedString = readFixture("resultCompare.json");
    }

    @Test
    public void test() {
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("Format json")
    void genDiffTest() throws Exception {
        Assertions.assertEquals(expectedString, Differ.generate(pathFile1, pathFile2, "JSON"));
    }
}
