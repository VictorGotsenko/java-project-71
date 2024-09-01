//Choosing a formatter module
package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;

public class Formatter {
    static String formattingResult(Map<String, Object> mapCompareResult, String outFormat) throws Exception {
        return switch (outFormat) {
            case "plain" -> Plain.formatter(mapCompareResult);
            case "json" -> Json.formatter(mapCompareResult);
            default -> Stylish.formatter(mapCompareResult);
        };
    }
}
