package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.recreational.common.StringUtil;
import site.markhenrick.recreational.common.TestUtil;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Day09Test {
	private static final String SAMPLE_INPUT = """
0 3 6 9 12 15
1 3 6 10 15 21
10 13 16 21 30 45
""";

	static Stream<Arguments> part1() {
		return Stream.of(
			arguments(114, SAMPLE_INPUT),
			arguments(1992273652, TestUtil.getResourceAsString("AoC/input/2023/day09.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part1(int expected, String input)
	{
		assertThat(Day09.part1(input)).isEqualTo(expected);
	}

	static Stream<Arguments> part2() {
		return Stream.of(
			arguments(2, SAMPLE_INPUT),
			arguments(1012, TestUtil.getResourceAsString("AoC/input/2023/day09.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part2(int expected, String input)
	{
		assertThat(Day09.part2(input)).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource({
		"1, 1",
		"2, -1 2",
		"3, 1 -3 3",
		"4, -1 4 -6 4",
		"5, 1 -5 10 -10 5",
	})
	void getCoefficientsP1(int n, String expectedRaw) {
		val expected = StringUtil.spaceSeparatedInts(expectedRaw).mapToLong(x -> x).boxed().toList();
		assertThat(Day09.getCoefficientsP1(n)).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource({
		"1, 1",
		"2, 2 -1",
		"3, 3 -3 1",
		"4, 4 -6 4 -1",
		"5, 5 -10 10 -5 1",
	})
	void getCoefficientsP2(int n, String expectedRaw) {
		val expected = StringUtil.spaceSeparatedInts(expectedRaw).mapToLong(x -> x).boxed().toList();
		assertThat(Day09.getCoefficientsP2(n)).isEqualTo(expected);
	}
}
