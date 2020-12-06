package site.markhenrick.adventofcode.y2020;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static site.markhenrick.adventofcode.common.StringUtil.LINE_SPLITTER;
import static site.markhenrick.adventofcode.common.TestUtil.getResourceAsString;

class Day01Test {
	private static final List<Integer> SAMPLE_INPUT = List.of(1721, 979, 366, 299, 675, 1456);
	private static final List<Integer> MY_INPUT = LINE_SPLITTER.apply(getResourceAsString("input/2020/day01.txt"))
		.map(Integer::parseInt)
		.collect(Collectors.toList());

	@SuppressWarnings("unused")
	static Stream<Arguments> part1() {
		return Stream.of(
			arguments(1721 * 299, SAMPLE_INPUT),
			arguments(1010 * 1010, List.of(1010, 1010)),
			arguments(2019, List.of(1, 2019)),
			arguments(2019, List.of(2019, 1)),
			arguments(2019, List.of(1, 0, 2019)),
			arguments(357504, MY_INPUT)
		);
	}

	@ParameterizedTest
	@MethodSource
	void part1(final int expected, final Iterable<Integer> input) {
		assertThat(Day01.part1(input)).isEqualTo(expected);
	}

	@SuppressWarnings("unused")
	static Stream<Arguments> part2() {
		return Stream.of(
			arguments(979 * 366 * 675, SAMPLE_INPUT),
			arguments(2018, List.of(1, 1, 2018)),
			arguments(2018, List.of(1, 2018, 1)),
			arguments(12747392, MY_INPUT)
		);
	}

	@ParameterizedTest
	@MethodSource
	void part2(final int expected, final List<Integer> input)
	{
		assertThat(Day01.part2(input)).isEqualTo(expected);
	}
}
