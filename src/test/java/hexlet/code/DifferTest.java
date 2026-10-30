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

  @Test
  public void testGenerateJsonStylish() throws Exception {
    String filePath1 = getFixturePath("file1.json").toString();
    String filePath2 = getFixturePath("file2.json").toString();

    // Обязательно прогоняем через normalize оба значения!
    String expected = normalize(readFixture("expectedStylish.txt"));
    String actual = normalize(Differ.generate(filePath1, filePath2));

    assertEquals(expected, actual);
  }

  private static String normalize(String text) {
    if (text == null) {
      return "";
    }
    return text.lines().map(String::stripTrailing).collect(Collectors.joining("\n")).trim();
  }
}
