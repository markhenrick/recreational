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

public class Day06Test {
	private static final String SAMPLE_INPUT = """
Time:      7  15   30
Distance:  9  40  200
""";

	static Stream<Arguments> part1() {
		return Stream.of(
				arguments(288L, SAMPLE_INPUT),
				arguments(1660968L, TestUtil.getResourceAsString("AoC/input/2023/day06.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part1(long expected, String input)
	{
		assertThat(Day06.part1(input)).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource({
			"7,		9,		4",
			"15,	40,		8",
			"30,	200,	9",
	})
	void countValids(long duration, long record, long expected) {
		assertThat(Day06.countValids(duration, record)).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource({
			"-1,	7,	-9,		1.6972,	5.3028",
			"-1,	15,	-40,	3.4689,	11.531",
			"-1,	30,	-200,	10,		20",
	})
	void quadraticRoots(double a, double b, double c, double firstRoot, double secondRoot) {
		val roots = Day06.quadraticRoots(a, b, c);
		assertThat(Math.abs(roots.l() - firstRoot)).isLessThan(1.0);
		assertThat(Math.abs(roots.r() - secondRoot)).isLessThan(1.0);
	}

	@ParameterizedTest
	@CsvSource({
			"1.6972,	5.3028,	4",
			"3.4689,	11.531,	8",
			"10,		20,		9", // exclusive!
			"9.9,		20,		10",
			"10.1,		20,		9",
			"10,		19.9,	9",
			"10,		20.1,	10",
	})
	void countIntegers(double start, double end, long expected) {
		assertThat(Day06.countIntegers(start, end)).isEqualTo(expected);
	}
}
