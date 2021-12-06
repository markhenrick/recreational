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
		assertThat(Day03.part1(input)).isEqualTo(expected);
	}
}
