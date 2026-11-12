package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DiffBuilder {

    public static List<DiffNode> build(Map<String, Object> data1, Map<String, Object> data2) {
        List<DiffNode> result = new ArrayList<>();
        List<String> keys = new ArrayList<>(data1.keySet());

        for (String key : data2.keySet()) {
            if (!keys.contains(key)) {
                keys.add(key);
            }
        }
        keys.sort(String::compareTo);

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
}
