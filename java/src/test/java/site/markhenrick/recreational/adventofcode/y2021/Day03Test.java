package site.markhenrick.recreational.adventofcode.y2021;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.recreational.common.TestUtil;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Day03Test {
	private static final String SAMPLE_INPUT = "00100\n" +
		"11110\n" +
		"10110\n" +
		"10111\n" +
		"10101\n" +
		"01111\n" +
		"00111\n" +
		"11100\n" +
		"10000\n" +
		"11001\n" +
		"00010\n" +
		"01010";

	@SuppressWarnings("unused")
	static Stream<Arguments> part1() {
		return Stream.of(
			arguments(198, SAMPLE_INPUT),
			arguments(3923414, TestUtil.getResourceAsString("AoC/input/2021/day03.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part1(final int expected, final String input) {
		assertThat(Day03.part1(input)).isEqualTo(expected);
	}

	@SuppressWarnings("unused")
	static Stream<Arguments> part2() {
		return Stream.of(
			arguments(230, SAMPLE_INPUT),
			arguments(5852595, TestUtil.getResourceAsString("AoC/input/2021/day03.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part2(final int expected, final String input) {
		assertThat(Day03.part2(input)).isEqualTo(expected);
	}
}
