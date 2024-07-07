package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.recreational.common.TestUtil;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Day14Test {

	private static final String SAMPLE_INPUT = """
O....#....
O.OO#....#
.....##...
OO.#O....O
.O.....O#.
O.#..O.#.#
..O..#O..O
.......O..
#....###..
#OO..#....
""";

	private static final String SAMPLE_ROLLED = """
OOOO.#.O..
OO..#....#
OO..O##..O
O..#.OO...
........#.
..#....#.#
..O..#.O.O
..O.......
#....###..
#....#....
""";

	private static final int SAMPLE_WEIGHT = 136;

	static Stream<Arguments> part1() {
		return Stream.of(
			arguments(SAMPLE_WEIGHT, SAMPLE_INPUT),
			arguments(110128, TestUtil.getResourceAsString("AoC/input/2023/day14.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part1(int expected, String input) {
		assertThat(Day14.part1(input)).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource({
		"OOOO....##,OO.O.O..##",
		"OOO.......,...OO....O",
		"O....#OO..,.O...#O..O",
		"O..#......,.O.#......",
	})
	void rollColumn(String expected, String input) {
		val parsedInput = Day14.parseLine(input);
		val parsedExpected = Day14.parseLine(expected);
		val actual = Day14.rollColumn(parsedInput);
		assertThat(actual).isEqualTo(parsedExpected);
	}

	@Test
	void roll() {
		val parsedInput = Day14.parse(SAMPLE_INPUT);
		val parsedExpected = Day14.parse(SAMPLE_ROLLED);
		val actual = Day14.roll(parsedInput);
		assertThat(actual).isEqualTo(parsedExpected);
	}

	@ParameterizedTest
	@CsvSource({
		"34,OOOO....##",
		"27,OOO.......",
		"17,O....#OO..",
		"10,O..#......",
	})
	void weighColumn(int expected, String input) {
		val parsedInput = Day14.parseLine(input);
		val actual = Day14.weighColumn(parsedInput);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void weigh() {
		val parsedInput = Day14.parse(SAMPLE_ROLLED);
		val actual = Day14.weigh(parsedInput);
		assertThat(actual).isEqualTo(SAMPLE_WEIGHT);
	}
}
