package site.markhenrick.recreational.adventofcode.y2021;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.recreational.common.TestUtil;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Day09Test {
	private static final String SAMPLE_INPUT = """
2199943210
3987894921
9856789892
8767896789
9899965678
""";

	static Stream<Arguments> part1() {
		return Stream.of(
			arguments(15L, SAMPLE_INPUT),
			arguments(516L, TestUtil.getResourceAsString("AoC/input/2021/day09.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part1(long expected, String input) {
		assertThat(Day09.part1(input)).isEqualTo(expected);
	}
}
