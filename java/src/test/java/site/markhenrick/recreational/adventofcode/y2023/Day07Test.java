package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.recreational.common.FunctionalUtil;
import site.markhenrick.recreational.common.Permutator;
import site.markhenrick.recreational.common.TestUtil;

import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static site.markhenrick.recreational.common.CollectionUtil.mutableListOf;

public class Day07Test {
	private static final String SAMPLE_INPUT = """
32T3K 765
T55J5 684
KK677 28
KTJJT 220
QQQJA 483
""";

	static Stream<Arguments> part1() {
		return Stream.of(
			arguments(6440, SAMPLE_INPUT),
			arguments(249390788, TestUtil.getResourceAsString("AoC/input/2023/day07.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part1(int expected, String input)
	{
		assertThat(Day07.part1(input)).isEqualTo(expected);
	}

	@Test
	void handComparator() {
		val hands = mutableListOf("32T3K", "T55J5", "KK677", "KTJJT", "QQQJA");
		hands.sort(Day07.HAND_COMPARATOR);
		assertThat(hands).containsExactly("32T3K", "KTJJT", "KK677", "T55J5", "QQQJA");
	}

	// Using String instead of Character to make joining easier
	private static Stream<Arguments> argumentsForType(Day07.HandType expectedType, Map<String, Integer> cards) {
		assert cards.keySet().stream().allMatch(string -> string.length() == 1);
		assert cards.values().stream().mapToInt(x -> x).sum() == 5;
		return Permutator.uniquePermutations(cards)
			.map(list -> String.join("", list))
			.map(permutation -> Arguments.of(permutation, expectedType));
	}

	static Stream<Arguments> getHandType() {
		return FunctionalUtil.concat(
			argumentsForType(Day07.HandType.FIVE, Map.of("A", 5)),
			argumentsForType(Day07.HandType.FOUR, Map.of("A", 4, "K", 1)),
			argumentsForType(Day07.HandType.FULL, Map.of("A", 3, "K", 2)),
			argumentsForType(Day07.HandType.THREE, Map.of("A", 3, "K", 1, "Q", 1)),
			argumentsForType(Day07.HandType.TWO_PAIR, Map.of("A", 2, "K", 2, "Q", 1)),
			argumentsForType(Day07.HandType.ONE_PAIR, Map.of("A", 2, "K", 1, "Q", 1, "J", 1)),
			argumentsForType(Day07.HandType.HIGH, Map.of("A", 1, "K", 1, "Q", 1, "J", 1, "T", 1))
		);
	}

	@ParameterizedTest
	@MethodSource
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
		assertComparisonsEqual(Day07.CARD_COMPARATOR.compare(card0, card1), expectedComparison);
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
