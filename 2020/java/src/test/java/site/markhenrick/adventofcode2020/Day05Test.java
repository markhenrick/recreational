package site.markhenrick.adventofcode2020;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static site.markhenrick.adventofcode2020.common.TestUtil.getResourceAsString;

// "No assertions" inspection is false-positiving for some reason. Perhaps it's related to the IntelliJ upgrade
@SuppressWarnings({"SpellCheckingInspection", "JUnitTestMethodWithNoAssertions"})
class Day05Test {
	private static final String SAMPLE_INPUT = """
BFFFBBFRRR
FFFBBBFRRR
BBFFBBFRLL
""";
	private static final String MY_INPUT = getResourceAsString("input/day05.txt");

	@ParameterizedTest(name = "{0} {1} {2}")
	@CsvSource({
		"BFFFBBFRRR, EQ, BFFFBBFRRR",
		"BFFFBBFRRR, GT, FFFBBBFRRR",
		"BFFFBBFRRR, LT, BBFFBBFRLL",
		"FFFBBBFRRR, LT, BFFFBBFRRR",
		"FFFBBBFRRR, EQ, FFFBBBFRRR",
		"FFFBBBFRRR, LT, BBFFBBFRLL",
		"BBFFBBFRLL, GT, BFFFBBFRRR",
		"BBFFBBFRLL, GT, FFFBBBFRRR",
		"BBFFBBFRLL, EQ, BBFFBBFRLL",
	})
	void compareSeat(final String l, final Ordering relation, final String r) {
		assertThat(Day05.SEAT_COMPARATOR.compare(l, r)).matches(relation.matcher());
	}

	@ParameterizedTest(name = "{0} = {1}")
	@CsvSource({
		"BFFFBBFRRR, 567",
		"FFFBBBFRRR, 119",
		"BBFFBBFRLL, 820",
	})
	void decodeSeat(final String seat, final int expected) {
		assertThat(Day05.decodeSeat(seat)).isEqualTo(expected);
	}

	@SuppressWarnings("unused")
	static Stream<Arguments> solvePart1() {
		return Stream.of(
			arguments(SAMPLE_INPUT, 820),
			arguments(MY_INPUT, 850)
		);
	}

	@ParameterizedTest
	@MethodSource
	void solvePart1(final String input, final int expected) {
		assertThat(Day05.solvePart1(input)).isEqualTo(expected);
	}

	enum Ordering {
		LT, EQ, GT;

		// Unfortunately JUnit doesn't actually use this...
		public String toString() {
			return switch (this) {
				case LT -> "<";
				case EQ -> "=";
				case GT -> ">";
			};
		}

		public Predicate<Integer> matcher() {
			return input -> switch (this) {
				case LT -> input < 0;
				case EQ -> input == 0;
				case GT -> input > 0;
			};
		}
	}
}
