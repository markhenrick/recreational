package site.markhenrick.recreational.adventofcode.y2023;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.recreational.common.TestUtil;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Day09AltTest {
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
		assertThat(Day09Alt.part1(input)).isEqualTo(expected);
	}
}
