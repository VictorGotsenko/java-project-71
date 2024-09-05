//Choosing a formatter module
package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import hexlet.code.formatters.Json;

import java.util.List;
import java.util.Map;

public class Formatter {

    static String formattingResult(List<Map<String, Object>> compareResult, String outFormat) throws Exception {
        return switch (outFormat) {
            case "PLAIN" -> Plain.formatter(compareResult);
            case "JSON" -> Json.formatter(compareResult);
            default -> Stylish.formatter(compareResult);
        };
    }


}
