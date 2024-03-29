package site.markhenrick.recreational.adventofcode.y2021;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.recreational.common.TestUtil;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Day02Test {
	// Multiline strings are causing IntelliJ to hang for me :shrug:
	private static final String SAMPLE_INPUT = "forward 5\n" +
		"down 5\n" +
		"forward 8\n" +
		"up 3\n" +
		"down 8\n" +
		"forward 2";

	@SuppressWarnings("unused")
	static Stream<Arguments> part1() {
		return Stream.of(
			arguments(150, SAMPLE_INPUT),
			arguments(1990000, TestUtil.getResourceAsString("AoC/input/2021/day02.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part1(final int expected, final String input) {
		assertThat(Day02.part1(input)).isEqualTo(expected);
	}

	@SuppressWarnings("unused")
	static Stream<Arguments> part2() {
		return Stream.of(
			arguments(900, SAMPLE_INPUT),
			arguments(1975421260, TestUtil.getResourceAsString("AoC/input/2021/day02.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part2(final int expected, final String input) {
		assertThat(Day02.part2(input)).isEqualTo(expected);
	}
}
