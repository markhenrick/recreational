package site.markhenrick.recreational.adventofcode.y2021;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.recreational.common.TestUtil;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Day01Test {
	private static final String SAMPLE_INPUT = "199\n" +
		"200\n" +
		"208\n" +
		"210\n" +
		"200\n" +
		"207\n" +
		"240\n" +
		"269\n" +
		"260\n" +
		"263";

	@SuppressWarnings("unused")
	static Stream<Arguments> part1() {
		return Stream.of(
			arguments(7L, SAMPLE_INPUT),
			arguments(1532L, TestUtil.getResourceAsString("AoC/input/2021/day01.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part1(long expected, String input) {
		var parseResult = Day01.parse(input);
		assertThat(Day01.part1(parseResult)).isEqualTo(expected);
	}

	@SuppressWarnings("unused")
	static Stream<Arguments> part2() {
		return Stream.of(
			arguments(5L, SAMPLE_INPUT),
			arguments(1571L, TestUtil.getResourceAsString("AoC/input/2021/day01.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part2(long expected, String input) {
		var parseResult = Day01.parse(input);
		assertThat(Day01.part2(parseResult)).isEqualTo(expected);
	}
}
