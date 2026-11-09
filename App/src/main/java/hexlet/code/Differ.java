package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

  // Старый метод (по умолчанию stylish)
  public static String generate(String filePath1, String filePath2) throws Exception {
    return generate(filePath1, filePath2, "stylish");
  }

  // Новый метод с форматом
  public static String generate(String filePath1, String filePath2, String format) throws Exception {
    String content1 = Files.readString(Path.of(filePath1));
    String content2 = Files.readString(Path.of(filePath2));

    String format1 = getFileExtention(filePath1);
    String format2 = getFileExtention(filePath2);

    Map<String, Object> data1 = Parser.parse(content1, format1);
    Map<String, Object> data2 = Parser.parse(content2, format2);

    List<DiffNode> diffNodes = buildDiff(data1, data2);

    // ВОТ ЗДЕСЬ МЫ ВЫЗЫВАЕМ НАШ ПЕРЕКЛЮЧАТЕЛЬ
    return Formatter.format(diffNodes, format);
  }

  // Этот метод оставляем без изменений!
  public static List<DiffNode> buildDiff(Map<String, Object> data1, Map<String, Object> data2) {
    List<DiffNode> result = new ArrayList<>();
    Set<String> keys = new TreeSet<>(data1.keySet());
    keys.addAll(data2.keySet());

    for (String key : keys) {
      boolean inData1 = data1.containsKey(key);
      boolean inData2 = data2.containsKey(key);

      Object val1 = data1.get(key);
      Object val2 = data2.get(key);

      if (!inData2) {
        result.add(new DiffNode(key, "removed", val1, null));
      } else if (!inData1) {
        result.add(new DiffNode(key, "added", null, val2));
      } else if (java.util.Objects.equals(val1, val2)) {
        result.add(new DiffNode(key, "unchanged", val1, val2));
      } else {
        result.add(new DiffNode(key, "changed", val1, val2));
      }
    }
    return result;
  }

  // Этот метод тоже оставляем без изменений!
  private static String getFileExtention(String filePath) {
    int lastIndex = filePath.lastIndexOf('.');
    if (lastIndex == -1) {
      return "";
    }
    return filePath.substring(lastIndex + 1);
  }
}