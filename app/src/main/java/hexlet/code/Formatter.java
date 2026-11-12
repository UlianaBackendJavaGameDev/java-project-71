package hexlet.code;

import hexlet.code.formatter.JsonFormatter;
import hexlet.code.formatter.PlainFormatter;
import hexlet.code.formatter.StylishFormatter;

import java.util.List;

public class Formatter {

    public static String format(List<DiffNode> diff, String formatName) throws Exception {
        if (formatName == null || formatName.equals("stylish")) {
            return StylishFormatter.format(diff);
        }
        if (formatName.equals("plain")) {
            return new PlainFormatter().format(diff);
        }
        if (formatName.equals("json")) {
            return new JsonFormatter().format(diff);
        }
        throw new IllegalArgumentException("Неизвестный формат: " + formatName);
    }
}
