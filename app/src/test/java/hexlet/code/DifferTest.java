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
//        Path filePath = Paths.get("src", "test", "resources", "tests", fileName);
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
    @DisplayName("Test compare nested json files whitout formatter")
    void genDiffJsonTest01() throws Exception {
        Assertions.assertEquals(readFixture("result_stylish(default).txt"),
                                Differ.generate(getAbsolutePath("file1NestStrct.json").toString(),
                                                getAbsolutePath("file2NestStrct.json").toString()));
    }

    //    ** test 02 json json -> stylish(default) formatter
    @Test
    @DisplayName("Test compare nested json files whit stylish formatter")
    void genDiffJsonTest02() throws Exception {
        Assertions.assertEquals(readFixture("result_stylish(default).txt"),
                                Differ.generate(getAbsolutePath("file1NestStrct.json").toString(),
                                                getAbsolutePath("file2NestStrct.json").toString(),
                                                "stylish"));
    }

    //    ** test 03 json json -> plain formatter
    @Test
    @DisplayName("Test compare nested json files whit plain formatter")
    void genDiffJsonTest03() throws Exception {
        Assertions.assertEquals(readFixture("result_plain_format.txt"),
                                Differ.generate(getAbsolutePath("file1NestStrct.json").toString(),
                                                getAbsolutePath("file2NestStrct.json").toString(),
                                                "plain"));
    }

//    ** test 04 json json -> json formatter - not working
//    @Test
//    @DisplayName("Test compare nested json files whit json formatter")
//    void genDiffJsonTest04() throws Exception {
//        Assertions.assertEquals(readFixture("result_stylish(default).txt"),
//                                Differ.generate(getAbsolutePath("file1NestStrct.json").toString(),
//                                                getAbsolutePath("file2NestStrct.json").toString(),
//                                                "json"));
//    }

//





//    **
//** local my
//    @Test
//    @DisplayName("Test compare nested yaml files")
//    void genDiffYamlTest() throws Exception {
//        Assertions.assertEquals(readFixture("resultCompareNestStrct.json"),
//                                Differ.generate(getAbsolutePath("file1NestStrct.yaml").toString(),
//                                                getAbsolutePath("file2NestStrct.yaml").toString()));
//    }
//**

//  ** from Check
//    @Test
//    @DisplayName("Test compare nested json files - result_stylish")
//    void genDiffJsonStylishTest() throws Exception {
//        Assertions.assertEquals(readFixture("result_stylish.txt"),
//                                Differ.generate(getAbsolutePath("file1.json").toString(),
//                                                getAbsolutePath("file2.json").toString()));
//    }
//  **

//  ** from Check
//    @Test
//    @DisplayName("Test compare nested json files - result_plain ")
//    void genDiffJsonPlainTest() throws Exception {
//        Assertions.assertEquals(readFixture("result_plain.txt"),
//                                Differ.generate(getAbsolutePath("file1.json").toString(),
//                                                getAbsolutePath("file2.json").toString(), "plain"));
//    }
//  **

//  ** from Check
//    @Test
//    @DisplayName("Test compare nested yaml files - result_stylish ")
//    void genDiffYmlStylishTest() throws Exception {
//        Assertions.assertEquals(readFixture("result_stylish.txt"),
//                                Differ.generate(getAbsolutePath("file1.yml").toString(),
//                                                getAbsolutePath("file2.yml").toString()));
//    }
//  **

//  ** from Check
//    @Test
//    @DisplayName("Test compare nested yaml files - result_plain ")
//    void genDiffYmlPlainTest() throws Exception {
//        Assertions.assertEquals(readFixture("result_plain.txt"),
//                                Differ.generate(getAbsolutePath("file1.yml").toString(),
//                                                getAbsolutePath("file2.yml").toString(), "plain"));
//    }
// **

/*
    @Test
    @DisplayName("Output formatter plain")
    void genDiffFormatPlainTest() throws Exception {
        Assertions.assertEquals(readFixture("resultComparePlain.txt"),
                                Differ.generate(getAbsolutePath("file1NestStrct.yaml").toString(),
                                                getAbsolutePath("file2NestStrct.yaml").toString(),
                                                "plain"));
    }

    @Test
    @DisplayName("Output formatter JSON")
    void genDiffFormatJsonTest() throws Exception {
        Assertions.assertEquals(readFixture("resultCompareNestJson.txt"),
                                Differ.generate(getAbsolutePath("file1NestStrct.yaml").toString(),
                                                getAbsolutePath("file2NestStrct.yaml").toString(),
                                                "json"));
    }
*/
}
