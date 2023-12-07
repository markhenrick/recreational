package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static site.markhenrick.recreational.common.CollectionUtil.mutableListOf;

public class Day07Test {
	@Test
	void handComparator() {
		val hands = mutableListOf("32T3K", "T55J5", "KK677", "KTJJT", "QQQJA");
		hands.sort(Day07.HAND_COMPARATOR);
		assertThat(hands).containsExactly("32T3K", "KTJJT", "KK677", "T55J5", "QQQJA");
	}

	// TODO With a small enough alphabet it is probably feasible to generate an exhaustive test parameter stream
	// will probably need to write and test a permutations function first though (no libraries allowed!)
	@ParameterizedTest
	@CsvSource({
			"11111,FIVE",
			"11112,FOUR",
			"11211,FOUR",
			"11112,FOUR",
			"11222,FULL",
			"11122,FULL",
			"11212,FULL",
			"11123,THREE",
			"12232,THREE",
			"11223,TWO_PAIR",
			"12332,TWO_PAIR",
			"11234,ONE_PAIR",
			"12334,ONE_PAIR",
			"12345,HIGH",
			"54321,HIGH",
	})
	void getHandType(String input, Day07.HandType expected) {
		assertThat(Day07.getHandType(input)).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource({
			"33332,	2AAAA,	1",
			"2AAAA,	33332,	-1",
			"77888,	77788,	1",
			"77788,	77888,	-1",
			"KK677,	KTJJT,	1",
			"KTJJT,	KK677,	-1",
			"T55J5,	QQQJA,	-1",
			"QQQJA,	T55J5,	1",
	})
	void compareHandsLexicographically(String hand0, String hand1, int expectedComparison) {
		assertComparisonsEqual(Day07.compareHandsLexicographically(hand0, hand1), expectedComparison);
	}

	static Stream<Arguments> compareCard() {
		val numberOfCards = Day07.CARD_STRENGTH_ASC.length();
		return IntStream.range(0, numberOfCards)
				.boxed()
				.flatMap(i -> IntStream.range(0, numberOfCards)
						.mapToObj(j -> Arguments.of(Day07.CARD_STRENGTH_ASC.charAt(i), Day07.CARD_STRENGTH_ASC.charAt(j), Integer.compare(i, j)))
				);
	}

	@ParameterizedTest
	@MethodSource
	void compareCard(char card0, char card1, int expectedComparison) {
		assertComparisonsEqual(Day07.compareCard(card0, card1), expectedComparison);
	}

	private void assertComparisonsEqual(int actual, int expected) {
		if (expected == 0) {
			assertThat(actual).isEqualTo(0);
		} else if (expected < 0) {
			assertThat(actual).isLessThan(0);
		} else {
			assertThat(actual).isGreaterThan(0);
		}
	}
}
