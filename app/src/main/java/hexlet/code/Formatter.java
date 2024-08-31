//Choosing a formatter module
package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;

public class Formatter {
    static String formattingResult(Map<String, Object> mapCompareResult, String outFormat) {
        String result = "";

        switch (outFormat) {
            case "plain" -> {
                result = Plain.formatter(mapCompareResult);
            }
            default -> {
                result = Stylish.formatter(mapCompareResult);
            }
        }
        return result;
    }
}
