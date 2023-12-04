package site.markhenrick.recreational.adventofcode.y2023;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.recreational.common.TestUtil;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Day03Test {
	private static final String SAMPLE_INPUT = """
467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...$.*....
.664.598..
""";

	static Stream<Arguments> part1() {
		return Stream.of(
			arguments(4361, SAMPLE_INPUT),
			arguments(529618, TestUtil.getResourceAsString("AoC/input/2023/day03.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part1(int expected, String input)
	{
		assertThat(Day03.part1(input)).isEqualTo(expected);
	}

	static Stream<Arguments> part2() {
		return Stream.of(
			arguments(467835, SAMPLE_INPUT),
			arguments(77509019, TestUtil.getResourceAsString("AoC/input/2023/day03.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part2(int expected, String input)
	{
		assertThat(Day03.part2(input)).isEqualTo(expected);
	}

	// TODO unit tests for symbolAround
	// TODO unit tests for safeIndex
	// TODO unit tests for isSymbol
	// TODO unit tests for findSurroundingStars
}
