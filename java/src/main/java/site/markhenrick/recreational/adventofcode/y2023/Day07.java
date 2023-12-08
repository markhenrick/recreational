package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import site.markhenrick.recreational.common.FunctionalUtil;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static site.markhenrick.recreational.common.StringUtil.LINE_SPLITTER;

public class Day07 {
	public static final int HAND_SIZE = 5;
	public static final Character JOKER = 'J';
	public static final String STRING_JOKER = "" + JOKER;
	public static final Map.Entry<Character, Integer> DEFAULT_ENTRY = Map.entry('A', 0);
	@SuppressWarnings("SpellCheckingInspection")
	public static final String CARD_STRENGTH_ASC = "23456789TJQKA";
	// String is too small for anything other than a linear search to be worth it
	public static Comparator<Character> CARD_COMPARATOR = Comparator.comparing(CARD_STRENGTH_ASC::indexOf);
	public static Comparator<String> HAND_COMPARATOR = Comparator.comparing(Day07::getHandTypeP1)
			.thenComparing(Day07::compareHandsLexicographically);

	static long part1(String input) {
		// really need that zip/enumerate now
		// TODO rewrite with streams when it exists
		// just like .map((i, rank) -> i * rank).sum()
		val hands = LINE_SPLITTER.apply(input)
			.map(Day07::parseLine)
			.sorted(Comparator.comparing(FunctionalUtil.Pair::l, HAND_COMPARATOR))
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

	// TODO actually use this
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

	// So the invariant that x.uniqueCardCount() > y.uniqueCardCount() <=> x > y does hold, meaning it would be possible
	// to have a comparator chain that does that first, and only looks deeper for FOUR/FULL and THREE/TWO_PAIR, but the
	// optimisation would be tiny (quite possible negative), and require more complex effort to avoid building `cardCounts`
	// twice (could do it by mapping to a LinkedHashMap early)
	static HandType getHandTypeP1(String hand) {
		assert hand.length() == HAND_SIZE;
		val counts = countCards(hand);
		val uniqueCardCount = counts.size();
		switch (uniqueCardCount) {
			case 1:
				return HandType.FIVE;
			case 2: {
				val countOfOneType = counts.get(hand.charAt(0));
				return countOfOneType == 1 || countOfOneType == 4 ? HandType.FOUR : HandType.FULL;
			}
			case 3: {
				var countOfOneType = counts.get(hand.charAt(0));
				if (countOfOneType == 1) {
					countOfOneType = counts.get(hand.charAt(1));
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

	static HandType getHandTypeP2(String hand) {
		// TODO temporary code to prove a concept, which hence includes duplication and extreme inefficiency
		var mutableHand = hand;
		if (hand.contains(STRING_JOKER)) {
			val counts = countCards(hand);
			counts.remove(JOKER);
			val modalCard = counts.entrySet().stream()
				.max(Map.Entry.comparingByValue())
				.orElse(DEFAULT_ENTRY)
				.getKey();
			mutableHand = hand.replaceAll(STRING_JOKER, "" + modalCard);
		}
		return getHandTypeP1(mutableHand);
	}

	static int compareHandsLexicographically(String hand0, String hand1) {
		assert hand0.length() == hand1.length();
		// This is a really stupid way to do it versus just a for loop
		// TODO still need that zip(with) function
		return IntStream.range(0, hand0.length())
				.map(i -> CARD_COMPARATOR.compare(hand0.charAt(i), hand1.charAt(i)))
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
