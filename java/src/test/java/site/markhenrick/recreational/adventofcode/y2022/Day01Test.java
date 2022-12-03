package site.markhenrick.recreational.adventofcode.y2022;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.recreational.common.TestUtil;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Day01Test
{
	private static final String SAMPLE_INPUT = """
1000
2000
3000

4000

5000
6000

7000
8000
9000

10000
""";

	static Stream<Arguments> part1() {
		return Stream.of(
				arguments(24000, SAMPLE_INPUT),
				arguments(74198, TestUtil.getResourceAsString("AoC/input/2022/day01.txt"))
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
				arguments(45000, SAMPLE_INPUT),
				arguments(209914, TestUtil.getResourceAsString("AoC/input/2022/day01.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part2(int expected, String input)
	{
		assertThat(Day01.part2(input)).isEqualTo(expected);
	}
}
