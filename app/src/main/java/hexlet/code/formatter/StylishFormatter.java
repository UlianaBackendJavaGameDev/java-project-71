package hexlet.code.formatter;

import hexlet.code.DiffNode;

import java.util.List;

public class StylishFormatter {

    public static String format(List<DiffNode> diffNodes) {
        StringBuilder sb = new StringBuilder("{\n");

        for (DiffNode node : diffNodes) {
            String key = node.getKey();
            String status = node.getStatus();
            Object oldVal = node.getOldValue();
            Object newVal = node.getNewValue();

            switch (status) {
                case "unchanged":
                    sb.append("    ").append(key).append(": ").append(oldVal).append("\n");
                    break;
                case "removed":
                    sb.append("  - ").append(key).append(": ").append(oldVal).append("\n");
                    break;
                case "added":
                    sb.append("  + ").append(key).append(": ").append(newVal).append("\n");
                    break;
                case "changed":
                    sb.append("  - ").append(key).append(": ").append(oldVal).append("\n");
                    sb.append("  + ").append(key).append(": ").append(newVal).append("\n");
                    break;

            }
        }
        sb.append("}");
        return sb.toString();
    }
}