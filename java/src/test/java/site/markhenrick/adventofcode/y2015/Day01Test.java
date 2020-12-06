package site.markhenrick.adventofcode.y2015;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import site.markhenrick.adventofcode.common.TestUtil;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Day01Test {
	private static final String MY_INPUT = TestUtil.getResourceAsString("input/2015/day01.txt");

	@ParameterizedTest
	@CsvSource({
		"(()),		0",
		"(((,		3",
		"(()(()(,	3",
		"))(((((,	3",
		"()),		-1",
		"))(,		-1",
		"))),		-3",
		")())()),	-3",
	})
	void solvePart1Samples(final CharSequence input, final int expected) {
		assertThat(Day01.solvePart1(input)).isEqualTo(expected);
	}

	@Test
	void solvePart1Actual() {
		assertThat(Day01.solvePart1(MY_INPUT)).isEqualTo(280);
	}

	@ParameterizedTest
	@CsvSource({
		"),		1",
		"()()),	5",
	})
	void solvePart2Samples(final CharSequence input, final int expected) {
		assertThat(Day01.solvePart2(input)).isEqualTo(expected);
	}

	@Test
	void solvePart2Actual() {
		assertThat(Day01.solvePart2(MY_INPUT)).isEqualTo(1797);
	}
}
