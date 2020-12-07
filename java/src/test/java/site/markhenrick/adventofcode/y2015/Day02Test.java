package site.markhenrick.adventofcode.y2015;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static site.markhenrick.adventofcode.common.TestUtil.getResourceAsString;

class Day02Test {
	private static final String SAMPLE_INPUT = """
2x3x4
1x1x10
""";
	private static final String MY_INPUT = getResourceAsString("input/2015/day02.txt");

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
		assertThat(Day02.solvePart1(input)).isEqualTo(expected);
	}
}
