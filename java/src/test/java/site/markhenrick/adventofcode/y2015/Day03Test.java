package site.markhenrick.adventofcode.y2015;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static site.markhenrick.adventofcode.common.TestUtil.getResourceAsString;

public class Day03Test {
	@SuppressWarnings("unused")
	static Stream<Arguments> part1() {
		return Stream.of(
			arguments(2, ">"),
			arguments(4, "^>v<"),
			arguments(2, "^v^v^v^v^v"),
			arguments(2565, getResourceAsString("input/2015/day03.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part1(int expected, String input) {
		assertThat(Day03.part1And2(input, 1)).isEqualTo(expected);
	}

	@SuppressWarnings("unused")
	static Stream<Arguments> part2() {
		return Stream.of(
			arguments(3, "^v"),
			arguments(3, "^>v<"),
			arguments(11, "^v^v^v^v^v"),
			arguments(2639, getResourceAsString("input/2015/day03.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part2(int expected, String input) {
		assertThat(Day03.part1And2(input, 2)).isEqualTo(expected);
	}
}
