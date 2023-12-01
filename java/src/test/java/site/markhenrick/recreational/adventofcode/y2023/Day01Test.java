package site.markhenrick.recreational.adventofcode.y2023;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.recreational.common.TestUtil;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Day01Test {
	private static final String SAMPLE_INPUT_P1 = """
1abc2
pqr3stu8vwx
a1b2c3d4e5f
treb7uchet
""";

	private static final String SAMPLE_INPUT_P2 = """
two1nine
eightwothree
abcone2threexyz
xtwone3four
4nineeightseven2
zoneight234
7pqrstsixteen
""";

	static Stream<Arguments> part1() {
		return Stream.of(
				arguments(142, SAMPLE_INPUT_P1),
				arguments(55130, TestUtil.getResourceAsString("AoC/input/2023/day01.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part1(int expected, String input)
	{
		assertThat(Day01.part1(input)).isEqualTo(expected);
	}

	static Stream<Arguments> part2() {
		return Stream.of(
				arguments(142, SAMPLE_INPUT_P1),
				arguments(281, SAMPLE_INPUT_P2),
				arguments(55130, TestUtil.getResourceAsString("AoC/input/2023/day01.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part2(int expected, String input)
	{
		assertThat(Day01.part2(input)).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource({
			"1abc2,12",
			"pqr3stu8vwx,38",
			"a1b2c3d4e5f,15",
			"treb7uchet,77",
	})
	void processLineP1(String line, int expected) {
		assertThat(Day01.processLineWith(Day01::p1Splitter).applyAsInt(line)).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource({
			"1abc2,12",
			"pqr3stu8vwx,38",
			"a1b2c3d4e5f,15",
			"treb7uchet,77",
			"two1nine,29",
			"eightwothree,83",
			"abcone2threexyz,13",
			"xtwone3four,24",
			"4nineeightseven2,42",
			"zoneight234,14",
			"7pqrstsixteen,76"
	})
	void processLineP2(String line, int expected) {
		assertThat(Day01.processLineWith(Day01::p2Splitter).applyAsInt(line)).isEqualTo(expected);
	}
}
