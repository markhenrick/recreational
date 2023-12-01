package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
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
				arguments(54985, TestUtil.getResourceAsString("AoC/input/2023/day01.txt"))
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
			"1abc2,1,2",
			"pqr3stu8vwx,3,8",
			"a1b2c3d4e5f,1,5",
			"treb7uchet,7,7",
	})
	void processLineP1(String line, int expectedFirst, int expectedSecond) {
		val result = Day01.p1Splitter(line);
		assertThat(result.getValue0()).isEqualTo(expectedFirst);
		assertThat(result.getValue1()).isEqualTo(expectedSecond);
	}

	@ParameterizedTest
	@CsvSource({
			"1abc2,1,2",
			"pqr3stu8vwx,3,8",
			"a1b2c3d4e5f,1,5",
			"treb7uchet,7,7",
			"two1nine,2,9",
			"eightwothree,8,3",
			"eightwothreeeightwo,8,2",
			"abcone2threexyz,1,3",
			"xtwone3four,2,4",
			"4nineeightseven2,4,2",
			"zoneight234,1,4",
			"7pqrstsixteen,7,6",
			"eighteight,8,8",
			"eight,8,8",
	})
	void processLineP2(String line, int expectedFirst, int expectedSecond) {
		val result = Day01.p2Splitter(line);
		assertThat(result.getValue0()).isEqualTo(expectedFirst);
		assertThat(result.getValue1()).isEqualTo(expectedSecond);
	}
}
