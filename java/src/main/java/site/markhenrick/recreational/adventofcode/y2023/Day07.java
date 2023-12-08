package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import site.markhenrick.recreational.common.FunctionalUtil;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static site.markhenrick.recreational.common.StringUtil.LINE_SPLITTER;

public class Day07 {
	public static final int HAND_SIZE = 5;
	public static final char JOKER = 'J';
	public static final Map.Entry<Character, Integer> DEFAULT_ENTRY = Map.entry('A', 0);
	@SuppressWarnings("SpellCheckingInspection")
	public static final String CARD_STRENGTH_P1 = "23456789TJQKA";
	public static final String CARD_STRENGTH_P2 = "J23456789TQKA";
	// String is too small for anything other than a linear search to be worth it
	public static Comparator<Character> CARD_COMPARATOR_P1 = Comparator.comparing(CARD_STRENGTH_P1::indexOf);
	public static Comparator<Character> CARD_COMPARATOR_P2 = Comparator.comparing(CARD_STRENGTH_P2::indexOf);
	public static Comparator<String> HAND_COMPARATOR_P1 = Comparator.comparing((String hand) -> getHandType(hand, false))
			.thenComparing((x, y) -> compareHandsLexicographically(CARD_COMPARATOR_P1, x, y));
	public static Comparator<String> HAND_COMPARATOR_P2 = Comparator.comparing((String hand) -> getHandType(hand, true))
			.thenComparing((x, y) -> compareHandsLexicographically(CARD_COMPARATOR_P2, x, y));

	static long part1(String input) {
		// really need that zip/enumerate now
		// TODO rewrite with streams when it exists
		// just like .map((i, rank) -> i * rank).sum()
		val hands = LINE_SPLITTER.apply(input)
			.map(Day07::parseLine)
			.sorted(Comparator.comparing(FunctionalUtil.Pair::l, HAND_COMPARATOR_P1))
			.map(FunctionalUtil.Pair::r)
			.toList();
		long total = 0;
		for (var i = 0; i < hands.size(); i++) {
			// explicit cast to suppress warning
			total += (long) (i + 1) * hands.get(i);
		}
		return total;
	}

	// TODO probably a stdlib function for this. Write one if not
	static Map<Character, Integer> countCards(String hand) {
		val counts = new HashMap<Character, Integer>(HAND_SIZE);
		for (var i = 0; i < hand.length(); i++) {
			counts.merge(hand.charAt(i), 1, Integer::sum);
		}
		return counts;
	}

	/** Mutates input in place, but also returns it for convenience in streams */
	static Map<Character, Integer> replaceJokersWithModalCard(Map<Character, Integer> counts) {
		assert mapSumsToFive(counts);
		val jokerCount = counts.get(JOKER);
		if (jokerCount == null) {
			return counts;
		}
		counts.remove(JOKER);
		val modalEntry = counts.entrySet().stream()
				.max(Map.Entry.comparingByValue())
				.orElse(DEFAULT_ENTRY); // For the special case of JJJJJ
		counts.put(modalEntry.getKey(), modalEntry.getValue() + jokerCount);
		assert mapSumsToFive(counts);
		return counts;
	}

	static <T> boolean mapSumsToFive(Map<T, Integer> map) {
		return map.values().stream()
				.mapToInt(x -> x)
				.sum() == HAND_SIZE;
	}

	static HandType getHandType(String hand, boolean considerJokers) {
		val countMap = countCards(hand);
		if (considerJokers) {
			replaceJokersWithModalCard(countMap);
		}
		val counts = countMap.values().stream().toList();
		return getHandType(counts);
	}

	static HandType getHandType(List<Integer> counts) {
		assert counts.stream().mapToInt(x -> x).sum() == HAND_SIZE;
		val uniqueCardCount = counts.size();
		switch (uniqueCardCount) {
			case 1:
				return HandType.FIVE;
			case 2: {
				val countOfOneType = counts.get(0);
				return countOfOneType == 1 || countOfOneType == 4 ? HandType.FOUR : HandType.FULL;
			}
			case 3: {
				var countOfOneType = counts.get(0);
				if (countOfOneType == 1) {
					countOfOneType = counts.get(1);
				}
				return countOfOneType == 3 || countOfOneType == 1 ? HandType.THREE : HandType.TWO_PAIR;
			}
			case 4:
				return HandType.ONE_PAIR;
			case 5:
				return HandType.HIGH;
			default:
				throw new AssertionError("Unexpected uniqueCardCount = " + uniqueCardCount);
		}
	}

	static int compareHandsLexicographically(Comparator<Character> cardComparator, String hand0, String hand1) {
		assert hand0.length() == hand1.length();
		// This is a really stupid way to do it versus just a for loop
		// TODO still need that zip(with) function
		return IntStream.range(0, hand0.length())
				.map(i -> cardComparator.compare(hand0.charAt(i), hand1.charAt(i)))
				.filter(x -> x != 0)
				.findFirst()
				.getAsInt();
	}

	private static FunctionalUtil.Pair<String, Integer> parseLine(String line) {
		var parts = line.split(" ");
		assert parts.length == 2;
		return new FunctionalUtil.Pair<>(parts[0], Integer.parseInt(parts[1]));
	}

	enum HandType {
		// ASC order

		/**
		 * All cards are distinct. E.g. 23456
		 */
		HIGH,

		/**
		 * Two common cards. Three distinct. E.g. A23A4
		 */
		ONE_PAIR,

		/**
		 * Two different pairs of common cards. One other. E.g. 23432
		 */
		TWO_PAIR,

		/**
		 * Three common cards. Two distinct. E.g. TTT98
		 */
		THREE,

		/**
		 * A triple and a pair. No distinct. E.g. 23332
		 */
		FULL,

		/**
		 * Four common. One other. E.g. AA8AA
		 */
		FOUR,

		/**
		 * All cards the same. E.g. AAAAA
		 */
		FIVE,
	}
}
