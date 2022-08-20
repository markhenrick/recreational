package site.markhenrick.recreational.adventofcode.y2020;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.recreational.common.TestUtil;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Day03Test {
	private static final String SAMPLE_INPUT = """
..##.......
#...#...#..
.#....#..#.
..#.#...#.#
.#...##..#.
..#.##.....
.#.#.#....#
.#........#
#.##...#...
#...##....#
.#..#...#.#
""";
	private static final String MY_INPUT = TestUtil.getResourceAsString("AoC/input/2020/day03.txt");

	@ParameterizedTest
	@CsvSource({
		"0,  0,  .",
		"0,  1,  .",
		"1,  0,  #",
		"10, 0,  .",
		"0,  10, .",
		"10, 10, #",
		"5,  2,  #",
		"0,  11, .",
		"0,  12, .",
		"1,  11, #",
		"10, 11, .",
		"0,  21, .",
		"10, 21, #",
		"5,  13, #"
	})
	void getChar(final int x, final int y, final char expected) {
		final var day = new Day03(SAMPLE_INPUT);
		assertThat(day.getChar(new Day03.IntVector(x, y))).isEqualTo(expected);
	}

	@SuppressWarnings("unused")
	static Stream<Arguments> solvePart1() {
		return Stream.of(
			arguments(SAMPLE_INPUT, 7),
			arguments(MY_INPUT, 292)
		);
	}

	@ParameterizedTest
	@MethodSource
	void solvePart1(final String input, final int expected) {
		final var day = new Day03(input);
		assertThat(day.solvePart1()).isEqualTo(expected);
	}

	@SuppressWarnings("unused")
	static Stream<Arguments> solvePart2() {
		return Stream.of(
			arguments(SAMPLE_INPUT, 336L),
			arguments(MY_INPUT, 9354744432L)
		);
	}

	@ParameterizedTest
	@MethodSource
	void solvePart2(final String input, final long expected) {
		final var day = new Day03(input);
		assertThat(day.solvePart2()).isEqualTo(expected);
	}
}
