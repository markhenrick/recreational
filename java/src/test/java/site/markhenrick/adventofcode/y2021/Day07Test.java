package site.markhenrick.adventofcode.y2021;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static site.markhenrick.adventofcode.common.TestUtil.getResourceAsString;

public class Day07Test {

	@SuppressWarnings("unused")
	static Stream<Arguments> part1() {
		return Stream.of(
				arguments(37, "16,1,2,0,4,2,7,1,2,14"),
				arguments(352997, getResourceAsString("input/2021/day07.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part1(int expected, String input) {
		var parseResult = Day07.parse(input);
		assertThat(Day07.part1(parseResult)).isEqualTo(expected);
	}

	@SuppressWarnings("unused")
	static Stream<Arguments> part2() {
		return Stream.of(
				arguments(168, "16,1,2,0,4,2,7,1,2,14"),
				arguments(101571302, getResourceAsString("input/2021/day07.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part2(int expected, String input) {
		var parseResult = Day07.parse(input);
		assertThat(Day07.part2(parseResult)).isEqualTo(expected);
	}
}
