package site.markhenrick.recreational.adventofcode.y2022;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.recreational.common.TestUtil;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

public class Day06Test {
    static Stream<Arguments> part1() {
        return Stream.of(
                of("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 7),
                of("bvwbjplbgvbhsrlpgdmjqwftvncz", 5),
                of("nppdvjthqldpwncqszvftbrmjlhg", 6),
                of("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 10),
                of("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 11),
                of("mjqapqmgbljsphdztnvjfqwrcgsmlb", 4),
                of("aaaaa", -1),
                of(TestUtil.getResourceAsString("AoC/input/2022/day06.txt"), 1142)
        );
    }

    @ParameterizedTest
    @MethodSource
    void part1(String input, int expected) {
        assertThat(Day06.part1(input)).isEqualTo(expected);
    }

    static Stream<Arguments> part2() {
        return Stream.of(
                of("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 19),
                of("bvwbjplbgvbhsrlpgdmjqwftvncz", 23),
                of("nppdvjthqldpwncqszvftbrmjlhg", 23),
                of("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 29),
                of("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 26),
                of(TestUtil.getResourceAsString("AoC/input/2022/day06.txt"), 2803)
        );
    }

    @ParameterizedTest
    @MethodSource
    void part2(String input, int expected) {
        assertThat(Day06.part2(input)).isEqualTo(expected);
    }
}
