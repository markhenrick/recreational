package site.markhenrick.recreational.adventofcode.y2022;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.recreational.common.TestUtil;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Day07Test {
    private static final String SAMPLE_INPUT = """
$ cd /
$ ls
dir a
14848514 b.txt
8504156 c.dat
dir d
$ cd a
$ ls
dir e
29116 f
2557 g
62596 h.lst
$ cd e
$ ls
584 i
$ cd ..
$ cd ..
$ cd d
$ ls
4060174 j
8033020 d.log
5626152 d.ext
7214296 k
""";

    private static final Day07.Node SAMPLE_TREE = new Day07.Directory(Map.of(
            "a", new Day07.Directory(Map.of(
                    "e", new Day07.Directory(Map.of(
                            "i", new Day07.File(584)
                    )),
                    "f", new Day07.File(29116),
                    "g", new Day07.File(2557),
                    "h.lst", new Day07.File(62596)
            )),
            "b.txt", new Day07.File(14848514),
            "c.dat", new Day07.File(8504156),
            "d", new Day07.Directory(Map.of(
                    "j", new Day07.File(4060174),
                    "d.log", new Day07.File(8033020),
                    "d.ext", new Day07.File(5626152),
                    "k", new Day07.File(7214296)
            ))
    ));

    @Test
    void getSize() {
        assertThat(SAMPLE_TREE.getSize()).isEqualTo(48381165L);
        assertThat(new Day07.Directory(Collections.emptyMap()).getSize()).isEqualTo(0L);
        System.out.println(SAMPLE_TREE);
    }

    @Test
    void countSmallDirs() {
        assertThat(SAMPLE_TREE.countSmallDirs()).isEqualTo(95437L);
    }

    @Test
    void findSmallestDir() {
        assertThat(SAMPLE_TREE.findSmallestDir(8381165L)).isEqualTo(24933642L);
    }

    @Test
    void parse() {
        var result = Day07.Directory.parse(SAMPLE_INPUT);
        assertThat(result).isEqualTo(SAMPLE_TREE);
    }

    static Stream<Arguments> part1() {
        return Stream.of(
                arguments(SAMPLE_INPUT, 95437L),
                arguments(TestUtil.getResourceAsString("AoC/input/2022/day07.txt"), 1182909L)
        );
    }

    @ParameterizedTest
    @MethodSource
    void part1(String input, long expected) {
        assertThat(Day07.part1(input)).isEqualTo(expected);
    }

    static Stream<Arguments> part2() {
        return Stream.of(
                arguments(SAMPLE_INPUT, 24933642L),
                arguments(TestUtil.getResourceAsString("AoC/input/2022/day07.txt"), 2832508L)
        );
    }

    @ParameterizedTest
    @MethodSource
    void part2(String input, long expected) {
        assertThat(Day07.part2(input)).isEqualTo(expected);
    }
}
