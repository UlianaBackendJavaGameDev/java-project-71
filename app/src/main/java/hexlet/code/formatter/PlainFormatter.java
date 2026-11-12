package hexlet.code.formatter;

import hexlet.code.DiffNode;

import java.util.List;
import java.util.Map;

public class PlainFormatter {
    private String formatValue(Object value){
        if(value == null){
            return "null";
        }
        if(value instanceof Map || value instanceof List){
            return "[complex value]";
        }
        if(value instanceof String){
            return "'" + value + "'";
        }
        return String.valueOf(value);
    }
    public String format(List<DiffNode> diff) {
        StringBuilder result = new StringBuilder();

        for (DiffNode node : diff) {
            String key = node.getKey();
            String status = node.getStatus();

            switch (status) {
                case "removed":
                    result.append("Property '").append(key).append("' was removed\n");
                    break;
                case "added":
                    result.append("Property '").append(key).append("' was added with value: ")
                            .append(formatValue(node.getNewValue())).append("\n");
                    break;
                case "changed":
                    result.append("Property '").append(key).append("' was updated. From ")
                            .append(formatValue(node.getOldValue())).append(" to ")
                            .append(formatValue(node.getNewValue())).append("\n");
                    break;
                case "unchanged":
                    // В плоском формате неизмененные поля НЕ выводим
                    break;
                default:
                    throw new IllegalArgumentException("Unknown status: " + status);
            }
        }

        // Удаляем последний перенос строки (если он есть)
        if (result.length() > 0) {
            result.setLength(result.length() - 1);
        }

        return result.toString();
    }
}
