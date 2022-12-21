package site.markhenrick.recreational.adventofcode.y2022;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

public class Day07 {
    private static final long SIZE_THRESHOLD = 100000;

    @Data
    public abstract static class Node {

        public abstract long getSize();

        public abstract long countSmallDirs();
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

        @Override
        public long countSmallDirs() {
            // Individual files never count
            return 0;
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

        @Override
        public long countSmallDirs() {
            var thisDirSize = getSize();
            return (thisDirSize <= SIZE_THRESHOLD ? thisDirSize : 0) + children.values().stream()
                    .mapToLong(Node::countSmallDirs)
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
