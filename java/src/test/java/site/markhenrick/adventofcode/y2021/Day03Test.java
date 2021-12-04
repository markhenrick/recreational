package site.markhenrick.adventofcode.y2021;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.adventofcode.common.TestUtil;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Day03Test {
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
			arguments(1990000, TestUtil.getResourceAsString("input/2021/day03.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part1(final int expected, final String input) {
		assertThat(Day03.part1(input)).isEqualTo(expected);
	}
}
