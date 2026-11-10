package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class DifferTest {

  private static Path getFixturePath(String fileName) throws Exception {
    URI uri = DifferTest.class.getClassLoader().getResource(fileName).toURI();
    return Path.of(uri);
  }

  private static String readFixture(String fileName) throws Exception {
    return Files.readString(getFixturePath(fileName));
  }

  private static String normalize(String text) {
    if (text == null) {
      return "";
    }
    return text.lines().map(String::stripTrailing).collect(Collectors.joining("\n")).trim();
  }

  // Хелпер для проверки
  private void assertFormat(String ext, String format, String expectedFile) throws Exception {
    String filePath1 = getFixturePath("file1." + ext).toString();
    String filePath2 = getFixturePath("file2." + ext).toString();

    String expected = normalize(readFixture(expectedFile));
    String actual;

    if (format.equals("default")) {
      // Вызов без третьего аргумента
      actual = normalize(Differ.generate(filePath1, filePath2));
    } else {
      // Вызов с явным указанием формата
      actual = normalize(Differ.generate(filePath1, filePath2, format));
    }

    assertEquals(expected, actual, "Failed for " + ext + " and format " + format);
  }

  // --- 8 Обязательных тестов ---

  // 1. JSON + stylish
  @Test
  public void testJsonStylish() throws Exception {
    assertFormat("json", "stylish", "expectedStylish.txt");
  }

  // 2. JSON + plain
  @Test
  public void testJsonPlain() throws Exception {
    assertFormat("json", "plain", "expectedPlain.txt");
  }

  // 3. JSON + json
  @Test
  public void testJsonJson() throws Exception {
    assertFormat("json", "json", "expectedJson.txt");
  }

  // 4. JSON + default
  @Test
  public void testJsonDefault() throws Exception {
    assertFormat("json", "default", "expectedStylish.txt");
  }

  // 5. YAML + stylish
  @Test
  public void testYamlStylish() throws Exception {
    assertFormat("yml", "stylish", "expectedStylish.txt");
  }

  // 6. YAML + plain
  @Test
  public void testYamlPlain() throws Exception {
    assertFormat("yml", "plain", "expectedPlain.txt");
  }

  // 7. YAML + json
  @Test
  public void testYamlJson() throws Exception {
    assertFormat("yml", "json", "expectedJson.txt");
  }

  // 8. YAML + default
  @Test
  public void testYamlDefault() throws Exception {
    assertFormat("yml", "default", "expectedStylish.txt");
  }
}