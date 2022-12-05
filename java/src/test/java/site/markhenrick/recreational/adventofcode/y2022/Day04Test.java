package site.markhenrick.recreational.adventofcode.y2022;

import org.javatuples.Pair;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.recreational.common.TestUtil;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.javatuples.Pair.with;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Day04Test {
	private static final String SAMPLE_INPUT = """
2-4,6-8
2-3,4-5
5-7,7-9
2-8,3-7
6-6,4-6
2-6,4-8
""";

	static Stream<Arguments> oneFullyContains() {
		return Stream.of(
			arguments(with(0, 0), with(0, 0), true),
			arguments(with(1, 4), with(1, 4), true),
			arguments(with(1, 4), with(2, 4), true),
			arguments(with(1, 4), with(1, 3), true),
			arguments(with(1, 4), with(2, 3), true),
			arguments(with(1, 4), with(2, 2), true),
			arguments(with(1, 4), with(4, 4), true),
			arguments(with(1, 4), with(2, 5), false),
			arguments(with(1, 4), with(5, 6), false),
			arguments(with(1, 4), with(6, 7), false),
			// Sample input
			arguments(with(2, 4), with(6, 8), false),
			arguments(with(2, 3), with(4, 5), false),
			arguments(with(5, 7), with(7, 9), false),
			arguments(with(2, 8), with(3, 7), true),
			arguments(with(6, 6), with(4, 6), true),
			arguments(with(2, 6), with(4, 8), false)
		);
	}

	@ParameterizedTest
	@MethodSource("oneFullyContains")
	void oneFullyContainsForwards(Pair<Integer, Integer> left, Pair<Integer, Integer> right, boolean expected) {
		assertThat(Day04.oneFullyContains(left, right)).isEqualTo(expected);
	}

	@ParameterizedTest
	@MethodSource("oneFullyContains")
	void oneFullyContainsBackwards(Pair<Integer, Integer> left, Pair<Integer, Integer> right, boolean expected) {
		assertThat(Day04.oneFullyContains(right, left)).isEqualTo(expected);
	}

	static Stream<Arguments> overlap() {
		return Stream.of(
			arguments(with(0, 0), with(0, 0), true),
			arguments(with(1, 4), with(1, 4), true),
			arguments(with(1, 4), with(2, 4), true),
			arguments(with(1, 4), with(1, 3), true),
			arguments(with(1, 4), with(2, 3), true),
			arguments(with(1, 4), with(2, 2), true),
			arguments(with(1, 4), with(4, 4), true),
			arguments(with(1, 4), with(2, 5), true),
			arguments(with(1, 4), with(4, 5), true),
			arguments(with(1, 4), with(5, 6), false),
			arguments(with(1, 4), with(6, 7), false),
			// Sample input
			arguments(with(2, 4), with(6, 8), false),
			arguments(with(2, 3), with(4, 5), false),
			arguments(with(5, 7), with(7, 9), true),
			arguments(with(2, 8), with(3, 7), true),
			arguments(with(6, 6), with(4, 6), true),
			arguments(with(2, 6), with(4, 8), true)
		);
	}

	@ParameterizedTest
	@MethodSource("overlap")
	void overlapForwards(Pair<Integer, Integer> left, Pair<Integer, Integer> right, boolean expected) {
		assertThat(Day04.overlap(left, right)).isEqualTo(expected);
	}

	@ParameterizedTest
	@MethodSource("overlap")
	void overlapBackwards(Pair<Integer, Integer> left, Pair<Integer, Integer> right, boolean expected) {
		assertThat(Day04.overlap(right, left)).isEqualTo(expected);
	}

	static Stream<Arguments> part1() {
		return Stream.of(
			arguments(SAMPLE_INPUT, 2L),
			arguments(TestUtil.getResourceAsString("AoC/input/2022/day04.txt"), 540L)
		);
	}

	@ParameterizedTest
	@MethodSource
	void part1(String input, long expected) {
		assertThat(Day04.part1(input)).isEqualTo(expected);
	}

	static Stream<Arguments> part2() {
		return Stream.of(
			arguments(SAMPLE_INPUT, 4L),
			arguments(TestUtil.getResourceAsString("AoC/input/2022/day04.txt"), 872L)
		);
	}

	@ParameterizedTest
	@MethodSource
	void part2(String input, long expected) {
		assertThat(Day04.part2(input)).isEqualTo(expected);
	}
}
