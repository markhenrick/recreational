package site.markhenrick.recreational.adventofcode.y2022;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day07Test {
    private static final Day07.Node SAMPLE_TREE = new Day07.Directory("/",
            new Day07.Directory("a",
                    new Day07.Directory("e",
                            new Day07.File("i", 584)
                    ),
                    new Day07.File("f", 29116),
                    new Day07.File("g", 2557),
                    new Day07.File("h.lst", 62596)
            ),
            new Day07.File("b.txt", 14848514),
            new Day07.File("c.dat", 8504156),
            new Day07.Directory("d",
                    new Day07.File("j", 4060174),
                    new Day07.File("d.log", 8033020),
                    new Day07.File("d.ext", 5626152),
                    new Day07.File("k", 7214296)
            )
    );

    @Test
    void getSize() {
        assertThat(SAMPLE_TREE.getSize()).isEqualTo(48381165L);
        assertThat(new Day07.Directory("foo").getSize()).isEqualTo(0L);
    }
}
