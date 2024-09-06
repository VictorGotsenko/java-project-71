// App test module
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

    static Path getAbsolutePathTest2(String fileName) {
        Path result = Paths.get("").toAbsolutePath();
        Path filePath = Paths.get("src", "test", "resources", "tests", fileName);
        return result.resolve(filePath).normalize();
    }

    static String readFixture(String fileName) throws IOException {
        return Files.readString(getAbsolutePath(fileName));
    }

    @Test
    public void test() {
        Assertions.assertTrue(true);
    }

//    ** test 01 json without formatter
    @Test
    @DisplayName("Test compare nested json files without formatter")
    void genDiffJsonTest01() throws Exception {
        Assertions.assertEquals(readFixture("result_stylish(default).txt"),
                                Differ.generate(getAbsolutePath("file1NestStrct.json").toString(),
                                                getAbsolutePath("file2NestStrct.json").toString()));
    }

    //    ** test 01 json without formatter for test2
    @Test
    @DisplayName("Test compare nested json files without formatter")
    void genDiffJsonTest01ForTest2() throws Exception {
        Assertions.assertEquals(Files.readString(getAbsolutePathTest2("result_stylish.txt")),
                                Differ.generate(getAbsolutePathTest2("file1.json").toString(),
                                                getAbsolutePathTest2("file2.json").toString()));
    }

    //    ** test 02 json json -> stylish(default) formatter
    @Test
    @DisplayName("Test compare nested json files with stylish formatter")
    void genDiffJsonTest02() throws Exception {
        Assertions.assertEquals(readFixture("result_stylish(default).txt"),
                                Differ.generate(getAbsolutePath("file1NestStrct.json").toString(),
                                                getAbsolutePath("file2NestStrct.json").toString(),
                                                "stylish"));
    }

    //    ** test 02 json json -> stylish(default) formatter for test2
    @Test
    @DisplayName("Test compare nested json files with stylish(default) formatter")
    void genDiffJsonTest02ForTest2() throws Exception {
        Assertions.assertEquals(Files.readString(getAbsolutePathTest2("result_stylish.txt")),
                                Differ.generate(getAbsolutePathTest2("file1.json").toString(),
                                                getAbsolutePathTest2("file2.json").toString(),
                                                "stylish"));
    }

    //    ** test 03 json json -> plain formatter
    @Test
    @DisplayName("Test compare nested json files with plain formatter")
    void genDiffJsonTest03() throws Exception {
        Assertions.assertEquals(readFixture("result_plain_format.txt"),
                                Differ.generate(getAbsolutePath("file1NestStrct.json").toString(),
                                                getAbsolutePath("file2NestStrct.json").toString(),
                                                "plain"));
    }

    //    ** test 03 json json -> plain formatter for Test2
    @Test
    @DisplayName("Test compare nested json files with plain formatter")
    void genDiffJsonTest03ForTest2() throws Exception {
        Assertions.assertEquals(Files.readString(getAbsolutePathTest2("result_plain.txt")),
                                Differ.generate(getAbsolutePathTest2("file1.json").toString(),
                                                getAbsolutePathTest2("file2.json").toString(),
                                                "plain"));
    }

    //    ** test 04 json json -> json formatter
    @Test
    @DisplayName("Test compare nested json files  json formatter")
    void genDiffJsonTest04() throws Exception {
        Assertions.assertEquals(readFixture("result_json_format.txt"),
                                Differ.generate(getAbsolutePath("file1NestStrct.json").toString(),
                                                getAbsolutePath("file2NestStrct.json").toString(),
                                                "json"));
    }

    //    ** test 04 json json -> plain formatter for Test2
    @Test
    @DisplayName("Test compare nested json files with json formatter")
    void genDiffJsonTest04ForTest2() throws Exception {
        Assertions.assertEquals(Files.readString(getAbsolutePathTest2("result_json.txt")),
                                Differ.generate(getAbsolutePathTest2("file1.json").toString(),
                                                getAbsolutePathTest2("file2.json").toString(),
                                                "json"));
    }

/*
 *  Block test YML files
 */

    //    ** test 01 yml without formatter for test2
    @Test
    @DisplayName("Test compare nested yml files without formatter")
    void genDiffYmlTest01ForTest2() throws Exception {
        Assertions.assertEquals(Files.readString(getAbsolutePathTest2("result_stylish.txt")),
                                Differ.generate(getAbsolutePathTest2("file1.yml").toString(),
                                                getAbsolutePathTest2("file2.yml").toString()));
    }

    //    ** test 02 yml yml -> stylish(default) formatter for test2
    @Test
    @DisplayName("Test compare nested yml files with stylish(default) formatter")
    void genDiffYmlTest02ForTest2() throws Exception {
        Assertions.assertEquals(Files.readString(getAbsolutePathTest2("result_stylish.txt")),
                                Differ.generate(getAbsolutePathTest2("file1.yml").toString(),
                                                getAbsolutePathTest2("file2.yml").toString(),
                                                "stylish"));
    }

    //    ** test 03 yml yml -> plain formatter for test2
    @Test
    @DisplayName("Test compare nested yml files with plain formatter")
    void genDiffYmlTest03ForTest2() throws Exception {
        Assertions.assertEquals(Files.readString(getAbsolutePathTest2("result_plain.txt")),
                                Differ.generate(getAbsolutePathTest2("file1.yml").toString(),
                                                getAbsolutePathTest2("file2.yml").toString(),
                                                "plain"));
    }

    //    ** test 04 yml yml -> json formatter for test2
    @Test
    @DisplayName("Test compare nested yml files with json formatter")
    void genDiffYmlTest04ForTest2() throws Exception {
        Assertions.assertEquals(Files.readString(getAbsolutePathTest2("result_json.txt")),
                                Differ.generate(getAbsolutePathTest2("file1.yml").toString(),
                                                getAbsolutePathTest2("file2.yml").toString(),
                                                "json"));
    }
}
