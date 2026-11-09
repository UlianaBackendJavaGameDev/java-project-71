package hexlet.code.formatter;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffNode;
import java.util.List;

public class JsonFormatter {
    public String format(List<DiffNode> diff) throws Exception{
        ObjectMapper mapper  = new ObjectMapper();

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(diff);
    }
}
