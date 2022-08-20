package site.markhenrick.recreational.adventofcode.y2021;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.recreational.common.TestUtil;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Day06Test {
	private static final String SAMPLE_INPUT = "3,4,3,1,2";

	@Test
	void parse() {
		var parseResult = Day06.parse(SAMPLE_INPUT);
		assertThat(parseResult).containsExactly(3, 4, 3, 1, 2);
	}

	@SuppressWarnings("unused")
	static Stream<Arguments> part1() {
		return Stream.of(
			arguments(5934L, SAMPLE_INPUT),
			arguments(371379L, TestUtil.getResourceAsString("AoC/input/2021/day06.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part1(long expected, String input) {
		var parseResult = Day06.parse(input);
		assertThat(Day06.part1(parseResult, 80)).isEqualTo(expected);
	}

	@SuppressWarnings("unused")
	static Stream<Arguments> part2() {
		return Stream.of(
			arguments(26984457539L, SAMPLE_INPUT),
			arguments(1674303997472L, TestUtil.getResourceAsString("AoC/input/2021/day06.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part2(long expected, String input) {
		var parseResult = Day06.parse(input);
		assertThat(Day06.part1(parseResult, 256)).isEqualTo(expected);
	}
}
