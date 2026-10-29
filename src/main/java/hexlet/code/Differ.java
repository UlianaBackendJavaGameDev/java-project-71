package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception{
        Path fullPath1 = Path.of(filePath1).toAbsolutePath();
        Path fullPath2 = Path.of(filePath2).toAbsolutePath();

        String content1 = Files.readString(fullPath1);
        String content2 = Files.readString(fullPath2);

        String format1 = getFileExtention(filePath1);
        String format2 = getFileExtention(filePath2);

        Map<String, Object> data1 = Parser.parse(content1, format1);
        Map<String, Object> data2 = Parser.parse(content2, format2);

        return "Comparison logic will be here!";
    }
    private static String getFileExtention(String filePath){
        int lastIndex = filePath.lastIndexOf('.');
        if(lastIndex == -1){
            return "";
        }
        return filePath.substring(lastIndex + 1);
    }
}
