package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class DifferTest {
    private static Path getFixturePath(String fileName){
        return Paths.get("src", "test", "resources" , fileName).toAbsolutePath().normalize();
    }
    private static String readFixture(String fileName) throws Exception{
        return Files.readString(getFixturePath(fileName));
    }
    @Test
    public void testGenerateJsonStylish() throws Exception{
        String filePath1 = getFixturePath("file1.json").toString();
        String filePath2 = getFixturePath("file2.json").toString();

        String expected = readFixture("expectedStylish.txt").trim();
        String actual = Differ.generate(filePath1, filePath2).trim();

        assertEquals(expected, actual);
    }
}
