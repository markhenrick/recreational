package site.markhenrick.adventofcode.y2021;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static site.markhenrick.adventofcode.common.TestUtil.getResourceAsString;

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
		assertThat(firstLine.getValue0().getValue0()).isEqualTo(0);
		assertThat(firstLine.getValue0().getValue1()).isEqualTo(9);
		assertThat(firstLine.getValue1().getValue0()).isEqualTo(5);
		assertThat(firstLine.getValue1().getValue1()).isEqualTo(9);

		var lastLine = parseResult.get(9);
		assertThat(lastLine.getValue0().getValue0()).isEqualTo(5);
		assertThat(lastLine.getValue0().getValue1()).isEqualTo(5);
		assertThat(lastLine.getValue1().getValue0()).isEqualTo(8);
		assertThat(lastLine.getValue1().getValue1()).isEqualTo(2);
	}

	@SuppressWarnings("unused")
	static Stream<Arguments> part1() {
		return Stream.of(
			arguments(5, SAMPLE_INPUT),
			arguments(5084, getResourceAsString("input/2021/day05.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part1(int expected, String input) {
		var parseResult = Day05.parse(input);
		assertThat(Day05.part1(parseResult)).isEqualTo(expected);
	}
}
