package site.markhenrick.recreational.adventofcode.y2015;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.recreational.common.TestUtil;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Day02Test {
	private static final String SAMPLE_INPUT = """
2x3x4
1x1x10
""";
	private static final String MY_INPUT = TestUtil.getResourceAsString("AoC/input/2015/day02.txt");

	@ParameterizedTest
	@CsvSource({
		"2, 3, 4, 58",
		"1, 1, 10, 43",
	})
	void surfaceArea(final int l, final int w, final int h, final int expected) {
		final var input = List.of(l, w, h);
		assertThat(Day02.surfaceArea(input)).isEqualTo(expected);
	}

	@SuppressWarnings("unused")
	static Stream<Arguments> solvePart1() {
		return Stream.of(
			arguments(SAMPLE_INPUT, 58 + 43),
			arguments(MY_INPUT, 1598415)
		);
	}

	@ParameterizedTest
	@MethodSource
	void solvePart1(final CharSequence input, final int expected) {
		assertThat(Day02.SOLVE_PART_1.apply(input)).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource({
		"2, 3, 4, 34",
		"1, 1, 10, 14",
	})
	void ribbonRequired(final int l, final int w, final int h, final int expected) {
		final var input = List.of(l, w, h);
		assertThat(Day02.ribbonRequired(input)).isEqualTo(expected);
	}
	@SuppressWarnings("unused")
	static Stream<Arguments> solvePart2() {
		return Stream.of(
			arguments(SAMPLE_INPUT, 34 + 14),
			arguments(MY_INPUT, 3812909)
		);
	}

	@ParameterizedTest
	@MethodSource
	void solvePart2(final CharSequence input, final int expected) {
		assertThat(Day02.SOLVE_PART_2.apply(input)).isEqualTo(expected);
	}
}
