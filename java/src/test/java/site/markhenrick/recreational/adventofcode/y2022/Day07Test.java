package site.markhenrick.recreational.adventofcode.y2022;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class Day07Test {
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
    ) );

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
}
