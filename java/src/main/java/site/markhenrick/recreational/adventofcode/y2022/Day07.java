package site.markhenrick.recreational.adventofcode.y2022;

import lombok.Data;
import lombok.EqualsAndHashCode;
import site.markhenrick.recreational.common.StringUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day07 {
    private static final long SIZE_THRESHOLD = 100000;
    private static final long FS_SIZE = 70000000;
    private static final long DESIRED_FREE_SPACE = 30000000;
    private static final long MAX_USED_SPACE = FS_SIZE - DESIRED_FREE_SPACE;

    private static final String ROOT_STRING = "/";
    private static final String UP_STRING = "..";

    private static final Pattern PATTERN_CD = Pattern.compile("^\\$ cd (.*)$");
    private static final Pattern PATTERN_LS = Pattern.compile("^\\$ ls$");
    private static final Pattern PATTERN_DIR = Pattern.compile("^dir (.*)$");
    private static final Pattern PATTERN_FILE = Pattern.compile("^(\\d*) (.*)$");

    public static long part1(String input) {
        var tree = Directory.parse(input);
        return tree.countSmallDirs();
    }

    public static long part2(String input) {
        var tree = Directory.parse(input);
        var totalSize = tree.getSize();
        var deficit = totalSize - MAX_USED_SPACE;
        return deficit <= 0 ? 0 : tree.findSmallestDir(deficit);
    }

    // TODO generally clean up classes here. Redundant methods, encapsulation etc.

    @Data
    @EqualsAndHashCode(exclude = "parent")
    public abstract static class Node {
        private Directory parent;

        public abstract long getSize();

        public abstract long countSmallDirs();

        public abstract long findSmallestDir(long minimumSize);
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

        @Override
        public long findSmallestDir(long minimumSize) {
            // Individual files never count
            return Long.MAX_VALUE;
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class Directory extends Node {
        private Map<String, Node> children = new HashMap<>();

        public static Directory parse(String input) {
            Directory root = new Directory();
            Directory cwd = root;
            var lines = StringUtil.LINE_SPLITTER.apply(input).toList();
            for (var line : lines) {
                Matcher matchCd;
                Matcher matchLs;
                Matcher matchDir;
                Matcher matchFile;
                if ((matchCd = PATTERN_CD.matcher(line)).matches()) {
                    assert matchCd.groupCount() == 1;
                    var directory = matchCd.group(1);
                    if (directory.equals(ROOT_STRING)) {
                        cwd = root;
                    } else if (directory.equals(UP_STRING)) {
                        cwd = cwd.getParent();
                    } else {
                        // We'll let the ClassCastException be our validation
                        cwd = (Directory) cwd.getChild(directory);
                    }
                } else if ((matchLs = PATTERN_LS.matcher(line)).matches()) {
                    // Nothing to do actually
                } else if ((matchDir = PATTERN_DIR.matcher(line)).matches()) {
                    assert matchDir.groupCount() == 1;
                    cwd.addChild(matchDir.group(1), new Directory());
                } else if ((matchFile = PATTERN_FILE.matcher(line)).matches()) {
                    assert matchFile.groupCount() == 2;
                    cwd.addChild(matchFile.group(2), new File(Long.parseLong(matchFile.group(1))));
                } else {
                    throw new AssertionError("Unrecognised line: " + line);
                }
            }
            return root;
        }

        public Directory() {

        }

        public Directory(Map<String, Node> children) {
            for (var entry : children.entrySet()) {
                addChild(entry.getKey(), entry.getValue());
            }
        }

        public void addChild(String name, Node child) {
            child.setParent(this);
            children.put(name, child);
        }

        public Node getChild(String name) {
            return children.get(name);
        }

        @Override
        public long getSize() {
            // TODO, don't redundantly calculate this each time. Store and update it when children change
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

        @Override
        public long findSmallestDir(long minimumSize) {
            var ownSize = getSize();
            if (ownSize >= minimumSize) {
                var smallestChild = children.values().stream()
                        .mapToLong(child -> child.findSmallestDir(minimumSize))
                        .min()
                        .orElse(Long.MAX_VALUE);
                return smallestChild < ownSize ? smallestChild : ownSize;
            } else {
                return Long.MAX_VALUE;
            }
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
