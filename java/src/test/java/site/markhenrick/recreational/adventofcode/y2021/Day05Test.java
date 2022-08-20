package site.markhenrick.recreational.adventofcode.y2021;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.recreational.common.TestUtil;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Day05Test {
	private static final String SAMPLE_INPUT = "0,9 -> 5,9\n" +
		"8,0 -> 0,8\n" +
		"9,4 -> 3,4\n" +
		"2,2 -> 2,1\n" +
		"7,0 -> 7,4\n" +
		"6,4 -> 2,0\n" +
		"0,9 -> 2,9\n" +
		"3,4 -> 1,4\n" +
		"0,0 -> 8,8\n" +
		"5,5 -> 8,2";

	@Test
	void parse() {
		var parseResult = Day05.parse(SAMPLE_INPUT).toList();
		assertThat(parseResult).hasSize(10);

		var firstLine = parseResult.get(0);
		assertThat(firstLine.getValue0().x()).isEqualTo(0);
		assertThat(firstLine.getValue0().y()).isEqualTo(9);
		assertThat(firstLine.getValue1().x()).isEqualTo(5);
		assertThat(firstLine.getValue1().y()).isEqualTo(9);

		var lastLine = parseResult.get(9);
		assertThat(lastLine.getValue0().x()).isEqualTo(5);
		assertThat(lastLine.getValue0().y()).isEqualTo(5);
		assertThat(lastLine.getValue1().x()).isEqualTo(8);
		assertThat(lastLine.getValue1().y()).isEqualTo(2);
	}

	@SuppressWarnings("unused")
	static Stream<Arguments> part1() {
		return Stream.of(
			arguments(5, SAMPLE_INPUT),
			arguments(5084, TestUtil.getResourceAsString("AoC/input/2021/day05.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part1(int expected, String input) {
		var parseResult = Day05.parse(input);
		assertThat(Day05.part1And2(parseResult, false)).isEqualTo(expected);
	}

	@SuppressWarnings("unused")
	static Stream<Arguments> part2() {
		return Stream.of(
			arguments(12, SAMPLE_INPUT),
			arguments(17882, TestUtil.getResourceAsString("AoC/input/2021/day05.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part2(int expected, String input) {
		var parseResult = Day05.parse(input);
		assertThat(Day05.part1And2(parseResult, true)).isEqualTo(expected);
	}
}
