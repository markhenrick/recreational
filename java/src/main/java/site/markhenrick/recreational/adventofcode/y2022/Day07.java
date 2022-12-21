package site.markhenrick.recreational.adventofcode.y2022;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.List;

public class Day07 {
    @Data
    public abstract static class Node {
        private String name;

        public abstract long getSize();
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class File extends Node {
        private long size;

        public File() {

        }

        public File(String name, long size) {
            this.setName(name);
            this.setSize(size);
        }

        public String toString() {
            return String.format("File {name='%s', size=%s}", getName(), getSize());
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class Directory extends Node {
        private List<Node> children;

        public Directory() {

        }

        public Directory(String name, Node... children) {
            this.setName(name);
            this.setChildren(Arrays.asList(children));
        }

        @Override
        public long getSize() {
            return children.stream()
                    .mapToLong(Node::getSize)
                    .sum();
        }

        public String toString() {
            var sb = new StringBuilder();
            sb.append("Directory {name='").append(getName()).append("'}");
            for (var child : children) {
                var childString = child.toString();
                childString = childString.replace("\n", "\n\t");
                sb.append("\n\t").append(childString);
            }
            return sb.toString();
        }
    }
}
