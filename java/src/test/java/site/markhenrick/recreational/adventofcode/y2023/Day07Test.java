package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import org.javatuples.Triplet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.recreational.common.Permutator;
import site.markhenrick.recreational.common.StringUtil;
import site.markhenrick.recreational.common.TestUtil;

import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static site.markhenrick.recreational.common.CollectionUtil.mutableListOf;

public class Day07Test {
	// TODO Still not really satisfied with the amount of duplication here. Needs refactoring
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

	static Stream<Arguments> part2() {
		return Stream.of(
				arguments(5905, SAMPLE_INPUT),
				arguments(248750248, TestUtil.getResourceAsString("AoC/input/2023/day07.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part2(int expected, String input)
	{
		assertThat(Day07.part2(input)).isEqualTo(expected);
	}

	@Test
	void handComparatorP1() {
		val hands = mutableListOf("32T3K", "T55J5", "KK677", "KTJJT", "QQQJA");
		hands.sort(Day07.HAND_COMPARATOR_P1);
		assertThat(hands).containsExactly("32T3K", "KTJJT", "KK677", "T55J5", "QQQJA");
	}

	@Test
	void handComparatorP2() {
		val hands = mutableListOf("32T3K", "T55J5", "KK677", "KTJJT", "QQQJA");
		hands.sort(Day07.HAND_COMPARATOR_P2);
		assertThat(hands).containsExactly("32T3K", "KK677", "T55J5", "QQQJA", "KTJJT");
	}

	private static Stream<Arguments> argumentsForType(Map<Character, Integer> cards, Day07.HandType expectedType) {
		return Permutator.uniquePermutations(cards)
			.map(list -> list.stream().collect(new StringUtil.StringCollector()))
			.map(permutation -> Arguments.of(permutation, expectedType));
	}

	private static Stream<Triplet<Map<Character, Integer>, Day07.HandType, Day07.HandType>> hands() {
		return Stream.of(
				// hand, P1 type, P2 type
				// jokerless - P1 == P2
				Triplet.with("AAAAA", Day07.HandType.FIVE, Day07.HandType.FIVE),
				Triplet.with("AAAAK", Day07.HandType.FOUR, Day07.HandType.FOUR),
				Triplet.with("AAAKK", Day07.HandType.FULL, Day07.HandType.FULL),
				Triplet.with("AAAKQ", Day07.HandType.THREE, Day07.HandType.THREE),
				Triplet.with("AAKKQ", Day07.HandType.TWO_PAIR, Day07.HandType.TWO_PAIR),
				Triplet.with("AAKQ9", Day07.HandType.ONE_PAIR, Day07.HandType.ONE_PAIR),
				Triplet.with("AKQ98", Day07.HandType.HIGH, Day07.HandType.HIGH),
				// jokers
				Triplet.with("JJJJJ", Day07.HandType.FIVE, Day07.HandType.FIVE),
				Triplet.with("JJJJK", Day07.HandType.FOUR, Day07.HandType.FIVE),
				Triplet.with("JJJKK", Day07.HandType.FULL, Day07.HandType.FIVE),
				Triplet.with("JJKKK", Day07.HandType.FULL, Day07.HandType.FIVE),
				Triplet.with("JKKKK", Day07.HandType.FOUR, Day07.HandType.FIVE),
				Triplet.with("AAAKJ", Day07.HandType.THREE, Day07.HandType.FOUR),
				Triplet.with("AAKJJ", Day07.HandType.TWO_PAIR, Day07.HandType.FOUR),
				Triplet.with("AKJJJ", Day07.HandType.THREE, Day07.HandType.FOUR),
				Triplet.with("AAKKJ", Day07.HandType.TWO_PAIR, Day07.HandType.FULL),
				Triplet.with("AAKQJ", Day07.HandType.ONE_PAIR, Day07.HandType.THREE),
				Triplet.with("AKQJJ", Day07.HandType.ONE_PAIR, Day07.HandType.THREE),
				Triplet.with("AKQ1J", Day07.HandType.HIGH, Day07.HandType.ONE_PAIR)
		)
				.map(triplet -> Triplet.with(Day07.countCards(triplet.getValue0()), triplet.getValue1(), triplet.getValue2()));
	}

	static Stream<Arguments> getHandTypeP1() {
		return hands().flatMap(triple -> argumentsForType(triple.getValue0(), triple.getValue1()));
	}

	static Stream<Arguments> getHandTypeP2() {
		return hands().flatMap(triple -> argumentsForType(triple.getValue0(), triple.getValue2()));
	}

	@ParameterizedTest
	@MethodSource
	void getHandTypeP1(String input, Day07.HandType expected) {
		assertThat(input).hasSize(Day07.HAND_SIZE); // meta
		assertThat(Day07.getHandType(input, false)).isEqualTo(expected);
	}

	@ParameterizedTest
	@MethodSource
	void getHandTypeP2(String input, Day07.HandType expected) {
		assertThat(input).hasSize(Day07.HAND_SIZE); // meta
		assertThat(Day07.getHandType(input, true)).isEqualTo(expected);
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
			"JJJJJ,	22222,	1,",
			"22222,	JJJJJ,	-1,",
	})
	void compareHandsLexicographicallyP1(String hand0, String hand1, int expectedComparison) {
		assertComparisonsEqual(Day07.compareHandsLexicographically(Day07.CARD_COMPARATOR_P1, hand0, hand1), expectedComparison);
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
			"JJJJJ,	22222,	-1,",
			"22222,	JJJJJ,	1,",
	})
	void compareHandsLexicographicallyP2(String hand0, String hand1, int expectedComparison) {
		assertComparisonsEqual(Day07.compareHandsLexicographically(Day07.CARD_COMPARATOR_P2, hand0, hand1), expectedComparison);
	}

	static Stream<Arguments> cardsForComparison(String strengths) {
		val numberOfCards = strengths.length();
		return IntStream.range(0, numberOfCards)
				.boxed()
				.flatMap(i -> IntStream.range(0, numberOfCards)
						.mapToObj(j -> Arguments.of(strengths.charAt(i), strengths.charAt(j), Integer.compare(i, j)))
				);
	}

	static Stream<Arguments> compareCardP1() {
		return cardsForComparison(Day07.CARD_STRENGTH_P1);
	}

	@ParameterizedTest
	@MethodSource
	void compareCardP1(char card0, char card1, int expectedComparison) {
		assertComparisonsEqual(Day07.CARD_COMPARATOR_P1.compare(card0, card1), expectedComparison);
	}

	static Stream<Arguments> compareCardP2() {
		return cardsForComparison(Day07.CARD_STRENGTH_P2);
	}

	@ParameterizedTest
	@MethodSource
	void compareCardP2(char card0, char card1, int expectedComparison) {
		assertComparisonsEqual(Day07.CARD_COMPARATOR_P2.compare(card0, card1), expectedComparison);
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
