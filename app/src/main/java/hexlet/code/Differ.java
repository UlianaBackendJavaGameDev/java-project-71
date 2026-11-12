package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Differ {

    // Старый метод (по умолчанию stylish)
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    // Новый метод с форматом
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        String content1 = Files.readString(Path.of(filePath1));
        String content2 = Files.readString(Path.of(filePath2));

        String extension1 = getFileExtension(filePath1);
        String extension2 = getFileExtension(filePath2);

        Map<String, Object> data1 = Parser.parse(content1, extension1);
        Map<String, Object> data2 = Parser.parse(content2, extension2);

        List<DiffNode> diffNodes = DiffBuilder.build(data1, data2);

        // ВОТ ЗДЕСЬ МЫ ВЫЗЫВАЕМ НАШ ПЕРЕКЛЮЧАТЕЛЬ
        return Formatter.format(diffNodes, format);
    }

    // Этот метод тоже оставляем без изменений!
    private static String getFileExtension(String filePath) {
        int lastIndex = filePath.lastIndexOf('.');
        if (lastIndex == -1) {
            return "";
        }
        return filePath.substring(lastIndex + 1);
    }
}