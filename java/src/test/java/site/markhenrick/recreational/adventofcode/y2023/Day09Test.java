package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import org.junit.jupiter.api.Test;
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
		"0 3 6 9 12 15, 18",
		"1 3 6 10 15 21, 28",
		"10 13 16 21 30 45, 68",
	})
	void extrapolateForwards(String inputRaw, int expected) {
		val input = StringUtil.spaceSeparatedInts(inputRaw).boxed().toList();
		val triangle = Day09.recursiveDeltas(input);
		assertThat(Day09.extrapolateForwards(triangle)).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource({
		"0 3 6 9 12 15, -3",
		"1 3 6 10 15 21, 0",
		"10 13 16 21 30 45, 5",
	})
	void extrapolateBackwards(String inputRaw, int expected) {
		val input = StringUtil.spaceSeparatedInts(inputRaw).boxed().toList();
		val triangle = Day09.recursiveDeltas(input);
		assertThat(Day09.extrapolateBackwards(triangle)).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource({
		"'',''",
		"5,''",
		"5 7, 2",
		"5 7 3, 2 -4",
		"10  13  16  21  30  45  68, 3   3   5   9  15  23",
		"3   3   5   9  15  23, 0   2   4   6   8",
		"0   2   4   6   8, 2   2   2   2",
		"2   2   2   2, 0   0   0",
		"0   0   0, 0   0",
		"0   0, 0",
		"0, ''",
	})
	void deltas(String inputRaw, String expectedRaw) {
		assertThat(Day09.deltas(StringUtil.spaceSeparatedInts(inputRaw).boxed().toList())).isEqualTo(StringUtil.spaceSeparatedInts(expectedRaw).boxed().toList());
	}

	@Test
	void parseLine() {
		assertThat(StringUtil.spaceSeparatedInts("10 13  16  21  30  45  68").boxed().toList())
			.containsExactly(10, 13, 16, 21, 30, 45, 68);
	}
}
