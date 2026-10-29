package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

  public static String generate(String filePath1, String filePath2) throws Exception {
    Path fullPath1 = Path.of(filePath1).toAbsolutePath();
    Path fullPath2 = Path.of(filePath2).toAbsolutePath();

    String content1 = Files.readString(fullPath1);
    String content2 = Files.readString(fullPath2);

    String format1 = getFileExtention(filePath1);
    String format2 = getFileExtention(filePath2);

    Map<String, Object> data1 = Parser.parse(content1, format1);
    Map<String, Object> data2 = Parser.parse(content2, format2);

    return makeStylishDiff(data1, data2);
  }

  private static String makeStylishDiff(Map<String, Object> data1, Map<String, Object> data2) {
    Set<String> keys = new TreeSet<>(data1.keySet());
    keys.addAll(data2.keySet());

    StringBuilder sb = new StringBuilder("{\n");

    for (String key : keys) {
      Object val1 = data1.get(key);
      Object val2 = data2.get(key);

      if (!data1.containsKey(key)) {
        sb.append("  + ").append(key).append(": ").append(val2).append("\n");
      } else if (!data2.containsKey(key)) {
        sb.append("  - ").append(key).append(": ").append(val1).append("\n");

      } else if (java.util.Objects.equals(val1, val2)) {
        sb.append("    ").append(key).append(": ").append(val1).append("\n");
      } else {
        sb.append("  - ").append(key).append(": ").append(val1).append("\n");
        sb.append("  + ").append(key).append(": ").append(val2).append("\n");
      }
    }
    sb.append("}");
    return sb.toString();
  }

  private static String getFileExtention(String filePath) {
    int lastIndex = filePath.lastIndexOf('.');
    if (lastIndex == -1) {
      return "";
    }
    return filePath.substring(lastIndex + 1);
  }
}
