package site.markhenrick.recreational.adventofcode.y2022;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

public class Day07 {
    @Data
    public abstract static class Node {

        public abstract long getSize();
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class File extends Node {
        private long size;

        public File() {

        }

        public File(long size) {
            this.setSize(size);
        }

        public String toString() {
            return String.format("File {size=%s}", getSize());
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class Directory extends Node {
        private Map<String, Node> children;

        public Directory() {

        }

        public Directory(Map<String, Node> children) {
            this.setChildren(children);
        }

        @Override
        public long getSize() {
            return children.values().stream()
                    .mapToLong(Node::getSize)
                    .sum();
        }

        public String toString() {
            var sb = new StringBuilder();
            sb.append("Directory");
            for (var child : children.entrySet()) {
                var childString = child.getValue().toString();
                childString = childString.replace("\n", "\n\t");
                sb.append("\n\t").append(child.getKey()).append(' ').append(childString);
            }
            return sb.toString();
        }
    }
}
