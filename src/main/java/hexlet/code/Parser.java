package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser{

    public static Map<String, Object> parse(String content, String dataType) throws Exception{
        ObjectMapper mapper;

        if("json".equalsIgnoreCase(dataType)){
            mapper = new ObjectMapper();
        } else if("yaml".equalsIgnoreCase(dataType) || "yaml".equalsIgnoreCase(dataType)){
            mapper = new YAMLMapper();
        }else{
            throw new IllegalArgumentException("Unsupported format: " + dataType);
        }
        return mapper.readValue(content, Map.class);
    }
}