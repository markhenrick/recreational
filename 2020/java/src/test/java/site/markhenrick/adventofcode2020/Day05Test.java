package site.markhenrick.adventofcode2020;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.BitSet;
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

	@Test
	void listSeats() {
		final var expected = bitSetOf(567, 119, 820);
		assertThat(Day05.listSeats(SAMPLE_INPUT)).isEqualTo(expected);
	}

	@SuppressWarnings("unused")
	static Stream<Arguments> findMySeat() {
		return Stream.of(
			arguments(1, bitSetOf(0, 2)),
			arguments(1, bitSetOf(0, 2, 3)),
			arguments(2, bitSetOf(1, 3)),
			arguments(2, bitSetOf(1, 3, 4)),
			arguments(3, bitSetOf(1, 2, 4)),
			arguments(3, bitSetOf(1, 2, 4, 5))
		);
	}

	@ParameterizedTest
	@MethodSource
	void findMySeat(final int expected, final BitSet seats) {
		assert expected != 0;
		assert !seats.get(expected);
		assertThat(Day05.findMySeat(seats)).isEqualTo(expected);
	}

	@Test
	void solvePart2() {
		assertThat(Day05.solvePart2(MY_INPUT)).isEqualTo(599);
	}

	private static BitSet bitSetOf(final int... setBits) {
		final var bitset = new BitSet();
		Arrays.stream(setBits).forEach(bitset::set);
		return bitset;
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
