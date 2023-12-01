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
	private static final String SAMPLE_INPUT = """
1abc2
pqr3stu8vwx
a1b2c3d4e5f
treb7uchet
""";

	static Stream<Arguments> part1() {
		return Stream.of(
				arguments(142, SAMPLE_INPUT),
				arguments(55130, TestUtil.getResourceAsString("AoC/input/2023/day01.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part1(int expected, String input)
	{
		assertThat(Day01.part1(input)).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource({
			"1abc2,12",
			"pqr3stu8vwx,38",
			"a1b2c3d4e5f,15",
			"treb7uchet,77",
	})
	void processOneLine(String line, int expected) {
		assertThat(Day01.processOneLine(line)).isEqualTo(expected);
	}
}
