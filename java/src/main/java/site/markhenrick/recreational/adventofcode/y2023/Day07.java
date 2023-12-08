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
	public static final Character ACE = 'A';
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

	// So the invariant that x.uniqueCardCount() > y.uniqueCardCount() <=> x > y does hold, meaning it would be possible
	// to have a comparator chain that does that first, and only looks deeper for FOUR/FULL and THREE/TWO_PAIR, but the
	// optimisation would be tiny (quite possible negative), and require more complex effort to avoid building `cardCounts`
	// twice (could do it by mapping to a LinkedHashMap early)
	static HandType getHandTypeP1(String hand) {
		assert hand.length() == HAND_SIZE;
		val cardCounts = new HashMap<Character, Integer>(HAND_SIZE);
		// TODO probably a stdlib function for this. Write one if not
		for (var i = 0; i < hand.length(); i++) {
			cardCounts.merge(hand.charAt(i), 1, Integer::sum);
		}
		val uniqueCardCount = cardCounts.size();
		switch (uniqueCardCount) {
			case 1:
				return HandType.FIVE;
			case 2: {
				val countOfOneType = cardCounts.get(hand.charAt(0));
				return countOfOneType == 1 || countOfOneType == 4 ? HandType.FOUR : HandType.FULL;
			}
			case 3: {
				var countOfOneType = cardCounts.get(hand.charAt(0));
				if (countOfOneType == 1) {
					countOfOneType = cardCounts.get(hand.charAt(1));
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
			val cardCounts = new HashMap<Character, Integer>(HAND_SIZE);
			for (var j = 0; j < hand.length(); j++) {
				cardCounts.merge(hand.charAt(j), 1, Integer::sum);
			}
			cardCounts.remove(JOKER);
			val modalCard = cardCounts.entrySet().stream()
				.max(Map.Entry.comparingByValue())
				.map(Map.Entry::getKey)
				.orElse(ACE);
			mutableHand = hand.replaceAll(STRING_JOKER, "" + modalCard);
			System.out.println("Transformed " + hand + " to " + mutableHand);
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
		HIGH, ONE_PAIR, TWO_PAIR, THREE, FULL, FOUR, FIVE,
	}
}
